package com.is.tf.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.is.utils.Res;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.tf.currency.RefCurrencyData;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class PaymentService {
	 private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
     private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
     private static String msql ="SELECT * FROM TF_Payment ";


     public List<Payment> getPayment()  {

             List<Payment> list = new ArrayList<Payment>();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     Statement s = c.createStatement();
                     ResultSet rs = s.executeQuery("SELECT * FROM TF_Payment");
                     while (rs.next()) {
                             list.add(new Payment(
                                             rs.getLong("id"),
                                             rs.getString("p1t44"),
                                             rs.getString("p0t44"),
                                             rs.getDate("p2t44"),
                                             rs.getString("p3t44"),
                                             rs.getString("p4t44"),
                                             rs.getDate("p5t44"),
                                             rs.getString("p6t44"),
                                             rs.getString("p7t44"),
                                             rs.getString("p8t44"),
                                             rs.getString("p9t44"),
                                             rs.getString("p10t44"),
                                             rs.getString("p11t44"),
                                             rs.getString("p12t44"),
                                             rs.getString("p13t44"),
                                             rs.getString("p14t44"),
                                             rs.getString("p15t44"),
                                             rs.getDouble("p16t44"),
                                             rs.getDouble("p17t44"),
                                             rs.getDouble("p18t44"),
                                             rs.getDouble("p19t44"),
                                             rs.getDouble("p20t44"),
                                             rs.getDouble("p21t44"),
                                             rs.getString("p22t44"),
                                             rs.getString("p23t44"),
                                             rs.getString("p24t44"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p27t44"),
                                             rs.getString("p28t44"),
                                             rs.getString("p29t44"),
                                             rs.getDate("p30t44"),
                                             rs.getString("p100t44")));
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

     private static List<FilterField> getFilterFields(PaymentFilter filter){
             List<FilterField> flfields = new ArrayList<FilterField>();


           if(!CheckNull.isEmpty(filter.getId())){
                   flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
           }
           if(!CheckNull.isEmpty(filter.getP1t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p1t44=?",filter.getP1t44()));
           }
           if(!CheckNull.isEmpty(filter.getP0t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p0t44=?",filter.getP0t44()));
           }
           if(!CheckNull.isEmpty(filter.getP2t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p2t44=?",filter.getP2t44()));
           }
           if(!CheckNull.isEmpty(filter.getP3t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p3t44=?",filter.getP3t44()));
           }
           if(!CheckNull.isEmpty(filter.getP4t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p4t44=?",filter.getP4t44()));
           }
           if(!CheckNull.isEmpty(filter.getP5t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p5t44=?",filter.getP5t44()));
           }
           if(!CheckNull.isEmpty(filter.getP6t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p6t44=?",filter.getP6t44()));
           }
           if(!CheckNull.isEmpty(filter.getP7t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p7t44=?",filter.getP7t44()));
           }
           if(!CheckNull.isEmpty(filter.getP8t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p8t44=?",filter.getP8t44()));
           }
           if(!CheckNull.isEmpty(filter.getP9t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p9t44=?",filter.getP9t44()));
           }
           if(!CheckNull.isEmpty(filter.getP10t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p10t44=?",filter.getP10t44()));
           }
           if(!CheckNull.isEmpty(filter.getP11t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p11t44=?",filter.getP11t44()));
           }
           if(!CheckNull.isEmpty(filter.getP12t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p12t44=?",filter.getP12t44()));
           }
           if(!CheckNull.isEmpty(filter.getP13t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p13t44=?",filter.getP13t44()));
           }
           if(!CheckNull.isEmpty(filter.getP14t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p14t44=?",filter.getP14t44()));
           }
           if(!CheckNull.isEmpty(filter.getP15t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p15t44=?",filter.getP15t44()));
           }
           if(!CheckNull.isEmpty(filter.getP16t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p16t44=?",filter.getP16t44()));
           }
           if(!CheckNull.isEmpty(filter.getP17t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p17t44=?",filter.getP17t44()));
           }
           if(!CheckNull.isEmpty(filter.getP18t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p18t44=?",filter.getP18t44()));
           }
           if(!CheckNull.isEmpty(filter.getP19t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p19t44=?",filter.getP19t44()));
           }
           if(!CheckNull.isEmpty(filter.getP20t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p20t44=?",filter.getP20t44()));
           }
           if(!CheckNull.isEmpty(filter.getP21t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p21t44=?",filter.getP21t44()));
           }
           if(!CheckNull.isEmpty(filter.getP22t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p22t44=?",filter.getP22t44()));
           }
           if(!CheckNull.isEmpty(filter.getP23t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p23t44=?",filter.getP23t44()));
           }
           if(!CheckNull.isEmpty(filter.getP24t44())){
                   flfields.add(new FilterField(getCond(flfields)+ "p24t44=?",filter.getP24t44()));
           }

           if(!CheckNull.isEmpty(filter.getId_contract())){
               flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
           }
           if(!CheckNull.isEmpty(filter.getP27t44())){
               flfields.add(new FilterField(getCond(flfields)+ "p27t44=?",filter.getP27t44()));
           }
           if(!CheckNull.isEmpty(filter.getP28t44())){
               flfields.add(new FilterField(getCond(flfields)+ "p28t44=?",filter.getP28t44()));
           }
           if(!CheckNull.isEmpty(filter.getP29t44())){
               flfields.add(new FilterField(getCond(flfields)+ "p29t44=?",filter.getP29t44()));
           }
           if(!CheckNull.isEmpty(filter.getP30t44())){
               flfields.add(new FilterField(getCond(flfields)+ "p30t44=?",filter.getP30t44()));
           }
           if(!CheckNull.isEmpty(filter.getP100t44())){
               flfields.add(new FilterField(getCond(flfields)+ "p100t44=?",filter.getP100t44()));
           }
           flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

             return flfields;
     		}


     public static int getCount(PaymentFilter filter)  {

         Connection c = null;
         int n = 0;
         List<FilterField> flFields =getFilterFields(filter);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT count(*) ct FROM TF_Payment ");
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



     public static List<Payment> getPaymentsFl(int pageIndex, int pageSize, PaymentFilter filter)  {

             List<Payment> list = new ArrayList<Payment>();
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
                             list.add(new Payment(
                                             rs.getLong("id"),
                                             rs.getString("p1t44"),
                                             rs.getString("p0t44"),
                                             rs.getDate("p2t44"),
                                             rs.getString("p3t44"),
                                             rs.getString("p4t44"),
                                             rs.getDate("p5t44"),
                                             rs.getString("p6t44"),
                                             rs.getString("p7t44"),
                                             rs.getString("p8t44"),
                                             rs.getString("p9t44"),
                                             rs.getString("p10t44"),
                                             rs.getString("p11t44"),
                                             rs.getString("p12t44"),
                                             rs.getString("p13t44"),
                                             rs.getString("p14t44"),
                                             rs.getString("p15t44"),
                                             rs.getDouble("p16t44"),
                                             rs.getDouble("p17t44"),
                                             rs.getDouble("p18t44"),
                                             rs.getDouble("p19t44"),
                                             rs.getDouble("p20t44"),
                                             rs.getDouble("p21t44"),
                                             rs.getString("p22t44"),
                                             rs.getString("p23t44"),
                                             rs.getString("p24t44"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p27t44"),
                                             rs.getString("p28t44"),
                                             rs.getString("p29t44"),
                                             rs.getDate("p30t44"),
                                             rs.getString("p100t44")));
                     }
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return list;

     }


     public Payment getPayment(int paymentId) {

             Payment payment = new Payment();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_payment WHERE id=?");
                     ps.setInt(1, paymentId);
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             payment = new Payment();
                             
                             payment.setId(rs.getLong("id"));
                             payment.setP1t44(rs.getString("p1t44"));
                             payment.setP0t44(rs.getString("p0t44"));
                             payment.setP2t44(rs.getDate("p2t44"));
                             payment.setP3t44(rs.getString("p3t44"));
                             payment.setP4t44(rs.getString("p4t44"));
                             payment.setP5t44(rs.getDate("p5t44"));
                             payment.setP6t44(rs.getString("p6t44"));
                             payment.setP7t44(rs.getString("p7t44"));
                             payment.setP8t44(rs.getString("p8t44"));
                             payment.setP9t44(rs.getString("p9t44"));
                             payment.setP10t44(rs.getString("p10t44"));
                             payment.setP11t44(rs.getString("p11t44"));
                             payment.setP12t44(rs.getString("p12t44"));
                             payment.setP13t44(rs.getString("p13t44"));
                             payment.setP14t44(rs.getString("p14t44"));
                             payment.setP15t44(rs.getString("p15t44"));
                             payment.setP16t44(rs.getDouble("p16t44"));
                             payment.setP17t44(rs.getDouble("p17t44"));
                             payment.setP18t44(rs.getDouble("p18t44"));
                             payment.setP19t44(rs.getDouble("p19t44"));
                             payment.setP20t44(rs.getDouble("p20t44"));
                             payment.setP21t44(rs.getDouble("p21t44"));
                             payment.setP22t44(rs.getString("p22t44"));
                             payment.setP23t44(rs.getString("p23t44"));
                             payment.setP24t44(rs.getString("p24t44"));
                             payment.setId_contract(rs.getLong("id_contract"));
                             payment.setP27t44(rs.getString("p27t44"));
                             payment.setP28t44(rs.getString("p28t44"));
                             payment.setP29t44(rs.getString("p29t44"));
                             payment.setP30t44(rs.getDate("p30t44"));
                             payment.setP100t44(rs.getString("p100t44"));
                     }
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
             return payment;
     }

     public static Payment createBuhg(Payment payment , String idn, Long id_contract)  {

             Connection c = null;
             PreparedStatement ps = null;
             Long aid=new Long ("0");
             try {
                     c = ConnectionPool.getConnection();
                     ps = c.prepareStatement("SELECT SQ_TF_payment.NEXTVAL id FROM DUAL");
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                     aid=(rs.getLong("id"));
                     }
                     ps = c.prepareStatement("INSERT INTO TF_payment (id, p1t44, p0t44, p2t44, p3t44, p4t44, p5t44, p6t44, p7t44, p8t44, p9t44, p10t44, p11t44, p12t44, p13t44, p14t44, p15t44, p16t44, p17t44, p18t44, p19t44, p20t44, p21t44, p22t44, p23t44, p24t44,id_contract, p100t44) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                     ps.setLong(1,aid);
                     ps.setString(2,idn);
                     ps.setString(3,"0");
                     ps.setDate(4,new java.sql.Date(payment.getP2t44().getTime()));
                     ps.setString(5,payment.getP3t44()!=null? payment.getP3t44():null );
                     ps.setString(6,payment.getP4t44()!=null? payment.getP4t44():null );
                     ps.setDate(7,payment.getP5t44()!=null? new java.sql.Date(payment.getP5t44().getTime()):null);
                     ps.setString(8,payment.getP6t44()!=null? payment.getP6t44():null );
                     ps.setString(9,payment.getP7t44()!=null? payment.getP7t44():null );
                     ps.setString(10,payment.getP8t44()!=null? payment.getP8t44():null );
                     ps.setString(11,payment.getP9t44()!=null? payment.getP9t44():null );
                     ps.setString(12,payment.getP10t44()!=null? payment.getP10t44():null );
                     ps.setString(13,payment.getP11t44()!=null? payment.getP11t44():null );
                     ps.setString(14,payment.getP12t44()!=null? payment.getP12t44():null );
                     ps.setString(15,payment.getP13t44()!=null? payment.getP13t44():null );
                     ps.setString(16,payment.getP14t44()!=null? payment.getP14t44():null );
                     ps.setString(17,payment.getP15t44());
                     ps.setDouble(18,payment.getP16t44());
                     ps.setDouble(19,payment.getP17t44());
                     ps.setDouble(20,payment.getP18t44());
                     ps.setDouble(21,payment.getP19t44()!=null? payment.getP19t44():null );
                     ps.setDouble(22,payment.getP20t44()!=null? payment.getP20t44():null );
                     ps.setDouble(23,payment.getP21t44()!=null? payment.getP21t44():null );
                     ps.setString(24,payment.getP22t44()!=null? payment.getP22t44():null );
                     ps.setString(25,payment.getP23t44());
                     ps.setString(26,payment.getP24t44());
                     ps.setLong(27,id_contract);
                     ps.setString(28,"9");
                     //ps.setString(29,payment.getP28t44());
                     //ps.setString(30,payment.getP29t44());
                     //ps.setDate(31,new java.sql.Date(payment.getP30t44().getTime()));
                     //ps.setString(32,payment.getP100t44());
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return payment;
     }
     
     public static Payment correctBuhg(Payment payment , String idn, Long id_contract)  {

         Connection c = null;
         PreparedStatement ps = null;
         Long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_payment.NEXTVAL id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                	 aid=(rs.getLong("id"));
                 }
                 ps = c.prepareStatement("INSERT INTO TF_payment (id, p1t44, p0t44, p2t44, p3t44, p4t44, p5t44, p6t44, p7t44, p8t44, p9t44, p10t44, p11t44, p12t44, p13t44, p14t44, p15t44, p16t44, p17t44, p18t44, p19t44, p20t44, p21t44, p22t44, p23t44, p24t44,id_contract, p100t44) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 ps.setLong(1,aid);
                 ps.setString(2,idn);
                 ps.setString(3,"0");
                 ps.setDate(4,new java.sql.Date(payment.getP2t44().getTime()));
                 ps.setString(5,payment.getP3t44()!=null? payment.getP3t44():null );
                 ps.setString(6,payment.getP4t44()!=null? payment.getP4t44():null );
                 ps.setDate(7,payment.getP5t44()!=null? new java.sql.Date(payment.getP5t44().getTime()):null);
                 ps.setString(8,payment.getP6t44()!=null? payment.getP6t44():null );
                 ps.setString(9,payment.getP7t44()!=null? payment.getP7t44():null );
                 ps.setString(10,payment.getP8t44()!=null? payment.getP8t44():null );
                 ps.setString(11,payment.getP9t44()!=null? payment.getP9t44():null );
                 ps.setString(12,payment.getP10t44()!=null? payment.getP10t44():null );
                 ps.setString(13,payment.getP11t44()!=null? payment.getP11t44():null );
                 ps.setString(14,payment.getP12t44()!=null? payment.getP12t44():null );
                 ps.setString(15,payment.getP13t44()!=null? payment.getP13t44():null );
                 ps.setString(16,payment.getP14t44()!=null? payment.getP14t44():null );
                 ps.setString(17,payment.getP15t44());
                 ps.setDouble(18,payment.getP16t44());
                 ps.setDouble(19,payment.getP17t44());
                 ps.setDouble(20,payment.getP18t44());
                 ps.setDouble(21,payment.getP19t44()!=null? payment.getP19t44():null );
                 ps.setDouble(22,payment.getP20t44()!=null? payment.getP20t44():null );
                 ps.setDouble(23,payment.getP21t44()!=null? payment.getP21t44():null );
                 ps.setString(24,payment.getP22t44()!=null? payment.getP22t44():null );
                 ps.setString(25,payment.getP23t44());
                 ps.setString(26,payment.getP24t44());
                 ps.setLong(27,id_contract);
                 ps.setString(28,"8");
                 //ps.setString(29,payment.getP28t44());
                 //ps.setString(30,payment.getP29t44());
                 //ps.setDate(31,new java.sql.Date(payment.getP30t44().getTime()));
                 //ps.setString(32,payment.getP100t44());
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return payment;
 }

     public static Res update(Payment payment )  {
    	 Res res = new Res();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("UPDATE TF_payment SET p0t44=?, p2t44=?, p3t44=?, p4t44=?, p5t44=?, p6t44=?, p7t44=?, p8t44=?, p9t44=?, p10t44=?, p11t44=?, p12t44=?, p13t44=?, p14t44=?, p15t44=?, p16t44=?, p17t44=?, p18t44=?, p19t44=?, p20t44=?, p21t44=?, p22t44=?, p23t44=?, p24t44=?  WHERE id=?  ");
                     
                     
                    // ps.setString(2, P1T44);
                     ps.setString(1,payment.getP0t44());
                     ps.setDate(2,new java.sql.Date(payment.getP2t44().getTime()));
                     ps.setString(3,payment.getP3t44()!=null? payment.getP3t44():null );
                     ps.setString(4,payment.getP4t44()!=null? payment.getP4t44():null );
                     ps.setDate(5,payment.getP5t44()!=null? new java.sql.Date(payment.getP5t44().getTime()):null);
                     ps.setString(6,payment.getP6t44()!=null? payment.getP6t44():null );
                     ps.setString(7,payment.getP7t44()!=null? payment.getP7t44():null );
                     ps.setString(8,payment.getP8t44()!=null? payment.getP8t44():null );
                     ps.setString(9,payment.getP9t44()!=null? payment.getP9t44():null );
                     ps.setString(10,payment.getP10t44()!=null? payment.getP10t44():null );
                     ps.setString(11,payment.getP11t44()!=null? payment.getP11t44():null );
                     ps.setString(12,payment.getP12t44()!=null? payment.getP12t44():null );
                     ps.setString(13,payment.getP13t44()!=null? payment.getP13t44():null );
                     ps.setString(14,payment.getP14t44()!=null? payment.getP14t44():null );
                     ps.setString(15,payment.getP15t44());
                     ps.setDouble(16,payment.getP16t44());
                     ps.setDouble(17,payment.getP17t44());
                     ps.setDouble(18,payment.getP18t44());
                     ps.setDouble(19,payment.getP19t44()!=null? payment.getP19t44():null );
                     ps.setDouble(20,payment.getP20t44()!=null? payment.getP20t44():null );
                     ps.setDouble(21,payment.getP21t44()!=null? payment.getP21t44():null );
                     ps.setString(22,payment.getP22t44());
                     ps.setString(23,payment.getP23t44());
                     ps.setString(24,payment.getP24t44());
                     ps.setLong(25,9);
                     //ps.setLong(27,id_contract);
                    
                     //ps.setString(28,"9");
                    // ps.setString(29,payment.getP28t44());
                     //ps.setString(30,payment.getP29t44());
                     //ps.setDate(31,new java.sql.Date(payment.getP30t44().getTime()));
                     //ps.setString(32,payment.getP100t44());
             		if (ps.executeUpdate() == 1) {
        				c.commit();
        				res = new Res(0, "Ok");
        				System.out.println("Ok");
        			} else {
        				c.rollback();
        				res = new Res(1, "Сохранение невозможно!");
        			    System.out.println("Сохранение невозможно!");
        			}
        		} catch (SQLException e) {
        			e.printStackTrace(); 
        			ISLogger.getLogger().error(CheckNull.getPstr(e));
        			if (e.getMessage() == null) {
        				res = new Res(1, "Сохранение невозможно2! "+CheckNull.getPstr(e));
        				System.out.println("Сохранение невозможно2!"+CheckNull.getPstr(e));
        			} else {
        				res = new Res(1, "Сохранение невозможно3! "+e.getMessage());
        				System.out.println("Сохранение невозможно3!"+e.getMessage());
        			}
        			try {
        				c.rollback();
        			} catch (Exception ex) {
        			
        	ex.printStackTrace();
        			}
        		} finally {
        			ConnectionPool.close(c);
        		}
        		return res;
        	}

     public static Res updateDel(Payment payment )  {
    	 Res res = new Res();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("UPDATE TF_payment SET p0t44=?, p2t44=?, p3t44=?, p4t44=?, p5t44=?, p6t44=?, p7t44=?, p8t44=?, p9t44=?, p10t44=?, p11t44=?, p12t44=?, p13t44=?, p14t44=?, p15t44=?, p16t44=?, p17t44=?, p18t44=?, p19t44=?, p20t44=?, p21t44=?, p22t44=?, p23t44=?, p24t44=? p100t44=?  WHERE id=?  ");
                     
                     
                    // ps.setString(2, P1T44);
                     ps.setString(1,"2");
                     ps.setDate(2,new java.sql.Date(payment.getP2t44().getTime()));
                     ps.setString(3,payment.getP3t44()!=null? payment.getP3t44():null );
                     ps.setString(4,payment.getP4t44()!=null? payment.getP4t44():null );
                     ps.setDate(5,payment.getP5t44()!=null? new java.sql.Date(payment.getP5t44().getTime()):null);
                     ps.setString(6,payment.getP6t44()!=null? payment.getP6t44():null );
                     ps.setString(7,payment.getP7t44()!=null? payment.getP7t44():null );
                     ps.setString(8,payment.getP8t44()!=null? payment.getP8t44():null );
                     ps.setString(9,payment.getP9t44()!=null? payment.getP9t44():null );
                     ps.setString(10,payment.getP10t44()!=null? payment.getP10t44():null );
                     ps.setString(11,payment.getP11t44()!=null? payment.getP11t44():null );
                     ps.setString(12,payment.getP12t44()!=null? payment.getP12t44():null );
                     ps.setString(13,payment.getP13t44()!=null? payment.getP13t44():null );
                     ps.setString(14,payment.getP14t44()!=null? payment.getP14t44():null );
                     ps.setString(15,payment.getP15t44());
                     ps.setDouble(16,payment.getP16t44());
                     ps.setDouble(17,payment.getP17t44());
                     ps.setDouble(18,payment.getP18t44());
                     ps.setDouble(19,payment.getP19t44()!=null? payment.getP19t44():null );
                     ps.setDouble(20,payment.getP20t44()!=null? payment.getP20t44():null );
                     ps.setDouble(21,payment.getP21t44()!=null? payment.getP21t44():null );
                     ps.setString(22,payment.getP22t44());
                     ps.setString(23,payment.getP23t44());
                     ps.setString(24,payment.getP24t44());
                     ps.setLong(25,payment.getId());
                     ps.setString(26,"9");
                     //ps.setLong(27,id_contract);
                    
                     //ps.setString(28,"9");
                    // ps.setString(29,payment.getP28t44());
                     //ps.setString(30,payment.getP29t44());
                     //ps.setDate(31,new java.sql.Date(payment.getP30t44().getTime()));
                     //ps.setString(32,payment.getP100t44());
             		if (ps.executeUpdate() == 1) {
        				c.commit();
        				res = new Res(0, "Ok");
        				System.out.println("Ok");
        			} else {
        				c.rollback();
        				res = new Res(1, "Сохранение невозможно!");
        			    System.out.println("Сохранение невозможно!");
        			}
        		} catch (SQLException e) {
        			e.printStackTrace(); 
        			ISLogger.getLogger().error(CheckNull.getPstr(e));
        			if (e.getMessage() == null) {
        				res = new Res(1, "Сохранение невозможно2! "+CheckNull.getPstr(e));
        				System.out.println("Сохранение невозможно2!"+CheckNull.getPstr(e));
        			} else {
        				res = new Res(1, "Сохранение невозможно3! "+e.getMessage());
        				System.out.println("Сохранение невозможно3!"+e.getMessage());
        			}
        			try {
        				c.rollback();
        			} catch (Exception ex) {
        			
        	ex.printStackTrace();
        			}
        		} finally {
        			ConnectionPool.close(c);
        		}
        		return res;
        	}

     
     public static Res removeBugh(Payment payment, Long id_contract)  {
    	 Res res = new Res();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM tf_payment WHERE id=? and id_contract=? ");
                     ps.setLong(1, payment.getId());
                     ps.setLong(2, id_contract);
                  
                     if (ps.executeUpdate() == 1) {
         				c.commit();
         				res = new Res(0, "Ok");
         				
         			} else {
         				c.rollback();
         				res = new Res(1, "Удаление невозможно!");
         			}
         		
                   
             } catch (Exception e) {
                     e.printStackTrace();
                     
         			System.out.println("Сохранение невозможно2!"+CheckNull.getPstr(e)+"-----"+e.getMessage());
             		
             } finally {
             
            	 ConnectionPool.close(c);
             }
             return res;
     }

     public static void remove(Payment payment, Long id_contract)  {

         Connection c = null;

         try {
                 c = ConnectionPool.getConnection();
                 PreparedStatement ps = c.prepareStatement("DELETE FROM TF_payment WHERE id_contract=?  and p100t44<>9");
                 ps.setLong(1, id_contract);
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
 }






     public static com.sbs.service.Payment create(com.sbs.service.Payment payment, String P1T44, Long id_contract)  {

         Connection c = null;
         PreparedStatement ps = null;
         Long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_Payment.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                 aid=(rs.getLong("id"));
                 }
                 ps = c.prepareStatement("INSERT INTO TF_payment (id, p1t44, p0t44, p2t44, p3t44, p4t44, p5t44, p6t44, p7t44, p8t44, p9t44, p10t44, p11t44, p12t44, p13t44, p14t44, p15t44, p16t44, p17t44, p18t44, p19t44, p20t44, p21t44, p22t44, p23t44, p24t44, id_contract, p27t44, p29t44, p30t44, p100t44) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 ps.setLong(1,aid);
                 ps.setString(2,P1T44);
                 ps.setInt(3,payment.getP0T44()!=null? payment.getP0T44():0);
                 ps.setDate(4,new java.sql.Date(payment.getP2T44().getTimeInMillis()));
                 ps.setInt(5,payment.getP3T44()!=null? payment.getP3T44():0);
                 ps.setString(6,payment.getP4T44());
                 ps.setDate(7,payment.getP5T44()!=null? new java.sql.Date(payment.getP5T44().getTimeInMillis()):null);
                 ps.setString(8,payment.getP6T44());
                 ps.setInt(9,payment.getP7T44()!=null? payment.getP7T44():0);
                 ps.setInt(10,payment.getP8T44()!=null? payment.getP8T44():0);
                 ps.setInt(11,payment.getP9T44()!=null? payment.getP9T44():0);
                 ps.setInt(12,payment.getP10T44()!=null? payment.getP10T44():0);
                 ps.setString(13,payment.getP11T44());
                 ps.setInt(14,payment.getP12T44()!=null? payment.getP12T44():0);
                 ps.setString(15,payment.getP13T44()!=null? payment.getP13T44():null);
                 ps.setString(16,payment.getP14T44()!=null? payment.getP14T44():null);
                 ps.setString(17,payment.getP15T44());
                 ps.setDouble(18,payment.getP16T44());
                 ps.setDouble(19,payment.getP17T44());
                 ps.setDouble(20,payment.getP18T44());
                 ps.setDouble(21,payment.getP19T44());
                 ps.setDouble(22,payment.getP20T44());
                 ps.setDouble(23,payment.getP21T44());
                 ps.setInt(24,payment.getP22T44()!=null? payment.getP22T44():0);
                 ps.setString(25,payment.getP23T44());
                 ps.setString(26,payment.getP24T44());
                 ps.setLong(27,id_contract);
                 ps.setShort(28,payment.getP27T44());
                 //ps.setS(29,payment.getP28T44()!=null? payment.getP28T44():0);
                 ps.setString(29,payment.getP29T44()!=null? payment.getP29T44():null);
                 ps.setDate(30,new java.sql.Date(payment.getP30T44().getTimeInMillis()));
                 if (payment.getP100T44()!=null){
                 ps.setShort(31,payment.getP100T44());
                 } else {
                	 ps.setString(31,null);
                 }
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return payment;
 }
     public static RefCurrencyData getRefCurrencyData(String val, List<RefCurrencyData> dp) {
       	RefCurrencyData res = null;
   	    if ( dp != null ) {
   	        for (int i = 0; i < dp.size(); i++) {
   	            if ( val.equals(dp.get(i).getKod())) {
   	                res = dp.get(i);
   	    }    }    }
   	    return res;
   	}



}
