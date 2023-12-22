package com.is.tf.lease;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class LeaseService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Lease ";
    static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


    public List<Lease> getLease()  {

            List<Lease> list = new ArrayList<Lease>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Lease");
                    while (rs.next()) {
                            list.add(new Lease(
                                            rs.getLong("id"),
                                            rs.getString("p1t50"),
                                            rs.getString("p0t50"),
                                            rs.getDate("p2t50"),
                                            rs.getString("p3t50"),
                                            rs.getDouble("p4t50"),
                                            rs.getString("p5t50"),
                                            rs.getString("p6t50"),
                                            rs.getTimestamp("p9t50"),
                                            rs.getString("p100t50")));
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

    private static List<FilterField> getFilterFields(LeaseFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t50=?",filter.getP1t50()));
          }
          if(!CheckNull.isEmpty(filter.getP0t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t50=?",filter.getP0t50()));
          }
          if(!CheckNull.isEmpty(filter.getP2t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t50=?",filter.getP2t50()));
          }
          if(!CheckNull.isEmpty(filter.getP3t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t50=?",filter.getP3t50()));
          }
          if(!CheckNull.isEmpty(filter.getP4t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t50=?",filter.getP4t50()));
          }
          if(!CheckNull.isEmpty(filter.getP5t50())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t50=?",filter.getP5t50()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(LeaseFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Lease ");
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



    public static List<Lease> getLeasesFl(int pageIndex, int pageSize, LeaseFilter filter)  {

            List<Lease> list = new ArrayList<Lease>();
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
            sql.append(" order by 1");


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
                            list.add(new Lease(
                                            rs.getLong("id"),
                                            rs.getString("p1t50"),
                                            rs.getString("p0t50"),
                                            rs.getDate("p2t50"),
                                            rs.getString("p3t50"),
                                            rs.getDouble("p4t50"),
                                            rs.getString("p5t50"),
                                            rs.getString("p6t50"),
                                            rs.getTimestamp("p9t50"),
                                            rs.getString("p100t50")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Lease getLease(int leaseId) {

            Lease lease = new Lease();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_lease WHERE id=?");
                    ps.setInt(1, leaseId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            lease = new Lease();
                            
                            lease.setId(rs.getLong("id"));
                            lease.setP1t50(rs.getString("p1t50"));
                            lease.setP0t50(rs.getString("p0t50"));
                            lease.setP2t50(rs.getDate("p2t50"));
                            lease.setP3t50(rs.getString("p3t50"));
                            lease.setP4t50(rs.getDouble("p4t50"));
                            lease.setP5t50(rs.getString("p5t50"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return lease;
    }

    public static Lease create(Lease lease)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_lease.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            lease.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_lease (id, p1t50, p0t50, p2t50, p3t50, p4t50, p5t50, p6t50, p9t50, p100t50 ) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,lease.getId());
                    ps.setString(2,lease.getP1t50());
                    ps.setString(3,lease.getP0t50());
                    ps.setDate(4,new java.sql.Date(lease.getP2t50().getTime()));
                    ps.setString(5,lease.getP3t50());
                    ps.setDouble(6,lease.getP4t50());
                    ps.setString(7,lease.getP5t50());
                    ps.setString(8,lease.getP6t50());
                    ps.setDate(9,new java.sql.Date(lease.getP9t50().getTime()));
                    ps.setString(10,lease.getP100t50());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return lease;
    }

    public static void update(Lease lease)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_lease SET id=?, p1t50=?, p0t50=?, p2t50=?, p3t50=?, p4t50=?, p5t50=?,  WHERE id=?");
                    
                    ps.setLong(1,lease.getId());
                    ps.setString(2,lease.getP1t50());
                    ps.setString(3,lease.getP0t50());
                    ps.setDate(4,new java.sql.Date(lease.getP2t50().getTime()));
                    ps.setString(5,lease.getP3t50());
                    ps.setDouble(6,lease.getP4t50());
                    ps.setString(7,lease.getP5t50());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Lease lease, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_lease WHERE id_contract=?");
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }








    public static com.sbs.service.Lease create(com.sbs.service.Lease lease, String P1T50, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid = new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_lease.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                	aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_lease (id, p1t50, p0t50, p2t50, p3t50, p4t50, p5t50, id_contract , p6t50, p9t50, p100t50) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T50);
                ps.setInt(3,lease.getP0T50()!=null? lease.getP0T50():0);
                ps.setDate(4,new java.sql.Date(lease.getP2T50().getTimeInMillis()));
                ps.setString(5,lease.getP3T50());
                ps.setDouble(6,lease.getP4T50());
                ps.setInt(7,lease.getP5T50()!=null? lease.getP5T50():0);
                ps.setLong(8,id_contract);
                
                ps.setString(9,lease.getP6T50());
                System.out.println("lease.getP6T50()->"+lease.getP6T50());
                
                ps.setDate(10, new java.sql.Date(lease.getP9T50().getTimeInMillis()));
                ps.setString(11, String.valueOf(lease.getP100T50()));
                
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return lease;
}



}
