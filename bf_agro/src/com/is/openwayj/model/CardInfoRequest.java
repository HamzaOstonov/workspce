package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardInfoRequest {

	@JsonProperty("rbs_number")
	private String rbsNumber;
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

	@JsonProperty("request_id")
	public String getRequestId() {
	return requestId;
	}

	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
	this.requestId = requestId;
	}
    
}
