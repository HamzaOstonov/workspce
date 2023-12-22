package com.is.customer_.core.model;

import java.util.Date;

public class CustomerBuilder {
    private String id;
    private String idSap;
    private String branch;
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
    private Long _id;
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
    private String unionId;
    private String subbranch;
    
    public CustomerBuilder setLongId(Long _id) {
        this._id = _id;
        return this;
    }

    public CustomerBuilder setIdSap(String idSap) {
        this.idSap = idSap;
        return this;
    }

    public CustomerBuilder setBranch(String branch) {
        this.branch = branch;
        return this;
    }

    public CustomerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setCode_country(String code_country) {
        this.code_country = code_country;
        return this;
    }

    public CustomerBuilder setCode_type(String code_type) {
        this.code_type = code_type;
        return this;
    }

    public CustomerBuilder setCode_resident(String code_resident) {
        this.code_resident = code_resident;
        return this;
    }

    public CustomerBuilder setCode_subject(String code_subject) {
        this.code_subject = code_subject;
        return this;
    }

    public CustomerBuilder setSign_registr(int sign_registr) {
        this.sign_registr = sign_registr;
        return this;
    }

    public CustomerBuilder setCode_form(String code_form) {
        this.code_form = code_form;
        return this;
    }

    public CustomerBuilder setDate_open(Date date_open) {
        this.date_open = date_open;
        return this;
    }

    public CustomerBuilder setDate_close(Date date_close) {
        this.date_close = date_close;
        return this;
    }

    public CustomerBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public CustomerBuilder setKod_err(int kod_err) {
        this.kod_err = kod_err;
        return this;
    }

    public CustomerBuilder setFile_name(String file_name) {
        this.file_name = file_name;
        return this;
    }

    public CustomerBuilder setSubbranch(String subbranch) {
        this.subbranch = subbranch;
        return this;
    }
    
    public CustomerBuilder setP_family(String p_family) {
        this.p_family = p_family;
        return this;
    }

    public CustomerBuilder setP_first_name(String p_first_name) {
        this.p_first_name = p_first_name;
        return this;
    }

    public CustomerBuilder setP_patronymic(String p_patronymic) {
        this.p_patronymic = p_patronymic;
        return this;
    }

    public CustomerBuilder setP_family_local(String p_family_local) {
        this.p_family_local = p_family_local;
        return this;
    }

    public CustomerBuilder setP_first_name_local(String p_first_name_local) {
        this.p_first_name_local = p_first_name_local;
        return this;
    }

    public CustomerBuilder setP_patronymic_local(String p_patronymic_local) {
        this.p_patronymic_local = p_patronymic_local;
        return this;
    }

    public CustomerBuilder setP_birthday(Date p_birthday) {
        this.p_birthday = p_birthday;
        return this;
    }

    public CustomerBuilder setP_post_address(String p_post_address) {
        this.p_post_address = p_post_address;
        return this;
    }

    public CustomerBuilder setP_passport_type(String p_passport_type) {
        this.p_passport_type = p_passport_type;
        return this;
    }

    public CustomerBuilder setP_passport_serial(String p_passport_serial) {
        this.p_passport_serial = p_passport_serial;
        return this;
    }

    public CustomerBuilder setP_passport_number(String p_passport_number) {
        this.p_passport_number = p_passport_number;
        return this;
    }

    public CustomerBuilder setP_passport_place_registration(String p_passport_place_registration) {
        this.p_passport_place_registration = p_passport_place_registration;
        return this;
    }

    public CustomerBuilder setP_passport_date_registration(Date p_passport_date_registration) {
        this.p_passport_date_registration = p_passport_date_registration;
        return this;
    }

    public CustomerBuilder setP_code_tax_org(String p_code_tax_org) {
        this.p_code_tax_org = p_code_tax_org;
        return this;
    }

    public CustomerBuilder setP_number_tax_registration(String p_number_tax_registration) {
        this.p_number_tax_registration = p_number_tax_registration;
        return this;
    }

    public CustomerBuilder setP_code_bank(String p_code_bank) {
        this.p_code_bank = p_code_bank;
        return this;
    }

