package com.is.openwayutils.accounting;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.FilterField;
import com.is.utils.Res;

public class AccountingService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM v_BF_SETS_ACCOUNTING ";


    public List<Accounting> getAccounting(String alias )  {

            List<Accounting> list = new ArrayList<Accounting>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM v_BF_SETS_ACCOUNTING");
                    while (rs.next()) {
                            list.add(new Accounting(
                                            rs.getString("branch"),
                                            rs.getLong("id"),
                                            rs.getLong("operation_id"),
                                            rs.getLong("sets_id"),
                                            rs.getString("doc_type"),
                                            rs.getString("acc_dt"),
                                            rs.getString("acc_ct"),
                                            rs.getString("acc_dt_name"),
                                            rs.getString("acc_ct_name"),
                                            rs.getString("purpose"),
                                            rs.getString("purpose_code"),
                                            rs.getString("cash_code"),
                                            rs.getString("cash_sub_code"),
                                            rs.getInt("ord"),
                                            rs.getString("mfo_ct"),
                                            rs.getString("inn_ct")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


/*
  public static String doAction(String un,String pw, Accounting accounting,int actionid) {
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
             
              cs.setString(1, "BRANCH");  cs.setString(2,accounting.getBranch()); cs.execute();
              cs.setString(1, "ID");  cs.setString(2,accounting.getId()); cs.execute();
              cs.setString(1, "OPERATION_ID");  cs.setString(2,accounting.getOperation_id()); cs.execute();
              cs.setString(1, "SETS_ID");  cs.setString(2,accounting.getSets_id()); cs.execute();
              cs.setString(1, "DOC_TYPE");  cs.setString(2,accounting.getDoc_type()); cs.execute();
              cs.setString(1, "ACC_DT");  cs.setString(2,accounting.getAcc_dt()); cs.execute();
              cs.setString(1, "ACC_CT");  cs.setString(2,accounting.getAcc_ct()); cs.execute();
              cs.setString(1, "ACC_DT_NAME");  cs.setString(2,accounting.getAcc_dt_name()); cs.execute();
              cs.setString(1, "ACC_CT_NAME");  cs.setString(2,accounting.getAcc_ct_name()); cs.execute();
              cs.setString(1, "PURPOSE");  cs.setString(2,accounting.getPurpose()); cs.execute();
              cs.setString(1, "PURPOSE_CODE");  cs.setString(2,accounting.getPurpose_code()); cs.execute();
              cs.setString(1, "CASH_CODE");  cs.setString(2,accounting.getCash_code()); cs.execute();
              cs.setString(1, "CASH_SUB_CODE");  cs.setString(2,accounting.getCash_sub_code()); cs.execute();
              cs.setString(1, "ORD");  cs.setString(2,accounting.getOrd()); cs.execute();
              cs.setString(1, "MFO_CT");  cs.setString(2,accounting.getMfo_ct()); cs.execute();
              cs.setString(1, "INN_CT");  cs.setString(2,accounting.getInn_ct()); cs.execute();

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


            PreparedStatement ps = c.prepareStatement("SELECT * FROM Accounting WHERE branch=? and id=?");
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

    private static List<FilterField> getFilterFields(AccountingFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_id=?",filter.getOperation_id()));
          }
          if(!CheckNull.isEmpty(filter.getSets_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "sets_id=?",filter.getSets_id()));
          }
          if(!CheckNull.isEmpty(filter.getDoc_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "doc_type=?",filter.getDoc_type()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_dt())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_dt=?",filter.getAcc_dt()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_ct())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_ct=?",filter.getAcc_ct()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_dt_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_dt_name=?",filter.getAcc_dt_name()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_ct_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_ct_name=?",filter.getAcc_ct_name()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose=?",filter.getPurpose()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose_code=?",filter.getPurpose_code()));
          }
          if(!CheckNull.isEmpty(filter.getCash_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "cash_code=?",filter.getCash_code()));
          }
          if(!CheckNull.isEmpty(filter.getCash_sub_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "cash_sub_code=?",filter.getCash_sub_code()));
          }
          if(!CheckNull.isEmpty(filter.getOrd())){
                  flfields.add(new FilterField(getCond(flfields)+ "ord=?",filter.getOrd()));
          }
          if(!CheckNull.isEmpty(filter.getMfo_ct())){
                  flfields.add(new FilterField(getCond(flfields)+ "mfo_ct=?",filter.getMfo_ct()));
          }
          if(!CheckNull.isEmpty(filter.getInn_ct())){
                  flfields.add(new FilterField(getCond(flfields)+ "inn_ct=?",filter.getInn_ct()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(AccountingFilter filter, String alias )  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM v_BF_SETS_ACCOUNTING ");
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



    public static List<Accounting> getAccountingsFl(int pageIndex, int pageSize, AccountingFilter filter, String alias )  {

            List<Accounting> list = new ArrayList<Accounting>();
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
                            list.add(new Accounting(
                                            rs.getString("branch"),
                                            rs.getLong("id"),
                                            rs.getLong("operation_id"),
                                            rs.getLong("sets_id"),
                                            rs.getString("doc_type"),
                                            rs.getString("acc_dt"),
                                            rs.getString("acc_ct"),
                                            rs.getString("acc_dt_name"),
                                            rs.getString("acc_ct_name"),
                                            rs.getString("purpose"),
                                            rs.getString("purpose_code"),
                                            rs.getString("cash_code"),
                                            rs.getString("cash_sub_code"),
                                            rs.getInt("ord"),
                                            rs.getString("mfo_ct"),
                                            rs.getString("inn_ct")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Accounting getAccounting(int accountingId, String alias ) {

            Accounting accounting = new Accounting();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM v_BF_SETS_ACCOUNTING WHERE id=?");
                    ps.setInt(1, accountingId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            accounting = new Accounting();
                            
                            accounting.setBranch(rs.getString("branch"));
                            accounting.setId(rs.getLong("id"));
                            accounting.setOperation_id(rs.getLong("operation_id"));
                            accounting.setSets_id(rs.getLong("sets_id"));
                            accounting.setDoc_type(rs.getString("doc_type"));
                            accounting.setAcc_dt(rs.getString("acc_dt"));
                            accounting.setAcc_ct(rs.getString("acc_ct"));
                            accounting.setAcc_dt_name(rs.getString("acc_dt_name"));
                            accounting.setAcc_ct_name(rs.getString("acc_ct_name"));
                            accounting.setPurpose(rs.getString("purpose"));
                            accounting.setPurpose_code(rs.getString("purpose_code"));
                            accounting.setCash_code(rs.getString("cash_code"));
                            accounting.setCash_sub_code(rs.getString("cash_sub_code"));
                            accounting.setOrd(rs.getInt("ord"));
                            accounting.setMfo_ct(rs.getString("mfo_ct"));
                            accounting.setInn_ct(rs.getString("inn_ct"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return accounting;
    }

    public static Accounting create(Accounting accounting, String alias )  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_accounting.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            accounting.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO accounting (branch, id, operation_id, sets_id, doc_type, acc_dt, acc_ct, acc_dt_name, acc_ct_name, purpose, purpose_code, cash_code, cash_sub_code, ord, mfo_ct, inn_ct, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,accounting.getBranch());
                    ps.setLong(2,accounting.getId());
                    ps.setLong(3,accounting.getOperation_id());
                    ps.setLong(4,accounting.getSets_id());
                    ps.setString(5,accounting.getDoc_type());
                    ps.setString(6,accounting.getAcc_dt());
                    ps.setString(7,accounting.getAcc_ct());
                    ps.setString(8,accounting.getAcc_dt_name());
                    ps.setString(9,accounting.getAcc_ct_name());
                    ps.setString(10,accounting.getPurpose());
                    ps.setString(11,accounting.getPurpose_code());
                    ps.setString(12,accounting.getCash_code());
                    ps.setString(13,accounting.getCash_sub_code());
                    ps.setDouble(14,accounting.getOrd());
                    ps.setString(15,accounting.getMfo_ct());
                    ps.setString(16,accounting.getInn_ct());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return accounting;
    }

    public static void update(Accounting accounting, String alias )  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE accounting SET branch=?, id=?, operation_id=?, sets_id=?, doc_type=?, acc_dt=?, acc_ct=?, acc_dt_name=?, acc_ct_name=?, purpose=?, purpose_code=?, cash_code=?, cash_sub_code=?, ord=?, mfo_ct=?, inn_ct=?,  WHERE accounting_id=?");
                    
                    ps.setString(1,accounting.getBranch());
                    ps.setLong(2,accounting.getId());
                    ps.setLong(3,accounting.getOperation_id());
                    ps.setLong(4,accounting.getSets_id());
                    ps.setString(5,accounting.getDoc_type());
                    ps.setString(6,accounting.getAcc_dt());
                    ps.setString(7,accounting.getAcc_ct());
                    ps.setString(8,accounting.getAcc_dt_name());
                    ps.setString(9,accounting.getAcc_ct_name());
                    ps.setString(10,accounting.getPurpose());
                    ps.setString(11,accounting.getPurpose_code());
                    ps.setString(12,accounting.getCash_code());
                    ps.setString(13,accounting.getCash_sub_code());
                    ps.setDouble(14,accounting.getOrd());
                    ps.setString(15,accounting.getMfo_ct());
                    ps.setString(16,accounting.getInn_ct());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Accounting accounting, String alias )  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM accounting WHERE accounting_id=?");
                    ps.setLong(1, accounting.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }


}

