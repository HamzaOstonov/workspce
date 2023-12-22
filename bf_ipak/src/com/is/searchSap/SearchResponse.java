package com.is.searchSap;

import java.util.Date;
import java.util.Formatter;

public class SearchResponse {
	private int count;
	private String branch;
	private String nciId;
	private String sapId;
	private String lastNameGlobal;
	private String firstNameGlobal;
	private String middleNameGlobal;
	private String lastNameLocal;
	private String firstNameLocal;
	private String middleNameLocal;
	private Date birthDay;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getNciId() {
		return nciId;
	}

	public void setNciId(String nciId) {
		this.nciId = nciId;
	}

	public String getSapId() {
		return sapId;
	}

	public void setSapId(String sapId) {
		this.sapId = sapId;
	}

	public String getLastNameGlobal() {
		return lastNameGlobal;
	}

	public void setLastNameGlobal(String lastNameGlobal) {
		this.lastNameGlobal = lastNameGlobal;
	}

	public String getFirstNameGlobal() {
		return firstNameGlobal;
	}

	public void setFirstNameGlobal(String firstNameGlobal) {
		this.firstNameGlobal = firstNameGlobal;
	}

	public String getMiddleNameGlobal() {
		return middleNameGlobal;
	}

	public void setMiddleNameGlobal(String middleNameGlobal) {
		this.middleNameGlobal = middleNameGlobal;
	}

	public String getLastNameLocal() {
		return lastNameLocal;
	}

	public void setLastNameLocal(String lastNameLocal) {
		this.lastNameLocal = lastNameLocal;
	}

	public String getFirstNameLocal() {
		return firstNameLocal;
	}

	public void setFirstNameLocal(String firstNameLocal) {
		this.firstNameLocal = firstNameLocal;
	}

	public String getMiddleNameLocal() {
		return middleNameLocal;
	}

	public void setMiddleNameLocal(String middleNameLocal) {
		this.middleNameLocal = middleNameLocal;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		String name = ((lastNameLocal != null) ? lastNameLocal.trim() + " " : "")
				+ ((firstNameLocal != null) ? firstNameLocal.trim() : "") + " "
				+ ((middleNameLocal != null ? middleNameLocal.trim() : ""));
		return name;
	}
}
