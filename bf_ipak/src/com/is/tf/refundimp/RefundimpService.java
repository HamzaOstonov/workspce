package com.is.tf.refundimp;

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
import com.is.utils.Res;

public class RefundimpService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Refundimp ";


    public List<Refundimp> getRefundimp()  {

            List<Refundimp> list = new ArrayList<Refundimp>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Refundimp");
                    while (rs.next()) {
                            list.add(new Refundimp(
                                            rs.getLong("id"),
                                            rs.getString("p1t45"),
                                            rs.getString("p0t45"),
                                            rs.getDate("p2t45"),
                                            rs.getString("p3t45"),
                                            rs.getString("p4t45"),
                                            rs.getString("p5t45"),
                                            rs.getDouble("p6t45"),
                                            rs.getDouble("p7t45"),
                                            rs.getDouble("p8t45"),
                                            rs.getDouble("p9t45"),
                                            rs.getDouble("p10t45"),
                                            rs.getDouble("p11t45"),
                                            rs.getString("p12t45"),
                                            rs.getString("p13t45"),
                                            rs.getDouble("p14t45"),
                                            rs.getString("p15t45"),
                                            rs.getDouble("p16t45"),
                                            rs.getDouble("p17t45"),
                                            rs.getString("p18t45"),
                                            rs.getString("p19t45"),
                                            rs.getString("p20t45"),
                                            rs.getString("p21t45"),
                                            rs.getDouble("p22t45"),
                                            rs.getString("p23t45"),
                                            rs.getDate("p24t45"),
                                            rs.getString("p100t45"),
                                            rs.getLong("id_contract")
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

    private static List<FilterField> getFilterFields(RefundimpFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getP1t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p1t45=?",filter.getP1t45()));
          }
          if(!CheckNull.isEmpty(filter.getP0t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p0t45=?",filter.getP0t45()));
          }
          if(!CheckNull.isEmpty(filter.getP2t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p2t45=?",filter.getP2t45()));
          }
          if(!CheckNull.isEmpty(filter.getP3t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p3t45=?",filter.getP3t45()));
          }
          if(!CheckNull.isEmpty(filter.getP4t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p4t45=?",filter.getP4t45()));
          }
          if(!CheckNull.isEmpty(filter.getP5t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p5t45=?",filter.getP5t45()));
          }
          if(!CheckNull.isEmpty(filter.getP6t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p6t45=?",filter.getP6t45()));
          }
          if(!CheckNull.isEmpty(filter.getP7t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p7t45=?",filter.getP7t45()));
          }
          if(!CheckNull.isEmpty(filter.getP8t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p8t45=?",filter.getP8t45()));
          }
          if(!CheckNull.isEmpty(filter.getP9t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p9t45=?",filter.getP9t45()));
          }
          if(!CheckNull.isEmpty(filter.getP10t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p10t45=?",filter.getP10t45()));
          }
          if(!CheckNull.isEmpty(filter.getP11t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p11t45=?",filter.getP11t45()));
          }
          if(!CheckNull.isEmpty(filter.getP12t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p12t45=?",filter.getP12t45()));
          }
          if(!CheckNull.isEmpty(filter.getP13t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p13t45=?",filter.getP13t45()));
          }
          if(!CheckNull.isEmpty(filter.getP14t45())){
                  flfields.add(new FilterField(getCond(flfields)+ "p14t45=?",filter.getP14t45()));
          }
        /*
          if(!CheckNull.isEmpty(filter.getP15t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p15t45=?",filter.getP15t45()));
          }
        
          if(!CheckNull.isEmpty(filter.getP17t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p17t45=?",filter.getP17t45()));
          }
          
          if(!CheckNull.isEmpty(filter.getP18t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p18t45=?",filter.getP18t45()));
      }
      if(!CheckNull.isEmpty(filter.getP19t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p19t45=?",filter.getP19t45()));
      }
      if(!CheckNull.isEmpty(filter.getP20t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p20t45=?",filter.getP20t45()));
      }
      if(!CheckNull.isEmpty(filter.getP21t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p21t45=?",filter.getP21t45()));
      }
      if(!CheckNull.isEmpty(filter.getP22t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p22t45=?",filter.getP22t45()));
      }
      if(!CheckNull.isEmpty(filter.getP23t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p23t45=?",filter.getP23t45()));
      }
      if(!CheckNull.isEmpty(filter.getP24t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p24t45=?",filter.getP24t45()));
      }
      if(!CheckNull.isEmpty(filter.getP100t45())){
              flfields.add(new FilterField(getCond(flfields)+ "p100t45=?",filter.getP100t45()));
      }
*/
          
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(RefundimpFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Refundimp ");
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



    public static List<Refundimp> getRefundimpsFl(int pageIndex, int pageSize, RefundimpFilter filter)  {

            List<Refundimp> list = new ArrayList<Refundimp>();
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
                            list.add(new Refundimp(
                                            rs.getLong("id"),
                                            rs.getString("p1t45"),
                                            rs.getString("p0t45"),
                                            rs.getDate("p2t45"),
                                            rs.getString("p3t45"),
                                            rs.getString("p4t45"),
                                            rs.getString("p5t45"),
                                            rs.getDouble("p6t45"),
                                            rs.getDouble("p7t45"),
                                            rs.getDouble("p8t45"),
                                            rs.getDouble("p9t45"),
                                            rs.getDouble("p10t45"),
                                            rs.getDouble("p11t45"),
                                            rs.getString("p12t45"),
                                            rs.getString("p13t45"),
                                            rs.getDouble("p14t45"),
                                            rs.getString("p15t45"),
                                            rs.getDouble("p16t45"),
                                            rs.getDouble("p17t45"),
                                            rs.getString("p18t45"),
                                            rs.getString("p19t45"),
                                            rs.getString("p20t45"),
                                            rs.getString("p21t45"),
                                            rs.getDouble("p22t45"),
                                            rs.getString("p23t45"),
                                            rs.getDate("p24t45"),
                                            rs.getString("p100t45"),
                                            rs.getLong("id_contract")
                            
                            ));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Refundimp getRefundimp(int refundimpId) {

            Refundimp refundimp = new Refundimp();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_refundimp WHERE id=?");
                    ps.setInt(1, refundimpId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            refundimp = new Refundimp();
                            
                            refundimp.setId(rs.getLong("id"));
                            refundimp.setP1t45(rs.getString("p1t45"));
                            refundimp.setP0t45(rs.getString("p0t45"));
                            refundimp.setP2t45(rs.getDate("p2t45"));
                            refundimp.setP3t45(rs.getString("p3t45"));
                            refundimp.setP4t45(rs.getString("p4t45"));
                            refundimp.setP5t45(rs.getString("p5t45"));
                            refundimp.setP6t45(rs.getDouble("p6t45"));
                            refundimp.setP7t45(rs.getDouble("p7t45"));
                            refundimp.setP8t45(rs.getDouble("p8t45"));
                            refundimp.setP9t45(rs.getDouble("p9t45"));
                            refundimp.setP10t45(rs.getDouble("p10t45"));
                            refundimp.setP11t45(rs.getDouble("p11t45"));
                            refundimp.setP12t45(rs.getString("p12t45"));
                            refundimp.setP13t45(rs.getString("p13t45"));
                            refundimp.setP14t45(rs.getDouble("p14t45"));
                            refundimp.setP15t45(rs.getString("p15t45"));
                            refundimp.setP16t45(rs.getDouble("p16t45"));
                            refundimp.setP17t45(rs.getDouble("p17t45"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return refundimp;
    }

    public static Res create(Refundimp refundimp , String idn, Long idc)  {
    	Res res = new Res();
            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_refundimp.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            refundimp.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_refundimp (id, p1t45, p0t45, p2t45, p3t45, p4t45, p5t45, p6t45, p7t45, p8t45, p9t45, p10t45, p11t45, p13t45, p14t45, p15t45, p22t45, p23t45, p100t45, id_contract) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,refundimp.getId());
                    ps.setString(2,idn);
                    ps.setString(3,"0");
                    ps.setDate(4,new java.sql.Date(refundimp.getP2t45().getTime()));
                    ps.setString(5,refundimp.getP3t45()!=null?refundimp.getP3t45():null);
                    ps.setString(6,refundimp.getP4t45()!=null?refundimp.getP4t45():null);
                    ps.setString(7,refundimp.getP5t45()!=null?refundimp.getP5t45():null);
                    ps.setDouble(8,refundimp.getP6t45()!=null?refundimp.getP6t45():null);
                    ps.setDouble(9,refundimp.getP7t45()!=null?refundimp.getP7t45():null);
                    ps.setDouble(10,refundimp.getP8t45()!=null?refundimp.getP8t45():null);
                    ps.setDouble(11,refundimp.getP9t45()!=null?refundimp.getP9t45():null);
                    ps.setDouble(12,refundimp.getP10t45()!=null?refundimp.getP10t45():null);
                    ps.setDouble(13,refundimp.getP11t45()!=null?refundimp.getP11t45():null);
                    ps.setString(14,refundimp.getP13t45()!=null?refundimp.getP13t45():null);
                    ps.setDouble(15,refundimp.getP14t45()!=null?refundimp.getP14t45():null);
                    ps.setString(16,refundimp.getP15t45()!=null?refundimp.getP15t45():null);
                    ps.setDouble(17,refundimp.getP22t45()!=null?refundimp.getP22t45():null);
                    ps.setString(18,refundimp.getP23t45()!=null?refundimp.getP23t45():null);
                    ps.setString(19,"9");
                    ps.setLong(20,idc);
                    
                    if (ps.executeUpdate() == 1) {
           				c.commit();
           				res = new Res(0, "Ok");
           			} else {
           				c.rollback();
           				res = new Res(1, "Сохранение невозоможно!");
           			}
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return res;
    }

    public static Res update(Refundimp refundimp)  {
    	 Res res = new Res();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_refundimp set p1t45=?, p0t45=?, p2t45=?, p3t45=?, p4t45=?, p5t45=?, p6t45=?, p7t45=?, p8t45=?, p9t45=?, p10t45=?, p11t45=?, p12t45=?, p13t45=?, p14t45=?, p15t45=?, p18t45=?, p19t45=?, p21t45=?, p22t45=?, p23t45=?, p100t45=?  WHERE id=?");
                    
                    
                    ps.setString(1,refundimp.getP1t45());
                    ps.setString(2,refundimp.getP0t45());
                    ps.setDate(3,new java.sql.Date(refundimp.getP2t45().getTime()));
                    ps.setString(4,refundimp.getP3t45());
                    ps.setString(5,refundimp.getP4t45());
                    ps.setString(6,refundimp.getP5t45());
                    ps.setDouble(7,refundimp.getP6t45());
                    ps.setDouble(8,refundimp.getP7t45());
                    ps.setDouble(9,refundimp.getP8t45());
                    ps.setDouble(10,refundimp.getP9t45());
                    ps.setDouble(11,refundimp.getP10t45());
                    ps.setDouble(12,refundimp.getP11t45());
                    ps.setString(13,refundimp.getP12t45());
                    ps.setString(14,refundimp.getP13t45());
                    ps.setDouble(15,refundimp.getP14t45());
                    ps.setString(16,refundimp.getP15t45());
                    //ps.setDouble(18,refundimp.getP16t45());
                    //ps.setDouble(19,refundimp.getP17t45());
                    ps.setString(17,refundimp.getP18t45());
                    ps.setString(18,refundimp.getP19t45());
                    //ps.setString(20,refundimp.getP20t45());
                    ps.setString(19,refundimp.getP21t45());
                    ps.setDouble(20,refundimp.getP22t45());
                    ps.setString(21,refundimp.getP23t45());
                    ps.setString(22,refundimp.getP100t45());
                    ps.setLong(23,refundimp.getId());
                    if (ps.executeUpdate() == 1) {
        				c.commit();
        				res = new Res(0, "Ok");
        				System.out.println("Ok");
        			} else {
        				c.rollback();
        				res = new Res(1, "Изменение невозможно!");
        			    System.out.println("Изменение невозможно!");
        			}
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return res;
    }

    public static void remove(Refundimp refundimp, Long id_contract)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_refundimp WHERE id_contract=? and p100t45 in ('0','1','2','3','4','5')");
                    ps.setLong(1, id_contract);
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

    public static Res remove1(Refundimp refundimp)  {
  	  Res res = new Res();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_refundimp WHERE id=?");
                    ps.setLong(1, refundimp.getId());
                    if (ps.executeUpdate() == 1) {
        				c.commit();
        				res = new Res(0, "Ok");
        				System.out.println("Ok");
        			} else {
        				c.rollback();
        				res = new Res(1, "Удаление невозможно!");
        			    System.out.println("Удаление невозможно!");
        			}
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return res;
    }





    public static com.sbs.service.RefundImp create(com.sbs.service.RefundImp refundimp, String P1T45, Long id_contract)  {

        Connection c = null;
        PreparedStatement ps = null;
        Long aid=new Long ("0");
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SQ_TF_refundimp.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                aid=(rs.getLong("id"));
                }
                ps = c.prepareStatement("INSERT INTO TF_refundimp (id, p1t45, p0t45, p2t45, p3t45, p5t45, p6t45, p7t45, p8t45, p9t45, p10t45, p11t45, p14t45,p15t45,p18t45,p19t45,p21t45,p22t45,p23t45,p24t45,p100t45,id_contract) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
                ps.setLong(1,aid);
                ps.setString(2,P1T45);
                ps.setInt(3,refundimp.getP0T45()!=null? refundimp.getP0T45():0);
                ps.setDate(4,new java.sql.Date(refundimp.getP2T45().getTimeInMillis()));
                ps.setInt(5,refundimp.getP3T45());
                //ps.setString(6,refundimp.getP4T45());
                ps.setString(6,refundimp.getP5T45()!=null?refundimp.getP5T45():null);
                ps.setDouble(7,refundimp.getP6T45()!=null?refundimp.getP6T45():null);
                ps.setDouble(8,refundimp.getP7T45()!=null?refundimp.getP7T45():null);
                ps.setDouble(9,refundimp.getP8T45()!=null?refundimp.getP8T45():null);
                ps.setDouble(10,refundimp.getP9T45()!=null?refundimp.getP9T45():null);
                ps.setDouble(11,refundimp.getP10T45()!=null?refundimp.getP10T45():null);
                ps.setDouble(12,refundimp.getP11T45()!=null?refundimp.getP11T45():null);
                //ps.setString(14,P12T45);
                //ps.setString(15,P13T45);
                ps.setDouble(13,refundimp.getP14T45()!=null?refundimp.getP14T45():null);
                ps.setString(14,refundimp.getP15T45()!=null?refundimp.getP15T45():null);
                //ps.setDouble(18,refundimp.getP16T45());
                //ps.setDouble(19,refundimp.getP17T45());
                ps.setShort(15,refundimp.getP18T45()!=null?refundimp.getP18T45():null);
                ps.setString(16,refundimp.getP19T45()!=null?refundimp.getP19T45():null);
                //ps.setString(23,refundimp.getP20T45());
                ps.setShort(17,refundimp.getP21T45()!=null?refundimp.getP21T45():null);
                ps.setDouble(18,refundimp.getP22T45()!=null?refundimp.getP22T45():null);
                ps.setString(19,refundimp.getP23T45()!=null?refundimp.getP23T45():null);
                ps.setDate(20,refundimp.getP24T45()!=null?new java.sql.Date(refundimp.getP24T45().getTimeInMillis()):null);
                ps.setShort(21,refundimp.getP100T45());
                ps.setLong(22,id_contract);
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return refundimp;
}

}
