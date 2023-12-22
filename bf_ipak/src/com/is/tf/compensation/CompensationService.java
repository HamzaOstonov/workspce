package com.is.tf.compensation;

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

public class CompensationService {
	 private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
     private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
     private static String msql ="SELECT * FROM TF_Compensation ";


     public List<Compensation> getCompensation()  {

             List<Compensation> list = new ArrayList<Compensation>();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     Statement s = c.createStatement();
                     ResultSet rs = s.executeQuery("SELECT * FROM TF_Compensation");
                     while (rs.next()) {
                             list.add(new Compensation(
                                             rs.getLong("id"),
                                             rs.getString("p1t42"),
                                             rs.getString("p0t42"),
                                             rs.getString("p2t42"),
                                             rs.getString("p3t42"),
                                             rs.getString("p4t42"),
                                             rs.getDouble("p5t42"),
                                             rs.getDate("p6t42"),
                                             rs.getDouble("p8t42"),
                                             rs.getDouble("p9t42"),
                                             rs.getDouble("p10t42"),
                                             rs.getDouble("p11t42"),
                                             rs.getDouble("p12t42"),
                                             rs.getString("p15t42"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p16t42"),
                                             rs.getString("p19t42"),
                                             rs.getString("p20t42"),
                                             rs.getDate("p21t42"),
                                             rs.getString("p100t42")));
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

     private static List<FilterField> getFilterFields(CompensationFilter filter){
             List<FilterField> flfields = new ArrayList<FilterField>();


           if(!CheckNull.isEmpty(filter.getId())){
                   flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
           }
           if(!CheckNull.isEmpty(filter.getP1t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p1t42=?",filter.getP1t42()));
           }
           if(!CheckNull.isEmpty(filter.getP0t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p0t42=?",filter.getP0t42()));
           }
           if(!CheckNull.isEmpty(filter.getP2t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p2t42=?",filter.getP2t42()));
           }
           if(!CheckNull.isEmpty(filter.getP3t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p3t42=?",filter.getP3t42()));
           }
           if(!CheckNull.isEmpty(filter.getP4t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p4t42=?",filter.getP4t42()));
           }
           if(!CheckNull.isEmpty(filter.getP5t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p5t42=?",filter.getP5t42()));
           }
           if(!CheckNull.isEmpty(filter.getP6t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p6t42=?",filter.getP6t42()));
           }
          
           if(!CheckNull.isEmpty(filter.getP8t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p8t42=?",filter.getP8t42()));
           }
           if(!CheckNull.isEmpty(filter.getP9t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p9t42=?",filter.getP9t42()));
           }
           if(!CheckNull.isEmpty(filter.getP10t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p10t42=?",filter.getP10t42()));
           }
           if(!CheckNull.isEmpty(filter.getP11t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p11t42=?",filter.getP11t42()));
           }
           if(!CheckNull.isEmpty(filter.getP12t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p12t42=?",filter.getP12t42()));
           }
           if(!CheckNull.isEmpty(filter.getP15t42())){
                   flfields.add(new FilterField(getCond(flfields)+ "p15t42=?",filter.getP15t42()));
           }
           if(!CheckNull.isEmpty(filter.getId_contract())){
               flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
       }
       if(!CheckNull.isEmpty(filter.getP16t42())){
               flfields.add(new FilterField(getCond(flfields)+ "p16t42=?",filter.getP16t42()));
       }
       if(!CheckNull.isEmpty(filter.getP19t42())){
               flfields.add(new FilterField(getCond(flfields)+ "p19t42=?",filter.getP19t42()));
       }
       if(!CheckNull.isEmpty(filter.getP20t42())){
               flfields.add(new FilterField(getCond(flfields)+ "p20t42=?",filter.getP20t42()));
       }
       if(!CheckNull.isEmpty(filter.getP21t42())){
               flfields.add(new FilterField(getCond(flfields)+ "p21t42=?",filter.getP21t42()));
       }
       if(!CheckNull.isEmpty(filter.getP100t42())){
               flfields.add(new FilterField(getCond(flfields)+ "p100t42=?",filter.getP100t42()));
       }
           flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

             return flfields;
     }


     public static int getCount(CompensationFilter filter)  {

         Connection c = null;
         int n = 0;
         List<FilterField> flFields =getFilterFields(filter);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT count(*) ct FROM TF_Compensation ");
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



     public static List<Compensation> getCompensationsFl(int pageIndex, int pageSize, CompensationFilter filter)  {

             List<Compensation> list = new ArrayList<Compensation>();
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
             sql.append(" order by 1");


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
                             list.add(new Compensation(
                                             rs.getLong("id"),
                                             rs.getString("p1t42"),
                                             rs.getString("p0t42"),
                                             rs.getString("p2t42"),
                                             rs.getString("p3t42"),
                                             rs.getString("p4t42"),
                                             rs.getDouble("p5t42"),
                                             rs.getDate("p6t42"),
                                             rs.getDouble("p8t42"),
                                             rs.getDouble("p9t42"),
                                             rs.getDouble("p10t42"),
                                             rs.getDouble("p11t42"),
                                             rs.getDouble("p12t42"),
                                             rs.getString("p15t42"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p16t42"),
                                             rs.getString("p19t42"),
                                             rs.getString("p20t42"),
                                             rs.getDate("p21t42"),
                                             rs.getString("p100t42")));
                     }
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return list;

     }


     public Compensation getCompensation(int compensationId) {

             Compensation compensation = new Compensation();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_compensation WHERE id=?");
                     ps.setInt(1, compensationId);
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             compensation = new Compensation();
                             
                             compensation.setId(rs.getLong("id"));
                             compensation.setP1t42(rs.getString("p1t42"));
                             compensation.setP0t42(rs.getString("p0t42"));
                             compensation.setP2t42(rs.getString("p2t42"));
                             compensation.setP3t42(rs.getString("p3t42"));
                             compensation.setP4t42(rs.getString("p4t42"));
                             compensation.setP5t42(rs.getDouble("p5t42"));
                             compensation.setP6t42(rs.getDate("p6t42"));
                             compensation.setP8t42(rs.getDouble("p8t42"));
                             compensation.setP9t42(rs.getDouble("p9t42"));
                             compensation.setP10t42(rs.getDouble("p10t42"));
                             compensation.setP11t42(rs.getDouble("p11t42"));
                             compensation.setP12t42(rs.getDouble("p12t42"));
                             compensation.setP15t42(rs.getString("p15t42"));
                             compensation.setId_contract(rs.getLong("id_contract"));
                             compensation.setP16t42(rs.getString("p16t42"));
                             compensation.setP19t42(rs.getString("p19t42"));
                             compensation.setP20t42(rs.getString("p20t42"));
                             compensation.setP21t42(rs.getDate("p21t42"));
                             compensation.setP100t42(rs.getString("p100t42"));
                     }
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
             return compensation;
     }

     public static Compensation create(Compensation compensation)  {

             Connection c = null;
             PreparedStatement ps = null;
             try {
                     c = ConnectionPool.getConnection();
                     ps = c.prepareStatement("SELECT SQ_TF_compensation.NEXTVAL id FROM DUAL");
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             compensation.setId(rs.getLong("id"));
                     }
                     ps = c.prepareStatement("INSERT INTO TF_compensation (id, p1t42, p0t42, p2t42, p3t42, p4t42, p5t42, p6t42, p8t42, p9t42, p10t42, p11t42, p12t42, p15t42 , id_contract, p16t42, p19t42, p20t42, p21t42, p100t42) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                     ps.setLong(1,compensation.getId());
                     ps.setString(2,compensation.getP1t42());
                     ps.setString(3,compensation.getP0t42());
                     ps.setString(4,compensation.getP2t42());
                     ps.setString(5,compensation.getP3t42());
                     ps.setString(6,compensation.getP4t42());
                     ps.setDouble(7,compensation.getP5t42());
                     ps.setDate(8,new java.sql.Date(compensation.getP6t42().getTime()));
                     ps.setDouble(9,compensation.getP8t42());
                     ps.setDouble(10,compensation.getP9t42());
                     ps.setDouble(11,compensation.getP10t42());
                     ps.setDouble(12,compensation.getP11t42());
                     ps.setDouble(13,compensation.getP12t42());
                     ps.setString(14,compensation.getP15t42());
                     ps.setLong(15,compensation.getId_contract());
                     ps.setString(16,compensation.getP16t42());
                     ps.setString(17,compensation.getP19t42());
                     ps.setString(18,compensation.getP20t42());
                     ps.setDate(19,new java.sql.Date(compensation.getP21t42().getTime()));
                     ps.setString(20,compensation.getP100t42());
                     
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return compensation;
     }

     public static void update(Compensation compensation)  {

             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("UPDATE TF_compensation SET id=?, p1t42=?, p0t42=?, p2t42=?, p3t42=?, p4t42=?, p5t42=?, p6t42=?, p8t42=?, p9t42=?, p10t42=?, p11t42=?, p12t42=?, p15t42=?, , id_contract=?, p16t42=?, p19t42=?, p20t42=?, p21t42=?, p100t42=? WHERE id=?");
                     
                     ps.setLong(1,compensation.getId());
                     ps.setString(2,compensation.getP1t42());
                     ps.setString(3,compensation.getP0t42());
                     ps.setString(4,compensation.getP2t42());
                     ps.setString(5,compensation.getP3t42());
                     ps.setString(6,compensation.getP4t42());
                     ps.setDouble(7,compensation.getP5t42());
                     ps.setDate(8,new java.sql.Date(compensation.getP6t42().getTime()));
                     ps.setDouble(9,compensation.getP8t42());
                     ps.setDouble(10,compensation.getP9t42());
                     ps.setDouble(11,compensation.getP10t42());
                     ps.setDouble(12,compensation.getP11t42());
                     ps.setDouble(13,compensation.getP12t42());
                     ps.setString(14,compensation.getP15t42());
                     ps.setLong(15,compensation.getId_contract());
                     ps.setString(16,compensation.getP16t42());
                     ps.setString(17,compensation.getP19t42());
                     ps.setString(18,compensation.getP20t42());
                     ps.setDate(19,new java.sql.Date(compensation.getP21t42().getTime()));
                     ps.setString(20,compensation.getP100t42());
                     ps.executeUpdate();
                     c.commit();
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }

     }

     public static void remove(Compensation compensation, Long id_contract)  {

             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM TF_compensation WHERE id_contract=?");
                     ps.setLong(1, id_contract);
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
     }









     public static com.sbs.service.Compensation create(com.sbs.service.Compensation compensation, String P1t42, Long id_contract)  {

         Connection c = null;
         PreparedStatement ps = null;
         Long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_Compensation.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) aid=rs.getLong("id");
                 
                 ps = c.prepareStatement("INSERT INTO TF_compensation (id, p1t42, p0t42, p2t42, p3t42, p4t42, p5t42, p6t42, p8t42, p10t42, p11t42, p12t42, p15t42, id_contract, p16t42, p19t42, p20t42, p21t42, p100t42 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setLong(1,aid);
                 ps.setString(2,P1t42);
                 ps.setInt(3,compensation.getP0T42()!=null? compensation.getP0T42():0);
                 ps.setString(4,compensation.getP2T42());
                 ps.setString(5,compensation.getP3T42());
                 ps.setString(6,compensation.getP4T42());
                 ps.setDouble(7,compensation.getP5T42()!=null? compensation.getP5T42():0);
                 ps.setDate(8,new java.sql.Date(compensation.getP6T42().getTimeInMillis()));
                 ps.setDouble(9,compensation.getP8T42()!=null? compensation.getP8T42():0);
                 ps.setDouble(10,compensation.getP10T42()!=null? compensation.getP10T42():0);
                 ps.setDouble(11,compensation.getP11T42()!=null? compensation.getP11T42():0);
                 ps.setDouble(12,compensation.getP12T42()!=null? compensation.getP12T42():0);
                 ps.setInt(13,compensation.getP15T42()!=null? compensation.getP15T42():0);
                 ps.setLong(14,id_contract);
                 ps.setString(15,compensation.getP16T42());
                 ps.setShort(16,compensation.getP19T42()!=null? compensation.getP19T42():0);
                 ps.setShort(17,compensation.getP20T42()!=null? compensation.getP20T42():0);
                 ps.setDate(18,new java.sql.Date(compensation.getP21T42().getTimeInMillis()));
                 ps.setShort(19,compensation.getP100T42()!=null? compensation.getP100T42():0);
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return compensation;
 }

}
