package com.is.tieto_capital.customer;

public class CardCustomerSearch {
	
	private String tietoClientId;
	private String cardAcct;
	private String cardPan;
	
	public CardCustomerSearch() {
		super();
	}
	
	public CardCustomerSearch(String tietoClientId, String cardAcct, String cardPan) {
		super();
		this.tietoClientId = tietoClientId;
		this.cardAcct = cardAcct;
		this.cardPan = cardPan;
	}

	public String getTietoClientId() {
		return tietoClientId;
	}

	public void setTietoClientId(String tietoClientId) {
		this.tietoClientId = tietoClientId;
	}

	public String getCardAcct() {
		return cardAcct;
	}

	public void setCardAcct(String cardAcct) {
		this.cardAcct = cardAcct;
	}

	public String getCardPan() {
		return cardPan;
	}

	public void setCardPan(String cardPan) {
		this.cardPan = cardPan;
	}
	
}
