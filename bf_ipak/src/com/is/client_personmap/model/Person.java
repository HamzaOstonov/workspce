package com.is.client_personmap.model;

import java.util.Date;

import com.google.common.base.Objects;
import com.is.clients.models.ClientJ;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString

public class Person implements Cloneable {

    @Getter
    @Setter
    protected PersonMap personMap;

    @Getter
    @Setter
    protected String id;
    @Getter
    @Setter
    protected String idSap;
    @Getter
    @Setter
    protected String branch;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected Date birthday;
    @Getter
    @Setter
    protected String post_address;
    @Getter
    @Setter
    protected String passport_type;
    @Getter
    @Setter
    protected String passport_serial;
    @Getter
    @Setter
    protected String passport_number;
    @Getter
    @Setter
    protected String passport_place_registration;
    @Getter
    @Setter
    protected Date passport_date_registration;
    @Getter
    @Setter
    protected String code_place_regist;
    @Getter
    @Setter
    protected Date date_registration;
    @Getter
    @Setter
    protected String number_registration_doc;
    @Getter
    @Setter
    protected String code_tax_org;
    @Getter
    @Setter
    protected String number_tax_registration;
    @Getter
    @Setter
    protected int state;
    @Getter
    @Setter
    protected String code_citizenship;
    @Getter
    @Setter
    protected String birth_place;
    @Getter
    @Setter
    protected String code_capacity;
    @Getter
    @Setter
    protected Date capacity_status_date;
    @Getter
    @Setter
    protected String capacity_status_place;
    @Getter
    @Setter
    protected String num_certif_capacity;
    @Getter
    @Setter
    protected String phone_home;
    @Getter
    @Setter
    protected String phone_mobile;
    @Getter
    @Setter
    protected String email_address;
    @Getter
    @Setter
    protected String pension_sertif_serial;
    @Getter
    @Setter
    protected String code_gender;
    @Getter
    @Setter
    protected String code_nation;
    @Getter
    @Setter
    protected String code_birth_region;
    @Getter
    @Setter
    protected String code_birth_distr;
    @Getter
    @Setter
    protected String type_document;
    @Getter
    @Setter
    protected Date passport_date_expiration;
    @Getter
    @Setter
    protected String code_adr_region;
    @Getter
    @Setter
    protected String code_adr_distr;
    @Getter
    @Setter
    protected String inps;
    @Getter
    @Setter
    protected String pinfl;
    @Getter
    @Setter
    protected String family;
    @Getter
    @Setter
    protected String first_name;
    @Getter
    @Setter
    protected String patronymic;
    @Getter
    @Setter
    protected String family_local;
    @Getter
    @Setter
    protected String first_name_local;
    @Getter
    @Setter
    protected String patronymic_local;
    @Getter
    @Setter
    protected String code_country;
    @Getter
    @Setter
    protected String code_resident;

    @Getter
    @Setter
    protected String pass_place_region;
    @Getter
    @Setter
    protected String pass_place_district;

    @Getter
    @Setter
    protected String union_id;

    @Getter
    @Setter
    protected String parent_id_client_j;  /* eto pole iz client_j.id*/

    @Getter
    @Setter
    protected String person_role;  

    @Getter
    @Setter
    protected String sign_public_official;

    @Getter
    @Setter
    protected String po_job_title;
    
    @Getter
    @Setter
    protected String po_welfare_source;
    
    @Getter
    @Setter
    protected String po_other_data;

    @Getter
    @Setter
    protected String emp_id;
    
    @Getter
    @Setter
    protected boolean checkedInAtaccama;    
    
    public Person() {
    	this.checkedInAtaccama=false;
    }

