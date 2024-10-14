package com.is.hr;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;



public class ok_addinformService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM ok_addinform ";


        public List<ok_addinform> getok_addinform()  {

                List<ok_addinform> list = new ArrayList<ok_addinform>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM ok_addinform");
                        while (rs.next()) {
                                list.add(new ok_addinform(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getDate("addinform_date"),
                                                rs.getString("character_addinform"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getString("estimation"),
                                                rs.getInt("addinform_code"),
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

        private static List<FilterField> getFilterFields(ok_addinformFilter filter){
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
              if(!CheckNull.isEmpty(filter.getAddinform_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "addinform_date=?",filter.getAddinform_date()));
              }
              if(!CheckNull.isEmpty(filter.getCharacter_addinform())){
                      flfields.add(new FilterField(getCond(flfields)+ "character_addinform=?",filter.getCharacter_addinform()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
              }
              if(!CheckNull.isEmpty(filter.getIns_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
              }
              if(!CheckNull.isEmpty(filter.getEstimation())){
                      flfields.add(new FilterField(getCond(flfields)+ "estimation=?",filter.getEstimation()));
              }
              if(!CheckNull.isEmpty(filter.getAddinform_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "addinform_code=?",filter.getAddinform_code()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(ok_addinformFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM ok_addinform ");
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



        public static List<ok_addinform> getok_addinformsFl(int pageIndex, int pageSize, ok_addinformFilter filter)  {

                List<ok_addinform> list = new ArrayList<ok_addinform>();
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
                                list.add(new ok_addinform(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getDate("addinform_date"),
                                                rs.getString("character_addinform"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getString("estimation"),
                                                rs.getInt("addinform_code"),
                                                rs.getString("emp_code_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public ok_addinform getok_addinform(int ok_addinformId) {

                ok_addinform ok_addinform = new ok_addinform();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_addinform WHERE id=?");
                        ps.setInt(1, ok_addinformId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_addinform = new ok_addinform();
                                
                                ok_addinform.setId(rs.getInt("id"));
                                ok_addinform.setBranch(rs.getString("branch"));
                                ok_addinform.setPersonal_code(rs.getInt("personal_code"));
                                ok_addinform.setAddinform_date(rs.getDate("addinform_date"));
                                ok_addinform.setCharacter_addinform(rs.getString("character_addinform"));
                                ok_addinform.setEmp_code(rs.getInt("emp_code"));
                                ok_addinform.setIns_date(rs.getDate("ins_date"));
                                ok_addinform.setEstimation(rs.getString("estimation"));
                                ok_addinform.setAddinform_code(rs.getInt("addinform_code"));
                                ok_addinform.setEmp_code_name(rs.getString("emp_code_name"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return ok_addinform;
        }

        public static ok_addinform create(ok_addinform ok_addinform)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_ok_addinform.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_addinform.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO ok_addinform (id, branch, personal_code, addinform_date, character_addinform, emp_code, ins_date, estimation, addinform_code, emp_code_name ) VALUES (?,?,?,?,?,?,?,?,?,?)");
                        
                        ps.setInt(1,ok_addinform.getId());
                        ps.setString(2,ok_addinform.getBranch());
                        ps.setInt(3,ok_addinform.getPersonal_code());
                        ps.setDate(4,new java.sql.Date(ok_addinform.getAddinform_date().getTime()));
                        ps.setString(5,ok_addinform.getCharacter_addinform());
                        ps.setInt(6,ok_addinform.getEmp_code());
                        ps.setDate(7,new java.sql.Date(ok_addinform.getIns_date().getTime()));
                        ps.setString(8,ok_addinform.getEstimation());
                        ps.setInt(9,ok_addinform.getAddinform_code());
                        ps.setString(10,ok_addinform.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return ok_addinform;
        }

        public static void update(ok_addinform ok_addinform)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE ok_addinform SET id=?, branch=?, personal_code=?, addinform_date=?, character_addinform=?, emp_code=?, ins_date=?, estimation=?, addinform_code=?, emp_code_name=?  WHERE id=?");
                        
                        ps.setInt(1,ok_addinform.getId());
                        ps.setString(2,ok_addinform.getBranch());
                        ps.setInt(3,ok_addinform.getPersonal_code());
                        ps.setDate(4,new java.sql.Date(ok_addinform.getAddinform_date().getTime()));
                        ps.setString(5,ok_addinform.getCharacter_addinform());
                        ps.setInt(6,ok_addinform.getEmp_code());
                        ps.setDate(7,new java.sql.Date(ok_addinform.getIns_date().getTime()));
                        ps.setString(8,ok_addinform.getEstimation());
                        ps.setInt(9,ok_addinform.getAddinform_code());
                        ps.setString(10,ok_addinform.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(ok_addinform ok_addinform)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM ok_addinform WHERE id=?");
                        ps.setLong(1, ok_addinform.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }


}