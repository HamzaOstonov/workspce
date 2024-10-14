package com.is.humo;

public enum ClientSources {
	EBP("ebp"),
	NIBBD("nibbd"),
	SAP("sap");
	
	private String value;
	
	private ClientSources(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
