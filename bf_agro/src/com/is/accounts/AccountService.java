package com.is.accounts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.clients.Client;
import com.is.clients.ClientService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class AccountService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM (select a.*, s.name state_desc from ACCOUNT a, (select * from state_account where deal_id = 1) s where s.id = a.state) ";
//	private List<SAccount> saccList;
	
	private Map<Integer,String> actionsMap;
	private Map<String,String> statesMap;
	private List<RefData> states;
	private List<RefData> currencies;
	private List<RefData> accGroups;
	private List<RefData> sgnList;
	private List<RefData> balList;
	private List<RefData> accBalList;
	private String alias;
	private String branch;
	private String un;
	private String pw;

	private AccountService(String branch, String alias, String un, String pw) {
	        this.branch = branch;
	        this.alias = alias;
	        this.un = un;
	        this.pw = pw;
	    }

	public static AccountService getInstance(String branch, String alias, String un, String pw) {
	        return new AccountService(branch, alias, un, pw);
	    }

	public static List<Account> getAccount(String alias)  {
		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM account");
			while (rs.next()) {
				list.add(new Account(
						rs.getString("branch"),
						rs.getString("id"),
						rs.getString("acc_bal"),
						rs.getString("currency"),
						rs.getString("client"),
						rs.getString("id_order"),
						rs.getString("name"),
						rs.getString("sgn"),
						rs.getString("bal"),
						rs.getLong("sign_registr"),
						rs.getLong("s_in"),
						rs.getLong("s_out"),
						rs.getLong("dt"),
						rs.getLong("ct"),
						rs.getLong("s_in_tmp"),
						rs.getLong("s_out_tmp"),
						rs.getLong("dt_tmp"),
						rs.getLong("ct_tmp"),
						rs.getDate("l_date"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getLong("acc_group_id"),
						rs.getLong("state"),
						rs.getLong("kod_err"),
						rs.getString("file_name"),
						rs.getString("reason_close")));
				System.out.println("reason_close:"+rs.getString("reason_close"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Res doAction(String un,String pw, Account account,int actionid, String alias) {
         Res res =null;
         SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
         Connection c = null;
         CallableStatement cs = null;
         CallableStatement acs = null;
         CallableStatement ccs = null;
         try {
        	 c = ConnectionPool.getConnection(un, pw, alias);
        	 cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	 acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	 ccs = c.prepareCall("{ call Param.clearparam() }");
        	 ccs.execute();
        	 ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			cs.setString(1, "branch");  cs.setString(2,account.getBranch()); cs.execute();
			cs.setString(1, "id");  cs.setString(2,account.getId()); cs.execute();
			cs.setString(1, "acc_bal");  cs.setString(2,account.getAcc_bal()); cs.execute();
			cs.setString(1, "currency");  cs.setString(2,account.getCurrency()); cs.execute();
			cs.setString(1, "client");  cs.setString(2,account.getClient()); cs.execute();
			cs.setString(1, "id_order");  cs.setString(2,account.getId_order()); cs.execute();
			cs.setString(1, "name");  cs.setString(2,account.getName()); cs.execute();
			cs.setString(1, "sgn");  cs.setString(2,account.getSgn()); cs.execute();
			cs.setString(1, "bal");  cs.setString(2,account.getBal()); cs.execute();
			cs.setString(1, "sign_registr");  cs.setLong(2,account.getSign_registr()); cs.execute();
			cs.setString(1, "s_in");  cs.setLong(2,account.getS_in()); cs.execute();
			cs.setString(1, "s_out");  cs.setLong(2,account.getS_out()); cs.execute();
			cs.setString(1, "dt");  cs.setLong(2,account.getDt()); cs.execute();
			cs.setString(1, "ct");  cs.setLong(2,account.getCt()); cs.execute();
			cs.setString(1, "s_in_tmp");  cs.setLong(2,account.getS_in_tmp()); cs.execute();
			cs.setString(1, "s_out_tmp");  cs.setLong(2,account.getS_out_tmp()); cs.execute();
			cs.setString(1, "dt_tmp");  cs.setLong(2,account.getDt_tmp()); cs.execute();
			cs.setString(1, "ct_tmp");  cs.setLong(2,account.getCt_tmp()); cs.execute();
			cs.setString(1, "l_date");  cs.setDate(2,new java.sql.Date(account.getL_date().getTime())); cs.execute();
			cs.setString(1, "date_open");  cs.setDate(2,new java.sql.Date(account.getDate_open().getTime())); cs.execute();
			cs.setString(1, "date_close");  cs.setDate(2,new java.sql.Date(account.getDate_close().getTime())); cs.execute();
			cs.setString(1, "acc_group_id");  cs.setLong(2,account.getAcc_group_id()); cs.execute();
			cs.setString(1, "state");  cs.setLong(2,account.getState()); cs.execute();
			cs.setString(1, "kod_err");  cs.setLong(2,account.getKod_err()); cs.execute();
			cs.setString(1, "file_name");  cs.setString(2,account.getFile_name()); cs.execute();

        	 acs.setInt(1, 2);
        	 acs.setInt(2, 2);
        	 acs.setInt(3,actionid);
        	 acs.execute();
        	 c.commit();
        	 ccs.execute();
        	 res = new Res(0,ccs.getString(1));
         } catch (Exception e) {
             res = new Res(-1, e.getMessage());
         } finally {
        	 ConnectionPool.close(c);
         }
         return res;
    }

	public static String doAction(String un,String pw, String branch, String id,int actionid) {
		String res ="";
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
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE branch=? and id=?");
        	ps.setString(1, branch);
        	ps.setString(2, id);
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		ccs.execute();
        		for (int i=1;  i<=rs.getMetaData().getColumnCount();i++){
        			cn = rs.getMetaData().getColumnName(i);
        			// System.out.println(cn+"  "+ rs.getMetaData().getColumnTypeName(i));
        			if( rs.getString(cn)!=null){
        				cs.setString(1, cn);
        				if (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
        					cs.setString(2,bdf.format(rs.getDate(cn)));
        				}else{
        					cs.setString(2,rs.getString(cn));
        				}
        				cs.execute();
        			}
        		}
        		acs.setInt(1, 2);
        		acs.setInt(2, 2);
        		acs.setInt(3,actionid);
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

	private static String getCond(List<FilterField> flfields){
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}

	private static List<FilterField> getFilterFields(AccountFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getBranch())){
			flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
		}
		if(!CheckNull.isEmpty(filter.getId())){
			flfields.add(new FilterField(getCond(flfields)+ "id = ?", filter.getId()));
		}
		if(!CheckNull.isEmpty(filter.getAcc_bal())){
			flfields.add(new FilterField(getCond(flfields)+ "acc_bal = ?", filter.getAcc_bal()));
		}
		if(!CheckNull.isEmpty(filter.getCurrency())){
			flfields.add(new FilterField(getCond(flfields)+ "currency = ?", filter.getCurrency()));
		}
		if(!CheckNull.isEmpty(filter.getClient())){
			flfields.add(new FilterField(getCond(flfields)+ "client = ?", filter.getClient()));
		}
		if(!CheckNull.isEmpty(filter.getId_order())){
			flfields.add(new FilterField(getCond(flfields)+ "id_order = ?", filter.getId_order()));
		}
		if(!CheckNull.isEmpty(filter.getName())){
			flfields.add(new FilterField(getCond(flfields)+ "name = ?", filter.getName()));
		}
		if(!CheckNull.isEmpty(filter.getSgn())){
			flfields.add(new FilterField(getCond(flfields)+ "sgn = ?", filter.getSgn()));
		}
		if(!CheckNull.isEmpty(filter.getBal())){
			flfields.add(new FilterField(getCond(flfields)+ "bal = ?", filter.getBal()));
		}
		if(!CheckNull.isEmpty(filter.getSign_registr())){
			flfields.add(new FilterField(getCond(flfields)+ "sign_registr = ?", filter.getSign_registr()));
		}
		if(!CheckNull.isEmpty(filter.getS_in())){
			flfields.add(new FilterField(getCond(flfields)+ "s_in = ?", filter.getS_in()));
		}
		if(!CheckNull.isEmpty(filter.getS_out())){
			flfields.add(new FilterField(getCond(flfields)+ "s_out = ?", filter.getS_out()));
		}
		if(!CheckNull.isEmpty(filter.getDt())){
			flfields.add(new FilterField(getCond(flfields)+ "dt = ?", filter.getDt()));
		}
		if(!CheckNull.isEmpty(filter.getCt())){
			flfields.add(new FilterField(getCond(flfields)+ "ct = ?", filter.getCt()));
		}
		if(!CheckNull.isEmpty(filter.getS_in_tmp())){
			flfields.add(new FilterField(getCond(flfields)+ "s_in_tmp = ?", filter.getS_in_tmp()));
		}
		if(!CheckNull.isEmpty(filter.getS_out_tmp())){
			flfields.add(new FilterField(getCond(flfields)+ "s_out_tmp = ?", filter.getS_out_tmp()));
		}
		if(!CheckNull.isEmpty(filter.getDt_tmp())){
			flfields.add(new FilterField(getCond(flfields)+ "dt_tmp = ?", filter.getDt_tmp()));
		}
		if(!CheckNull.isEmpty(filter.getCt_tmp())){
			flfields.add(new FilterField(getCond(flfields)+ "ct_tmp = ?", filter.getCt_tmp()));
		}
		if(!CheckNull.isEmpty(filter.getL_date())){
			flfields.add(new FilterField(getCond(flfields)+ "l_date = ?", filter.getL_date()));
		}
		if(!CheckNull.isEmpty(filter.getDate_open())){
			flfields.add(new FilterField(getCond(flfields)+ "date_open = ?", filter.getDate_open()));
		}
		if(!CheckNull.isEmpty(filter.getDate_close())){
			flfields.add(new FilterField(getCond(flfields)+ "date_close = ?", filter.getDate_close()));
		}
		if(!CheckNull.isEmpty(filter.getAcc_group_id())){
			flfields.add(new FilterField(getCond(flfields)+ "acc_group_id = ?", filter.getAcc_group_id()));
		}
		if(!CheckNull.isEmpty(filter.getState())){
			flfields.add(new FilterField(getCond(flfields)+ "state = ?", filter.getState()));
		}
		if(!CheckNull.isEmpty(filter.getKod_err())){
			flfields.add(new FilterField(getCond(flfields)+ "kod_err = ?", filter.getKod_err()));
		}
		if(!CheckNull.isEmpty(filter.getFile_name())){
			flfields.add(new FilterField(getCond(flfields)+ "file_name = ?", filter.getFile_name()));
		}
		
		if(!CheckNull.isEmpty(filter.getClose_sgn())){
			flfields.add(new FilterField(getCond(flfields)+ "reason_close = ?", filter.getClose_sgn()));	
			
		}
		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(AccountFilter filter, String alias)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM (select * from account a) " );
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			for(int k=0;k<flFields.size();k++){
				ps.setObject(k+1, flFields.get(k).getColobject());
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

	public static List<Account> getAccountsFl(int pageIndex, int pageSize, AccountFilter filter, String alias)  {
		List<Account> list = new ArrayList<Account>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append("SELECT * FROM (select a.*, s.name state_desc from ACCOUNT a, (select * from state_account where deal_id = 1) s where s.id = a.state) ");// 
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			for(params=0;params<flFields.size();params++){
				ps.setObject(params+1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++,v_upperbound);
			ps.setInt(params++,v_lowerbound);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(
						rs.getString("branch"),
						rs.getString("id"),
						rs.getString("acc_bal"),
						rs.getString("currency"),
						rs.getString("client"),
						rs.getString("id_order"),
						rs.getString("name"),
						rs.getString("sgn"),
						rs.getString("bal"),
						rs.getLong("sign_registr"),
						rs.getLong("s_in"),
						rs.getLong("s_out"),
						rs.getLong("dt"),
						rs.getLong("ct"),
						rs.getLong("s_in_tmp"),
						rs.getLong("s_out_tmp"),
						rs.getLong("dt_tmp"),
						rs.getLong("ct_tmp"),
						rs.getDate("l_date"),
						rs.getDate("date_open"),
						rs.getDate("date_close"),
						rs.getLong("acc_group_id"),
						rs.getLong("state"),
						rs.getLong("kod_err"),
						rs.getString("file_name"),
						rs.getString("state_desc"),
				        rs.getString("reason_close")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Account getAccount(int accountId) {
		Account account = new Account();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE id=?");
			ps.setLong(1, accountId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setBranch(rs.getString("branch"));
				account.setId(rs.getString("id"));
				account.setAcc_bal(rs.getString("acc_bal"));
				account.setCurrency(rs.getString("currency"));
				account.setClient(rs.getString("client"));
				account.setId_order(rs.getString("id_order"));
				account.setName(rs.getString("name"));
				account.setSgn(rs.getString("sgn"));
				account.setBal(rs.getString("bal"));
				account.setSign_registr(rs.getLong("sign_registr"));
				account.setS_in(rs.getLong("s_in"));
				account.setS_out(rs.getLong("s_out"));
				account.setDt(rs.getLong("dt"));
				account.setCt(rs.getLong("ct"));
				account.setS_in_tmp(rs.getLong("s_in_tmp"));
				account.setS_out_tmp(rs.getLong("s_out_tmp"));
				account.setDt_tmp(rs.getLong("dt_tmp"));
				account.setCt_tmp(rs.getLong("ct_tmp"));
				account.setL_date(rs.getDate("l_date"));
				account.setDate_open(rs.getDate("date_open"));
				account.setDate_close(rs.getDate("date_close"));
				account.setAcc_group_id(rs.getLong("acc_group_id"));
				account.setState(rs.getLong("state"));
				account.setKod_err(rs.getLong("kod_err"));
				account.setFile_name(rs.getString("file_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return account;
	}

	public static Account create(Account account)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_account.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account.setId(rs.getString("id"));
			}
			ps = c.prepareStatement("INSERT INTO account (branch, id, acc_bal, currency, client, id_order, name, sgn, bal, sign_registr, s_in, s_out, dt, ct, s_in_tmp, s_out_tmp, dt_tmp, ct_tmp, l_date, date_open, date_close, acc_group_id, state, kod_err, file_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, account.getBranch());
				ps.setString(2, account.getId());
				ps.setString(3, account.getAcc_bal());
				ps.setString(4, account.getCurrency());
				ps.setString(5, account.getClient());
				ps.setString(6, account.getId_order());
				ps.setString(7, account.getName());
				ps.setString(8, account.getSgn());
				ps.setString(9, account.getBal());
				ps.setLong(10, account.getSign_registr());
				ps.setLong(11, account.getS_in());
				ps.setLong(12, account.getS_out());
				ps.setLong(13, account.getDt());
				ps.setLong(14, account.getCt());
				ps.setLong(15, account.getS_in_tmp());
				ps.setLong(16, account.getS_out_tmp());
				ps.setLong(17, account.getDt_tmp());
				ps.setLong(18, account.getCt_tmp());
				ps.setDate(19, new java.sql.Date(account.getL_date().getTime()));
				ps.setDate(20, new java.sql.Date(account.getDate_open().getTime()));
				ps.setDate(21, new java.sql.Date(account.getDate_close().getTime()));
				ps.setLong(22, account.getAcc_group_id());
				ps.setLong(23, account.getState());
				ps.setLong(24, account.getKod_err());
				ps.setString(25, account.getFile_name());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return account;
	}

	public static void update(Account account)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE account SET branch = ?, id = ?, acc_bal = ?, currency = ?, client = ?, id_order = ?, name = ?, sgn = ?, bal = ?, sign_registr = ?, s_in = ?, s_out = ?, dt = ?, ct = ?, s_in_tmp = ?, s_out_tmp = ?, dt_tmp = ?, ct_tmp = ?, l_date = ?, date_open = ?, date_close = ?, acc_group_id = ?, state = ?, kod_err = ?, file_name = ?  WHERE id=?");
				ps.setString(1, account.getBranch());
				ps.setString(2, account.getId());
				ps.setString(3, account.getAcc_bal());
				ps.setString(4, account.getCurrency());
				ps.setString(5, account.getClient());
				ps.setString(6, account.getId_order());
				ps.setString(7, account.getName());
				ps.setString(8, account.getSgn());
				ps.setString(9, account.getBal());
				ps.setLong(10, account.getSign_registr());
				ps.setLong(11, account.getS_in());
				ps.setLong(12, account.getS_out());
				ps.setLong(13, account.getDt());
				ps.setLong(14, account.getCt());
				ps.setLong(15, account.getS_in_tmp());
				ps.setLong(16, account.getS_out_tmp());
				ps.setLong(17, account.getDt_tmp());
				ps.setLong(18, account.getCt_tmp());
				ps.setDate(19, new java.sql.Date(account.getL_date().getTime()));
				ps.setDate(20, new java.sql.Date(account.getDate_open().getTime()));
				ps.setDate(21, new java.sql.Date(account.getDate_close().getTime()));
				ps.setLong(22, account.getAcc_group_id());
				ps.setLong(23, account.getState());
				ps.setLong(24, account.getKod_err());
				ps.setString(25, account.getFile_name());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(Account account)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM account WHERE id=?");
			ps.setString(1, account.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	public static List<RefData> getPageSizes()  {
		List<RefData> res = new ArrayList<RefData>();
		for (int i = 5; i < 51; i = (i + 5)) {
			res.add(new RefData(""+i, ""+i));
		} 
		return res;
	}
	
	public static List<RefData> getRefData(String sql)  {
        List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        try {
        	c = ConnectionPool.getConnection();
        	Statement s = c.createStatement();
        	ResultSet rs = s.executeQuery(sql);
        	while (rs.next()) {
                list.add(new RefData(
                                rs.getString("data"),
                                rs.getString("label")));
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	ConnectionPool.close(c);
        }	
        return list;
	}
	
	public static List<RefData> getOperdates(String alias) {
		 return RefDataService.getRefData(	"select 'PREV_DATE' data, to_char(b.PREV_DATE, 'dd.mm.yyyy') label from branch b "+
				 				"union all "+
				 				"select 'CURR_DATE' data, to_char(b.CURR_DATE, 'dd.mm.yyyy') label from branch b ", alias);
	}
	
	public static List<RefData> getAstates(String alias) {
	    return RefDataService.getRefData("select distinct id data, name label from state_account order by id", alias);
	}
	
	public static List<RefData> getS_Mfo(String alias) {
	    return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf where smf.bank_type = (select smf1.bank_type from s_mfo smf1 where smf1.bank_id =  (select VALUE from bf_sets bs where bs.id ='HO'))  order by smf.bank_id", alias);
	}
	
	public static List<RefData> getS_MfoAll(String alias) {
	    return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf order by smf.bank_id", alias);
	}
	
	public static List<RefData> getS_currencies(String alias) {
	    return RefDataService.getRefData("select kod data,kod || ' - ' ||decode((select value from ss_const where id=1002 and rownum=1), 'Y',kod||'-', null)||namev label from s_val where allow <> 0 and act <> 'Z' order by kod", alias);
	}
	
	public static List<RefData> getS_accounts(String alias) {
		 return RefDataService.getRefData("select code_b data, code_b||' - '||name_s label from s_account where not code_b like '_0000' and not code_b like '___00' and destin = 3 and act <> 'Z' order by code_b", alias);
			}
	
	public static Date addDays(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, count);
	    return cal.getTime();
	}
    
	public static Date addMonths(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MONTH, count);
	    return cal.getTime();
	}
	
	public Res doAction(Account account, int actionid) {
        Res res = null;
     //   SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement dcs = null;
        CallableStatement csi = null;
        try {
            c = ConnectionPool.getConnection(un, pw, alias);
            csi = c.prepareCall("{ call info.init() }");
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{ call Param.clearparam() }");
            ccs.execute();
            dcs = c.prepareCall("{? = call Param.getparam('ID') }");
            dcs.registerOutParameter(1, java.sql.Types.VARCHAR);
            System.out.println("comes to here");
            cs.setString(1, "BRANCH");
            cs.setString(2, account.getBranch());
            System.out.println("branch :"+account.getBranch());
            cs.execute();
            if (!CheckNull.isEmpty(account.getId())) {
                cs.setString(1, "ID");
                cs.setString(2, account.getId());
                System.out.println("account_id :"+account.getId());
                cs.execute();
            }
            cs.setString(1, "ACC_BAL");
            cs.setString(2, account.getAcc_bal());
            System.out.println("account_bal :"+account.getAcc_bal());
            cs.execute();
            cs.setString(1, "CURRENCY");
            cs.setString(2, account.getCurrency());
            System.out.println("account_cur :"+account.getCurrency());
            cs.execute();
            cs.setString(1, "CLIENT");
            cs.setString(2, account.getClient());
            System.out.println("account_client :"+account.getClient());
            cs.execute();
            cs.setString(1, "ID_ORDER");
            cs.setString(2, account.getId_order());
            System.out.println("account_id_order :"+account.getId_order());
            cs.execute();
            cs.setString(1, "NAME");
            cs.setString(2, account.getName() != null ? account.getName() : "NEW ACCOUNT");
            System.out.println("account_name :"+account.getName());
            cs.execute();
            cs.setString(1, "SGN");
            cs.setString(2, account.getSgn() != null ? account.getSgn() : "A");
            System.out.println("account_sgn :"+account.getSgn());
            cs.execute();
            cs.setString(1, "BAL");
            cs.setString(2, account.getBal() != null ? account.getBal() : "B");
            System.out.println("account_bal :"+account.getBal());
            cs.execute();
            cs.setString(1, "SIGN_REGISTR");
            cs.setString(2, (""+account.getSign_registr()));
            System.out.println("account_sign_Reg :"+account.getSign_registr());
            cs.execute();
            cs.setString(1, "ACC_GROUP_ID");
            cs.setString(2, account.getAcc_group_id()+"");
            System.out.println("account_group_id :"+account.getAcc_group_id());
            cs.execute();

            System.out.println("sign_reg :"+Integer.valueOf(account.getSign_registr()+""));
            
            acs.setInt(1, 2);
            acs.setInt(2, Integer.valueOf(account.getSign_registr()+""));//2
            acs.setInt(3, actionid);
            //logger.error("Account error " + String.format("groupId = %d, dealType = %d, action = %d\n",
             //       2, account.getSign_registr(), actionid));
            csi.execute();
            acs.execute();
            dcs.execute();


            //boolean isConfirmed = actionid == 2
            //        && account.getSign_registr() == 2
            //        &&
            //        !ClientUtil.isClientConfirmed(c, account.getClient());
            //logger.error("Account Action " + actionid + account);
            //logger.error("\n isConfirmed " + isConfirmed);
            /*if(isConfirmed) {
                try {
                    ClientJ byStringId = ClientDao.
                            getInstance(alias).
                            getItemByStringId(account.getBranch(),
                                    account.getClient());
                    logger.error("\n byString iD ");
                    SapHandler.makeSapRequest(
                            byStringId, 2);
                }
                catch (Exception e){
                    logger.error(CheckNull.getPstr(e));
                    throw e;
                }
			}*/
            c.commit();
            res = new Res(0, dcs.getString(1));
            if (actionid == 6) {
                account.setId(null);
            }

        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
            DbUtils.rollback(c);
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(acs);
            DbUtils.closeStmt(ccs);
            DbUtils.closeStmt(dcs);
            DbUtils.closeStmt(csi);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static String getMark(String alias,String par,String mark_query){
		String str = null;
		String sgn_sql = null;
		if(mark_query.equalsIgnoreCase("S")){
			sgn_sql = "select kernel.setsgn(?) sgn from dual";
			}
		else if(mark_query.equalsIgnoreCase("T")){
		    sgn_sql = "select kernel.getbal(?) sgn from dual";
		}
		else if(mark_query.equalsIgnoreCase("G")){
			sgn_sql = "select kernel.getaccreg(?) sgn from dual";
		}
		Connection c = null; 
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sgn_sql);
		      ps.setString(1, par);
		      ResultSet rs = ps.executeQuery();
		    if(rs.next()){
		      str = rs.getString("SGN");
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		System.out.println("SGN:"+str);
		return str;		
	}
	
	public static String getNameClient(String branch,String client_id,String alias){
		String name_client = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
		    ps = c.prepareStatement("select p.name from client_p p where p.branch = ? and p.id = ? and rownum = 1");
		    ps.setString(1, branch);
		    ps.setString(2, client_id);
		    rs = ps.executeQuery();
		    
		    if(rs.next()){
		    	name_client = rs.getString("NAME");
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		System.out.println("name_client:"+name_client);
		return name_client;		
		
	}
	
    
}
