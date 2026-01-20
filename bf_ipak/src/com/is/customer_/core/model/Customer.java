package com.is.customer_.core.model;

import java.util.Date;

import org.joda.time.DateTime;

import com.is.account.model.Account;

import lombok.Data;

/**
 * Created by root on 06.05.2017.
 * 21:02
 */
@SuppressWarnings("ALL")
@Data
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
    private String p_code_adr_mahalla;
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

    // ��� �� ������ � SAP �� ����� ��� ���
    // ������� SAP
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
                    String subbranch,
                    String code_adr_mahalla) {
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
        this.p_code_adr_mahalla = code_adr_mahalla;
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
