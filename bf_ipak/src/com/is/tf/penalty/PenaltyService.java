package com.is.tf.penalty;

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

public class PenaltyService {
	  private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
      private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
      private static String msql =" select * from (SELECT * FROM TF_Penalty order by p7t26)   ";


      public List<Penalty> getPenalty()  {

              List<Penalty> list = new ArrayList<Penalty>();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      Statement s = c.createStatement();
                      ResultSet rs = s.executeQuery("SELECT * FROM TF_Penalty  where order by p7t26 ");
                      while (rs.next()) {
                              list.add(new Penalty(
                                              rs.getLong("id"),
                                              rs.getString("p1t26"),
                                              rs.getString("p0t26"),
                                              rs.getString("p2t26"),
                                              rs.getDate("p3t26"),
                                              rs.getString("p4t26"),
                                              rs.getDouble("p5t26"),
                                              rs.getString("p6t26"),
                                              rs.getInt("p7t26"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p100t26"),
                                              rs.getString("p8t26"),
                                              rs.getString("p9t26"),
                                              rs.getString("p10t26"),
                                              rs.getDate("p11t26"),
                                              rs.getString("p101t26")));
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

      private static List<FilterField> getFilterFields(PenaltyFilter filter){
              List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                    flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
            }
            if(!CheckNull.isEmpty(filter.getP1t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p1t26=?",filter.getP1t26()));
            }
            if(!CheckNull.isEmpty(filter.getP0t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p0t26=?",filter.getP0t26()));
            }
            if(!CheckNull.isEmpty(filter.getP2t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p2t26=?",filter.getP2t26()));
            }
            if(!CheckNull.isEmpty(filter.getP3t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p3t26=?",filter.getP3t26()));
            }
            if(!CheckNull.isEmpty(filter.getP4t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p4t26=?",filter.getP4t26()));
            }
            if(!CheckNull.isEmpty(filter.getP5t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p5t26=?",filter.getP5t26()));
            }
            if(!CheckNull.isEmpty(filter.getP6t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p6t26=?",filter.getP6t26()));
            }
            if(!CheckNull.isEmpty(filter.getP7t26())){
                    flfields.add(new FilterField(getCond(flfields)+ "p7t26=?",filter.getP7t26()));
            }
            if(!CheckNull.isEmpty(filter.getId_contract())){
                flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
        }
        if(!CheckNull.isEmpty(filter.getP100t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p100t26=?",filter.getP100t26()));
        }
        if(!CheckNull.isEmpty(filter.getP8t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p8t26=?",filter.getP8t26()));
        }
        if(!CheckNull.isEmpty(filter.getP9t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p9t26=?",filter.getP9t26()));
        }
        if(!CheckNull.isEmpty(filter.getP10t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p10t26=?",filter.getP10t26()));
        }
        if(!CheckNull.isEmpty(filter.getP11t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p11t26=?",filter.getP11t26()));
        }
        if(!CheckNull.isEmpty(filter.getP101t26())){
                flfields.add(new FilterField(getCond(flfields)+ "p101t26=?",filter.getP101t26()));
        }

            flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

              return flfields;
      }


      public static int getCount(PenaltyFilter filter)  {

          Connection c = null;
          int n = 0;
          List<FilterField> flFields =getFilterFields(filter);
          StringBuffer sql = new StringBuffer();
          sql.append("SELECT count(*) ct FROM TF_Penalty ");
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



      public static List<Penalty> getPenaltysFl(int pageIndex, int pageSize, PenaltyFilter filter)  {

              List<Penalty> list = new ArrayList<Penalty>();
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
                              list.add(new Penalty(
                                              rs.getLong("id"),
                                              rs.getString("p1t26"),
                                              rs.getString("p0t26"),
                                              rs.getString("p2t26"),
                                              rs.getDate("p3t26"),
                                              rs.getString("p4t26"),
                                              rs.getDouble("p5t26"),
                                              rs.getString("p6t26"),
                                              rs.getInt("p7t26"),
                                              rs.getLong("id_contract"),
                                              rs.getString("p100t26"),
                                              rs.getString("p8t26"),
                                              rs.getString("p9t26"),
                                              rs.getString("p10t26"),
                                              rs.getDate("p11t26"),
                                              rs.getString("p101t26")));
                      }
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return list;

      }


      public Penalty getPenalty(int penaltyId) {

              Penalty penalty = new Penalty();
              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_penalty WHERE id=?");
                      ps.setInt(1, penaltyId);
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              penalty = new Penalty();
                              
                              penalty.setId(rs.getLong("id"));
                              penalty.setP1t26(rs.getString("p1t26"));
                              penalty.setP0t26(rs.getString("p0t26"));
                              penalty.setP2t26(rs.getString("p2t26"));
                              penalty.setP3t26(rs.getDate("p3t26"));
                              penalty.setP4t26(rs.getString("p4t26"));
                              penalty.setP5t26(rs.getDouble("p5t26"));
                              penalty.setP6t26(rs.getString("p6t26"));
                              penalty.setP7t26(rs.getInt("p7t26"));
                      }
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
              return penalty;
      }

      public static Penalty create(Penalty penalty)  {

              Connection c = null;
              PreparedStatement ps = null;
              try {
                      c = ConnectionPool.getConnection();
                      ps = c.prepareStatement("SELECT SQ_TF_penalty.NEXTVAL id FROM DUAL");
                      ResultSet rs = ps.executeQuery();
                      if (rs.next()) {
                              penalty.setId(rs.getLong("id"));
                      }
                      ps = c.prepareStatement("INSERT INTO TF_penalty (id, p1t26, p0t26, p2t26, p3t26, p4t26, p5t26, p6t26, p7t26 ) VALUES (?,?,?,?,?,?,?,?,?)");
                      
                      ps.setLong(1,penalty.getId());
                      ps.setString(2,penalty.getP1t26());
                      ps.setString(3,penalty.getP0t26());
                      ps.setString(4,penalty.getP2t26());
                      ps.setDate(5,new java.sql.Date(penalty.getP3t26().getTime()));
                      ps.setString(6,penalty.getP4t26());
                      ps.setDouble(7,penalty.getP5t26());
                      ps.setString(8,penalty.getP6t26());
                      ps.setInt(9,penalty.getP7t26());
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }
              return penalty;
      }

      public static void update(Penalty penalty)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("UPDATE TF_penalty SET id=?, p1t26=?, p0t26=?, p2t26=?, p3t26=?, p4t26=?, p5t26=?, p6t26=?, p7t26=?,  WHERE id=?");
                      
                      ps.setLong(1,penalty.getId());
                      ps.setString(2,penalty.getP1t26());
                      ps.setString(3,penalty.getP0t26());
                      ps.setString(4,penalty.getP2t26());
                      ps.setDate(5,new java.sql.Date(penalty.getP3t26().getTime()));
                      ps.setString(6,penalty.getP4t26());
                      ps.setDouble(7,penalty.getP5t26());
                      ps.setString(8,penalty.getP6t26());
                      ps.setInt(9,penalty.getP7t26());
                      ps.executeUpdate();
                      c.commit();
              } catch (SQLException e) {
                      e.printStackTrace();

              } finally {
                      ConnectionPool.close(c);
              }

      }

      public static void remove(Penalty penalty, Long id_contract)  {

              Connection c = null;

              try {
                      c = ConnectionPool.getConnection();
                      PreparedStatement ps = c.prepareStatement("DELETE FROM TF_penalty WHERE id_contract=?");
                      ps.setLong(1, id_contract);
                      ps.executeUpdate();
                      c.commit();
              } catch (Exception e) {
                      e.printStackTrace();
              } finally {
                      ConnectionPool.close(c);
              }
      }
      
      
      public static com.sbs.service.Penalty create(com.sbs.service.Penalty penalty, String P1t26, Long id_contract ) 
      {

          Connection c = null;
          PreparedStatement ps = null;
          long aid=new Long ("0");
          try {
                  c = ConnectionPool.getConnection();
                  ps = c.prepareStatement("SELECT SQ_TF_penalty.NEXTVAL id FROM DUAL");
                  ResultSet rs = ps.executeQuery();
                  if (rs.next()) {
                	  aid=rs.getLong("id");
                  }
                  ps = c.prepareStatement("INSERT INTO TF_penalty (id, p1t26, p0t26, p2t26, p3t26, p4t26, p5t26, p6t26, id_contract, p7t26,p100t26,p8t26,p11t26 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  
                  ps.setLong(1,aid);
                  ps.setString(2,P1t26);
                  ps.setInt(3,0);
                  ps.setInt(4,penalty.getP2T26());
                  ps.setDate(5,penalty.getP3T26()!=null?new java.sql.Date(penalty.getP3T26().getTimeInMillis()):null);
                  ps.setString(6,penalty.getP4T26()!=null? penalty.getP4T26():null);
                  ps.setDouble(7,penalty.getP5T26()!=null? penalty.getP5T26():null);
                  ps.setString(8,penalty.getP6T26()!=null? penalty.getP6T26():null);
                  ps.setLong(9, id_contract);
                  ps.setInt(10,penalty.getP7T26()!=null? penalty.getP7T26():null);
                  ps.setShort(11,penalty.getP100T26());
                  ps.setString(12,penalty.getP8T26()!=null? penalty.getP8T26():null);
                  ps.setDate(13,penalty.getP11T26()!=null?new java.sql.Date(penalty.getP11T26().getTimeInMillis()):null);
                  
                  
                  ps.executeUpdate();
                  c.commit();
          } catch (Exception e) {
                  e.printStackTrace();

          } finally {
                  ConnectionPool.close(c);
          }
          return penalty;
  }

}
