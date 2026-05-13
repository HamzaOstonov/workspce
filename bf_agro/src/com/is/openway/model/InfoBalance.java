package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName(value = "Info")
public class InfoBalance {
	@JsonProperty("Balances")
	private Balances BalancesObject;

	@JsonProperty("Balances")
	public Balances getBalancesObject() {
		return BalancesObject;
	}
	
	@JsonProperty("Balances")
	public void setBalancesObject(Balances balancesObject) {
		BalancesObject = balancesObject;
	}

}
