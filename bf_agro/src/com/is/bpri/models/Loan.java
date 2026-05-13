package com.is.bpri.models;

public class Loan {

	private String loan_id;
	private Double amount;
	private Graf[] grafs;
	private History[] histories;
	
	public Loan() {
	
	}

	public Loan(String loan_id, Double amount, Graf[] grafs, History[] histories) {
		super();
		this.loan_id = loan_id;
		this.amount = amount;
		this.grafs = grafs;
		this.histories = histories;
	}

	public String getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Graf[] getGrafs() {
		return grafs;
	}

	public void setGrafs(Graf[] grafs) {
		this.grafs = grafs;
	}

	public History[] getHistories() {
		return histories;
	}

	public void setHistories(History[] histories) {
		this.histories = histories;
	}
	
}
