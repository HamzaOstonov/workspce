package com.is.clients.ebp.models.legalentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectLegalEntity {

	private String branch;
	private String tin;
	private String citizenship_code;
	private String name;
	private String name_short;
	private String address_region;
	private String address_subregion;
	private String address_code;
	private String property_form;
	private String legal_form;
	private String juridical_form;
	private String Oked;
	private String header_tin;
	private String registration_issuer;
	private String registration_doc_number;
	private String registration_date;
	private String issuer_region;
	private String issuer_subregion;
	private String registration_expire_date;
	private String subject_type;
	private String residency_code;

	public SubjectLegalEntity() {
	}

	public SubjectLegalEntity(String branch, String tin,
							  String citizenship_code, String name, String name_short,
							  String address_region, String address_subregion,
							  String address_code, String property_form, String legal_form,
							  String juridical_form, String oked, String header_tin,
							  String registration_issuer, String registration_doc_number,
							  String registration_date, String issuer_region,
							  String issuer_subregion, String registration_expire_date,
							  String subject_type, String residency_code) {
		super();
		this.branch = branch;
		this.tin = tin;
		this.citizenship_code = citizenship_code;
		this.name = name;
		this.name_short = name_short;
		this.address_region = address_region;
		this.address_subregion = address_subregion;
		this.address_code = address_code;
		this.property_form = property_form;
		this.legal_form = legal_form;
		this.juridical_form = juridical_form;
		Oked = oked;
		this.header_tin = header_tin;
		this.registration_issuer = registration_issuer;
		this.registration_doc_number = registration_doc_number;
		this.registration_date = registration_date;
		this.issuer_region = issuer_region;
		this.issuer_subregion = issuer_subregion;
		this.registration_expire_date = registration_expire_date;
		this.subject_type = subject_type;
		this.residency_code = residency_code;
	}
	
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getCitizenship_code() {
		return citizenship_code;
	}
	public void setCitizenship_code(String citizenship_code) {
		this.citizenship_code = citizenship_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}
	public String getAddress_region() {
		return address_region;
	}
	public void setAddress_region(String address_region) {
		this.address_region = address_region;
	}
	public String getAddress_subregion() {
		return address_subregion;
	}
	public void setAddress_subregion(String address_subregion) {
		this.address_subregion = address_subregion;
	}
	public String getAddress_code() {
		return address_code;
	}
	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}
	public String getProperty_form() {
		return property_form;
	}
	public void setProperty_form(String property_form) {
		this.property_form = property_form;
	}
	public String getLegal_form() {
		return legal_form;
	}
	public void setLegal_form(String legal_form) {
		this.legal_form = legal_form;
	}
	public String getJuridical_form() {
		return juridical_form;
	}
	public void setJuridical_form(String juridical_form) {
		this.juridical_form = juridical_form;
	}
	public String getOked() {
		return Oked;
	}
	public void setOked(String oked) {
		Oked = oked;
	}
	public String getHeader_tin() {
		return header_tin;
	}
	public void setHeader_tin(String header_tin) {
		this.header_tin = header_tin;
	}
	public String getRegistration_issuer() {
		return registration_issuer;
	}
	public void setRegistration_issuer(String registration_issuer) {
		this.registration_issuer = registration_issuer;
	}
	public String getRegistration_doc_number() {
		return registration_doc_number;
	}
	public void setRegistration_doc_number(String registration_doc_number) {
		this.registration_doc_number = registration_doc_number;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getIssuer_region() {
		return issuer_region;
	}
	public void setIssuer_region(String issuer_region) {
		this.issuer_region = issuer_region;
	}
	public String getIssuer_subregion() {
		return issuer_subregion;
	}
	public void setIssuer_subregion(String issuer_subregion) {
		this.issuer_subregion = issuer_subregion;
	}
	public String getRegistration_expire_date() {
		return registration_expire_date;
	}
	public void setRegistration_expire_date(String registration_expire_date) {
		this.registration_expire_date = registration_expire_date;
	}
	public String getSubject_type() {
		return subject_type;
	}
	public void setSubject_type(String subject_type) {
		this.subject_type = subject_type;
	}

    public String getResidency_code() {
        return residency_code;
    }

    public void setResidency_code(String residency_code) {
        this.residency_code = residency_code;
    }
}
