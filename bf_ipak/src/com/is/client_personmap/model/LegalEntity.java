package com.is.client_personmap.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class LegalEntity {
	
	@Getter @Setter private String idSap;
	@Getter @Setter private String id;
    @Getter @Setter private String branch;
    @Getter @Setter private String client_id;
    @Getter @Setter private String name;
    @Getter @Setter private String short_name;
    @Getter @Setter private Date date_registration;
    @Getter @Setter private String place_regist_name;
    @Getter @Setter private String number_registration_doc;
    @Getter @Setter private String code_tax_org;
    @Getter @Setter private String number_tax_registration;
    @Getter @Setter private String code_sector;
    @Getter @Setter private String code_organ_direct;
    @Getter @Setter private String code_head_organization;
    @Getter @Setter private String inn_head_organization;
//    @Getter @Setter private String code_class_credit;
    @Getter @Setter private String code_bank;
    @Getter @Setter private String account;
    @Getter @Setter private String post_address_region;
    @Getter @Setter private String post_address_distr;
    @Getter @Setter private String post_address;
    @Getter @Setter private String phone_mobile;
    @Getter @Setter private String phone;
    @Getter @Setter private String fax;
    @Getter @Setter private String email;
    @Getter @Setter private String sign_trade;
    @Getter @Setter private String opf;
    @Getter @Setter private String soato;
    @Getter @Setter private String okpo;
    @Getter @Setter private int state;
    @Getter private String reestrNumOld;
    @Getter @Setter private String directorname;
    @Getter @Setter private String code_country;
    @Getter @Setter private String code_resident;
    @Getter @Setter private String num_reestr;
    @Getter @Setter private String code_form;
    @Getter @Setter private String code_type;
    @Getter @Setter private String type_activity;
    @Getter @Setter private String union_id;
    
    @Getter @Setter private String small_business;
    @Getter @Setter private Date egrsp_date;
    @Getter @Setter private String egrsp_number;
    @Getter @Setter private String beneficiary;
    @Getter @Setter private String parent_id_client_j; /*eto pole iz client_j.id*/
    @Getter @Setter private String emp_id;
    
    public LegalEntity() {
		// TODO Auto-generated constructor stub
	}
	public LegalEntity(String id, String branch, String client_id, String name,
			String short_name, Date date_registration,
			String place_regist_name, String number_registration_doc,
			String code_tax_org, String number_tax_registration,
			String code_sector, String code_organ_direct,
			String code_head_organization, String inn_head_organization,
			String code_bank, String account,
			String post_address_region, String post_address_distr,
			String post_address, String phone_mobile, String phone, String fax,
			String email, String sign_trade, String opf, String soato,
			String okpo, int state, String code_country, String code_resident, String num_reestr,
                       String code_form,String code_type,String type_activity, String union_id,
                       String small_business,Date egrsp_date,String egrsp_number, String beneficiary) {
		super();
		this.id = id;
		this.branch = branch;
		this.client_id = client_id;
		this.name = name;
		this.short_name = short_name;
		this.date_registration = date_registration;
		this.place_regist_name = place_regist_name;
		this.number_registration_doc = number_registration_doc;
		this.code_tax_org = code_tax_org;
		this.number_tax_registration = number_tax_registration;
		this.code_sector = code_sector;
		this.code_organ_direct = code_organ_direct;
		this.code_head_organization = code_head_organization;
		this.inn_head_organization = inn_head_organization;
//		this.code_class_credit = code_class_credit;
		this.code_bank = code_bank;
		this.account = account;
		this.post_address_region = post_address_region;
		this.post_address_distr = post_address_distr;
		this.post_address = post_address;
		this.phone_mobile = phone_mobile;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.sign_trade = sign_trade;
		this.opf = opf;
		this.soato = soato;
		this.okpo = okpo;
		this.state = state;
		this.code_country = code_country;
		this.code_resident = code_resident;
		this.num_reestr = num_reestr;
		this.code_form = code_form;
		this.code_type = code_type;
		this.type_activity = type_activity;
		this.union_id = union_id;
		
	    this.small_business=small_business;
	    this.egrsp_date=egrsp_date;
	    this.egrsp_number=egrsp_number;
	    this.beneficiary=beneficiary;
	    		
	}
	
	public void setReestrNumOld(String reestrNumOld) {
		this.reestrNumOld = reestrNumOld;
		this.num_reestr = reestrNumOld;
	}
}
