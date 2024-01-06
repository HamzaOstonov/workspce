package com.is.clients.models;


public class AccountsResp {
	private String client;
	private Main MainObject;
	private Account[] accounts;


	 // Getter Methods 

	 public String getClient() {
	  return client;
	 }

	 public Main getMain() {
	  return MainObject;
	 }

	 // Setter Methods 

	 public void setClient(String client) {
	  this.client = client;
	 }

	 public void setMain(Main mainObject) {
	  this.MainObject = mainObject;
	 }

	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}
}
