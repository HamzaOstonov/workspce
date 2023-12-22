package com.is.qr_online.payee;

public class Payee {
    private String branch;
	private String inn;
	private String account;
	private String name;
	private String mobile_phone;
	private String docnum;
	private String mark_RegQRClient;
	private String mark_ClientPhone;

	

	public String getMark_ClientPhone() {
		return mark_ClientPhone;
	}

	public void setMark_ClientPhone(String mark_ClientPhone) {
		this.mark_ClientPhone = mark_ClientPhone;
	}

	public String getMark_RegQRClient() {
		return mark_RegQRClient;
	}

	public void setMark_RegQRClient(String mark_RegQRClient) {
		this.mark_RegQRClient = mark_RegQRClient;
	}

	public String getDocnum() {
		return docnum;
	}

	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}

	public Payee(String inn, String account) {
		super();
		this.inn = inn;
		this.account = account;
	}

	public Payee() {

	}
	
	
	public Payee(String branch, String inn, String account, String name,String mobile_phone,String mark_RegQRClient,String mark_ClientPhone
			) {
		super();
		this.branch = branch;
		this.inn = inn;
		this.account = account;
		this.name = name;
		this.mobile_phone=mobile_phone;
		this.mark_RegQRClient=mark_RegQRClient;
		this.mark_ClientPhone=mark_ClientPhone;
		
	}

	public Payee(String branch, String inn, String account){
		super();
		this.branch = branch;
		this.inn = inn;
		this.account = account;
				
	}
	

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String toString() {
		return "{" + "\"inn\":\"" + inn + "\", " + "\"account\":\"" + account + "\", " + "\"branch\":\"" + branch +"\" " + "}";
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
