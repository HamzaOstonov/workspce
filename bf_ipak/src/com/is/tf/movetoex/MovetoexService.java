package com.is.tf.movetoex;

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

public class MovetoexService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Movetoex ";


    public List<Movetoex> getMovetoex()  {

            List<Movetoex> list = new ArrayList<Movetoex>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Movetoex");
                    while (rs.next()) {
                            list.add(new Movetoex(
                                            rs.getLong("id"),
                                            rs.getString("p1t40"),
                                            rs.getString("p0t40"),
                                            rs.getString("p2t40"),
                                            rs.getDouble("p3t40"),
                                            rs.getString("p4t40"),
                                            rs.getDouble("p5t40"),
                                            rs.getDouble("p6t40"),
                                            rs.getDouble("p7t40"),
                                            rs.getDouble("p8t40"),
                                            rs.getDouble("p9t40"),
                                            rs.getString("p10t40"),
                                            rs.getString("p11t40"),
                                            rs.getString("p12t40"),
                                            rs.getString("p13t40"),
                                            rs.getString("p14t40"),
                                            rs.getString("p15t40"),
                                            rs.getString("p16t40"),
                                            rs.getString("p17t40"),
                                            rs.getDouble("p18t40"),
                                            rs.getDouble("p19t40"),
                                            rs.getDouble("p20t40"),
                                            rs.getDouble("p21t40"),
                                            rs.getDouble("p22t40"),
                                            rs.getDouble("p23t40"),
                                            rs.getDouble("p24t40"),
                                            rs.getString("p25t40"),
                                            rs.getString("p26t40"),
                                            rs.getString("p27t40"),
                                            rs.getString("p28t40"),
                                            rs.getString("p29t40"),
                                            rs.getDate("p30t40"),
                                            rs.getString("p31t40"),
                                            rs.getDate("p32t40"),
                                            rs.getString("p33t40"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p34t40"),
                                            rs.getString("p37t40"),
                                            rs.getString("p38t40"),
                                            rs.getString("p39t40"),
                                            rs.getDate("p40t40"),
                                            rs.getString("p100t40")
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

    private static List<FilterField> getFilterFields(MovetoexFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t40=?",filter.getP1t40()));
          }
          if(!CheckNull.isEmpty(filter.getP0t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t40=?",filter.getP0t40()));
          }
          if(!CheckNull.isEmpty(filter.getP2t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t40=?",filter.getP2t40()));
          }
          if(!CheckNull.isEmpty(filter.getP3t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t40=?",filter.getP3t40()));
          }
          if(!CheckNull.isEmpty(filter.getP4t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t40=?",filter.getP4t40()));
          }
          if(!CheckNull.isEmpty(filter.getP5t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t40=?",filter.getP5t40()));
          }
          if(!CheckNull.isEmpty(filter.getP6t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t40=?",filter.getP6t40()));
          }
          if(!CheckNull.isEmpty(filter.getP7t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t40=?",filter.getP7t40()));
          }
          if(!CheckNull.isEmpty(filter.getP8t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t40=?",filter.getP8t40()));
          }
          if(!CheckNull.isEmpty(filter.getP9t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t40=?",filter.getP9t40()));
          }
          if(!CheckNull.isEmpty(filter.getP10t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t40=?",filter.getP10t40()));
          }
          if(!CheckNull.isEmpty(filter.getP11t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t40=?",filter.getP11t40()));
          }
          if(!CheckNull.isEmpty(filter.getP12t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t40=?",filter.getP12t40()));
          }
          if(!CheckNull.isEmpty(filter.getP13t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t40=?",filter.getP13t40()));
          }
          if(!CheckNull.isEmpty(filter.getP14t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t40=?",filter.getP14t40()));
          }
          if(!CheckNull.isEmpty(filter.getP15t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p15t40=?",filter.getP15t40()));
          }
          if(!CheckNull.isEmpty(filter.getP16t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p16t40=?",filter.getP16t40()));
          }
          if(!CheckNull.isEmpty(filter.getP17t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p17t40=?",filter.getP17t40()));
          }
          if(!CheckNull.isEmpty(filter.getP18t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p18t40=?",filter.getP18t40()));
          }
          if(!CheckNull.isEmpty(filter.getP19t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p19t40=?",filter.getP19t40()));
          }
          if(!CheckNull.isEmpty(filter.getP20t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p20t40=?",filter.getP20t40()));
          }
          if(!CheckNull.isEmpty(filter.getP21t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p21t40=?",filter.getP21t40()));
          }
          if(!CheckNull.isEmpty(filter.getP22t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p22t40=?",filter.getP22t40()));
          }
          if(!CheckNull.isEmpty(filter.getP23t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p23t40=?",filter.getP23t40()));
          }
          if(!CheckNull.isEmpty(filter.getP24t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p24t40=?",filter.getP24t40()));
          }
          if(!CheckNull.isEmpty(filter.getP25t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p25t40=?",filter.getP25t40()));
          }
          if(!CheckNull.isEmpty(filter.getP26t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p26t40=?",filter.getP26t40()));
          }
          if(!CheckNull.isEmpty(filter.getP27t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p27t40=?",filter.getP27t40()));
          }
          if(!CheckNull.isEmpty(filter.getP28t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p28t40=?",filter.getP28t40()));
          }
          if(!CheckNull.isEmpty(filter.getP29t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p29t40=?",filter.getP29t40()));
          }
          if(!CheckNull.isEmpty(filter.getP30t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p30t40=?",filter.getP30t40()));
          }
          if(!CheckNull.isEmpty(filter.getP31t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p31t40=?",filter.getP31t40()));
          }
          if(!CheckNull.isEmpty(filter.getP32t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p32t40=?",filter.getP32t40()));
          }
          if(!CheckNull.isEmpty(filter.getP33t40())){
                  flfields.add(new FilterField(getCond(flfields)+ "p33t40=?",filter.getP33t40()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(MovetoexFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Movetoex ");
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



    public static List<Movetoex> getMovetoexsFl(int pageIndex, int pageSize, MovetoexFilter filter)  {

            List<Movetoex> list = new ArrayList<Movetoex>();
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
                            list.add(new Movetoex(
                                            rs.getLong("id"),
                                            rs.getString("p1t40"),
                                            rs.getString("p0t40"),
                                            rs.getString("p2t40"),
                                            rs.getDouble("p3t40"),
                                            rs.getString("p4t40"),
                                            rs.getDouble("p5t40"),
                                            rs.getDouble("p6t40"),
                                            rs.getDouble("p7t40"),
                                            rs.getDouble("p8t40"),
                                            rs.getDouble("p9t40"),
                                            rs.getString("p10t40"),
                                            rs.getString("p11t40"),
                                            rs.getString("p12t40"),
                                            rs.getString("p13t40"),
                                            rs.getString("p14t40"),
                                            rs.getString("p15t40"),
                                            rs.getString("p16t40"),
                                            rs.getString("p17t40"),
                                            rs.getDouble("p18t40"),
                                            rs.getDouble("p19t40"),
                                            rs.getDouble("p20t40"),
                                            rs.getDouble("p21t40"),
                                            rs.getDouble("p22t40"),
                                            rs.getDouble("p23t40"),
                                            rs.getDouble("p24t40"),
                                            rs.getString("p25t40"),
                                            rs.getString("p26t40"),
                                            rs.getString("p27t40"),
                                            rs.getString("p28t40"),
                                            rs.getString("p29t40"),
                                            rs.getDate("p30t40"),
                                            rs.getString("p31t40"),
                                            rs.getDate("p32t40"),
                                            rs.getString("p33t40"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p34t40"),
                                            rs.getString("p37t40"),
                                            rs.getString("p38t40"),
                                            rs.getString("p39t40"),
                                            rs.getDate("p40t40"),
                                            rs.getString("p100t40")                
                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Movetoex getMovetoex(int movetoexId) {

            Movetoex movetoex = new Movetoex();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_movetoex WHERE id=?");
                    ps.setInt(1, movetoexId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movetoex = new Movetoex();
                            
                            movetoex.setId(rs.getLong("id"));
                            movetoex.setP1t40(rs.getString("p1t40"));
                            movetoex.setP0t40(rs.getString("p0t40"));
                            movetoex.setP2t40(rs.getString("p2t40"));
                            movetoex.setP3t40(rs.getDouble("p3t40"));
                            movetoex.setP4t40(rs.getString("p4t40"));
                            movetoex.setP5t40(rs.getDouble("p5t40"));
                            movetoex.setP6t40(rs.getDouble("p6t40"));
                            movetoex.setP7t40(rs.getDouble("p7t40"));
                            movetoex.setP8t40(rs.getDouble("p8t40"));
                            movetoex.setP9t40(rs.getDouble("p9t40"));
                            movetoex.setP10t40(rs.getString("p10t40"));
                            movetoex.setP11t40(rs.getString("p11t40"));
                            movetoex.setP12t40(rs.getString("p12t40"));
                            movetoex.setP13t40(rs.getString("p13t40"));
                            movetoex.setP14t40(rs.getString("p14t40"));
                            movetoex.setP15t40(rs.getString("p15t40"));
                            movetoex.setP16t40(rs.getString("p16t40"));
                            movetoex.setP17t40(rs.getString("p17t40"));
                            movetoex.setP18t40(rs.getDouble("p18t40"));
                            movetoex.setP19t40(rs.getDouble("p19t40"));
                            movetoex.setP20t40(rs.getDouble("p20t40"));
                            movetoex.setP21t40(rs.getDouble("p21t40"));
                            movetoex.setP22t40(rs.getDouble("p22t40"));
                            movetoex.setP23t40(rs.getDouble("p23t40"));
                            movetoex.setP24t40(rs.getDouble("p24t40"));
                            movetoex.setP25t40(rs.getString("p25t40"));
                            movetoex.setP26t40(rs.getString("p26t40"));
                            movetoex.setP27t40(rs.getString("p27t40"));
                            movetoex.setP28t40(rs.getString("p28t40"));
                            movetoex.setP29t40(rs.getString("p29t40"));
                            movetoex.setP30t40(rs.getDate("p30t40"));
                            movetoex.setP31t40(rs.getString("p31t40"));
                            movetoex.setP32t40(rs.getDate("p32t40"));
                            movetoex.setP33t40(rs.getString("p33t40"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return movetoex;
    }

    public static Movetoex create(Movetoex movetoex)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_movetoex.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movetoex.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_movetoex (id, p1t40, p0t40, p2t40, p3t40, p4t40, p5t40, p6t40, p7t40, p8t40, p9t40, p10t40, p11t40, p12t40, p13t40, p14t40, p15t40, p16t40, p17t40, p18t40, p19t40, p20t40, p21t40, p22t40, p23t40, p24t40, p25t40, p26t40, p27t40, p28t40, p29t40, p30t40, p31t40, p32t40, p33t40 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,movetoex.getId());
                    ps.setString(2,movetoex.getP1t40());
                    ps.setString(3,movetoex.getP0t40());
                    ps.setString(4,movetoex.getP2t40());
                    ps.setDouble(5,movetoex.getP3t40());
                    ps.setString(6,movetoex.getP4t40());
                    ps.setDouble(7,movetoex.getP5t40());
                    ps.setDouble(8,movetoex.getP6t40());
                    ps.setDouble(9,movetoex.getP7t40());
                    ps.setDouble(10,movetoex.getP8t40());
                    ps.setDouble(11,movetoex.getP9t40());
                    ps.setString(12,movetoex.getP10t40());
                    ps.setString(13,movetoex.getP11t40());
                    ps.setString(14,movetoex.getP12t40());
                    ps.setString(15,movetoex.getP13t40());
                    ps.setString(16,movetoex.getP14t40());
                    ps.setString(17,movetoex.getP15t40());
                    ps.setString(18,movetoex.getP16t40());
                    ps.setString(19,movetoex.getP17t40());
                    ps.setDouble(20,movetoex.getP18t40());
                    ps.setDouble(21,movetoex.getP19t40());
                    ps.setDouble(22,movetoex.getP20t40());
                    ps.setDouble(23,movetoex.getP21t40());
                    ps.setDouble(24,movetoex.getP22t40());
                    ps.setDouble(25,movetoex.getP23t40());
                    ps.setDouble(26,movetoex.getP24t40());
                    ps.setString(27,movetoex.getP25t40());
                    ps.setString(28,movetoex.getP26t40());
                    ps.setString(29,movetoex.getP27t40());
                    ps.setString(30,movetoex.getP28t40());
                    ps.setString(31,movetoex.getP29t40());
                    ps.setDate(32,new java.sql.Date(movetoex.getP30t40().getTime()));
                    ps.setString(33,movetoex.getP31t40());
                    ps.setDate(34,new java.sql.Date(movetoex.getP32t40().getTime()));
                    ps.setString(35,movetoex.getP33t40());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return movetoex;
    }

    public static void update(Movetoex movetoex)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_movetoex SET id=?, p1t40=?, p0t40=?, p2t40=?, p3t40=?, p4t40=?, p5t40=?, p6t40=?, p7t40=?, p8t40=?, p9t40=?, p10t40=?, p11t40=?, p12t40=?, p13t40=?, p14t40=?, p15t40=?, p16t40=?, p17t40=?, p18t40=?, p19t40=?, p20t40=?, p21t40=?, p22t40=?, p23t40=?, p24t40=?, p25t40=?, p26t40=?, p27t40=?, p28t40=?, p29t40=?, p30t40=?, p31t40=?, p32t40=?, p33t40=?,  WHERE id=?");
                    
                    ps.setLong(1,movetoex.getId());
                    ps.setString(2,movetoex.getP1t40());
                    ps.setString(3,movetoex.getP0t40());
                    ps.setString(4,movetoex.getP2t40());
                    ps.setDouble(5,movetoex.getP3t40());
                    ps.setString(6,movetoex.getP4t40());
                    ps.setDouble(7,movetoex.getP5t40());
                    ps.setDouble(8,movetoex.getP6t40());
                    ps.setDouble(9,movetoex.getP7t40());
                    ps.setDouble(10,movetoex.getP8t40());
                    ps.setDouble(11,movetoex.getP9t40());
                    ps.setString(12,movetoex.getP10t40());
                    ps.setString(13,movetoex.getP11t40());
                    ps.setString(14,movetoex.getP12t40());
                    ps.setString(15,movetoex.getP13t40());
                    ps.setString(16,movetoex.getP14t40());
                    ps.setString(17,movetoex.getP15t40());
                    ps.setString(18,movetoex.getP16t40());
                    ps.setString(19,movetoex.getP17t40());
                    ps.setDouble(20,movetoex.getP18t40());
                    ps.setDouble(21,movetoex.getP19t40());
                    ps.setDouble(22,movetoex.getP20t40());
                    ps.setDouble(23,movetoex.getP21t40());
                    ps.setDouble(24,movetoex.getP22t40());
                    ps.setDouble(25,movetoex.getP23t40());
                    ps.setDouble(26,movetoex.getP24t40());
                    ps.setString(27,movetoex.getP25t40());
                    ps.setString(28,movetoex.getP26t40());
                    ps.setString(29,movetoex.getP27t40());
                    ps.setString(30,movetoex.getP28t40());
                    ps.setString(31,movetoex.getP29t40());
                    ps.setDate(32,new java.sql.Date(movetoex.getP30t40().getTime()));
                    ps.setString(33,movetoex.getP31t40());
                    ps.setDate(34,new java.sql.Date(movetoex.getP32t40().getTime()));
                    ps.setString(35,movetoex.getP33t40());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Movetoex movetoex)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_movetoex WHERE id=?");
                    ps.setLong(1, movetoex.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }










    public static com.sbs.service.MoveToEx create(com.sbs.service.MoveToEx movetoex, String P1T40, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_movetoex.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_movetoex (id, p1t40, p0t40, p2t40, p3t40, p4t40, p5t40, p6t40, p7t40, p8t40, p9t40, p10t40, p11t40, p12t40, p13t40, p14t40, p15t40, p16t40, p17t40, p18t40, p19t40, p20t40, p21t40, p22t40, p23t40, p24t40, p25t40, p26t40, p27t40, p28t40, p29t40, p30t40, p31t40, p32t40, p33t40, id_contract ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T40);
                ps.setInt(3,movetoex.getP0T40()!=null? movetoex.getP0T40():0);
                ps.setInt(4,movetoex.getP2T40());
                ps.setDouble(5,movetoex.getP3T40());
               // ps.setString(6,movetoex.getP4T40());
                ps.setDouble(7,movetoex.getP5T40());
                ps.setDouble(8,movetoex.getP6T40());
                ps.setDouble(9,movetoex.getP7T40());
                ps.setDouble(10,movetoex.getP8T40());
                ps.setDouble(11,movetoex.getP9T40());
                ps.setInt(12,movetoex.getP10T40()!=null? movetoex.getP10T40():0);
                ps.setString(13,movetoex.getP11T40());
                ps.setInt(14,movetoex.getP12T40()!=null? movetoex.getP12T40():0);
                ps.setInt(15,movetoex.getP13T40()!=null? movetoex.getP13T40():0);
                ps.setString(16,movetoex.getP14T40());
                ps.setString(17,movetoex.getP15T40()!=null? movetoex.getP15T40():null);
                ps.setString(18,movetoex.getP16T40());
                ps.setString(19,movetoex.getP17T40());
                ps.setDouble(20,movetoex.getP18T40());
                ps.setDouble(21,movetoex.getP19T40());
                ps.setDouble(22,movetoex.getP20T40());
                ps.setDouble(23,movetoex.getP21T40());
                ps.setDouble(24,movetoex.getP22T40());
                ps.setDouble(25,movetoex.getP23T40());
                ps.setDouble(26,movetoex.getP24T40());
                ps.setInt(27,movetoex.getP25T40()!=null? movetoex.getP25T40():0);
                ps.setString(28,movetoex.getP26T40());
                ps.setString(29,movetoex.getP27T40());
                ps.setString(30,movetoex.getP28T40());
                ps.setString(31,movetoex.getP29T40());
                ps.setDate(32,movetoex.getP30T40() !=null? new java.sql.Date(movetoex.getP30T40().getTimeInMillis()):null);
                ps.setString(33,movetoex.getP31T40());
                ps.setDate(34,new java.sql.Date(movetoex.getP32T40().getTimeInMillis()));
                ps.setInt(35,movetoex.getP33T40()!=null? movetoex.getP33T40():0);
                ps.setLong(36,id_contract);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return movetoex;
}



}
