package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccCardRequest {
	
	
    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("order_dprt")
    private String orderDprt;

    @JsonProperty("object_for")
    private ObjectForAccCard objectFor;

    @JsonProperty("account_address")
    private AccountAddress accountAddress;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("card_address")
    private AccountAddress cardAddress;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getOrderDprt() {
		return orderDprt;
	}

	public void setOrderDprt(String orderDprt) {
		this.orderDprt = orderDprt;
	}

	public ObjectForAccCard getObjectFor() {
		return objectFor;
	}

	public void setObjectFor(ObjectForAccCard objectFor) {
		this.objectFor = objectFor;
	}

	public AccountAddress getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(AccountAddress accountAddress) {
		this.accountAddress = accountAddress;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public AccountAddress getCardAddress() {
		return cardAddress;
	}

	public void setCardAddress(AccountAddress cardAddress) {
		this.cardAddress = cardAddress;
	}

}
