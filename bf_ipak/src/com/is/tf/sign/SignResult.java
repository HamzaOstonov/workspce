package com.is.tf.sign;

public class SignResult {
	private int code;
	private String message;
	private String serialNumber;
    private String signature;
    private long timeStamp;
	public SignResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SignResult(int code, String message, String serialNumber,
			String signature, long timeStamp) {
		super();
		this.code = code;
		this.message = message;
		this.serialNumber = serialNumber;
		this.signature = signature;
		this.timeStamp = timeStamp;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
