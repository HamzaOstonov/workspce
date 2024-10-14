package com.is.account_form;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.time.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.habib.Habib;
//import com.is.agrotieto.customer.CustomerService;
//import com.is.agrotieto.tieto.TclientService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class AccountFormService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM AccountFormForm ";
    public static class actions_for_acc
    {
    	private String firstname;
    	private String lastname;
    	private String middlename;
    	private Date birthdate;
    	private String gender;
    	private String bankname;
    	private String branch;
    	private String position;
    	
    	public String getFirstname() {
    		return firstname;
    	}
    	public void setFirstname(String firstname) {
    		this.firstname = firstname;
    	}
    	public String getLastname() {
    		return lastname;
    	}
    	public void setLastname(String lastname) {
    		this.lastname = lastname;
    	}
    	public String getMiddlename() {
    		return middlename;
    	}
    	public void setMiddlename(String middlename) {	
    		this.middlename = middlename;
    	}
    	public Date getBirthdate() {
    		return birthdate;
    	}
    	public void setBirthdate(Date birthdate) {
    		this.birthdate = birthdate;
    	}
    	public String getGender() {
    		return gender;
    	}
    	public void setGender(String gender) {
    		this.gender = gender;
    	}
    	public String getBankname() {
    		return bankname;
    	}
    	public void setBankname(String bankname) {
    		this.bankname = bankname;
    	}
    	public String getBranch() {
    		return branch;
    	}
    	public void setBranch(String branch) {
    		this.branch = branch;
    	}
    	public String getPosition() {
    		return position;
    	}
    	public void setPosition(String position) {
    		this.position = position;
    	}
		public actions_for_acc(String firstname, String lastname, String middlename, Date birthdate, String gender,
				String bankname, String branch, String position) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.middlename = middlename;
			this.birthdate = birthdate;
			this.gender = gender;
			this.bankname = bankname;
			this.branch = branch;
			this.position = position;
		}
    }
    public List<AccountForm> getAccountForm(String alias)  {
            List<AccountForm> list = new ArrayList<AccountForm>();
            Connection c = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM temp_account_form");
                    while (rs.next()) {
//                            list.add(new AccountForm(
//                                            rs.getString("id"),
//                                            rs.getString("title"),
//                                            rs.getString("year"),
//                                            rs.getString("score")));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;
    }
    public static int insertacc(AccountForm m) {
		int result = -1;
		Connection c = null;
		PreparedStatement ps = null;
		// boolean bool = false;
		try {
			// ISLogger.getLogger().error("insert bf_openway_contract_acc");
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into temp_account_form(id,firstname,lastname, middlename, birthday, gender, bankname, branch, position) values(seq_movie.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");

			// ps.setInt(1, m.getid());
			java.util.Date date = new java.util.Date();		
			
			ps.setString(1, m.getFirstname());
			ps.setString(2, m.getLastname()); 
			ps.setString(3, m.getMiddlename());
			ps.setDate(4, new Date(date.getTime()));
			ps.setString(5, m.getGender() ); 
			ps.setString(6, m.getBankname());
			ps.setString(7, m.getBranch());
			ps.setString(8, m.getPosition());
			ps.execute();

			c.commit();
			result = 0;

		}

		catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
			}
			// res = new Res(-1, e.getMessage());
			result = -1;
			ISLogger.getLogger().error("insert xato " + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			}
			ConnectionPool.close(c);
		}
		return result;
	}
}
//    public static List<actions_for_acc> getactions_for_acc(int state, String alias)  {
//        List<actions_for_acc> list = new ArrayList<actions_for_acc>();
//        Connection c = null;
//        try {
//                c = ConnectionPool.getConnection(alias);
//                PreparedStatement ps = c.prepareStatement("select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_AccountForm tc, action_AccountForm aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id");
//                ps.setInt(1, state);
//                
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                        list.add(new actions_for_acc(
//                                rs.getString("id"),
//                                rs.getString("title"),
//                                rs.getString("year"),
//                                rs.getString("score")));
//                }
//        } catch (SQLException e) {
//        	LtLogger.getLogger().error(CheckNull.getPstr(e));
//                e.printStackTrace();
//        } finally {
//                ConnectionPool.close(c);
//        }
//        return list;
//}
//    private static String getCond(List<FilterField> flfields){
//            if(flfields.size()>0){
//                    return " and ";
//            } else
//            return " where ";
//    }
//    private static List<FilterField> getFilterFields(AccountFormFilter filter){
//            List<FilterField> flfields = new ArrayList<FilterField>();
//          if(!CheckNull.isEmpty(filter.getBranch())){
//                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getBranch()));
//          }
//          if(!CheckNull.isEmpty(filter.getId())){
//                  flfields.add(new FilterField(getCond(flfields)+ "title=?",filter.getId()));
//          }
//          if(!CheckNull.isEmpty(filter.getAcc_bal())){
//                  flfields.add(new FilterField(getCond(flfields)+ "year=?",filter.getAcc_bal()));
//          }
//          if(!CheckNull.isEmpty(filter.getCurrency())){
//                  flfields.add(new FilterField(getCond(flfields)+ "score=?",filter.getCurrency()));
//          }
//          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
//            return flfields;
//    }
//}