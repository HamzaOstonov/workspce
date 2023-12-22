package com.is.creditanket.table_models;

public class Ld_account {

	private String branch;
	private Long id;
	private Long acc_type_id;
	private String bank;
	private String account;
	private Integer sign;
	
	public Ld_account() {
	
	}

	public Ld_account(String branch, Long id, Long acc_type_id, String bank,
			String account, Integer sign) {
		super();
		this.branch = branch;
		this.id = id;
		this.acc_type_id = acc_type_id;
		this.bank = bank;
		this.account = account;
		this.sign = sign;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAcc_type_id() {
		return acc_type_id;
	}

	public void setAcc_type_id(Long acc_type_id) {
		this.acc_type_id = acc_type_id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}
	
}
