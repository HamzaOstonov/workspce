package com.is.hr;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;



public class pay_calc_LOGService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM pay_calc_LOG ";


        public List<pay_calc_LOG> getpay_calc_LOG()  {

                List<pay_calc_LOG> list = new ArrayList<pay_calc_LOG>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM pay_calc_LOG");
                        while (rs.next()) {
                                list.add(new pay_calc_LOG(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("emp_id"),
                                                rs.getDate("curdate"),
                                                rs.getDate("period"),
                                                rs.getString("user_id"),
                                                rs.getString("user_name"),
                                                rs.getString("txt"),
                                                rs.getString("err"),
                                                rs.getDate("sysd")));
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

        private static List<FilterField> getFilterFields(pay_calc_LOGFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getBranch())){
                      flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_id=?",filter.getEmp_id()));
              }
              if(!CheckNull.isEmpty(filter.getCurdate())){
                      flfields.add(new FilterField(getCond(flfields)+ "curdate=?",filter.getCurdate()));
              }
              if(!CheckNull.isEmpty(filter.getPeriod())){
                      flfields.add(new FilterField(getCond(flfields)+ "period=?",filter.getPeriod()));
              }
              if(!CheckNull.isEmpty(filter.getUser_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "user_id=?",filter.getUser_id()));
              }
              if(!CheckNull.isEmpty(filter.getUser_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "user_name=?",filter.getUser_name()));
              }
              if(!CheckNull.isEmpty(filter.getTxt())){
                      flfields.add(new FilterField(getCond(flfields)+ "txt=?",filter.getTxt()));
              }
              if(!CheckNull.isEmpty(filter.getErr())){
                      flfields.add(new FilterField(getCond(flfields)+ "err=?",filter.getErr()));
              }
              if(!CheckNull.isEmpty(filter.getSysd())){
                      flfields.add(new FilterField(getCond(flfields)+ "sysd=?",filter.getSysd()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(pay_calc_LOGFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM pay_calc_LOG ");
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



        public static List<pay_calc_LOG> getpay_calc_LOGsFl(int pageIndex, int pageSize, pay_calc_LOGFilter filter)  {

                List<pay_calc_LOG> list = new ArrayList<pay_calc_LOG>();
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
                                list.add(new pay_calc_LOG(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("emp_id"),
                                                rs.getDate("curdate"),
                                                rs.getDate("period"),
                                                rs.getString("user_id"),
                                                rs.getString("user_name"),
                                                rs.getString("txt"),
                                                rs.getString("err"),
                                                rs.getDate("sysd")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public pay_calc_LOG getpay_calc_LOG(int pay_calc_logId) {

                pay_calc_LOG pay_calc_log = new pay_calc_LOG();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM pay_calc_log WHERE id=?");
                        ps.setInt(1, pay_calc_logId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                pay_calc_log = new pay_calc_LOG();
                                
                                pay_calc_log.setId(rs.getInt("id"));
                                pay_calc_log.setBranch(rs.getString("branch"));
                                pay_calc_log.setEmp_id(rs.getInt("emp_id"));
                                pay_calc_log.setCurdate(rs.getDate("curdate"));
                                pay_calc_log.setPeriod(rs.getDate("period"));
                                pay_calc_log.setUser_id(rs.getString("user_id"));
                                pay_calc_log.setUser_name(rs.getString("user_name"));
                                pay_calc_log.setTxt(rs.getString("txt"));
                                pay_calc_log.setErr(rs.getString("err"));
                                pay_calc_log.setSysd(rs.getDate("sysd"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return pay_calc_log;
        }

        public static pay_calc_LOG create(pay_calc_LOG pay_calc_log)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_pay_calc_log.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                pay_calc_log.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO pay_calc_log (id, branch, emp_id, curdate, period, user_id, user_name, txt, err, sysd ) VALUES (?,?,?,?,?,?,?,?,?,?)");
                        
                        ps.setInt(1,pay_calc_log.getId());
                        ps.setString(2,pay_calc_log.getBranch());
                        ps.setInt(3,pay_calc_log.getEmp_id());
                        ps.setDate(4,new java.sql.Date(pay_calc_log.getCurdate().getTime()));
                        ps.setDate(5,new java.sql.Date(pay_calc_log.getPeriod().getTime()));
                        ps.setString(6,pay_calc_log.getUser_id());
                        ps.setString(7,pay_calc_log.getUser_name());
                        ps.setString(8,pay_calc_log.getTxt());
                        ps.setString(9,pay_calc_log.getErr());
                        ps.setDate(10,new java.sql.Date(pay_calc_log.getSysd().getTime()));
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return pay_calc_log;
        }

        public static void update(pay_calc_LOG pay_calc_log)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE pay_calc_log SET id=?, branch=?, emp_id=?, curdate=?, period=?, user_id=?, user_name=?, txt=?, err=?, sysd=?  WHERE id=?");
                        
                        ps.setInt(1,pay_calc_log.getId());
                        ps.setString(2,pay_calc_log.getBranch());
                        ps.setInt(3,pay_calc_log.getEmp_id());
                        ps.setDate(4,new java.sql.Date(pay_calc_log.getCurdate().getTime()));
                        ps.setDate(5,new java.sql.Date(pay_calc_log.getPeriod().getTime()));
                        ps.setString(6,pay_calc_log.getUser_id());
                        ps.setString(7,pay_calc_log.getUser_name());
                        ps.setString(8,pay_calc_log.getTxt());
                        ps.setString(9,pay_calc_log.getErr());
                        ps.setDate(10,new java.sql.Date(pay_calc_log.getSysd().getTime()));
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(pay_calc_LOG pay_calc_log)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM pay_calc_log WHERE id=?");
                        ps.setLong(1, pay_calc_log.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }


}

