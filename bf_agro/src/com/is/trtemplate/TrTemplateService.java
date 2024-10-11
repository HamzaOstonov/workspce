package com.is.trtemplate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class TrTemplateService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by ord";
    private static String msql ="SELECT * FROM BF_TR_TEMPLATE ";


    public List<TrTemplate> getTrTemplate(String alias)  {

            List<TrTemplate> list = new ArrayList<TrTemplate>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM BF_TR_TEMPLATE order by ord");
                    while (rs.next()) {
                            list.add(new TrTemplate(
                                            rs.getInt("id"),
                                            rs.getInt("operation_id"),
                                            rs.getInt("acc_dt"),
                                            rs.getInt("acc_ct"),
                                            rs.getString("currency"),
                                            rs.getString("doc_type"),
                                            rs.getString("cash_code"),
                                            rs.getString("purpose"),
                                            rs.getString("purpose_code"),
                                            rs.getInt("ord"),
                                            rs.getInt("id_transh_purp"),
                                            rs.getInt("pay_type"),
                                            rs.getInt("trans_type"),
                                            rs.getDouble("perc_for_tr")));
                    }
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


/*
  public static String doAction(String un,String pw, TrTemplate trtemplate,int actionid) {
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
             
              cs.setString(1, "ID");  cs.setString(2,trtemplate.getId()); cs.execute();
              cs.setString(1, "OPERATION_ID");  cs.setString(2,trtemplate.getOperation_id()); cs.execute();
              cs.setString(1, "ACC_DT");  cs.setString(2,trtemplate.getAcc_dt()); cs.execute();
              cs.setString(1, "ACC_CT");  cs.setString(2,trtemplate.getAcc_ct()); cs.execute();
              cs.setString(1, "CURRENCY");  cs.setString(2,trtemplate.getCurrency()); cs.execute();
              cs.setString(1, "DOC_TYPE");  cs.setString(2,trtemplate.getDoc_type()); cs.execute();
              cs.setString(1, "CASH_CODE");  cs.setString(2,trtemplate.getCash_code()); cs.execute();
              cs.setString(1, "PURPOSE");  cs.setString(2,trtemplate.getPurpose()); cs.execute();
              cs.setString(1, "PURPOSE_CODE");  cs.setString(2,trtemplate.getPurpose_code()); cs.execute();
              cs.setString(1, "ORD");  cs.setString(2,trtemplate.getOrd()); cs.execute();

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


            PreparedStatement ps = c.prepareStatement("SELECT * FROM TrTemplate WHERE branch=? and id=?");
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
           // com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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

    private static List<FilterField> getFilterFields(TrTemplateFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_id=?",filter.getOperation_id()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_dt())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_dt=?",filter.getAcc_dt()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_ct())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_ct=?",filter.getAcc_ct()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getDoc_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "doc_type=?",filter.getDoc_type()));
          }
          if(!CheckNull.isEmpty(filter.getCash_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "cash_code=?",filter.getCash_code()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose=?",filter.getPurpose()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose_code=?",filter.getPurpose_code()));
          }
          if(!CheckNull.isEmpty(filter.getOrd())){
                  flfields.add(new FilterField(getCond(flfields)+ "ord=?",filter.getOrd()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(TrTemplateFilter filter,String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_TR_TEMPLATE ");
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
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<TrTemplate> getTrTemplatesFl(int pageIndex, int pageSize, TrTemplateFilter filter,String alias)  {

            List<TrTemplate> list = new ArrayList<TrTemplate>();
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
                            list.add(new TrTemplate(
                                            rs.getInt("id"),
                                            rs.getInt("operation_id"),
                                            rs.getInt("acc_dt"),
                                            rs.getInt("acc_ct"),
                                            rs.getString("currency"),
                                            rs.getString("doc_type"),
                                            rs.getString("cash_code"),
                                            rs.getString("purpose"),
                                            rs.getString("purpose_code"),
                                            rs.getInt("ord"),
                                            rs.getInt("id_transh_purp"),
                                            rs.getInt("pay_type"),
                                            rs.getInt("trans_type"),
                                            rs.getDouble("perc_for_tr"),
                                            rs.getString("pdc")));
                    }
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public TrTemplate getTrTemplate(int trtemplateId,String alias) {

            TrTemplate trtemplate = new TrTemplate();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_TR_TEMPLATE WHERE trtemplate_id=?");
                    ps.setInt(1, trtemplateId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            trtemplate = new TrTemplate();
                            
                            trtemplate.setId(rs.getInt("id"));
                            trtemplate.setOperation_id(rs.getInt("operation_id"));
                            trtemplate.setAcc_dt(rs.getInt("acc_dt"));
                            trtemplate.setAcc_ct(rs.getInt("acc_ct"));
                            trtemplate.setCurrency(rs.getString("currency"));
                            trtemplate.setDoc_type(rs.getString("doc_type"));
                            trtemplate.setCash_code(rs.getString("cash_code"));
                            trtemplate.setPurpose(rs.getString("purpose"));
                            trtemplate.setPurpose_code(rs.getString("purpose_code"));
                            trtemplate.setOrd(rs.getInt("ord"));
                            trtemplate.setPay_type(rs.getInt("pay_type"));
                            trtemplate.setTrans_type(rs.getInt("trans_type"));
                            trtemplate.setPerc_for_tr(rs.getDouble("perc_for_tr"));
                    }
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
            return trtemplate;
    }

    //public static TrTemplate
    public static Res create(TrTemplate trtemplate,String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            Res res1 = new Res(-1, "Îøèáêà");
            try {
                    c = ConnectionPool.getConnection( alias);
                    ps = c.prepareStatement("SELECT SEQ_BF_TR_TEMPLATE.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            trtemplate.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO BF_TR_TEMPLATE (id, operation_id, acc_dt, acc_ct, currency, doc_type, cash_code, purpose, purpose_code, ord,id_transh_purp,pay_type,trans_type,perc_for_tr,pdc ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,trtemplate.getId());
                    ps.setInt(2,trtemplate.getOperation_id());
                    ps.setInt(3,trtemplate.getAcc_dt());
                    ps.setInt(4,trtemplate.getAcc_ct());
                    ps.setString(5,trtemplate.getCurrency());
                    ps.setString(6,trtemplate.getDoc_type());
                    ps.setString(7, (trtemplate.getCash_code().equals("0"))?null:trtemplate.getCash_code());
                    ps.setString(8,trtemplate.getPurpose());
                    ps.setString(9, (trtemplate.getPurpose_code().equals("0"))?null:trtemplate.getPurpose_code());
                    ps.setInt(10,trtemplate.getOrd());
                    
                    if (trtemplate.getId_transh_purp()==123456)
                    	ps.setNull(11, java.sql.Types.INTEGER);
                    else
                    	ps.setInt(11, trtemplate.getId_transh_purp());
                    //ps.setInt(11, (trtemplate.getId_transh_purp()==0?(Integer)null:trtemplate.getId_transh_purp()));
                    ps.setInt(12, trtemplate.getPay_type());
                    ps.setInt(13, trtemplate.getTrans_type());
                    ps.setDouble(14, trtemplate.getPerc_for_tr());
                    ps.setString(15,trtemplate.getPdc());
                    ps.executeUpdate();
                    c.commit();
                    res1.setCode(0);
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                    if(e.getMessage().indexOf("XUK_TR_SETS_TEMLATE_2")>0)
        			{
        				String A = Labels.getLabel("trtemplate.wrong_ord_num");
        				res1.setName(A.replaceAll("#1", Integer.toString(trtemplate.getOrd())));
        			}
                    res1.setName(e.getMessage());
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }
            //return trtemplate;
            return res1;
    }

    public static Res update(TrTemplate trtemplate, String alias)  {

            Connection c = null;
            
            Res res1 = new Res(-1, "Îøèáêà");

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE BF_TR_TEMPLATE SET  operation_id=?, acc_dt=?, acc_ct=?, currency=?, doc_type=?, cash_code=?, purpose=?, purpose_code=?, ord=?,id_transh_purp=?,pay_type=?,trans_type=?,perc_for_tr=?,pdc =?  WHERE id=?");
                    
                    
                    ps.setLong(1,trtemplate.getOperation_id());
                    ps.setLong(2,trtemplate.getAcc_dt());
                    ps.setLong(3,trtemplate.getAcc_ct());
                    ps.setString(4,trtemplate.getCurrency());
                    ps.setString(5,trtemplate.getDoc_type());
                    ps.setString(6,(trtemplate.getCash_code().equals("0"))?null:trtemplate.getCash_code());
                    ps.setString(7,trtemplate.getPurpose());
                    ps.setString(8,(trtemplate.getPurpose_code().equals("0"))?null:trtemplate.getPurpose_code());
                    ps.setInt(9,trtemplate.getOrd());
                    
                    if (trtemplate.getId_transh_purp()==123456)
                    	ps.setNull(10, java.sql.Types.INTEGER);
                    else
                    	ps.setInt(10, trtemplate.getId_transh_purp());
                    
                    ps.setInt(11, trtemplate.getPay_type());
                    ps.setInt(12, trtemplate.getTrans_type());
                    
                    ps.setDouble(13, trtemplate.getPerc_for_tr());
                    ps.setString(14,trtemplate.getPdc());
                    ps.setLong(15,trtemplate.getId());
                    
                    ps.executeUpdate();
                    c.commit();
                    res1.setCode(0);
            } catch (SQLException e) {
            		res1.setCode(1);
            		res1.setName(e.getMessage());
            		if(e.getMessage().indexOf("XUK_TR_SETS_TEMLATE_2")>0)
            			{
            				String A = Labels.getLabel("trtemplate.wrong_ord_num");
            				res1.setName(A.replaceAll("#1", Integer.toString(trtemplate.getOrd())));
            			}
            		
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                    
            } finally {
                    ConnectionPool.close(c);
            }
            return res1;

    }

    public static void remove(TrTemplate trtemplate,String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM BF_TR_TEMPLATE WHERE id=?");
                    ps.setInt(1, trtemplate.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
    }
}
