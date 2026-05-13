package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataUpdateSMS {

    @JsonProperty("client_number")
    private String clientNumber;

    @JsonProperty("social_number")
    private String socialNumber;

    @JsonProperty("rbs_number")
    private String rbsNumber;

    @JsonProperty("order_dprt")
    private String orderDprt;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("client_number")
	public String getClientNumber() {
		return clientNumber;
	}

    @JsonProperty("social_number")
	public String getSocialNumber() {
		return socialNumber;
	}

    @JsonProperty("rbs_number")
	public String getRbsNumber() {
		return rbsNumber;
	}

    @JsonProperty("order_dprt")
	public String getOrderDprt() {
		return orderDprt;
	}

    @JsonProperty("postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("client_number")
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	@JsonProperty("social_number")
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	@JsonProperty("rbs_number")
	public void setRbsNumber(String rbsNumber) {
		this.rbsNumber = rbsNumber;
	}

	@JsonProperty("order_dprt")
	public void setOrderDprt(String orderDprt) {
		this.orderDprt = orderDprt;
	}

	@JsonProperty("postal_code")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	    

}
