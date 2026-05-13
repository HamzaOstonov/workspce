// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

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
    private String acode_tel;
    private String R_CITY;
    private String R_STREET;
    private String R_E_MAILS;
    private String R_MOB_PHONE;
    private String R_PHONE;
    private String R_CNTRY;
    private String ISSUED_BY;
    private String PERSON_CODE;
    private String pinfl;
    
    public Customer() {
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
        this.pinfl = pinfl;
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
        this.pinfl = pinfl;
    }
    
    public String getAcode_tel() {
        return this.acode_tel;
    }
    
    public void setAcode_tel(final String acode_tel) {
        this.acode_tel = acode_tel;
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
    
    public String getR_CITY() {
        return this.R_CITY;
    }
    
    public void setR_CITY(final String r_CITY) {
        this.R_CITY = r_CITY;
    }
    
    public String getR_STREET() {
        return this.R_STREET;
    }
    
    public void setR_STREET(final String r_STREET) {
        this.R_STREET = r_STREET;
    }
    
    public String getR_E_MAILS() {
        return this.R_E_MAILS;
    }
    
    public void setR_E_MAILS(final String r_E_MAILS) {
        this.R_E_MAILS = r_E_MAILS;
    }
    
    public String getR_MOB_PHONE() {
        return this.R_MOB_PHONE;
    }
    
    public void setR_MOB_PHONE(final String r_MOB_PHONE) {
        this.R_MOB_PHONE = r_MOB_PHONE;
    }
    
    public String getR_PHONE() {
        return this.R_PHONE;
    }
    
    public void setR_PHONE(final String r_PHONE) {
        this.R_PHONE = r_PHONE;
    }
    
    public String getR_CNTRY() {
        return this.R_CNTRY;
    }
    
    public void setR_CNTRY(final String r_CNTRY) {
        this.R_CNTRY = r_CNTRY;
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
    
    @Override
    public String toString() {
        return "Customer [id=" + this.id + ", branch=" + this.branch + ", id_client=" + this.id_client + ", name=" + this.name + ", code_country=" + this.code_country + ", code_type=" + this.code_type + ", code_resident=" + this.code_resident + ", code_subject=" + this.code_subject + ", code_product=" + this.code_product + ", sign_registr=" + this.sign_registr + ", code_form=" + this.code_form + ", date_open=" + this.date_open + ", date_close=" + this.date_close + ", state=" + this.state + ", p_birthday=" + this.p_birthday + ", p_post_address=" + this.p_post_address + ", p_passport_type=" + this.p_passport_type + ", p_passport_serial=" + this.p_passport_serial + ", p_passport_number=" + this.p_passport_number + ", p_passport_place_registration=" + this.p_passport_place_registration + ", p_passport_date_registration=" + this.p_passport_date_registration + ", p_code_tax_org=" + this.p_code_tax_org + ", p_number_tax_registration=" + this.p_number_tax_registration + ", p_code_bank=" + this.p_code_bank + ", p_code_class_credit=" + this.p_code_class_credit + ", p_code_citizenship=" + this.p_code_citizenship + ", p_birth_place=" + this.p_birth_place + ", p_code_capacity=" + this.p_code_capacity + ", p_capacity_status_date=" + this.p_capacity_status_date + ", p_capacity_status_place=" + this.p_capacity_status_place + ", p_num_certif_capacity=" + this.p_num_certif_capacity + ", p_phone_home=" + this.p_phone_home + ", p_phone_mobile=" + this.p_phone_mobile + ", p_email_address=" + this.p_email_address + ", p_pension_sertif_serial=" + this.p_pension_sertif_serial + ", p_code_gender=" + this.p_code_gender + ", p_code_nation=" + this.p_code_nation + ", p_code_birth_region=" + this.p_code_birth_region + ", p_code_birth_distr=" + this.p_code_birth_distr + ", p_type_document=" + this.p_type_document + ", p_passport_date_expiration=" + this.p_passport_date_expiration + ", p_code_adr_region=" + this.p_code_adr_region + ", p_code_adr_distr=" + this.p_code_adr_distr + ", p_inps=" + this.p_inps + ", p_family=" + this.p_family + ", p_first_name=" + this.p_first_name + ", p_patronymic=" + this.p_patronymic + ", tieto_customer_id=" + this.tieto_customer_id + ", head_customer_id=" + this.head_customer_id + ", acode_tel=" + this.acode_tel + ", R_CITY=" + this.R_CITY + ", R_STREET=" + this.R_STREET + ", R_E_MAILS=" + this.R_E_MAILS + ", R_MOB_PHONE=" + this.R_MOB_PHONE + ", R_PHONE=" + this.R_PHONE + ", R_CNTRY=" + this.R_CNTRY + ", ISSUED_BY=" + this.ISSUED_BY + ", PERSON_CODE=" + this.PERSON_CODE + "]";
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public String getPinfl() {
        return pinfl;
    }
}
