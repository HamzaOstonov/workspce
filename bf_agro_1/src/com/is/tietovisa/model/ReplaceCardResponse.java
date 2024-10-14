package com.is.tietovisa.model;

public class ReplaceCardResponse {
	private String err_code;
	private String err_text;
	private OperationResponseInfo operResponseInfo;
	private RowType_ReplaceCard rtrc_parameters;
	
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
	public OperationResponseInfo getOperResponseInfo() {
		return operResponseInfo;
	}
	public void setOperResponseInfo(OperationResponseInfo operResponseInfo) {
		this.operResponseInfo = operResponseInfo;
	}
	public RowType_ReplaceCard getRtrc_parameters() {
		return rtrc_parameters;
	}
	public void setRtrc_parameters(RowType_ReplaceCard rtrc_parameters) {
		this.rtrc_parameters = rtrc_parameters;
	}
	
}
