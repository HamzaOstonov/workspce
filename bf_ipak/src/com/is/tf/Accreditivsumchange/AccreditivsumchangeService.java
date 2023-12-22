package com.is.tf.Accreditivsumchange;

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

public class AccreditivsumchangeService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql ="SELECT * FROM TF_Accreditivsumchange order by p11t23 ";


      public static List<Accreditivsumchange> getAccreditivsumchanges(String p2t21, Long id_contract)  {

              List<Accreditivsumchange> list = new ArrayList<Accreditivsumchange>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Accreditivsumchange where p1t23 = '"+p2t21+"' and id_contract= "+id_contract+" order by  p11t23");
                      //System.out.println(("SELECT * FROM TF_Accreditivsumchange where p1t23 = '"+p2t21+"' and id_contract= "+id_contract));
                      while (rs.next()) {
                              list.add(new Accreditivsumchange(
                                              rs.getLong("id"),
                                              rs.getString("p0t23"),
                                              rs.getString("p1t23"),
                                              rs.getString("p2t23"),
                                              rs.getDouble("p3t23"),
                                              rs.getDouble("p4t23"),
                                              rs.getDouble("p5t23"),
                                              rs.getDouble("p6t23"),
                                              rs.getDouble("p7t23"),
                                              rs.getString("p8t23"),
                                              rs.getString("p9t23"),
                                              rs.getString("p10t23"),
                                              rs.getString("p11t23"),
                                              rs.getString("p12t23"),
                                              rs.getString("p15t23"),
                                              rs.getDate("p16t23"),
                                              rs.getString("p100t23")
                              
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

      private static List<FilterField> getFilterFields(AccreditivsumchangeFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                    flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
            }
            if(!CheckNull.isEmpty(filter.getP0t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p0t23=?",filter.getP0t23()));
            }
            if(!CheckNull.isEmpty(filter.getP1t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p1t23=?",filter.getP1t23()));
            }
            if(!CheckNull.isEmpty(filter.getP2t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p2t23=?",filter.getP2t23()));
            }
            if(!CheckNull.isEmpty(filter.getP3t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p3t23=?",filter.getP3t23()));
            }
            if(!CheckNull.isEmpty(filter.getP4t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p4t23=?",filter.getP4t23()));
            }
            if(!CheckNull.isEmpty(filter.getP5t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p5t23=?",filter.getP5t23()));
            }
            if(!CheckNull.isEmpty(filter.getP6t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p6t23=?",filter.getP6t23()));
            }
            if(!CheckNull.isEmpty(filter.getP7t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p7t23=?",filter.getP7t23()));
            }
            if(!CheckNull.isEmpty(filter.getP8t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p8t23=?",filter.getP8t23()));
            }
            if(!CheckNull.isEmpty(filter.getP9t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p9t23=?",filter.getP9t23()));
            }
            if(!CheckNull.isEmpty(filter.getP10t23())){
                    flfields.add(new FilterField(getCond(flfields)+ "p10t23=?",filter.getP10t23()));
            }

            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(AccreditivsumchangeFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Accreditivsumchange ");
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



      public static List<Accreditivsumchange> getAccreditivsumchangesFl(int pageIndex, int pageSize, AccreditivsumchangeFilter filter)  {

              List<Accreditivsumchange> list = new ArrayList<Accreditivsumchange>();
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
                              list.add(new Accreditivsumchange(
                                              rs.getLong("id"),
                                              rs.getString("p0t23"),
                                              rs.getString("p1t23"),
                                              rs.getString("p2t23"),
                                              rs.getDouble("p3t23"),
                                              rs.getDouble("p4t23"),
                                              rs.getDouble("p5t23"),
                                              rs.getDouble("p6t23"),
                                              rs.getDouble("p7t23"),
                                              rs.getString("p8t23"),
                                              rs.getString("p9t23"),
                                              rs.getString("p10t23"),
                                              rs.getString("p11t23"),
                                              rs.getString("p12t23"),
                                              rs.getString("p15t23"),
                                              rs.getDate("p16t23"),
                                              rs.getString("p100t23")
                                              
                                              
                              
                              ));
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Accreditivsumchange getAccreditivsumchange(int accreditivsumchangeId) {

              Accreditivsumchange accreditivsumchange = new Accreditivsumchange();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_accreditivsumchange WHERE id=?");
                      ps.setInt(1, accreditivsumchangeId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              accreditivsumchange = new Accreditivsumchange();
                              
                              accreditivsumchange.setId(rs.getLong("id"));
                              accreditivsumchange.setP0t23(rs.getString("p0t23"));
                              accreditivsumchange.setP1t23(rs.getString("p1t23"));
                              accreditivsumchange.setP2t23(rs.getString("p2t23"));
                              accreditivsumchange.setP3t23(rs.getDouble("p3t23"));
                              accreditivsumchange.setP4t23(rs.getDouble("p4t23"));
                              accreditivsumchange.setP5t23(rs.getDouble("p5t23"));
                              accreditivsumchange.setP6t23(rs.getDouble("p6t23"));
                              accreditivsumchange.setP7t23(rs.getDouble("p7t23"));
                              accreditivsumchange.setP8t23(rs.getString("p8t23"));
                              accreditivsumchange.setP9t23(rs.getString("p9t23"));
                              accreditivsumchange.setP10t23(rs.getString("p10t23"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return accreditivsumchange;
      }

      public static Accreditivsumchange create(Accreditivsumchange accreditivsumchange)  {

              Connection c = null;
              PreparedStatement ps = null;
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SEQ_accreditivsumchange.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              accreditivsumchange.setId(rs.getLong("id"));
                      }
                      ps = c.prepareStatement("INSERT INTO TF_accreditivsumchange (id, p0t23, p1t23, p2t23, p3t23, p4t23, p5t23, p6t23, p7t23, p8t23, p9t23, p10t23, p11t23, p12t23, p15t23, p16t23, p100t23 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,accreditivsumchange.getId());
                      ps.setString(2,accreditivsumchange.getP0t23());
                      ps.setString(3,accreditivsumchange.getP1t23());
                      ps.setString(4,accreditivsumchange.getP2t23());
                      ps.setDouble(5,accreditivsumchange.getP3t23());
                      ps.setDouble(6,accreditivsumchange.getP4t23());
                      ps.setDouble(7,accreditivsumchange.getP5t23());
                      ps.setDouble(8,accreditivsumchange.getP6t23());
                      ps.setDouble(9,accreditivsumchange.getP7t23());
                      ps.setString(10,accreditivsumchange.getP8t23());
                      ps.setString(11,accreditivsumchange.getP9t23());
                      ps.setString(12,accreditivsumchange.getP10t23());
                      ps.setString(13,accreditivsumchange.getP11t23());
                      ps.setString(14,accreditivsumchange.getP12t23());
                      ps.setString(15,accreditivsumchange.getP15t23());
                      ps.setDate(16,new java.sql.Date(accreditivsumchange.getP16t23().getTime()));
                      ps.setString(17,accreditivsumchange.getP100t23());
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return accreditivsumchange;
      }

      public static void update(Accreditivsumchange accreditivsumchange)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_accreditivsumchange SET id=?, p0t23=?, p1t23=?, p2t23=?, p3t23=?, p4t23=?, p5t23=?, p6t23=?, p7t23=?, p8t23=?, p9t23=?, p10t23=?,  WHERE id=?");
                      
                      ps.setLong(1,accreditivsumchange.getId());
                      ps.setString(2,accreditivsumchange.getP0t23());
                      ps.setString(3,accreditivsumchange.getP1t23());
                      ps.setString(4,accreditivsumchange.getP2t23());
                      ps.setDouble(5,accreditivsumchange.getP3t23());
                      ps.setDouble(6,accreditivsumchange.getP4t23());
                      ps.setDouble(7,accreditivsumchange.getP5t23());
                      ps.setDouble(8,accreditivsumchange.getP6t23());
                      ps.setDouble(9,accreditivsumchange.getP7t23());
                      ps.setString(10,accreditivsumchange.getP8t23());
                      ps.setString(11,accreditivsumchange.getP9t23());
                      ps.setString(12,accreditivsumchange.getP10t23());
                      ps.executeUpdate();
                      c.commit();
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }

      }

      public static void remove(Accreditivsumchange accreditivsumchange, String p2t21, Long id_contract)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_accreditivsumchange WHERE p1t23=? and id_contract=? ");
                      ps.setString(1, p2t21);
                      ps.setLong(2, id_contract);
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
      }

      public static com.sbs.service.AccreditivSumChange create2(com.sbs.service.AccreditivSumChange accreditivSumChange, String p2t21, Long id_contract)  {
          Connection c = null;
          PreparedStatement ps = null;
          long aid=new Long ("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_AccreditivSumchange.Nextval id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                 	 aid=rs.getLong("id");
                  }
                  ps = c.prepareStatement("INSERT INTO TF_accreditivsumchange (id, p1t23, p3t23, p4t23, p5t23, p6t23, p7t23, p8t23, p9t23, p10t23, id_contract, p11t23, p12t23, p15t23, p16t23, p100t23 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  
                  ps.setLong(1,aid);
                  ps.setString(2,p2t21);
                  ps.setDouble(3,accreditivSumChange.getP3T23()!=null? accreditivSumChange.getP3T23():null);
                  ps.setDouble(4,accreditivSumChange.getP4T23()!=null? accreditivSumChange.getP4T23():null);
                  ps.setDouble(5,accreditivSumChange.getP5T23()!=null? accreditivSumChange.getP5T23():null);
                  ps.setDouble(6,accreditivSumChange.getP6T23()!=null? accreditivSumChange.getP6T23():null);
                  ps.setDouble(7,accreditivSumChange.getP7T23()!=null? accreditivSumChange.getP7T23():null);
                  if (accreditivSumChange.getP8T23()!=null){
                  ps.setShort(8,accreditivSumChange.getP8T23());
                  } else {
                	  ps.setString(8,null);
                  }
                  if (accreditivSumChange.getP9T23()!=null){
                  ps.setString(9,accreditivSumChange.getP9T23());
                  } else {
                	  ps.setString(9,null);
                  }
                  if (accreditivSumChange.getP10T23()!=null) {
                  ps.setString(10,accreditivSumChange.getP10T23());
                  } else { 
                  ps.setString(10,null);
                  }
                  ps.setLong(11,id_contract);
                  ps.setInt(12,accreditivSumChange.getP11T23()!=null? accreditivSumChange.getP11T23():null);
                  ps.setString(13,accreditivSumChange.getP12T23());
                  ps.setShort(14,accreditivSumChange.getP15T23()!=null? accreditivSumChange.getP15T23():null);
                  ps.setDate(15,accreditivSumChange.getP16T23()!=null? new java.sql.Date(accreditivSumChange.getP16T23().getTimeInMillis()):null);
                  ps.setShort(16,accreditivSumChange.getP100T23());
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();
                  ISLogger.getLogger().error("AccreditivSumChange Service ERROR="+CheckNull.getPstr(e));
                  

          } finally {
                  ConnectionPool.close(c);
          }
          return accreditivSumChange;
      }
      
}
