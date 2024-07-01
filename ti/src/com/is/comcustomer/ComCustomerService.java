package com.is.comcustomer;

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

public class ComCustomerService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM bf_com_Customers ";


    public List<ComCustomer> getComCustomer(String alias)  {

            List<ComCustomer> list = new ArrayList<ComCustomer>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM bf_Com_Customers");
                    while (rs.next()) {
                            list.add(new ComCustomer(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getString("account"),
                                            rs.getString("name"),
                                            rs.getString("inn"),
                                            rs.getString("region"),
                                            rs.getString("distr"),
                                            rs.getString("agreement_number"),
                                            rs.getDate("agreement_date"),
                                            rs.getString("purpose_template"),
                                            rs.getString("budget_inn"),
                                            rs.getString("budget_account"),
                                            rs.getInt("is_budget_org"),
                                            rs.getString("purpose_code"),
                                            rs.getInt("category_id"),
                                            rs.getString("provider_class"),
                                            rs.getString("provider_url")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    
    public static ComCustomer getComCustomer(int id, String alias)  {

        ComCustomer list = null;
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias);
                //Statement s = c.createStatement();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM bf_Com_Customers where id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                        list = new ComCustomer(
                                        rs.getInt("id"),
                                        rs.getString("branch"),
                                        rs.getString("account"),
                                        rs.getString("name"),
                                        rs.getString("inn"),
                                        rs.getString("region"),
                                        rs.getString("distr"),
                                        rs.getString("agreement_number"),
                                        rs.getDate("agreement_date"),
                                        rs.getString("purpose_template"),
                                        rs.getString("budget_inn"),
                                        rs.getString("budget_account"),
                                        rs.getInt("is_budget_org"),
                                        rs.getString("purpose_code"),
                                        rs.getInt("category_id"),
                                        rs.getString("provider_class"),
                                        rs.getString("provider_url"));
                }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}



  public static Res doAction(String un,String pw, ComCustomer comcustomer,int actionid) {
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
             
              cs.setString(1, "ID");  cs.setString(2,comcustomer.getId()+""); cs.execute();
              cs.setString(1, "BRANCH");  cs.setString(2,comcustomer.getBranch()); cs.execute();
              cs.setString(1, "ACCOUNT");  cs.setString(2,comcustomer.getAccount()); cs.execute();
              cs.setString(1, "NAME");  cs.setString(2,comcustomer.getName()); cs.execute();
              cs.setString(1, "INN");  cs.setString(2,comcustomer.getInn()); cs.execute();
              cs.setString(1, "REGION");  cs.setString(2,comcustomer.getRegion()); cs.execute();
              cs.setString(1, "DISTR");  cs.setString(2,comcustomer.getDistr()); cs.execute();
              cs.setString(1, "AGREEMENT_NUMBER");  cs.setString(2,comcustomer.getAgreement_number()); cs.execute();
              cs.setString(1, "AGREEMENT_DATE");  cs.setString(2,bdf.format(comcustomer.getAgreement_date())); cs.execute();
              cs.setString(1, "PURPOSE_TEMPLATE");  cs.setString(2,comcustomer.getPurpose_template()); cs.execute();
              cs.setString(1, "BUDGET_INN");  cs.setString(2,comcustomer.getBudget_inn()); cs.execute();
              cs.setString(1, "BUDGET_ACCOUNT");  cs.setString(2,comcustomer.getBudget_account()); cs.execute();
              cs.setString(1, "IS_BUDGET_ORG");  cs.setString(2,comcustomer.getIs_budget_org()+""); cs.execute();
              cs.setString(1, "PURPOSE_CODE");  cs.setString(2,comcustomer.getPurpose_code()); cs.execute();
              cs.setString(1, "CATEGORY_ID");  cs.setString(2,comcustomer.getCategory_id()+""); cs.execute();

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


            PreparedStatement ps = c.prepareStatement("SELECT * FROM ComCustomer WHERE branch=? and id=?");
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

    private static List<FilterField> getFilterFields(ComCustomerFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getAccount())){
                  flfields.add(new FilterField(getCond(flfields)+ "account=?",filter.getAccount()));
          }
          if(!CheckNull.isEmpty(filter.getName())){
                  flfields.add(new FilterField(getCond(flfields)+ "name=?",filter.getName()));
          }
          if(!CheckNull.isEmpty(filter.getInn())){
                  flfields.add(new FilterField(getCond(flfields)+ "inn=?",filter.getInn()));
          }
          if(!CheckNull.isEmpty(filter.getRegion())){
                  flfields.add(new FilterField(getCond(flfields)+ "region=?",filter.getRegion()));
          }
          if(!CheckNull.isEmpty(filter.getDistr())){
                  flfields.add(new FilterField(getCond(flfields)+ "distr=?",filter.getDistr()));
          }
          if(!CheckNull.isEmpty(filter.getAgreement_number())){
                  flfields.add(new FilterField(getCond(flfields)+ "agreement_number=?",filter.getAgreement_number()));
          }
          if(!CheckNull.isEmpty(filter.getAgreement_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "agreement_date=?",filter.getAgreement_date()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose_template())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose_template=?",filter.getPurpose_template()));
          }
          if(!CheckNull.isEmpty(filter.getBudget_inn())){
                  flfields.add(new FilterField(getCond(flfields)+ "budget_inn=?",filter.getBudget_inn()));
          }
          if(!CheckNull.isEmpty(filter.getBudget_account())){
                  flfields.add(new FilterField(getCond(flfields)+ "budget_account=?",filter.getBudget_account()));
          }
          if(!CheckNull.isEmpty(filter.getIs_budget_org())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_budget_org=?",filter.getIs_budget_org()));
          }
          if(!CheckNull.isEmpty(filter.getPurpose_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "purpose_code=?",filter.getPurpose_code()));
          }
          if(!CheckNull.isEmpty(filter.getCategory_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "category_id=?",filter.getCategory_id()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ComCustomerFilter filter,String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM bf_Com_Customers ");
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



    public static List<ComCustomer> getComCustomersFl(int pageIndex, int pageSize, ComCustomerFilter filter, String alias)  {

            List<ComCustomer> list = new ArrayList<ComCustomer>();
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
                            list.add(new ComCustomer(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getString("account"),
                                            rs.getString("name"),
                                            rs.getString("inn"),
                                            rs.getString("region"),
                                            rs.getString("distr"),
                                            rs.getString("agreement_number"),
                                            rs.getDate("agreement_date"),
                                            rs.getString("purpose_template"),
                                            rs.getString("budget_inn"),
                                            rs.getString("budget_account"),
                                            rs.getInt("is_budget_org"),
                                            rs.getString("purpose_code"),
                                            rs.getInt("category_id"),
                                            rs.getString("provider_class"),
                                            rs.getString("provider_url")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }



    public static ComCustomer create(ComCustomer comcustomer,String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_comcustomer.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            comcustomer.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO bf_com_customers (id, branch, account, name, inn, region, distr, agreement_number, agreement_date, purpose_template, budget_inn, budget_account, is_budget_org, purpose_code, category_id ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,comcustomer.getId());
                    ps.setString(2,comcustomer.getBranch());
                    ps.setString(3,comcustomer.getAccount());
                    ps.setString(4,comcustomer.getName());
                    ps.setString(5,comcustomer.getInn());
                    ps.setString(6,comcustomer.getRegion());
                    ps.setString(7,comcustomer.getDistr());
                    ps.setString(8,comcustomer.getAgreement_number());
                    ps.setDate(9,comcustomer.getAgreement_date()!=null?new java.sql.Date( comcustomer.getAgreement_date().getTime()):null);
                    ps.setString(10,comcustomer.getPurpose_template());
                    ps.setString(11,comcustomer.getBudget_inn());
                    ps.setString(12,comcustomer.getBudget_account());
                    ps.setInt(13,comcustomer.getIs_budget_org());
                    ps.setString(14,comcustomer.getPurpose_code());
                    ps.setInt(15,comcustomer.getCategory_id());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return comcustomer;
    }

    public static void update(ComCustomer comcustomer,  String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(  alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE bf_com_customers SET  branch=?, account=?, name=?, inn=?, region=?, distr=?, agreement_number=?, agreement_date=?, purpose_template=?, budget_inn=?, budget_account=?, is_budget_org=?, purpose_code=?, category_id=?  WHERE id=?");
                    
                   
                    ps.setString(1,comcustomer.getBranch());
                    ps.setString(2,comcustomer.getAccount());
                    ps.setString(3,comcustomer.getName());
                    ps.setString(4,comcustomer.getInn());
                    ps.setString(5,comcustomer.getRegion());
                    ps.setString(6,comcustomer.getDistr());
                    ps.setString(7,comcustomer.getAgreement_number());
                    ps.setDate(8,new java.sql.Date( comcustomer.getAgreement_date().getTime()));
                    ps.setString(9,comcustomer.getPurpose_template());
                    ps.setString(10,comcustomer.getBudget_inn());
                    ps.setString(11,comcustomer.getBudget_account());
                    ps.setInt(12,comcustomer.getIs_budget_org());
                    ps.setString(13,comcustomer.getPurpose_code());
                    ps.setInt(14,comcustomer.getCategory_id());
                    ps.setInt(15,comcustomer.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
                    
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ComCustomer comcustomer,  String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection( alias);
                    PreparedStatement ps = c.prepareStatement("DELETE FROM bf_com_customers WHERE comcustomer_id=?");
                    ps.setInt(1, comcustomer.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    public static HashMap< Integer,ComCustomer> getHCustomer(String alias)  {

        HashMap< Integer,ComCustomer> list = new HashMap< Integer,ComCustomer>();
    Connection c = null;

    try {
            c = ConnectionPool.getConnection(alias);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM BF_COM_CUSTOMERS");
            while (rs.next()) {
               list.put(rs.getInt("id"),new ComCustomer(
                               rs.getInt("id"),
                               rs.getString("name")
                                ));
            }
    } catch (SQLException e) {
            e.printStackTrace();
    } finally {
            ConnectionPool.close(c);
    }
    return list;

}
}
