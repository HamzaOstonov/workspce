package com.is.hr;

import java.util.ArrayList;
import java.util.List;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class ok_educationService {

	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM ok_education ";


    public List<ok_education> getok_education()  {

            List<ok_education> list = new ArrayList<ok_education>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM ok_education");
                    while (rs.next()) {
                            list.add(new ok_education(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("personal_code"),
                                            rs.getInt("education_code"),
                                            rs.getInt("basis_code"),
                                            rs.getInt("institution_code"),
                                            rs.getInt("begin_date"),
                                            rs.getInt("end_date"),
                                            rs.getString("profession_personal"),
                                            rs.getInt("qualification_code"),
                                            rs.getString("diplom_num"),
                                            rs.getInt("emp_code"),
                                            rs.getDate("ins_date"),
                                            rs.getString("cod_vuz_prim"),
                                            rs.getString("curs"),
                                            rs.getString("fakultet"),
                                            rs.getString("nostra"),
                                            rs.getString("nostra_series"),
                                            rs.getString("nostra_number"),
                                            rs.getDate("nostra_date"),
                                            rs.getDate("diplom_date"),
                                            rs.getInt("begin_date_mm"),
                                            rs.getInt("begin_date_dd"),
                                            rs.getInt("end_date_mm"),
                                            rs.getInt("end_date_dd"),
                                            rs.getInt("education_end"),
                                            rs.getString("education_city"),
                                            rs.getInt("education_count_code"),
                                            rs.getInt("vid_education_code"),
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

    private static List<FilterField> getFilterFields(ok_educationFilter filter){
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
          if(!CheckNull.isEmpty(filter.getEducation_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "education_code=?",filter.getEducation_code()));
          }
          if(!CheckNull.isEmpty(filter.getBasis_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "basis_code=?",filter.getBasis_code()));
          }
          if(!CheckNull.isEmpty(filter.getInstitution_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "institution_code=?",filter.getInstitution_code()));
          }
          if(!CheckNull.isEmpty(filter.getBegin_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "begin_date=?",filter.getBegin_date()));
          }
          if(!CheckNull.isEmpty(filter.getEnd_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "end_date=?",filter.getEnd_date()));
          }
          if(!CheckNull.isEmpty(filter.getProfession_personal())){
                  flfields.add(new FilterField(getCond(flfields)+ "profession_personal=?",filter.getProfession_personal()));
          }
          if(!CheckNull.isEmpty(filter.getQualification_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "qualification_code=?",filter.getQualification_code()));
          }
          if(!CheckNull.isEmpty(filter.getDiplom_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "diplom_num=?",filter.getDiplom_num()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
          }
          if(!CheckNull.isEmpty(filter.getIns_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
          }
          if(!CheckNull.isEmpty(filter.getCod_vuz_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_vuz_prim=?",filter.getCod_vuz_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCurs())){
                  flfields.add(new FilterField(getCond(flfields)+ "curs=?",filter.getCurs()));
          }
          if(!CheckNull.isEmpty(filter.getFakultet())){
                  flfields.add(new FilterField(getCond(flfields)+ "fakultet=?",filter.getFakultet()));
          }
          if(!CheckNull.isEmpty(filter.getNostra())){
                  flfields.add(new FilterField(getCond(flfields)+ "nostra=?",filter.getNostra()));
          }
          if(!CheckNull.isEmpty(filter.getNostra_series())){
                  flfields.add(new FilterField(getCond(flfields)+ "nostra_series=?",filter.getNostra_series()));
          }
          if(!CheckNull.isEmpty(filter.getNostra_number())){
                  flfields.add(new FilterField(getCond(flfields)+ "nostra_number=?",filter.getNostra_number()));
          }
          if(!CheckNull.isEmpty(filter.getNostra_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "nostra_date=?",filter.getNostra_date()));
          }
          if(!CheckNull.isEmpty(filter.getDiplom_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "diplom_date=?",filter.getDiplom_date()));
          }
          if(!CheckNull.isEmpty(filter.getBegin_date_mm())){
                  flfields.add(new FilterField(getCond(flfields)+ "begin_date_mm=?",filter.getBegin_date_mm()));
          }
          if(!CheckNull.isEmpty(filter.getBegin_date_dd())){
                  flfields.add(new FilterField(getCond(flfields)+ "begin_date_dd=?",filter.getBegin_date_dd()));
          }
          if(!CheckNull.isEmpty(filter.getEnd_date_mm())){
                  flfields.add(new FilterField(getCond(flfields)+ "end_date_mm=?",filter.getEnd_date_mm()));
          }
          if(!CheckNull.isEmpty(filter.getEnd_date_dd())){
                  flfields.add(new FilterField(getCond(flfields)+ "end_date_dd=?",filter.getEnd_date_dd()));
          }
          if(!CheckNull.isEmpty(filter.getEducation_end())){
                  flfields.add(new FilterField(getCond(flfields)+ "education_end=?",filter.getEducation_end()));
          }
          if(!CheckNull.isEmpty(filter.getEducation_city())){
                  flfields.add(new FilterField(getCond(flfields)+ "education_city=?",filter.getEducation_city()));
          }
          if(!CheckNull.isEmpty(filter.getEducation_count_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "education_count_code=?",filter.getEducation_count_code()));
          }
          if(!CheckNull.isEmpty(filter.getVid_education_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "vid_education_code=?",filter.getVid_education_code()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ok_educationFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM ok_education ");
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



    public static List<ok_education> getok_educationsFl(int pageIndex, int pageSize, ok_educationFilter filter)  {

            List<ok_education> list = new ArrayList<ok_education>();
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
                            list.add(new ok_education(
                            		rs.getInt("id"),
                                    rs.getString("branch"),
                                    rs.getInt("personal_code"),
                                    rs.getInt("education_code"),
                                    rs.getInt("basis_code"),
                                    rs.getInt("institution_code"),
                                    rs.getInt("begin_date"),
                                    rs.getInt("end_date"),
                                    rs.getString("profession_personal"),
                                    rs.getInt("qualification_code"),
                                    rs.getString("diplom_num"),
                                    rs.getInt("emp_code"),
                                    rs.getDate("ins_date"),
                                    rs.getString("cod_vuz_prim"),
                                    rs.getString("curs"),
                                    rs.getString("fakultet"),
                                    rs.getString("nostra"),
                                    rs.getString("nostra_series"),
                                    rs.getString("nostra_number"),
                                    rs.getDate("nostra_date"),
                                    rs.getDate("diplom_date"),
                                    rs.getInt("begin_date_mm"),
                                    rs.getInt("begin_date_dd"),
                                    rs.getInt("end_date_mm"),
                                    rs.getInt("end_date_dd"),
                                    rs.getInt("education_end"),
                                    rs.getString("education_city"),
                                    rs.getInt("education_count_code"),
                                    rs.getInt("vid_education_code"),
                                    rs.getString("emp_code_name")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
                    

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public ok_education getok_education(int ok_educationId) {

            ok_education ok_education = new ok_education();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_education WHERE id=?");
                    ps.setInt(1, ok_educationId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_education = new ok_education();
                            
                            ok_education.setId(rs.getInt("id"));
                            ok_education.setBranch(rs.getString("branch"));
                            ok_education.setPersonal_code(rs.getInt("personal_code"));
                            ok_education.setEducation_code(rs.getInt("education_code"));
                            ok_education.setBasis_code(rs.getInt("basis_code"));
                            ok_education.setInstitution_code(rs.getInt("institution_code"));
                            ok_education.setBegin_date(rs.getInt("begin_date"));
                            ok_education.setEnd_date(rs.getInt("end_date"));
                            ok_education.setProfession_personal(rs.getString("profession_personal"));
                            ok_education.setQualification_code(rs.getInt("qualification_code"));
                            ok_education.setDiplom_num(rs.getString("diplom_num"));
                            ok_education.setEmp_code(rs.getInt("emp_code"));
                            ok_education.setIns_date(rs.getDate("ins_date"));
                            ok_education.setCod_vuz_prim(rs.getString("cod_vuz_prim"));
                            ok_education.setCurs(rs.getString("curs"));
                            ok_education.setFakultet(rs.getString("fakultet"));
                            ok_education.setNostra(rs.getString("nostra"));
                            ok_education.setNostra_series(rs.getString("nostra_series"));
                            ok_education.setNostra_number(rs.getString("nostra_number"));
                            ok_education.setNostra_date(rs.getDate("nostra_date"));
                            ok_education.setDiplom_date(rs.getDate("diplom_date"));
                            ok_education.setBegin_date_mm(rs.getInt("begin_date_mm"));
                            ok_education.setBegin_date_dd(rs.getInt("begin_date_dd"));
                            ok_education.setEnd_date_mm(rs.getInt("end_date_mm"));
                            ok_education.setEnd_date_dd(rs.getInt("end_date_dd"));
                            ok_education.setEducation_end(rs.getInt("education_end"));
                            ok_education.setEducation_city(rs.getString("education_city"));
                            ok_education.setEducation_count_code(rs.getInt("education_count_code"));
                            ok_education.setVid_education_code(rs.getInt("vid_education_code"));
                            ok_education.setEmp_code_name(rs.getString("emp_code_name"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
                   
            } finally {
                    ConnectionPool.close(c);
            }
            return ok_education;
    }

    public static ok_education create(ok_education ok_education)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_ok_education.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_education.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO TF_ok_education (id, branch, personal_code, education_code, basis_code, institution_code, begin_date, end_date, profession_personal, qualification_code, diplom_num, emp_code, ins_date, cod_vuz_prim, curs, fakultet, nostra, nostra_series, nostra_number, nostra_date, diplom_date, begin_date_mm, begin_date_dd, end_date_mm, end_date_dd, education_end, education_city, education_count_code, vid_education_code, emp_code_name, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setDouble(1,ok_education.getId());
                    ps.setString(2,ok_education.getBranch());
                    ps.setDouble(3,ok_education.getPersonal_code());
                    ps.setDouble(4,ok_education.getEducation_code());
                    ps.setDouble(5,ok_education.getBasis_code());
                    ps.setDouble(6,ok_education.getInstitution_code());
                    ps.setDouble(7,ok_education.getBegin_date());
                    ps.setDouble(8,ok_education.getEnd_date());
                    ps.setString(9,ok_education.getProfession_personal());
                    ps.setDouble(10,ok_education.getQualification_code());
                    ps.setString(11,ok_education.getDiplom_num());
                    ps.setDouble(12,ok_education.getEmp_code());
                    ps.setTimestamp(13, new java.sql.Timestamp(ok_education.getIns_date().getTime()));
                    ps.setString(14,ok_education.getCod_vuz_prim());
                    ps.setString(15,ok_education.getCurs());
                    ps.setString(16,ok_education.getFakultet());
                    ps.setString(17,ok_education.getNostra());
                    ps.setString(18,ok_education.getNostra_series());
                    ps.setString(19,ok_education.getNostra_number());
                    ps.setTimestamp(20, new java.sql.Timestamp(ok_education.getNostra_date().getTime()));
                    ps.setTimestamp(21, new java.sql.Timestamp(ok_education.getDiplom_date().getTime()));
                    ps.setDouble(22,ok_education.getBegin_date_mm());
                    ps.setDouble(23,ok_education.getBegin_date_dd());
                    ps.setDouble(24,ok_education.getEnd_date_mm());
                    ps.setDouble(25,ok_education.getEnd_date_dd());
                    ps.setDouble(26,ok_education.getEducation_end());
                    ps.setString(27,ok_education.getEducation_city());
                    ps.setDouble(28,ok_education.getEducation_count_code());
                    ps.setDouble(29,ok_education.getVid_education_code());
                    ps.setString(30,ok_education.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
                    

            } finally {
                    ConnectionPool.close(c);
            }
            return ok_education;
    }

    public static void update(ok_education ok_education)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE ok_education SET id=?, branch=?, personal_code=?, education_code=?, basis_code=?, institution_code=?, begin_date=?, end_date=?, profession_personal=?, qualification_code=?, diplom_num=?, emp_code=?, ins_date=?, cod_vuz_prim=?, curs=?, fakultet=?, nostra=?, nostra_series=?, nostra_number=?, nostra_date=?, diplom_date=?, begin_date_mm=?, begin_date_dd=?, end_date_mm=?, end_date_dd=?, education_end=?, education_city=?, education_count_code=?, vid_education_code=?, emp_code_name=?,  WHERE id=?");
                    
                    ps.setDouble(1,ok_education.getId());
                    ps.setString(2,ok_education.getBranch());
                    ps.setDouble(3,ok_education.getPersonal_code());
                    ps.setDouble(4,ok_education.getEducation_code());
                    ps.setDouble(5,ok_education.getBasis_code());
                    ps.setDouble(6,ok_education.getInstitution_code());
                    ps.setDouble(7,ok_education.getBegin_date());
                    ps.setDouble(8,ok_education.getEnd_date());
                    ps.setString(9,ok_education.getProfession_personal());
                    ps.setDouble(10,ok_education.getQualification_code());
                    ps.setString(11,ok_education.getDiplom_num());
                    ps.setDouble(12,ok_education.getEmp_code());
                    ps.setTimestamp(13, new java.sql.Timestamp(ok_education.getIns_date().getTime()));
                    ps.setString(14,ok_education.getCod_vuz_prim());
                    ps.setString(15,ok_education.getCurs());
                    ps.setString(16,ok_education.getFakultet());
                    ps.setString(17,ok_education.getNostra());
                    ps.setString(18,ok_education.getNostra_series());
                    ps.setString(19,ok_education.getNostra_number());
                    ps.setTimestamp(20, new java.sql.Timestamp(ok_education.getNostra_date().getTime()));
                    ps.setTimestamp(21, new java.sql.Timestamp(ok_education.getDiplom_date().getTime()));
                    ps.setDouble(22,ok_education.getBegin_date_mm());
                    ps.setDouble(23,ok_education.getBegin_date_dd());
                    ps.setDouble(24,ok_education.getEnd_date_mm());
                    ps.setDouble(25,ok_education.getEnd_date_dd());
                    ps.setDouble(26,ok_education.getEducation_end());
                    ps.setString(27,ok_education.getEducation_city());
                    ps.setDouble(28,ok_education.getEducation_count_code());
                    ps.setDouble(29,ok_education.getVid_education_code());
                    ps.setString(30,ok_education.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ok_education ok_education)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM ok_education WHERE id=?");
                    ps.setLong(1, ok_education.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }


}
