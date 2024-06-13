package com.is.clients.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.clients.models.ClientJ;
import com.is.clients.models.ClientJFilter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class ClientDao implements Dao<ClientJ> {
    private static Logger logger = Logger.getLogger(ClientDao.class);

    private String alias;
    private ClientJFilter filter;
    private int count;


    private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql = "SELECT * FROM v_client_sap ";

    //	   rs.getLong("id"),
//       rs.getString("branch"),
//       rs.getString("id_client"),
//       rs.getString("name"),
//       rs.getString("code_type"),
//       rs.getInt("sign_registr"),
//       rs.getDate("date_open"),
//       rs.getString("state")));
    private ClientDao(String alias) {
        super();
        this.alias = alias;
    }

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        return new ClientDao();
    }

    public static ClientDao getInstance(String alias) {
        return new ClientDao(alias);
    }


    @Override
	/*public List<FilterField> getFilterFields() {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client=?", filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getClientIds())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client in (?)", filter.getClientIds()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "name like upper(?)", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_country=?", filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_type=?", filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_resident=?", filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_subject=?", filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_form=?", filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_open=?", Util.sqlDate(filter.getDate_open())));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_close=?", Util.sqlDate(filter.getDate_close())));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getKod_err())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "kod_err=?", filter.getKod_err()));
		}
		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "file_name=?", filter.getFile_name()));
		}
		
		 * if(!CheckNull.isEmpty(filter.getJ_short_name())){ flfields.add(new
		 * FilterField(DbUtils.getCond(flfields)+
		 * "j_short_name=?",filter.getJ_short_name())); }
		 
		if (!CheckNull.isEmpty(filter.getJ_place_regist_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_place_regist_name=?",
					filter.getJ_place_regist_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_date_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_date_registration=?",
					Util.sqlDate(filter.getJ_date_registration())));
		}
		if (!CheckNull.isEmpty(filter.getJ_number_registration_doc())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_registration_doc=?",
					filter.getJ_number_registration_doc()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_tax_org())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_tax_org=?", filter.getJ_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getJ_number_tax_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_tax_registration=?",
					filter.getJ_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_sector())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_sector=?", filter.getJ_code_sector()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_organ_direct())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_organ_direct=?",
					filter.getJ_code_organ_direct()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_head_organization())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_head_organization=?",
					filter.getJ_code_head_organization()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_class_credit())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_class_credit=?",
					filter.getJ_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_name=?", filter.getJ_director_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passport())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passport=?",
					filter.getJ_director_passport()));
		}
		if (!CheckNull.isEmpty(filter.getJ_chief_accounter_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_name=?",
					filter.getJ_chief_accounter_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_chief_accounter_passport())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_passport=?",
					filter.getJ_chief_accounter_passport()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_bank())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_bank=?", filter.getJ_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getJ_account())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_account=?", filter.getJ_account()));
		}
		if (!CheckNull.isEmpty(filter.getJ_post_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_post_address=?", filter.getJ_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_phone())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_phone=?", filter.getJ_phone()));
		}
		if (!CheckNull.isEmpty(filter.getJ_fax())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_fax=?", filter.getJ_fax()));
		}
		if (!CheckNull.isEmpty(filter.getJ_email())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_email=?", filter.getJ_email()));
		}
		if (!CheckNull.isEmpty(filter.getJ_sign_trade())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_sign_trade=?", filter.getJ_sign_trade()));
		}
		if (!CheckNull.isEmpty(filter.getJ_opf())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_opf=?", filter.getJ_opf()));
		}
		if (!CheckNull.isEmpty(filter.getJ_soato())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_soato=?", filter.getJ_soato()));
		}
		if (!CheckNull.isEmpty(filter.getJ_okpo())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_okpo=?", filter.getJ_okpo()));
		}
		if (!CheckNull.isEmpty(filter.getJ_inn_head_organization())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_inn_head_organization=?",
					filter.getJ_inn_head_organization()));
		}
		if (!CheckNull.isEmpty(filter.getJ_region())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_region=?", filter.getJ_region()));
		}
		if (!CheckNull.isEmpty(filter.getJ_distr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_distr=?", filter.getJ_distr()));
		}
		if (!CheckNull.isEmpty(filter.getJ_small_business())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "j_small_business=?", filter.getJ_small_business()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_type_document=?",
					filter.getJ_director_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_serial=?",
					filter.getJ_director_passp_serial()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_number())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_number=?",
					filter.getJ_director_passp_number()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_date_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_reg=?",
					filter.getJ_director_passp_date_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_place_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_place_reg=?",
					filter.getJ_director_passp_place_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_date_end())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_end=?",
					filter.getJ_director_passp_date_end()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_code_citizenship=?",
					filter.getJ_director_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birthday=?",
					filter.getJ_director_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birth_place=?",
					filter.getJ_director_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_address=?",
					filter.getJ_director_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_type_document=?",
					filter.getJ_accountant_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_serial=?",
					filter.getJ_accountant_passp_serial()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_number())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_number=?",
					filter.getJ_accountant_passp_number()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_reg=?",
					filter.getJ_accountant_passp_date_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_place_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_place_reg=?",
					filter.getJ_accountant_passp_place_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_end())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_end=?",
					filter.getJ_accountant_passp_date_end()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_code_citizenship=?",
					filter.getJ_accountant_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birthday=?",
					filter.getJ_accountant_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birth_place=?",
					filter.getJ_accountant_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_address=?",
					filter.getJ_accountant_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_327())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_327=?", filter.getJ_327()));
		}
		if (!CheckNull.isEmpty(filter.getJ_patent_expiration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_patent_expiration=?",
					filter.getJ_patent_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getJ_responsible_emp())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "j_responsible_emp=?", filter.getJ_responsible_emp()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_birthday=?", filter.getP_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_post_address=?", filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_type=?", filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_passport_serial=?", filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_passport_number=?", filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_place_registration=?",
					filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_registration=?",
					filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_tax_org=?", filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_number_tax_registration=?",
					filter.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_bank=?", filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_class_credit=?",
					filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_citizenship=?",
					filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_birth_place=?", filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_capacity=?", filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_capacity_status_date=?",
					filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_capacity_status_place=?",
					filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_num_certif_capacity=?",
					filter.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_home=?", filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_mobile=?", filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_email_address=?", filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_pension_sertif_serial=?",
					filter.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_gender=?", filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_nation=?", filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_region=?",
					filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_distr=?",
					filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_type_document=?", filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_expiration=?",
					filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_code_adr_region=?", filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_code_adr_distr=?", filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_inps=?", filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_family=?", filter.getP_family()));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_first_name=?", filter.getP_first_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_patronymic=?", filter.getP_patronymic()));
		}
		if (!CheckNull.isEmpty(filter.getP_last_name_cyr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_last_name_cyr=?", filter.getP_last_name_cyr()));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name_cyr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_first_name_cyr=?", filter.getP_first_name_cyr()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic_cyr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_patronymic_cyr=?", filter.getP_patronymic_cyr()));
		}
		flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}*/

    public List<FilterField> getFilterFields() {
        List<FilterField> flfields = new ArrayList<FilterField>();

        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id=?", filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getBranch())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "branch=?", filter.getBranch()));
        }
        if (!CheckNull.isEmpty(filter.getId_client())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client=?", filter.getId_client()));
        }
        if (!CheckNull.isEmpty(filter.getClientIds())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client in (?)", filter.getClientIds()));
        }
        //if (!CheckNull.isEmpty(filter.getName())) {
        //    flfields.add(new FilterField(DbUtils.getCond(flfields) + "name like upper(?)", filter.getName()));
        //}
        if (!CheckNull.isEmpty(filter.getName())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "(upper(name) like ? or upper(j_short_name) like ?)", filter.getName()));
            //yeshyo raz delayu. parametr doljen peredatsya dva raz 
            flfields.add(new FilterField("", filter.getName()));
        }
        if (!CheckNull.isEmpty(filter.getCode_country())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_country=?", filter.getCode_country()));
        }
        if (!CheckNull.isEmpty(filter.getCode_type())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_type=?", filter.getCode_type()));
        }
        if (!CheckNull.isEmpty(filter.getCode_resident())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_resident=?", filter.getCode_resident()));
        }
        if (!CheckNull.isEmpty(filter.getCode_subject())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_subject=?", filter.getCode_subject()));
        }
        if (!CheckNull.isEmpty(filter.getSign_registr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
        }
        if (!CheckNull.isEmpty(filter.getCode_form())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_form=?", filter.getCode_form()));
        }
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 1
				&& !CheckNull.isEmpty(filter.getDate_open())) {
        	flfields.add(new FilterField(DbUtils.getCond(flfields) + "date_open=?", Util.sqlDate(filter.getDate_open())));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 2
				&& !CheckNull.isEmpty(filter.getDate_open()) && !CheckNull.isEmpty(filter.getDate_open1())) {
 		   flfields.add(new FilterField(DbUtils.getCond(flfields) + "date_open between ? and ?", filter.getDate_open()));
 		   flfields.add(new FilterField("", filter.getDate_open1()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 1
				&& !CheckNull.isEmpty(filter.getDate_close())) {
        	flfields.add(new FilterField(DbUtils.getCond(flfields) + "date_close=?", Util.sqlDate(filter.getDate_close())));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 2
				&& !CheckNull.isEmpty(filter.getDate_close()) && !CheckNull.isEmpty(filter.getDate_close1())) {
 		   flfields.add(new FilterField(DbUtils.getCond(flfields) + "date_close between ? and ?", filter.getDate_close()));
 		   flfields.add(new FilterField("", filter.getDate_close1()));
		}
        if (!CheckNull.isEmpty(filter.getState())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "state=?", filter.getState()));
        }
        if (!CheckNull.isEmpty(filter.getKod_err())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "kod_err=?", filter.getKod_err()));
        }
        if (!CheckNull.isEmpty(filter.getFile_name())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "file_name=?", filter.getFile_name()));
        }
        if (!CheckNull.isEmpty(filter.getSubbranch())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "subbranch=?", filter.getSubbranch()));
        }
        /*if(!CheckNull.isEmpty(filter.getJ_short_name())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "j_short_name=?",filter.getJ_short_name()));
	    }*/
        if (!CheckNull.isEmpty(filter.getJ_place_regist_name())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_place_regist_name=?", filter.getJ_place_regist_name()));
        }
        if (!CheckNull.isEmpty(filter.getJ_date_registration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_date_registration=?", Util.sqlDate(filter.getJ_date_registration())));
        }
        if (!CheckNull.isEmpty(filter.getJ_number_registration_doc())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_registration_doc=?", filter.getJ_number_registration_doc()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_tax_org())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_tax_org=?", filter.getJ_code_tax_org()));
        }
        if (!CheckNull.isEmpty(filter.getJ_number_tax_registration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_tax_registration like ?", filter.getJ_number_tax_registration()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_sector())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_sector=?", filter.getJ_code_sector()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_organ_direct())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_organ_direct=?", filter.getJ_code_organ_direct()));
        }
        if (!CheckNull.isEmpty(filter.getJ_type_activity())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_type_activity=?", filter.getJ_type_activity()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_head_organization())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_head_organization=?", filter.getJ_code_head_organization()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_class_credit())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_class_credit=?", filter.getJ_code_class_credit()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_name())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_name=?", filter.getJ_director_name()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passport())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passport=?", filter.getJ_director_passport()));
        }
        if (!CheckNull.isEmpty(filter.getJ_chief_accounter_name())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_name=?", filter.getJ_chief_accounter_name()));
        }
        if (!CheckNull.isEmpty(filter.getJ_chief_accounter_passport())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_passport=?", filter.getJ_chief_accounter_passport()));
        }
        if (!CheckNull.isEmpty(filter.getJ_code_bank())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_bank=?", filter.getJ_code_bank()));
        }
        if (!CheckNull.isEmpty(filter.getJ_account())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_account=?", filter.getJ_account()));
        }
        if (!CheckNull.isEmpty(filter.getJ_post_address())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_post_address=?", filter.getJ_post_address()));
        }
        if (!CheckNull.isEmpty(filter.getJ_phone())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_phone=?", filter.getJ_phone()));
        }
        if (!CheckNull.isEmpty(filter.getJ_fax())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_fax=?", filter.getJ_fax()));
        }
        if (!CheckNull.isEmpty(filter.getJ_email())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_email=?", filter.getJ_email()));
        }
        if (!CheckNull.isEmpty(filter.getJ_sign_trade())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_sign_trade=?", filter.getJ_sign_trade()));
        }
        if (!CheckNull.isEmpty(filter.getJ_opf())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_opf=?", filter.getJ_opf()));
        }
        if (!CheckNull.isEmpty(filter.getJ_soato())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_soato=?", filter.getJ_soato()));
        }
        if (!CheckNull.isEmpty(filter.getJ_okpo())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_okpo=?", filter.getJ_okpo()));
        }
        if (!CheckNull.isEmpty(filter.getJ_inn_head_organization())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_inn_head_organization=?", filter.getJ_inn_head_organization()));
        }
        if (!CheckNull.isEmpty(filter.getJ_region())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_region=?", filter.getJ_region()));
        }
        if (!CheckNull.isEmpty(filter.getJ_distr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_distr=?", filter.getJ_distr()));
        }
        if (!CheckNull.isEmpty(filter.getJ_small_business())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_small_business=?", filter.getJ_small_business()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_type_document())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_type_document=?", filter.getJ_director_type_document()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passp_serial())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_serial=?", filter.getJ_director_passp_serial()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passp_number())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_number=?", filter.getJ_director_passp_number()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passp_date_reg())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_reg=?", filter.getJ_director_passp_date_reg()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passp_place_reg())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_place_reg=?", filter.getJ_director_passp_place_reg()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_passp_date_end())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_end=?", filter.getJ_director_passp_date_end()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_code_citizenship())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_code_citizenship=?", filter.getJ_director_code_citizenship()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_birthday())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birthday=?", filter.getJ_director_birthday()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_birth_place())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birth_place=?", filter.getJ_director_birth_place()));
        }
        if (!CheckNull.isEmpty(filter.getJ_director_address())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_address=?", filter.getJ_director_address()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_type_document())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_type_document=?", filter.getJ_accountant_type_document()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_passp_serial())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_serial=?", filter.getJ_accountant_passp_serial()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_passp_number())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_number=?", filter.getJ_accountant_passp_number()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_reg())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_reg=?", filter.getJ_accountant_passp_date_reg()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_passp_place_reg())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_place_reg=?", filter.getJ_accountant_passp_place_reg()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_end())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_end=?", filter.getJ_accountant_passp_date_end()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_code_citizenship())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_code_citizenship=?", filter.getJ_accountant_code_citizenship()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_birthday())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birthday=?", filter.getJ_accountant_birthday()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_birth_place())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birth_place=?", filter.getJ_accountant_birth_place()));
        }
        if (!CheckNull.isEmpty(filter.getJ_accountant_address())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_address=?", filter.getJ_accountant_address()));
        }
        if (!CheckNull.isEmpty(filter.getJ_327())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_327=?", filter.getJ_327()));
        }
        if (!CheckNull.isEmpty(filter.getJ_patent_expiration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_patent_expiration=?", filter.getJ_patent_expiration()));
        }
        if (!CheckNull.isEmpty(filter.getJ_responsible_emp())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_responsible_emp=?", filter.getJ_responsible_emp()));
        }
        if (!CheckNull.isEmpty(filter.getP_birthday())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_birthday=?", filter.getP_birthday()));
        }
        if (!CheckNull.isEmpty(filter.getP_post_address())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_post_address=?", filter.getP_post_address()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_type())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_type=?", filter.getP_passport_type()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_serial=?", filter.getP_passport_serial()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_number())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_number=?", filter.getP_passport_number()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_place_registration=?", filter.getP_passport_place_registration()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_registration=?", filter.getP_passport_date_registration()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_tax_org=?", filter.getP_code_tax_org()));
        }
        if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_number_tax_registration=?", filter.getP_number_tax_registration()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_bank())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_bank=?", filter.getP_code_bank()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_class_credit=?", filter.getP_code_class_credit()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_citizenship=?", filter.getP_code_citizenship()));
        }
        if (!CheckNull.isEmpty(filter.getP_birth_place())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_birth_place=?", filter.getP_birth_place()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_capacity=?", filter.getP_code_capacity()));
        }
        if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_capacity_status_date=?", filter.getP_capacity_status_date()));
        }
        if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_capacity_status_place=?", filter.getP_capacity_status_place()));
        }
        if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_num_certif_capacity=?", filter.getP_num_certif_capacity()));
        }
        if (!CheckNull.isEmpty(filter.getP_phone_home())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_home=?", filter.getP_phone_home()));
        }
        if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_mobile=?", filter.getP_phone_mobile()));
        }
        if (!CheckNull.isEmpty(filter.getP_email_address())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_email_address=?", filter.getP_email_address()));
        }
        if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_pension_sertif_serial=?", filter.getP_pension_sertif_serial()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_gender())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_gender=?", filter.getP_code_gender()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_nation())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_nation=?", filter.getP_code_nation()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_region=?", filter.getP_code_birth_region()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_distr=?", filter.getP_code_birth_distr()));
        }
        if (!CheckNull.isEmpty(filter.getP_type_document())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_type_document=?", filter.getP_type_document()));
        }
        if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_expiration=?", filter.getP_passport_date_expiration()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_adr_region=?", filter.getP_code_adr_region()));
        }
        if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_adr_distr=?", filter.getP_code_adr_distr()));
        }
        if (!CheckNull.isEmpty(filter.getP_inps())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_inps=?", filter.getP_inps()));
        }
        if (!CheckNull.isEmpty(filter.getP_pinfl())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_pinfl=?", filter.getP_pinfl()));
        }
        if (!CheckNull.isEmpty(filter.getP_family())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_family=?", filter.getP_family()));
        }
        if (!CheckNull.isEmpty(filter.getP_first_name())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_first_name=?", filter.getP_first_name()));
        }
        if (!CheckNull.isEmpty(filter.getP_patronymic())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_patronymic=?", filter.getP_patronymic()));
        }
        if (!CheckNull.isEmpty(filter.getP_last_name_cyr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_last_name_cyr=?", filter.getP_last_name_cyr()));
        }
        if (!CheckNull.isEmpty(filter.getP_first_name_cyr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_first_name_cyr=?", filter.getP_first_name_cyr()));
        }
        if (!CheckNull.isEmpty(filter.getP_patronymic_cyr())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_patronymic_cyr=?", filter.getP_patronymic_cyr()));
        }
        flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));
        return flfields;
    }

    private String appendLikeConditions() {
        StringBuilder sb = new StringBuilder();
        if (!CheckNull.isEmpty(filter.getName())) {
            sb.append(" and name like '").append(filter.getName().toUpperCase()).append("' ");
        }
        return sb.toString();
    }

    @Override
    public int getCount() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        List<FilterField> flFields = getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM v_client_sap ");
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
            //sql.append(appendLikeConditions());
            sql.append(" and code_subject in('J','I')");
        }
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(sql.toString());
            for (int k = 0; k < flFields.size(); k++) {
                ps.setObject(k + 1, flFields.get(k).getColobject());
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
            ConnectionPool.close(c);
        }
        return count;
    }

    @Override
    public List<ClientJ> getList() {
        Connection c = null;
        List<ClientJ> list = new ArrayList<ClientJ>();
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        int params;
        List<FilterField> flFields = getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from v_client_sap ");
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
            //sql.append(appendLikeConditions());
            sql.append(" and code_subject in ('J','I')");
        }
        try {
            if (alias == null) {
                c = ConnectionPool.getConnection();
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            cs = c.prepareCall("{ call info.init() }");
            ps = c.prepareStatement(sql.toString());
            cs.execute();
            for (params = 0; params < flFields.size(); params++) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            params++;

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ClientJ(
                        rs.getLong("id"),
                        rs.getString("branch"),
                        rs.getString("id_client"),
                        rs.getString("name"),
                        rs.getString("code_country"),
                        rs.getString("code_type"),
                        rs.getString("code_resident"),
                        rs.getString("code_subject"),
                        rs.getInt("sign_registr"),
                        rs.getString("code_form"),
                        rs.getDate("date_open"),
                        rs.getDate("date_close"),
                        rs.getString("state"),
                        rs.getInt("kod_err"),
                        rs.getString("file_name"),
                        rs.getString("subbranch"),
                        rs.getString("j_short_name"),
                        rs.getString("j_place_regist_name"),
                        rs.getDate("j_date_registration"),
                        rs.getString("j_number_registration_doc"),
                        rs.getString("j_code_tax_org"),
                        rs.getString("j_number_tax_registration"),
                        rs.getString("j_code_sector_old"),
                        rs.getString("j_code_sector"),
                        rs.getString("j_code_organ_direct"),
                        rs.getString("j_code_head_organization"),
                        rs.getString("j_code_class_credit"),
                        rs.getString("j_director_name"),
                        rs.getString("j_director_passport"),
                        rs.getString("j_chief_accounter_name"),
                        rs.getString("j_chief_accounter_passport"),
                        rs.getString("j_code_bank"),
                        rs.getString("j_account"),
                        rs.getString("j_post_address"),
                        rs.getString("j_phone"),
                        rs.getString("j_fax"),
                        rs.getString("j_email"),
                        rs.getString("j_sign_trade"),
                        rs.getString("j_opf").trim(),
                        rs.getString("j_soato").trim(),
                        rs.getString("j_okpo").trim(),
                        rs.getString("j_inn_head_organization"),
                        rs.getString("j_region"),
                        rs.getString("j_distr"),
                        rs.getString("j_small_business"),
                        rs.getString("j_director_type_document"),
                        rs.getString("j_director_passp_serial"),
                        rs.getString("j_director_passp_number"),
                        rs.getDate("j_director_passp_date_reg"),
                        rs.getString("j_director_passp_place_reg"),
                        rs.getDate("j_director_passp_date_end"),
                        rs.getString("j_director_code_citizenship"),
                        rs.getDate("j_director_birthday"),
                        rs.getString("j_director_birth_place"),
                        rs.getString("j_director_address"),
                        rs.getString("j_accountant_type_document"),
                        rs.getString("j_accountant_passp_serial"),
                        rs.getString("j_accountant_passp_number"),
                        rs.getDate("j_accountant_passp_date_reg"),
                        rs.getString("j_accountant_passp_place_reg"),
                        rs.getDate("j_accountant_passp_date_end"),
                        rs.getString("j_accountant_code_citizenship"),
                        rs.getDate("j_accountant_birthday"),
                        rs.getString("j_accountant_birth_place"),
                        rs.getString("j_accountant_address"),
                        rs.getLong("j_327"),
                        rs.getDate("j_patent_expiration"),
                        rs.getString("j_responsible_emp"),
                        rs.getString("i_short_name"),
                        rs.getDate("i_date_registration"),
                        rs.getString("i_number_registration_doc"),
                        rs.getString("i_place_regist_name"),
                        rs.getString("i_number_tax_registration"),
                        rs.getString("i_opf"),
                        rs.getString("i_form"),
                        rs.getString("i_sector"),
                        rs.getString("i_organ_direct"),
                        rs.getString("i_account"),
                        rs.getString("i_post_address"),
                        rs.getString("i_director_name"),
                        rs.getString("i_chief_accounter_name"),
                        rs.getString("i_phone"),
                        rs.getString("i_fax"),
                        rs.getString("i_email"),
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
                        rs.getString("p_last_name_cyr"),
                        rs.getString("p_first_name_cyr"),
                        rs.getString("p_patronymic_cyr"),
                        rs.getString("p_pass_place_region"),
                        rs.getString("p_pass_place_district"),
                        rs.getString("p_post_address_street"),
                        rs.getString("p_post_address_house"),
                        rs.getString("p_post_address_flat"),
                        rs.getString("p_pinfl"),
                        rs.getString("sign_100"),
                        rs.getString("j_type_activity"),
                        rs.getString("code_country_adr"), null, null, 0,0,
                        rs.getString("post_address_country")));
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
        }
        return list;
    }

    @Override
    public List<ClientJ> getListWithPaging(int pageIndex, int pageSize) {
        Connection c = null;
        List<ClientJ> list = new ArrayList<ClientJ>();
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        int v_lowerbound = pageIndex * pageSize + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields = getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append("SELECT id, branch, id_client, name, code_type, sign_registr, date_open, state FROM v_client_sap ");
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
            //sql.append(appendLikeConditions());
            sql.append(" and code_subject in ('J','I')");
        }
        sql.append(psql2);
        try {
            if (alias == null) {
                c = ConnectionPool.getConnection();
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            ps = c.prepareStatement(sql.toString());
            cs.execute();

            for (params = 0; params < flFields.size(); params++) {
                Object obj = flFields.get(params).getColobject();
                if (obj instanceof java.util.Date) {
                    ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
                    continue;
                }
                if (obj instanceof String) {
                    //ps.setString(params + 1, ((String) obj).toUpperCase());
                	ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
                    continue;
                }
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            params++;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params++, v_lowerbound);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ClientJ(
                        rs.getLong("id"),
                        rs.getString("branch"),
                        rs.getString("id_client"),
                        rs.getString("name"),
                        rs.getString("code_type"),
                        rs.getInt("sign_registr"),
                        rs.getDate("date_open"),
                        rs.getString("state")));
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
        }
        return list;
    }

    @Override
    public ClientJ getItemByLongId(String branch, long itemId) {
        ClientJ customer = null;
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            if (alias == null) {
                String schema = DbUtils.getSchemaByBranch(branch);
                //logger.error("Schema is " + schema);
                c = ConnectionPool.getConnection(schema);
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            cs = c.prepareCall("{call info.init()}");
            cs.execute();
            ps = c.prepareStatement("SELECT * FROM v_client_sap WHERE branch = ? and id=?");
            ps.setString(1,branch);
            ps.setLong(2, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new ClientJ(
                        rs.getLong("id"),
                        rs.getString("branch"),
                        rs.getString("id_client"),
                        rs.getString("name"),
                        rs.getString("code_country"),
                        rs.getString("code_type"),
                        rs.getString("code_resident"),
                        rs.getString("code_subject"),
                        rs.getInt("sign_registr"),
                        rs.getString("code_form"),
                        rs.getDate("date_open"),
                        rs.getDate("date_close"),
                        rs.getString("state"),
                        rs.getInt("kod_err"),
                        rs.getString("file_name"),
                        rs.getString("subbranch"),
                        rs.getString("j_short_name"),
                        rs.getString("j_place_regist_name"),
                        rs.getDate("j_date_registration"),
                        rs.getString("j_number_registration_doc"),
                        rs.getString("j_code_tax_org"),
                        rs.getString("j_number_tax_registration"),
                        rs.getString("j_code_sector_old"),
                        rs.getString("j_code_sector"),
                        rs.getString("j_code_organ_direct"),
                        rs.getString("j_code_head_organization"),
                        rs.getString("j_code_class_credit"),
                        rs.getString("j_director_name"),
                        rs.getString("j_director_passport"),
                        rs.getString("j_chief_accounter_name"),
                        rs.getString("j_chief_accounter_passport"),
                        rs.getString("j_code_bank"),
                        rs.getString("j_account"),
                        rs.getString("j_post_address"),
                        rs.getString("j_phone"),
                        rs.getString("j_fax"),
                        rs.getString("j_email"),
                        rs.getString("j_sign_trade"),
                        rs.getString("j_opf").trim(),
                        rs.getString("j_soato").trim(),
                        rs.getString("j_okpo").trim(),
                        rs.getString("j_inn_head_organization"),
                        rs.getString("j_region"),
                        rs.getString("j_distr"),
                        rs.getString("j_small_business"),
                        rs.getString("j_director_type_document"),
                        rs.getString("j_director_passp_serial"),
                        rs.getString("j_director_passp_number"),
                        rs.getDate("j_director_passp_date_reg"),
                        rs.getString("j_director_passp_place_reg"),
                        rs.getDate("j_director_passp_date_end"),
                        rs.getString("j_director_code_citizenship"),
                        rs.getDate("j_director_birthday"),
                        rs.getString("j_director_birth_place"),
                        rs.getString("j_director_address"),
                        rs.getString("j_accountant_type_document"),
                        rs.getString("j_accountant_passp_serial"),
                        rs.getString("j_accountant_passp_number"),
                        rs.getDate("j_accountant_passp_date_reg"),
                        rs.getString("j_accountant_passp_place_reg"),
                        rs.getDate("j_accountant_passp_date_end"),
                        rs.getString("j_accountant_code_citizenship"),
                        rs.getDate("j_accountant_birthday"),
                        rs.getString("j_accountant_birth_place"),
                        rs.getString("j_accountant_address"),
                        rs.getLong("j_327"),
                        rs.getDate("j_patent_expiration"),
                        rs.getString("j_responsible_emp"),
                        rs.getString("i_short_name"),
                        rs.getDate("i_date_registration"),
                        rs.getString("i_number_registration_doc"),
                        rs.getString("i_place_regist_name"),
                        rs.getString("i_number_tax_registration"),
                        rs.getString("i_opf"),
                        rs.getString("i_form"),
                        rs.getString("i_sector"),
                        rs.getString("i_organ_direct"),
                        rs.getString("i_account"),
                        rs.getString("i_post_address"),
                        rs.getString("i_director_name"),
                        rs.getString("i_chief_accounter_name"),
                        rs.getString("i_phone"),
                        rs.getString("i_fax"),
                        rs.getString("i_email"),
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
                        rs.getString("p_last_name_cyr"),
                        rs.getString("p_first_name_cyr"),
                        rs.getString("p_patronymic_cyr"),
                        rs.getString("p_pass_place_region"),
                        rs.getString("p_pass_place_district"),
                        rs.getString("p_post_address_street"),
                        rs.getString("p_post_address_house"),
                        rs.getString("p_post_address_flat"),
                        rs.getString("p_pinfl"),
                        rs.getString("sign_100"),
                        rs.getString("j_type_activity"),
                        rs.getString("code_country_adr"), null, null, 0,0,
                        rs.getString("post_address_country")
                );
                customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
                        ? customer.getJ_code_head_organization().trim()
                        : "0");
                customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
                        ? customer.getJ_inn_head_organization().trim()
                        : "0");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return customer;
    }

    @Override
    public int update(ClientJ clientj) {
        return 0;
    }

    @Override
    public int remove(ClientJ clientj) {
        return 0;
    }

    @Override
    public ClientJ create(ClientJ clientj) {
        return null;
    }

    @Override
    public ClientJ getItemByStringId(String branch, String itemId) {
        ClientJ customer = null;
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            if (alias == null) {
                String schema = DbUtils.getSchemaByBranch(branch);
                //logger.error("Schema is " + schema);
                c = ConnectionPool.getConnection(schema);
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            cs = c.prepareCall("{call info.init()}");
            cs.execute();
            ps = c.prepareStatement("SELECT * FROM v_client_sap WHERE branch = ? and id_client=?");
            ps.setString(1,branch);
            ps.setString(2, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new ClientJ(
                        rs.getLong("id"),
                        rs.getString("branch"),
                        rs.getString("id_client"),
                        rs.getString("name"),
                        rs.getString("code_country"),
                        rs.getString("code_type"),
                        rs.getString("code_resident"),
                        rs.getString("code_subject"),
                        rs.getInt("sign_registr"),
                        rs.getString("code_form"),
                        rs.getDate("date_open"),
                        rs.getDate("date_close"),
                        rs.getString("state"),
                        rs.getInt("kod_err"),
                        rs.getString("file_name"),
                        rs.getString("subbranch"),
                        rs.getString("j_short_name"),
                        rs.getString("j_place_regist_name"),
                        rs.getDate("j_date_registration"),
                        rs.getString("j_number_registration_doc"),
                        rs.getString("j_code_tax_org"),
                        rs.getString("j_number_tax_registration"),
                        rs.getString("j_code_sector_old"),
                        rs.getString("j_code_sector"),
                        rs.getString("j_code_organ_direct"),
                        rs.getString("j_code_head_organization"),
                        rs.getString("j_code_class_credit"),
                        rs.getString("j_director_name"),
                        rs.getString("j_director_passport"),
                        rs.getString("j_chief_accounter_name"),
                        rs.getString("j_chief_accounter_passport"),
                        rs.getString("j_code_bank"),
                        rs.getString("j_account"),
                        rs.getString("j_post_address"),
                        rs.getString("j_phone"),
                        rs.getString("j_fax"),
                        rs.getString("j_email"),
                        rs.getString("j_sign_trade"),
                        rs.getString("j_opf")!=null ? rs.getString("j_opf").trim() : rs.getString("j_opf"),
                        rs.getString("j_soato")!=null? rs.getString("j_soato").trim(): rs.getString("j_soato"),
                        		rs.getString("j_okpo")!=null ?  rs.getString("j_okpo").trim():rs.getString("j_okpo"),
                        rs.getString("j_inn_head_organization"),
                        rs.getString("j_region"),
                        rs.getString("j_distr"),
                        rs.getString("j_small_business"),
                        rs.getString("j_director_type_document"),
                        rs.getString("j_director_passp_serial"),
                        rs.getString("j_director_passp_number"),
                        rs.getDate("j_director_passp_date_reg"),
                        rs.getString("j_director_passp_place_reg"),
                        rs.getDate("j_director_passp_date_end"),
                        rs.getString("j_director_code_citizenship"),
                        rs.getDate("j_director_birthday"),
                        rs.getString("j_director_birth_place"),
                        rs.getString("j_director_address"),
                        rs.getString("j_accountant_type_document"),
                        rs.getString("j_accountant_passp_serial"),
                        rs.getString("j_accountant_passp_number"),
                        rs.getDate("j_accountant_passp_date_reg"),
                        rs.getString("j_accountant_passp_place_reg"),
                        rs.getDate("j_accountant_passp_date_end"),
                        rs.getString("j_accountant_code_citizenship"),
                        rs.getDate("j_accountant_birthday"),
                        rs.getString("j_accountant_birth_place"),
                        rs.getString("j_accountant_address"),
                        rs.getLong("j_327"),
                        rs.getDate("j_patent_expiration"),
                        rs.getString("j_responsible_emp"),
                        rs.getString("i_short_name"),
                        rs.getDate("i_date_registration"),
                        rs.getString("i_number_registration_doc"),
                        rs.getString("i_place_regist_name"),
                        rs.getString("i_number_tax_registration"),
                        rs.getString("i_opf"),
                        rs.getString("i_form"),
                        rs.getString("i_sector"),
                        rs.getString("i_organ_direct"),
                        rs.getString("i_account"),
                        rs.getString("i_post_address"),
                        rs.getString("i_director_name"),
                        rs.getString("i_chief_accounter_name"),
                        rs.getString("i_phone"),
                        rs.getString("i_fax"),
                        rs.getString("i_email"),
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
                        rs.getString("p_last_name_cyr"),
                        rs.getString("p_first_name_cyr"),
                        rs.getString("p_patronymic_cyr"),
                        rs.getString("p_pass_place_region"),
                        rs.getString("p_pass_place_district"),
                        rs.getString("p_post_address_street"),
                        rs.getString("p_post_address_house"),
                        rs.getString("p_post_address_flat"),
                        rs.getString("p_pinfl"),
                        rs.getString("sign_100"),
                        rs.getString("j_type_activity"),
                        rs.getString("code_country_adr"), null, null, 0,0,
                        rs.getString("post_address_country")
                );
                customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
                        ? customer.getJ_code_head_organization().trim()
                        : "0");
                customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
                        ? customer.getJ_inn_head_organization().trim()
                        : "0");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return customer;
    }

    @Override
    public <T1 extends ClientJ> void setFilter(T1 filter) {
        this.filter = (ClientJFilter) filter;
    }

    @Override
    public ClientJ create(Connection c, ClientJ item)
            throws Exception {
        return null;
    }

    @Override
    public int update(Connection c, ClientJ item) throws Exception {
        return 0;
    }

    @Override
    public int remove(Connection c, ClientJ item) {
        return 0;
    }

    @Override
    public ClientJ getItemByLongId(Connection c, String branch, long itemId) {
        return null;
    }

    @Override
    public ClientJ getItemByStringId(Connection c, String branch, String itemId) {
        ClientJ customer = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = c.prepareCall("{call info.init()}");
            cs.execute();
            ps = c.prepareStatement("SELECT * FROM v_client_sap WHERE branch = ? and id_client=?");
            ps.setString(1,branch);
            ps.setString(2, itemId);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new ClientJ(
                        rs.getLong("id"),
                        rs.getString("branch"),
                        rs.getString("id_client"),
                        rs.getString("name"),
                        rs.getString("code_country"),
                        rs.getString("code_type"),
                        rs.getString("code_resident"),
                        rs.getString("code_subject"),
                        rs.getInt("sign_registr"),
                        rs.getString("code_form"),
                        rs.getDate("date_open"),
                        rs.getDate("date_close"),
                        rs.getString("state"),
                        rs.getInt("kod_err"),
                        rs.getString("file_name"),
                        rs.getString("subbranch"),
                        rs.getString("j_short_name"),
                        rs.getString("j_place_regist_name"),
                        rs.getDate("j_date_registration"),
                        rs.getString("j_number_registration_doc"),
                        rs.getString("j_code_tax_org"),
                        rs.getString("j_number_tax_registration"),
                        rs.getString("j_code_sector_old"),
                        rs.getString("j_code_sector"),
                        rs.getString("j_code_organ_direct"),
                        rs.getString("j_code_head_organization"),
                        rs.getString("j_code_class_credit"),
                        rs.getString("j_director_name"),
                        rs.getString("j_director_passport"),
                        rs.getString("j_chief_accounter_name"),
                        rs.getString("j_chief_accounter_passport"),
                        rs.getString("j_code_bank"),
                        rs.getString("j_account"),
                        rs.getString("j_post_address"),
                        rs.getString("j_phone"),
                        rs.getString("j_fax"),
                        rs.getString("j_email"),
                        rs.getString("j_sign_trade"),
                        rs.getString("j_opf").trim(),
                        rs.getString("j_soato").trim(),
                        rs.getString("j_okpo").trim(),
                        rs.getString("j_inn_head_organization"),
                        rs.getString("j_region"),
                        rs.getString("j_distr"),
                        rs.getString("j_small_business"),
                        rs.getString("j_director_type_document"),
                        rs.getString("j_director_passp_serial"),
                        rs.getString("j_director_passp_number"),
                        rs.getDate("j_director_passp_date_reg"),
                        rs.getString("j_director_passp_place_reg"),
                        rs.getDate("j_director_passp_date_end"),
                        rs.getString("j_director_code_citizenship"),
                        rs.getDate("j_director_birthday"),
                        rs.getString("j_director_birth_place"),
                        rs.getString("j_director_address"),
                        rs.getString("j_accountant_type_document"),
                        rs.getString("j_accountant_passp_serial"),
                        rs.getString("j_accountant_passp_number"),
                        rs.getDate("j_accountant_passp_date_reg"),
                        rs.getString("j_accountant_passp_place_reg"),
                        rs.getDate("j_accountant_passp_date_end"),
                        rs.getString("j_accountant_code_citizenship"),
                        rs.getDate("j_accountant_birthday"),
                        rs.getString("j_accountant_birth_place"),
                        rs.getString("j_accountant_address"),
                        rs.getLong("j_327"),
                        rs.getDate("j_patent_expiration"),
                        rs.getString("j_responsible_emp"),
                        rs.getString("i_short_name"),
                        rs.getDate("i_date_registration"),
                        rs.getString("i_number_registration_doc"),
                        rs.getString("i_place_regist_name"),
                        rs.getString("i_number_tax_registration"),
                        rs.getString("i_opf"),
                        rs.getString("i_form"),
                        rs.getString("i_sector"),
                        rs.getString("i_organ_direct"),
                        rs.getString("i_account"),
                        rs.getString("i_post_address"),
                        rs.getString("i_director_name"),
                        rs.getString("i_chief_accounter_name"),
                        rs.getString("i_phone"),
                        rs.getString("i_fax"),
                        rs.getString("i_email"),
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
                        rs.getString("p_last_name_cyr"),
                        rs.getString("p_first_name_cyr"),
                        rs.getString("p_patronymic_cyr"),
                        rs.getString("p_pass_place_region"),
                        rs.getString("p_pass_place_district"),
                        rs.getString("p_post_address_street"),
                        rs.getString("p_post_address_house"),
                        rs.getString("p_post_address_flat"),
                        rs.getString("p_pinfl"),
                        rs.getString("sign_100"),
                        rs.getString("j_type_activity"),
                        rs.getString("code_country_adr"), null, null, 0,0,
                        rs.getString("post_address_country")
                );
                customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
                        ? customer.getJ_code_head_organization().trim()
                        : "0");
                customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
                        ? customer.getJ_inn_head_organization().trim()
                        : "0");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }
        return customer;
    }

}