    public Person(
            String branch,
            String id,
            String name,
            Date birthday,
            String post_address,
            String passport_type,
            String passport_serial,
            String passport_number,
            String passport_place_registration,
            Date passport_date_registration,
            String code_place_regist,
            Date date_registration,
            String number_registration_doc,
            String code_tax_org,
            String number_tax_registration,
            int state,
            String code_citizenship,
            String birth_place,
            String code_capacity,
            Date capacity_status_date,
            String capacity_status_place, String num_certif_capacity,
            String phone_home, String phone_mobile, String email_address,
            String pension_sertif_serial, String code_gender,
            String code_nation, String code_birth_region,
            String code_birth_distr, String type_document,
            Date passport_date_expiration, String code_adr_region,
            String code_adr_distr, String inps, String family,
            String first_name, String patronymic, String code_country,
            String code_resident, String family_local, String first_name_local,
            String patronymic_local, String pass_place_region, String pass_place_district, String union_id) {
        super();
        this.branch = branch;
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.post_address = post_address;
        this.passport_type = passport_type;
        this.passport_serial = passport_serial;
        this.passport_number = passport_number;
        this.passport_place_registration = passport_place_registration;
        this.passport_date_registration = passport_date_registration;
        this.code_place_regist = code_place_regist;
        this.date_registration = date_registration;
        this.number_registration_doc = number_registration_doc;
        this.code_tax_org = code_tax_org;
        this.number_tax_registration = number_tax_registration;
//		this.code_sector = code_sector;
//		this.code_organ_direct = code_organ_direct;
//		this.code_bank = code_bank;
//		this.account = account;
//		this.code_class_credit = code_class_credit;
        this.state = state;
//		this.kod_err = kod_err;
//		this.file_name = file_name;
        this.code_citizenship = code_citizenship;
        this.birth_place = birth_place;
        this.code_capacity = code_capacity;
        this.capacity_status_date = capacity_status_date;
        this.capacity_status_place = capacity_status_place;
        this.num_certif_capacity = num_certif_capacity;
        this.phone_home = phone_home;
        this.phone_mobile = phone_mobile;
        this.email_address = email_address;
        this.pension_sertif_serial = pension_sertif_serial;
        this.code_gender = code_gender;
        this.code_nation = code_nation;
        this.code_birth_region = code_birth_region;
        this.code_birth_distr = code_birth_distr;
        this.type_document = type_document;
        this.passport_date_expiration = passport_date_expiration;
        this.code_adr_region = code_adr_region;
        this.code_adr_distr = code_adr_distr;
        this.inps = inps;
        this.family = family;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.code_country = code_country;
        this.code_resident = code_resident;
        this.family_local = family_local;
        this.first_name_local = first_name_local;
        this.patronymic_local = patronymic_local;
        this.pass_place_district = pass_place_district;
        this.pass_place_region = pass_place_region;
        this.union_id = union_id;
        this.checkedInAtaccama=false;
    }

    public Person(ClientJ client) {
        if (client.getIndividualEnterpreneur() != null && client.getIndividualEnterpreneur().getCustomer() != null) {
            setIdSap(client.getIndividualEnterpreneur().getCustomer().getIdSap());
        }
        setBranch(client.getBranch());
        setBirth_place(client.getP_birth_place());
        setBirthday(client.getP_birthday());
        setCode_resident(client.getCode_resident());
//		setP_capacity_status_date(client.getP_capacity_status_date);
//		setP_capacity_status_place(client.getP_capacity_status_place);
        setCode_adr_distr(client.getP_code_adr_distr());
        setCode_adr_region(client.getP_code_adr_region());
//		setCode_bank(client.getP_code_bank());
        setCode_birth_distr(client.getP_code_birth_distr());
        setCode_birth_region(client.getP_code_birth_region());
        setCode_capacity(client.getP_code_capacity());
        setCode_citizenship(client.getP_code_citizenship());
        setCode_country(client.getCode_country());
//		setP_code_class_credit(client.getP_code_class_credit);
        setCode_gender(client.getP_code_gender());
        setCode_nation(client.getP_code_nation());
        setCode_tax_org(client.getP_code_tax_org());
        setEmail_address(client.getP_email_address());
        setFamily(client.getP_family());
        setFirst_name(client.getP_first_name());
        setInps(client.getP_inps());
        setNum_certif_capacity(client.getP_num_certif_capacity());
        setNumber_tax_registration(client.getP_number_tax_registration());
        setPass_place_district(client.getP_pass_place_district());
        setPass_place_region(client.getP_pass_place_region());
        setPassport_date_expiration(client.getP_passport_date_expiration());
        setPassport_date_registration(client.getP_passport_date_registration());
        setPassport_number(client.getP_passport_number());
        setPassport_place_registration(client.getP_passport_place_registration());
        setPassport_serial(client.getP_passport_serial());
        setPassport_type(client.getP_passport_type());
        setPatronymic(client.getP_patronymic());
//		setPension_sertif_serial(client.getP_pension_sertif_serial);
        setPhone_home(client.getP_phone_home());
        setPhone_mobile(client.getP_phone_mobile());
        setPost_address(client.getP_post_address());
//		setPost_address_flat(client.getP_post_address_flat());
//		setPost_address_house(client.getP_post_address_house());
//		setPost_address_quarter(client.getP_post_address_quarter());
//		setPost_address_street(client.getP_post_address_street());
        setType_document(client.getP_type_document());
        setFamily_local(client.getP_last_name_cyr());
        setFirst_name_local(client.getP_first_name_cyr());
        setPatronymic_local(client.getP_patronymic_cyr());
        setName(personName());
        setCheckedInAtaccama(false);
    }

