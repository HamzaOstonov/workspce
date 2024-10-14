package com.is.clientcrm.reference;

public class Reference {
	private String data;
	private String label;

	public Reference() {
	}

	public Reference(String data, String label) {
		this.data=data;
		this.label=label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}