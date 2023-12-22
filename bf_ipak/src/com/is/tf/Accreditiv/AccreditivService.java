package com.is.tf.Accreditiv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tf.Accreditivsumchange.Accreditivsumchange;
import com.is.tf.Accreditivsumchange.AccreditivsumchangeService;
import com.is.tf.Accreditivtimechange.Accreditivtimechange;
import com.is.tf.Accreditivtimechange.AccreditivtimechangeService;
import com.is.tf.contract.Contract;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class AccreditivService {
	 private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
     private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
     private static String msql ="SELECT * FROM TF_Accreditiv ";


     public List<Accreditiv> getAccreditiv()  {

             List<Accreditiv> list = new ArrayList<Accreditiv>();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     Statement s = c.createStatement();
                     ResultSet rs = s.executeQuery("SELECT * FROM TF_Accreditiv ");
                     while (rs.next()) {
                             list.add(new Accreditiv(
                                             rs.getLong("id"),
                                             rs.getString("p0t21"),
                                             rs.getString("p1t21"),
                                             rs.getString("p2t21"),
                                             rs.getDate("p3t21"),
                                             rs.getString("p4t21"),
                                             rs.getDouble("p5t21"),
                                             rs.getDouble("p6t21"),
                                             rs.getDouble("p7t21"),
                                             rs.getDouble("p8t21"),
                                             rs.getString("p9t21"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p10t21"),
                                             rs.getString("p11t21"),
                                             rs.getString("p12t21"),
                                             rs.getString("p13t21"),
                                             rs.getInt("p14t21"),
                                             rs.getString("p15t21"),
                                             rs.getString("p16t21"),
                                             rs.getDate("p17t21"),
                                             rs.getShort("p100t21")
                                              
                             )) ;
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

     private static List<FilterField> getFilterFields(AccreditivFilter filter){
             List<FilterField> flfields = new ArrayList<FilterField>();


           if(!CheckNull.isEmpty(filter.getId())){
                   flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
           }
           if(!CheckNull.isEmpty(filter.getP0t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p0t21=?",filter.getP0t21()));
           }
           if(!CheckNull.isEmpty(filter.getP1t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p1t21=?",filter.getP1t21()));
           }
           if(!CheckNull.isEmpty(filter.getP2t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p2t21=?",filter.getP2t21()));
           }
           if(!CheckNull.isEmpty(filter.getP3t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p3t21=?",filter.getP3t21()));
           }
           if(!CheckNull.isEmpty(filter.getP4t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p4t21=?",filter.getP4t21()));
           }
           if(!CheckNull.isEmpty(filter.getP5t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p5t21=?",filter.getP5t21()));
           }
           if(!CheckNull.isEmpty(filter.getP6t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p6t21=?",filter.getP6t21()));
           }
           if(!CheckNull.isEmpty(filter.getP7t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p7t21=?",filter.getP7t21()));
           }
           if(!CheckNull.isEmpty(filter.getP8t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p8t21=?",filter.getP8t21()));
           }
           if(!CheckNull.isEmpty(filter.getP9t21())){
                   flfields.add(new FilterField(getCond(flfields)+ "p9t21=?",filter.getP9t21()));
           }
           if(!CheckNull.isEmpty(filter.getP10t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p10t21=?",filter.getP10t21()));
       }
           if(!CheckNull.isEmpty(filter.getId_contract())){
               flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
       }
           if(!CheckNull.isEmpty(filter.getP11t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p11t21=?",filter.getP11t21()));
       }
       if(!CheckNull.isEmpty(filter.getP12t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p12t21=?",filter.getP12t21()));
       }
       if(!CheckNull.isEmpty(filter.getP13t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p13t21=?",filter.getP13t21()));
       }
       if(!CheckNull.isEmpty(filter.getP14t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p14t21=?",filter.getP14t21()));
       }
       if(!CheckNull.isEmpty(filter.getP15t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p15t21=?",filter.getP15t21()));
       }
       if(!CheckNull.isEmpty(filter.getP16t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p16t21=?",filter.getP16t21()));
       }
       if(!CheckNull.isEmpty(filter.getP17t21())){
               flfields.add(new FilterField(getCond(flfields)+ "p17t21=?",filter.getP17t21()));
       }

   
      
           flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

             return flfields;
     }


     public static int getCount(AccreditivFilter filter)  {

         Connection c = null;
         int n = 0;
         List<FilterField> flFields =getFilterFields(filter);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT count(*) ct FROM TF_Accreditiv ");
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



     public static List<Accreditiv> getAccreditivsFl(int pageIndex, int pageSize, AccreditivFilter filter)  {

             List<Accreditiv> list = new ArrayList<Accreditiv>();
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
                   //  System.out.println(sql.toString());
                     for(params=0;params<flFields.size();params++){
                     ps.setObject(params+1, flFields.get(params).getColobject());
                     }
                    
                     params++;
                     ps.setInt(params++,v_upperbound);
                     ps.setInt(params++,v_lowerbound);

                     ResultSet rs = ps.executeQuery();
                     while (rs.next()) {
                             list.add(new Accreditiv(
                                             rs.getLong("id"),
                                             rs.getString("p0t21"),
                                             rs.getString("p1t21"),
                                             rs.getString("p2t21"),
                                             rs.getDate("p3t21"),
                                             rs.getString("p4t21"),
                                             rs.getDouble("p5t21"),
                                             rs.getDouble("p6t21"),
                                             rs.getDouble("p7t21"),
                                             rs.getDouble("p8t21"),
                                             rs.getString("p9t21"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p10t21"),
                                              rs.getString("p11t21"),
                                              rs.getString("p12t21"),
                                              rs.getString("p13t21"),
                                              rs.getInt("p14t21"),
                                              rs.getString("p15t21"),
                                              rs.getString("p16t21"),
                                              rs.getDate("p17t21"),
                                              rs.getShort("p100t21"),
                                              AccreditivsumchangeService.getAccreditivsumchanges(rs.getString("p2t21"),rs.getLong("id_contract")),
                                              AccreditivtimechangeService.getAccreditivtimechange(rs.getString("p2t21"),rs.getLong("id_contract"))
                                              
                             ));  
                     }
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return list;

     }


     public Accreditiv getAccreditiv(int accreditivId) {

             Accreditiv accreditiv = new Accreditiv();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_accreditiv WHERE id=?");
                     ps.setInt(1, accreditivId);
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             accreditiv = new Accreditiv();
                             
                             accreditiv.setId(rs.getLong("id"));
                             accreditiv.setP0t21(rs.getString("p0t21"));
                             accreditiv.setP1t21(rs.getString("p1t21"));
                             accreditiv.setP2t21(rs.getString("p2t21"));
                             accreditiv.setP3t21(rs.getDate("p3t21"));
                             accreditiv.setP4t21(rs.getString("p4t21"));
                             accreditiv.setP5t21(rs.getDouble("p5t21"));
                             accreditiv.setP6t21(rs.getDouble("p6t21"));
                             accreditiv.setP7t21(rs.getDouble("p7t21"));
                             accreditiv.setP8t21(rs.getDouble("p8t21"));
                             accreditiv.setP9t21(rs.getString("p9t21"));
                             accreditiv.setP10t21(rs.getString("p10t21"));
                             accreditiv.setP11t21(rs.getString("p11t21"));
                             accreditiv.setP12t21(rs.getString("p12t21"));
                             accreditiv.setP13t21(rs.getString("p13t21"));
                             accreditiv.setP14t21(rs.getInt("p14t21"));
                             accreditiv.setP15t21(rs.getString("p15t21"));
                             accreditiv.setP16t21(rs.getString("p16t21"));
                             accreditiv.setP17t21(rs.getDate("p17t21"));
                             accreditiv.setP100t21(rs.getShort("p100t21"));
                     }
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
             return accreditiv;
     }

     public static Accreditiv create(Accreditiv accreditiv)  {

             Connection c = null;
             PreparedStatement ps = null;
             try {
                     c = ConnectionPool.getConnection();
                     ps = c.prepareStatement("SELECT SQ_TF_Accreditiv.Nextval id FROM DUAL");
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             accreditiv.setId(rs.getLong("id"));
                     }
                     ps = c.prepareStatement("INSERT INTO TF_accreditiv (id, p0t21, p1t21, p2t21, p3t21, p4t21, p5t21, p6t21, p7t21, p8t21, p9t21, id_contract, p10t21, p11t21, p12t21, p13t21, p14t21, p15t21, p16t21, p17t21) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                     ps.setLong(1,accreditiv.getId());
                     ps.setString(2,accreditiv.getP0t21());
                     ps.setString(3,accreditiv.getP1t21());
                     ps.setString(4,accreditiv.getP2t21());
                     ps.setDate(5,new java.sql.Date(accreditiv.getP3t21().getTime()));
                     ps.setString(6,accreditiv.getP4t21());
                     ps.setDouble(7,accreditiv.getP5t21());
                     ps.setDouble(8,accreditiv.getP6t21());
                     ps.setDouble(9,accreditiv.getP7t21());
                     ps.setDouble(10,accreditiv.getP8t21());
                     ps.setString(11,accreditiv.getP9t21());
                     //ps.setLong(12,accreditiv.getId_contract());
                     ps.setString(13,accreditiv.getP10t21());
                     ps.setString(14,accreditiv.getP11t21());
                     ps.setString(15,accreditiv.getP12t21());
                     ps.setString(16,accreditiv.getP13t21());
                    // ps.setInt(17,accreditiv.getP14t21());
                     //ps.setString(18,accreditiv.getP15t21());
                     //ps.setString(19,accreditiv.getP16t21());
                     //ps.setDate(20,new java.sql.Date(accreditiv.getP17t21().getTime()));
                     
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return accreditiv;
     }

     public static void update(Accreditiv accreditiv)  {

             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("UPDATE TF_accreditiv SET  p0t21=?, p1t21=?, p2t21=?, p3t21=?, p4t21=?, p5t21=?, p6t21=?, p7t21=?, p8t21=?, p9t21=?, id_contract=?, p10t21=?, WHERE id=?");
                     
                     
                     ps.setString(1,accreditiv.getP0t21());
                     ps.setString(2,accreditiv.getP1t21());
                     ps.setString(3,accreditiv.getP2t21());
                     ps.setDate(4,new java.sql.Date(accreditiv.getP3t21().getTime()));
                     ps.setString(5,accreditiv.getP4t21());
                     ps.setDouble(6,accreditiv.getP5t21());
                     ps.setDouble(7,accreditiv.getP6t21());
                     ps.setDouble(8,accreditiv.getP7t21());
                     ps.setDouble(9,accreditiv.getP8t21());
                     ps.setString(10,accreditiv.getP9t21());
                     ps.setLong(11,accreditiv.getId_contract());
                     ps.setString(12,accreditiv.getP10t21());
                     ps.setLong(13,accreditiv.getId());
                     ps.setString(14,accreditiv.getP11t21());
                     ps.setString(15,accreditiv.getP12t21());
                     ps.setString(16,accreditiv.getP13t21());
                     ps.setInt(17,accreditiv.getP14t21());
                     ps.setString(18,accreditiv.getP15t21());
                     ps.setString(19,accreditiv.getP16t21());
                     ps.setDate(20,new java.sql.Date(accreditiv.getP17t21().getTime()));
                     
                     ps.executeUpdate();
                     c.commit();
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }

     }

     public static void remove(Accreditiv accreditiv, Long id_contract)  {

             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM TF_accreditiv WHERE id_contract=?");
                     
                     ps.setLong(1, id_contract);
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
     }
     
     
     
     
     public static com.sbs.service.Accreditiv create2(com.sbs.service.Accreditiv accreditiv, String p1t21, Long id_contract)  {
         Connection c = null;
         PreparedStatement ps = null;
         long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_Accreditiv.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                	 aid=rs.getLong("id");
                 }
                 
                 ps = c.prepareStatement("INSERT INTO TF_accreditiv (id, p1t21, p2t21, p3t21, p4t21, p5t21, p6t21, p7t21, p8t21, p9t21, id_contract, p10t21,p11t21,p12t21,p13t21,p16t21,p17t21,P100T21) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 //ps.setString(1,accreditiv.getIdCol());
                 ps.setLong(1, aid);
                 ps.setString(2,p1t21);
                 ps.setString(3,accreditiv.getP2T21()!=null? accreditiv.getP2T21():null);
                 ps.setDate(4,accreditiv.getP3T21() !=null? new java.sql.Date(accreditiv.getP3T21().getTimeInMillis()):null);
                 ps.setString(5,accreditiv.getP4T21());
                 ps.setDouble(6,accreditiv.getP5T21()!=null? accreditiv.getP5T21():null);
                 ps.setDouble(7,accreditiv.getP6T21()!=null? accreditiv.getP6T21():null);
                 ps.setDouble(8,accreditiv.getP7T21()!=null? accreditiv.getP7T21():null);
                 ps.setDouble(9,accreditiv.getP8T21()!=null? accreditiv.getP8T21():null);
                 ps.setInt(10,accreditiv.getP9T21()!=null? accreditiv.getP9T21():null);
                 ps.setLong(11,id_contract);
                 ps.setString(12,accreditiv.getP10T21());
                 ps.setShort(13,accreditiv.getP11T21()!=null? accreditiv.getP11T21():0);
                 ps.setShort(14,accreditiv.getP12T21()!=null? accreditiv.getP12T21():0);
                 ps.setShort(15,accreditiv.getP13T21()!=null? accreditiv.getP13T21():0);
                 ps.setShort(16,accreditiv.getP16T21()!=null? accreditiv.getP16T21():0);
                 ps.setDate(17,accreditiv.getP17T21() !=null? new java.sql.Date(accreditiv.getP17T21().getTimeInMillis()):null);
                 ps.setInt(18,accreditiv.getP100T21()!=null? accreditiv.getP100T21():null);
                 ps.executeUpdate();
                 AccreditivsumchangeService.remove(new Accreditivsumchange(), accreditiv.getP2T21(), id_contract);
                 if (accreditiv.getAccreditivSumChanges()!=null){
                 for (int i=0;i<accreditiv.getAccreditivSumChanges().length;i++){
                 AccreditivsumchangeService.create2(accreditiv.getAccreditivSumChanges()[i], accreditiv.getP2T21() , id_contract);
                     }
                 }
                //System.out.println("accreditiv.getAccreditivSumChanges()="+accreditiv.getAccreditivSumChanges()+"    accreditiv.getP2T21()=" +accreditiv.getP2T21()+ "   id_contract="+id_contract);
                 AccreditivtimechangeService.remove(new Accreditivtimechange(),accreditiv.getP2T21(), id_contract);
                 if (accreditiv.getAccreditivTimeChanges()!=null){
                 for (int i=0;i<accreditiv.getAccreditivTimeChanges().length;i++){
                 AccreditivtimechangeService.create2(accreditiv.getAccreditivTimeChanges()[i],accreditiv.getP2T21() , id_contract);
                     }
                 }
                 
                 c.commit();
            } catch (Exception e) {
                 e.printStackTrace();
                 ISLogger.getLogger().error("Accreditiv Service ERROR: --> "+CheckNull.getPstr(e));
                 //System.out.println("Accreditiv Service ERROR="+CheckNull.getPstr(e));

         } finally {
                 ConnectionPool.close(c);
         }
         return accreditiv;
         
     }}
 
     
     
    	 
     
   
   
     



