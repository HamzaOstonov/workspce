package com.is.tieto_globuz.customer;

public class TempClient {
	private String branch;
	private String client_id;
	private String state;
	private String text;
	private String excel;
	
	public TempClient(String branch, String client_id, String state, String text, String excel){
		this.branch = branch;
		this.client_id = client_id;
		this.state = state;
		this.text = text;
		this.excel = excel;
	}
	
	public TempClient(){
		super();
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getExcel() {
		return excel;
	}
	public void setExcel(String excel) {
		this.excel = excel;
	}
}
