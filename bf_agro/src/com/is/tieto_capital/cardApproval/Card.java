package com.is.tieto_capital.cardApproval;

import java.io.Serializable;

public class Card implements Serializable {

    static final long serialVersionUID = 103844514947365244L;

    private Long id;
    private String account_no;
    private String card_acct;
    private String card;
    private String base_supp;
    private String status;
    private String status2;
    private String stop_cause;
    private String expiry;
    private String expiry2;
    private String cond_set;
    private String risk_level;
    private String client_id;
    private String cl_role;
    private String agreement_key;
    private String card_string;
    private String bank_c;
    private String groupc;
    private Long account_avail_amount;
    private Long account_locked_amount;
    private Long account_end_bal;
    private String bank_account;
    private String bank_account_status;
    private String bank_account_ccy;
    /*
     * 1 - Введена
     * 2 - Подтверждена
     * 3 - Отправлена в ТИЕТО
     */
    private Long state_id;
    
	public Card() {
		super();
	}

	public Card(Long id, String account_no, String card_acct, String card, String base_supp, String status, String status2,
			String stop_cause, String expiry, String expiry2, String cond_set, String risk_level, String client_id,
			String cl_role, String agreement_key, String card_string, String bank_c, String groupc,
			Long account_avail_amount, Long account_locked_amount, Long account_end_bal, String bank_account,
			String bank_account_status, String bank_account_ccy, Long state_id) {
		super();
		this.id = id;
		this.account_no = account_no;
		this.card_acct = card_acct;
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
		this.card_string = card_string;
		this.bank_c = bank_c;
		this.groupc = groupc;
		this.account_avail_amount = account_avail_amount;
		this.account_locked_amount = account_locked_amount;
		this.account_end_bal = account_end_bal;
		this.bank_account = bank_account;
		this.bank_account_status = bank_account_status;
		this.bank_account_ccy = bank_account_ccy;
		this.state_id = state_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getCard_acct() {
		return card_acct;
	}

	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
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

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getExpiry2() {
		return expiry2;
	}

	public void setExpiry2(String expiry2) {
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

	public String getAgreement_key() {
		return agreement_key;
	}

	public void setAgreement_key(String agreement_key) {
		this.agreement_key = agreement_key;
	}

	public String getCard_string() {
		return card_string;
	}

	public void setCard_string(String card_string) {
		this.card_string = card_string;
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

	public Long getAccount_avail_amount() {
		return account_avail_amount;
	}

	public void setAccount_avail_amount(Long account_avail_amount) {
		this.account_avail_amount = account_avail_amount;
	}

	public Long getAccount_locked_amount() {
		return account_locked_amount;
	}

	public void setAccount_locked_amount(Long account_locked_amount) {
		this.account_locked_amount = account_locked_amount;
	}

	public Long getAccount_end_bal() {
		return account_end_bal;
	}

	public void setAccount_end_bal(Long account_end_bal) {
		this.account_end_bal = account_end_bal;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getBank_account_status() {
		return bank_account_status;
	}

	public void setBank_account_status(String bank_account_status) {
		this.bank_account_status = bank_account_status;
	}

	public String getBank_account_ccy() {
		return bank_account_ccy;
	}

	public void setBank_account_ccy(String bank_account_ccy) {
		this.bank_account_ccy = bank_account_ccy;
	}

	public Long getState_id() {
		return state_id;
	}

	public void setState_id(Long state_id) {
		this.state_id = state_id;
	}

}

