package com.is.bpri.models;

public class Ld_sv_gate {

	private String branch;
	private Long id;
	private String card_number;
	private String expiry_date;
	private Integer sign_client;
	private Integer sign_card;
	
	public Ld_sv_gate() {
	
	}

	public Ld_sv_gate(String branch, Long id, String card_number,
			String expiry_date, Integer sign_client, Integer sign_card) {
		this.branch = branch;
		this.id = id;
		this.card_number = card_number;
		this.expiry_date = expiry_date;
		this.sign_client = sign_client;
		this.sign_card = sign_card;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Integer getSign_client() {
		return sign_client;
	}

	public void setSign_client(Integer sign_client) {
		this.sign_client = sign_client;
	}

	public Integer getSign_card() {
		return sign_card;
	}

	public void setSign_card(Integer sign_card) {
		this.sign_card = sign_card;
	}
	
}
