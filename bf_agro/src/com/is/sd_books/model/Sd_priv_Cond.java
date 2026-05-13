package com.is.sd_books.model;

public class Sd_priv_Cond {
	
	private String par;
	private String name_par;
	private String value;
	
	public Sd_priv_Cond(){
						
	}

	public Sd_priv_Cond(String par, String name_par, String value) {
		super();
		this.par = par;
		this.name_par = name_par;
		this.value = value;
	}

	public String getPar() {
		return par;
	}

	public void setPar(String par) {
		this.par = par;
	}

	public String getName_par() {
		return name_par;
	}

	public void setName_par(String name_par) {
		this.name_par = name_par;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	}
