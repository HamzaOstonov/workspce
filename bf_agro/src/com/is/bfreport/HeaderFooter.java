package com.is.bfreport;

public class HeaderFooter {
	
	private String id;
	private String sel;
	private String value;
	
	public HeaderFooter(String id, String sel, String value) {
		super();
		this.id = id;
		this.sel = sel;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSel() {
		return sel;
	}

	public void setSel(String sel) {
		this.sel = sel;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	   
}
