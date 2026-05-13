// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

public class TietoCustomer
{
    private String branch;
    private String bankCustomerId;
    private String tietoCustomerId;
    
    public TietoCustomer() {
    }
    
    public TietoCustomer(final String branch, final String bankCustomerId, final String tietoCustomerId) {
        this.branch = branch;
        this.setBankCustomerId(bankCustomerId);
        this.setTietoCustomerId(tietoCustomerId);
    }
    
    public String getBranch() {
        return this.branch;
    }
    
    public void setBranch(final String branch) {
        this.branch = branch;
    }

	public void setBankCustomerId(String bankCustomerId) {
		this.bankCustomerId = bankCustomerId;
	}

	public String getBankCustomerId() {
		return bankCustomerId;
	}

	public void setTietoCustomerId(String tietoCustomerId) {
		this.tietoCustomerId = tietoCustomerId;
	}

	public String getTietoCustomerId() {
		return tietoCustomerId;
	}
}
    
  