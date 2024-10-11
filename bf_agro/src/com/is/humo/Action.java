package com.is.humo;


public class Action {
	 public Action(int dealId, int id, String name, int manual) {
		super();
		this.dealId = dealId;
		this.id = id;
		this.name = name;
		this.manual = manual;
	}

	private int dealId;
	 private int id;
	 private String name;
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

	private int manual;
	
	public Action() {}

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
}
