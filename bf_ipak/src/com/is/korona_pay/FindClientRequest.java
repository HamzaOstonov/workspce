package com.is.korona_pay;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindClientRequest {
	
	@JsonProperty("Action")
	public String action;
	@JsonProperty("Operation")
	public Integer operation;
	@JsonProperty("FullName")
	public String fullName;
	@JsonProperty("DocSeries")
	public String docSeries;
	@JsonProperty("DocNumber")
	public String docNumber;
	@JsonProperty("DocType")
	private String docType;
	@JsonProperty("AbsClientId")
	private String absClientId;
	
	public FindClientRequest() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	public String getDocSeries() {
		return docSeries;
	}

	public void setDocSeries(String docSeries) {
		this.docSeries = docSeries;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getAbsClientId() {
		return absClientId;
	}

	public void setAbsClientId(String absClientId) {
		this.absClientId = absClientId;
	}

	@Override
	public String toString() {
		return "FindClientRequest [action=" + action + ", operation=" + operation + ", fullName=" + fullName
				+ ", docNumber=" + docNumber + ", docType=" + docType + ", absClientId=" + absClientId + "]";
	}
	
	

}
