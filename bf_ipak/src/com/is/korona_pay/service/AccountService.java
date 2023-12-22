/**
 * 
 */
/**
 * @author Rustam Kremcheev;
 *
 */
package com.is.korona_pay.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.korona_pay.model.Account;
import com.is.korona_pay.TransactionService;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class AccountService {
	public static Account getAccount(String branch, String client, String alias){
    	Connection c = null;
    	PreparedStatement prep = null;
    	ResultSet rs;
    	Account acc = new Account();;
    	try {
    		c = ConnectionPool.getConnection(alias);
			prep = c.prepareStatement("Select id,name,s_out_tmp  from account t where t.state = '2' and t.acc_bal = '20206' and t.branch = ? \r\n" + 
					"         and t.client = ?\r\n" + 
					"         and t.id not like '20206%842'\r\n" + 
					"         and (t.id not in (select r.account_code\r\n" + 
					"         from sd_book_accounts r))");
			prep.setString(1, branch);
			prep.setString(2, client);
			rs = prep.executeQuery();
			 if (rs.next()){
				acc.setAcc_dep(rs.getString("id"));
				acc.setName(rs.getString("name"));
				acc.setS_out_tmp(rs.getInt("s_out_tmp"));
			 } else {acc.setAcc_dep("");} 
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getMessage());
			System.out.println("getAccount >>> " + e.getMessage());
			
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return acc;
    }
	
	public static Account getAccountFromState(Connection c, String branch, String client, String alias){
    	PreparedStatement prep = null;
    	ResultSet rs;
    	Account acc = new Account();;
    	try {
			prep = c.prepareStatement("Select id,name,s_out_tmp  from account t where t.state = '2' and t.acc_bal = '20206' and t.branch = ? \r\n" + 
					"         and t.client = ?\r\n" + 
					"         and t.id not like '20206%842'\r\n" + 
					"         and (t.id not in (select r.account_code\r\n" + 
					"         from sd_book_accounts r))");
			prep.setString(1, branch);
			prep.setString(2, client);
			rs = prep.executeQuery();
			 if (rs.next()){
				acc.setAcc_dep(rs.getString("id"));
				acc.setName(rs.getString("name"));
				acc.setS_out_tmp(rs.getInt("s_out_tmp"));
			 } else {acc.setAcc_dep("");} 
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getMessage());
			System.out.println("getAccount >>> " + e.getMessage());
			
			e.printStackTrace();
		}
		finally{
			//ConnectionPool.close(c);
		}
		return acc;
    }
	
    public static String getAccBal(String veoper, String eqval, String branch, String alias){
    	Connection c = null;
    	PreparedStatement getAcc = null;
    	ResultSet rs;
    	String dep="";
    	try {
    		c = ConnectionPool.getConnection(alias);
    		getAcc = c.prepareStatement("select ACCOUNT from DPER_ACC " +
    				"Where BRANCH=? AND MBRANCH=? AND VEOPER=? and " +
    				"CURRENCY=? and TYPEACC=?");
    		getAcc.setString(1, branch);
    		getAcc.setString(2, TransactionService.getMbranch_code(c));
    		getAcc.setString(3, veoper);
    		getAcc.setString(4, eqval);
    		getAcc.setInt(5, 2);
    		rs = getAcc.executeQuery();
    		while(rs.next()){
    			dep = rs.getString("ACCOUNT");
    			System.out.println("DEP ::: Account >>> " + rs.getString("ACCOUNT"));
	    	}
		} catch (SQLException e) {
			ISLogger.getLogger().info(e.getMessage());
			System.out.println("getAccBall >>> " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return dep.length() <= 5 ? dep : dep.substring(0, 5);
    }
    
    public static List<Account> getListAccount(String branch,String veoper, String eqval, String client, String dep, String alias){
    	Connection c = null;
    	List<Account> list = new ArrayList<Account>();
    	PreparedStatement prep = null;
    	CallableStatement init = null;
    	ResultSet rs;
    	Account acc = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
			init  = c.prepareCall("{ call info.init() }");
			init.execute();
    		prep = c.prepareStatement("Select id,name,s_out_tmp  from account t " +
							     	  " where t.branch = ? "+
							          " and t.client =? "+
							          " and t.acc_bal like ?"+
							          " and trim(t.currency) = ?"+
							          " and t.state=? "+ 
							          " and nvl(t.l_date,info.getday-?)<=info.getday "+
							          " and t.id not in "+
							          "(select r.account_code from sd_book_accounts r where r.state =? )"+
							          " order by t.id_order ");
			prep.setString(1, branch);
			prep.setString(2, client);
			prep.setString(3, dep);
			prep.setString(4, eqval);
			prep.setInt(5, 2);
			prep.setInt(6, 1);
			prep.setInt(7, 2);
			rs = prep.executeQuery();
			while(rs.next()){
				acc = new Account();
				acc.setAcc_dep(rs.getString("id"));
				acc.setName(rs.getString("name"));
				acc.setS_out_tmp(rs.getInt("s_out_tmp"));
				list.add(acc);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().info(e.getMessage());
			System.out.println("getListAccount >>> " + e.getMessage());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally{
			ConnectionPool.close(c);
		}
		return list;
    }
    
    public static Res dper_openAcc(String a_account, String vName,String un,String pw,String alias){
    	Connection c = null;
    	CallableStatement openAcc = null;
    	CallableStatement init = null;
    	Res res = null;
    	try {
			c = ConnectionPool.getConnection(un,pw,alias);
			init  = c.prepareCall("{ call info.init() }");
			init.execute();
			openAcc = c.prepareCall("{ call Dper.OpenAcc(?,?) }");
			openAcc.setString(1, a_account);
			openAcc.setString(2, vName);
			openAcc.execute();
			c.commit();
			res = new Res(0, "ok");
		} catch (SQLException e) {
			res = new Res(1,e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			ISLogger.getLogger().info(e.getMessage());
			System.out.println("dper_openAcc >>> " + e.getMessage());
			try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().info(e1.getMessage());
				System.out.println("dper_openAcc rollback>>> " + e1.getMessage());
				e1.printStackTrace();
			}
		} finally {
			ConnectionPool.close(c);
		}
		return res;
    }
}
