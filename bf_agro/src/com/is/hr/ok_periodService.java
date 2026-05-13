package com.is.hr;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
public class ok_periodService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM ok_period ";


    public List<ok_period> getok_period()  {

            List<ok_period> list = new ArrayList<ok_period>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM ok_period");
                    while (rs.next()) {
                            list.add(new ok_period(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("personal_code"),
                                            rs.getDate("in_office_date"),
                                            rs.getDate("out_office_date"),
                                            rs.getString("office_name"),
                                            rs.getString("office_address"),
                                            rs.getString("established_post"),
                                            rs.getString("motive_out"),
                                            rs.getInt("type_period_code"),
                                            rs.getInt("article_code"),
                                            rs.getString("basis_num"),
                                            rs.getDate("basis_date"),
                                            rs.getInt("emp_code"),
                                            rs.getDate("ins_date"),
                                            rs.getInt("base_move_code"),
                                            rs.getString("established_department"),
                                            rs.getString("priz_system"),
                                            rs.getString("cod_bank"),
                                            rs.getString("cod_type_prn"),
                                            rs.getString("cod_pr_off"),
                                            rs.getString("pr_off"),
                                            rs.getDate("date_utv_km"),
                                            rs.getString("numb_utv_km"),
                                            rs.getDate("date_pr_kvl"),
                                            rs.getString("numb_pr_kvl"),
                                            rs.getDate("date_attest"),
                                            rs.getString("resh_attest"),
                                            rs.getString("doljn_id"),
                                            rs.getString("num_pr_off"),
                                            rs.getDate("date_pr_off"),
                                            rs.getInt("post_code"),
                                            rs.getInt("department_code"),
                                            rs.getString("department"),
                                            rs.getInt("stag_code"),
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

    private static List<FilterField> getFilterFields(ok_periodFilter filter){
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
          if(!CheckNull.isEmpty(filter.getIn_office_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "in_office_date=?",filter.getIn_office_date()));
          }
          if(!CheckNull.isEmpty(filter.getOut_office_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "out_office_date=?",filter.getOut_office_date()));
          }
          if(!CheckNull.isEmpty(filter.getOffice_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "office_name=?",filter.getOffice_name()));
          }
          if(!CheckNull.isEmpty(filter.getOffice_address())){
                  flfields.add(new FilterField(getCond(flfields)+ "office_address=?",filter.getOffice_address()));
          }
          if(!CheckNull.isEmpty(filter.getEstablished_post())){
                  flfields.add(new FilterField(getCond(flfields)+ "established_post=?",filter.getEstablished_post()));
          }
          if(!CheckNull.isEmpty(filter.getMotive_out())){
                  flfields.add(new FilterField(getCond(flfields)+ "motive_out=?",filter.getMotive_out()));
          }
          if(!CheckNull.isEmpty(filter.getType_period_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "type_period_code=?",filter.getType_period_code()));
          }
          if(!CheckNull.isEmpty(filter.getArticle_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "article_code=?",filter.getArticle_code()));
          }
          if(!CheckNull.isEmpty(filter.getBasis_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "basis_num=?",filter.getBasis_num()));
          }
          if(!CheckNull.isEmpty(filter.getBasis_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "basis_date=?",filter.getBasis_date()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
          }
          if(!CheckNull.isEmpty(filter.getIns_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
          }
          if(!CheckNull.isEmpty(filter.getBase_move_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "base_move_code=?",filter.getBase_move_code()));
          }
          if(!CheckNull.isEmpty(filter.getEstablished_department())){
                  flfields.add(new FilterField(getCond(flfields)+ "established_department=?",filter.getEstablished_department()));
          }
          if(!CheckNull.isEmpty(filter.getPriz_system())){
                  flfields.add(new FilterField(getCond(flfields)+ "priz_system=?",filter.getPriz_system()));
          }
          if(!CheckNull.isEmpty(filter.getCod_bank())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_bank=?",filter.getCod_bank()));
          }
          if(!CheckNull.isEmpty(filter.getCod_type_prn())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_type_prn=?",filter.getCod_type_prn()));
          }
          if(!CheckNull.isEmpty(filter.getCod_pr_off())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_pr_off=?",filter.getCod_pr_off()));
          }
          if(!CheckNull.isEmpty(filter.getPr_off())){
                  flfields.add(new FilterField(getCond(flfields)+ "pr_off=?",filter.getPr_off()));
          }
          if(!CheckNull.isEmpty(filter.getDate_utv_km())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_utv_km=?",filter.getDate_utv_km()));
          }
          if(!CheckNull.isEmpty(filter.getNumb_utv_km())){
                  flfields.add(new FilterField(getCond(flfields)+ "numb_utv_km=?",filter.getNumb_utv_km()));
          }
          if(!CheckNull.isEmpty(filter.getDate_pr_kvl())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_pr_kvl=?",filter.getDate_pr_kvl()));
          }
          if(!CheckNull.isEmpty(filter.getNumb_pr_kvl())){
                  flfields.add(new FilterField(getCond(flfields)+ "numb_pr_kvl=?",filter.getNumb_pr_kvl()));
          }
          if(!CheckNull.isEmpty(filter.getDate_attest())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_attest=?",filter.getDate_attest()));
          }
          if(!CheckNull.isEmpty(filter.getResh_attest())){
                  flfields.add(new FilterField(getCond(flfields)+ "resh_attest=?",filter.getResh_attest()));
          }
          if(!CheckNull.isEmpty(filter.getDoljn_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "doljn_id=?",filter.getDoljn_id()));
          }
          if(!CheckNull.isEmpty(filter.getNum_pr_off())){
                  flfields.add(new FilterField(getCond(flfields)+ "num_pr_off=?",filter.getNum_pr_off()));
          }
          if(!CheckNull.isEmpty(filter.getDate_pr_off())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_pr_off=?",filter.getDate_pr_off()));
          }
          if(!CheckNull.isEmpty(filter.getPost_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "post_code=?",filter.getPost_code()));
          }
          if(!CheckNull.isEmpty(filter.getDepartment_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "department_code=?",filter.getDepartment_code()));
          }
          if(!CheckNull.isEmpty(filter.getDepartment())){
                  flfields.add(new FilterField(getCond(flfields)+ "department=?",filter.getDepartment()));
          }
          if(!CheckNull.isEmpty(filter.getStag_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "stag_code=?",filter.getStag_code()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ok_periodFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM ok_period ");
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



    public static List<ok_period> getok_periodsFl(int pageIndex, int pageSize, ok_periodFilter filter)  {

            List<ok_period> list = new ArrayList<ok_period>();
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
                            list.add(new ok_period(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("personal_code"),
                                            rs.getDate("in_office_date"),
                                            rs.getDate("out_office_date"),
                                            rs.getString("office_name"),
                                            rs.getString("office_address"),
                                            rs.getString("established_post"),
                                            rs.getString("motive_out"),
                                            rs.getInt("type_period_code"),
                                            rs.getInt("article_code"),
                                            rs.getString("basis_num"),
                                            rs.getDate("basis_date"),
                                            rs.getInt("emp_code"),
                                            rs.getDate("ins_date"),
                                            rs.getInt("base_move_code"),
                                            rs.getString("established_department"),
                                            rs.getString("priz_system"),
                                            rs.getString("cod_bank"),
                                            rs.getString("cod_type_prn"),
                                            rs.getString("cod_pr_off"),
                                            rs.getString("pr_off"),
                                            rs.getDate("date_utv_km"),
                                            rs.getString("numb_utv_km"),
                                            rs.getDate("date_pr_kvl"),
                                            rs.getString("numb_pr_kvl"),
                                            rs.getDate("date_attest"),
                                            rs.getString("resh_attest"),
                                            rs.getString("doljn_id"),
                                            rs.getString("num_pr_off"),
                                            rs.getDate("date_pr_off"),
                                            rs.getInt("post_code"),
                                            rs.getInt("department_code"),
                                            rs.getString("department"),
                                            rs.getInt("stag_code"),
                                            rs.getString("emp_code_name")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public ok_period getok_period(int ok_periodId) {

            ok_period ok_period = new ok_period();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_period WHERE id=?");
                    ps.setDouble(1, ok_periodId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_period = new ok_period();
                            
                            ok_period.setId(rs.getInt("id"));
                            ok_period.setBranch(rs.getString("branch"));
                            ok_period.setPersonal_code(rs.getInt("personal_code"));
                            ok_period.setIn_office_date(rs.getDate("in_office_date"));
                            ok_period.setOut_office_date(rs.getDate("out_office_date"));
                            ok_period.setOffice_name(rs.getString("office_name"));
                            ok_period.setOffice_address(rs.getString("office_address"));
                            ok_period.setEstablished_post(rs.getString("established_post"));
                            ok_period.setMotive_out(rs.getString("motive_out"));
                            ok_period.setType_period_code(rs.getInt("type_period_code"));
                            ok_period.setArticle_code(rs.getInt("article_code"));
                            ok_period.setBasis_num(rs.getString("basis_num"));
                            ok_period.setBasis_date(rs.getDate("basis_date"));
                            ok_period.setEmp_code(rs.getInt("emp_code"));
                            ok_period.setIns_date(rs.getDate("ins_date"));
                            ok_period.setBase_move_code(rs.getInt("base_move_code"));
                            ok_period.setEstablished_department(rs.getString("established_department"));
                            ok_period.setPriz_system(rs.getString("priz_system"));
                            ok_period.setCod_bank(rs.getString("cod_bank"));
                            ok_period.setCod_type_prn(rs.getString("cod_type_prn"));
                            ok_period.setCod_pr_off(rs.getString("cod_pr_off"));
                            ok_period.setPr_off(rs.getString("pr_off"));
                            ok_period.setDate_utv_km(rs.getDate("date_utv_km"));
                            ok_period.setNumb_utv_km(rs.getString("numb_utv_km"));
                            ok_period.setDate_pr_kvl(rs.getDate("date_pr_kvl"));
                            ok_period.setNumb_pr_kvl(rs.getString("numb_pr_kvl"));
                            ok_period.setDate_attest(rs.getDate("date_attest"));
                            ok_period.setResh_attest(rs.getString("resh_attest"));
                            ok_period.setDoljn_id(rs.getString("doljn_id"));
                            ok_period.setNum_pr_off(rs.getString("num_pr_off"));
                            ok_period.setDate_pr_off(rs.getDate("date_pr_off"));
                            ok_period.setPost_code(rs.getInt("post_code"));
                            ok_period.setDepartment_code(rs.getInt("department_code"));
                            ok_period.setDepartment(rs.getString("department"));
                            ok_period.setStag_code(rs.getInt("stag_code"));
                            ok_period.setEmp_code_name(rs.getString("emp_code_name"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return ok_period;
    }

    public static ok_period create(ok_period ok_period)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_ok_period.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_period.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO ok_period (id, branch, personal_code, in_office_date, out_office_date, office_name, office_address, established_post, motive_out, type_period_code, article_code, basis_num, basis_date, emp_code, ins_date, base_move_code, established_department, priz_system, cod_bank, cod_type_prn, cod_pr_off, pr_off, date_utv_km, numb_utv_km, date_pr_kvl, numb_pr_kvl, date_attest, resh_attest, doljn_id, num_pr_off, date_pr_off, post_code, department_code, department, stag_code, emp_code_name ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    //ps.setDate(14,new java.sql.Date(ok_relation.getIns_date().getTime()));
                    System.out.println("дата ебаная "+ok_period.getIns_date().getTime());
                    ps.setDouble(1,ok_period.getId());
                    ps.setString(2,ok_period.getBranch());
                    ps.setInt(3,ok_period.getPersonal_code());
                    ps.setDate(4,new java.sql.Date(ok_period.getIn_office_date().getTime()));
                    ps.setDate(5,new java.sql.Date(ok_period.getOut_office_date().getTime()));
                    ps.setString(6,ok_period.getOffice_name());
                    ps.setString(7,ok_period.getOffice_address());
                    ps.setString(8,ok_period.getEstablished_post());
                    ps.setString(9,ok_period.getMotive_out());
                    ps.setDouble(10,ok_period.getType_period_code());
                    ps.setDouble(11,ok_period.getArticle_code());
                    ps.setString(12,ok_period.getBasis_num());
                    ps.setDate(13,new java.sql.Date(ok_period.getBasis_date().getTime()));
                    ps.setDouble(14,ok_period.getEmp_code());
                    ps.setTimestamp(15, new java.sql.Timestamp(ok_period.getIns_date().getTime()));
                    ps.setDouble(16,ok_period.getBase_move_code());
                    ps.setString(17,ok_period.getEstablished_department());
                    ps.setString(18,ok_period.getPriz_system());
                    ps.setString(19,ok_period.getCod_bank());
                    ps.setString(20,ok_period.getCod_type_prn());
                    ps.setString(21,ok_period.getCod_pr_off());
                    ps.setString(22,ok_period.getPr_off());
                    ps.setDate(23,new java.sql.Date(ok_period.getDate_utv_km().getTime()));
                    ps.setString(24,ok_period.getNumb_utv_km());
                    ps.setDate(25,new java.sql.Date(ok_period.getDate_pr_kvl().getTime()));
                    ps.setString(26,ok_period.getNumb_pr_kvl());
                    ps.setDate(27,new java.sql.Date(ok_period.getDate_attest().getTime()));
                    ps.setString(28,ok_period.getResh_attest());
                    ps.setString(29,ok_period.getDoljn_id());
                    ps.setString(30,ok_period.getNum_pr_off());
                    ps.setDate(31,new java.sql.Date(ok_period.getDate_pr_off().getTime()));
                    ps.setDouble(32,ok_period.getPost_code());
                    ps.setDouble(33,ok_period.getDepartment_code());
                    ps.setString(34,ok_period.getDepartment());
                    ps.setDouble(35,ok_period.getStag_code());
                    ps.setString(36,ok_period.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return ok_period;
    }

    public static void update(ok_period ok_period)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE ok_period SET id=?, branch=?, personal_code=?, in_office_date=?, out_office_date=?, office_name=?, office_address=?, established_post=?, motive_out=?, type_period_code=?, article_code=?, basis_num=?, basis_date=?, emp_code=?, ins_date=?, base_move_code=?, established_department=?, priz_system=?, cod_bank=?, cod_type_prn=?, cod_pr_off=?, pr_off=?, date_utv_km=?, numb_utv_km=?, date_pr_kvl=?, numb_pr_kvl=?, date_attest=?, resh_attest=?, doljn_id=?, num_pr_off=?, date_pr_off=?, post_code=?, department_code=?, department=?, stag_code=?, emp_code_name=?  WHERE id=?");
                  //  ps.setDate(14,new java.sql.Date(ok_relation.getIns_date().getTime()));
                    ps.setInt(1,ok_period.getId());
                    ps.setString(2,ok_period.getBranch());
                    ps.setInt(3,ok_period.getPersonal_code());
                    ps.setDate(4,new java.sql.Date(ok_period.getIn_office_date().getTime()));
                    ps.setDate(5,new java.sql.Date(ok_period.getOut_office_date().getTime()));
                    ps.setString(6,ok_period.getOffice_name());
                    ps.setString(7,ok_period.getOffice_address());
                    ps.setString(8,ok_period.getEstablished_post());
                    ps.setString(9,ok_period.getMotive_out());
                    ps.setInt(10,ok_period.getType_period_code());
                    ps.setInt(11,ok_period.getArticle_code());
                    ps.setString(12,ok_period.getBasis_num());
                    ps.setDate(13,new java.sql.Date(ok_period.getBasis_date().getTime()));
                    ps.setInt(14,ok_period.getEmp_code());
                    ps.setDate(15,new java.sql.Date(ok_period.getIns_date().getTime()));
                    ps.setInt(16,ok_period.getBase_move_code());
                    ps.setString(17,ok_period.getEstablished_department());
                    ps.setString(18,ok_period.getPriz_system());
                    ps.setString(19,ok_period.getCod_bank());
                    ps.setString(20,ok_period.getCod_type_prn());
                    ps.setString(21,ok_period.getCod_pr_off());
                    ps.setString(22,ok_period.getPr_off());
                    ps.setDate(23,new java.sql.Date(ok_period.getDate_utv_km().getTime()));
                    ps.setString(24,ok_period.getNumb_utv_km());
                    ps.setDate(25,new java.sql.Date(ok_period.getDate_pr_kvl().getTime()));
                    ps.setString(26,ok_period.getNumb_pr_kvl());
                    ps.setDate(27,new java.sql.Date(ok_period.getDate_attest().getTime()));
                    ps.setString(28,ok_period.getResh_attest());
                    ps.setString(29,ok_period.getDoljn_id());
                    ps.setString(30,ok_period.getNum_pr_off());
                    ps.setDate(31,new java.sql.Date(ok_period.getDate_pr_off().getTime()));
                    ps.setInt(32,ok_period.getPost_code());
                    ps.setInt(33,ok_period.getDepartment_code());
                    ps.setString(34,ok_period.getDepartment());
                    ps.setInt(35,ok_period.getStag_code());
                    ps.setString(36,ok_period.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ok_period ok_period)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM ok_period WHERE id=?");
                    ps.setDouble(1, ok_period.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

}
