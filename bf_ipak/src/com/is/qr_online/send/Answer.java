package com.is.qr_online.send;

public class Answer {

	private String message;
	private String code;
	private String qr;
	private String qr_id;
	private String descr;

	public Answer(String message, String code, String qr,String qr_id,String descr) {
		super();
		this.message = message;
		this.code = code;
		this.qr = qr;
		this.qr_id=qr_id;
		this.descr=descr;
	}

	public Answer() {

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

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getQr_id() {
		return qr_id;
	}

	public void setQr_id(String qr_id) {
		this.qr_id = qr_id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	

}
