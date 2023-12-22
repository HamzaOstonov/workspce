package com.is.qr_online.send.SetClientPhone;

public class ResponseClientPhone {
	private String phone;

	public ResponseClientPhone(String phone) {
		super();
		this.phone = phone;
	}
	public ResponseClientPhone(){
		
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
