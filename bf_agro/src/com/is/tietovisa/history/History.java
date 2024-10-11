
package com.is.tietovisa.history;

import java.util.Date;



public class History
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
    private String id_tieto;
    //private String head_customer_id;   
    private String p_pinfl;
    private String p_zip_code;
    private String code_nation_val     ;
    private String code_resident_val   ;
    private String code_adr_distr_val  ;
    private String code_adr_region_val ;
    private String code_gender_val     ;
    private String code_citizenship_val;
    private String code_country_val    ;
    private String type_document_val   ;   
    
    private boolean tieto_exist;
    private boolean sign_error_record;
    private String sign_record; 
    //private String ISSUED_BY;
    //private String PERSON_CODE;

    //private String t_client;
	private String t_bank_c;
	private String t_client_b;
	private String t_cl_type;
	private String t_cln_cat;
	private Date t_rec_date;
	private String t_f_names;
	private String t_surname;
	private String t_title;
	private String t_m_name;
	private String t_midle_name;
	private Date t_b_date;
	private String t_r_street;
	private String t_r_city;
	private String t_r_cntry;
	private String t_usrid;
	private Date t_ctime;
	private String t_status;
	private String t_search_name;
	private String t_sex;
	private String t_serial_no;
	private Date t_doc_since;
	private String t_issued_by;
	private Date t_status_change_date;
	private String t_card;	
	private String t_id_card;
	private String t_resident;
	private String t_person_code;
	private String t_rmob_phone;
	private String t_r_phone;
	private String t_r_emails;
	private String t_r_pcode;
	private String t_region;
	    
    public History() {
    	 this.p_passport_type = "N";
         this.code_subject = "P";
         this.sign_registr = 2;
         this.code_type = "08";
         this.tieto_exist = false;
         this.sign_record = "_";
         this.t_status="10";
    }
    
    public History(final long id, final String branch, final String type) {
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
    
    public History(final long id, final String branch, final String id_client, final String name,
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
    
    public History(final long id, final String branch, final String id_client, final String name,
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
        //this.tieto_customer_id = tieto_customer_id;
        //this.head_customer_id = head_customer_id;
        this.p_pinfl = pinfl;
    }
    
    public String getP_zip_code() {
		return p_zip_code;
	}

	public void setP_zip_code(String p_zip_code) {
		this.p_zip_code = p_zip_code;
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
    
    /*public String getTieto_customer_id() {
        return this.tieto_customer_id;
    }
    
    public void setTieto_customer_id(final String tieto_customer_id) {
        this.tieto_customer_id = tieto_customer_id;
    }*/
    
    //public String getHead_customer_id() {
    //    return this.head_customer_id;
    //}
    
    //public void setHead_customer_id(final String head_customer_id) {
    //    this.head_customer_id = head_customer_id;
    //}
    
    //public String getISSUED_BY() {
    //    return this.ISSUED_BY;
    //}
    
    //public void setISSUED_BY(final String iSSUED_BY) {
    //    this.ISSUED_BY = iSSUED_BY;
    //}
    
    //public String getPERSON_CODE() {
    //    return this.PERSON_CODE;
    //}
    
    //public void setPERSON_CODE(final String pERSON_CODE) {
    //    this.PERSON_CODE = pERSON_CODE;
    //}
    
    public void setP_pinfl(String p_pinfl) {
		this.p_pinfl = p_pinfl;
	}

	public String getP_pinfl() {
		return p_pinfl;
	}
	
	
    public String getCode_nation_val() {
		return code_nation_val;
	}

	public String getCode_resident_val() {
		return code_resident_val;
	}

	public String getCode_adr_distr_val() {
		return code_adr_distr_val;
	}

	public String getCode_adr_region_val() {
		return code_adr_region_val;
	}

	public String getCode_gender_val() {
		return code_gender_val;
	}

	public String getCode_citizenship_val() {
		return code_citizenship_val;
	}

	public String getCode_country_val() {
		return code_country_val;
	}

	public String getType_document_val() {
		return type_document_val;
	}

	public void setCode_nation_val(String code_nation_val) {
		this.code_nation_val = code_nation_val;
	}

	public void setCode_resident_val(String code_resident_val) {
		this.code_resident_val = code_resident_val;
	}

	public void setCode_adr_distr_val(String code_adr_distr_val) {
		this.code_adr_distr_val = code_adr_distr_val;
	}

	public void setCode_adr_region_val(String code_adr_region_val) {
		this.code_adr_region_val = code_adr_region_val;
	}

	public void setCode_gender_val(String code_gender_val) {
		this.code_gender_val = code_gender_val;
	}

	public void setCode_citizenship_val(String code_citizenship_val) {
		this.code_citizenship_val = code_citizenship_val;
	}

	public void setCode_country_val(String code_country_val) {
		this.code_country_val = code_country_val;
	}

	public void setType_document_val(String type_document_val) {
		this.type_document_val = type_document_val;
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
        this.p_family + ", p_first_name=" + this.p_first_name + ", p_patronymic=" + this.p_patronymic + 
          ", t_r_street=" + this.t_r_street + ", t_person_code=" +     this.t_person_code + ", tieto_exist=" + this.tieto_exist  +
          ", id_tieto=" + this.id_tieto + ", t_bank_c=" +     this.t_bank_c + ", t_client_b=" + this.t_client_b  +
          ", t_cl_type=" + this.t_cl_type + ", t_cln_cat=" +     this.t_cln_cat + ", t_f_names=" + this.t_f_names  +
          ", t_surname=" + this.t_surname + ", t_b_date=" +     this.t_b_date + ", t_search_name=" + this.t_search_name  +
          ", t_sex=" + this.t_sex + ", t_serial_no=" +     this.t_serial_no + ", t_doc_since=" + this.t_doc_since  +
          ", t_card=" + this.t_card + ", t_r_city=" +     this.t_r_city + ", t_r_cntry=" + this.t_r_cntry  +
          ", t_status=" + this.t_status + ", t_rmob_phone=" +     this.t_rmob_phone + ", t_resident=" + this.t_resident  +
          ", p_pinfl=" + this.p_pinfl + "]";
    }


	public boolean isTieto_exist() {
		return tieto_exist;
	}

	public void setTieto_exist(boolean tieto_exist) {
		this.tieto_exist = tieto_exist;
	}
	
	
	/*public String getT_client() {
		return t_client;
	}

	public void setT_client(String t_client) {
		this.t_client = t_client;
	}*/

	public String getT_bank_c() {
		return t_bank_c;
	}

	public void setT_bank_c(String t_bank_c) {
		this.t_bank_c = t_bank_c;
	}

	public String getT_client_b() {
		return t_client_b;
	}

	public void setT_client_b(String t_client_b) {
		this.t_client_b = t_client_b;
	}

	public String getT_cl_type() {
		return t_cl_type;
	}

	public void setT_cl_type(String t_cl_type) {
		this.t_cl_type = t_cl_type;
	}

	public String getT_cln_cat() {
		return t_cln_cat;
	}

	public void setT_cln_cat(String t_cln_cat) {
		this.t_cln_cat = t_cln_cat;
	}

	public Date getT_rec_date() {
		return t_rec_date;
	}

	public void setT_rec_date(Date t_rec_date) {
		this.t_rec_date = t_rec_date;
	}

	public String getT_f_names() {
		return t_f_names;
	}

	public void setT_f_names(String t_f_names) {
		this.t_f_names = t_f_names;
	}

	public String getT_surname() {
		return t_surname;
	}

	public void setT_surname(String t_surname) {
		this.t_surname = t_surname;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}

	public String getT_m_name() {
		return t_m_name;
	}

	public void setT_m_name(String t_m_name) {
		this.t_m_name = t_m_name;
	}

	public Date getT_b_date() {
		return t_b_date;
	}

	public void setT_b_date(Date t_b_date) {
		this.t_b_date = t_b_date;
	}

	public String getT_r_street() {
		return t_r_street;
	}

	public void setT_r_street(String t_r_street) {
		this.t_r_street = t_r_street;
	}

	public String getT_r_city() {
		return t_r_city;
	}

	public void setT_r_city(String t_r_city) {
		this.t_r_city = t_r_city;
	}

	public String getT_r_cntry() {
		return t_r_cntry;
	}

	public void setT_r_cntry(String t_r_cntry) {
		this.t_r_cntry = t_r_cntry;
	}

	public String getT_usrid() {
		return t_usrid;
	}

	public void setT_usrid(String t_usrid) {
		this.t_usrid = t_usrid;
	}

	public Date getT_ctime() {
		return t_ctime;
	}

	public void setT_ctime(Date t_ctime) {
		this.t_ctime = t_ctime;
	}

	public String getT_status() {
		return t_status;
	}

	public void setT_status(String t_status) {
		this.t_status = t_status;
	}

	public String getT_search_name() {
		return t_search_name;
	}

	public void setT_search_name(String t_search_name) {
		this.t_search_name = t_search_name;
	}

	public String getT_sex() {
		return t_sex;
	}

	public void setT_sex(String t_sex) {
		this.t_sex = t_sex;
	}

	public String getT_serial_no() {
		return t_serial_no;
	}

	public void setT_serial_no(String t_serial_no) {
		this.t_serial_no = t_serial_no;
	}

	public Date getT_doc_since() {
		return t_doc_since;
	}

	public void setT_doc_since(Date t_doc_since) {
		this.t_doc_since = t_doc_since;
	}

	public String getT_issued_by() {
		return t_issued_by;
	}

	public void setT_issued_by(String t_issued_by) {
		this.t_issued_by = t_issued_by;
	}

	public Date getT_status_change_date() {
		return t_status_change_date;
	}

	public void setT_status_change_date(Date t_status_change_date) {
		this.t_status_change_date = t_status_change_date;
	}

	public String getT_card() {
		return t_card;
	}

	public void setT_card(String t_card) {
		this.t_card = t_card;
	}

	public String getT_id_card() {
		return t_id_card;
	}

	public void setT_id_card(String t_id_card) {
		this.t_id_card = t_id_card;
	}

	public String getT_resident() {
		return t_resident;
	}

	public void setT_resident(String t_resident) {
		this.t_resident = t_resident;
	}

	public String getT_person_code() {
		return t_person_code;
	}

	public void setT_person_code(String t_person_code) {
		this.t_person_code = t_person_code;
	}

	public String getT_rmob_phone() {
		return t_rmob_phone;
	}

	public void setT_rmob_phone(String t_rmob_phone) {
		this.t_rmob_phone = t_rmob_phone;
	}

	public String getT_r_phone() {
		return t_r_phone;
	}

	public void setT_r_phone(String t_r_phone) {
		this.t_r_phone = t_r_phone;
	}

	public String getT_r_emails() {
		return t_r_emails;
	}

	public void setT_r_emails(String t_r_emails) {
		this.t_r_emails = t_r_emails;
	}

	public History clone (History old) {
		History newCust=new History();

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
		newCust.setId_tieto(old.getId_tieto());
		newCust.setP_pinfl(old.getP_pinfl());
		newCust.setP_zip_code(old.getP_zip_code());
		newCust.setTieto_exist(old.isTieto_exist());
		
		return newCust;
	}
    
	public void copyTietoFields (History old, History newCust) {
		//Customer newCust=new Customer();

		newCust.setId_tieto(old.getId_tieto());
		newCust.setT_bank_c(old.getT_bank_c());
		newCust.setT_client_b(old.getT_client_b());
		newCust.setT_cl_type(old.getT_cl_type());
		newCust.setT_cln_cat(old.getT_cln_cat());
		newCust.setT_rec_date(old.getT_rec_date());
		newCust.setT_f_names(old.getT_f_names());
		newCust.setT_surname(old.getT_surname());
		newCust.setT_title(old.getT_title());
		newCust.setT_m_name(old.getT_m_name());
		newCust.setT_b_date(old.getT_b_date());
		newCust.setT_r_street(old.getT_r_street());
		newCust.setT_r_city(old.getT_r_city());
		newCust.setT_r_cntry(old.getT_r_cntry());
		newCust.setT_usrid(old.getT_usrid());
		newCust.setT_ctime(old.getT_ctime());
		newCust.setT_status(old.getT_status());
		newCust.setT_search_name(old.getT_search_name());
		newCust.setT_sex(old.getT_sex());
		newCust.setT_serial_no(old.getT_serial_no());
		newCust.setT_doc_since(old.getT_doc_since());
		newCust.setT_issued_by(old.getT_issued_by());
		newCust.setT_status_change_date(old.getT_status_change_date());
		newCust.setT_card(old.getT_card());
		newCust.setT_id_card(old.getT_id_card());
		newCust.setT_resident(old.getT_resident());
		newCust.setT_person_code(old.getT_person_code());
		newCust.setT_rmob_phone(old.getT_rmob_phone());
		newCust.setT_r_phone(old.getT_r_phone());
		newCust.setT_r_emails(old.getT_r_emails());
		
	}
	
	public static boolean hasChanges(Object obj1, Object obj2) {
		return (obj1 != null && obj2 != null && !obj1.equals(obj2)) 
			|| (obj1 != null && obj2 == null) 
			|| (obj1 == null && obj2 != null);
	}

    public boolean hasBankChanges(History old) {
        
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
    	if (hasChanges(this.getId_tieto(), old.getId_tieto())) {
        		return true;
        }
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

	public boolean isSign_error_record() {
		return sign_error_record;
	}

	public void setSign_error_record(boolean sign_error_record) {
		this.sign_error_record = sign_error_record;
	}

	public String getId_tieto() {
		return id_tieto;
	}

	public void setId_tieto(String id_tieto) {
		this.id_tieto = id_tieto;
	}

	public String getSign_record() {
		return sign_record;
	}

	public void setSign_record(String sign_record) {
		this.sign_record = sign_record;
	}

	public void setT_midle_name(String t_midle_name) {
		this.t_midle_name = t_midle_name;
	}

	public String getT_midle_name() {
		return t_midle_name;
	}

	public void setT_r_pcode(String t_r_pcode) {
		this.t_r_pcode = t_r_pcode;
	}

	public String getT_r_pcode() {
		return t_r_pcode;
	}

	public void setT_region(String t_region) {
		this.t_region = t_region;
	}

	public String getT_region() {
		return t_region;
	}

	}
