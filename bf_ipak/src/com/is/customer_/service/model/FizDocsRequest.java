package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizDocsRequest {

	@JsonProperty("transaction_id")
	private Float transaction_id;
	@JsonProperty("is_consent")
	private String is_consent;
	@JsonProperty("sender_pinfl")
	private String sender_pinfl;
	@JsonProperty("langId")
	private int langId;
	@JsonProperty("birth_date")
	private String birth_date;
	@JsonProperty("document")
	private String document;
	@JsonProperty("pinpp")
	private String pinpp;
	@JsonProperty("is_photo")
	private String is_photo;
	@JsonProperty("Sender")
	private String Sender;
	
	
	
	public FizDocsRequest() {
		//super();
		this.is_consent = "Y";
		this.langId = 1;
		this.is_photo = "Y";
		this.Sender = "P";
	}
	
	
	public Float getTransaction_id() {
		return transaction_id;
	}
	public String getIs_consent() {
		return is_consent;
	}
	public String getSender_pinfl() {
		return sender_pinfl;
	}
	public int getLangId() {
		return langId;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public String getDocument() {
		return document;
	}
	public String getPinpp() {
		return pinpp;
	}
	public String getIs_photo() {
		return is_photo;
	}
	public String getSender() {
		return Sender;
	}
	public void setTransaction_id(Float transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setIs_consent(String is_consent) {
		this.is_consent = is_consent;
	}
	public void setSender_pinfl(String sender_pinfl) {
		this.sender_pinfl = sender_pinfl;
	}
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public void setPinpp(String pinpp) {
		this.pinpp = pinpp;
	}
	public void setIs_photo(String is_photo) {
		this.is_photo = is_photo;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	

}
