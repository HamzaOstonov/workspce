package com.is.tieto_visa.tieto;

import java.math.BigDecimal;

/**
* ACCOUNT_NO	number		���������� ����� �����
* CARD_ACCT		string(34)	��������� ����
* CCY			string(19)	������ �����
 */
public class ListCardsFilter{
	public BigDecimal getAccount_no() {
		return account_no;
	}
	public void setAccount_no(BigDecimal account_no) {
		this.account_no = account_no;
	}
	public String getCard_acct() {
		return card_acct;
	}
	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	private BigDecimal account_no;
	private String card_acct;
	private String ccy;
}
