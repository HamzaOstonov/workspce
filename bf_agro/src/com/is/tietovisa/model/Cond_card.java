package com.is.tietovisa.model;

public class Cond_card {
	private String cond_set;
	private String ccy;
	private String name; 
	private String bin_code;
	private String cardname;
	
	 public Cond_card() {
			super();
		}

	public String getCond_set() {
		return cond_set;
	}

	public String getCcy() {
		return ccy;
	}

	public String getName() {
		return name;
	}

	public String getBin_code() {
		return bin_code;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCond_set(String cond_set) {
		this.cond_set = cond_set;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBin_code(String bin_code) {
		this.bin_code = bin_code;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	 
}
