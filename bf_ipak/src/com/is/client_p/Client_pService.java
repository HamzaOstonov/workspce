package com.is.client_p;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class Client_pService  {
	
	//private static String SAP_SCHEMA = ConnectionPool.getValue("SAP_SCHEMA");
	
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM v_client_p ";
  
	private static Logger logger = Logger.getLogger(Client_pService.class);

	private static final int DEAL_ID = 1;
	public static final int ACTION_OPEN = 1;
	public static final int ACTION_CONFIRM = 2; 
	public static final int ACTION_CHANGE_NIBBD = 4; 

	public static List<RefData> getStateList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select distinct id data, name label from STATE_CLIENT", alias);
    }
    
    public static List<RefData> getCodeCitizenshipList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select code_str data, name label from s_str where act <> 'Z' order by code_str", alias);
    }

    public static List<RefData> getCodeResidentList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select kod_rez data, type_rez label from S_REZKL order by 1", alias);
    }

    public static List<RefData> getTypeDocumentList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select code_cert data, name_cert label from s_certificate where act <> 'Z' order by code_cert", alias);
    }

    public static List<RefData> getCodeNationList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id", alias);
    }

    public static List<RefData> getCodeGenderList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by 1", alias);
    }

    public static List<RefData> getCodeAdrRegionList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select region_id data, region_nam label from s_region where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeAdrDistrList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select distr data, distr_name label from s_distr where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeCapacityList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select kod_kr data, name_kr label from s_krfl where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeBankList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select bank_id data, bank_name label from s_mfo where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeClassCreditList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select klass_id data, klass_name label from s_klass where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeTaxOrgList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select gni_id data, name_gni label from s_gni where act <> 'Z' order by 1", alias);
    }

    public static List<RefData> getCodeTypeList(String alias) {
    	return com.is.utils.RefDataService.getRefData("select kod_k data, name_k2 label from s_typekl where kod_k <> '00' and act <> 'Z' order by data", alias);
    }

    
    public List<Client_p> getclient_p()  {

            List<Client_p> list = new ArrayList<Client_p>();
            Connection c = null;

            try {
            	  
            	//c = ConnectionPool.getConnection(un,pw,alias);
            	    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM v_client_p");
                    while (rs.next()) {
                            list.add(new Client_p(
                                                rs.getString("id"),
                                                rs.getString("branch"),
                                                rs.getString("id_client"),
                                                rs.getString("name"),
                                                rs.getString("code_country"),
                                                rs.getString("code_type"),
                                                rs.getString("code_resident"),
                                                rs.getString("code_subject"),
                                                rs.getString("sign_registr"),
                                                rs.getString("code_form"),
                                                rs.getDate("date_open"),
                                                rs.getDate("date_close"),
                                                rs.getString("state"),
                                                rs.getString("kod_err"),
                                                rs.getString("file_name"),
                                                rs.getDate("p_birthday"),
                                                rs.getString("p_post_address"),
                                                rs.getString("p_passport_type"),
                                                rs.getString("p_passport_serial"),
                                                rs.getString("p_passport_number"),
                                                rs.getString("p_passport_place_registration"),
                                                rs.getDate("p_passport_date_registration"),
                                                rs.getString("p_code_tax_org"),
                                                rs.getString("p_number_tax_registration"),
                                                rs.getString("p_code_bank"),
                                                rs.getString("p_code_class_credit"),
                                                rs.getString("p_code_citizenship"),
                                                rs.getString("p_birth_place"),
                                                rs.getString("p_code_capacity"),
                                                rs.getDate("p_capacity_status_date"),
                                                rs.getString("p_capacity_status_place"),
                                                rs.getString("p_num_certif_capacity"),
                                                rs.getString("p_phone_home"),
                                                rs.getString("p_phone_mobile"),
                                                rs.getString("p_email_address"),
                                                rs.getString("p_pension_sertif_serial"),
                                                rs.getString("p_code_gender"),
                                                rs.getString("p_code_nation"),
                                                rs.getString("p_code_birth_region"),
                                                rs.getString("p_code_birth_distr"),
                                                rs.getString("p_type_document"),
                                                rs.getDate("p_passport_date_expiration"),
                                                rs.getString("p_code_adr_region"),
                                                rs.getString("p_code_adr_distr"),
                                                rs.getString("p_inps"),
                                                rs.getString("p_family"),
                                                rs.getString("p_first_name"),
                                                rs.getString("p_patronymic"),
                                                rs.getString("p_sign_vip") ) );
                            		
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

    private static List<FilterField> getFilterFields(Client_pFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getBranch())){
                      flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
              }
              if(!CheckNull.isEmpty(filter.getId_client())){
                      flfields.add(new FilterField(getCond(flfields)+ "id_client=?",filter.getId_client()));
              }
              if(!CheckNull.isEmpty(filter.getName())){
                      flfields.add(new FilterField(getCond(flfields)+ "name=?",filter.getName()));
              }
              if(!CheckNull.isEmpty(filter.getCode_country())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_country=?",filter.getCode_country()));
              }
              if(!CheckNull.isEmpty(filter.getCode_type())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_type=?",filter.getCode_type()));
              }
              if(!CheckNull.isEmpty(filter.getCode_resident())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_resident=?",filter.getCode_resident()));
              }
              if(!CheckNull.isEmpty(filter.getCode_subject())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_subject=?",filter.getCode_subject()));
              }
              if(!CheckNull.isEmpty(filter.getSign_registr())){
                      flfields.add(new FilterField(getCond(flfields)+ "sign_registr=?",filter.getSign_registr()));
              }
              if(!CheckNull.isEmpty(filter.getCode_form())){
                      flfields.add(new FilterField(getCond(flfields)+ "code_form=?",filter.getCode_form()));
              }
              if(!CheckNull.isEmpty(filter.getDate_open())){
                      flfields.add(new FilterField(getCond(flfields)+ "date_open=?",filter.getDate_open()));
              }
              if(!CheckNull.isEmpty(filter.getDate_close())){
                      flfields.add(new FilterField(getCond(flfields)+ "date_close=?",filter.getDate_close()));
              }
              if(!CheckNull.isEmpty(filter.getState())){
                      flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
              }
              if(!CheckNull.isEmpty(filter.getKod_err())){
                      flfields.add(new FilterField(getCond(flfields)+ "kod_err=?",filter.getKod_err()));
              }
              if(!CheckNull.isEmpty(filter.getFile_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "file_name=?",filter.getFile_name()));
              }
              if(!CheckNull.isEmpty(filter.getP_birthday())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_birthday=?",filter.getP_birthday()));
              }
              if(!CheckNull.isEmpty(filter.getP_post_address())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_post_address=?",filter.getP_post_address()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_type())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_type=?",filter.getP_passport_type()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_serial())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_serial=?",filter.getP_passport_serial()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_number())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_number=?",filter.getP_passport_number()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_place_registration())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_place_registration=?",filter.getP_passport_place_registration()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_date_registration())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_date_registration=?",filter.getP_passport_date_registration()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_tax_org())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_tax_org=?",filter.getP_code_tax_org()));
              }
              if(!CheckNull.isEmpty(filter.getP_number_tax_registration())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_number_tax_registration=?",filter.getP_number_tax_registration()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_bank())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_bank=?",filter.getP_code_bank()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_class_credit())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_class_credit=?",filter.getP_code_class_credit()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_citizenship())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_citizenship=?",filter.getP_code_citizenship()));
              }
              if(!CheckNull.isEmpty(filter.getP_birth_place())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_birth_place=?",filter.getP_birth_place()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_capacity())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_capacity=?",filter.getP_code_capacity()));
              }
              if(!CheckNull.isEmpty(filter.getP_capacity_status_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_capacity_status_date=?",filter.getP_capacity_status_date()));
              }
              if(!CheckNull.isEmpty(filter.getP_capacity_status_place())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_capacity_status_place=?",filter.getP_capacity_status_place()));
              }
              if(!CheckNull.isEmpty(filter.getP_num_certif_capacity())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_num_certif_capacity=?",filter.getP_num_certif_capacity()));
              }
              if(!CheckNull.isEmpty(filter.getP_phone_home())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_phone_home=?",filter.getP_phone_home()));
              }
              if(!CheckNull.isEmpty(filter.getP_phone_mobile())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_phone_mobile=?",filter.getP_phone_mobile()));
              }
              if(!CheckNull.isEmpty(filter.getP_email_address())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_email_address=?",filter.getP_email_address()));
              }
              if(!CheckNull.isEmpty(filter.getP_pension_sertif_serial())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_pension_sertif_serial=?",filter.getP_pension_sertif_serial()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_gender())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_gender=?",filter.getP_code_gender()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_nation())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_nation=?",filter.getP_code_nation()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_birth_region())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_birth_region=?",filter.getP_code_birth_region()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_birth_distr())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_birth_distr=?",filter.getP_code_birth_distr()));
              }
              if(!CheckNull.isEmpty(filter.getP_type_document())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_type_document=?",filter.getP_type_document()));
              }
              if(!CheckNull.isEmpty(filter.getP_passport_date_expiration())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_passport_date_expiration=?",filter.getP_passport_date_expiration()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_adr_region())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_adr_region=?",filter.getP_code_adr_region()));
              }
              if(!CheckNull.isEmpty(filter.getP_code_adr_distr())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_code_adr_distr=?",filter.getP_code_adr_distr()));
              }
              if(!CheckNull.isEmpty(filter.getP_inps())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_inps=?",filter.getP_inps()));
              }
              if(!CheckNull.isEmpty(filter.getP_family())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_family=?",filter.getP_family()));
              }
              if(!CheckNull.isEmpty(filter.getP_first_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_first_name=?",filter.getP_first_name()));
              }
              if(!CheckNull.isEmpty(filter.getP_patronymic())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_patronymic=?",filter.getP_patronymic()));
              }
              if(!CheckNull.isEmpty(filter.getP_sign_vip())){
                      flfields.add(new FilterField(getCond(flfields)+ "p_sign_vip=?",filter.getP_sign_vip()));
              }



          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(Client_pFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM v_client_p ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                //un = (String) session.getAttribute("un");
        	    //pwd= (String) session.getAttribute("pwd");
        	    //alias = (String) session.getAttribute("alias");
        	    //c = ConnectionPool.getConnection(un, pwd, alias);
        	//c = ConnectionPool.getConnection(alias);
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



    public static List<Client_p> getclient_psFl(int pageIndex, int pageSize, Client_pFilter filter)  {

            List<Client_p> list = new ArrayList<Client_p>();
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
                    //c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                                 list.add(new Client_p(
                                                rs.getString("id"),
                                                rs.getString("branch"),
                                                rs.getString("id_client"),
                                                rs.getString("name"),
                                                rs.getString("code_country"),
                                                rs.getString("code_type"),
                                                rs.getString("code_resident"),
                                                rs.getString("code_subject"),
                                                rs.getString("sign_registr"),
                                                rs.getString("code_form"),
                                                rs.getDate("date_open"),
                                                rs.getDate("date_close"),
                                                rs.getString("state"),
                                                rs.getString("kod_err"),
                                                rs.getString("file_name"),
                                                rs.getDate("p_birthday"),
                                                rs.getString("p_post_address"),
                                                rs.getString("p_passport_type"),
                                                rs.getString("p_passport_serial"),
                                                rs.getString("p_passport_number"),
                                                rs.getString("p_passport_place_registration"),
                                                rs.getDate("p_passport_date_registration"),
                                                rs.getString("p_code_tax_org"),
                                                rs.getString("p_number_tax_registration"),
                                                rs.getString("p_code_bank"),
                                                rs.getString("p_code_class_credit"),
                                                rs.getString("p_code_citizenship"),
                                                rs.getString("p_birth_place"),
                                                rs.getString("p_code_capacity"),
                                                rs.getDate("p_capacity_status_date"),
                                                rs.getString("p_capacity_status_place"),
                                                rs.getString("p_num_certif_capacity"),
                                                rs.getString("p_phone_home"),
                                                rs.getString("p_phone_mobile"),
                                                rs.getString("p_email_address"),
                                                rs.getString("p_pension_sertif_serial"),
                                                rs.getString("p_code_gender"),
                                                rs.getString("p_code_nation"),
                                                rs.getString("p_code_birth_region"),
                                                rs.getString("p_code_birth_distr"),
                                                rs.getString("p_type_document"),
                                                rs.getDate("p_passport_date_expiration"),
                                                rs.getString("p_code_adr_region"),
                                                rs.getString("p_code_adr_distr"),
                                                rs.getString("p_inps"),
                                                rs.getString("p_family"),
                                                rs.getString("p_first_name"),
                                                rs.getString("p_patronymic"),
                                                rs.getString("p_sign_vip")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public static Client_p getclient_p(int client_pId) {

            Client_p client_p = new Client_p();
            Connection c = null;

            try {
                   // c = ConnectionPool.getConnection();
                    c = ConnectionPool.getConnection("admin","admin","bank394");
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM v_client_p WHERE id=?");
                    ps.setInt(1, client_pId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_p = new Client_p();
                            
                            client_p.setId(rs.getString("id"));
                                client_p.setBranch(rs.getString("branch"));
                                client_p.setId_client(rs.getString("id_client"));
                                client_p.setName(rs.getString("name"));
                                client_p.setCode_country(rs.getString("code_country"));
                                client_p.setCode_type(rs.getString("code_type"));
                                client_p.setCode_resident(rs.getString("code_resident"));
                                client_p.setCode_subject(rs.getString("code_subject"));
                                client_p.setSign_registr(rs.getString("sign_registr"));
                                client_p.setCode_form(rs.getString("code_form"));
                                client_p.setDate_open(rs.getDate("date_open"));
                                client_p.setDate_close(rs.getDate("date_close"));
                                client_p.setState(rs.getString("state"));
                                client_p.setKod_err(rs.getString("kod_err"));
                                client_p.setFile_name(rs.getString("file_name"));
                                client_p.setP_birthday(rs.getDate("p_birthday"));
                                client_p.setP_post_address(rs.getString("p_post_address"));
                                client_p.setP_passport_type(rs.getString("p_passport_type"));
                                client_p.setP_passport_serial(rs.getString("p_passport_serial"));
                                client_p.setP_passport_number(rs.getString("p_passport_number"));
                                client_p.setP_passport_place_registration(rs.getString("p_passport_place_registration"));
                                client_p.setP_passport_date_registration(rs.getDate("p_passport_date_registration"));
                                client_p.setP_code_tax_org(rs.getString("p_code_tax_org"));
                                client_p.setP_number_tax_registration(rs.getString("p_number_tax_registration"));
                                client_p.setP_code_bank(rs.getString("p_code_bank"));
                                client_p.setP_code_class_credit(rs.getString("p_code_class_credit"));
                                client_p.setP_code_citizenship(rs.getString("p_code_citizenship"));
                                client_p.setP_birth_place(rs.getString("p_birth_place"));
                                client_p.setP_code_capacity(rs.getString("p_code_capacity"));
                                client_p.setP_capacity_status_date(rs.getDate("p_capacity_status_date"));
                                client_p.setP_capacity_status_place(rs.getString("p_capacity_status_place"));
                                client_p.setP_num_certif_capacity(rs.getString("p_num_certif_capacity"));
                                client_p.setP_phone_home(rs.getString("p_phone_home"));
                                client_p.setP_phone_mobile(rs.getString("p_phone_mobile"));
                                client_p.setP_email_address(rs.getString("p_email_address"));
                                client_p.setP_pension_sertif_serial(rs.getString("p_pension_sertif_serial"));
                                client_p.setP_code_gender(rs.getString("p_code_gender"));
                                client_p.setP_code_nation(rs.getString("p_code_nation"));
                                client_p.setP_code_birth_region(rs.getString("p_code_birth_region"));
                                client_p.setP_code_birth_distr(rs.getString("p_code_birth_distr"));
                                client_p.setP_type_document(rs.getString("p_type_document"));
                                client_p.setP_passport_date_expiration(rs.getDate("p_passport_date_expiration"));
                                client_p.setP_code_adr_region(rs.getString("p_code_adr_region"));
                                client_p.setP_code_adr_distr(rs.getString("p_code_adr_distr"));
                                client_p.setP_inps(rs.getString("p_inps"));
                                client_p.setP_family(rs.getString("p_family"));
                                client_p.setP_first_name(rs.getString("p_first_name"));
                                client_p.setP_patronymic(rs.getString("p_patronymic"));
                                client_p.setP_sign_vip(rs.getString("p_sign_vip"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return client_p;
    }

    public static Client_p create(Client_p client_p)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_client_p.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_p.setId(String.valueOf( rs.getLong("id")));
                    }
                    ps = c.prepareStatement("INSERT INTO client_p (branch, id, name, birthday, post_address, passport_type, passport_serial, passport_number, passport_place_registration, passport_date_registration, code_place_regist, date_registration, number_registration_doc, code_tax_org, number_tax_registration, code_sector, code_organ_direct, code_bank, account, code_class_credit, state, kod_err, file_name, code_citizenship, birth_place, code_capacity, capacity_status_date, capacity_status_place, num_certif_capacity, phone_home, phone_mobile, email_address, pension_sertif_serial, code_gender, code_nation, code_birth_region, code_birth_distr, type_document, passport_date_expiration, code_adr_region, code_adr_distr, inps, family, first_name, patronymic, sign_vip, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,client_p.getId());
                        ps.setString(2,client_p.getBranch());
                        ps.setString(3,client_p.getId_client());
                        ps.setString(4,client_p.getName());
                        ps.setString(5,client_p.getCode_country());
                        ps.setString(6,client_p.getCode_type());
                        ps.setString(7,client_p.getCode_resident());
                        ps.setString(8,client_p.getCode_subject());
                        ps.setString(9,client_p.getSign_registr());
                        ps.setString(10,client_p.getCode_form());
                        ps.setDate(11, new java.sql.Date(client_p.getDate_open().getTime()));
                        ps.setDate(12, new java.sql.Date(client_p.getDate_close().getTime()));
                        ps.setString(13,client_p.getState());
                        ps.setString(14,client_p.getKod_err());
                        ps.setString(15,client_p.getFile_name());
                        ps.setDate(16, new java.sql.Date(client_p.getP_birthday().getTime()));
                        ps.setString(17,client_p.getP_post_address());
                        ps.setString(18,client_p.getP_passport_type());
                        ps.setString(19,client_p.getP_passport_serial());
                        ps.setString(20,client_p.getP_passport_number());
                        ps.setString(21,client_p.getP_passport_place_registration());
                        ps.setDate(22, new java.sql.Date(client_p.getP_passport_date_registration().getTime()));
                        ps.setString(23,client_p.getP_code_tax_org());
                        ps.setString(24,client_p.getP_number_tax_registration());
                        ps.setString(25,client_p.getP_code_bank());
                        ps.setString(26,client_p.getP_code_class_credit());
                        ps.setString(27,client_p.getP_code_citizenship());
                        ps.setString(28,client_p.getP_birth_place());
                        ps.setString(29,client_p.getP_code_capacity());
                        ps.setDate(30, new java.sql.Date(client_p.getP_capacity_status_date().getTime()));
                        ps.setString(31,client_p.getP_capacity_status_place());
                        ps.setString(32,client_p.getP_num_certif_capacity());
                        ps.setString(33,client_p.getP_phone_home());
                        ps.setString(34,client_p.getP_phone_mobile());
                        ps.setString(35,client_p.getP_email_address());
                        ps.setString(36,client_p.getP_pension_sertif_serial());
                        ps.setString(37,client_p.getP_code_gender());
                        ps.setString(38,client_p.getP_code_nation());
                        ps.setString(39,client_p.getP_code_birth_region());
                        ps.setString(40,client_p.getP_code_birth_distr());
                        ps.setString(41,client_p.getP_type_document());
                        ps.setDate(42, new java.sql.Date(client_p.getP_passport_date_expiration().getTime()));
                        ps.setString(43,client_p.getP_code_adr_region());
                        ps.setString(44,client_p.getP_code_adr_distr());
                        ps.setString(45,client_p.getP_inps());
                        ps.setString(46,client_p.getP_family());
                        ps.setString(47,client_p.getP_first_name());
                        ps.setString(48,client_p.getP_patronymic());
                        ps.setString(49,client_p.getP_sign_vip());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return client_p;
    }

    public static void update(Client_p client_p)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE client_p SET branch=?, id=?, name=?, birthday=?, post_address=?, passport_type=?, passport_serial=?, passport_number=?, passport_place_registration=?, passport_date_registration=?, code_place_regist=?, date_registration=?, number_registration_doc=?, code_tax_org=?, number_tax_registration=?, code_sector=?, code_organ_direct=?, code_bank=?, account=?, code_class_credit=?, state=?, kod_err=?, file_name=?, code_citizenship=?, birth_place=?, code_capacity=?, capacity_status_date=?, capacity_status_place=?, num_certif_capacity=?, phone_home=?, phone_mobile=?, email_address=?, pension_sertif_serial=?, code_gender=?, code_nation=?, code_birth_region=?, code_birth_distr=?, type_document=?, passport_date_expiration=?, code_adr_region=?, code_adr_distr=?, inps=?, family=?, first_name=?, patronymic=?, sign_vip=?,  WHERE id=?");
                    
                    ps.setString(1,client_p.getId());
                        ps.setString(2,client_p.getBranch());
                        ps.setString(3,client_p.getId_client());
                        ps.setString(4,client_p.getName());
                        ps.setString(5,client_p.getCode_country());
                        ps.setString(6,client_p.getCode_type());
                        ps.setString(7,client_p.getCode_resident());
                        ps.setString(8,client_p.getCode_subject());
                        ps.setString(9,client_p.getSign_registr());
                        ps.setString(10,client_p.getCode_form());
                        //ps.setDate(11,client_p.getDate_open());
                        //ps.setDate(12,client_p.getDate_close());
                        ps.setString(13,client_p.getState());
                        ps.setString(14,client_p.getKod_err());
                        ps.setString(15,client_p.getFile_name());
                        //ps.setDate(16,client_p.getP_birthday());
                        ps.setString(17,client_p.getP_post_address());
                        ps.setString(18,client_p.getP_passport_type());
                        ps.setString(19,client_p.getP_passport_serial());
                        ps.setString(20,client_p.getP_passport_number());
                        ps.setString(21,client_p.getP_passport_place_registration());
                        //ps.setDate(22,client_p.getP_passport_date_registration());
                        ps.setString(23,client_p.getP_code_tax_org());
                        ps.setString(24,client_p.getP_number_tax_registration());
                        ps.setString(25,client_p.getP_code_bank());
                        ps.setString(26,client_p.getP_code_class_credit());
                        ps.setString(27,client_p.getP_code_citizenship());
                        ps.setString(28,client_p.getP_birth_place());
                        ps.setString(29,client_p.getP_code_capacity());
                        //ps.setDate(30,client_p.getP_capacity_status_date());
                        ps.setString(31,client_p.getP_capacity_status_place());
                        ps.setString(32,client_p.getP_num_certif_capacity());
                        ps.setString(33,client_p.getP_phone_home());
                        ps.setString(34,client_p.getP_phone_mobile());
                        ps.setString(35,client_p.getP_email_address());
                        ps.setString(36,client_p.getP_pension_sertif_serial());
                        ps.setString(37,client_p.getP_code_gender());
                        ps.setString(38,client_p.getP_code_nation());
                        ps.setString(39,client_p.getP_code_birth_region());
                        ps.setString(40,client_p.getP_code_birth_distr());
                        ps.setString(41,client_p.getP_type_document());
                        //ps.setDate(42,client_p.getP_passport_date_expiration());
                        ps.setString(43,client_p.getP_code_adr_region());
                        ps.setString(44,client_p.getP_code_adr_distr());
                        ps.setString(45,client_p.getP_inps());
                        ps.setString(46,client_p.getP_family());
                        ps.setString(47,client_p.getP_first_name());
                        ps.setString(48,client_p.getP_patronymic());
                        ps.setString(49,client_p.getP_sign_vip());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(Client_p client_p)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM v_client_p WHERE id=?");
                    //ps.setLong(1, client_p.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }

	public static Map<Integer, String> getClientActionsMap(String alias) {
		Map<Integer, String> list = new HashMap<Integer, String>();
		Connection c = null;
		PreparedStatement ps = null;
		try
		{//"select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id"
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select aa.id data, aa.name label " +
									"from action_client aa " +
											"where aa.deal_id = ? " +
													"and aa.manual=1");
			ps.setInt(1, 2);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.put(rs.getInt("data"),
						rs.getString("label"));
			}
		}
		catch (SQLException e) {
			com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static Map<Integer, String> getAvailableActions(Client_p client, String alias, String uname, String pwd) {
		Map<Integer, String> list = new HashMap<Integer, String>();
		Connection c = null;
		PreparedStatement ps = null;
		try
		{//"select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id"
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select aa.id data, aa.name label " +
									"from trans_client tc, action_client aa " +
											"where tc.deal_id = ? " +
													"and state_begin = ? " +
													"and aa.deal_id = ? " +
													"and aa.id = tc.action_id " +
													"and aa.manual=1");
			ps.setInt(1, 2);
			if ((client==null) || client.getState()==null)
			{
				ps.setObject(2, null);
			}
			else
			{
				ps.setString(2, client.getState());
			}
			ps.setInt(3, 2);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.put(rs.getInt("data"),
						rs.getString("label"));
			}
		}
		catch (SQLException e) {
			com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static String getParamID(String alias, String uname, String pwd) {
		String Id="";

		Connection c = null;
		CallableStatement getParam = null;

		try {
			c = ConnectionPool.getConnection(uname,pwd,alias);
	
			getParam = c.prepareCall(SqlScripts.GET_PARAM_ID.getSql());
			getParam.registerOutParameter(1, Types.BIGINT);
		
			getParam.execute();
			Id=getParam.getString(1);
			
		} catch(Exception e) {
			
		} finally {
			ConnectionPool.close(c);
		}
		return Id;
	}

	
	public static Res doAction(Client_p clientp, int actionid, String alias, String uname, String pwd) {
		Res res =null;

//		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement getParam = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(uname,pwd,alias);
			res = new Res(0, "");
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			
			cs = c.prepareCall(SqlScripts.SET_PARAM.getSql());
			acs = c.prepareCall(SqlScripts.DO_ACTION.getSql());
			getParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
			getParam.execute();
			
			getParam = c.prepareCall(SqlScripts.GET_PARAM_ID.getSql());
			getParam.registerOutParameter(1, Types.BIGINT);
			if(clientp.getState() != null){
				cs.setString(1, "ID");  cs.setString(2, clientp.getId()); cs.execute();
			}
			for (Field field : Client_p.class.getDeclaredFields()) {
				if(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
					continue;
				}
				field.setAccessible(true);
				if (field.getName().equals("id") && clientp.getState() == null
						) {
					continue;
				}
				cs.setString(1, field.getName().toUpperCase());
				Object obj = field.get(clientp);
				logger.error(":::::::::::::::::::::::::::::::::::: before doAction fieldName = "+field.getName() + ", value = '"+obj+"'");
				if(obj instanceof Date) {
					cs.setDate(2, Util.sqlDate((Date)obj));
				} else {
					cs.setObject(2, obj);
				}
				cs.execute();
			}
			
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::: before doaction action = " +actionid);
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::: before doaction id = " +clientp.getId());
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::: before doaction client.id_client = " +clientp.getId_client());
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::: before doaction client.branch = " +clientp.getBranch());
			acs.setInt(1, DEAL_ID);
			acs.setInt(2, Integer.parseInt(clientp.getSign_registr()));
			acs.setInt(3, actionid);
			acs.execute();

			c.commit();

			//String Id;
			//if (clientp.getState()=="0")
			//{
			//	getParam.execute();
			//	Id=getParam.getString(1);
			//}
			//else
			//{
			//	Id=clientp.getId();
			//}

		} catch(Exception e) {
			logger.error(CheckNull.getPstr(e));
			res = new Res(-1, e.getMessage());
			DbUtils.rollback(c);
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(getParam);
			DbUtils.closeStmt(acs);
			DbUtils.closeStmt(cs);
		}
		return res;
	}

}


