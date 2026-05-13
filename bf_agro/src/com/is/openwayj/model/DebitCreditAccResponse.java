package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DebitCreditAccResponse {
	
	@JsonProperty("message")
	private String message;

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("rrn")
	private String rrn;

	@JsonProperty("drn")
	private String drn;
		
	@JsonProperty("auth_code")
	private String authCode;
		

    @JsonProperty("message")
	public String getMessage() {
		return message;
	}

    @JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("success")
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@JsonProperty("rrn")
	public String getRrn() {
		return rrn;
	}
	@JsonProperty("drn")
	public String getDrn() {
		return drn;
	}
	@JsonProperty("auth_code")
	public String getAuthCode() {
		return authCode;
	}
	@JsonProperty("rrn")
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	@JsonProperty("drn")
	public void setDrn(String drn) {
		this.drn = drn;
	}
	@JsonProperty("auth_code")
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
		
}
