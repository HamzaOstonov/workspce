package com.is.client_mass_openingCheckResident;

public class NewCardRequest {
	private String branch;
	private String clientId;
	private String cardType;
	private String user;
	private String phone =  null ;
	private String organizationId;

	public NewCardRequest() {
		super();
	}
	
	public NewCardRequest(String branch, String clientId, String cardType, String user, String phone,String organizationId) {
		super();
		this.branch = branch;
		this.clientId = clientId;
		this.cardType = cardType;
		this.user = user;
		this.phone = phone;
		this.organizationId = organizationId;
	}

	public NewCardRequest(String branch, String clientId, String cardType, String user, String phone) {
		super();
		this.branch = branch;
		this.clientId = clientId;
		this.cardType = cardType;
		this.user = user;
		this.phone = phone;
	}

	public NewCardRequest(String branch, String clientId, String cardType, String user) {
		super();
		this.branch = branch;
		this.clientId = clientId;
		this.cardType = cardType;
		this.user = user;
	}



	
	public String getOrganizationId() {
		return organizationId;
	}

	
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "NewCardRequest [branch=" + branch + ", clientId=" + clientId + ", cardType=" + cardType + ", user="
				+ user + ", phone=" + phone + ", organizationId=" + organizationId + "]";
	}



}




