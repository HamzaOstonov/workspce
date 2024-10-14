package com.is.bpri;

public class BproductReport {
	
	private String id;
	private String branch;
	private String client;
	private String amount;
	private String s_in;
	private String acc_type_id;
	
	public BproductReport() {
		
	}
	
	public BproductReport(String branch,String id,String client,String amount,String s_in,String acc_type_id) {
		this.id = id;
		this.client = client;
		this.amount = amount;
		this.s_in = s_in;
		this.acc_type_id = acc_type_id;
		this.branch = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getS_in() {
		return s_in;
	}

	public void setS_in(String s_in) {
		this.s_in = s_in;
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAcc_type_id() {
		return acc_type_id;
	}
	
	public void setAcc_type_id(String acc_type_id) {
		this.acc_type_id = acc_type_id;
	}
}
