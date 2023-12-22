package com.is.tf.Accreditivtimechange;

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

public class AccreditivtimechangeService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Accreditivtimechange order by p7t22";


    public static List<Accreditivtimechange> getAccreditivtimechange(String p2t21, Long id_contract)  {

            List<Accreditivtimechange> list = new ArrayList<Accreditivtimechange>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Accreditivtimechange where p1t22 = '"+p2t21+"' and id_contract= "+id_contract+" order by p7t22 ");
                    while (rs.next()) {
                            list.add(new Accreditivtimechange(
                                            rs.getLong("id"),
                                            rs.getString("p0t22"),
                                            rs.getString("p1t22"),
                                            rs.getString("p2t22"),
                                            rs.getString("p3t22"),
                                            rs.getString("p4t22"),
                                            rs.getString("p5t22"),
                                            rs.getString("p6t22"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p8t22"),
                                            rs.getInt("p7t22"),
                                            rs.getString("p9t22"),
                                            rs.getString("p10t22"),
                                            rs.getDate("p11t22"),
                                            rs.getString("p100t22")));
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

    private static List<FilterField> getFilterFields(AccreditivtimechangeFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP0t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t22=?",filter.getP0t22()));
          }
          if(!CheckNull.isEmpty(filter.getP1t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t22=?",filter.getP1t22()));
          }
          if(!CheckNull.isEmpty(filter.getP2t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t22=?",filter.getP2t22()));
          }
          if(!CheckNull.isEmpty(filter.getP3t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t22=?",filter.getP3t22()));
          }
          if(!CheckNull.isEmpty(filter.getP4t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t22=?",filter.getP4t22()));
          }
          if(!CheckNull.isEmpty(filter.getP5t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t22=?",filter.getP5t22()));
          }
          if(!CheckNull.isEmpty(filter.getP6t22())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t22=?",filter.getP6t22()));
          }
          if(!CheckNull.isEmpty(filter.getId_contract())){
              flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
      }
      if(!CheckNull.isEmpty(filter.getP8t22())){
              flfields.add(new FilterField(getCond(flfields)+ "p8t22=?",filter.getP8t22()));
      }
      if(!CheckNull.isEmpty(filter.getP7t22())){
          flfields.add(new FilterField(getCond(flfields)+ "p7t22=?",filter.getP7t22()));
  }
      if(!CheckNull.isEmpty(filter.getP9t22())){
              flfields.add(new FilterField(getCond(flfields)+ "p9t22=?",filter.getP9t22()));
      }
      if(!CheckNull.isEmpty(filter.getP10t22())){
              flfields.add(new FilterField(getCond(flfields)+ "p10t22=?",filter.getP10t22()));
      }
      if(!CheckNull.isEmpty(filter.getP11t22())){
              flfields.add(new FilterField(getCond(flfields)+ "p11t22=?",filter.getP11t22()));
      }
      if(!CheckNull.isEmpty(filter.getP100t22())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t22=?",filter.getP100t22()));
      }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(AccreditivtimechangeFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Accreditivtimechange ");
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



    public static List<Accreditivtimechange> getAccreditivtimechangesFl(int pageIndex, int pageSize, AccreditivtimechangeFilter filter)  {

            List<Accreditivtimechange> list = new ArrayList<Accreditivtimechange>();
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
                            list.add(new Accreditivtimechange(
                                            rs.getLong("id"),
                                            rs.getString("p0t22"),
                                            rs.getString("p1t22"),
                                            rs.getString("p2t22"),
                                            rs.getString("p3t22"),
                                            rs.getString("p4t22"),
                                            rs.getString("p5t22"),
                                            rs.getString("p6t22"),
                                            rs.getLong("id_contract"),
                                            rs.getString("p8t22"),
                                            rs.getInt("p7t22"),
                                            rs.getString("p9t22"),
                                            rs.getString("p10t22"),
                                            rs.getDate("p11t22"),
                                            rs.getString("p100t22")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Accreditivtimechange getAccreditivtimechange(int accreditivtimechangeId) {

            Accreditivtimechange accreditivtimechange = new Accreditivtimechange();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_accreditivtimechange WHERE id=?");
                    ps.setInt(1, accreditivtimechangeId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            accreditivtimechange = new Accreditivtimechange();
                            
                            accreditivtimechange.setId(rs.getLong("id"));
                            accreditivtimechange.setP0t22(rs.getString("p0t22"));
                            accreditivtimechange.setP1t22(rs.getString("p1t22"));
                            accreditivtimechange.setP2t22(rs.getString("p2t22"));
                            accreditivtimechange.setP3t22(rs.getString("p3t22"));
                            accreditivtimechange.setP4t22(rs.getString("p4t22"));
                            accreditivtimechange.setP5t22(rs.getString("p5t22"));
                            accreditivtimechange.setP6t22(rs.getString("p6t22"));
                            accreditivtimechange.setId_contract(rs.getLong("id_contract"));
                            accreditivtimechange.setP8t22(rs.getString("p8t22"));
                            accreditivtimechange.setP7t22(rs.getInt("p7t22"));
                            accreditivtimechange.setP9t22(rs.getString("p9t22"));
                            accreditivtimechange.setP10t22(rs.getString("p10t22"));
                            accreditivtimechange.setP11t22(rs.getDate("p11t22"));
                            accreditivtimechange.setP100t22(rs.getString("p100t22"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return accreditivtimechange;
    }

    public static Accreditivtimechange create(Accreditivtimechange accreditivtimechange)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_accreditivtimechange.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            accreditivtimechange.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_accreditivtimechange (id, p0t22, p1t22, p2t22, p3t22, p4t22, p5t22, p6t22, id_contract, p8t22, p7t22, p9t22, p10t22, p11t22, p100t22 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setLong(1,accreditivtimechange.getId());
                    ps.setString(2,accreditivtimechange.getP0t22());
                    ps.setString(3,accreditivtimechange.getP1t22());
                    ps.setString(4,accreditivtimechange.getP2t22());
                    ps.setString(5,accreditivtimechange.getP3t22());
                    ps.setString(6,accreditivtimechange.getP4t22());
                    ps.setString(7,accreditivtimechange.getP5t22());
                    ps.setString(8,accreditivtimechange.getP6t22());
                    ps.setLong(9,accreditivtimechange.getId_contract());
                    ps.setString(10,accreditivtimechange.getP8t22());
                    ps.setInt(11,accreditivtimechange.getP7t22());
                    ps.setString(12,accreditivtimechange.getP9t22());
                    ps.setString(13,accreditivtimechange.getP10t22());
                    ps.setDate(14,new java.sql.Date(accreditivtimechange.getP11t22().getTime()));
                    ps.setString(15,accreditivtimechange.getP100t22());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return accreditivtimechange;
    }

    public static void update(Accreditivtimechange accreditivtimechange)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_accreditivtimechange SET id=?, p0t22=?, p1t22=?, p2t22=?, p3t22=?, p4t22=?, p5t22=?, p6t22=?, id_contract=?, p8t22=?, p7t22=?, p9t22=?, p10t22=?, p11t22=?, p100t22=?  WHERE id=?");
                    
                    ps.setLong(1,accreditivtimechange.getId());
                    ps.setString(2,accreditivtimechange.getP0t22());
                    ps.setString(3,accreditivtimechange.getP1t22());
                    ps.setString(4,accreditivtimechange.getP2t22());
                    ps.setString(5,accreditivtimechange.getP3t22());
                    ps.setString(6,accreditivtimechange.getP4t22());
                    ps.setString(7,accreditivtimechange.getP5t22());
                    ps.setString(8,accreditivtimechange.getP6t22());
                    ps.setLong(9,accreditivtimechange.getId_contract());
                    ps.setString(10,accreditivtimechange.getP8t22());
                    ps.setInt(11,accreditivtimechange.getP7t22());
                    ps.setString(12,accreditivtimechange.getP9t22());
                    ps.setString(13,accreditivtimechange.getP10t22());
                    ps.setDate(14,new java.sql.Date(accreditivtimechange.getP11t22().getTime()));
                    ps.setString(15,accreditivtimechange.getP100t22());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Accreditivtimechange accreditivtimechange, String p2t21, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_accreditivtimechange WHERE p1t22=? and id_contract=?");
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
    
    public static com.sbs.service.AccreditivTimeChange create2(com.sbs.service.AccreditivTimeChange accreditivTimeChange, String p1t23, Long id_contract)  {

    	 Connection c = null;
         PreparedStatement ps = null;
         long aid=new Long ("0");
         try {
                 c = ConnectionPool.getConnection();
                 ps = c.prepareStatement("SELECT SQ_TF_Accreditivtimechange.Nextval id FROM DUAL");
                 ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
                	 aid=rs.getLong("id");
                 }
                ps = c.prepareStatement("INSERT INTO TF_accreditivtimechange (id, p1t22, p2t22, p3t22, p4t22, p5t22, p6t22, id_contract, p8t22, p7t22, p11t22, p100t22 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,p1t23);
                ps.setInt(3,accreditivTimeChange.getP2T22());
                ps.setInt(4,accreditivTimeChange.getP3T22());
                ps.setInt(5,accreditivTimeChange.getP4T22());
                ps.setString(6,accreditivTimeChange.getP5T22());
                ps.setString(7,accreditivTimeChange.getP6T22());
                ps.setLong(8,id_contract);
                ps.setString(9,accreditivTimeChange.getP8T22());
                ps.setInt(10,accreditivTimeChange.getP7T22());
                ps.setDate(11,new java.sql.Date(accreditivTimeChange.getP11T22().getTimeInMillis()));
                ps.setShort(12,accreditivTimeChange.getP100T22());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return accreditivTimeChange;
}
    
}
