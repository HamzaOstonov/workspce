package com.is.tf.paymentref;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tf.Accreditivsumchange.AccreditivsumchangeService;
import com.is.tf.payment.Payment;
import com.is.tf.paymentrefsumchange.PaymentrefsumchangeService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class PaymentrefService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql ="SELECT * FROM TF_Paymentref ";


      public List<Paymentref> getPaymentref()  {

              List<Paymentref> list = new ArrayList<Paymentref>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Paymentref");
                      while (rs.next()) {
                              list.add(new Paymentref(
                            		  rs.getLong("id"),
                                      rs.getString("p1t37"),
                                      rs.getLong("p0t37"),
                                      rs.getString("p2t37"),
                                      rs.getDate("p3t37"),
                                      rs.getLong("p4t37"),
                                      rs.getString("p5t37"),
                                      rs.getString("p6t37"),
                                      rs.getString("p7t37"),
                                      rs.getString("p8t37"),
                                      rs.getDouble("p9t37"),
                                      rs.getString("p10t37"),
                                      rs.getDouble("p11t37"),
                                      rs.getDate("p12t37"),
                                      rs.getLong("p13t37"),
                                      rs.getString("p14t37"),
                                      rs.getString("p15t37"),
                                      rs.getString("p16t37"),
                                      rs.getString("p17t37"),
                                      rs.getString("p18t37"),
                                      rs.getString("p19t37"),
                                      rs.getLong("id_contract"),
                                      rs.getString("p21t37"),
                                      rs.getString("p24t37"),
                                      rs.getDate("p25t37"),
                                      rs.getString("p100t37"),
                                      rs.getString("p26t37")
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

      private static List<FilterField> getFilterFields(PaymentrefFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t37=?",filter.getP1t37()));
          }
          if(!CheckNull.isEmpty(filter.getP0t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t37=?",filter.getP0t37()));
          }
          if(!CheckNull.isEmpty(filter.getP2t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t37=?",filter.getP2t37()));
          }
          if(!CheckNull.isEmpty(filter.getP3t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t37=?",filter.getP3t37()));
          }
          if(!CheckNull.isEmpty(filter.getP4t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t37=?",filter.getP4t37()));
          }
          if(!CheckNull.isEmpty(filter.getP5t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t37=?",filter.getP5t37()));
          }
          if(!CheckNull.isEmpty(filter.getP6t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t37=?",filter.getP6t37()));
          }
          if(!CheckNull.isEmpty(filter.getP7t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t37=?",filter.getP7t37()));
          }
          if(!CheckNull.isEmpty(filter.getP8t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t37=?",filter.getP8t37()));
          }
          if(!CheckNull.isEmpty(filter.getP9t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t37=?",filter.getP9t37()));
          }
          if(!CheckNull.isEmpty(filter.getP10t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t37=?",filter.getP10t37()));
          }
          if(!CheckNull.isEmpty(filter.getP11t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t37=?",filter.getP11t37()));
          }
          if(!CheckNull.isEmpty(filter.getP12t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t37=?",filter.getP12t37()));
          }
          if(!CheckNull.isEmpty(filter.getP13t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t37=?",filter.getP13t37()));
          }
          if(!CheckNull.isEmpty(filter.getP14t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t37=?",filter.getP14t37()));
          }
          if(!CheckNull.isEmpty(filter.getP15t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p15t37=?",filter.getP15t37()));
          }
          if(!CheckNull.isEmpty(filter.getP16t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p16t37=?",filter.getP16t37()));
          }
          if(!CheckNull.isEmpty(filter.getP17t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p17t37=?",filter.getP17t37()));
          }
          if(!CheckNull.isEmpty(filter.getP18t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p18t37=?",filter.getP18t37()));
          }
          if(!CheckNull.isEmpty(filter.getP19t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p19t37=?",filter.getP19t37()));
          }
          if(!CheckNull.isEmpty(filter.getId_contract())){
                  flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
          }
          if(!CheckNull.isEmpty(filter.getP21t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p21t37=?",filter.getP21t37()));
          }
          if(!CheckNull.isEmpty(filter.getP24t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p24t37=?",filter.getP24t37()));
          }
          if(!CheckNull.isEmpty(filter.getP25t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p25t37=?",filter.getP25t37()));
          }
          if(!CheckNull.isEmpty(filter.getP100t37())){
                  flfields.add(new FilterField(getCond(flfields)+ "p100t37=?",filter.getP100t37()));
          }
            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(PaymentrefFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Paymentref ");
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



      public static List<Paymentref> getPaymentrefsFl(int pageIndex, int pageSize, PaymentrefFilter filter)  {

              List<Paymentref> list = new ArrayList<Paymentref>();
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
                              list.add(new Paymentref(
                            		   rs.getLong("id"),
                                       rs.getString("p1t37"),
                                       rs.getLong("p0t37"),
                                       rs.getString("p2t37"),
                                       rs.getDate("p3t37"),
                                       rs.getLong("p4t37"),
                                       rs.getString("p5t37"),
                                       rs.getString("p6t37"),
                                       rs.getString("p7t37"),
                                       rs.getString("p8t37"),
                                       rs.getDouble("p9t37"),
                                       rs.getString("p10t37"),
                                       rs.getDouble("p11t37"),
                                       rs.getDate("p12t37"),
                                       rs.getLong("p13t37"),
                                       rs.getString("p14t37"),
                                       rs.getString("p15t37"),
                                       rs.getString("p16t37"),
                                       rs.getString("p17t37"),
                                       rs.getString("p18t37"),
                                       rs.getString("p19t37"),
                                       rs.getLong("id_contract"),
                                       rs.getString("p21t37"),
                                       rs.getString("p24t37"),
                                       rs.getDate("p25t37"),
                                       rs.getString("p100t37"),
                                       rs.getString("p26t37"),
                                       PaymentrefsumchangeService.getPaymentrefsumchange(rs.getString("p2t37"),rs.getLong("id_contract"))         
                              ));
                             
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Paymentref getPaymentref(int paymentrefId) {

              Paymentref paymentref = new Paymentref();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_paymentref WHERE id=?");
                      ps.setInt(1, paymentrefId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              paymentref = new Paymentref();
                              
                              paymentref.setId(rs.getLong("id"));
                              paymentref.setP1t37(rs.getString("p1t37"));
                              paymentref.setP0t37(rs.getLong("p0t37"));
                              paymentref.setP2t37(rs.getString("p2t37"));
                              paymentref.setP3t37(rs.getDate("p3t37"));
                              paymentref.setP4t37(rs.getLong("p4t37"));
                              paymentref.setP5t37(rs.getString("p5t37"));
                              paymentref.setP6t37(rs.getString("p6t37"));
                              paymentref.setP7t37(rs.getString("p7t37"));
                              paymentref.setP8t37(rs.getString("p8t37"));
                              paymentref.setP9t37(rs.getDouble("p9t37"));
                              paymentref.setP10t37(rs.getString("p10t37"));
                              paymentref.setP11t37(rs.getDouble("p11t37"));
                              paymentref.setP12t37(rs.getDate("p12t37"));
                              paymentref.setP13t37(rs.getLong("p13t37"));
                              paymentref.setP14t37(rs.getString("p14t37"));
                              paymentref.setP15t37(rs.getString("p15t37"));
                              paymentref.setP16t37(rs.getString("p16t37"));
                              paymentref.setP17t37(rs.getString("p17t37"));
                              paymentref.setP18t37(rs.getString("p18t37"));
                              paymentref.setP19t37(rs.getString("p19t37"));
                              paymentref.setId_contract(rs.getLong("id_contract"));
                              paymentref.setP21t37(rs.getString("p21t37"));
                              paymentref.setP24t37(rs.getString("p24t37"));
                              paymentref.setP25t37(rs.getDate("p25t37"));
                              paymentref.setP100t37(rs.getString("p100t37"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return paymentref;
      }
///////////////// Добавление нового документа Филиалом /////////////////////////
      public static Res createFilial(Paymentref paymentref , String idn, Long id_contract)  {
    	  Res res = new Res();
              Connection c = null;
              PreparedStatement ps = null;
              Long aid=new Long ("0");
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SQ_TF_paymentref.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                    	  aid=(rs.getLong("id"));
                      }
                                            
                      ps = c.prepareStatement("INSERT INTO TF_paymentref (id, p1t37, p0t37, p3t37, p4t37, p5t37, p6t37, p7t37, p8t37, p9t37, p10t37, p12t37, p21t37, p24t37, p100t37 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,aid);
                      ps.setString(2,idn);
                      ps.setLong(3,0);
                      ps.setDate(4,new java.sql.Date(paymentref.getP3t37().getTime()));
                      ps.setLong(5,paymentref.getP4t37());
                      ps.setString(6,paymentref.getP5t37()!=null? paymentref.getP5t37():null );
                      ps.setString(7,paymentref.getP6t37()!=null? paymentref.getP6t37():null );
                      ps.setString(8,paymentref.getP7t37()!=null? paymentref.getP7t37():null );
                      ps.setString(9,paymentref.getP8t37()!=null? paymentref.getP8t37():null );
                      ps.setDouble(10,paymentref.getP9t37());
                      ps.setString(11,paymentref.getP10t37());
                      ps.setDate(12,paymentref.getP12t37()!=null? new java.sql.Date(paymentref.getP12t37().getTime()):null);
                      ps.setString(13,paymentref.getP21t37());
                      ps.setString(14,paymentref.getP24t37()!=null? paymentref.getP24t37():null );
                      ps.setString(15,"9");
                      if (ps.executeUpdate() == 1) {
           				c.commit();
           				res = new Res(0, "Ok");
           			} else {
           				c.rollback();
           				res = new Res(1, "Сохранение невозоможно!");
           			}
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return res;
      }
      
     

////////////// Корректировка существующего со стороны филиала ////////////////////////////
      public static Res updateFilial(Paymentref paymentref)  { 
    	  Res res = new Res();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_paymentref SET p1t37=?, p0t37=?, p2t37=?, p3t37=?, p4t37=?, p5t37=?, p6t37=?, p7t37=?, p8t37=?, p9t37=?, p10t37=?, p11t37=?, p12t37=?, p13t37=?,  p15t37=?, p17t37=?, p19t37=?, p21t37=?, p24t37=?, p25t37=?, p100t37=?  WHERE id=?");
                      
                      ps.setString(1,paymentref.getP1t37());
                      ps.setLong(2,1);
                      ps.setString(3,paymentref.getP2t37());
                      ps.setDate(4,new java.sql.Date(paymentref.getP3t37().getTime()));
                      ps.setLong(5,paymentref.getP4t37());
                      ps.setString(6,paymentref.getP5t37());
                      ps.setString(7,paymentref.getP6t37());
                      ps.setString(8,paymentref.getP7t37());
                      ps.setString(9,paymentref.getP8t37());
                      ps.setDouble(10,paymentref.getP9t37());
                      ps.setString(11,paymentref.getP10t37());
                      ps.setDouble(12,paymentref.getP11t37());
                      ps.setDate(13,(paymentref.getP12t37()!=null?new java.sql.Date(paymentref.getP12t37().getTime()):null));
                      ps.setLong(14,paymentref.getP13t37());
                      //ps.setString(16,paymentref.getP14t37());
                      ps.setString(15,paymentref.getP15t37());
                      //ps.setString(18,paymentref.getP16t37());
                      ps.setString(16,paymentref.getP17t37());
                      //ps.setString(20,paymentref.getP18t37());
                      ps.setString(17,paymentref.getP19t37());
                      ps.setString(18,paymentref.getP21t37());
                      ps.setString(19,paymentref.getP24t37());
                      ps.setDate(20,paymentref.getP25t37()!=null? new java.sql.Date(paymentref.getP25t37().getTime()):null);
                      ps.setString(21,"9");
                      ps.setLong(22,paymentref.getId());
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
////////////////////// Глав Бухг ///////////////////
      public static Res updateGlbuh(Paymentref paymentref)  {
    	  Res res = new Res();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_paymentref SET p1t37=?, p0t37=?, p2t37=?, p3t37=?, p4t37=?, p5t37=?, p6t37=?, p7t37=?, p8t37=?, p9t37=?, p10t37=?, p11t37=?, p12t37=?, p13t37=?, p15t37=?, p17t37=?, p19t37=?, p21t37=?, p24t37=?, p25t37=?, p100t37=?  WHERE id=?");
                      
                      
                      ps.setString(1,paymentref.getP1t37());
                      ps.setLong(2,paymentref.getP0t37());
                      ps.setString(3,paymentref.getP2t37());
                      ps.setDate(4,new java.sql.Date(paymentref.getP3t37().getTime()));
                      ps.setLong(5,paymentref.getP4t37());
                      ps.setString(6,paymentref.getP5t37());
                      ps.setString(7,paymentref.getP6t37());
                      ps.setString(8,paymentref.getP7t37());
                      ps.setString(9,paymentref.getP8t37());
                      ps.setDouble(10,paymentref.getP9t37());
                      ps.setString(11,paymentref.getP10t37());
                      ps.setDouble(12,paymentref.getP11t37());
                      ps.setDate(13,(paymentref.getP12t37()!=null?new java.sql.Date(paymentref.getP12t37().getTime()):null));
                      ps.setLong(14,paymentref.getP13t37());
                      //ps.setString(16,paymentref.getP14t37());
                      ps.setString(15,paymentref.getP15t37()!=null?paymentref.getP15t37():null);
                      //ps.setString(18,paymentref.getP16t37());
                      ps.setString(16,paymentref.getP17t37()!=null?paymentref.getP17t37():null);
                      //ps.setString(20,paymentref.getP18t37());
                      ps.setString(17,paymentref.getP19t37()!=null?paymentref.getP19t37():null);
                      ps.setString(18,paymentref.getP21t37()!=null?paymentref.getP21t37():null);
                      ps.setString(19,paymentref.getP24t37()!=null?paymentref.getP24t37():null);
                      ps.setDate(20,paymentref.getP25t37()!=null? new java.sql.Date(paymentref.getP25t37().getTime()):null);
                      ps.setString(21,"8");
                      ps.setLong(22,paymentref.getId());
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
      
      public static Res updateUpr(Paymentref paymentref)  {
    	  Res res = new Res();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_paymentref SET p1t37=?, p0t37=?, p2t37=?, p3t37=?, p4t37=?, p5t37=?, p6t37=?, p7t37=?, p8t37=?, p9t37=?, p10t37=?, p11t37=?, p12t37=?, p13t37=?, p15t37=?, p17t37=?, p19t37=?, p21t37=?, p24t37=?, p25t37=?, p100t37=?  WHERE id=?");
                      
                      
                      ps.setString(1,paymentref.getP1t37());
                      ps.setLong(2,paymentref.getP0t37());
                      ps.setString(3,paymentref.getP2t37());
                      ps.setDate(4,new java.sql.Date(paymentref.getP3t37().getTime()));
                      ps.setLong(5,paymentref.getP4t37());
                      ps.setString(6,paymentref.getP5t37());
                      ps.setString(7,paymentref.getP6t37());
                      ps.setString(8,paymentref.getP7t37());
                      ps.setString(9,paymentref.getP8t37());
                      ps.setDouble(10,paymentref.getP9t37());
                      ps.setString(11,paymentref.getP10t37());
                      ps.setDouble(12,paymentref.getP11t37());
                      ps.setDate(13,(paymentref.getP12t37()!=null?new java.sql.Date(paymentref.getP12t37().getTime()):null));
                      ps.setLong(14,paymentref.getP13t37());
                      //ps.setString(16,paymentref.getP14t37());
                      ps.setString(15,paymentref.getP15t37()!=null?paymentref.getP15t37():null);
                      //ps.setString(18,paymentref.getP16t37());
                      ps.setString(16,paymentref.getP17t37()!=null?paymentref.getP17t37():null);
                      //ps.setString(20,paymentref.getP18t37());
                      ps.setString(17,paymentref.getP19t37()!=null?paymentref.getP19t37():null);
                      ps.setString(18,paymentref.getP21t37()!=null?paymentref.getP21t37():null);
                      ps.setString(19,paymentref.getP24t37()!=null?paymentref.getP24t37():null);
                      ps.setDate(20,paymentref.getP25t37()!=null? new java.sql.Date(paymentref.getP25t37().getTime()):null);
                      ps.setString(21,"7");
                      ps.setLong(22,paymentref.getId());
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
     
      public static Res remove1(Paymentref paymentref)  {
    	  Res res = new Res();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_paymentref WHERE id=?");
                      ps.setLong(1, paymentref.getId());
                      if (ps.executeUpdate() == 1) {
          				c.commit();
          				res = new Res(0, "Ok");
          				System.out.println("Ok");
          			} else {
          				c.rollback();
          				res = new Res(1, "Удаление невозможно!");
          			    System.out.println("Удаление невозможно!");
          			}
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return res;
      }



      public static void remove(Paymentref paymentref, Long id_contract)  {

          Connection c = null;

          try {
                  c = ConnectionPool.getConnection();
                  PreparedStatement ps = c.prepareStatement("DELETE FROM TF_paymentref WHERE id_contract=?  and p100t37 in ('1','2', '0', '3', '4', '5')");
                  ps.setLong(1, id_contract);
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();
          } finally {
                  ConnectionPool.close(c);
          }
  }






      public static com.sbs.service.PaymentRef create(com.sbs.service.PaymentRef paymentref, String P1T37, Long id_contract)  {

          Connection c = null;
          PreparedStatement ps = null;
          Long aid=new Long ("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_paymentref.NEXTVAL id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                  aid=(rs.getLong("id"));
                  }
                  ps = c.prepareStatement("INSERT INTO TF_paymentref (id, p1t37, p0t37, p2t37, p3t37, p4t37, p5t37, p6t37, p7t37, p8t37, p9t37, p10t37, p11t37, p12t37,p15t37, p17t37,p19t37, id_contract,p21t37,p24t37,p25t37,p100t37 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  
                  ps.setLong(1,aid);
                  ps.setString(2,P1T37);
                  ps.setInt(3,paymentref.getP0T37()!=null? paymentref.getP0T37():0);
                  ps.setString(4,paymentref.getP2T37());
                  ps.setDate(5,new java.sql.Date(paymentref.getP3T37().getTimeInMillis()));
                  ps.setInt(6,paymentref.getP4T37()!=null? paymentref.getP4T37():null);
                  ps.setInt(7,paymentref.getP5T37());
                  ps.setString(8,paymentref.getP6T37());
                  ps.setString(9,paymentref.getP7T37());
                  ps.setString(10,paymentref.getP8T37());
                  ps.setDouble(11,paymentref.getP9T37());
                  ps.setString(12,paymentref.getP10T37());
                  ps.setDouble(13,paymentref.getP11T37());
                  ps.setDate(14,paymentref.getP12T37() !=null? new java.sql.Date(paymentref.getP12T37().getTimeInMillis()):null);
                  //ps.setInt(15,paymentref.getP13T37()!=null? paymentref.getP13T37():0);
                  //ps.setShort(15,paymentref.getP14T37()!=null? paymentref.getP14T37():null);
                  ps.setString(15,paymentref.getP15T37()!=null? paymentref.getP15T37():null);
                  //ps.setShort(17,paymentref.getP16T37()!=null? paymentref.getP16T37():0);
                  ps.setString(16,paymentref.getP17T37()!=null? paymentref.getP17T37():null);
                  //ps.setInt(19,paymentref.getP18T37()!=null? paymentref.getP18T37():0);
                  ps.setString(17,paymentref.getP19T37()!=null? paymentref.getP19T37():null);
                  ps.setLong(18,id_contract);
                  ps.setString(19,paymentref.getP21T37()!=null? paymentref.getP21T37():null);
                  if (paymentref.getP24T37()!=null){
                	  ps.setInt(20,paymentref.getP24T37());
                  } else {
                	  ps.setString(20,null);
                  }
                  ps.setDate(21,paymentref.getP25T37() !=null? new java.sql.Date(paymentref.getP25T37().getTimeInMillis()):null);
                  ps.setShort(22,paymentref.getP100T37()!=null? paymentref.getP100T37():null);
                  
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();

          } finally {
                  ConnectionPool.close(c);
          }
          return paymentref;
  }


}
