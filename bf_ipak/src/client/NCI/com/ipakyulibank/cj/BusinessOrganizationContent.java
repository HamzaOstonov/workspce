/**
 * BusinessOrganizationContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessOrganizationContent  implements java.io.Serializable {
    private java.lang.String number_registration_doc;

    private java.util.Date date_registration;

    private java.lang.String place_regist_name;

    private java.lang.String code_tax_org;

    private java.lang.String number_tax_registration;

    private java.lang.String code_gender;

    private java.lang.String code_head_organization;

    private java.lang.String inn_head_organization;

    private java.lang.String opf;

    private java.util.Date patent_expiration;

    private java.lang.String soato;

    private java.lang.String okpo;

    private java.lang.String code_sector;

    private java.lang.String code_organ_direct;

    private java.lang.String region;

    private java.lang.String distr;

    private java.lang.String sign_trade;

    private java.lang.String small_business;

    private java.util.Date accreditation_expiration;

    private java.lang.String code_class_credit;

    private java.lang.String code_bank;

    private java.lang.String account;

    private java.lang.String post_address;

    private java.lang.String director_name;

    private java.lang.String director_lastname;

    private java.lang.String director_midname;

    private java.lang.String director_firstname;

    private java.lang.String director_id;

    private java.lang.String director_passport;

    private java.lang.String director_type_document;

    private java.lang.String director_passp_serial;

    private java.lang.String director_passp_number;

    private java.util.Date director_passp_date_reg;

    private java.lang.String director_passp_place_reg;

    private java.util.Date director_passp_date_end;

    private java.lang.String director_code_citizenship;

    private java.lang.String director_birth_place;

    private java.util.Date director_birthday;

    private java.lang.String director_address;

    private java.lang.String chief_accounter_name;

    private java.lang.String chief_accounter_lastname;

    private java.lang.String chief_accounter_midname;

    private java.lang.String chief_accounter_firstname;

    private java.lang.String chief_accounter_id;

    private java.lang.String chief_accounter_passport;

    private java.lang.String accountant_type_document;

    private java.lang.String accountant_passp_serial;

    private java.lang.String accountant_passp_number;

    private java.util.Date accountant_passp_date_reg;

    private java.lang.String accountant_passp_place_reg;

    private java.util.Date accountant_passp_date_end;

    private java.lang.String accountant_code_citizenship;

    private java.util.Date accountant_birthday;
 
    private java.lang.String accountant_birth_place;

    private java.lang.String accountant_address;

    private java.lang.String phone;

    private java.lang.String phone_ext;

    private java.lang.String phone_country;

    private java.lang.String fax;

    private java.lang.String fax_ext;

    private java.lang.String fax_country;

    private java.lang.String email;

    private java.lang.String country;

    private java.lang.String INN;

    private java.lang.String rn_type_id;

    private java.lang.String nibdd_ident_type;

    private java.lang.String nibdd_id;

    private java.lang.String nibdd_issued_by;

    private java.lang.String okpo_type_id;

    private client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set[] license_set;

    private java.lang.String general_id;

    private java.lang.String general_name;

    private java.lang.String general_short_name;

    private java.lang.String general_type;

    private java.lang.String general_profile_author;

    private java.lang.String general_org_status;

    private java.util.Date general_date_by_statute;

    private java.util.Date general_date_of_termination;

    private java.lang.String general_residency;

    private java.lang.String general_client_status;

    private java.util.Date uvk_valid_from;

    private java.util.Date uvk_valid_to;

    private java.util.Date uvk_risk_level_date;

    private java.lang.String uvk_risk_level_reason;

    private java.lang.String uvk_risk_level;

    private java.lang.String account_is_opened;

    private java.lang.String so_note;

    private java.lang.String po_note;

    private java.lang.String sales_data;

    private java.lang.String kind_of_industry;

    private java.lang.String kind_of_industry_descr;

    public BusinessOrganizationContent() {
    }

    public BusinessOrganizationContent(
           java.lang.String number_registration_doc,
           java.util.Date date_registration,
           java.lang.String place_regist_name,
           java.lang.String code_tax_org,
           java.lang.String number_tax_registration,
           java.lang.String code_gender,
           java.lang.String code_head_organization,
           java.lang.String inn_head_organization,
           java.lang.String opf,
           java.util.Date patent_expiration,
           java.lang.String soato,
           java.lang.String okpo,
           java.lang.String code_sector,
           java.lang.String code_organ_direct,
           java.lang.String region,
           java.lang.String distr,
           java.lang.String sign_trade,
           java.lang.String small_business,
           java.util.Date accreditation_expiration,
           java.lang.String code_class_credit,
           java.lang.String code_bank,
           java.lang.String account,
           java.lang.String post_address,
           java.lang.String director_name,
           java.lang.String director_lastname,
           java.lang.String director_midname,
           java.lang.String director_firstname,
           java.lang.String director_id,
           java.lang.String director_passport,
           java.lang.String director_type_document,
           java.lang.String director_passp_serial,
           java.lang.String director_passp_number,
           java.util.Date director_passp_date_reg,
           java.lang.String director_passp_place_reg,
           java.util.Date director_passp_date_end,
           java.lang.String director_code_citizenship,
           java.lang.String director_birth_place,
           java.util.Date director_birthday,
           java.lang.String director_address,
           java.lang.String chief_accounter_name,
           java.lang.String chief_accounter_lastname,
           java.lang.String chief_accounter_midname,
           java.lang.String chief_accounter_firstname,
           java.lang.String chief_accounter_id,
           java.lang.String chief_accounter_passport,
           java.lang.String accountant_type_document,
           java.lang.String accountant_passp_serial,
           java.lang.String accountant_passp_number,
           java.util.Date accountant_passp_date_reg,
           java.lang.String accountant_passp_place_reg,
           java.util.Date accountant_passp_date_end,
           java.lang.String accountant_code_citizenship,
           java.util.Date accountant_birthday,
           java.lang.String accountant_birth_place,
           java.lang.String accountant_address,
           java.lang.String phone,
           java.lang.String phone_ext,
           java.lang.String phone_country,
           java.lang.String fax,
           java.lang.String fax_ext,
           java.lang.String fax_country,
           java.lang.String email,
           java.lang.String country,
           java.lang.String INN,
           java.lang.String rn_type_id,
           java.lang.String nibdd_ident_type,
           java.lang.String nibdd_id,
           java.lang.String nibdd_issued_by,
           java.lang.String okpo_type_id,
           client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set[] license_set,
           java.lang.String general_id,
           java.lang.String general_name,
           java.lang.String general_short_name,
           java.lang.String general_type,
           java.lang.String general_profile_author,
           java.lang.String general_org_status,
           java.util.Date general_date_by_statute,
           java.util.Date general_date_of_termination,
           java.lang.String general_residency,
           java.lang.String general_client_status,
           java.util.Date uvk_valid_from,
           java.util.Date uvk_valid_to,
           java.util.Date uvk_risk_level_date,
           java.lang.String uvk_risk_level_reason,
           java.lang.String uvk_risk_level,
           java.lang.String account_is_opened,
           java.lang.String so_note,
           java.lang.String po_note,
           java.lang.String sales_data,
           java.lang.String kind_of_industry,
           java.lang.String kind_of_industry_descr) {
           this.number_registration_doc = number_registration_doc;
           this.date_registration = date_registration;
           this.place_regist_name = place_regist_name;
           this.code_tax_org = code_tax_org;
           this.number_tax_registration = number_tax_registration;
           this.code_gender = code_gender;
           this.code_head_organization = code_head_organization;
           this.inn_head_organization = inn_head_organization;
           this.opf = opf;
           this.patent_expiration = patent_expiration;
           this.soato = soato;
           this.okpo = okpo;
           this.code_sector = code_sector;
           this.code_organ_direct = code_organ_direct;
           this.region = region;
           this.distr = distr;
           this.sign_trade = sign_trade;
           this.small_business = small_business;
           this.accreditation_expiration = accreditation_expiration;
           this.code_class_credit = code_class_credit;
           this.code_bank = code_bank;
           this.account = account;
           this.post_address = post_address;
           this.director_name = director_name;
           this.director_lastname = director_lastname;
           this.director_midname = director_midname;
           this.director_firstname = director_firstname;
           this.director_id = director_id;
           this.director_passport = director_passport;
           this.director_type_document = director_type_document;
           this.director_passp_serial = director_passp_serial;
           this.director_passp_number = director_passp_number;
           this.director_passp_date_reg = director_passp_date_reg;
           this.director_passp_place_reg = director_passp_place_reg;
           this.director_passp_date_end = director_passp_date_end;
           this.director_code_citizenship = director_code_citizenship;
           this.director_birth_place = director_birth_place;
           this.director_birthday = director_birthday;
           this.director_address = director_address;
           this.chief_accounter_name = chief_accounter_name;
           this.chief_accounter_lastname = chief_accounter_lastname;
           this.chief_accounter_midname = chief_accounter_midname;
           this.chief_accounter_firstname = chief_accounter_firstname;
           this.chief_accounter_id = chief_accounter_id;
           this.chief_accounter_passport = chief_accounter_passport;
           this.accountant_type_document = accountant_type_document;
           this.accountant_passp_serial = accountant_passp_serial;
           this.accountant_passp_number = accountant_passp_number;
           this.accountant_passp_date_reg = accountant_passp_date_reg;
           this.accountant_passp_place_reg = accountant_passp_place_reg;
           this.accountant_passp_date_end = accountant_passp_date_end;
           this.accountant_code_citizenship = accountant_code_citizenship;
           this.accountant_birthday = accountant_birthday;
           this.accountant_birth_place = accountant_birth_place;
           this.accountant_address = accountant_address;
           this.phone = phone;
           this.phone_ext = phone_ext;
           this.phone_country = phone_country;
           this.fax = fax;
           this.fax_ext = fax_ext;
           this.fax_country = fax_country;
           this.email = email;
           this.country = country;
           this.INN = INN;
           this.rn_type_id = rn_type_id;
           this.nibdd_ident_type = nibdd_ident_type;
           this.nibdd_id = nibdd_id;
           this.nibdd_issued_by = nibdd_issued_by;
           this.okpo_type_id = okpo_type_id;
           this.license_set = license_set;
           this.general_id = general_id;
           this.general_name = general_name;
           this.general_short_name = general_short_name;
           this.general_type = general_type;
           this.general_profile_author = general_profile_author;
           this.general_org_status = general_org_status;
           this.general_date_by_statute = general_date_by_statute;
           this.general_date_of_termination = general_date_of_termination;
           this.general_residency = general_residency;
           this.general_client_status = general_client_status;
           this.uvk_valid_from = uvk_valid_from;
           this.uvk_valid_to = uvk_valid_to;
           this.uvk_risk_level_date = uvk_risk_level_date;
           this.uvk_risk_level_reason = uvk_risk_level_reason;
           this.uvk_risk_level = uvk_risk_level;
           this.account_is_opened = account_is_opened;
           this.so_note = so_note;
           this.po_note = po_note;
           this.sales_data = sales_data;
           this.kind_of_industry = kind_of_industry;
           this.kind_of_industry_descr = kind_of_industry_descr;
    }


    /**
     * Gets the number_registration_doc value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getNumber_registration_doc() {
        return number_registration_doc;
    }


    /**
     * Sets the number_registration_doc value for this BusinessOrganizationContent.
     * 
     */
    public void setNumber_registration_doc(java.lang.String number_registration_doc) {
        this.number_registration_doc = number_registration_doc;
    }


    /**
     * Gets the date_registration value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getDate_registration() {
        return date_registration;
    }


    /**
     * Sets the date_registration value for this BusinessOrganizationContent.
     * 
     */
    public void setDate_registration(java.util.Date date_registration) {
        this.date_registration = date_registration;
    }


    /**
     * Gets the place_regist_name value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPlace_regist_name() {
        return place_regist_name;
    }


    /**
     * Sets the place_regist_name value for this BusinessOrganizationContent.
     * 
     */
    public void setPlace_regist_name(java.lang.String place_regist_name) {
        this.place_regist_name = place_regist_name;
    }


    /**
     * Gets the code_tax_org value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_tax_org() {
        return code_tax_org;
    }


    /**
     * Sets the code_tax_org value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_tax_org(java.lang.String code_tax_org) {
        this.code_tax_org = code_tax_org;
    }


    /**
     * Gets the number_tax_registration value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getNumber_tax_registration() {
        return number_tax_registration;
    }


    /**
     * Sets the number_tax_registration value for this BusinessOrganizationContent.
     * 
     */
    public void setNumber_tax_registration(java.lang.String number_tax_registration) {
        this.number_tax_registration = number_tax_registration;
    }


    /**
     * Gets the code_gender value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_gender() {
        return code_gender;
    }


    /**
     * Sets the code_gender value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_gender(java.lang.String code_gender) {
        this.code_gender = code_gender;
    }


    /**
     * Gets the code_head_organization value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_head_organization() {
        return code_head_organization;
    }


    /**
     * Sets the code_head_organization value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_head_organization(java.lang.String code_head_organization) {
        this.code_head_organization = code_head_organization;
    }


    /**
     * Gets the inn_head_organization value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getInn_head_organization() {
        return inn_head_organization;
    }


    /**
     * Sets the inn_head_organization value for this BusinessOrganizationContent.
     * 
     */
    public void setInn_head_organization(java.lang.String inn_head_organization) {
        this.inn_head_organization = inn_head_organization;
    }


    /**
     * Gets the opf value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getOpf() {
        return opf;
    }


    /**
     * Sets the opf value for this BusinessOrganizationContent.
     * 
     */
    public void setOpf(java.lang.String opf) {
        this.opf = opf;
    }


    /**
     * Gets the patent_expiration value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getPatent_expiration() {
        return patent_expiration;
    }


    /**
     * Sets the patent_expiration value for this BusinessOrganizationContent.
     * 
     */
    public void setPatent_expiration(java.util.Date patent_expiration) {
        this.patent_expiration = patent_expiration;
    }


    /**
     * Gets the soato value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getSoato() {
        return soato;
    }


    /**
     * Sets the soato value for this BusinessOrganizationContent.
     * 
     */
    public void setSoato(java.lang.String soato) {
        this.soato = soato;
    }


    /**
     * Gets the okpo value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getOkpo() {
        return okpo;
    }


    /**
     * Sets the okpo value for this BusinessOrganizationContent.
     * 
     */
    public void setOkpo(java.lang.String okpo) {
        this.okpo = okpo;
    }


    /**
     * Gets the code_sector value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_sector() {
        return code_sector;
    }


    /**
     * Sets the code_sector value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_sector(java.lang.String code_sector) {
        this.code_sector = code_sector;
    }


    /**
     * Gets the code_organ_direct value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_organ_direct() {
        return code_organ_direct;
    }


    /**
     * Sets the code_organ_direct value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_organ_direct(java.lang.String code_organ_direct) {
        this.code_organ_direct = code_organ_direct;
    }


    /**
     * Gets the region value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getRegion() {
        return region;
    }


    /**
     * Sets the region value for this BusinessOrganizationContent.
     * 
     */
    public void setRegion(java.lang.String region) {
        this.region = region;
    }


    /**
     * Gets the distr value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDistr() {
        return distr;
    }


    /**
     * Sets the distr value for this BusinessOrganizationContent.
     * 
     */
    public void setDistr(java.lang.String distr) {
        this.distr = distr;
    }


    /**
     * Gets the sign_trade value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getSign_trade() {
        return sign_trade;
    }


    /**
     * Sets the sign_trade value for this BusinessOrganizationContent.
     * 
     */
    public void setSign_trade(java.lang.String sign_trade) {
        this.sign_trade = sign_trade;
    }


    /**
     * Gets the small_business value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getSmall_business() {
        return small_business;
    }


    /**
     * Sets the small_business value for this BusinessOrganizationContent.
     * 
     */
    public void setSmall_business(java.lang.String small_business) {
        this.small_business = small_business;
    }


    /**
     * Gets the accreditation_expiration value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getAccreditation_expiration() {
        return accreditation_expiration;
    }


    /**
     * Sets the accreditation_expiration value for this BusinessOrganizationContent.
     * 
     */
    public void setAccreditation_expiration(java.util.Date accreditation_expiration) {
        this.accreditation_expiration = accreditation_expiration;
    }


    /**
     * Gets the code_class_credit value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_class_credit() {
        return code_class_credit;
    }


    /**
     * Sets the code_class_credit value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_class_credit(java.lang.String code_class_credit) {
        this.code_class_credit = code_class_credit;
    }


    /**
     * Gets the code_bank value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCode_bank() {
        return code_bank;
    }


    /**
     * Sets the code_bank value for this BusinessOrganizationContent.
     * 
     */
    public void setCode_bank(java.lang.String code_bank) {
        this.code_bank = code_bank;
    }


    /**
     * Gets the account value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccount() {
        return account;
    }


    /**
     * Sets the account value for this BusinessOrganizationContent.
     * 
     */
    public void setAccount(java.lang.String account) {
        this.account = account;
    }


    /**
     * Gets the post_address value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPost_address() {
        return post_address;
    }


    /**
     * Sets the post_address value for this BusinessOrganizationContent.
     * 
     */
    public void setPost_address(java.lang.String post_address) {
        this.post_address = post_address;
    }


    /**
     * Gets the director_name value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_name() {
        return director_name;
    }


    /**
     * Sets the director_name value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_name(java.lang.String director_name) {
        this.director_name = director_name;
    }


    /**
     * Gets the director_lastname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_lastname() {
        return director_lastname;
    }


    /**
     * Sets the director_lastname value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_lastname(java.lang.String director_lastname) {
        this.director_lastname = director_lastname;
    }


    /**
     * Gets the director_midname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_midname() {
        return director_midname;
    }


    /**
     * Sets the director_midname value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_midname(java.lang.String director_midname) {
        this.director_midname = director_midname;
    }


    /**
     * Gets the director_firstname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_firstname() {
        return director_firstname;
    }


    /**
     * Sets the director_firstname value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_firstname(java.lang.String director_firstname) {
        this.director_firstname = director_firstname;
    }


    /**
     * Gets the director_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_id() {
        return director_id;
    }


    /**
     * Sets the director_id value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_id(java.lang.String director_id) {
        this.director_id = director_id;
    }


    /**
     * Gets the director_passport value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_passport() {
        return director_passport;
    }


    /**
     * Sets the director_passport value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passport(java.lang.String director_passport) {
        this.director_passport = director_passport;
    }


    /**
     * Gets the director_type_document value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_type_document() {
        return director_type_document;
    }


    /**
     * Sets the director_type_document value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_type_document(java.lang.String director_type_document) {
        this.director_type_document = director_type_document;
    }


    /**
     * Gets the director_passp_serial value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_passp_serial() {
        return director_passp_serial;
    }


    /**
     * Sets the director_passp_serial value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passp_serial(java.lang.String director_passp_serial) {
        this.director_passp_serial = director_passp_serial;
    }


    /**
     * Gets the director_passp_number value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_passp_number() {
        return director_passp_number;
    }


    /**
     * Sets the director_passp_number value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passp_number(java.lang.String director_passp_number) {
        this.director_passp_number = director_passp_number;
    }


    /**
     * Gets the director_passp_date_reg value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getDirector_passp_date_reg() {
        return director_passp_date_reg;
    }


    /**
     * Sets the director_passp_date_reg value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passp_date_reg(java.util.Date director_passp_date_reg) {
        this.director_passp_date_reg = director_passp_date_reg;
    }


    /**
     * Gets the director_passp_place_reg value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_passp_place_reg() {
        return director_passp_place_reg;
    }


    /**
     * Sets the director_passp_place_reg value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passp_place_reg(java.lang.String director_passp_place_reg) {
        this.director_passp_place_reg = director_passp_place_reg;
    }


    /**
     * Gets the director_passp_date_end value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getDirector_passp_date_end() {
        return director_passp_date_end;
    }


    /**
     * Sets the director_passp_date_end value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_passp_date_end(java.util.Date director_passp_date_end) {
        this.director_passp_date_end = director_passp_date_end;
    }


    /**
     * Gets the director_code_citizenship value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_code_citizenship() {
        return director_code_citizenship;
    }


    /**
     * Sets the director_code_citizenship value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_code_citizenship(java.lang.String director_code_citizenship) {
        this.director_code_citizenship = director_code_citizenship;
    }


    /**
     * Gets the director_birth_place value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_birth_place() {
        return director_birth_place;
    }


    /**
     * Sets the director_birth_place value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_birth_place(java.lang.String director_birth_place) {
        this.director_birth_place = director_birth_place;
    }


    /**
     * Gets the director_birthday value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getDirector_birthday() {
        return director_birthday;
    }


    /**
     * Sets the director_birthday value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_birthday(java.util.Date director_birthday) {
        this.director_birthday = director_birthday;
    }


    /**
     * Gets the director_address value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getDirector_address() {
        return director_address;
    }


    /**
     * Sets the director_address value for this BusinessOrganizationContent.
     * 
     */
    public void setDirector_address(java.lang.String director_address) {
        this.director_address = director_address;
    }


    /**
     * Gets the chief_accounter_name value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_name() {
        return chief_accounter_name;
    }


    /**
     * Sets the chief_accounter_name value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_name(java.lang.String chief_accounter_name) {
        this.chief_accounter_name = chief_accounter_name;
    }


    /**
     * Gets the chief_accounter_lastname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_lastname() {
        return chief_accounter_lastname;
    }


    /**
     * Sets the chief_accounter_lastname value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_lastname(java.lang.String chief_accounter_lastname) {
        this.chief_accounter_lastname = chief_accounter_lastname;
    }


    /**
     * Gets the chief_accounter_midname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_midname() {
        return chief_accounter_midname;
    }


    /**
     * Sets the chief_accounter_midname value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_midname(java.lang.String chief_accounter_midname) {
        this.chief_accounter_midname = chief_accounter_midname;
    }


    /**
     * Gets the chief_accounter_firstname value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_firstname() {
        return chief_accounter_firstname;
    }


    /**
     * Sets the chief_accounter_firstname value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_firstname(java.lang.String chief_accounter_firstname) {
        this.chief_accounter_firstname = chief_accounter_firstname;
    }


    /**
     * Gets the chief_accounter_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_id() {
        return chief_accounter_id;
    }


    /**
     * Sets the chief_accounter_id value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_id(java.lang.String chief_accounter_id) {
        this.chief_accounter_id = chief_accounter_id;
    }


    /**
     * Gets the chief_accounter_passport value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getChief_accounter_passport() {
        return chief_accounter_passport;
    }


    /**
     * Sets the chief_accounter_passport value for this BusinessOrganizationContent.
     * 
     */
    public void setChief_accounter_passport(java.lang.String chief_accounter_passport) {
        this.chief_accounter_passport = chief_accounter_passport;
    }


    /**
     * Gets the accountant_type_document value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_type_document() {
        return accountant_type_document;
    }


    /**
     * Sets the accountant_type_document value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_type_document(java.lang.String accountant_type_document) {
        this.accountant_type_document = accountant_type_document;
    }


    /**
     * Gets the accountant_passp_serial value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_passp_serial() {
        return accountant_passp_serial;
    }


    /**
     * Sets the accountant_passp_serial value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_passp_serial(java.lang.String accountant_passp_serial) {
        this.accountant_passp_serial = accountant_passp_serial;
    }


    /**
     * Gets the accountant_passp_number value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_passp_number() {
        return accountant_passp_number;
    }


    /**
     * Sets the accountant_passp_number value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_passp_number(java.lang.String accountant_passp_number) {
        this.accountant_passp_number = accountant_passp_number;
    }


    /**
     * Gets the accountant_passp_date_reg value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getAccountant_passp_date_reg() {
        return accountant_passp_date_reg;
    }


    /**
     * Sets the accountant_passp_date_reg value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_passp_date_reg(java.util.Date accountant_passp_date_reg) {
        this.accountant_passp_date_reg = accountant_passp_date_reg;
    }


    /**
     * Gets the accountant_passp_place_reg value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_passp_place_reg() {
        return accountant_passp_place_reg;
    }


    /**
     * Sets the accountant_passp_place_reg value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_passp_place_reg(java.lang.String accountant_passp_place_reg) {
        this.accountant_passp_place_reg = accountant_passp_place_reg;
    }


    /**
     * Gets the accountant_passp_date_end value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getAccountant_passp_date_end() {
        return accountant_passp_date_end;
    }


    /**
     * Sets the accountant_passp_date_end value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_passp_date_end(java.util.Date accountant_passp_date_end) {
        this.accountant_passp_date_end = accountant_passp_date_end;
    }


    /**
     * Gets the accountant_code_citizenship value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_code_citizenship() {
        return accountant_code_citizenship;
    }


    /**
     * Sets the accountant_code_citizenship value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_code_citizenship(java.lang.String accountant_code_citizenship) {
        this.accountant_code_citizenship = accountant_code_citizenship;
    }


    /**
     * Gets the accountant_birthday value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getAccountant_birthday() {
        return accountant_birthday;
    }


    /**
     * Sets the accountant_birthday value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_birthday(java.util.Date accountant_birthday) {
        this.accountant_birthday = accountant_birthday;
    }


    /**
     * Gets the accountant_birth_place value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_birth_place() {
        return accountant_birth_place;
    }


    /**
     * Sets the accountant_birth_place value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_birth_place(java.lang.String accountant_birth_place) {
        this.accountant_birth_place = accountant_birth_place;
    }


    /**
     * Gets the accountant_address value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccountant_address() {
        return accountant_address;
    }


    /**
     * Sets the accountant_address value for this BusinessOrganizationContent.
     * 
     */
    public void setAccountant_address(java.lang.String accountant_address) {
        this.accountant_address = accountant_address;
    }


    /**
     * Gets the phone value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this BusinessOrganizationContent.
     * 
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the phone_ext value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPhone_ext() {
        return phone_ext;
    }


    /**
     * Sets the phone_ext value for this BusinessOrganizationContent.
     * 
     */
    public void setPhone_ext(java.lang.String phone_ext) {
        this.phone_ext = phone_ext;
    }


    /**
     * Gets the phone_country value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPhone_country() {
        return phone_country;
    }


    /**
     * Sets the phone_country value for this BusinessOrganizationContent.
     * 
     */
    public void setPhone_country(java.lang.String phone_country) {
        this.phone_country = phone_country;
    }


    /**
     * Gets the fax value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this BusinessOrganizationContent.
     * 
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the fax_ext value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getFax_ext() {
        return fax_ext;
    }


    /**
     * Sets the fax_ext value for this BusinessOrganizationContent.
     * 
     */
    public void setFax_ext(java.lang.String fax_ext) {
        this.fax_ext = fax_ext;
    }


    /**
     * Gets the fax_country value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getFax_country() {
        return fax_country;
    }


    /**
     * Sets the fax_country value for this BusinessOrganizationContent.
     * 
     */
    public void setFax_country(java.lang.String fax_country) {
        this.fax_country = fax_country;
    }


    /**
     * Gets the email value for this BusinessOrganizationContent.
     * 
     * @return email   * e-mail
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this BusinessOrganizationContent.
     * 
     * @param email   * e-mail
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the country value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this BusinessOrganizationContent.
     * 
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the INN value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getINN() {
        return INN;
    }


    /**
     * Sets the INN value for this BusinessOrganizationContent.
     * 
     */
    public void setINN(java.lang.String INN) {
        this.INN = INN;
    }


    /**
     * Gets the rn_type_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getRn_type_id() {
        return rn_type_id;
    }


    /**
     * Sets the rn_type_id value for this BusinessOrganizationContent.
     * 
     */
    public void setRn_type_id(java.lang.String rn_type_id) {
        this.rn_type_id = rn_type_id;
    }


    /**
     * Gets the nibdd_ident_type value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getNibdd_ident_type() {
        return nibdd_ident_type;
    }


    /**
     * Sets the nibdd_ident_type value for this BusinessOrganizationContent.
     * 
     */
    public void setNibdd_ident_type(java.lang.String nibdd_ident_type) {
        this.nibdd_ident_type = nibdd_ident_type;
    }


    /**
     * Gets the nibdd_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getNibdd_id() {
        return nibdd_id;
    }


    /**
     * Sets the nibdd_id value for this BusinessOrganizationContent.
     * 
     */
    public void setNibdd_id(java.lang.String nibdd_id) {
        this.nibdd_id = nibdd_id;
    }


    /**
     * Gets the nibdd_issued_by value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getNibdd_issued_by() {
        return nibdd_issued_by;
    }


    /**
     * Sets the nibdd_issued_by value for this BusinessOrganizationContent.
     * 
     */
    public void setNibdd_issued_by(java.lang.String nibdd_issued_by) {
        this.nibdd_issued_by = nibdd_issued_by;
    }


    /**
     * Gets the okpo_type_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getOkpo_type_id() {
        return okpo_type_id;
    }


    /**
     * Sets the okpo_type_id value for this BusinessOrganizationContent.
     * 
     */
    public void setOkpo_type_id(java.lang.String okpo_type_id) {
        this.okpo_type_id = okpo_type_id;
    }


    /**
     * Gets the license_set value for this BusinessOrganizationContent.
     * 
     */
    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set[] getLicense_set() {
        return license_set;
    }


    /**
     * Sets the license_set value for this BusinessOrganizationContent.
     * 
     * @param license_set
     */
    public void setLicense_set(client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set[] license_set) {
        this.license_set = license_set;
    }

    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set getLicense_set(int i) {
        return this.license_set[i];
    }

    public void setLicense_set(int i, client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set _value) {
        this.license_set[i] = _value;
    }


    /**
     * Gets the general_id value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_id() {
        return general_id;
    }


    /**
     * Sets the general_id value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_id(java.lang.String general_id) {
        this.general_id = general_id;
    }


    /**
     * Gets the general_name value for this BusinessOrganizationContent.
     * 

     */
    public java.lang.String getGeneral_name() {
        return general_name;
    }


    /**
     * Sets the general_name value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_name(java.lang.String general_name) {
        this.general_name = general_name;
    }


    /**
     * Gets the general_short_name value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_short_name() {
        return general_short_name;
    }


    /**
     * Sets the general_short_name value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_short_name(java.lang.String general_short_name) {
        this.general_short_name = general_short_name;
    }


    /**
     * Gets the general_type value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_type() {
        return general_type;
    }


    /**
     * Sets the general_type value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_type(java.lang.String general_type) {
        this.general_type = general_type;
    }


    /**
     * Gets the general_profile_author value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_profile_author() {
        return general_profile_author;
    }


    /**
     * Sets the general_profile_author value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_profile_author(java.lang.String general_profile_author) {
        this.general_profile_author = general_profile_author;
    }


    /**
     * Gets the general_org_status value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_org_status() {
        return general_org_status;
    }


    /**
     * Sets the general_org_status value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_org_status(java.lang.String general_org_status) {
        this.general_org_status = general_org_status;
    }


    /**
     * Gets the general_date_by_statute value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getGeneral_date_by_statute() {
        return general_date_by_statute;
    }


    /**
     * Sets the general_date_by_statute value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_date_by_statute(java.util.Date general_date_by_statute) {
        this.general_date_by_statute = general_date_by_statute;
    }


    /**
     * Gets the general_date_of_termination value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getGeneral_date_of_termination() {
        return general_date_of_termination;
    }


    /**
     * Sets the general_date_of_termination value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_date_of_termination(java.util.Date general_date_of_termination) {
        this.general_date_of_termination = general_date_of_termination;
    }


    /**
     * Gets the general_residency value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_residency() {
        return general_residency;
    }


    /**
     * Sets the general_residency value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_residency(java.lang.String general_residency) {
        this.general_residency = general_residency;
    }


    /**
     * Gets the general_client_status value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getGeneral_client_status() {
        return general_client_status;
    }


    /**
     * Sets the general_client_status value for this BusinessOrganizationContent.
     * 
     */
    public void setGeneral_client_status(java.lang.String general_client_status) {
        this.general_client_status = general_client_status;
    }


    /**
     * Gets the uvk_valid_from value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getUvk_valid_from() {
        return uvk_valid_from;
    }


    /**
     * Sets the uvk_valid_from value for this BusinessOrganizationContent.
     * 
     */
    public void setUvk_valid_from(java.util.Date uvk_valid_from) {
        this.uvk_valid_from = uvk_valid_from;
    }


    /**
     * Gets the uvk_valid_to value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getUvk_valid_to() {
        return uvk_valid_to;
    }


    /**
     * Sets the uvk_valid_to value for this BusinessOrganizationContent.
     * 
     */
    public void setUvk_valid_to(java.util.Date uvk_valid_to) {
        this.uvk_valid_to = uvk_valid_to;
    }


    /**
     * Gets the uvk_risk_level_date value for this BusinessOrganizationContent.
     * 
     */
    public java.util.Date getUvk_risk_level_date() {
        return uvk_risk_level_date;
    }


    /**
     * Sets the uvk_risk_level_date value for this BusinessOrganizationContent.
     * 
     */
    public void setUvk_risk_level_date(java.util.Date uvk_risk_level_date) {
        this.uvk_risk_level_date = uvk_risk_level_date;
    }


    /**
     * Gets the uvk_risk_level_reason value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getUvk_risk_level_reason() {
        return uvk_risk_level_reason;
    }


    /**
     * Sets the uvk_risk_level_reason value for this BusinessOrganizationContent.
     * 
     */
    public void setUvk_risk_level_reason(java.lang.String uvk_risk_level_reason) {
        this.uvk_risk_level_reason = uvk_risk_level_reason;
    }


    /**
     * Gets the uvk_risk_level value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getUvk_risk_level() {
        return uvk_risk_level;
    }


    /**
     * Sets the uvk_risk_level value for this BusinessOrganizationContent.
     * 
     */
    public void setUvk_risk_level(java.lang.String uvk_risk_level) {
        this.uvk_risk_level = uvk_risk_level;
    }


    /**
     * Gets the account_is_opened value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getAccount_is_opened() {
        return account_is_opened;
    }


    /**
     * Sets the account_is_opened value for this BusinessOrganizationContent.
     * 
     */
    public void setAccount_is_opened(java.lang.String account_is_opened) {
        this.account_is_opened = account_is_opened;
    }


    /**
     * Gets the so_note value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getSo_note() {
        return so_note;
    }


    /**
     * Sets the so_note value for this BusinessOrganizationContent.
     * 
     */
    public void setSo_note(java.lang.String so_note) {
        this.so_note = so_note;
    }


    /**
     * Gets the po_note value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getPo_note() {
        return po_note;
    }


    /**
     * Sets the po_note value for this BusinessOrganizationContent.
     * 
     */
    public void setPo_note(java.lang.String po_note) {
        this.po_note = po_note;
    }


    /**
     * Gets the sales_data value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getSales_data() {
        return sales_data;
    }


    /**
     * Sets the sales_data value for this BusinessOrganizationContent.
     * 
     */
    public void setSales_data(java.lang.String sales_data) {
        this.sales_data = sales_data;
    }


    /**
     * Gets the kind_of_industry value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getKind_of_industry() {
        return kind_of_industry;
    }


    /**
     * Sets the kind_of_industry value for this BusinessOrganizationContent.
     * 
     */
    public void setKind_of_industry(java.lang.String kind_of_industry) {
        this.kind_of_industry = kind_of_industry;
    }


    /**
     * Gets the kind_of_industry_descr value for this BusinessOrganizationContent.
     * 
     */
    public java.lang.String getKind_of_industry_descr() {
        return kind_of_industry_descr;
    }


    /**
     * Sets the kind_of_industry_descr value for this BusinessOrganizationContent.
     * 
     */
    public void setKind_of_industry_descr(java.lang.String kind_of_industry_descr) {
        this.kind_of_industry_descr = kind_of_industry_descr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessOrganizationContent)) return false;
        BusinessOrganizationContent other = (BusinessOrganizationContent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.number_registration_doc==null && other.getNumber_registration_doc()==null) || 
             (this.number_registration_doc!=null &&
              this.number_registration_doc.equals(other.getNumber_registration_doc()))) &&
            ((this.date_registration==null && other.getDate_registration()==null) || 
             (this.date_registration!=null &&
              this.date_registration.equals(other.getDate_registration()))) &&
            ((this.place_regist_name==null && other.getPlace_regist_name()==null) || 
             (this.place_regist_name!=null &&
              this.place_regist_name.equals(other.getPlace_regist_name()))) &&
            ((this.code_tax_org==null && other.getCode_tax_org()==null) || 
             (this.code_tax_org!=null &&
              this.code_tax_org.equals(other.getCode_tax_org()))) &&
            ((this.number_tax_registration==null && other.getNumber_tax_registration()==null) || 
             (this.number_tax_registration!=null &&
              this.number_tax_registration.equals(other.getNumber_tax_registration()))) &&
            ((this.code_gender==null && other.getCode_gender()==null) || 
             (this.code_gender!=null &&
              this.code_gender.equals(other.getCode_gender()))) &&
            ((this.code_head_organization==null && other.getCode_head_organization()==null) || 
             (this.code_head_organization!=null &&
              this.code_head_organization.equals(other.getCode_head_organization()))) &&
            ((this.inn_head_organization==null && other.getInn_head_organization()==null) || 
             (this.inn_head_organization!=null &&
              this.inn_head_organization.equals(other.getInn_head_organization()))) &&
            ((this.opf==null && other.getOpf()==null) || 
             (this.opf!=null &&
              this.opf.equals(other.getOpf()))) &&
            ((this.patent_expiration==null && other.getPatent_expiration()==null) || 
             (this.patent_expiration!=null &&
              this.patent_expiration.equals(other.getPatent_expiration()))) &&
            ((this.soato==null && other.getSoato()==null) || 
             (this.soato!=null &&
              this.soato.equals(other.getSoato()))) &&
            ((this.okpo==null && other.getOkpo()==null) || 
             (this.okpo!=null &&
              this.okpo.equals(other.getOkpo()))) &&
            ((this.code_sector==null && other.getCode_sector()==null) || 
             (this.code_sector!=null &&
              this.code_sector.equals(other.getCode_sector()))) &&
            ((this.code_organ_direct==null && other.getCode_organ_direct()==null) || 
             (this.code_organ_direct!=null &&
              this.code_organ_direct.equals(other.getCode_organ_direct()))) &&
            ((this.region==null && other.getRegion()==null) || 
             (this.region!=null &&
              this.region.equals(other.getRegion()))) &&
            ((this.distr==null && other.getDistr()==null) || 
             (this.distr!=null &&
              this.distr.equals(other.getDistr()))) &&
            ((this.sign_trade==null && other.getSign_trade()==null) || 
             (this.sign_trade!=null &&
              this.sign_trade.equals(other.getSign_trade()))) &&
            ((this.small_business==null && other.getSmall_business()==null) || 
             (this.small_business!=null &&
              this.small_business.equals(other.getSmall_business()))) &&
            ((this.accreditation_expiration==null && other.getAccreditation_expiration()==null) || 
             (this.accreditation_expiration!=null &&
              this.accreditation_expiration.equals(other.getAccreditation_expiration()))) &&
            ((this.code_class_credit==null && other.getCode_class_credit()==null) || 
             (this.code_class_credit!=null &&
              this.code_class_credit.equals(other.getCode_class_credit()))) &&
            ((this.code_bank==null && other.getCode_bank()==null) || 
             (this.code_bank!=null &&
              this.code_bank.equals(other.getCode_bank()))) &&
            ((this.account==null && other.getAccount()==null) || 
             (this.account!=null &&
              this.account.equals(other.getAccount()))) &&
            ((this.post_address==null && other.getPost_address()==null) || 
             (this.post_address!=null &&
              this.post_address.equals(other.getPost_address()))) &&
            ((this.director_name==null && other.getDirector_name()==null) || 
             (this.director_name!=null &&
              this.director_name.equals(other.getDirector_name()))) &&
            ((this.director_lastname==null && other.getDirector_lastname()==null) || 
             (this.director_lastname!=null &&
              this.director_lastname.equals(other.getDirector_lastname()))) &&
            ((this.director_midname==null && other.getDirector_midname()==null) || 
             (this.director_midname!=null &&
              this.director_midname.equals(other.getDirector_midname()))) &&
            ((this.director_firstname==null && other.getDirector_firstname()==null) || 
             (this.director_firstname!=null &&
              this.director_firstname.equals(other.getDirector_firstname()))) &&
            ((this.director_id==null && other.getDirector_id()==null) || 
             (this.director_id!=null &&
              this.director_id.equals(other.getDirector_id()))) &&
            ((this.director_passport==null && other.getDirector_passport()==null) || 
             (this.director_passport!=null &&
              this.director_passport.equals(other.getDirector_passport()))) &&
            ((this.director_type_document==null && other.getDirector_type_document()==null) || 
             (this.director_type_document!=null &&
              this.director_type_document.equals(other.getDirector_type_document()))) &&
            ((this.director_passp_serial==null && other.getDirector_passp_serial()==null) || 
             (this.director_passp_serial!=null &&
              this.director_passp_serial.equals(other.getDirector_passp_serial()))) &&
            ((this.director_passp_number==null && other.getDirector_passp_number()==null) || 
             (this.director_passp_number!=null &&
              this.director_passp_number.equals(other.getDirector_passp_number()))) &&
            ((this.director_passp_date_reg==null && other.getDirector_passp_date_reg()==null) || 
             (this.director_passp_date_reg!=null &&
              this.director_passp_date_reg.equals(other.getDirector_passp_date_reg()))) &&
            ((this.director_passp_place_reg==null && other.getDirector_passp_place_reg()==null) || 
             (this.director_passp_place_reg!=null &&
              this.director_passp_place_reg.equals(other.getDirector_passp_place_reg()))) &&
            ((this.director_passp_date_end==null && other.getDirector_passp_date_end()==null) || 
             (this.director_passp_date_end!=null &&
              this.director_passp_date_end.equals(other.getDirector_passp_date_end()))) &&
            ((this.director_code_citizenship==null && other.getDirector_code_citizenship()==null) || 
             (this.director_code_citizenship!=null &&
              this.director_code_citizenship.equals(other.getDirector_code_citizenship()))) &&
            ((this.director_birth_place==null && other.getDirector_birth_place()==null) || 
             (this.director_birth_place!=null &&
              this.director_birth_place.equals(other.getDirector_birth_place()))) &&
            ((this.director_birthday==null && other.getDirector_birthday()==null) || 
             (this.director_birthday!=null &&
              this.director_birthday.equals(other.getDirector_birthday()))) &&
            ((this.director_address==null && other.getDirector_address()==null) || 
             (this.director_address!=null &&
              this.director_address.equals(other.getDirector_address()))) &&
            ((this.chief_accounter_name==null && other.getChief_accounter_name()==null) || 
             (this.chief_accounter_name!=null &&
              this.chief_accounter_name.equals(other.getChief_accounter_name()))) &&
            ((this.chief_accounter_lastname==null && other.getChief_accounter_lastname()==null) || 
             (this.chief_accounter_lastname!=null &&
              this.chief_accounter_lastname.equals(other.getChief_accounter_lastname()))) &&
            ((this.chief_accounter_midname==null && other.getChief_accounter_midname()==null) || 
             (this.chief_accounter_midname!=null &&
              this.chief_accounter_midname.equals(other.getChief_accounter_midname()))) &&
            ((this.chief_accounter_firstname==null && other.getChief_accounter_firstname()==null) || 
             (this.chief_accounter_firstname!=null &&
              this.chief_accounter_firstname.equals(other.getChief_accounter_firstname()))) &&
            ((this.chief_accounter_id==null && other.getChief_accounter_id()==null) || 
             (this.chief_accounter_id!=null &&
              this.chief_accounter_id.equals(other.getChief_accounter_id()))) &&
            ((this.chief_accounter_passport==null && other.getChief_accounter_passport()==null) || 
             (this.chief_accounter_passport!=null &&
              this.chief_accounter_passport.equals(other.getChief_accounter_passport()))) &&
            ((this.accountant_type_document==null && other.getAccountant_type_document()==null) || 
             (this.accountant_type_document!=null &&
              this.accountant_type_document.equals(other.getAccountant_type_document()))) &&
            ((this.accountant_passp_serial==null && other.getAccountant_passp_serial()==null) || 
             (this.accountant_passp_serial!=null &&
              this.accountant_passp_serial.equals(other.getAccountant_passp_serial()))) &&
            ((this.accountant_passp_number==null && other.getAccountant_passp_number()==null) || 
             (this.accountant_passp_number!=null &&
              this.accountant_passp_number.equals(other.getAccountant_passp_number()))) &&
            ((this.accountant_passp_date_reg==null && other.getAccountant_passp_date_reg()==null) || 
             (this.accountant_passp_date_reg!=null &&
              this.accountant_passp_date_reg.equals(other.getAccountant_passp_date_reg()))) &&
            ((this.accountant_passp_place_reg==null && other.getAccountant_passp_place_reg()==null) || 
             (this.accountant_passp_place_reg!=null &&
              this.accountant_passp_place_reg.equals(other.getAccountant_passp_place_reg()))) &&
            ((this.accountant_passp_date_end==null && other.getAccountant_passp_date_end()==null) || 
             (this.accountant_passp_date_end!=null &&
              this.accountant_passp_date_end.equals(other.getAccountant_passp_date_end()))) &&
            ((this.accountant_code_citizenship==null && other.getAccountant_code_citizenship()==null) || 
             (this.accountant_code_citizenship!=null &&
              this.accountant_code_citizenship.equals(other.getAccountant_code_citizenship()))) &&
            ((this.accountant_birthday==null && other.getAccountant_birthday()==null) || 
             (this.accountant_birthday!=null &&
              this.accountant_birthday.equals(other.getAccountant_birthday()))) &&
            ((this.accountant_birth_place==null && other.getAccountant_birth_place()==null) || 
             (this.accountant_birth_place!=null &&
              this.accountant_birth_place.equals(other.getAccountant_birth_place()))) &&
            ((this.accountant_address==null && other.getAccountant_address()==null) || 
             (this.accountant_address!=null &&
              this.accountant_address.equals(other.getAccountant_address()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.phone_ext==null && other.getPhone_ext()==null) || 
             (this.phone_ext!=null &&
              this.phone_ext.equals(other.getPhone_ext()))) &&
            ((this.phone_country==null && other.getPhone_country()==null) || 
             (this.phone_country!=null &&
              this.phone_country.equals(other.getPhone_country()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.fax_ext==null && other.getFax_ext()==null) || 
             (this.fax_ext!=null &&
              this.fax_ext.equals(other.getFax_ext()))) &&
            ((this.fax_country==null && other.getFax_country()==null) || 
             (this.fax_country!=null &&
              this.fax_country.equals(other.getFax_country()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.INN==null && other.getINN()==null) || 
             (this.INN!=null &&
              this.INN.equals(other.getINN()))) &&
            ((this.rn_type_id==null && other.getRn_type_id()==null) || 
             (this.rn_type_id!=null &&
              this.rn_type_id.equals(other.getRn_type_id()))) &&
            ((this.nibdd_ident_type==null && other.getNibdd_ident_type()==null) || 
             (this.nibdd_ident_type!=null &&
              this.nibdd_ident_type.equals(other.getNibdd_ident_type()))) &&
            ((this.nibdd_id==null && other.getNibdd_id()==null) || 
             (this.nibdd_id!=null &&
              this.nibdd_id.equals(other.getNibdd_id()))) &&
            ((this.nibdd_issued_by==null && other.getNibdd_issued_by()==null) || 
             (this.nibdd_issued_by!=null &&
              this.nibdd_issued_by.equals(other.getNibdd_issued_by()))) &&
            ((this.okpo_type_id==null && other.getOkpo_type_id()==null) || 
             (this.okpo_type_id!=null &&
              this.okpo_type_id.equals(other.getOkpo_type_id()))) &&
            ((this.license_set==null && other.getLicense_set()==null) || 
             (this.license_set!=null &&
              java.util.Arrays.equals(this.license_set, other.getLicense_set()))) &&
            ((this.general_id==null && other.getGeneral_id()==null) || 
             (this.general_id!=null &&
              this.general_id.equals(other.getGeneral_id()))) &&
            ((this.general_name==null && other.getGeneral_name()==null) || 
             (this.general_name!=null &&
              this.general_name.equals(other.getGeneral_name()))) &&
            ((this.general_short_name==null && other.getGeneral_short_name()==null) || 
             (this.general_short_name!=null &&
              this.general_short_name.equals(other.getGeneral_short_name()))) &&
            ((this.general_type==null && other.getGeneral_type()==null) || 
             (this.general_type!=null &&
              this.general_type.equals(other.getGeneral_type()))) &&
            ((this.general_profile_author==null && other.getGeneral_profile_author()==null) || 
             (this.general_profile_author!=null &&
              this.general_profile_author.equals(other.getGeneral_profile_author()))) &&
            ((this.general_org_status==null && other.getGeneral_org_status()==null) || 
             (this.general_org_status!=null &&
              this.general_org_status.equals(other.getGeneral_org_status()))) &&
            ((this.general_date_by_statute==null && other.getGeneral_date_by_statute()==null) || 
             (this.general_date_by_statute!=null &&
              this.general_date_by_statute.equals(other.getGeneral_date_by_statute()))) &&
            ((this.general_date_of_termination==null && other.getGeneral_date_of_termination()==null) || 
             (this.general_date_of_termination!=null &&
              this.general_date_of_termination.equals(other.getGeneral_date_of_termination()))) &&
            ((this.general_residency==null && other.getGeneral_residency()==null) || 
             (this.general_residency!=null &&
              this.general_residency.equals(other.getGeneral_residency()))) &&
            ((this.general_client_status==null && other.getGeneral_client_status()==null) || 
             (this.general_client_status!=null &&
              this.general_client_status.equals(other.getGeneral_client_status()))) &&
            ((this.uvk_valid_from==null && other.getUvk_valid_from()==null) || 
             (this.uvk_valid_from!=null &&
              this.uvk_valid_from.equals(other.getUvk_valid_from()))) &&
            ((this.uvk_valid_to==null && other.getUvk_valid_to()==null) || 
             (this.uvk_valid_to!=null &&
              this.uvk_valid_to.equals(other.getUvk_valid_to()))) &&
            ((this.uvk_risk_level_date==null && other.getUvk_risk_level_date()==null) || 
             (this.uvk_risk_level_date!=null &&
              this.uvk_risk_level_date.equals(other.getUvk_risk_level_date()))) &&
            ((this.uvk_risk_level_reason==null && other.getUvk_risk_level_reason()==null) || 
             (this.uvk_risk_level_reason!=null &&
              this.uvk_risk_level_reason.equals(other.getUvk_risk_level_reason()))) &&
            ((this.uvk_risk_level==null && other.getUvk_risk_level()==null) || 
             (this.uvk_risk_level!=null &&
              this.uvk_risk_level.equals(other.getUvk_risk_level()))) &&
            ((this.account_is_opened==null && other.getAccount_is_opened()==null) || 
             (this.account_is_opened!=null &&
              this.account_is_opened.equals(other.getAccount_is_opened()))) &&
            ((this.so_note==null && other.getSo_note()==null) || 
             (this.so_note!=null &&
              this.so_note.equals(other.getSo_note()))) &&
            ((this.po_note==null && other.getPo_note()==null) || 
             (this.po_note!=null &&
              this.po_note.equals(other.getPo_note()))) &&
            ((this.sales_data==null && other.getSales_data()==null) || 
             (this.sales_data!=null &&
              this.sales_data.equals(other.getSales_data()))) &&
            ((this.kind_of_industry==null && other.getKind_of_industry()==null) || 
             (this.kind_of_industry!=null &&
              this.kind_of_industry.equals(other.getKind_of_industry()))) &&
            ((this.kind_of_industry_descr==null && other.getKind_of_industry_descr()==null) || 
             (this.kind_of_industry_descr!=null &&
              this.kind_of_industry_descr.equals(other.getKind_of_industry_descr())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNumber_registration_doc() != null) {
            _hashCode += getNumber_registration_doc().hashCode();
        }
        if (getDate_registration() != null) {
            _hashCode += getDate_registration().hashCode();
        }
        if (getPlace_regist_name() != null) {
            _hashCode += getPlace_regist_name().hashCode();
        }
        if (getCode_tax_org() != null) {
            _hashCode += getCode_tax_org().hashCode();
        }
        if (getNumber_tax_registration() != null) {
            _hashCode += getNumber_tax_registration().hashCode();
        }
        if (getCode_gender() != null) {
            _hashCode += getCode_gender().hashCode();
        }
        if (getCode_head_organization() != null) {
            _hashCode += getCode_head_organization().hashCode();
        }
        if (getInn_head_organization() != null) {
            _hashCode += getInn_head_organization().hashCode();
        }
        if (getOpf() != null) {
            _hashCode += getOpf().hashCode();
        }
        if (getPatent_expiration() != null) {
            _hashCode += getPatent_expiration().hashCode();
        }
        if (getSoato() != null) {
            _hashCode += getSoato().hashCode();
        }
        if (getOkpo() != null) {
            _hashCode += getOkpo().hashCode();
        }
        if (getCode_sector() != null) {
            _hashCode += getCode_sector().hashCode();
        }
        if (getCode_organ_direct() != null) {
            _hashCode += getCode_organ_direct().hashCode();
        }
        if (getRegion() != null) {
            _hashCode += getRegion().hashCode();
        }
        if (getDistr() != null) {
            _hashCode += getDistr().hashCode();
        }
        if (getSign_trade() != null) {
            _hashCode += getSign_trade().hashCode();
        }
        if (getSmall_business() != null) {
            _hashCode += getSmall_business().hashCode();
        }
        if (getAccreditation_expiration() != null) {
            _hashCode += getAccreditation_expiration().hashCode();
        }
        if (getCode_class_credit() != null) {
            _hashCode += getCode_class_credit().hashCode();
        }
        if (getCode_bank() != null) {
            _hashCode += getCode_bank().hashCode();
        }
        if (getAccount() != null) {
            _hashCode += getAccount().hashCode();
        }
        if (getPost_address() != null) {
            _hashCode += getPost_address().hashCode();
        }
        if (getDirector_name() != null) {
            _hashCode += getDirector_name().hashCode();
        }
        if (getDirector_lastname() != null) {
            _hashCode += getDirector_lastname().hashCode();
        }
        if (getDirector_midname() != null) {
            _hashCode += getDirector_midname().hashCode();
        }
        if (getDirector_firstname() != null) {
            _hashCode += getDirector_firstname().hashCode();
        }
        if (getDirector_id() != null) {
            _hashCode += getDirector_id().hashCode();
        }
        if (getDirector_passport() != null) {
            _hashCode += getDirector_passport().hashCode();
        }
        if (getDirector_type_document() != null) {
            _hashCode += getDirector_type_document().hashCode();
        }
        if (getDirector_passp_serial() != null) {
            _hashCode += getDirector_passp_serial().hashCode();
        }
        if (getDirector_passp_number() != null) {
            _hashCode += getDirector_passp_number().hashCode();
        }
        if (getDirector_passp_date_reg() != null) {
            _hashCode += getDirector_passp_date_reg().hashCode();
        }
        if (getDirector_passp_place_reg() != null) {
            _hashCode += getDirector_passp_place_reg().hashCode();
        }
        if (getDirector_passp_date_end() != null) {
            _hashCode += getDirector_passp_date_end().hashCode();
        }
        if (getDirector_code_citizenship() != null) {
            _hashCode += getDirector_code_citizenship().hashCode();
        }
        if (getDirector_birth_place() != null) {
            _hashCode += getDirector_birth_place().hashCode();
        }
        if (getDirector_birthday() != null) {
            _hashCode += getDirector_birthday().hashCode();
        }
        if (getDirector_address() != null) {
            _hashCode += getDirector_address().hashCode();
        }
        if (getChief_accounter_name() != null) {
            _hashCode += getChief_accounter_name().hashCode();
        }
        if (getChief_accounter_lastname() != null) {
            _hashCode += getChief_accounter_lastname().hashCode();
        }
        if (getChief_accounter_midname() != null) {
            _hashCode += getChief_accounter_midname().hashCode();
        }
        if (getChief_accounter_firstname() != null) {
            _hashCode += getChief_accounter_firstname().hashCode();
        }
        if (getChief_accounter_id() != null) {
            _hashCode += getChief_accounter_id().hashCode();
        }
        if (getChief_accounter_passport() != null) {
            _hashCode += getChief_accounter_passport().hashCode();
        }
        if (getAccountant_type_document() != null) {
            _hashCode += getAccountant_type_document().hashCode();
        }
        if (getAccountant_passp_serial() != null) {
            _hashCode += getAccountant_passp_serial().hashCode();
        }
        if (getAccountant_passp_number() != null) {
            _hashCode += getAccountant_passp_number().hashCode();
        }
        if (getAccountant_passp_date_reg() != null) {
            _hashCode += getAccountant_passp_date_reg().hashCode();
        }
        if (getAccountant_passp_place_reg() != null) {
            _hashCode += getAccountant_passp_place_reg().hashCode();
        }
        if (getAccountant_passp_date_end() != null) {
            _hashCode += getAccountant_passp_date_end().hashCode();
        }
        if (getAccountant_code_citizenship() != null) {
            _hashCode += getAccountant_code_citizenship().hashCode();
        }
        if (getAccountant_birthday() != null) {
            _hashCode += getAccountant_birthday().hashCode();
        }
        if (getAccountant_birth_place() != null) {
            _hashCode += getAccountant_birth_place().hashCode();
        }
        if (getAccountant_address() != null) {
            _hashCode += getAccountant_address().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getPhone_ext() != null) {
            _hashCode += getPhone_ext().hashCode();
        }
        if (getPhone_country() != null) {
            _hashCode += getPhone_country().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getFax_ext() != null) {
            _hashCode += getFax_ext().hashCode();
        }
        if (getFax_country() != null) {
            _hashCode += getFax_country().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getINN() != null) {
            _hashCode += getINN().hashCode();
        }
        if (getRn_type_id() != null) {
            _hashCode += getRn_type_id().hashCode();
        }
        if (getNibdd_ident_type() != null) {
            _hashCode += getNibdd_ident_type().hashCode();
        }
        if (getNibdd_id() != null) {
            _hashCode += getNibdd_id().hashCode();
        }
        if (getNibdd_issued_by() != null) {
            _hashCode += getNibdd_issued_by().hashCode();
        }
        if (getOkpo_type_id() != null) {
            _hashCode += getOkpo_type_id().hashCode();
        }
        if (getLicense_set() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLicense_set());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLicense_set(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGeneral_id() != null) {
            _hashCode += getGeneral_id().hashCode();
        }
        if (getGeneral_name() != null) {
            _hashCode += getGeneral_name().hashCode();
        }
        if (getGeneral_short_name() != null) {
            _hashCode += getGeneral_short_name().hashCode();
        }
        if (getGeneral_type() != null) {
            _hashCode += getGeneral_type().hashCode();
        }
        if (getGeneral_profile_author() != null) {
            _hashCode += getGeneral_profile_author().hashCode();
        }
        if (getGeneral_org_status() != null) {
            _hashCode += getGeneral_org_status().hashCode();
        }
        if (getGeneral_date_by_statute() != null) {
            _hashCode += getGeneral_date_by_statute().hashCode();
        }
        if (getGeneral_date_of_termination() != null) {
            _hashCode += getGeneral_date_of_termination().hashCode();
        }
        if (getGeneral_residency() != null) {
            _hashCode += getGeneral_residency().hashCode();
        }
        if (getGeneral_client_status() != null) {
            _hashCode += getGeneral_client_status().hashCode();
        }
        if (getUvk_valid_from() != null) {
            _hashCode += getUvk_valid_from().hashCode();
        }
        if (getUvk_valid_to() != null) {
            _hashCode += getUvk_valid_to().hashCode();
        }
        if (getUvk_risk_level_date() != null) {
            _hashCode += getUvk_risk_level_date().hashCode();
        }
        if (getUvk_risk_level_reason() != null) {
            _hashCode += getUvk_risk_level_reason().hashCode();
        }
        if (getUvk_risk_level() != null) {
            _hashCode += getUvk_risk_level().hashCode();
        }
        if (getAccount_is_opened() != null) {
            _hashCode += getAccount_is_opened().hashCode();
        }
        if (getSo_note() != null) {
            _hashCode += getSo_note().hashCode();
        }
        if (getPo_note() != null) {
            _hashCode += getPo_note().hashCode();
        }
        if (getSales_data() != null) {
            _hashCode += getSales_data().hashCode();
        }
        if (getKind_of_industry() != null) {
            _hashCode += getKind_of_industry().hashCode();
        }
        if (getKind_of_industry_descr() != null) {
            _hashCode += getKind_of_industry_descr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessOrganizationContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessOrganizationContent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_registration_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number_registration_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_registration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date_registration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("place_regist_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "place_regist_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_tax_org");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_tax_org"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_tax_registration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number_tax_registration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_head_organization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_head_organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inn_head_organization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inn_head_organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "opf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patent_expiration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "patent_expiration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "soato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("okpo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "okpo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_sector");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_sector"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_organ_direct");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_organ_direct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("distr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "distr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sign_trade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sign_trade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("small_business");
        elemField.setXmlName(new javax.xml.namespace.QName("", "small_business"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accreditation_expiration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accreditation_expiration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_class_credit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_class_credit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_bank");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_bank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account");
        elemField.setXmlName(new javax.xml.namespace.QName("", "account"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("post_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "post_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_midname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_midname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passport");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_type_document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_type_document"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passp_serial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passp_serial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passp_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passp_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passp_date_reg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passp_date_reg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passp_place_reg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passp_place_reg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_passp_date_end");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_passp_date_end"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_code_citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_code_citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_birth_place");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_birth_place"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("director_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "director_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_lastname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_lastname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_midname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_midname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_firstname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_firstname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chief_accounter_passport");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chief_accounter_passport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_type_document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_type_document"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_passp_serial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_passp_serial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_passp_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_passp_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_passp_date_reg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_passp_date_reg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_passp_place_reg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_passp_place_reg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_passp_date_end");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_passp_date_end"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_code_citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_code_citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_birth_place");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_birth_place"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountant_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountant_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone_ext");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone_ext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone_country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax_ext");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax_ext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax_country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rn_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rn_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nibdd_ident_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nibdd_ident_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nibdd_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nibdd_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nibdd_issued_by");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nibdd_issued_by"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("okpo_type_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "okpo_type_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("license_set");
        elemField.setXmlName(new javax.xml.namespace.QName("", "license_set"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BusinessOrganizationContent>license_set"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_short_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_short_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_profile_author");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_profile_author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_org_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_org_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_date_by_statute");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_date_by_statute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_date_of_termination");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_date_of_termination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_residency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_residency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("general_client_status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "general_client_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_valid_from");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_valid_from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_valid_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_valid_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_risk_level_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_risk_level_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_risk_level_reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_risk_level_reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_risk_level");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_risk_level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_is_opened");
        elemField.setXmlName(new javax.xml.namespace.QName("", "account_is_opened"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("so_note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "so_note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("po_note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "po_note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sales_data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sales_data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kind_of_industry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kind_of_industry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kind_of_industry_descr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kind_of_industry_descr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
