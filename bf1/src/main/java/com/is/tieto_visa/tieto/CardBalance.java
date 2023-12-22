package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;

public class CardBalance {
	private  String card;
	private  Date expiry1;
	private  String card_status;
	private  BigDecimal account_no;
	private  String card_acct;
	private  String acc_status;
	private  String ccy;
	private  BigDecimal end_bal;
	private  BigDecimal locked_amount;
	private  BigDecimal avail_amount;
	private  String c_accnt_type;
	private  String stop_cause;
	private  BigDecimal main_row;
	private  String bank_c;
	private  String groupc;
	private  String ccy_exp;
	private  BigDecimal crd;
	private  Date crd_expiry;

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public CardBalance(String card, Date expiry1, String card_status, BigDecimal account_no, String card_acct,
			String acc_status, String ccy, BigDecimal end_bal, BigDecimal locked_amount, BigDecimal avail_amount,
			String c_accnt_type, String stop_cause, BigDecimal main_row, String bank_c, String groupc, String ccy_exp,
			BigDecimal crd, Date crd_expiry) {
		super();
		this.card = card;
		this.expiry1 = expiry1;
		this.card_status = card_status;
		this.account_no = account_no;
		this.card_acct = card_acct;
		this.acc_status = acc_status;
		this.ccy = ccy;
		this.end_bal = end_bal;
		this.locked_amount = locked_amount;
		this.avail_amount = avail_amount;
		this.c_accnt_type = c_accnt_type;
		this.stop_cause = stop_cause;
		this.main_row = main_row;
		this.bank_c = bank_c;
		this.groupc = groupc;
		this.ccy_exp = ccy_exp;
		this.crd = crd;
		this.crd_expiry = crd_expiry;
	}

	public Date getExpiry1() {
		return expiry1;
	}

	public void setExpiry1(Date expiry1) {
		this.expiry1 = expiry1;
	}

	public String getCard_status() {
		return card_status;
	}

	public void setCard_status(String card_status) {
		this.card_status = card_status;
	}

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

	public String getAcc_status() {
		return acc_status;
	}

	public void setAcc_status(String acc_status) {
		this.acc_status = acc_status;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getEnd_bal() {
		return end_bal;
	}

	public void setEnd_bal(BigDecimal end_bal) {
		this.end_bal = end_bal;
	}

	public BigDecimal getLocked_amount() {
		return locked_amount;
	}

	public void setLocked_amount(BigDecimal locked_amount) {
		this.locked_amount = locked_amount;
	}

	public BigDecimal getAvail_amount() {
		return avail_amount;
	}

	public void setAvail_amount(BigDecimal avail_amount) {
		this.avail_amount = avail_amount;
	}

	public String getC_accnt_type() {
		return c_accnt_type;
	}

	public void setC_accnt_type(String c_accnt_type) {
		this.c_accnt_type = c_accnt_type;
	}

	public String getStop_cause() {
		return stop_cause;
	}

	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}

	public BigDecimal getMain_row() {
		return main_row;
	}

	public void setMain_row(BigDecimal main_row) {
		this.main_row = main_row;
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

	public String getCcy_exp() {
		return ccy_exp;
	}

	public void setCcy_exp(String ccy_exp) {
		this.ccy_exp = ccy_exp;
	}

	public BigDecimal getCrd() {
		return crd;
	}

	public void setCrd(BigDecimal crd) {
		this.crd = crd;
	}

	public Date getCrd_expiry() {
		return crd_expiry;
	}

	public void setCrd_expiry(Date crd_expiry) {
		this.crd_expiry = crd_expiry;
	}

	public CardBalance() {
		// TODO Auto-generated constructor stub
	}

}
