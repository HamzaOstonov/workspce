package com.is.tf.movetoim;

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

public class MovetoimService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Movetoim ";


    public List<Movetoim> getMovetoim()  {

            List<Movetoim> list = new ArrayList<Movetoim>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Movetoim");
                    while (rs.next()) {
                            list.add(new Movetoim(
                                            rs.getLong("id"),
                                            rs.getString("p1t47"),
                                            rs.getString("p0t47"),
                                            rs.getString("p2t47"),
                                            rs.getDouble("p3t47"),
                                            rs.getDouble("p4t47"),
                                            rs.getDouble("p5t47"),
                                            rs.getDouble("p6t47"),
                                            rs.getDouble("p7t47"),
                                            rs.getDouble("p8t47"),
                                            rs.getDouble("p9t47"),
                                            rs.getDouble("p10t47"),
                                            rs.getString("p11t47"),
                                            rs.getString("p12t47"),
                                            rs.getString("p13t47"),
                                            rs.getString("p14t47"),
                                            rs.getString("p15t47"),
                                            rs.getString("p16t47"),
                                            rs.getString("p17t47"),
                                            rs.getString("p18t47"),
                                            rs.getString("p19t47"),
                                            rs.getDouble("p20t47"),
                                            rs.getDouble("p21t47"),
                                            rs.getDouble("p22t47"),
                                            rs.getDouble("p23t47"),
                                            rs.getDouble("p24t47"),
                                            rs.getDouble("p25t47"),
                                            rs.getDouble("p26t47"),
                                            rs.getDouble("p27t47"),
                                            rs.getDouble("p28t47"),
                                            rs.getString("p29t47"),
                                            rs.getString("p30t47"),
                                            rs.getString("p31t47"),
                                            rs.getString("p32t47"),
                                            rs.getString("p33t47"),
                                            rs.getDate("p34t47"),
                                            rs.getString("p35t47"),
                                            rs.getString("p36t47"),
                                            rs.getDate("p37t47"),
                                            rs.getInt("p38t47"),
                                            rs.getString("p39t47"),
                                            rs.getString("p40t47"),
                                            rs.getString("p41t47"),
                                            rs.getDate("p42t47"),
                            				rs.getString("p43t47"),
                            				rs.getString("p46t47"),
                            				rs.getString("p47t47"),
                            				rs.getString("p48t47"),
                            				rs.getDate("p49t47"),
                            				rs.getString("p100t47")));
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

    private static List<FilterField> getFilterFields(MovetoimFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t47=?",filter.getP1t47()));
          }
          if(!CheckNull.isEmpty(filter.getP0t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t47=?",filter.getP0t47()));
          }
          if(!CheckNull.isEmpty(filter.getP2t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t47=?",filter.getP2t47()));
          }
          if(!CheckNull.isEmpty(filter.getP3t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t47=?",filter.getP3t47()));
          }
          if(!CheckNull.isEmpty(filter.getP4t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t47=?",filter.getP4t47()));
          }
          if(!CheckNull.isEmpty(filter.getP5t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t47=?",filter.getP5t47()));
          }
          if(!CheckNull.isEmpty(filter.getP6t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t47=?",filter.getP6t47()));
          }
          if(!CheckNull.isEmpty(filter.getP7t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t47=?",filter.getP7t47()));
          }
          if(!CheckNull.isEmpty(filter.getP8t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t47=?",filter.getP8t47()));
          }
          if(!CheckNull.isEmpty(filter.getP9t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t47=?",filter.getP9t47()));
          }
          if(!CheckNull.isEmpty(filter.getP10t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t47=?",filter.getP10t47()));
          }
          if(!CheckNull.isEmpty(filter.getP11t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t47=?",filter.getP11t47()));
          }
          if(!CheckNull.isEmpty(filter.getP12t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t47=?",filter.getP12t47()));
          }
          if(!CheckNull.isEmpty(filter.getP13t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t47=?",filter.getP13t47()));
          }
          if(!CheckNull.isEmpty(filter.getP14t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t47=?",filter.getP14t47()));
          }
          if(!CheckNull.isEmpty(filter.getP15t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p15t47=?",filter.getP15t47()));
          }
          if(!CheckNull.isEmpty(filter.getP16t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p16t47=?",filter.getP16t47()));
          }
          if(!CheckNull.isEmpty(filter.getP17t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p17t47=?",filter.getP17t47()));
          }
          if(!CheckNull.isEmpty(filter.getP18t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p18t47=?",filter.getP18t47()));
          }
          if(!CheckNull.isEmpty(filter.getP19t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p19t47=?",filter.getP19t47()));
          }
          if(!CheckNull.isEmpty(filter.getP20t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p20t47=?",filter.getP20t47()));
          }
          if(!CheckNull.isEmpty(filter.getP21t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p21t47=?",filter.getP21t47()));
          }
          if(!CheckNull.isEmpty(filter.getP22t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p22t47=?",filter.getP22t47()));
          }
          if(!CheckNull.isEmpty(filter.getP23t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p23t47=?",filter.getP23t47()));
          }
          if(!CheckNull.isEmpty(filter.getP24t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p24t47=?",filter.getP24t47()));
          }
          if(!CheckNull.isEmpty(filter.getP25t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p25t47=?",filter.getP25t47()));
          }
          if(!CheckNull.isEmpty(filter.getP26t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p26t47=?",filter.getP26t47()));
          }
          if(!CheckNull.isEmpty(filter.getP27t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p27t47=?",filter.getP27t47()));
          }
          if(!CheckNull.isEmpty(filter.getP28t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p28t47=?",filter.getP28t47()));
          }
          if(!CheckNull.isEmpty(filter.getP29t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p29t47=?",filter.getP29t47()));
          }
          if(!CheckNull.isEmpty(filter.getP30t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p30t47=?",filter.getP30t47()));
          }
          if(!CheckNull.isEmpty(filter.getP31t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p31t47=?",filter.getP31t47()));
          }
          if(!CheckNull.isEmpty(filter.getP32t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p32t47=?",filter.getP32t47()));
          }
          if(!CheckNull.isEmpty(filter.getP33t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p33t47=?",filter.getP33t47()));
          }
          if(!CheckNull.isEmpty(filter.getP34t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p34t47=?",filter.getP34t47()));
          }
          if(!CheckNull.isEmpty(filter.getP35t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p35t47=?",filter.getP35t47()));
          }
          if(!CheckNull.isEmpty(filter.getP36t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p36t47=?",filter.getP36t47()));
          }
          if(!CheckNull.isEmpty(filter.getP37t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p37t47=?",filter.getP37t47()));
          }
          if(!CheckNull.isEmpty(filter.getP38t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p38t47=?",filter.getP38t47()));
          }
          if(!CheckNull.isEmpty(filter.getP39t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p39t47=?",filter.getP39t47()));
          }
          if(!CheckNull.isEmpty(filter.getP40t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p40t47=?",filter.getP40t47()));
          }
          if(!CheckNull.isEmpty(filter.getP41t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p41t47=?",filter.getP41t47()));
          }
          if(!CheckNull.isEmpty(filter.getP42t47())){
                  flfields.add(new FilterField(getCond(flfields)+ "p42t47=?",filter.getP42t47()));
          }
          if(!CheckNull.isEmpty(filter.getP43t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p43t47=?",filter.getP43t47()));
          }
          if(!CheckNull.isEmpty(filter.getP46t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p46t47=?",filter.getP46t47()));
          }
          if(!CheckNull.isEmpty(filter.getP47t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p47t47=?",filter.getP47t47()));
          }
          if(!CheckNull.isEmpty(filter.getP48t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p48t47=?",filter.getP48t47()));
          }
          if(!CheckNull.isEmpty(filter.getP49t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p49t47=?",filter.getP49t47()));
          }
          if(!CheckNull.isEmpty(filter.getP100t47())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t47=?",filter.getP100t47()));
          }
          
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(MovetoimFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Movetoim ");
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



    public static List<Movetoim> getMovetoimsFl(int pageIndex, int pageSize, MovetoimFilter filter)  {

            List<Movetoim> list = new ArrayList<Movetoim>();
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
                            list.add(new Movetoim(
                                            rs.getLong("id"),
                                            rs.getString("p1t47"),
                                            rs.getString("p0t47"),
                                            rs.getString("p2t47"),
                                            rs.getDouble("p3t47"),
                                            rs.getDouble("p4t47"),
                                            rs.getDouble("p5t47"),
                                            rs.getDouble("p6t47"),
                                            rs.getDouble("p7t47"),
                                            rs.getDouble("p8t47"),
                                            rs.getDouble("p9t47"),
                                            rs.getDouble("p10t47"),
                                            rs.getString("p11t47"),
                                            rs.getString("p12t47"),
                                            rs.getString("p13t47"),
                                            rs.getString("p14t47"),
                                            rs.getString("p15t47"),
                                            rs.getString("p16t47"),
                                            rs.getString("p17t47"),
                                            rs.getString("p18t47"),
                                            rs.getString("p19t47"),
                                            rs.getDouble("p20t47"),
                                            rs.getDouble("p21t47"),
                                            rs.getDouble("p22t47"),
                                            rs.getDouble("p23t47"),
                                            rs.getDouble("p24t47"),
                                            rs.getDouble("p25t47"),
                                            rs.getDouble("p26t47"),
                                            rs.getDouble("p27t47"),
                                            rs.getDouble("p28t47"),
                                            rs.getString("p29t47"),
                                            rs.getString("p30t47"),
                                            rs.getString("p31t47"),
                                            rs.getString("p32t47"),
                                            rs.getString("p33t47"),
                                            rs.getDate("p34t47"),
                                            rs.getString("p35t47"),
                                            rs.getString("p36t47"),
                                            rs.getDate("p37t47"),
                                            rs.getInt("p38t47"),
                                            rs.getString("p39t47"),
                                            rs.getString("p40t47"),
                                            rs.getString("p41t47"),
                                            rs.getDate("p42t47"),
                                            rs.getString("p43t47"),
                                            rs.getString("p46t47"),
                                            rs.getString("p47t47"),
                                            rs.getString("p48t47"),
                                            rs.getDate("p49t47"),
                                            rs.getString("p100t47")));                
                            
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Movetoim getMovetoim(int movetoimId) {

            Movetoim movetoim = new Movetoim();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_movetoim WHERE id=?");
                    ps.setInt(1, movetoimId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movetoim = new Movetoim();
                            
                            movetoim.setId(rs.getLong("id"));
                            movetoim.setP1t47(rs.getString("p1t47"));
                            movetoim.setP0t47(rs.getString("p0t47"));
                            movetoim.setP2t47(rs.getString("p2t47"));
                            movetoim.setP3t47(rs.getDouble("p3t47"));
                            movetoim.setP4t47(rs.getDouble("p4t47"));
                            movetoim.setP5t47(rs.getDouble("p5t47"));
                            movetoim.setP6t47(rs.getDouble("p6t47"));
                            movetoim.setP7t47(rs.getDouble("p7t47"));
                            movetoim.setP8t47(rs.getDouble("p8t47"));
                            movetoim.setP9t47(rs.getDouble("p9t47"));
                            movetoim.setP10t47(rs.getDouble("p10t47"));
                            movetoim.setP11t47(rs.getString("p11t47"));
                            movetoim.setP12t47(rs.getString("p12t47"));
                            movetoim.setP13t47(rs.getString("p13t47"));
                            movetoim.setP14t47(rs.getString("p14t47"));
                            movetoim.setP15t47(rs.getString("p15t47"));
                            movetoim.setP16t47(rs.getString("p16t47"));
                            movetoim.setP17t47(rs.getString("p17t47"));
                            movetoim.setP18t47(rs.getString("p18t47"));
                            movetoim.setP19t47(rs.getString("p19t47"));
                            movetoim.setP20t47(rs.getDouble("p20t47"));
                            movetoim.setP21t47(rs.getDouble("p21t47"));
                            movetoim.setP22t47(rs.getDouble("p22t47"));
                            movetoim.setP23t47(rs.getDouble("p23t47"));
                            movetoim.setP24t47(rs.getDouble("p24t47"));
                            movetoim.setP25t47(rs.getDouble("p25t47"));
                            movetoim.setP26t47(rs.getDouble("p26t47"));
                            movetoim.setP27t47(rs.getDouble("p27t47"));
                            movetoim.setP28t47(rs.getDouble("p28t47"));
                            movetoim.setP29t47(rs.getString("p29t47"));
                            movetoim.setP30t47(rs.getString("p30t47"));
                            movetoim.setP31t47(rs.getString("p31t47"));
                            movetoim.setP32t47(rs.getString("p32t47"));
                            movetoim.setP33t47(rs.getString("p33t47"));
                            movetoim.setP34t47(rs.getDate("p34t47"));
                            movetoim.setP35t47(rs.getString("p35t47"));
                            movetoim.setP36t47(rs.getString("p36t47"));
                            movetoim.setP37t47(rs.getDate("p37t47"));
                            movetoim.setP38t47(rs.getInt("p38t47"));
                            movetoim.setP39t47(rs.getString("p39t47"));
                            movetoim.setP40t47(rs.getString("p40t47"));
                            movetoim.setP41t47(rs.getString("p41t47"));
                            movetoim.setP42t47(rs.getDate("p42t47"));
                            movetoim.setP43t47(rs.getString("p43t47"));
                            movetoim.setP46t47(rs.getString("p46t47"));
                            movetoim.setP47t47(rs.getString("p47t47"));
                            movetoim.setP48t47(rs.getString("p48t47"));
                            movetoim.setP49t47(rs.getDate("p49t47"));
                            movetoim.setP100t47(rs.getString("p100t47"));
                            
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return movetoim;
    }

    public static Movetoim create(Movetoim movetoim)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_movetoim.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            movetoim.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_movetoim (id, p1t47, p0t47, p2t47, p3t47, p4t47, p5t47, p6t47, p7t47, p8t47, p9t47, p10t47, p11t47, p12t47, p13t47, p14t47, p15t47, p16t47, p17t47, p18t47, p19t47, p20t47, p21t47, p22t47, p23t47, p24t47, p25t47, p26t47, p27t47, p28t47, p29t47, p30t47, p31t47, p32t47, p33t47, p34t47, p35t47, p36t47, p37t47, p38t47, p39t47, p40t47, p41t47, p42t47, p43t47, p46t47, p47t47, p48t47, p49t47, p100t47 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,movetoim.getId());
                    ps.setString(2,movetoim.getP1t47());
                    ps.setString(3,movetoim.getP0t47());
                    ps.setString(4,movetoim.getP2t47());
                    ps.setDouble(5,movetoim.getP3t47());
                    ps.setDouble(6,movetoim.getP4t47());
                    ps.setDouble(7,movetoim.getP5t47());
                    ps.setDouble(8,movetoim.getP6t47());
                    ps.setDouble(9,movetoim.getP7t47());
                    ps.setDouble(10,movetoim.getP8t47());
                    ps.setDouble(11,movetoim.getP9t47());
                    ps.setDouble(12,movetoim.getP10t47());
                    ps.setString(13,movetoim.getP11t47());
                    ps.setString(14,movetoim.getP12t47());
                    ps.setString(15,movetoim.getP13t47());
                    ps.setString(16,movetoim.getP14t47());
                    ps.setString(17,movetoim.getP15t47());
                    ps.setString(18,movetoim.getP16t47());
                    ps.setString(19,movetoim.getP17t47());
                    ps.setString(20,movetoim.getP18t47());
                    ps.setString(21,movetoim.getP19t47());
                    ps.setDouble(22,movetoim.getP20t47());
                    ps.setDouble(23,movetoim.getP21t47());
                    ps.setDouble(24,movetoim.getP22t47());
                    ps.setDouble(25,movetoim.getP23t47());
                    ps.setDouble(26,movetoim.getP24t47());
                    ps.setDouble(27,movetoim.getP25t47());
                    ps.setDouble(28,movetoim.getP26t47());
                    ps.setDouble(29,movetoim.getP27t47());
                    ps.setDouble(30,movetoim.getP28t47());
                    ps.setString(31,movetoim.getP29t47());
                    ps.setString(32,movetoim.getP30t47());
                    ps.setString(33,movetoim.getP31t47());
                    ps.setString(34,movetoim.getP32t47());
                    ps.setString(35,movetoim.getP33t47());
                    ps.setDate(36,new java.sql.Date(movetoim.getP34t47().getTime()));
                    ps.setString(37,movetoim.getP35t47());
                    ps.setString(38,movetoim.getP36t47());
                    ps.setDate(39,new java.sql.Date(movetoim.getP37t47().getTime()));
                    ps.setInt(40,movetoim.getP38t47());
                    ps.setString(41,movetoim.getP39t47());
                    ps.setString(42,movetoim.getP40t47());
                    ps.setString(43,movetoim.getP41t47());
                    ps.setDate(44,new java.sql.Date(movetoim.getP42t47().getTime()));
                    ps.setString(46,movetoim.getP43t47());
                    ps.setString(47,movetoim.getP46t47());
                    ps.setString(48,movetoim.getP47t47());
                    ps.setString(49,movetoim.getP48t47());
                    ps.setDate(50,new java.sql.Date(movetoim.getP49t47().getTime()));
                    ps.setString(51,movetoim.getP100t47());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return movetoim;
    }

    public static void update(Movetoim movetoim)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_movetoim SET id=?, p1t47=?, p0t47=?, p2t47=?, p3t47=?, p4t47=?, p5t47=?, p6t47=?, p7t47=?, p8t47=?, p9t47=?, p10t47=?, p11t47=?, p12t47=?, p13t47=?, p14t47=?, p15t47=?, p16t47=?, p17t47=?, p18t47=?, p19t47=?, p20t47=?, p21t47=?, p22t47=?, p23t47=?, p24t47=?, p25t47=?, p26t47=?, p27t47=?, p28t47=?, p29t47=?, p30t47=?, p31t47=?, p32t47=?, p33t47=?, p34t47=?, p35t47=?, p36t47=?, p37t47=?, p38t47=?, p39t47=?, p40t47=?, p41t47=?, p42t47=?, p43t47=?, p46t47=?, p47t47=?, p48t47=?, p49t47=?, p100t47=?  WHERE id=?");
                    
                    ps.setLong(1,movetoim.getId());
                    ps.setString(2,movetoim.getP1t47());
                    ps.setString(3,movetoim.getP0t47());
                    ps.setString(4,movetoim.getP2t47());
                    ps.setDouble(5,movetoim.getP3t47());
                    ps.setDouble(6,movetoim.getP4t47());
                    ps.setDouble(7,movetoim.getP5t47());
                    ps.setDouble(8,movetoim.getP6t47());
                    ps.setDouble(9,movetoim.getP7t47());
                    ps.setDouble(10,movetoim.getP8t47());
                    ps.setDouble(11,movetoim.getP9t47());
                    ps.setDouble(12,movetoim.getP10t47());
                    ps.setString(13,movetoim.getP11t47());
                    ps.setString(14,movetoim.getP12t47());
                    ps.setString(15,movetoim.getP13t47());
                    ps.setString(16,movetoim.getP14t47());
                    ps.setString(17,movetoim.getP15t47());
                    ps.setString(18,movetoim.getP16t47());
                    ps.setString(19,movetoim.getP17t47());
                    ps.setString(20,movetoim.getP18t47());
                    ps.setString(21,movetoim.getP19t47());
                    ps.setDouble(22,movetoim.getP20t47());
                    ps.setDouble(23,movetoim.getP21t47());
                    ps.setDouble(24,movetoim.getP22t47());
                    ps.setDouble(25,movetoim.getP23t47());
                    ps.setDouble(26,movetoim.getP24t47());
                    ps.setDouble(27,movetoim.getP25t47());
                    ps.setDouble(28,movetoim.getP26t47());
                    ps.setDouble(29,movetoim.getP27t47());
                    ps.setDouble(30,movetoim.getP28t47());
                    ps.setString(31,movetoim.getP29t47());
                    ps.setString(32,movetoim.getP30t47());
                    ps.setString(33,movetoim.getP31t47());
                    ps.setString(34,movetoim.getP32t47());
                    ps.setString(35,movetoim.getP33t47());
                    ps.setDate(36,new java.sql.Date(movetoim.getP34t47().getTime()));
                    ps.setString(37,movetoim.getP35t47());
                    ps.setString(38,movetoim.getP36t47());
                    ps.setDate(39,new java.sql.Date(movetoim.getP37t47().getTime()));
                    ps.setInt(40,movetoim.getP38t47());
                    ps.setString(41,movetoim.getP39t47());
                    ps.setString(42,movetoim.getP40t47());
                    ps.setString(43,movetoim.getP41t47());
                    ps.setDate(44,new java.sql.Date(movetoim.getP42t47().getTime()));
                    ps.setString(46,movetoim.getP43t47());
                    ps.setString(47,movetoim.getP46t47());
                    ps.setString(48,movetoim.getP47t47());
                    ps.setString(49,movetoim.getP48t47());
                    ps.setDate(50,new java.sql.Date(movetoim.getP49t47().getTime()));
                    ps.setString(51,movetoim.getP100t47());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Movetoim movetoim)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_movetoim WHERE id=?");
                    ps.setLong(1, movetoim.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }









    public static com.sbs.service.MoveToIm create(com.sbs.service.MoveToIm movetoim
    		, String P1T47, String P9T47, String P10T47 , String P27T47 ,String P28T47 ,Long id_contract)  
    {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_movetoim.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_movetoim (id, p1t47, p0t47, p2t47, p3t47" +
                		", p4t47, p5t47, p6t47, p7t47, p8t47, p9t47, p10t47, p11t47" +
                		", p12t47, p13t47, p14t47, p15t47, p16t47, p17t47, p18t47, p19t47" +
                		", p20t47, p21t47, p22t47, p23t47, p24t47, p25t47, p26t47, p27t47" +
                		", p28t47, p29t47, p30t47, p31t47, p32t47, p33t47, p34t47, p35t47" +
                		", p36t47, id_contract, p37t47, p38t47, p39t47, p40t47, p41t47, p42t47 ) " +
                		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T47);
                ps.setInt(3,movetoim.getP0T47()!=null? movetoim.getP0T47():0);
                ps.setInt(4,movetoim.getP2T47());
                ps.setDouble(5,movetoim.getP3T47());
                ps.setDouble(6,movetoim.getP4T47());
                ps.setDouble(7,movetoim.getP5T47());
                ps.setDouble(8,movetoim.getP6T47());
                ps.setDouble(9,movetoim.getP7T47());
                ps.setDouble(10,movetoim.getP8T47());
                ps.setString(11,P9T47);
                ps.setString(12,P10T47);
                ps.setString(13,movetoim.getP11T47());
                ps.setInt(14,movetoim.getP12T47()!=null? movetoim.getP12T47():0);
                ps.setInt(15,movetoim.getP13T47()!=null? movetoim.getP13T47():0);
                ps.setInt(16,movetoim.getP14T47()!=null? movetoim.getP14T47():0);
                ps.setInt(17,movetoim.getP15T47()!=null? movetoim.getP15T47():0);
                ps.setInt(18,movetoim.getP16T47()!=null? movetoim.getP16T47():0);
                ps.setString(19,movetoim.getP17T47()!=null? movetoim.getP17T47():null);
                ps.setString(20,movetoim.getP18T47()!=null? movetoim.getP18T47():null);
                ps.setString(21,movetoim.getP19T47());
                ps.setDouble(22,movetoim.getP20T47());
                ps.setDouble(23,movetoim.getP21T47());
                ps.setDouble(24,movetoim.getP22T47());
                ps.setDouble(25,movetoim.getP23T47());
                ps.setDouble(26,movetoim.getP24T47());
                ps.setDouble(27,movetoim.getP25T47());
                ps.setDouble(28,movetoim.getP26T47());
                ps.setString(29,P27T47);
                ps.setString(30,P28T47);
                ps.setInt(31,movetoim.getP29T47()!=null? movetoim.getP29T47():0);
                ps.setString(32,movetoim.getP30T47());
                ps.setString(33,movetoim.getP31T47());
                ps.setString(34,movetoim.getP32T47());
                ps.setString(35,movetoim.getP33T47());
                ps.setDate(36,movetoim.getP34T47()!=null? new java.sql.Date(movetoim.getP34T47().getTimeInMillis()):null);
                ps.setString(37,movetoim.getP35T47());
                ps.setInt(38,movetoim.getP36T47()!=null? movetoim.getP36T47():0);
                ps.setLong(39,id_contract);
                ps.setDate(40,new java.sql.Date(movetoim.getP37T47().getTimeInMillis()));
                ps.setInt(41,movetoim.getP38T47()!=null? movetoim.getP38T47():0);
                ps.setString(42,movetoim.getP39T47());
                ps.setString(43,movetoim.getP40T47());
                ps.setString(44,movetoim.getP41T47());
                ps.setDate(45,movetoim.getP42T47()!=null? new java.sql.Date(movetoim.getP42T47().getTimeInMillis()):null);
                ps.setString(46,movetoim.getP43T47());
                ps.setShort(47,movetoim.getP46T47()!=null? movetoim.getP46T47():0);
                ps.setShort(48,movetoim.getP47T47()!=null? movetoim.getP47T47():0);
                ps.setShort(49,movetoim.getP48T47()!=null? movetoim.getP48T47():0);
                ps.setDate(50,new java.sql.Date(movetoim.getP49T47().getTimeInMillis()));
                ps.setShort(51,movetoim.getP100T47()!=null? movetoim.getP100T47():0);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return movetoim;
}


}
