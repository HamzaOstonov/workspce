package com.is.tieto_visae.tieto;

/**
* CARD		string(19)	Номер карты (PAN) 
* BANK_C	string(2)	Код банка – владельца строки
* GROUPC	string(2)	Код группы карт – локальный для каждого банка
*/
public class CardBalanceFilter {

	private String card;
	
	private String bank_c;
	
	public CardBalanceFilter() {
		super();
	}

	public CardBalanceFilter(String card, String bank_c, String groupc) {
		super();
		this.card = card;
		this.bank_c = bank_c;
		this.groupc = groupc;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getBank_c() {
		return bank_c;
	}

	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}

	public String getGroupc() {
		return groupc;
	}

	public void setGroupc(String groupc) {
		this.groupc = groupc;
	}

	private String groupc;
}

