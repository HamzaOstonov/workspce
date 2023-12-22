package com.is.tf.debet;

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

public class DebetService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql ="SELECT * FROM TF_Debet ";


      public List<Debet> getDebet()  {

              List<Debet> list = new ArrayList<Debet>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Debet");
                      while (rs.next()) {
                              list.add(new Debet(
                                              rs.getLong("id"),
                                              rs.getInt("p0t24"),
                                              rs.getString("p1t24"),
                                              rs.getString("p2t24"),
                                              rs.getDouble("p3t24"),
                                              rs.getInt("p4t24"),
                                              rs.getString("p5t24"),
                                              rs.getString("p6t24"),
                                              rs.getDate("p7t24"),
                                              rs.getString("p8t24"),
                                              rs.getString("p9t24"),
                                              rs.getDate("p10t24"),
                                              rs.getString("p11t24"),
                                              rs.getDate("p12t24"),
                                              rs.getInt("p13t24"),
                                              rs.getDouble("p14t24"),
                                              rs.getDouble("p15t24"),
                                              rs.getDate("p16t24"),
                                              rs.getString("p17t24"),
                                              rs.getString("p18t24"),
                                              rs.getString("p19t24"),
                                              rs.getDate("p20t24"),
                                              rs.getString("p100t24")));
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

      private static List<FilterField> getFilterFields(DebetFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                    flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
            }
            if(!CheckNull.isEmpty(filter.getP0t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p0t24=?",filter.getP0t24()));
            }
            if(!CheckNull.isEmpty(filter.getP1t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p1t24=?",filter.getP1t24()));
            }
            if(!CheckNull.isEmpty(filter.getP2t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p2t24=?",filter.getP2t24()));
            }
            if(!CheckNull.isEmpty(filter.getP3t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p3t24=?",filter.getP3t24()));
            }
            if(!CheckNull.isEmpty(filter.getP4t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p4t24=?",filter.getP4t24()));
            }
            if(!CheckNull.isEmpty(filter.getP5t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p5t24=?",filter.getP5t24()));
            }
            if(!CheckNull.isEmpty(filter.getP6t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p6t24=?",filter.getP6t24()));
            }
            if(!CheckNull.isEmpty(filter.getP7t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p7t24=?",filter.getP7t24()));
            }
            if(!CheckNull.isEmpty(filter.getP8t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p8t24=?",filter.getP8t24()));
            }
            if(!CheckNull.isEmpty(filter.getP9t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p9t24=?",filter.getP9t24()));
            }
            if(!CheckNull.isEmpty(filter.getP10t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p10t24=?",filter.getP10t24()));
            }
            if(!CheckNull.isEmpty(filter.getP11t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p11t24=?",filter.getP11t24()));
            }
            if(!CheckNull.isEmpty(filter.getP12t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p12t24=?",filter.getP12t24()));
            }
            if(!CheckNull.isEmpty(filter.getP13t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p13t24=?",filter.getP13t24()));
            }
            if(!CheckNull.isEmpty(filter.getP14t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p14t24=?",filter.getP14t24()));
            }
            if(!CheckNull.isEmpty(filter.getP15t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p15t24=?",filter.getP15t24()));
            }
            if(!CheckNull.isEmpty(filter.getP16t24())){
                    flfields.add(new FilterField(getCond(flfields)+ "p16t24=?",filter.getP16t24()));
            }
            if(!CheckNull.isEmpty(filter.getP17t24())){
                flfields.add(new FilterField(getCond(flfields)+ "p17t24=?",filter.getP17t24()));
        }
        if(!CheckNull.isEmpty(filter.getP18t24())){
                flfields.add(new FilterField(getCond(flfields)+ "p18t24=?",filter.getP18t24()));
        }
        if(!CheckNull.isEmpty(filter.getP19t24())){
                flfields.add(new FilterField(getCond(flfields)+ "p19t24=?",filter.getP19t24()));
        }
        if(!CheckNull.isEmpty(filter.getP20t24())){
                flfields.add(new FilterField(getCond(flfields)+ "p20t24=?",filter.getP20t24()));
        }
        if(!CheckNull.isEmpty(filter.getP100t24())){
                flfields.add(new FilterField(getCond(flfields)+ "p100t24=?",filter.getP100t24()));
        }

            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(DebetFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Debet ");
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



      public static List<Debet> getDebetsFl(int pageIndex, int pageSize, DebetFilter filter)  {

              List<Debet> list = new ArrayList<Debet>();
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
                              list.add(new Debet(
                                              rs.getLong("id"),
                                              rs.getInt("p0t24"),
                                              rs.getString("p1t24"),
                                              rs.getString("p2t24"),
                                              rs.getDouble("p3t24"),
                                              rs.getInt("p4t24"),
                                              rs.getString("p5t24"),
                                              rs.getString("p6t24"),
                                              rs.getDate("p7t24"),
                                              rs.getString("p8t24"),
                                              rs.getString("p9t24"),
                                              rs.getDate("p10t24"),
                                              rs.getString("p11t24"),
                                              rs.getDate("p12t24"),
                                              rs.getInt("p13t24"),
                                              rs.getDouble("p14t24"),
                                              rs.getDouble("p15t24"),
                                              rs.getDate("p16t24"),
                                              rs.getString("p17t24"),
                                              rs.getString("p18t24"),
                                              rs.getString("p19t24"),
                                              rs.getDate("p20t24"),
                                              rs.getString("p100t24")));
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Debet getDebet(int debetId) {

              Debet debet = new Debet();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_debet WHERE id=?");
                      ps.setInt(1, debetId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              debet = new Debet();
                              
                              debet.setId(rs.getLong("id"));
                              debet.setP0t24(rs.getInt("p0t24"));
                              debet.setP1t24(rs.getString("p1t24"));
                              debet.setP2t24(rs.getString("p2t24"));
                              debet.setP3t24(rs.getDouble("p3t24"));
                              debet.setP4t24(rs.getInt("p4t24"));
                              debet.setP5t24(rs.getString("p5t24"));
                              debet.setP6t24(rs.getString("p6t24"));
                              debet.setP7t24(rs.getDate("p7t24"));
                              debet.setP8t24(rs.getString("p8t24"));
                              debet.setP9t24(rs.getString("p9t24"));
                              debet.setP10t24(rs.getDate("p10t24"));
                              debet.setP11t24(rs.getString("p11t24"));
                              debet.setP12t24(rs.getDate("p12t24"));
                              debet.setP13t24(rs.getInt("p13t24"));
                              debet.setP14t24(rs.getDouble("p14t24"));
                              debet.setP15t24(rs.getDouble("p15t24"));
                              debet.setP16t24(rs.getDate("p16t24"));
                              debet.setP17t24(rs.getString("p17t24"));
                              debet.setP18t24(rs.getString("p18t24"));
                              debet.setP19t24(rs.getString("p19t24"));
                              debet.setP20t24(rs.getDate("p20t24"));
                              debet.setP100t24(rs.getString("p100t24"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return debet;
      }

      public static Debet create(Debet debet)  {

              Connection c = null;
              PreparedStatement ps = null;
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SQ_TF_debet.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              debet.setId(rs.getLong("id"));
                      }
                      ps = c.prepareStatement("INSERT INTO TF_debet (id, p0t24, p1t24, p2t24, p3t24, p4t24, p5t24, p6t24, p7t24, p8t24, p9t24, p10t24, p11t24, p12t24, p13t24, p14t24, p15t24, p16t24, p17t24, p18t24, p19t24, p20t24, p100t24 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,debet.getId());
                      ps.setInt(2,debet.getP0t24());
                      ps.setString(3,debet.getP1t24());
                      ps.setString(4,debet.getP2t24());
                      ps.setDouble(5,debet.getP3t24());
                      ps.setInt(6,debet.getP4t24());
                      ps.setString(7,debet.getP5t24());
                      ps.setString(8,debet.getP6t24());
                      ps.setDate(9,new java.sql.Date(debet.getP7t24().getTime()));
                      ps.setString(10,debet.getP8t24());
                      ps.setString(11,debet.getP9t24());
                      ps.setDate(12,new java.sql.Date(debet.getP10t24().getTime()));
                      ps.setString(13,debet.getP11t24());
                      ps.setDate(14,new java.sql.Date(debet.getP12t24().getTime()));
                      ps.setInt(15,debet.getP13t24());
                      ps.setDouble(16,debet.getP14t24());
                      ps.setDouble(17,debet.getP15t24());
                      ps.setDate(18,new java.sql.Date(debet.getP16t24().getTime()));
                      ps.setString(19,debet.getP17t24());
                      ps.setString(20,debet.getP18t24());
                      ps.setString(21,debet.getP19t24());
                      ps.setDate(22,new java.sql.Date(debet.getP20t24().getTime()));
                      ps.setString(23,debet.getP100t24());
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return debet;
      }

      public static void update(Debet debet)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_debet SET id=?, p0t24=?, p1t24=?, p2t24=?, p3t24=?, p4t24=?, p5t24=?, p6t24=?, p7t24=?, p8t24=?, p9t24=?, p10t24=?, p11t24=?, p12t24=?, p13t24=?, p14t24=?, p15t24=?, p16t24=? , p17t24=?, p18t24=?, p19t24=?, p20t24=?, p100t24=? WHERE id=?");
                      
                      ps.setLong(1,debet.getId());
                      ps.setInt(2,debet.getP0t24());
                      ps.setString(3,debet.getP1t24());
                      ps.setString(4,debet.getP2t24());
                      ps.setDouble(5,debet.getP3t24());
                      ps.setInt(6,debet.getP4t24());
                      ps.setString(7,debet.getP5t24());
                      ps.setString(8,debet.getP6t24());
                      ps.setDate(9,new java.sql.Date(debet.getP7t24().getTime()));
                      ps.setString(10,debet.getP8t24());
                      ps.setString(11,debet.getP9t24());
                      ps.setDate(12,new java.sql.Date(debet.getP10t24().getTime()));
                      ps.setString(13,debet.getP11t24());
                      ps.setDate(14,new java.sql.Date(debet.getP12t24().getTime()));
                      ps.setInt(15,debet.getP13t24());
                      ps.setDouble(16,debet.getP14t24());
                      ps.setDouble(17,debet.getP15t24());
                      ps.setDate(18,new java.sql.Date(debet.getP16t24().getTime()));
                      ps.setString(19,debet.getP17t24());
                      ps.setString(20,debet.getP18t24());
                      ps.setString(21,debet.getP19t24());
                      ps.setDate(22,new java.sql.Date(debet.getP20t24().getTime()));
                      ps.setString(23,debet.getP100t24());
                      ps.executeUpdate();
                      c.commit();
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }

      }

      public static void remove(Debet debet, Long id_contract)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_debet WHERE id_contract=?");
                      ps.setLong(1, id_contract);
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
      }







      public static com.sbs.service.Debet create(com.sbs.service.Debet debet, String P1t24, Long id_contract)  {

          Connection c = null;
          PreparedStatement ps = null;
          
         
          long aid=new Long("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_Debet.Nextval id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                  aid=rs.getLong("id");
                  }
                  ps = c.prepareStatement("INSERT INTO TF_Debet (id, p0t24, p1t24, p2t24, p3t24, p4t24, p5t24, p6t24, p7t24, p8t24, p9t24, p10t24, p11t24, p12t24, p13t24, p14t24, p15t24, p16t24, id_contract, p17t24, p20t24, p100t24 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  ps.setLong(1,aid);
                  ps.setInt(2,debet.getP0T24()!=null? debet.getP0T24():0);
                  ps.setString(3,P1t24);
                  ps.setString(4,debet.getP2T24());
                  ps.setDouble(5,debet.getP3T24());
                  ps.setInt(6,debet.getP4T24()!=null? debet.getP4T24():0);
                  ps.setString(7,debet.getP5T24());
                  ps.setString(8,debet.getP6T24());
                  ps.setDate(9,debet.getP7T24() !=null ? new java.sql.Date(debet.getP7T24().getTimeInMillis()):null);
                  ps.setString(10,debet.getP8T24());
                  ps.setString(11,debet.getP9T24());
                  ps.setDate(12,debet.getP10T24()!=null ? new java.sql.Date(debet.getP10T24().getTimeInMillis()):null);
                  ps.setString(13,debet.getP11T24());
                  ps.setDate(14,debet.getP12T24()!=null ? new java.sql.Date(debet.getP12T24().getTimeInMillis()):null);
                  ps.setInt(15,debet.getP13T24()!=null? debet.getP13T24():0);
                  ps.setDouble(16,debet.getP14T24());
                  ps.setDouble(17,debet.getP15T24());
                  ps.setDate(18,debet.getP16T24() !=null ? new java.sql.Date(debet.getP16T24().getTimeInMillis()):null);
                  ps.setLong(19,id_contract);
                  ps.setString(20,debet.getP17T24());
                  ps.setDate(21,debet.getP20T24()!=null ? new java.sql.Date(debet.getP20T24().getTimeInMillis()):null);
                  ps.setShort(22,debet.getP100T24());
                 
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();

          } finally {
                  ConnectionPool.close(c);
          }
          return debet;
  }



}
