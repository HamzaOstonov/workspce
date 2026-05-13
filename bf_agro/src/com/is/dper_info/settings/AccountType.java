package com.is.dper_info.settings;

public class AccountType {
	private int id;
	private String acc;
	private String name;
	private String rowid;
	
	
	
	public AccountType(int id, String acc, String name,String rowid) {
		super();
		this.id = id;
		this.acc = acc;
		this.name = name;
		this.rowid = rowid;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRowid() {
		return rowid;
	}



	public void setRowid(String rowid) {
		this.rowid = rowid;
	}



	public AccountType() {
	}
	
	
}
