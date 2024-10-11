package com.is.humo;

import java.math.BigInteger;

public class ResponsInfo {
	BigInteger ResponseCode;
	String ErrorDescription;
	String ErrorAction;
	Boolean successful;
	
		
	public Boolean getSuccessful() {
		return successful;
	}
	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}
	public BigInteger getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(BigInteger responseCode) {
		ResponseCode = responseCode;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
	public String getErrorAction() {
		return ErrorAction;
	}
	public void setErrorAction(String errorAction) {
		ErrorAction = errorAction;
	}
	
	
	
}
