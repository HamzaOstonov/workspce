/**
 * BusinessPartnerContent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public class BusinessPartnerContent  implements java.io.Serializable {
    private java.lang.String branch;

    private java.lang.String id_client;

    private java.lang.String id_client_sap;

    private java.lang.String name;

    private java.lang.String code_country;

    private java.lang.String code_form;

    private java.lang.String code_resident;

    private java.lang.String code_subject;

    private java.lang.String code_type;

    private java.util.Date date_close;

    private java.util.Date date_open;

    private java.lang.String p_birth_place;

    private java.util.Date p_birthday;

    private java.util.Date p_capacity_status_date;

    private java.lang.String p_capacity_status_place;

    private java.lang.String p_code_adr_distr;

    private java.lang.String p_code_adr_region;

    private java.lang.String p_code_bank;

    private java.lang.String p_code_birth_distr;

    private java.lang.String p_code_birth_region;

    private java.lang.String p_code_capacity;

    private java.lang.String p_code_citizenship;

    private java.lang.String p_code_class_credit;

    private java.lang.String p_code_gender;

    private java.lang.String p_code_nation;

    private java.lang.String p_code_tax_org;

    private java.lang.String p_email_address;

    private java.lang.String p_family;

    private java.lang.String p_first_name;

    private java.lang.String p_inps;

    private java.lang.String p_num_certif_capacity;

    private java.lang.String p_number_tax_registration;

    private java.util.Date p_passport_date_expiration;

    private java.util.Date p_passport_date_registration;

    private java.lang.String p_passport_number;

    private java.lang.String p_passport_place_registration;

    private java.lang.String p_passport_serial;

    private java.lang.String p_passport_type;

    private java.lang.String p_patronymic;

    private java.lang.String p_pension_sertif_serial;

    private java.lang.String p_phone_home;

    private java.lang.String p_phone_mobile;

    private java.lang.String p_post_address;

    private java.lang.String p_type_document;

    private java.lang.Integer sign_registr;

    private java.lang.Integer state;

    private java.lang.String operation;
    
    private java.lang.String operation_origin;

    private java.lang.String first_name;

    private java.lang.String mid_name;

    private java.lang.String last_name;

    private java.lang.String p_phone_home_country;

    private java.lang.String p_phone_mobile_country;

    private java.util.Date uvk_valid_from;

    private java.util.Date uvk_valid_to;

    private java.util.Date uvk_init_date;

    private java.lang.String uvk_reason;

    private java.lang.String uvk_risk_lavel;

    private java.lang.String dul_country_code;

    private java.lang.String dul_region;

    private java.lang.String dul_district;

    private java.lang.String gc_series;

    private java.lang.String gc_number;

    private java.lang.String gc_country;

    private java.util.Date gc_date;

    private java.util.Date gc_date_valid_to;

    private java.lang.String gc_issued_by;

    private java.lang.String gc_region;

    private java.lang.String gc_district;

    /* Автор профайла */
    private java.lang.String profile_author;

    /* Cомнительные операции */
    private java.lang.String som_opers;

    /* Подозрительные операции */
    private java.lang.String pod_opers;

    /* сбытовые данные */
    private java.lang.String sales_data;

    /* Массив/Квартал */
    private java.lang.String adr_district;

    /* Улица */
    private java.lang.String adr_street;

    /* Дом/Строение */
    private java.lang.String adr_building;

    /* Квартира/офис */
    private java.lang.String adr_room;

    public BusinessPartnerContent() {
    }

    public BusinessPartnerContent(
           java.lang.String branch,
           java.lang.String id_client,
           java.lang.String id_client_sap,
           java.lang.String name,
           java.lang.String code_country,
           java.lang.String code_form,
           java.lang.String code_resident,
           java.lang.String code_subject,
           java.lang.String code_type,
           java.util.Date date_close,
           java.util.Date date_open,
           java.lang.String p_birth_place,
           java.util.Date p_birthday,
           java.util.Date p_capacity_status_date,
           java.lang.String p_capacity_status_place,
           java.lang.String p_code_adr_distr,
           java.lang.String p_code_adr_region,
           java.lang.String p_code_bank,
           java.lang.String p_code_birth_distr,
           java.lang.String p_code_birth_region,
           java.lang.String p_code_capacity,
           java.lang.String p_code_citizenship,
           java.lang.String p_code_class_credit,
           java.lang.String p_code_gender,
           java.lang.String p_code_nation,
           java.lang.String p_code_tax_org,
           java.lang.String p_email_address,
           java.lang.String p_family,
           java.lang.String p_first_name,
           java.lang.String p_inps,
           java.lang.String p_num_certif_capacity,
           java.lang.String p_number_tax_registration,
           java.util.Date p_passport_date_expiration,
           java.util.Date p_passport_date_registration,
           java.lang.String p_passport_number,
           java.lang.String p_passport_place_registration,
           java.lang.String p_passport_serial,
           java.lang.String p_passport_type,
           java.lang.String p_patronymic,
           java.lang.String p_pension_sertif_serial,
           java.lang.String p_phone_home,
           java.lang.String p_phone_mobile,
           java.lang.String p_post_address,
           java.lang.String p_type_document,
           java.lang.Integer sign_registr,
           java.lang.Integer state,
           java.lang.String operation,
           java.lang.String operation_origin,
           java.lang.String first_name,
           java.lang.String mid_name,
           java.lang.String last_name,
           java.lang.String p_phone_home_country,
           java.lang.String p_phone_mobile_country,
           java.util.Date uvk_valid_from,
           java.util.Date uvk_valid_to,
           java.util.Date uvk_init_date,
           java.lang.String uvk_reason,
           java.lang.String uvk_risk_lavel,
           java.lang.String dul_country_code,
           java.lang.String dul_region,
           java.lang.String dul_district,
           java.lang.String gc_series,
           java.lang.String gc_number,
           java.lang.String gc_country,
           java.util.Date gc_date,
           java.util.Date gc_date_valid_to,
           java.lang.String gc_issued_by,
           java.lang.String gc_region,
           java.lang.String gc_district,
           java.lang.String profile_author,
           java.lang.String som_opers,
           java.lang.String pod_opers,
           java.lang.String sales_data,
           java.lang.String adr_district,
           java.lang.String adr_street,
           java.lang.String adr_building,
           java.lang.String adr_room) {
           this.branch = branch;
           this.id_client = id_client;
           this.id_client_sap = id_client_sap;
           this.name = name;
           this.code_country = code_country;
           this.code_form = code_form;
           this.code_resident = code_resident;
           this.code_subject = code_subject;
           this.code_type = code_type;
           this.date_close = date_close;
           this.date_open = date_open;
           this.p_birth_place = p_birth_place;
           this.p_birthday = p_birthday;
           this.p_capacity_status_date = p_capacity_status_date;
           this.p_capacity_status_place = p_capacity_status_place;
           this.p_code_adr_distr = p_code_adr_distr;
           this.p_code_adr_region = p_code_adr_region;
           this.p_code_bank = p_code_bank;
           this.p_code_birth_distr = p_code_birth_distr;
           this.p_code_birth_region = p_code_birth_region;
           this.p_code_capacity = p_code_capacity;
           this.p_code_citizenship = p_code_citizenship;
           this.p_code_class_credit = p_code_class_credit;
           this.p_code_gender = p_code_gender;
           this.p_code_nation = p_code_nation;
           this.p_code_tax_org = p_code_tax_org;
           this.p_email_address = p_email_address;
           this.p_family = p_family;
           this.p_first_name = p_first_name;
           this.p_inps = p_inps;
           this.p_num_certif_capacity = p_num_certif_capacity;
           this.p_number_tax_registration = p_number_tax_registration;
           this.p_passport_date_expiration = p_passport_date_expiration;
           this.p_passport_date_registration = p_passport_date_registration;
           this.p_passport_number = p_passport_number;
           this.p_passport_place_registration = p_passport_place_registration;
           this.p_passport_serial = p_passport_serial;
           this.p_passport_type = p_passport_type;
           this.p_patronymic = p_patronymic;
           this.p_pension_sertif_serial = p_pension_sertif_serial;
           this.p_phone_home = p_phone_home;
           this.p_phone_mobile = p_phone_mobile;
           this.p_post_address = p_post_address;
           this.p_type_document = p_type_document;
           this.sign_registr = sign_registr;
           this.state = state;
           this.operation = operation;
           this.operation_origin = operation_origin;           
           this.first_name = first_name;
           this.mid_name = mid_name;
           this.last_name = last_name;
           this.p_phone_home_country = p_phone_home_country;
           this.p_phone_mobile_country = p_phone_mobile_country;
           this.uvk_valid_from = uvk_valid_from;
           this.uvk_valid_to = uvk_valid_to;
           this.uvk_init_date = uvk_init_date;
           this.uvk_reason = uvk_reason;
           this.uvk_risk_lavel = uvk_risk_lavel;
           this.dul_country_code = dul_country_code;
           this.dul_region = dul_region;
           this.dul_district = dul_district;
           this.gc_series = gc_series;
           this.gc_number = gc_number;
           this.gc_country = gc_country;
           this.gc_date = gc_date;
           this.gc_date_valid_to = gc_date_valid_to;
           this.gc_issued_by = gc_issued_by;
           this.gc_region = gc_region;
           this.gc_district = gc_district;
           this.profile_author = profile_author;
           this.som_opers = som_opers;
           this.pod_opers = pod_opers;
           this.sales_data = sales_data;
           this.adr_district = adr_district;
           this.adr_street = adr_street;
           this.adr_building = adr_building;
           this.adr_room = adr_room;
    }


    /**
     * Gets the branch value for this BusinessPartnerContent.
     * 
     * @return branch
     */
    public java.lang.String getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this BusinessPartnerContent.
     * 
     * @param branch
     */
    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    /**
     * Gets the id_client value for this BusinessPartnerContent.
     * 
     * @return id_client
     */
    public java.lang.String getId_client() {
        return id_client;
    }


    /**
     * Sets the id_client value for this BusinessPartnerContent.
     * 
     * @param id_client
     */
    public void setId_client(java.lang.String id_client) {
        this.id_client = id_client;
    }


    /**
     * Gets the id_client_sap value for this BusinessPartnerContent.
     * 
     * @return id_client_sap
     */
    public java.lang.String getId_client_sap() {
        return id_client_sap;
    }


    /**
     * Sets the id_client_sap value for this BusinessPartnerContent.
     * 
     * @param id_client_sap
     */
    public void setId_client_sap(java.lang.String id_client_sap) {
        this.id_client_sap = id_client_sap;
    }


    /**
     * Gets the name value for this BusinessPartnerContent.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this BusinessPartnerContent.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the code_country value for this BusinessPartnerContent.
     * 
     * @return code_country
     */
    public java.lang.String getCode_country() {
        return code_country;
    }


    /**
     * Sets the code_country value for this BusinessPartnerContent.
     * 
     * @param code_country
     */
    public void setCode_country(java.lang.String code_country) {
        this.code_country = code_country;
    }


    /**
     * Gets the code_form value for this BusinessPartnerContent.
     * 
     * @return code_form
     */
    public java.lang.String getCode_form() {
        return code_form;
    }


    /**
     * Sets the code_form value for this BusinessPartnerContent.
     * 
     * @param code_form
     */
    public void setCode_form(java.lang.String code_form) {
        this.code_form = code_form;
    }


    /**
     * Gets the code_resident value for this BusinessPartnerContent.
     * 
     * @return code_resident
     */
    public java.lang.String getCode_resident() {
        return code_resident;
    }


    /**
     * Sets the code_resident value for this BusinessPartnerContent.
     * 
     * @param code_resident
     */
    public void setCode_resident(java.lang.String code_resident) {
        this.code_resident = code_resident;
    }


    /**
     * Gets the code_subject value for this BusinessPartnerContent.
     * 
     * @return code_subject
     */
    public java.lang.String getCode_subject() {
        return code_subject;
    }


    /**
     * Sets the code_subject value for this BusinessPartnerContent.
     * 
     * @param code_subject
     */
    public void setCode_subject(java.lang.String code_subject) {
        this.code_subject = code_subject;
    }


    /**
     * Gets the code_type value for this BusinessPartnerContent.
     * 
     * @return code_type
     */
    public java.lang.String getCode_type() {
        return code_type;
    }


    /**
     * Sets the code_type value for this BusinessPartnerContent.
     * 
     * @param code_type
     */
    public void setCode_type(java.lang.String code_type) {
        this.code_type = code_type;
    }


    /**
     * Gets the date_close value for this BusinessPartnerContent.
     * 
     * @return date_close
     */
    public java.util.Date getDate_close() {
        return date_close;
    }


    /**
     * Sets the date_close value for this BusinessPartnerContent.
     * 
     * @param date_close
     */
    public void setDate_close(java.util.Date date_close) {
        this.date_close = date_close;
    }


    /**
     * Gets the date_open value for this BusinessPartnerContent.
     * 
     * @return date_open
     */
    public java.util.Date getDate_open() {
        return date_open;
    }


    /**
     * Sets the date_open value for this BusinessPartnerContent.
     * 
     * @param date_open
     */
    public void setDate_open(java.util.Date date_open) {
        this.date_open = date_open;
    }


    /**
     * Gets the p_birth_place value for this BusinessPartnerContent.
     * 
     * @return p_birth_place
     */
    public java.lang.String getP_birth_place() {
        return p_birth_place;
    }


    /**
     * Sets the p_birth_place value for this BusinessPartnerContent.
     * 
     * @param p_birth_place
     */
    public void setP_birth_place(java.lang.String p_birth_place) {
        this.p_birth_place = p_birth_place;
    }


    /**
     * Gets the p_birthday value for this BusinessPartnerContent.
     * 
     * @return p_birthday
     */
    public java.util.Date getP_birthday() {
        return p_birthday;
    }


    /**
     * Sets the p_birthday value for this BusinessPartnerContent.
     * 
     * @param p_birthday
     */
    public void setP_birthday(java.util.Date p_birthday) {
        this.p_birthday = p_birthday;
    }


    /**
     * Gets the p_capacity_status_date value for this BusinessPartnerContent.
     * 
     * @return p_capacity_status_date
     */
    public java.util.Date getP_capacity_status_date() {
        return p_capacity_status_date;
    }


    /**
     * Sets the p_capacity_status_date value for this BusinessPartnerContent.
     * 
     * @param p_capacity_status_date
     */
    public void setP_capacity_status_date(java.util.Date p_capacity_status_date) {
        this.p_capacity_status_date = p_capacity_status_date;
    }


    /**
     * Gets the p_capacity_status_place value for this BusinessPartnerContent.
     * 
     * @return p_capacity_status_place
     */
    public java.lang.String getP_capacity_status_place() {
        return p_capacity_status_place;
    }


    /**
     * Sets the p_capacity_status_place value for this BusinessPartnerContent.
     * 
     * @param p_capacity_status_place
     */
    public void setP_capacity_status_place(java.lang.String p_capacity_status_place) {
        this.p_capacity_status_place = p_capacity_status_place;
    }


    /**
     * Gets the p_code_adr_distr value for this BusinessPartnerContent.
     * 
     * @return p_code_adr_distr
     */
    public java.lang.String getP_code_adr_distr() {
        return p_code_adr_distr;
    }


    /**
     * Sets the p_code_adr_distr value for this BusinessPartnerContent.
     * 
     * @param p_code_adr_distr
     */
    public void setP_code_adr_distr(java.lang.String p_code_adr_distr) {
        this.p_code_adr_distr = p_code_adr_distr;
    }


    /**
     * Gets the p_code_adr_region value for this BusinessPartnerContent.
     * 
     * @return p_code_adr_region
     */
    public java.lang.String getP_code_adr_region() {
        return p_code_adr_region;
    }


    /**
     * Sets the p_code_adr_region value for this BusinessPartnerContent.
     * 
     * @param p_code_adr_region
     */
    public void setP_code_adr_region(java.lang.String p_code_adr_region) {
        this.p_code_adr_region = p_code_adr_region;
    }


    /**
     * Gets the p_code_bank value for this BusinessPartnerContent.
     * 
     * @return p_code_bank
     */
    public java.lang.String getP_code_bank() {
        return p_code_bank;
    }


    /**
     * Sets the p_code_bank value for this BusinessPartnerContent.
     * 
     * @param p_code_bank
     */
    public void setP_code_bank(java.lang.String p_code_bank) {
        this.p_code_bank = p_code_bank;
    }


    /**
     * Gets the p_code_birth_distr value for this BusinessPartnerContent.
     * 
     * @return p_code_birth_distr
     */
    public java.lang.String getP_code_birth_distr() {
        return p_code_birth_distr;
    }


    /**
     * Sets the p_code_birth_distr value for this BusinessPartnerContent.
     * 
     * @param p_code_birth_distr
     */
    public void setP_code_birth_distr(java.lang.String p_code_birth_distr) {
        this.p_code_birth_distr = p_code_birth_distr;
    }


    /**
     * Gets the p_code_birth_region value for this BusinessPartnerContent.
     * 
     * @return p_code_birth_region
     */
    public java.lang.String getP_code_birth_region() {
        return p_code_birth_region;
    }


    /**
     * Sets the p_code_birth_region value for this BusinessPartnerContent.
     * 
     * @param p_code_birth_region
     */
    public void setP_code_birth_region(java.lang.String p_code_birth_region) {
        this.p_code_birth_region = p_code_birth_region;
    }


    /**
     * Gets the p_code_capacity value for this BusinessPartnerContent.
     * 
     * @return p_code_capacity
     */
    public java.lang.String getP_code_capacity() {
        return p_code_capacity;
    }


    /**
     * Sets the p_code_capacity value for this BusinessPartnerContent.
     * 
     * @param p_code_capacity
     */
    public void setP_code_capacity(java.lang.String p_code_capacity) {
        this.p_code_capacity = p_code_capacity;
    }


    /**
     * Gets the p_code_citizenship value for this BusinessPartnerContent.
     * 
     * @return p_code_citizenship
     */
    public java.lang.String getP_code_citizenship() {
        return p_code_citizenship;
    }


    /**
     * Sets the p_code_citizenship value for this BusinessPartnerContent.
     * 
     * @param p_code_citizenship
     */
    public void setP_code_citizenship(java.lang.String p_code_citizenship) {
        this.p_code_citizenship = p_code_citizenship;
    }


    /**
     * Gets the p_code_class_credit value for this BusinessPartnerContent.
     * 
     * @return p_code_class_credit
     */
    public java.lang.String getP_code_class_credit() {
        return p_code_class_credit;
    }


    /**
     * Sets the p_code_class_credit value for this BusinessPartnerContent.
     * 
     * @param p_code_class_credit
     */
    public void setP_code_class_credit(java.lang.String p_code_class_credit) {
        this.p_code_class_credit = p_code_class_credit;
    }


    /**
     * Gets the p_code_gender value for this BusinessPartnerContent.
     * 
     * @return p_code_gender
     */
    public java.lang.String getP_code_gender() {
        return p_code_gender;
    }


    /**
     * Sets the p_code_gender value for this BusinessPartnerContent.
     * 
     * @param p_code_gender
     */
    public void setP_code_gender(java.lang.String p_code_gender) {
        this.p_code_gender = p_code_gender;
    }


    /**
     * Gets the p_code_nation value for this BusinessPartnerContent.
     * 
     * @return p_code_nation
     */
    public java.lang.String getP_code_nation() {
        return p_code_nation;
    }


    /**
     * Sets the p_code_nation value for this BusinessPartnerContent.
     * 
     * @param p_code_nation
     */
    public void setP_code_nation(java.lang.String p_code_nation) {
        this.p_code_nation = p_code_nation;
    }


    /**
     * Gets the p_code_tax_org value for this BusinessPartnerContent.
     * 
     * @return p_code_tax_org
     */
    public java.lang.String getP_code_tax_org() {
        return p_code_tax_org;
    }


    /**
     * Sets the p_code_tax_org value for this BusinessPartnerContent.
     * 
     * @param p_code_tax_org
     */
    public void setP_code_tax_org(java.lang.String p_code_tax_org) {
        this.p_code_tax_org = p_code_tax_org;
    }


    /**
     * Gets the p_email_address value for this BusinessPartnerContent.
     * 
     * @return p_email_address
     */
    public java.lang.String getP_email_address() {
        return p_email_address;
    }


    /**
     * Sets the p_email_address value for this BusinessPartnerContent.
     * 
     * @param p_email_address
     */
    public void setP_email_address(java.lang.String p_email_address) {
        this.p_email_address = p_email_address;
    }


    /**
     * Gets the p_family value for this BusinessPartnerContent.
     * 
     * @return p_family
     */
    public java.lang.String getP_family() {
        return p_family;
    }


    /**
     * Sets the p_family value for this BusinessPartnerContent.
     * 
     * @param p_family
     */
    public void setP_family(java.lang.String p_family) {
        this.p_family = p_family;
    }


    /**
     * Gets the p_first_name value for this BusinessPartnerContent.
     * 
     * @return p_first_name
     */
    public java.lang.String getP_first_name() {
        return p_first_name;
    }


    /**
     * Sets the p_first_name value for this BusinessPartnerContent.
     * 
     * @param p_first_name
     */
    public void setP_first_name(java.lang.String p_first_name) {
        this.p_first_name = p_first_name;
    }


    /**
     * Gets the p_inps value for this BusinessPartnerContent.
     * 
     * @return p_inps
     */
    public java.lang.String getP_inps() {
        return p_inps;
    }


    /**
     * Sets the p_inps value for this BusinessPartnerContent.
     * 
     * @param p_inps
     */
    public void setP_inps(java.lang.String p_inps) {
        this.p_inps = p_inps;
    }


    /**
     * Gets the p_num_certif_capacity value for this BusinessPartnerContent.
     * 
     * @return p_num_certif_capacity
     */
    public java.lang.String getP_num_certif_capacity() {
        return p_num_certif_capacity;
    }


    /**
     * Sets the p_num_certif_capacity value for this BusinessPartnerContent.
     * 
     * @param p_num_certif_capacity
     */
    public void setP_num_certif_capacity(java.lang.String p_num_certif_capacity) {
        this.p_num_certif_capacity = p_num_certif_capacity;
    }


    /**
     * Gets the p_number_tax_registration value for this BusinessPartnerContent.
     * 
     * @return p_number_tax_registration
     */
    public java.lang.String getP_number_tax_registration() {
        return p_number_tax_registration;
    }


    /**
     * Sets the p_number_tax_registration value for this BusinessPartnerContent.
     * 
     * @param p_number_tax_registration
     */
    public void setP_number_tax_registration(java.lang.String p_number_tax_registration) {
        this.p_number_tax_registration = p_number_tax_registration;
    }


    /**
     * Gets the p_passport_date_expiration value for this BusinessPartnerContent.
     * 
     * @return p_passport_date_expiration
     */
    public java.util.Date getP_passport_date_expiration() {
        return p_passport_date_expiration;
    }


    /**
     * Sets the p_passport_date_expiration value for this BusinessPartnerContent.
     * 
     * @param p_passport_date_expiration
     */
    public void setP_passport_date_expiration(java.util.Date p_passport_date_expiration) {
        this.p_passport_date_expiration = p_passport_date_expiration;
    }


    /**
     * Gets the p_passport_date_registration value for this BusinessPartnerContent.
     * 
     * @return p_passport_date_registration
     */
    public java.util.Date getP_passport_date_registration() {
        return p_passport_date_registration;
    }


    /**
     * Sets the p_passport_date_registration value for this BusinessPartnerContent.
     * 
     * @param p_passport_date_registration
     */
    public void setP_passport_date_registration(java.util.Date p_passport_date_registration) {
        this.p_passport_date_registration = p_passport_date_registration;
    }


    /**
     * Gets the p_passport_number value for this BusinessPartnerContent.
     * 
     * @return p_passport_number
     */
    public java.lang.String getP_passport_number() {
        return p_passport_number;
    }


    /**
     * Sets the p_passport_number value for this BusinessPartnerContent.
     * 
     * @param p_passport_number
     */
    public void setP_passport_number(java.lang.String p_passport_number) {
        this.p_passport_number = p_passport_number;
    }


    /**
     * Gets the p_passport_place_registration value for this BusinessPartnerContent.
     * 
     * @return p_passport_place_registration
     */
    public java.lang.String getP_passport_place_registration() {
        return p_passport_place_registration;
    }


    /**
     * Sets the p_passport_place_registration value for this BusinessPartnerContent.
     * 
     * @param p_passport_place_registration
     */
    public void setP_passport_place_registration(java.lang.String p_passport_place_registration) {
        this.p_passport_place_registration = p_passport_place_registration;
    }


    /**
     * Gets the p_passport_serial value for this BusinessPartnerContent.
     * 
     * @return p_passport_serial
     */
    public java.lang.String getP_passport_serial() {
        return p_passport_serial;
    }


    /**
     * Sets the p_passport_serial value for this BusinessPartnerContent.
     * 
     * @param p_passport_serial
     */
    public void setP_passport_serial(java.lang.String p_passport_serial) {
        this.p_passport_serial = p_passport_serial;
    }


    /**
     * Gets the p_passport_type value for this BusinessPartnerContent.
     * 
     * @return p_passport_type
     */
    public java.lang.String getP_passport_type() {
        return p_passport_type;
    }


    /**
     * Sets the p_passport_type value for this BusinessPartnerContent.
     * 
     * @param p_passport_type
     */
    public void setP_passport_type(java.lang.String p_passport_type) {
        this.p_passport_type = p_passport_type;
    }


    /**
     * Gets the p_patronymic value for this BusinessPartnerContent.
     * 
     * @return p_patronymic
     */
    public java.lang.String getP_patronymic() {
        return p_patronymic;
    }


    /**
     * Sets the p_patronymic value for this BusinessPartnerContent.
     * 
     * @param p_patronymic
     */
    public void setP_patronymic(java.lang.String p_patronymic) {
        this.p_patronymic = p_patronymic;
    }


    /**
     * Gets the p_pension_sertif_serial value for this BusinessPartnerContent.
     * 
     * @return p_pension_sertif_serial
     */
    public java.lang.String getP_pension_sertif_serial() {
        return p_pension_sertif_serial;
    }


    /**
     * Sets the p_pension_sertif_serial value for this BusinessPartnerContent.
     * 
     * @param p_pension_sertif_serial
     */
    public void setP_pension_sertif_serial(java.lang.String p_pension_sertif_serial) {
        this.p_pension_sertif_serial = p_pension_sertif_serial;
    }


    /**
     * Gets the p_phone_home value for this BusinessPartnerContent.
     * 
     * @return p_phone_home
     */
    public java.lang.String getP_phone_home() {
        return p_phone_home;
    }


    /**
     * Sets the p_phone_home value for this BusinessPartnerContent.
     * 
     * @param p_phone_home
     */
    public void setP_phone_home(java.lang.String p_phone_home) {
        this.p_phone_home = p_phone_home;
    }


    /**
     * Gets the p_phone_mobile value for this BusinessPartnerContent.
     * 
     * @return p_phone_mobile
     */
    public java.lang.String getP_phone_mobile() {
        return p_phone_mobile;
    }


    /**
     * Sets the p_phone_mobile value for this BusinessPartnerContent.
     * 
     * @param p_phone_mobile
     */
    public void setP_phone_mobile(java.lang.String p_phone_mobile) {
        this.p_phone_mobile = p_phone_mobile;
    }


    /**
     * Gets the p_post_address value for this BusinessPartnerContent.
     * 
     * @return p_post_address
     */
    public java.lang.String getP_post_address() {
        return p_post_address;
    }


    /**
     * Sets the p_post_address value for this BusinessPartnerContent.
     * 
     * @param p_post_address
     */
    public void setP_post_address(java.lang.String p_post_address) {
        this.p_post_address = p_post_address;
    }


    /**
     * Gets the p_type_document value for this BusinessPartnerContent.
     * 
     * @return p_type_document
     */
    public java.lang.String getP_type_document() {
        return p_type_document;
    }


    /**
     * Sets the p_type_document value for this BusinessPartnerContent.
     * 
     * @param p_type_document
     */
    public void setP_type_document(java.lang.String p_type_document) {
        this.p_type_document = p_type_document;
    }


    /**
     * Gets the sign_registr value for this BusinessPartnerContent.
     * 
     * @return sign_registr
     */
    public java.lang.Integer getSign_registr() {
        return sign_registr;
    }


    /**
     * Sets the sign_registr value for this BusinessPartnerContent.
     * 
     * @param sign_registr
     */
    public void setSign_registr(java.lang.Integer sign_registr) {
        this.sign_registr = sign_registr;
    }


    /**
     * Gets the state value for this BusinessPartnerContent.
     * 
     * @return state
     */
    public java.lang.Integer getState() {
        return state;
    }


    /**
     * Sets the state value for this BusinessPartnerContent.
     * 
     * @param state
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }


    /**
     * Gets the operation value for this BusinessPartnerContent.
     * 
     * @return operation
     */
    public java.lang.String getOperation() {
        return operation;
    }


    /**
     * Sets the operation value for this BusinessPartnerContent.
     * 
     * @param operation
     */
    public void setOperation(java.lang.String operation) {
        this.operation = operation;
    }


    public java.lang.String getOperation_origin() {
        return operation_origin;
    }

    public void setOperation_origin(java.lang.String operation_origin) {
        this.operation_origin = operation_origin;
    }

    
    /**
     * Gets the first_name value for this BusinessPartnerContent.
     * 
     * @return first_name
     */
    public java.lang.String getFirst_name() {
        return first_name;
    }


    /**
     * Sets the first_name value for this BusinessPartnerContent.
     * 
     * @param first_name
     */
    public void setFirst_name(java.lang.String first_name) {
        this.first_name = first_name;
    }


    /**
     * Gets the mid_name value for this BusinessPartnerContent.
     * 
     * @return mid_name
     */
    public java.lang.String getMid_name() {
        return mid_name;
    }


    /**
     * Sets the mid_name value for this BusinessPartnerContent.
     * 
     * @param mid_name
     */
    public void setMid_name(java.lang.String mid_name) {
        this.mid_name = mid_name;
    }


    /**
     * Gets the last_name value for this BusinessPartnerContent.
     * 
     * @return last_name
     */
    public java.lang.String getLast_name() {
        return last_name;
    }


    /**
     * Sets the last_name value for this BusinessPartnerContent.
     * 
     * @param last_name
     */
    public void setLast_name(java.lang.String last_name) {
        this.last_name = last_name;
    }


    /**
     * Gets the p_phone_home_country value for this BusinessPartnerContent.
     * 
     * @return p_phone_home_country
     */
    public java.lang.String getP_phone_home_country() {
        return p_phone_home_country;
    }


    /**
     * Sets the p_phone_home_country value for this BusinessPartnerContent.
     * 
     * @param p_phone_home_country
     */
    public void setP_phone_home_country(java.lang.String p_phone_home_country) {
        this.p_phone_home_country = p_phone_home_country;
    }


    /**
     * Gets the p_phone_mobile_country value for this BusinessPartnerContent.
     * 
     * @return p_phone_mobile_country
     */
    public java.lang.String getP_phone_mobile_country() {
        return p_phone_mobile_country;
    }


    /**
     * Sets the p_phone_mobile_country value for this BusinessPartnerContent.
     * 
     * @param p_phone_mobile_country
     */
    public void setP_phone_mobile_country(java.lang.String p_phone_mobile_country) {
        this.p_phone_mobile_country = p_phone_mobile_country;
    }


    /**
     * Gets the uvk_valid_from value for this BusinessPartnerContent.
     * 
     * @return uvk_valid_from
     */
    public java.util.Date getUvk_valid_from() {
        return uvk_valid_from;
    }


    /**
     * Sets the uvk_valid_from value for this BusinessPartnerContent.
     * 
     * @param uvk_valid_from
     */
    public void setUvk_valid_from(java.util.Date uvk_valid_from) {
        this.uvk_valid_from = uvk_valid_from;
    }


    /**
     * Gets the uvk_valid_to value for this BusinessPartnerContent.
     * 
     * @return uvk_valid_to
     */
    public java.util.Date getUvk_valid_to() {
        return uvk_valid_to;
    }


    /**
     * Sets the uvk_valid_to value for this BusinessPartnerContent.
     * 
     * @param uvk_valid_to
     */
    public void setUvk_valid_to(java.util.Date uvk_valid_to) {
        this.uvk_valid_to = uvk_valid_to;
    }


    /**
     * Gets the uvk_init_date value for this BusinessPartnerContent.
     * 
     * @return uvk_init_date
     */
    public java.util.Date getUvk_init_date() {
        return uvk_init_date;
    }


    /**
     * Sets the uvk_init_date value for this BusinessPartnerContent.
     * 
     * @param uvk_init_date
     */
    public void setUvk_init_date(java.util.Date uvk_init_date) {
        this.uvk_init_date = uvk_init_date;
    }


    /**
     * Gets the uvk_reason value for this BusinessPartnerContent.
     * 
     * @return uvk_reason
     */
    public java.lang.String getUvk_reason() {
        return uvk_reason;
    }


    /**
     * Sets the uvk_reason value for this BusinessPartnerContent.
     * 
     * @param uvk_reason
     */
    public void setUvk_reason(java.lang.String uvk_reason) {
        this.uvk_reason = uvk_reason;
    }


    /**
     * Gets the uvk_risk_lavel value for this BusinessPartnerContent.
     * 
     * @return uvk_risk_lavel
     */
    public java.lang.String getUvk_risk_lavel() {
        return uvk_risk_lavel;
    }


    /**
     * Sets the uvk_risk_lavel value for this BusinessPartnerContent.
     * 
     * @param uvk_risk_lavel
     */
    public void setUvk_risk_lavel(java.lang.String uvk_risk_lavel) {
        this.uvk_risk_lavel = uvk_risk_lavel;
    }


    /**
     * Gets the dul_country_code value for this BusinessPartnerContent.
     * 
     * @return dul_country_code
     */
    public java.lang.String getDul_country_code() {
        return dul_country_code;
    }


    /**
     * Sets the dul_country_code value for this BusinessPartnerContent.
     * 
     * @param dul_country_code
     */
    public void setDul_country_code(java.lang.String dul_country_code) {
        this.dul_country_code = dul_country_code;
    }


    /**
     * Gets the dul_region value for this BusinessPartnerContent.
     * 
     * @return dul_region
     */
    public java.lang.String getDul_region() {
        return dul_region;
    }


    /**
     * Sets the dul_region value for this BusinessPartnerContent.
     * 
     * @param dul_region
     */
    public void setDul_region(java.lang.String dul_region) {
        this.dul_region = dul_region;
    }


    /**
     * Gets the dul_district value for this BusinessPartnerContent.
     * 
     * @return dul_district
     */
    public java.lang.String getDul_district() {
        return dul_district;
    }


    /**
     * Sets the dul_district value for this BusinessPartnerContent.
     * 
     * @param dul_district
     */
    public void setDul_district(java.lang.String dul_district) {
        this.dul_district = dul_district;
    }


    /**
     * Gets the gc_series value for this BusinessPartnerContent.
     * 
     * @return gc_series
     */
    public java.lang.String getGc_series() {
        return gc_series;
    }


    /**
     * Sets the gc_series value for this BusinessPartnerContent.
     * 
     * @param gc_series
     */
    public void setGc_series(java.lang.String gc_series) {
        this.gc_series = gc_series;
    }


    /**
     * Gets the gc_number value for this BusinessPartnerContent.
     * 
     * @return gc_number
     */
    public java.lang.String getGc_number() {
        return gc_number;
    }


    /**
     * Sets the gc_number value for this BusinessPartnerContent.
     * 
     * @param gc_number
     */
    public void setGc_number(java.lang.String gc_number) {
        this.gc_number = gc_number;
    }


    /**
     * Gets the gc_country value for this BusinessPartnerContent.
     * 
     * @return gc_country
     */
    public java.lang.String getGc_country() {
        return gc_country;
    }


    /**
     * Sets the gc_country value for this BusinessPartnerContent.
     * 
     * @param gc_country
     */
    public void setGc_country(java.lang.String gc_country) {
        this.gc_country = gc_country;
    }


    /**
     * Gets the gc_date value for this BusinessPartnerContent.
     * 
     * @return gc_date
     */
    public java.util.Date getGc_date() {
        return gc_date;
    }


    /**
     * Sets the gc_date value for this BusinessPartnerContent.
     * 
     * @param gc_date
     */
    public void setGc_date(java.util.Date gc_date) {
        this.gc_date = gc_date;
    }


    /**
     * Gets the gc_date_valid_to value for this BusinessPartnerContent.
     * 
     * @return gc_date_valid_to
     */
    public java.util.Date getGc_date_valid_to() {
        return gc_date_valid_to;
    }


    /**
     * Sets the gc_date_valid_to value for this BusinessPartnerContent.
     * 
     * @param gc_date_valid_to
     */
    public void setGc_date_valid_to(java.util.Date gc_date_valid_to) {
        this.gc_date_valid_to = gc_date_valid_to;
    }


    /**
     * Gets the gc_issued_by value for this BusinessPartnerContent.
     * 
     * @return gc_issued_by
     */
    public java.lang.String getGc_issued_by() {
        return gc_issued_by;
    }


    /**
     * Sets the gc_issued_by value for this BusinessPartnerContent.
     * 
     * @param gc_issued_by
     */
    public void setGc_issued_by(java.lang.String gc_issued_by) {
        this.gc_issued_by = gc_issued_by;
    }


    /**
     * Gets the gc_region value for this BusinessPartnerContent.
     * 
     * @return gc_region
     */
    public java.lang.String getGc_region() {
        return gc_region;
    }


    /**
     * Sets the gc_region value for this BusinessPartnerContent.
     * 
     * @param gc_region
     */
    public void setGc_region(java.lang.String gc_region) {
        this.gc_region = gc_region;
    }


    /**
     * Gets the gc_district value for this BusinessPartnerContent.
     * 
     * @return gc_district
     */
    public java.lang.String getGc_district() {
        return gc_district;
    }


    /**
     * Sets the gc_district value for this BusinessPartnerContent.
     * 
     * @param gc_district
     */
    public void setGc_district(java.lang.String gc_district) {
        this.gc_district = gc_district;
    }


    /**
     * Gets the profile_author value for this BusinessPartnerContent.
     * 
     * @return profile_author   * Автор профайла
     */
    public java.lang.String getProfile_author() {
        return profile_author;
    }


    /**
     * Sets the profile_author value for this BusinessPartnerContent.
     * 
     * @param profile_author   * Автор профайла
     */
    public void setProfile_author(java.lang.String profile_author) {
        this.profile_author = profile_author;
    }


    /**
     * Gets the som_opers value for this BusinessPartnerContent.
     * 
     * @return som_opers   * Cомнительные операции
     */
    public java.lang.String getSom_opers() {
        return som_opers;
    }


    /**
     * Sets the som_opers value for this BusinessPartnerContent.
     * 
     * @param som_opers   * Cомнительные операции
     */
    public void setSom_opers(java.lang.String som_opers) {
        this.som_opers = som_opers;
    }


    /**
     * Gets the pod_opers value for this BusinessPartnerContent.
     * 
     * @return pod_opers   * Подозрительные операции
     */
    public java.lang.String getPod_opers() {
        return pod_opers;
    }


    /**
     * Sets the pod_opers value for this BusinessPartnerContent.
     * 
     * @param pod_opers   * Подозрительные операции
     */
    public void setPod_opers(java.lang.String pod_opers) {
        this.pod_opers = pod_opers;
    }


    /**
     * Gets the sales_data value for this BusinessPartnerContent.
     * 
     * @return sales_data   * сбытовые данные
     */
    public java.lang.String getSales_data() {
        return sales_data;
    }


    /**
     * Sets the sales_data value for this BusinessPartnerContent.
     * 
     * @param sales_data   * сбытовые данные
     */
    public void setSales_data(java.lang.String sales_data) {
        this.sales_data = sales_data;
    }


    /**
     * Gets the adr_district value for this BusinessPartnerContent.
     * 
     * @return adr_district   * Массив/Квартал
     */
    public java.lang.String getAdr_district() {
        return adr_district;
    }


    /**
     * Sets the adr_district value for this BusinessPartnerContent.
     * 
     * @param adr_district   * Массив/Квартал
     */
    public void setAdr_district(java.lang.String adr_district) {
        this.adr_district = adr_district;
    }


    /**
     * Gets the adr_street value for this BusinessPartnerContent.
     * 
     * @return adr_street   * Улица
     */
    public java.lang.String getAdr_street() {
        return adr_street;
    }


    /**
     * Sets the adr_street value for this BusinessPartnerContent.
     * 
     * @param adr_street   * Улица
     */
    public void setAdr_street(java.lang.String adr_street) {
        this.adr_street = adr_street;
    }


    /**
     * Gets the adr_building value for this BusinessPartnerContent.
     * 
     * @return adr_building   * Дом/Строение
     */
    public java.lang.String getAdr_building() {
        return adr_building;
    }


    /**
     * Sets the adr_building value for this BusinessPartnerContent.
     * 
     * @param adr_building   * Дом/Строение
     */
    public void setAdr_building(java.lang.String adr_building) {
        this.adr_building = adr_building;
    }


    /**
     * Gets the adr_room value for this BusinessPartnerContent.
     * 
     * @return adr_room   * Квартира/офис
     */
    public java.lang.String getAdr_room() {
        return adr_room;
    }


    /**
     * Sets the adr_room value for this BusinessPartnerContent.
     * 
     * @param adr_room   * Квартира/офис
     */
    public void setAdr_room(java.lang.String adr_room) {
        this.adr_room = adr_room;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessPartnerContent)) return false;
        BusinessPartnerContent other = (BusinessPartnerContent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.branch==null && other.getBranch()==null) || 
             (this.branch!=null &&
              this.branch.equals(other.getBranch()))) &&
            ((this.id_client==null && other.getId_client()==null) || 
             (this.id_client!=null &&
              this.id_client.equals(other.getId_client()))) &&
            ((this.id_client_sap==null && other.getId_client_sap()==null) || 
             (this.id_client_sap!=null &&
              this.id_client_sap.equals(other.getId_client_sap()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.code_country==null && other.getCode_country()==null) || 
             (this.code_country!=null &&
              this.code_country.equals(other.getCode_country()))) &&
            ((this.code_form==null && other.getCode_form()==null) || 
             (this.code_form!=null &&
              this.code_form.equals(other.getCode_form()))) &&
            ((this.code_resident==null && other.getCode_resident()==null) || 
             (this.code_resident!=null &&
              this.code_resident.equals(other.getCode_resident()))) &&
            ((this.code_subject==null && other.getCode_subject()==null) || 
             (this.code_subject!=null &&
              this.code_subject.equals(other.getCode_subject()))) &&
            ((this.code_type==null && other.getCode_type()==null) || 
             (this.code_type!=null &&
              this.code_type.equals(other.getCode_type()))) &&
            ((this.date_close==null && other.getDate_close()==null) || 
             (this.date_close!=null &&
              this.date_close.equals(other.getDate_close()))) &&
            ((this.date_open==null && other.getDate_open()==null) || 
             (this.date_open!=null &&
              this.date_open.equals(other.getDate_open()))) &&
            ((this.p_birth_place==null && other.getP_birth_place()==null) || 
             (this.p_birth_place!=null &&
              this.p_birth_place.equals(other.getP_birth_place()))) &&
            ((this.p_birthday==null && other.getP_birthday()==null) || 
             (this.p_birthday!=null &&
              this.p_birthday.equals(other.getP_birthday()))) &&
            ((this.p_capacity_status_date==null && other.getP_capacity_status_date()==null) || 
             (this.p_capacity_status_date!=null &&
              this.p_capacity_status_date.equals(other.getP_capacity_status_date()))) &&
            ((this.p_capacity_status_place==null && other.getP_capacity_status_place()==null) || 
             (this.p_capacity_status_place!=null &&
              this.p_capacity_status_place.equals(other.getP_capacity_status_place()))) &&
            ((this.p_code_adr_distr==null && other.getP_code_adr_distr()==null) || 
             (this.p_code_adr_distr!=null &&
              this.p_code_adr_distr.equals(other.getP_code_adr_distr()))) &&
            ((this.p_code_adr_region==null && other.getP_code_adr_region()==null) || 
             (this.p_code_adr_region!=null &&
              this.p_code_adr_region.equals(other.getP_code_adr_region()))) &&
            ((this.p_code_bank==null && other.getP_code_bank()==null) || 
             (this.p_code_bank!=null &&
              this.p_code_bank.equals(other.getP_code_bank()))) &&
            ((this.p_code_birth_distr==null && other.getP_code_birth_distr()==null) || 
             (this.p_code_birth_distr!=null &&
              this.p_code_birth_distr.equals(other.getP_code_birth_distr()))) &&
            ((this.p_code_birth_region==null && other.getP_code_birth_region()==null) || 
             (this.p_code_birth_region!=null &&
              this.p_code_birth_region.equals(other.getP_code_birth_region()))) &&
            ((this.p_code_capacity==null && other.getP_code_capacity()==null) || 
             (this.p_code_capacity!=null &&
              this.p_code_capacity.equals(other.getP_code_capacity()))) &&
            ((this.p_code_citizenship==null && other.getP_code_citizenship()==null) || 
             (this.p_code_citizenship!=null &&
              this.p_code_citizenship.equals(other.getP_code_citizenship()))) &&
            ((this.p_code_class_credit==null && other.getP_code_class_credit()==null) || 
             (this.p_code_class_credit!=null &&
              this.p_code_class_credit.equals(other.getP_code_class_credit()))) &&
            ((this.p_code_gender==null && other.getP_code_gender()==null) || 
             (this.p_code_gender!=null &&
              this.p_code_gender.equals(other.getP_code_gender()))) &&
            ((this.p_code_nation==null && other.getP_code_nation()==null) || 
             (this.p_code_nation!=null &&
              this.p_code_nation.equals(other.getP_code_nation()))) &&
            ((this.p_code_tax_org==null && other.getP_code_tax_org()==null) || 
             (this.p_code_tax_org!=null &&
              this.p_code_tax_org.equals(other.getP_code_tax_org()))) &&
            ((this.p_email_address==null && other.getP_email_address()==null) || 
             (this.p_email_address!=null &&
              this.p_email_address.equals(other.getP_email_address()))) &&
            ((this.p_family==null && other.getP_family()==null) || 
             (this.p_family!=null &&
              this.p_family.equals(other.getP_family()))) &&
            ((this.p_first_name==null && other.getP_first_name()==null) || 
             (this.p_first_name!=null &&
              this.p_first_name.equals(other.getP_first_name()))) &&
            ((this.p_inps==null && other.getP_inps()==null) || 
             (this.p_inps!=null &&
              this.p_inps.equals(other.getP_inps()))) &&
            ((this.p_num_certif_capacity==null && other.getP_num_certif_capacity()==null) || 
             (this.p_num_certif_capacity!=null &&
              this.p_num_certif_capacity.equals(other.getP_num_certif_capacity()))) &&
            ((this.p_number_tax_registration==null && other.getP_number_tax_registration()==null) || 
             (this.p_number_tax_registration!=null &&
              this.p_number_tax_registration.equals(other.getP_number_tax_registration()))) &&
            ((this.p_passport_date_expiration==null && other.getP_passport_date_expiration()==null) || 
             (this.p_passport_date_expiration!=null &&
              this.p_passport_date_expiration.equals(other.getP_passport_date_expiration()))) &&
            ((this.p_passport_date_registration==null && other.getP_passport_date_registration()==null) || 
             (this.p_passport_date_registration!=null &&
              this.p_passport_date_registration.equals(other.getP_passport_date_registration()))) &&
            ((this.p_passport_number==null && other.getP_passport_number()==null) || 
             (this.p_passport_number!=null &&
              this.p_passport_number.equals(other.getP_passport_number()))) &&
            ((this.p_passport_place_registration==null && other.getP_passport_place_registration()==null) || 
             (this.p_passport_place_registration!=null &&
              this.p_passport_place_registration.equals(other.getP_passport_place_registration()))) &&
            ((this.p_passport_serial==null && other.getP_passport_serial()==null) || 
             (this.p_passport_serial!=null &&
              this.p_passport_serial.equals(other.getP_passport_serial()))) &&
            ((this.p_passport_type==null && other.getP_passport_type()==null) || 
             (this.p_passport_type!=null &&
              this.p_passport_type.equals(other.getP_passport_type()))) &&
            ((this.p_patronymic==null && other.getP_patronymic()==null) || 
             (this.p_patronymic!=null &&
              this.p_patronymic.equals(other.getP_patronymic()))) &&
            ((this.p_pension_sertif_serial==null && other.getP_pension_sertif_serial()==null) || 
             (this.p_pension_sertif_serial!=null &&
              this.p_pension_sertif_serial.equals(other.getP_pension_sertif_serial()))) &&
            ((this.p_phone_home==null && other.getP_phone_home()==null) || 
             (this.p_phone_home!=null &&
              this.p_phone_home.equals(other.getP_phone_home()))) &&
            ((this.p_phone_mobile==null && other.getP_phone_mobile()==null) || 
             (this.p_phone_mobile!=null &&
              this.p_phone_mobile.equals(other.getP_phone_mobile()))) &&
            ((this.p_post_address==null && other.getP_post_address()==null) || 
             (this.p_post_address!=null &&
              this.p_post_address.equals(other.getP_post_address()))) &&
            ((this.p_type_document==null && other.getP_type_document()==null) || 
             (this.p_type_document!=null &&
              this.p_type_document.equals(other.getP_type_document()))) &&
            ((this.sign_registr==null && other.getSign_registr()==null) || 
             (this.sign_registr!=null &&
              this.sign_registr.equals(other.getSign_registr()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.operation==null && other.getOperation()==null) || 
             (this.operation!=null &&
              this.operation.equals(other.getOperation()))) &&
            ((this.operation_origin==null && other.getOperation_origin()==null) || 
                    (this.operation_origin!=null &&
                     this.operation_origin.equals(other.getOperation_origin()))) &&
            ((this.first_name==null && other.getFirst_name()==null) || 
             (this.first_name!=null &&
              this.first_name.equals(other.getFirst_name()))) &&
            ((this.mid_name==null && other.getMid_name()==null) || 
             (this.mid_name!=null &&
              this.mid_name.equals(other.getMid_name()))) &&
            ((this.last_name==null && other.getLast_name()==null) || 
             (this.last_name!=null &&
              this.last_name.equals(other.getLast_name()))) &&
            ((this.p_phone_home_country==null && other.getP_phone_home_country()==null) || 
             (this.p_phone_home_country!=null &&
              this.p_phone_home_country.equals(other.getP_phone_home_country()))) &&
            ((this.p_phone_mobile_country==null && other.getP_phone_mobile_country()==null) || 
             (this.p_phone_mobile_country!=null &&
              this.p_phone_mobile_country.equals(other.getP_phone_mobile_country()))) &&
            ((this.uvk_valid_from==null && other.getUvk_valid_from()==null) || 
             (this.uvk_valid_from!=null &&
              this.uvk_valid_from.equals(other.getUvk_valid_from()))) &&
            ((this.uvk_valid_to==null && other.getUvk_valid_to()==null) || 
             (this.uvk_valid_to!=null &&
              this.uvk_valid_to.equals(other.getUvk_valid_to()))) &&
            ((this.uvk_init_date==null && other.getUvk_init_date()==null) || 
             (this.uvk_init_date!=null &&
              this.uvk_init_date.equals(other.getUvk_init_date()))) &&
            ((this.uvk_reason==null && other.getUvk_reason()==null) || 
             (this.uvk_reason!=null &&
              this.uvk_reason.equals(other.getUvk_reason()))) &&
            ((this.uvk_risk_lavel==null && other.getUvk_risk_lavel()==null) || 
             (this.uvk_risk_lavel!=null &&
              this.uvk_risk_lavel.equals(other.getUvk_risk_lavel()))) &&
            ((this.dul_country_code==null && other.getDul_country_code()==null) || 
             (this.dul_country_code!=null &&
              this.dul_country_code.equals(other.getDul_country_code()))) &&
            ((this.dul_region==null && other.getDul_region()==null) || 
             (this.dul_region!=null &&
              this.dul_region.equals(other.getDul_region()))) &&
            ((this.dul_district==null && other.getDul_district()==null) || 
             (this.dul_district!=null &&
              this.dul_district.equals(other.getDul_district()))) &&
            ((this.gc_series==null && other.getGc_series()==null) || 
             (this.gc_series!=null &&
              this.gc_series.equals(other.getGc_series()))) &&
            ((this.gc_number==null && other.getGc_number()==null) || 
             (this.gc_number!=null &&
              this.gc_number.equals(other.getGc_number()))) &&
            ((this.gc_country==null && other.getGc_country()==null) || 
             (this.gc_country!=null &&
              this.gc_country.equals(other.getGc_country()))) &&
            ((this.gc_date==null && other.getGc_date()==null) || 
             (this.gc_date!=null &&
              this.gc_date.equals(other.getGc_date()))) &&
            ((this.gc_date_valid_to==null && other.getGc_date_valid_to()==null) || 
             (this.gc_date_valid_to!=null &&
              this.gc_date_valid_to.equals(other.getGc_date_valid_to()))) &&
            ((this.gc_issued_by==null && other.getGc_issued_by()==null) || 
             (this.gc_issued_by!=null &&
              this.gc_issued_by.equals(other.getGc_issued_by()))) &&
            ((this.gc_region==null && other.getGc_region()==null) || 
             (this.gc_region!=null &&
              this.gc_region.equals(other.getGc_region()))) &&
            ((this.gc_district==null && other.getGc_district()==null) || 
             (this.gc_district!=null &&
              this.gc_district.equals(other.getGc_district()))) &&
            ((this.profile_author==null && other.getProfile_author()==null) || 
             (this.profile_author!=null &&
              this.profile_author.equals(other.getProfile_author()))) &&
            ((this.som_opers==null && other.getSom_opers()==null) || 
             (this.som_opers!=null &&
              this.som_opers.equals(other.getSom_opers()))) &&
            ((this.pod_opers==null && other.getPod_opers()==null) || 
             (this.pod_opers!=null &&
              this.pod_opers.equals(other.getPod_opers()))) &&
            ((this.sales_data==null && other.getSales_data()==null) || 
             (this.sales_data!=null &&
              this.sales_data.equals(other.getSales_data()))) &&
            ((this.adr_district==null && other.getAdr_district()==null) || 
             (this.adr_district!=null &&
              this.adr_district.equals(other.getAdr_district()))) &&
            ((this.adr_street==null && other.getAdr_street()==null) || 
             (this.adr_street!=null &&
              this.adr_street.equals(other.getAdr_street()))) &&
            ((this.adr_building==null && other.getAdr_building()==null) || 
             (this.adr_building!=null &&
              this.adr_building.equals(other.getAdr_building()))) &&
            ((this.adr_room==null && other.getAdr_room()==null) || 
             (this.adr_room!=null &&
              this.adr_room.equals(other.getAdr_room())));
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
        if (getBranch() != null) {
            _hashCode += getBranch().hashCode();
        }
        if (getId_client() != null) {
            _hashCode += getId_client().hashCode();
        }
        if (getId_client_sap() != null) {
            _hashCode += getId_client_sap().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getCode_country() != null) {
            _hashCode += getCode_country().hashCode();
        }
        if (getCode_form() != null) {
            _hashCode += getCode_form().hashCode();
        }
        if (getCode_resident() != null) {
            _hashCode += getCode_resident().hashCode();
        }
        if (getCode_subject() != null) {
            _hashCode += getCode_subject().hashCode();
        }
        if (getCode_type() != null) {
            _hashCode += getCode_type().hashCode();
        }
        if (getDate_close() != null) {
            _hashCode += getDate_close().hashCode();
        }
        if (getDate_open() != null) {
            _hashCode += getDate_open().hashCode();
        }
        if (getP_birth_place() != null) {
            _hashCode += getP_birth_place().hashCode();
        }
        if (getP_birthday() != null) {
            _hashCode += getP_birthday().hashCode();
        }
        if (getP_capacity_status_date() != null) {
            _hashCode += getP_capacity_status_date().hashCode();
        }
        if (getP_capacity_status_place() != null) {
            _hashCode += getP_capacity_status_place().hashCode();
        }
        if (getP_code_adr_distr() != null) {
            _hashCode += getP_code_adr_distr().hashCode();
        }
        if (getP_code_adr_region() != null) {
            _hashCode += getP_code_adr_region().hashCode();
        }
        if (getP_code_bank() != null) {
            _hashCode += getP_code_bank().hashCode();
        }
        if (getP_code_birth_distr() != null) {
            _hashCode += getP_code_birth_distr().hashCode();
        }
        if (getP_code_birth_region() != null) {
            _hashCode += getP_code_birth_region().hashCode();
        }
        if (getP_code_capacity() != null) {
            _hashCode += getP_code_capacity().hashCode();
        }
        if (getP_code_citizenship() != null) {
            _hashCode += getP_code_citizenship().hashCode();
        }
        if (getP_code_class_credit() != null) {
            _hashCode += getP_code_class_credit().hashCode();
        }
        if (getP_code_gender() != null) {
            _hashCode += getP_code_gender().hashCode();
        }
        if (getP_code_nation() != null) {
            _hashCode += getP_code_nation().hashCode();
        }
        if (getP_code_tax_org() != null) {
            _hashCode += getP_code_tax_org().hashCode();
        }
        if (getP_email_address() != null) {
            _hashCode += getP_email_address().hashCode();
        }
        if (getP_family() != null) {
            _hashCode += getP_family().hashCode();
        }
        if (getP_first_name() != null) {
            _hashCode += getP_first_name().hashCode();
        }
        if (getP_inps() != null) {
            _hashCode += getP_inps().hashCode();
        }
        if (getP_num_certif_capacity() != null) {
            _hashCode += getP_num_certif_capacity().hashCode();
        }
        if (getP_number_tax_registration() != null) {
            _hashCode += getP_number_tax_registration().hashCode();
        }
        if (getP_passport_date_expiration() != null) {
            _hashCode += getP_passport_date_expiration().hashCode();
        }
        if (getP_passport_date_registration() != null) {
            _hashCode += getP_passport_date_registration().hashCode();
        }
        if (getP_passport_number() != null) {
            _hashCode += getP_passport_number().hashCode();
        }
        if (getP_passport_place_registration() != null) {
            _hashCode += getP_passport_place_registration().hashCode();
        }
        if (getP_passport_serial() != null) {
            _hashCode += getP_passport_serial().hashCode();
        }
        if (getP_passport_type() != null) {
            _hashCode += getP_passport_type().hashCode();
        }
        if (getP_patronymic() != null) {
            _hashCode += getP_patronymic().hashCode();
        }
        if (getP_pension_sertif_serial() != null) {
            _hashCode += getP_pension_sertif_serial().hashCode();
        }
        if (getP_phone_home() != null) {
            _hashCode += getP_phone_home().hashCode();
        }
        if (getP_phone_mobile() != null) {
            _hashCode += getP_phone_mobile().hashCode();
        }
        if (getP_post_address() != null) {
            _hashCode += getP_post_address().hashCode();
        }
        if (getP_type_document() != null) {
            _hashCode += getP_type_document().hashCode();
        }
        if (getSign_registr() != null) {
            _hashCode += getSign_registr().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getOperation() != null) {
            _hashCode += getOperation().hashCode();
        }
        if (getOperation_origin() != null) {
            _hashCode += getOperation_origin().hashCode();
        }
        if (getFirst_name() != null) {
            _hashCode += getFirst_name().hashCode();
        }
        if (getMid_name() != null) {
            _hashCode += getMid_name().hashCode();
        }
        if (getLast_name() != null) {
            _hashCode += getLast_name().hashCode();
        }
        if (getP_phone_home_country() != null) {
            _hashCode += getP_phone_home_country().hashCode();
        }
        if (getP_phone_mobile_country() != null) {
            _hashCode += getP_phone_mobile_country().hashCode();
        }
        if (getUvk_valid_from() != null) {
            _hashCode += getUvk_valid_from().hashCode();
        }
        if (getUvk_valid_to() != null) {
            _hashCode += getUvk_valid_to().hashCode();
        }
        if (getUvk_init_date() != null) {
            _hashCode += getUvk_init_date().hashCode();
        }
        if (getUvk_reason() != null) {
            _hashCode += getUvk_reason().hashCode();
        }
        if (getUvk_risk_lavel() != null) {
            _hashCode += getUvk_risk_lavel().hashCode();
        }
        if (getDul_country_code() != null) {
            _hashCode += getDul_country_code().hashCode();
        }
        if (getDul_region() != null) {
            _hashCode += getDul_region().hashCode();
        }
        if (getDul_district() != null) {
            _hashCode += getDul_district().hashCode();
        }
        if (getGc_series() != null) {
            _hashCode += getGc_series().hashCode();
        }
        if (getGc_number() != null) {
            _hashCode += getGc_number().hashCode();
        }
        if (getGc_country() != null) {
            _hashCode += getGc_country().hashCode();
        }
        if (getGc_date() != null) {
            _hashCode += getGc_date().hashCode();
        }
        if (getGc_date_valid_to() != null) {
            _hashCode += getGc_date_valid_to().hashCode();
        }
        if (getGc_issued_by() != null) {
            _hashCode += getGc_issued_by().hashCode();
        }
        if (getGc_region() != null) {
            _hashCode += getGc_region().hashCode();
        }
        if (getGc_district() != null) {
            _hashCode += getGc_district().hashCode();
        }
        if (getProfile_author() != null) {
            _hashCode += getProfile_author().hashCode();
        }
        if (getSom_opers() != null) {
            _hashCode += getSom_opers().hashCode();
        }
        if (getPod_opers() != null) {
            _hashCode += getPod_opers().hashCode();
        }
        if (getSales_data() != null) {
            _hashCode += getSales_data().hashCode();
        }
        if (getAdr_district() != null) {
            _hashCode += getAdr_district().hashCode();
        }
        if (getAdr_street() != null) {
            _hashCode += getAdr_street().hashCode();
        }
        if (getAdr_building() != null) {
            _hashCode += getAdr_building().hashCode();
        }
        if (getAdr_room() != null) {
            _hashCode += getAdr_room().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessPartnerContent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client_sap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client_sap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_form");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_form"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_resident");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_resident"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_close");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date_close"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_open");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date_open"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_birth_place");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_birth_place"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_capacity_status_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_capacity_status_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_capacity_status_place");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_capacity_status_place"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_adr_distr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_adr_distr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_adr_region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_adr_region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_bank");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_bank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_birth_distr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_birth_distr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_birth_region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_birth_region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_capacity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_capacity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_class_credit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_class_credit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_nation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_nation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_code_tax_org");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_code_tax_org"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_email_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_email_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_family");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_family"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_first_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_first_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_inps");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_inps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_num_certif_capacity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_num_certif_capacity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_number_tax_registration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_number_tax_registration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_date_expiration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_date_expiration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_date_registration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_date_registration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_place_registration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_place_registration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_serial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_serial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_passport_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_passport_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_patronymic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_patronymic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_pension_sertif_serial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_pension_sertif_serial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_phone_home");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_phone_home"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_phone_mobile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_phone_mobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_post_address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_post_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_type_document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_type_document"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sign_registr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sign_registr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operation_origin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operation_origin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "first_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mid_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mid_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "last_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_phone_home_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_phone_home_country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_phone_mobile_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_phone_mobile_country"));
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
        elemField.setFieldName("uvk_init_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_init_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uvk_risk_lavel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uvk_risk_lavel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dul_country_code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dul_country_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dul_region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dul_region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dul_district");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dul_district"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_series");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_series"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_date_valid_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_date_valid_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_issued_by");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_issued_by"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gc_district");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gc_district"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profile_author");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("som_opers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "som_opers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pod_opers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pod_opers"));
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
        elemField.setFieldName("adr_district");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adr_district"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adr_street");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adr_street"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adr_building");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adr_building"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adr_room");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adr_room"));
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
