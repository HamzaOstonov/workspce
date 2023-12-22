package com.is.creditanket.table_models;

public class V_ld_account {
	
	private Long ord;
	private String name;
	private String bal;
	private String account;
	private String nstate;
	private String saldo1;
	private String saldo;
	private String acc_type_id;
	
	public V_ld_account() {
	
	}

	public V_ld_account(Long ord, String name, String bal, String account,
			String nstate, String saldo1, String saldo, String acc_type_id) {
		super();
		this.ord = ord;
		this.name = name;
		this.bal = bal;
		this.account = account;
		this.nstate = nstate;
		this.saldo1 = saldo1;
		this.saldo = saldo;
		this.acc_type_id = acc_type_id;
	}

	public Long getOrd() {
		return ord;
	}

	public void setOrd(Long ord) {
		this.ord = ord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNstate() {
		return nstate;
	}

	public void setNstate(String nstate) {
		this.nstate = nstate;
	}

	public String getSaldo1() {
		return saldo1;
	}

	public void setSaldo1(String saldo1) {
		this.saldo1 = saldo1;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getAcc_type_id() {
		return acc_type_id;
	}

	public void setAcc_type_id(String acc_type_id) {
		this.acc_type_id = acc_type_id;
	}
	
}
