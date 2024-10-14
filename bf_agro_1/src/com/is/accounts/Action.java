package com.is.accounts;

public class Action {
	 private int dealId;
	 private int id;
	 private String name;
	 private int manual;
	
	public Action() {}
	
	public Action(int dealId,int id,String name, int manual){
		super();
		this.dealId = dealId;
		this.id = id;
		this.manual = manual;
		this.name = name;
		
	}

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
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

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}
	
	
}
