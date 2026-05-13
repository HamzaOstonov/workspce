package com.is.card_to_card;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Exception;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
import java.util.Random;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.Account;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;
import com.is.card_to_card.Ton;

public class CardtcService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " order by id desc) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM BF_TR_ACC ";
	private static Long id_id;
	private static Integer id_for_general;
	private static Date date1;
	private static List<RefData> listCardTypes;
	public static Long transaction1_general_id, transaction2_general_id, transaction_general_id;
	public static String transaction1code;

	public List<TrAcc> getTrAcc(String alias) {

		List<TrAcc> list = new ArrayList<TrAcc>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM TrAcc");
			while (rs.next()) {
				list.add(new TrAcc(rs.getInt("id"), rs.getString("branch"), rs.getInt("acc_template_id"),
						rs.getString("acc_mfo"), rs.getString("account"), rs.getString("acc_name")));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(s);
			ConnectionPool.close(c);
		}
		return list;

	}

	/*
	 * public static String doAction(String un,String pw, TrAcc tracc,int actionid)
	 * { Ton ton =null; SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	 * Connection c = null; CallableStatement cs = null; CallableStatement acs =
	 * null; CallableStatement ccs = null;
	 * 
	 * try { c = ConnectionPool.getConnection(un,pw); cs =
	 * c.prepareCall("{ call Param.SetParam(?,?) }"); acs =
	 * c.prepareCall("{ call kernel.doAction(?,?,?) }"); ccs =
	 * c.prepareCall("{ call Param.clearparam() }"); ccs.execute(); ccs =
	 * c.prepareCall("{? = call Param.getparam('ID') }");
	 * 
	 * cs.setString(1, "ID"); cs.setString(2,tracc.getId()); cs.execute();
	 * cs.setString(1, "BRANCH"); cs.setString(2,tracc.getBranch()); cs.execute();
	 * cs.setString(1, "ACC_TEMPLATE_ID");
	 * cs.setString(2,tracc.getAcc_template_id()); cs.execute(); cs.setString(1,
	 * "ACC_MFO"); cs.setString(2,tracc.getAcc_mfo()); cs.execute(); cs.setString(1,
	 * "ACCOUNT"); cs.setString(2,tracc.getAccount()); cs.execute(); cs.setString(1,
	 * "ACC_NAME"); cs.setString(2,tracc.getAcc_name()); cs.execute();
	 * 
	 * acs.setInt(1, 2); acs.setInt(2, 2); acs.setInt(3,actionid); acs.execute();
	 * c.commit(); ccs.execute(); ton = new Ton(0,ccs.getString(1));
	 * 
	 * 
	 * } catch (Exception e) { ton = new Ton(-1, e.getMessage()); } finally {
	 * ConnectionPool.close(c); } return ton; }
	 * 
	 * public static String doAction(String un,String pw, String branch, String
	 * id,int actionid) { String ton =""; SimpleDateFormat bdf = new
	 * SimpleDateFormat("dd.MM.yyyy"); Connection c = null; CallableStatement cs =
	 * null; CallableStatement acs = null; CallableStatement ccs = null; String cn;
	 * try { c = ConnectionPool.getConnection(un,pw); cs =
	 * c.prepareCall("{ call Param.SetParam(?,?) }"); acs =
	 * c.prepareCall("{ call kernel.doAction(?,?,?) }"); ccs =
	 * c.prepareCall("{ call Param.clearparam() }");
	 * 
	 * 
	 * PreparedStatement ps =
	 * c.prepareStatement("SELECT * FROM TrAcc WHERE branch=? and id=?");
	 * ps.setString(1, branch); ps.setString(2, id); ResultSet rs =
	 * ps.executeQuery(); if (rs.next()) { ccs.execute(); for (int i=1;
	 * i<=rs.getMetaData().getColumnCount();i++){ cn =
	 * rs.getMetaData().getColumnName(i); // System.out.println(cn+"  "+
	 * rs.getMetaData().getColumnTypeName(i)); if( rs.getString(cn)!=null){
	 * cs.setString(1, cn); if
	 * (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
	 * cs.setString(2,bdf.format(rs.getDate(cn))); }else{
	 * cs.setString(2,rs.getString(cn)); } cs.execute(); } }
	 * 
	 * acs.setInt(1, 2); acs.setInt(2, 2); acs.setInt(3,actionid); acs.execute();
	 * c.commit(); } } catch (Exception e) { //
	 * ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); ton =
	 * e.getMessage(); } finally { ConnectionPool.close(c); } return ton; }
	 * 
	 */
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

