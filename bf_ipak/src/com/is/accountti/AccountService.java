package com.is.accountti;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import com.is.customer.CustomerService;
import com.is.tieto.TclientService;
import com.is.utilsti.CheckNull;
import com.is.utilsti.FilterField;
import com.is.utilsti.Res;

public class AccountService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM Account ";

    public static class actions_for_acc
    {
    	private int deal_group;
    	private int deal_id;
    	private int action_id;
    	private String name;
    	
		public int getDeal_group() {
			return deal_group;
		}
		public void setDeal_group(int deal_group) {
			this.deal_group = deal_group;
		}
		public int getDeal_id() {
			return deal_id;
		}
		public void setDeal_id(int deal_id) {
			this.deal_id = deal_id;
		}
		public int getAction_id() {
			return action_id;
		}
		public void setAction_id(int action_id) {
			this.action_id = action_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public actions_for_acc(int deal_group, int deal_id, int action_id,
				String name) {
			super();
			this.deal_group = deal_group;
			this.deal_id = deal_id;
			this.action_id = action_id;
			this.name = name;
		}
    	
    }

    public List<Account> getAccount(String alias)  {

            List<Account> list = new ArrayList<Account>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Account");
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
                                            rs.getInt("sign_registr"),
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
                                            rs.getInt("acc_group_id"),
                                            rs.getInt("state")));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    
    public static List<actions_for_acc> getactions_for_acc(int state, String alias)  {

        List<actions_for_acc> list = new ArrayList<actions_for_acc>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id");
                ps.setInt(1, state);
                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new actions_for_acc(
                        		rs.getInt("deal_group"),
                        		rs.getInt("deal_id"),
                        		rs.getInt("action_id"),
                        		rs.getString("name")
                        		));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

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
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_bal())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_bal=?",filter.getAcc_bal()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getClient())){
                  flfields.add(new FilterField(getCond(flfields)+ "client=?",filter.getClient()));
          }
          if(!CheckNull.isEmpty(filter.getId_order())){
                  flfields.add(new FilterField(getCond(flfields)+ "id_order=?",filter.getId_order()));
          }
          if(!CheckNull.isEmpty(filter.getName())){
                  flfields.add(new FilterField(getCond(flfields)+ "name=?",filter.getName()));
          }
          if(!CheckNull.isEmpty(filter.getSgn())){
                  flfields.add(new FilterField(getCond(flfields)+ "sgn=?",filter.getSgn()));
          }
          if(!CheckNull.isEmpty(filter.getBal())){
                  flfields.add(new FilterField(getCond(flfields)+ "bal=?",filter.getBal()));
          }
          if(!CheckNull.isEmpty(filter.getSign_registr())){
                  flfields.add(new FilterField(getCond(flfields)+ "sign_registr=?",filter.getSign_registr()));
          }
          if(!CheckNull.isEmpty(filter.getS_in())){
                  flfields.add(new FilterField(getCond(flfields)+ "s_in=?",filter.getS_in()));
          }
          if(!CheckNull.isEmpty(filter.getS_out())){
                  flfields.add(new FilterField(getCond(flfields)+ "s_out=?",filter.getS_out()));
          }
          if(!CheckNull.isEmpty(filter.getDt())){
                  flfields.add(new FilterField(getCond(flfields)+ "dt=?",filter.getDt()));
          }
          if(!CheckNull.isEmpty(filter.getCt())){
                  flfields.add(new FilterField(getCond(flfields)+ "ct=?",filter.getCt()));
          }
          if(!CheckNull.isEmpty(filter.getS_in_tmp())){
                  flfields.add(new FilterField(getCond(flfields)+ "s_in_tmp=?",filter.getS_in_tmp()));
          }
          if(!CheckNull.isEmpty(filter.getS_out_tmp())){
                  flfields.add(new FilterField(getCond(flfields)+ "s_out_tmp=?",filter.getS_out_tmp()));
          }
          if(!CheckNull.isEmpty(filter.getDt_tmp())){
                  flfields.add(new FilterField(getCond(flfields)+ "dt_tmp=?",filter.getDt_tmp()));
          }
          if(!CheckNull.isEmpty(filter.getCt_tmp())){
                  flfields.add(new FilterField(getCond(flfields)+ "ct_tmp=?",filter.getCt_tmp()));
          }
          if(!CheckNull.isEmpty(filter.getL_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "l_date=?",filter.getL_date()));
          }
          if(!CheckNull.isEmpty(filter.getDate_open())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_open=?",filter.getDate_open()));
          }
          if(!CheckNull.isEmpty(filter.getDate_close())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_close=?",filter.getDate_close()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_group_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_group_id=?",filter.getAcc_group_id()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(AccountFilter filter,String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM Account ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    n = rs.getInt(1);
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
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
            sql.append(msql);
            if(flFields.size()>0){

                    for(int i=0;i<flFields.size();i++){
                            sql.append(flFields.get(i).getSqlwhere());
                    }
            }
            sql.append(psql2);


            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
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
                                            rs.getInt("sign_registr"),
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
                                            rs.getInt("acc_group_id"),
                                            rs.getInt("state")));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    
    public static String doAction1(String un,String pw, String branch, String id,int actionid, String alias) {
         String res ="";
         String halias = CustomerService.get_alias_ho(alias);
         Connection c = null;
         try {
	        	if (halias.compareTo(alias) == 0)
	     		{
	     			c = ConnectionPool.getConnection(alias);
	     		}
	     		else
	     		{
	     			c = ConnectionPool.getConnection(un,pw, alias);
	     		}
                 //c = ConnectionPool.getConnection(un,pw, alias);
                 PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE branch=? and id=?");
                 ps.setString(1, branch);
                 ps.setString(2, id);
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                	 ConnectionPool.doAction(c, rs, 2, 2, actionid);
                      c.commit();
                 }
         } catch (Exception e) {
        	 LtLogger.getLogger().error(CheckNull.getPstr(e));
               //  e.printStackTrace();
        	 res = e.getMessage();
         } finally {
                 ConnectionPool.close(c);
         }
         return res;
 }

    public static Res doAction(String un,String pw, Account account,int actionid, String alias, Boolean selfBranch) {
        Res res =null;
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        String halias = CustomerService.get_alias_ho(alias);
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement csi = null;

        try {
        	if ((halias.compareTo(alias) == 0)&&(!selfBranch))
	  		{
	  			c = ConnectionPool.getConnection(alias);
	  		}
	  		else
	  		{
	  			c = ConnectionPool.getConnection(un,pw, alias);
	  		}
                //c = ConnectionPool.getConnection(un,pw, alias);
        		//c = ConnectionPool.getConnection(alias);
        		csi = c.prepareCall("{ call info.init() }");
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{ call Param.clearparam() }");
                ccs.execute();
                ccs = c.prepareCall("{? = call Param.getparam('ID') }");
                ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
                
                 cs.setString(1, "BRANCH");  cs.setString(2,account.getBranch()); cs.execute();
                 if (!CheckNull.isEmpty(account.getId())){
                 cs.setString(1, "ID");  cs.setString(2,account.getId()); cs.execute();
                 }
                 cs.setString(1, "ACC_BAL");  cs.setString(2,account.getAcc_bal()); cs.execute();
                 cs.setString(1, "CURRENCY");  cs.setString(2,account.getCurrency()); cs.execute();
                 cs.setString(1, "CLIENT");  cs.setString(2,account.getClient()); cs.execute();
                 cs.setString(1, "ID_ORDER");  cs.setString(2,account.getId_order()); cs.execute();
                 cs.setString(1, "NAME");  cs.setString(2,account.getName()); cs.execute();
                 cs.setString(1, "SGN");  cs.setString(2,account.getSgn()); cs.execute();
                 cs.setString(1, "BAL");  cs.setString(2,account.getBal()); cs.execute();
                 cs.setString(1, "SIGN_REGISTR");  cs.setString(2,account.getSign_registr()+""); cs.execute();
                 //cs.setString(1, "S_IN");  cs.setString(2,account.getS_in()+""); cs.execute();
                 //cs.setString(1, "S_OUT");  cs.setString(2,account.getS_out()+""); cs.execute();
                 //cs.setString(1, "DT");  cs.setString(2,account.getDt()+""); cs.execute();
                 //cs.setString(1, "CT");  cs.setString(2,account.getCt()+""); cs.execute();
                 //cs.setString(1, "S_IN_TMP");  cs.setString(2,account.getS_in_tmp()+""); cs.execute();
                 //cs.setString(1, "S_OUT_TMP");  cs.setString(2,account.getS_out_tmp()+""); cs.execute();
                 //cs.setString(1, "DT_TMP");  cs.setString(2,account.getDt_tmp()+""); cs.execute();
                 //cs.setString(1, "CT_TMP");  cs.setString(2,account.getCt_tmp()+""); cs.execute();
                 //cs.setString(1, "L_DATE");  cs.setString(2,account.getL_date()); cs.execute();
                 //cs.setString(1, "DATE_OPEN");  cs.setString(2,account.getDate_open()); cs.execute();
                 //cs.setString(1, "DATE_CLOSE");  cs.setString(2,account.getDate_close()); cs.execute();
                 cs.setString(1, "ACC_GROUP_ID");  cs.setString(2,account.getAcc_group_id()+""); cs.execute();
                // cs.setString(1, "STATE");  cs.setString(2,account.getState()); cs.execute();
                // cs.setString(1, "KOD_ERR");  cs.setString(2,account.getKod_err()); cs.execute();
                // cs.setString(1, "FILE_NAME");  cs.setString(2,account.getFile_name()); cs.execute();

                acs.setInt(1, 2);
                acs.setInt(2, 2);
                acs.setInt(3,actionid);
                csi.execute();
                acs.execute();
                c.commit();
                ccs.execute();
                res = new Res(0,ccs.getString(1));


    } catch (Exception e) {
    	LtLogger.getLogger().error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
    } finally {
            ConnectionPool.close(c);
    }
    return res;
   }
    
    public static Res doAction_br(String un,String pw, Account account,int actionid1, int actionid2, String alias, Boolean selfBranch) {
        Res res =null;
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        String halias = CustomerService.get_alias_ho(alias);
        
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement csi = null;

        try {
        	if ((halias.compareTo(alias) == 0)&&(!selfBranch))
	  		{
	  			c = ConnectionPool.getConnection(alias);
	  		}
	  		else
	  		{
	  			c = ConnectionPool.getConnection(un,pw, alias);
	  		}
                
        		csi = c.prepareCall("{ call info.init() }");
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{? = call Param.getparam('ID') }");
                ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
                
                 cs.setString(1, "BRANCH");  cs.setString(2,account.getBranch()); cs.execute();
                 if (!CheckNull.isEmpty(account.getId())){
                 cs.setString(1, "ID");  cs.setString(2,account.getId()); cs.execute();
                 }
                 cs.setString(1, "ACC_BAL");  cs.setString(2,account.getAcc_bal()); cs.execute();
                 cs.setString(1, "CURRENCY");  cs.setString(2,account.getCurrency()); cs.execute();
                 cs.setString(1, "CLIENT");  cs.setString(2,account.getClient()); cs.execute();
                 cs.setString(1, "ID_ORDER");  cs.setString(2,account.getId_order()); cs.execute();
                 cs.setString(1, "NAME");  cs.setString(2,account.getName()); cs.execute();
                 cs.setString(1, "SGN");  cs.setString(2,account.getSgn()); cs.execute();
                 cs.setString(1, "BAL");  cs.setString(2,account.getBal()); cs.execute();
                 cs.setString(1, "SIGN_REGISTR");  cs.setInt(2,account.getSign_registr()); cs.execute();
                 cs.setString(1, "ACC_GROUP_ID");  cs.setInt(2,account.getAcc_group_id()); cs.execute();
                 
                acs.setInt(1, 2);
                acs.setInt(2, 2);
                acs.setInt(3,actionid1);
                csi.execute();
                acs.execute();
                ccs.execute();
                res = new Res(0,ccs.getString(1));
                if (actionid2 != 0)
                {
                	acs.setInt(3,actionid2);
                	acs.execute();
                }
                c.commit();
              //  c.rollback();
    }
        catch (Exception e)
    {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
    	try
		{
	    	c.rollback();
		}
		catch (Exception g)
		{
			res = new Res(-1, g.getMessage());
			return res;
		}
        res = new Res(-1, e.getMessage());
    }
    finally
    {
            ConnectionPool.close(c);
    }
    return res;
   }
    
    
    public static Res doAction_acc(String un,String pw, int deal_group_id, int deal_id, Account account,int actionid, String alias, Boolean selfBranch) {
        Res res =null;
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        String halias = CustomerService.get_alias_ho(alias);
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement csi = null;

        try {
        	if ((halias.compareTo(alias) == 0)&&(!selfBranch))
	  		{
	  			c = ConnectionPool.getConnection(alias);
	  		}
	  		else
	  		{
	  			c = ConnectionPool.getConnection(un,pw, alias);
	  		}
        		//c = ConnectionPool.getConnection(un, pw, alias);
        		csi = c.prepareCall("{ call info.init() }");
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{ call Param.clearparam() }");
                ccs.execute();
                ccs = c.prepareCall("{? = call Param.getparam('ID') }");
                ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
                
                 cs.setString(1, "BRANCH");  cs.setString(2,account.getBranch()); cs.execute();
                 if (!CheckNull.isEmpty(account.getId())){
                 cs.setString(1, "ID");  cs.setString(2,account.getId()); cs.execute();
            }
                acs.setInt(1, deal_group_id);
                acs.setInt(2, deal_id);
                acs.setInt(3,actionid);
                csi.execute();
                acs.execute();
                c.commit();
                ccs.execute();
                res = new Res(0,ccs.getString(1));
    } catch (Exception e) {
    	LtLogger.getLogger().error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
    } finally {
            ConnectionPool.close(c);
    }
    return res;
   }
    
    public static Res doAction_acc_ho(String un,String pw, int deal_group_id, int deal_id, Account account,int actionid, String alias, Boolean selfBranch) {
        Res res =null;
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        String halias = CustomerService.get_alias_ho(alias);
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        CallableStatement csi = null;

        try {
        	if ((halias.compareTo(alias) == 0)&&(!selfBranch))
	  		{
	  			c = ConnectionPool.getConnection(alias);
	  		}
	  		else
	  		{
	  			c = ConnectionPool.getConnection(un,pw, alias);
	  		}
        		csi = c.prepareCall("{ call info.init() }");
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{ call Param.clearparam() }");
                ccs.execute();
                ccs = c.prepareCall("{? = call Param.getparam('ID') }");
                ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
                
                 cs.setString(1, "BRANCH");  cs.setString(2,account.getBranch()); cs.execute();
                 if (!CheckNull.isEmpty(account.getId()))
                 {
                	 cs.setString(1, "ID");  cs.setString(2,account.getId()); cs.execute();
                	 if (
                	(account.getBranch().compareTo(ConnectionPool.getValue("HO", alias))==0)&&
                	(account.getAcc_bal().compareTo("20206")==0)
                	)
                	 {
                		 cs.setString(1, "ACC_GROUP_ID"); cs.setString(2,"101"); cs.execute();
                	 }
                 }
                acs.setInt(1, deal_group_id);
                acs.setInt(2, deal_id);
                acs.setInt(3,actionid);
                csi.execute();
                acs.execute();
                c.commit();
                ccs.execute();
                res = new Res(0,ccs.getString(1));
    } catch (Exception e) {
    	LtLogger.getLogger().error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
            try
			{
				c.rollback();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
    } finally {
            ConnectionPool.close(c);
    }
    return res;
   }
    
    
    public static String doAction(String un,String pw, String branch, String id,int actionid, String alias, Boolean selfBranch) {
    	String res ="";
       // Account account = new Account();
    	SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy"); 
        Connection c = null;
        String halias = CustomerService.get_alias_ho(alias);
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        String cn;
        try {
        	if ((halias.compareTo(alias) == 0)&&(!selfBranch))
	  		{
	  			c = ConnectionPool.getConnection(alias);
	  		}
	  		else
	  		{
	  			c = ConnectionPool.getConnection(un,pw, alias);
	  		}
                //c = ConnectionPool.getConnection(un,pw, alias);
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
                		 //
                	 }
                	 
                     acs.setInt(1, 2);
                     acs.setInt(2, 2);
                     acs.setInt(3,actionid);
                     acs.execute();
                     c.commit();
                }
        } catch (Exception e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
               // e.printStackTrace();
        	res = e.getMessage();
        } finally {
                ConnectionPool.close(c);
        }
        return res;
}
/*
    public Account getAccount(int accountId) {

            Account account = new Account();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM account WHERE account_id=?");
                    ps.setInt(1, accountId);
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
                            account.setSign_registr(rs.getInt("sign_registr"));
                            account.setS_in(rs.getDouble("s_in"));
                            account.setS_out(rs.getDouble("s_out"));
                            account.setDt(rs.getDouble("dt"));
                            account.setCt(rs.getDouble("ct"));
                            account.setS_in_tmp(rs.getDouble("s_in_tmp"));
                            account.setS_out_tmp(rs.getDouble("s_out_tmp"));
                            account.setDt_tmp(rs.getDouble("dt_tmp"));
                            account.setCt_tmp(rs.getDouble("ct_tmp"));
                            account.setL_date(rs.getDate("l_date"));
                            account.setDate_open(rs.getDate("date_open"));
                            account.setDate_close(rs.getDate("date_close"));
                            account.setAcc_group_id(rs.getDouble("acc_group_id"));
                            account.setState(rs.getDouble("state"));
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
                           // account.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO account (branch, id, acc_bal, currency, client, id_order, name, sgn, bal, sign_registr, s_in, s_out, dt, ct, s_in_tmp, s_out_tmp, dt_tmp, ct_tmp, l_date, date_open, date_close, acc_group_id, state, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,account.getBranch());
                    ps.setString(2,account.getId());
                    ps.setString(3,account.getAcc_bal());
                    ps.setString(4,account.getCurrency());
                    ps.setString(5,account.getClient());
                    ps.setString(6,account.getId_order());
                    ps.setString(7,account.getName());
                    ps.setString(8,account.getSgn());
                    ps.setString(9,account.getBal());
                    ps.setInt(10,account.getSign_registr());
                    ps.setDouble(11,account.getS_in());
                    ps.setDouble(12,account.getS_out());
                    ps.setDouble(13,account.getDt());
                    ps.setDouble(14,account.getCt());
                    ps.setDouble(15,account.getS_in_tmp());
                    ps.setDouble(16,account.getS_out_tmp());
                    ps.setDouble(17,account.getDt_tmp());
                    ps.setDouble(18,account.getCt_tmp());
                    ps.setDate(19,account.getL_date());
                    ps.setDate(20,account.getDate_open());
                    ps.setDate(21,account.getDate_close());
                    ps.setDouble(22,account.getAcc_group_id());
                    ps.setDouble(23,account.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return account;
    }
*/    
/*
    public static void update(Account account)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE account SET branch=?, id=?, acc_bal=?, currency=?, client=?, id_order=?, name=?, sgn=?, bal=?, sign_registr=?, s_in=?, s_out=?, dt=?, ct=?, s_in_tmp=?, s_out_tmp=?, dt_tmp=?, ct_tmp=?, l_date=?, date_open=?, date_close=?, acc_group_id=?, state=?,  WHERE account_id=?");
                    
                    ps.setString(1,account.getBranch());
                    ps.setString(2,account.getId());
                    ps.setString(3,account.getAcc_bal());
                    ps.setString(4,account.getCurrency());
                    ps.setString(5,account.getClient());
                    ps.setString(6,account.getId_order());
                    ps.setString(7,account.getName());
                    ps.setString(8,account.getSgn());
                    ps.setString(9,account.getBal());
                    ps.setint(10,account.getSign_registr());
                    ps.setDouble(11,account.getS_in());
                    ps.setDouble(12,account.getS_out());
                    ps.setDouble(13,account.getDt());
                    ps.setDouble(14,account.getCt());
                    ps.setDouble(15,account.getS_in_tmp());
                    ps.setDouble(16,account.getS_out_tmp());
                    ps.setDouble(17,account.getDt_tmp());
                    ps.setDouble(18,account.getCt_tmp());
                    ps.setDate(19,account.getL_date());
                    ps.setDate(20,account.getDate_open());
                    ps.setDate(21,account.getDate_close());
                    ps.setDouble(22,account.getAcc_group_id());
                    ps.setDouble(23,account.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    throw new Exception(e);
            } finally {
                    ConnectionPool.close(c);
            }

    }

*/
    public static String getCardAcc(String un, String pw,String bal, String client,  String curr, String ido, String name, String alias, Boolean selfBranch){
        Connection c = null;
        String res= "";
        Res dres;
        try {
                c = ConnectionPool.getConnection( alias);
                System.out.println("sql: select * from account a where a.acc_bal="+bal+" and a.client="+client+" and a.currency="+curr+" and a.id_order="+ido+"");
                PreparedStatement ps = c.prepareStatement("select * from account a where a.acc_bal=? and a.client=? and a.currency=? and a.id_order=?");
                ps.setString(1, bal);
                ps.setString(2, client);
                ps.setString(3, curr);
                ps.setString(4, ido);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                	if (rs.getInt("state")==2){
                		res = rs.getString("id");
                	}
                }else{
                	Account acc = new Account();
                	acc.setBal("B");
                	acc.setSgn("P");
                	acc.setAcc_bal(bal);
                	acc.setCurrency(curr);
                	acc.setId_order(ido);
                	acc.setName(name);
                	acc.setClient(client);
                	acc.setSign_registr(2);
                	//acc.
                	dres = doAction(un,pw,acc,1,alias,selfBranch);
                	acc.setId(dres.getName());
                	dres = doAction(un,pw,acc,2,alias,selfBranch);
                	res = dres.getName();
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
            
    } finally {
            ConnectionPool.close(c);
    }   
    return res;
    }
    
    
    public static Res Get_acc_hole(String acc_bal, String first, String last, String client_id, String branch, String alias)
    {
    	Res res = new Res();
    	res.setCode(0);
    	Connection c;
    	try
		{
			c = ConnectionPool.getConnection(alias);
			
			PreparedStatement ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t "+
					"where "+
					"t.client = ? "+
					"and t.branch = ? "+
					"and t.acc_bal = ? "+
					"and TO_NUMBER(t.id_order) >= TO_NUMBER(?) "+
					"and TO_NUMBER(t.id_order) < TO_NUMBER(?)" +
					"and t.currency = '840'");
			ps.setString(1, client_id);
			ps.setString(2, branch);
			ps.setString(3, acc_bal);
			ps.setString(4, first);
			ps.setString(5, last);
			
			ResultSet rs = ps.executeQuery();
			res.setName(first);
			if (rs.next())
			{
				if ((rs.getString("res") != null)&&(rs.getString("res").compareTo("") != 0))
					res.setName(rs.getString("res"));
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		}
    	
    	return res;
    }
    
    
    public static Res Get_acc_hole1(String acc_bal, String first, String last, String client_id, String branch, String alias)
    {
    	Res res = new Res();
    	res.setCode(0);
    	Connection c;
    	try
		{
			c = ConnectionPool.getConnection(alias);
			
			PreparedStatement ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t "+
					"where "+
					"t.client = ? "+
					"and t.branch = ? "+
					"and t.acc_bal = ? "+
					"and TO_NUMBER(t.id_order) >= TO_NUMBER(?) "+
					"and TO_NUMBER(t.id_order) < TO_NUMBER(?)" +
					"and t.currency = '000'");
			ps.setString(1, client_id);
			ps.setString(2, branch);
			ps.setString(3, acc_bal);
			ps.setString(4, first);
			ps.setString(5, last);
			
			ResultSet rs = ps.executeQuery();
			res.setName(first);
			if (rs.next())
			{
				if ((rs.getString("res") != null)&&(rs.getString("res").compareTo("") != 0))
					res.setName(rs.getString("res"));
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		}
    	
    	return res;
    }
    
    public static Res doAction_create_acc_in_br(String un, String pw, 
    		String bal, String client, String curr, String ido, String name, int group, String alias, String branch, Boolean selfBranch)
    {
      //  String res= null;
        Res dres, ores = new Res();
    	Account acc = new Account();
    	dres = null;
    	ores.setCode(0);
    	ores.setName(null);
    	
    	if (ido == null)
    	{
    		ido = get_ord(bal, curr, client, branch, alias);
    	}
    	
    	acc.setBal("B");
    	acc.setSgn("P");
    	acc.setAcc_bal(bal);
    	acc.setCurrency(curr);
    	acc.setId_order(ido);
    	acc.setAcc_group_id(group);
    	acc.setName(name);
    	acc.setClient(client);
    	acc.setBranch(branch);
    	acc.setSign_registr(2);
    	dres = doAction_br(un, pw, acc, 1, 2, alias, selfBranch);
    	//if (dres.getCode() != 0){ores.setCode(dres.getCode()); ores.setName(dres.getName());}
    	return dres;
    }
    
    public static String get_ord(String bal, String cur, String client_id, String branch, String alias)
    {
    	String res = null;
    	Connection c = null;
    	
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select info.GetNewAccOrder(?, ?, ?, ?) NEW_ACC_ORDER from dual");
                ps.setString(1, bal);
                ps.setString(2, cur);
                ps.setString(3, client_id);
                ps.setString(4, branch);
                ResultSet rs = ps.executeQuery();
                rs.next();
                     res = rs.getString("NEW_ACC_ORDER");
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return res;
    }
    
    public static List<Account> getClAccount(String client_id, String branch, String alias)  {

        List<Account> list = new ArrayList<Account>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                //Statement s = c.createStatement();
                PreparedStatement ps = c.prepareStatement("SELECT A.*, S.name state_desc FROM Account A, State_account S where A.branch=? and A.client=? and S.deal_id=2 and S.id = A.state");
                ps.setString(1, branch);
                ps.setString(2, client_id);
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
                                        rs.getInt("sign_registr"),
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
                                        rs.getInt("acc_group_id"),
                                        rs.getInt("state"),
                                        rs.getString("state_desc")
                                        ));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;
    }
    
    public static String get_account_state_caption(int state, String alias)
    {
    	String res = null;
    	Connection c = null;
       
        try {
        	c = ConnectionPool.getConnection(alias);
            PreparedStatement ps = c.prepareStatement("select r.name from state_account r where r.deal_id = '2' and r.id = ?");
            ps.setInt(1, state);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	res = rs.getString("name");
            }
            
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
    	return res;
    }
    
    public static List<Account> get_card_accounts(String client_id, String branch, String card_code)
    {
    	List<Account> res = new ArrayList<Account>();
    	
    	Connection c = null;
       
        try {
        	c = ConnectionPool.getConnection("iy00444");
        	
        	PreparedStatement ps = c.prepareStatement("select * from account t "+
          "where "+
          "t.client = ? "+
          "and t.branch = '00444' "+
          "and t.acc_bal = '22618' "+
          "and TO_NUMBER(t.id_order) >= (select s.id_order_account from bf_tieto_card_setting s where s.code = ?)"+
          "and TO_NUMBER(t.id_order) <= (select s.id_order_max from bf_tieto_card_setting s where s.code = ?) "+
          "and t.currency = '840'");
            
        	ps.setString(1, client_id);
            ps.setString(2, card_code);
            ps.setString(3, card_code);
        	
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
            	res.add(new Account(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("acc_bal"),
                        rs.getString("currency"),
                        rs.getString("client"),
                        rs.getString("id_order"),
                        rs.getString("name"),
                        rs.getString("sgn"),
                        rs.getString("bal"),
                        rs.getInt("sign_registr"),
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
                        rs.getInt("acc_group_id"),
                        rs.getInt("state")));
            }
            
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
    	return res;
    }
    
    
    // ONLY for new card window.
    public static List<Account> get_card_accounts_new_card(String client_id, 
    		String branch, String card_code, String t_client_id)
    {
    	List<Account> res = new ArrayList<Account>();
    	
    	Connection c = null;
       
        try {
        	c = ConnectionPool.getConnection("iy00444");
        	
        	PreparedStatement ps_st = c.prepareStatement("select t.allow_multiple_cards_per_acc " +
        			"from bf_tieto_card_setting t where t.code = ?");
        	ps_st.setString(1, card_code);
        	ResultSet rs_st = ps_st.executeQuery();
        	rs_st.next();
        	boolean allowed_multiple = (rs_st.getInt("allow_multiple_cards_per_acc") == 1);
        	
        	HashMap<String, String> client_accounts_for_this_product = 
        		TclientService.get_client_ti_acc(t_client_id, card_code);
        	
        	PreparedStatement ps = c.prepareStatement("select * from account t "+
          "where "+
          "t.client = ? "+
          "and t.branch = '00444' "+
          "and t.acc_bal in ('22618', '22619') "+
          "and TO_NUMBER(t.id_order) >= (select s.id_order_account from bf_tieto_card_setting s where s.code = ?)"+
          "and TO_NUMBER(t.id_order) <= (select s.id_order_max from bf_tieto_card_setting s where s.code = ?) "+
          "and t.currency = '840'");
            
        	ps.setString(1, client_id);
            ps.setString(2, card_code);
            ps.setString(3, card_code);
        	
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
            	if (allowed_multiple || 
            			(!client_accounts_for_this_product.containsKey(rs.getString("id")))
            		)
            	res.add(new Account(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("acc_bal"),
                        rs.getString("currency"),
                        rs.getString("client"),
                        rs.getString("id_order"),
                        rs.getString("name"),
                        rs.getString("sgn"),
                        rs.getString("bal"),
                        rs.getInt("sign_registr"),
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
                        rs.getInt("acc_group_id"),
                        rs.getInt("state")));
            }
            
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
    	return res;
    }
    
    public static List<Account> get_card_accounts_new_card1(String client_id, 
    		String branch, String card_code, String t_client_id)
    {
    	List<Account> res = new ArrayList<Account>();
    	
    	Connection c = null;
       
        try {
        	c = ConnectionPool.getConnection("iy00444");
        	
        	PreparedStatement ps_st = c.prepareStatement("select t.allow_multiple_cards_per_acc " +
        			"from bf_tieto_card_setting t where t.code = ?");
        	ps_st.setString(1, card_code);
        	ResultSet rs_st = ps_st.executeQuery();
        	rs_st.next();
        	boolean allowed_multiple = (rs_st.getInt("allow_multiple_cards_per_acc") == 1);
        	
        	HashMap<String, String> client_accounts_for_this_product = 
        		TclientService.get_client_ti_acc(t_client_id, card_code);
        	
        	PreparedStatement ps = c.prepareStatement("select * from account t "+
          "where "+
          "t.client = ? "+
          "and t.branch = '00444' "+
          "and t.acc_bal in ('22618', '22619') "+
          "and TO_NUMBER(t.id_order) >= (select s.id_order_account from bf_tieto_card_setting s where s.code = ?)"+
          "and TO_NUMBER(t.id_order) <= (select s.id_order_max from bf_tieto_card_setting s where s.code = ?) "+
          "and t.currency = '000'");
            
        	ps.setString(1, client_id);
            ps.setString(2, card_code);
            ps.setString(3, card_code);
        	
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
            	if (allowed_multiple || 
            			(!client_accounts_for_this_product.containsKey(rs.getString("id")))
            		)
            	res.add(new Account(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("acc_bal"),
                        rs.getString("currency"),
                        rs.getString("client"),
                        rs.getString("id_order"),
                        rs.getString("name"),
                        rs.getString("sgn"),
                        rs.getString("bal"),
                        rs.getInt("sign_registr"),
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
                        rs.getInt("acc_group_id"),
                        rs.getInt("state")));
            }
            
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
    	return res;
    }
    
    public static Account get_account(String id, String branch, String alias)
    {
    	Account res = null;
    	Connection c = null;
    	
    	try
		{
			c = ConnectionPool.getConnection(alias);
			
			PreparedStatement ps = c.prepareStatement("select * from account t where t.id = ? and t.branch = ?");
			ps.setString(1, id);
			ps.setString(2, branch);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				res = new Account(
                        rs.getString("branch"),
                        rs.getString("id"),
                        rs.getString("acc_bal"),
                        rs.getString("currency"),
                        rs.getString("client"),
                        rs.getString("id_order"),
                        rs.getString("name"),
                        rs.getString("sgn"),
                        rs.getString("bal"),
                        rs.getInt("sign_registr"),
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
                        rs.getInt("acc_group_id"),
                        rs.getInt("state"));
			}
			
		} catch (SQLException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
    	
    	
    	
    	return res;
    }
    
    
    public static Account getAccount(String accoun_n, String alias, String branch, Connection c)  {

        Account list = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	
                ps = c.prepareStatement("SELECT * FROM Account t where t.id = ? and t.branch = ?");
                ps.setString(1, accoun_n);
                ps.setString(2, branch);
                rs = ps.executeQuery();
                if (rs.next()) {
                        list = new Account(
                                        rs.getString("branch"),
                                        rs.getString("id"),
                                        rs.getString("acc_bal"),
                                        rs.getString("currency"),
                                        rs.getString("client"),
                                        rs.getString("id_order"),
                                        rs.getString("name"),
                                        rs.getString("sgn"),
                                        rs.getString("bal"),
                                        rs.getInt("sign_registr"),
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
                                        rs.getInt("acc_group_id"),
                                        rs.getInt("state"));
                }
        } catch (SQLException e) {
        	LtLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
        	try {if (ps!=null)ps.close();
        	if (rs!=null)rs.close();} catch(Exception e){}
        }
        return list;

}
    
    public static String multimoneytostr(BigDecimal amount, String language, String currency, Connection c)
    {
    	String res = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try
    	{
    		ps = c.prepareStatement("select bf_money_to_str.MultiMoneyToStr(?, ?, ?) res from dual");
    		ps.setBigDecimal(1, amount);
    		ps.setString(2, language);
    		ps.setString(3, currency);
    		rs = ps.executeQuery();
    		if (rs.next())
    		{
    			res = rs.getString("res");
    		}
    	}
    	catch(Exception e)
    	{
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
    	}
    	finally
    	{
    		try {if (ps!=null)ps.close();
        	if (rs!=null)rs.close();} catch(Exception e){}
    	}
    	return res;
    }
    
    public static double GetCourse(String currency_1, String currency_2, Connection c)
    {
    	double res = 0;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try
    	{
    		ps = c.prepareStatement("select info.GetCourse(?, ?, 1, sysdate) res from dual");
    		ps.setString(1, currency_1);
    		ps.setString(2, currency_2);
    		rs = ps.executeQuery();
    		if (rs.next())
    		{
    			res = rs.getDouble("res");
    		}
    	}
    	catch(Exception e)
    	{
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
    	}
    	finally
    	{
    		try {if (ps!=null)ps.close();
        	if (rs!=null)rs.close();} catch(Exception e){}
    	}
    	return res;
    }
    
    public static String GetCur20206(String client_id, String branch, Connection c)
    {
    	String res = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try
    	{
    		ps = c.prepareStatement("select t.cur_acc res from bf_tieto_customers t " +
    				"where t.head_customer_id = ? and t.branch = ?");
    		ps.setString(1, client_id);
    		ps.setString(2, branch);
    		rs = ps.executeQuery();
    		if (rs.next())
    		{
    			res = rs.getString("res");
    		}
    	}
    	catch(Exception e)
    	{
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
    	}
    	finally
    	{
    		try {if (ps!=null)ps.close();
        	if (rs!=null)rs.close();} catch(Exception e){}
    	}
    	return res;
    }
    public static String GetCur20206_tclient(String client_id, String branch, Connection c)
    {
    	String res = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try
    	{
    		ps = c.prepareStatement("select t.cur_acc res from bf_tieto_customers t " +
    				"where t.tieto_customer_id = ? and t.branch = ?");
    		ps.setString(1, client_id);
    		ps.setString(2, branch);
    		rs = ps.executeQuery();
    		if (rs.next())
    		{
    			res = rs.getString("res");
    		}
    	}
    	catch(Exception e)
    	{
    		LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
    	}
    	finally
    	{
    		try {if (ps!=null)ps.close();
        	if (rs!=null)rs.close();} catch(Exception e){}
    	}
    	return res;
    }
    
 /*   public long get_ac_balance(String branch, String account_n, Connection c)
    {
    	long res = 0;
    	
    	PreparedStatement ps = c.prepareStatement("");
    	
    	return 0;
    }*/
}