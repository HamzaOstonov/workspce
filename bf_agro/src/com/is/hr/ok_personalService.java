package com.is.hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class ok_personalService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM ok_personal ";


    public List<Ok_personal> getok_personal(String alias)  {

            List<Ok_personal> list = new ArrayList<Ok_personal>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM ok_personal");
                    while (rs.next()) {
                            list.add(new Ok_personal(
                            		rs.getString("id"),
                            		rs.getString("branch"),
                            		rs.getString("personal_code"),
                            		rs.getString("colleague_code"),
                            		rs.getString("status_code"),
                            		rs.getString("salary_code"),
                            		rs.getString("family"),
                            		rs.getString("first_name"),
                            		rs.getString("patronymic"),
                            		rs.getString("gender_code"),
                            		rs.getDate("birthday"),
                            		rs.getString("region_id"),
                            		rs.getString("distr"),
                            		rs.getString("nationality_code"),
                            		rs.getString("family_status_code"),
                            		rs.getString("reg_type_code"),
                            		rs.getString("home_address"),
                            		rs.getString("home_addressfact"),
                            		rs.getString("pass_seriya"),
                            		rs.getString("pass_num"),
                            		rs.getDate("pass_date"),
                            		rs.getString("pass_reg"),
                            		rs.getString("record_book_number"),
                            		rs.getString("record_book_series"),
                            		rs.getString("telefon"),
                            		rs.getString("emp_code"),
                            		rs.getDate("ins_date"),
                            		rs.getString("profmember"),
                            		rs.getString("tabno"),
                            		rs.getString("education_title_code"),
                            		rs.getString("birthplace"),
                            		rs.getString("motive_out"),
                            		rs.getString("basis_num"),
                            		rs.getDate("basis_date"),
                            		rs.getString("leave_code"),
                            		rs.getString("department_code"),
                            		rs.getString("post_code"),
                            		rs.getString("nn"),
                            		rs.getString("motive_dismissial_code"),
                            		rs.getString("nps_id"),
                            		rs.getString("special_code"),
                            		rs.getString("inn"),
                            		rs.getString("cod_distr_uvd"),
                            		rs.getString("cod_distr_prim"),
                            		rs.getString("cod_str_birth"),
                            		rs.getString("cod_obl_prim"),
                            		rs.getString("cod_place_birth_prim"),
                            		rs.getString("cod_sitizent"),
                            		rs.getString("cod_str_sitizent"),
                            		rs.getString("cod_str_live"),
                            		rs.getString("cod_obl_live"),
                            		rs.getString("cod_obl_live_prim"),
                            		rs.getString("cod_distr_live"),
                            		rs.getString("cod_distr_live_prim"),
                            		rs.getString("code_naci_prim"),
                            		rs.getString("check_kfs"),
                            		rs.getString("is_boss"),
                            		rs.getString("bank_spec"),
                            		rs.getString("notice"),
                            		rs.getString("prich_id"),
                            		rs.getString("name_pr"),
                            		rs.getDate("srok_date"),
                            		rs.getString("home_addressfact_region_id"),
                            		rs.getString("home_addressfact_distr"),
                            		rs.getString("home_address_region_id"),
                            		rs.getString("home_address_distr"),
                            		rs.getString("passport_type_code"),
                            		rs.getString("regplace_code"),
                            		rs.getDate("pass_date_end"),
                            		rs.getString("gosubmit_code"),
                            		rs.getString("live_place"),
                            		rs.getString("birthplace_point"),
                            		rs.getString("maiden_family"),
                            		rs.getString("department_code_old"),
                            		rs.getString("post_code_old"),
                            		rs.getString("special_code_old"),
                            		rs.getString("spec_ikki"),
                            		rs.getString("department_code_new"),
                            		rs.getString("post_code_new"),
                            		rs.getString("special_code_new"),
                            		rs.getDate("reg_date_end"),
                            		rs.getString("trud_sogl"),
                            		rs.getDate("application_date"),
                            		rs.getDate("reply_date"),
                            		rs.getString("is_party"),
                            		rs.getString("is_academic"),
                            		rs.getString("is_degree"),
                            		rs.getString("is_award"),
                            		rs.getString("is_scientific"),
                            		rs.getString("is_voyage"),
                            		rs.getString("is_convictions"),
                            		rs.getString("is_language"),
                            		rs.getString("is_rise"),
                            		rs.getString("is_election"),
                            		rs.getString("is_premium"),
                            		rs.getString("is_army"),
                            		rs.getString("user_name"),
                            		rs.getString("maidenstag"),
                            		rs.getString("zp_rate_code"),
                            		rs.getString("family_lat"),
                            		rs.getString("first_name_lat"),
                            		rs.getString("patronymic_lat"),
                            		rs.getString("comments"),
                            		rs.getString("emp_code_name"),
                            		rs.getString("tabel_line"),
                            		rs.getString("id_client"),
                            		rs.getString("user_id"),
                            		rs.getDate("gph_date_begin"),
                            		rs.getDate("gph_date_end"),
                            		rs.getString("gph_summa"),
                            		rs.getString("gph_post_code"),
                            		rs.getString("gph_department_code"),
                            		rs.getDate("gph_order_date"),
                            		rs.getString("gph_order_num"),
                            		rs.getString("gph_rezident"),
                            		rs.getString("email"),
                            		rs.getString("zp_rate_code_old"),
                            		rs.getString("zp_rate_code_new"),
                            		rs.getString("profession_personal"),
                            		rs.getString("bxm"),
                            		rs.getString("institution_code"),
                            		rs.getString("institution_end_year"),
                            		rs.getString("birthplace_point")));
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

    private static List<FilterField> getFilterFields(ok_personalFilter filter){
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
          if(!CheckNull.isEmpty(filter.getColleague_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "colleague_code=?",filter.getColleague_code()));
          }
          if(!CheckNull.isEmpty(filter.getStatus_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "status_code=?",filter.getStatus_code()));
          }
          if(!CheckNull.isEmpty(filter.getSalary_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "salary_code=?",filter.getSalary_code()));
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
          if(!CheckNull.isEmpty(filter.getGender_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "gender_code=?",filter.getGender_code()));
          }
          if(!CheckNull.isEmpty(filter.getBirthday())){
                  flfields.add(new FilterField(getCond(flfields)+ "birthday=?",filter.getBirthday()));
          }
          if(!CheckNull.isEmpty(filter.getRegion_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "region_id=?",filter.getRegion_id()));
          }
          if(!CheckNull.isEmpty(filter.getDistr())){
                  flfields.add(new FilterField(getCond(flfields)+ "distr=?",filter.getDistr()));
          }
          if(!CheckNull.isEmpty(filter.getNationality_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "nationality_code=?",filter.getNationality_code()));
          }
          if(!CheckNull.isEmpty(filter.getFamily_status_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "family_status_code=?",filter.getFamily_status_code()));
          }
          if(!CheckNull.isEmpty(filter.getReg_type_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "reg_type_code=?",filter.getReg_type_code()));
          }
          if(!CheckNull.isEmpty(filter.getHome_address())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_address=?",filter.getHome_address()));
          }
          if(!CheckNull.isEmpty(filter.getHome_addressfact())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_addressfact=?",filter.getHome_addressfact()));
          }
          if(!CheckNull.isEmpty(filter.getPass_seriya())){
                  flfields.add(new FilterField(getCond(flfields)+ "pass_seriya=?",filter.getPass_seriya()));
          }
          if(!CheckNull.isEmpty(filter.getPass_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "pass_num=?",filter.getPass_num()));
          }
          if(!CheckNull.isEmpty(filter.getPass_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "pass_date=?",filter.getPass_date()));
          }
          if(!CheckNull.isEmpty(filter.getPass_reg())){
                  flfields.add(new FilterField(getCond(flfields)+ "pass_reg=?",filter.getPass_reg()));
          }
          if(!CheckNull.isEmpty(filter.getRecord_book_number())){
                  flfields.add(new FilterField(getCond(flfields)+ "record_book_number=?",filter.getRecord_book_number()));
          }
          if(!CheckNull.isEmpty(filter.getRecord_book_series())){
                  flfields.add(new FilterField(getCond(flfields)+ "record_book_series=?",filter.getRecord_book_series()));
          }
          if(!CheckNull.isEmpty(filter.getTelefon())){
                  flfields.add(new FilterField(getCond(flfields)+ "telefon=?",filter.getTelefon()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
          }
          if(!CheckNull.isEmpty(filter.getIns_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
          }
          if(!CheckNull.isEmpty(filter.getProfmember())){
                  flfields.add(new FilterField(getCond(flfields)+ "profmember=?",filter.getProfmember()));
          }
          if(!CheckNull.isEmpty(filter.getTabno())){
                  flfields.add(new FilterField(getCond(flfields)+ "tabno=?",filter.getTabno()));
          }
          if(!CheckNull.isEmpty(filter.getEducation_title_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "education_title_code=?",filter.getEducation_title_code()));
          }
          if(!CheckNull.isEmpty(filter.getBirthplace())){
                  flfields.add(new FilterField(getCond(flfields)+ "birthplace=?",filter.getBirthplace()));
          }
          if(!CheckNull.isEmpty(filter.getMotive_out())){
                  flfields.add(new FilterField(getCond(flfields)+ "motive_out=?",filter.getMotive_out()));
          }
          if(!CheckNull.isEmpty(filter.getBasis_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "basis_num=?",filter.getBasis_num()));
          }
          if(!CheckNull.isEmpty(filter.getBasis_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "basis_date=?",filter.getBasis_date()));
          }
          if(!CheckNull.isEmpty(filter.getLeave_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "leave_code=?",filter.getLeave_code()));
          }
          if(!CheckNull.isEmpty(filter.getDepartment_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "department_code=?",filter.getDepartment_code()));
          }
          if(!CheckNull.isEmpty(filter.getPost_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "post_code=?",filter.getPost_code()));
          }
          if(!CheckNull.isEmpty(filter.getNn())){
                  flfields.add(new FilterField(getCond(flfields)+ "nn=?",filter.getNn()));
          }
          if(!CheckNull.isEmpty(filter.getMotive_dismissial_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "motive_dismissial_code=?",filter.getMotive_dismissial_code()));
          }
          if(!CheckNull.isEmpty(filter.getNps_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "nps_id=?",filter.getNps_id()));
          }
          if(!CheckNull.isEmpty(filter.getSpecial_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "special_code=?",filter.getSpecial_code()));
          }
          if(!CheckNull.isEmpty(filter.getInn())){
                  flfields.add(new FilterField(getCond(flfields)+ "inn=?",filter.getInn()));
          }
          if(!CheckNull.isEmpty(filter.getCod_distr_uvd())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_distr_uvd=?",filter.getCod_distr_uvd()));
          }
          if(!CheckNull.isEmpty(filter.getCod_distr_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_distr_prim=?",filter.getCod_distr_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_birth())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_birth=?",filter.getCod_str_birth()));
          }
          if(!CheckNull.isEmpty(filter.getCod_obl_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_obl_prim=?",filter.getCod_obl_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_place_birth_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_place_birth_prim=?",filter.getCod_place_birth_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_sitizent())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_sitizent=?",filter.getCod_sitizent()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_sitizent())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_sitizent=?",filter.getCod_str_sitizent()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_live())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_live=?",filter.getCod_str_live()));
          }
          if(!CheckNull.isEmpty(filter.getCod_obl_live())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_obl_live=?",filter.getCod_obl_live()));
          }
          if(!CheckNull.isEmpty(filter.getCod_obl_live_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_obl_live_prim=?",filter.getCod_obl_live_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_distr_live())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_distr_live=?",filter.getCod_distr_live()));
          }
          if(!CheckNull.isEmpty(filter.getCod_distr_live_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_distr_live_prim=?",filter.getCod_distr_live_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCode_naci_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "code_naci_prim=?",filter.getCode_naci_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCheck_kfs())){
                  flfields.add(new FilterField(getCond(flfields)+ "check_kfs=?",filter.getCheck_kfs()));
          }
          if(!CheckNull.isEmpty(filter.getIs_boss())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_boss=?",filter.getIs_boss()));
          }
          if(!CheckNull.isEmpty(filter.getBank_spec())){
                  flfields.add(new FilterField(getCond(flfields)+ "bank_spec=?",filter.getBank_spec()));
          }
          if(!CheckNull.isEmpty(filter.getNotice())){
                  flfields.add(new FilterField(getCond(flfields)+ "notice=?",filter.getNotice()));
          }
          if(!CheckNull.isEmpty(filter.getPrich_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "prich_id=?",filter.getPrich_id()));
          }
          if(!CheckNull.isEmpty(filter.getName_pr())){
                  flfields.add(new FilterField(getCond(flfields)+ "name_pr=?",filter.getName_pr()));
          }
          if(!CheckNull.isEmpty(filter.getSrok_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "srok_date=?",filter.getSrok_date()));
          }
          if(!CheckNull.isEmpty(filter.getHome_addressfact_region_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_addressfact_region_id=?",filter.getHome_addressfact_region_id()));
          }
          if(!CheckNull.isEmpty(filter.getHome_addressfact_distr())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_addressfact_distr=?",filter.getHome_addressfact_distr()));
          }
          if(!CheckNull.isEmpty(filter.getHome_address_region_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_address_region_id=?",filter.getHome_address_region_id()));
          }
          if(!CheckNull.isEmpty(filter.getHome_address_distr())){
                  flfields.add(new FilterField(getCond(flfields)+ "home_address_distr=?",filter.getHome_address_distr()));
          }
          if(!CheckNull.isEmpty(filter.getPassport_type_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "passport_type_code=?",filter.getPassport_type_code()));
          }
          if(!CheckNull.isEmpty(filter.getRegplace_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "regplace_code=?",filter.getRegplace_code()));
          }
          if(!CheckNull.isEmpty(filter.getPass_date_end())){
                  flfields.add(new FilterField(getCond(flfields)+ "pass_date_end=?",filter.getPass_date_end()));
          }
          if(!CheckNull.isEmpty(filter.getGosubmit_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "gosubmit_code=?",filter.getGosubmit_code()));
          }
          if(!CheckNull.isEmpty(filter.getLive_place())){
                  flfields.add(new FilterField(getCond(flfields)+ "live_place=?",filter.getLive_place()));
          }
          if(!CheckNull.isEmpty(filter.getBirthplace_point())){
                  flfields.add(new FilterField(getCond(flfields)+ "birthplace_point=?",filter.getBirthplace_point()));
          }
          if(!CheckNull.isEmpty(filter.getMaiden_family())){
                  flfields.add(new FilterField(getCond(flfields)+ "maiden_family=?",filter.getMaiden_family()));
          }
          if(!CheckNull.isEmpty(filter.getDepartment_code_old())){
                  flfields.add(new FilterField(getCond(flfields)+ "department_code_old=?",filter.getDepartment_code_old()));
          }
          if(!CheckNull.isEmpty(filter.getPost_code_old())){
                  flfields.add(new FilterField(getCond(flfields)+ "post_code_old=?",filter.getPost_code_old()));
          }
          if(!CheckNull.isEmpty(filter.getSpecial_code_old())){
                  flfields.add(new FilterField(getCond(flfields)+ "special_code_old=?",filter.getSpecial_code_old()));
          }
          if(!CheckNull.isEmpty(filter.getSpec_ikki())){
                  flfields.add(new FilterField(getCond(flfields)+ "spec_ikki=?",filter.getSpec_ikki()));
          }
          if(!CheckNull.isEmpty(filter.getDepartment_code_new())){
                  flfields.add(new FilterField(getCond(flfields)+ "department_code_new=?",filter.getDepartment_code_new()));
          }
          if(!CheckNull.isEmpty(filter.getPost_code_new())){
                  flfields.add(new FilterField(getCond(flfields)+ "post_code_new=?",filter.getPost_code_new()));
          }
          if(!CheckNull.isEmpty(filter.getSpecial_code_new())){
                  flfields.add(new FilterField(getCond(flfields)+ "special_code_new=?",filter.getSpecial_code_new()));
          }
          if(!CheckNull.isEmpty(filter.getReg_date_end())){
                  flfields.add(new FilterField(getCond(flfields)+ "reg_date_end=?",filter.getReg_date_end()));
          }
          if(!CheckNull.isEmpty(filter.getTrud_sogl())){
                  flfields.add(new FilterField(getCond(flfields)+ "trud_sogl=?",filter.getTrud_sogl()));
          }
          if(!CheckNull.isEmpty(filter.getApplication_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "application_date=?",filter.getApplication_date()));
          }
          if(!CheckNull.isEmpty(filter.getReply_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "reply_date=?",filter.getReply_date()));
          }
          if(!CheckNull.isEmpty(filter.getIs_party())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_party=?",filter.getIs_party()));
          }
          if(!CheckNull.isEmpty(filter.getIs_academic())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_academic=?",filter.getIs_academic()));
          }
          if(!CheckNull.isEmpty(filter.getIs_degree())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_degree=?",filter.getIs_degree()));
          }
          if(!CheckNull.isEmpty(filter.getIs_award())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_award=?",filter.getIs_award()));
          }
          if(!CheckNull.isEmpty(filter.getIs_scientific())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_scientific=?",filter.getIs_scientific()));
          }
          if(!CheckNull.isEmpty(filter.getIs_voyage())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_voyage=?",filter.getIs_voyage()));
          }
          if(!CheckNull.isEmpty(filter.getIs_convictions())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_convictions=?",filter.getIs_convictions()));
          }
          if(!CheckNull.isEmpty(filter.getIs_language())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_language=?",filter.getIs_language()));
          }
          if(!CheckNull.isEmpty(filter.getIs_rise())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_rise=?",filter.getIs_rise()));
          }
          if(!CheckNull.isEmpty(filter.getIs_election())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_election=?",filter.getIs_election()));
          }
          if(!CheckNull.isEmpty(filter.getIs_premium())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_premium=?",filter.getIs_premium()));
          }
          if(!CheckNull.isEmpty(filter.getIs_army())){
                  flfields.add(new FilterField(getCond(flfields)+ "is_army=?",filter.getIs_army()));
          }
          if(!CheckNull.isEmpty(filter.getUser_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "user_name=?",filter.getUser_name()));
          }
          if(!CheckNull.isEmpty(filter.getMaidenstag())){
                  flfields.add(new FilterField(getCond(flfields)+ "maidenstag=?",filter.getMaidenstag()));
          }
          if(!CheckNull.isEmpty(filter.getZp_rate_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "zp_rate_code=?",filter.getZp_rate_code()));
          }
          if(!CheckNull.isEmpty(filter.getFamily_lat())){
                  flfields.add(new FilterField(getCond(flfields)+ "family_lat=?",filter.getFamily_lat()));
          }
          if(!CheckNull.isEmpty(filter.getFirst_name_lat())){
                  flfields.add(new FilterField(getCond(flfields)+ "first_name_lat=?",filter.getFirst_name_lat()));
          }
          if(!CheckNull.isEmpty(filter.getPatronymic_lat())){
                  flfields.add(new FilterField(getCond(flfields)+ "patronymic_lat=?",filter.getPatronymic_lat()));
          }
          if(!CheckNull.isEmpty(filter.getComments())){
                  flfields.add(new FilterField(getCond(flfields)+ "comments=?",filter.getComments()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
          }
          if(!CheckNull.isEmpty(filter.getTabel_line())){
                  flfields.add(new FilterField(getCond(flfields)+ "tabel_line=?",filter.getTabel_line()));
          }
          if(!CheckNull.isEmpty(filter.getId_client())){
                  flfields.add(new FilterField(getCond(flfields)+ "id_client=?",filter.getId_client()));
          }
          if(!CheckNull.isEmpty(filter.getUser_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "user_id=?",filter.getUser_id()));
          }
          if(!CheckNull.isEmpty(filter.getGph_date_begin())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_date_begin=?",filter.getGph_date_begin()));
          }
          if(!CheckNull.isEmpty(filter.getGph_date_end())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_date_end=?",filter.getGph_date_end()));
          }
          if(!CheckNull.isEmpty(filter.getGph_summa())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_summa=?",filter.getGph_summa()));
          }
          if(!CheckNull.isEmpty(filter.getGph_post_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_post_code=?",filter.getGph_post_code()));
          }
          if(!CheckNull.isEmpty(filter.getGph_department_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_department_code=?",filter.getGph_department_code()));
          }
          if(!CheckNull.isEmpty(filter.getGph_order_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_order_date=?",filter.getGph_order_date()));
          }
          if(!CheckNull.isEmpty(filter.getGph_order_num())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_order_num=?",filter.getGph_order_num()));
          }
          if(!CheckNull.isEmpty(filter.getGph_rezident())){
                  flfields.add(new FilterField(getCond(flfields)+ "gph_rezident=?",filter.getGph_rezident()));
          }
          if(!CheckNull.isEmpty(filter.getEmail())){
                  flfields.add(new FilterField(getCond(flfields)+ "email=?",filter.getEmail()));
          }
          if(!CheckNull.isEmpty(filter.getZp_rate_code_old())){
                  flfields.add(new FilterField(getCond(flfields)+ "zp_rate_code_old=?",filter.getZp_rate_code_old()));
          }
          if(!CheckNull.isEmpty(filter.getZp_rate_code_new())){
                  flfields.add(new FilterField(getCond(flfields)+ "zp_rate_code_new=?",filter.getZp_rate_code_new()));
          }
          if(!CheckNull.isEmpty(filter.getProfession_personal())){
                  flfields.add(new FilterField(getCond(flfields)+ "profession_personal=?",filter.getProfession_personal()));
          }
          if(!CheckNull.isEmpty(filter.getBxm())){
                  flfields.add(new FilterField(getCond(flfields)+ "bxm=?",filter.getBxm()));
          }
          if(!CheckNull.isEmpty(filter.getInstitution_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "institution_code=?",filter.getInstitution_code()));
          }
          if(!CheckNull.isEmpty(filter.getInstitution_end_year())){
                  flfields.add(new FilterField(getCond(flfields)+ "institution_end_year=?",filter.getInstitution_end_year()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }

    public static int getCount(ok_personalFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM ok_personal ");
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

    public static List<Ok_personal> getok_personalsFl(int pageIndex, int pageSize, ok_personalFilter filter, String alias)  {

            List<Ok_personal> list = new ArrayList<Ok_personal>();
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
                            list.add(new Ok_personal(
                            		rs.getString("id"),
                            		rs.getString("branch"),
                            		rs.getString("personal_code"),
                            		rs.getString("colleague_code"),
                            		rs.getString("status_code"),
                            		rs.getString("salary_code"),
                            		rs.getString("family"),
                            		rs.getString("first_name"),
                            		rs.getString("patronymic"),
                            		rs.getString("gender_code"),
                            		rs.getDate("birthday"),
                            		rs.getString("region_id"),
                            		rs.getString("distr"),
                            		rs.getString("nationality_code"),
                            		rs.getString("family_status_code"),
                            		rs.getString("reg_type_code"),
                            		rs.getString("home_address"),
                            		rs.getString("home_addressfact"),
                            		rs.getString("pass_seriya"),
                            		rs.getString("pass_num"),
                            		rs.getDate("pass_date"),
                            		rs.getString("pass_reg"),
                            		rs.getString("record_book_number"),
                            		rs.getString("record_book_series"),
                            		rs.getString("telefon"),
                            		rs.getString("emp_code"),
                            		rs.getDate("ins_date"),
                            		rs.getString("profmember"),
                            		rs.getString("tabno"),
                            		rs.getString("education_title_code"),
                            		rs.getString("birthplace"),
                            		rs.getString("motive_out"),
                            		rs.getString("basis_num"),
                            		rs.getDate("basis_date"),
                            		rs.getString("leave_code"),
                            		rs.getString("department_code"),
                            		rs.getString("post_code"),
                            		rs.getString("nn"),
                            		rs.getString("motive_dismissial_code"),
                            		rs.getString("nps_id"),
                            		rs.getString("special_code"),
                            		rs.getString("inn"),
                            		rs.getString("cod_distr_uvd"),
                            		rs.getString("cod_distr_prim"),
                            		rs.getString("cod_str_birth"),
                            		rs.getString("cod_obl_prim"),
                            		rs.getString("cod_place_birth_prim"),
                            		rs.getString("cod_sitizent"),
                            		rs.getString("cod_str_sitizent"),
                            		rs.getString("cod_str_live"),
                            		rs.getString("cod_obl_live"),
                            		rs.getString("cod_obl_live_prim"),
                            		rs.getString("cod_distr_live"),
                            		rs.getString("cod_distr_live_prim"),
                            		rs.getString("code_naci_prim"),
                            		rs.getString("check_kfs"),
                            		rs.getString("is_boss"),
                            		rs.getString("bank_spec"),
                            		rs.getString("notice"),
                            		rs.getString("prich_id"),
                            		rs.getString("name_pr"),
                            		rs.getDate("srok_date"),
                            		rs.getString("home_addressfact_region_id"),
                            		rs.getString("home_addressfact_distr"),
                            		rs.getString("home_address_region_id"),
                            		rs.getString("home_address_distr"),
                            		rs.getString("passport_type_code"),
                            		rs.getString("regplace_code"),
                            		rs.getDate("pass_date_end"),
                            		rs.getString("gosubmit_code"),
                            		rs.getString("live_place"),
                            		rs.getString("birthplace_point"),
                            		rs.getString("maiden_family"),
                            		rs.getString("department_code_old"),
                            		rs.getString("post_code_old"),
                            		rs.getString("special_code_old"),
                            		rs.getString("spec_ikki"),
                            		rs.getString("department_code_new"),
                            		rs.getString("post_code_new"),
                            		rs.getString("special_code_new"),
                            		rs.getDate("reg_date_end"),
                            		rs.getString("trud_sogl"),
                            		rs.getDate("application_date"),
                            		rs.getDate("reply_date"),
                            		rs.getString("is_party"),
                            		rs.getString("is_academic"),
                            		rs.getString("is_degree"),
                            		rs.getString("is_award"),
                            		rs.getString("is_scientific"),
                            		rs.getString("is_voyage"),
                            		rs.getString("is_convictions"),
                            		rs.getString("is_language"),
                            		rs.getString("is_rise"),
                            		rs.getString("is_election"),
                            		rs.getString("is_premium"),
                            		rs.getString("is_army"),
                            		rs.getString("user_name"),
                            		rs.getString("maidenstag"),
                            		rs.getString("zp_rate_code"),
                            		rs.getString("family_lat"),
                            		rs.getString("first_name_lat"),
                            		rs.getString("patronymic_lat"),
                            		rs.getString("comments"),
                            		rs.getString("emp_code_name"),
                            		rs.getString("tabel_line"),
                            		rs.getString("id_client"),
                            		rs.getString("user_id"),
                            		rs.getDate("gph_date_begin"),
                            		rs.getDate("gph_date_end"),
                            		rs.getString("gph_summa"),
                            		rs.getString("gph_post_code"),
                            		rs.getString("gph_department_code"),
                            		rs.getDate("gph_order_date"),
                            		rs.getString("gph_order_num"),
                            		rs.getString("gph_rezident"),
                            		rs.getString("email"),
                            		rs.getString("zp_rate_code_old"),
                            		rs.getString("zp_rate_code_new"),
                            		rs.getString("profession_personal"),
                            		rs.getString("bxm"),
                            		rs.getString("institution_code"),
                            		rs.getString("institution_end_year"),
                            		rs.getString("birthplace_point")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }

    public Ok_personal getok_personal(int ok_personalId) {

    	Ok_personal ok_personal = new Ok_personal();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_personal WHERE id=?");
                    ps.setInt(1, ok_personalId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_personal = new Ok_personal();
                            
                            ok_personal.setId(rs.getString("id"));
                            ok_personal.setBranch(rs.getString("branch"));
                            ok_personal.setPersonal_code(rs.getString("personal_code"));
                            ok_personal.setColleague_code(rs.getString("colleague_code"));
                            ok_personal.setStatus_code(rs.getString("status_code"));
                            ok_personal.setSalary_code(rs.getString("salary_code"));
                            ok_personal.setFamily(rs.getString("family"));
                            ok_personal.setFirst_name(rs.getString("first_name"));
                            ok_personal.setPatronymic(rs.getString("patronymic"));
                            ok_personal.setGender_code(rs.getString("gender_code"));
                            ok_personal.setBirthday(rs.getDate("birthday"));
                            ok_personal.setRegion_id(rs.getString("region_id"));
                            ok_personal.setDistr(rs.getString("distr"));
                            ok_personal.setNationality_code(rs.getString("nationality_code"));
                            ok_personal.setFamily_status_code(rs.getString("family_status_code"));
                            ok_personal.setReg_type_code(rs.getString("reg_type_code"));
                            ok_personal.setHome_address(rs.getString("home_address"));
                            ok_personal.setHome_addressfact(rs.getString("home_addressfact"));
                            ok_personal.setPass_seriya(rs.getString("pass_seriya"));
                            ok_personal.setPass_num(rs.getString("pass_num"));
                            ok_personal.setPass_date(rs.getDate("pass_date"));
                            ok_personal.setPass_reg(rs.getString("pass_reg"));
                            ok_personal.setRecord_book_number(rs.getString("record_book_number"));
                            ok_personal.setRecord_book_series(rs.getString("record_book_series"));
                            ok_personal.setTelefon(rs.getString("telefon"));
                            ok_personal.setEmp_code(rs.getString("emp_code"));
                            ok_personal.setIns_date(rs.getDate("ins_date"));
                            ok_personal.setProfmember(rs.getString("profmember"));
                            ok_personal.setTabno(rs.getString("tabno"));
                            ok_personal.setEducation_title_code(rs.getString("education_title_code"));
                            ok_personal.setBirthplace(rs.getString("birthplace"));
                            ok_personal.setMotive_out(rs.getString("motive_out"));
                            ok_personal.setBasis_num(rs.getString("basis_num"));
                            ok_personal.setBasis_date(rs.getDate("basis_date"));
                            ok_personal.setLeave_code(rs.getString("leave_code"));
                            ok_personal.setDepartment_code(rs.getString("department_code"));
                            ok_personal.setPost_code(rs.getString("post_code"));
                            ok_personal.setNn(rs.getString("nn"));
                            ok_personal.setMotive_dismissial_code(rs.getString("motive_dismissial_code"));
                            ok_personal.setNps_id(rs.getString("nps_id"));
                            ok_personal.setSpecial_code(rs.getString("special_code"));
                            ok_personal.setInn(rs.getString("inn"));
                            ok_personal.setCod_distr_uvd(rs.getString("cod_distr_uvd"));
                            ok_personal.setCod_distr_prim(rs.getString("cod_distr_prim"));
                            ok_personal.setCod_str_birth(rs.getString("cod_str_birth"));
                            ok_personal.setCod_obl_prim(rs.getString("cod_obl_prim"));
                            ok_personal.setCod_place_birth_prim(rs.getString("cod_place_birth_prim"));
                            ok_personal.setCod_sitizent(rs.getString("cod_sitizent"));
                            ok_personal.setCod_str_sitizent(rs.getString("cod_str_sitizent"));
                            ok_personal.setCod_str_live(rs.getString("cod_str_live"));
                            ok_personal.setCod_obl_live(rs.getString("cod_obl_live"));
                            ok_personal.setCod_obl_live_prim(rs.getString("cod_obl_live_prim"));
                            ok_personal.setCod_distr_live(rs.getString("cod_distr_live"));
                            ok_personal.setCod_distr_live_prim(rs.getString("cod_distr_live_prim"));
                            ok_personal.setCode_naci_prim(rs.getString("code_naci_prim"));
                            ok_personal.setCheck_kfs(rs.getString("check_kfs"));
                            ok_personal.setIs_boss(rs.getString("is_boss"));
                            ok_personal.setBank_spec(rs.getString("bank_spec"));
                            ok_personal.setNotice(rs.getString("notice"));
                            ok_personal.setPrich_id(rs.getString("prich_id"));
                            ok_personal.setName_pr(rs.getString("name_pr"));
                            ok_personal.setSrok_date(rs.getDate("srok_date"));
                            ok_personal.setHome_addressfact_region_id(rs.getString("home_addressfact_region_id"));
                            ok_personal.setHome_addressfact_distr(rs.getString("home_addressfact_distr"));
                            ok_personal.setHome_address_region_id(rs.getString("home_address_region_id"));
                            ok_personal.setHome_address_distr(rs.getString("home_address_distr"));
                            ok_personal.setPassport_type_code(rs.getString("passport_type_code"));
                            ok_personal.setRegplace_code(rs.getString("regplace_code"));
                            ok_personal.setPass_date_end(rs.getDate("pass_date_end"));
                            ok_personal.setGosubmit_code(rs.getString("gosubmit_code"));
                            ok_personal.setLive_place(rs.getString("live_place"));
                            ok_personal.setBirthplace_point(rs.getString("birthplace_point"));
                            ok_personal.setMaiden_family(rs.getString("maiden_family"));
                            ok_personal.setDepartment_code_old(rs.getString("department_code_old"));
                            ok_personal.setPost_code_old(rs.getString("post_code_old"));
                            ok_personal.setSpecial_code_old(rs.getString("special_code_old"));
                            ok_personal.setSpec_ikki(rs.getString("spec_ikki"));
                            ok_personal.setDepartment_code_new(rs.getString("department_code_new"));
                            ok_personal.setPost_code_new(rs.getString("post_code_new"));
                            ok_personal.setSpecial_code_new(rs.getString("special_code_new"));
                            ok_personal.setReg_date_end(rs.getDate("reg_date_end"));
                            ok_personal.setTrud_sogl(rs.getString("trud_sogl"));
                            ok_personal.setApplication_date(rs.getDate("application_date"));
                            ok_personal.setReply_date(rs.getDate("reply_date"));
                            ok_personal.setIs_party(rs.getString("is_party"));
                            ok_personal.setIs_academic(rs.getString("is_academic"));
                            ok_personal.setIs_degree(rs.getString("is_degree"));
                            ok_personal.setIs_award(rs.getString("is_award"));
                            ok_personal.setIs_scientific(rs.getString("is_scientific"));
                            ok_personal.setIs_voyage(rs.getString("is_voyage"));
                            ok_personal.setIs_convictions(rs.getString("is_convictions"));
                            ok_personal.setIs_language(rs.getString("is_language"));
                            ok_personal.setIs_rise(rs.getString("is_rise"));
                            ok_personal.setIs_election(rs.getString("is_election"));
                            ok_personal.setIs_premium(rs.getString("is_premium"));
                            ok_personal.setIs_army(rs.getString("is_army"));
                            ok_personal.setUser_name(rs.getString("user_name"));
                            ok_personal.setMaidenstag(rs.getString("maidenstag"));
                            ok_personal.setZp_rate_code(rs.getString("zp_rate_code"));
                            ok_personal.setFamily_lat(rs.getString("family_lat"));
                            ok_personal.setFirst_name_lat(rs.getString("first_name_lat"));
                            ok_personal.setPatronymic_lat(rs.getString("patronymic_lat"));
                            ok_personal.setComments(rs.getString("comments"));
                            ok_personal.setEmp_code_name(rs.getString("emp_code_name"));
                            ok_personal.setTabel_line(rs.getString("tabel_line"));
                            ok_personal.setId_client(rs.getString("id_client"));
                            ok_personal.setUser_id(rs.getString("user_id"));
                            ok_personal.setGph_date_begin(rs.getDate("gph_date_begin"));
                            ok_personal.setGph_date_end(rs.getDate("gph_date_end"));
                            ok_personal.setGph_summa(rs.getString("gph_summa"));
                            ok_personal.setGph_post_code(rs.getString("gph_post_code"));
                            ok_personal.setGph_department_code(rs.getString("gph_department_code"));
                            ok_personal.setGph_order_date(rs.getDate("gph_order_date"));
                            ok_personal.setGph_order_num(rs.getString("gph_order_num"));
                            ok_personal.setGph_rezident(rs.getString("gph_rezident"));
                            ok_personal.setEmail(rs.getString("email"));
                            ok_personal.setZp_rate_code_old(rs.getString("zp_rate_code_old"));
                            ok_personal.setZp_rate_code_new(rs.getString("zp_rate_code_new"));
                            ok_personal.setProfession_personal(rs.getString("profession_personal"));
                            ok_personal.setBxm(rs.getString("bxm"));
                            ok_personal.setInstitution_code(rs.getString("institution_code"));
                            ok_personal.setInstitution_end_year(rs.getString("institution_end_year"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return ok_personal;
    }

    public static Ok_personal create(Ok_personal ok_personal1)  {	

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    
                    //ps = c.prepareStatement("SELECT SQ_ok_personal.NEXTVAL id FROM DUAL");
                    //ResultSet rs = ps.executeQuery();
                    //if (rs.next()) {
                    //       ok_personal.setId(rs.getDouble("id"));
                    //}
                    ps = c.prepareStatement("INSERT INTO ok_personal (id, branch, personal_code, "
                    		+ "colleague_code, status_code, salary_code, family, first_name, patronymic, "
                    		+ "gender_code, birthday, region_id, distr, nationality_code, "
                    		+ "family_status_code, reg_type_code, home_address, home_addressfact, "
                    		+ "pass_seriya, pass_num, pass_date, pass_reg, record_book_number, "
                    		+ "record_book_series, telefon, emp_code, ins_date, profmember, tabno, "
                    		+ "education_title_code, birthplace, motive_out, basis_num, basis_date, "
                    		+ "leave_code, department_code, post_code, nn, motive_dismissial_code, "
                    		+ "nps_id, special_code, inn, cod_distr_uvd, cod_distr_prim, cod_str_birth, "
                    		+ "cod_obl_prim, cod_place_birth_prim, cod_sitizent, cod_str_sitizent, "
                    		+ "cod_str_live, cod_obl_live, cod_obl_live_prim, cod_distr_live, "
                    		+ "cod_distr_live_prim, code_naci_prim, check_kfs, is_boss, bank_spec, "
                    		+ "notice, prich_id, name_pr, srok_date, home_addressfact_region_id, "
                    		+ "home_addressfact_distr, home_address_region_id, home_address_distr, "
                    		+ "passport_type_code, regplace_code, pass_date_end, gosubmit_code, "
                    		+ "live_place, birthplace_point, maiden_family, department_code_old, "
                    		+ "post_code_old, special_code_old, spec_ikki, department_code_new, "
                    		+ "post_code_new, special_code_new, reg_date_end, trud_sogl, "
                    		+ "application_date, reply_date, is_party, is_academic, is_degree, "
                    		+ "is_award, is_scientific, is_voyage, is_convictions, is_language, "
                    		+ "is_rise, is_election, is_premium, is_army, user_name, maidenstag, "
                    		+ "zp_rate_code, family_lat, first_name_lat, patronymic_lat, comments, "
                    		+ "emp_code_name, tabel_line, id_client, user_id, gph_date_begin, "
                    		+ "gph_date_end, gph_summa, gph_post_code, gph_department_code, "
                    		+ "gph_order_date, gph_order_num, gph_rezident, email, zp_rate_code_old, "
                    		+ "zp_rate_code_new, profession_personal, bxm, institution_code, "
                    		+ "institution_end_year ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    		+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    		+ "?,?,?,?,?,?,?)");
                    
                    ps.setString(1,ok_personal1.getId());
                    ps.setString(2,ok_personal1.getBranch());
                    ps.setString(3,ok_personal1.getPersonal_code());
                    ps.setString(4,ok_personal1.getColleague_code());
                    ps.setString(5,ok_personal1.getStatus_code());
                    ps.setString(6,ok_personal1.getSalary_code());
                    ps.setString(7,ok_personal1.getFamily());
                    ps.setString(8,ok_personal1.getFirst_name());
                    ps.setString(9,ok_personal1.getPatronymic());
                    ps.setString(10,ok_personal1.getGender_code());
                    ps.setDate(11,new java.sql.Date(ok_personal1.getBirthday().getTime()));
                    ps.setString(12,ok_personal1.getRegion_id());
                    ps.setString(13,ok_personal1.getDistr());
                    ps.setString(14,ok_personal1.getNationality_code());
                    ps.setString(15,ok_personal1.getFamily_status_code());
                    ps.setString(16,ok_personal1.getReg_type_code());
                    ps.setString(17,ok_personal1.getHome_address());
                    ps.setString(18,ok_personal1.getHome_addressfact());
                    ps.setString(19,ok_personal1.getPass_seriya());
                    ps.setString(20,ok_personal1.getPass_num());
                    ps.setDate(21,new java.sql.Date(ok_personal1.getPass_date().getTime()));
                    ps.setString(22,ok_personal1.getPass_reg());
                    ps.setString(23,ok_personal1.getRecord_book_number());
                    ps.setString(24,ok_personal1.getRecord_book_series());
                    ps.setString(25,ok_personal1.getTelefon());
                    ps.setString(26,ok_personal1.getEmp_code());
                    ps.setDate(27,new java.sql.Date(ok_personal1.getIns_date().getTime()));
                    ps.setString(28,ok_personal1.getProfmember());
                    ps.setString(29,ok_personal1.getTabno());
                    ps.setString(30,ok_personal1.getEducation_title_code());
                    ps.setString(31,ok_personal1.getBirthplace());
                    ps.setString(32,ok_personal1.getMotive_out());
                    ps.setString(33,ok_personal1.getBasis_num());
                    ps.setDate(34,new java.sql.Date(ok_personal1.getBasis_date().getTime()));
                    ps.setString(35,ok_personal1.getLeave_code());
                    ps.setString(36,ok_personal1.getDepartment_code());
                    ps.setString(37,ok_personal1.getPost_code());
                    ps.setString(38,ok_personal1.getNn());
                    ps.setString(39,ok_personal1.getMotive_dismissial_code());
                    ps.setString(40,ok_personal1.getNps_id());
                    ps.setString(41,ok_personal1.getSpecial_code());
                    ps.setString(42,ok_personal1.getInn());
                    ps.setString(43,ok_personal1.getCod_distr_uvd());
                    ps.setString(44,ok_personal1.getCod_distr_prim());
                    ps.setString(45,ok_personal1.getCod_str_birth());
                    ps.setString(46,ok_personal1.getCod_obl_prim());
                    ps.setString(47,ok_personal1.getCod_place_birth_prim());
                    ps.setString(48,ok_personal1.getCod_sitizent());
                    ps.setString(49,ok_personal1.getCod_str_sitizent());
                    ps.setString(50,ok_personal1.getCod_str_live());
                    ps.setString(51,ok_personal1.getCod_obl_live());
                    ps.setString(52,ok_personal1.getCod_obl_live_prim());
                    ps.setString(53,ok_personal1.getCod_distr_live());
                    ps.setString(54,ok_personal1.getCod_distr_live_prim());
                    ps.setString(55,ok_personal1.getCode_naci_prim());
                    ps.setString(56,ok_personal1.getCheck_kfs());
                    ps.setString(57,ok_personal1.getIs_boss());
                    ps.setString(58,ok_personal1.getBank_spec());
                    ps.setString(59,ok_personal1.getNotice());
                    ps.setString(60,ok_personal1.getPrich_id());
                    ps.setString(61,ok_personal1.getName_pr());
                    ps.setDate(62,new java.sql.Date(ok_personal1.getSrok_date().getTime()));
                    ps.setString(63,ok_personal1.getHome_addressfact_region_id());
                    ps.setString(64,ok_personal1.getHome_addressfact_distr());
                    ps.setString(65,ok_personal1.getHome_address_region_id());
                    ps.setString(66,ok_personal1.getHome_address_distr());
                    ps.setString(67,ok_personal1.getPassport_type_code());
                    ps.setString(68,ok_personal1.getRegplace_code());
                    ps.setDate(69,new java.sql.Date(ok_personal1.getPass_date_end().getTime()));
                    ps.setString(70,ok_personal1.getGosubmit_code());
                    ps.setString(71,ok_personal1.getLive_place());
                    ps.setString(72,ok_personal1.getBirthplace_point());
                    ps.setString(73,ok_personal1.getMaiden_family());
                    ps.setString(74,ok_personal1.getDepartment_code_old());
                    ps.setString(75,ok_personal1.getPost_code_old());
                    ps.setString(76,ok_personal1.getSpecial_code_old());
                    ps.setString(77,ok_personal1.getSpec_ikki());
                    ps.setString(78,ok_personal1.getDepartment_code_new());
                    ps.setString(79,ok_personal1.getPost_code_new());
                    ps.setString(80,ok_personal1.getSpecial_code_new());
                    ps.setDate(81,new java.sql.Date(ok_personal1.getReg_date_end().getTime()));
                    ps.setString(82,ok_personal1.getTrud_sogl());
                    ps.setDate(83,new java.sql.Date(ok_personal1.getApplication_date().getTime()));
                    ps.setDate(84,new java.sql.Date(ok_personal1.getReply_date().getTime()));
                    ps.setString(85,ok_personal1.getIs_party());
                    ps.setString(86,ok_personal1.getIs_academic());
                    ps.setString(87,ok_personal1.getIs_degree());
                    ps.setString(88,ok_personal1.getIs_award());
                    ps.setString(89,ok_personal1.getIs_scientific());
                    ps.setString(90,ok_personal1.getIs_voyage());
                    ps.setString(91,ok_personal1.getIs_convictions());
                    ps.setString(92,ok_personal1.getIs_language());
                    ps.setString(93,ok_personal1.getIs_rise());
                    ps.setString(94,ok_personal1.getIs_election());
                    ps.setString(95,ok_personal1.getIs_premium());
                    ps.setString(96,ok_personal1.getIs_army());
                    ps.setString(97,ok_personal1.getUser_name());
                    ps.setString(98,ok_personal1.getMaidenstag());
                    ps.setString(99,ok_personal1.getZp_rate_code());
                    ps.setString(100,ok_personal1.getFamily_lat());
                    ps.setString(101,ok_personal1.getFirst_name_lat());
                    ps.setString(102,ok_personal1.getPatronymic_lat());
                    ps.setString(103,ok_personal1.getComments());
                    ps.setString(104,ok_personal1.getEmp_code_name());
                    ps.setString(105,ok_personal1.getTabel_line());
                    ps.setString(106,ok_personal1.getId_client());
                    ps.setString(107,ok_personal1.getUser_id());
                    ps.setDate(108,new java.sql.Date(ok_personal1.getGph_date_begin().getTime()));
                    ps.setDate(109,new java.sql.Date(ok_personal1.getGph_date_end().getTime()));
                    ps.setString(110,ok_personal1.getGph_summa());
                    ps.setString(111,ok_personal1.getGph_post_code());
                    ps.setString(112,ok_personal1.getGph_department_code());
                    ps.setDate(113,new java.sql.Date(ok_personal1.getGph_order_date().getTime()));
                    ps.setString(114,ok_personal1.getGph_order_num());
                    ps.setString(115,ok_personal1.getGph_rezident());
                    ps.setString(116,ok_personal1.getEmail());
                    ps.setString(117,ok_personal1.getZp_rate_code_old());
                    ps.setString(118,ok_personal1.getZp_rate_code_new());
                    ps.setString(119,ok_personal1.getProfession_personal());
                    ps.setString(120,ok_personal1.getBxm());
                    ps.setString(121,ok_personal1.getInstitution_code());
                    ps.setString(122,ok_personal1.getInstitution_end_year());
                    
                    
                    System.out.println("Parameters:");
                    System.out.println("ID: " + ok_personal1.getId());
                    System.out.println("FAMILY NAME: " + ok_personal1.getFamily());
                    System.out.println("FIRST NAME: " + ok_personal1.getFirst_name());
                    System.out.println("DATE OF BIRTH: " + new java.sql.Date(ok_personal1.getBirthday().getTime()));
                    System.out.println("NATIONALITY: " + ok_personal1.getNationality_code());
                    
                    
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return ok_personal1;
    }

    public static void update(Ok_personal ok_personal)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE ok_personal SET id=?, branch=?, personal_code=?, colleague_code=?, status_code=?, salary_code=?, family=?, first_name=?, patronymic=?, gender_code=?, birthday=?, region_id=?, distr=?, nationality_code=?, family_status_code=?, reg_type_code=?, home_address=?, home_addressfact=?, pass_seriya=?, pass_num=?, pass_date=?, pass_reg=?, record_book_number=?, record_book_series=?, telefon=?, emp_code=?, ins_date=?, profmember=?, tabno=?, education_title_code=?, birthplace=?, motive_out=?, basis_num=?, basis_date=?, leave_code=?, department_code=?, post_code=?, nn=?, motive_dismissial_code=?, nps_id=?, special_code=?, inn=?, cod_distr_uvd=?, cod_distr_prim=?, cod_str_birth=?, cod_obl_prim=?, cod_place_birth_prim=?, cod_sitizent=?, cod_str_sitizent=?, cod_str_live=?, cod_obl_live=?, cod_obl_live_prim=?, cod_distr_live=?, cod_distr_live_prim=?, code_naci_prim=?, check_kfs=?, is_boss=?, bank_spec=?, notice=?, prich_id=?, name_pr=?, srok_date=?, home_addressfact_region_id=?, home_addressfact_distr=?, home_address_region_id=?, home_address_distr=?, passport_type_code=?, regplace_code=?, pass_date_end=?, gosubmit_code=?, live_place=?, birthplace_point=?, maiden_family=?, department_code_old=?, post_code_old=?, special_code_old=?, spec_ikki=?, department_code_new=?, post_code_new=?, special_code_new=?, reg_date_end=?, trud_sogl=?, application_date=?, reply_date=?, is_party=?, is_academic=?, is_degree=?, is_award=?, is_scientific=?, is_voyage=?, is_convictions=?, is_language=?, is_rise=?, is_election=?, is_premium=?, is_army=?, user_name=?, maidenstag=?, zp_rate_code=?, family_lat=?, first_name_lat=?, patronymic_lat=?, comments=?, emp_code_name=?, tabel_line=?, id_client=?, user_id=?, gph_date_begin=?, gph_date_end=?, gph_summa=?, gph_post_code=?, gph_department_code=?, gph_order_date=?, gph_order_num=?, gph_rezident=?, email=?, zp_rate_code_old=?, zp_rate_code_new=?, profession_personal=?, bxm=?, institution_code=?, institution_end_year=?,  WHERE id=?");
                    
                    ps.setString(1,ok_personal.getId());
                    ps.setString(2,ok_personal.getBranch());
                    ps.setString(3,ok_personal.getPersonal_code());
                    ps.setString(4,ok_personal.getColleague_code());
                    ps.setString(5,ok_personal.getStatus_code());
                    ps.setString(6,ok_personal.getSalary_code());
                    ps.setString(7,ok_personal.getFamily());
                    ps.setString(8,ok_personal.getFirst_name());
                    ps.setString(9,ok_personal.getPatronymic());
                    ps.setString(10,ok_personal.getGender_code());
                    ps.setDate(11,new java.sql.Date(ok_personal.getBirthday().getTime()));
                    ps.setString(12,ok_personal.getRegion_id());
                    ps.setString(13,ok_personal.getDistr());
                    ps.setString(14,ok_personal.getNationality_code());
                    ps.setString(15,ok_personal.getFamily_status_code());
                    ps.setString(16,ok_personal.getReg_type_code());
                    ps.setString(17,ok_personal.getHome_address());
                    ps.setString(18,ok_personal.getHome_addressfact());
                    ps.setString(19,ok_personal.getPass_seriya());
                    ps.setString(20,ok_personal.getPass_num());
                    ps.setDate(21,new java.sql.Date(ok_personal.getPass_date().getTime()));
                    ps.setString(22,ok_personal.getPass_reg());
                    ps.setString(23,ok_personal.getRecord_book_number());
                    ps.setString(24,ok_personal.getRecord_book_series());
                    ps.setString(25,ok_personal.getTelefon());
                    ps.setString(26,ok_personal.getEmp_code());
                    ps.setDate(27,new java.sql.Date(ok_personal.getIns_date().getTime()));
                    ps.setString(28,ok_personal.getProfmember());
                    ps.setString(29,ok_personal.getTabno());
                    ps.setString(30,ok_personal.getEducation_title_code());
                    ps.setString(31,ok_personal.getBirthplace());
                    ps.setString(32,ok_personal.getMotive_out());
                    ps.setString(33,ok_personal.getBasis_num());
                    ps.setDate(34,new java.sql.Date(ok_personal.getBasis_date().getTime()));
                    ps.setString(35,ok_personal.getLeave_code());
                    ps.setString(36,ok_personal.getDepartment_code());
                    ps.setString(37,ok_personal.getPost_code());
                    ps.setString(38,ok_personal.getNn());
                    ps.setString(39,ok_personal.getMotive_dismissial_code());
                    ps.setString(40,ok_personal.getNps_id());
                    ps.setString(41,ok_personal.getSpecial_code());
                    ps.setString(42,ok_personal.getInn());
                    ps.setString(43,ok_personal.getCod_distr_uvd());
                    ps.setString(44,ok_personal.getCod_distr_prim());
                    ps.setString(45,ok_personal.getCod_str_birth());
                    ps.setString(46,ok_personal.getCod_obl_prim());
                    ps.setString(47,ok_personal.getCod_place_birth_prim());
                    ps.setString(48,ok_personal.getCod_sitizent());
                    ps.setString(49,ok_personal.getCod_str_sitizent());
                    ps.setString(50,ok_personal.getCod_str_live());
                    ps.setString(51,ok_personal.getCod_obl_live());
                    ps.setString(52,ok_personal.getCod_obl_live_prim());
                    ps.setString(53,ok_personal.getCod_distr_live());
                    ps.setString(54,ok_personal.getCod_distr_live_prim());
                    ps.setString(55,ok_personal.getCode_naci_prim());
                    ps.setString(56,ok_personal.getCheck_kfs());
                    ps.setString(57,ok_personal.getIs_boss());
                    ps.setString(58,ok_personal.getBank_spec());
                    ps.setString(59,ok_personal.getNotice());
                    ps.setString(60,ok_personal.getPrich_id());
                    ps.setString(61,ok_personal.getName_pr());
                    ps.setDate(62,new java.sql.Date(ok_personal.getSrok_date().getTime()));
                    ps.setString(63,ok_personal.getHome_addressfact_region_id());
                    ps.setString(64,ok_personal.getHome_addressfact_distr());
                    ps.setString(65,ok_personal.getHome_address_region_id());
                    ps.setString(66,ok_personal.getHome_address_distr());
                    ps.setString(67,ok_personal.getPassport_type_code());
                    ps.setString(68,ok_personal.getRegplace_code());
                    ps.setDate(69,new java.sql.Date(ok_personal.getPass_date_end().getTime()));
                    ps.setString(70,ok_personal.getGosubmit_code());
                    ps.setString(71,ok_personal.getLive_place());
                    ps.setString(72,ok_personal.getBirthplace_point());
                    ps.setString(73,ok_personal.getMaiden_family());
                    ps.setString(74,ok_personal.getDepartment_code_old());
                    ps.setString(75,ok_personal.getPost_code_old());
                    ps.setString(76,ok_personal.getSpecial_code_old());
                    ps.setString(77,ok_personal.getSpec_ikki());
                    ps.setString(78,ok_personal.getDepartment_code_new());
                    ps.setString(79,ok_personal.getPost_code_new());
                    ps.setString(80,ok_personal.getSpecial_code_new());
                    ps.setDate(81,new java.sql.Date(ok_personal.getReg_date_end().getTime()));
                    ps.setString(82,ok_personal.getTrud_sogl());
                    ps.setDate(83,new java.sql.Date(ok_personal.getApplication_date().getTime()));
                    ps.setDate(84,new java.sql.Date(ok_personal.getReply_date().getTime()));
                    ps.setString(85,ok_personal.getIs_party());
                    ps.setString(86,ok_personal.getIs_academic());
                    ps.setString(87,ok_personal.getIs_degree());
                    ps.setString(88,ok_personal.getIs_award());
                    ps.setString(89,ok_personal.getIs_scientific());
                    ps.setString(90,ok_personal.getIs_voyage());
                    ps.setString(91,ok_personal.getIs_convictions());
                    ps.setString(92,ok_personal.getIs_language());
                    ps.setString(93,ok_personal.getIs_rise());
                    ps.setString(94,ok_personal.getIs_election());
                    ps.setString(95,ok_personal.getIs_premium());
                    ps.setString(96,ok_personal.getIs_army());
                    ps.setString(97,ok_personal.getUser_name());
                    ps.setString(98,ok_personal.getMaidenstag());
                    ps.setString(99,ok_personal.getZp_rate_code());
                    ps.setString(100,ok_personal.getFamily_lat());
                    ps.setString(101,ok_personal.getFirst_name_lat());
                    ps.setString(102,ok_personal.getPatronymic_lat());
                    ps.setString(103,ok_personal.getComments());
                    ps.setString(104,ok_personal.getEmp_code_name());
                    ps.setString(105,ok_personal.getTabel_line());
                    ps.setString(106,ok_personal.getId_client());
                    ps.setString(107,ok_personal.getUser_id());
                    ps.setDate(108,new java.sql.Date(ok_personal.getGph_date_begin().getTime()));
                    ps.setDate(109,new java.sql.Date(ok_personal.getGph_date_end().getTime()));
                    ps.setString(110,ok_personal.getGph_summa());
                    ps.setString(111,ok_personal.getGph_post_code());
                    ps.setString(112,ok_personal.getGph_department_code());
                    ps.setDate(113,new java.sql.Date(ok_personal.getGph_order_date().getTime()));
                    ps.setString(114,ok_personal.getGph_order_num());
                    ps.setString(115,ok_personal.getGph_rezident());
                    ps.setString(116,ok_personal.getEmail());
                    ps.setString(117,ok_personal.getZp_rate_code_old());
                    ps.setString(118,ok_personal.getZp_rate_code_new());
                    ps.setString(119,ok_personal.getProfession_personal());
                    ps.setString(120,ok_personal.getBxm());
                    ps.setString(121,ok_personal.getInstitution_code());
                    ps.setString(122,ok_personal.getInstitution_end_year());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }
    
    public static void salary (String un, String pw, String alias, pay_calc_LOG pay_calc_LOG) {
        Connection c = null;
       try {
          System.out.println("SALARY");
          c = ConnectionPool.getConnection(un,pw,alias);
          CallableStatement ps = c.prepareCall("{ call proc_pay_calc.procJob('00394') }");  // Тут вызов процедуру из пакета
          System.out.println("Rabotaet");
          ps.execute();
          System.out.println("OK");
          c.commit();
       } catch (SQLException e) {
             org.zkoss.zk.ui.util.Clients.alert(e.getMessage());
          com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

       } finally {
         ConnectionPool.close(c);
       }
     }

    public static void delete(Ok_personal ok_personal)  {
        Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection();
               ps = c.prepareStatement("DELETE FROM ok_personal WHERE id=?"); 
                ps.setString(1,ok_personal.getId());
                ps.executeUpdate();
                c.commit();
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
}
    
    public static List<RefData> getS_MfoAll(String alias) {
   	 return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf order by smf.bank_id", alias);
   	 
    }
    public static List<RefData> getSS_ok_status(String alias){
 		return Utils.getRefData("select ss_ok_status.Status_code code, ss_ok_status.Status_Name label from ss_ok_status order by ss_ok_status.status_code", alias);
 	}                            
    public static List<RefData> getSS_ok_gender(String alias){
 		return Utils.getRefData("select ss_ok_gender.Gender_code code, ss_ok_gender.Gender_Name label from ss_ok_gender order by ss_ok_gender.gender_code", alias);
 	}
    public static List<RefData> getS_region(String alias){
 		return Utils.getRefData("select s_region.Region_id code, s_region.Region_Nam label from s_region order by s_region.region_id", alias);
 	}
    public static List<RefData> getS_distr(String alias){
 		return Utils.getRefData("select s_distr.Distr code, s_distr.Distr_Name label from s_distr order by s_distr.distr", alias);
 	}
    public static List<RefData> getSS_ok_nationality(String alias){
 		return Utils.getRefData("select ss_ok_nationality.nationality_code code, ss_ok_nationality.nationality_Name label from ss_ok_nationality order by ss_ok_nationality.nationality_code", alias);
 	}
    public static List<RefData> getSS_ok_family_status(String alias){
 		return Utils.getRefData("select ss_ok_family_status.family_status_code code, ss_ok_family_status.family_status_name label from ss_ok_family_status order by ss_ok_family_status.family_status_code", alias);
 	}
    public static List<RefData> getSS_ok_reg_type(String alias){
 		return Utils.getRefData("select ss_ok_reg_type.reg_type_code code, ss_ok_reg_type.reg_type_name label from ss_ok_reg_type order by ss_ok_reg_type.reg_type_code", alias);
 	}
//    public static List<RefData> getUsers(String alias){
// 		return Utils.getRefData("чтото непонятное для просмотра", alias);
// 	}//спросить у Ильдара 

    public static ok_personalFilter find(ok_personalFilter ok_personal1)  {

        Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT * FROM ok_personal WHERE id=? or branch=? or personal_code=? or "
                		+ "colleague_code=? or status_code=? or salary_code=? or family=? or first_name=? or "
                		+ "patronymic=? or gender_code=? or birthday=? or region_id=? or distr=? or "
                		+ "nationality_code=? or family_status_code=? or reg_type_code=? or home_address=? or "
                		+ "home_addressfact=? or pass_seriya=? or pass_num=? or pass_date=? or pass_reg=? or "
                		+ "record_book_number=? or record_book_series=? or telefon=? or emp_code=? or ins_date=? or "
                		+ "profmember=? or tabno=? or education_title_code=? or birthplace=? or motive_out=? or "
                		+ "basis_num=? or basis_date=? or leave_code=? or department_code=? or post_code=? or "
                		+ "nn=? or motive_dismissial_code=? or nps_id=? or special_code=? or inn=? or "
                		+ "cod_distr_uvd=? or cod_distr_prim=? or cod_str_birth=? or cod_obl_prim=? or "
                		+ "cod_place_birth_prim=? or cod_sitizent=? or cod_str_sitizent=? or + cod_str_live=? or "
                		+ "cod_obl_live=? or cod_obl_live_prim=? or cod_distr_live=? or cod_distr_live_prim=? or "
                		+ "code_naci_prim=? or check_kfs=? or is_boss=? or bank_spec=? or notice=? or prich_id=? or "
                		+ "name_pr=? or srok_date=? or home_addressfact_region_id=? or home_addressfact_distr=? or "
                		+ "home_address_region_id=? or home_address_distr=? or passport_type_code=? or regplace_code=? or"
                		+ " pass_date_end=? or gosubmit_code=? or live_place=? or birthplace_point=? or maiden_family=? or "
                		+ "department_code_old=? or post_code_old=? or special_code_old=? or spec_ikki=? or "
                		+ "department_code_new=? or post_code_new=? or special_code_new=? or reg_date_end=? or trud_sogl=? or "
                		+ "application_date=? or reply_date=? or is_party=? or is_academic=? or is_degree=? or is_award=? or "
                		+ "is_scientific=? or is_voyage=? or is_convictions=? or is_language=? or is_rise=? or is_election=? or "
                		+ "is_premium=? or is_army=? or user_name=? or maidenstag=? or zp_rate_code=? or family_lat=? or "
                		+ "first_name_lat=? or patronymic_lat=? or comments=? or emp_code_name=? or tabel_line=? or id_client=? or "
                		+ "user_id=? or gph_date_begin=? or gph_date_end=? or gph_summa=? or gph_post_code=? or "
                		+ "gph_department_code=? or gph_order_date=? or gph_order_num=? or gph_rezident=? or email=? or "
                		+ "zp_rate_code_old=? or zp_rate_code_new=? or profession_personal=? or bxm=? or institution_code=? or institution_end_year=?");

                ps.setString(1,ok_personal1.getId());
                ps.setString(2,ok_personal1.getBranch());
                ps.setString(3,ok_personal1.getPersonal_code());
                ps.setString(4,ok_personal1.getColleague_code());
                ps.setString(5,ok_personal1.getStatus_code());
                ps.setString(6,ok_personal1.getSalary_code());
                ps.setString(7,ok_personal1.getFamily());
                ps.setString(8,ok_personal1.getFirst_name());
                ps.setString(9,ok_personal1.getPatronymic());
                ps.setString(10,ok_personal1.getGender_code());
                if (ok_personal1.getBirthday() != null) {
                    ps.setDate(11, new java.sql.Date(ok_personal1.getBirthday().getTime()));
                } else {
                    ps.setDate(11, null);  
                }
                ps.setString(12,ok_personal1.getRegion_id());
                ps.setString(13,ok_personal1.getDistr());
                ps.setString(14,ok_personal1.getNationality_code());
                ps.setString(15,ok_personal1.getFamily_status_code());
                ps.setString(16,ok_personal1.getReg_type_code());
                ps.setString(17,ok_personal1.getHome_address());
                ps.setString(18,ok_personal1.getHome_addressfact());
                ps.setString(19,ok_personal1.getPass_seriya());
                ps.setString(20,ok_personal1.getPass_num());
                if (ok_personal1.getPass_date() != null) {
                    ps.setDate(21, new java.sql.Date(ok_personal1.getPass_date().getTime()));
                } else {
                    ps.setDate(21, null);
                }
                ps.setString(22,ok_personal1.getPass_reg());
                ps.setString(23,ok_personal1.getRecord_book_number());
                ps.setString(24,ok_personal1.getRecord_book_series());
                ps.setString(25,ok_personal1.getTelefon());
                ps.setString(26,ok_personal1.getEmp_code());
                if (ok_personal1.getIns_date() != null) {
                    ps.setDate(27, new java.sql.Date(ok_personal1.getIns_date().getTime()));
                } else {
                    ps.setDate(27, null);
                }
                ps.setString(28,ok_personal1.getProfmember());
                ps.setString(29,ok_personal1.getTabno());
                ps.setString(30,ok_personal1.getEducation_title_code());
                ps.setString(31,ok_personal1.getBirthplace());
                ps.setString(32,ok_personal1.getMotive_out());
                ps.setString(33,ok_personal1.getBasis_num());
                if (ok_personal1.getBasis_date() != null) {
                    ps.setDate(34, new java.sql.Date(ok_personal1.getBasis_date().getTime()));
                } else {
                    ps.setDate(34, null);
                }
                ps.setString(35,ok_personal1.getLeave_code());
                ps.setString(36,ok_personal1.getDepartment_code());
                ps.setString(37,ok_personal1.getPost_code());
                ps.setString(38,ok_personal1.getNn());
                ps.setString(39,ok_personal1.getMotive_dismissial_code());
                ps.setString(40,ok_personal1.getNps_id());
                ps.setString(41,ok_personal1.getSpecial_code());
                ps.setString(42,ok_personal1.getInn());
                ps.setString(43,ok_personal1.getCod_distr_uvd());
                ps.setString(44,ok_personal1.getCod_distr_prim());
                ps.setString(45,ok_personal1.getCod_str_birth());
                ps.setString(46,ok_personal1.getCod_obl_prim());
                ps.setString(47,ok_personal1.getCod_place_birth_prim());
                ps.setString(48,ok_personal1.getCod_sitizent());
                ps.setString(49,ok_personal1.getCod_str_sitizent());
                ps.setString(50,ok_personal1.getCod_str_live());
                ps.setString(51,ok_personal1.getCod_obl_live());
                ps.setString(52,ok_personal1.getCod_obl_live_prim());
                ps.setString(53,ok_personal1.getCod_distr_live());
                ps.setString(54,ok_personal1.getCod_distr_live_prim());
                ps.setString(55,ok_personal1.getCode_naci_prim());
                ps.setString(56,ok_personal1.getCheck_kfs());
                ps.setString(57,ok_personal1.getIs_boss());
                ps.setString(58,ok_personal1.getBank_spec());
                ps.setString(59,ok_personal1.getNotice());
                ps.setString(60,ok_personal1.getPrich_id());
                ps.setString(61,ok_personal1.getName_pr());
                if (ok_personal1.getSrok_date() != null) {
                    ps.setDate(62, new java.sql.Date(ok_personal1.getSrok_date().getTime()));
                } else {
                    ps.setDate(62, null);
                }
                ps.setString(63,ok_personal1.getHome_addressfact_region_id());
                ps.setString(64,ok_personal1.getHome_addressfact_distr());
                ps.setString(65,ok_personal1.getHome_address_region_id());
                ps.setString(66,ok_personal1.getHome_address_distr());
                ps.setString(67,ok_personal1.getPassport_type_code());
                ps.setString(68,ok_personal1.getRegplace_code());
                if (ok_personal1.getPass_date_end() != null) {
                    ps.setDate(69, new java.sql.Date(ok_personal1.getPass_date_end().getTime()));
                } else {
                    ps.setDate(69, null);
                }
                ps.setString(70,ok_personal1.getGosubmit_code());
                ps.setString(71,ok_personal1.getLive_place());
                ps.setString(72,ok_personal1.getBirthplace_point());
                ps.setString(73,ok_personal1.getMaiden_family());
                ps.setString(74,ok_personal1.getDepartment_code_old());
                ps.setString(75,ok_personal1.getPost_code_old());
                ps.setString(76,ok_personal1.getSpecial_code_old());
                ps.setString(77,ok_personal1.getSpec_ikki());
                ps.setString(78,ok_personal1.getDepartment_code_new());
                ps.setString(79,ok_personal1.getPost_code_new());
                ps.setString(80,ok_personal1.getSpecial_code_new());
                if (ok_personal1.getReg_date_end() != null) {
                    ps.setDate(81, new java.sql.Date(ok_personal1.getReg_date_end().getTime()));
                } else {
                    ps.setDate(81, null);
                }
                ps.setString(82,ok_personal1.getTrud_sogl());
                if (ok_personal1.getApplication_date() != null) {
                    ps.setDate(83, new java.sql.Date(ok_personal1.getApplication_date().getTime()));
                } else {
                    ps.setDate(83, null);
                }
                if (ok_personal1.getReply_date() != null) {
                    ps.setDate(84, new java.sql.Date(ok_personal1.getReply_date().getTime()));
                } else {
                    ps.setDate(84, null);
                }
                ps.setString(85,ok_personal1.getIs_party());
                ps.setString(86,ok_personal1.getIs_academic());
                ps.setString(87,ok_personal1.getIs_degree());
                ps.setString(88,ok_personal1.getIs_award());
                ps.setString(89,ok_personal1.getIs_scientific());
                ps.setString(90,ok_personal1.getIs_voyage());
                ps.setString(91,ok_personal1.getIs_convictions());
                ps.setString(92,ok_personal1.getIs_language());
                ps.setString(93,ok_personal1.getIs_rise());
                ps.setString(94,ok_personal1.getIs_election());
                ps.setString(95,ok_personal1.getIs_premium());
                ps.setString(96,ok_personal1.getIs_army());
                ps.setString(97,ok_personal1.getUser_name());
                ps.setString(98,ok_personal1.getMaidenstag());
                ps.setString(99,ok_personal1.getZp_rate_code());
                ps.setString(100,ok_personal1.getFamily_lat());
                ps.setString(101,ok_personal1.getFirst_name_lat());
                ps.setString(102,ok_personal1.getPatronymic_lat());
                ps.setString(103,ok_personal1.getComments());
                ps.setString(104,ok_personal1.getEmp_code_name());
                ps.setString(105,ok_personal1.getTabel_line());
                ps.setString(106,ok_personal1.getId_client());
                ps.setString(107,ok_personal1.getUser_id());
                if (ok_personal1.getGph_date_begin() != null) {
                    ps.setDate(108, new java.sql.Date(ok_personal1.getGph_date_begin().getTime()));
                } else {
                    ps.setDate(108, null);
                }
                if (ok_personal1.getGph_date_end() != null) {
                    ps.setDate(109, new java.sql.Date(ok_personal1.getGph_date_end().getTime()));
                } else {
                    ps.setDate(109, null);
                }//
                ps.setString(110,ok_personal1.getGph_summa());
                ps.setString(111,ok_personal1.getGph_post_code());
                ps.setString(112,ok_personal1.getGph_department_code());
                if (ok_personal1.getGph_order_date() != null) {
                    ps.setDate(109, new java.sql.Date(ok_personal1.getGph_order_date().getTime()));
                } else {
                    ps.setDate(109, null);
                }
                ps.setString(114,ok_personal1.getGph_order_num());
                ps.setString(115,ok_personal1.getGph_rezident());
                ps.setString(116,ok_personal1.getEmail());
                ps.setString(117,ok_personal1.getZp_rate_code_old());
                ps.setString(118,ok_personal1.getZp_rate_code_new());
                ps.setString(119,ok_personal1.getProfession_personal());
                ps.setString(120,ok_personal1.getBxm());
                ps.setString(121,ok_personal1.getInstitution_code());
                ps.setString(122,ok_personal1.getInstitution_end_year());
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return ok_personal1;
}
    
}