package com.is.qr_online.payee;

public class PayeeFilter {

	private String inn;
	private String account;
	
	public PayeeFilter(String inn, String account) {
		super();
		this.inn = inn;
		this.account = account;
	}
	
	public PayeeFilter(){
		
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
