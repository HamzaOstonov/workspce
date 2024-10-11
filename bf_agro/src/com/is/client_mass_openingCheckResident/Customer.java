package com.is.client_mass_openingCheckResident;


import java.util.Date;

import org.joda.time.DateTime;


/**
 * Created by root on 06.05.2017.
 * 21:02
 */
@SuppressWarnings("ALL")


public class Customer implements Cloneable{
    private Long longId;
    private String idSap;
    private String branch;
    private String id;
    private String name;
    private String code_country;
    private String code_type;
    private String code_resident;
    private String code_subject;
    private int sign_registr;
    private String code_form;
    private Date date_open;
    private Date date_close;
    private String state;
    private int kod_err;
    private String file_name;
    private String p_family;
    private String p_first_name;
    private String p_patronymic;
    private String p_family_local;
    private String p_first_name_local;
    private String p_patronymic_local;
    private Date p_birthday;
    private String p_post_address;
    private String p_passport_type;
    private String p_passport_serial;
    private String p_passport_number;
    private String p_passport_place_registration;
    private Date p_passport_date_registration;
    private String p_code_tax_org;
    private String p_number_tax_registration;
    private String p_code_bank;
    private String p_code_class_credit;
    private String p_code_citizenship;
    private String p_birth_place;
    private String p_code_capacity;
    private Date p_capacity_status_date;
    private String p_capacity_status_place;
    private String p_num_certif_capacity;
    private String p_phone_home;
    private String p_phone_mobile;
    private String p_email_address;
    private String p_pension_sertif_serial;
    private String p_code_gender;
    private String p_code_nation;
    private String p_code_birth_region;
    private String p_code_birth_distr;
    private String p_type_document;
    private Date p_passport_date_expiration;
    private String p_code_adr_region;
    private String p_code_adr_distr;
    private String p_inps;
    private String p_pinfl;
    private String p_pass_place_region;
    private String p_pass_place_district;
    private String p_post_address_street;
    private String p_post_address_street_code;
    private String p_post_address_house;
    private String p_post_address_flat;
    private String p_post_address_quarter;
    private String j_327;
    private String sign_100;
    private String name_search;
    private Date date_open1;
    private Date date_close1;
    private int sign_date_open;
    private int sign_date_close;
    private Date p_date_open_nibbd;
    private Date p_date_close_nibbd;
    private String p_id_nibbd;
    private String p_close_type;
    private String p_close_doc_n;
    private Date p_close_doc_d;
    private String p_drive_permit_num;
    private String p_drive_permit_ser;
    private Date p_drive_permit_reg_d;
    private Date p_drive_permit_exp_d;
    private String p_drive_permit_place;
    private String p_agreement;
    private InternalControl internalControl;
    private Account account;
    private String union_id;
    private boolean isForceCreated;
    private String emp_id;
    //private String j_parent_branch;   /*esli customer yavlyaetsa direktorom yurliso, to eto pole iz client_j.branch*/  
    private String parent_id_client_j; /*esli customer yavlyaetsa direktorom yurliso, to eto pole iz client_j.id*/
    private String person_role; /*esli customer yavlyaetsa direktorom/buxgalterom yurliso, to ukazivaet kakoe iz nix direktorom ili buxgalter*/
    //private boolean isCheckedStoplistAtaccama;
    private String sign_public_official;
    private String po_job_title;
    private String po_welfare_source;
    private String po_other_data;
    private String subbranch;

    // Был ли создан в SAP до этого или нет
    // признак SAP
    private boolean i014;

    public Customer() {
    }

