package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Phone {
	@JsonProperty("PhoneType")
	private String PhoneType;
	@JsonProperty("PhoneNumber")
	private String PhoneNumber;
	
	@JsonProperty("PhoneType")
	public String getPhoneType() {
		return PhoneType;
	}
	@JsonProperty("PhoneNumber")
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	@JsonProperty("PhoneType")
	public void setPhoneType(String phoneType) {
		PhoneType = phoneType;
	}
	@JsonProperty("PhoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	

}
