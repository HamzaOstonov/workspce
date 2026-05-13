package com.is.tieto_visae.tieto;



public class Customir {
	private BankClient bankClient;

	private BankClientP bankClientP;

	private TietoCustomir tietoClient;

	public void setBankClientP(BankClientP bankClientP) {
		this.bankClientP = bankClientP;
	}
	
	public void setBankClient(BankClient bankClient) {
		this.bankClient = bankClient;
	}

	public void setTietoClient(TietoCustomir tietoClient) {
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
	
	public TietoCustomir getTietoClient() {
		if(tietoClient == null) {
			tietoClient = new TietoCustomir();
		}
		return tietoClient;
	}
}
