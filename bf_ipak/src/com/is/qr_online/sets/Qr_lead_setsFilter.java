package com.is.qr_online.sets;

public class Qr_lead_setsFilter {
	private String  payee_id;
	private String  id;
	private String  branch_dt;
	private String  account_dt;
	private String  branch_ct;
	private String  account_ct;
	private String  inn_ct;
	private String  purpose_code;
	private String  purpose;
	
	public Qr_lead_setsFilter(String payee_id,String id,String branch_dt, String account_dt, String branch_ct, String account_ct,
			String inn_ct, String purpose_code, String purpose) {
		super();
		this.payee_id = payee_id;
		this.id = id;
		this.branch_dt = branch_dt;
		this.account_dt = account_dt;
		this.branch_ct = branch_ct;
		this.account_ct = account_ct;
		this.inn_ct = inn_ct;
		this.purpose_code = purpose_code;
		this.purpose = purpose;
	}
	
	public Qr_lead_setsFilter(){
					
	}
    
	
	public String getPayee_id() {
		return payee_id;
	}

	public void setPayee_id(String payee_id) {
		this.payee_id = payee_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch_dt() {
		return branch_dt;
	}

	public void setBranch_dt(String branch_dt) {
		this.branch_dt = branch_dt;
	}

	public String getAccount_dt() {
		return account_dt;
	}

	public void setAccount_dt(String account_dt) {
		this.account_dt = account_dt;
	}

	public String getBranch_ct() {
		return branch_ct;
	}

	public void setBranch_ct(String branch_ct) {
		this.branch_ct = branch_ct;
	}

	public String getAccount_ct() {
		return account_ct;
	}

	public void setAccount_ct(String account_ct) {
		this.account_ct = account_ct;
	}

	public String getInn_ct() {
		return inn_ct;
	}

	public void setInn_ct(String inn_ct) {
		this.inn_ct = inn_ct;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return "Qr_lead_setsFilter [branch_dt=" + branch_dt + ", account_dt=" + account_dt 
				+ ", branch_ct=" + branch_ct + ", account_ct=" + account_ct + ", inn_ct=" + inn_ct + ", purpose_code="
				+ purpose_code + ", purpose=" + purpose + "]";
	}
		
}
