package com.is.client_mass_opening;

public class ClientHumo {
	
	private	String	branch;
	private String	clientId;
	private	String	card_type;
	private String  user;
	private String	phone;


public ClientHumo () {}


public ClientHumo(String branch, String clientId, String card_type, String user, String phone) {
	super();
	this.branch = branch;
	this.clientId = clientId;
	this.card_type = card_type;
	this.user = user;
	this.phone = phone;
}


/**
 * @return the branch
 */
public String getBranch() {
	return branch;
}


/**
 * @param branch the branch to set
 */
public void setBranch(String branch) {
	this.branch = branch;
}


/**
 * @return the clientId
 */
public String getClientId() {
	return clientId;
}


/**
 * @param clientId the clientId to set
 */
public void setClientId(String clientId) {
	this.clientId = clientId;
}


/**
 * @return the card_type
 */
public String getCard_type() {
	return card_type;
}


/**
 * @param card_type the card_type to set
 */
public void setCard_type(String card_type) {
	this.card_type = card_type;
}


/**
 * @return the user
 */
public String getUser() {
	return user;
}


/**
 * @param user the user to set
 */
public void setUser(String user) {
	this.user = user;
}


/**
 * @return the phone
 */
public String getPhone() {
	return phone;
}


/**
 * @param phone the phone to set
 */
public void setPhone(String phone) {
	this.phone = phone;
}


@Override
public String toString() {
	return "ClientHumo [branch=" + branch + ", clientId=" + clientId +
			", card_type=" + card_type + ", user=" + user
			+ ", phone=" + phone + "]";
}



}