//	private static List<FilterField> getFilterFields(CardFilter filter) {
//		List<FilterField> flfields = new ArrayList<FilterField>();
//
//		if (!CheckNull.isEmpty(filter.getId())) {
//			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
//		}
//		if (!CheckNull.isEmpty(filter.getBranch())) {
//			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
//		}
//		if (!CheckNull.isEmpty(filter.getAcc_template_id())) {
//			flfields.add(new FilterField(getCond(flfields) + "acc_template_id=?", filter.getAcc_template_id()));
//		}
//		if (!CheckNull.isEmpty(filter.getAcc_mfo())) {
//			flfields.add(new FilterField(getCond(flfields) + "acc_mfo=?", filter.getAcc_mfo()));
//		}
//		if (!CheckNull.isEmpty(filter.getAccount())) {
//			flfields.add(new FilterField(getCond(flfields) + "account like(?)", filter.getAccount()));
//		}
//		if (!CheckNull.isEmpty(filter.getAcc_name())) {
//			flfields.add(new FilterField(getCond(flfields) + "acc_name=?", filter.getAcc_name()));
//		}
//
//		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
//
//		return flfields;
//	}

	private static List<FilterField> getFilterFields(Card filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getCard_number())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getCard_number()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_template_id=?", filter.getAccount()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_mfo=?", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getExpiry())) {
			flfields.add(new FilterField(getCond(flfields) + "account like(?)", filter.getExpiry()));
		}
		if (!CheckNull.isEmpty(filter.getStatus())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_name=?", filter.getStatus()));
		}
		if (!CheckNull.isEmpty(filter.getContract())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_name=?", filter.getContract()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(Card filter) {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_TR_ACC ");
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
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return n;

	}

	public static int getCount1(Card filter, String alias) {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM BF_TR_ACC ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Card> getTrAccsFl(int pageIndex, int pageSize, Card filter) {

		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
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
				list.add(new Card(rs.getString("id"), rs.getString("branch"), rs.getString("card_number"),
						rs.getString("account"), rs.getString("name"), msql));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return list;

	}

	public TrAcc getTrAcc(int traccId, String alias) {

		TrAcc tracc = new TrAcc();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM BF_TR_ACC WHERE tracc_id=?");
			ps.setInt(1, traccId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tracc = new TrAcc();

				tracc.setId(rs.getInt("id"));
				tracc.setBranch(rs.getString("branch"));
				tracc.setAcc_template_id(rs.getInt("acc_template_id"));
				tracc.setAcc_mfo(rs.getString("acc_mfo"));
				tracc.setAccount(rs.getString("account"));
				tracc.setAcc_name(rs.getString("acc_name"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(ps);
			ConnectionPool.close(c);
		}
		return tracc;
	}

	public static TrAcc create(TrAcc tracc, String alias) {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_BF_TR_ACC.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				tracc.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO BF_TR_ACC (id, branch, acc_template_id, acc_mfo, account, acc_name, ) VALUES (?,?,?,?,?,?,)");

			ps.setLong(1, tracc.getId());
			ps.setString(2, tracc.getBranch());
			ps.setLong(3, tracc.getAcc_template_id());
			ps.setString(4, tracc.getAcc_mfo());
			ps.setString(5, tracc.getAccount());
			ps.setString(6, tracc.getAcc_name());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(ps);
			ConnectionPool.close(c);
		}
		return tracc;
	}

	public static void update(TrAcc tracc, String alias) {

		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE BF_TR_ACC " + "SET account=? " + "WHERE id=?");

			// ps.setString(1,tracc.getBranch());
			// ps.setInt(2,tracc.getAcc_template_id());
			// ps.setString(3,tracc.getAcc_mfo());
			ps.setString(1, tracc.getAccount());
			// ps.setString(5,tracc.getAcc_name());
			ps.setInt(2, tracc.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(c);
		}

	}

	public static void remove(TrAcc tracc, String alias) {

		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("DELETE FROM BF_TR_ACC WHERE id=?");
			ps.setInt(1, tracc.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
			ConnectionPool.close(ps);
		}
	}

	public static List<Account> getAccount(TrAcc tracc, String fl, String alias, String branch) {

		if (fl.compareTo("") == 0)
			fl = "%";

		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String whr = "()";
		String nm = "";

		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement ps1 = c.prepareStatement("select * from ss_dblink_branch t where t.branch = ?");
			ps1.setString(1, branch);
			ResultSet rs1 = ps1.executeQuery();
			String us = null;
			if (rs1.next()) {
				us = rs1.getString("user_name");
			}
			c = ConnectionPool.getConnection(us);

			ps = c.prepareStatement("select * FROM BF_TR_ACC_TEMPLATE WHERE id=?");
			ps.setInt(1, tracc.getAcc_template_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				whr = rs.getString("acc_mask");
				nm = rs.getString("acc_name");
			}

			Statement s = c.createStatement();
			rs = s.executeQuery(
					"SELECT * FROM Account where id like '" + whr + "' and id like '" + fl + "' and state=2 ");
			// System.out.println("SELECT:"+"SELECT * FROM Account where id like
			// '"+whr+"' and id like '"+fl+"' and state=2 ");
			while (rs.next()) {
				list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"),
						rs.getString("currency"), rs.getString("client"), rs.getString("id_order"),
						rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"),
						rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"),
						rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"),
						rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"),
						rs.getInt("acc_group_id"), rs.getInt("state"), rs.getString("state_desc")));
			}
			if (whr.endsWith("ACC")) {
				Account tacc = new Account();
				tacc.setId(whr);
				tacc.setName(nm);
				tacc.setBranch(tracc.getBranch());
				list.add(tacc);
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<Account> getAccount(String fl, String alias, String branch) {

		if (fl.compareTo("") == 0)
			fl = "%";

		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		ResultSet rs1 = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
//			    String nm = "";

		try {
			c = ConnectionPool.getConnection(alias);

			ps1 = c.prepareStatement("select * from ss_dblink_branch t where t.branch = ?");
			ps1.setString(1, branch);
			rs1 = ps1.executeQuery();
			String us = null;
			if (rs1.next()) {
				us = rs1.getString("user_name");
			}
			ConnectionPool.close(c);
			c = ConnectionPool.getConnection(us);

			Statement s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM Account where id like '" + fl + "' and rownum < 50");
			System.out.println(
					"SELECT:" + "SELECT * FROM Account where id like whr and id lik e '" + fl + "' and state=2 ");
			while (rs.next()) {
				list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"),
						rs.getString("currency"), rs.getString("client"), rs.getString("id_order"),
						rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"),
						rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"),
						rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"),
						rs.getDate("l_date"), rs.getDate("date_open"), rs.getDate("date_close"),
						rs.getInt("acc_group_id"), rs.getInt("state"), rs.getString("state_desc")));
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(ps1);
			ConnectionPool.close(rs);
			ConnectionPool.close(rs1);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<CardFilter> getClientCards(CardFilter filter, String alias) {
		return null;
//			    List<CardFilter> list = new ArrayList<CardFilter>();
//			    Connection c = null;
//			    ResultSet rs = null;
//			    PreparedStatement ps = null;
		//
//			    try {
//			      c = ConnectionPool.getConnection(alias);
//			      ps = c.prepareStatement("SELECT c.branch, c.client_b,  b.card_acct AS schet, c.card_name AS embossed_ch_name, "
//			          + "TO_CHAR(b.ab_expirity, 'DD-MM-YYYY') AS exp_date, c.status1 AS status, TO_CHAR(c.account_no) AS contract"
//			          + "FROM humo_cards c JOIN bf_empc_accounts b ON c.client = b.client"
//			          + "WHERE c.client_b = ? AND ROWNUM < 50 UNION ALL"
//			          + "SELECT branch,  SUBSTR(def_atm_account, 15, 8) as client_b,  embossed_ch_name, expiration_date, "
//			          + "card_status, card_type AS contract_id FROM v_card_all_rmm WHERE SUBSTR(card_number, "
//			          + "1, 6) IN ('414565', '402306') AND client_b = ?"
//			          + "AND ROWNUM < 50 UNION ALL SELECT branch, card_number, SUBSTR(def_atm_account, -20) AS "
//			          + "def_atm_account, embossed_ch_name, expiration_date, card_status, contract_id"
//			          + "from card WHERE client_b = ? AND ROWNUM < 50");
//			      ps.setString(1,filter.getClient_code());
//			            ps.setString(2,filter.getClient_code());
//			            ps.setString(3,filter.getClient_code());
//			    } catch (Exception e) {
//			      e.printStackTrace(); 
//			      ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
//			    } finally {
//			      try {
//			        if (rs != null)
//			          rs.close();
//			        if (ps != null)
//			          ps.close();
//			        ConnectionPool.close(ps);
//			        ConnectionPool.close(rs);
//			        ConnectionPool.close(c);
//			      } catch (Exception e) {
//			        e.printStackTrace(); 
//			      }
//			    }
//			    return list;
	}

	public static List<Card> getCards(int itemStartNumber, int pageSize, Card filter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int v_lowerbound = itemStartNumber + 1;
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
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			rs = ps.executeQuery();
//			      while (rs.next()) {
//			        list.add(new Card(
//			              rs.getInt("id"), 
//			              rs.getString("branch"), 
//			              rs.getInt("acc_template_id"),
//			              rs.getString("acc_mfo"), 
//			              rs.getString("account"), 
//			              rs.getString("acc_name"),
//			              rs.getString("acc_name")));
//			      }
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static void create(Card card1, Card card2) {
//			    Connection c = null;
//			    ResultSet rs = null;
//			    PreparedStatement ps = null;
		//
//			    try {
//			      c = ConnectionPool.getConnection();
//			      ps = c.prepareStatement("INSERT INTO temp_card_to_card VALUES (0, ?, ?, 5000)");
//			      ps.setString(1, card1.getCard_number());
//			      ps.setString(2, card2.getCard_number());
//			      ps.executeUpdate();
//			      c.commit();
//			    } catch (SQLException e) {
//			      com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
//			      e.printStackTrace();
		//
//			    } finally {
//			      ConnectionPool.close(ps);
//			      ConnectionPool.close(rs);
//			      ConnectionPool.close(c);
//			    }
	}

	// main tables
	public static List<Card> getHumoCards(Card cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT c.branch, c.real_card as real_card, c.client_b as client_b,"
					+ "b.tranz_acct as schet, c.card_name as name, b.ab_expirity as exp_date,"
					+ "c.status1 as status FROM humo_cards c, bf_empc_accounts b WHERE c.client = b.client "
					+ "and rownum < 50 ";
			int i = 0;
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				sql += " and client_b like ? ";
			} else {
				sql += "";
			}

			ps = c.prepareStatement(sql);
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getClient_code() + "%");
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("real_card"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("schet"));
				card.setName(rs.getString("name"));
				card.setExpiry(rs.getString("exp_date"));
				card.setStatus(rs.getString("status"));
				list.add(card);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}

	public static List<Card> getUzcardCards(Card cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "select branch, card_number, substr(def_atm_account, 15, 8) as client_b, "
					+ "def_pos_account, embossed_ch_name, expiration_date, card_status from card "
					+ "where rownum < 50";

			int i = 0;
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				sql += " and substr(def_atm_account, 15, 8) like ? ";
			} else {
				sql += "";
			}

			ps = c.prepareStatement(sql);
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getClient_code() + "%");
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("def_pos_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				list.add(card);
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

	public static List<Card> getVisaSumCards(String vc_pinfl, final String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT vc.branch, vc.card_number, SUBSTR(vc.def_atm_account, 15, 8) AS client_b, "
					+ " SUBSTR(vc.def_atm_account, -20) AS def_atm_account, vc.embossed_ch_name, vc.expiration_date, TO_CHAR(vc.card_status) AS card_status "
					+ " FROM v_card_all_rmm vc, client_p cp WHERE ROWNUM < 50 "
					+ "and cp.branch=vc.branch "
					+ "and cp.id=SUBSTR(vc.def_atm_account, 15, 8) "
					+ "and cp.pinfl=? ";
//			System.out.println("prepareStatement dan oldin cardfilter: " + cardfilter);
//			System.out.println("prepareStatement dan oldin cardfilter.getClient_code: " + cardfilter.getClient_code());
//			System.out.println("prepareStatement dan oldin cardfilter.getCard_number: " + cardfilter.getCard_number());
//			System.out.println("prepareStatement dan oldin cardfilter.getName: " + cardfilter.getName());

			ps = c.prepareStatement(sql);
			ps.setString(1, vc_pinfl);
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("def_atm_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				list.add(card);
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
//			System.out.println("return list dan oldin cardfilter: " + cardfilter);
		}
		return list;
	}

	public static List<Card> getHumoCardsOld(CardFilter cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT c.branch, c.real_card as real_card, c.client_b as client_b,"
					+ "b.tranz_acct as schet, c.card_name as name, b.ab_expirity as exp_date,"
					+ "c.status1 as status FROM humo_cards c, bf_empc_accounts b WHERE c.client = b.client "
					+ "and rownum < 50 ";
			int i = 0;
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				sql += " and client_b like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				sql += " and real_card like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				sql += " and regexp_like(c.card_name, ?, 'i') ";
			} else {
				sql += "";
			}

			ps = c.prepareStatement(sql);
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getClient_code() + "%");
			}
			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getCard_number() + "%");
			}
			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				i++;
				ps.setString(i, cardfilter.getName());
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("real_card"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("schet"));
				card.setName(rs.getString("name"));
				card.setExpiry(rs.getString("exp_date"));
				card.setStatus(rs.getString("status"));
				list.add(card);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				ConnectionPool.close(ps);
				ConnectionPool.close(rs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<Card> getUzcardCardsOld(CardFilter cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "select branch, card_number, substr(def_atm_account, 15, 8) as client_b, "
					+ "def_pos_account, embossed_ch_name, expiration_date, card_status from card "
					+ "where rownum < 50";

			int i = 0;
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				sql += " and substr(def_atm_account, 15, 8) like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				sql += " and card_number like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				sql += " and regexp_like(embossed_ch_name, ?, 'i') ";
			} else {
				sql += "";
			}

			ps = c.prepareStatement(sql);
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getClient_code() + "%");
			}
			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getCard_number() + "%");
			}
			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				i++;
				ps.setString(i, cardfilter.getName());
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("def_pos_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				list.add(card);
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

	public static List<Card> getVisaSumCardsOld(CardFilter cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT branch, card_number, SUBSTR(def_atm_account, 15, 8) AS client_b, "
					+ "SUBSTR(def_atm_account, -20) AS def_atm_account, embossed_ch_name, expiration_date, TO_CHAR(card_status) AS card_status "
					+ "FROM v_card_all_rmm  WHERE ROWNUM < 50 ";
			int i = 0;
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				sql += " and SUBSTR(def_atm_account, 15, 8) like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				sql += " and card_number like ? ";
			} else {
				sql += "";
			}

			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				sql += " and regexp_like(embossed_ch_name, ?, 'i') ";
			} else {
				sql += "";
			}

			ps = c.prepareStatement(sql);
			if (cardfilter.getClient_code() != null && !cardfilter.getClient_code().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getClient_code() + "%");
			}
			if (cardfilter.getCard_number() != null && !cardfilter.getCard_number().isEmpty()) {
				i++;
				ps.setString(i, "%" + cardfilter.getCard_number() + "%");
			}
			if (cardfilter.getName() != null && !cardfilter.getName().isEmpty()) {
				i++;
				ps.setString(i, cardfilter.getName());
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("client_b"));
				card.setAccount(rs.getString("def_atm_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				list.add(card);
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

	public static List<RefData> getCardTypes(final String alias) {
		if (listCardTypes == null || listCardTypes.size() == 0) {
			listCardTypes = (List<RefData>) RefDataService
					.getRefData("select ID data, NAME label from SS_card_jump_type order by 1", alias);

		}
		return listCardTypes;
	}

	public static List<Card> getProtocolById(String un, String pw, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			System.out.println(un + "|" + pw + "|" + alias);
			String sql = "select * from CardToCard_Protocol order by ID desc";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setId(rs.getString("ID"));
				card.setBranch(rs.getString("BRANCH"));
//				card.setBank_time(rs.getDate("bank_date"));
//				card.setTime(rs.getDate("date_time"));
				card.setBank_time(rs.getString("bank_date"));
				card.setTime(rs.getString("date_time"));
				card.setFromcardtype(rs.getString("fromcardtype"));
				card.setFromcardnumber(rs.getString("fromcardnumber"));
				card.setFromcardbranch(rs.getString("fromcardbranch"));
				card.setFromcardacc(rs.getString("fromcardacc"));
				card.setFromcard_client_id(rs.getString("fromcard_client_id"));
				card.setFromcard_client_name(rs.getString("fromcard_client_name"));
				card.setTocardtype(rs.getString("tocardtype"));
				card.setTocardnumber(rs.getString("tocardnumber"));
				card.setTocardbranch(rs.getString("tocardbranch"));
				card.setTocardacc(rs.getString("tocardacc"));
				card.setTocard_client_id(rs.getString("tocard_client_id"));
				card.setTocard_client_name(rs.getString("tocard_client_name"));
				card.setState(rs.getString("state"));

//				System.out.println("-----------getProtocolByCardNumber------------");
//				System.out.println("ID: " + card.getId());
//				System.out.println("Branch: " + card.getBranch());
//				System.out.println("Bank Date: " + card.getBank_time());
//				System.out.println("Date Time: " + card.getTime());
//				System.out.println("from card Type: " + card.getFromcardtype());
//				System.out.println("from card Number: " + card.getFromcardnumber());
//				System.out.println("from card Branch: " + card.getFromcardbranch());
//				System.out.println("from card Account: " + card.getFromcardacc());
//				System.out.println("from card Client ID: " + card.getFromcard_client_id());
//				System.out.println("from card Client Name: " + card.getFromcard_client_name());
//				System.out.println("To Card Type: " + card.getTocardtype());
//				System.out.println("To Card Number: " + card.getTocardnumber());
//				System.out.println("To Card Branch: " + card.getTocardbranch());
//				System.out.println("To Card Account: " + card.getTocardacc());
//				System.out.println("To Card Client ID: " + card.getTocard_client_id());
//				System.out.println("To Card Client Name: " + card.getTocard_client_name());
//				System.out.println("State: " + card.getState());

				list.add(card);
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

	public static List<Card> getDetailsById(String x_id) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "select id, protocol_id, date_time, code, description from CardToCard_Protocol_dtl where protocol_id = ? order by id";
			ps = c.prepareStatement(sql);
			ps.setString(1, x_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setId(rs.getString("ID"));
				card.setCurrency(rs.getString("PROTOCOL_ID"));
//				card.setTime(rs.getDate("DATE_TIME"));
				card.setTime(rs.getString("DATE_TIME"));
				card.setClient_code(rs.getString("CODE"));
				transaction1_general_id = Long.parseLong(rs.getString("CODE"));
				card.setName(rs.getString("DESCRIPTION"));
				list.add(card);
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

	public static Ton TRANSACT_XUMO_VISA_01(Long protocol_id, Card card_send, Card card_receive, long amount, String un,
			String pw, String alias) {
		Connection c = null;
//		Ton ton = new Ton();
		Ton ton = new Ton();
		CallableStatement ccs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			System.out.println(un + "|" + pw + "|" + alias);
			ccs = c.prepareCall("{call proc_cardtrans.TRANSACT_XUMO_VISA_01(?,?)}");
			ccs.setLong(1, protocol_id);// "22618000599377668101"); //ccs.setString(2, "23504000700000444921");
										// //50606000000000394001-agro, 23504000700000444921-ipakyuli
			ccs.setLong(2, amount);
			ccs.execute();
			c.commit();
			System.out.println("- TRANSACT_XUMO_VISA_01 muvaffaqiyatli bajarildi -");
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, 12);
			ccs.execute();
			System.out.println("GENERAL jadvaldagi ID => " + ccs.getString(1)); // Document Id ga yoziladi, details
																				// tablitsaga ham //ID =
																				// param.getparam('ID');
			transaction1code = ccs.getString(1);
			ton.setCode(Long.parseLong(ccs.getString(1)));
			ton.setName(ccs.getString(1) + " <- shu raqamli tranzaksiya hosil bo'ldi (tranz_1)");
//			transaction1_general_id = Long.parseLong(ccs.getString(1));
		} catch (Exception e) {
			ton.setCode(0L);
			ton.setName(e.getMessage());
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				ConnectionPool.close(ccs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}
		return ton;
	}

	  public static Ton HUMO_PAYMENT_WRITEOFF(String branch, String card_number_from, String un, String pw,
	          String alias) {
	        Connection c = null;
	        Ton ton = new Ton();
	        CallableStatement ccs = null;
	        PreparedStatement ps1=null; 
	        
	        ResultSet rs1=null;
	        String br1="";
	        try {
	          c = ConnectionPool.getConnection(un, pw, alias);
	          System.out.println(un + "|" + pw + "|" + alias);
	          
	          ps1 = c.prepareStatement("select info.getbranch br from dual");
	          rs1 = ps1.executeQuery();
	          if (rs1.next())
	          br1=rs1.getString("br");
	          ccs = c.prepareCall("{ call HUMO_PAYMENT.WRITEOFF(?,?," + transaction1code + ") }");//1-branch, 2-card_number_from, 3-generalID(o'zi qo'yiladi)
	          ccs.setString(1, branch);
	          ccs.setString(2, card_number_from);
	          ccs.execute();
	          c.commit();
	          System.out.println("- HUMO_PAYMENT.WRITEOFF muvaffaqiyatli bajarildi -");
	          ton.setCode(1);
	          ton.setName("WriteOff success! branch="+br1+", trCode="+transaction1code+ ", cardNumber=" + card_number_from+ ", branch=" + branch);
	          
	        } catch (Exception e) {
	          ton.setCode(0);
	          ton.setName("WriteOff error! branch="+br1+", trCode="+transaction1code+ ", cardNumber=" + card_number_from+ ", branch=" + branch+". err="+ e.getMessage() );
	          e.printStackTrace();
	          ISLogger.getLogger().error("WriteOff error! branch="+br1+", trCode="+transaction1code + ", cardNumber=" + card_number_from + ", branch=" + branch + ". err="+ e.getMessage() );
	          ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	          try {
	            if (rs1 != null)
	              rs1.close();
	            ConnectionPool.close(ps1);
	            ConnectionPool.close(ccs);
	            ConnectionPool.close(c);
	          } catch (Exception e) {
	            ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	            e.printStackTrace();
	          }
	        }
	        return ton;
	      }


	
	public static Ton TRANSACT_XUMO_VISA_02(Long protocol_id, Card card_send, Card card_receive, long amount, String un,
			String pw, String alias) {
		Connection c = null;
//		Ton ton = new Ton();
		Ton ton = new Ton();
		CallableStatement ccs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			System.out.println(un + "|" + pw + "|" + alias);
			ccs = c.prepareCall("{ call proc_cardtrans.TRANSACT_XUMO_VISA_02(?,?) }");
			ccs.setLong(1, protocol_id);// ccs.setString(1, "23504000700000444921");
										// //50606000000000394001-agro,23504000700000444921-ipakyuli //ccs.setString(2,
										// card_receive.getAccount()); ccs.setString(2, "22618000599377668101");
			ccs.setLong(2, amount);
			ccs.execute();
			c.commit();
			System.out.println("- TRANSACT_XUMO_VISA_02 muvaffaqiyatli bajarildi -");
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, 12);
			ccs.execute();
			System.out.println("GENERAL jadvaldagi ID => " + ccs.getString(1));// Document Id ga yoziladi, details
																				// tablitsaga ham
//	        ID = param.getparam('ID');
			ton.setCode(Long.parseLong(ccs.getString(1)));
			ton.setName(ccs.getString(1) + " <- shu raqamli tranzaksiya hosil bo'ldi (tranz_2)");
//			transaction2_general_id = Long.parseLong(ccs.getString(1));
		} catch (Exception e) {
			ton.setCode(0);
			ton.setName(e.getMessage());
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				ConnectionPool.close(ccs);
				ConnectionPool.close(c);
			} catch (Exception e) {
				ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}
		return ton;
	}

	public static Ton InsertProtocolTable(Card card1, Card card2, String branch, String karta1, String karta2,
			String un, String pw, String alias) {
		long id = 0;
//		Ton ton = new Ton();
		Ton ton = new Ton();
		Date date = null;
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			ps = c.prepareStatement("select seq_ctc_p.nextval id from dual");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getLong("id");
				id_id = id;
				ton.setCode((int) id);
			} // ton.getName()==null ? "" : ton.getName().substring(0, 100)

			ps = c.prepareStatement("select info.getday as kun from dual");
			ResultSet rs1 = ps.executeQuery();
			if (rs1.next()) {
				date = rs1.getDate("kun");
				date1 = date;
			}

			String sql = "INSERT INTO CardToCard_Protocol (id, branch, "
					+ "bank_date, date_time, fromcardtype, fromcardnumber, "
					+ "fromcardbranch, fromcardacc, fromcard_client_id, "
					+ "fromcard_client_name, tocardtype, tocardnumber, " + "tocardbranch, tocardacc, tocard_client_id, "
					+ "tocard_client_name) VALUES (?, ?, ?, SYSDATE, ?, " + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 																										// ketiladi
			ps = c.prepareStatement(sql);
			ps.setLong(1, id);// id-done
			ps.setString(2, branch);// branch-done
			ps.setDate(3, date);// bank_date
			ps.setString(4, karta1);// fromcardtype-done
			ps.setString(5, card1.getCard_number());// fromcardnumber-done
			ps.setString(6, card1.getBranch());// fromcardbranch
			ps.setString(7, card1.getAccount());// fromcardacc-done
			ps.setString(8, card1.getClient_code());// fromcard_client_id-done
			ps.setString(9, card1.getName());// fromcard_client_name
			ps.setString(10, karta2);// tocardtype-done
			ps.setString(11, card2.getCard_number());// tocardnumber-done
			ps.setString(12, card2.getBranch());// tocardbranch-done
			ps.setString(13, card2.getAccount());// tocardacc-done
			ps.setString(14, card2.getClient_code());// tocard_client_id-done
			ps.setString(15, card2.getName());// tocard_client_name-done
//			ps.setString(16, card2.getAccount());//state
			ps.executeUpdate();
			c.commit();
			System.out.println("CardToCard_Protocol ga muvaffaqiyatli kiritildi.");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			ton.setCode(0);
			ton.setName(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ton;
	}

	public static void InsertDetailsTable(Ton ton, long id2, Card card, long i) {
		long details_id = 0;
		String substr_4000 = "";
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_CTC_PD.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				details_id = rs.getLong("id");
			}
			String sql = "INSERT INTO CardToCard_Protocol_dtl (ID, PROTOCOL_ID, DATE_TIME, CODE, DESCRIPTION) VALUES (?, ?, SYSDATE, ?, ?)";

			ps = c.prepareStatement(sql);
			ps.setLong(1, details_id);
			ps.setLong(2, id2);
//			if (ton.getCode() == -1) {
//				ton.setCode(0);
//			}
			ps.setLong(3, i);
			if(ton.getName().equals("") && ton.getName()!="" && ton.getName()!=null && ton.getName().length()>4000) {
				substr_4000 = ton.getName().substring(0, 4000);
			} else {
				substr_4000 = "CLIENT maydoni bo'sh";
			}
			
			if(ton.getName().length()>4000) {
				substr_4000 = ton.getName().substring(0, 4000);
			} else {
				substr_4000 = ton.getName();
			}
			ISLogger.getLogger().error("ton.getName() (cardtc service): " + ton.getName());
			System.out.println("ton.getName() (cardtc service): " + ton.getName());


			ps.setString(4, substr_4000); //			System.out.println("ps.setString(4, " + ton.getName() + ");");
			ps.executeUpdate();
			c.commit();
			System.out.println("CardToCard_Protocol_dtl ga muvaffaqiyatli kiritildi.");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null)
					ps.close();
				ConnectionPool.close(ps);
				ConnectionPool.close(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static long general_id_1() {
		if(transaction1_general_id!=null) {
			return transaction1_general_id-1;
		} else {
			return 0;
		}
		
	}
	
	public static List<Card> getTransactionFromGeneral(int x) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "select id, acc_cl, bank_cl, name_cl, acc_co, bank_co, name_co, summa, purpose from"
					+ " general where id in(select code from cardtocard_protocol_dtl where protocol_id = ?)";
			ps = c.prepareStatement(sql);
			ps.setInt(1, x);
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setId(rs.getString("ID"));
				card.setFromcardacc(rs.getString("acc_cl"));
				card.setFromcardbranch(rs.getString("bank_cl"));
				card.setFromcard_client_name(rs.getString("name_cl"));
				card.setTocardacc(rs.getString("acc_co"));
				card.setTocardbranch(rs.getString("bank_co"));
				card.setTocard_client_name(rs.getString("name_co"));
				card.setAmount(rs.getString("summa"));
				card.setPurpose(rs.getString("purpose"));
				list.add(card);
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

	public static int getCount(Card fc, String alias) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static String getVisaSumCardsPinfl(Card filter_datagrid1, String alias) {
		List<Card> list = new ArrayList<Card>();
		String pinfl = "";
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "select pinfl from client_p where branch=? and id=?";

			
			ps = c.prepareStatement(sql);
			ps.setString(1, filter_datagrid1.getBranch());
			ps.setString(2, filter_datagrid1.getClient_code());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setPinfl(rs.getString("pinfl"));
				pinfl = card.getPinfl();
				list.add(card);
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
//			System.out.println("return list dan oldin cardfilter: " + cardfilter);
		}
		return pinfl;
	}


}
