package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"client_number",
"social_number",
"data"
})
public class ObjectForRegCard {
	@JsonProperty("rbs_number")
	private String rbsNumber;
	@JsonProperty("client_number")
	private String clientNumber;
	@JsonProperty("social_number")
	private String socialNumber;
	@JsonProperty("data")
	private DataRegCard data;

	@JsonProperty("client_number")
	public String getClientNumber() {
	return clientNumber;
	}

	@JsonProperty("client_number")
	public void setClientNumber(String clientNumber) {
	this.clientNumber = clientNumber;
	}

	@JsonProperty("social_number")
	public String getSocialNumber() {
	return socialNumber;
	}

	@JsonProperty("social_number")
	public void setSocialNumber(String socialNumber) {
	this.socialNumber = socialNumber;
	}

	@JsonProperty("data")
	public DataRegCard getData() {
	return data;
	}

	@JsonProperty("data")
	public void setData(DataRegCard data) {
	this.data = data;
	}

	public void setRbsNumber(String rbsNumber) {
		this.rbsNumber = rbsNumber;
	}

	public String getRbsNumber() {
		return rbsNumber;
	}

}
