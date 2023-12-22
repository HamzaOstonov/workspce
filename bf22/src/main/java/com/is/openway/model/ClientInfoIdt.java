package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ClientInfo")
public class ClientInfoIdt {
	@JsonProperty("ClientNumber")
	 private String ClientNumber;
	@JsonProperty("SocialNumber")
	 private String SocialNumber;


	 // Getter Methods 
	@JsonProperty("ClientNumber")
	 public String getClientNumber() {
	  return ClientNumber;
	 }
	@JsonProperty("SocialNumber")
	 public String getSocialNumber() {
	  return SocialNumber;
	 }

	 // Setter Methods 
	 @JsonProperty("ClientNumber")
	 public void setClientNumber(String ClientNumber) {
	  this.ClientNumber = ClientNumber;
	 }
	 @JsonProperty("SocialNumber")
	 public void setSocialNumber(String SocialNumber) {
	  this.SocialNumber = SocialNumber;
	 }
	}