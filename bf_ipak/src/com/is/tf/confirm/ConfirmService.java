package com.is.tf.confirm;

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

public class ConfirmService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM TF_confirm ";


    public List<Confirm> getconfirm()  {

            List<Confirm> list = new ArrayList<Confirm>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM TF_confirm");
                    while (rs.next()) {
                            list.add(new Confirm(
                                            rs.getString("bankinn"),
                                            rs.getString("contractidn"),
                                            rs.getString("doctype"),
                                            rs.getString("docnum"),
                                            rs.getString("chdocnum"),
                                            rs.getString("confirm"),
                                            rs.getString("reason"),
                                            rs.getString("responsiblename")));
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

    private static List<FilterField> getFilterFields(ConfirmFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getBankinn())){
                  flfields.add(new FilterField(getCond(flfields)+ "bankinn=?",filter.getBankinn()));
          }
          if(!CheckNull.isEmpty(filter.getContractidn())){
                  flfields.add(new FilterField(getCond(flfields)+ "contractidn=?",filter.getContractidn()));
          }
          if(!CheckNull.isEmpty(filter.getDoctype())){
                  flfields.add(new FilterField(getCond(flfields)+ "doctype=?",filter.getDoctype()));
          }
          if(!CheckNull.isEmpty(filter.getDocnum())){
                  flfields.add(new FilterField(getCond(flfields)+ "docnum=?",filter.getDocnum()));
          }
          if(!CheckNull.isEmpty(filter.getChdocnum())){
                  flfields.add(new FilterField(getCond(flfields)+ "chdocnum=?",filter.getChdocnum()));
          }
          if(!CheckNull.isEmpty(filter.getConfirm())){
                  flfields.add(new FilterField(getCond(flfields)+ "confirm=?",filter.getConfirm()));
          }
          if(!CheckNull.isEmpty(filter.getReason())){
                  flfields.add(new FilterField(getCond(flfields)+ "reason=?",filter.getReason()));
          }
          if(!CheckNull.isEmpty(filter.getResponsiblename())){
                  flfields.add(new FilterField(getCond(flfields)+ "responsiblename=?",filter.getResponsiblename()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ConfirmFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_confirm ");
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



    public static List<Confirm> getconfirmsFl(int pageIndex, int pageSize, ConfirmFilter filter)  {

            List<Confirm> list = new ArrayList<Confirm>();
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
                            list.add(new Confirm(
                                            rs.getString("bankinn"),
                                            rs.getString("contractidn"),
                                            rs.getString("doctype"),
                                            rs.getString("docnum"),
                                            rs.getString("chdocnum"),
                                            rs.getString("confirm"),
                                            rs.getString("reason"),
                                            rs.getString("responsiblename")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Confirm getconfirm(int confirmId) {

    	Confirm confirm = new Confirm();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM TF_confirm WHERE id=?");
                    ps.setInt(1, confirmId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            confirm = new Confirm();
                            
                            confirm.setBankinn(rs.getString("bankinn"));
                            confirm.setContractidn(rs.getString("contractidn"));
                            confirm.setDoctype(rs.getString("doctype"));
                            confirm.setDocnum(rs.getString("docnum"));
                            confirm.setChdocnum(rs.getString("chdocnum"));
                            confirm.setConfirm(rs.getString("confirm"));
                            confirm.setReason(rs.getString("reason"));
                            confirm.setResponsiblename(rs.getString("responsiblename"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return confirm;
    }

    public static Confirm create(Confirm confirm)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_TF_confirm.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                          //  Confirm.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_confirm (bankinn, contractidn, doctype, docnum, chdocnum, confirm, reason, responsiblename, ) VALUES (?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,confirm.getBankinn());
                    ps.setString(2,confirm.getContractidn());
                    ps.setString(3,confirm.getDoctype());
                    ps.setString(4,confirm.getDocnum());
                    ps.setString(5,confirm.getChdocnum());
                    ps.setString(6,confirm.getConfirm());
                    ps.setString(7,confirm.getReason());
                    ps.setString(8,confirm.getResponsiblename());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return confirm;
    }

    public static void update(Confirm confirm)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE TF_confirm SET bankinn=?, contractidn=?, doctype=?, docnum=?, chdocnum=?, confirm=?, reason=?, responsiblename=?,  WHERE id=?");
                    
                    ps.setString(1,confirm.getBankinn());
                    ps.setString(2,confirm.getContractidn());
                    ps.setString(3,confirm.getDoctype());
                    ps.setString(4,confirm.getDocnum());
                    ps.setString(5,confirm.getChdocnum());
                    ps.setString(6,confirm.getConfirm());
                    ps.setString(7,confirm.getReason());
                    ps.setString(8,confirm.getResponsiblename());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Confirm confirm)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM TF_confirm WHERE id=?");
                    //ps.setLong(1, confirm.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

}
