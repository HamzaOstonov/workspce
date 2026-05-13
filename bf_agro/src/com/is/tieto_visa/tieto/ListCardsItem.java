package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;

public class ListCardsItem {
	private  BigDecimal account_no;
	private  String card;
	private  String base_supp;
	private  String status;
	private  String status2;
	private  String stop_cause;
	private  Date expiry;
	private  Date expiry2;
	private  String cond_set;
	private  String risk_level;
	private  String client_id;
	private  String cl_role;
	private  BigDecimal agreement_key;
	private  String card_name;

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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getBase_supp() {
		return base_supp;
	}

	public void setBase_supp(String base_supp) {
		this.base_supp = base_supp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStop_cause() {
		return stop_cause;
	}

	public void setStop_cause(String stop_cause) {
		this.stop_cause = stop_cause;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Date getExpiry2() {
		return expiry2;
	}

	public void setExpiry2(Date expiry2) {
		this.expiry2 = expiry2;
	}

	public String getCond_set() {
		return cond_set;
	}

	public void setCond_set(String cond_set) {
		this.cond_set = cond_set;
	}

	public String getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getCl_role() {
		return cl_role;
	}

	public void setCl_role(String cl_role) {
		this.cl_role = cl_role;
	}

	public BigDecimal getAgreement_key() {
		return agreement_key;
	}

	public void setAgreement_key(BigDecimal agreement_key) {
		this.agreement_key = agreement_key;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	private  String card_acct;
	private  String ccy;
	public ListCardsItem(BigDecimal account_no, String card_acct, String ccy, String card, String base_supp,
			String status, String status2, String stop_cause, Date expiry, Date expiry2, String cond_set,
			String risk_level, String client_id, String cl_role, BigDecimal agreement_key, String card_name) {
		super();
		this.account_no = account_no;
		this.card_acct = card_acct;
		this.ccy = ccy;
		this.card = card;
		this.base_supp = base_supp;
		this.status = status;
		this.status2 = status2;
		this.stop_cause = stop_cause;
		this.expiry = expiry;
		this.expiry2 = expiry2;
		this.cond_set = cond_set;
		this.risk_level = risk_level;
		this.client_id = client_id;
		this.cl_role = cl_role;
		this.agreement_key = agreement_key;
		this.card_name = card_name;
	}

	public ListCardsItem() {
		// TODO Auto-generated constructor stub
	}

}
