package com.is.uzcard.model;

public class CardMaskInfo {
	private String acc_bal;
	private String acc_currency;
	private String acc_num;
	private String client_id;
	private String client_name;
	private String contract_id;

	public CardMaskInfo(String acc_bal, String acc_currency, String acc_num) {
		this.acc_bal = acc_bal;
		this.acc_currency = acc_currency;
		this.acc_num = acc_num;
	}

	public CardMaskInfo(String acc_bal, String acc_currency, String acc_num,
			String client_id, String client_name, String contract_id) {
		super();
		this.acc_bal = acc_bal;
		this.acc_currency = acc_currency;
		this.acc_num = acc_num;
		this.client_id = client_id;
		this.client_name = client_name;
		this.contract_id = contract_id;
	}

	public String getAcc_bal() {
		return acc_bal;
	}

	public String getAcc_currency() {
		return acc_currency;
	}

	public String getAcc_num() {
		return acc_num;
	}

	public String getClient_id() {
		return client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setAcc_bal(String acc_bal) {
		this.acc_bal = acc_bal;
	}

	public void setAcc_currency(String acc_currency) {
		this.acc_currency = acc_currency;
	}

	public void setAcc_num(String acc_num) {
		this.acc_num = acc_num;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
}
