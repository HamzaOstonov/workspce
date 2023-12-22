package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
	@JsonProperty("document")
	private String documentSerAndNum;
	@JsonProperty("type")
	private String doctype;
	@JsonProperty("docgiveplace")
	private String docgiveplace;
	@JsonProperty("docgiveplaceid")
	private float docgiveplaceid;
	@JsonProperty("datebegin")
	private String datebegin;
	@JsonProperty("dateend")
	private String dateend;
	@JsonProperty("status")
	private int status;

	@JsonProperty("document")
	public String getDocumentSerAndNum() {
		return documentSerAndNum;
	}
	@JsonProperty("type")
	public String getDoctype() {
		return doctype;
	}
	@JsonProperty("docgiveplace")
	public String getDocgiveplace() {
		return docgiveplace;
	}
	@JsonProperty("docgiveplaceid")
	public float getDocgiveplaceid() {
		return docgiveplaceid;
	}
	@JsonProperty("datebegin")
	public String getDatebegin() {
		return datebegin;
	}
	@JsonProperty("dateend")
	public String getDateend() {
		return dateend;
	}
	@JsonProperty("status")
	public int getStatus() {
		return status;
	}
	@JsonProperty("document")
	public void setDocumentSerAndNum(String documentSerAndNum) {
		this.documentSerAndNum = documentSerAndNum;
	}
	@JsonProperty("type")
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	@JsonProperty("docgiveplace")
	public void setDocgiveplace(String docgiveplace) {
		this.docgiveplace = docgiveplace;
	}
	@JsonProperty("docgiveplaceid")
	public void setDocgiveplaceid(float docgiveplaceid) {
		this.docgiveplaceid = docgiveplaceid;
	}
	@JsonProperty("datebegin")
	public void setDatebegin(String datebegin) {
		this.datebegin = datebegin;
	}
	@JsonProperty("dateend")
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	@JsonProperty("status")
	public void setStatus(int status) {
		this.status = status;
	}

	
}
