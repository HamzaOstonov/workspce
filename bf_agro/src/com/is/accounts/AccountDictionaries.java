package com.is.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.accounts.SAccount;
import com.is.accounts.DbUtils;
import com.is.accounts.CommonDictionaries;
import com.is.accounts.SqlScripts;
import com.is.utils.RefData;


public class AccountDictionaries {
	private static Logger logger = Logger.getLogger(AccountDictionaries.class);
	private String alias;
	 private List<SAccount> saccList;
	 public List<SAccount> getSaccList() {
		return saccList;
	}

	public void setSaccList(List<SAccount> saccList) {
		this.saccList = saccList;
	}
	private Map<Integer,String> actionsMap;
	 private Map<String,String> statesMap;
	 private List<RefData> states;
	 private List<RefData> currencies;
	 private List<RefData> accGroups;
	 private List<RefData> sgnList;
	 private List<RefData> balList;
	 private List<RefData> accBalList;
	
	private AccountDictionaries(String alias) {
		this.alias = alias;
		initLists();
	}
	
	public static AccountDictionaries instance(String alias) {
		return new AccountDictionaries(alias);
	}
	
	private void initLists() {
		Connection c = null; 
		try {
			c = ConnectionPool.getConnection(alias);
			saccList = getSAccountList(c);
			states = DbUtils.getRefData(c, SqlScripts.ACCOUNT_STATE.getSql());
			currencies = CommonDictionaries.getCurrencies(c,alias);
			accGroups = DbUtils.getRefData(c,SqlScripts.ACCOUNT_GROUP.getSql());
			accBalList = AccountUtil.getAccBal(saccList);
			actionsMap = DbUtils.getHRefDataInt(c, "select distinct id data, name label from action_account where manual=1");
			statesMap = Utility.listToMap(states);
			balList = makeBalList();
			sgnList = makeSgnList();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
	private List<SAccount> getSAccountList(Connection c) {
		PreparedStatement ps = null;
		List<SAccount> list = new ArrayList<SAccount>();
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from s_account where not code_b like '_0000' and not code_b like '___00' and destin = 3 and act <> 'Z' order by code_b");
			ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    list.add(new SAccount(
                                    rs.getString("nci_id"),
                                    rs.getString("destin"),
                                    rs.getString("code_b"),
                                    rs.getString("name_s"),
                                    rs.getString("type"),
                                    rs.getString("sect_code"),
                                    rs.getString("kod_acc"),
                                    rs.getString("rever_code"),
                                    rs.getString("kod_k"),
                                    rs.getString("kod_r"),
                                    rs.getDate("date_open"),
                                    rs.getDate("date_close"),
                                    rs.getString("act"),
                                    rs.getString("pr_nibbd")));
            }
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		finally {
			DbUtils.closeStmt(ps);
		}
		return list;
	}
	public List<RefData> makeSgnList(){
		List<RefData> list = new ArrayList<RefData>();
		list.add(new RefData("P","пассивный"));
		list.add(new RefData("A","активный"));
		list.add(new RefData("N","активно-пассивный"));
		return list;
	}
	public List<RefData> makeBalList(){
		List<RefData> list = new ArrayList<RefData>();
		list.add(new RefData("B","балансовый"));
		list.add(new RefData("O","внебалансовый"));
		return list;
	}
}
