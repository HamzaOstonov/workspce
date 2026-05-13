package com.is.client_mass_openingCheckResident;

public class Client_mass_opening_resident {
	private String id;
    private String file_id;
    private String lastname;
    private String date_birth;
    private String code_organization;
    private String card_type;
    private String phone;
    private String pinfl;
    private String status;
    private String responce;
    private String acc_group;
    private int checked;
    private String passport_series;
	private String passport_number;
    
    public Client_mass_opening_resident() {}
    
    
    public Client_mass_opening_resident(String id, String file_id, String lastname, String date_birth,
			String code_organization, String card_type, String phone, String pinfl, String status, String responce,
			String acc_group,String passport_series, String passport_number) {
		super();
		this.id = id;
		this.file_id = file_id;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.passport_series = passport_series;
		this.passport_number = passport_number;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.pinfl = pinfl;
		this.status = status;
		this.responce = responce;
		this.acc_group = acc_group;
		
	}
    
	public Client_mass_opening_resident(String id, String file_id, String lastname, String date_birth,
			String code_organization, String card_type, String phone, String pinfl, String status, String responce,
			String acc_group, int checked,String passport_series, String passport_number) {
		super();
		this.id = id;
		this.file_id = file_id;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.pinfl = pinfl;
		this.status = status;
		this.responce = responce;
		this.acc_group = acc_group;
		this.checked = checked;
		this.passport_series = passport_series;
		this.passport_number = passport_number;
	}

	public Client_mass_opening_resident(String id, String file_id, String lastname, String date_birth,
			String code_organization, String card_type, String phone, String pinfl, String status, String responce,
			String acc_group) {
		super();
		this.id = id;
		this.file_id = file_id;
		this.lastname = lastname;
		this.date_birth = date_birth;
		this.code_organization = code_organization;
		this.card_type = card_type;
		this.phone = phone;
		this.pinfl = pinfl;
		this.status = status;
		this.responce = responce;
		this.acc_group = acc_group;
	}
	
	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
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
	public String getPinfl() {
		return pinfl;
	}
	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
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
	public String getAcc_group() {
		return acc_group;
	}
	public void setAcc_group(String acc_group) {
		this.acc_group = acc_group;
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
		return "Client_mass_opening_resident [id=" + id + ", file_id=" + file_id + ", lastname=" + lastname
				+ ", date_birth=" + date_birth + ", code_organization=" + code_organization + ", card_type=" + card_type
				+ ", phone=" + phone + ", pinfl=" + pinfl + ", status=" + status + ", responce=" + responce
				+ ", acc_group=" + acc_group + ", passport_series=" + passport_series + ", passport_number="
				+ passport_number + "]" ;
	}

}
