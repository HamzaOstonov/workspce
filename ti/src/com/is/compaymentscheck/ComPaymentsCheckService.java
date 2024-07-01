package com.is.compaymentscheck;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class ComPaymentsCheckService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM BF_COM_PAYMENTS_CHECK ";


    public List<ComPaymentsCheck> getComPaymentsCheck(String alias)  {

            List<ComPaymentsCheck> list = new ArrayList<ComPaymentsCheck>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM BF_COM_PAYMENTS_CHECK");
                    while (rs.next()) {
                            list.add(new ComPaymentsCheck(
                                            rs.getLong("id"),
                                            rs.getInt("provider_id"),
                                            rs.getDate("from_time"),
                                            rs.getDate("to_time"),
                                            rs.getDate("exec_time"),
                                            rs.getInt("trans_count"),
                                            rs.getLong("amount"),
                                            rs.getInt("state")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


/*
  public static String doAction(String un,String pw, ComPaymentsCheck compaymentscheck,int actionid) {
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
             
              cs.setString(1, "ID");  cs.setString(2,compaymentscheck.getId()); cs.execute();
              cs.setString(1, "PROVIDER_ID");  cs.setString(2,compaymentscheck.getProvider_id()); cs.execute();
              cs.setString(1, "FROM_TIME");  cs.setString(2,compaymentscheck.getFrom_time()); cs.execute();
              cs.setString(1, "TO_TIME");  cs.setString(2,compaymentscheck.getTo_time()); cs.execute();
              cs.setString(1, "EXEC_TIME");  cs.setString(2,compaymentscheck.getExec_time()); cs.execute();
              cs.setString(1, "TRANS_COUNT");  cs.setString(2,compaymentscheck.getTrans_count()); cs.execute();
              cs.setString(1, "AMOUNT");  cs.setString(2,compaymentscheck.getAmount()); cs.execute();
              cs.setString(1, "STATE");  cs.setString(2,compaymentscheck.getState()); cs.execute();

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


            PreparedStatement ps = c.prepareStatement("SELECT * FROM ComPaymentsCheck WHERE branch=? and id=?");
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

    private static List<FilterField> getFilterFields(ComPaymentsCheckFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getProvider_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "provider_id=?",filter.getProvider_id()));
          }
          if(!CheckNull.isEmpty(filter.getFrom_time())){
                  flfields.add(new FilterField(getCond(flfields)+ "from_time=?",filter.getFrom_time()));
          }
          if(!CheckNull.isEmpty(filter.getTo_time())){
                  flfields.add(new FilterField(getCond(flfields)+ "to_time=?",filter.getTo_time()));
          }
          if(!CheckNull.isEmpty(filter.getExec_time())){
                  flfields.add(new FilterField(getCond(flfields)+ "exec_time=?",filter.getExec_time()));
          }
          if(!CheckNull.isEmpty(filter.getTrans_count())){
                  flfields.add(new FilterField(getCond(flfields)+ "trans_count=?",filter.getTrans_count()));
          }
          if(!CheckNull.isEmpty(filter.getAmount())){
                  flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ComPaymentsCheckFilter filter,String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_COM_PAYMENTS_CHECK ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection( alias);
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



    public static List<ComPaymentsCheck> getComPaymentsChecksFl(int pageIndex, int pageSize, ComPaymentsCheckFilter filter, String alias)  {

            List<ComPaymentsCheck> list = new ArrayList<ComPaymentsCheck>();
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
                    c = ConnectionPool.getConnection( alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new ComPaymentsCheck(
                                            rs.getLong("id"),
                                            rs.getInt("provider_id"),
                                            rs.getDate("from_time"),
                                            rs.getDate("to_time"),
                                            rs.getDate("exec_time"),
                                            rs.getInt("trans_count"),
                                            rs.getLong("amount"),
                                            rs.getInt("state")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public ComPaymentsCheck getComPaymentsCheck(long compaymentscheckId, String alias) {

            ComPaymentsCheck compaymentscheck = new ComPaymentsCheck();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_COM_PAYMENTS_CHECK WHERE id=?");
                    ps.setLong(1, compaymentscheckId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            compaymentscheck = new ComPaymentsCheck();
                            
                            compaymentscheck.setId(rs.getLong("id"));
                            compaymentscheck.setProvider_id(rs.getInt("provider_id"));
                            compaymentscheck.setFrom_time(rs.getDate("from_time"));
                            compaymentscheck.setTo_time(rs.getDate("to_time"));
                            compaymentscheck.setExec_time(rs.getDate("exec_time"));
                            compaymentscheck.setTrans_count(rs.getInt("trans_count"));
                            compaymentscheck.setAmount(rs.getLong("amount"));
                            compaymentscheck.setState(rs.getInt("state"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return compaymentscheck;
    }

    public static ComPaymentsCheck create(ComPaymentsCheck compaymentscheck,String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection( alias);
                    ps = c.prepareStatement("SELECT BF_COM_PAYMENTS_CHECK_SEQ.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            compaymentscheck.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO BF_COM_PAYMENTS_CHECK (id, provider_id, from_time, to_time, exec_time, trans_count, amount, state ) VALUES (?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,compaymentscheck.getId());
                    ps.setInt(2,compaymentscheck.getProvider_id());
                    ps.setTimestamp(3,new java.sql.Timestamp(compaymentscheck.getFrom_time().getTime()));
                    ps.setTimestamp(4,new java.sql.Timestamp(compaymentscheck.getTo_time().getTime()));
                    ps.setTimestamp(5,new java.sql.Timestamp(compaymentscheck.getExec_time().getTime()));
                    ps.setInt(6,compaymentscheck.getTrans_count());
                    ps.setLong(7,compaymentscheck.getAmount());
                    ps.setInt(8,compaymentscheck.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return compaymentscheck;
    }

    public static void update(ComPaymentsCheck compaymentscheck,String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE BF_COM_PAYMENTS_CHECK SET  provider_id=?, from_time=?, to_time=?, exec_time=?, trans_count=?, amount=?, state=?  WHERE id=?");
                    ps.setInt(1,compaymentscheck.getProvider_id());
                    ps.setDate(2,new java.sql.Date(compaymentscheck.getFrom_time().getTime()));
                    ps.setDate(3,new java.sql.Date(compaymentscheck.getTo_time().getTime()));
                    ps.setDate(4,new java.sql.Date(compaymentscheck.getExec_time().getTime()));
                    ps.setInt(5,compaymentscheck.getTrans_count());
                    ps.setLong(6,compaymentscheck.getAmount());
                    ps.setInt(7,compaymentscheck.getState());
                    ps.setLong(8,compaymentscheck.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ComPaymentsCheck compaymentscheck,String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM compaymentscheck WHERE BF_COM_PAYMENTS_CHECK=?");
                    ps.setLong(1, compaymentscheck.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
}
