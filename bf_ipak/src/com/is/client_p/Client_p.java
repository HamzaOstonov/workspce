package com.is.client_p;

import java.io.Serializable;
import java.util.Date;

public class Client_p implements Serializable {

    static final long serialVersionUID = 103844514947365244L;


    private String id;
    private String branch;
    private String id_client;
    private String name;
    private String code_country;
    private String code_type;
    private String code_resident;
    private String code_subject;
    private String sign_registr;
    private String code_form;
    private Date date_open;
    private Date date_close;
    private String state;
    private String kod_err;
    private String file_name;
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
    private String p_code_birth_region; // не исползуется
    private String p_code_birth_distr; // не исползуется
    private String p_type_document;
    private Date p_passport_date_expiration;
    private String p_code_adr_region;
    private String p_code_adr_distr;
    private String p_inps;
    private String p_family;
    private String p_first_name;
    private String p_patronymic;
    private String p_sign_vip;

    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
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

	public String getSign_registr() {
		return sign_registr;
	}

	public void setSign_registr(String sign_registr) {
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

	public String getKod_err() {
		return kod_err;
	}

	public void setKod_err(String kod_err) {
		this.kod_err = kod_err;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
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

	public String getP_sign_vip() {
		return p_sign_vip;
	}

	public void setP_sign_vip(String p_sign_vip) {
		this.p_sign_vip = p_sign_vip;
	}

	public Client_p() {
		super();
	}

	public Client_p(String id, String branch, String id_client, String name, String code_country, String code_type, String code_resident, String code_subject, String sign_registr, String code_form, Date date_open, Date date_close, String state, String kod_err, String file_name, Date p_birthday, String p_post_address, String p_passport_type, String p_passport_serial, String p_passport_number, String p_passport_place_registration, Date p_passport_date_registration, String p_code_tax_org, String p_number_tax_registration, String p_code_bank, String p_code_class_credit, String p_code_citizenship, String p_birth_place, String p_code_capacity, Date p_capacity_status_date, String p_capacity_status_place, String p_num_certif_capacity, String p_phone_home, String p_phone_mobile, String p_email_address, String p_pension_sertif_serial, String p_code_gender, String p_code_nation, String p_code_birth_region, String p_code_birth_distr, String p_type_document, Date p_passport_date_expiration, String p_code_adr_region, String p_code_adr_distr, String p_inps, String p_family, String p_first_name, String p_patronymic, String p_sign_vip) {
		super();
		
                this.id = id;
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
                this.p_sign_vip = p_sign_vip;
	}

}