package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;
public class AccCardBody {
    @JsonProperty("client")
    private ClientCategory client;

    @JsonProperty("account")
    private AccountRs account;

    @JsonProperty("card")
    private CardRs card;
    @JsonProperty("client")
	public ClientCategory getClient() {
		return client;
	}
    @JsonProperty("client")
	public void setClient(ClientCategory client) {
		this.client = client;
	}
    @JsonProperty("account")
	public AccountRs getAccount() {
		return account;
	}
    @JsonProperty("account")
	public void setAccount(AccountRs account) {
		this.account = account;
	}
    @JsonProperty("card")
	public CardRs getCard() {
		return card;
	}
    @JsonProperty("card")
	public void setCard(CardRs card) {
		this.card = card;
	}

}
