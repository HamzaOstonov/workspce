package com.is.tracc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.account.Account;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;


public class TrAccService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM BF_TR_ACC ";


    public List<TrAcc> getTrAcc(String alias)  {

            List<TrAcc> list = new ArrayList<TrAcc>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TrAcc");
                    while (rs.next()) {
                            list.add(new TrAcc(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("acc_template_id"),
                                            rs.getString("acc_mfo"),
                                            rs.getString("account"),
                                            rs.getString("acc_name")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


/*
  public static String doAction(String un,String pw, TrAcc tracc,int actionid) {
     Res res =null;
     SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
     Connection c = null;
     CallableStatement cs = null;
     CallableStatement acs = null;
     CallableStatement ccs = null;

     try {
             c = ConnectionPool.getConnection(un,pw);
             cs = c.prepareCall("{ call Param.SetParam(?,?) }");
             acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
             ccs = c.prepareCall("{ call Param.clearparam() }");
             ccs.execute();
             ccs = c.prepareCall("{? = call Param.getparam('ID') }");
             
              cs.setString(1, "ID");  cs.setString(2,tracc.getId()); cs.execute();
              cs.setString(1, "BRANCH");  cs.setString(2,tracc.getBranch()); cs.execute();
              cs.setString(1, "ACC_TEMPLATE_ID");  cs.setString(2,tracc.getAcc_template_id()); cs.execute();
              cs.setString(1, "ACC_MFO");  cs.setString(2,tracc.getAcc_mfo()); cs.execute();
              cs.setString(1, "ACCOUNT");  cs.setString(2,tracc.getAccount()); cs.execute();
              cs.setString(1, "ACC_NAME");  cs.setString(2,tracc.getAcc_name()); cs.execute();

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
            c = ConnectionPool.getConnection(un,pw);
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{ call Param.clearparam() }");


            PreparedStatement ps = c.prepareStatement("SELECT * FROM TrAcc WHERE branch=? and id=?");
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

*/
    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            }else
            return " where ";
    }

    private static List<FilterField> getFilterFields(TrAccFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_template_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_template_id=?",filter.getAcc_template_id()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_mfo())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_mfo=?",filter.getAcc_mfo()));
          }
          if(!CheckNull.isEmpty(filter.getAccount())){
                  flfields.add(new FilterField(getCond(flfields)+ "account=?",filter.getAccount()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_name=?",filter.getAcc_name()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(TrAccFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_TR_ACC ");
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
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<TrAcc> getTrAccsFl(int pageIndex, int pageSize, TrAccFilter filter, String alias)  {

            List<TrAcc> list = new ArrayList<TrAcc>();
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
                            list.add(new TrAcc(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("acc_template_id"),
                                            rs.getString("acc_mfo"),
                                            rs.getString("account"),
                                            rs.getString("acc_name")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public TrAcc getTrAcc(int traccId,String alias) {

            TrAcc tracc = new TrAcc();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
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
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return tracc;
    }

    public static TrAcc create(TrAcc tracc, String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection( alias);
                    ps = c.prepareStatement("SELECT SEQ_BF_TR_ACC.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            tracc.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO BF_TR_ACC (id, branch, acc_template_id, acc_mfo, account, acc_name, ) VALUES (?,?,?,?,?,?,)");
                    
                    ps.setLong(1,tracc.getId());
                    ps.setString(2,tracc.getBranch());
                    ps.setLong(3,tracc.getAcc_template_id());
                    ps.setString(4,tracc.getAcc_mfo());
                    ps.setString(5,tracc.getAccount());
                    ps.setString(6,tracc.getAcc_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return tracc;
    }

    public static void update(TrAcc tracc,String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE BF_TR_ACC SET  branch=?, acc_template_id=?, acc_mfo=?, account=?, acc_name=?  WHERE id=?");
                    
                    
                    ps.setString(1,tracc.getBranch());
                    ps.setInt(2,tracc.getAcc_template_id());
                    ps.setString(3,tracc.getAcc_mfo());
                    ps.setString(4,tracc.getAccount());
                    ps.setString(5,tracc.getAcc_name());
                    ps.setInt(6,tracc.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(TrAcc tracc,String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM BF_TR_ACC WHERE id=?");
                    ps.setInt(1, tracc.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    public static List<Account> getAccount( TrAcc tracc,String alias )  {

        List<Account> list = new ArrayList<Account>();
        Connection c = null;
        ResultSet rs = null;
        String whr = "()";
        String nm ="";

        try {
                c = ConnectionPool.getConnection( alias);
                PreparedStatement ps = c.prepareStatement("select * FROM BF_TR_ACC_TEMPLATE WHERE id=?");
                ps.setInt(1,tracc.getAcc_template_id());
                rs = ps.executeQuery();
                if (rs.next()) {
                	whr = rs.getString("acc_mask");
                	nm = rs.getString("acc_name");
                }
                
                Statement s = c.createStatement();
                rs = s.executeQuery("SELECT * FROM Account where id like '"+whr+"' and state=2 ");
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
                if(whr.endsWith("ACC") ) {
                	 Account tacc =  new Account();
                	 tacc.setId(whr);
                	 tacc.setName(nm);
                	 tacc.setBranch(tracc.getBranch());
                	 list.add(tacc);
                }
                
               
                 
                 
                
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
}
