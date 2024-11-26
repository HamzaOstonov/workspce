package com.is.card_to_card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Exception;
import java.sql.Statement;
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

public class CardtcService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " order by id desc) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM BF_TR_ACC ";

	private static List<RefData> listCardTypes;

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
	 * { Res res =null; SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
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
	 * c.commit(); ccs.execute(); res = new Res(0,ccs.getString(1));
	 * 
	 * 
	 * } catch (Exception e) { res = new Res(-1, e.getMessage()); } finally {
	 * ConnectionPool.close(c); } return res; }
	 * 
	 * public static String doAction(String un,String pw, String branch, String
	 * id,int actionid) { String res =""; SimpleDateFormat bdf = new
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
	 * ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); res =
	 * e.getMessage(); } finally { ConnectionPool.close(c); } return res; }
	 * 
	 */
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(TrAccFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_template_id())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_template_id=?", filter.getAcc_template_id()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_mfo())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_mfo=?", filter.getAcc_mfo()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "account like(?)", filter.getAccount()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_name())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_name=?", filter.getAcc_name()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}
	
	private static List<FilterField> getFieldFilters(CardFilter filter) {
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

	public static int getCount(TrAccFilter filter, String alias) {

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
	
	public static int getCount1(CardFilter filter, String alias) {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int n = 0;
		List<FilterField> flFields = getFieldFilters(filter);
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
	
	public static List<Card> getTrAccsFl(int pageIndex, int pageSize, TrAccFilter filter, String alias) {

		List<Card> list = new ArrayList<Card>();
		/*Connection c = null;
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
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TrAcc(
							rs.getInt("id"), 
							rs.getString("branch"), 
							rs.getInt("acc_template_id"),
							rs.getString("acc_mfo"), 
							rs.getString("account"), 
							rs.getString("acc_name")));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}*/
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
		
		return list;

	}

	public static List<Account> getAccount(String fl, String alias, String branch) {

		if (fl.compareTo("") == 0)
			fl = "%";

		List<Account> list = new ArrayList<Account>();
		
		return list;

	}
	
//	public static List<CardFilter> getClientCards(CardFilter filter, String alias) {
//		List<CardFilter> list = new ArrayList<CardFilter>();
//		Connection c = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//
//		try {
//			c = ConnectionPool.getConnection(alias);
//			ps = c.prepareStatement("SELECT c.branch, c.client_b,  b.card_acct AS schet, c.card_name AS embossed_ch_name, "
//					+ "TO_CHAR(b.ab_expirity, 'DD-MM-YYYY') AS exp_date, c.status1 AS status, TO_CHAR(c.account_no) AS contract"
//					+ "FROM humo_cards c JOIN bf_empc_accounts b ON c.client = b.client"
//					+ "WHERE c.client_b = ? AND ROWNUM < 50 UNION ALL"
//					+ "SELECT branch,  SUBSTR(def_atm_account, 15, 8) as client_b,  embossed_ch_name, expiration_date, "
//					+ "card_status, card_type AS contract_id FROM v_card_all_rmm WHERE SUBSTR(card_number, "
//					+ "1, 6) IN ('414565', '402306') AND client_b = ?"
//					+ "AND ROWNUM < 50 UNION ALL SELECT branch, card_number, SUBSTR(def_atm_account, -20) AS "
//					+ "def_atm_account, embossed_ch_name, expiration_date, card_status, contract_id"
//					+ "FROM card WHERE client_b = ? AND ROWNUM < 50");
//			ps.setString(1,filter.getClient_code());
//            ps.setString(2,filter.getClient_code());
//            ps.setString(3,filter.getClient_code());
//		} catch (Exception e) {
//			e.printStackTrace(); 
//			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (ps != null)
//					ps.close();
//				ConnectionPool.close(ps);
//				ConnectionPool.close(rs);
//				ConnectionPool.close(c);
//			} catch (Exception e) {
//				e.printStackTrace(); 
//			}
//		}
//		return list;
//	}
	
	public static List<Card> getHumoCards(Card filter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT c.branch,"
					+ "c.real_card as real_card,"
					+ "c.client_b as client_b,"
					+ "b.card_acct as schet,"
					+ "c.card_name as name,"
					+ "b.ab_expirity as exp_date,"
					+ "c.status1 as status "
					+ "FROM humo_cards c, bf_empc_accounts b "
					+ "WHERE c.client = b.client "
					+ "AND regexp_like(c.card_name, ?, 'i')"
					+ "AND rownum < 50";
			ps = c.prepareStatement(sql);
			ps.setString(1, filter.getName());
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

	public static List<Card> getUzcardCards(Card filter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "select branch, card_number, substr(def_atm_account,15, 7) as "
					+ "def_atm_account, def_pos_account, embossed_ch_name, expiration_date,"
					+ "card_status from card where regexp_like(embossed_ch_name, ?, 'i')" 
					+ "and rownum<50";
			ps = c.prepareStatement(sql);
			ps.setString(1, filter.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("def_atm_account"));
				card.setAccount(rs.getString("def_pos_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				list.add(card);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {			try {
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
		}		return list;
	}

	public static List<Card> getVisaSumCards(Card filter, final String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);

			String sql = "select branch, card_number, substr(def_atm_account,15, 7) as schet, def_atm_account, "
					+ "embossed_ch_name, expiration_date, TO_CHAR(card_status) as card_status from "
					+ "v_card_all_rmm where SUBSTR(card_number,1,6) in ('414565','402306') and "
					+ "regexp_like(embossed_ch_name, ?, 'i') and rownum <50";
			ps = c.prepareStatement(sql);
			ps.setString(1, filter.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("schet"));
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

	public static List<Card> getHumoCardsOld(CardFilter cardfilter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "SELECT c.branch, c.real_card, c.client_b, b.card_acct AS schet,"
					+ " c.card_name AS name, b.ab_expirity AS exp_date,"
					+ " c.status1 AS status FROM humo_cards c, bf_empc_accounts b"
					+ " WHERE c.client = b.client and c.account_no = b.account_no"
					+ " and regexp_like(c.card_name, ?, 'i') and rownum < 50";
			ps = c.prepareStatement(sql);

			ps.setString(1, cardfilter.getName());
//			ps.setString(2, cardfilter.getClient_code());
//			ps.setString(3, cardfilter.getName());

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
			String sql = "select branch, card_number, SUBSTR(def_atm_account, 15, 7) as def_atm_account, def_pos_account, "
					+ "embossed_ch_name, expiration_date, card_status from card where rownum<50 and "
					//+ "(card_number=? or "
					+ "regexp_like(embossed_ch_name, ?, 'i')";
			ps = c.prepareStatement(sql);
//			ps.setString(1, cardfilter.getCard_number());
			ps.setString(1, cardfilter.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("def_atm_account"));
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

			String sql = "select branch, card_number, SUBSTR(def_atm_account, 15, 7) as schet, def_atm_account, "
					+ "embossed_ch_name, expiration_date, TO_CHAR(card_status) as card_status from "
					+ "v_card_all_rmm where SUBSTR(card_number,1,6) in ('414565','402306') and rownum<50 "
					+ "and (card_number=? or regexp_like(embossed_ch_name, ?, 'i'))";
			ps = c.prepareStatement(sql);
			ps.setString(1, cardfilter.getClient_code());
			ps.setString(2, cardfilter.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setClient_code(rs.getString("schet"));
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
	
	public static List<Card> getCards(int itemStartNumber, int pageSize, CardFilter filter, String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int v_lowerbound = itemStartNumber + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFieldFilters(filter);

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
//			while (rs.next()) {
//				list.add(new Card(
//							rs.getInt("id"), 
//							rs.getString("branch"), 
//							rs.getInt("acc_template_id"),
//							rs.getString("acc_mfo"), 
//							rs.getString("account"), 
//							rs.getString("acc_name"),
//							rs.getString("acc_name")));
//			}
		} catch (Exception e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(ps);
			ConnectionPool.close(rs);
			ConnectionPool.close(c);
		}
		return list;
	}
	
//	public static void create(Card card1, Card card2) {
//		Connection c = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//
//		try {
//			c = ConnectionPool.getConnection();
//			ps = c.prepareStatement("INSERT INTO temp_card_to_card VALUES (0, ?, ?, 5000)");
//			ps.setString(1, card1.getCard_number());
//			ps.setString(2, card2.getCard_number());
//			ps.executeUpdate();
//			c.commit();
//		} catch (SQLException e) {
//			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
//			e.printStackTrace();
//
//		} finally {
//			ConnectionPool.close(ps);
//			ConnectionPool.close(rs);
//			ConnectionPool.close(c);
//		}
//	}
	
	public static List<Card> getProtocolByCardNumber() {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "select * from tempCardToCardProtocol";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setClient_code(rs.getString("ID"));
				card.setName(rs.getString("FULL_NAME"));;
				card.setCard_number(rs.getString("FROM_CARD"));
				card.setContract(rs.getString("TO_CARD"));
				card.setCurrency(rs.getString("AMOUNT"));
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
	
	public static List<Card> getDetailsByName(Card card1) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = "select time, big_3, yes_or_no, description "
					+ "from api_details_table where name = ?";
			ps = c.prepareStatement(sql);
			ps.setString(1, card1.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				Card card = new Card();
				card.setClient_code(rs.getString("TIME"));
				card.setName(rs.getString("BIG_3"));
				card.setCard_number(rs.getString("YES_OR_NO"));
				card.setStatus(rs.getString("DESCRIPTION"));
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
	
	public static void InsertToProtocolTable(Card card1, Card card2) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    Random random = new Random();
	    int min = 5000; 
	    int max = 100000; 
	    int randomAmount = (random.nextInt((max - min) / 1000) * 1000) + min;
	    try {
	        c = ConnectionPool.getConnection();
	        String sql = "INSERT INTO tempCardToCardProtocol (ID, FULL_NAME, FROM_CARD, TO_CARD, AMOUNT) VALUES (0, ?, ?, ?, ?)";
	        ps = c.prepareStatement(sql);
	        ps.setString(1, card1.getName());
	        ps.setString(2, card1.getCard_number());
	        ps.setString(3, card2.getCard_number());
	        ps.setInt(4, randomAmount); 
	        ps.executeUpdate();
	        c.commit();
	        System.out.println("Record successfully inserted into tempCardToCardProtocol.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            ConnectionPool.close(ps);
	            ConnectionPool.close(c);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void InsertToDetailsTable(Card card) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = ConnectionPool.getConnection();
	        Random random = new Random();
	        String result = random.nextBoolean() ? "yes" : "no";
	        String big_1 = "BALANCE";
	        String big_2 = "PROVODKA";
	        String big_3 = "VISA SUM API";
	        String description = random.nextBoolean() ? "No Error" : "Error";
	        if(result == "yes") {
	        	description = "No Error";
	        } else {
	        	description = "Error";
	        } 
	        String sql_1 = "INSERT INTO api_details_table VALUES (0, SYSTIMESTAMP, ?, ?, ?, ?)";
		    ps = c.prepareStatement(sql_1);
		    ps.setString(1, big_1);
		    ps.setString(2, result);
		    ps.setString(3, description);
		    ps.setString(4, card.getName());
		    ps.executeUpdate();

	        String sql_2 = "INSERT INTO api_details_table VALUES (0, SYSTIMESTAMP, ?, ?, ?, ?)";
		    ps = c.prepareStatement(sql_2);
		    ps.setString(1, big_2);
		    ps.setString(2, result);
		    ps.setString(3, description);
		    ps.setString(4, card.getName());
		    ps.executeUpdate();
	        
	        String sql_3 = "INSERT INTO api_details_table VALUES (0, SYSTIMESTAMP, ?, ?, ?, ?)";
		    ps = c.prepareStatement(sql_3);
		    ps.setString(1, big_3);
		    ps.setString(2, result);
		    ps.setString(3, description);
		    ps.setString(4, card.getName());
		    ps.executeUpdate();

	        
	        c.commit();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        ISLogger.getLogger().error("Error inserting into database: " + e.getMessage());
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

}
