package com.is.qr_online.send;

public class Send {
	
	private String header;
	private String body;
	public Send(String header, String body) {
		super();
		this.header = header;
		this.body = body;
	}
	
	public Send(){
		
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "{" +
				"\"header\": " + header.toString() + "," +
				"\"body\": " + body.toString() + ""+
				"}";
	}

	
	
}