    public CustomerBuilder setP_code_class_credit(String p_code_class_credit) {
        this.p_code_class_credit = p_code_class_credit;
        return this;
    }

    public CustomerBuilder setP_code_citizenship(String p_code_citizenship) {
        this.p_code_citizenship = p_code_citizenship;
        return this;
    }

    public CustomerBuilder setP_birth_place(String p_birth_place) {
        this.p_birth_place = p_birth_place;
        return this;
    }

    public CustomerBuilder setP_code_capacity(String p_code_capacity) {
        this.p_code_capacity = p_code_capacity;
        return this;
    }

    public CustomerBuilder setP_capacity_status_date(Date p_capacity_status_date) {
        this.p_capacity_status_date = p_capacity_status_date;
        return this;
    }

    public CustomerBuilder setP_capacity_status_place(String p_capacity_status_place) {
        this.p_capacity_status_place = p_capacity_status_place;
        return this;
    }

    public CustomerBuilder setP_num_certif_capacity(String p_num_certif_capacity) {
        this.p_num_certif_capacity = p_num_certif_capacity;
        return this;
    }

    public CustomerBuilder setP_phone_home(String p_phone_home) {
        this.p_phone_home = p_phone_home;
        return this;
    }

    public CustomerBuilder setP_phone_mobile(String p_phone_mobile) {
        this.p_phone_mobile = p_phone_mobile;
        return this;
    }

    public CustomerBuilder setP_email_address(String p_email_address) {
        this.p_email_address = p_email_address;
        return this;
    }

    public CustomerBuilder setP_pension_sertif_serial(String p_pension_sertif_serial) {
        this.p_pension_sertif_serial = p_pension_sertif_serial;
        return this;
    }

    public CustomerBuilder setP_code_gender(String p_code_gender) {
        this.p_code_gender = p_code_gender;
        return this;
    }

    public CustomerBuilder setP_code_nation(String p_code_nation) {
        this.p_code_nation = p_code_nation;
        return this;
    }

    public CustomerBuilder setP_code_birth_region(String p_code_birth_region) {
        this.p_code_birth_region = p_code_birth_region;
        return this;
    }

    public CustomerBuilder setP_code_birth_distr(String p_code_birth_distr) {
        this.p_code_birth_distr = p_code_birth_distr;
        return this;
    }

    public CustomerBuilder setP_type_document(String p_type_document) {
        this.p_type_document = p_type_document;
        return this;
    }

    public CustomerBuilder setP_passport_date_expiration(Date p_passport_date_expiration) {
        this.p_passport_date_expiration = p_passport_date_expiration;
        return this;
    }

    public CustomerBuilder setP_code_adr_region(String p_code_adr_region) {
        this.p_code_adr_region = p_code_adr_region;
        return this;
    }

    public CustomerBuilder setP_code_adr_distr(String p_code_adr_distr) {
        this.p_code_adr_distr = p_code_adr_distr;
        return this;
    }

    public CustomerBuilder setP_inps(String p_inps) {
        this.p_inps = p_inps;
        return this;
    }

    public CustomerBuilder setP_pinfl(String p_pinfl) {
        this.p_pinfl = p_pinfl;
        return this;
    }
    
    public CustomerBuilder setP_pass_place_region(String p_pass_place_region) {
        this.p_pass_place_region = p_pass_place_region;
        return this;
    }

    public CustomerBuilder setP_pass_place_district(String p_pass_place_district) {
        this.p_pass_place_district = p_pass_place_district;
        return this;
    }

    public CustomerBuilder setP_post_address_street(String p_post_address_street) {
        this.p_post_address_street = p_post_address_street;
        return this;
    }

    public CustomerBuilder setP_post_address_street_code(String p_post_address_street_code) {
        this.p_post_address_street_code = p_post_address_street_code;
        return this;
    }

    public CustomerBuilder setP_post_address_house(String p_post_address_house) {
        this.p_post_address_house = p_post_address_house;
        return this;
    }

    public CustomerBuilder setP_post_address_flat(String p_post_address_flat) {
        this.p_post_address_flat = p_post_address_flat;
        return this;
    }

