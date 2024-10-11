package com.is.tietovisa.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class NewCustomerResponse {
	private String err_code;
	private String err_text;
	private RowType_Customer rt_cust;
	
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
	public RowType_Customer getRt_cust() {
		return rt_cust;
	}
	public void setRt_cust(RowType_Customer rt_cust) {
		this.rt_cust = rt_cust;
	}
}
