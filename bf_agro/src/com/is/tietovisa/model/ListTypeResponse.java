package com.is.tietovisa.model;

public class ListTypeResponse {
	private String err_code;
	private String err_text;
	private ListType_GenericHolder lt_holder;
	
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_text() {
		return err_text;
	}
	public void setErr_text(String err_text) {
		this.err_text = err_text;
	}
	public ListType_GenericHolder getLt_holder() {
		return lt_holder;
	}
	public void setLt_holder(ListType_GenericHolder lt_holder) {
		this.lt_holder = lt_holder;
	}

}
