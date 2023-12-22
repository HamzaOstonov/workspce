package com.is.tf.paymentrefsumchange;

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

public class PaymentrefsumchangeService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_Paymentrefsumchange ";


    public static List<Paymentrefsumchange> getPaymentrefsumchange(String p2t37, Long id_contract)   {

            List<Paymentrefsumchange> list = new ArrayList<Paymentrefsumchange>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_Paymentrefsumchange where p1t38 = '"+p2t37+"' and id_contract= "+id_contract+" order by  p12t38");
                    while (rs.next()) {
                            list.add(new Paymentrefsumchange(
                            		  rs.getLong("id"),
                                      rs.getString("p0t38"),
                                      rs.getString("p1t38"),
                                      rs.getDouble("p2t38"),
                                      rs.getDouble("p3t38"),
                                      rs.getDouble("p4t38"),
                                      rs.getString("p5t38"),
                                      rs.getString("p6t38"),
                                      rs.getString("p7t38"),
                                      rs.getString("p8t38"),
                                      rs.getString("p9t38"),
                                      rs.getString("p10t38"),
                                      rs.getLong("id_contract"),
                                      rs.getInt("p12t38"),
                                      rs.getString("p13t38"),
                                      rs.getString("p14t38"),
                                      rs.getString("p15t38"),
                                      rs.getDate("p18t38"),
                                      rs.getString("p100t38")));
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

    private static List<FilterField> getFilterFields(PaymentrefsumchangeFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


            if(!CheckNull.isEmpty(filter.getId())){
                flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
        }
        if(!CheckNull.isEmpty(filter.getP0t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p0t38=?",filter.getP0t38()));
        }
        if(!CheckNull.isEmpty(filter.getP1t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p1t38=?",filter.getP1t38()));
        }
        if(!CheckNull.isEmpty(filter.getP2t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p2t38=?",filter.getP2t38()));
        }
        if(!CheckNull.isEmpty(filter.getP3t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p3t38=?",filter.getP3t38()));
        }
        if(!CheckNull.isEmpty(filter.getP4t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p4t38=?",filter.getP4t38()));
        }
        if(!CheckNull.isEmpty(filter.getP5t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p5t38=?",filter.getP5t38()));
        }
        if(!CheckNull.isEmpty(filter.getP6t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p6t38=?",filter.getP6t38()));
        }
        if(!CheckNull.isEmpty(filter.getP7t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p7t38=?",filter.getP7t38()));
        }
        if(!CheckNull.isEmpty(filter.getP8t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p8t38=?",filter.getP8t38()));
        }
        if(!CheckNull.isEmpty(filter.getP9t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p9t38=?",filter.getP9t38()));
        }
        if(!CheckNull.isEmpty(filter.getP10t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p10t38=?",filter.getP10t38()));
        }
        if(!CheckNull.isEmpty(filter.getId_contract())){
                flfields.add(new FilterField(getCond(flfields)+ "id_contract=?",filter.getId_contract()));
        }
        if(!CheckNull.isEmpty(filter.getP12t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p12t38=?",filter.getP12t38()));
        }
        if(!CheckNull.isEmpty(filter.getP13t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p13t38=?",filter.getP13t38()));
        }
        if(!CheckNull.isEmpty(filter.getP14t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p14t38=?",filter.getP14t38()));
        }
        if(!CheckNull.isEmpty(filter.getP15t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p15t38=?",filter.getP15t38()));
        }
        if(!CheckNull.isEmpty(filter.getP18t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p18t38=?",filter.getP18t38()));
        }
        if(!CheckNull.isEmpty(filter.getP100t38())){
                flfields.add(new FilterField(getCond(flfields)+ "p100t38=?",filter.getP100t38()));
        }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(PaymentrefsumchangeFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_Paymentrefsumchange ");
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



    public static List<Paymentrefsumchange> getPaymentrefsumchangesFl(int pageIndex, int pageSize, PaymentrefsumchangeFilter filter)  {

            List<Paymentrefsumchange> list = new ArrayList<Paymentrefsumchange>();
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
                            list.add(new Paymentrefsumchange(
                            	    rs.getLong("id"),
                                    rs.getString("p0t38"),
                                    rs.getString("p1t38"),
                                    rs.getDouble("p2t38"),
                                    rs.getDouble("p3t38"),
                                    rs.getDouble("p4t38"),
                                    rs.getString("p5t38"),
                                    rs.getString("p6t38"),
                                    rs.getString("p7t38"),
                                    rs.getString("p8t38"),
                                    rs.getString("p9t38"),
                                    rs.getString("p10t38"),
                                    rs.getLong("id_contract"),
                                    rs.getInt("p12t38"),
                                    rs.getString("p13t38"),
                                    rs.getString("p14t38"),
                                    rs.getString("p15t38"),
                                    rs.getDate("p18t38"),
                                    rs.getString("p100t38")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Paymentrefsumchange getPaymentrefsumchange(int paymentrefsumchangeId) {

            Paymentrefsumchange paymentrefsumchange = new Paymentrefsumchange();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_paymentrefsumchange WHERE id=?");
                    ps.setInt(1, paymentrefsumchangeId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            paymentrefsumchange = new Paymentrefsumchange();
                            
                            paymentrefsumchange.setId(rs.getLong("id"));
                            paymentrefsumchange.setP0t38(rs.getString("p0t38"));
                            paymentrefsumchange.setP1t38(rs.getString("p1t38"));
                            paymentrefsumchange.setP2t38(rs.getDouble("p2t38"));
                            paymentrefsumchange.setP3t38(rs.getDouble("p3t38"));
                            paymentrefsumchange.setP4t38(rs.getDouble("p4t38"));
                            paymentrefsumchange.setP5t38(rs.getString("p5t38"));
                            paymentrefsumchange.setP6t38(rs.getString("p6t38"));
                            paymentrefsumchange.setP7t38(rs.getString("p7t38"));
                            paymentrefsumchange.setP8t38(rs.getString("p8t38"));
                            paymentrefsumchange.setP9t38(rs.getString("p9t38"));
                            paymentrefsumchange.setP10t38(rs.getString("p10t38"));
                            paymentrefsumchange.setId_contract(rs.getLong("id_contract"));
                            paymentrefsumchange.setP12t38(rs.getInt("p12t38"));
                            paymentrefsumchange.setP13t38(rs.getString("p13t38"));
                            paymentrefsumchange.setP14t38(rs.getString("p14t38"));
                            paymentrefsumchange.setP15t38(rs.getString("p15t38"));
                            paymentrefsumchange.setP18t38(rs.getDate("p18t38"));
                            paymentrefsumchange.setP100t38(rs.getString("p100t38"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return paymentrefsumchange;
    }

    public static  Res create(Paymentrefsumchange paymr, Long idc, String p2t37)  {
    	Res res = new Res();
            Connection c = null;
            PreparedStatement ps = null;
            Long aid=new Long ("0");
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_paymentrefsumchange.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                    	aid=(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_paymentrefsumchange (id, p0t38, p1t38, p2t38, p4t38, p6t38, id_contract,p14t38,p100t38) VALUES (?,?,?,?,?,?,?,?,?)");
                    
                    ps.setLong(1,aid);
                    ps.setString(2,paymr.getP0t38());
                    ps.setString(3,p2t37);
                    ps.setDouble(4,paymr.getP2t38());
                    ps.setDouble(5,paymr.getP4t38());
                    ps.setString(6,paymr.getP6t38());
                    ps.setLong(7,idc);
                    ps.setString(8,paymr.getP14t38());
                    ps.setString(9,"9");
                    
                    if (ps.executeUpdate() == 1) {
        				c.commit();
        				res = new Res(0, "Ok");
        				System.out.println("Ok");
        			} else {
        				c.rollback();
        				res = new Res(1, "Изменение невозможно!");
        			    System.out.println("Изменение невозможно!");
        			}
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return res;
    }

    public static Res updateGlBuh(Paymentrefsumchange paymentrefsumchange, Long idc, String P2T37)  {
Res res= new Res();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_paymentrefsumchange SET id=?, p0t38=?, p4t38=?, p6t38=?, p8t38=?, p10t38=?, id_contract=?, p12t38=?,p14t38=?, p15t38=?, p100t38=?  WHERE id=?");
                   
                    ps.setLong(1,paymentrefsumchange.getId());
                    ps.setString(2,paymentrefsumchange.getP0t38());
                    //ps.setString(3,paymentrefsumchange.getP1t38());
                    //ps.setDouble(4,paymentrefsumchange.getP2t38());
                    //ps.setDouble(5,paymentrefsumchange.getP3t38());
                    ps.setDouble(3,paymentrefsumchange.getP4t38());
                    //ps.setString(7,paymentrefsumchange.getP5t38());
                    ps.setString(4,paymentrefsumchange.getP6t38());
                    //ps.setString(9,paymentrefsumchange.getP7t38());
                    ps.setString(5,paymentrefsumchange.getP8t38());
                    //ps.setString(11,paymentrefsumchange.getP9t38());
                    ps.setString(6,paymentrefsumchange.getP10t38());
                    ps.setLong(7,idc);
                    ps.setDouble(8,paymentrefsumchange.getP12t38());
                    //ps.setString(15,paymentrefsumchange.getP13t38());
                    ps.setString(9,paymentrefsumchange.getP14t38());
                    ps.setString(10,paymentrefsumchange.getP15t38());
                    ps.setString(11,paymentrefsumchange.getP100t38());
                    ps.setLong(12,paymentrefsumchange.getId());
                    
                    if (ps.executeUpdate() == 1) {
         				c.commit();
         				res = new Res(0, "Ok");
         				
         			} else {
         				c.rollback();
         				res = new Res(1, "Ошибка!");
         			}
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
return res;
    }

    public static void remove(Paymentrefsumchange paymentrefsumchange)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_paymentrefsumchange WHERE id=? and p100t38 in ('g','7')");
                    ps.setLong(1, paymentrefsumchange.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

}
