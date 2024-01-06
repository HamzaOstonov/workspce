package com.is.clients.models;

public class LockedMain {

	 private String bank;
	 private String branch;
	 private String account;
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

	public Lock_info[] getLock_info() {
		return lock_info;
	}

	public void setLock_info(Lock_info[] lock_info) {
		this.lock_info = lock_info;
	}
}