    public ClientJ mapToClientJ() {
        ClientJ cl = new ClientJ();

        cl.setId_client(personMap.getClient_id());
        cl.setP_birth_place(birth_place);
        cl.setP_birthday(birthday);
        cl.setP_code_adr_distr(code_adr_distr);
        cl.setP_code_adr_region(code_adr_region);
        cl.setP_code_birth_distr(code_birth_distr);
        cl.setP_code_birth_region(code_birth_region);
        cl.setP_code_gender(code_gender);
        cl.setP_code_citizenship(code_citizenship);
        cl.setP_code_nation(code_nation);
        cl.setP_code_tax_org(code_tax_org);
        cl.setP_email_address(email_address);
        cl.setP_family(family);
        cl.setP_first_name(first_name);
        cl.setP_inps(inps);
        cl.setP_number_tax_registration(number_tax_registration);
        cl.setP_passport_date_expiration(passport_date_expiration);
        cl.setP_passport_date_registration(passport_date_registration);
        cl.setP_passport_number(passport_number);
        cl.setP_passport_place_registration(passport_place_registration);
        cl.setP_passport_serial(passport_serial);
        cl.setP_passport_type(passport_type);
        cl.setP_patronymic(patronymic);
        cl.setP_post_address(post_address);
        cl.setP_type_document(type_document);
        cl.setP_phone_home(phone_home);
        cl.setP_phone_mobile(phone_mobile);
        return cl;
    }


    //	public Person(String name, String idSap, String family,
//			String first_name, String patronymic) {
//		super();
//		this.name = name;
//		this.idSap = idSap;
//		this.family = family;
//		this.first_name = first_name;
//		this.patronymic = patronymic;
//	}
//
//	public Person(String id, String idSap, String name, Date birthday,
//			String code_gender, String family, String first_name,
//			String patronymic) {
//		super();
//		this.id = id;
//		this.idSap = idSap;
//		this.name = name;
//		this.birthday = birthday;
//		this.code_gender = code_gender;
//		this.family = family;
//		this.first_name = first_name;
//		this.patronymic = patronymic;
//	}
//
//	public Person(String idSap, String name, Date birthday, String family,
//			String first_name, String patronymic) {
//		super();
//		this.idSap = idSap;
//		this.name = name;
//		this.birthday = birthday;
//		this.family = family;
//		this.first_name = first_name;
//		this.patronymic = patronymic;
//	}
    public PersonMap getNewPersonMap() {
        personMap = new PersonMap();
        return personMap;
    }

    public String convertToIdSAP() {
        return String.format("A%s", id);
    }

    public String personName() {
        StringBuilder sb = new StringBuilder();
        if (this.family != null) {
            sb.append(this.family).append(" ");
        }
        if (this.first_name != null) {
            sb.append(this.first_name).append(" ");
        }
        if (this.patronymic != null) {
            sb.append(this.patronymic);
        }
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o1) {
        Person that = (Person) o1;
        /*if (p1 instanceof Person) {
            Person that = (Person) p1;
            if (this.getBranch() != null &&
                    this.getId() != null &&
                    that.getBranch() != null &&
                    that.getId() != null &&
                    (this.getBranch().equalsIgnoreCase(that.getBranch()) &&
                            this.getId().equalsIgnoreCase(that.getId())))
                return true;
            if ((this.type_document != null
                    && that.type_document != null
                    && this.type_document.equalsIgnoreCase(that.type_document))
                    &&
                    (this.passport_serial != null
                            && that.passport_serial != null
                            && this.passport_serial.equalsIgnoreCase(that.passport_serial)
                            &&
                            this.passport_number != null
                            && that.passport_number != null
                            &&
                            this.passport_number.equalsIgnoreCase(that.passport_number)))
                return true;
        }*/
        if (Objects.equal(this.type_document,that.type_document) &&
                Objects.equal(this.passport_serial,that.passport_serial) &&
                Objects.equal(this.passport_number,that.passport_number))
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        //if (this.branch != null && this.id != null)
            //return Objects.hashCode(this.branch,this.id);
        return Objects.hashCode(this.passport_type,this.passport_serial,this.passport_number);
    }

    public String concatenateFullName(){
        String family = "";
        String firstName = "";
        String patronymic = "";
        if (family_local != null)
            family = family_local;
        if (first_name_local != null)
            firstName = first_name_local;
        if (patronymic_local != null)
            patronymic = patronymic_local;

        return String.format("%s %s %s", family.trim(), firstName.trim(), patronymic.trim()).trim();
    }
}
