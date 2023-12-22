package com.is.tf.policytimechange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class PolicytimechangeService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Policytimechange order by p9t33";


    public static List<Policytimechange> getPolicytimechange(String p3t32, Long id_contract)  {

            List<Policytimechange> list = new ArrayList<Policytimechange>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Policytimechange where p1t33 = '"+p3t32+"' and id_contract= "+id_contract+" order by  p9t33");
                    while (rs.next()) {
                            list.add(new Policytimechange(
                                            rs.getLong("id"),
                                            rs.getString("p0t33"),
                                            rs.getString("p1t33"),
                                            rs.getDate("p2t33"),
                                            rs.getString("p3t33"),
                                            rs.getDate("p4t33"),
                                            rs.getString("p5t33"),
                                            rs.getString("p6t33"),
                                            rs.getString("p7t33"),
                                            rs.getString("p8t33"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p100t33"),
                                            rs.getString("p9t33"),
                                            rs.getString("p10t33"),
                                            rs.getDate("p11t33"),
                                            rs.getString("p12t33"),
                                            rs.getString("p13t33"),
                                            rs.getDate("p14t33")                
                            ));
                    }
            } catch (SQLException e) {
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

    private static List<FilterField> getFilterFields(PolicytimechangeFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t33=?",filter.getP0t33()));
          }
          if(!CheckNull.isEmpty(filter.getP1t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t33=?",filter.getP1t33()));
          }
          if(!CheckNull.isEmpty(filter.getP2t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t33=?",filter.getP2t33()));
          }
          if(!CheckNull.isEmpty(filter.getP3t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t33=?",filter.getP3t33()));
          }
          if(!CheckNull.isEmpty(filter.getP4t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t33=?",filter.getP4t33()));
          }
          if(!CheckNull.isEmpty(filter.getP5t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t33=?",filter.getP5t33()));
          }
          if(!CheckNull.isEmpty(filter.getP6t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t33=?",filter.getP6t33()));
          }
          if(!CheckNull.isEmpty(filter.getP7t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t33=?",filter.getP7t33()));
          }
          if(!CheckNull.isEmpty(filter.getP8t33())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t33=?",filter.getP8t33()));
          }
          if(!CheckNull.isEmpty(filter.getId_contract())){
              flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
      }
      if(!CheckNull.isEmpty(filter.getP100t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t33=?",filter.getP100t33()));
      }
      if(!CheckNull.isEmpty(filter.getP9t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p9t33=?",filter.getP9t33()));
      }
      if(!CheckNull.isEmpty(filter.getP10t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p10t33=?",filter.getP10t33()));
      }
      if(!CheckNull.isEmpty(filter.getP11t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p11t33=?",filter.getP11t33()));
      }
      if(!CheckNull.isEmpty(filter.getP12t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p12t33=?",filter.getP12t33()));
      }
      if(!CheckNull.isEmpty(filter.getP13t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p13t33=?",filter.getP13t33()));
      }
      if(!CheckNull.isEmpty(filter.getP14t33())){
              flfields.add(new FilterField(getCond(flfields)+ "p14t33=?",filter.getP14t33()));
      }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(PolicytimechangeFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Policytimechange ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection();
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



    public static List<Policytimechange> getPolicytimechangesFl(int pageIndex, int pageSize, PolicytimechangeFilter filter)  {

            List<Policytimechange> list = new ArrayList<Policytimechange>();
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
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new Policytimechange(
                                            rs.getLong("id"),
                                            rs.getString("p0t33"),
                                            rs.getString("p1t33"),
                                            rs.getDate("p2t33"),
                                            rs.getString("p3t33"),
                                            rs.getDate("p4t33"),
                                            rs.getString("p5t33"),
                                            rs.getString("p6t33"),
                                            rs.getString("p7t33"),
                                            rs.getString("p8t33"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p100t33"),
                                            rs.getString("p9t33"),
                                            rs.getString("p10t33"),
                                            rs.getDate("p11t33"),
                                            rs.getString("p12t33"),
                                            rs.getString("p13t33"),
                                            rs.getDate("p14t33")                
                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Policytimechange getPolicytimechange(int policytimechangeId) {

            Policytimechange policytimechange = new Policytimechange();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_policytimechange WHERE id=?");
                    ps.setInt(1, policytimechangeId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policytimechange = new Policytimechange();
                            
                            policytimechange.setId(rs.getLong("id"));
                            policytimechange.setP0t33(rs.getString("p0t33"));
                            policytimechange.setP1t33(rs.getString("p1t33"));
                            policytimechange.setP2t33(rs.getDate("p2t33"));
                            policytimechange.setP3t33(rs.getString("p3t33"));
                            policytimechange.setP4t33(rs.getDate("p4t33"));
                            policytimechange.setP5t33(rs.getString("p5t33"));
                            policytimechange.setP6t33(rs.getString("p6t33"));
                            policytimechange.setP7t33(rs.getString("p7t33"));
                            policytimechange.setP8t33(rs.getString("p8t33"));
                            policytimechange.setId_contract(rs.getLong("id_contract"));
                            policytimechange.setP100t33(rs.getString("p100t33"));
                            policytimechange.setP9t33(rs.getString("p9t33"));
                            policytimechange.setP10t33(rs.getString("p10t33"));
                            policytimechange.setP11t33(rs.getDate("p11t33"));
                            policytimechange.setP12t33(rs.getString("p12t33"));
                            policytimechange.setP13t33(rs.getString("p13t33"));
                            policytimechange.setP14t33(rs.getDate("p14t33"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return policytimechange;
    }

    public static Policytimechange create(Policytimechange policytimechange)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_TF_policytimechange.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policytimechange.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_policytimechange (id, p0t33, p1t33, p2t33, p3t33, p4t33, p5t33, p6t33, p7t33, p8t33, id_contract, p100t33, p9t33, p10t33, p11t33, p12t33, p13t33, p14t33) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,policytimechange.getId());
                    ps.setString(2,policytimechange.getP0t33());
                    ps.setString(3,policytimechange.getP1t33());
                    ps.setDate(4,new java.sql.Date(policytimechange.getP2t33().getTime()));
                    ps.setString(5,policytimechange.getP3t33());
                    ps.setDate(6,new java.sql.Date(policytimechange.getP4t33().getTime()));
                    ps.setString(7,policytimechange.getP5t33());
                    ps.setString(8,policytimechange.getP6t33());
                    ps.setString(9,policytimechange.getP7t33());
                    ps.setString(10,policytimechange.getP8t33());
                    ps.setLong(11,policytimechange.getId_contract());
                    ps.setString(12,policytimechange.getP100t33());
                    ps.setString(13,policytimechange.getP9t33());
                    ps.setString(14,policytimechange.getP10t33());
                    ps.setDate(15,new java.sql.Date(policytimechange.getP11t33().getTime()));
                    ps.setString(16,policytimechange.getP12t33());
                    ps.setString(17,policytimechange.getP13t33());
                    ps.setDate(18,new java.sql.Date(policytimechange.getP14t33().getTime()));
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return policytimechange;
    }

    public static void update(Policytimechange policytimechange)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_policytimechange SET id=?, p0t33=?, p2t33=?, p3t33=?, p4t33=?, p5t33=?, p6t33=?, p7t33=?, p8t33=?, p100t33=?, p9t33=?, p10t33=?, p11t33=?, p12t33=?, p13t33=?, p14t33=?  WHERE id=? and id_contract=? and p1t33=? ");
                    
                    ps.setLong(1,policytimechange.getId());
                    ps.setString(2,policytimechange.getP0t33());
                    
                    ps.setDate(4,new java.sql.Date(policytimechange.getP2t33().getTime()));
                    ps.setString(5,policytimechange.getP3t33());
                    ps.setDate(6,new java.sql.Date(policytimechange.getP4t33().getTime()));
                    ps.setString(7,policytimechange.getP5t33());
                    ps.setString(8,policytimechange.getP6t33());
                    ps.setString(9,policytimechange.getP7t33());
                    ps.setString(10,policytimechange.getP8t33());
                   
                    ps.setString(11,policytimechange.getP100t33());
                    ps.setString(12,policytimechange.getP9t33());
                    ps.setString(13,policytimechange.getP10t33());
                    ps.setDate(14,new java.sql.Date(policytimechange.getP11t33().getTime()));
                    ps.setString(15,policytimechange.getP12t33());
                    ps.setString(16,policytimechange.getP13t33());
                    ps.setDate(17,new java.sql.Date(policytimechange.getP14t33().getTime()));
                    ps.setLong(18,policytimechange.getId_contract());
                    ps.setString(3,policytimechange.getP1t33());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Policytimechange policytimechange , String p3t32, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_policytimechange WHERE p1t33=? and id_contract=?");
                    ps.setString(1, p3t32);
                    ps.setLong(2, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

    
    
    
    public static com.sbs.service.PolicyTimeChange create2(com.sbs.service.PolicyTimeChange policyTimeChange, String p3t32, Long id_contract)  {

   	 Connection c = null;
        PreparedStatement ps = null;
        long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_Policytimechange.Nextval id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
               	 aid=rs.getLong("id");
                }
                ps = c.prepareStatement("INSERT INTO TF_policytimechange (id, p1t33, p2t33, p3t33, p4t33, p5t33, p6t33, p7t33, p8t33, id_contract, p100t33, p9t33, p10t33, p11t33, p14t33) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,p3t32);
                ps.setDate(3,new java.sql.Date(policyTimeChange.getP2T33().getTimeInMillis()));
                if (policyTimeChange.getP3T33()!=null){
                ps.setInt(4,policyTimeChange.getP3T33());
                } else {
                	ps.setString(4,null);
                }
                ps.setDate(5,new java.sql.Date(policyTimeChange.getP4T33().getTimeInMillis()));
                if (policyTimeChange.getP5T33()!=null){
                ps.setInt(6,policyTimeChange.getP5T33());
                } else {
                	ps.setString(6,null);
                }
                ps.setShort(7,policyTimeChange.getP6T33());
                ps.setString(8,policyTimeChange.getP7T33());
                ps.setString(9,policyTimeChange.getP8T33());
                ps.setLong(10,id_contract);
                ps.setShort(11,policyTimeChange.getP100T33());
                ps.setInt(12,policyTimeChange.getP9T33());
                ps.setString(13,policyTimeChange.getP10T33());
                ps.setDate(14,new java.sql.Date(policyTimeChange.getP11T33().getTimeInMillis()));
                //ps.setString(16,policyTimeChange.getP12T33());
                //ps.setString(17,policyTimeChange.getP13T33());
                ps.setDate(15,new java.sql.Date(policyTimeChange.getP14T33().getTimeInMillis()));
                
               ps.executeUpdate();
               c.commit();
       } catch (Exception e) {
               e.printStackTrace();

       } finally {
               ConnectionPool.close(c);
       }
       return policyTimeChange;
}
    
    
    
    
    
    
    
}
