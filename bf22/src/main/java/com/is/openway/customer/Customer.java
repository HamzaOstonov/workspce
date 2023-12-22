
package com.is.openway.customer;

import java.util.Date;

import org.apache.axis2.addressing.AddressingConstants.Final;

public class Customer
{
    private long id;
    private String branch;
    private String id_client;
    private String name;
    private String code_country;
    private String code_type;
    private String code_resident;
    private String code_subject;
    private String code_product;
    private int sign_registr;
    private String code_form;
    private Date date_open;
    private Date date_close;
    private int state;
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
    private String tieto_customer_id;
    private String head_customer_id;
   
    private String p_pinfl;
    private String p_zip_code;
   
	private String o_city;
    private String o_client_type;
    private String o_security_name;
    private String o_post_address_fact;
    private Date o_address_fact_date;
    
    private boolean way_exist;
    private String ISSUED_BY;
    private String PERSON_CODE;

    
    public Customer() {
    	 this.p_passport_type = "N";
         this.code_subject = "P";
         this.sign_registr = 2;
         this.code_type = "08";
         this.way_exist = false;
    }
    
    public Customer(final long id, final String branch, final String type) {
        this.id = id;
        this.branch = branch;
        this.code_subject = type;
    }
    
    public String getCode_product() {
        return this.code_product;
    }
    
    public void setCode_product(final String code_product) {
        this.code_product = code_product;
    }
    
    public Customer(final long id, final String branch, final String id_client, final String name,
            final String code_country, final String code_type, final String code_resident,
            final String code_subject, final int sign_registr, final String code_form,
            final Date date_open, final Date date_close, final int state, final Date p_birthday,
            final String p_post_address, final String p_passport_type,
            final String p_passport_serial, final String p_passport_number,
            final String p_passport_place_registration, final Date p_passport_date_registration,
            final String p_code_tax_org, final String p_number_tax_registration,
            final String p_code_bank, final String p_code_class_credit,
            final String p_code_citizenship, final String p_birth_place,
            final String p_code_capacity, final Date p_capacity_status_date,
            final String p_capacity_status_place, final String p_num_certif_capacity,
            final String p_phone_home, final String p_phone_mobile, final String p_email_address,
            final String p_pension_sertif_serial, final String p_code_gender,
            final String p_code_nation, final String p_code_birth_region,
            final String p_code_birth_distr, final String p_type_document,
            final Date p_passport_date_expiration, final String p_code_adr_region,
            final String p_code_adr_distr, final String p_inps, final String p_family,
            final String p_first_name, final String p_patronymic, final String pinfl) {
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
        this.p_pinfl = pinfl;
    }
    
    public Customer(final long id, final String branch, final String id_client, final String name,
            final String code_country, final String code_type, final String code_resident,
            final String code_subject, final int sign_registr, final String code_form,
            final Date date_open, final Date date_close, final int state, final Date p_birthday,
            final String p_post_address, final String p_passport_type,
            final String p_passport_serial, final String p_passport_number,
            final String p_passport_place_registration, final Date p_passport_date_registration,
            final String p_code_tax_org, final String p_number_tax_registration,
            final String p_code_bank, final String p_code_class_credit,
            final String p_code_citizenship, final String p_birth_place,
            final String p_code_capacity, final Date p_capacity_status_date,
            final String p_capacity_status_place, final String p_num_certif_capacity,
            final String p_phone_home, final String p_phone_mobile, final String p_email_address,
            final String p_pension_sertif_serial, final String p_code_gender,
            final String p_code_nation, final String p_code_birth_region,
            final String p_code_birth_distr, final String p_type_document,
            final Date p_passport_date_expiration, final String p_code_adr_region,
            final String p_code_adr_distr, final String p_inps, final String p_family,
            final String p_first_name, final String p_patronymic, final String tieto_customer_id,
            final String head_customer_id, final String pinfl) {
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
        this.tieto_customer_id = tieto_customer_id;
        this.head_customer_id = head_customer_id;
        this.p_pinfl = pinfl;
    }
    
