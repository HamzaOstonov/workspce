package com.is.clients.models;

public class Response {
	 private String client;
	 private String client_type;
	 private String client_bank;
	 private String client_branch;
	 private String account_bank;
	 private String account_branch;
	 private String account_office;
	 private String account_state;
	 private String client_state;
	 private String name;
	 private String opened;
	 private String closed;

	 // Getter Methods 

	 
	 public String getClient() {
	  return client;
	 }

	 public String getClient_bank() {
		return client_bank;
	}

	public void setClient_bank(String client_bank) {
		this.client_bank = client_bank;
	}

	public String getClient_branch() {
		return client_branch;
	}

	public void setClient_branch(String client_branch) {
		this.client_branch = client_branch;
	}

	public String getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	public String getAccount_branch() {
		return account_branch;
	}

	public void setAccount_branch(String account_branch) {
		this.account_branch = account_branch;
	}

	public String getAccount_office() {
		return account_office;
	}

	public void setAccount_office(String account_office) {
		this.account_office = account_office;
	}

	public String getClient_type() {
	  return client_type;
	 }

	 public String getClient_state() {
	  return client_state;
	 }

	 public String getAccount_state() {
	  return account_state;
	 }

	 public String getName() {
	  return name;
	 }

	 public String getOpened() {
	  return opened;
	 }

	 public String getClosed() {
	  return closed;
	 }

	 // Setter Methods 

	 public void setClient(String client) {
	  this.client = client;
	 }

	 public void setClient_type(String client_type) {
	  this.client_type = client_type;
	 }

	 public void setClient_state(String client_state) {
	  this.client_state = client_state;
	 }

	 public void setAccount_state(String account_state) {
	  this.account_state = account_state;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setOpened(String opened) {
	  this.opened = opened;
	 }

	 public void setClosed(String closed) {
	  this.closed = closed;
	 }

}