    public Customer(Long longId, String idSap,
                    String branch, String id,
                    String name, String code_country,
                    String code_type, String code_resident,
                    String code_subject, int sign_registr,
                    String code_form, Date date_open, Date date_close,
                    String state, int kod_err,
                    String file_name, String p_family,
                    String p_first_name, String p_patronymic,
                    String p_family_local, String p_first_name_local,
                    String p_patronymic_local, Date p_birthday,
                    String p_post_address, String p_passport_type,
                    String p_passport_serial, String p_passport_number,
                    String p_passport_place_registration,
                    Date p_passport_date_registration,
                    String p_code_tax_org,
                    String p_number_tax_registration, String p_code_bank,
                    String p_code_class_credit, String p_code_citizenship,
                    String p_birth_place, String p_code_capacity,
                    Date p_capacity_status_date,
                    String p_capacity_status_place,
                    String p_num_certif_capacity,
                    String p_phone_home,
                    String p_phone_mobile,
                    String p_email_address, String p_pension_sertif_serial, String p_code_gender,
                    String p_code_nation, String p_code_birth_region,
                    String p_code_birth_distr,
                    String p_type_document,
                    Date p_passport_date_expiration,
                    String p_code_adr_region,
                    String p_code_adr_distr,
                    String p_inps,
                    String p_pinfl,
                    String p_pass_place_region, String p_pass_place_district, String p_post_address_street,
                    String p_post_address_street_code, String p_post_address_house,
                    String p_post_address_flat, String p_post_address_quarter,
                    String j_327, String sign_100, 
                    String name_search,
                    Date date_open1, Date date_close1,   
                    int sign_date_open, int sign_date_close,
                    Date date_open_nibbd,
                    Date date_close_nibbd,
                    String id_nibbd,
                    String close_type,
                    String close_doc_n,
                    Date close_doc_d,
                    String drive_permit_num,
                    String drive_permit_ser,
                    Date drive_permit_reg_d,
                    Date drive_permit_exp_d,
                    String drive_permit_place,
                    String agreement,
                    String unionId,
                    String subbranch) {
        this.longId = longId;
        this.idSap = idSap;
        this.branch = branch;
        this.id = id;
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
        this.p_family = p_family;
        this.p_first_name = p_first_name;
        this.p_patronymic = p_patronymic;
        this.p_family_local = p_family_local;
        this.p_first_name_local = p_first_name_local;
        this.p_patronymic_local = p_patronymic_local;
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
        this.p_pinfl = p_pinfl;
        this.p_pass_place_region = p_pass_place_region;
        this.p_pass_place_district = p_pass_place_district;
        this.p_post_address_street = p_post_address_street;
        this.p_post_address_street_code = p_post_address_street_code;
        this.p_post_address_house = p_post_address_house;
        this.p_post_address_flat = p_post_address_flat;
        this.p_post_address_quarter = p_post_address_quarter;
        this.j_327 = j_327;
        this.sign_100 = sign_100;
        this.name_search = name_search;
        this.date_open1=date_open1;
        this.date_close1=date_close1;
        this.sign_date_open=sign_date_open;
        this.sign_date_close=sign_date_close;
        this.p_date_open_nibbd=date_open_nibbd;
        this.p_date_close_nibbd=date_close_nibbd;
        this.p_id_nibbd=id_nibbd;
        this.p_close_type=close_type;
        this.p_close_doc_n=close_doc_n;
        this.p_close_doc_d=close_doc_d;
        this.p_drive_permit_num=drive_permit_num;
        this.p_drive_permit_ser=drive_permit_ser;
        this.p_drive_permit_reg_d=drive_permit_reg_d;
        this.p_drive_permit_exp_d=drive_permit_exp_d;
        this.p_drive_permit_place=drive_permit_place;
        this.p_agreement=agreement;
        this.union_id = unionId;
        this.subbranch = subbranch;
    }
    
    
    
    public Long getLongId() {
		return longId;
	}

	public void setLongId(Long longId) {
		this.longId = longId;
	}

	public String getIdSap() {
		return idSap;
	}

