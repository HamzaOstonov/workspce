package com.is.tieto_globuz.customer;

import java.util.HashMap;

import com.is.tieto_globuz.EmpcSettings;
import com.is.tieto_globuz.tieto.Tclient;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;

public class NewCardRequest {
	
	private HashMap<String, EmpcSettings> settings;
	private String branch;
	private Customer new_customer;
	private GlobuzAccount acc;
	private Tclient tc;
	private String alias;
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy;
	public HashMap<String, EmpcSettings> getSettings() {
		return settings;
	}
	public void setSettings(HashMap<String, EmpcSettings> settings) {
		this.settings = settings;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Customer getNew_customer() {
		return new_customer;
	}
	public void setNew_customer(Customer new_customer) {
		this.new_customer = new_customer;
	}
	public GlobuzAccount getAcc() {
		return acc;
	}
	public void setAcc(GlobuzAccount acc) {
		this.acc = acc;
	}
	public Tclient getTc() {
		return tc;
	}
	public void setTc(Tclient tc) {
		this.tc = tc;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public globus.IssuingWS.IssuingPortProxy getIssuingPortProxy() {
		return issuingPortProxy;
	}
	public void setIssuingPortProxy(
			globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		this.issuingPortProxy = issuingPortProxy;
	}
	
	
}
