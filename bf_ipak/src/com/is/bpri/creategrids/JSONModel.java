package com.is.bpri.creategrids;

public class JSONModel {
	
	String name;
	String value;
	
	public JSONModel() {
	
	}
	
	public JSONModel(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
