package com.is.useractionlog;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.login.SessionController;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class UserActionLogService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT /*+ INDEX_DESC(lg, XPK_BF_USER_ACTIONS_LOG) */ * FROM BF_USER_ACTIONS_LOG lg  ";
	private static SessionController sc = new SessionController();
	private static Logger log = ISLogger.getLogger();

	public static List<UserActionLog> getUserActionsLog() {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM BF_USER_ACTIONS_LOG where rownum <= 100  order by action_date desc");
			while (rs.next()) {

				list.add(new 
						UserActionLog(rs.getString("id"), 
									rs.getString("branch"), 
									rs.getString("user_id"), 
									rs.getString("user_name"),
									rs.getString("ip_address"), 
									rs.getDate("action_date"), 
									rs.getString("act_type"), 
									rs.getString("entity_type"), 
									rs.getString("entity_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<UserActionLog> getGeneralLog() {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM GENERAL_LOG where rownum <= 100 order by date_time desc");
			while (rs.next()) {

				list.add(new 
						UserActionLog(rs.getString("branch"), 
									rs.getString("user_id"), 
									rs.getString("user_name"), 
									rs.getString("user_mac"),
									rs.getString("user_ip"), 
									rs.getString("type_log"), 
									rs.getString("log_text"), 
									rs.getDate("v_date"), 
									rs.getDate("date_time"), 
									rs.getString("group_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<UserActionLog> getLogProtocol() {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM LOG_PROTOCOL where rownum <= 100 order by actiondate desc");
			while (rs.next()) {

				list.add(new 
						UserActionLog(rs.getString("branch"), 
									rs.getString("username"), 
									rs.getString("action"), 
									rs.getDate("actiondate"),
									rs.getString("userip"), 
									rs.getString("usermac"), 
									rs.getString("userhost"), 
									rs.getString("userport"), 
									rs.getString("appip"), 
									rs.getString("clientver")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<UserActionLog> getReportHistory() {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM REPORT_HISTORY where rownum <= 100 order by DATE_TIME desc");
			while (rs.next()) {

				list.add(new 
						UserActionLog(rs.getString("id"), 
									rs.getString("rep_deal_id"), 
									rs.getString("rep_id"), 
									rs.getString("par_name"),
									rs.getString("par_value"), 
									rs.getString("user_id"), 
									rs.getString("comp_name"), 
									rs.getDate("v_date"), 
									rs.getDate("date_time")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static Res doAction(String un, String pw, UserActionLog useractionlog, int actionid) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		try {
			c = ConnectionPool.getConnection();
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			if (!CheckNull.isEmpty(useractionlog.getId())) {
				cs.setString(1, "ID");
				cs.setString(2, useractionlog.getId());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getUser_id())) {
				cs.setString(1, "USER_ID");
				cs.setString(2, useractionlog.getUser_id());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getUser_name())) {
				cs.setString(1, "USER_NAME");
				cs.setString(2, useractionlog.getUser_name());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getIp_address())) {
				cs.setString(1, "IP_ADDRESS");
				cs.setString(2, useractionlog.getIp_address());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getAction_date())) {
				cs.setString(1, "ACTION_DATE");
				cs.setDate(2, new java.sql.Date(useractionlog.getAction_date().getTime()));
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getAct_type())) {
				cs.setString(1, "act_type");
				cs.setString(2, useractionlog.getAct_type());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getEntity_type())) {
				cs.setString(1, "ENTITY_TYPE");
				cs.setString(2, useractionlog.getEntity_type());
				cs.execute();
			}
			if (!CheckNull.isEmpty(useractionlog.getEntity_id())) {
				cs.setString(1, "ENTITY_ID");
				cs.setString(2, useractionlog.getEntity_id());
				cs.execute();
			}

			acs.setInt(1, 2);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();
			c.commit();
			ccs.execute();
			res = new Res(0, ccs.getString(1));
		} catch (Exception e) {
			res = new Res(-1, e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String doAction(String un, String pw, String branch, String id, int actionid) {
		String res = "";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
		try {
			c = ConnectionPool.getConnection();
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_USER_ACTIONS_LOG WHERE branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ccs.execute();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					cn = rs.getMetaData().getColumnName(i);
					// System.out.println(cn+" "+ rs.getMetaData().getColumnTypeName(i));
					if (rs.getString(cn) != null) {
						cs.setString(1, cn);
						if (rs.getMetaData().getColumnTypeName(i).equals("DATE")) {
							cs.setString(2, bdf.format(rs.getDate(cn)));
						} else {
							cs.setString(2, rs.getString(cn));
						}
						cs.execute();
					}
				}
				acs.setInt(1, 2);
				acs.setInt(2, 2);
				acs.setInt(3, actionid);
				acs.execute();
				c.commit();
			}
		} catch (Exception e) {
			// e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = e.getMessage();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(UserActionLog filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id = ?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch = ?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getUser_id())) {
			flfields.add(new FilterField(getCond(flfields) + "user_id = ?", filter.getUser_id()));
		}
		if (!CheckNull.isEmpty(filter.getUser_name())) {
			flfields.add(new FilterField(getCond(flfields) + "user_name = ?", filter.getUser_name()));
		}
		if (!CheckNull.isEmpty(filter.getIp_address())) {
			flfields.add(new FilterField(getCond(flfields) + "ip_address = ?", filter.getIp_address()));
		}
		if (!CheckNull.isEmpty(filter.getAction_date_from())) {
			flfields.add(new FilterField(getCond(flfields) + "action_date >= ?", filter.getAction_date_from()));
		}
		if (!CheckNull.isEmpty(filter.getAction_date_to())) {
			flfields.add(new FilterField(getCond(flfields) + "action_date <= ?", filter.getAction_date_to()));
		}
		if (!CheckNull.isEmpty(filter.getAct_type())) {
			flfields.add(new FilterField(getCond(flfields) + "act_type = ?", filter.getAct_type()));
		}
		if (!CheckNull.isEmpty(filter.getEntity_type())) {
			flfields.add(new FilterField(getCond(flfields) + "entity_type = ?", filter.getEntity_type()));
		}
		if (!CheckNull.isEmpty(filter.getEntity_id())) {
			flfields.add(new FilterField(getCond(flfields) + "entity_id = ?", filter.getEntity_id()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(UserActionLog filter) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_USER_ACTIONS_LOG ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<UserActionLog> getUserActionLogsFl(int pageIndex, int pageSize, UserActionLog filter) {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
//		Connection c = null;
//		int v_lowerbound = pageIndex + 1;
//		int v_upperbound = v_lowerbound + pageSize - 1;
//		int params;
//		List<FilterField> flFields = getFilterFields(filter);
//		StringBuffer sql = new StringBuffer();
//		sql.append(psql1);
//		sql.append(msql);
//		if (flFields.size() > 0) {
//			for (int i = 0; i < flFields.size(); i++) {
//				sql.append(flFields.get(i).getSqlwhere());
//			}
//		}
//		sql.append(psql2);
//		try {
//			c = ConnectionPool.getConnection();
//			PreparedStatement ps = c.prepareStatement(sql.toString());
//			for (params = 0; params < flFields.size(); params++) {
//				ps.setObject(params + 1, flFields.get(params).getColobject());
//			}
//			params++;
//			ps.setInt(params++, v_upperbound);
//			ps.setInt(params++, v_lowerbound);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				/*
//				 * list.add(new UserActionLog(rs.getString("id"), rs.getString("branch"),
//				 * rs.getString("user_id"), rs.getString("user_name"),
//				 * rs.getString("ip_address"), rs.getTimestamp("action_date"),
//				 * rs.getString("act_type"), rs.getString("entity_type"),
//				 * rs.getString("entity_id"), "1",
//				 * UserActionsAddinfoService.getUserActionsAddinfo(c, rs.getLong("id")), msql,
//				 * msql, msql, msql, msql, msql, "1", "1", "1"));
//				 */
//
//				list.add(new UserActionLog(rs.getString("id"), rs.getString("branch"), rs.getString("user_id"),
//						rs.getString("user_name"), rs.getString("ip_address"), rs.getTimestamp("action_date"),
//						rs.getString("act_type"), rs.getString("entity_type"), rs.getString("entity_id"), "1",
//						new ArrayList<UserActionsAddinfo>(), msql, msql, msql, msql, msql, msql, "1", "1", "1"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//		} finally {
//			ConnectionPool.close(c);
//		}
		return list;
	}

	public static UserActionLog prepareToLog(String action_type, String entity_type, String entity_id, HashMap<String, String> params, Object obj) {
//		User user = null;
//		String ip = "localhost";
//		try {
//			user = (User) sc.getSessionObject("current_user");
//		} catch (Exception e) {
//			// e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
//		}
//		try {
//			HttpServletRequest hr = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
//			ip = IPUtilService.getClientIpAddr(hr);
//		} catch (Exception e) {
//			// e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
//		}
//
//		params = getParamsByObject(obj, params);
		UserActionLog useractionslog = null;
//		if (user != null) {
//			useractionslog = new UserActionLog(user.getBranch(), Integer.toString(user.getId()), user.getUser_name(),
//					ip, ip, new java.sql.Date(0), action_type, entity_type, entity_id, params, null, ip, ip, ip, ip, ip,
//					ip, null, null, null);
//		} else {
//			useractionslog = new UserActionLog("00000", "0", "SYSTEMUSER", ip, ip, new java.util.Date(), action_type,
//					entity_type, entity_id, params, null, ip, ip, ip, ip, ip, ip, null, null, null);
//		}
		return useractionslog;
	}

	public static HashMap<String, String> getParamsByObject(Object obj, HashMap<String, String> params) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		if (params == null) {
			params = new HashMap<String, String>();
		}
		if (obj != null) {
			if (obj instanceof User) {
				User user = (User) obj;
				params.put("USER.USERNAME", user.getUser_name());
				params.put("USER.BRANCH", user.getBranch());
				params.put("USER.NAME", user.getFull_name());
				params.put("USER.TITLE", user.getTitle());
				params.put("USER.DATEOPEN",
						CheckNull.isEmpty(user.getDate_open()) ? "" : df.format(user.getDate_open()));
				params.put("USER.PWDEXPIRED",
						CheckNull.isEmpty(user.getPwd_expired()) ? "" : df.format(user.getPwd_expired()));
			} /*
				 * else if (obj instanceof Client) { Client c = (Client)obj;
				 * params.put("CLIENT.ID", c.getId()+""); params.put("CLIENT.BRANCH",
				 * c.getBranch()+""); params.put("CLIENT.CLIENT_ID", c.getClient_id()+"");
				 * params.put("CLIENT.NAME", c.getClient_name()+""); params.put("CLIENT.INN",
				 * c.getInn()+""); params.put("CLIENT.IS_BUDGET", c.getIs_budget()+"");
				 * params.put("CLIENT.PAYMENT_SCHEME", c.getPayment_scheme()+"");
				 * params.put("CLIENT.STATE", c.getState()+""); } else if (obj instanceof
				 * Document) { Document d = (Document)obj; params.put("DOCUMNET.ID",
				 * d.getId()+""); params.put("DOCUMNET.BRANCH", d.getBranch()+"");
				 * params.put("DOCUMNET.CLIENT_ID", d.getClient_id()+"");
				 * params.put("DOCUMNET.NUM", d.getDoc_num()+""); try
				 * {params.put("DOCUMNET.DATE", df.format(d.getD_date())+"");} catch (Exception
				 * e) {e.printStackTrace();} params.put("DOCUMNET.BANK_CL", d.getBank_cl()+"");
				 * params.put("DOCUMNET.ACC_CL", d.getAcc_cl()+"");
				 * params.put("DOCUMNET.INN_CL", d.getInn_cl()+"");
				 * params.put("DOCUMNET.NAME_CL", d.getName_cl()+"");
				 * params.put("DOCUMNET.BANK_CO", d.getBank_co()+"");
				 * params.put("DOCUMNET.ACC_CO", d.getAcc_co()+"");
				 * params.put("DOCUMNET.INN_CO", d.getInn_co()+"");
				 * params.put("DOCUMNET.NAME_CO", d.getName_co()+"");
				 * params.put("DOCUMNET.SUMMA", d.getSumma()+""); params.put("DOCUMNET.PURPOSE",
				 * d.getPurpose_code()+"-"+d.getPurpose());
				 * params.put("DOCUMNET.BUDGET_ACCOUNT", d.getBudget_account()+"");
				 * params.put("DOCUMNET.BUDGET_INN", d.getBudget_inn()+"");
				 * params.put("DOCUMNET.BUDGET_NAME", d.getBudget_name()+"");
				 * params.put("DOCUMNET.STATE", d.getState()+""); params.put("DOCUMNET.ERROR",
				 * d.getError_message()+""); } else if (obj instanceof GeneralTemplate) {
				 * GeneralTemplate d = (GeneralTemplate)obj; params.put("DOCUMNET.ID",
				 * d.getId()+""); params.put("DOCUMNET.BRANCH", d.getBranch()+"");
				 * params.put("DOCUMNET.CLIENT_ID", d.getClient_id()+"");
				 * params.put("DOCUMNET.NUM", d.getDoc_num()+""); try
				 * {params.put("DOCUMNET.DATE", df.format(d.getD_date())+"");} catch (Exception
				 * e) {e.printStackTrace();} params.put("DOCUMNET.BANK_CL", d.getBank_cl()+"");
				 * params.put("DOCUMNET.ACC_CL", d.getAcc_cl()+"");
				 * params.put("DOCUMNET.INN_CL", d.getInn_cl()+"");
				 * params.put("DOCUMNET.NAME_CL", d.getName_cl()+"");
				 * params.put("DOCUMNET.BANK_CO", d.getBank_co()+"");
				 * params.put("DOCUMNET.ACC_CO", d.getAcc_co()+"");
				 * params.put("DOCUMNET.INN_CO", d.getInn_co()+"");
				 * params.put("DOCUMNET.NAME_CO", d.getName_co()+"");
				 * params.put("DOCUMNET.SUMMA", d.getSumma()+""); params.put("DOCUMNET.PURPOSE",
				 * d.getPurpose_code()+"-"+d.getPurpose());
				 * params.put("DOCUMNET.BUDGET_ACCOUNT", d.getBudget_account()+"");
				 * params.put("DOCUMNET.BUDGET_INN", d.getBudget_inn()+"");
				 * params.put("DOCUMNET.BUDGET_NAME", d.getBudget_name()+"");
				 * params.put("DOCUMNET.STATE", d.getState()+""); } else if (obj instanceof
				 * General) { General d = (General)obj; params.put("DOCUMNET.ID", d.getId()+"");
				 * params.put("DOCUMNET.BRANCH", d.getBranch()+""); params.put("DOCUMNET.NUM",
				 * d.getDoc_num()+""); try {params.put("DOCUMNET.DATE",
				 * df.format(d.getD_date())+"");} catch (Exception e) {e.printStackTrace();}
				 * params.put("DOCUMNET.BANK_CL", d.getBank_cl()+"");
				 * params.put("DOCUMNET.ACC_CL", d.getAcc_cl()+"");
				 * params.put("DOCUMNET.NAME_CL", d.getName_cl()+"");
				 * params.put("DOCUMNET.BANK_CO", d.getBank_co()+"");
				 * params.put("DOCUMNET.ACC_CO", d.getAcc_co()+"");
				 * params.put("DOCUMNET.NAME_CO", d.getName_co()+"");
				 * params.put("DOCUMNET.SUMMA", d.getSumma()+""); params.put("DOCUMNET.PURPOSE",
				 * d.getPurpose()); params.put("DOCUMNET.STATE", d.getState()+""); } else if
				 * (obj instanceof Salaryloading) { Salaryloading s = (Salaryloading)obj;
				 * params.put("SALARY.ID", s.getId()+""); params.put("SALARY.NAME",
				 * s.getSalary_name()); params.put("SALARY.CLIENT_ID", s.getClient_id()+"");
				 * params.put("SALARY.USERNAME", s.getUsername()+"");
				 * params.put("SALARY.PURPOSE", s.getPurpose_code()+"-"+s.getPurpose_text());
				 * params.put("SALARY.FILE", s.getFile_name()); params.put("SALARY.ACCOUNT",
				 * s.getSalary_account()); params.put("SALARY.ERRMESSAGE",
				 * s.getError_message()); params.put("SALARY.AMOUNT", s.getAmount()+"");
				 * params.put("SALARY.STATE", s.getState()+""); } else if (obj instanceof
				 * PersonMap) { PersonMap p = (PersonMap)obj; params.put("PERSON.ID",
				 * p.getId()+""); params.put("PERSON.NAME", p.getPrson_name());
				 * params.put("PERSON.CLIENT_ID", p.getClient_id()+"");
				 * params.put("PERSON.TYPE", p.getPerson_type()); params.put("PERSON.EMAIL",
				 * p.getEmail_address()); params.put("PERSON.PHONE", p.getPhone_mobile());
				 * params.put("PERSON.PID", p.getPerson_id()+""); params.put("PERSON.KIND",
				 * p.getPerson_kind()+""); }
				 */
		}
		return params;
	}

	public static Res userLog(UserActionLog useractionlog) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_BF_USR_ACTLOG.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				useractionlog.setId(rs.getString("id"));
			} else {
				throw new Exception("SEQ_BF_USR_ACTLOG not found!");
			}
			ps = c.prepareStatement(
					"INSERT INTO BF_USER_ACTIONS_LOG (id, branch, user_id, user_name, ip_address, action_date, act_type, entity_type, entity_id) VALUES (?, ?, ?, ?, ?, systimestamp, ?, ?, ?)");
			ps.setString(1, useractionlog.getId());
			ps.setString(2, useractionlog.getBranch());
			ps.setString(3, useractionlog.getUser_id());
			ps.setString(4, useractionlog.getUser_name());
			ps.setString(5, useractionlog.getIp_address());
			// ps.setDate(6, new java.sql.Date(useractionlog.getAction_date().getTime()));
			ps.setString(6, useractionlog.getAct_type());
			ps.setString(7, useractionlog.getEntity_type());
			ps.setString(8, useractionlog.getEntity_id());
			if (ps.executeUpdate() != 1) {
				throw new Exception("���������� ����������, ���������� ������� �� ������������� ���������������!");
			}
			if (useractionlog.getParameters().size() > 0) {
				Object[] k = useractionlog.getParameters().keySet().toArray();
				ps = c.prepareStatement("INSERT INTO KLB_USER_ACTIONS_ADDINFO (id, a_key, a_value) VALUES (?, ?, ?)");
				for (int i = 0; i < k.length; i++) {
					ps.setString(1, useractionlog.getId());
					ps.setString(2, (String) k[i]);
					ps.setString(3, useractionlog.getParameters().get(k[i]));
					if (ps.executeUpdate() != 1) {
						throw new Exception(
								"���������� ����������, ���������� ������� �� ������������� ���������������!");
					}
				}
			}
			c.commit();
			res = new Res(0, useractionlog.getId() + "");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(ex));
			}
			res = new Res(1, CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage());
		} finally {
			ConnectionPool.close(c);
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		return res;
	}

	public static Res userLog(Connection c, UserActionLog useractionlog) {
		Res res = new Res();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("SELECT SEQ_BF_USR_ACTLOG.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				useractionlog.setId(rs.getString("id"));
			} else {
				throw new Exception("SEQ_BF_USR_ACTLOG not found!");
			}
			ps = c.prepareStatement(
					"INSERT INTO BF_USER_ACTIONS_LOG (id, branch, user_id, user_name, ip_address, action_date, act_type, entity_type, entity_id) VALUES (?, ?, ?, ?, ?, systimestamp, ?, ?, ?)");
			ps.setString(1, useractionlog.getId());
			ps.setString(2, useractionlog.getBranch());
			ps.setString(3, useractionlog.getUser_id());
			ps.setString(4, useractionlog.getUser_name());
			ps.setString(5, useractionlog.getIp_address());
			// ps.setDate(6, new java.sql.Date(useractionlog.getAction_date().getTime()));
			ps.setString(6, useractionlog.getAct_type());
			ps.setString(7, useractionlog.getEntity_type());
			ps.setString(8, useractionlog.getEntity_id());
			if (ps.executeUpdate() != 1) {
				throw new Exception("���������� ����������, ���������� ������� �� ������������� ���������������!");
			}
			if (useractionlog.getParameters().size() > 0) {
				Object[] k = useractionlog.getParameters().keySet().toArray();
				ps = c.prepareStatement("INSERT INTO KLB_USER_ACTIONS_ADDINFO (id, a_key, a_value) VALUES (?, ?, ?)");
				for (int i = 0; i < k.length; i++) {
					ps.setString(1, useractionlog.getId());
					ps.setString(2, (String) k[i]);
					ps.setString(3, useractionlog.getParameters().get(k[i]));
					if (ps.executeUpdate() != 1) {
						throw new Exception(
								"���������� ����������, ���������� ������� �� ������������� ���������������!");
					}
				}
			}
			res = new Res(0, useractionlog.getId() + "");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = new Res(1, CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e) : e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		return res;
	}

	public static UserActionLog create(UserActionLog useractionlog) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_BF_USR_ACTLOG.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				useractionlog.setId(rs.getString("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO BF_USER_ACTIONS_LOG (id, branch, user_id, user_name, ip_address, action_date, act_type, entity_type, entity_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, useractionlog.getId());
			ps.setString(2, useractionlog.getBranch());
			ps.setString(3, useractionlog.getUser_id());
			ps.setString(4, useractionlog.getUser_name());
			ps.setString(5, useractionlog.getIp_address());
			ps.setDate(6, new java.sql.Date(useractionlog.getAction_date().getTime()));
			ps.setString(7, useractionlog.getAct_type());
			ps.setString(8, useractionlog.getEntity_type());
			ps.setString(9, useractionlog.getEntity_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
		return useractionlog;
	}

	public static void update(UserActionLog useractionlog) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE BF_USER_ACTIONS_LOG SET id = ?, branch = ?, user_id = ?, user_name = ?, ip_address = ?, action_date = ?, act_type = ?, entity_type = ?, entity_id = ?  WHERE id=?");
			ps.setString(1, useractionlog.getId());
			ps.setString(2, useractionlog.getBranch());
			ps.setString(3, useractionlog.getUser_id());
			ps.setString(4, useractionlog.getUser_name());
			ps.setString(5, useractionlog.getIp_address());
			ps.setDate(6, new java.sql.Date(useractionlog.getAction_date().getTime()));
			ps.setString(7, useractionlog.getAct_type());
			ps.setString(8, useractionlog.getEntity_type());
			ps.setString(9, useractionlog.getEntity_id());
			ps.setString(10, useractionlog.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(UserActionLog useractionlog) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_USER_ACTIONS_LOG WHERE id=?");
			ps.setString(1, useractionlog.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static List<RefData> getRefData(String sql) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(new RefData(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			ConnectionPool.close(c);
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	public static List<RefData> getActions() {
		return getRefData("select * from bf_user_actions_type t order by id");
	}

	public static List<RefData> getEntities() {
		return getRefData("select * from bf_user_act_enttype t order by id");
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

	public static List<UserActionLog> getSearchedUserActionLog(String username, java.sql.Date date) {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "";
			boolean uEmpty = (username == null || username.trim().isEmpty());
			boolean dEmpty = (date == null);

			if (!uEmpty && !dEmpty) {
				sql = "select * from bf_user_actions_log where user_name = ? and TRUNC(action_date) = TRUNC(?) and rownum <= 100 order by action_date desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				ps.setDate(2, new java.sql.Date(date.getTime()));
			} else if (uEmpty && !dEmpty) {
				sql = "select * from bf_user_actions_log where TRUNC(action_date) = TRUNC(?) and rownum <= 100 order by action_date desc";
				ps = c.prepareStatement(sql);
				ps.setDate(1, new java.sql.Date(date.getTime()));
			} else if (!uEmpty && dEmpty) {
				sql = "select * from bf_user_actions_log where user_name = ? and rownum <= 100 order by action_date desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
			} else {
				sql = "select * from bf_user_actions_log where rownum <= 100 order by action_date desc";
				ps = c.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				UserActionLog user = new UserActionLog();
				user.setId(rs.getString("id"));
				user.setBranch(rs.getString("branch"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setIp_address(rs.getString("ip_address"));
				user.setAction_date(rs.getDate("action_date"));
				user.setAct_type(rs.getString("act_type"));
				user.setEntity_name(rs.getString("entity_type"));
				user.setEntity_id(rs.getString("entity_id"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<UserActionLog> getSearchedUserGeneralLog(String username, java.sql.Date date) {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "";
			boolean uEmpty = (username == null || username.trim().isEmpty());
			boolean dEmpty = (date == null);

			if (!uEmpty && !dEmpty) {
				sql = "select * from general_log where user_name = ? and TRUNC(date_time) = TRUNC(?) and rownum <= 100 order by date_time desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				ps.setDate(2, new java.sql.Date(date.getTime()));
			} else if (uEmpty && !dEmpty) {
				sql = "select * from general_log where TRUNC(date_time) = TRUNC(?) and rownum <= 100 order by date_time desc";
				ps = c.prepareStatement(sql);
				ps.setDate(1, new java.sql.Date(date.getTime()));
			} else if (!uEmpty && dEmpty) {
				sql = "select * from general_log where user_name = ? and rownum <= 100  order by date_time desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
			} else {
				sql = "select * from general_log where rownum <= 100 order by date_time desc";
				ps = c.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				UserActionLog user = new UserActionLog();
				user.setBranch(rs.getString("branch"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_mac(rs.getString("user_mac"));
				user.setIp_address(rs.getString("user_ip"));
				user.setType_log(rs.getString("type_log"));
				user.setLog_text(rs.getString("log_text"));
				user.setV_date(rs.getDate("v_date"));
				user.setDate_time(rs.getDate("date_time"));				
				user.setGroup_id(rs.getString("group_id"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<UserActionLog> getSearchedUserLogProtocol(String username, java.sql.Date date) {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "";
			boolean uEmpty = (username == null || username.trim().isEmpty());
			boolean dEmpty = (date == null);

			if (!uEmpty && !dEmpty) {
				sql = "select * from log_protocol where rownum <= 100 and username = ? and TRUNC(actiondate) = TRUNC(?) order by actiondate desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				ps.setDate(2, new java.sql.Date(date.getTime()));
			} else if (uEmpty && !dEmpty) {
				sql = "select * from log_protocol where TRUNC(date_time) = TRUNC(?) and rownum <= 100 order by actiondate desc";
				ps = c.prepareStatement(sql);
				ps.setDate(1, new java.sql.Date(date.getTime()));
			} else if (!uEmpty && dEmpty) {
				sql = "select * from log_protocol where username = ? and rownum <= 100 order by actiondate desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
			} else {
				sql = "select * from log_protocol where rownum <= 100 order by actiondate desc";
				ps = c.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				UserActionLog user = new UserActionLog();
				user.setBranch(rs.getString("branch"));
				user.setUsername(rs.getString("username"));
				user.setAction(rs.getString("action"));
				user.setActiondate(rs.getDate("actiondate"));
				user.setUserip(rs.getString("userip"));
				user.setUsermac(rs.getString("usermac"));
				user.setUserhost(rs.getString("userhost"));
				user.setUserport(rs.getString("userport"));
				user.setAppip(rs.getString("appip"));				
				user.setClientver(rs.getString("clientver"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<UserActionLog> getSearchedReportHistory(String username, java.sql.Date date1, java.sql.Date date2) {
		List<UserActionLog> list = new ArrayList<UserActionLog>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "";
			boolean uEmpty = (username == null || username.trim().isEmpty());
			boolean dEmpty = (date1 == null) && (date2 == null);

			if (!uEmpty && !dEmpty) {
				sql = "select * from report_history where rownum <= 100 and user_id = ? and TRUNC(v_date) between TRUNC(?) and TRUNC(?) order by v_date desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
				ps.setDate(2, new java.sql.Date(date1.getTime()));
				ps.setDate(3, new java.sql.Date(date2.getTime()));
			} else if (uEmpty && !dEmpty) {
				sql = "select * from report_history where TRUNC(v_date) between TRUNC(?) and TRUNC(?) and rownum <= 100 order by actiondate desc";
				ps = c.prepareStatement(sql);
				ps.setDate(1, new java.sql.Date(date1.getTime()));
				ps.setDate(2, new java.sql.Date(date2.getTime()));
			} else if (!uEmpty && dEmpty) {
				sql = "select * from report_history where user_id = ? and rownum <= 100 order by v_date desc";
				ps = c.prepareStatement(sql);
				ps.setString(1, username);
			} else {
				sql = "select * from report_history where rownum <= 100 order by v_date desc";
				ps = c.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				UserActionLog user = new UserActionLog();
				user.setId(rs.getString("id"));
				user.setRep_deal_id(rs.getString("rep_deal_id"));
				user.setRep_id(rs.getString("rep_id"));
				user.setPar_name(rs.getString("par_name"));
				user.setPar_value(rs.getString("par_value"));
				user.setUser_id(rs.getString("user_id"));
				user.setComp_name(rs.getString("comp_name"));
				user.setV_date(rs.getDate("v_date"));
				user.setDate_time(rs.getDate("date_time"));	
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
