package com.is.qr_online.send;

public class Header {

	private String bank;
	private String branch;
	private String format;
	
	public Header(String bank, String branch, String format) {
		super();
		this.bank = bank;
		this.branch = branch;
		this.format = format;
	}
	
	public Header(){
		
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "\"bank\":\"" + bank + "\", "
				+ "\"branch\":\"" + branch + "\", "
				+ "\"format\":\"" + format + "\" "
				+ "}";
	}
	
}
