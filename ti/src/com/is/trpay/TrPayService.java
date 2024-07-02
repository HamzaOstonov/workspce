package com.is.trpay;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.tieto.AccInfo;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import com.lowagie.text.xml.xmp.DublinCoreSchema;

public class TrPayService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = "  ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "SELECT * FROM bf_Tr_Pay P";

	public List<TrPay> getTrPay(String alias) {

		List<TrPay> list = new ArrayList<TrPay>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM TrPay order by ID desc");
			while (rs.next()) {
				list.add(new TrPay(rs.getLong("id"), rs.getString("branch"), rs
						.getInt("operation_id"), rs.getLong("amount"), rs
						.getString("card_acc"), rs.getString("cur_acc"), rs
						.getDate("date_created"), rs.getInt("parent_group_id"),
						rs.getInt("state"), rs.getString("account_no"), rs
								.getString("cl_name"), rs.getInt("deal_id"), rs
								.getInt("tieto_type"), rs.getLong("amount_t")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Res checkLimit(String un, String pw, TrPay trpay, int actionid,
			int deal_id, String alias) {
		
		LtLogger.getLogger().error(
				"check_limit_start PAY_ERROR(TrPayService s 126):\n"
						+ "details:\n" + "--tr_pay:\n branch:"
						+ trpay.getBranch()
						+ "\ngetOperation_id:"
						+ trpay.getOperation_id()
						+ "\ntrpay.getAmount():"
						+ trpay.getAmount()
						+ "\ntrpay.getCard_acc():"
						+ trpay.getCard_acc()
						+ "\ntrpay.getCur_acc():"
						+ trpay.getCur_acc()
						+ "\ntrpay.getParent_group_id():"
						+ trpay.getParent_group_id()
						+ "\ntrpay.getAccount_no():"
						+ trpay.getAccount_no()
						+ "\ntrpay.getCl_name():"
						+ trpay.getCl_name()
						+ "\ntrpay.getEmp_id():"
						+ trpay.getEmp_id()
						+ "\ntrpay.getPan():"
						+ trpay.getPan()
						+ "\ntrpay.getDoc_num():"
						+ trpay.getDoc_num()
						+ "\n--Action_id:"
						+ actionid
						+ "\n--deal_id:"
						+ deal_id
						);		
		
		//Ildarni gaplari
		/*
		 1) сначала смотрит  pay_limit_pinfl
           если лимит превышен то выдает сообщение а пункты 2-3-4 не делает

         2) если лимит не превышен в п.1 то
          смотрит  сумму новой проводки - вдруг они сразу в одной проводке внесут в валюте больше лимита (нерезидент 10 а резидент 100 тыс долл эквивалент в сумах)
          если лимит превышен то выдает сообщение а пункты  3-4 не делает

         3) если лимит не превышен в п.1 или в п.2 то
          смотрим сумму  за день (general)  с учетом суммы новой проводки  (все валюты в эквиваленте и сравниваю с лимитом)
          если лимит превышен то выдает сообщение а пункт 4 не делает

         4) если лимит не превышен в п.1 или в п.2 или в п.3   то
          смотрим сумму  за 30 дней (general_arh) с учетом суммы новой проводки и суммы проводок за день  (general)
          если лимит превышен то выдает сообщение

         5) если лимит не превышен в п.1 или в п.2 или в п.3 или в п.4  то
         нет сообщения

         сообщение выдает один раз     п.1 или  п.2 или  п.3 или  п.4
         а если п.5 то нет сообщения
		 */
		
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		PreparedStatement pcs = null;
		// System.out.println(trpay.getAmount()+ "  "+actionid);

		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			//cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call proc_check_limit(?,?,?) }");
			//ccs = c.prepareCall("{ call Param.clearparam() }");
			//ccs.execute();

			acs.setString(1, trpay.getBranch());
			acs.setString(2, trpay.getCard_acc());
			acs.setString(3, (long) trpay.getAmount() + "");
			acs.execute();

			ccs = c.prepareCall("{? = call Param.getparam('LIMIT_MSG') }");
			ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
			ccs.execute();

			c.commit();

			res = new Res(0, ccs.getString(1));

		} catch (Exception e) {

			res = new Res(-1, e.getMessage());

			LtLogger.getLogger().error(
					"check_limit PAY_ERROR(TrPayService s 126):\n"
							+ "details:\n" + "--tr_pay:\n branch:"
							+ trpay.getBranch()
							+ "\ngetOperation_id:"
							+ trpay.getOperation_id()
							+ "\ntrpay.getAmount():"
							+ trpay.getAmount()
							+ "\ntrpay.getCard_acc():"
							+ trpay.getCard_acc()
							+ "\ntrpay.getCur_acc():"
							+ trpay.getCur_acc()
							+ "\ntrpay.getParent_group_id():"
							+ trpay.getParent_group_id()
							+ "\ntrpay.getAccount_no():"
							+ trpay.getAccount_no()
							+ "\ntrpay.getCl_name():"
							+ trpay.getCl_name()
							+ "\ntrpay.getEmp_id():"
							+ trpay.getEmp_id()
							+ "\ntrpay.getPan():"
							+ trpay.getPan()
							+ "\ntrpay.getDoc_num():"
							+ trpay.getDoc_num()
							+ "\n--Action_id:"
							+ actionid
							+ "\n--deal_id:"
							+ deal_id
							+ "\n"
							+ CheckNull.getPstr(e));

			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res doAction(String un, String pw, TrPay trpay, int actionid,
			int deal_id, String alias) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		PreparedStatement pcs = null;
		// System.out.println(trpay.getAmount()+ "  "+actionid);

		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.INTEGER);

			if (trpay.getId() != null) {
				cs.setString(1, "ID");
				cs.setString(2, trpay.getId() + "");
				cs.execute();
			}
			cs.setString(1, "BRANCH");
			cs.setString(2, trpay.getBranch());
			cs.execute();
			cs.setString(1, "OPERATION_ID");
			cs.setString(2, trpay.getOperation_id() + "");
			cs.execute();
			cs.setString(1, "AMOUNT");
			cs.setString(2, (long) trpay.getAmount() + "");
			cs.execute();
			cs.setString(1, "CARD_ACC");
			cs.setString(2, trpay.getCard_acc());
			cs.execute();
			cs.setString(1, "CUR_ACC");
			cs.setString(2, trpay.getCur_acc());
			cs.execute();
			// cs.setString(1, "DATE_CREATED");
			// cs.setString(2,bdf.format(trpay.getDate_created()));
			// cs.execute();
			cs.setString(1, "PARENT_GROUP_ID");
			cs.setString(2, trpay.getParent_group_id() + "");
			cs.execute();
			// cs.setString(1, "STATE"); cs.setString(2,trpay.getState()+"");
			// cs.execute();
			cs.setString(1, "ACCOUNT_NO");
			cs.setString(2, trpay.getAccount_no());
			cs.execute();
			cs.setString(1, "CL_NAME");
			cs.setString(2, trpay.getCl_name());
			cs.execute();
			cs.setString(1, "BFUSER_ID");
			cs.setString(2, trpay.getEmp_id() + "");
			cs.execute();
			cs.setString(1, "PAN");
			cs.setString(2, trpay.getPan());
			cs.execute();
			cs.setString(1, "DOC_NUM");
			cs.setString(2, trpay.getDoc_num());
			cs.execute();
			cs.setString(1, "CUR_ACC_UZS"); 
			cs.setString(2, trpay.getCur_acc_uzs());
			cs.execute();
			cs.setString(1, "DEP_ACC"); //**
			cs.setString(2, trpay.getCur_acc_uzs());
			cs.execute();

			acs.setInt(1, 144);
			acs.setInt(2, deal_id);
			acs.setInt(3, actionid);
			acs.execute();
			
			ccs.execute();

			pcs = c.prepareStatement("insert into bf_tr_pay_printed values(?, ?)");
			pcs.setInt(1, Integer.parseInt(ccs.getString(1)));
			pcs.setInt(2, 0);
			pcs.execute();

			c.commit();
			// c.rollback();

			res = new Res(0, ccs.getString(1));

		} catch (Exception e) {

			res = new Res(-1, e.getMessage());

			LtLogger.getLogger().error(
					"__________________PAY_ERROR(TrPayService s 126):\n"
							+ "details:\n" + "--tr_pay:\n branch:"
							+ trpay.getBranch()
							+ "\ngetOperation_id:"
							+ trpay.getOperation_id()
							+ "\ntrpay.getAmount():"
							+ trpay.getAmount()
							+ "\ntrpay.getCard_acc():"
							+ trpay.getCard_acc()
							+ "\ntrpay.getCur_acc():"
							+ trpay.getCur_acc()
							+ "\ntrpay.getParent_group_id():"
							+ trpay.getParent_group_id()
							+ "\ntrpay.getAccount_no():"
							+ trpay.getAccount_no()
							+ "\ntrpay.getCl_name():"
							+ trpay.getCl_name()
							+ "\ntrpay.getEmp_id():"
							+ trpay.getEmp_id()
							+ "\ntrpay.getPan():"
							+ trpay.getPan()
							+ "\ntrpay.getDoc_num():"
							+ trpay.getDoc_num()
							+ "\n--Action_id:"
							+ actionid
							+ "\n--deal_id:"
							+ deal_id
							+ "\n"
							+ CheckNull.getPstr(e));

			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res doAction(String un, String pw, TrPay trpay, int actionid,
			String alias, Connection c) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		// Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		// System.out.println(trpay.getAmount()+ "  "+actionid);

		try {
			// c = ConnectionPool.getConnection(un,pw,alias);
			PreparedStatement ptc = c
					.prepareStatement("select t.user_name from ss_dblink_branch t where t.branch = ?");
			ptc.setString(1, trpay.getBranch());
			String pay_alias = "";
			System.out
					.println("select t.user_name from ss_dblink_branch t where t.branch = '"
							+ trpay.getBranch() + "'\n");
			ResultSet rs_ptc = ptc.executeQuery();
			if (rs_ptc.next())
				pay_alias = rs_ptc.getString("user_name");
			System.out.println("alias:" + pay_alias + "\n");
			Statement cs_as = c.createStatement();
			/*
			 * System.out.println("ALTER SESSION SET CURRENT_SCHEMA="+pay_alias+"\n"
			 * );
			 * cs_as.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+pay_alias
			 * );
			 * 
			 * 
			 * PreparedStatement ps = c.prepareStatement("select count(*) " +
			 * "res from v_bf_bank_users t " +
			 * "where UPPER(t.USER_NAME) = UPPER(?) " + "and t.BRANCH = ?");
			 * 
			 * ps.setString(1, un); ps.setString(2, trpay.getBranch());
			 * ResultSet rs = ps.executeQuery(); rs.next(); int N =
			 * rs.getInt("RES");
			 * 
			 * System.out.println("user_count:"+N+"\n"); /* if (N == 0) {
			 * System.out.println("IS_GO_USER('"+un+"', 90144)\n");
			 * CallableStatement cs_go =
			 * c.prepareCall("{ call IS_GO_USER(?, 90144) }");
			 * cs_go.setString(1, un); cs_go.execute(); }
			 */
			/*
			 * System.out.println("{ call info.init() }\n"); CallableStatement
			 * cs_in = c.prepareCall("{ call info.init() }"); cs_in.execute();
			 * System.out.println("inite\n");
			 */
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.INTEGER);

			if (trpay.getId() != null) {
				cs.setString(1, "ID");
				cs.setString(2, trpay.getId() + "");
				cs.execute();
			}
			cs.setString(1, "BRANCH");
			cs.setString(2, trpay.getBranch());
			cs.execute();
			cs.setString(1, "OPERATION_ID");
			cs.setString(2, trpay.getOperation_id() + "");
			cs.execute();
			cs.setString(1, "AMOUNT");
			cs.setString(2, (long) trpay.getAmount() + "");
			cs.execute();
			cs.setString(1, "CARD_ACC");
			cs.setString(2, trpay.getCard_acc());
			cs.execute();
			cs.setString(1, "CUR_ACC");
			cs.setString(2, trpay.getCur_acc());
			cs.execute();
			// cs.setString(1, "DATE_CREATED");
			// cs.setString(2,bdf.format(trpay.getDate_created()));
			// cs.execute();
			cs.setString(1, "PARENT_GROUP_ID");
			cs.setString(2, trpay.getParent_group_id() + "");
			cs.execute();
			// cs.setString(1, "STATE"); cs.setString(2,trpay.getState()+"");
			// cs.execute();
			cs.setString(1, "ACCOUNT_NO");
			cs.setString(2, trpay.getAccount_no());
			cs.execute();
			cs.setString(1, "CL_NAME");
			cs.setString(2, trpay.getCl_name());
			cs.execute();
			cs.setString(1, "BFUSER_ID");
			cs.setString(2, trpay.getEmp_id() + "");
			cs.execute();
			cs.setString(1, "PAN");
			cs.setString(2, trpay.getPan());
			cs.execute();
			cs.setString(1, "DOC_NUM");
			cs.setString(2, trpay.getDoc_num());
			cs.execute();

			acs.setInt(1, 144);
			acs.setInt(2, trpay.getDeal_id());
			acs.setInt(3, actionid);
			acs.execute();
			// c.commit();
			ccs.execute();
			res = new Res(0, ccs.getString(1));

		} catch (Exception e) {

			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			// ConnectionPool.close(c);
		}
		return res;
	}

	public static String doAction(String un, String pw, String branch,
			String id, int actionid, String alias) {
		String res = "";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_Tr_Pay WHERE branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ccs.execute();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					cn = rs.getMetaData().getColumnName(i);
					// System.out.println(cn+"  "+
					// rs.getMetaData().getColumnTypeName(i));
					if (rs.getString(cn) != null) {
						cs.setString(1, cn);
						if (rs.getMetaData().getColumnTypeName(i)
								.equals("DATE")) {
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
			// e.printStackTrace();
			res = e.getMessage();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	private static String getCond(List<FilterField> flfields, TrPayFilter filter) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(TrPayFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields, filter) + "id=?",
					filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(
					getCond(flfields, filter) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getOperation_id())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "operation_id=?", filter.getOperation_id()));
		}
		if (!CheckNull.isEmpty(filter.getAmount())) {
			flfields.add(new FilterField(
					getCond(flfields, filter) + "amount=?", filter.getAmount()));
		}
		if (!CheckNull.isEmpty(filter.getCard_acc())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "card_acc=?", filter.getCard_acc()));
		}
		if (!CheckNull.isEmpty(filter.getCur_acc())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "cur_acc=?", filter.getCur_acc()));
		}
		if (!CheckNull.isEmpty(filter.getDate_created())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "date_created=(TO_DATE(?, 'dd.MM.yyyy'))", df
					.format(filter.getDate_created())));
		}
		if (!CheckNull.isEmpty(filter.getParent_group_id())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "parent_group_id=?", filter.getParent_group_id()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields, filter) + "state=?",
					filter.getState()));
		}

		if (filter.isShow_act()) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ " exists( " + "Select 'X' "
					+ "      From   BF_USER_ROLES   UR, "
					+ "              BF_ROLE_MODULES RM,  "
					+ "              BF_ROLE_ACTIONS RA,  "
					+ "              BF_ACTIONS BA,  "
					+ "              bf_tr_trans BT  "
					+ "       Where  UR.BRANCH = '" + filter.getU_br() + "'  "
					+ "              and UR.USERID = ? "
					+ "              and UR.Roleid = RM.ROLEID "
					+ "              and RM.ROLEID = RA.ROLEID  "
					+ "              and RM.MODULEID = RA.MID   "
					+ "              and RA.MID = 13  "
					+ "              and RA.MID = BA.MID "
					+ "              and RA.ACTIONID = BA.ID "
					+ "              and RA.DEAL_ID = BA.DEAL_ID "
					+ "              "
					+ "              and BA.ID = BT.ACTION_ID "
					+ "              and BA.DEAL_ID = BT.DEAL_ID "
					+ "              and P.STATE = BT.STATE_BEGIN "
					+ "			  and P.deal_id = BA.DEAL_ID" + "       )", filter
					.getU_id()));
		}

		if (!CheckNull.isEmpty(filter.getAccount_no())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "account_no=?", filter.getAccount_no()));
		}
		if (!CheckNull.isEmpty(filter.getCl_name())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "cl_name=?", filter.getCl_name()));
		}
		if (!CheckNull.isEmpty(filter.getDeal_id())) {
			flfields.add(new FilterField(getCond(flfields, filter)
					+ "deal_id=?", filter.getDeal_id()));
		}

		flfields.add(new FilterField(getCond(flfields, filter) + "rownum<?",
				1001));

		return flfields;
	}

	public static int getCount(TrPayFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bf_Tr_Pay p");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
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

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<TrPay> getTrPaysFl(int pageIndex, int pageSize,
			TrPayFilter filter, String alias, boolean sorted_desc) {

		List<TrPay> list = new ArrayList<TrPay>();
		Connection c = null;
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

		sql.append("order by P.doc_num " + (sorted_desc ? "desc" : ""));
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TrPay(rs.getLong("id"), rs.getString("branch"), rs
						.getInt("operation_id"), rs.getLong("amount"), rs
						.getString("card_acc"), rs.getString("cur_acc"), rs
						.getDate("date_created"), rs.getInt("parent_group_id"),
						rs.getInt("state"), rs.getString("account_no"), rs
								.getString("cl_name"), rs.getInt("emp_id"), rs
								.getString("pan"), rs.getInt("deal_id"), rs
								.getInt("tieto_type"), rs.getLong("amount_t"),
						rs.getString("doc_num")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static TrPay getTrPay(int trpayId, String alias) {

		TrPay trpay = new TrPay();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_tr_pay WHERE id=?");
			ps.setInt(1, trpayId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trpay = new TrPay();

				trpay.setId(rs.getLong("id"));
				trpay.setBranch(rs.getString("branch"));
				trpay.setOperation_id(rs.getInt("operation_id"));
				trpay.setAmount(rs.getLong("amount"));
				trpay.setCard_acc(rs.getString("card_acc"));
				trpay.setCur_acc(rs.getString("cur_acc"));
				trpay.setDate_created(rs.getDate("date_created"));
				trpay.setParent_group_id(rs.getInt("parent_group_id"));
				trpay.setState(rs.getInt("state"));
				trpay.setAccount_no(rs.getString("account_no"));
				trpay.setCl_name(rs.getString("cl_name"));
				trpay.setEqv_amount(rs.getLong("eqv_amount"));
				trpay.setDoc_num(rs.getString("doc_num"));
				trpay.setDeal_id(rs.getInt("deal_id"));
				trpay.setPan(rs.getString("pan"));
				trpay.setTieto_type(rs.getInt("tieto_type"));
				trpay.setAmount_t(rs.getLong("amount_t"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LtLogger.getLogger().error(
					"__________________PAY_ERROR(TrPayService s 458):\n"
							+ CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return trpay;
	}

	public static TrPay create(TrPay trpay, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_trpay.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trpay.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO trpay (id, branch, operation_id, amount, card_acc, cur_acc, date_created, parent_group_id, state, account_no, cl_name, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");

			ps.setLong(1, trpay.getId());
			ps.setString(2, trpay.getBranch());
			ps.setInt(3, trpay.getOperation_id());
			ps.setLong(4, trpay.getAmount());
			ps.setString(5, trpay.getCard_acc());
			ps.setString(6, trpay.getCur_acc());
			ps.setDate(7, new java.sql.Date(trpay.getDate_created().getTime()));
			ps.setInt(8, trpay.getParent_group_id());
			ps.setInt(9, trpay.getState());
			ps.setString(10, trpay.getAccount_no());
			ps.setString(11, trpay.getCl_name());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return trpay;
	}

	public static void update(TrPay trpay, String alias) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("UPDATE trpay SET id=?, branch=?, operation_id=?, amount=?, card_acc=?, cur_acc=?, date_created=?, parent_group_id=?, state=?, account_no=?, cl_name=?,  WHERE trpay_id=?");

			ps.setLong(1, trpay.getId());
			ps.setString(2, trpay.getBranch());
			ps.setInt(3, trpay.getOperation_id());
			ps.setLong(4, trpay.getAmount());
			ps.setString(5, trpay.getCard_acc());
			ps.setString(6, trpay.getCur_acc());
			ps.setDate(7, new java.sql.Date(trpay.getDate_created().getTime()));
			ps.setInt(8, trpay.getParent_group_id());
			ps.setInt(9, trpay.getState());
			ps.setString(10, trpay.getAccount_no());
			ps.setString(11, trpay.getCl_name());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void remove(TrPay trpay, String alias) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM bf_tr_pay WHERE id=?");
			ps.setLong(1, trpay.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static List<Action> getActions(int state_begin, int userid,
			String user_branch, int deal_id, String alias) {

		List<Action> list = new ArrayList<Action>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			/*
			 * System.out.println("select a.* from BF_TR_TRANS t, bf_actions a "+
			 * "where a.mid=13 and a.id=t.action_id and  t.state_begin="
			 * +state_begin
			 * +" and a.deal_id = t.deal_id and t.deal_id = "+deal_id+" "+
			 * "and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="
			 * +userid+" and r.actionid=a.id and r.deal_id = t.deal_id)");
			 */
			PreparedStatement ps = c
					.prepareStatement("select a.* from BF_TR_TRANS t, bf_actions a "
							+ "where a.mid=13 and a.id=t.action_id and  t.state_begin=? and a.deal_id = t.deal_id and t.deal_id = ?"
							+ "and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid=? and u.branch=? and r.actionid=a.id and r.deal_id = t.deal_id)");
			ps.setInt(1, state_begin);
			ps.setInt(2, deal_id);
			ps.setInt(3, userid);
			ps.setString(4, user_branch);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Action(rs.getInt("id"), rs.getInt("mid"), rs
						.getString("name"), rs.getString("icon"), rs
						.getInt("rep_type_id")));

			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<State> getStates(String alias) {

		List<State> list = new ArrayList<State>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("select st.id state_id, st.deal_id, st.name title from bf_tr_state st where st.flag_client_view = 1");
			// ps.setInt(1, deal_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new State(rs.getInt("state_id"),
						rs.getString("title"), rs.getInt("deal_id")));
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static boolean isEndState(int actionid, int deal_id, String alias) {

		boolean res = false;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("select s.flag_createdoct "
							+ "from bf_tr_state s ,bf_tr_trans t "
							+ "where t.state_end=s.id and t.action_id=? and s.deal_id=?");

			ps.setInt(1, actionid);
			ps.setInt(2, deal_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt("flag_createdoct") == 1;

			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return res;

	}

	public static boolean isEndState(Long pay_id, String alias, Connection c) {
		boolean res = false;

		try {
			// c = ConnectionPool.getConnection( alias );
			PreparedStatement ps = c
					.prepareStatement("select st.flag_createdoct res "
							+ "from bf_tr_pay t," + "bf_tr_state st "
							+ "where t.id = ? " + "and st.deal_id = t.deal_id "
							+ "and st.id = t.state");
			ps.setLong(1, pay_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt("res") == 1;
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			// ConnectionPool.close(c);
		}
		return res;
	}

	public static List<TrPayDocs> getTrPayDocs(long pay_id, String alias) {

		List<TrPayDocs> list = new ArrayList<TrPayDocs>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM BF_TR_PAYDOCS where PAY_ID=?");
			ps.setLong(1, pay_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TrPayDocs(rs.getLong("id"), rs.getInt("pay_id"),
						rs.getString("branch"), rs.getDate("d_date"), rs
								.getString("bank_cl"), rs.getString("acc_cl"),
						rs.getString("name_cl"), rs.getString("bank_co"), rs
								.getString("acc_co"), rs.getString("name_co"),
						rs.getLong("summa"), rs.getString("purpose"), rs
								.getString("type_doc"), rs.getString("pdc"), rs
								.getInt("parent_group_id"), rs
								.getInt("parent_id"),
						rs.getString("cash_code"), rs
								.getDouble("id_transh_purp"), rs
								.getString("schema_name"), rs.getInt("ord"), rs
								.getString("g_branch"), rs.getLong("g_docid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static String getOperation_desc(int operation_id, String alias) {

		String res = null;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM BF_TR_OPERATIONS where ID=?");
			ps.setLong(1, operation_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString("descripption");
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(
					"__________________PAY_ERROR(TrPayService s 733):\n"
							+ CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;

	}

	public static String getDeal_desc(int deal_id, String alias) {

		String res = null;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM ss_deal where ID=? and group_id='144'");
			ps.setLong(1, deal_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;

	}

	public static List<Paydoc> getPaydocs(long pay_id, String alias) {

		List<Paydoc> list = new ArrayList<Paydoc>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("Select T.ORD, T.BRANCH, T.D_DATE, T.BANK_CL, T.ACC_CL, T.NAME_CL, T.BANK_CO, T.ACC_CO, T.NAME_CO, T.PURPOSE, T.SUMMA, (T.TYPE_DOC || ' - ' || TD.NAIM_DOC) AS TYPEDOC, T.PDC, T.CASH_CODE, T.ID_TRANSH_PURP, T.G_BRANCH, T.G_DOCID From   BF_TR_PAYDOCS T, V_TYPEDOC TD Where  T.PAY_ID = ? and TD.KOD_DOC(+) = T.TYPE_DOC");
			// System.out.println("Select T.ORD, T.BRANCH, T.D_DATE, T.BANK_CL, T.ACC_CL, T.NAME_CL, T.BANK_CO, T.ACC_CO, T.NAME_CO, T.PURPOSE, T.SUMMA, (T.TYPE_DOC || ' - ' || TD.NAIM_DOC) AS TYPEDOC, T.PDC, T.CASH_CODE, T.ID_TRANSH_PURP, T.G_BRANCH, T.G_DOCID From   BF_TR_PAYDOCS T, V_TYPEDOC TD Where  T.PAY_ID = '"+pay_id+"' and TD.KOD_DOC(+) = T.TYPE_DOC");
			ps.setLong(1, pay_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Paydoc(rs.getInt("ORD"), rs.getString("BRANCH"),
						rs.getDate("D_DATE"), rs.getString("BANK_CL"), rs
								.getString("ACC_CL"), rs.getString("NAME_CL"),
						rs.getString("BANK_CO"), rs.getString("ACC_CO"), rs
								.getString("NAME_CO"), rs.getString("PURPOSE"),
						rs.getDouble("SUMMA") / 100, rs.getString("TYPEDOC"),
						rs.getString("PDC"), rs.getString("CASH_CODE"), rs
								.getInt("ID_TRANSH_PURP"), rs
								.getString("G_BRANCH"), rs.getInt("G_DOCID")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static int get_tieto_operation_code(int operation_id, String alias) {
		int res = 0;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("select t.tieto_type res from bf_tieto_type_operations t where t.operation_id = ?");
			ps.setInt(1, operation_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt("res");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String get_operation_desc(int operation_id, String alias) {
		String res = null;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("select t.descripption res from bf_tr_operations t where t.id=?");
			ps.setInt(1, operation_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getString("res");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static boolean isInPrintableState(int dealId, int state, String alias) {
		boolean res = false;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("Select count(ST.ID) as flag_print From   BF_TR_STATE ST Where  ST.DEAL_ID = ? and st.id = ? and (ST.FLAG_CREATEDOC = 1 OR ST.FLAG_CREATEDOCT = 1 OR ST.FLAG_DELETEDOC = 1)");
			ps.setInt(1, dealId);
			ps.setInt(2, state);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				res = ((rs.getString("flag_print").compareTo("0") == 0) ? true
						: false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static int is_printed(int pay_id, String alias) {
		int res = 0;

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("select t.printed from bf_tr_pay_printed t where t.pay_id = ?");
			ps.setInt(1, pay_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getInt("printed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static void set_printed(int pay_id, String alias) {
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("update bf_tr_pay_printed t set t.printed = '1' where t.pay_id = ?;");
			ps.setInt(1, pay_id);
			ps.execute();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String get_all_unprinted_by_day(String branch, String alias) {
		String res = null;

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("select p.id from bf_tr_pay p, bf_tr_pay_printed pri where p.id = pri.pay_id and p.date_created = trunc(sysdate) and p.branch = ? and pri.printed = 0");
			ps.setString(1, branch);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("id");
			}
			while (rs.next()) {
				res += ";" + rs.getString("id");
			}

			PreparedStatement ps_upd = c
					.prepareStatement("update bf_tr_pay_printed pri set pri.printed = 1 "
							+ "where exists (select 'X' from bf_tr_pay p where p.id = pri.pay_id and p.date_created = trunc(sysdate) and p.branch = ? and pri.printed = 0)");
			ps_upd.setString(1, branch);

			ps_upd.execute();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return res;
	}

	public static boolean get_confirm_action(int group_id, int deal_id,
			int action_id, String alias) {
		boolean res = false;

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("select * from bf_tr_action_confirm t where t.group_id = ? and t.deal_id = ? and t.id = ?");
			ps.setInt(1, group_id);
			ps.setInt(2, deal_id);
			ps.setInt(3, action_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return res;
	}

	public static String get_alias(String branch, String alias) {
		String res = "";

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps = c
					.prepareStatement("select * from ss_dblink_branch br where br.branch = ?");
			ps.setString(1, branch);
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getString("user_name");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return res;
	}

	/*
	 * public static String getDuplicate(String branch, String alias) {
	 * Ti_Duplicate duplicate = new Ti_Duplicate(); Connection c = null;
	 * 
	 * try { c = ConnectionPool.getConnection(alias);
	 * 
	 * PreparedStatement ps =
	 * c.prepareStatement("select * from ss_dblink_branch br where br.branch = ?"
	 * ); ResultSet rs = ps.executeQuery(); rs.next(); duplicate.setSeq(seq);
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { ConnectionPool.close(c); }
	 * 
	 * return res; }
	 * 
	 * public static String getDuplicateDebit(String branch, String alias) {
	 * String res = "";
	 * 
	 * Connection c = null;
	 * 
	 * try { c = ConnectionPool.getConnection(alias);
	 * 
	 * PreparedStatement ps =
	 * c.prepareStatement("select * from ss_dblink_branch br where br.branch = ?"
	 * ); ResultSet rs = ps.executeQuery(); rs.next(); res =
	 * rs.getString("user_name");
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } finally { ConnectionPool.close(c); }
	 * 
	 * return res; }
	 */

}
