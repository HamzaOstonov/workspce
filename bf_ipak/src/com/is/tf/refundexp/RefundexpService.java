package com.is.tf.refundexp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tf.fund.Fund;
import com.is.tf.movefromex.MoveFromEx;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.RefObjDataService;

public class RefundexpService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Refundexp ";


    public List<Refundexp> getRefundexp()  {

            List<Refundexp> list = new ArrayList<Refundexp>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Refundexp");
                    while (rs.next()) {
                            list.add(new Refundexp(
                                            rs.getLong("id"),
                                            rs.getString("p1t36"),
                                            rs.getString("p0t36"),
                                            rs.getDate("p2t36"),
                                            rs.getString("p3t36"),
                                            rs.getString("p4t36"),
                                            rs.getDouble("p5t36"),
                                            rs.getDouble("p6t36"),
                                            rs.getDouble("p7t36"),
                                            rs.getDouble("p8t36"),
                                            rs.getDouble("p9t36"),
                                            rs.getDouble("p10t36"),
                                            rs.getDouble("p11t36"),
                                            rs.getDouble("p12t36"),
                                            rs.getString("p13t36"),
                                            rs.getString("p14t36"),
                                            rs.getString("p15t36"),
                                            rs.getString("p18t36"),
                                            rs.getString("p19t36"),
                                            rs.getString("p20t36"),
                                            rs.getString("p21t36"),
                                            rs.getString("p22t36"),
                                            rs.getDate("p23t36"),
                                            rs.getString("p100t36")
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

    private static List<FilterField> getFilterFields(RefundexpFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t36=?",filter.getP1t36()));
          }
          if(!CheckNull.isEmpty(filter.getP0t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t36=?",filter.getP0t36()));
          }
          if(!CheckNull.isEmpty(filter.getP2t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t36=?",filter.getP2t36()));
          }
          if(!CheckNull.isEmpty(filter.getP3t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t36=?",filter.getP3t36()));
          }
          if(!CheckNull.isEmpty(filter.getP4t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t36=?",filter.getP4t36()));
          }
          if(!CheckNull.isEmpty(filter.getP5t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t36=?",filter.getP5t36()));
          }
          if(!CheckNull.isEmpty(filter.getP6t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t36=?",filter.getP6t36()));
          }
          if(!CheckNull.isEmpty(filter.getP7t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t36=?",filter.getP7t36()));
          }
          if(!CheckNull.isEmpty(filter.getP8t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t36=?",filter.getP8t36()));
          }
          if(!CheckNull.isEmpty(filter.getP9t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t36=?",filter.getP9t36()));
          }
          if(!CheckNull.isEmpty(filter.getP10t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t36=?",filter.getP10t36()));
          }
          if(!CheckNull.isEmpty(filter.getP11t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t36=?",filter.getP11t36()));
          }
          if(!CheckNull.isEmpty(filter.getP12t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t36=?",filter.getP12t36()));
          }
          if(!CheckNull.isEmpty(filter.getP13t36())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t36=?",filter.getP13t36()));
          }
          if(!CheckNull.isEmpty(filter.getP14t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p14t36=?",filter.getP14t36()));
      }
          if(!CheckNull.isEmpty(filter.getP15t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p15t36=?",filter.getP15t36()));
      }
      if(!CheckNull.isEmpty(filter.getP18t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p18t36=?",filter.getP18t36()));
      }
      if(!CheckNull.isEmpty(filter.getP19t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p19t36=?",filter.getP19t36()));
      }
      if(!CheckNull.isEmpty(filter.getP20t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p20t36=?",filter.getP20t36()));
      }
      if(!CheckNull.isEmpty(filter.getP21t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p21t36=?",filter.getP21t36()));
      }
      if(!CheckNull.isEmpty(filter.getP22t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p22t36=?",filter.getP22t36()));
      }
      if(!CheckNull.isEmpty(filter.getP23t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p23t36=?",filter.getP23t36()));
      }
      if(!CheckNull.isEmpty(filter.getP100t36())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t36=?",filter.getP100t36()));
      }
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(RefundexpFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Refundexp ");
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



    public static List<Refundexp> getRefundexpsFl(int pageIndex, int pageSize, RefundexpFilter filter)  {

            List<Refundexp> list = new ArrayList<Refundexp>();
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
                            list.add(new Refundexp(
                                            rs.getLong("id"),
                                            rs.getString("p1t36"),
                                            rs.getString("p0t36"),
                                            rs.getDate("p2t36"),
                                            rs.getString("p3t36"),
                                            rs.getString("p4t36"),
                                            rs.getDouble("p5t36"),
                                            rs.getDouble("p6t36"),
                                            rs.getDouble("p7t36"),
                                            rs.getDouble("p8t36"),
                                            rs.getDouble("p9t36"),
                                            rs.getDouble("p10t36"),
                                            rs.getDouble("p11t36"),
                                            rs.getDouble("p12t36"),
                                            rs.getString("p13t36"),
                                            rs.getString("p14t36"),
                                            rs.getString("p15t36"),
                                            rs.getString("p18t36"),
                                            rs.getString("p19t36"),
                                            rs.getString("p20t36"),
                                            rs.getString("p21t36"),
                                            rs.getString("p22t36"),
                                            rs.getDate("p23t36"),
                                            rs.getString("p100t36")
                                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Refundexp getRefundexp(int refundexpId) {

            Refundexp refundexp = new Refundexp();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_refundexp WHERE id=?");
                    ps.setInt(1, refundexpId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            refundexp = new Refundexp();
                            
                            refundexp.setId(rs.getLong("id"));
                            refundexp.setP1t36(rs.getString("p1t36"));
                            refundexp.setP0t36(rs.getString("p0t36"));
                            refundexp.setP2t36(rs.getDate("p2t36"));
                            refundexp.setP3t36(rs.getString("p3t36"));
                            refundexp.setP4t36(rs.getString("p4t36"));
                            refundexp.setP5t36(rs.getDouble("p5t36"));
                            refundexp.setP6t36(rs.getDouble("p6t36"));
                            refundexp.setP7t36(rs.getDouble("p7t36"));
                            refundexp.setP8t36(rs.getDouble("p8t36"));
                            refundexp.setP9t36(rs.getDouble("p9t36"));
                            refundexp.setP10t36(rs.getDouble("p10t36"));
                            refundexp.setP11t36(rs.getDouble("p11t36"));
                            refundexp.setP12t36(rs.getDouble("p12t36"));
                            refundexp.setP13t36(rs.getString("p13t36"));
                            refundexp.setP13t36(rs.getString("p14t36"));
                            refundexp.setP15t36(rs.getString("p15t36"));
                            refundexp.setP18t36(rs.getString("p18t36"));
                            refundexp.setP19t36(rs.getString("p19t36"));
                            refundexp.setP20t36(rs.getString("p20t36"));
                            refundexp.setP21t36(rs.getString("p21t36"));
                            refundexp.setP22t36(rs.getString("p22t36"));
                            refundexp.setP23t36(rs.getDate("p23t36"));
                            refundexp.setP100t36(rs.getString("p100t36"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return refundexp;
    }

    public static Refundexp create(Refundexp refundexp)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_refundexp.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            refundexp.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_refundexp (id, p1t36, p0t36, p2t36, p3t36, p4t36, p5t36, p6t36, p7t36, p8t36, p9t36, p10t36, p11t36, p12t36, p13t36, p14t36) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,refundexp.getId());
                    ps.setString(2,refundexp.getP1t36());
                    ps.setString(3,refundexp.getP0t36());
                    ps.setDate(4,new java.sql.Date(refundexp.getP2t36().getTime()));
                    ps.setString(5,refundexp.getP3t36());
                    ps.setString(6,refundexp.getP4t36());
                    ps.setDouble(7,refundexp.getP5t36());
                    ps.setDouble(8,refundexp.getP6t36());
                    ps.setDouble(9,refundexp.getP7t36());
                    ps.setDouble(10,refundexp.getP8t36());
                    ps.setDouble(11,refundexp.getP9t36());
                    ps.setDouble(12,refundexp.getP10t36());
                    ps.setDouble(13,refundexp.getP11t36());
                    ps.setDouble(14,refundexp.getP12t36());
                    ps.setString(15,refundexp.getP13t36());
                    ps.setString(16,refundexp.getP14t36());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return refundexp;
    }

    public static void update(Refundexp refundexp)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_refundexp SET id=?, p1t36=?, p0t36=?, p2t36=?, p3t36=?, p4t36=?, p5t36=?, p6t36=?, p7t36=?, p8t36=?, p9t36=?, p10t36=?, p11t36=?, p12t36=?, p13t36=?, , p14t36=?, WHERE id=?");
                    
                    ps.setLong(1,refundexp.getId());
                    ps.setString(2,refundexp.getP1t36());
                    ps.setString(3,refundexp.getP0t36());
                    ps.setDate(4,new java.sql.Date(refundexp.getP2t36().getTime()));
                    ps.setString(5,refundexp.getP3t36());
                    ps.setString(6,refundexp.getP4t36());
                    ps.setDouble(7,refundexp.getP5t36());
                    ps.setDouble(8,refundexp.getP6t36());
                    ps.setDouble(9,refundexp.getP7t36());
                    ps.setDouble(10,refundexp.getP8t36());
                    ps.setDouble(11,refundexp.getP9t36());
                    ps.setDouble(12,refundexp.getP10t36());
                    ps.setDouble(13,refundexp.getP11t36());
                    ps.setDouble(14,refundexp.getP12t36());
                    ps.setString(15,refundexp.getP13t36());
                    ps.setString(16,refundexp.getP14t36());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Refundexp refundexp, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_refundexp WHERE id_contract=?");
                    //ps.setLong(1, refundexp.getId());
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    
    
    
    
    
    
    
    
    public static com.sbs.service.RefundExp create(com.sbs.service.RefundExp refundexp , String P1T36, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_refundexp.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                 aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_refundexp (id, p1t36, p0t36, p2t36, p4t36, p5t36, p6t36, p7t36, p8t36, p9t36, p10t36,  p13t36, id_contract, p14t36,p15t36,p18t36,p20t36,p21t36,p22t36,p23t36,p100t36) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T36);
                ps.setInt(3,refundexp.getP0T36()!=null? refundexp.getP0T36():0);
                ps.setDate(4,new java.sql.Date(refundexp.getP2T36().getTimeInMillis()));
                //ps.setInt(5,refundexp.getP3T36());
                ps.setInt(5,refundexp.getP4T36());
                ps.setDouble(6,refundexp.getP5T36());
                ps.setDouble(7,refundexp.getP6T36()!=null? refundexp.getP6T36():0);
                ps.setDouble(8,refundexp.getP7T36()!=null? refundexp.getP7T36():0);
                ps.setDouble(9,refundexp.getP8T36()!=null? refundexp.getP8T36():0);
                ps.setDouble(10,refundexp.getP9T36()!=null? refundexp.getP9T36():0);
                ps.setDouble(11,refundexp.getP10T36()!=null? refundexp.getP10T36():0);
                //ps.setString(12,P11T36);
                //ps.setString(13,P12T36);
                ps.setInt(12,refundexp.getP13T36());
                ps.setLong(13,id_contract);
                ps.setInt(14,refundexp.getP14T36()!=null? refundexp.getP14T36():0);
                ps.setString(15,refundexp.getP15T36());
                ps.setShort(16,refundexp.getP18T36());
               // ps.setShort(19,refundexp.getP19T36()!=null?refundexp.getP19T36():0);  
                ps.setString(17,refundexp.getP20T36());
                ps.setShort(18,refundexp.getP21T36());
                ps.setString(19,refundexp.getP22T36());
                ps.setDate(20,new java.sql.Date(refundexp.getP23T36().getTimeInMillis()));
                ps.setShort(21,refundexp.getP100T36());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return refundexp;
    }

}
