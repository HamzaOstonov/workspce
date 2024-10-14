package com.is.tieto_visa.tieto;

import java.math.BigDecimal;

/**
* ID				number 			Id
* CARD_ACCT 		varchar2(20)	Номер счета карты
* CARD				varchar2(19)	Номер карты (PAN)
* TRANSACTION_AMNT	number			Сумма транзакции
* BRANCH			varchar2(64)	Филиал банка
* CLIENT_TIETO		varchar2(128)	Клиентский номер держателя карты
* CLIENT_BANK		varchar2(128)	Номер клиента в банке
* STATE				number(2)		Состояние
* PAYMENT_MODE		varchar2(1)		Следующие значения PAYMENT_MODE позволены: 0,1,2,3
* BANK_C			varchar2(2)		Код банка – владельца строки
* GROUPC			varchar2(2)		Код группы карт – локальный для каждого банка
* CARD_ACC_CCY		varchar2(3)		Валюта счета (алфавитный код)
* TRAN_TYPE			varchar2(3)		Тип транзакции
* TRAN_CCY			varchar2(3)		Код валюты транзакции
* CLIENT_NAME		varchar2(34)	Имя клиента
* CLIENT_SURNAME	varchar2(20)	Фамилия клиента
 */

public class ConfirmRefill {
	String id;
	String card_acct;
	String card;
	BigDecimal transaction_amnt;
	String branch;
	String client_tieto;
	String client_bank;
	String state;
	String payment_mode;
	String bank_c;
	String groupc;
	String card_acc_ccy;
	String tran_type;
	String tran_ccy;
	String client_name;
	String client_surname;
    Long type_oper;
	String tranz_acct;
	String account_no;
	String err_code;
	String err_msg;

	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getTranz_acct() {
		return tranz_acct;
	}
	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCard_acct() {
		return card_acct;
	}
	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}
	public ConfirmRefill() {
		super();
	}
	public ConfirmRefill(String id, String card_acct, String card, BigDecimal transaction_amnt, String branch,
			String client_tieto, String client_bank, String state, String payment_mode, String bank_c,
			String groupc, String card_acc_ccy, String tran_type, String tran_ccy, String client_name,
			String client_surname, Long type_oper, String tranz_acct, String account_no, String err_code, String err_msg) {
		super();
		this.id = id;
		this.card_acct = card_acct;
		this.card = card;
		this.transaction_amnt = transaction_amnt;
		this.branch = branch;
		this.client_tieto = client_tieto;
		this.client_bank = client_bank;
		this.state = state;
		this.payment_mode = payment_mode;
		this.bank_c = bank_c;
		this.groupc = groupc;
		this.card_acc_ccy = card_acc_ccy;
		this.tran_type = tran_type;
		this.tran_ccy = tran_ccy;
		this.client_name = client_name;
		this.client_surname = client_surname;
		this.type_oper = type_oper;
		this.tranz_acct = tranz_acct;
		this.account_no = account_no;
		this.err_code = err_code;
		this.err_msg = err_msg;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public BigDecimal getTransaction_amnt() {
		return transaction_amnt;
	}
	public void setTransaction_amnt(BigDecimal transaction_amnt) {
		this.transaction_amnt = transaction_amnt;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getClient_tieto() {
		return client_tieto;
	}
	public void setClient_tieto(String client_tieto) {
		this.client_tieto = client_tieto;
	}
	public String getClient_bank() {
		return client_bank;
	}
	public void setClient_bank(String client_bank) {
		this.client_bank = client_bank;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
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
	public String getCard_acc_ccy() {
		return card_acc_ccy;
	}
	public void setCard_acc_ccy(String card_acc_ccy) {
		this.card_acc_ccy = card_acc_ccy;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getTran_ccy() {
		return tran_ccy;
	}
	public void setTran_ccy(String tran_ccy) {
		this.tran_ccy = tran_ccy;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_surname() {
		return client_surname;
	}
	public void setClient_surname(String client_surname) {
		this.client_surname = client_surname;
	}
	public Long getType_oper() {
		return type_oper;
	}
	public void setType_oper(Long type_oper) {
		this.type_oper = type_oper;
	}
}
