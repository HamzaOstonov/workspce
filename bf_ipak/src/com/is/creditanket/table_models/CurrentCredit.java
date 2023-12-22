package com.is.creditanket.table_models;

public class CurrentCredit {

	private Ld_account[] ld_account;
	private V_ld_account[] v_ld_account;
	private Ld_char ld_char;
	private Ld_forms ld_forms;
	private Ld_exp[] ld_exp;
	private Ld_gr[] ld_gr;
	private Ld_guarr[] ld_guarr;
	private Specialfrm[] specialfrm;
	
	public CurrentCredit() {
	
	}

	public CurrentCredit(Ld_account[] ld_account, V_ld_account[] v_ld_account, Ld_char ld_char,
			Ld_forms ld_forms, Ld_exp[] ld_exp, Ld_gr[] ld_gr,
			Ld_guarr[] ld_guarr, Specialfrm[] specialfrm) {
		super();
		this.ld_account = ld_account;
		this.v_ld_account = v_ld_account;
		this.ld_char = ld_char;
		this.ld_forms = ld_forms;
		this.ld_exp = ld_exp;
		this.ld_gr = ld_gr;
		this.ld_guarr = ld_guarr;
		this.specialfrm = specialfrm;
	}

	public Ld_account[] getLd_account() {
		return ld_account;
	}

	public void setLd_account(Ld_account[] ld_account) {
		this.ld_account = ld_account;
	}

	public Ld_char getLd_char() {
		return ld_char;
	}

	public void setLd_char(Ld_char ld_char) {
		this.ld_char = ld_char;
	}

	public Ld_forms getLd_forms() {
		return ld_forms;
	}

	public void setLd_forms(Ld_forms ld_forms) {
		this.ld_forms = ld_forms;
	}

	public Ld_exp[] getLd_exp() {
		return ld_exp;
	}

	public void setLd_exp(Ld_exp[] ld_exp) {
		this.ld_exp = ld_exp;
	}

	public Ld_gr[] getLd_gr() {
		return ld_gr;
	}

	public void setLd_gr(Ld_gr[] ld_gr) {
		this.ld_gr = ld_gr;
	}

	public Ld_guarr[] getLd_guarr() {
		return ld_guarr;
	}

	public void setLd_guarr(Ld_guarr[] ld_guarr) {
		this.ld_guarr = ld_guarr;
	}

	public Specialfrm[] getSpecialfrm() {
		return specialfrm;
	}

	public void setSpecialfrm(Specialfrm[] specialfrm) {
		this.specialfrm = specialfrm;
	}

	public V_ld_account[] getV_ld_account() {
		return v_ld_account;
	}

	public void setV_ld_account(V_ld_account[] v_ld_account) {
		this.v_ld_account = v_ld_account;
	}
	
}
