package com.is.tf.movefromim;

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

public class MovefromimService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Movefromim ";


    public List<Movefromim> getMovefromim()  {

            List<Movefromim> list = new ArrayList<Movefromim>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Movefromim");
                    while (rs.next()) {
                            list.add(new Movefromim(
                                            rs.getLong("id"),
                                            rs.getString("p0t53"),
                                            rs.getString("p1t53"),
                                            rs.getDate("p2t53"),
                                            rs.getString("p3t53"),
                                            rs.getString("p4t53"),
                                            rs.getString("p5t53"),
                                            rs.getDate("p6t53"),
                                            rs.getString("p7t53"),
                                            rs.getString("p8t53"),
                                            rs.getString("p9t53"),
                                            rs.getString("p10t53"),
                                            rs.getString("p11t53"),
                                            rs.getString("p12t53"),
                                            rs.getString("p13t53"),
                                            rs.getString("p14t53"),
                                            rs.getString("p15t53"),
                                            rs.getString("p16t53"),
                                            rs.getDouble("p17t53"),
                                            rs.getDouble("p18t53"),
                                            rs.getDouble("p19t53"),
                                            rs.getDouble("p20t53"),
                                            rs.getDouble("p21t53"),
                                            rs.getDouble("p22t53"),
                                            rs.getString("p23t53"),
                                            rs.getString("p24t53"),
                                            rs.getString("p25t53"),
                                            rs.getString("p26t53"),
                                            rs.getDate("p27t53"),
                                            rs.getString("p28t53"),
                                            rs.getString("p29t53"),
                                            rs.getString("p30t53"),
                                            rs.getDate("p31t53"),
                                            rs.getDate("p33t53")));
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

    private static List<FilterField> getFilterFields(MovefromimFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t53=?",filter.getP0t53()));
          }
          if(!CheckNull.isEmpty(filter.getP1t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t53=?",filter.getP1t53()));
          }
          if(!CheckNull.isEmpty(filter.getP2t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t53=?",filter.getP2t53()));
          }
          if(!CheckNull.isEmpty(filter.getP3t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t53=?",filter.getP3t53()));
          }
          if(!CheckNull.isEmpty(filter.getP4t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t53=?",filter.getP4t53()));
          }
          if(!CheckNull.isEmpty(filter.getP5t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t53=?",filter.getP5t53()));
          }
          if(!CheckNull.isEmpty(filter.getP6t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t53=?",filter.getP6t53()));
          }
          if(!CheckNull.isEmpty(filter.getP7t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t53=?",filter.getP7t53()));
          }
          if(!CheckNull.isEmpty(filter.getP8t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t53=?",filter.getP8t53()));
          }
          if(!CheckNull.isEmpty(filter.getP9t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t53=?",filter.getP9t53()));
          }
          if(!CheckNull.isEmpty(filter.getP10t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t53=?",filter.getP10t53()));
          }
          if(!CheckNull.isEmpty(filter.getP11t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t53=?",filter.getP11t53()));
          }
          if(!CheckNull.isEmpty(filter.getP12t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t53=?",filter.getP12t53()));
          }
          if(!CheckNull.isEmpty(filter.getP13t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t53=?",filter.getP13t53()));
          }
          if(!CheckNull.isEmpty(filter.getP14t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t53=?",filter.getP14t53()));
          }
          if(!CheckNull.isEmpty(filter.getP15t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p15t53=?",filter.getP15t53()));
          }
          if(!CheckNull.isEmpty(filter.getP16t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p16t53=?",filter.getP16t53()));
          }
          if(!CheckNull.isEmpty(filter.getP17t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p17t53=?",filter.getP17t53()));
          }
          if(!CheckNull.isEmpty(filter.getP18t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p18t53=?",filter.getP18t53()));
          }
          if(!CheckNull.isEmpty(filter.getP19t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p19t53=?",filter.getP19t53()));
          }
          if(!CheckNull.isEmpty(filter.getP20t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p20t53=?",filter.getP20t53()));
          }
          if(!CheckNull.isEmpty(filter.getP21t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p21t53=?",filter.getP21t53()));
          }
          if(!CheckNull.isEmpty(filter.getP22t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p22t53=?",filter.getP22t53()));
          }
          if(!CheckNull.isEmpty(filter.getP23t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p23t53=?",filter.getP23t53()));
          }
          if(!CheckNull.isEmpty(filter.getP24t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p24t53=?",filter.getP24t53()));
          }
          if(!CheckNull.isEmpty(filter.getP25t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p25t53=?",filter.getP25t53()));
          }
          if(!CheckNull.isEmpty(filter.getP26t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p26t53=?",filter.getP26t53()));
          }
          if(!CheckNull.isEmpty(filter.getP27t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p27t53=?",filter.getP27t53()));
          }
          if(!CheckNull.isEmpty(filter.getP28t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p28t53=?",filter.getP28t53()));
          }
          if(!CheckNull.isEmpty(filter.getP29t53())){
                  flfields.add(new FilterField(getCond(flfields)+ "p29t53=?",filter.getP29t53()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(MovefromimFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Movefromim ");
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



    public static List<Movefromim> getMovefromimsFl(int pageIndex, int pageSize, MovefromimFilter filter)  {

            List<Movefromim> list = new ArrayList<Movefromim>();
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
                            list.add(new Movefromim(
                                            rs.getLong("id"),
                                            rs.getString("p0t53"),
                                            rs.getString("p1t53"),
                                            rs.getDate("p2t53"),
                                            rs.getString("p3t53"),
                                            rs.getString("p4t53"),
                                            rs.getString("p5t53"),
                                            rs.getDate("p6t53"),
                                            rs.getString("p7t53"),
                                            rs.getString("p8t53"),
                                            rs.getString("p9t53"),
                                            rs.getString("p10t53"),
                                            rs.getString("p11t53"),
                                            rs.getString("p12t53"),
                                            rs.getString("p13t53"),
                                            rs.getString("p14t53"),
                                            rs.getString("p15t53"),
                                            rs.getString("p16t53"),
                                            rs.getDouble("p17t53"),
                                            rs.getDouble("p18t53"),
                                            rs.getDouble("p19t53"),
                                            rs.getDouble("p20t53"),
                                            rs.getDouble("p21t53"),
                                            rs.getDouble("p22t53"),
                                            rs.getString("p23t53"),
                                            rs.getString("p24t53"),
                                            rs.getString("p25t53"),
                                            rs.getString("p26t53"),
                                            rs.getDate("p27t53"),
                                            rs.getString("p28t53"),
                                            rs.getString("p29t53"),
                                            rs.getString("p27t53"),
                                            rs.getDate("p27t53"),
                                            rs.getDate("p27t53")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Movefromim getMovefromim(int movefromimId) {

            Movefromim movefromim = new Movefromim();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_movefromim WHERE id=?");
                    ps.setInt(1, movefromimId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movefromim = new Movefromim();
                            
                            movefromim.setId(rs.getLong("id"));
                            movefromim.setP0t53(rs.getString("p0t53"));
                            movefromim.setP1t53(rs.getString("p1t53"));
                            movefromim.setP2t53(rs.getDate("p2t53"));
                            movefromim.setP3t53(rs.getString("p3t53"));
                            movefromim.setP4t53(rs.getString("p4t53"));
                            movefromim.setP5t53(rs.getString("p5t53"));
                            movefromim.setP6t53(rs.getDate("p6t53"));
                            movefromim.setP7t53(rs.getString("p7t53"));
                            movefromim.setP8t53(rs.getString("p8t53"));
                            movefromim.setP9t53(rs.getString("p9t53"));
                            movefromim.setP10t53(rs.getString("p10t53"));
                            movefromim.setP11t53(rs.getString("p11t53"));
                            movefromim.setP12t53(rs.getString("p12t53"));
                            movefromim.setP13t53(rs.getString("p13t53"));
                            movefromim.setP14t53(rs.getString("p14t53"));
                            movefromim.setP15t53(rs.getString("p15t53"));
                            movefromim.setP16t53(rs.getString("p16t53"));
                            movefromim.setP17t53(rs.getDouble("p17t53"));
                            movefromim.setP18t53(rs.getDouble("p18t53"));
                            movefromim.setP19t53(rs.getDouble("p19t53"));
                            movefromim.setP20t53(rs.getDouble("p20t53"));
                            movefromim.setP21t53(rs.getDouble("p21t53"));
                            movefromim.setP22t53(rs.getDouble("p22t53"));
                            movefromim.setP23t53(rs.getString("p23t53"));
                            movefromim.setP24t53(rs.getString("p24t53"));
                            movefromim.setP25t53(rs.getString("p25t53"));
                            movefromim.setP26t53(rs.getString("p26t53"));
                            movefromim.setP27t53(rs.getDate("p27t53"));
                            movefromim.setP28t53(rs.getString("p28t53"));
                            movefromim.setP29t53(rs.getString("p29t53"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return movefromim;
    }

    public static Movefromim create(Movefromim movefromim)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_movefromim.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movefromim.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_movefromim (id, p0t53, p1t53, p2t53, p3t53, p4t53, p5t53, p6t53, p7t53, p8t53, p9t53, p10t53, p11t53, p12t53, p13t53, p14t53, p15t53, p16t53, p17t53, p18t53, p19t53, p20t53, p21t53, p22t53, p23t53, p24t53, p25t53, p26t53, p27t53, p28t53, p29t53) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,movefromim.getId());
                    ps.setString(2,movefromim.getP0t53());
                    ps.setString(3,movefromim.getP1t53());
                    ps.setDate(4,new java.sql.Date(movefromim.getP2t53().getTime()));
                    ps.setString(5,movefromim.getP3t53());
                    ps.setString(6,movefromim.getP4t53());
                    ps.setString(7,movefromim.getP5t53());
                    ps.setDate(8,new java.sql.Date(movefromim.getP6t53().getTime()));
                    ps.setString(9,movefromim.getP7t53());
                    ps.setString(10,movefromim.getP8t53());
                    ps.setString(11,movefromim.getP9t53());
                    ps.setString(12,movefromim.getP10t53());
                    ps.setString(13,movefromim.getP11t53());
                    ps.setString(14,movefromim.getP12t53());
                    ps.setString(15,movefromim.getP13t53());
                    ps.setString(16,movefromim.getP14t53());
                    ps.setString(17,movefromim.getP15t53());
                    ps.setString(18,movefromim.getP16t53());
                    ps.setDouble(19,movefromim.getP17t53());
                    ps.setDouble(20,movefromim.getP18t53());
                    ps.setDouble(21,movefromim.getP19t53());
                    ps.setDouble(22,movefromim.getP20t53());
                    ps.setDouble(23,movefromim.getP21t53());
                    ps.setDouble(24,movefromim.getP22t53());
                    ps.setString(25,movefromim.getP23t53());
                    ps.setString(26,movefromim.getP24t53());
                    ps.setString(27,movefromim.getP25t53());
                    ps.setString(28,movefromim.getP26t53());
                    ps.setDate(29,new java.sql.Date(movefromim.getP27t53().getTime()));
                    ps.setString(30,movefromim.getP28t53());
                    ps.setString(31,movefromim.getP29t53());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return movefromim;
    }

    public static void update(Movefromim movefromim)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_movefromim SET id=?, p0t53=?, p1t53=?, p2t53=?, p3t53=?, p4t53=?, p5t53=?, p6t53=?, p7t53=?, p8t53=?, p9t53=?, p10t53=?, p11t53=?, p12t53=?, p13t53=?, p14t53=?, p15t53=?, p16t53=?, p17t53=?, p18t53=?, p19t53=?, p20t53=?, p21t53=?, p22t53=?, p23t53=?, p24t53=?, p25t53=?, p26t53=?, p27t53=?, p28t53=?, p29t53=?,  WHERE id=?");
                    
                    ps.setLong(1,movefromim.getId());
                    ps.setString(2,movefromim.getP0t53());
                    ps.setString(3,movefromim.getP1t53());
                    ps.setDate(4,new java.sql.Date(movefromim.getP2t53().getTime()));
                    ps.setString(5,movefromim.getP3t53());
                    ps.setString(6,movefromim.getP4t53());
                    ps.setString(7,movefromim.getP5t53());
                    ps.setDate(8,new java.sql.Date(movefromim.getP6t53().getTime()));
                    ps.setString(9,movefromim.getP7t53());
                    ps.setString(10,movefromim.getP8t53());
                    ps.setString(11,movefromim.getP9t53());
                    ps.setString(12,movefromim.getP10t53());
                    ps.setString(13,movefromim.getP11t53());
                    ps.setString(14,movefromim.getP12t53());
                    ps.setString(15,movefromim.getP13t53());
                    ps.setString(16,movefromim.getP14t53());
                    ps.setString(17,movefromim.getP15t53());
                    ps.setString(18,movefromim.getP16t53());
                    ps.setDouble(19,movefromim.getP17t53());
                    ps.setDouble(20,movefromim.getP18t53());
                    ps.setDouble(21,movefromim.getP19t53());
                    ps.setDouble(22,movefromim.getP20t53());
                    ps.setDouble(23,movefromim.getP21t53());
                    ps.setDouble(24,movefromim.getP22t53());
                    ps.setString(25,movefromim.getP23t53());
                    ps.setString(26,movefromim.getP24t53());
                    ps.setString(27,movefromim.getP25t53());
                    ps.setString(28,movefromim.getP26t53());
                    ps.setDate(29,new java.sql.Date(movefromim.getP27t53().getTime()));
                    ps.setString(30,movefromim.getP28t53());
                    ps.setString(31,movefromim.getP29t53());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Movefromim movefromim)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_movefromim WHERE id=?");
                    ps.setLong(1, movefromim.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }






    public static com.sbs.service.MoveFromIm create(com.sbs.service.MoveFromIm movefromim, String P1T53,Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_movefromim.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_movefromim (id, p0t53, p1t53, p2t53, p3t53, p4t53, p5t53, p6t53, p7t53, p8t53, p9t53, p10t53, p11t53, p12t53, p13t53, p14t53, p15t53, p16t53, p17t53, p18t53, p19t53, p20t53, p21t53, p22t53, p23t53, p24t53, p25t53, p26t53, p27t53, p28t53, p29t53, id_contract) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setInt(2,movefromim.getP0T53()!=null? movefromim.getP0T53():0 );
                ps.setString(3,P1T53);
                ps.setDate(4,new java.sql.Date(movefromim.getP2T53().getTimeInMillis()));
                ps.setString(5,movefromim.getP3T53());
                ps.setInt(6,movefromim.getP4T53()!=null? movefromim.getP4T53():0);
                ps.setString(7,movefromim.getP5T53());
                ps.setDate(8,new java.sql.Date(movefromim.getP6T53().getTimeInMillis()));
                ps.setString(9,movefromim.getP7T53());
                ps.setInt(10,movefromim.getP8T53()!=null? movefromim.getP8T53():0);
                ps.setInt(11,movefromim.getP9T53()!=null? movefromim.getP9T53():0);
                ps.setInt(12,movefromim.getP10T53()!=null? movefromim.getP10T53():0);
                ps.setString(13,movefromim.getP11T53());
                ps.setInt(14,movefromim.getP12T53()!=null? movefromim.getP12T53():0);
                ps.setInt(15,movefromim.getP13T53()!=null? movefromim.getP13T53():0);
                ps.setString(16,movefromim.getP14T53());
                ps.setString(17,movefromim.getP15T53()!=null? movefromim.getP15T53():null);
                ps.setString(18,movefromim.getP16T53());
                ps.setDouble(19,movefromim.getP17T53());
                ps.setDouble(20,movefromim.getP18T53());
                ps.setDouble(21,movefromim.getP19T53());
                ps.setDouble(22,movefromim.getP20T53());
                ps.setDouble(23,movefromim.getP21T53());
                ps.setDouble(24,movefromim.getP22T53());
                ps.setInt(25,movefromim.getP23T53()!=null? movefromim.getP23T53():0);
                ps.setString(26,movefromim.getP24T53());
                ps.setString(27,movefromim.getP25T53());
                ps.setString(28,movefromim.getP26T53());
                ps.setDate(29,new java.sql.Date(movefromim.getP27T53().getTimeInMillis()));
                ps.setString(30,movefromim.getP28T53());
                ps.setInt(31,movefromim.getP29T53()!=null? movefromim.getP29T53():0);
                ps.setLong(32,id_contract);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return movefromim;
}


}
