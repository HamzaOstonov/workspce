package com.is.clients.models;

public class LockedAccountsResp {

	 private String client;
	 private LockedMain main;
	 private LockedAccount[] accounts;
	 
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public LockedMain getMain() {
		return main;
	}
	public void setMain(LockedMain main) {
		this.main = main;
	}
	public LockedAccount[] getAccounts() {
		return accounts;
	}
	public void setAccounts(LockedAccount[] accounts) {
		this.accounts = accounts;
	}
	 
	 
}
