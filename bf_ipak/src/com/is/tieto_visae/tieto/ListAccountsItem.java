package com.is.tieto_visae.tieto;

import java.math.BigDecimal;

/**
 * @author Rush
 * CARD_ACCT	String(34)		Номер счета карты *
 * TRANZ_ACCT	String(34)		Номер счета для маршрутизации на банковскую систему
 * STATUS		String(1)		Статус счета 0,3,4 *
 * ACC_PRTY		String(1)		Приоритет счета
 * C_ACCNT_TYPE	String(2)		Тип счета
 * CCY			String(3)		Валюта счета (алфавитный код) *
 * COND_SET		String(3)		Условия счета
 * CLIENT_B		String(19)		Код клиента
 * CLIENT		String(8)		Клиентский номер держателя карты
 * F_NAMES		String(34)		Имя
 * SURNAME		String(20)		Фамилия
 * B_BR_ID		Decimal(7,0)	Идентификатор филиала
 * OFFICE_ID	Decimal(7,0)	Идентификатор офиса
 * MAIN_ROW		Decimal(20,0)	Внутренний идентификатор для всех счетов, связанных с картой (договором), кроме счета loyalty 
 * ACCOUNT_NO	Decimal(20,0)	Внутренний номер счета
 * BANK_C		String(2)		Код банка – владельца строки
 * GROUPC		String(2)		Код группы карт – локальный для каждого банка
 * CARD_ACCT_B	String(2)		Номер счета карты в банке

 */

public class ListAccountsItem {
	private String card_acct;
	private String tranz_acct;
	private String status;
	private String acc_prty;
	private String c_accnt_type;
	private String ccy;
	private String cond_set;
	private String client_b;
	private String client;
	private String f_names;
	private String surname;
	private BigDecimal b_br_id;
	private BigDecimal office_id;
	private BigDecimal main_row;
	private BigDecimal account_no;
	private String bank_c;
	private String groupc;
	public String getCard_acct() {
		return card_acct;
	}
	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}
	public String getTranz_acct() {
		return tranz_acct;
	}
	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcc_prty() {
		return acc_prty;
	}
	public void setAcc_prty(String acc_prty) {
		this.acc_prty = acc_prty;
	}
	public String getC_accnt_type() {
		return c_accnt_type;
	}
	public void setC_accnt_type(String c_accnt_type) {
		this.c_accnt_type = c_accnt_type;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getCond_set() {
		return cond_set;
	}
	public void setCond_set(String cond_set) {
		this.cond_set = cond_set;
	}
	public String getClient_b() {
		return client_b;
	}
	public void setClient_b(String client_b) {
		this.client_b = client_b;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getF_names() {
		return f_names;
	}
	public void setF_names(String f_names) {
		this.f_names = f_names;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public BigDecimal getB_br_id() {
		return b_br_id;
	}
	public void setB_br_id(BigDecimal b_br_id) {
		this.b_br_id = b_br_id;
	}
	public BigDecimal getOffice_id() {
		return office_id;
	}
	public void setOffice_id(BigDecimal office_id) {
		this.office_id = office_id;
	}
	public BigDecimal getMain_row() {
		return main_row;
	}
	public void setMain_row(BigDecimal main_row) {
		this.main_row = main_row;
	}
	public BigDecimal getAccount_no() {
		return account_no;
	}
	public void setAccount_no(BigDecimal account_no) {
		this.account_no = account_no;
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
	public ListAccountsItem(String card_acct, String tranz_acct, String status, String acc_prty, String c_accnt_type,
			String ccy, String cond_set, String client_b, String client, String f_names, String surname,
			BigDecimal b_br_id, BigDecimal office_id, BigDecimal main_row, BigDecimal account_no, String bank_c,
			String groupc) {
		super();
		this.card_acct = card_acct;
		this.tranz_acct = tranz_acct;
		this.status = status;
		this.acc_prty = acc_prty;
		this.c_accnt_type = c_accnt_type;
		this.ccy = ccy;
		this.cond_set = cond_set;
		this.client_b = client_b;
		this.client = client;
		this.f_names = f_names;
		this.surname = surname;
		this.b_br_id = b_br_id;
		this.office_id = office_id;
		this.main_row = main_row;
		this.account_no = account_no;
		this.bank_c = bank_c;
		this.groupc = groupc;
	}
	public ListAccountsItem() {
		super();
	}

}
