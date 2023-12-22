package com.is.tf.policysumchange;

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

public class PolicysumchangeService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Policysumchange order by p12t32 ";


    public static List<Policysumchange> getPolicysumchange(String p3t32, Long id_contract)   {

            List<Policysumchange> list = new ArrayList<Policysumchange>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Policysumchange where p1t34 = '"+p3t32+"' and id_contract= "+id_contract+" order by  p12t34");
                    while (rs.next()) {
                            list.add(new Policysumchange(
                                            rs.getLong("id"),
                                            rs.getString("p0t34"),
                                            rs.getString("p1t34"),
                                            rs.getString("p2t34"),
                                            rs.getDouble("p3t34"),
                                            rs.getString("p4t34"),
                                            rs.getDouble("p5t34"),
                                            rs.getDouble("p6t34"),
                                            rs.getDouble("p7t34"),
                                            rs.getDouble("p8t34"),
                                            rs.getString("p9t34"),
                                            rs.getString("p10t34"),
                                            rs.getString("p11t34"),
                                            rs.getString("p12t34"),
                                            rs.getString("p13t34"),
                                            rs.getDate("p14t34"),
                                            rs.getString("p17t34"),
                                            rs.getDate("p18t34"),
                                            rs.getString("p100t34")
                                            
                            
                            
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

    private static List<FilterField> getFilterFields(PolicysumchangeFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t34=?",filter.getP0t34()));
          }
          if(!CheckNull.isEmpty(filter.getP1t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t34=?",filter.getP1t34()));
          }
          if(!CheckNull.isEmpty(filter.getP2t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t34=?",filter.getP2t34()));
          }
          if(!CheckNull.isEmpty(filter.getP3t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t34=?",filter.getP3t34()));
          }
          if(!CheckNull.isEmpty(filter.getP4t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t34=?",filter.getP4t34()));
          }
          if(!CheckNull.isEmpty(filter.getP5t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t34=?",filter.getP5t34()));
          }
          if(!CheckNull.isEmpty(filter.getP6t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t34=?",filter.getP6t34()));
          }
          if(!CheckNull.isEmpty(filter.getP7t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t34=?",filter.getP7t34()));
          }
          if(!CheckNull.isEmpty(filter.getP8t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t34=?",filter.getP8t34()));
          }
          if(!CheckNull.isEmpty(filter.getP9t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t34=?",filter.getP9t34()));
          }
          if(!CheckNull.isEmpty(filter.getP10t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t34=?",filter.getP10t34()));
          }
          if(!CheckNull.isEmpty(filter.getP11t34())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t34=?",filter.getP11t34()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(PolicysumchangeFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Policysumchange ");
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



    public static List<Policysumchange> getPolicysumchangesFl(int pageIndex, int pageSize, PolicysumchangeFilter filter)  {

            List<Policysumchange> list = new ArrayList<Policysumchange>();
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
                            list.add(new Policysumchange(
                                            rs.getLong("id"),
                                            rs.getString("p0t34"),
                                            rs.getString("p1t34"),
                                            rs.getString("p2t34"),
                                            rs.getDouble("p3t34"),
                                            rs.getString("p4t34"),
                                            rs.getDouble("p5t34"),
                                            rs.getDouble("p6t34"),
                                            rs.getDouble("p7t34"),
                                            rs.getDouble("p8t34"),
                                            rs.getString("p9t34"),
                                            rs.getString("p10t34"),
                                            rs.getString("p11t34"),
                                            rs.getString("p12t34"),
                                            rs.getString("p13t34"),
                                            rs.getDate("p14t34"),
                                            rs.getString("p17t34"),
                                            rs.getDate("p18t34"),
                                            rs.getString("p100t34")
                                            ));
                            
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Policysumchange getPolicysumchange(int policysumchangeId) {

            Policysumchange policysumchange = new Policysumchange();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_policysumchange WHERE id=?");
                    ps.setInt(1, policysumchangeId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policysumchange = new Policysumchange();
                            
                            policysumchange.setId(rs.getLong("id"));
                            policysumchange.setP0t34(rs.getString("p0t34"));
                            policysumchange.setP1t34(rs.getString("p1t34"));
                            policysumchange.setP2t34(rs.getString("p2t34"));
                            policysumchange.setP3t34(rs.getDouble("p3t34"));
                            policysumchange.setP4t34(rs.getString("p4t34"));
                            policysumchange.setP5t34(rs.getDouble("p5t34"));
                            policysumchange.setP6t34(rs.getDouble("p6t34"));
                            policysumchange.setP7t34(rs.getDouble("p7t34"));
                            policysumchange.setP8t34(rs.getDouble("p8t34"));
                            policysumchange.setP9t34(rs.getString("p9t34"));
                            policysumchange.setP10t34(rs.getString("p10t34"));
                            policysumchange.setP11t34(rs.getString("p11t34"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return policysumchange;
    }

    public static Policysumchange create(Policysumchange policysumchange)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_TF_policysumchange.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            policysumchange.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_policysumchange (id, p0t34, p1t34, p2t34, p3t34, p4t34, p5t34, p6t34, p7t34, p8t34, p9t34, p10t34, p11t34, p12t34, p13t34, p14t34, p17t34, p18t34, p100t34 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,policysumchange.getId());
                    ps.setString(2,policysumchange.getP0t34());
                    ps.setString(3,policysumchange.getP1t34());
                    ps.setString(4,policysumchange.getP2t34());
                    ps.setDouble(5,policysumchange.getP3t34());
                    ps.setString(6,policysumchange.getP4t34());
                    ps.setDouble(7,policysumchange.getP5t34());
                    ps.setDouble(8,policysumchange.getP6t34());
                    ps.setDouble(9,policysumchange.getP7t34());
                    ps.setDouble(10,policysumchange.getP8t34());
                    ps.setString(11,policysumchange.getP9t34());
                    ps.setString(12,policysumchange.getP10t34());
                    ps.setString(13,policysumchange.getP11t34());
                    ps.setString(13,policysumchange.getP12t34());
                    ps.setString(13,policysumchange.getP13t34());
                    ps.setDate(13,new java.sql.Date(policysumchange.getP14t34().getTime()));
                    ps.setString(13,policysumchange.getP17t34());
                    ps.setDate(13,new java.sql.Date(policysumchange.getP18t34().getTime()));
                    ps.setString(13,policysumchange.getP100t34());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return policysumchange;
    }

    public static void update(Policysumchange policysumchange)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_policysumchange SET id=?, p0t34=?, p1t34=?, p2t34=?, p3t34=?, p4t34=?, p5t34=?, p6t34=?, p7t34=?, p8t34=?, p9t34=?, p10t34=?, p11t34=?,  WHERE id=?");
                    
                    ps.setLong(1,policysumchange.getId());
                    ps.setString(2,policysumchange.getP0t34());
                    ps.setString(3,policysumchange.getP1t34());
                    ps.setString(4,policysumchange.getP2t34());
                    ps.setDouble(5,policysumchange.getP3t34());
                    ps.setString(6,policysumchange.getP4t34());
                    ps.setDouble(7,policysumchange.getP5t34());
                    ps.setDouble(8,policysumchange.getP6t34());
                    ps.setDouble(9,policysumchange.getP7t34());
                    ps.setDouble(10,policysumchange.getP8t34());
                    ps.setString(11,policysumchange.getP9t34());
                    ps.setString(12,policysumchange.getP10t34());
                    ps.setString(13,policysumchange.getP11t34());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Policysumchange policysumchange, String p3t32, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_policysumchange WHERE p1t34=? and id_contract=?");
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
    
    
    
    
    
    public static com.sbs.service.PolicySumChange create2(com.sbs.service.PolicySumChange PolicySumChange, String p3t32, Long id_contract)   {

    	 Connection c = null;
         PreparedStatement ps = null;
         long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_policysumchange.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                	 aid=rs.getLong("id");
                 }
                ps = c.prepareStatement("INSERT INTO TF_policysumchange (id, p1t34, p3t34, p5t34, p6t34, p7t34, p8t34, p9t34, p10t34, p11t34, p12t34, p13t34, p14t34, p17t34, p18t34, p100t34, id_contract ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                //ps.setString(2,policysumchange.getP0t34());
                ps.setString(2,p3t32);
                //ps.setString(4,PolicySumChange.getP2T34());
                ps.setDouble(3,PolicySumChange.getP3T34());
                //ps.setString(6,PolicySumChange.getP4T34());
                ps.setDouble(4,PolicySumChange.getP5T34());
                ps.setDouble(5,PolicySumChange.getP6T34());
                ps.setDouble(6,PolicySumChange.getP7T34());
                ps.setDouble(7,PolicySumChange.getP8T34());
                ps.setShort(8,PolicySumChange.getP9T34());
                ps.setString(9,PolicySumChange.getP10T34());
                ps.setString(10,PolicySumChange.getP11T34());
                ps.setInt(11,PolicySumChange.getP12T34());
                ps.setString(12,PolicySumChange.getP13T34());
                ps.setDate(13,new java.sql.Date(PolicySumChange.getP14T34().getTimeInMillis()));
                ps.setShort(14,PolicySumChange.getP17T34());
                ps.setDate(15,new java.sql.Date(PolicySumChange.getP18T34().getTimeInMillis()));
                ps.setShort(16,PolicySumChange.getP100T34());
                ps.setLong(17,id_contract);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return PolicySumChange;
}
    
    
    
    
}
