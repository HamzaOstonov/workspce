package com.is.openway.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Balances {
	@JsonProperty("Balance")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList < Balance > BalanceObject ;
	

	 // Getter Methods 
	@JsonProperty("Balance")
	 public ArrayList < Balance >  getBalance() {
	  return BalanceObject;
	 }

	 // Setter Methods 
	@JsonProperty("Balance")
	 public void setBalance(ArrayList < Balance >  ContractRsObject) {
	  this.BalanceObject = ContractRsObject;
	 }
}
