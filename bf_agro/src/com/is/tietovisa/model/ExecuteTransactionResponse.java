package com.is.tietovisa.model;


public class ExecuteTransactionResponse {

	private String err_code;
	private String err_text;
	private visa.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo;
	private visa.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details;

	
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
	public visa.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder getDetails() {
		return details;
	}
	public void setDetails(visa.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) {
		this.details = details;
	}
	public visa.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder getResponseInfo() {
		return responseInfo;
	}
	public void setResponseInfo(visa.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo) {
		this.responseInfo = responseInfo;
	}

}
