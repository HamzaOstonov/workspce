package com.is.client_mass_opening;

public class ClientResident {
		private String id;
		private String pinfl;
		private String	lastname;
		private	String	date_birth;
		private String	code_organization ;
		private	String	card_type;
		private String	phone;
		private String acc_group;
		private String passport_series;
		private String passport_number;
;

		
	public ClientResident () {}
	
	public ClientResident(String pinfl, String lastname, String date_birth, String code_organization, String card_type,
			String phone, String acc_group, String passport_series, String passport_number) {
		super();
		this.pinfl = pinfl;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.acc_group = acc_group;
		this.passport_series = passport_series;
		this.passport_number = passport_number;
		
	}

	public ClientResident(String id, String pinfl, String lastname, String date_birth, String code_organization,
			String card_type, String phone, String acc_group) {
		super();
		this.id = id;
		this.pinfl = pinfl;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.acc_group = acc_group;
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

	

	@Override
	public String toString() {
		return "ClientResident [id=" + id + ", pinfl=" + pinfl + ", lastname=" + lastname + ", date_birth=" + date_birth
				+ ", code_organization=" + code_organization + ", card_type=" + card_type + ", phone=" + phone
				+ ", acc_group=" + acc_group + ", passport_series=" + passport_series + ", passport_number="
				+ passport_number + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcc_group() {
		return acc_group;
	}

	public void setAcc_group(String acc_group) {
		this.acc_group = acc_group;
	}
	
}
