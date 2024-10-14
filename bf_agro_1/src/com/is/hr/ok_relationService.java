package com.is.hr;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

public class ok_relationService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM ok_relation ";


    public List<ok_relation> getok_relation()  {

            List<ok_relation> list = new ArrayList<ok_relation>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM ok_relation");
                    while (rs.next()) {
                            list.add(new ok_relation(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("personal_code"),
                                            rs.getInt("relation_code"),
                                            rs.getString("relation_family"),
                                            rs.getString("relation_name"),
                                            rs.getString("relation_patronymic"),
                                            rs.getInt("relation_birthday"),
                                            rs.getString("relation_birthplace"),
                                            rs.getString("relation_office"),
                                            rs.getString("relation_post"),
                                            rs.getString("relation_home_address"),
                                            rs.getInt("emp_code"),
                                            rs.getDate("ins_date"),
                                            rs.getInt("relation_deathday"),
                                            rs.getString("cod_str_live"),
                                            rs.getString("cod_obl_live"),
                                            rs.getString("cod_obl_live_prim"),
                                            rs.getString("cod_city"),
                                            rs.getString("cod_city_prim"),
                                            rs.getString("cod_str_live_prim"),
                                            rs.getString("cod_str_birth"),
                                            rs.getString("cod_str_birth_prim"),
                                            rs.getString("cod_obl_birth"),
                                            rs.getString("cod_obl_birth_prim"),
                                            rs.getString("cod_city_birth"),
                                            rs.getString("cod_city_birth_prim"),
                                            rs.getInt("dd"),
                                            rs.getInt("mm"),
                                            rs.getInt("dd_death"),
                                            rs.getInt("mm_death"),
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

    private static List<FilterField> getFilterFields(ok_relationFilter filter){
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
          if(!CheckNull.isEmpty(filter.getRelation_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_code=?",filter.getRelation_code()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_family())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_family=?",filter.getRelation_family()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_name=?",filter.getRelation_name()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_patronymic())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_patronymic=?",filter.getRelation_patronymic()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_birthday())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_birthday=?",filter.getRelation_birthday()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_birthplace())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_birthplace=?",filter.getRelation_birthplace()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_office())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_office=?",filter.getRelation_office()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_post())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_post=?",filter.getRelation_post()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_home_address())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_home_address=?",filter.getRelation_home_address()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code=?",filter.getEmp_code()));
          }
          if(!CheckNull.isEmpty(filter.getIns_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "ins_date=?",filter.getIns_date()));
          }
          if(!CheckNull.isEmpty(filter.getRelation_deathday())){
                  flfields.add(new FilterField(getCond(flfields)+ "relation_deathday=?",filter.getRelation_deathday()));
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
          if(!CheckNull.isEmpty(filter.getCod_city())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_city=?",filter.getCod_city()));
          }
          if(!CheckNull.isEmpty(filter.getCod_city_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_city_prim=?",filter.getCod_city_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_live_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_live_prim=?",filter.getCod_str_live_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_birth())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_birth=?",filter.getCod_str_birth()));
          }
          if(!CheckNull.isEmpty(filter.getCod_str_birth_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_str_birth_prim=?",filter.getCod_str_birth_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_obl_birth())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_obl_birth=?",filter.getCod_obl_birth()));
          }
          if(!CheckNull.isEmpty(filter.getCod_obl_birth_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_obl_birth_prim=?",filter.getCod_obl_birth_prim()));
          }
          if(!CheckNull.isEmpty(filter.getCod_city_birth())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_city_birth=?",filter.getCod_city_birth()));
          }
          if(!CheckNull.isEmpty(filter.getCod_city_birth_prim())){
                  flfields.add(new FilterField(getCond(flfields)+ "cod_city_birth_prim=?",filter.getCod_city_birth_prim()));
          }
          if(!CheckNull.isEmpty(filter.getDd())){
                  flfields.add(new FilterField(getCond(flfields)+ "dd=?",filter.getDd()));
          }
          if(!CheckNull.isEmpty(filter.getMm())){
                  flfields.add(new FilterField(getCond(flfields)+ "mm=?",filter.getMm()));
          }
          if(!CheckNull.isEmpty(filter.getDd_death())){
                  flfields.add(new FilterField(getCond(flfields)+ "dd_death=?",filter.getDd_death()));
          }
          if(!CheckNull.isEmpty(filter.getMm_death())){
                  flfields.add(new FilterField(getCond(flfields)+ "mm_death=?",filter.getMm_death()));
          }
          if(!CheckNull.isEmpty(filter.getEmp_code_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "emp_code_name=?",filter.getEmp_code_name()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ok_relationFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM ok_relation ");
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



    public static List<ok_relation> getok_relationsFl(int pageIndex, int pageSize, ok_relationFilter filter)  {

            List<ok_relation> list = new ArrayList<ok_relation>();
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
                            list.add(new ok_relation(
                                            rs.getInt("id"),
                                            rs.getString("branch"),
                                            rs.getInt("personal_code"),
                                            rs.getInt("relation_code"),
                                            rs.getString("relation_family"),
                                            rs.getString("relation_name"),
                                            rs.getString("relation_patronymic"),
                                            rs.getInt("relation_birthday"),
                                            rs.getString("relation_birthplace"),
                                            rs.getString("relation_office"),
                                            rs.getString("relation_post"),
                                            rs.getString("relation_home_address"),
                                            rs.getInt("emp_code"),
                                            rs.getDate("ins_date"),
                                            rs.getInt("relation_deathday"),
                                            rs.getString("cod_str_live"),
                                            rs.getString("cod_obl_live"),
                                            rs.getString("cod_obl_live_prim"),
                                            rs.getString("cod_city"),
                                            rs.getString("cod_city_prim"),
                                            rs.getString("cod_str_live_prim"),
                                            rs.getString("cod_str_birth"),
                                            rs.getString("cod_str_birth_prim"),
                                            rs.getString("cod_obl_birth"),
                                            rs.getString("cod_obl_birth_prim"),
                                            rs.getString("cod_city_birth"),
                                            rs.getString("cod_city_birth_prim"),
                                            rs.getInt("dd"),
                                            rs.getInt("mm"),
                                            rs.getInt("dd_death"),
                                            rs.getInt("mm_death"),
                                            rs.getString("emp_code_name")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public ok_relation getok_relation(int ok_relationId) {

            ok_relation ok_relation = new ok_relation();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_relation WHERE id=?");
                    ps.setInt(1, ok_relationId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_relation = new ok_relation();
                            
                            ok_relation.setId(rs.getInt("id"));
                            ok_relation.setBranch(rs.getString("branch"));
                            ok_relation.setPersonal_code(rs.getInt("personal_code"));
                            ok_relation.setRelation_code(rs.getInt("relation_code"));
                            ok_relation.setRelation_family(rs.getString("relation_family"));
                            ok_relation.setRelation_name(rs.getString("relation_name"));
                            ok_relation.setRelation_patronymic(rs.getString("relation_patronymic"));
                            ok_relation.setRelation_birthday(rs.getInt("relation_birthday"));
                            ok_relation.setRelation_birthplace(rs.getString("relation_birthplace"));
                            ok_relation.setRelation_office(rs.getString("relation_office"));
                            ok_relation.setRelation_post(rs.getString("relation_post"));
                            ok_relation.setRelation_home_address(rs.getString("relation_home_address"));
                            ok_relation.setEmp_code(rs.getInt("emp_code"));
                            ok_relation.setIns_date(rs.getDate("ins_date"));
                            ok_relation.setRelation_deathday(rs.getInt("relation_deathday"));
                            ok_relation.setCod_str_live(rs.getString("cod_str_live"));
                            ok_relation.setCod_obl_live(rs.getString("cod_obl_live"));
                            ok_relation.setCod_obl_live_prim(rs.getString("cod_obl_live_prim"));
                            ok_relation.setCod_city(rs.getString("cod_city"));
                            ok_relation.setCod_city_prim(rs.getString("cod_city_prim"));
                            ok_relation.setCod_str_live_prim(rs.getString("cod_str_live_prim"));
                            ok_relation.setCod_str_birth(rs.getString("cod_str_birth"));
                            ok_relation.setCod_str_birth_prim(rs.getString("cod_str_birth_prim"));
                            ok_relation.setCod_obl_birth(rs.getString("cod_obl_birth"));
                            ok_relation.setCod_obl_birth_prim(rs.getString("cod_obl_birth_prim"));
                            ok_relation.setCod_city_birth(rs.getString("cod_city_birth"));
                            ok_relation.setCod_city_birth_prim(rs.getString("cod_city_birth_prim"));
                            ok_relation.setDd(rs.getInt("dd"));
                            ok_relation.setMm(rs.getInt("mm"));
                            ok_relation.setDd_death(rs.getInt("dd_death"));
                            ok_relation.setMm_death(rs.getInt("mm_death"));
                            ok_relation.setEmp_code_name(rs.getString("emp_code_name"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return ok_relation;
    }
    

    public static List<RefData> getSS_ok_relation(String alias){
 		return Utils.getRefData("select ss_ok_relation.Relation_Code code, ss_ok_relation.Relation_Code||' '||ss_ok_relation.Relation_Name label from ss_ok_relation order by relation_code", alias);
 	}
    public static List<RefData> getS_str(String alias){
 		return Utils.getRefData("select s_str.Code_Str code, s_str.Code_Str||' '||s_str.Name label from s_str order by code", alias);
     }
    public static List<RefData> getS_distr(String alias){
    	return Utils.getRefData("select s_distr.Distr code, s_distr.Distr||' '||s_distr.Distr_name label from s_distr order by distr",alias);
    }
    public static List<RefData> getS_region(String alias){
    	return Utils.getRefData("select s_region.Region_id code, s_region.Region_id||' '||s_region.Region_nam label from s_region order by region_id",alias);
    }
    
    public static ok_relation create(ok_relation ok_relation)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
            	
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SEQ_ok_relation.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            ok_relation.setId(rs.getInt("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO ok_relation (id, branch, personal_code, relation_code, relation_family, relation_name, relation_patronymic, relation_birthday, relation_birthplace, relation_office, relation_post, relation_home_address, emp_code, ins_date, relation_deathday, cod_str_live, cod_obl_live, cod_obl_live_prim, cod_city, cod_city_prim, cod_str_live_prim, cod_str_birth, cod_str_birth_prim, cod_obl_birth, cod_obl_birth_prim, cod_city_birth, cod_city_birth_prim, dd, mm, dd_death, mm_death, emp_code_name ) "
                    		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
//                    java.sql.Date date = new java.sql.Date(ok_relation.getIns_date().getTime());
                    System.out.println("ID "+ok_relation.getId());
                    System.out.println("Branch "+ok_relation.getBranch());
                    System.out.println("CODE "+ok_relation.getPersonal_code());
                    System.out.println("RELATION CODE "+ok_relation.getRelation_code());
                    System.out.println("DATE "+ok_relation.getIns_date().getTime());
                    
                    
                    
                    ps.setInt(1,ok_relation.getId());
                    ps.setString(2,ok_relation.getBranch());
                    ps.setInt(3,ok_relation.getPersonal_code());
                    ps.setInt(4,ok_relation.getRelation_code());
                    ps.setString(5,ok_relation.getRelation_family());
                    ps.setString(6,ok_relation.getRelation_name());
                    ps.setString(7,ok_relation.getRelation_patronymic());
                    ps.setInt(8,ok_relation.getRelation_birthday());
                    ps.setString(9,ok_relation.getRelation_birthplace());
                    ps.setString(10,ok_relation.getRelation_office());
                    ps.setString(11,ok_relation.getRelation_post());
                    ps.setString(12,ok_relation.getRelation_home_address());
                    ps.setInt(13,ok_relation.getEmp_code());
                    ps.setTimestamp(14, new java.sql.Timestamp(ok_relation.getIns_date().getTime()));
                    if(ok_relation.getRelation_deathday() ==0 ) {
                    	ps.setNull(15,java.sql.Types.INTEGER);
                    }
                    else {
                    	ps.setInt(15,ok_relation.getRelation_deathday());
                    }
                    ps.setString(16,ok_relation.getCod_str_live());
                    ps.setString(17,ok_relation.getCod_obl_live());
                    ps.setString(18,ok_relation.getCod_obl_live_prim());
                    ps.setString(19,ok_relation.getCod_city());
                    ps.setString(20,ok_relation.getCod_city_prim());
                    ps.setString(21,ok_relation.getCod_str_live_prim());
                    ps.setString(22,ok_relation.getCod_str_birth());
                    ps.setString(23,ok_relation.getCod_str_birth_prim());
                    ps.setString(24,ok_relation.getCod_obl_birth());
                    ps.setString(25,ok_relation.getCod_obl_birth_prim());
                    ps.setString(26,ok_relation.getCod_city_birth());
                    ps.setString(27,ok_relation.getCod_city_birth_prim());
                    ps.setInt(28,ok_relation.getDd());
                    ps.setInt(29,ok_relation.getMm());
                    if(ok_relation.getDd_death() ==0 ) {
                    	ps.setNull(30,java.sql.Types.INTEGER);
                    }
                    else {
                    	ps.setInt(30,ok_relation.getDd_death());
                    }
                    if(ok_relation.getMm_death() ==0 ) {
                    	ps.setNull(31,java.sql.Types.INTEGER);
                    }
                    else {
                    	ps.setInt(31,ok_relation.getMm_death());
                    }
                    
                    
                    ps.setString(32,ok_relation.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return ok_relation;
    }

    public static void update(ok_relation ok_relation)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE ok_relation SET id=?, branch=?, personal_code=?, relation_code=?, relation_family=?, relation_name=?, relation_patronymic=?, relation_birthday=?, relation_birthplace=?, relation_office=?, relation_post=?, relation_home_address=?, emp_code=?, ins_date=?, relation_deathday=?, cod_str_live=?, cod_obl_live=?, cod_obl_live_prim=?, cod_city=?, cod_city_prim=?, cod_str_live_prim=?, cod_str_birth=?, cod_str_birth_prim=?, cod_obl_birth=?, cod_obl_birth_prim=?, cod_city_birth=?, cod_city_birth_prim=?, dd=?, mm=?, dd_death=?, mm_death=?, emp_code_name=?,  WHERE id=?");
                    
                    ps.setInt(1,ok_relation.getId());
                    ps.setString(2,ok_relation.getBranch());
                    ps.setInt(3,ok_relation.getPersonal_code());
                    ps.setInt(4,ok_relation.getRelation_code());
                    ps.setString(5,ok_relation.getRelation_family());
                    ps.setString(6,ok_relation.getRelation_name());
                    ps.setString(7,ok_relation.getRelation_patronymic());
                    ps.setInt(8,ok_relation.getRelation_birthday());
                    ps.setString(9,ok_relation.getRelation_birthplace());
                    ps.setString(10,ok_relation.getRelation_office());
                    ps.setString(11,ok_relation.getRelation_post());
                    ps.setString(12,ok_relation.getRelation_home_address());
                    ps.setInt(13,ok_relation.getEmp_code());
                    ps.setDate(14,new java.sql.Date(ok_relation.getIns_date().getTime()));
                    ps.setInt(15,ok_relation.getRelation_deathday());
                    ps.setString(16,ok_relation.getCod_str_live());
                    ps.setString(17,ok_relation.getCod_obl_live());
                    ps.setString(18,ok_relation.getCod_obl_live_prim());
                    ps.setString(19,ok_relation.getCod_city());
                    ps.setString(20,ok_relation.getCod_city_prim());
                    ps.setString(21,ok_relation.getCod_str_live_prim());
                    ps.setString(22,ok_relation.getCod_str_birth());
                    ps.setString(23,ok_relation.getCod_str_birth_prim());
                    ps.setString(24,ok_relation.getCod_obl_birth());
                    ps.setString(25,ok_relation.getCod_obl_birth_prim());
                    ps.setString(26,ok_relation.getCod_city_birth());
                    ps.setString(27,ok_relation.getCod_city_birth_prim());
                    ps.setInt(28,ok_relation.getDd());
                    ps.setInt(29,ok_relation.getMm());
                    ps.setInt(30,ok_relation.getDd_death());
                    ps.setInt(31,ok_relation.getMm_death());
                    ps.setString(32,ok_relation.getEmp_code_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(ok_relation ok_relation)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM ok_relation WHERE id=?");
                    ps.setInt(1, ok_relation.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

}
