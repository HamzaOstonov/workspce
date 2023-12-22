package com.is.tf.credit;

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

public class CreditService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql ="SELECT * FROM TF_Credit ";


      public List<Credit> getCredit()  {

              List<Credit> list = new ArrayList<Credit>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Credit");
                      while (rs.next()) {
                              list.add(new Credit(
                                              rs.getLong("id"),
                                              rs.getInt("p0t25"),
                                              rs.getString("p1t25"),
                                              rs.getString("p2t25"),
                                              rs.getDouble("p3t25"),
                                              rs.getInt("p4t25"),
                                              rs.getString("p5t25"),
                                              rs.getString("p6t25"),
                                              rs.getString("p7t25"),
                                              rs.getDate("p8t25"),
                                              rs.getString("p9t25"),
                                              rs.getString("p10t25"),
                                              rs.getDate("p11t25"),
                                              rs.getString("p12t25"),
                                              rs.getInt("p13t25"),
                                              rs.getDouble("p14t25"),
                                              rs.getDouble("p15t25"),
                                              rs.getDate("p16t25"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p17t25"),
                                              rs.getString("p18t25"),
                                              rs.getString("p19t25"),
                                              rs.getDate("p20t25"),
                                              rs.getString("p100t25")                
                              
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

      private static List<FilterField> getFilterFields(CreditFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                    flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
            }
            if(!CheckNull.isEmpty(filter.getP0t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p0t25=?",filter.getP0t25()));
            }
            if(!CheckNull.isEmpty(filter.getP1t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p1t25=?",filter.getP1t25()));
            }
            if(!CheckNull.isEmpty(filter.getP2t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p2t25=?",filter.getP2t25()));
            }
            if(!CheckNull.isEmpty(filter.getP3t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p3t25=?",filter.getP3t25()));
            }
            if(!CheckNull.isEmpty(filter.getP4t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p4t25=?",filter.getP4t25()));
            }
            if(!CheckNull.isEmpty(filter.getP5t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p5t25=?",filter.getP5t25()));
            }
            if(!CheckNull.isEmpty(filter.getP6t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p6t25=?",filter.getP6t25()));
            }
            if(!CheckNull.isEmpty(filter.getP7t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p7t25=?",filter.getP7t25()));
            }
            if(!CheckNull.isEmpty(filter.getP8t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p8t25=?",filter.getP8t25()));
            }
            if(!CheckNull.isEmpty(filter.getP9t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p9t25=?",filter.getP9t25()));
            }
            if(!CheckNull.isEmpty(filter.getP10t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p10t25=?",filter.getP10t25()));
            }
            if(!CheckNull.isEmpty(filter.getP11t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p11t25=?",filter.getP11t25()));
            }
            if(!CheckNull.isEmpty(filter.getP12t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p12t25=?",filter.getP12t25()));
            }
            if(!CheckNull.isEmpty(filter.getP13t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p13t25=?",filter.getP13t25()));
            }
            if(!CheckNull.isEmpty(filter.getP14t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p14t25=?",filter.getP14t25()));
            }
            if(!CheckNull.isEmpty(filter.getP15t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p15t25=?",filter.getP15t25()));
            }
            if(!CheckNull.isEmpty(filter.getP16t25())){
                    flfields.add(new FilterField(getCond(flfields)+ "p16t25=?",filter.getP16t25()));
            }
            if(!CheckNull.isEmpty(filter.getId_contract())){
                flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
        }
        if(!CheckNull.isEmpty(filter.getP17t25())){
                flfields.add(new FilterField(getCond(flfields)+ "p17t25=?",filter.getP17t25()));
        }
        if(!CheckNull.isEmpty(filter.getP18t25())){
                flfields.add(new FilterField(getCond(flfields)+ "p18t25=?",filter.getP18t25()));
        }
        if(!CheckNull.isEmpty(filter.getP19t25())){
                flfields.add(new FilterField(getCond(flfields)+ "p19t25=?",filter.getP19t25()));
        }
        if(!CheckNull.isEmpty(filter.getP20t25())){
                flfields.add(new FilterField(getCond(flfields)+ "p20t25=?",filter.getP20t25()));
        }
        if(!CheckNull.isEmpty(filter.getP100t25())){
                flfields.add(new FilterField(getCond(flfields)+ "p100t25=?",filter.getP100t25()));
        }

            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(CreditFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Credit ");
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



      public static List<Credit> getCreditsFl(int pageIndex, int pageSize, CreditFilter filter)  {

              List<Credit> list = new ArrayList<Credit>();
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
                              list.add(new Credit(
                                              rs.getLong("id"),
                                              rs.getInt("p0t25"),
                                              rs.getString("p1t25"),
                                              rs.getString("p2t25"),
                                              rs.getDouble("p3t25"),
                                              rs.getInt("p4t25"),
                                              rs.getString("p5t25"),
                                              rs.getString("p6t25"),
                                              rs.getString("p7t25"),
                                              rs.getDate("p8t25"),
                                              rs.getString("p9t25"),
                                              rs.getString("p10t25"),
                                              rs.getDate("p11t25"),
                                              rs.getString("p12t25"),
                                              rs.getInt("p13t25"),
                                              rs.getDouble("p14t25"),
                                              rs.getDouble("p15t25"),
                                              rs.getDate("p16t25"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p17t25"),
                                              rs.getString("p18t25"),
                                              rs.getString("p19t25"),
                                              rs.getDate("p20t25"),
                                              rs.getString("p100t25")                
                              
                              ));
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Credit getCredit(int creditId) {

              Credit credit = new Credit();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_credit WHERE id=?");
                      ps.setInt(1, creditId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              credit = new Credit();
                              
                              credit.setId(rs.getLong("id"));
                              credit.setP0t25(rs.getInt("p0t25"));
                              credit.setP1t25(rs.getString("p1t25"));
                              credit.setP2t25(rs.getString("p2t25"));
                              credit.setP3t25(rs.getDouble("p3t25"));
                              credit.setP4t25(rs.getInt("p4t25"));
                              credit.setP5t25(rs.getString("p5t25"));
                              credit.setP6t25(rs.getString("p6t25"));
                              credit.setP7t25(rs.getString("p7t25"));
                              credit.setP8t25(rs.getDate("p8t25"));
                              credit.setP9t25(rs.getString("p9t25"));
                              credit.setP10t25(rs.getString("p10t25"));
                              credit.setP11t25(rs.getDate("p11t25"));
                              credit.setP12t25(rs.getString("p12t25"));
                              credit.setP13t25(rs.getInt("p13t25"));
                              credit.setP14t25(rs.getDouble("p14t25"));
                              credit.setP15t25(rs.getDouble("p15t25"));
                              credit.setP16t25(rs.getDate("p16t25"));
                              credit.setId_contract(rs.getLong("id_contract"));
                              credit.setP17t25(rs.getString("p17t25"));
                              credit.setP18t25(rs.getString("p18t25"));
                              credit.setP19t25(rs.getString("p19t25"));
                              credit.setP20t25(rs.getDate("p20t25"));
                              credit.setP100t25(rs.getString("p100t25"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return credit;
      }

      public static Credit create(Credit credit)  {

              Connection c = null;
              PreparedStatement ps = null;
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SQ_TF_credit.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              credit.setId(rs.getLong("id"));
                      }
                      ps = c.prepareStatement("INSERT INTO TF_credit (id, p0t25, p1t25, p2t25, p3t25, p4t25, p5t25, p6t25, p7t25, p8t25, p9t25, p10t25, p11t25, p12t25, p13t25, p14t25, p15t25, p16t25, id_contract, p17t25, p18t25, p19t25, p20t25, p100t25 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,credit.getId());
                      ps.setInt(2,credit.getP0t25());
                      ps.setString(3,credit.getP1t25());
                      ps.setString(4,credit.getP2t25());
                      ps.setDouble(5,credit.getP3t25());
                      ps.setInt(6,credit.getP4t25());
                      ps.setString(7,credit.getP5t25());
                      ps.setString(8,credit.getP6t25());
                      ps.setString(9,credit.getP7t25());
                      ps.setDate(10,new java.sql.Date(credit.getP8t25().getTime()));
                      ps.setString(11,credit.getP9t25());
                      ps.setString(12,credit.getP10t25());
                      ps.setDate(13,new java.sql.Date(credit.getP11t25().getTime()));
                      ps.setString(14,credit.getP12t25());
                      ps.setInt(15,credit.getP13t25());
                      ps.setDouble(16,credit.getP14t25());
                      ps.setDouble(17,credit.getP15t25());
                      ps.setDate(18,new java.sql.Date(credit.getP16t25().getTime()));
                      ps.setLong(19,credit.getId_contract());
                      ps.setString(20,credit.getP17t25());
                      ps.setString(21,credit.getP18t25());
                      ps.setString(22,credit.getP19t25());
                      ps.setDate(23,new java.sql.Date(credit.getP20t25().getTime()));
                      ps.setString(24,credit.getP100t25());
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return credit;
      }

      public static void update(Credit credit)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_credit SET id=?, p0t25=?, p1t25=?, p2t25=?, p3t25=?, p4t25=?, p5t25=?, p6t25=?, p7t25=?, p8t25=?, p9t25=?, p10t25=?, p11t25=?, p12t25=?, p13t25=?, p14t25=?, p15t25=?, p16t25=?,id_contract=?, p17t25=?, p18t25=?, p19t25=?, p20t25=?  WHERE id=?");
                      
                      ps.setLong(1,credit.getId());
                      ps.setInt(2,credit.getP0t25());
                      ps.setString(3,credit.getP1t25());
                      ps.setString(4,credit.getP2t25());
                      ps.setDouble(5,credit.getP3t25());
                      ps.setInt(6,credit.getP4t25());
                      ps.setString(7,credit.getP5t25());
                      ps.setString(8,credit.getP6t25());
                      ps.setString(9,credit.getP7t25());
                      ps.setDate(10,new java.sql.Date(credit.getP8t25().getTime()));
                      ps.setString(11,credit.getP9t25());
                      ps.setString(12,credit.getP10t25());
                      ps.setDate(13,new java.sql.Date(credit.getP11t25().getTime()));
                      ps.setString(14,credit.getP12t25());
                      ps.setInt(15,credit.getP13t25());
                      ps.setDouble(16,credit.getP14t25());
                      ps.setDouble(17,credit.getP15t25());
                      ps.setDate(18,new java.sql.Date(credit.getP16t25().getTime()));
                      ps.setLong(19,credit.getId_contract());
                      ps.setString(20,credit.getP17t25());
                      ps.setString(21,credit.getP18t25());
                      ps.setString(22,credit.getP19t25());
                      ps.setDate(23,new java.sql.Date(credit.getP20t25().getTime()));
                      
                      
                      ps.executeUpdate();
                      c.commit();
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }

      }

      public static void remove(Credit credit, Long id_contract)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_credit WHERE id_contract=?");
                      ps.setLong(1, id_contract);
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
      }












      public static com.sbs.service.Credit create(com.sbs.service.Credit credit, String P1t25, Long id_contract)  {

          Connection c = null;
          PreparedStatement ps = null;
          Long aid=new Long("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_Credit.Nextval id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                  aid=rs.getLong("id");
                  }
                  ps = c.prepareStatement("INSERT INTO TF_credit (id, p0t25, p1t25, p2t25, p3t25, p4t25, p5t25, p6t25, p7t25, p8t25, p9t25, p10t25, p11t25, p12t25, p13t25, p14t25, p15t25, p16t25, id_contract , p17t25, p20t25, p100t25) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  
                  ps.setLong(1,aid);
                  ps.setInt(2,credit.getP0T25()!=null? credit.getP0T25():0);
                  ps.setString(3,P1t25);
                  ps.setString(4,credit.getP2T25());
                  ps.setDouble(5,credit.getP3T25());
                  ps.setInt(6,credit.getP4T25()!=null? credit.getP4T25():0);
                  ps.setString(7,credit.getP5T25());
                  ps.setString(8,credit.getP6T25());
                  ps.setString(9,credit.getP7T25());
                  ps.setDate(10,credit.getP8T25() !=null ? new java.sql.Date(credit.getP8T25().getTimeInMillis()):null);
                  ps.setString(11,credit.getP9T25());
                  ps.setString(12,credit.getP10T25());
                  ps.setDate(13,credit.getP11T25()!=null ? new java.sql.Date(credit.getP11T25().getTimeInMillis()):null);
                  ps.setString(14,credit.getP12T25());
                  ps.setInt(15,credit.getP13T25());
                  ps.setDouble(16,credit.getP14T25());
                  ps.setDouble(17,credit.getP15T25());
                  ps.setDate(18,credit.getP16T25() !=null? new java.sql.Date(credit.getP16T25().getTimeInMillis()):null);
                  ps.setLong(19,id_contract);
                  ps.setString(20,credit.getP17T25());
                  ps.setDate(21,credit.getP20T25()!=null? new java.sql.Date(credit.getP20T25().getTimeInMillis()):null);
                  ps.setShort(22,credit.getP100T25());
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();

          } finally {
                  ConnectionPool.close(c);
          }
          return credit;
  }





}
