package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DebitCreditAccRequest {

	@JsonProperty("request_id")
    private String requestId;

    @JsonProperty("msg_code")
    private String msgCode;

    @JsonProperty("rrn")
    private String rrn;

    @JsonProperty("description")
    private String description;

    @JsonProperty("requestor")
    private String requestor;

    @JsonProperty("source")
    private String source;

    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
    @JsonProperty("msg_code")
	public String getMsgCode() {
		return msgCode;
	}
    @JsonProperty("rrn")
	public String getRrn() {
		return rrn;
	}
    @JsonProperty("description")
	public String getDescription() {
		return description;
	}
    @JsonProperty("requestor")
	public String getRequestor() {
		return requestor;
	}
    @JsonProperty("source")
	public String getSource() {
		return source;
	}
    @JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}
    @JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	 @JsonProperty("msg_code")
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	 @JsonProperty("rrn")
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	 @JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}
	  @JsonProperty("requestor")
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	   @JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}
	   @JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	   @JsonProperty("amount")
	public void setAmount(String amount) {
		this.amount = amount;
	}

        
	
}
