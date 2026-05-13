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
import java.util.List;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.agrotieto.tieto.AccInfo;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class TrPayService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by ID desc";
    private static String msql ="SELECT * FROM bf_Tr_Pay ";


    public List<TrPay> getTrPay(String alias )  {

            List<TrPay> list = new ArrayList<TrPay>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias );
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TrPay order by ID desc");
                    while (rs.next()) {
                            list.add(new TrPay(
                                            rs.getLong("id"),
                                            rs.getString("branch"),
                                            rs.getInt("operation_id"),
                                            rs.getBigDecimal("amount"),
                                            rs.getString("card_acc"),
                                            rs.getString("cur_acc"),
                                            rs.getDate("date_created"),
                                            rs.getInt("parent_group_id"),
                                            rs.getInt("state"),
                                            rs.getString("account_no"),
                                            rs.getString("cl_name"),
                                            rs.getInt("deal_id")));
                            
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }

    
    


  public static Res doAction(String un,String pw, TrPay trpay,int actionid, int deal_id, String alias) {
     Res res =null;
     SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
     Connection c = null;
     CallableStatement cs = null;
     CallableStatement acs = null;
     CallableStatement ccs = null;
 //   System.out.println(trpay.getAmount()+ "  "+actionid);

     try {
             c = ConnectionPool.getConnection(un,pw,alias);
             cs = c.prepareCall("{ call Param.SetParam(?,?) }");
             acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
             ccs = c.prepareCall("{ call Param.clearparam() }");
             ccs.execute();
             ccs = c.prepareCall("{? = call Param.getparam('ID') }");
             ccs.registerOutParameter(1, java.sql.Types.INTEGER);
             
             if(trpay.getId()!=null){  
             cs.setString(1, "ID");  cs.setString(2,trpay.getId()+""); cs.execute();
             }
              cs.setString(1, "BRANCH");  cs.setString(2,trpay.getBranch()); cs.execute();
              cs.setString(1, "OPERATION_ID");  cs.setString(2,trpay.getOperation_id()+""); cs.execute();
              cs.setString(1, "AMOUNT");  cs.setString(2,trpay.getAmount()+""); cs.execute();
              cs.setString(1, "CARD_ACC");  cs.setString(2,trpay.getCard_acc()); cs.execute();
              cs.setString(1, "CUR_ACC");  cs.setString(2,trpay.getCur_acc()); cs.execute();
              //cs.setString(1, "DATE_CREATED");  cs.setString(2,bdf.format(trpay.getDate_created())); cs.execute();
              cs.setString(1, "PARENT_GROUP_ID");  cs.setString(2,trpay.getParent_group_id()+""); cs.execute();
              //cs.setString(1, "STATE");  cs.setString(2,trpay.getState()+""); cs.execute();
              cs.setString(1, "ACCOUNT_NO");  cs.setString(2,trpay.getAccount_no()); cs.execute();
              cs.setString(1, "CL_NAME");  cs.setString(2,trpay.getCl_name()); cs.execute();
              cs.setString(1, "BFUSER_ID");  cs.setString(2,trpay.getEmp_id()+""); cs.execute();
              cs.setString(1, "PAN");  cs.setString(2,trpay.getPan()); cs.execute();
              cs.setString(1, "DOC_NUM");  cs.setString(2,trpay.getDoc_num()); cs.execute();

             acs.setInt(1, 144);
             acs.setInt(2, deal_id);
             acs.setInt(3,actionid);
             acs.execute();
             c.commit();
             ccs.execute();
             res = new Res(0,ccs.getString(1));


 } catch (Exception e) {
	    
	 res = new Res(-1, e.getMessage());
	 e.printStackTrace();
 } finally {
         ConnectionPool.close(c);
 }
 return res;
}
  
  
  public static Res doAction(String un,String pw, TrPay trpay,int actionid, String alias, Connection c) {
	     Res res =null;
	     SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	     //Connection c = null;
	     CallableStatement cs = null;
	     CallableStatement acs = null;
	     CallableStatement ccs = null;
	 //   System.out.println(trpay.getAmount()+ "  "+actionid);

	     try {
	             //c = ConnectionPool.getConnection(un,pw,alias);
	             cs = c.prepareCall("{ call Param.SetParam(?,?) }");
	             acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
	             ccs = c.prepareCall("{ call Param.clearparam() }");
	             ccs.execute();
	             ccs = c.prepareCall("{? = call Param.getparam('ID') }");
	             ccs.registerOutParameter(1, java.sql.Types.INTEGER);
	             
	             if(trpay.getId()!=null){  
	             cs.setString(1, "ID");  cs.setString(2,trpay.getId()+""); cs.execute();
	             }
	              cs.setString(1, "BRANCH");  cs.setString(2,trpay.getBranch()); cs.execute();
	              cs.setString(1, "OPERATION_ID");  cs.setString(2,trpay.getOperation_id()+""); cs.execute();
	              cs.setString(1, "AMOUNT");  cs.setString(2,trpay.getAmount()+""); cs.execute();
	              cs.setString(1, "CARD_ACC");  cs.setString(2,trpay.getCard_acc()); cs.execute();
	              cs.setString(1, "CUR_ACC");  cs.setString(2,trpay.getCur_acc()); cs.execute();
	              //cs.setString(1, "DATE_CREATED");  cs.setString(2,bdf.format(trpay.getDate_created())); cs.execute();
	              cs.setString(1, "PARENT_GROUP_ID");  cs.setString(2,trpay.getParent_group_id()+""); cs.execute();
	              //cs.setString(1, "STATE");  cs.setString(2,trpay.getState()+""); cs.execute();
	              cs.setString(1, "ACCOUNT_NO");  cs.setString(2,trpay.getAccount_no()); cs.execute();
	              cs.setString(1, "CL_NAME");  cs.setString(2,trpay.getCl_name()); cs.execute();
	              cs.setString(1, "BFUSER_ID");  cs.setString(2,trpay.getEmp_id()+""); cs.execute();
	              cs.setString(1, "PAN");  cs.setString(2,trpay.getPan()); cs.execute();
	              cs.setString(1, "DOC_NUM");  cs.setString(2,trpay.getDoc_num()); cs.execute();

	             acs.setInt(1, 144);
	             acs.setInt(2, trpay.getDeal_id());
	             acs.setInt(3,actionid);
	             acs.execute();
	             //c.commit();
	             ccs.execute();
	             res = new Res(0,ccs.getString(1));


	 } catch (Exception e) {
		    
		 res = new Res(-1, e.getMessage());
		 e.printStackTrace();
	 } finally {
	         //ConnectionPool.close(c);
	 }
	 return res;
	}

   public static String doAction(String un,String pw, String branch, String id,int actionid, String alias) {
        String res ="";
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        String cn;
    try {
            c = ConnectionPool.getConnection(un,pw, alias);
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{ call Param.clearparam() }");


            PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_Tr_Pay WHERE branch=? and id=?");
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

    private static List<FilterField> getFilterFields(TrPayFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_id=?",filter.getOperation_id()));
          }
          if(!CheckNull.isEmpty(filter.getAmount())){
                  flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
          }
          if(!CheckNull.isEmpty(filter.getCard_acc())){
                  flfields.add(new FilterField(getCond(flfields)+ "card_acc=?",filter.getCard_acc()));
          }
          if(!CheckNull.isEmpty(filter.getCur_acc())){
                  flfields.add(new FilterField(getCond(flfields)+ "cur_acc=?",filter.getCur_acc()));
          }
          if(!CheckNull.isEmpty(filter.getDate_created())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_created=?",filter.getDate_created()));
          }
          if(!CheckNull.isEmpty(filter.getParent_group_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_group_id=?",filter.getParent_group_id()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }
          if(!CheckNull.isEmpty(filter.getAccount_no())){
                  flfields.add(new FilterField(getCond(flfields)+ "account_no=?",filter.getAccount_no()));
          }
          if(!CheckNull.isEmpty(filter.getCl_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "cl_name=?",filter.getCl_name()));
          }
          if(!CheckNull.isEmpty(filter.getDeal_id())){
              flfields.add(new FilterField(getCond(flfields)+ "deal_id=?",filter.getDeal_id()));
      }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(TrPayFilter filter,String alias )  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM bf_Tr_Pay ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias );
                PreparedStatement ps = c.prepareStatement(sql.toString());

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



    public static List<TrPay> getTrPaysFl(int pageIndex, int pageSize, TrPayFilter filter,String alias )  {

            List<TrPay> list = new ArrayList<TrPay>();
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
                    c = ConnectionPool.getConnection( alias );
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new TrPay(
                                            rs.getLong("id"),
                                            rs.getString("branch"),
                                            rs.getInt("operation_id"),
                                            rs.getBigDecimal("amount"),
                                            rs.getString("card_acc"),
                                            rs.getString("cur_acc"),
                                            rs.getDate("date_created"),
                                            rs.getInt("parent_group_id"),
                                            rs.getInt("state"),
                                            rs.getString("account_no"),
                                            rs.getString("cl_name"),
                                            rs.getInt("emp_id"),
                                            rs.getString("pan"),
                                            rs.getInt("deal_id")
                                            
                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public static TrPay getTrPay(int trpayId,String alias ) {

            TrPay trpay = new TrPay();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias );
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_tr_pay WHERE id=?");
                    ps.setInt(1, trpayId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            trpay = new TrPay();
                            
                            trpay.setId(rs.getLong("id"));
                            trpay.setBranch(rs.getString("branch"));
                            trpay.setOperation_id(rs.getInt("operation_id"));
                            trpay.setAmount(rs.getBigDecimal("amount"));
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
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return trpay;
    }

    public static TrPay create(TrPay trpay,String alias )  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection( alias );
                    ps = c.prepareStatement("SELECT SEQ_trpay.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            trpay.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO trpay (id, branch, operation_id, amount, card_acc, cur_acc, date_created, parent_group_id, state, account_no, cl_name, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setLong(1,trpay.getId());
                    ps.setString(2,trpay.getBranch());
                    ps.setInt(3,trpay.getOperation_id());
                    ps.setBigDecimal(4,trpay.getAmount());
                    ps.setString(5,trpay.getCard_acc());
                    ps.setString(6,trpay.getCur_acc());
                    ps.setDate(7,new java.sql.Date(trpay.getDate_created().getTime()));
                    ps.setInt(8,trpay.getParent_group_id());
                    ps.setInt(9,trpay.getState());
                    ps.setString(10,trpay.getAccount_no());
                    ps.setString(11,trpay.getCl_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return trpay;
    }

    public static void update(TrPay trpay,String alias )  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias );
                    PreparedStatement ps = c.prepareStatement("UPDATE trpay SET id=?, branch=?, operation_id=?, amount=?, card_acc=?, cur_acc=?, date_created=?, parent_group_id=?, state=?, account_no=?, cl_name=?,  WHERE trpay_id=?");
                    
                    ps.setLong(1,trpay.getId());
                    ps.setString(2,trpay.getBranch());
                    ps.setInt(3,trpay.getOperation_id());
                    ps.setBigDecimal(4,trpay.getAmount());
                    ps.setString(5,trpay.getCard_acc());
                    ps.setString(6,trpay.getCur_acc());
                    ps.setDate(7,new java.sql.Date( trpay.getDate_created().getTime()));
                    ps.setInt(8,trpay.getParent_group_id());
                    ps.setInt(9,trpay.getState());
                    ps.setString(10,trpay.getAccount_no());
                    ps.setString(11,trpay.getCl_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                   
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(TrPay trpay,String alias )  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias );
                    PreparedStatement ps = c.prepareStatement("DELETE FROM bf_tr_pay WHERE id=?");
                    ps.setLong(1, trpay.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    
    
    public static List<Action> getActions(int state_begin, int userid, String user_branch, int deal_id, String alias) {

        List<Action> list = new ArrayList<Action>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias );
                /*System.out.println("select a.* from BF_TR_TRANS t, bf_actions a "+
                		"where a.mid=13 and a.id=t.action_id and  t.state_begin="+state_begin+" and a.deal_id = t.deal_id and t.deal_id = "+deal_id+" "+
                		"and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="+userid+" and r.actionid=a.id and r.deal_id = t.deal_id)");
                	*/	                          
                PreparedStatement ps = c.prepareStatement("select a.* from BF_TR_TRANS t, bf_actions a "+
"where a.mid=13 and a.id=t.action_id and  t.state_begin=? and a.deal_id = t.deal_id and t.deal_id = ?"+
"and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid=? and u.branch=? and r.actionid=a.id and r.deal_id = t.deal_id)");
                ps.setInt(1, state_begin);
                ps.setInt(2, deal_id);
                ps.setInt(3, userid);
                ps.setString(4, user_branch);
                ResultSet rs = ps.executeQuery();
              
                while (rs.next()) {
                        list.add(new Action(
                                rs.getInt("id"),
                                rs.getInt("mid"),
                                rs.getString("name"),
                                rs.getString("icon")));

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
                c = ConnectionPool.getConnection( alias );
                PreparedStatement ps = c.prepareStatement("select st.id state_id, st.deal_id, st.name title from bf_tr_state st where st.flag_client_view = 1");        
                //ps.setInt(1, deal_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new State(
                            rs.getInt("state_id"),
                            rs.getString("title"),
                            rs.getInt("deal_id")
                        	));
                }
        } catch (SQLException e) {
                LtLogger.getLogger().error(CheckNull.getPstr(e));
                
        } finally {
                ConnectionPool.close(c);
        }
        return list;

    }
    
    
    
    public static boolean isEndState( int actionid, int deal_id, String alias ) {

        boolean res = false;
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias );
                PreparedStatement ps = c.prepareStatement("select s.flag_createdoct "+
"from bf_tr_state s ,bf_tr_trans t "+
"where t.state_end=s.id and t.action_id=? and s.deal_id=?");
                
                ps.setInt(1, actionid);
                ps.setInt(2, deal_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    res = rs.getInt("flag_createdoct")==1;

                }
        } catch (SQLException e) {
                LtLogger.getLogger().error(CheckNull.getPstr(e));
                
        } finally {
                ConnectionPool.close(c);
        }
        return res;

    }
    
    
    public static boolean isEndState( Long pay_id, String alias, Connection c)
    {
	    boolean res = false;
	   
	    try {
		    //c = ConnectionPool.getConnection( alias );
	        PreparedStatement ps = c.prepareStatement("select st.flag_createdoct res "+
	        								"from bf_tr_pay t,"+
	        									"bf_tr_state st "+
	        									"where t.id = ? "+
	        									"and st.deal_id = t.deal_id "+
	        									"and st.id = t.state");
	        ps.setLong(1, pay_id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
                res = rs.getInt("res")==1;
            }
		    } catch (SQLException e) {
		        LtLogger.getLogger().error(CheckNull.getPstr(e));
		        
		} finally {
		       // ConnectionPool.close(c);
		}
		return res;
    }
    
    public static List<TrPayDocs> getTrPayDocs( long pay_id,String alias )  {

        List<TrPayDocs> list = new ArrayList<TrPayDocs>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias );
                
                PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_TR_PAYDOCS where PAY_ID=?");
                ps.setLong(1, pay_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new TrPayDocs(
                                        rs.getLong("id"),
                                        rs.getInt("pay_id"),
                                        rs.getString("branch"),
                                        rs.getDate("d_date"),
                                        rs.getString("bank_cl"),
                                        rs.getString("acc_cl"),
                                        rs.getString("name_cl"),
                                        rs.getString("bank_co"),
                                        rs.getString("acc_co"),
                                        rs.getString("name_co"),
                                        rs.getInt("summa"),
                                        rs.getString("purpose"),
                                        rs.getString("type_doc"),
                                        rs.getString("pdc"),
                                        rs.getInt("parent_group_id"),
                                        rs.getInt("parent_id"),
                                        rs.getString("cash_code"),
                                        rs.getDouble("id_transh_purp"),
                                        rs.getString("schema_name"),
                                        rs.getInt("ord"),
                                        rs.getString("g_branch"),
                                        rs.getLong("g_docid")));
                }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
   
    public static List<Paydoc> getPaydocs(long pay_id,String alias)  {

        List<Paydoc> list = new ArrayList<Paydoc>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                
                PreparedStatement ps = c.prepareStatement("Select T.ORD, T.BRANCH, T.D_DATE, T.BANK_CL, T.ACC_CL, T.NAME_CL, T.BANK_CO, T.ACC_CO, T.NAME_CO, T.PURPOSE, T.SUMMA, (T.TYPE_DOC || ' - ' || TD.NAIM_DOC) AS TYPEDOC, T.PDC, T.CASH_CODE, T.ID_TRANSH_PURP, T.G_BRANCH, T.G_DOCID From   BF_TR_PAYDOCS T, V_TYPEDOC TD Where  T.PAY_ID = ? and TD.KOD_DOC(+) = T.TYPE_DOC");
                //System.out.println("Select T.ORD, T.BRANCH, T.D_DATE, T.BANK_CL, T.ACC_CL, T.NAME_CL, T.BANK_CO, T.ACC_CO, T.NAME_CO, T.PURPOSE, T.SUMMA, (T.TYPE_DOC || ' - ' || TD.NAIM_DOC) AS TYPEDOC, T.PDC, T.CASH_CODE, T.ID_TRANSH_PURP, T.G_BRANCH, T.G_DOCID From   BF_TR_PAYDOCS T, V_TYPEDOC TD Where  T.PAY_ID = '"+pay_id+"' and TD.KOD_DOC(+) = T.TYPE_DOC");
                ps.setLong(1, pay_id);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                        list.add(new Paydoc(
                            rs.getInt("ORD"),
                            rs.getString("BRANCH"),
                            rs.getDate("D_DATE"),   
                            rs.getString("BANK_CL"),
                            rs.getString("ACC_CL"),
                            rs.getString("NAME_CL"),
                            rs.getString("BANK_CO"),
                            rs.getString("ACC_CO"),
                            rs.getString("NAME_CO"),
                            rs.getString("PURPOSE"),
                            rs.getLong("SUMMA")/100,
                            rs.getString("TYPEDOC"),
                            rs.getString("PDC"),
                            rs.getString("CASH_CODE"),
                            rs.getInt("ID_TRANSH_PURP"),
                            rs.getString("G_BRANCH"),
                            rs.getInt("G_DOCID")
                        ));
                }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;
    }
    
    
    public static int get_tieto_operation_code(int operation_id, String alias)
    {
    	int res = 0;
    	Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                
                PreparedStatement ps = c.prepareStatement("select t.tieto_type res from bf_tieto_type_operations t where t.operation_id = ?");
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
    
    public static String get_operation_desc(int operation_id, String alias)
    {
            String res = null;
            Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("select t.descripption res from bf_tr_operations t where t.id=?");
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
    
    public static String getOperation_desc(int operation_id ,String alias )  {

        String res = null;
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_TR_OPERATIONS where ID=?");
                ps.setLong(1, operation_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        res = rs.getString("descripption");
                }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return res;

}

    public static String getDeal_desc(int deal_id ,String alias )  {

        String res = null;
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("SELECT * FROM ss_deal where ID=? and group_id='144'");
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
    
}
