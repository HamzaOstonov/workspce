package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegCardRequest {
	
    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("order_dprt")
    private String orderDprt;

    @JsonProperty("object_for")
    private ObjectForRegCard objectFor;

    @JsonProperty("card_address")
    private AccountAddress cardAddress;

    @JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
    @JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
    @JsonProperty("order_dprt")
	public String getOrderDprt() {
		return orderDprt;
	}
    @JsonProperty("order_dprt")
	public void setOrderDprt(String orderDprt) {
		this.orderDprt = orderDprt;
	}
    @JsonProperty("object_for")
	public ObjectForRegCard getObjectFor() {
		return objectFor;
	}
    @JsonProperty("object_for")
	public void setObjectFor(ObjectForRegCard objectFor) {
		this.objectFor = objectFor;
	}
    @JsonProperty("card_address")
	public AccountAddress getCardAddress() {
		return cardAddress;
	}
    @JsonProperty("card_address")
	public void setCardAddress(AccountAddress cardAddress) {
		this.cardAddress = cardAddress;
	}
    
}
