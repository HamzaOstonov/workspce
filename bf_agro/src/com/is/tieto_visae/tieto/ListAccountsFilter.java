package com.is.tieto_visae.tieto;

import java.math.BigDecimal;

/**
 * @author Rush
 * CARD_ACCT	string(34)		����� ����� ����� *
 * TRANZ_ACCT	string(34)		����� ����� ��� ������������� � ���������� ������� *
 * STATUS		string(1)		������ ����� 0,3,4
 * ACC_PRTY		string(1)		��������� ����� 
 * C_ACCNT_TYPE	string(2)		��� ��� �����
 * CCY			string(3)		������ �����
 * COND_SET		string(3)		����� ���������� �������, ����� ��� ������ ������
 * CLIENT_B		string(19)		����� ������� � ����� *
 * CLIENT		string(8)		���������� ����� ��������� ����� *
 * F_NAMES		string(34)		��� *
 * SURNAME		string(20)		������� *
 * B_BR_ID		decimal(7,0)	���������� ����� ������� ����� *
 * OFFICE_ID	decimal(7,0)	���������� ����� ����� ������� ����� *
 * MAIN_ROW		decimal(20,0)	���������� ������������� ��� ���� ������, ��������� � ������ (���������), ����� ����� loyalty *
 * ACCOUNT_NO	decimal(20,0)	���������� ����� ����� *

 */

public class ListAccountsFilter {
	public ListAccountsFilter() {
		super();
	}
	public ListAccountsFilter(String card_acct, String tranz_acct, String status, String acc_prty, String c_accnt_type,
			String ccy, String cond_set, String client_b, String client, String f_names, String surname,
			BigDecimal b_br_id, BigDecimal office_id, BigDecimal main_row, BigDecimal account_no) {
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
	}
	private String card_acct;
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

}
