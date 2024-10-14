package com.is.tieto_globuz.tieto;

import java.sql.Date;

public class PaymentFilter {
	public PaymentFilter(String employee_id, String account, String date,
			String cardNumber, String amount, String state) {
		super();
		this.employee_id = employee_id;
		this.account = account;
		this.date = date;
		this.cardNumber = cardNumber;
		this.amount = amount;
		this.state = state;
	}
	private String employee_id;
	private String account;
	private String date;
	//private String dateTill;
	private String cardNumber;
	private String amount;
	private String state;
	
	public PaymentFilter(){
	}
	
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount() {
		return account;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmount() {
		return amount;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}

/*	public void setDateSince(String dateSince) {
		this.dateSince = dateSince;
	}

	public String getDateSince() {
		return dateSince;
	}

	public void setDateTill(String dateTill) {
		this.dateTill = dateTill;
	}

	public String getDateTill() {
		return dateTill;
	}*/

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
}
