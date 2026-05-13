package com.is.globalTieto.customer;

import com.is.globalTieto.bankModels.BankClient;
import com.is.globalTieto.bankModels.BankClientP;
import com.is.globalTieto.tietoModels.TietoCustomer;
import lombok.Setter;

public class Customer {
	@Setter
	private BankClient bankClient;
	@Setter
	private BankClientP bankClientP;
	@Setter
	private TietoCustomer tietoClient;
	
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
