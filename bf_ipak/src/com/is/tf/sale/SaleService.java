package com.is.tf.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class SaleService {
	   private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
       private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
       private static String msql ="SELECT * FROM TF_Sale ";


       public List<Sale> getSale()  {

               List<Sale> list = new ArrayList<Sale>();
               Connection c = null;

               try {
                       c = ConnectionPool.getConnection();
                       Statement s = c.createStatement();
                       ResultSet rs = s.executeQuery("SELECT * FROM TF_Sale");
                       while (rs.next()) {
                               list.add(new Sale(
                                               rs.getLong("id"),
                                               rs.getString("p1t43"),
                                               rs.getString("p0t43"),
                                               rs.getString("p2t43"),
                                               rs.getString("p3t43"),
                                               rs.getDate("p4t43"),
                                               rs.getString("p5t43"),
                                               rs.getDouble("p6t43"),
                                               rs.getString("p7t43"),
                                               rs.getString("p8t43"),
                                               rs.getDouble("p9t43"),
                                               rs.getString("p10t43"),
                                               rs.getString("p11t43"),
                                               rs.getString("p12t43"),
                                               rs.getString("p13t43"),
                                               rs.getString("p14t43"),
                                               rs.getString("p15t43"),
                                               rs.getString("p16t43"),
                                               rs.getString("p17t43"),
                                               rs.getString("p18t43"),
                                               rs.getString("p19t43"),
                                               rs.getDate("p20t43"),
                                               rs.getString("p21t43"),
                                               rs.getDouble("p22t43"),
                                               rs.getDouble("p23t43"),
                                               rs.getDouble("p24t43"),
                                               rs.getDate("p25t43"),
                                               rs.getString("p26t43"),
                                               rs.getLong("id_contract"),
                                               rs.getDouble("p28t43"),
                                               rs.getString("p29t43"),
                                               rs.getString("p30t43"),
                                               rs.getDate("p32t43"),
                                               rs.getString("p100t43"),
                                               rs.getString("p101t43"),
                                               rs.getString("p27t43")));
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

       private static List<FilterField> getFilterFields(SaleFilter filter){
               List<FilterField> flfields = new ArrayList<FilterField>();


             if(!CheckNull.isEmpty(filter.getId())){
                     flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
             }
             if(!CheckNull.isEmpty(filter.getP1t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p1t43=?",filter.getP1t43()));
             }
             if(!CheckNull.isEmpty(filter.getP0t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p0t43=?",filter.getP0t43()));
             }
             if(!CheckNull.isEmpty(filter.getP2t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p2t43=?",filter.getP2t43()));
             }
             if(!CheckNull.isEmpty(filter.getP3t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p3t43=?",filter.getP3t43()));
             }
             if(!CheckNull.isEmpty(filter.getP4t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p4t43=?",filter.getP4t43()));
             }
             if(!CheckNull.isEmpty(filter.getP5t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p5t43=?",filter.getP5t43()));
             }
             if(!CheckNull.isEmpty(filter.getP6t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p6t43=?",filter.getP6t43()));
             }
             if(!CheckNull.isEmpty(filter.getP7t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p7t43=?",filter.getP7t43()));
             }
             if(!CheckNull.isEmpty(filter.getP8t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p8t43=?",filter.getP8t43()));
             }
             if(!CheckNull.isEmpty(filter.getP9t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p9t43=?",filter.getP9t43()));
             }
             if(!CheckNull.isEmpty(filter.getP10t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p10t43=?",filter.getP10t43()));
             }
             if(!CheckNull.isEmpty(filter.getP11t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p11t43=?",filter.getP11t43()));
             }
             if(!CheckNull.isEmpty(filter.getP12t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p12t43=?",filter.getP12t43()));
             }
             if(!CheckNull.isEmpty(filter.getP13t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p13t43=?",filter.getP13t43()));
             }
             if(!CheckNull.isEmpty(filter.getP14t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p14t43=?",filter.getP14t43()));
             }
             if(!CheckNull.isEmpty(filter.getP15t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p15t43=?",filter.getP15t43()));
             }
             if(!CheckNull.isEmpty(filter.getP16t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p16t43=?",filter.getP16t43()));
             }
             if(!CheckNull.isEmpty(filter.getP17t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p17t43=?",filter.getP17t43()));
             }
             if(!CheckNull.isEmpty(filter.getP18t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p18t43=?",filter.getP18t43()));
             }
             if(!CheckNull.isEmpty(filter.getP19t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p19t43=?",filter.getP19t43()));
             }
             if(!CheckNull.isEmpty(filter.getP20t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p20t43=?",filter.getP20t43()));
             }
             if(!CheckNull.isEmpty(filter.getP21t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p21t43=?",filter.getP21t43()));
             }
             if(!CheckNull.isEmpty(filter.getP22t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p22t43=?",filter.getP22t43()));
             }
             if(!CheckNull.isEmpty(filter.getP23t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p23t43=?",filter.getP23t43()));
             }
             if(!CheckNull.isEmpty(filter.getP24t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p24t43=?",filter.getP24t43()));
             }
             if(!CheckNull.isEmpty(filter.getP25t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p25t43=?",filter.getP25t43()));
             }
             if(!CheckNull.isEmpty(filter.getP26t43())){
                     flfields.add(new FilterField(getCond(flfields)+ "p26t43=?",filter.getP26t43()));
             }

             flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

               return flfields;
       }


       public static int getCount(SaleFilter filter)  {

           Connection c = null;
           int n = 0;
           List<FilterField> flFields =getFilterFields(filter);
           StringBuffer sql = new StringBuffer();
           sql.append("SELECT count(*) ct FROM TF_Sale ");
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



       public static List<Sale> getSalesFl(int pageIndex, int pageSize, SaleFilter filter)  {

               List<Sale> list = new ArrayList<Sale>();
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
                               list.add(new Sale(
                                               rs.getLong("id"),
                                               rs.getString("p1t43"),
                                               rs.getString("p0t43"),
                                               rs.getString("p2t43"),
                                               rs.getString("p3t43"),
                                               rs.getDate("p4t43"),
                                               rs.getString("p5t43"),
                                               rs.getDouble("p6t43"),
                                               rs.getString("p7t43"),
                                               rs.getString("p8t43"),
                                               rs.getDouble("p9t43"),
                                               rs.getString("p10t43"),
                                               rs.getString("p11t43"),
                                               rs.getString("p12t43"),
                                               rs.getString("p13t43"),
                                               rs.getString("p14t43"),
                                               rs.getString("p15t43"),
                                               rs.getString("p16t43"),
                                               rs.getString("p17t43"),
                                               rs.getString("p18t43"),
                                               rs.getString("p19t43"),
                                               rs.getDate("p20t43"),
                                               rs.getString("p21t43"),
                                               rs.getDouble("p22t43"),
                                               rs.getDouble("p23t43"),
                                               rs.getDouble("p24t43"),
                                               rs.getDate("p25t43"),
                                               rs.getString("p26t43"),
                                               rs.getLong("id_contract"),
                                               rs.getDouble("p28t43"),
                                               rs.getString("p29t43"),
                                               rs.getString("p30t43"),
                                               rs.getDate("p32t43"),
                                               rs.getString("p100t43"),
                                               rs.getString("p101t43"),
                                               rs.getString("p27t43")));
                       }
               } catch (SQLException e) {
                       e.printStackTrace();

               } finally {
                       ConnectionPool.close(c);
               }
               return list;

       }


       public Sale getSale(int saleId) {

               Sale sale = new Sale();
               Connection c = null;

               try {
                       c = ConnectionPool.getConnection();
                       PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_sale WHERE id=?");
                       ps.setInt(1, saleId);
                       ResultSet rs = ps.executeQuery();
                       if (rs.next()) {
                               sale = new Sale();
                               
                               sale.setId(rs.getLong("id"));
                               sale.setP1t43(rs.getString("p1t43"));
                               sale.setP0t43(rs.getString("p0t43"));
                               sale.setP2t43(rs.getString("p2t43"));
                               sale.setP3t43(rs.getString("p3t43"));
                               sale.setP4t43(rs.getDate("p4t43"));
                               sale.setP5t43(rs.getString("p5t43"));
                               sale.setP6t43(rs.getDouble("p6t43"));
                               sale.setP7t43(rs.getString("p7t43"));
                               sale.setP8t43(rs.getString("p8t43"));
                               sale.setP9t43(rs.getDouble("p9t43"));
                               sale.setP10t43(rs.getString("p10t43"));
                               sale.setP11t43(rs.getString("p11t43"));
                               sale.setP12t43(rs.getString("p12t43"));
                               sale.setP13t43(rs.getString("p13t43"));
                               sale.setP14t43(rs.getString("p14t43"));
                               sale.setP15t43(rs.getString("p15t43"));
                               sale.setP16t43(rs.getString("p16t43"));
                               sale.setP17t43(rs.getString("p17t43"));
                               sale.setP18t43(rs.getString("p18t43"));
                               sale.setP19t43(rs.getString("p19t43"));
                               sale.setP20t43(rs.getDate("p20t43"));
                               sale.setP21t43(rs.getString("p21t43"));
                               sale.setP22t43(rs.getDouble("p22t43"));
                               sale.setP23t43(rs.getDouble("p23t43"));
                               sale.setP24t43(rs.getDouble("p24t43"));
                               sale.setP25t43(rs.getDate("p25t43"));
                               sale.setP26t43(rs.getString("p26t43"));
                               sale.setId_contract(rs.getLong("id_contract"));
                               sale.setP28t43(rs.getDouble("p28t43"));
                               sale.setP29t43(rs.getString("p29t43"));
                               sale.setP30t43(rs.getString("p30t43"));
                               sale.setP32t43(rs.getDate("p32t43"));
                               sale.setP100t43(rs.getString("p100t43"));
                               sale.setP101t43(rs.getString("p101t43"));
                       }
               } catch (Exception e) {
                       e.printStackTrace();
               } finally {
                       ConnectionPool.close(c);
               }
               return sale;
       }

       public static Sale create(Sale sale)  {

               Connection c = null;
               PreparedStatement ps = null;
               try {
                       c = ConnectionPool.getConnection();
                       ps = c.prepareStatement("SELECT SQ_TF_sale.NEXTVAL id FROM DUAL");
                       ResultSet rs = ps.executeQuery();
                       if (rs.next()) {
                               sale.setId(rs.getLong("id"));
                       }
                       ps = c.prepareStatement("INSERT INTO TF_sale (id, p1t43, p0t43, p2t43, p3t43, p4t43, p5t43, p6t43, p7t43, p8t43, p9t43, p10t43, p11t43, p12t43, p13t43, p14t43, p15t43, p16t43, p17t43, p18t43, p19t43, p20t43, p21t43, p22t43, p23t43, p24t43, p25t43, p26t43, id_contract, p28t43, p29t43, p30t43, p32t43, p100t43, p101t43, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                       
                       ps.setLong(1,sale.getId());
                       ps.setString(2,sale.getP1t43());
                       ps.setString(3,sale.getP0t43());
                       ps.setString(4,sale.getP2t43());
                       ps.setString(5,sale.getP3t43());
                       ps.setDate(6,new java.sql.Date(sale.getP4t43().getTime()));
                       ps.setString(7,sale.getP5t43());
                       ps.setDouble(8,sale.getP6t43());
                       ps.setString(9,sale.getP7t43());
                       ps.setString(10,sale.getP8t43());
                       ps.setDouble(11,sale.getP9t43());
                       ps.setString(12,sale.getP10t43());
                       ps.setString(13,sale.getP11t43());
                       ps.setString(14,sale.getP12t43());
                       ps.setString(15,sale.getP13t43());
                       ps.setString(16,sale.getP14t43());
                       ps.setString(17,sale.getP15t43());
                       ps.setString(18,sale.getP16t43());
                       ps.setString(19,sale.getP17t43());
                       ps.setString(20,sale.getP18t43());
                       ps.setString(21,sale.getP19t43());
                       ps.setDate(22,new java.sql.Date(sale.getP20t43().getTime()));
                       ps.setString(23,sale.getP21t43());
                       ps.setDouble(24,sale.getP22t43());
                       ps.setDouble(25,sale.getP23t43());
                       ps.setDouble(26,sale.getP24t43());
                       ps.setDate(27,new java.sql.Date(sale.getP25t43().getTime()));
                       ps.setString(28,sale.getP26t43());
                       ps.setLong(29,sale.getId_contract());
                       ps.setDouble(30,sale.getP28t43());
                       ps.setString(31,sale.getP29t43());
                       ps.setString(32,sale.getP30t43());
                       ps.setDate(33,new java.sql.Date(sale.getP32t43().getTime()));
                       ps.setString(34,sale.getP100t43());
                       ps.setString(35,sale.getP101t43());
                       ps.executeUpdate();
                       c.commit();
               } catch (Exception e) {
                       e.printStackTrace();

               } finally {
                       ConnectionPool.close(c);
               }
               return sale;
       }

       public static void update(Sale sale)  {

               Connection c = null;

               try {
                       c = ConnectionPool.getConnection();
                       PreparedStatement ps = c.prepareStatement("UPDATE TF_sale SET id=?, p1t43=?, p0t43=?, p2t43=?, p3t43=?, p4t43=?, p5t43=?, p6t43=?, p7t43=?, p8t43=?, p9t43=?, p10t43=?, p11t43=?, p12t43=?, p13t43=?, p14t43=?, p15t43=?, p16t43=?, p17t43=?, p18t43=?, p19t43=?, p20t43=?, p21t43=?, p22t43=?, p23t43=?, p24t43=?, p25t43=?, p26t43=?,  WHERE id=?");
                       
                       ps.setLong(1,sale.getId());
                       ps.setString(2,sale.getP1t43());
                       ps.setString(3,sale.getP0t43());
                       ps.setString(4,sale.getP2t43());
                       ps.setString(5,sale.getP3t43());
                       ps.setDate(6,new java.sql.Date(sale.getP4t43().getTime()));
                       ps.setString(7,sale.getP5t43());
                       ps.setDouble(8,sale.getP6t43());
                       ps.setString(9,sale.getP7t43());
                       ps.setString(10,sale.getP8t43());
                       ps.setDouble(11,sale.getP9t43());
                       ps.setString(12,sale.getP10t43());
                       ps.setString(13,sale.getP11t43());
                       ps.setString(14,sale.getP12t43());
                       ps.setString(15,sale.getP13t43());
                       ps.setString(16,sale.getP14t43());
                       ps.setString(17,sale.getP15t43());
                       ps.setString(18,sale.getP16t43());
                       ps.setString(19,sale.getP17t43());
                       ps.setString(20,sale.getP18t43());
                       ps.setString(21,sale.getP19t43());
                       ps.setDate(22,new java.sql.Date(sale.getP20t43().getTime()));
                       ps.setString(23,sale.getP21t43());
                       ps.setDouble(24,sale.getP22t43());
                       ps.setDouble(25,sale.getP23t43());
                       ps.setDouble(26,sale.getP24t43());
                       ps.setDate(27,new java.sql.Date(sale.getP25t43().getTime()));
                       ps.setString(28,sale.getP26t43());
                       ps.executeUpdate();
                       c.commit();
               } catch (SQLException e) {
                       e.printStackTrace();

               } finally {
                       ConnectionPool.close(c);
               }

       }

       public static void remove(Sale sale)  {

               Connection c = null;

               try {
                       c = ConnectionPool.getConnection();
                       PreparedStatement ps = c.prepareStatement("DELETE FROM TF_sale WHERE id=?");
                       ps.setLong(1, sale.getId());
                       ps.executeUpdate();
                       c.commit();
               } catch (Exception e) {
                       e.printStackTrace();
               } finally {
                       ConnectionPool.close(c);
               }
       }








       public static com.sbs.service.Sale create(com.sbs.service.Sale sale, String P1T43, Long id_contract)  {

           Connection c = null;
           PreparedStatement ps = null;
           Long aid=new Long ("0");
           try {
                   c = ConnectionPool.getConnection();
                   ps = c.prepareStatement("SELECT SQ_TF_sale.NEXTVAL id FROM DUAL");
                   ResultSet rs = ps.executeQuery();
                   if (rs.next()) {
                   aid=(rs.getLong("id"));
                   }
                   ps = c.prepareStatement("INSERT INTO TF_sale (id, p1t43, p0t43, p2t43, p3t43, p4t43, p5t43, p6t43, p7t43, p11t43, p12t43, p13t43, p14t43, p15t43, p17t43, p18t43, p19t43, p20t43, p21t43, p22t43, p23t43, p24t43, p25t43, p26t43, id_contract, p28t43, p29t43, p30t43, p32t43, p100t43, p101t43, p27t43) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                   
                   ps.setLong(1,aid);
                   ps.setString(2,P1T43);
                   ps.setInt(3,sale.getP0T43()!=null? sale.getP0T43():0);
                   ps.setInt(4,sale.getP2T43());
                   ps.setString(5,sale.getP3T43()!=null? sale.getP3T43():null);
                   ps.setTimestamp(6,sale.getP4T43()!=null? new java.sql.Timestamp(sale.getP4T43().getTimeInMillis()):null);
                   ps.setString(7,sale.getP5T43());
                   ps.setDouble(8,sale.getP6T43());
                   ps.setString(9,sale.getP7T43()!=null? sale.getP7T43():null);
                //   ps.setString(10,sale.getP8T43()!=null? sale.getP8T43():null);
                 //  ps.setDouble(11,sale.getP9T43());
                 //  ps.setString(12,sale.getP10T43());
                   ps.setInt(10,sale.getP11T43()!=null? sale.getP11T43():0);
                   ps.setInt(11,sale.getP12T43()!=null? sale.getP12T43():0);
                   ps.setInt(12,sale.getP13T43()!=null? sale.getP13T43():0);
                   ps.setInt(13,sale.getP14T43()!=null? sale.getP14T43():0);
                   ps.setString(14,sale.getP15T43());
                   //ps.setString(15,sale.getP16T43()!=null? sale.getP16T43():null);
                   ps.setInt(15,sale.getP17T43()!=null? sale.getP17T43():0);
                   ps.setString(16,sale.getP18T43()!=null? sale.getP18T43():null);
                   ps.setString(17,sale.getP19T43()!=null? sale.getP19T43():null);
                   ps.setDate(18,sale.getP20T43()!=null? new java.sql.Date(sale.getP20T43().getTimeInMillis()):null);
                   ps.setString(19,sale.getP21T43()!=null? sale.getP21T43():null );
                   ps.setDouble(20,sale.getP22T43()!=null? sale.getP22T43():0);
                   ps.setDouble(21,sale.getP23T43()!=null? sale.getP23T43():0);
                   ps.setDouble(22,sale.getP24T43()!=null? sale.getP24T43():0);
                   ps.setDate(23,sale.getP25T43()!=null? new java.sql.Date(sale.getP25T43().getTimeInMillis()):null);
                   ps.setInt(24,sale.getP26T43()!=null? sale.getP26T43():null );
                   ps.setLong(25,id_contract);
                   ps.setDouble(26,sale.getP28T43()!=null? sale.getP28T43():null );
                   ps.setInt(27,sale.getP29T43()!=null? sale.getP29T43():null );
                   ps.setString(28,sale.getP30T43()!=null? sale.getP30T43():null );
                   ps.setDate(29,sale.getP32T43()!=null?new java.sql.Date(sale.getP32T43().getTimeInMillis()):null);
                   ps.setShort(30,sale.getP100T43());
                   //ps.setString(35,sale.getP101T43());
                   ps.setString(31,sale.getP27T43()!=null? sale.getP27T43():null );
                   ps.executeUpdate();
                   c.commit();
           } catch (Exception e) {
                   e.printStackTrace();
                   ISLogger.getLogger().error("Sale Service ERROR: --> "+CheckNull.getPstr(e));
                   System.out.println("Sale Service ERROR: --> "+CheckNull.getPstr(e));

           } finally {
                   ConnectionPool.close(c);
           }
           return sale;
   }



}
