package com.is.uzcard.model;

public class AppList {
	private String branch;
	private String client;
	private String fio;
	private int state;
	private String state_name;
	private long app_id;
	private String error;

	public AppList() {
		super();
	}

	public AppList(String branch, String client, String fio, int state, long app_id, String error) {
		super();
		this.branch = branch;
		this.client = client;
		this.fio = fio;
		this.state = state;
		this.app_id = app_id;
		this.error = error;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getApp_id() {
		return app_id;
	}

	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
	

}
