package com.is.client_mass_openingCheckResident;

public class CheckAccountResident {
	private String	branch;
	private String id_nibbd ;
	private String	id;
	private String   pinfl ;
	private String date_open_nibbd ;
	private String date_close_nibbd;  
	private String state  ;
	private String type_document;  
	private String passport_serial ;
	private String passport_number ;
	private String passport_place_registration ;
	private String passport_date_registration ;
	private String passport_date_expiration; 
	private String post_address;
	
	
	public CheckAccountResident() {};
	
	
	
	public CheckAccountResident(String branch, String id_nibbd, String id, String pinfl, String date_open_nibbd,
			String date_close_nibbd, String state, String type_document, String passport_serial, String passport_number,
			String passport_place_registration, String passport_date_registration, String passport_date_expiration,
			String post_address) {
		super();
		this.branch = branch;
		this.id_nibbd = id_nibbd;
		this.id = id;
		this.pinfl = pinfl;
		this.date_open_nibbd = date_open_nibbd;
		this.date_close_nibbd = date_close_nibbd;
		this.state = state;
		this.type_document = type_document;
		this.passport_serial = passport_serial;
		this.passport_number = passport_number;
		this.passport_place_registration = passport_place_registration;
		this.passport_date_registration = passport_date_registration;
		this.passport_date_expiration = passport_date_expiration;
		this.post_address = post_address;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getId_nibbd() {
		return id_nibbd;
	}
	public void setId_nibbd(String id_nibbd) {
		this.id_nibbd = id_nibbd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPinfl() {
		return pinfl;
	}
	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}
	public String getDate_open_nibbd() {
		return date_open_nibbd;
	}
	public void setDate_open_nibbd(String date_open_nibbd) {
		this.date_open_nibbd = date_open_nibbd;
	}
	public String getDate_close_nibbd() {
		return date_close_nibbd;
	}
	public void setDate_close_nibbd(String date_close_nibbd) {
		this.date_close_nibbd = date_close_nibbd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType_document() {
		return type_document;
	}
	public void setType_document(String type_document) {
		this.type_document = type_document;
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
	public String getPassport_place_registration() {
		return passport_place_registration;
	}
	public void setPassport_place_registration(String passport_place_registration) {
		this.passport_place_registration = passport_place_registration;
	}
	public String getPassport_date_registration() {
		return passport_date_registration;
	}
	public void setPassport_date_registration(String passport_date_registration) {
		this.passport_date_registration = passport_date_registration;
	}
	public String getPassport_date_expiration() {
		return passport_date_expiration;
	}
	public void setPassport_date_expiration(String passport_date_expiration) {
		this.passport_date_expiration = passport_date_expiration;
	}
	public String getPost_address() {
		return post_address;
	}
	public void setPost_address(String post_address) {
		this.post_address = post_address;
	}
	@Override
	public String toString() {
		return "CheckAccountResident [branch=" + branch + ", id_nibbd=" + id_nibbd + ", id=" + id + ", pinfl=" + pinfl
				+ ", date_open_nibbd=" + date_open_nibbd + ", date_close_nibbd=" + date_close_nibbd + ", state=" + state
				+ ", type_document=" + type_document + ", passport_serial=" + passport_serial + ", passport_number="
				+ passport_number + ", passport_place_registration=" + passport_place_registration
				+ ", passport_date_registration=" + passport_date_registration + ", passport_date_expiration="
				+ passport_date_expiration + ", post_address=" + post_address + "]";
	}
	
	
}

