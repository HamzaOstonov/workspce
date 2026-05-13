package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Phone {
	@JsonProperty("phone_type")
	private String PhoneType;
	@JsonProperty("phone_number")
	private String PhoneNumber;
	
	@JsonProperty("phone_type")
	public String getPhoneType() {
		return PhoneType;
	}
	@JsonProperty("phone_number")
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	@JsonProperty("phone_type")
	public void setPhoneType(String phoneType) {
		PhoneType = phoneType;
	}
	@JsonProperty("phone_number")
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	

}
