package com.is.clients.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.is.ISLogger;
import com.is.base.utils.FieldHandler;
import com.is.base.utils.Util;
import com.is.client_personmap.model.Person;
import com.is.clients.utils.ClientFields;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.model.Customer;
import com.is.customer_.entrepreneur.IndividualEnterpreneur;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public class ClientJ implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    protected Long id;
    @Getter
    @Setter
    protected String branch;
    @Getter
    @Setter
    protected String id_client;
    @Getter
    @Setter
    protected String id_client_in_sap;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected String code_country;
    @Getter
    @Setter
    protected String code_type;
    @Getter
    @Setter
    protected String code_resident;
    @Getter
    @Setter
    protected String code_subject;
    @Getter
    @Setter
    protected int sign_registr;
    @Getter
    @Setter
    protected String code_form;
    @Getter
    @Setter
    protected Date date_open;
    @Getter
    @Setter
    protected Date date_close;
    @Getter
    @Setter
    protected String state;
    @Getter
    @Setter
    protected int kod_err;
    @Getter
    @Setter
    protected String file_name;
    @Getter
    @Setter
    protected String sign_100;
    @Getter
    @Setter
    protected String subbranch;
    
    @Setter
    @Getter
    protected String j_short_name;
    /*public void setJ_short_name(String short_name){
        if (name != null)
            this.j_short_name = getJ_short_name();
    }
    */
    public String substringShortName(){
        if (name != null) {
            if (name.length() >= 25)
                return name.substring(0, 25);
            else
                return name.substring(0,name.length());
        }
        return null;
    }

    @Getter
    @Setter
    protected String j_place_regist_name;
    @Getter
    @Setter
    protected Date j_date_registration;
    @Getter
    @Setter
    protected String j_number_registration_doc;
    @Getter
    @Setter
    protected String j_code_tax_org;
    @Getter
    @Setter
    protected String j_number_tax_registration;
    @Getter
    @Setter
    protected String j_code_sector;
    @Getter
    @Setter
    protected String j_code_sector_old;
    @Getter
    @Setter
    protected String j_code_organ_direct;
    @Getter
    @Setter
    protected String j_code_head_organization;
    @Getter
    @Setter
    protected String j_code_class_credit;
    @Getter
    @Setter
    protected String j_director_name;
    @Getter
    @Setter
    protected String j_director_passport;
    @Getter
    @Setter
    protected String j_chief_accounter_name;
    @Getter
    @Setter
    protected String j_chief_accounter_passport;
    @Getter
    @Setter
    protected String j_code_bank;
    @Getter
    @Setter
    protected String j_account;
    @Getter
    @Setter
    protected String j_post_address;
    @Getter
    @Setter
    protected String j_phone;
    @Getter
    @Setter
    protected String j_fax;
    @Getter
    @Setter
    protected String j_email;
    @Getter
    @Setter
    protected String j_sign_trade;
    @Getter
    @Setter
    protected String j_opf;
    @Getter
    @Setter
    protected String j_soato;
    @Getter
    @Setter
    protected String j_okpo;
    @Getter
    @Setter
    protected String j_inn_head_organization;
    @Getter
    @Setter
    protected String j_region;
    @Getter
    @Setter
    protected String j_distr;
    @Getter
    @Setter
    protected String j_small_business;
    @Getter
    @Setter
    protected String j_director_type_document;
    @Getter
    @Setter
    protected String j_director_passp_serial;
    @Getter
    @Setter
    protected String j_director_passp_number;
    @Getter
    @Setter
    protected Date j_director_passp_date_reg;
    @Getter
    @Setter
    protected String j_director_passp_place_reg;
    @Getter
    @Setter
    protected Date j_director_passp_date_end;
    @Getter
    @Setter
    protected String j_director_code_citizenship;
    @Getter
    @Setter
    protected Date j_director_birthday;
    @Getter
    @Setter
    protected String j_director_birth_place;
    @Getter
    @Setter
    protected String j_director_address;
    @Getter
    @Setter
    protected String j_accountant_type_document;
    @Getter
    @Setter
    protected String j_accountant_passp_serial;
    @Getter
    @Setter
    protected String j_accountant_passp_number;
    @Getter
    @Setter
    protected Date j_accountant_passp_date_reg;
    @Getter
    @Setter
    protected String j_accountant_passp_place_reg;
    @Getter
    @Setter
    protected Date j_accountant_passp_date_end;
    @Getter
    @Setter
    protected String j_accountant_code_citizenship;
    @Getter
    @Setter
    protected Date j_accountant_birthday;
    @Getter
    @Setter
    protected String j_accountant_birth_place;
    @Getter
    @Setter
    protected String j_accountant_address;
    @Getter
    @Setter
    protected Long j_327;
    @Getter
    @Setter
    protected Date j_patent_expiration;
    @Getter
    @Setter
    protected String j_responsible_emp;
    @Setter
    protected String i_short_name;
    public String getI_short_name(){
        if (name != null) {
            if (name.length() >= 25)
                return name.substring(0, 25);
            else
                return name.substring(0,name.length());
        }
        return null;
    }
    @Getter
    @Setter
    protected Date i_date_registration;
    @Getter
    @Setter
    protected String i_number_registration_doc;
    @Getter
    @Setter
    protected String i_place_regist_name;
    @Getter
    @Setter
    protected String i_number_tax_registration;
    @Getter
    @Setter
    protected String i_opf;
    @Getter
    @Setter
    protected String i_form;
    @Getter
    @Setter
    protected String i_sector;
    @Getter
    @Setter
    protected String i_organ_direct;
    @Getter
    @Setter
    protected String i_account;
    @Getter
    @Setter
    protected String i_post_address;
    @Getter
    @Setter
    protected String i_director_name;
    @Getter
    @Setter
    protected String i_chief_accounter_name;
    @Getter
    @Setter
    protected String i_phone;
    @Getter
    @Setter
    protected String i_fax;
    @Getter
    @Setter
    protected String i_email;
    @Getter
    @Setter
    protected Date p_birthday;
    @Getter
    @Setter
    protected String p_post_address;
    @Getter
    @Setter
    protected String p_passport_type;
    @Getter
    @Setter
    protected String p_passport_serial;
    @Getter
    @Setter
    protected String p_passport_number;
    @Getter
    @Setter
    protected String p_passport_place_registration;
    @Getter
    @Setter
    protected Date p_passport_date_registration;
    @Getter
    @Setter
    protected String p_code_tax_org;
    @Getter
    @Setter
    protected String p_number_tax_registration;
    @Getter
    @Setter
    protected String p_code_bank;
    @Getter
    @Setter
    protected String p_code_class_credit;
    @Getter
    @Setter
    protected String p_code_citizenship;
    @Getter
    @Setter
    protected String p_birth_place;
    @Getter
    @Setter
    protected String p_code_capacity;
    @Getter
    @Setter
    protected Date p_capacity_status_date;
    @Getter
    @Setter
    protected String p_capacity_status_place;
    @Getter
    @Setter
    protected String p_num_certif_capacity;
    @Getter
    @Setter
    protected String p_phone_home;
    @Getter
    @Setter
    protected String p_phone_mobile;
    @Getter
    @Setter
    protected String p_email_address;
    @Getter
    @Setter
    protected String p_pension_sertif_serial;
    @Getter
    @Setter
    protected String p_code_gender;
    @Getter
    @Setter
    protected String p_code_nation;
    @Getter
    @Setter
    protected String p_code_birth_region;
    @Getter
    @Setter
    protected String p_code_birth_distr;
    @Getter
    @Setter
    protected String p_type_document;
    @Getter
    @Setter
    protected Date p_passport_date_expiration;
    @Getter
    @Setter
    protected String p_code_adr_region;
    @Getter
    @Setter
    protected String p_code_adr_distr;
    @Getter
    @Setter
    protected String p_inps;
    @Getter
    @Setter
    protected String p_family;
    @Getter
    @Setter
    protected String p_first_name;
    @Getter
    @Setter
    protected String p_patronymic;
    @Getter
    @Setter
    protected String p_last_name_cyr;
    @Getter
    @Setter
    protected String p_first_name_cyr;
    @Getter
    @Setter
    protected String p_patronymic_cyr;
    @Getter
    @Setter
    protected String p_pass_place_region;
    @Getter
    @Setter
    protected String p_pass_place_district;
    @Getter
    @Setter
    protected String p_post_address_quarter;
    @Getter
    @Setter
    protected String p_post_address_street;
    @Getter
    @Setter
    protected String p_post_address_house;
    @Getter
    @Setter
    protected String p_post_address_flat;
    @Getter
    @Setter
    protected String p_pinfl;
    @Getter
    @Setter
    protected String swift_id;
    @Getter
    @Setter
    protected String type_non_resident;
    @Getter
    @Setter
    private String j_sign_dep_acc;
    @Getter
    @Setter
    protected String j_type_activity;
    @Setter
    private Person director;
    @Setter
    private Person accountant;

    @Getter
    private IndividualEnterpreneur individualEnterpreneur;

    @Getter
    @Setter
    protected String id_sap;
    @Getter
    @Setter
    private String capital_inform;
    @Getter
    @Setter
    private String capital_currency;

    @Getter
    @Setter
    private int nibbd_emp_id;

    @Getter
    @Setter
    protected String post_address_country;
    
    @Getter
    @Setter
    protected Date date_open1;

    @Getter
    @Setter
    protected Date date_close1;
    
    @Getter
    @Setter
    protected int sign_date_open;
    
    @Getter
    @Setter
    protected int sign_date_close;

    @Getter
    @Setter
    private String addressCountry;

    @Getter
    @Setter
    private boolean i014;

    @Getter
    @Setter
    protected String emp_id;

    @Getter
    @Setter
    protected boolean checkedInAtaccama;
    
    public ClientJ() {
        super();
        this.checkedInAtaccama=false;
    }

    public ClientJ(long id, String branch, String id_client, String name, String code_type, int sign_registr,
                   Date date_open, String state) {
        super();
        this.id = id;
        this.branch = branch;
        this.id_client = id_client;
        this.name = name;
        this.code_type = code_type;
        this.sign_registr = sign_registr;
        this.date_open = date_open;
        this.state = state;
        this.checkedInAtaccama=false;
    }

    public ClientJ(Long id, String branch, String id_client, String name, String code_country, String code_type,
                   String code_resident, String code_subject, int sign_registr, String code_form, Date date_open,
                   Date date_close, String state, int kod_err, String file_name, String subbranch, String j_short_name,
                   String j_place_regist_name, Date j_date_registration, String j_number_registration_doc,
                   String j_code_tax_org, String j_number_tax_registration, String j_code_sector_old, String j_code_sector,
                   String j_code_organ_direct, String j_code_head_organization, String j_code_class_credit,
                   String j_director_name, String j_director_passport, String j_chief_accounter_name,
                   String j_chief_accounter_passport, String j_code_bank, String j_account, String j_post_address,
                   String j_phone, String j_fax, String j_email, String j_sign_trade, String j_opf, String j_soato,
                   String j_okpo, String j_inn_head_organization, String j_region, String j_distr, String j_small_business,
                   String j_director_type_document, String j_director_passp_serial, String j_director_passp_number,
                   Date j_director_passp_date_reg, String j_director_passp_place_reg, Date j_director_passp_date_end,
                   String j_director_code_citizenship, Date j_director_birthday, String j_director_birth_place,
                   String j_director_address, String j_accountant_type_document, String j_accountant_passp_serial,
                   String j_accountant_passp_number, Date j_accountant_passp_date_reg, String j_accountant_passp_place_reg,
                   Date j_accountant_passp_date_end, String j_accountant_code_citizenship, Date j_accountant_birthday,
                   String j_accountant_birth_place, String j_accountant_address, Long j_327, Date j_patent_expiration,
                   String j_responsible_emp, String i_short_name, Date i_date_registration, String i_number_registration_doc,
                   String i_place_regist_name, String i_number_tax_registration, String i_opf, String i_form, String i_sector,
                   String i_organ_direct, String i_account, String i_post_address, String i_director_name,
                   String i_chief_accounter_name, String i_phone, String i_fax, String i_email, Date p_birthday,
                   String p_post_address, String p_passport_type, String p_passport_serial, String p_passport_number,
                   String p_passport_place_registration, Date p_passport_date_registration, String p_code_tax_org,
                   String p_number_tax_registration, String p_code_bank, String p_code_class_credit, String p_code_citizenship,
                   String p_birth_place, String p_code_capacity, Date p_capacity_status_date, String p_capacity_status_place,
                   String p_num_certif_capacity, String p_phone_home, String p_phone_mobile, String p_email_address,
                   String p_pension_sertif_serial, String p_code_gender, String p_code_nation, String p_code_birth_region,
                   String p_code_birth_distr, String p_type_document, Date p_passport_date_expiration,
                   String p_code_adr_region, String p_code_adr_distr, String p_inps, String p_family, String p_first_name,
                   String p_patronymic, String p_last_name_cyr, String p_first_name_cyr, String p_patronymic_cyr,
                   String p_pass_place_region, String p_pass_place_district, String p_post_address_street,
                   String p_post_address_house, String p_post_adress_flast, String p_pinfl, String sign_100,
                           String activity_type, String addressCountry, Date date_open1, Date date_close1, int sign_date_open, int sign_date_close, String post_address_country) {
        super();
        this.id = id;
		this.post_address_country = post_address_country;
        this.branch = branch;
        this.id_client = id_client;
        this.name = name;
        this.code_country = code_country;
        this.code_type = code_type;
        this.code_resident = code_resident;
        this.code_subject = code_subject;
        this.sign_registr = sign_registr;
        this.code_form = code_form;
        this.date_open = date_open;
        this.date_close = date_close;
        this.state = state;
        this.kod_err = kod_err;
        this.file_name = file_name;
        this.subbranch = subbranch;
        this.j_short_name = j_short_name;
        this.j_place_regist_name = j_place_regist_name;
        this.j_date_registration = j_date_registration;
        this.j_number_registration_doc = j_number_registration_doc;
        this.j_code_tax_org = j_code_tax_org;
        this.j_number_tax_registration = j_number_tax_registration;
        this.j_code_sector = j_code_sector;
        this.j_code_sector_old = j_code_sector_old;
        this.j_code_organ_direct = j_code_organ_direct;
        this.j_code_head_organization = j_code_head_organization;
        this.j_code_class_credit = j_code_class_credit;
        this.j_director_name = j_director_name;
        this.j_director_passport = j_director_passport;
        this.j_chief_accounter_name = j_chief_accounter_name;
        this.j_chief_accounter_passport = j_chief_accounter_passport;
        this.j_code_bank = j_code_bank;
        this.j_account = j_account;
        this.j_post_address = j_post_address;
        this.j_phone = j_phone;
        this.j_fax = j_fax;
        this.j_email = j_email;
        this.j_sign_trade = j_sign_trade;
        this.j_opf = j_opf;
        this.j_soato = j_soato;
        this.j_okpo = j_okpo;
        this.j_inn_head_organization = j_inn_head_organization;
        this.j_region = j_region;
        this.j_distr = j_distr;
        this.j_small_business = j_small_business;
        this.j_director_type_document = j_director_type_document;
        this.j_director_passp_serial = j_director_passp_serial;
        this.j_director_passp_number = j_director_passp_number;
        this.j_director_passp_date_reg = j_director_passp_date_reg;
        this.j_director_passp_place_reg = j_director_passp_place_reg;
        this.j_director_passp_date_end = j_director_passp_date_end;
        this.j_director_code_citizenship = j_director_code_citizenship;
        this.j_director_birthday = j_director_birthday;
        this.j_director_birth_place = j_director_birth_place;
        this.j_director_address = j_director_address;
        this.j_accountant_type_document = j_accountant_type_document;
        this.j_accountant_passp_serial = j_accountant_passp_serial;
        this.j_accountant_passp_number = j_accountant_passp_number;
        this.j_accountant_passp_date_reg = j_accountant_passp_date_reg;
        this.j_accountant_passp_place_reg = j_accountant_passp_place_reg;
        this.j_accountant_passp_date_end = j_accountant_passp_date_end;
        this.j_accountant_code_citizenship = j_accountant_code_citizenship;
        this.j_accountant_birthday = j_accountant_birthday;
        this.j_accountant_birth_place = j_accountant_birth_place;
        this.j_accountant_address = j_accountant_address;
        this.j_327 = j_327;
        this.j_patent_expiration = j_patent_expiration;
        this.j_responsible_emp = j_responsible_emp;
        this.i_short_name = i_short_name;
        this.i_date_registration = i_date_registration;
        this.i_number_registration_doc = i_number_registration_doc;
        this.i_place_regist_name = i_place_regist_name;
        this.i_number_tax_registration = i_number_tax_registration;
        this.i_opf = i_opf;
        this.i_form = i_form;
        this.i_sector = i_sector;
        this.i_organ_direct = i_organ_direct;
        this.i_account = i_account;
        this.i_post_address = i_post_address;
        this.i_director_name = i_director_name;
        this.i_chief_accounter_name = i_chief_accounter_name;
        this.i_phone = i_phone;
        this.i_fax = i_fax;
        this.i_email = i_email;
        this.p_birthday = p_birthday;
        this.p_post_address = p_post_address;
        this.p_passport_type = p_passport_type;
        this.p_passport_serial = p_passport_serial;
        this.p_passport_number = p_passport_number;
        this.p_passport_place_registration = p_passport_place_registration;
        this.p_passport_date_registration = p_passport_date_registration;
        this.p_code_tax_org = p_code_tax_org;
        this.p_number_tax_registration = p_number_tax_registration;
        this.p_code_bank = p_code_bank;
        this.p_code_class_credit = p_code_class_credit;
        this.p_code_citizenship = p_code_citizenship;
        this.p_birth_place = p_birth_place;
        this.p_code_capacity = p_code_capacity;
        this.p_capacity_status_date = p_capacity_status_date;
        this.p_capacity_status_place = p_capacity_status_place;
        this.p_num_certif_capacity = p_num_certif_capacity;
        this.p_phone_home = p_phone_home;
        this.p_phone_mobile = p_phone_mobile;
        this.p_email_address = p_email_address;
        this.p_pension_sertif_serial = p_pension_sertif_serial;
        this.p_code_gender = p_code_gender;
        this.p_code_nation = p_code_nation;
        this.p_code_birth_region = p_code_birth_region;
        this.p_code_birth_distr = p_code_birth_distr;
        this.p_type_document = p_type_document;
        this.p_passport_date_expiration = p_passport_date_expiration;
        this.p_code_adr_region = p_code_adr_region;
        this.p_code_adr_distr = p_code_adr_distr;
        this.p_inps = p_inps;
        this.p_family = p_family;
        this.p_first_name = p_first_name;
        this.p_patronymic = p_patronymic;
        this.p_last_name_cyr = p_last_name_cyr;
        this.p_first_name_cyr = p_first_name_cyr;
        this.p_patronymic_cyr = p_patronymic_cyr;
        this.p_pass_place_region = p_pass_place_region;
        this.p_pass_place_district = p_pass_place_district;
        this.p_post_address_street = p_post_address_street;
        this.p_post_address_house = p_post_address_house;
        this.p_post_address_flat = p_post_adress_flast;
        this.p_pinfl = p_pinfl;
        this.sign_100 = sign_100;
        this.j_type_activity = activity_type;
        this.addressCountry = addressCountry;
        this.date_open1=date_open1;
        this.date_close1=date_close1;
        this.sign_date_open=sign_date_open;
        this.sign_date_close=sign_date_close;
        this.checkedInAtaccama=false;
    }

    public Person getDirector() {
        if (director == null) {
            director = new Person();
        }
        return director;
    }

    public Person getAccountant() {
        if (accountant == null) {
            accountant = new Person();
        }
        return accountant;
    }

    public void setIndividualEnterpreneur(IndividualEnterpreneur individualEnterpreneur) {
        this.individualEnterpreneur = individualEnterpreneur;
    }

    public void setIndividualEnterpreneurClean(IndividualEnterpreneur individualEnterpreneur) {
        this.individualEnterpreneur = individualEnterpreneur;

        this.p_birth_place = individualEnterpreneur.getCustomer().getP_birth_place();
        this.p_birthday = individualEnterpreneur.getCustomer().getP_birthday();
        this.p_capacity_status_date = individualEnterpreneur.getCustomer().getP_capacity_status_date();
        this.p_capacity_status_place = individualEnterpreneur.getCustomer().getP_capacity_status_place();
        this.p_code_adr_distr = individualEnterpreneur.getCustomer().getP_code_adr_distr();
        this.p_code_adr_region = individualEnterpreneur.getCustomer().getP_code_adr_region();
        this.p_code_bank = individualEnterpreneur.getCustomer().getP_code_bank();
        this.p_code_birth_distr = individualEnterpreneur.getCustomer().getP_code_birth_distr();
        this.p_code_birth_region = individualEnterpreneur.getCustomer().getP_code_birth_region();
        this.p_code_capacity = individualEnterpreneur.getCustomer().getP_code_capacity();
        this.p_code_citizenship = individualEnterpreneur.getCustomer().getP_code_citizenship();
        this.p_code_class_credit = individualEnterpreneur.getCustomer().getP_code_class_credit();
        this.p_code_gender = individualEnterpreneur.getCustomer().getP_code_gender();
        this.p_code_nation = individualEnterpreneur.getCustomer().getP_code_nation();
        this.p_code_tax_org = individualEnterpreneur.getCustomer().getP_code_tax_org();
        this.p_email_address = individualEnterpreneur.getCustomer().getP_email_address();
        this.p_family = individualEnterpreneur.getCustomer().getP_family();
        this.p_first_name = individualEnterpreneur.getCustomer().getP_first_name();
        this.p_inps = individualEnterpreneur.getCustomer().getP_inps();
        this.p_num_certif_capacity = individualEnterpreneur.getCustomer().getP_num_certif_capacity();
        this.p_number_tax_registration = individualEnterpreneur.getCustomer().getP_number_tax_registration();
        this.p_pass_place_district = individualEnterpreneur.getCustomer().getP_pass_place_district();
        this.p_pass_place_region = individualEnterpreneur.getCustomer().getP_pass_place_region();
        this.p_passport_date_expiration = individualEnterpreneur.getCustomer().getP_passport_date_expiration();
        this.p_passport_date_registration = individualEnterpreneur.getCustomer()
                .getP_passport_date_registration();
        this.p_passport_number = individualEnterpreneur.getCustomer().getP_passport_number();
        this.p_passport_place_registration = individualEnterpreneur.getCustomer()
                .getP_passport_place_registration();
        this.p_passport_serial = individualEnterpreneur.getCustomer().getP_passport_serial();
        this.p_passport_type = individualEnterpreneur.getCustomer().getP_passport_type();
        this.p_patronymic = individualEnterpreneur.getCustomer().getP_patronymic();
        this.p_pension_sertif_serial = individualEnterpreneur.getCustomer().getP_pension_sertif_serial();
        this.p_phone_home = individualEnterpreneur.getCustomer().getP_phone_home();
        this.p_phone_mobile = individualEnterpreneur.getCustomer().getP_phone_mobile();
        this.p_post_address = individualEnterpreneur.getCustomer().getP_post_address();
        this.p_post_address_flat = individualEnterpreneur.getCustomer().getP_post_address_flat();
        this.p_post_address_house = individualEnterpreneur.getCustomer().getP_post_address_house();
        this.p_post_address_quarter = individualEnterpreneur.getCustomer().getP_post_address_quarter();
        this.p_post_address_street = individualEnterpreneur.getCustomer().getP_post_address_street();
        this.p_type_document = individualEnterpreneur.getCustomer().getP_type_document();
        this.p_last_name_cyr = individualEnterpreneur.getCustomer().getP_family_local();
        this.p_first_name_cyr = individualEnterpreneur.getCustomer().getP_first_name_local();
        this.p_patronymic_cyr = individualEnterpreneur.getCustomer().getP_patronymic_local();

        this.j_number_tax_registration = individualEnterpreneur.getCustomer().getP_number_tax_registration();
        this.j_code_tax_org = individualEnterpreneur.getCustomer().getP_code_tax_org();
        this.j_region = individualEnterpreneur.getCustomer().getP_pass_place_region();
        this.j_distr = individualEnterpreneur.getCustomer().getP_pass_place_district();
        this.j_post_address = individualEnterpreneur.getCustomer().getP_post_address();
    }

    public void updateIndividualEnterpreneur() {
        if (individualEnterpreneur != null) {
            individualEnterpreneur.getCustomer().setName(this.name);
            individualEnterpreneur.getCustomer().setP_birth_place(this.p_birth_place);
            individualEnterpreneur.getCustomer().setP_birthday(this.p_birthday);
            individualEnterpreneur.getCustomer().setP_capacity_status_date(this.p_capacity_status_date);
            individualEnterpreneur.getCustomer().setP_capacity_status_place(this.p_capacity_status_place);
            individualEnterpreneur.getCustomer().setP_code_adr_distr(this.p_code_adr_distr);
            individualEnterpreneur.getCustomer().setP_code_adr_region(this.p_code_adr_region);
            individualEnterpreneur.getCustomer().setP_code_bank(this.p_code_bank);
            individualEnterpreneur.getCustomer().setP_code_birth_distr(this.p_code_birth_distr);
            individualEnterpreneur.getCustomer().setP_code_birth_region(this.p_code_birth_region);
            individualEnterpreneur.getCustomer().setP_code_capacity(this.p_code_capacity);
            individualEnterpreneur.getCustomer().setP_code_citizenship(this.p_code_citizenship);
            individualEnterpreneur.getCustomer().setP_code_class_credit(this.p_code_class_credit);
            individualEnterpreneur.getCustomer().setP_code_gender(this.p_code_gender);
            individualEnterpreneur.getCustomer().setP_code_nation(this.p_code_nation);
            individualEnterpreneur.getCustomer().setP_code_tax_org(this.p_code_tax_org);
            individualEnterpreneur.getCustomer().setP_email_address(this.p_email_address);
            individualEnterpreneur.getCustomer().setP_family(this.p_family);
            individualEnterpreneur.getCustomer().setP_first_name(this.p_first_name);
            individualEnterpreneur.getCustomer().setP_inps(this.p_inps);
            individualEnterpreneur.getCustomer().setP_num_certif_capacity(this.p_num_certif_capacity);
            individualEnterpreneur.getCustomer().setP_number_tax_registration(this.p_number_tax_registration);
            individualEnterpreneur.getCustomer().setP_pass_place_district(this.p_pass_place_district);
            individualEnterpreneur.getCustomer().setP_pass_place_region(this.p_pass_place_region);
            individualEnterpreneur.getCustomer().setP_passport_date_expiration(this.p_passport_date_expiration);
            individualEnterpreneur.getCustomer()
                    .setP_passport_date_registration(this.p_passport_date_registration);
            individualEnterpreneur.getCustomer().setP_passport_number(this.p_passport_number);
            individualEnterpreneur.getCustomer()
                    .setP_passport_place_registration(this.p_passport_place_registration);
            individualEnterpreneur.getCustomer().setP_passport_serial(this.p_passport_serial);
            individualEnterpreneur.getCustomer().setP_passport_type(this.p_passport_type);
            individualEnterpreneur.getCustomer().setP_patronymic(this.p_patronymic);
            individualEnterpreneur.getCustomer().setP_pension_sertif_serial(this.p_pension_sertif_serial);
            individualEnterpreneur.getCustomer().setP_phone_home(this.p_phone_home);
            individualEnterpreneur.getCustomer().setP_phone_mobile(this.p_phone_mobile);
            individualEnterpreneur.getCustomer().setP_post_address(this.p_post_address);
            individualEnterpreneur.getCustomer().setP_post_address_flat(this.p_post_address_flat);
            individualEnterpreneur.getCustomer().setP_post_address_house(this.p_post_address_house);
            individualEnterpreneur.getCustomer().setP_post_address_quarter(this.p_post_address_quarter);
            individualEnterpreneur.getCustomer().setP_post_address_street(this.p_post_address_street);
            individualEnterpreneur.getCustomer().setP_type_document(this.p_type_document);
            individualEnterpreneur.getCustomer().setP_family_local(this.p_last_name_cyr);
            individualEnterpreneur.getCustomer().setP_first_name_local(this.p_first_name_cyr);
            individualEnterpreneur.getCustomer().setP_patronymic_local(this.p_patronymic_cyr);

            individualEnterpreneur.getCustomer().setBranch(branch);
            individualEnterpreneur.getCustomer().setCode_country(code_country);
            individualEnterpreneur.getCustomer().setCode_form(null);
            individualEnterpreneur.getCustomer().setCode_resident(code_resident);
            individualEnterpreneur.getCustomer().setCode_subject("P");
            individualEnterpreneur.getCustomer().setCode_type("08");
            individualEnterpreneur.getCustomer().setSign_registr(2);
            individualEnterpreneur.getCustomer().setState("2");
            individualEnterpreneur.getCustomer().setId("A" + id_client);
        }

    }

    public void createIndividualEnterpreneur() {
        individualEnterpreneur = new IndividualEnterpreneur(new Customer(), "create");
        updateIndividualEnterpreneur();
    }


    public void fillDirectors() {
        if (director != null) {
            j_director_name = director.personName();
            j_director_passport = director.getPassport_serial() + director.getPassport_number();
            j_director_type_document = director.getType_document();
            j_director_passp_serial = director.getPassport_serial();
            j_director_passp_number = director.getPassport_number();
            j_director_passp_date_end = director.getPassport_date_expiration();
            j_director_passp_date_reg = director.getPassport_date_registration();
            j_director_passp_place_reg = director.getPassport_place_registration();
            j_director_code_citizenship = director.getCode_citizenship();
            j_director_birthday = director.getBirthday();
            j_director_birth_place = director.getBirth_place();
            j_director_address = director.getPost_address();
        }
        if (accountant != null) {
            j_chief_accounter_name = accountant.personName();
            j_chief_accounter_passport = accountant.getPassport_serial() + accountant.getPassport_number();
            j_accountant_type_document = accountant.getType_document();
            j_accountant_passp_serial = accountant.getPassport_serial();
            j_accountant_passp_number = accountant.getPassport_number();
            j_accountant_passp_date_end = accountant.getPassport_date_expiration();
            j_accountant_passp_date_reg = accountant.getPassport_date_registration();
            j_accountant_passp_place_reg = accountant.getPassport_place_registration();
            j_accountant_code_citizenship = accountant.getCode_citizenship();
            j_accountant_birthday = accountant.getBirthday();
            j_accountant_birth_place = accountant.getBirth_place();
            j_accountant_address = accountant.getPost_address();
        }
    }


    @Override
    public ClientJ clone() throws CloneNotSupportedException {
        return (ClientJ) super.clone();
    }

    public void rollBackObjectiveChanges(final ClientJ oldClient) {
        Util.interateThroughFields(ClientJ.class, this, new FieldHandler() {

            @Override
            public void eval(Field field, Object local) throws IllegalAccessException {
                try {
                    if (ClientFields.valueOf(field.getName().toUpperCase()) == null) {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                }
                Object curr = field.get(local);
                Object old = field.get(oldClient);
                field.setAccessible(true);
                if (ClientFields.valueOf(field.getName().toUpperCase()).isObjectiveField() &&
                        Util.hasChanges(curr, old)) {
                    field.set(curr, old);
                }
            }
        });
    }

    public void setClientData(ClientJ client) {
        /*if (id_client != null) {
            setNotObjectiveData(client);
        } else {
            setManualData(client);
        }*/
        setManualData(client);
    }

    public void setManualData(final ClientJ other) {
        Util.interateThroughFields(ClientJ.class, this, new FieldHandler() {

            @Override
            public void eval(Field field, Object local) throws IllegalAccessException {
                try {
                    if (ClientFields.valueOf(field.getName().toUpperCase()) == null) {
                        throw new IllegalArgumentException("Illegal field " + field.getName());
                    }
                }
                catch(IllegalArgumentException e){
                    throw new IllegalArgumentException(e.getMessage());
                }
                if (ClientFields.valueOf(field.getName().toUpperCase()).isManual()) {
                    Object otherObject = field.get(other);
                    field.set(local, otherObject);
                }
            }
        });
    }

    public void setNotObjectiveData(final ClientJ other) {
        ISLogger.getLogger().error("EBP set Not Objective Data");
        Util.interateThroughFields(ClientJ.class, this, new FieldHandler() {

            @Override
            public void eval(Field field, Object obj1) throws IllegalAccessException {
                try {
                    if (ClientFields.valueOf(field.getName().toUpperCase()) == null) {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                }
                if (!ClientFields.valueOf(field.getName().toUpperCase()).isObjectiveField()
                        && ClientFields.valueOf(field.getName().toUpperCase()).isManual()) {
                    field.set(obj1, field.get(other));
                }
            }
        });
    }

    public boolean hasObjectiveChanges(ClientJ old) {
        Field[] fields = ClientJ.class.getDeclaredFields();

        for (Field f : fields) {
            try {
                if (ClientFields.valueOf(f.getName().toUpperCase()) == null ||
                        !ClientFields.valueOf(f.getName().toUpperCase()).isObjectiveField()) {
                    //continue;
                //}
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                continue;
            }
            try {
                if (Util.hasChanges(f.get(this), f.get(old))) {
                    //ISLogger.getLogger().error("Change " + f.getName());
                    return true;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean equalsWithoutInn(ClientJ other) {
        if (other == null) {
            return false;
        }
        return this.j_number_registration_doc.equals(other.getJ_number_registration_doc())
                && this.j_date_registration.equals(other.getJ_date_registration())
                && this.j_opf.equals(other.getJ_opf()) && this.code_form.equals(other.getCode_form());
    }

    public void resolveSignRegistry(boolean isNibbd) {
        if (code_type.equalsIgnoreCase("08")) {
            this.sign_registr = 2;
            return;
        }
        /*if (code_type.equalsIgnoreCase("04")){
            this.sign_registr = 4;
            return;
        }*/
        if (isNibbd)
            this.sign_registr = 3;
        else
            this.sign_registr = 1;
    }

    public String concatenateShortName(){
        // Если фамилия имя и отчество пустые отправлять по умолчанию -> значит он из nibbd
        if (concatenateNames() != null && concatenateNames().trim().isEmpty()){
            return this.j_short_name;
        }
        String shortName;
        if (this.getCode_type().equalsIgnoreCase(ClientUtil.CODE_TYPE_IP) || this.getCode_type().equalsIgnoreCase(ClientUtil.CODE_TYPE_SE)) {
            String prefix = "YATT";
            if (!StringUtils.isBlank(this.getJ_opf()) && this.getJ_opf().equals("430")){
                prefix = "YATOT";
            }
            shortName = String.format("%s %s", prefix, concatenateNames());
        }
        else
            shortName = (this.j_short_name != null ? this.j_short_name : this.name);

        return shortName.length() > 25 ? shortName.substring(0, 25) : shortName;
    }

    public String concatenateFullName(){
        // Если фамилия имя и отчество пустые отправлять по умолчанию -> значит он из nibbd
        if (concatenateNames() != null && concatenateNames().trim().isEmpty()){
            return this.name;
        }

        String prefix = "YATT";
        if (this.getCode_type().equalsIgnoreCase(ClientUtil.CODE_TYPE_IP) || this.getCode_type().equalsIgnoreCase(ClientUtil.CODE_TYPE_SE)  ) {
            if (!StringUtils.isBlank(this.getJ_opf()) && this.getJ_opf().equals("430")){
                prefix = "YATOT";
            }

            return String.format("%s %s",prefix,concatenateNames());
        }
        return this.name;
    }

    public String concatenateNames(){
        String lastName = "",firstName = "", patronymic = "";

        if (this.getP_family() != null)
            lastName = this.getP_family();
        if (this.getP_last_name_cyr() != null)
            lastName = this.getP_last_name_cyr();

        if (this.getP_first_name() != null)
            firstName = this.getP_first_name();
        if (this.getP_first_name_cyr() != null)
            firstName = this.getP_first_name_cyr();

        if (this.getP_patronymic() != null)
            patronymic = this.getP_patronymic();
        if (this.getP_patronymic_cyr() != null)
            patronymic = this.getP_patronymic_cyr();

        return String.format("%s %s %s", lastName.trim(), firstName.trim(),
                patronymic.trim());
    }

    public boolean isIP() {
        return ( this.code_type.equalsIgnoreCase(ClientUtil.CODE_TYPE_IP) || this.code_type.equalsIgnoreCase(ClientUtil.CODE_TYPE_SE));
    }
}
