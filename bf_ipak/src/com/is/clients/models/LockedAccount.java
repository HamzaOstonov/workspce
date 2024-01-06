package com.is.clients.models;

public class LockedAccount {

	private String bank;
	 private String branch;
	 private String account;
	 private String id;
	 private String type;
	 private String sort;
	 private String doc_n;
	 private String doc_d;
	 private String locked;


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

	 public String getId() {
	  return id;
	 }

	 public String getType() {
	  return type;
	 }

	 public String getSort() {
	  return sort;
	 }

	 public String getDoc_n() {
	  return doc_n;
	 }

	 public String getDoc_d() {
	  return doc_d;
	 }

	 public String getLocked() {
	  return locked;
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

	 public void setId(String id) {
	  this.id = id;
	 }

	 public void setType(String type) {
	  this.type = type;
	 }

	 public void setSort(String sort) {
	  this.sort = sort;
	 }

	 public void setDoc_n(String doc_n) {
	  this.doc_n = doc_n;
	 }

	 public void setDoc_d(String doc_d) {
	  this.doc_d = doc_d;
	 }

	 public void setLocked(String locked) {
	  this.locked = locked;
	 }
}
