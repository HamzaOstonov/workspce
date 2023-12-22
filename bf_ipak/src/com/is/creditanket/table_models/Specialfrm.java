package com.is.creditanket.table_models;

public class Specialfrm {

	private Long id_special;
	private String value;
	
	public Specialfrm() {
	
	}

	public Specialfrm(Long id_special, String value) {
		super();
		this.id_special = id_special;
		this.value = value;
	}

	public Long getId_special() {
		return id_special;
	}

	public void setId_special(Long id_special) {
		this.id_special = id_special;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
