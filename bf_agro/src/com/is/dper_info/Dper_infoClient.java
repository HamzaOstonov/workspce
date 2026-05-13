package com.is.dper_info;

import java.util.Date;

public class Dper_infoClient {

	private String client_i2;
	private String client_i3;
	private String client_i8;
	private String client_i9;
	private String client_i10;
	private String client_i12;
	private Date client_i11date;
	private String rezident;
	
	
	public Dper_infoClient() {
		
	}
	
	public Dper_infoClient(String client_i2, String client_i3, String client_i8,
			               String client_i9, String client_i10, Date client_i11date,
			               String client_i12, String rezident ) {
		super();
		this.client_i2 = client_i2;
		this.client_i3 = client_i3;
		this.client_i8 = client_i8;
		this.client_i9 = client_i9;
		this.client_i10 = client_i10;
		this.client_i12 = client_i12;
		this.client_i11date = client_i11date;
		this.rezident = rezident;
	}
	
	
	
	public String getClient_i2() {
		return client_i2;
	}
	
	public void setClient_i2() {
		this.client_i2 = client_i2;
	}
	
	public String getClient_i3() {
		return client_i3;
	}
	
	public void setClient_i3() {
		this.client_i3 = client_i3;
	}
	
	public String getClient_i8() {
		return client_i8;
	}
	
	public void setClient_i8() {
		this.client_i8 = client_i8;
	}
	
	public String getClient_i9() {
		return client_i9;
	}
	
	public void setClient_i9() {
		this.client_i9 = client_i9;
	}
	
	public String getClient_i10() {
		return client_i10;
	}
	
	public void setClient_i10() {
		this.client_i10 = client_i10;
	}
	
	public Date getClient_i11date() {
		return client_i11date;
	}
	
	public void setClient_i11date() {
		this.client_i11date = client_i11date;
	}
	
	public String getClient_i12() {
		return client_i12;
	}
	
	public void setClient_i12() {
		this.client_i12 = client_i12;
	}
	
	public String getRezident() {
		return rezident;
	}
	
	public void setRezident() {
		this.rezident = rezident;
	}
}
