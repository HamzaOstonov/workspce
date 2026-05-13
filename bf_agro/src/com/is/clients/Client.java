package com.is.clients;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {

    static final long serialVersionUID = 15L;

	private Long id;
	private String branch;
	private String id_client;
	private String name;
	private String code_country;
	private String code_type;
	private String code_resident;
	private String code_subject;
	private Long sign_registr;
	private String code_form;
	private Date date_open;
	private Date date_close;
	private Long state;
	private Long kod_err;
	private String file_name;
	private String j_short_name;
	private String j_place_regist_name;
	private Date j_date_registration;
	private String j_number_registration_doc;
	private String j_code_tax_org;
	private String j_number_tax_registration;
	private String j_code_sector;
	private String j_code_organ_direct;
	private String j_code_head_organization;
	private String j_code_class_credit;
	private String j_director_name;
	private String j_director_passport;
	private String j_chief_accounter_name;
	private String j_chief_accounter_passport;
	private String j_code_bank;
	private String j_account;
	private String j_post_address;
	private String j_phone;
	private String j_fax;
	private String j_email;
	private String j_sign_trade;
	private String j_opf;
	private String j_soato;
	private String j_okpo;
	private String j_inn_head_organization;
	private String j_region;
	private String j_distr;
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
	private String p_family;
	private String p_first_name;
	private String p_patronymic;



    public Client() {
		super();
    }

    public Client(Long id, String branch, String id_client, String name, String code_country, String code_type, String code_resident, String code_subject, Long sign_registr, String code_form, Date date_open, Date date_close, Long state, Long kod_err, String file_name, String j_short_name, String j_place_regist_name, Date j_date_registration, String j_number_registration_doc, String j_code_tax_org, String j_number_tax_registration, String j_code_sector, String j_code_organ_direct, String j_code_head_organization, String j_code_class_credit, String j_director_name, String j_director_passport, String j_chief_accounter_name, String j_chief_accounter_passport, String j_code_bank, String j_account, String j_post_address, String j_phone, String j_fax, String j_email, String j_sign_trade, String j_opf, String j_soato, String j_okpo, String j_inn_head_organization, String j_region, String j_distr, Date p_birthday, String p_post_address, String p_passport_type, String p_passport_serial, String p_passport_number, String p_passport_place_registration, Date p_passport_date_registration, String p_code_tax_org, String p_number_tax_registration, String p_code_bank, String p_code_class_credit, String p_code_citizenship, String p_birth_place, String p_code_capacity, Date p_capacity_status_date, String p_capacity_status_place, String p_num_certif_capacity, String p_phone_home, String p_phone_mobile, String p_email_address, String p_pension_sertif_serial, String p_code_gender, String p_code_nation, String p_code_birth_region, String p_code_birth_distr, String p_type_document, Date p_passport_date_expiration, String p_code_adr_region, String p_code_adr_distr, String p_inps, String p_family, String p_first_name, String p_patronymic) {
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
		this.j_short_name = j_short_name;
		this.j_place_regist_name = j_place_regist_name;
		this.j_date_registration = j_date_registration;
		this.j_number_registration_doc = j_number_registration_doc;
		this.j_code_tax_org = j_code_tax_org;
		this.j_number_tax_registration = j_number_tax_registration;
		this.j_code_sector = j_code_sector;
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
    }
    

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
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

	public Long getSign_registr() { 
		return sign_registr;
	} 

	public void setSign_registr(Long sign_registr) { 
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

	public Long getState() { 
		return state;
	} 

	public void setState(Long state) { 
		this.state = state;
	} 

	public Long getKod_err() { 
		return kod_err;
	} 

	public void setKod_err(Long kod_err) { 
		this.kod_err = kod_err;
	} 

	public String getFile_name() { 
		return file_name;
	} 

	public void setFile_name(String file_name) { 
		this.file_name = file_name;
	} 

	public String getJ_short_name() { 
		return j_short_name;
	} 

	public void setJ_short_name(String j_short_name) { 
		this.j_short_name = j_short_name;
	} 

	public String getJ_place_regist_name() { 
		return j_place_regist_name;
	} 

	public void setJ_place_regist_name(String j_place_regist_name) { 
		this.j_place_regist_name = j_place_regist_name;
	} 

	public Date getJ_date_registration() { 
		return j_date_registration;
	} 

	public void setJ_date_registration(Date j_date_registration) { 
		this.j_date_registration = j_date_registration;
	} 

	public String getJ_number_registration_doc() { 
		return j_number_registration_doc;
	} 

	public void setJ_number_registration_doc(String j_number_registration_doc) { 
		this.j_number_registration_doc = j_number_registration_doc;
	} 

	public String getJ_code_tax_org() { 
		return j_code_tax_org;
	} 

	public void setJ_code_tax_org(String j_code_tax_org) { 
		this.j_code_tax_org = j_code_tax_org;
	} 

	public String getJ_number_tax_registration() { 
		return j_number_tax_registration;
	} 

	public void setJ_number_tax_registration(String j_number_tax_registration) { 
		this.j_number_tax_registration = j_number_tax_registration;
	} 

	public String getJ_code_sector() { 
		return j_code_sector;
	} 

	public void setJ_code_sector(String j_code_sector) { 
		this.j_code_sector = j_code_sector;
	} 

	public String getJ_code_organ_direct() { 
		return j_code_organ_direct;
	} 

	public void setJ_code_organ_direct(String j_code_organ_direct) { 
		this.j_code_organ_direct = j_code_organ_direct;
	} 

	public String getJ_code_head_organization() { 
		return j_code_head_organization;
	} 

	public void setJ_code_head_organization(String j_code_head_organization) { 
		this.j_code_head_organization = j_code_head_organization;
	} 

	public String getJ_code_class_credit() { 
		return j_code_class_credit;
	} 

	public void setJ_code_class_credit(String j_code_class_credit) { 
		this.j_code_class_credit = j_code_class_credit;
	} 

	public String getJ_director_name() { 
		return j_director_name;
	} 

	public void setJ_director_name(String j_director_name) { 
		this.j_director_name = j_director_name;
	} 

	public String getJ_director_passport() { 
		return j_director_passport;
	} 

	public void setJ_director_passport(String j_director_passport) { 
		this.j_director_passport = j_director_passport;
	} 

	public String getJ_chief_accounter_name() { 
		return j_chief_accounter_name;
	} 

	public void setJ_chief_accounter_name(String j_chief_accounter_name) { 
		this.j_chief_accounter_name = j_chief_accounter_name;
	} 

	public String getJ_chief_accounter_passport() { 
		return j_chief_accounter_passport;
	} 

	public void setJ_chief_accounter_passport(String j_chief_accounter_passport) { 
		this.j_chief_accounter_passport = j_chief_accounter_passport;
	} 

	public String getJ_code_bank() { 
		return j_code_bank;
	} 

	public void setJ_code_bank(String j_code_bank) { 
		this.j_code_bank = j_code_bank;
	} 

	public String getJ_account() { 
		return j_account;
	} 

	public void setJ_account(String j_account) { 
		this.j_account = j_account;
	} 

	public String getJ_post_address() { 
		return j_post_address;
	} 

	public void setJ_post_address(String j_post_address) { 
		this.j_post_address = j_post_address;
	} 

	public String getJ_phone() { 
		return j_phone;
	} 

	public void setJ_phone(String j_phone) { 
		this.j_phone = j_phone;
	} 

	public String getJ_fax() { 
		return j_fax;
	} 

	public void setJ_fax(String j_fax) { 
		this.j_fax = j_fax;
	} 

	public String getJ_email() { 
		return j_email;
	} 

	public void setJ_email(String j_email) { 
		this.j_email = j_email;
	} 

	public String getJ_sign_trade() { 
		return j_sign_trade;
	} 

	public void setJ_sign_trade(String j_sign_trade) { 
		this.j_sign_trade = j_sign_trade;
	} 

	public String getJ_opf() { 
		return j_opf;
	} 

	public void setJ_opf(String j_opf) { 
		this.j_opf = j_opf;
	} 

	public String getJ_soato() { 
		return j_soato;
	} 

	public void setJ_soato(String j_soato) { 
		this.j_soato = j_soato;
	} 

	public String getJ_okpo() { 
		return j_okpo;
	} 

	public void setJ_okpo(String j_okpo) { 
		this.j_okpo = j_okpo;
	} 

	public String getJ_inn_head_organization() { 
		return j_inn_head_organization;
	} 

	public void setJ_inn_head_organization(String j_inn_head_organization) { 
		this.j_inn_head_organization = j_inn_head_organization;
	} 

	public String getJ_region() { 
		return j_region;
	} 

	public void setJ_region(String j_region) { 
		this.j_region = j_region;
	} 

	public String getJ_distr() { 
		return j_distr;
	} 

	public void setJ_distr(String j_distr) { 
		this.j_distr = j_distr;
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
    

}
