package com.is.card_to_card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
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

		try {
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM TrAcc");
			while (rs.next()) {
				list.add(new TrAcc(rs.getInt("id"), rs.getString("branch"), rs.getInt("acc_template_id"),
						rs.getString("acc_mfo"), rs.getString("account"), rs.getString("acc_name")));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	/*
	 * public static String doAction(String un,String pw, TrAcc tracc,int
	 * actionid) { Res res =null; SimpleDateFormat bdf = new
	 * SimpleDateFormat("dd.MM.yyyy"); Connection c = null; CallableStatement cs
	 * = null; CallableStatement acs = null; CallableStatement ccs = null;
	 * 
	 * try { c = ConnectionPool.getConnection(un,pw); cs =
	 * c.prepareCall("{ call Param.SetParam(?,?) }"); acs =
	 * c.prepareCall("{ call kernel.doAction(?,?,?) }"); ccs =
	 * c.prepareCall("{ call Param.clearparam() }"); ccs.execute(); ccs =
	 * c.prepareCall("{? = call Param.getparam('ID') }");
	 * 
	 * cs.setString(1, "ID"); cs.setString(2,tracc.getId()); cs.execute();
	 * cs.setString(1, "BRANCH"); cs.setString(2,tracc.getBranch());
	 * cs.execute(); cs.setString(1, "ACC_TEMPLATE_ID");
	 * cs.setString(2,tracc.getAcc_template_id()); cs.execute(); cs.setString(1,
	 * "ACC_MFO"); cs.setString(2,tracc.getAcc_mfo()); cs.execute();
	 * cs.setString(1, "ACCOUNT"); cs.setString(2,tracc.getAccount());
	 * cs.execute(); cs.setString(1, "ACC_NAME");
	 * cs.setString(2,tracc.getAcc_name()); cs.execute();
	 * 
	 * acs.setInt(1, 2); acs.setInt(2, 2); acs.setInt(3,actionid);
	 * acs.execute(); c.commit(); ccs.execute(); res = new
	 * Res(0,ccs.getString(1));
	 * 
	 * 
	 * } catch (Exception e) { res = new Res(-1, e.getMessage()); } finally {
	 * ConnectionPool.close(c); } return res; }
	 * 
	 * public static String doAction(String un,String pw, String branch, String
	 * id,int actionid) { String res =""; SimpleDateFormat bdf = new
	 * SimpleDateFormat("dd.MM.yyyy"); Connection c = null; CallableStatement cs
	 * = null; CallableStatement acs = null; CallableStatement ccs = null;
	 * String cn; try { c = ConnectionPool.getConnection(un,pw); cs =
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
	 * acs.setInt(1, 2); acs.setInt(2, 2); acs.setInt(3,actionid);
	 * acs.execute(); c.commit(); } } catch (Exception e) { //
	 * com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); res
	 * = e.getMessage(); } finally { ConnectionPool.close(c); } return res; }
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

	public static int getCount(TrAccFilter filter, String alias) {

		Connection c = null;
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
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<TrAcc> getTrAccsFl(int pageIndex, int pageSize, TrAccFilter filter, String alias) {

		List<TrAcc> list = new ArrayList<TrAcc>();
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
				list.add(new TrAcc(rs.getInt("id"), rs.getString("branch"), rs.getInt("acc_template_id"),
						rs.getString("acc_mfo"), rs.getString("account"), rs.getString("acc_name")));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public TrAcc getTrAcc(int traccId, String alias) {

		TrAcc tracc = new TrAcc();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_TR_ACC WHERE tracc_id=?");
			ps.setInt(1, traccId);
			ResultSet rs = ps.executeQuery();
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
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return tracc;
	}

	public static TrAcc create(TrAcc tracc, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_BF_TR_ACC.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
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
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return tracc;
	}

	public static void update(TrAcc tracc, String alias) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("UPDATE BF_TR_ACC " + "SET account=? " + "WHERE id=?");

			// ps.setString(1,tracc.getBranch());
			// ps.setInt(2,tracc.getAcc_template_id());
			// ps.setString(3,tracc.getAcc_mfo());
			ps.setString(1, tracc.getAccount());
			// ps.setString(5,tracc.getAcc_name());
			ps.setInt(2, tracc.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void remove(TrAcc tracc, String alias) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("DELETE FROM BF_TR_ACC WHERE id=?");
			ps.setInt(1, tracc.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static List<Account> getAccount(TrAcc tracc, String fl, String alias, String branch) {

		if (fl.compareTo("") == 0)
			fl = "%";

		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		ResultSet rs = null;
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

			PreparedStatement ps = c.prepareStatement("select * FROM BF_TR_ACC_TEMPLATE WHERE id=?");
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
						rs.getBigDecimal("s_in"), rs.getBigDecimal("s_out"), rs.getBigDecimal("dt"),
						rs.getBigDecimal("ct"), rs.getBigDecimal("s_in_tmp"), rs.getBigDecimal("s_out_tmp"),
						rs.getBigDecimal("dt_tmp"), rs.getBigDecimal("ct_tmp"), rs.getDate("l_date"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"),
						rs.getInt("state")));
			}
			if (whr.endsWith("ACC")) {
				Account tacc = new Account();
				tacc.setId(whr);
				tacc.setName(nm);
				tacc.setBranch(tracc.getBranch());
				list.add(tacc);
			}

		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<Account> getAccount(String fl, String alias, String branch) {

		if (fl.compareTo("") == 0)
			fl = "%";

		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		ResultSet rs = null;
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
			ConnectionPool.close(c);
			c = ConnectionPool.getConnection(us);

			Statement s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM Account where id like '" + fl + "' and rownum < 50");
			// System.out.println("SELECT:"+"SELECT * FROM Account where id like
			// '"+whr+"' and id like '"+fl+"' and state=2 ");
			while (rs.next()) {
				list.add(new Account(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"),
						rs.getString("currency"), rs.getString("client"), rs.getString("id_order"),
						rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"),
						rs.getBigDecimal("s_in"), rs.getBigDecimal("s_out"), rs.getBigDecimal("dt"),
						rs.getBigDecimal("ct"), rs.getBigDecimal("s_in_tmp"), rs.getBigDecimal("s_out_tmp"),
						rs.getBigDecimal("dt_tmp"), rs.getBigDecimal("ct_tmp"), rs.getDate("l_date"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("acc_group_id"),
						rs.getInt("state")));
			}

		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<Card> getHumoCards(CardFilter filter, final String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps =null;
		
		
		try {
			c = ConnectionPool.getConnection(alias);

			//PreparedStatement ps1 = c.prepareStatement("select * from ss_dblink_branch t where t.branch = ?");
			//ps1.setString(1, branch);
			//ResultSet rs1 = ps1.executeQuery();
			//String us = null;
			//if (rs1.next()) {
			//	us = rs1.getString("user_name");
			//}
			//ConnectionPool.close(c);
			//c = ConnectionPool.getConnection(us);

			//PreparedStatement ps = c.prepareStatement("select * from .......todo...  and rownum < 50");

			String sql="select "+
					"c.branch, "+
					"c.real_card card_number, "+ 
					"ba.tranz_acct def_atm_account, "+ 
					"c.client_b client_id, "+
					"c.card_name embossed_ch_name, "+ 
					"c.expiry1 expiration_date, "+
					"c.status1 card_status, "+
					"c.account_no contract_id, "+ 
					"c.status1 hot_card_status "+
					"from humo_cards c, bf_empc_accounts ba "+ 
					"where c.account_no=ba.account_no "+
					"and c.branch=? "//+
					//"and substr(c.real_card,1,8) in ('98600301','98600303','98600308','98600309','98600324','98600325','98600330','98600366','40276700','40734200')";
					;
			
			if (filter.getClient_code()!=null && !filter.getClient_code().equals("")) 
				sql=sql+ " and c.client_b = ?";
			if (filter.getCard_number()!=null && !filter.getCard_number().equals("")) 
				sql=sql+ " and c.card like ?";
			if (filter.getCard_name()!=null && !filter.getCard_name().equals("")) 
				sql=sql+ " and c.card_name like ?";
			
			if ( (filter.getClient_code()==null || filter.getClient_code().equals("")) && 
					(filter.getCard_number()==null || filter.getCard_number().equals("")) &&
					(filter.getCard_name()==null || filter.getCard_name().equals(""))
					)
				sql=sql+ " and rownum<?";
			
			ps = c.prepareStatement(sql);
			ps.setString(1, filter.getBranch());
			int i=2;
			if (filter.getClient_code()!=null && !filter.getClient_code().equals("")) 
				ps.setString(i++, filter.getClient_code());
			if (filter.getCard_number()!=null && !filter.getCard_number().equals("")) 
				ps.setString(i++, filter.getCard_number());			
			if (filter.getCard_name()!=null && !filter.getCard_name().equals("")) 
				ps.setString(i++, filter.getCard_name());
			if ( (filter.getClient_code()==null || filter.getClient_code().equals("")) && 
					(filter.getCard_number()==null || filter.getCard_number().equals("")) &&
					(filter.getCard_name()==null || filter.getCard_name().equals(""))
					)
				ps.setInt(i++, 1000);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Card card= new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setAccount(rs.getString("def_atm_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				card.setContract(rs.getString("contract_id"));
				card.setClient_code(rs.getString("client_id"));
				card.setCurrency( rs.getString("def_atm_account")!=null ? rs.getString("def_atm_account").substring(5,8) : "" );
				list.add(card);
			}

		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
		
	}

	
	public static List<Card> getVisaSumCards(CardFilter filter, final String alias) {
		List<Card> list = new ArrayList<Card>();
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps =null;
		
		
		try {
			c = ConnectionPool.getConnection(alias);
		
			String sql="select "+
					"c.branch, "+
					"c.card_number, "+ 
					"c.def_atm_account, "+ 
					"substr(c.def_atm_account,15,8) client_id, "+
					"c.embossed_ch_name, "+ 
					"c.expiration_date, "+
					"c.card_status, "+
					"c.card_type contract_id, "+ 
					"c.card_status "+
					"from v_card_all_rmm c "+ 
					"where c.branch=? "//+
					//"and substr(c.real_card,1,8) in ('98600301','98600303','98600308','98600309','98600324','98600325','98600330','98600366','40276700','40734200')";
					;
			
			if (filter.getClient_code()!=null && !filter.getClient_code().equals("")) 
				sql=sql+ " and substr(c.def_atm_account,15,8) = ?";
			if (filter.getCard_number()!=null && !filter.getCard_number().equals("")) 
				sql=sql+ " and c.card_number like ?";
			if (filter.getCard_name()!=null && !filter.getCard_name().equals("")) 
				sql=sql+ " and c.embossed_ch_name like ?";
			if (filter.getCurrency()!=null && !filter.getCurrency().equals("")) 
				sql=sql+ " and substr(c.def_atm_account,11,3) = ?";
			
			
			if ( (filter.getClient_code()==null || filter.getClient_code().equals("")) && 
					(filter.getCard_number()==null || filter.getCard_number().equals("")) &&
					(filter.getCard_name()==null || filter.getCard_name().equals(""))
					)
				sql=sql+ " and rownum<?";
			
			ps = c.prepareStatement(sql);
			ps.setString(1, filter.getBranch());
			int i=2;
			if (filter.getClient_code()!=null && !filter.getClient_code().equals("")) 
				ps.setString(i++, filter.getClient_code());
			if (filter.getCard_number()!=null && !filter.getCard_number().equals("")) 
				ps.setString(i++, filter.getCard_number());			
			if (filter.getCard_name()!=null && !filter.getCard_name().equals("")) 
				ps.setString(i++, filter.getCard_name());
			if (filter.getCurrency()!=null && !filter.getCurrency().equals("")) 
				ps.setString(i++, filter.getCurrency());
			
			if ( (filter.getClient_code()==null || filter.getClient_code().equals("")) && 
					(filter.getCard_number()==null || filter.getCard_number().equals("")) &&
					(filter.getCard_name()==null || filter.getCard_name().equals("")) &&
					(filter.getCurrency()==null || filter.getCurrency().equals(""))
					
					)
				ps.setInt(i++, 1000);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Card card= new Card();
				card.setBranch(rs.getString("branch"));
				card.setCard_number(rs.getString("card_number"));
				card.setAccount(rs.getString("def_atm_account"));
				card.setName(rs.getString("embossed_ch_name"));
				card.setExpiry(rs.getString("expiration_date"));
				card.setStatus(rs.getString("card_status"));
				card.setContract(rs.getString("contract_id"));
				card.setClient_code(rs.getString("client_id"));
				card.setCurrency( rs.getString("def_atm_account")!=null ? rs.getString("def_atm_account").substring(10,13) : "" );
				list.add(card);
			}

		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
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


}
