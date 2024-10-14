package com.is.client_mass_openingHistory;

public class СlientResident {



		private String pinfl;
		private String	lastname;
		private	String	date_birth;
		private String	code_organization ;
		private	String	card_type;
		private String	phone;
		private String	status;
		private String responce;
		private String passport_series;
		private String passport_number;

	public СlientResident () {}


	
	



	public СlientResident(String pinfl, String lastname, String date_birth, String code_organization, String card_type,
			String phone, String status, String responce) {
		super();
		this.pinfl = pinfl;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.status = status;
		this.responce = responce;
	}
	public СlientResident(String pinfl, String lastname, String date_birth, String code_organization, String card_type,
			String phone, String status, String responce, String passport_series, String passport_number) {
		super();
		this.pinfl = pinfl;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.status = status;
		this.responce = responce;
		this.passport_series = passport_series;
		this.passport_number = passport_number;
	}
	
	public String getPassport_series() {
		return passport_series;
	}
	
	public void setPassport_series(String passport_series) {
		this.passport_series = passport_series;
	}
	
	public String getPassport_number() {
		return passport_number;
	}
	
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getPinfl() {
		return pinfl;
	}

	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}

	public String getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(String date_birth) {
		this.date_birth = date_birth;
	}

	public String getCode_organization() {
		return code_organization;
	}

	public void setCode_organization(String code_organization) {
		this.code_organization = code_organization;
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		return "ClientResident [pinfl=" + pinfl + ", lastname=" + lastname + ", date_birth=" + date_birth
				+ ", code_organization=" + code_organization + ", card_type=" + card_type + ", phone=" + phone + "]";
	}








	
}
