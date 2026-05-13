package com.is.bpri.bproductAddInf;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.bproductAddInf.Parameter;
import com.is.bpri.bproductAddInf.ParameterGroup;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class bproductAddInfService {

	public static List<ParameterGroup> getParametergroup(int bpr_id) {
		List<ParameterGroup> list = new ArrayList<ParameterGroup>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM SS_BPR_PARAM_GROUPS g where exists (SELECT 'x' FROM BPRODUCT_ADDINFO_TEMPLATE t where t.bpr_type = ? and g.id = t.param_group_id) order by ord");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ParameterGroup(rs.getLong("id"), rs
						.getString("name_en"), rs.getString("name_ru"), rs
						.getString("name_uz"), rs.getInt("is_open"), rs
						.getInt("ord")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<Parameter> getParameters(int bpr_type) {
		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("  select distinct decode(a.bpr_type, null, ?, a.bpr_type) bpr_type, "
					+ "a.param_value, "
					+ "t.*, "
					+ "decode(a.bpr_type, null, 1, 0) is_new "
					+ "from BPRODUCT_ADDINFO_TEMPLATE t, "
					+ "(select * "
					+ "from bpr_addinfo ca "
					+ "where ca.bpr_type = ?) a "
					+ "where t.bpr_type = ? "
					+ "and a.bpr_type(+) = t.bpr_type "
					+ "and a.param_group_id(+) = t.param_group_id "
					+ "and a.param_id(+) = t.param_id "
					+ "order by t.param_group_id, t.param_ord   ");
			// ps.setInt(1, id);
			ps.setInt(1, bpr_type);
			ps.setInt(2, bpr_type);
			ps.setInt(3, bpr_type);
			// ps.setInt(4, bpr_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parameter(rs.getInt("id"), rs.getInt("bpr_type"),
						rs.getLong("param_id"), rs.getString("param_name"), rs
								.getString("param_name_en"), rs
								.getString("param_name_ru"), rs
								.getString("param_name_uz"), rs
								.getString("param_select"), rs
								.getString("param_type"), rs
								.getString("param_mask"), rs
								.getString("param_def_value"), rs
								.getLong("param_ord"), rs
								.getInt("param_mandatory"), rs
								.getLong("param_group_id"),
						rs.getInt("is_new"), rs.getString("param_value")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<Parameter> getParametersByGroup(
			List<Parameter> parameters, ParameterGroup group) {
		List<Parameter> list = new ArrayList<Parameter>();
		for (Parameter parameter : parameters) {
			if (parameter.getParam_group_id() == group.getId()) {
				list.add(parameter);
			}
		}
		return list;
	}

	public static String setParamsInSQL(List<Parameter> params, String select) {
		String res = select;
		res = setParamsInSQL_Param(params, res);
		res = setParamsInSQL_Sharp(params, res);
		return res;
	}

	public static String setParamsInSQL2(List<Parameter> params, String select,
			String br, String cl_id) {
		List<Parameter> param = params;
		param.add(new Parameter(-1, 0, 2L, "BRANCH", "Филиал", "Филиал",
				"Филиал", "", "", "", br, -1L, 1, 0L, 1, br));

		param.add(new Parameter(-1, 0, 2L, "CLIENT_CODE", "Клиент", "Клиент",
				"Клиент", "", "", "", cl_id, -1L, 1, 0L, 1, cl_id));
		String res = select;
		res = setParamsInSQL_Param(param, res);
		res = setParamsInSQL_Sharp(param, res);
		return res;
	}

	public static String setParamsInSQLForCard(String select, String br,
			String cl_id, String card_number) {
		List<Parameter> param = new ArrayList<Parameter>();
		param.add(new Parameter(-1, 0, 2L, "BRANCH", "Филиал", "Филиал",
				"Филиал", "", "", "", br, -1L, 1, 0L, 1, br));

		param.add(new Parameter(-1, 0, 2L, "CLIENT_CODE", "Клиент", "Клиент",
				"Клиент", "", "", "", cl_id, -1L, 1, 0L, 1, cl_id));

		param.add(new Parameter(-1, 0, 2L, "CARD_NUMBER", "№ карты", "№ карты",
				"№ карты", "", "", "", card_number, -1L, 1, 0L, 1, card_number));

		String res = select;
		res = setParamsInSQL_Param(param, res);
		res = setParamsInSQL_Sharp(param, res);
		return res;
	}

	public static String setParamsInSQL_Param(List<Parameter> params,
			String select) {
		int k = 0; // позиция "PARAM.GETPARAM('"
		int m = 0; // позиция символа ',' или ')' или ' '
		int m1 = 0; // позиция символа ',' или ')' или ' '
		int l = 0; // длина строки селекта
		String cur_param = ""; // наименование заменяемого параметра
		String cur_param_val = ""; // значение заменяемого параметра
		String str = select; // селект с параметрами
		try {
			l = str.length();
			while (k < l + 1) {
				k = str.indexOf("PARAM.GETPARAM('");
				if (k == -1) {
					k = l + 1;
				} else {
					// Поиск окончания наименования параметра
					m = str.indexOf("')", k);

					m1 = str.indexOf(",", k);
					if ((m > m1 && m1 != -1) || m == -1) {
						m = m1;
					}
					m1 = str.indexOf(")", k);
					if ((m > m1 && m1 != -1) || m == -1) {
						m = m1;
					}

					if (m == -1) {
						m = l;
						throw new Exception("SELECT PARAMS NOT TRUE! param:"
								+ str.substring(k + 16, m) + "; SQL = "
								+ select);
					}
					// System.out.println("*** k = "+k+" m = "+m);
					cur_param = str.substring(k + 16, m);
					cur_param_val = getParamValue(params, cur_param);
					// System.out.println("*** '"+cur_param+"' = '"+cur_param_val+"' "+"PARAM.GETPARAM('"+cur_param+"')");
					str = str.replace("PARAM.GETPARAM('" + cur_param + "')",
							(cur_param_val == null ? "null" : "'"
									+ cur_param_val + "'"));
					// str = str.replaceAll("PARAM.GETPARAM('"+cur_param+"')",
					// (cur_param_val==null?"null":"'"+cur_param_val+"'"));
					// System.out.println("*** '"+str);
					if (cur_param_val.equals("")) {
						str = "";
						k = l + 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}

	public static String setParamsInSQL_Sharp(List<Parameter> params,
			String select) {
		int k = 0; // позиция символа '#'
		int m = 0; // позиция символа ',' или ')' или ' '
		int m1 = 0; // позиция символа ',' или ')' или ' '
		int l = 0; // длина строки селекта
		String cur_param = ""; // наименование заменяемого параметра
		String cur_param_val = ""; // значение заменяемого параметра
		String str = select; // селект с параметрами
		try {
			l = str.length();
			while (k < l + 1) {
				k = str.indexOf("#");
				if (k == -1) {
					k = l + 1;
				} else {
					// Поиск окончания наименования параметра
					m = str.indexOf(" ", k);
					m1 = str.indexOf(",", k);
					if ((m > m1 && m1 != -1) || m == -1) {
						m = m1;
					}
					m1 = str.indexOf(")", k);
					if ((m > m1 && m1 != -1) || m == -1) {
						m = m1;
					}
					if (m == -1) {
						m = l;
					}
					// System.out.println("*** k = "+k+" m = "+m);
					cur_param = str.substring(k + 1, m);
					cur_param_val = getParamValue(params, cur_param);
					// System.out.println("*** "+cur_param+" = "+cur_param_val);
					str = str.replaceAll("#" + cur_param,
							(cur_param_val == null ? "null" : "'"
									+ cur_param_val + "'"));
					if (cur_param_val.equals("")) {
						str = "";
						k = l + 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}

	public static String getParamValue(List<Parameter> params, String param) {
		String param_value = "";
		Parameter par = null;
		for (int i = 0; i < params.size(); i++) {
			par = (Parameter) params.get(i);
			// System.out.println(par.getPar_name()+" = '"+par.getPar_value()+"'; par_found = "+param);
			if (par.getParam_name().equals(param)) {
				if (par.getParam_value() != null) {
					param_value = par.getParam_value();
				}
			}
		}
		return param_value;
	}

	public static List<RefData> getListForCombobox(String str, String alias,Res res) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Statement ps = null;
		try {
			ISLogger.getLogger().error("Вот селект = "+str);
			ISLogger.getLogger().error("Вот схема = "+alias);
			ISLogger.getLogger().error("Вот бранч = "+res.getName());
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			ps = c.createStatement();
//			PreparedStatement ps = c.prepareStatement(str);
			rs = ps.executeQuery(str);
			System.out.println(str);
			while (rs.next()) {
				ISLogger.getLogger().error("Вот результ = "+rs.getString(1));
				list.add(new RefData(rs.getString(1), rs.getString(2)));
			}
			ISLogger.getLogger().error("Вот размер листа = "+list.size());
		} catch (SQLException e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getListForCombobox(String str, Boolean showErr,
			String alias) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			ps = c.prepareStatement(str);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			if (showErr)
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			else ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Boolean saveAddInfo(List<Parameter> parameters, String cl_id) {
		Boolean res = false;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			for (Parameter parameter : parameters) {
				System.out.println("В ЦИКЛЕ");
				if (parameter.getIsnew() == 1) {
					System.out.println("ПОПАЛ");
					res = create(c, parameter, cl_id);
				} else {
					System.out.println("ПОПАЛ!!!");
					res = update(c, parameter, cl_id);
				}
				if (res == false) {
					c.rollback();
					return res;
				}
			}
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static List<RefData> getCardNumber(String card,String alias){
		return Utils.getRefData("select bd.state data,bd.detail_id label from bproduct_desc bd, bproduct b where bd.id = b.id and bd.detail_group=117 and bd.detail_id="+card, alias);
	}

	public static Boolean create(Connection c, Parameter parameter, String cl_id) {
		Boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("INSERT INTO bpr_addinfo (id, bpr_type, param_group_id, param_id, param_value, client_code) VALUES (?, ?, ?, ?, ?, ?) ");
			ps.setInt(1, parameter.getId());
			ps.setInt(2, parameter.getBpr_type());
			ps.setLong(3, parameter.getParam_group_id());
			ps.setLong(4, parameter.getParam_id());
			ps.setString(5, parameter.getParam_value());
			ps.setString(6, cl_id);
			ps.executeUpdate();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = false;
		} finally {
			Utils.close(ps);
		}
		return res;
	}

	public static Boolean update(Connection c, Parameter parameter, String cl_id) {
		Boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("UPDATE bpr_addinfo SET param_value=?, client_code=? WHERE id=? and bpr_type=? and param_group_id=? and param_id=? ");
			ps.setString(1, parameter.getParam_value());
			ps.setString(2, cl_id);
			ps.setInt(3, parameter.getId());
			ps.setInt(4, parameter.getBpr_type());
			ps.setLong(5, parameter.getParam_group_id());
			ps.setLong(6, parameter.getParam_id());
			ps.executeUpdate();
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = false;
		} finally {
			Utils.close(ps);
		}
		return res;
	}

	public static void remove(String alias, int bpr_type) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("DELETE FROM bpr_addinfo WHERE bpr_type=?");
			ps.setInt(1, bpr_type);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static String getClient_code(String alias) {
		Connection c = null;
		String res = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select b.client_code label from bpr_addinfo b where id = 1 ");
			rs = ps.executeQuery();
			rs.next();
			res = rs.getString("label");
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

}
