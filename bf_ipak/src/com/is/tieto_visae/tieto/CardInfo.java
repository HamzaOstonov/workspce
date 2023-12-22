package com.is.tieto_visae.tieto;


public class CardInfo
{

	private ListCardsItem mainInfo;
	private CardBalance balance;
	
	public CardInfo(ListCardsItem maininfo_, CardBalance balance_)
	{
		super();
		this.setMainInfo(maininfo_);
		this.setBalance(balance_);
	}
	
	public CardInfo()
	{
		super();
	}

	public ListCardsItem getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(ListCardsItem mainInfo) {
		this.mainInfo = mainInfo;
	}

	public CardBalance getBalance() {
		return balance;
	}

	public void setBalance(CardBalance balance) {
		this.balance = balance;
	}
		
}

