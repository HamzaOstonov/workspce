package com.is.tieto_visae.tieto;

public class Action {

	public Action(String action_id, String name, String state_begin, String state_end, String deal_id) {
		super();
		this.action_id = action_id;
		this.name = name;
		this.state_begin = state_begin;
		this.state_end = state_end;
		this.deal_id = deal_id;
	}

	String action_id;
	String name ;
	String state_begin;
	String state_end;
	String deal_id;
	
	public Action() {
		// TODO Auto-generated constructor stub
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState_begin() {
		return state_begin;
	}

	public void setState_begin(String state_begin) {
		this.state_begin = state_begin;
	}

	public String getState_end() {
		return state_end;
	}

	public void setState_end(String state_end) {
		this.state_end = state_end;
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

}
