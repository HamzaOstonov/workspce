package com.is.bpri;


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

public class BproductService {
	
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM Bproduct ";


    public List<Bproduct> getBproduct()  {

            List<Bproduct> list = new ArrayList<Bproduct>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Bproduct");
                    while (rs.next()) {
                            list.add(new Bproduct(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("customer"),
                                            rs.getInt("prodid"),
                                            rs.getDate("vdate"),
                                            rs.getString("currency"),
                                            rs.getInt("amount"),
                                            rs.getInt("emp_id"),
                                            rs.getInt("state")));
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

    private static List<FilterField> getFilterFields(BproductFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getCustomer())){
                  flfields.add(new FilterField(getCond(flfields)+ "customer=?",filter.getCustomer()));
          }
          if(!CheckNull.isEmpty(filter.getProdid())){
                  flfields.add(new FilterField(getCond(flfields)+ "prodid=?",filter.getProdid()));
          }
          if(!CheckNull.isEmpty(filter.getVdate())){
                  flfields.add(new FilterField(getCond(flfields)+ "vdate=?",filter.getVdate()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getAmount())){
                  flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_id=?",filter.getEmp_id()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(BproductFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM Bproduct ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias);
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



    public static List<Bproduct> getBproductsFl(int pageIndex, int pageSize, BproductFilter filter, String alias)  {

            List<Bproduct> list = new ArrayList<Bproduct>();
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
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new Bproduct(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("customer"),
                                            rs.getInt("prodid"),
                                            rs.getDate("vdate"),
                                            rs.getString("currency"),
                                            rs.getInt("amount"),
                                            rs.getInt("emp_id"),
                                            rs.getInt("state")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Bproduct getBproduct(int bproductId) {

            Bproduct bproduct = new Bproduct();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM bproduct WHERE bproduct_id=?");
                    ps.setInt(1, bproductId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            bproduct = new Bproduct();
                            
                            bproduct.setId(rs.getInt("id"));
                            bproduct.setBranch(rs.getString("branch"));
                            bproduct.setCustomer(rs.getInt("customer"));
                            bproduct.setProdid(rs.getInt("prodid"));
                            bproduct.setVdate(rs.getDate("vdate"));
                            bproduct.setCurrency(rs.getString("currency"));
                            bproduct.setAmount(rs.getInt("amount"));
                            bproduct.setEmp_id(rs.getInt("emp_id"));
                            bproduct.setState(rs.getInt("state"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return bproduct;
    }

    public static Bproduct create(Bproduct bproduct, String alias)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    ps = c.prepareStatement("SELECT SEQ_bproduct.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            bproduct.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO bproduct (id, branch, customer, prodid, vdate, currency, amount, emp_id, state ) VALUES (?,?,?,?,?,?,?,?,?)");
                    
                    ps.setInt(1,bproduct.getId());
                    ps.setString(2,bproduct.getBranch());
                    ps.setInt(3,bproduct.getCustomer());
                    ps.setInt(4,bproduct.getProdid());
                    ps.setDate(5, new java.sql.Date(bproduct.getVdate().getTime()));
                    ps.setString(6,bproduct.getCurrency());
                    ps.setInt(7,bproduct.getAmount());
                    ps.setInt(8,bproduct.getEmp_id());
                    ps.setInt(9,bproduct.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return bproduct;
    }

    public static void update(Bproduct bproduct, String alias)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement("UPDATE bproduct SET id=?, branch=?, customer=?, prodid=?, vdate=?, currency=?, amount=?, emp_id=?, state=?,  WHERE bproduct_id=?");
                    
                    ps.setInt(1,bproduct.getId());
                    ps.setString(2,bproduct.getBranch());
                    ps.setInt(3,bproduct.getCustomer());
                    ps.setInt(4,bproduct.getProdid());
                    ps.setDate(5,new java.sql.Date(bproduct.getVdate().getTime()));
                    ps.setString(6,bproduct.getCurrency());
                    ps.setInt(7,bproduct.getAmount());
                    ps.setInt(8,bproduct.getEmp_id());
                    ps.setInt(9,bproduct.getState());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Bproduct bproduct)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM bproduct WHERE id=?");
                    ps.setInt(1, bproduct.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

}
