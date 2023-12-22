package com.is.qr_online.send.RegQRClient;

public class AnswerRegQRClient {
	private String message;
	private String code;
	
	public AnswerRegQRClient(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
