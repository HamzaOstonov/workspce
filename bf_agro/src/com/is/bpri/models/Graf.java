package com.is.bpri.models;

import java.util.Date;

public class Graf {

	private Date date;
	private Double amount;
	private Double main_amount;
	private Double max_amount;
	
	public Graf() {
		
	}

	public Graf(Date date, Double amount, Double main_amount, Double max_amount) {
		super();
		this.date = date;
		this.amount = amount;
		this.main_amount = main_amount;
		this.max_amount = max_amount;
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

	public Double getMain_amount() {
		return main_amount;
	}

	public void setMain_amount(Double main_amount) {
		this.main_amount = main_amount;
	}

	public Double getMax_amount() {
		return max_amount;
	}

	public void setMax_amount(Double max_amount) {
		this.max_amount = max_amount;
	}
	
}
