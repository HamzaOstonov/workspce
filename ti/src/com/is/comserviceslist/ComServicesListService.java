package com.is.comserviceslist;

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
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;


public class ComServicesListService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM BF_COM_SERVICES_LIST ";


    public List<ComServicesList> getComServicesList(String alias)  {

            List<ComServicesList> list = new ArrayList<ComServicesList>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM BF_COM_SERVICES_LIST");
                    while (rs.next()) {
                            list.add(new ComServicesList(
                                            rs.getInt("id"),
                                            rs.getInt("customerj_id"),
                                            rs.getInt("state"),
                                            rs.getString("name"),
                                            rs.getInt("p_name_mask"),
                                            rs.getInt("p_number_mask"),
                                            rs.getInt("from_date_mask"),
                                            rs.getInt("to_date_mask"),
                                            rs.getInt("from_value_mask"),
                                            rs.getInt("to_value_mask"),
                                            rs.getInt("difference_mask"),
                                            rs.getInt("penalty_amount_mask"),
                                            rs.getInt("operation_id"),
                                            rs.getInt("deal_id"),
                                            rs.getInt("parent_id"),
                                            rs.getInt("parent_group_id"),
                                            rs.getInt("client_address_mask"),
                                            rs.getInt("pay_cat_id"),
                                            rs.getInt("district_mask")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    
    public static HashMap< Integer,ComServicesList> getHComServicesList(String alias)  {

    	HashMap< Integer,ComServicesList> list = new HashMap< Integer,ComServicesList>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM BF_COM_SERVICES_LIST");
                while (rs.next()) {
                    list.put(rs.getInt("id"),new ComServicesList(
                                        rs.getInt("id"),
                                        rs.getInt("customerj_id"),
                                        rs.getInt("state"),
                                        rs.getString("name"),
                                        rs.getInt("p_name_mask"),
                                        rs.getInt("p_number_mask"),
                                        rs.getInt("from_date_mask"),
                                        rs.getInt("to_date_mask"),
                                        rs.getInt("from_value_mask"),
                                        rs.getInt("to_value_mask"),
                                        rs.getInt("difference_mask"),
                                        rs.getInt("penalty_amount_mask"),
                                        rs.getInt("operation_id"),
                                        rs.getInt("deal_id"),
                                        rs.getInt("parent_id"),
                                        rs.getInt("parent_group_id"),
                                        rs.getInt("client_address_mask"),
                                        rs.getInt("pay_cat_id"),
                                        rs.getInt("district_mask")));
                }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}



  public static Res doAction(String un,String pw, ComServicesList comserviceslist,int actionid,String alias) {
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
             
              cs.setString(1, "ID");  cs.setString(2,comserviceslist.getId()+""); cs.execute();
              cs.setString(1, "CUSTOMERJ_ID");  cs.setString(2,comserviceslist.getCustomerj_id()+""); cs.execute();
              cs.setString(1, "STATE");  cs.setString(2,comserviceslist.getState()+""); cs.execute();
              cs.setString(1, "NAME");  cs.setString(2,comserviceslist.getName()); cs.execute();
              cs.setString(1, "P_NAME_MASK");  cs.setString(2,comserviceslist.getP_name_mask()+""); cs.execute();
              cs.setString(1, "P_NUMBER_MASK");  cs.setString(2,comserviceslist.getP_number_mask()+""); cs.execute();
              cs.setString(1, "FROM_DATE_MASK");  cs.setString(2,comserviceslist.getFrom_date_mask()+""); cs.execute();
              cs.setString(1, "TO_DATE_MASK");  cs.setString(2,comserviceslist.getTo_date_mask()+""); cs.execute();
              cs.setString(1, "FROM_VALUE_MASK");  cs.setString(2,comserviceslist.getFrom_value_mask()+""); cs.execute();
              cs.setString(1, "TO_VALUE_MASK");  cs.setString(2,comserviceslist.getTo_value_mask()+""); cs.execute();
              cs.setString(1, "DIFFERENCE_MASK");  cs.setString(2,comserviceslist.getDifference_mask()+""); cs.execute();
              cs.setString(1, "PENALTY_AMOUNT_MASK");  cs.setString(2,comserviceslist.getPenalty_amount_mask()+""); cs.execute();
              cs.setString(1, "OPERATION_ID");  cs.setString(2,comserviceslist.getOperation_id()+""); cs.execute();
              cs.setString(1, "DEAL_ID");  cs.setString(2,comserviceslist.getDeal_id()+""); cs.execute();
              cs.setString(1, "PARENT_ID");  cs.setString(2,comserviceslist.getParent_id()+""); cs.execute();
              cs.setString(1, "PARENT_GROUP_ID");  cs.setString(2,comserviceslist.getParent_group_id()+""); cs.execute();
              cs.setString(1, "CLIENT_ADDRESS_MASK");  cs.setString(2,comserviceslist.getClient_address_mask()+""); cs.execute();
              cs.setString(1, "PAY_CAT_ID");  cs.setString(2,comserviceslist.getPay_cat_id()+""); cs.execute();

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


            PreparedStatement ps = c.prepareStatement("SELECT * FROM ComServicesList WHERE branch=? and id=?");
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

    private static List<FilterField> getFilterFields(ComServicesListFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getCustomerj_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "customerj_id=?",filter.getCustomerj_id()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }
          if(!CheckNull.isEmpty(filter.getName())){
                  flfields.add(new FilterField(getCond(flfields)+ "name=?",filter.getName()));
          }
          if(!CheckNull.isEmpty(filter.getP_name_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "p_name_mask=?",filter.getP_name_mask()));
          }
          if(!CheckNull.isEmpty(filter.getP_number_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "p_number_mask=?",filter.getP_number_mask()));
          }
          if(!CheckNull.isEmpty(filter.getFrom_date_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "from_date_mask=?",filter.getFrom_date_mask()));
          }
          if(!CheckNull.isEmpty(filter.getTo_date_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "to_date_mask=?",filter.getTo_date_mask()));
          }
          if(!CheckNull.isEmpty(filter.getFrom_value_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "from_value_mask=?",filter.getFrom_value_mask()));
          }
          if(!CheckNull.isEmpty(filter.getTo_value_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "to_value_mask=?",filter.getTo_value_mask()));
          }
          if(!CheckNull.isEmpty(filter.getDifference_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "difference_mask=?",filter.getDifference_mask()));
          }
          if(!CheckNull.isEmpty(filter.getPenalty_amount_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "penalty_amount_mask=?",filter.getPenalty_amount_mask()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_id=?",filter.getOperation_id()));
          }
          if(!CheckNull.isEmpty(filter.getDeal_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "deal_id=?",filter.getDeal_id()));
          }
          if(!CheckNull.isEmpty(filter.getParent_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_id=?",filter.getParent_id()));
          }
          if(!CheckNull.isEmpty(filter.getParent_group_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_group_id=?",filter.getParent_group_id()));
          }
          if(!CheckNull.isEmpty(filter.getClient_address_mask())){
                  flfields.add(new FilterField(getCond(flfields)+ "client_address_mask=?",filter.getClient_address_mask()));
          }
          if(!CheckNull.isEmpty(filter.getPay_cat_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "pay_cat_id=?",filter.getPay_cat_id()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ComServicesListFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_COM_SERVICES_LIST ");
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



    public static List<ComServicesList> getComServicesListsFl(int pageIndex, int pageSize, ComServicesListFilter filter, String alias)  {

            List<ComServicesList> list = new ArrayList<ComServicesList>();
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
                            list.add(new ComServicesList(
                                            rs.getInt("id"),
                                            rs.getInt("customerj_id"),
                                            rs.getInt("state"),
                                            rs.getString("name"),
                                            rs.getInt("p_name_mask"),
                                            rs.getInt("p_number_mask"),
                                            rs.getInt("from_date_mask"),
                                            rs.getInt("to_date_mask"),
                                            rs.getInt("from_value_mask"),
                                            rs.getInt("to_value_mask"),
                                            rs.getInt("difference_mask"),
                                            rs.getInt("penalty_amount_mask"),
                                            rs.getInt("operation_id"),
                                            rs.getInt("deal_id"),
                                            rs.getInt("parent_id"),
                                            rs.getInt("parent_group_id"),
                                            rs.getInt("client_address_mask"),
                                            rs.getInt("pay_cat_id"),
                                            rs.getInt("district_mask")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }



    public static ComServicesList create(ComServicesList comserviceslist, String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_BF_COM_SERVICES_LIST.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            comserviceslist.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO BF_COM_SERVICES_LIST (id, customerj_id, state, name, p_name_mask, p_number_mask, from_date_mask, to_date_mask, from_value_mask, to_value_mask, difference_mask, penalty_amount_mask, operation_id, deal_id, parent_id, parent_group_id, client_address_mask, pay_cat_id, district_mask) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,comserviceslist.getId());
                    ps.setInt(2,comserviceslist.getCustomerj_id());
                    ps.setInt(3,comserviceslist.getState());
                    ps.setString(4,comserviceslist.getName());
                    ps.setInt(5,comserviceslist.getP_name_mask());
                    ps.setInt(6,comserviceslist.getP_number_mask());
                    ps.setInt(7,comserviceslist.getFrom_date_mask());
                    ps.setInt(8,comserviceslist.getTo_date_mask());
                    ps.setInt(9,comserviceslist.getFrom_value_mask());
                    ps.setInt(10,comserviceslist.getTo_value_mask());
                    ps.setInt(11,comserviceslist.getDifference_mask());
                    ps.setInt(12,comserviceslist.getPenalty_amount_mask());
                    ps.setInt(13,comserviceslist.getOperation_id());
                    ps.setInt(14,comserviceslist.getDeal_id());
                    ps.setInt(15,comserviceslist.getParent_id());
                    ps.setInt(16,comserviceslist.getParent_group_id());
                    ps.setInt(17,comserviceslist.getClient_address_mask());
                    ps.setInt(18,comserviceslist.getPay_cat_id());
                    ps.setInt(19,comserviceslist.getDistrict_mask());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return comserviceslist;
    }

    public static void update(ComServicesList comserviceslist, String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE BF_COM_SERVICES_LIST SET  customerj_id=?, state=?, name=?, p_name_mask=?, p_number_mask=?, from_date_mask=?, to_date_mask=?, from_value_mask=?, to_value_mask=?, difference_mask=?, penalty_amount_mask=?, operation_id=?, deal_id=?, parent_id=?, parent_group_id=?, client_address_mask=?, pay_cat_id=?, district_mask=?  WHERE id=?");
                    
                    
                    ps.setInt(1,comserviceslist.getCustomerj_id());
                    ps.setInt(2,comserviceslist.getState());
                    ps.setString(3,comserviceslist.getName());
                    ps.setInt(4,comserviceslist.getP_name_mask());
                    ps.setInt(5,comserviceslist.getP_number_mask());
                    ps.setInt(6,comserviceslist.getFrom_date_mask());
                    ps.setInt(7,comserviceslist.getTo_date_mask());
                    ps.setInt(8,comserviceslist.getFrom_value_mask());
                    ps.setInt(9,comserviceslist.getTo_value_mask());
                    ps.setInt(10,comserviceslist.getDifference_mask());
                    ps.setInt(11,comserviceslist.getPenalty_amount_mask());
                    ps.setInt(12,comserviceslist.getOperation_id());
                    ps.setInt(13,comserviceslist.getDeal_id());
                    ps.setInt(14,comserviceslist.getParent_id());
                    ps.setInt(15,comserviceslist.getParent_group_id());
                    ps.setInt(16,comserviceslist.getClient_address_mask());
                    ps.setInt(17,comserviceslist.getPay_cat_id());
                    ps.setInt(18,comserviceslist.getDistrict_mask());
                    ps.setInt(19,comserviceslist.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ComServicesList comserviceslist, String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM BF_COM_SERVICES_LIST WHERE id=?");
                    ps.setInt(1, comserviceslist.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
}
