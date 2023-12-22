package com.is.korona_pay;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HandleOperationResponse {
	
	@JsonProperty("Action")
	public String action;
	@JsonProperty("RetCode")
	public Integer retCode;
	@JsonProperty("RetMsg")
	public String retMsg;
	@JsonProperty("RetExtMsg")
	public String retExtMsg;
	
	public HandleOperationResponse() {
		
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRetExtMsg() {
		return retExtMsg;
	}

	public void setRetExtMsg(String retExtMsg) {
		this.retExtMsg = retExtMsg;
	}
	
	

}
