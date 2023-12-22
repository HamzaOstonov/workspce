package com.is.tf.fund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tf.payment.Payment;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class FundService {
	 private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
     private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
     private static String msql ="SELECT * FROM TF_Fund ";


     public List<Fund> getFund()  {

             List<Fund> list = new ArrayList<Fund>();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     Statement s = c.createStatement();
                     ResultSet rs = s.executeQuery("SELECT * FROM TF_Fund");
                     while (rs.next()) {
                             list.add(new Fund(
                                             rs.getLong("id"),
                                             rs.getString("p0t35"),
                                             rs.getString("p1t35"),
                                             rs.getString("p2t35"),
                                             rs.getString("p3t35"),
                                             rs.getDate("p4t35"),
                                             rs.getString("p5t35"),
                                             rs.getString("p6t35"),
                                             rs.getDate("p7t35"),
                                             rs.getString("p8t35"),
                                             rs.getString("p9t35"),
                                             rs.getString("p10t35"),
                                             rs.getString("p11t35"),
                                             rs.getString("p12t35"),
                                             rs.getString("p13t35"),
                                             rs.getDouble("p14t35"),
                                             rs.getDouble("p15t35"),
                                             rs.getDouble("p16t35"),
                                             rs.getDouble("p17t35"),
                                             rs.getDouble("p18t35"),
                                             rs.getDouble("p19t35"),
                                             rs.getString("p20t35"),
                                             rs.getDouble("p21t35"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p23t35"),
                                             rs.getString("p24t35"),
                                             rs.getString("p25t35"),
                                             rs.getDate("p26t35"),
                                             rs.getString("p100t35")
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

     private static List<FilterField> getFilterFields(FundFilter filter){
             List<FilterField> flfields = new ArrayList<FilterField>();


           if(!CheckNull.isEmpty(filter.getId())){
                   flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
           }
           if(!CheckNull.isEmpty(filter.getP1t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p1t35=?",filter.getP1t35()));
           }
           if(!CheckNull.isEmpty(filter.getP0t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p0t35=?",filter.getP0t35()));
           }
       
           if(!CheckNull.isEmpty(filter.getP2t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p2t35=?",filter.getP2t35()));
           }
           if(!CheckNull.isEmpty(filter.getP3t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p3t35=?",filter.getP3t35()));
           }
           if(!CheckNull.isEmpty(filter.getP4t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p4t35=?",filter.getP4t35()));
           }
           if(!CheckNull.isEmpty(filter.getP5t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p5t35=?",filter.getP5t35()));
           }
           if(!CheckNull.isEmpty(filter.getP6t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p6t35=?",filter.getP6t35()));
           }
           if(!CheckNull.isEmpty(filter.getP7t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p7t35=?",filter.getP7t35()));
           }
           if(!CheckNull.isEmpty(filter.getP8t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p8t35=?",filter.getP8t35()));
           }
           if(!CheckNull.isEmpty(filter.getP9t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p9t35=?",filter.getP9t35()));
           }
           if(!CheckNull.isEmpty(filter.getP10t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p10t35=?",filter.getP10t35()));
           }
           if(!CheckNull.isEmpty(filter.getP11t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p11t35=?",filter.getP11t35()));
           }
           if(!CheckNull.isEmpty(filter.getP12t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p12t35=?",filter.getP12t35()));
           }
           if(!CheckNull.isEmpty(filter.getP13t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p13t35=?",filter.getP13t35()));
           }
           if(!CheckNull.isEmpty(filter.getP14t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p14t35=?",filter.getP14t35()));
           }
           if(!CheckNull.isEmpty(filter.getP15t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p15t35=?",filter.getP15t35()));
           }
           if(!CheckNull.isEmpty(filter.getP16t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p16t35=?",filter.getP16t35()));
           }
           if(!CheckNull.isEmpty(filter.getP17t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p17t35=?",filter.getP17t35()));
           }
           if(!CheckNull.isEmpty(filter.getP18t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p18t35=?",filter.getP18t35()));
           }
           if(!CheckNull.isEmpty(filter.getP19t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p19t35=?",filter.getP19t35()));
           }
           if(!CheckNull.isEmpty(filter.getP20t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p20t35=?",filter.getP20t35()));
           }
           if(!CheckNull.isEmpty(filter.getP21t35())){
                   flfields.add(new FilterField(getCond(flfields)+ "p21t35=?",filter.getP21t35()));
           }
           if(!CheckNull.isEmpty(filter.getId_contract())){
               flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
       }
       if(!CheckNull.isEmpty(filter.getP23t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p23t35=?",filter.getP23t35()));
       }
       if(!CheckNull.isEmpty(filter.getP24t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p24t35=?",filter.getP24t35()));
       }
       if(!CheckNull.isEmpty(filter.getP25t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p25t35=?",filter.getP25t35()));
       }
       if(!CheckNull.isEmpty(filter.getP26t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p26t35=?",filter.getP26t35()));
       }
       if(!CheckNull.isEmpty(filter.getP100t35())){
               flfields.add(new FilterField(getCond(flfields)+ "p100t35=?",filter.getP100t35()));
       }
           flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

             return flfields;
     }


     public static int getCount(FundFilter filter)  {

         Connection c = null;
         int n = 0;
         List<FilterField> flFields =getFilterFields(filter);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT count(*) ct FROM TF_Fund ");
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



     public static List<Fund> getFundsFl(int pageIndex, int pageSize, FundFilter filter)  {

             List<Fund> list = new ArrayList<Fund>();
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
                             list.add(new Fund(
                                             rs.getLong("id"),
                                             rs.getString("p0t35"),
                                             rs.getString("p1t35"),
                                             rs.getString("p2t35"),
                                             rs.getString("p3t35"),
                                             rs.getDate("p4t35"),
                                             rs.getString("p5t35"),
                                             rs.getString("p6t35"),
                                             rs.getDate("p7t35"),
                                             rs.getString("p8t35"),
                                             rs.getString("p9t35"),
                                             rs.getString("p10t35"),
                                             rs.getString("p11t35"),
                                             rs.getString("p12t35"),
                                             rs.getString("p13t35"),
                                             rs.getDouble("p14t35"),
                                             rs.getDouble("p15t35"),
                                             rs.getDouble("p16t35"),
                                             rs.getDouble("p17t35"),
                                             rs.getDouble("p18t35"),
                                             rs.getDouble("p19t35"),
                                             rs.getString("p20t35"),
                                             rs.getDouble("p21t35"),
                                             rs.getLong("id_contract"),
                                             rs.getString("p23t35"),
                                             rs.getString("p24t35"),
                                             rs.getString("p25t35"),
                                             rs.getDate("p26t35"),
                                             rs.getString("p100t35")));
                     }
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return list;

     }


     public Fund getFund(int fundId) {

             Fund fund = new Fund();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_fund WHERE id=?");
                     ps.setInt(1, fundId);
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                             fund = new Fund();
                             
                             fund.setId(rs.getLong("id"));
                             fund.setP0t35(rs.getString("p0t35"));
                             fund.setP1t35(rs.getString("p1t35"));
                             fund.setP2t35(rs.getString("p2t35"));
                             fund.setP3t35(rs.getString("p3t35"));
                             fund.setP4t35(rs.getDate("p4t35"));
                             fund.setP5t35(rs.getString("p5t35"));
                             fund.setP6t35(rs.getString("p6t35"));
                             fund.setP7t35(rs.getDate("p7t35"));
                             fund.setP8t35(rs.getString("p8t35"));
                             fund.setP9t35(rs.getString("p9t35"));
                             fund.setP10t35(rs.getString("p10t35"));
                             fund.setP11t35(rs.getString("p11t35"));
                             fund.setP12t35(rs.getString("p12t35"));
                             fund.setP13t35(rs.getString("p13t35"));
                             fund.setP14t35(rs.getDouble("p14t35"));
                             fund.setP15t35(rs.getDouble("p15t35"));
                             fund.setP16t35(rs.getDouble("p16t35"));
                             fund.setP17t35(rs.getDouble("p17t35"));
                             fund.setP18t35(rs.getDouble("p18t35"));
                             fund.setP19t35(rs.getDouble("p19t35"));
                             fund.setP20t35(rs.getString("p20t35"));
                             fund.setP21t35(rs.getDouble("p21t35"));
                             fund.setId_contract(rs.getLong("id_contract"));
                             fund.setP23t35(rs.getString("p23t35"));
                             fund.setP24t35(rs.getString("p24t35"));
                             fund.setP25t35(rs.getString("p25t35"));
                             fund.setP26t35(rs.getDate("p26t35"));
                             fund.setP100t35(rs.getString("p100t35"));
                     }
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
             return fund;
     }

     public static Res createBuhg(Fund fund , String idn, Long id_contract)  {
    	     Res res = new Res();
             Connection c = null;
             PreparedStatement ps = null;
             Long aid=new Long ("0");
             try {
                     c = ConnectionPool.getConnection();
                     ps = c.prepareStatement("SELECT SQ_TF_Fund.Nextval id FROM DUAL");
                     ResultSet rs = ps.executeQuery();
                     if (rs.next()) {
                     aid=(rs.getLong("id"));
                     }
                     ps = c.prepareStatement("INSERT INTO TF_fund (id,  p1t35, p0t35, p2t35, p3t35, p4t35, p5t35, p6t35, p7t35, p8t35, p9t35, p10t35, p11t35, p12t35, p13t35, p14t35, p15t35, p16t35, p17t35, p18t35, p19t35, p20t35, id_contract, p23t35, p100t35 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                     
                     ps.setLong(1,aid);
                     ps.setString(2,idn);
                     ps.setString(3,"0");
                     ps.setString(4,fund.getP2t35()!=null? fund.getP2t35():null);
                     ps.setString(5,fund.getP3t35()!=null? fund.getP3t35():null);
                     ps.setDate(6,new java.sql.Date(fund.getP4t35().getTime()));
                     ps.setString(7,fund.getP5t35()!=null? fund.getP5t35():null);
                     ps.setString(8,fund.getP6t35()!=null? fund.getP6t35():null);
                     ps.setDate(9,fund.getP7t35()!=null? new java.sql.Date(fund.getP7t35().getTime()):null);
                     ps.setString(10,fund.getP8t35()!=null? fund.getP8t35():null);
                     ps.setString(11,fund.getP9t35()!=null? fund.getP9t35():null);
                     ps.setString(12,fund.getP10t35()!=null? fund.getP10t35():null);
                     ps.setString(13,fund.getP11t35()!=null? fund.getP11t35():null);
                     ps.setString(14,fund.getP12t35()!=null? fund.getP12t35():null);
                     ps.setString(15,fund.getP13t35()!=null? fund.getP13t35():null);
                     ps.setDouble(16,fund.getP14t35()!=null? fund.getP14t35():null);
                     ps.setDouble(17,fund.getP15t35()!=null? fund.getP15t35():null);
                     ps.setDouble(18,fund.getP16t35()!=null? fund.getP16t35():null);
                     ps.setDouble(19,fund.getP17t35()!=null? fund.getP17t35():null);
                     ps.setDouble(20,fund.getP18t35()!=null? fund.getP18t35():null);
                     ps.setDouble(21,fund.getP19t35()!=null? fund.getP19t35():null);
                     ps.setString(22,fund.getP20t35()!=null? fund.getP20t35():null);
                     ps.setLong(23,id_contract);
                     ps.setString(24,fund.getP23t35());
                     ps.setString(25,"9");
                     if (ps.executeUpdate() == 1) {
         				c.commit();
         				res = new Res(0, "Ok");
         				System.out.println("Ok");
         			} else {
         				c.rollback();
         				res = new Res(1, "Сохранение невозможно!");
         			    System.out.println("Сохранение невозможно!");
         			}
                     
             } catch (Exception e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return res;
     }

     public static Res update(Fund fund)  {
    	     Res res = new Res();
             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("UPDATE TF_fund SET p0t35=?, p2t35=?, p3t35=?, p4t35=?, p5t35=?, p6t35=?, p7t35=?, p8t35=?, p9t35=?, p10t35=?, p11t35=?, p12t35=?, p13t35=?, p14t35=?, p15t35=?, p16t35=?, p17t35=?, p18t35=?, p19t35=?, p20t35=?, p23t35=?, p25t35=?, p26t35=?, p100t35=?  WHERE id=?");
                     
                     
                     //ps.setString(2,fund.getP1t35());
                     ps.setString(1,fund.getP0t35());
                     ps.setString(2,fund.getP2t35()!=null? fund.getP2t35():null);
                     ps.setString(3,fund.getP3t35()!=null? fund.getP3t35():null);
                     ps.setDate(4,new java.sql.Date(fund.getP4t35().getTime()));
                     ps.setString(5,fund.getP5t35()!=null? fund.getP5t35():null);
                     ps.setString(6,fund.getP6t35()!=null? fund.getP6t35():null);
                     ps.setDate(7,fund.getP7t35()!=null? new java.sql.Date(fund.getP7t35().getTime()):null);
                     ps.setString(8,fund.getP8t35()!=null? fund.getP8t35():null);
                     ps.setString(9,fund.getP9t35()!=null? fund.getP9t35():null);
                     ps.setString(10,fund.getP10t35()!=null? fund.getP10t35():null);
                     ps.setString(11,fund.getP11t35()!=null? fund.getP11t35():null);
                     ps.setString(12,fund.getP12t35()!=null? fund.getP12t35():null);
                     ps.setString(13,fund.getP13t35()!=null? fund.getP13t35():null);
                     ps.setDouble(14,fund.getP14t35()!=null? fund.getP14t35():null);
                     ps.setDouble(15,fund.getP15t35()!=null? fund.getP15t35():null);
                     ps.setDouble(16,fund.getP16t35()!=null? fund.getP16t35():null);
                     ps.setDouble(17,fund.getP17t35()!=null? fund.getP17t35():null);
                     ps.setDouble(18,fund.getP18t35()!=null? fund.getP18t35():null);
                     ps.setDouble(19,fund.getP19t35()!=null? fund.getP19t35():null);
                     ps.setString(20,fund.getP20t35()!=null? fund.getP20t35():null);
                     ps.setString(21,fund.getP23t35()!=null? fund.getP23t35():null);
                     ps.setString(22,fund.getP25t35()!=null? fund.getP25t35():null);
                     ps.setDate(23,fund.getP26t35()!=null? new java.sql.Date(fund.getP26t35().getTime()):null);
                     ps.setString(24,"9");
                     ps.setLong(25,fund.getId());
                     if (ps.executeUpdate() == 1) {
          				c.commit();
          				res = new Res(0, "Ok");
          				System.out.println("Ok");
          			} else {
          				c.rollback();
          				res = new Res(1, "Изменение невозможно!");
          			    System.out.println("Изменение невозможно!");
          			}
             } catch (SQLException e) {
                     e.printStackTrace();

             } finally {
                     ConnectionPool.close(c);
             }
             return res;
     }
     
     public static Res updateDel(Fund fund)  {
	     Res res = new Res();
         Connection c = null;

         try {
                 c = ConnectionPool.getConnection();
                 PreparedStatement ps = c.prepareStatement("UPDATE TF_fund SET p0t35=?, p2t35=?, p3t35=?, p4t35=?, p5t35=?, p6t35=?, p7t35=?, p8t35=?, p9t35=?, p10t35=?, p11t35=?, p12t35=?, p13t35=?, p14t35=?, p15t35=?, p16t35=?, p17t35=?, p18t35=?, p19t35=?, p20t35=?, p23t35=?, p25t35=?, p26t35=?, p100t35=? WHERE id=?");
                 
                 
                 //ps.setString(2,fund.getP1t35());
                 ps.setString(1,"2");
                 ps.setString(2,fund.getP2t35()!=null? fund.getP2t35():null);
                 ps.setString(3,fund.getP3t35()!=null? fund.getP3t35():null);
                 ps.setDate(4,new java.sql.Date(fund.getP4t35().getTime()));
                 ps.setString(5,fund.getP5t35()!=null? fund.getP5t35():null);
                 ps.setString(6,fund.getP6t35()!=null? fund.getP6t35():null);
                 ps.setDate(7,fund.getP7t35()!=null? new java.sql.Date(fund.getP7t35().getTime()):null);
                 ps.setString(8,fund.getP8t35()!=null? fund.getP8t35():null);
                 ps.setString(9,fund.getP9t35()!=null? fund.getP9t35():null);
                 ps.setString(10,fund.getP10t35()!=null? fund.getP10t35():null);
                 ps.setString(11,fund.getP11t35()!=null? fund.getP11t35():null);
                 ps.setString(12,fund.getP12t35()!=null? fund.getP12t35():null);
                 ps.setString(13,fund.getP13t35()!=null? fund.getP13t35():null);
                 ps.setDouble(14,fund.getP14t35()!=null? fund.getP14t35():null);
                 ps.setDouble(15,fund.getP15t35()!=null? fund.getP15t35():null);
                 ps.setDouble(16,fund.getP16t35()!=null? fund.getP16t35():null);
                 ps.setDouble(17,fund.getP17t35()!=null? fund.getP17t35():null);
                 ps.setDouble(18,fund.getP18t35()!=null? fund.getP18t35():null);
                 ps.setDouble(19,fund.getP19t35()!=null? fund.getP19t35():null);
                 ps.setString(20,fund.getP20t35()!=null? fund.getP20t35():null);
                 ps.setString(21,fund.getP23t35()!=null? fund.getP23t35():null);
                 ps.setString(22,fund.getP25t35()!=null? fund.getP25t35():null);
                 ps.setDate(23,fund.getP26t35()!=null? new java.sql.Date(fund.getP26t35().getTime()):null);
                 ps.setString(24,"9");
                 ps.setLong(25,fund.getId());
                 if (ps.executeUpdate() == 1) {
      				c.commit();
      				res = new Res(0, "Ok");
      				System.out.println("Ok");
      			} else {
      				c.rollback();
      				res = new Res(1, "Изменение невозможно!");
      			    System.out.println("Изменение невозможно!");
      			}
         } catch (SQLException e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return res;
 }

     public static Res updateCorrect(Fund fund)  {
	     Res res = new Res();
         Connection c = null;

         try {
                 c = ConnectionPool.getConnection();
                 PreparedStatement ps = c.prepareStatement("UPDATE TF_fund SET p0t35=?, p2t35=?, p3t35=?, p4t35=?, p5t35=?, p6t35=?, p7t35=?, p8t35=?, p9t35=?, p10t35=?, p11t35=?, p12t35=?, p13t35=?, p14t35=?, p15t35=?, p16t35=?, p17t35=?, p18t35=?, p19t35=?, p20t35=?, p23t35=?, p25t35=?, p26t35=?, p100t35=? WHERE id=?");
                 
                 
                 //ps.setString(2,fund.getP1t35());
                 ps.setString(1,"1");
                 ps.setString(2,fund.getP2t35()!=null? fund.getP2t35():null);
                 ps.setString(3,fund.getP3t35()!=null? fund.getP3t35():null);
                 ps.setDate(4,new java.sql.Date(fund.getP4t35().getTime()));
                 ps.setString(5,fund.getP5t35()!=null? fund.getP5t35():null);
                 ps.setString(6,fund.getP6t35()!=null? fund.getP6t35():null);
                 ps.setDate(7,fund.getP7t35()!=null? new java.sql.Date(fund.getP7t35().getTime()):null);
                 ps.setString(8,fund.getP8t35()!=null? fund.getP8t35():null);
                 ps.setString(9,fund.getP9t35()!=null? fund.getP9t35():null);
                 ps.setString(10,fund.getP10t35()!=null? fund.getP10t35():null);
                 ps.setString(11,fund.getP11t35()!=null? fund.getP11t35():null);
                 ps.setString(12,fund.getP12t35()!=null? fund.getP12t35():null);
                 ps.setString(13,fund.getP13t35()!=null? fund.getP13t35():null);
                 ps.setDouble(14,fund.getP14t35()!=null? fund.getP14t35():null);
                 ps.setDouble(15,fund.getP15t35()!=null? fund.getP15t35():null);
                 ps.setDouble(16,fund.getP16t35()!=null? fund.getP16t35():null);
                 ps.setDouble(17,fund.getP17t35()!=null? fund.getP17t35():null);
                 ps.setDouble(18,fund.getP18t35()!=null? fund.getP18t35():null);
                 ps.setDouble(19,fund.getP19t35()!=null? fund.getP19t35():null);
                 ps.setString(20,fund.getP20t35()!=null? fund.getP20t35():null);
                 ps.setString(21,fund.getP23t35()!=null? fund.getP23t35():null);
                 ps.setString(22,fund.getP25t35()!=null? fund.getP25t35():null);
                 ps.setDate(23,fund.getP26t35()!=null? new java.sql.Date(fund.getP26t35().getTime()):null);
                 ps.setLong(24,fund.getId());
                 ps.setString(25,"9");
                 if (ps.executeUpdate() == 1) {
      				c.commit();
      				res = new Res(0, "Ok");
      				System.out.println("Ok");
      			} else {
      				c.rollback();
      				res = new Res(1, "Изменение невозможно!");
      			    System.out.println("Изменение невозможно!");
      			}
         } catch (SQLException e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return res;
 }

     public static void remove(Fund fund, Long id_contract)  {

             Connection c = null;

             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM TF_fund WHERE id_contract=? and p100t35<>9");
                     ps.setLong(1, id_contract);
                     ps.executeUpdate();
                     c.commit();
             } catch (Exception e) {
                     e.printStackTrace();
             } finally {
                     ConnectionPool.close(c);
             }
     }

     public static Res removeBugh(Fund fund, Long id_contract)  {
    	 Res res = new Res();
             Connection c = null;
             try {
                     c = ConnectionPool.getConnection();
                     PreparedStatement ps = c.prepareStatement("DELETE FROM TF_fund WHERE id=? and id_contract=? ");
                     ps.setLong(1, fund.getId());
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
         			//System.out.println("Сохранение невозможно2!"+CheckNull.getPstr(e)+"-----"+e.getMessage());
             } finally {
            	 ConnectionPool.close(c);
             }
              return res;
           }




     public static com.sbs.service.Fund create(com.sbs.service.Fund fund, String P1T35,Long id_contract)  {

         Connection c = null;
         PreparedStatement ps = null;
         Long aid=new Long("0");
        
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_Fund.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                 aid=(rs.getLong("id"));
                 
                 }
                 ps = c.prepareStatement("INSERT INTO TF_fund (id,  p1t35, p0t35, p2t35, p3t35, p4t35, p5t35, p6t35, p7t35, p8t35, p9t35, p10t35, p11t35, p12t35, p13t35, p14t35, p15t35, p16t35, p17t35, p18t35, p19t35, p20t35, id_contract, p23t35, p25t35, p26t35, p100t35 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                 
                 ps.setLong(1,aid);
                 ps.setString(2,P1T35);
                 ps.setInt(3,fund.getP0T35()!=null? fund.getP0T35():0);
                 ps.setInt(4,fund.getP2T35());
                 ps.setString(5,fund.getP3T35()!=null? fund.getP3T35():null);
                 ps.setDate(6,new java.sql.Date(fund.getP4T35().getTimeInMillis()));
                 ps.setInt(7,fund.getP5T35()!=null? fund.getP5T35():0);
                 ps.setString(8,fund.getP6T35()!=null? fund.getP6T35():null);
                 ps.setDate(9,fund.getP7T35() !=null? new java.sql.Date(fund.getP7T35().getTimeInMillis()):null);
                 ps.setString(10,fund.getP8T35()!=null? fund.getP8T35():null);
                 ps.setInt(11,fund.getP9T35()!=null? fund.getP9T35():0);
                 ps.setString(12,fund.getP10T35()!=null? fund.getP10T35():null);
                 ps.setString(13,fund.getP11T35());
                 ps.setString(14,fund.getP12T35());
                 ps.setString(15,fund.getP13T35()!=null? fund.getP13T35():null);
                 ps.setDouble(16,fund.getP14T35()!=null? fund.getP14T35():0);
                 ps.setDouble(17,fund.getP15T35()!=null? fund.getP15T35():0);
                 ps.setDouble(18,fund.getP16T35()!=null? fund.getP16T35():0);
                 ps.setDouble(19,fund.getP17T35()!=null? fund.getP17T35():0);
                 ps.setDouble(20,fund.getP18T35()!=null? fund.getP18T35():0);
                 ps.setDouble(21,fund.getP19T35()!=null? fund.getP19T35():0);
                 ps.setString(22,fund.getP20T35());
                 ps.setLong(23,id_contract);
                 ps.setShort(24,fund.getP23T35()!=null? fund.getP23T35():0);
                 //ps.setShort(25,fund.getP24T35()!=null? fund.getP24T35():0);
                 ps.setString(25,fund.getP25T35()!=null? fund.getP25T35():null);
                 ps.setDate(26,new java.sql.Date(fund.getP26T35().getTimeInMillis()));
                 ps.setShort(27,fund.getP100T35());
                 
                 ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();

         } finally {
                 ConnectionPool.close(c);
         }
         return fund;
 }
     
}
