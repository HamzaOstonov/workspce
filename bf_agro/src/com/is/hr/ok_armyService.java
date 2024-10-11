package com.is.hr;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;



public class ok_armyService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM ok_army ";


        public List<ok_army> getok_army()  {

                List<ok_army> list = new ArrayList<ok_army>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM ok_army");
                        while (rs.next()) {
                                list.add(new ok_army(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getInt("army_code"),
                                                rs.getInt("fitness_army_code"),
                                                rs.getString("staff"),
                                                rs.getString("type_force"),
                                                rs.getString("army_group"),
                                                rs.getString("category_army"),
                                                rs.getString("special_army"),
                                                rs.getString("military_rank"),
                                                rs.getString("number_vus"),
                                                rs.getString("code_army_post"),
                                                rs.getString("name_army_office"),
                                                rs.getString("specreg_number"),
                                                rs.getDouble("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getInt("army_group_code"),
                                                rs.getInt("category_army_code"),
                                                rs.getInt("military_rank_code"),
                                                rs.getInt("staff_code"),
                                                rs.getInt("type_force_code"),
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

        private static List<FilterField> getFilterFields(ok_armyFilter filter){
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
              if(!CheckNull.isEmpty(filter.getArmy_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "army_code=?",filter.getArmy_code()));
              }
              if(!CheckNull.isEmpty(filter.getFitness_army_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "fitness_army_code=?",filter.getFitness_army_code()));
              }
              if(!CheckNull.isEmpty(filter.getStaff())){
                      flfields.add(new FilterField(getCond(flfields)+ "staff=?",filter.getStaff()));
              }
              if(!CheckNull.isEmpty(filter.getType_force())){
                      flfields.add(new FilterField(getCond(flfields)+ "type_force=?",filter.getType_force()));
              }
              if(!CheckNull.isEmpty(filter.getArmy_group())){
                      flfields.add(new FilterField(getCond(flfields)+ "army_group=?",filter.getArmy_group()));
              }
              if(!CheckNull.isEmpty(filter.getCategory_army())){
                      flfields.add(new FilterField(getCond(flfields)+ "category_army=?",filter.getCategory_army()));
              }
              if(!CheckNull.isEmpty(filter.getSpecial_army())){
                      flfields.add(new FilterField(getCond(flfields)+ "special_army=?",filter.getSpecial_army()));
              }
              if(!CheckNull.isEmpty(filter.getMilitary_rank())){
                      flfields.add(new FilterField(getCond(flfields)+ "military_rank=?",filter.getMilitary_rank()));
              }
              if(!CheckNull.isEmpty(filter.getNumber_vus())){
                      flfields.add(new FilterField(getCond(flfields)+ "number_vus=?",filter.getNumber_vus()));
              }
              if(!CheckNull.isEmpty(filter.getCode_army_post())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_army_post=?",filter.getCode_army_post()));
              }
              if(!CheckNull.isEmpty(filter.getName_army_office())){
                      flfields.add(new FilterField(getCond(flfields)+ "name_army_office=?",filter.getName_army_office()));
              }
              if(!CheckNull.isEmpty(filter.getSpecreg_number())){
                      flfields.add(new FilterField(getCond(flfields)+ "specreg_number=?",filter.getSpecreg_number()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
              }
              if(!CheckNull.isEmpty(filter.getIns_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
              }
              if(!CheckNull.isEmpty(filter.getArmy_group_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "army_group_code=?",filter.getArmy_group_code()));
              }
              if(!CheckNull.isEmpty(filter.getCategory_army_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "category_army_code=?",filter.getCategory_army_code()));
              }
              if(!CheckNull.isEmpty(filter.getMilitary_rank_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "military_rank_code=?",filter.getMilitary_rank_code()));
              }
              if(!CheckNull.isEmpty(filter.getStaff_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "staff_code=?",filter.getStaff_code()));
              }
              if(!CheckNull.isEmpty(filter.getType_force_code())){
                      flfields.add(new FilterField(getCond(flfields)+ "type_force_code=?",filter.getType_force_code()));
              }
              if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(ok_armyFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM ok_army ");
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



        public static List<ok_army> getok_armysFl(int pageIndex, int pageSize, ok_armyFilter filter)  {

                List<ok_army> list = new ArrayList<ok_army>();
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
                                list.add(new ok_army(
                                                rs.getInt("id"),
                                                rs.getString("branch"),
                                                rs.getInt("personal_code"),
                                                rs.getInt("army_code"),
                                                rs.getInt("fitness_army_code"),
                                                rs.getString("staff"),
                                                rs.getString("type_force"),
                                                rs.getString("army_group"),
                                                rs.getString("category_army"),
                                                rs.getString("special_army"),
                                                rs.getString("military_rank"),
                                                rs.getString("number_vus"),
                                                rs.getString("code_army_post"),
                                                rs.getString("name_army_office"),
                                                rs.getString("specreg_number"),
                                                rs.getDouble("emp_code"),
                                                rs.getDate("ins_date"),
                                                rs.getInt("army_group_code"),
                                                rs.getInt("category_army_code"),
                                                rs.getInt("military_rank_code"),
                                                rs.getInt("staff_code"),
                                                rs.getInt("type_force_code"),
                                                rs.getString("emp_code_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public ok_army getok_army(int ok_armyId) {

                ok_army ok_army = new ok_army();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_army WHERE id=?");
                        ps.setInt(1, ok_armyId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_army = new ok_army();
                                
                                ok_army.setId(rs.getInt("id"));
                                ok_army.setBranch(rs.getString("branch"));
                                ok_army.setPersonal_code(rs.getInt("personal_code"));
                                ok_army.setArmy_code(rs.getInt("army_code"));
                                ok_army.setFitness_army_code(rs.getInt("fitness_army_code"));
                                ok_army.setStaff(rs.getString("staff"));
                                ok_army.setType_force(rs.getString("type_force"));
                                ok_army.setArmy_group(rs.getString("army_group"));
                                ok_army.setCategory_army(rs.getString("category_army"));
                                ok_army.setSpecial_army(rs.getString("special_army"));
                                ok_army.setMilitary_rank(rs.getString("military_rank"));
                                ok_army.setNumber_vus(rs.getString("number_vus"));
                                ok_army.setCode_army_post(rs.getString("code_army_post"));
                                ok_army.setName_army_office(rs.getString("name_army_office"));
                                ok_army.setSpecreg_number(rs.getString("specreg_number"));
                                ok_army.setEmp_code(rs.getDouble("emp_code"));
                                ok_army.setIns_date(rs.getDate("ins_date"));
                                ok_army.setArmy_group_code(rs.getInt("army_group_code"));
                                ok_army.setCategory_army_code(rs.getInt("category_army_code"));
                                ok_army.setMilitary_rank_code(rs.getInt("military_rank_code"));
                                ok_army.setStaff_code(rs.getInt("staff_code"));
                                ok_army.setType_force_code(rs.getInt("type_force_code"));
                                ok_army.setEmp_code_name(rs.getString("emp_code_name"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return ok_army;
        }

        public static ok_army create(ok_army ok_army)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_ok_army.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                ok_army.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO ok_army (id, branch, personal_code, army_code, fitness_army_code, staff, type_force, army_group, category_army, special_army, military_rank, number_vus, code_army_post, name_army_office, specreg_number, emp_code, ins_date, army_group_code, category_army_code, military_rank_code, staff_code, type_force_code, emp_code_name ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        
                        ps.setInt(1,ok_army.getId());
                        ps.setString(2,ok_army.getBranch());
                        ps.setInt(3,ok_army.getPersonal_code());
                        ps.setInt(4,ok_army.getArmy_code());
                        ps.setInt(5,ok_army.getFitness_army_code());
                        ps.setString(6,ok_army.getStaff());
                        ps.setString(7,ok_army.getType_force());
                        ps.setString(8,ok_army.getArmy_group());
                        ps.setString(9,ok_army.getCategory_army());
                        ps.setString(10,ok_army.getSpecial_army());
                        ps.setString(11,ok_army.getMilitary_rank());
                        ps.setString(12,ok_army.getNumber_vus());
                        ps.setString(13,ok_army.getCode_army_post());
                        ps.setString(14,ok_army.getName_army_office());
                        ps.setString(15,ok_army.getSpecreg_number());
                        ps.setDouble(16,ok_army.getEmp_code());
                        ps.setDate(17,new java.sql.Date(ok_army.getIns_date().getTime()));
                        ps.setInt(18,ok_army.getArmy_group_code());
                        ps.setInt(19,ok_army.getCategory_army_code());
                        ps.setInt(20,ok_army.getMilitary_rank_code());
                        ps.setInt(21,ok_army.getStaff_code());
                        ps.setInt(22,ok_army.getType_force_code());
                        ps.setString(23,ok_army.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return ok_army;
        }

        public static void update(ok_army ok_army)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE ok_army SET id=?, branch=?, personal_code=?, army_code=?, fitness_army_code=?, staff=?, type_force=?, army_group=?, category_army=?, special_army=?, military_rank=?, number_vus=?, code_army_post=?, name_army_office=?, specreg_number=?, emp_code=?, ins_date=?, army_group_code=?, category_army_code=?, military_rank_code=?, staff_code=?, type_force_code=?, emp_code_name=?  WHERE id=?");
                        
                        ps.setInt(1,ok_army.getId());
                        ps.setString(2,ok_army.getBranch());
                        ps.setInt(3,ok_army.getPersonal_code());
                        ps.setInt(4,ok_army.getArmy_code());
                        ps.setInt(5,ok_army.getFitness_army_code());
                        ps.setString(6,ok_army.getStaff());
                        ps.setString(7,ok_army.getType_force());
                        ps.setString(8,ok_army.getArmy_group());
                        ps.setString(9,ok_army.getCategory_army());
                        ps.setString(10,ok_army.getSpecial_army());
                        ps.setString(11,ok_army.getMilitary_rank());
                        ps.setString(12,ok_army.getNumber_vus());
                        ps.setString(13,ok_army.getCode_army_post());
                        ps.setString(14,ok_army.getName_army_office());
                        ps.setString(15,ok_army.getSpecreg_number());
                        ps.setDouble(16,ok_army.getEmp_code());
                        ps.setDate(17,new java.sql.Date(ok_army.getIns_date().getTime()));
                        ps.setInt(18,ok_army.getArmy_group_code());
                        ps.setInt(19,ok_army.getCategory_army_code());
                        ps.setInt(20,ok_army.getMilitary_rank_code());
                        ps.setInt(21,ok_army.getStaff_code());
                        ps.setInt(22,ok_army.getType_force_code());
                        ps.setString(23,ok_army.getEmp_code_name());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(ok_army ok_army)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM ok_army WHERE id=?");
                        ps.setLong(1, ok_army.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }


}