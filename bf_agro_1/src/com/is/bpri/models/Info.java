package com.is.bpri.models;

public class Info {
	
	private Client client;
	private Loan[] loans;
	
	public Info() {
	
	}

	public Info(Client client, Loan[] loans) {
		super();
		this.client = client;
		this.loans = loans;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Loan[] getLoans() {
		return loans;
	}

	public void setLoans(Loan[] loans) {
		this.loans = loans;
	}
	
}
