package com.is.trpayti;

public class State {

	int deal_id;
	int id;
	String name;
	int flag_createdoc;
	int flag_edit;
	int flag_client_view;
	int flag_prog_view;
	int flag_createdoct;
	
	
	
	public State(int id, String name, int deal_id) {
		super();
		this.id = id;
		this.name = name;
		this.deal_id = deal_id;
	}
	
	
	public int getDeal_id() {
		return deal_id;
	}
	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlag_createdoc() {
		return flag_createdoc;
	}
	public void setFlag_createdoc(int flag_createdoc) {
		this.flag_createdoc = flag_createdoc;
	}
	public int getFlag_edit() {
		return flag_edit;
	}
	public void setFlag_edit(int flag_edit) {
		this.flag_edit = flag_edit;
	}
	public int getFlag_client_view() {
		return flag_client_view;
	}
	public void setFlag_client_view(int flag_client_view) {
		this.flag_client_view = flag_client_view;
	}
	public int getFlag_prog_view() {
		return flag_prog_view;
	}
	public void setFlag_prog_view(int flag_prog_view) {
		this.flag_prog_view = flag_prog_view;
	}
	public int getFlag_createdoct() {
		return flag_createdoct;
	}
	public void setFlag_createdoct(int flag_createdoct) {
		this.flag_createdoct = flag_createdoct;
	}
	
	
}
