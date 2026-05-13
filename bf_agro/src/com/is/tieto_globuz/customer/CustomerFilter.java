// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

public class CustomerFilter extends Customer
{
    private int tietoIdIsNotNull;
    private String card;
    public int getTietoIdIsNotNull() {
        return this.tietoIdIsNotNull;
    }
    
    public void setTietoIdIsNotNull(final int tietoIdIsNotNull) {
        this.tietoIdIsNotNull = tietoIdIsNotNull;
    }

	public void setCard(String card) {
		this.card = card;
	}

	public String getCard() {
		return card;
	}
}
