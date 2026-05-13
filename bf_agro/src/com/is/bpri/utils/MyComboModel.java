package com.is.bpri.utils;

public class MyComboModel {

	private String value;
	private String label;
	private String model;
	
	public MyComboModel() {
	
	}

	public MyComboModel(String value, String label, String model) {
		this.value = value;
		this.label = label;
		this.model = model;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}