    public CustomerBuilder setP_post_address_quarter(String p_post_address_quarter) {
        this.p_post_address_quarter = p_post_address_quarter;
        return this;
    }

    public CustomerBuilder setJ_327(String j_327) {
        this.j_327 = j_327;
        return this;
    }

    public CustomerBuilder setSign_100(String sign_100) {
        this.sign_100 = sign_100;
        return this;
    }

    public CustomerBuilder setP_date_open_nibbd(Date p_date_open_nibbd) {
        this.p_date_open_nibbd = p_date_open_nibbd;
        return this;
    }
    
    public CustomerBuilder setP_date_close_nibbd(Date p_date_close_nibbd) {
        this.p_date_close_nibbd = p_date_close_nibbd;
        return this;
    }
    public CustomerBuilder setP_id_nibbd(String p_id_nibbd) {
        this.p_id_nibbd = p_id_nibbd;
        return this;
    }
    public CustomerBuilder setP_close_type(String p_close_type) {
        this.p_close_type = p_close_type;
        return this;
    }
    public CustomerBuilder setP_close_doc_n(String p_close_doc_n) {
        this.p_close_doc_n = p_close_doc_n;
        return this;
    }
    public CustomerBuilder setP_close_doc_d(Date p_close_doc_d) {
        this.p_close_doc_d = p_close_doc_d;
        return this;
    }
    public CustomerBuilder setP_drive_permit_num(String p_drive_permit_num) {
        this.p_drive_permit_num = p_drive_permit_num;
        return this;
    }
    public CustomerBuilder setP_drive_permit_ser(String p_drive_permit_ser) {
        this.p_drive_permit_ser = p_drive_permit_ser;
        return this;
    }
    public CustomerBuilder setP_drive_permit_reg_d(Date p_drive_permit_reg_d) {
        this.p_drive_permit_reg_d = p_drive_permit_reg_d;
        return this;
    }
    public CustomerBuilder setP_drive_permit_exp_d(Date p_drive_permit_exp_d) {
        this.p_drive_permit_exp_d = p_drive_permit_exp_d;
        return this;
    }
    public CustomerBuilder setP_drive_permit_place(String p_drive_permit_place) {
        this.p_drive_permit_place = p_drive_permit_place;
        return this;
    }
    public CustomerBuilder setP_agreement(String p_agreement) {
        this.p_agreement = p_agreement;
        return this;
    }
    public CustomerBuilder setUnionId(String unionId) {
        this.unionId = unionId;
        return this;
    }

    public Customer createCustomer() {
        return new Customer(_id, idSap, branch,
                id, name, code_country, code_type, code_resident,
                code_subject, sign_registr, code_form, date_open, date_close,
                state, kod_err, file_name, p_family, p_first_name, p_patronymic,
                p_family_local, p_first_name_local, p_patronymic_local, p_birthday,
                p_post_address, p_passport_type, p_passport_serial, p_passport_number,
                p_passport_place_registration, p_passport_date_registration, p_code_tax_org,
                p_number_tax_registration, p_code_bank, p_code_class_credit, p_code_citizenship,
                p_birth_place, p_code_capacity, p_capacity_status_date, p_capacity_status_place,
                p_num_certif_capacity, p_phone_home, p_phone_mobile, p_email_address, p_pension_sertif_serial,
                p_code_gender, p_code_nation, p_code_birth_region, p_code_birth_distr, p_type_document,
                p_passport_date_expiration, p_code_adr_region, p_code_adr_distr, p_inps, p_pinfl, p_pass_place_region,
                p_pass_place_district, p_post_address_street, p_post_address_street_code, p_post_address_house,
                p_post_address_flat, p_post_address_quarter, j_327, sign_100, null, null, null,
                0,  0,
                p_date_open_nibbd,
                p_date_close_nibbd,
                p_id_nibbd,
                p_close_type,
                p_close_doc_n,
                p_close_doc_d,
                p_drive_permit_num,
                p_drive_permit_ser,
                p_drive_permit_reg_d,
                p_drive_permit_exp_d,
                p_drive_permit_place,
                p_agreement,
                unionId, 
                subbranch);
    }
}