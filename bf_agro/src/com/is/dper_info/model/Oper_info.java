package com.is.dper_info.model;

public class Oper_info {
	private String nazn;
	private String purpose;
	private String cashsymd;
	private String cashsym;
	private String val_d;
	private String val_c;
	private int typeover;
	public Oper_info() {
	}
	
	public Oper_info(String nazn, String purpose, String cashsymd,
			String cashsym, String val_d, String val_c, int typeover) {
		super();
		this.nazn = nazn;
		this.purpose = purpose;
		this.cashsymd = cashsymd;
		this.cashsym = cashsym;
		this.val_d = val_d;
		this.val_c = val_c;
		this.typeover = typeover;
	}

	public String getNazn() {
		return nazn;
	}
	public void setNazn(String nazn) {
		this.nazn = nazn;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getCashsymd() {
		return cashsymd;
	}
	public void setCashsymd(String cashsymd) {
		this.cashsymd = cashsymd;
	}
	public String getCashsym() {
		return cashsym;
	}
	public void setCashsym(String cashsym) {
		this.cashsym = cashsym;
	}
	public String getVal_d() {
		return val_d;
	}
	public void setVal_d(String val_d) {
		this.val_d = val_d;
	}
	public String getVal_c() {
		return val_c;
	}
	public void setVal_c(String val_c) {
		this.val_c = val_c;
	}
	public int getTypeover() {
		return typeover;
	}
	public void setTypeover(int typeover) {
		this.typeover = typeover;
	}
	
}
