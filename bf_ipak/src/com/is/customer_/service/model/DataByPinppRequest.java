package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataByPinppRequest {
	@JsonProperty("queryID")
	 private String queryID;
	 private String pinpp;
	 private String document;
	 private float langId;
	 private String orgtin;
	 private String fio;
	 private String pin;
	 private String is_consent;


	 // Getter Methods 

	 public DataByPinppRequest() {
		//super();
         this.queryID= "string";
         this.langId = 1;
         this.orgtin= "string";
         this.fio="string";
         this.pin="string";
         this.is_consent= "N";
	}
	 @JsonProperty("queryID")
	public String getQueryID() {
	  return queryID;
	 }

	 public String getPinpp() {
	  return pinpp;
	 }

	 public String getDocument() {
	  return document;
	 }

	 public float getLangId() {
	  return langId;
	 }

	 public String getOrgtin() {
	  return orgtin;
	 }

	 public String getFio() {
	  return fio;
	 }

	 public String getPin() {
	  return pin;
	 }

	 public String getIs_consent() {
	  return is_consent;
	 }

	 // Setter Methods 
	 @JsonProperty("queryID")
	 public void setQueryID(String queryID) {
	  this.queryID = queryID;
	 }

	 public void setPinpp(String pinpp) {
	  this.pinpp = pinpp;
	 }

	 public void setDocument(String document) {
	  this.document = document;
	 }

	 public void setLangId(float langId) {
	  this.langId = langId;
	 }

	 public void setOrgtin(String orgtin) {
	  this.orgtin = orgtin;
	 }

	 public void setFio(String fio) {
	  this.fio = fio;
	 }

	 public void setPin(String pin) {
	  this.pin = pin;
	 }

	 public void setIs_consent(String is_consent) {
	  this.is_consent = is_consent;
	 }
}
