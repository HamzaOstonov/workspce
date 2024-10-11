package com.is.hr;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;



public class ok_awardService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM ok_award ";


        public List<ok_award> getok_award()  {

                List<ok_award> list = new ArrayList<ok_award>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM ok_award");
                        while (rs.next()) {
                                list.add(new ok_award(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getInt("award_date"),
                                                rs.getString("award_info"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getInt("award_date_mm"),
                                                rs.getInt("award_date_dd"),
                                                rs.getString("emp_code_name")));
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

        private static List<FilterField> getFilterFields(ok_awardFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getBranch())){
                      flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
              }
              if(!CheckNull.isEmpty(filter.getPersonal_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "personal_code=?",filter.getPersonal_code()));
              }
              if(!CheckNull.isEmpty(filter.getAward_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "award_date=?",filter.getAward_date()));
              }
              if(!CheckNull.isEmpty(filter.getAward_info())){
                      flfields.add(new FilterField(getCond(flfields)+ "award_info=?",filter.getAward_info()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
              }
              if(!CheckNull.isEmpty(filter.getIns_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
              }
              if(!CheckNull.isEmpty(filter.getAward_date_mm())){
                      flfields.add(new FilterField(getCond(flfields)+ "award_date_mm=?",filter.getAward_date_mm()));
              }
              if(!CheckNull.isEmpty(filter.getAward_date_dd())){
                      flfields.add(new FilterField(getCond(flfields)+ "award_date_dd=?",filter.getAward_date_dd()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(ok_awardFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM ok_award ");
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



        public static List<ok_award> getok_awardsFl(int pageIndex, int pageSize, ok_awardFilter filter)  {

                List<ok_award> list = new ArrayList<ok_award>();
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
                                list.add(new ok_award(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getInt("award_date"),
                                                rs.getString("award_info"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getInt("award_date_mm"),
                                                rs.getInt("award_date_dd"),
                                                rs.getString("emp_code_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public ok_award getok_award(int ok_awardId) {

                ok_award ok_award = new ok_award();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_award WHERE id=?");
                        ps.setInt(1, ok_awardId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_award = new ok_award();
                                
                                ok_award.setId(rs.getInt("id"));
                                ok_award.setBranch(rs.getString("branch"));
                                ok_award.setPersonal_code(rs.getInt("personal_code"));
                                ok_award.setAward_date(rs.getInt("award_date"));
                                ok_award.setAward_info(rs.getString("award_info"));
                                ok_award.setEmp_code(rs.getInt("emp_code"));
                                ok_award.setIns_date(rs.getDate("ins_date"));
                                ok_award.setAward_date_mm(rs.getInt("award_date_mm"));
                                ok_award.setAward_date_dd(rs.getInt("award_date_dd"));
                                ok_award.setEmp_code_name(rs.getString("emp_code_name"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return ok_award;
        }

        public static ok_award create(ok_award ok_award)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_ok_award.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_award.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO ok_award (id, branch, personal_code, award_date, award_info, emp_code, ins_date, award_date_mm, award_date_dd, emp_code_name ) VALUES (?,?,?,?,?,?,?,?,?,?)");
                        
                        ps.setInt(1,ok_award.getId());
                        ps.setString(2,ok_award.getBranch());
                        ps.setInt(3,ok_award.getPersonal_code());
                        ps.setInt(4,ok_award.getAward_date());
                        ps.setString(5,ok_award.getAward_info());
                        ps.setInt(6,ok_award.getEmp_code());
                        ps.setDate(7,new java.sql.Date(ok_award.getIns_date().getTime()));
                        ps.setInt(8,ok_award.getAward_date_mm());
                        ps.setInt(9,ok_award.getAward_date_dd());
                        ps.setString(10,ok_award.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return ok_award;
        }

        public static void update(ok_award ok_award)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE ok_award SET id=?, branch=?, personal_code=?, award_date=?, award_info=?, emp_code=?, ins_date=?, award_date_mm=?, award_date_dd=?, emp_code_name=?  WHERE id=?");
                        
                        ps.setInt(1,ok_award.getId());
                        ps.setString(2,ok_award.getBranch());
                        ps.setInt(3,ok_award.getPersonal_code());
                        ps.setInt(4,ok_award.getAward_date());
                        ps.setString(5,ok_award.getAward_info());
                        ps.setInt(6,ok_award.getEmp_code());
                        ps.setDate(7,new java.sql.Date(ok_award.getIns_date().getTime()));
                        ps.setInt(8,ok_award.getAward_date_mm());
                        ps.setInt(9,ok_award.getAward_date_dd());
                        ps.setString(10,ok_award.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(ok_award ok_award)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM ok_award WHERE id=?");
                        ps.setLong(1, ok_award.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }


}