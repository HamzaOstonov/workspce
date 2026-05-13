package com.is.bpri.product_info;

public class Product_ld_char {

	private String currency;
	private String amount;
	private String shifr;
	private String kred_cb;
	private String method;
	private String speed;
	private String kred;
	private String typeZM;
	
	public Product_ld_char() {
	
	}

	public Product_ld_char(String currency, String amount, String shifr,
			String kred_cb, String method, String speed, String kred,
			String typeZM) {
		super();
		this.currency = currency;
		this.amount = amount;
		this.shifr = shifr;
		this.kred_cb = kred_cb;
		this.method = method;
		this.speed = speed;
		this.kred = kred;
		this.typeZM = typeZM;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getShifr() {
		return shifr;
	}

	public void setShifr(String shifr) {
		this.shifr = shifr;
	}

	public String getKred_cb() {
		return kred_cb;
	}

	public void setKred_cb(String kred_cb) {
		this.kred_cb = kred_cb;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getKred() {
		return kred;
	}

	public void setKred(String kred) {
		this.kred = kred;
	}

	public String getTypeZM() {
		return typeZM;
	}

	public void setTypeZM(String typeZM) {
		this.typeZM = typeZM;
	}
	
}