	public void setIdSap(String idSap) {
		this.idSap = idSap;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode_country() {
		return code_country;
	}

	public void setCode_country(String code_country) {
		this.code_country = code_country;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getCode_resident() {
		return code_resident;
	}

	public void setCode_resident(String code_resident) {
		this.code_resident = code_resident;
	}

	public String getCode_subject() {
		return code_subject;
	}

	public void setCode_subject(String code_subject) {
		this.code_subject = code_subject;
	}

	public int getSign_registr() {
		return sign_registr;
	}

	public void setSign_registr(int sign_registr) {
		this.sign_registr = sign_registr;
	}

	public String getCode_form() {
		return code_form;
	}

	public void setCode_form(String code_form) {
		this.code_form = code_form;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getKod_err() {
		return kod_err;
	}

	public void setKod_err(int kod_err) {
		this.kod_err = kod_err;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getP_family() {
		return p_family;
	}

	public void setP_family(String p_family) {
		this.p_family = p_family;
	}

	public String getP_first_name() {
		return p_first_name;
	}

	public void setP_first_name(String p_first_name) {
		this.p_first_name = p_first_name;
	}

	public String getP_patronymic() {
		return p_patronymic;
	}

	public void setP_patronymic(String p_patronymic) {
		this.p_patronymic = p_patronymic;
	}

	public String getP_family_local() {
		return p_family_local;
	}

	public void setP_family_local(String p_family_local) {
		this.p_family_local = p_family_local;
	}

	public String getP_first_name_local() {
		return p_first_name_local;
	}

	public void setP_first_name_local(String p_first_name_local) {
		this.p_first_name_local = p_first_name_local;
	}

	public String getP_patronymic_local() {
		return p_patronymic_local;
	}

	public void setP_patronymic_local(String p_patronymic_local) {
		this.p_patronymic_local = p_patronymic_local;
	}

	public Date getP_birthday() {
		return p_birthday;
	}

	public void setP_birthday(Date p_birthday) {
		this.p_birthday = p_birthday;
	}

	public String getP_post_address() {
		return p_post_address;
	}

	public void setP_post_address(String p_post_address) {
		this.p_post_address = p_post_address;
	}

	public String getP_passport_type() {
		return p_passport_type;
	}

	public void setP_passport_type(String p_passport_type) {
		this.p_passport_type = p_passport_type;
	}

	public String getP_passport_serial() {
		return p_passport_serial;
	}

	public void setP_passport_serial(String p_passport_serial) {
		this.p_passport_serial = p_passport_serial;
	}

	public String getP_passport_number() {
		return p_passport_number;
	}

	public void setP_passport_number(String p_passport_number) {
		this.p_passport_number = p_passport_number;
	}

	public String getP_passport_place_registration() {
		return p_passport_place_registration;
	}

	public void setP_passport_place_registration(String p_passport_place_registration) {
		this.p_passport_place_registration = p_passport_place_registration;
	}

	public Date getP_passport_date_registration() {
		return p_passport_date_registration;
	}

	public void setP_passport_date_registration(Date p_passport_date_registration) {
		this.p_passport_date_registration = p_passport_date_registration;
	}

	public String getP_code_tax_org() {
		return p_code_tax_org;
	}

	public void setP_code_tax_org(String p_code_tax_org) {
		this.p_code_tax_org = p_code_tax_org;
	}

	public String getP_number_tax_registration() {
		return p_number_tax_registration;
	}

	public void setP_number_tax_registration(String p_number_tax_registration) {
		this.p_number_tax_registration = p_number_tax_registration;
	}

	public String getP_code_bank() {
		return p_code_bank;
	}

	public void setP_code_bank(String p_code_bank) {
		this.p_code_bank = p_code_bank;
	}

	public String getP_code_class_credit() {
		return p_code_class_credit;
	}

	public void setP_code_class_credit(String p_code_class_credit) {
		this.p_code_class_credit = p_code_class_credit;
	}

	public String getP_code_citizenship() {
		return p_code_citizenship;
	}

	public void setP_code_citizenship(String p_code_citizenship) {
		this.p_code_citizenship = p_code_citizenship;
	}

	public String getP_birth_place() {
		return p_birth_place;
	}

	public void setP_birth_place(String p_birth_place) {
		this.p_birth_place = p_birth_place;
	}

	public String getP_code_capacity() {
		return p_code_capacity;
	}

	public void setP_code_capacity(String p_code_capacity) {
		this.p_code_capacity = p_code_capacity;
	}

	public Date getP_capacity_status_date() {
		return p_capacity_status_date;
	}

	public void setP_capacity_status_date(Date p_capacity_status_date) {
		this.p_capacity_status_date = p_capacity_status_date;
	}

	public String getP_capacity_status_place() {
		return p_capacity_status_place;
	}

	public void setP_capacity_status_place(String p_capacity_status_place) {
		this.p_capacity_status_place = p_capacity_status_place;
	}

	public String getP_num_certif_capacity() {
		return p_num_certif_capacity;
	}

	public void setP_num_certif_capacity(String p_num_certif_capacity) {
		this.p_num_certif_capacity = p_num_certif_capacity;
	}

	public String getP_phone_home() {
		return p_phone_home;
	}

	public void setP_phone_home(String p_phone_home) {
		this.p_phone_home = p_phone_home;
	}

	public String getP_phone_mobile() {
		return p_phone_mobile;
	}

	public void setP_phone_mobile(String p_phone_mobile) {
		this.p_phone_mobile = p_phone_mobile;
	}

	public String getP_email_address() {
		return p_email_address;
	}

	public void setP_email_address(String p_email_address) {
		this.p_email_address = p_email_address;
	}

	public String getP_pension_sertif_serial() {
		return p_pension_sertif_serial;
	}

	public void setP_pension_sertif_serial(String p_pension_sertif_serial) {
		this.p_pension_sertif_serial = p_pension_sertif_serial;
	}

	public String getP_code_gender() {
		return p_code_gender;
	}

	public void setP_code_gender(String p_code_gender) {
		this.p_code_gender = p_code_gender;
	}

	public String getP_code_nation() {
		return p_code_nation;
	}

	public void setP_code_nation(String p_code_nation) {
		this.p_code_nation = p_code_nation;
	}

	public String getP_code_birth_region() {
		return p_code_birth_region;
	}

	public void setP_code_birth_region(String p_code_birth_region) {
		this.p_code_birth_region = p_code_birth_region;
	}

	public String getP_code_birth_distr() {
		return p_code_birth_distr;
	}

	public void setP_code_birth_distr(String p_code_birth_distr) {
		this.p_code_birth_distr = p_code_birth_distr;
	}

	public String getP_type_document() {
		return p_type_document;
	}

	public void setP_type_document(String p_type_document) {
		this.p_type_document = p_type_document;
	}

	public Date getP_passport_date_expiration() {
		return p_passport_date_expiration;
	}

	public void setP_passport_date_expiration(Date p_passport_date_expiration) {
		this.p_passport_date_expiration = p_passport_date_expiration;
	}

	public String getP_code_adr_region() {
		return p_code_adr_region;
	}

	public void setP_code_adr_region(String p_code_adr_region) {
		this.p_code_adr_region = p_code_adr_region;
	}

	public String getP_code_adr_distr() {
		return p_code_adr_distr;
	}

	public void setP_code_adr_distr(String p_code_adr_distr) {
		this.p_code_adr_distr = p_code_adr_distr;
	}

	public String getP_inps() {
		return p_inps;
	}

	public void setP_inps(String p_inps) {
		this.p_inps = p_inps;
	}

	public String getP_pinfl() {
		return p_pinfl;
	}

	public void setP_pinfl(String p_pinfl) {
		this.p_pinfl = p_pinfl;
	}

	public String getP_pass_place_region() {
		return p_pass_place_region;
	}

	public void setP_pass_place_region(String p_pass_place_region) {
		this.p_pass_place_region = p_pass_place_region;
	}

	public String getP_pass_place_district() {
		return p_pass_place_district;
	}

	public void setP_pass_place_district(String p_pass_place_district) {
		this.p_pass_place_district = p_pass_place_district;
	}

	public String getP_post_address_street() {
		return p_post_address_street;
	}

	public void setP_post_address_street(String p_post_address_street) {
		this.p_post_address_street = p_post_address_street;
	}

	public String getP_post_address_street_code() {
		return p_post_address_street_code;
	}

	public void setP_post_address_street_code(String p_post_address_street_code) {
		this.p_post_address_street_code = p_post_address_street_code;
	}

	public String getP_post_address_house() {
		return p_post_address_house;
	}

	public void setP_post_address_house(String p_post_address_house) {
		this.p_post_address_house = p_post_address_house;
	}

	public String getP_post_address_flat() {
		return p_post_address_flat;
	}

	public void setP_post_address_flat(String p_post_address_flat) {
		this.p_post_address_flat = p_post_address_flat;
	}

	public String getP_post_address_quarter() {
		return p_post_address_quarter;
	}

	public void setP_post_address_quarter(String p_post_address_quarter) {
		this.p_post_address_quarter = p_post_address_quarter;
	}

	public String getJ_327() {
		return j_327;
	}

	public void setJ_327(String j_327) {
		this.j_327 = j_327;
	}

	public String getSign_100() {
		return sign_100;
	}

	public void setSign_100(String sign_100) {
		this.sign_100 = sign_100;
	}

	public String getName_search() {
		return name_search;
	}

	public void setName_search(String name_search) {
		this.name_search = name_search;
	}

	public Date getDate_open1() {
		return date_open1;
	}

	public void setDate_open1(Date date_open1) {
		this.date_open1 = date_open1;
	}

	public Date getDate_close1() {
		return date_close1;
	}

	public void setDate_close1(Date date_close1) {
		this.date_close1 = date_close1;
	}

	public int getSign_date_open() {
		return sign_date_open;
	}

	public void setSign_date_open(int sign_date_open) {
		this.sign_date_open = sign_date_open;
	}

	public int getSign_date_close() {
		return sign_date_close;
	}

	public void setSign_date_close(int sign_date_close) {
		this.sign_date_close = sign_date_close;
	}

	public Date getP_date_open_nibbd() {
		return p_date_open_nibbd;
	}

	public void setP_date_open_nibbd(Date p_date_open_nibbd) {
		this.p_date_open_nibbd = p_date_open_nibbd;
	}

	public Date getP_date_close_nibbd() {
		return p_date_close_nibbd;
	}

	public void setP_date_close_nibbd(Date p_date_close_nibbd) {
		this.p_date_close_nibbd = p_date_close_nibbd;
	}

	public String getP_id_nibbd() {
		return p_id_nibbd;
	}

	public void setP_id_nibbd(String p_id_nibbd) {
		this.p_id_nibbd = p_id_nibbd;
	}

	public String getP_close_type() {
		return p_close_type;
	}

	public void setP_close_type(String p_close_type) {
		this.p_close_type = p_close_type;
	}

	public String getP_close_doc_n() {
		return p_close_doc_n;
	}

	public void setP_close_doc_n(String p_close_doc_n) {
		this.p_close_doc_n = p_close_doc_n;
	}

	public Date getP_close_doc_d() {
		return p_close_doc_d;
	}

	public void setP_close_doc_d(Date p_close_doc_d) {
		this.p_close_doc_d = p_close_doc_d;
	}

	public String getP_drive_permit_num() {
		return p_drive_permit_num;
	}

	public void setP_drive_permit_num(String p_drive_permit_num) {
		this.p_drive_permit_num = p_drive_permit_num;
	}

	public String getP_drive_permit_ser() {
		return p_drive_permit_ser;
	}

	public void setP_drive_permit_ser(String p_drive_permit_ser) {
		this.p_drive_permit_ser = p_drive_permit_ser;
	}

	public Date getP_drive_permit_reg_d() {
		return p_drive_permit_reg_d;
	}

	public void setP_drive_permit_reg_d(Date p_drive_permit_reg_d) {
		this.p_drive_permit_reg_d = p_drive_permit_reg_d;
	}

	public Date getP_drive_permit_exp_d() {
		return p_drive_permit_exp_d;
	}

	public void setP_drive_permit_exp_d(Date p_drive_permit_exp_d) {
		this.p_drive_permit_exp_d = p_drive_permit_exp_d;
	}

	public String getP_drive_permit_place() {
		return p_drive_permit_place;
	}

	public void setP_drive_permit_place(String p_drive_permit_place) {
		this.p_drive_permit_place = p_drive_permit_place;
	}

	public String getP_agreement() {
		return p_agreement;
	}

	public void setP_agreement(String p_agreement) {
		this.p_agreement = p_agreement;
	}

	public InternalControl getInternalControl() {
		return internalControl;
	}

	public void setInternalControl(InternalControl internalControl) {
		this.internalControl = internalControl;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUnion_id() {
		return union_id;
	}

	public void setUnion_id(String union_id) {
		this.union_id = union_id;
	}

	public boolean isForceCreated() {
		return isForceCreated;
	}

	public void setForceCreated(boolean isForceCreated) {
		this.isForceCreated = isForceCreated;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getParent_id_client_j() {
		return parent_id_client_j;
	}

	public void setParent_id_client_j(String parent_id_client_j) {
		this.parent_id_client_j = parent_id_client_j;
	}

	public String getPerson_role() {
		return person_role;
	}

	public void setPerson_role(String person_role) {
		this.person_role = person_role;
	}

	public String getSign_public_official() {
		return sign_public_official;
	}

	public void setSign_public_official(String sign_public_official) {
		this.sign_public_official = sign_public_official;
	}

	public String getPo_job_title() {
		return po_job_title;
	}

	public void setPo_job_title(String po_job_title) {
		this.po_job_title = po_job_title;
	}

	public String getPo_welfare_source() {
		return po_welfare_source;
	}

	public void setPo_welfare_source(String po_welfare_source) {
		this.po_welfare_source = po_welfare_source;
	}

	public String getPo_other_data() {
		return po_other_data;
	}

	public void setPo_other_data(String po_other_data) {
		this.po_other_data = po_other_data;
	}

	public String getSubbranch() {
		return subbranch;
	}

	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}

	public boolean isI014() {
		return i014;
	}

	public void setI014(boolean i014) {
		this.i014 = i014;
	}

	public void initBaseAttributes(String sessionBranch){
        this.setSign_registr(2);
        this.setCode_subject("P");
        this.setCode_type("08");
        this.setP_passport_type("N");
        this.setBranch(sessionBranch);
        this.setP_code_bank(sessionBranch);
    }

    public void initCreationAttributes(String sessionBranch) {
        this.setSign_registr(2);
        this.setCode_subject("P");
        this.setCode_type("08");
        this.setP_code_bank(sessionBranch);
        this.setBranch(sessionBranch);
        this.setP_code_citizenship("860");
        this.setP_passport_type("N");
        this.setCode_country("860");
        this.setCode_resident("1");
        this.setP_type_document("6");
        this.setP_code_capacity("0801");
    }

    public int getIntState(){
        return state == null || state.trim().isEmpty() ? 0 : Integer.parseInt(state);
    }

    public String getFullName(){
        return String.format("%s %s %s",
                this.p_family_local,
                this.p_first_name_local,
                this.p_patronymic_local);
                //this.p_family,
                //this.p_first_name,
                //this.p_patronymic);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean isExist(){
        return branch != null && id != null ? true : false;
    }

    public boolean isSAPCustomer() {
        return idSap == null ? false : true;
    }

    public boolean isBranchCustomer(String sessionBranch) {
        return branch != null && id != null && ( id.startsWith("99") || id.startsWith("6") || id.startsWith("7") || id.startsWith("8") ) && branch.equalsIgnoreCase(sessionBranch);
    }

    public boolean isCustomer(){
        return id != null && id!=null && (id.startsWith("99") ||id.startsWith("6")||id.startsWith("7")||id.startsWith("8") ) && id.length() == 8;
    }

    public boolean isContactCustomer(){
        if (this.branch != null && this.id!=null && this.id.startsWith("A"))
            return true;
        return false;
    }

    public boolean isDocumentDateRegistrationValid(Date operDate) {
        if (p_passport_date_registration == null || operDate == null)
            return false;
        return new DateTime(this.p_passport_date_registration).isAfter(new DateTime(operDate));
    }


}