package com.is.qr_online.send.RegQRClient;

public class ResponseQrClient {
	private String inn;

	public ResponseQrClient(String inn) {
		super();
		this.inn = inn;
	}
	public ResponseQrClient(){
		
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

}