    public String getP_zip_code() {
		return p_zip_code;
	}

	public void setP_zip_code(String p_zip_code) {
		this.p_zip_code = p_zip_code;
	}

	public String getO_city() {
		return o_city;
	}

	public void setO_city(String o_city) {
		this.o_city = o_city;
	}

	public String getO_client_type() {
		return o_client_type;
	}

	public void setO_client_type(String o_client_type) {
		this.o_client_type = o_client_type;
	}

	public String getO_post_address_fact() {
		return o_post_address_fact;
	}

	public void setO_post_address_fact(String o_post_address_fact) {
		this.o_post_address_fact = o_post_address_fact;
	}

	public Date getO_address_fact_date() {
		return o_address_fact_date;
	}

	public void setO_address_fact_date(Date o_address_fact_date) {
		this.o_address_fact_date = o_address_fact_date;
	}

    
    public long getId() {
        return this.id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(final String branch) {
        this.branch = branch;
    }
    
    public String getId_client() {
        return this.id_client;
    }
    
    public void setId_client(final String id_client) {
        this.id_client = id_client;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getCode_country() {
        return this.code_country;
    }
    
    public void setCode_country(final String code_country) {
        this.code_country = code_country;
    }
    
    public String getCode_type() {
        return this.code_type;
    }
    
    public void setCode_type(final String code_type) {
        this.code_type = code_type;
    }
    
    public String getCode_resident() {
        return this.code_resident;
    }
    
    public void setCode_resident(final String code_resident) {
        this.code_resident = code_resident;
    }
    
    public String getCode_subject() {
        return this.code_subject;
    }
    
    public void setCode_subject(final String code_subject) {
        this.code_subject = code_subject;
    }
    
    public int getSign_registr() {
        return this.sign_registr;
    }
    
    public void setSign_registr(final int sign_registr) {
        this.sign_registr = sign_registr;
    }
    
    public String getCode_form() {
        return this.code_form;
    }
    
    public void setCode_form(final String code_form) {
        this.code_form = code_form;
    }
    
    public Date getDate_open() {
        return this.date_open;
    }
    
    public void setDate_open(final Date date_open) {
        this.date_open = date_open;
    }
    
    public Date getDate_close() {
        return this.date_close;
    }
    
    public void setDate_close(final Date date_close) {
        this.date_close = date_close;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(final int state) {
        this.state = state;
    }
    
    public Date getP_birthday() {
        return this.p_birthday;
    }
    
    public void setP_birthday(final Date p_birthday) {
        this.p_birthday = p_birthday;
    }
    
    public String getP_post_address() {
        return this.p_post_address;
    }
    
    public void setP_post_address(final String p_post_address) {
        this.p_post_address = p_post_address;
    }
    
    public String getP_passport_type() {
        return this.p_passport_type;
    }
    
    public void setP_passport_type(final String p_passport_type) {
        this.p_passport_type = p_passport_type;
    }
    
    public String getP_passport_serial() {
        return this.p_passport_serial;
    }
    
    public void setP_passport_serial(final String p_passport_serial) {
        this.p_passport_serial = p_passport_serial;
    }
    
    public String getP_passport_number() {
        return this.p_passport_number;
    }
    
    public void setP_passport_number(final String p_passport_number) {
        this.p_passport_number = p_passport_number;
    }
    
    public String getP_passport_place_registration() {
        return this.p_passport_place_registration;
    }
    
    public void setP_passport_place_registration(final String p_passport_place_registration) {
        this.p_passport_place_registration = p_passport_place_registration;
    }
    
    public Date getP_passport_date_registration() {
        return this.p_passport_date_registration;
    }
    
    public void setP_passport_date_registration(final Date p_passport_date_registration) {
        this.p_passport_date_registration = p_passport_date_registration;
    }
    
    public String getP_code_tax_org() {
        return this.p_code_tax_org;
    }
    
    public void setP_code_tax_org(final String p_code_tax_org) {
        this.p_code_tax_org = p_code_tax_org;
    }
    
    public String getP_number_tax_registration() {
        return this.p_number_tax_registration;
    }
    
    public void setP_number_tax_registration(final String p_number_tax_registration) {
        this.p_number_tax_registration = p_number_tax_registration;
    }
    
    public String getP_code_bank() {
        return this.p_code_bank;
    }
    
    public void setP_code_bank(final String p_code_bank) {
        this.p_code_bank = p_code_bank;
    }
    
    public String getP_code_class_credit() {
        return this.p_code_class_credit;
    }
    
    public void setP_code_class_credit(final String p_code_class_credit) {
        this.p_code_class_credit = p_code_class_credit;
    }
    
    public String getP_code_citizenship() {
        return this.p_code_citizenship;
    }
    
    public void setP_code_citizenship(final String p_code_citizenship) {
        this.p_code_citizenship = p_code_citizenship;
    }
    
    public String getP_birth_place() {
        return this.p_birth_place;
    }
    
    public void setP_birth_place(final String p_birth_place) {
        this.p_birth_place = p_birth_place;
    }
    
    public String getP_code_capacity() {
        return this.p_code_capacity;
    }
    
    public void setP_code_capacity(final String p_code_capacity) {
        this.p_code_capacity = p_code_capacity;
    }
    
    public Date getP_capacity_status_date() {
        return this.p_capacity_status_date;
    }
    
    public void setP_capacity_status_date(final Date p_capacity_status_date) {
        this.p_capacity_status_date = p_capacity_status_date;
    }
    
    public String getP_capacity_status_place() {
        return this.p_capacity_status_place;
    }
    
    public void setP_capacity_status_place(final String p_capacity_status_place) {
        this.p_capacity_status_place = p_capacity_status_place;
    }
    
    public String getP_num_certif_capacity() {
        return this.p_num_certif_capacity;
    }
    
    public void setP_num_certif_capacity(final String p_num_certif_capacity) {
        this.p_num_certif_capacity = p_num_certif_capacity;
    }
    
    public String getP_phone_home() {
        return this.p_phone_home;
    }
    
    public void setP_phone_home(final String p_phone_home) {
        this.p_phone_home = p_phone_home;
    }
    
    public String getP_phone_mobile() {
        return this.p_phone_mobile;
    }
    
    public void setP_phone_mobile(final String p_phone_mobile) {
        this.p_phone_mobile = p_phone_mobile;
    }
    
    public String getP_email_address() {
        return this.p_email_address;
    }
    
    public void setP_email_address(final String p_email_address) {
        this.p_email_address = p_email_address;
    }
    
    public String getP_pension_sertif_serial() {
        return this.p_pension_sertif_serial;
    }
    
    public void setP_pension_sertif_serial(final String p_pension_sertif_serial) {
        this.p_pension_sertif_serial = p_pension_sertif_serial;
    }
    
    public String getP_code_gender() {
        return this.p_code_gender;
    }
    
    public void setP_code_gender(final String p_code_gender) {
        this.p_code_gender = p_code_gender;
    }
    
    public String getP_code_nation() {
        return this.p_code_nation;
    }
    
    public void setP_code_nation(final String p_code_nation) {
        this.p_code_nation = p_code_nation;
    }
    
    public String getP_code_birth_region() {
        return this.p_code_birth_region;
    }
    
    public void setP_code_birth_region(final String p_code_birth_region) {
        this.p_code_birth_region = p_code_birth_region;
    }
    
    public String getP_code_birth_distr() {
        return this.p_code_birth_distr;
    }
    
    public void setP_code_birth_distr(final String p_code_birth_distr) {
        this.p_code_birth_distr = p_code_birth_distr;
    }
    
    public String getP_type_document() {
        return this.p_type_document;
    }
    
    public void setP_type_document(final String p_type_document) {
        this.p_type_document = p_type_document;
    }
    
    public Date getP_passport_date_expiration() {
        return this.p_passport_date_expiration;
    }
    
    public void setP_passport_date_expiration(final Date p_passport_date_expiration) {
        this.p_passport_date_expiration = p_passport_date_expiration;
    }
    
    public String getP_code_adr_region() {
        return this.p_code_adr_region;
    }
    
    public void setP_code_adr_region(final String p_code_adr_region) {
        this.p_code_adr_region = p_code_adr_region;
    }
    
    public String getP_code_adr_distr() {
        return this.p_code_adr_distr;
    }
    
    public void setP_code_adr_distr(final String p_code_adr_distr) {
        this.p_code_adr_distr = p_code_adr_distr;
    }
    
    public String getP_inps() {
        return this.p_inps;
    }
    
    public void setP_inps(final String p_inps) {
        this.p_inps = p_inps;
    }
    
    public String getP_family() {
        return this.p_family;
    }
    
    public void setP_family(final String p_family) {
        this.p_family = ((p_family == null) ? p_family : p_family.toUpperCase());
    }
    
    public String getP_first_name() {
        return this.p_first_name;
    }
    
    public void setP_first_name(final String p_first_name) {
        this.p_first_name = ((p_first_name == null) ? p_first_name : p_first_name.toUpperCase());
    }
    
    public String getP_patronymic() {
        return this.p_patronymic;
    }
    
    public void setP_patronymic(final String p_patronymic) {
        this.p_patronymic = ((p_patronymic == null) ? p_patronymic : p_patronymic.toUpperCase());
    }
    
    public String getTieto_customer_id() {
        return this.tieto_customer_id;
    }
    
    public void setTieto_customer_id(final String tieto_customer_id) {
        this.tieto_customer_id = tieto_customer_id;
    }
    
    public String getHead_customer_id() {
        return this.head_customer_id;
    }
    
    public void setHead_customer_id(final String head_customer_id) {
        this.head_customer_id = head_customer_id;
    }
    
    public String getISSUED_BY() {
        return this.ISSUED_BY;
    }
    
    public void setISSUED_BY(final String iSSUED_BY) {
        this.ISSUED_BY = iSSUED_BY;
    }
    
    public String getPERSON_CODE() {
        return this.PERSON_CODE;
    }
    
    public void setPERSON_CODE(final String pERSON_CODE) {
        this.PERSON_CODE = pERSON_CODE;
    }
    
    public void setP_pinfl(String p_pinfl) {
		this.p_pinfl = p_pinfl;
	}

	public String getP_pinfl() {
		return p_pinfl;
	}
	
    @Override
    public String toString() {
        return "Customer [id=" + this.id + ", branch=" + this.branch + ", id_client=" + this.id_client + ", name=" + this.name + ", code_country=" + this.code_country + ", code_type=" + this.code_type + ", code_resident=" + this.code_resident + ", code_subject=" + this.code_subject + ", code_product=" + this.code_product + ", sign_registr=" + this.sign_registr + ", code_form=" + this.code_form + ", date_open=" + this.date_open + ", date_close=" + this.date_close + ", state=" + this.state + ", p_birthday=" + this.p_birthday + ", p_post_address=" + this.p_post_address + ", p_passport_type=" + this.p_passport_type + ", p_passport_serial=" + this.p_passport_serial + ", p_passport_number=" + this.p_passport_number + ", p_passport_place_registration=" + this.p_passport_place_registration + ", p_passport_date_registration=" + this.p_passport_date_registration + ", p_code_tax_org=" + this.p_code_tax_org + ", p_number_tax_registration=" + 
        this.p_number_tax_registration + ", p_code_bank=" + this.p_code_bank + ", p_code_class_credit=" + 
        this.p_code_class_credit + ", p_code_citizenship=" + this.p_code_citizenship + ", p_birth_place=" + 
        this.p_birth_place + ", p_code_capacity=" + this.p_code_capacity + ", p_capacity_status_date=" + 
        this.p_capacity_status_date + ", p_capacity_status_place=" + this.p_capacity_status_place + ", p_num_certif_capacity=" + 
        this.p_num_certif_capacity + ", p_phone_home=" + this.p_phone_home + ", p_phone_mobile=" + this.p_phone_mobile + ", p_email_address=" + 
        this.p_email_address + ", p_pension_sertif_serial=" + this.p_pension_sertif_serial + ", p_code_gender=" + 
        this.p_code_gender + ", p_code_nation=" + this.p_code_nation + ", p_code_birth_region=" + 
        this.p_code_birth_region + ", p_code_birth_distr=" + this.p_code_birth_distr + ", p_type_document=" + 
        this.p_type_document + ", p_passport_date_expiration=" + this.p_passport_date_expiration + ", p_code_adr_region=" + 
        this.p_code_adr_region + ", p_code_adr_distr=" + this.p_code_adr_distr + ", p_inps=" + this.p_inps + ", p_family=" + 
        this.p_family + ", p_first_name=" + this.p_first_name + ", p_patronymic=" + this.p_patronymic + ", tieto_customer_id=" + 
        this.tieto_customer_id + ", head_customer_id=" + this.head_customer_id + ", o_security_name=" + this.o_security_name + ", o_city=" + 
        this.o_city + ", way_exist=" + this.way_exist  + ", p_pinfl=" + this.p_pinfl + "]";
    }

	public void setO_security_name(String o_security_name) {
		this.o_security_name = o_security_name;
	}

	public String getO_security_name() {
		return o_security_name;
	}

	public boolean isWay_exist() {
		return way_exist;
	}

	public void setWay_exist(boolean way_exist) {
		this.way_exist = way_exist;
	}

	public Customer clone (Customer old) {
		Customer newCust=new Customer();

		newCust.setId(old.getId());
		newCust.setBranch(old.getBranch());
		newCust.setId_client(old.getId_client());
		newCust.setName(old.getName());
		newCust.setCode_country(old.getCode_country());
		newCust.setCode_type(old.getCode_type());
		newCust.setCode_resident(old.getCode_resident());
		newCust.setCode_subject(old.getCode_subject());
		newCust.setCode_product(old.getCode_product());
		newCust.setSign_registr(old.getSign_registr());
		newCust.setCode_form(old.getCode_form());
		newCust.setDate_open(old.getDate_open());
		newCust.setDate_close(old.getDate_close());
		newCust.setState(old.getState());
		newCust.setP_birthday(old.getP_birthday());
		newCust.setP_post_address(old.getP_post_address());
		newCust.setP_passport_type(old.getP_passport_type());
		newCust.setP_passport_serial(old.getP_passport_serial());
		newCust.setP_passport_number(old.getP_passport_number());
		newCust.setP_passport_place_registration(old.getP_passport_place_registration());
		newCust.setP_passport_date_registration(old.getP_passport_date_registration());
		newCust.setP_code_tax_org(old.getP_code_tax_org());
		newCust.setP_number_tax_registration(old.getP_number_tax_registration());
		newCust.setP_code_bank(old.getP_code_bank());
		newCust.setP_code_class_credit(old.getP_code_class_credit());
		newCust.setP_code_citizenship(old.getP_code_citizenship());
		newCust.setP_birth_place(old.getP_birth_place());
		newCust.setP_code_capacity(old.getP_code_capacity());
		newCust.setP_capacity_status_date(old.getP_capacity_status_date());
		newCust.setP_capacity_status_place(old.getP_capacity_status_place());
		newCust.setP_num_certif_capacity(old.getP_num_certif_capacity());
		newCust.setP_phone_home(old.getP_phone_home());
		newCust.setP_phone_mobile(old.getP_phone_mobile());
		newCust.setP_email_address(old.getP_email_address());
		newCust.setP_pension_sertif_serial(old.getP_pension_sertif_serial());
		newCust.setP_code_gender(old.getP_code_gender());
		newCust.setP_code_nation(old.getP_code_nation());
		newCust.setP_code_birth_region(old.getP_code_birth_region());
		newCust.setP_code_birth_distr(old.getP_code_birth_distr());
		newCust.setP_type_document(old.getP_type_document());
		newCust.setP_passport_date_expiration(old.getP_passport_date_expiration());
		newCust.setP_code_adr_region(old.getP_code_adr_region());
		newCust.setP_code_adr_distr(old.getP_code_adr_distr());
		newCust.setP_inps(old.getP_inps());
		newCust.setP_family(old.getP_family());
		newCust.setP_first_name(old.getP_first_name());
		newCust.setP_patronymic(old.getP_patronymic());
		newCust.setTieto_customer_id(old.getTieto_customer_id());
		newCust.setHead_customer_id(old.getHead_customer_id());
		newCust.setP_pinfl(old.getP_pinfl());
		newCust.setP_zip_code(old.getP_zip_code());
		newCust.setO_client_type(old.getO_client_type());
		newCust.setO_security_name(old.getO_security_name());
		newCust.setO_post_address_fact(old.getO_post_address_fact());
		newCust.setO_address_fact_date(old.getO_address_fact_date());
		newCust.setWay_exist(old.isWay_exist());
		newCust.setISSUED_BY(old.getISSUED_BY());
		newCust.setPERSON_CODE(old.getPERSON_CODE());
		
		return newCust;
	}
    
	public static boolean hasChanges(Object obj1, Object obj2) {
		return (obj1 != null && obj2 != null && !obj1.equals(obj2)) 
			|| (obj1 != null && obj2 == null) 
			|| (obj1 == null && obj2 != null);
	}

    public boolean hasBankChanges(Customer old) {
        
    	if (hasChanges(this.getId(), old.getId())) {
    		return true;
        }
    	
    	if (hasChanges(this.getBranch(), old.getBranch())) {
    		return true;
        }
		if (hasChanges(this.getId_client(), old.getId_client())) {
    		return true;
        }
		if (hasChanges(this.getName(), old.getName())) {
    		return true;
        }
		if (hasChanges(this.getCode_country(), old.getCode_country())) {
    		return true;
        }
		if (hasChanges(this.getCode_type(), old.getCode_type())) {
    		return true;
        }
		if (hasChanges(this.getCode_resident(), old.getCode_resident())) {
    		return true;
        }
		if (hasChanges(this.getCode_subject(), old.getCode_subject())) {
    		return true;
        }
		if (hasChanges(this.getCode_product(), old.getCode_product())) {
    		return true;
        }
		if (hasChanges(this.getSign_registr(), old.getSign_registr())) {
    		return true;
        }
		if (hasChanges(this.getCode_form(), old.getCode_form())) {
    		return true;
        }
		if (hasChanges(this.getDate_open(), old.getDate_open())) {
    		return true;
        }
		if (hasChanges(this.getDate_close(), old.getDate_close())) {
    		return true;
        }
		if (hasChanges(this.getState(), old.getState())) {
    		return true;
        }
		if (hasChanges(this.getP_birthday(), old.getP_birthday())) {
    		return true;
        }
		if (hasChanges(this.getP_post_address(), old.getP_post_address())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_type(), old.getP_passport_type())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_serial(), old.getP_passport_serial())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_number(), old.getP_passport_number())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_place_registration(), old.getP_passport_place_registration())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_date_registration(), old.getP_passport_date_registration())) {
    		return true;
        }
		if (hasChanges(this.getP_code_tax_org(), old.getP_code_tax_org())) {
    		return true;
        }
		if (hasChanges(this.getP_number_tax_registration(), old.getP_number_tax_registration())) {
    		return true;
        }
		if (hasChanges(this.getP_code_bank(), old.getP_code_bank())) {
    		return true;
        }
		if (hasChanges(this.getP_code_class_credit(), old.getP_code_class_credit())) {
    		return true;
        }
		if (hasChanges(this.getP_code_citizenship(), old.getP_code_citizenship())) {
    		return true;
        }
		if (hasChanges(this.getP_birth_place(), old.getP_birth_place())) {
    		return true;
        }
		if (hasChanges(this.getP_code_capacity(), old.getP_code_capacity())) {
    		return true;
        }
		if (hasChanges(this.getP_capacity_status_date(), old.getP_capacity_status_date())) {
    		return true;
        }
		if (hasChanges(this.getP_capacity_status_place(), old.getP_capacity_status_place())) {
    		return true;
        }
		if (hasChanges(this.getP_num_certif_capacity(), old.getP_num_certif_capacity())) {
    		return true;
        }
		if (hasChanges(this.getP_phone_home(), old.getP_phone_home())) {
    		return true;
        }
		if (hasChanges(this.getP_phone_mobile(), old.getP_phone_mobile())) {
    		return true;
        }
		if (hasChanges(this.getP_email_address(), old.getP_email_address())) {
    		return true;
        }
		if (hasChanges(this.getP_pension_sertif_serial(), old.getP_pension_sertif_serial())) {
    		return true;
        }
		if (hasChanges(this.getP_code_gender(), old.getP_code_gender())) {
    		return true;
        }
		if (hasChanges(this.getP_code_nation(), old.getP_code_nation())) {
    		return true;
        }
		if (hasChanges(this.getP_code_birth_region(), old.getP_code_birth_region())) {
    		return true;
        }
		if (hasChanges(this.getP_code_birth_distr(), old.getP_code_birth_distr())) {
    		return true;
        }
		if (hasChanges(this.getP_type_document(), old.getP_type_document())) {
    		return true;
        }
		if (hasChanges(this.getP_passport_date_expiration(), old.getP_passport_date_expiration())) {
    		return true;
        }
		if (hasChanges(this.getP_code_adr_region(), old.getP_code_adr_region())) {
    		return true;
        }
		if (hasChanges(this.getP_code_adr_distr(), old.getP_code_adr_distr())) {
    		return true;
        }
		if (hasChanges(this.getP_inps(), old.getP_inps())) {
    		return true;
        }
		if (hasChanges(this.getP_family(), old.getP_family())) {
    		return true;
        }
		if (hasChanges(this.getP_first_name(), old.getP_first_name())) {
    		return true;
        }
		if (hasChanges(this.getP_patronymic(), old.getP_patronymic())) {
    		return true;
        }
		
    	/*if (hasChanges(this.getTieto_customer_id(), old.getTieto_customer_id())) {
        		return true;
            }
    		if (hasChanges(this.getHead_customer_id(), old.getHead_customer_id())) {
        		return true;
            }*/
		if (hasChanges(this.getP_pinfl(), old.getP_pinfl())) {
    		return true;
        }
		if (hasChanges(this.getP_zip_code(), old.getP_zip_code())) {
    		return true;
        }

		/*if (hasChanges(this.getO_client_type(), old.getO_client_type())) {
        		return true;
            }
    		if (hasChanges(this.getO_security_name(), old.getO_security_name())) {
        		return true;
            }
    		if (hasChanges(this.getO_post_address_fact(), old.getO_post_address_fact())) {
        		return true;
            }
    		if (hasChanges(this.getO_address_fact_date(), old.getO_address_fact_date())) {
        		return true;
            }
    		if (hasChanges(this.isWay_exist(), old.isWay_exist())) {
        		return true;
            }
    		if (hasChanges(this.getISSUED_BY(), old.getISSUED_BY())) {
        		return true;
            }
    		if (hasChanges(this.getPERSON_CODE(), old.getPERSON_CODE())) {
        		return true;
            }*/
    	
        return false;
    }

	}
