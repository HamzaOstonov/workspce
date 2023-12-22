package com.is.tf.movefromex;

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

public class MoveFromExService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_MoveFromEx ";


    public List<MoveFromEx> getMoveFromEx()  {

            List<MoveFromEx> list = new ArrayList<MoveFromEx>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_MoveFromEx");
                    while (rs.next()) {
                            list.add(new MoveFromEx(
                                            rs.getLong("id"),
                                            rs.getString("p0t52"),
                                            rs.getString("p1t52"),
                                            rs.getDate("p2t52"),
                                            rs.getString("p3t52"),
                                            rs.getString("p4t52"),
                                            rs.getString("p5t52"),
                                            rs.getString("p6t52"),
                                            rs.getString("p7t52"),
                                            rs.getString("p8t52"),
                                            rs.getString("p9t52"),
                                            rs.getDouble("p10t52"),
                                            rs.getDouble("p11t52"),
                                            rs.getDouble("p12t52"),
                                            rs.getDouble("p13t52"),
                                            rs.getDouble("p14t52"),
                                            rs.getDouble("p15t52"),
                                            rs.getDouble("p16t52"),
                                            rs.getString("p17t52"),
                                            rs.getString("p18t52"),
                                            rs.getString("p19t52"),
                                            rs.getString("p20t52"),
                                            rs.getDate("p21t52"),
                                            rs.getString("p22t52"),
                                            rs.getString("p23t52"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p24t52"),
                                            rs.getDate("p25t52"),
                                            rs.getString("p26t52"),
                                            rs.getInt("p27t52"),
                                            rs.getDate("p28t52"),
                                            rs.getString("p100t52"),
                                            rs.getString("p101t52")));
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

    private static List<FilterField> getFilterFields(MoveFromExFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t52=?",filter.getP0t52()));
          }
          if(!CheckNull.isEmpty(filter.getP1t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t52=?",filter.getP1t52()));
          }
          if(!CheckNull.isEmpty(filter.getP2t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t52=?",filter.getP2t52()));
          }
          if(!CheckNull.isEmpty(filter.getP3t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t52=?",filter.getP3t52()));
          }
          if(!CheckNull.isEmpty(filter.getP4t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t52=?",filter.getP4t52()));
          }
          if(!CheckNull.isEmpty(filter.getP5t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t52=?",filter.getP5t52()));
          }
          if(!CheckNull.isEmpty(filter.getP6t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t52=?",filter.getP6t52()));
          }
          if(!CheckNull.isEmpty(filter.getP7t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t52=?",filter.getP7t52()));
          }
          if(!CheckNull.isEmpty(filter.getP8t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t52=?",filter.getP8t52()));
          }
          if(!CheckNull.isEmpty(filter.getP9t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t52=?",filter.getP9t52()));
          }
          if(!CheckNull.isEmpty(filter.getP10t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t52=?",filter.getP10t52()));
          }
          if(!CheckNull.isEmpty(filter.getP11t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t52=?",filter.getP11t52()));
          }
          if(!CheckNull.isEmpty(filter.getP12t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t52=?",filter.getP12t52()));
          }
          if(!CheckNull.isEmpty(filter.getP13t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t52=?",filter.getP13t52()));
          }
          if(!CheckNull.isEmpty(filter.getP14t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t52=?",filter.getP14t52()));
          }
          if(!CheckNull.isEmpty(filter.getP15t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p15t52=?",filter.getP15t52()));
          }
          if(!CheckNull.isEmpty(filter.getP16t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p16t52=?",filter.getP16t52()));
          }
          if(!CheckNull.isEmpty(filter.getP17t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p17t52=?",filter.getP17t52()));
          }
          if(!CheckNull.isEmpty(filter.getP18t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p18t52=?",filter.getP18t52()));
          }
          if(!CheckNull.isEmpty(filter.getP19t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p19t52=?",filter.getP19t52()));
          }
          if(!CheckNull.isEmpty(filter.getP20t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p20t52=?",filter.getP20t52()));
          }
          if(!CheckNull.isEmpty(filter.getP21t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p21t52=?",filter.getP21t52()));
          }
          if(!CheckNull.isEmpty(filter.getP22t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p22t52=?",filter.getP22t52()));
          }
          if(!CheckNull.isEmpty(filter.getP23t52())){
                  flfields.add(new FilterField(getCond(flfields)+ "p23t52=?",filter.getP23t52()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(MoveFromExFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_MoveFromEx ");
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



    public static List<MoveFromEx> getMoveFromExsFl(int pageIndex, int pageSize, MoveFromExFilter filter)  {

            List<MoveFromEx> list = new ArrayList<MoveFromEx>();
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
                            list.add(new MoveFromEx(
                                            rs.getLong("id"),
                                            rs.getString("p0t52"),
                                            rs.getString("p1t52"),
                                            rs.getDate("p2t52"),
                                            rs.getString("p3t52"),
                                            rs.getString("p4t52"),
                                            rs.getString("p5t52"),
                                            rs.getString("p6t52"),
                                            rs.getString("p7t52"),
                                            rs.getString("p8t52"),
                                            rs.getString("p9t52"),
                                            rs.getDouble("p10t52"),
                                            rs.getDouble("p11t52"),
                                            rs.getDouble("p12t52"),
                                            rs.getDouble("p13t52"),
                                            rs.getDouble("p14t52"),
                                            rs.getDouble("p15t52"),
                                            rs.getDouble("p16t52"),
                                            rs.getString("p17t52"),
                                            rs.getString("p18t52"),
                                            rs.getString("p19t52"),
                                            rs.getString("p20t52"),
                                            rs.getDate("p21t52"),
                                            rs.getString("p22t52"),
                                            rs.getString("p23t52"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p24t52"),
                                            rs.getDate("p25t52"),
                                            rs.getString("p26t52"),
                                            rs.getInt("p27t52"),
                                            rs.getDate("p28t52"),
                                            rs.getString("p100t52"),
                                            rs.getString("p101t52")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public MoveFromEx getMoveFromEx(int movefromexId) {

            MoveFromEx movefromex = new MoveFromEx();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_movefromex WHERE id=?");
                    ps.setInt(1, movefromexId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movefromex = new MoveFromEx();
                            
                            movefromex.setId(rs.getLong("id"));
                            movefromex.setP0t52(rs.getString("p0t52"));
                            movefromex.setP1t52(rs.getString("p1t52"));
                            movefromex.setP2t52(rs.getDate("p2t52"));
                            movefromex.setP3t52(rs.getString("p3t52"));
                            movefromex.setP4t52(rs.getString("p4t52"));
                            movefromex.setP5t52(rs.getString("p5t52"));
                            movefromex.setP6t52(rs.getString("p6t52"));
                            movefromex.setP7t52(rs.getString("p7t52"));
                            movefromex.setP8t52(rs.getString("p8t52"));
                            movefromex.setP9t52(rs.getString("p9t52"));
                            movefromex.setP10t52(rs.getDouble("p10t52"));
                            movefromex.setP11t52(rs.getDouble("p11t52"));
                            movefromex.setP12t52(rs.getDouble("p12t52"));
                            movefromex.setP13t52(rs.getDouble("p13t52"));
                            movefromex.setP14t52(rs.getDouble("p14t52"));
                            movefromex.setP15t52(rs.getDouble("p15t52"));
                            movefromex.setP16t52(rs.getDouble("p16t52"));
                            movefromex.setP17t52(rs.getString("p17t52"));
                            movefromex.setP18t52(rs.getString("p18t52"));
                            movefromex.setP19t52(rs.getString("p19t52"));
                            movefromex.setP20t52(rs.getString("p20t52"));
                            movefromex.setP21t52(rs.getDate("p21t52"));
                            movefromex.setP22t52(rs.getString("p22t52"));
                            movefromex.setP23t52(rs.getString("p23t52"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return movefromex;
    }

    public static MoveFromEx create(MoveFromEx movefromex)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_movefromex.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movefromex.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_movefromex (id, p0t52, p1t52, p2t52, p3t52, p4t52, p5t52, p6t52, p7t52, p8t52, p9t52, p10t52, p11t52, p12t52, p13t52, p14t52, p15t52, p16t52, p17t52, p18t52, p19t52, p20t52, p21t52, p22t52, p23t52, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setLong(1,movefromex.getId());
                    ps.setString(2,movefromex.getP0t52());
                    ps.setString(3,movefromex.getP1t52());
                    ps.setDate(4,new java.sql.Date(movefromex.getP2t52().getTime()));
                    ps.setString(5,movefromex.getP3t52());
                    ps.setString(6,movefromex.getP4t52());
                    ps.setString(7,movefromex.getP5t52());
                    ps.setString(8,movefromex.getP6t52());
                    ps.setString(9,movefromex.getP7t52());
                    ps.setString(10,movefromex.getP8t52());
                    ps.setString(11,movefromex.getP9t52());
                    ps.setDouble(12,movefromex.getP10t52());
                    ps.setDouble(13,movefromex.getP11t52());
                    ps.setDouble(14,movefromex.getP12t52());
                    ps.setDouble(15,movefromex.getP13t52());
                    ps.setDouble(16,movefromex.getP14t52());
                    ps.setDouble(17,movefromex.getP15t52());
                    ps.setDouble(18,movefromex.getP16t52());
                    ps.setString(19,movefromex.getP17t52());
                    ps.setString(20,movefromex.getP18t52());
                    ps.setString(21,movefromex.getP19t52());
                    ps.setString(22,movefromex.getP20t52());
                    ps.setDate(23,new java.sql.Date(movefromex.getP21t52().getTime()));
                    ps.setString(24,movefromex.getP22t52());
                    ps.setString(25,movefromex.getP23t52());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return movefromex;
    }

    public static void update(MoveFromEx movefromex)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_movefromex SET id=?, p0t52=?, p1t52=?, p2t52=?, p3t52=?, p4t52=?, p5t52=?, p6t52=?, p7t52=?, p8t52=?, p9t52=?, p10t52=?, p11t52=?, p12t52=?, p13t52=?, p14t52=?, p15t52=?, p16t52=?, p17t52=?, p18t52=?, p19t52=?, p20t52=?, p21t52=?, p22t52=?, p23t52=?,  WHERE id=?");
                    
                    ps.setLong(1,movefromex.getId());
                    ps.setString(2,movefromex.getP0t52());
                    ps.setString(3,movefromex.getP1t52());
                    ps.setDate(4,new java.sql.Date(movefromex.getP2t52().getTime()));
                    ps.setString(5,movefromex.getP3t52());
                    ps.setString(6,movefromex.getP4t52());
                    ps.setString(7,movefromex.getP5t52());
                    ps.setString(8,movefromex.getP6t52());
                    ps.setString(9,movefromex.getP7t52());
                    ps.setString(10,movefromex.getP8t52());
                    ps.setString(11,movefromex.getP9t52());
                    ps.setDouble(12,movefromex.getP10t52());
                    ps.setDouble(13,movefromex.getP11t52());
                    ps.setDouble(14,movefromex.getP12t52());
                    ps.setDouble(15,movefromex.getP13t52());
                    ps.setDouble(16,movefromex.getP14t52());
                    ps.setDouble(17,movefromex.getP15t52());
                    ps.setDouble(18,movefromex.getP16t52());
                    ps.setString(19,movefromex.getP17t52());
                    ps.setString(20,movefromex.getP18t52());
                    ps.setString(21,movefromex.getP19t52());
                    ps.setString(22,movefromex.getP20t52());
                    ps.setDate(23,new java.sql.Date(movefromex.getP21t52().getTime()));
                    ps.setString(24,movefromex.getP22t52());
                    ps.setString(25,movefromex.getP23t52());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(MoveFromEx movefromex)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_movefromex WHERE id=?");
                    ps.setLong(1, movefromex.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }










    public static com.sbs.service.MoveFromEx create(com.sbs.service.MoveFromEx movefromex, String P1T52, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_movefromex.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_movefromex (id, p0t52, p1t52, p2t52, p3t52, p4t52, p5t52, p6t52, p7t52, p8t52, p9t52, p10t52, p11t52, p12t52, p13t52, p14t52, p15t52, p16t52, p17t52, p18t52, p19t52, p20t52, p21t52, p22t52, p23t52, id_contract ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                //ps.setInt(2,movefromex.getP0T52()!=null? movefromex.getP0T52():0 );
                ps.setString(3,P1T52);
                ps.setDate(4,new java.sql.Date(movefromex.getP2T52().getTimeInMillis()));
                ps.setString(5,movefromex.getP3T52());
                ps.setInt(6,movefromex.getP4T52()!=null? movefromex.getP4T52():0);
                ps.setInt(7,movefromex.getP5T52()!=null? movefromex.getP5T52():0);
                ps.setString(8,movefromex.getP6T52());
                ps.setString(9,movefromex.getP7T52()!=null? movefromex.getP7T52():null);
                ps.setString(10,movefromex.getP8T52());
                ps.setString(11,movefromex.getP9T52());
                //ps.setDouble(12,movefromex.getP10T52());
                ps.setDouble(13,movefromex.getP11T52());
                ps.setDouble(14,movefromex.getP12T52());
                ps.setDouble(15,movefromex.getP13T52());
                ps.setDouble(16,movefromex.getP14T52());
                ps.setDouble(17,movefromex.getP15T52());
                ps.setDouble(18,movefromex.getP16T52());
                ps.setInt(19,movefromex.getP17T52()!=null? movefromex.getP17T52():0);
                ps.setString(20,movefromex.getP18T52());
                ps.setString(21,movefromex.getP19T52());
                ps.setString(22,movefromex.getP20T52());
                ps.setDate(23,movefromex.getP21T52() !=null? new java.sql.Date(movefromex.getP21T52().getTimeInMillis()):null);
                ps.setString(24,movefromex.getP22T52());
                ps.setInt(25,movefromex.getP23T52()!=null? movefromex.getP23T52():0);
                ps.setLong(26,id_contract);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return movefromex;
}


}
