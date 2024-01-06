package com.is.clients.models;

public class Lock_info {

	 private String id; 
	 private String type;
	 private String sort;
	 private String doc_n;
	 private String doc_d;
	 private String locked;
	 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDoc_n() {
		return doc_n;
	}
	public void setDoc_n(String doc_n) {
		this.doc_n = doc_n;
	}
	
	public String getDoc_d() {
		return doc_d;
	}
	public void setDoc_d(String doc_d) {
		this.doc_d = doc_d;
	}
	
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	 
	 
}
