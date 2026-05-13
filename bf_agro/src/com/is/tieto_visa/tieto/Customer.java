package com.is.tieto_visa.tieto;



public class Customer {
	private BankClient bankClient;

	private BankClientP bankClientP;

	private TietoCustomer tietoClient;

	public void setBankClientP(BankClientP bankClientP) {
		this.bankClientP = bankClientP;
	}
	
	public void setBankClient(BankClient bankClient) {
		this.bankClient = bankClient;
	}

	public void setTietoClient(TietoCustomer tietoClient) {
		this.tietoClient = tietoClient;
	}
	
	public BankClient getBankClient() {
		if(bankClient == null) {
			bankClient = new BankClient();
		}
		return bankClient;
	}
	
	public BankClientP getBankClientP() {
		if(bankClientP == null) {
			bankClientP = new BankClientP();
		}
		return bankClientP;
	}
	
	public TietoCustomer getTietoClient() {
		if(tietoClient == null) {
			tietoClient = new TietoCustomer();
		}
		return tietoClient;
	}
}
