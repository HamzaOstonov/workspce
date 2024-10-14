package com.is.bpri;

import java.io.Serializable;

public class BprType implements Serializable {
	static final long serialVersionUID = 103844514947365244L;

	private int bpr_type;
	private int bpr_id;
	private String bpr_name;
	private String deal_id;
	private String currency;
	private String provision;
	private String mfo;
	private String region_id;
	private int state;
	private String state_name;
	private String target_clients;

	public BprType() {

	}

	public BprType(int bpr_type, int bpr_id, String bpr_name, String deal_id,
			String currency, String provision,String mfo,String region_id,int state,String state_name,String target_clients) {
		this.bpr_type = bpr_type;
		this.bpr_id = bpr_id;
		this.bpr_name = bpr_name;
		this.currency = currency;
		this.deal_id = deal_id;
		this.provision = provision;
		this.state = state;
		this.mfo = mfo;
		this.region_id = region_id;
		this.state_name = state_name;
		this.target_clients = target_clients;
	}

	public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	public int getBpr_type() {
		return bpr_type;
	}

	public void setBpr_type(int bpr_type) {
		this.bpr_type = bpr_type;
	}

	public String getName() {
		return bpr_name;
	}

	public void setName(String bpr_name) {
		this.bpr_name = bpr_name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getProvision() {
		return provision;
	}

	public void setProvision(String provision) {
		this.provision = provision;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getTarget_clients() {
		return target_clients;
	}

	public void setTarget_clients(String target_clients) {
		this.target_clients = target_clients;
	}
	
}
