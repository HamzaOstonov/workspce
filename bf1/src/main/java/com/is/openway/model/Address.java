package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	@JsonProperty("AddressType")
	 private String AddressType;
	@JsonProperty("PostalCode")
	 private String PostalCode;
	@JsonProperty("AddressLine1")
	 private String AddressLine1;


	 // Getter Methods 
	@JsonProperty("AddressType")
	 public String getAddressType() {
	  return AddressType;
	 }
	@JsonProperty("PostalCode")
	 public String getPostalCode() {
	  return PostalCode;
	 }
	@JsonProperty("AddressLine1")
	 public String getAddressLine1() {
	  return AddressLine1;
	 }

	 // Setter Methods 
	 @JsonProperty("AddressType")
	 public void setAddressType(String AddressType) {
	  this.AddressType = AddressType;
	 }
	 @JsonProperty("PostalCode")
	 public void setPostalCode(String PostalCode) {
	  this.PostalCode = PostalCode;
	 }
	 @JsonProperty("AddressLine1")
	 public void setAddressLine1(String AddressLine1) {
	  this.AddressLine1 = AddressLine1;
	 }

}
