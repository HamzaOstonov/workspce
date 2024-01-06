package com.is.clients.models;

public class Account {
	 private String bank;
	 private String branch;
	 private String office;
	 private String account;
	 private String account_state;
	 private String opened;
	 private Lock_info[] lock_info;


	 // Getter Methods 

	 public String getBank() {
	  return bank;
	 }

	 public String getBranch() {
	  return branch;
	 }


	 public String getAccount() {
	  return account;
	 }

	 public String getAccount_state() {
	  return account_state;
	 }

	 public String getOpened() {
	  return opened;
	 }

	 // Setter Methods 

	 public void setBank(String bank) {
	  this.bank = bank;
	 }

	 public void setBranch(String branch) {
	  this.branch = branch;
	 }

	 public void setAccount(String account) {
	  this.account = account;
	 }

	 public void setAccount_state(String account_state) {
	  this.account_state = account_state;
	 }

	 public void setOpened(String opened) {
	  this.opened = opened;
	 }

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Lock_info[] getLock_info() {
		return lock_info;
	}

	public void setLock_info(Lock_info[] lock_info) {
		this.lock_info = lock_info;
	}

}
