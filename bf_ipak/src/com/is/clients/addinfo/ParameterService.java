package com.is.clients.addinfo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.clients.addinfo.list.ParameterList;
import com.is.customer_.sap.EmergencyMode;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class ParameterService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM (select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,  nvl(t.param_visible, 1) param_visibility,   t.*, decode(a.branch, null, 1, 0) isnew   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id) ";
	private static Logger log = ISLogger.getLogger();

	public static Res setApprove(ClientAddinfoParameter clientadd, int uid, int act) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement psbank = null;
		PreparedStatement psins = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"UPDATE CLIENT_ADDINFO_PARAMETERS p SET STATE = ?  WHERE p.client_type = ? and p.branch = ? and p.client_id = ?");
			ps.setLong(1, clientadd.getState());
			ps.setString(2, clientadd.getClient_type());
			ps.setString(3, clientadd.getBranch());
			ps.setString(4, clientadd.getClient_id());
			int r = ps.executeUpdate();
			if (r == 1) {
				String sql = "";
				if (clientadd.getClient_type().equalsIgnoreCase("P")) {
					sql = "UPDATE cl_add_01 p SET STATE = ? WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("J")) {
					sql = "UPDATE cl_add_02 p SET STATE = ? WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("I")) {
					sql = "UPDATE cl_add_03 p SET STATE = ? WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("N")) {
					sql = "UPDATE cl_add_04 p SET STATE = ? WHERE p.branch = ? and p.id = ? ";
				}
				psbank = c.prepareStatement(sql);

				// 05-03-2018
				psbank.setLong(1, clientadd.getState());
				psbank.setString(2, clientadd.getBranch());
				psbank.setLong(3, clientadd.getId_cl_add());

				if (!clientadd.getClient_type().equalsIgnoreCase("N")) {
					psbank.setString(4, clientadd.getClient_id());
				}
				r = psbank.executeUpdate();
				if (r == 1) {
					psins = c.prepareStatement(
							"insert into cl_add_hist(branch,id_cl_add,act,emp_id,date_oper) values(?, ?, ?, ?,sysdate)");
					psins.setString(1, clientadd.getBranch());
					psins.setLong(2, clientadd.getId_cl_add());
					psins.setInt(3, act);
					psins.setInt(4, uid);
					psins.executeUpdate();
					c.commit();
					res = new Res(0, "Ok");
				} else {
					res = new Res(1,
							"Утверждение невозможно, количество записей не соответствует действительному cl_add = "
									+ r);
				}
			} else {
				res = new Res(1,
						"Утверждение невозможно, количество записей не соответствует действительному p = " + r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			res = new Res(1, e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(psbank);
			DbUtils.closeStmt(psins);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res delete(ClientAddinfoParameter clientadd, int uid, int act) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement psbank = null;
		PreparedStatement psins = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("" + "UPDATE CLIENT_ADDINFO_PARAMETERS p SET STATE = ?  "
					+ "WHERE p.client_type = ? and p.branch = ? " + "and p.client_id = ?");
			ps.setLong(1, clientadd.getState());
			ps.setString(2, clientadd.getClient_type());
			ps.setString(3, clientadd.getBranch());
			ps.setString(4, clientadd.getClient_id());
			int r = ps.executeUpdate();
			if (r == 1) {
				String sql = "";
				if (clientadd.getClient_type().equalsIgnoreCase("P")) {
					sql = "UPDATE cl_add_01 p SET STATE = ?, " + "risk_degree = 1, " + "risk_degree_detail = null, "
							+ "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("J")) {
					sql = "UPDATE cl_add_02 p " + "SET STATE = ?, " + "risk_degree = 1, "
							+ "risk_degree_detail = null, " + "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("I")) {
					sql = "UPDATE cl_add_03 p " + "SET STATE = ?, " + "risk_degree = 1, "
							+ "risk_degree_detail = null, " + "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				}
				psbank = c.prepareStatement(sql);
				psbank.setLong(1, clientadd.getState());
				psbank.setString(2, clientadd.getBranch());
				psbank.setLong(3, clientadd.getId_cl_add());
				psbank.setString(4, clientadd.getClient_id());
				r = psbank.executeUpdate();
				if (r == 1) {
					psins = c.prepareStatement(
							"insert into cl_add_hist(branch,id_cl_add,act,emp_id,date_oper) values(?, ?, ?, ?,sysdate)");
					psins.setString(1, clientadd.getBranch());
					psins.setLong(2, clientadd.getId_cl_add());
					psins.setInt(3, act);
					psins.setInt(4, uid);
					psins.executeUpdate();
					c.commit();
					res = new Res(0, "Ok");
				} else {
					res = new Res(1,
							"Удаление невозможно, количество записей не соответствует действительному cl_add = " + r);
				}
			} else {
				res = new Res(1, "Удаление невозможно, количество записей не соответствует действительному p = " + r);
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			res = new Res(1, e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(psbank);
			DbUtils.closeStmt(psins);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res restore(ClientAddinfoParameter clientadd, int uid, int act) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement psbank = null;
		PreparedStatement psins = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"UPDATE CLIENT_ADDINFO_PARAMETERS p SET STATE = ?  WHERE p.client_type = ? and p.branch = ? and p.client_id = ?");
			ps.setLong(1, clientadd.getState());
			ps.setString(2, clientadd.getClient_type());
			ps.setString(3, clientadd.getBranch());
			ps.setString(4, clientadd.getClient_id());
			int r = ps.executeUpdate();
			if (r == 1) {
				String sql = "";
				if (clientadd.getClient_type().equalsIgnoreCase("P")) {
					sql = "UPDATE cl_add_01 p " + "SET STATE = ?," + "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("J")) {
					sql = "UPDATE cl_add_02 p " + "SET STATE = ?," + "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				} else if (clientadd.getClient_type().equalsIgnoreCase("I")) {
					sql = "UPDATE cl_add_03 p " + "SET STATE = ?," + "risk_date = sysdate, " + "date_change = sysdate "
							+ "WHERE p.branch = ? and p.id = ? and p.client_id = ? ";
				}
				psbank = c.prepareStatement(sql);
				psbank.setLong(1, clientadd.getState());
				psbank.setString(2, clientadd.getBranch());
				psbank.setLong(3, clientadd.getId_cl_add());
				psbank.setString(4, clientadd.getClient_id());
				r = psbank.executeUpdate();
				if (r == 1) {
					psins = c.prepareStatement(
							"insert into cl_add_hist(branch,id_cl_add,act,emp_id,date_oper) values(?, ?, ?, ?,sysdate)");
					psins.setString(1, clientadd.getBranch());
					psins.setLong(2, clientadd.getId_cl_add());
					psins.setInt(3, act);
					psins.setInt(4, uid);
					psins.executeUpdate();
					c.commit();
					res = new Res(0, "Ok");
				} else {
					res = new Res(1,
							"Востановление невозможно, количество записей не соответствует действительному cl_add = "
									+ r);
				}
			} else {
				res = new Res(1,
						"Востановление невозможно, количество записей не соответствует действительному p = " + r);
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			res = new Res(1, e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(psbank);
			DbUtils.closeStmt(psins);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res loadParameters(String branch, String client_id, String code_subject, String un, String pw, String alias) {
		log.error("Load Code Subject " + code_subject);
		Res res = new Res();
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call BF_CLIENT_ADDINFO.loadParameters_n(?,?,?,?)}");
			cs.setString(1, branch);
			cs.setString(2, client_id);
			cs.setString(3, alias);
			cs.setString(4, code_subject);
			cs.execute();
			c.commit();
			res = new Res(0, "Ok");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			res = new Res(1, "Load error: " + e.getMessage());
		} finally {
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res saveParameters(String branch, String client_id, String code_subject, String un, String pw, String alias) {
		Res res = new Res();
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call BF_CLIENT_ADDINFO.saveParameters_n(?,?,?,?) }");
			cs.setString(1, branch);
			cs.setString(2, client_id);
			cs.setString(3, alias);
			cs.setString(4, code_subject);
			cs.execute();
			c.commit();
			res = new Res(0, "Ok");
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			res = new Res(1, "Load error: " + e.getMessage());
		} finally {
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res saveParameters(Connection c, String branch, String client_id, String alias, String code_subject) {
		log.warn("Параметры синхронизации: " + branch + " " + client_id + " " + alias);
		Res res = new Res();
		CallableStatement cs = null;
		try {
			cs = c.prepareCall("{ call BF_CLIENT_ADDINFO.saveParameters_n(?,?,?,?) }");
			cs.setString(1, branch);
			cs.setString(2, client_id);
			cs.setString(3, alias);
			cs.setString(4, code_subject);
			cs.execute();
			res = new Res(0, "Ok");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			res = new Res(1, "Load error: " + e.getMessage());
		} finally {
			try {
				if (cs != null) {
					cs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public List<Parameter> getParameter() {
		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(
					"SELECT * FROM select    decode(a.branch, null, '00000', a.branch) branch,    decode(a.client_id, null, '00000000', a.client_id) client_id,    a.param_value, nvl(t.param_visible, 1) param_visibility, t.*, decode(a.branch, null, 1, 0) isnew   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '00000' and ca.client_id='00000000') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id");
			while (rs.next()) {
				list.add(new Parameter(rs.getString("branch"), rs.getString("client_id"), rs.getString("param_value"),
						rs.getString("client_type"), rs.getString("param_group_id"), rs.getString("param_id"),
						rs.getString("param_select"), rs.getString("param_type"), rs.getString("param_mask"),
						rs.getString("param_def_value"), rs.getLong("param_ord"), rs.getInt("param_mandatory"),
						rs.getInt("isnew"), rs.getInt("param_align"), rs.getString("param_actions"),
						rs.getString("param_enable"), rs.getInt("param_act_runatstart"),
						rs.getInt("param_visibility")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(s);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<ParameterGroup> getParametergroup(String code_subject) {
		List<ParameterGroup> list = new ArrayList<ParameterGroup>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM SS_CLIENT_PARAM_GROUPS g "
					+ "where exists (SELECT 'x' FROM CLIENT_ADDINFO_TEMPLATE t where t.client_type = ? and g.id = t.param_group_id) order by ord");
			ps.setString(1, code_subject);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ParameterGroup(rs.getString("id"), rs.getInt("is_open"), rs.getInt("ord")));
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<Parameter> getParameters(String branch, String client_id, String code_subject) {
		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select    " + "	decode(a.branch, null, ?, a.branch) branch, "
					+ "	decode(a.client_id, null, ?, a.client_id) client_id, " + "	a.param_value, "
					+ "	nvl(t.param_visible, 1) param_visibility, " + "   t.*, "
					+ "	decode(a.branch, null, 1, 0) isnew " + "from CLIENT_ADDINFO_TEMPLATE t, "
					+ "	  (select * from CLIENT_ADDINFO ca where ca.client_type = ? and ca.branch = ? and ca.client_id=?) a "
					+ "where t.client_type = ? " + "  and a.client_type(+) = t.client_type "
					+ "  and a.param_group_id(+) = t.param_group_id " + "  and a.param_id(+) = t.param_id "
					+ "order by t.param_group_id, t.param_ord");
			ps.setString(1, branch);
			ps.setString(2, client_id);
			ps.setString(3, code_subject);
			ps.setString(4, branch);
			ps.setString(5, client_id);
			ps.setString(6, code_subject);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parameter(rs.getString("branch"), rs.getString("client_id"), rs.getString("param_value"),
						rs.getString("client_type"), rs.getString("param_group_id"), rs.getString("param_id"),
						rs.getString("param_select"), rs.getString("param_type"), rs.getString("param_mask"),
						rs.getString("param_def_value"), rs.getLong("param_ord"), rs.getInt("param_mandatory"),
						rs.getInt("isnew"), rs.getInt("param_align"), rs.getString("param_actions"),
						rs.getString("param_enable"), rs.getInt("param_act_runatstart"),
						rs.getInt("param_visibility")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<Parameter> getParametersByGroup(List<Parameter> parameters, ParameterGroup group) {
		List<Parameter> list = new ArrayList<Parameter>();
		for (Parameter parameter : parameters) {
			if (parameter.getParam_group_id().equals(group.getId())) {
				list.add(parameter);
			}
		}
		return list;
	}

	public static String setParamsInSQL(List<Parameter> params, String select, Clientmap client) {
		String res = select;
		res = res.replaceAll("PARAM.GETPARAM('CLIENT_BRANCH')", "'" + client.getBranch() + "'");
		res = res.replaceAll("#CLIENT_BRANCH", "'" + client.getBranch() + "'");
		res = res.replaceAll("PARAM.GETPARAM('CLIENT_ID')", "'" + client.getId_client() + "'");
		res = res.replaceAll("#CLIENT_ID", "'" + client.getId_client() + "'");

		res = setParamsInSQL_Param(params, res);
		res = setParamsInSQL_Sharp(params, res);
		return res;
	}

	public static String setParamsInSQL_Param(List<Parameter> params, String select) {
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
					/*
					 * m1 = str.indexOf(",",k); if ((m > m1 && m1 != -1) || m ==
					 * -1) { m = m1; } m1 = str.indexOf(")",k); if ((m > m1 &&
					 * m1 != -1) || m == -1) { m = m1; }
					 */
					if (m == -1) {
						m = l;
						throw new Exception(
								"SELECT PARAMS NOT TRUE! param:" + str.substring(k + 16, m) + "; SQL = " + select);
					}
					// System.out.println("*** k = "+k+" m = "+m);
					cur_param = str.substring(k + 16, m);
					cur_param_val = getParamValue(params, cur_param);
					// System.out.println("*** '"+cur_param+"' =
					// '"+cur_param_val+"' "+"PARAM.GETPARAM('"+cur_param+"')");
					str = str.replace("PARAM.GETPARAM('" + cur_param + "')",
							(cur_param_val == null ? "null" : "'" + cur_param_val + "'"));
					// str = str.replaceAll("PARAM.GETPARAM('"+cur_param+"')",
					// (cur_param_val==null?"null":"'"+cur_param_val+"'"));
					// System.out.println("*** '"+str);
					/*
					 * if (cur_param_val.equals("")){ str = ""; k=l+1; }
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		}
		return str;
	}

	public static String setParamsInSQL_Sharp(List<Parameter> params, String select) {
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
					str = str.replaceAll("#" + cur_param, (cur_param_val == null ? "null" : "'" + cur_param_val + "'"));
					/*
					 * if (cur_param_val.equals("")){ str = ""; k=l+1; }
					 */
				}
			}
		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
		}
		return str;
	}

	private static String getParamValue(List<Parameter> params, String param_id) {
		String param_value = "";
		Parameter par = null;
		for (int i = 0; i < params.size(); i++) {
			par = (Parameter) params.get(i);
			// System.out.println(par.getPar_name()+" =
			// '"+par.getPar_value()+"'; par_found = "+param);
			if (par.getParam_id().equals(param_id)) {
				if (par.getParam_value() != null) {
					param_value = par.getParam_value();
				}
			}
		}
		return param_value;
	}

	public static List<RefData> getListForCombobox(String str, String un, String pw, String alias) {

		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			ps = c.prepareStatement(str);
			rs = ps.executeQuery();
			// System.out.println(str);
			// System.out.println(alias);
			while (rs.next()) {
				// System.out.println("111");
				list.add(new RefData(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<CheckListRefData> getListForCheckList(String str, String parvalue, String un, String pw, String alias) {
		String data;
		String[] params = {};
		Boolean isChecked = false;
		List<CheckListRefData> list = new LinkedList<CheckListRefData>();
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (!CheckNull.isEmpty(parvalue)) {
				params = parvalue.split(",");
			}
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			ps = c.prepareStatement(str);
			rs = ps.executeQuery();
			// System.out.println(str);
			// System.out.println(alias);
			while (rs.next()) {
				isChecked = false;
				data = rs.getString(1);
				for (int i = 0; i < params.length; i++) {
					if (data.equalsIgnoreCase(params[i])) {
						isChecked = true;
					}
				}
				list.add(new CheckListRefData(isChecked, data, rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getListForCombobox(String str, Boolean showErr, String un, String pw, String alias) {

		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			ps = c.prepareStatement(str);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("kod"), rs.getString("name")));
			}
		} catch (SQLException e) {
			if (showErr)
				log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(ParameterFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch = ?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getClient_id())) {
			flfields.add(new FilterField(getCond(flfields) + "client_id = ?", filter.getClient_id()));
		}
		if (!CheckNull.isEmpty(filter.getParam_value())) {
			flfields.add(new FilterField(getCond(flfields) + "param_value = ?", filter.getParam_value()));
		}
		if (!CheckNull.isEmpty(filter.getClient_type())) {
			flfields.add(new FilterField(getCond(flfields) + "client_type = ?", filter.getClient_type()));
		}
		if (!CheckNull.isEmpty(filter.getParam_group_id())) {
			flfields.add(new FilterField(getCond(flfields) + "param_group_id = ?", filter.getParam_group_id()));
		}
		if (!CheckNull.isEmpty(filter.getParam_id())) {
			flfields.add(new FilterField(getCond(flfields) + "param_id = ?", filter.getParam_id()));
		}
		if (!CheckNull.isEmpty(filter.getParam_select())) {
			flfields.add(new FilterField(getCond(flfields) + "param_select = ?", filter.getParam_select()));
		}
		if (!CheckNull.isEmpty(filter.getParam_type())) {
			flfields.add(new FilterField(getCond(flfields) + "param_type = ?", filter.getParam_type()));
		}
		if (!CheckNull.isEmpty(filter.getParam_mask())) {
			flfields.add(new FilterField(getCond(flfields) + "param_mask = ?", filter.getParam_mask()));
		}
		if (!CheckNull.isEmpty(filter.getParam_def_value())) {
			flfields.add(new FilterField(getCond(flfields) + "param_def_value = ?", filter.getParam_def_value()));
		}
		if (!CheckNull.isEmpty(filter.getParam_ord())) {
			flfields.add(new FilterField(getCond(flfields) + "param_ord = ?", filter.getParam_ord()));
		}
		if (!CheckNull.isEmpty(filter.getParam_mandatory())) {
			flfields.add(new FilterField(getCond(flfields) + "param_mandatory = ?", filter.getParam_mandatory()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public int getCount(ParameterFilter filter) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT count(*) ct FROM (select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*, decode(a.branch, null, 1, 0) isnew   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id) ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));

		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Parameter> getParametersFl(int pageIndex, int pageSize, ParameterFilter filter) {

		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parameter(rs.getString("branch"), rs.getString("client_id"), rs.getString("param_value"),
						rs.getString("client_type"), rs.getString("param_group_id"), rs.getString("param_id"),
						rs.getString("param_select"), rs.getString("param_type"), rs.getString("param_mask"),
						rs.getString("param_def_value"), rs.getLong("param_ord"), rs.getInt("param_mandatory"),
						rs.getInt("isnew"), rs.getInt("param_align"), rs.getString("param_actions"),
						rs.getString("param_enable"), rs.getInt("param_act_runatstart"),
						rs.getInt("param_visibility")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));

		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;

	}

	public Parameter getParameter(int parameterId) {

		Parameter parameter = new Parameter();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"SELECT * FROM select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id WHERE id=?");
			ps.setInt(1, parameterId);
			rs = ps.executeQuery();
			if (rs.next()) {
				parameter = new Parameter();
				parameter.setBranch(rs.getString("branch"));
				parameter.setClient_id(rs.getString("client_id"));
				parameter.setParam_value(rs.getString("param_value"));
				parameter.setClient_type(rs.getString("client_type"));
				parameter.setParam_group_id(rs.getString("param_group_id"));
				parameter.setParam_id(rs.getString("param_id"));
				parameter.setParam_select(rs.getString("param_select"));
				parameter.setParam_type(rs.getString("param_type"));
				parameter.setParam_mask(rs.getString("param_mask"));
				parameter.setParam_def_value(rs.getString("param_def_value"));
				parameter.setParam_ord(rs.getLong("param_ord"));
				parameter.setParam_mandatory(rs.getInt("param_mandatory"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return parameter;
	}
	/*
	 * public static Parameter create(Parameter parameter) {
	 * 
	 * Connection c = null; PreparedStatement ps = null; try { c =
	 * ConnectionPool.getConnection(); ps = c.prepareStatement(
	 * "SELECT SEQ_select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id.NEXTVAL id FROM DUAL"
	 * ); ResultSet rs = ps.executeQuery(); if (rs.next()) {
	 * parameter.setId(rs.getLong("id")); } ps = c.prepareStatement(
	 * "INSERT INTO select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id (branch, client_id, param_value, client_type, param_group_id, param_id, param_name_en, param_name_ru, param_name_uz, param_select, param_type, param_mask, param_def_value, param_ord, param_mandatory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ); ps.setString(1, parameter.getBranch()); ps.setString(2,
	 * parameter.getClient_id()); ps.setString(3, parameter.getParam_value());
	 * ps.setString(4, parameter.getClient_type()); ps.setLong(5,
	 * parameter.getParam_group_id()); ps.setLong(6, parameter.getParam_id());
	 * ps.setString(7, parameter.getParam_name_en()); ps.setString(8,
	 * parameter.getParam_name_ru()); ps.setString(9,
	 * parameter.getParam_name_uz()); ps.setString(10,
	 * parameter.getParam_select()); ps.setString(11,
	 * parameter.getParam_type()); ps.setString(12, parameter.getParam_mask());
	 * ps.setString(13, parameter.getParam_def_value()); ps.setLong(14,
	 * parameter.getParam_ord()); ps.setLong(15,
	 * parameter.getParam_mandatory());
	 * 
	 * ps.executeUpdate(); c.commit(); } catch (Exception e) {
	 * e.printStackTrace();
	 * 
	 * } finally { ConnectionPool.close(c); } return parameter; }
	 * 
	 * public static void update(Parameter parameter) {
	 * 
	 * Connection c = null;
	 * 
	 * try { c = ConnectionPool.getConnection(); PreparedStatement ps =
	 * c.prepareStatement(
	 * "UPDATE select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id SET branch = ?, client_id = ?, param_value = ?, client_type = ?, param_group_id = ?, param_id = ?, param_name_en = ?, param_name_ru = ?, param_name_uz = ?, param_select = ?, param_type = ?, param_mask = ?, param_def_value = ?, param_ord = ?, param_mandatory = ?  WHERE id=?"
	 * ); ps.setString(1, parameter.getBranch()); ps.setString(2,
	 * parameter.getClient_id()); ps.setString(3, parameter.getParam_value());
	 * ps.setString(4, parameter.getClient_type()); ps.setLong(5,
	 * parameter.getParam_group_id()); ps.setLong(6, parameter.getParam_id());
	 * ps.setString(7, parameter.getParam_name_en()); ps.setString(8,
	 * parameter.getParam_name_ru()); ps.setString(9,
	 * parameter.getParam_name_uz()); ps.setString(10,
	 * parameter.getParam_select()); ps.setString(11,
	 * parameter.getParam_type()); ps.setString(12, parameter.getParam_mask());
	 * ps.setString(13, parameter.getParam_def_value()); ps.setLong(14,
	 * parameter.getParam_ord()); ps.setLong(15,
	 * parameter.getParam_mandatory());
	 * 
	 * ps.executeUpdate(); c.commit(); } catch (SQLException e) {
	 * e.printStackTrace(); throw new Exception(e); } finally {
	 * ConnectionPool.close(c); }
	 * 
	 * }
	 * 
	 * public static void remove(Parameter parameter) {
	 * 
	 * Connection c = null;
	 * 
	 * try { c = ConnectionPool.getConnection(); PreparedStatement ps =
	 * c.prepareStatement(
	 * "DELETE FROM select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from CLIENT_ADDINFO_TEMPLATE t,       (select * from CLIENT_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id WHERE id=?"
	 * ); ps.setInt(1, parameter.getId()); ps.executeUpdate(); c.commit(); }
	 * catch (Exception e) { e.printStackTrace(); } finally {
	 * ConnectionPool.close(c); } }
	 */

	public static Res saveAddInfo(List<Parameter> parameters, Clientmap client, List<Parameter> oldparameters,
			int correctinclient, HashMap<String, List<ParameterList>> paramlistparams, String un, String pw) {
		Res res = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(un, pw, client.getAlias());
			for (Parameter parameter : parameters) {
				System.out.println("save " + parameter.getBranch() + " " + parameter.getClient_id() + " "
						+ parameter.getClient_type() + " " + parameter.getParam_group_id() + " "
						+ parameter.getParam_id() + " " + parameter.getParam_value());
				log.warn("save " + parameter.getBranch() + " " + parameter.getClient_id() + " "
						+ parameter.getClient_type() + " " + parameter.getParam_group_id() + " "
						+ parameter.getParam_id() + " " + parameter.getParam_value());
				res = update(c, parameter);
				System.out.println("res update: " + parameter.getParam_id() + " " + parameter.getParam_value() + " = "
						+ res.getCode() + " " + res.getName());
				log.warn("res update: " + parameter.getParam_id() + " " + parameter.getParam_value() + " = "
						+ res.getCode() + " " + res.getName());
				if (res.getCode() != 0) {
					res = create(c, parameter);
					System.out.println("res create: " + parameter.getParam_id() + " " + parameter.getParam_value()
							+ " = " + res.getCode() + " " + res.getName());
					log.warn("res update: " + parameter.getParam_id() + " " + parameter.getParam_value() + " = "
							+ res.getCode() + " " + res.getName());
				}
				/*
				 * if (parameter.getIsnew() == 1) { res = create(c, parameter);
				 * } else { res = update(c, parameter); }
				 */
				if (res.getCode() != 0) {
					c.rollback();
					return res;
				}
			}
			Object[] keysets = (Object[]) paramlistparams.keySet().toArray();
			for (int i = 0; i < keysets.length; i++) {
				for (ParameterList parameterlist : paramlistparams.get((String) keysets[i])) {
					// System.out.println("list upd: "+
					// parameterlist.getParam_id() + " " +
					// parameterlist.getList_id());
					// log.warn("list upd: "+ parameterlist.getParam_id() + " "
					// + parameterlist.getList_id());
					Res resPL = saveAddinfoParameterList(c, parameterlist);
					if (resPL.getCode() != 0) {
						throw new Exception("Ошибка при сохранении лицензий: " + resPL.getName());
					}
				}
			}

			log.warn("Параметры синхронизации: " + client.getBranch() + " " + client.getId_client() + " "
					+ client.getAlias());
			Res ress = saveParameters(c, client.getBranch(), client.getId_client(), client.getAlias(),
					client.getCode_subject());
			if (ress.getCode() != 0) {
				throw new Exception("Ошибка при синхронизации: " + client.getBranch() + " " + client.getId_client()
						+ " " + client.getAlias() + " " + ress.getName());
			}
			if (correctinclient == 1) {
				if (!EmergencyMode.isTrue) {
					HashMap<String, Object> uvkMap = getAddinfoForClient(c, client.getBranch(), client.getId_client(),
							client.getCode_subject(), client.getCode_type(), client.getAlias());
					log.warn("Параметры корректировки: " + "uvkMap.size() = " + uvkMap.size() + " "
							+ "uvkMap.alias() = " + uvkMap.get("alias") + " " + uvkMap.get("branch") + " "
							+ uvkMap.get("id_client") + " " + uvkMap.get("code_subject") + " " + uvkMap.get("uvk_level")
							+ " " + uvkMap.get("uvk_level_reason") + " " + uvkMap.get("uvk_date_from") + " "
							+ uvkMap.get("uvk_date_to") + " " + uvkMap.get("so_note") + " " + uvkMap.get("po_note")
							+ " " + uvkMap.get("risk date"));

					Res resuvk = com.is.clients.sap.SapHandler.sendUvk(uvkMap);
					if (resuvk.getCode() != 1) {
						throw new Exception(resuvk.getName());
					}
				}
			}
			c.commit();

			// Object[] keysets_ = (Object[])paramlistparams.keySet().toArray();
			// for (int i = 0; i < keysets_.length; i++) {
			// for (ParameterList parameterlist :
			// paramlistparams.get((String)keysets_[i])) {
			// parameterlist = new ParameterList();
			// }
			// }

		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			res = new Res(1, e.getMessage());
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				log.error(CheckNull.getPstr(e));
			}
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res create(Connection c, Parameter parameter) {
		Res res = new Res();
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO client_addinfo (client_type, branch, client_id, param_group_id, param_id, param_value) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, parameter.getClient_type());
			ps.setString(2, parameter.getBranch());
			ps.setString(3, parameter.getClient_id());
			ps.setString(4, parameter.getParam_group_id());
			ps.setString(5, parameter.getParam_id());
			ps.setString(6, parameter.getParam_value());
			int r = ps.executeUpdate();
			if (r == 1) {
				// c.commit();
				res = new Res(0, "Ok");
			} else {
				res = new Res(1, "Сохранение невозможно, количество записей не соответствует действительному " + r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			/*
			 * try{ c.rollback(); } catch (Exception ex) { ex.printStackTrace();
			 * }
			 */
			res = new Res(1, e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			// ConnectionPool.close(c);
		}
		return res;
	}

	public static Res update(Connection c, Parameter parameter) {
		Res res = new Res();
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"UPDATE client_addinfo SET param_value = ? WHERE client_type = ? and branch = ? and client_id = ? and param_group_id = ? and param_id = ?");
			ps.setString(1, parameter.getParam_value());
			ps.setString(2, parameter.getClient_type());
			ps.setString(3, parameter.getBranch());
			ps.setString(4, parameter.getClient_id());
			ps.setString(5, parameter.getParam_group_id());
			ps.setString(6, parameter.getParam_id());
			int r = ps.executeUpdate();
			if (r == 1) {
				// c.commit();
				res = new Res(0, "Ok");
			} else {
				res = new Res(1, "Сохранение невозможно, количество записей не соответствует действительному " + r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			/*
			 * try{ c.rollback(); } catch (Exception ex) { ex.printStackTrace();
			 * }
			 */
			res = new Res(1, e.getMessage());
		} finally {
			// ConnectionPool.close(c);
			DbUtils.closeStmt(ps);
		}
		return res;
	}

	public static Res saveAddinfoParameterList(Connection c, ParameterList parameterlist) {
		Res res = new Res();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (CheckNull.isEmpty(parameterlist.getList_id()) || parameterlist.getList_id() <= 0) {
				ps = c.prepareStatement("SELECT SEQ_CLIENT_ADDINFO_LIST.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if (rs.next()) {
					parameterlist.setList_id(rs.getLong("id"));
				} else {
					throw new Exception("SEQ_CLIENT_ADDINFO_LIST not found!");
				}
				for (int i = 0; i < parameterlist.getParameters().size(); i++) {
					System.out.println("list ins: " + parameterlist.getParam_id() + " " + parameterlist.getList_id()
							+ " " + parameterlist.getParameters().get(i).getParam_list_teg() + " "
							+ parameterlist.getParameters().get(i).getParam_value());
					log.warn("list ins: " + parameterlist.getParam_id() + " " + parameterlist.getList_id() + " "
							+ parameterlist.getParameters().get(i).getParam_list_teg() + " "
							+ parameterlist.getParameters().get(i).getParam_value());
					ps = c.prepareStatement(
							"INSERT INTO CLIENT_ADDINFO_LIST (client_type, branch, client_id, param_id, list_id, param_teg, param_value, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
					ps.setString(1, parameterlist.getClient_type());
					ps.setString(2, parameterlist.getBranch());
					ps.setString(3, parameterlist.getClient_id());
					ps.setString(4, parameterlist.getParam_id());
					ps.setLong(5, parameterlist.getList_id());
					ps.setString(6, parameterlist.getParameters().get(i).getParam_list_teg());
					ps.setString(7, parameterlist.getParameters().get(i).getParam_value());
					ps.setLong(8, parameterlist.getState());
					int cnt = ps.executeUpdate();
					if (cnt != 1) {
						throw new Exception("Количество строк не соответствует действительному! ins = " + cnt);
					}
				}
			} else {
				log.error("BUUG " + parameterlist);
				for (int i = 0; i < parameterlist.getParameters().size(); i++) {
					System.out.println("list upd: " + parameterlist.getParam_id() + " " + parameterlist.getList_id()
							+ " " + parameterlist.getParameters().get(i).getParam_list_teg() + " "
							+ parameterlist.getParameters().get(i).getParam_value());
					log.warn("list upd: " + parameterlist.getParam_id() + " " + parameterlist.getList_id() + " "
							+ parameterlist.getParameters().get(i).getParam_list_teg() + " "
							+ parameterlist.getParameters().get(i).getParam_value());
					ps = c.prepareStatement("UPDATE CLIENT_ADDINFO_LIST SET param_value = ?, state = ? "
							+ "WHERE client_type = ? and branch = ? and client_id = ? and param_id = ? and list_id = ? and param_teg = ? ");
					ps.setString(1, parameterlist.getParameters().get(i).getParam_value());
					ps.setLong(2, parameterlist.getState());
					ps.setString(3, parameterlist.getClient_type());
					ps.setString(4, parameterlist.getBranch());
					ps.setString(5, parameterlist.getClient_id());
					ps.setString(6, parameterlist.getParam_id());
					ps.setLong(7, parameterlist.getList_id());
					ps.setString(8, parameterlist.getParameters().get(i).getParam_list_teg());
					int cnt = ps.executeUpdate();
					if (cnt == 0) {
						System.out.println("list ins: " + parameterlist.getParam_id() + " " + parameterlist.getList_id()
								+ " " + parameterlist.getParameters().get(i).getParam_list_teg() + " "
								+ parameterlist.getParameters().get(i).getParam_value());
						log.warn("list ins: " + parameterlist.getParam_id() + " " + parameterlist.getList_id() + " "
								+ parameterlist.getParameters().get(i).getParam_list_teg() + " "
								+ parameterlist.getParameters().get(i).getParam_value());
						ps = c.prepareStatement(
								"INSERT INTO CLIENT_ADDINFO_LIST (client_type, branch, client_id, param_id, list_id, param_teg, param_value, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
						ps.setString(1, parameterlist.getClient_type());
						ps.setString(2, parameterlist.getBranch());
						ps.setString(3, parameterlist.getClient_id());
						ps.setString(4, parameterlist.getParam_id());
						ps.setLong(5, parameterlist.getList_id());
						ps.setString(6, parameterlist.getParameters().get(i).getParam_list_teg());
						ps.setString(7, parameterlist.getParameters().get(i).getParam_value());
						ps.setLong(8, parameterlist.getState());
						int cnt1 = ps.executeUpdate();
						if (cnt1 != 1) {
							throw new Exception("Количество строк не соответствует действительному! ins = " + cnt);
						}
					} else if (cnt != 1) {
						throw new Exception("Количество строк не соответствует действительному! upd = " + cnt);
					}
				}
			}
			res = new Res(0, "Ok");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			res = new Res(1, e.getMessage());
		} finally {
			DbUtils.closeResultSet(rs);
			if (ps != null)
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
		return res;
	}

	public static List<Clientmap> getClientsmap() {
		List<Clientmap> list = new ArrayList<Clientmap>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM client");
			while (rs.next()) {
				list.add(new Clientmap(rs.getLong("id"), rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name"), rs.getString("code_country"), rs.getString("code_type"),
						rs.getString("code_resident"), rs.getString("code_subject"), rs.getString("code_form"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getLong("state"), rs.getLong("kod_err"),
						rs.getString("file_name"), rs.getLong("sign_registr"), ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Clientmap getClientmap(String branch, String client_id, String code_subject) {/// *,
																								/// String
																								/// code_subject*/,
																								/// String
																								/// alias)
																								/// {
		Clientmap client = new Clientmap();
		Connection c = null;
		Statement s = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String alias = "";
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("SELECT s.user_name FROM ss_dblink_branch s" + " where s.branch = '" + branch + "'");
			if (rs.next()) {
				alias = rs.getString("user_name");
				s.execute("alter session set current_schema=" + alias);
			} else {
				throw new Exception("No SCHEMA found!");
			}
			log.error("Map Branch " + branch);
			log.error("Map Id " + client_id);
			log.error("Map Code Subject " + code_subject);
			if (code_subject.equals("N")) {
				ps = c.prepareStatement(
						"select a.*,(select user_name from ss_dblink_branch s where s.branch = a.branch) alias "
								+ " from cl_add_04 a where a.branch = ? and a.id = ?");
				ps.setString(1, branch);
				ps.setString(2, client_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					client = new Clientmap(rs.getLong("id"), rs.getString("branch"), rs.getString("id"),
							rs.getString("FAMILY") + " " + rs.getString("FIRST_NAME"), rs.getString("citizen"), null,
							null, code_subject, null, rs.getDate("date_open"), null, rs.getLong("state"), null, null,
							null, rs.getString("alias"));
					return client;
				}

			}
			ps = c.prepareStatement(
					"SELECT c.*, s.user_name as alias, " + "(case when c.code_subject = 'J' and code_type = '11' "
							+ "then 'I' else c.code_subject end) csubj FROM client c, "
							+ "ss_dblink_branch s where c.branch = ? and c.id_client = ? "
							+ "and code_subject <> 'I' and s.branch = c.branch");
			ps.setString(1, branch);
			ps.setString(2, client_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				client = new Clientmap(rs.getLong("id"), rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name"), rs.getString("code_country"), rs.getString("code_type"),
						rs.getString("code_resident"), rs.getString("csubj"), rs.getString("code_form"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getLong("state"), rs.getLong("kod_err"),
						rs.getString("file_name"), rs.getLong("sign_registr"), rs.getString("alias"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return client;
	}

	public static ClientAddinfoParameter getClientAddinfoParameters(Clientmap clmap, String un, String pw) {
		ClientAddinfoParameter client = new ClientAddinfoParameter();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, clmap.getAlias());
			ps = c.prepareStatement("select * from CLIENT_ADDINFO_PARAMETERS "
					+ "p where p.client_type = ? and p.branch = ? and p.client_id = ?");
			ps.setString(1, clmap.getCode_subject());
			ps.setString(2, clmap.getBranch());
			ps.setString(3, clmap.getId_client());
			rs = ps.executeQuery();
			if (rs.next()) {
				client = new ClientAddinfoParameter(rs.getString("CLIENT_TYPE"), rs.getString("BRANCH"),
						rs.getString("CLIENT_ID"), rs.getLong("DEAL_ID"), rs.getLong("STATE"), rs.getLong("ID_CL_ADD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return client;
	}

	public static HashMap<String, Object> getAddinfoForClient(String branch, String clientid, String code_subject,
			String code_type, String un, String pw, String alias) throws Exception {
		Connection c = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			hashMap = getAddinfo(c, branch, clientid, code_subject, code_type, alias);
		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return hashMap;
	}

	public static HashMap<String, Object> getAddinfo(Connection c, String branch, String clientid, String code_subject,
			String code_type, String alias) throws Exception {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Statement s = null;
		PreparedStatement ps = null;
		PreparedStatement pslis = null;
		ResultSet rs = null;
		ResultSet rscl = null;
		ResultSet rslis = null;
		String sqlssdblinkbranch = "";
		int uvk_level = 1;
		String uvk_level_reason = "";
		java.util.Date uvk_date_from = null;
		java.util.Date nouvk_date_from = null;
		String sopers = "";
		String popers = "";
		String _branch = "";
		String _alias = "";
		List<com.is.clients.models.License> lislist = new ArrayList<com.is.clients.models.License>();
		try {
			if (code_subject.equalsIgnoreCase("J") && code_type.equalsIgnoreCase("11")) {
				code_subject = "I";
			}
			s = c.createStatement();
			sqlssdblinkbranch = "SELECT s.* FROM ss_dblink_branch s where s.branch = '" + branch + "' ";

			/*
			 * if (code_subject.equalsIgnoreCase("P")) { sqlssdblinkbranch =
			 * "SELECT s.* FROM ss_dblink_branch s where s.branch = '"+branch+
			 * "' "; } else { sqlssdblinkbranch =
			 * "SELECT s.* FROM ss_dblink_branch s where s.branch not like '09%' order by branch"
			 * ; }
			 */

			ps = c.prepareStatement(
					"select a.*, c.date_open cldate_open, c.date_close cldate_close, c.state clstate from v_cl_add_0"
							+ (code_subject.equalsIgnoreCase("P") ? "1"
									: (code_subject.equalsIgnoreCase("J") ? "2"
											: (code_subject.equalsIgnoreCase("N") ? "4" : "3")))
							+ " a, client c where a.branch = ? and a.client_id = ? and c.branch = a.branch and c.id_client = a.client_id and rownum = 1");
			pslis = c.prepareStatement("select l.*, "
					+ "       nvl(sla.id, (select id from ss_licensing_authority s where s.need_addinfo = 1)) LIS_GIVE_ORGAN_CODE, "
					+ "       nvl(slact.id, (select id from ss_licensed_activities s where s.need_addinfo = 1)) ACT_CODE "
					+ "from cl_add_lis_inform l, ss_licensing_authority sla, ss_licensed_activities slact "
					+ "where l.branch = ? and l.id_cl_add = ? and lis_num is not null "
					+ "  and sla.name (+) = l.lis_give_organ " + "  and slact.name (+) = l.act_name order by l.id");
			rs = s.executeQuery(sqlssdblinkbranch);
			while (rs.next()) {
				_branch = rs.getString("branch");
				_alias = rs.getString("user_name");
				s.execute("alter session set current_schema=" + _alias);
				ps.setString(1, branch);
				ps.setString(2, clientid);
				rscl = ps.executeQuery();
				if (rscl.next()) {
					long id_cl_add = rscl.getLong("ID");
					int uvk = rscl.getInt("RISK_DEGREE");
					String uvkreason = rscl.getString("RISK_DEGREE_DETAIL");
					java.util.Date uvk_dt_from = rscl.getDate("RISK_DATE");
					String sop = rscl.getString("SOM_OPERS");
					String pop = rscl.getString("POD_OPERS");
					int clstate = rscl.getInt("CLSTATE");
					if (clstate == 2) {
						if (uvk_level < uvk)
							uvk_level = uvk;
						if (uvk > 1) {
							if (CheckNull.isEmpty(uvk_date_from) || uvk_date_from.getTime() > uvk_dt_from.getTime()) {
								uvk_date_from = uvk_dt_from;
							}
							if (!CheckNull.isEmpty(uvkreason)) {
								String[] uvkreasons = uvkreason.split(",");
								String[] uvk_level_reasons = uvk_level_reason.split(",");
								for (int i = 0; i < uvkreasons.length; i++) {
									boolean bool = false;
									for (int j = 0; j < uvk_level_reasons.length; j++) {
										if (!CheckNull.isEmpty(uvk_level_reasons[j])
												&& uvk_level_reasons[j].equalsIgnoreCase(uvkreasons[i])) {
											bool = true;
										}
									}
									if (!bool) {
										uvk_level_reason = uvk_level_reason
												+ (CheckNull.isEmpty(uvk_level_reason) ? "" : ",") + uvkreasons[i];
									}
								}
							}
						} else {
							/*
							 * if (CheckNull.isEmpty(nouvk_date_from) ||
							 * nouvk_date_from.getTime() >
							 * uvk_dt_from.getTime()) { nouvk_date_from =
							 * uvk_dt_from; }
							 */
						}
						sopers = sopers + (CheckNull.isEmpty(sop) ? "" : (_branch + ", "));// ":
																							// "+sop+"\r\n"));
						popers = popers + (CheckNull.isEmpty(pop) ? "" : (_branch + ", "));// ":
																							// "+pop+"\r\n"));
					}

					pslis.setString(1, _branch);
					pslis.setLong(2, id_cl_add);
					rslis = pslis.executeQuery();
					while (rslis.next()) {
						com.is.clients.models.License lis = new com.is.clients.models.License("ZIY031", // license_type_id,
								rslis.getString("LIS_NUM"), // license_id,
								rslis.getDate("LAST_LIS_DATE"), // license_valid_to,
								rslis.getDate("LIS_DATE"), // license_issue_date,
								rslis.getString("LIS_GIVE_ORGAN_CODE"), // license_issued_by
								rslis.getString("ACT_CODE"), // license_type,
								rslis.getString("ACT_NAME"), // license_type_other
								rslis.getString("LIS_GIVE_ORGAN")// license_issued_by_other
						);
						boolean bool = false;
						/*
						 * for (int i = 0; i < lislist.size(); i++) { if
						 * (lislist.get(i).getLicense_id().equalsIgnoreCase(lis.
						 * getLicense_id())) { bool = true; } }
						 */
						if (!bool) {
							lislist.add(lis);
						}

					}
				}
			}
			if (sopers.length() > 0)
				sopers = sopers.substring(0, sopers.length() - 2);
			if (popers.length() > 0)
				popers = popers.substring(0, popers.length() - 2);

			res.put("alias", alias);
			res.put("branch", branch);
			res.put("id_client", clientid);
			res.put("code_subject", code_subject);
			res.put("uvk_level", uvk_level);
			res.put("uvk_level_reason", uvk_level_reason);
			res.put("uvk_date_from", uvk_date_from);
			res.put("uvk_date_to", null);
			res.put("so_note", sopers);
			res.put("po_note", popers);
			res.put("licensies", lislist);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			if (s != null)
				try {
					s.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (pslis != null)
				try {
					pslis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (rscl != null)
				try {
					rscl.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			if (rslis != null)
				try {
					rslis.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			throw new Exception(e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pslis != null)
				try {
					pslis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rscl != null)
				try {
					rscl.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rslis != null)
				try {
					rslis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return res;
	}

	public static HashMap<String, Object> getAddinfoForClient(Connection c, String branch, String clientid,
			String code_subject, String code_type, String alias) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Statement s = null;
		Statement salter = null;
		PreparedStatement ps = null;
		PreparedStatement pslis = null;
		ResultSet rsss = null;
		ResultSet rscl = null;
		ResultSet rslis = null;
		String sqlssdblinkbranch = "";
		int uvk_level = 1;
		String uvk_level_reason = "";
		java.util.Date riskdate = null;
		java.util.Date nouvk_date_from = null;
		java.util.Date initdate = null;
		String sopers = "";
		String popers = "";
		String _branch = "";
		String _alias = "";
		List<com.is.clients.models.License> lislist = new ArrayList<com.is.clients.models.License>();
		try {
			if (code_subject.equalsIgnoreCase("J") && code_type.equalsIgnoreCase("11")) {
				code_subject = "I";
			}
			s = c.createStatement();
			salter = c.createStatement();
			sqlssdblinkbranch = "SELECT * FROM ss_dblink_branch s where s.branch = '" + branch + "' ";
			/*
			 * if (code_subject.equalsIgnoreCase("P")) { sqlssdblinkbranch =
			 * "SELECT * FROM ss_dblink_branch s where s.branch = '"+branch+"' "
			 * ; } else { sqlssdblinkbranch =
			 * "SELECT * FROM ss_dblink_branch s where s.branch not like '09%' order by s.branch"
			 * ; }
			 */
			ps = c.prepareStatement(
					"select a.*, c.date_open cldate_open, c.date_close cldate_close, c.state clstate from v_cl_add_0"
							+ (code_subject.equalsIgnoreCase("P") ? "1"
									: (code_subject.equalsIgnoreCase("J") ? "2"
											: (code_subject.equalsIgnoreCase("N") ? "4" : "3")))
							+ " a, client c where a.branch = ? and a.client_id = ? and c.branch = a.branch and c.id_client = a.client_id and rownum = 1");
			pslis = c.prepareStatement("select l.*, "
					+ "       nvl(sla.id, (select id from ss_licensing_authority s where s.need_addinfo = 1)) LIS_GIVE_ORGAN_CODE, "
					+ "       nvl(slact.id, (select id from ss_licensed_activities s where s.need_addinfo = 1)) ACT_CODE "
					+ "from cl_add_lis_inform l, ss_licensing_authority sla, ss_licensed_activities slact "
					+ "where l.branch = ? and l.id_cl_add = ? and lis_num is not null "
					+ "  and sla.name (+) = l.lis_give_organ " + "  and slact.name (+) = l.act_name order by l.id");
			log.warn("getAddinfoForClient sqlssdblinkbranch = " + sqlssdblinkbranch);
			rsss = s.executeQuery(sqlssdblinkbranch);
			while (rsss.next()) {
				_branch = rsss.getString("branch");
				_alias = rsss.getString("user_name");
				salter.execute("alter session set current_schema=" + _alias);
				log.warn("getAddinfoForClient " + _branch + " " + _alias + " " + code_subject);
				ps.setString(1, branch);
				ps.setString(2, clientid);
				rscl = ps.executeQuery();
				if (rscl.next()) {
					long id_cl_add = rscl.getLong("ID");
					int uvk = rscl.getInt("RISK_DEGREE");
					String uvkreason = rscl.getString("RISK_DEGREE_DETAIL");
					java.util.Date risk_date = rscl.getDate("risk_date");
					java.util.Date init_date = rscl.getDate("date_open");
					String sop = rscl.getString("SOM_OPERS");
					String pop = rscl.getString("POD_OPERS");
					int clstate = rscl.getInt("CLSTATE");
					log.warn("getAddinfoForClient " + id_cl_add + " " + uvk + " " + uvkreason + " " + sop + " " + pop);
					if (clstate == 2) {
						if (uvk_level < uvk)
							uvk_level = uvk;
						if (uvk > 1) {
							if (!CheckNull.isEmpty(risk_date)
									&& (CheckNull.isEmpty(riskdate) || riskdate.getTime() > risk_date.getTime())) {
								riskdate = risk_date;
							}
							if (!CheckNull.isEmpty(uvkreason)) {
								String[] uvkreasons = uvkreason.split(",");
								String[] uvk_level_reasons = uvk_level_reason.split(",");
								for (int i = 0; i < uvkreasons.length; i++) {
									boolean bool = false;
									for (int j = 0; j < uvk_level_reasons.length; j++) {
										if (!CheckNull.isEmpty(uvkreasons[j])
												&& !CheckNull.isEmpty(uvk_level_reasons[j])
												&& uvk_level_reasons[j].equalsIgnoreCase(uvkreasons[i])) {
											bool = true;
										}
									}
									if (!bool) {
										uvk_level_reason = uvk_level_reason + "," + uvkreasons[i];
									}
								}
							}
						} else {
							/*
							 * if (!CheckNull.isEmpty(nouvk_dt_from) &&
							 * (CheckNull.isEmpty(nouvk_date_from) ||
							 * nouvk_date_from.getTime() <
							 * uvk_dt_from.getTime()) { nouvk_date_from =
							 * uvk_dt_from; }
							 */
						}
						sopers = sopers + (CheckNull.isEmpty(sop) ? "" : (_branch + ", "));// ":
																							// "+sop+"\r\n"));
						popers = popers + (CheckNull.isEmpty(pop) ? "" : (_branch + ", "));// ":
																							// "+pop+"\r\n"));
						initdate = init_date;
					}

					pslis.setString(1, _branch);
					pslis.setLong(2, id_cl_add);
					rslis = pslis.executeQuery();
					while (rslis.next()) {
						com.is.clients.models.License lis = new com.is.clients.models.License("ZIY031", // license_type_id,
								rslis.getString("LIS_NUM"), // license_id,
								rslis.getDate("LAST_LIS_DATE"), // license_valid_to,
								rslis.getDate("LIS_DATE"), // license_issue_date,
								rslis.getString("LIS_GIVE_ORGAN_CODE"), // license_issued_by
								rslis.getString("ACT_CODE"), // license_type,
								rslis.getString("ACT_NAME"), // license_type_other
								rslis.getString("LIS_GIVE_ORGAN")// license_issued_by_other
						);
						boolean bool = false;
						for (int i = 0; i < lislist.size(); i++) {
							if (lislist.get(i).getLicense_id().equalsIgnoreCase(lis.getLicense_id())) {
								bool = true;
								log.warn("getAddinfoForClient lis found " + lis.getLicense_id());
							}
						}
						if (!bool) {
							lislist.add(lis);
							log.warn("getAddinfoForClient lis add " + lis.getLicense_id());
						}

					}
				}
			}
			if (sopers.length() > 0)
				sopers = sopers.substring(0, sopers.length() - 2);
			if (popers.length() > 0)
				popers = popers.substring(0, popers.length() - 2);

			res.put("alias", alias);
			res.put("branch", branch);
			res.put("id_client", clientid);
			res.put("code_subject", code_subject);
			res.put("uvk_level", uvk_level);
			res.put("uvk_level_reason", uvk_level_reason);
			res.put("uvk_date_from", initdate);
			res.put("uvk_date_to", null);
			res.put("so_note", sopers);
			res.put("po_note", popers);
			res.put("licensies", lislist);
			res.put("risk_date ", riskdate);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (salter != null)
				try {
					salter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pslis != null)
				try {
					pslis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rsss != null)
				try {
					rsss.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rscl != null)
				try {
					rscl.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (rslis != null)
				try {
					rslis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return res;
	}

	public static String lvalue(String val, List<RefData> dp) {
		String res = "";
		if (dp != null) {
			for (int i = 0; i < dp.size(); i++) {
				if (val.equals(dp.get(i).getData())) {
					res = dp.get(i).getLabel();
				}
			}
		}
		return res;
	}

	public static List<RefData> getClientTypes(String alias) {
		return RefDataService.getRefData("select id data, name_ru label from SS_CLIENT_TYPES", alias);
	}

	public static List<RefData> getSSDBLinkBranch(String alias) {
		return RefDataService.getRefData("select branch data, user_name label from ss_dblink_branch order by branch",
				alias);
	}

	public static List<RefData> getClientStates(int deal_id, String alias) {
		return RefDataService.getRefData("select id data, name label from STATE_CLIENT t where deal_id = " + deal_id,
				alias);
	}

	public static List<RefData> getCountries(String alias) {
		return RefDataService
				.getRefData("select code_str data, name label from s_str where act <> 'Z' order by code_str", alias);
	}

	public static List<RefData> getRezKl(String alias) {
		return RefDataService.getRefData("select kod_rez data, type_rez label from S_REZKL", alias);
	}

	public static List<RefData> getTypeKl(String alias) {
		return RefDataService.getRefData(
				"select kod_k data, name_k2 label from S_TYPEKL where kod_k <> '00' and act <> 'Z' order by kod_k",
				alias);
	}

	public static List<RefData> getVSBS(String alias) {
		return RefDataService.getRefData("select kod data, name label from s_vsbs where act <> 'Z' order by kod",
				alias);
	}

	public static List<RefData> getPersonKind(String lang, String alias) {
		return RefDataService.getRefData("select id data, name_" + lang + " label from SS_PERSON_KINDS order by id",
				alias);
	}

	public static List<RefData> getDocumentTypes(String alias) {
		return RefDataService.getRefData(
				"select kod_pas data, name_pas label from s_passport where act <> 'Z' order by kod_pas", alias);
	}

	public static List<RefData> getNations(String alias) {
		return RefDataService
				.getRefData("select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id", alias);
	}

	public static List<RefData> getPasportTypes(String alias) {
		return RefDataService.getRefData(
				"select 'O' data, 'не биометрический' label from dual union all select 'N' data, 'биометрический' label from dual ",
				alias);
	}

	public static List<RefData> getGenderTypes(String alias) {
		return RefDataService
				.getRefData("select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX ", alias);
	}

	public static List<RefData> getRegions(String alias) {
		return RefDataService.getRefData(
				"select region_id data, region_nam label from s_region where act <> 'Z' order by region_id", alias);
	}

	public static List<RefData> getDistricts(String alias) {
		return RefDataService.getRefData(
				"select distr data, distr_name label, region_id from s_distr where act <> 'Z' order by distr", alias);
	}

	public static List<RefData> getAllRegions(String alias) {
		return RefDataService.getRefData("select region_id data, region_nam label from s_region order by region_id",
				alias);
	}

	public static List<RefData> getAllDistricts(String alias) {
		return RefDataService.getRefData("select distr data, distr_name label, region_id from s_distr order by distr",
				alias);
	}

	public static List<RefData> getCapacity(String alias) {
		return RefDataService
				.getRefData("select kod_kr data, name_kr label from s_krfl where act <> 'Z' order by kod_kr", alias);
	}

	public static List<RefData> getSKlass(String alias) {
		return RefDataService.getRefData(
				"select klass_id data, klass_name label from s_klass where act <> 'Z' order by klass_id", alias);
	}

	public static List<RefData> getGNI(String alias) {
		return RefDataService
				.getRefData("select gni_id data, name_gni label from s_gni where act <> 'Z' order by gni_id", alias);
	}

	public static List<RefData> getOPF(String alias) {
		return RefDataService.getRefData(
				"select opf_id data, opf_name label, t.* from S_OPF t where t.act <> 'Z' order by opf_id", alias);
	}

	public static List<RefData> getCodeSector(String alias) {
		return RefDataService.getRefData(
				"select branch_id data, branch_nam label from s_branch where branch_id >= '100' and act <> 'Z' order by branch_id",
				alias);
	}

	public static List<RefData> getSoogun(String alias) {
		return RefDataService
				.getRefData("select soogu data, soogu1 label from s_soogun where act <> 'Z' order by soogu", alias);
	}

	public static HashMap<String, String> getTegNames(String lang, String alias) {
		return RefDataService.getHRefData(
				"select id data, name label from CLIENT_ADDINFO_TEGS_NAME where lang = '" + lang + "'", alias);
	}

	public static void synchronize(Clientmap current, String un, String pw) throws SQLException {
		Connection c = null;
		CallableStatement callableStatement = null;
		try {
			c = ConnectionPool.getConnection(un, pw, current.getAlias());
			callableStatement = c.prepareCall("{call Gate_sap.syncLicenseList(?,?,?,?)}");
			callableStatement.setString(1, current.getBranch());
			callableStatement.setString(2, current.getId_client());
			callableStatement.setString(3, current.getCode_type());
			callableStatement.setString(4, current.getCode_subject());
			callableStatement.execute();
			c.commit();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbUtils.closeStmt(callableStatement);
			ConnectionPool.close(c);
		}
	}
}
