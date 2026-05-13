package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseAddress {
	@JsonProperty("EMail")
	 private String EMail;
	@JsonProperty("City")
	 private String City;
	@JsonProperty("PostalCode")
	 private String PostalCode;
	@JsonProperty("AddressLine1")
	 private String AddressLine1;
	@JsonProperty("AddressLine2")
	 private String AddressLine2;
	@JsonProperty("ActivityPeriod")
	private ActivityPeriod ActivityPeriodObject;

	 // Getter Methods 
	@JsonProperty("EMail")
	 public String getEMail() {
	  return EMail;
	 }
	@JsonProperty("City")
	 public String getCity() {
	  return City;
	 }
	@JsonProperty("PostalCode")
	 public String getPostalCode() {
	  return PostalCode;
	 }
	@JsonProperty("AddressLine1")
	 public String getAddressLine1() {
	  return AddressLine1;
	 }
	@JsonProperty("AddressLine2")
	 public String getAddressLine2() {
	  return AddressLine2;
	 }
	 @JsonProperty("ActivityPeriod")
		public ActivityPeriod getActivityPeriodObject() {
			return ActivityPeriodObject;
		}

	 // Setter Methods 
	 @JsonProperty("EMail")
	 public void setEMail(String EMail) {
	  this.EMail = EMail;
	 }
	 @JsonProperty("City")
	 public void setCity(String City) {
	  this.City = City;
	 }
	 @JsonProperty("PostalCode")
	 public void setPostalCode(String PostalCode) {
	  this.PostalCode = PostalCode;
	 }
	 @JsonProperty("AddressLine1")
	 public void setAddressLine1(String AddressLine1) {
	  this.AddressLine1 = AddressLine1;
	 }
	 @JsonProperty("AddressLine2")
	 public void setAddressLine2(String AddressLine2) {
	  this.AddressLine2 = AddressLine2;
	 }
	 @JsonProperty("ActivityPeriod")
	public void setActivityPeriodObject(ActivityPeriod activityPeriodObject) {
		ActivityPeriodObject = activityPeriodObject;
	}
	}