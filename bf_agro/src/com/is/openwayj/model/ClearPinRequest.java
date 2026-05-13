package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClearPinRequest {
	
	@JsonProperty("rbs_number")
	private String rbsNumber;

	@JsonProperty("social_number")
	private String socialNumber;

	@JsonProperty("request_id")
	private String requestId;

	@JsonProperty("rbs_number")
	public String getRbsNumber() {
		return rbsNumber;
	}
	@JsonProperty("rbs_number")
	public void setRbsNumber(String rbsNumber) {
		this.rbsNumber = rbsNumber;
	}
	@JsonProperty("social_number")
	public String getSocialNumber() {
		return socialNumber;
	}
	@JsonProperty("social_number")
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}
	@JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	    
}
