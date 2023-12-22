package com.is.tf.debetinfo;

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

public class DebetinfoService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_DebetInfo ";


    public List<Debetinfo> getDebetInfo()  {

            List<Debetinfo> list = new ArrayList<Debetinfo>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_DebetInfo");
                    while (rs.next()) {
                            list.add(new Debetinfo(
                                            rs.getLong("id"),
                                            rs.getInt("p0t31"),
                                            rs.getString("p2t31"),
                                            rs.getDouble("p3t31"),
                                            rs.getInt("p5t31"),
                                            rs.getString("p6t31"),
                                            rs.getInt("p7t31"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p100t31"),
                                            rs.getString("p4t31"),
                                            rs.getString("p8t31"),
                                            rs.getString("p11t31"),
                                            rs.getString("p12t31"),
                                            rs.getDate("p13t31"),
                                            rs.getString("p1t31")));
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

    private static List<FilterField> getFilterFields(DebetinfoFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t31=?",filter.getP0t31()));
          }
          if(!CheckNull.isEmpty(filter.getP2t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t31=?",filter.getP2t31()));
          }
          if(!CheckNull.isEmpty(filter.getP3t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t31=?",filter.getP3t31()));
          }
          if(!CheckNull.isEmpty(filter.getP5t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t31=?",filter.getP5t31()));
          }
          if(!CheckNull.isEmpty(filter.getP6t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t31=?",filter.getP6t31()));
          }
          if(!CheckNull.isEmpty(filter.getP7t31())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t31=?",filter.getP7t31()));
          }
          if(!CheckNull.isEmpty(filter.getId_contract())){
              flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
      }
      if(!CheckNull.isEmpty(filter.getP100t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t31=?",filter.getP100t31()));
      }
      if(!CheckNull.isEmpty(filter.getP4t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p4t31=?",filter.getP4t31()));
      }
      if(!CheckNull.isEmpty(filter.getP8t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p8t31=?",filter.getP8t31()));
      }
      if(!CheckNull.isEmpty(filter.getP11t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p11t31=?",filter.getP11t31()));
      }
      if(!CheckNull.isEmpty(filter.getP12t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p12t31=?",filter.getP12t31()));
      }
      if(!CheckNull.isEmpty(filter.getP13t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p13t31=?",filter.getP13t31()));
      }
      if(!CheckNull.isEmpty(filter.getP1t31())){
              flfields.add(new FilterField(getCond(flfields)+ "p1t31=?",filter.getP1t31()));
      }


          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(DebetinfoFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_DebetInfo ");
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



    public static List<Debetinfo> getDebetInfosFl(int pageIndex, int pageSize, DebetinfoFilter filter)  {

            List<Debetinfo> list = new ArrayList<Debetinfo>();
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
                            list.add(new Debetinfo(
                                            rs.getLong("id"),
                                            rs.getInt("p0t31"),
                                            rs.getString("p2t31"),
                                            rs.getDouble("p3t31"),
                                            rs.getInt("p5t31"),
                                            rs.getString("p6t31"),
                                            rs.getInt("p7t31"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p100t31"),
                                            rs.getString("p4t31"),
                                            rs.getString("p8t31"),
                                            rs.getString("p11t31"),
                                            rs.getString("p12t31"),
                                            rs.getDate("p13t31"),
                                            rs.getString("p1t31")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Debetinfo getDebetInfo(int debetinfoId) {

            Debetinfo debetinfo = new Debetinfo();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_debetinfo WHERE id=?");
                    ps.setInt(1, debetinfoId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            debetinfo = new Debetinfo();
                            
                            debetinfo.setId(rs.getLong("id"));
                            debetinfo.setP0t31(rs.getInt("p0t31"));
                            debetinfo.setP2t31(rs.getString("p2t31"));
                            debetinfo.setP3t31(rs.getDouble("p3t31"));
                            debetinfo.setP5t31(rs.getInt("p5t31"));
                            debetinfo.setP6t31(rs.getString("p6t31"));
                            debetinfo.setP7t31(rs.getInt("p7t31"));
                            debetinfo.setId_contract(rs.getLong("id_contract"));
                            debetinfo.setP100t31(rs.getString("p100t31"));
                            debetinfo.setP4t31(rs.getString("p4t31"));
                            debetinfo.setP8t31(rs.getString("p8t31"));
                            debetinfo.setP11t31(rs.getString("p11t31"));
                            debetinfo.setP12t31(rs.getString("p12t31"));
                            debetinfo.setP13t31(rs.getDate("p13t31"));
                            debetinfo.setP1t31(rs.getString("p1t31"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return debetinfo;
    }

    public static Debetinfo create(Debetinfo debetinfo)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_debetinfo.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            debetinfo.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_debetinfo (id, p0t31, p2t31, p3t31, p5t31, p6t31, p7t31,id_contract, p100t31, p4t31, p8t31, p11t31, p12t31, p13t31, p1t31 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,debetinfo.getId());
                    ps.setInt(2,debetinfo.getP0t31());
                    ps.setString(3,debetinfo.getP2t31());
                    ps.setDouble(4,debetinfo.getP3t31());
                    ps.setInt(5,debetinfo.getP5t31());
                    ps.setString(6,debetinfo.getP6t31());
                    ps.setInt(7,debetinfo.getP7t31());
                    ps.setLong(8,debetinfo.getId_contract());
                    ps.setString(9,debetinfo.getP100t31());
                    ps.setString(10,debetinfo.getP4t31());
                    ps.setString(11,debetinfo.getP8t31());
                    ps.setString(12,debetinfo.getP11t31());
                    ps.setString(13,debetinfo.getP12t31());
                    ps.setDate(14,new java.sql.Date(debetinfo.getP13t31().getTime()));
                    ps.setString(15,debetinfo.getP1t31());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return debetinfo;
    }

    public static void update(Debetinfo debetinfo)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_debetinfo SET id=?, p0t31=?, p2t31=?, p3t31=?, p5t31=?, p6t31=?, p7t31=?, id_contract=?, p100t31=?, p4t31=?, p8t31=?, p11t31=?, p12t31=?, p13t31=?, p1t31=?  WHERE id=?");
                    
                    ps.setLong(1,debetinfo.getId());
                    ps.setInt(2,debetinfo.getP0t31());
                    ps.setString(3,debetinfo.getP2t31());
                    ps.setDouble(4,debetinfo.getP3t31());
                    ps.setInt(5,debetinfo.getP5t31());
                    ps.setString(6,debetinfo.getP6t31());
                    ps.setInt(7,debetinfo.getP7t31());
                    ps.setLong(8,debetinfo.getId_contract());
                    ps.setString(9,debetinfo.getP100t31());
                    ps.setString(10,debetinfo.getP4t31());
                    ps.setString(11,debetinfo.getP8t31());
                    ps.setString(12,debetinfo.getP11t31());
                    ps.setString(13,debetinfo.getP12t31());
                    ps.setDate(14,new java.sql.Date(debetinfo.getP13t31().getTime()));
                    ps.setString(15,debetinfo.getP1t31());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Debetinfo debetinfo, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_debetinfo WHERE id_contract=?");
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }












    public static com.sbs.service.DebetInfo create(com.sbs.service.DebetInfo debetinfo, String P1T31, Long id_contract)  
    {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_debetinfo.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=rs.getLong("id");
                }
                ps = c.prepareStatement("INSERT INTO TF_debetinfo (id, p0t31, p2t31, p3t31, p6t31, p7t31, id_contract, p100t31, p4t31, p8t31, p13t31, p1t31 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setInt(2,debetinfo.getP0T31()!=null? debetinfo.getP0T31():0);
                ps.setString(3,debetinfo.getP2T31());
                ps.setDouble(4,debetinfo.getP3T31());
                //ps.setInt(5,debetinfo.getP5T31()!=null? debetinfo.getP5T31():0);
                ps.setString(5,debetinfo.getP6T31());
                ps.setInt(6,debetinfo.getP7T31()!=null? debetinfo.getP7T31():0);
                ps.setLong(7,id_contract);
                ps.setShort(8,debetinfo.getP100T31());
                ps.setShort(9,debetinfo.getP4T31());
                ps.setString(10,debetinfo.getP8T31());
                ps.setDate(11,new java.sql.Date(debetinfo.getP13T31().getTimeInMillis()));
                ps.setString(12,P1T31);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return debetinfo;
}






}
