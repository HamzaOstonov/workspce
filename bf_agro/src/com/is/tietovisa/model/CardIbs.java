package com.is.tietovisa.model;

import java.util.Date;

public class CardIbs {

	private String client_id ;      
	private String client_b   ;     
	private String branch      ;    
	private String card         ;   
	private String status1      ;   
	private String status2      ;   
	private Date card_regist_date;
	private Date expiry1         ;
	private Date expirity2       ;
	private String renew	        ;  
	private Date renew_date      ;
	private String card_name       ;
	private String stop_cause      ;
	private String real_card       ;
	private String tranz_acct      ;
	private String card_acct       ;
	private String pinfl           ;
	private String cond_set        ;
	
	
	public CardIbs() {
		super();
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_b() {
		return client_b;
	}

	public void setClient_b(String client_b) {
		this.client_b = client_b;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public Date getCard_regist_date() {
		return card_regist_date;
	}

	public void setCard_regist_date(Date card_regist_date) {
		this.card_regist_date = card_regist_date;
	}

	public Date getExpiry1() {
		return expiry1;
	}

	public void setExpiry1(Date expiry1) {
		this.expiry1 = expiry1;
	}

	public Date getExpirity2() {
		return expirity2;
	}

	public void setExpirity2(Date expirity2) {
		this.expirity2 = expirity2;
	}

	public String getRenew() {
		return renew;
	}

	public void setRenew(String renew) {
		this.renew = renew;
	}

	public Date getRenew_date() {
		return renew_date;
	}

	public void setRenew_date(Date renew_date) {
		this.renew_date = renew_date;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getStop_cause() {
		return stop_cause;
	}

	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}

	public String getReal_card() {
		return real_card;
	}

	public void setReal_card(String real_card) {
		this.real_card = real_card;
	}

	public String getTranz_acct() {
		return tranz_acct;
	}

	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}

	public String getPinfl() {
		return pinfl;
	}

	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}

	public String getCond_set() {
		return cond_set;
	}

	public void setCond_set(String cond_set) {
		this.cond_set = cond_set;
	}

	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}

	public String getCard_acct() {
		return card_acct;
	}


}
