package com.is.hr;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;



public class ok_change_fioService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM ok_change_fio ";


        public List<ok_change_fio> getok_change_fio()  {

                List<ok_change_fio> list = new ArrayList<ok_change_fio>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM ok_change_fio");
                        while (rs.next()) {
                                list.add(new ok_change_fio(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getString("family"),
                                                rs.getString("first_name"),
                                                rs.getString("patronymic"),
                                                rs.getDate("change_date"),
                                                rs.getString("change_motive"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
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

        private static List<FilterField> getFilterFields(ok_change_fioFilter filter){
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
              if(!CheckNull.isEmpty(filter.getFamily())){
                      flfields.add(new FilterField(getCond(flfields)+ "family=?",filter.getFamily()));
              }
              if(!CheckNull.isEmpty(filter.getFirst_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "first_name=?",filter.getFirst_name()));
              }
              if(!CheckNull.isEmpty(filter.getPatronymic())){
                      flfields.add(new FilterField(getCond(flfields)+ "patronymic=?",filter.getPatronymic()));
              }
              if(!CheckNull.isEmpty(filter.getChange_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "change_date=?",filter.getChange_date()));
              }
              if(!CheckNull.isEmpty(filter.getChange_motive())){
                      flfields.add(new FilterField(getCond(flfields)+ "change_motive=?",filter.getChange_motive()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
              }
              if(!CheckNull.isEmpty(filter.getIns_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(ok_change_fioFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM ok_change_fio ");
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



        public static List<ok_change_fio> getok_change_fiosFl(int pageIndex, int pageSize, ok_change_fioFilter filter)  {

                List<ok_change_fio> list = new ArrayList<ok_change_fio>();
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
                                list.add(new ok_change_fio(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getString("family"),
                                                rs.getString("first_name"),
                                                rs.getString("patronymic"),
                                                rs.getDate("change_date"),
                                                rs.getString("change_motive"),
                                                rs.getInt("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getString("emp_code_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public ok_change_fio getok_change_fio(int ok_change_fioId) {

                ok_change_fio ok_change_fio = new ok_change_fio();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_change_fio WHERE id=?");
                        ps.setInt(1, ok_change_fioId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_change_fio = new ok_change_fio();
                                
                                ok_change_fio.setId(rs.getInt("id"));
                                ok_change_fio.setBranch(rs.getString("branch"));
                                ok_change_fio.setPersonal_code(rs.getInt("personal_code"));
                                ok_change_fio.setFamily(rs.getString("family"));
                                ok_change_fio.setFirst_name(rs.getString("first_name"));
                                ok_change_fio.setPatronymic(rs.getString("patronymic"));
                                ok_change_fio.setChange_date(rs.getDate("change_date"));
                                ok_change_fio.setChange_motive(rs.getString("change_motive"));
                                ok_change_fio.setEmp_code(rs.getInt("emp_code"));
                                ok_change_fio.setIns_date(rs.getDate("ins_date"));
                                ok_change_fio.setEmp_code_name(rs.getString("emp_code_name"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return ok_change_fio;
        }

        public static ok_change_fio create(ok_change_fio ok_change_fio)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_ok_change_fio.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_change_fio.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO ok_change_fio (id, branch, personal_code, family, first_name, patronymic, change_date, change_motive, emp_code, ins_date, emp_code_name ) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                        
                        ps.setInt(1,ok_change_fio.getId());
                        ps.setString(2,ok_change_fio.getBranch());
                        ps.setInt(3,ok_change_fio.getPersonal_code());
                        ps.setString(4,ok_change_fio.getFamily());
                        ps.setString(5,ok_change_fio.getFirst_name());
                        ps.setString(6,ok_change_fio.getPatronymic());
                        ps.setDate(7,new java.sql.Date(ok_change_fio.getChange_date().getTime()));
                        ps.setString(8,ok_change_fio.getChange_motive());
                        ps.setInt(9,ok_change_fio.getEmp_code());
                        ps.setDate(10,new java.sql.Date(ok_change_fio.getIns_date().getTime()));
                        ps.setString(11,ok_change_fio.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return ok_change_fio;
        }

        public static void update(ok_change_fio ok_change_fio)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE ok_change_fio SET id=?, branch=?, personal_code=?, family=?, first_name=?, patronymic=?, change_date=?, change_motive=?, emp_code=?, ins_date=?, emp_code_name=?  WHERE id=?");
                        
                        ps.setInt(1,ok_change_fio.getId());
                        ps.setString(2,ok_change_fio.getBranch());
                        ps.setInt(3,ok_change_fio.getPersonal_code());
                        ps.setString(4,ok_change_fio.getFamily());
                        ps.setString(5,ok_change_fio.getFirst_name());
                        ps.setString(6,ok_change_fio.getPatronymic());
                        ps.setDate(7,new java.sql.Date(ok_change_fio.getChange_date().getTime()));
                        ps.setString(8,ok_change_fio.getChange_motive());
                        ps.setInt(9,ok_change_fio.getEmp_code());
                        ps.setDate(10,new java.sql.Date(ok_change_fio.getIns_date().getTime()));
                        ps.setString(11,ok_change_fio.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(ok_change_fio ok_change_fio)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM ok_change_fio WHERE id=?");
                        ps.setLong(1, ok_change_fio.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }


}
