package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccCardRequest {

	@JsonProperty("request_id")
    private String requestId;

    @JsonProperty("order_dprt")
    private String orderDprt;

    @JsonProperty("client")
    private Client client;

    @JsonProperty("account")
    private Account account;

    @JsonProperty("account_address")
    private AccountAddress accountAddress;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("card_address")
    private AccountAddress cardAddress;

    @JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}
    @JsonProperty("order_dprt")
	public String getOrderDprt() {
		return orderDprt;
	}
    @JsonProperty("client")
	public Client getClient() {
		return client;
	}
    @JsonProperty("account")
	public Account getAccount() {
		return account;
	}
    @JsonProperty("account_address")
	public AccountAddress getAccountAddress() {
		return accountAddress;
	}
    @JsonProperty("card")
	public Card getCard() {
		return card;
	}
    @JsonProperty("card_address")
	public AccountAddress getCardAddress() {
		return cardAddress;
	}

	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@JsonProperty("order_dprt")
	public void setOrderDprt(String orderDprt) {
		this.orderDprt = orderDprt;
	}
	@JsonProperty("client")
	public void setClient(Client client) {
		this.client = client;
	}
	@JsonProperty("account")
	public void setAccount(Account account) {
		this.account = account;
	}
	@JsonProperty("account_address")
	public void setAccountAddress(AccountAddress accountAddress) {
		this.accountAddress = accountAddress;
	}
	@JsonProperty("card")
	public void setCard(Card card) {
		this.card = card;
	}
	@JsonProperty("card_address")
	public void setCardAddress(AccountAddress cardAddress) {
		this.cardAddress = cardAddress;
	}
	
    
	
}
