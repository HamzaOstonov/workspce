package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"social_number",
"request_id"
})

public class ClientContractsRequest {

	@JsonProperty("social_number")
	private String socialNumber;
	@JsonProperty("request_id")
	private String requestId;

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
