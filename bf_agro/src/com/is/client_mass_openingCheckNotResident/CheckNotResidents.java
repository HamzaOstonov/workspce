package com.is.client_mass_openingCheckNotResident;

public class CheckNotResidents {
	private  Boolean check;
	private String pinfl;
	private String	lastname;
	private	String	date_birth;
	private String	code_organization ;
	private	String	card_type;
	private String	phone;
	private String acc_group;
	
	
	public CheckNotResidents() {
		super();
	}
	public CheckNotResidents(Boolean check, String pinfl, String lastname, String date_birth, String code_organization,
			String card_type, String phone, String acc_group) {
		super();
		this.check = check;
		this.pinfl = pinfl;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.acc_group = acc_group;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public String getPinfl() {
		return pinfl;
	}
	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAcc_group() {
		return acc_group;
	}
	public void setAcc_group(String acc_group) {
		this.acc_group = acc_group;
	} 
	
	
	
	

}
