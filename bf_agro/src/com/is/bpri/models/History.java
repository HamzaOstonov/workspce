package com.is.bpri.models;

import java.util.Date;

public class History {

	private Date date;
	private Double amount;
	
	public History() {
	
	}

	public History(Date date, Double amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
