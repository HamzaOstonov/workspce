package com.is.client_mass_openingHistory;

public class ClientNotResident {
	
	private String	pinfl; 
	private String 	firstname ;
	private String	lastname ;
	private String	patronymic ;
	private String	nationality ;
	private String	citizenship;
	private String	passport_serial; 
	private String	passport_number ;
	private String	date_birth;
	private String 	code_gender;
	private String 	birth_place ;
	private	String 	issued_by;
	private String 	date_issue;
	private String	validity_expire;
	private String	organization_code ;
	private String	type_card;
	private String	phone ;
	private String 	region ;
	private String	district;
	private String	address;
	private String status;
	private String responce;
	
	
	public ClientNotResident() {}
	
	public ClientNotResident(String pinfl, String firstname, String lastname, String patronymic, String nationality,
			String citizenship, String passport_serial, String passport_number, String date_birth, String code_gender,
			String birth_place, String issued_by, String date_issue, String validity_expire, String organization_code,
			String type_card, String phone, String region, String district, String address, String status,
			String responce) {
		super();
		this.pinfl = pinfl;
		this.firstname = firstname;
		this.lastname = lastname;
		this.patronymic = patronymic;
		this.nationality = nationality;
		this.citizenship = citizenship;
		this.passport_serial = passport_serial;
		this.passport_number = passport_number;
		this.date_birth = date_birth;
		this.code_gender = code_gender;
		this.birth_place = birth_place;
		this.issued_by = issued_by;
		this.date_issue = date_issue;
		this.validity_expire = validity_expire;
		this.organization_code = organization_code;
		this.type_card = type_card;
		this.phone = phone;
		this.region = region;
		this.district = district;
		this.address = address;
		this.status = status;
		this.responce = responce;
	}
	
	public String getPinfl() {
		return pinfl;
	}
	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getPassport_serial() {
		return passport_serial;
	}
	public void setPassport_serial(String passport_serial) {
		this.passport_serial = passport_serial;
	}
	public String getPassport_number() {
		return passport_number;
	}
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}
	public String getDate_birth() {
		return date_birth;
	}
	public void setDate_birth(String date_birth) {
		this.date_birth = date_birth;
	}
	public String getCode_gender() {
		return code_gender;
	}
	public void setCode_gender(String code_gender) {
		this.code_gender = code_gender;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	public String getIssued_by() {
		return issued_by;
	}
	public void setIssued_by(String issued_by) {
		this.issued_by = issued_by;
	}
	public String getDate_issue() {
		return date_issue;
	}
	public void setDate_issue(String date_issue) {
		this.date_issue = date_issue;
	}
	public String getValidity_expire() {
		return validity_expire;
	}
	public void setValidity_expire(String validity_expire) {
		this.validity_expire = validity_expire;
	}
	public String getOrganization_code() {
		return organization_code;
	}
	public void setOrganization_code(String organization_code) {
		this.organization_code = organization_code;
	}
	public String getType_card() {
		return type_card;
	}
	public void setType_card(String type_card) {
		this.type_card = type_card;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponce() {
		return responce;
	}
	public void setResponce(String responce) {
		this.responce = responce;
	}

	@Override
	public String toString() {
		return "ClientNotResident [pinfl=" + pinfl + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", patronymic=" + patronymic + ", nationality=" + nationality + ", citizenship=" + citizenship
				+ ", passport_serial=" + passport_serial + ", passport_number=" + passport_number + ", date_birth="
				+ date_birth + ", code_gender=" + code_gender + ", birth_place=" + birth_place + ", issued_by="
				+ issued_by + ", date_issue=" + date_issue + ", validity_expire=" + validity_expire
				+ ", organization_code=" + organization_code + ", type_card=" + type_card + ", phone=" + phone
				+ ", region=" + region + ", district=" + district + ", address=" + address + ", status=" + status
				+ ", responce=" + responce + "]";
	}
}