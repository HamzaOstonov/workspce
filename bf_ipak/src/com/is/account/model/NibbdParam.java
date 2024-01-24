package com.is.account.model;

import java.util.Date;

public class NibbdParam {

	private String inn;
	private String pinfl;
	private String coa;
	private String currency;
	private String n_order;
	private String close_type;
	private String closed_doc_n;
	private Date closed_doc_d;
	private String account;
	private String client;
	
	
	 public NibbdParam() {
	    }
	 
	public String getInn() {
		return inn;
	}
	public void setInn(String inn) {
		this.inn = inn;
	}
	public String getPinfl() {
		return pinfl;
	}
	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}
	public String getCoa() {
		return coa;
	}
	public void setCoa(String coa) {
		this.coa = coa;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getN_order() {
		return n_order;
	}
	public void setN_order(String nOrder) {
		this.n_order = nOrder;
	}
	public String getClose_type() {
		return close_type;
	}
	public void setClose_type(String closeType) {
		this.close_type = closeType;
	}
	public String getClosed_doc_n() {
		return closed_doc_n;
	}
	public void setClosed_doc_n(String closed_doc_n) {
		this.closed_doc_n = closed_doc_n;
	}
	public Date getClosed_doc_d() {
		return closed_doc_d;
	}
	public void setClosed_doc_d(Date closed_doc_d) {
		this.closed_doc_d = closed_doc_d;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	
	
}
