package com.is.providers;
public class Credentials {
	private String un;
	private String pw;

	public Credentials() {
		super();
	}

	public Credentials(String un, String pw) {
		super();
		this.un = un;
		this.pw = pw;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
