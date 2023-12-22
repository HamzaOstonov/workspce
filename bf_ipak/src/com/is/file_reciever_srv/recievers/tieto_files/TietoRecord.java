package com.is.file_reciever_srv.recievers.tieto_files;

import java.util.Date;

public class TietoRecord {
	
	private Long id;
	private Long tieto_file_id;
	private Integer state_id;
	private String record_type;
	private Long line_number;
	private String mfo_short;
	private String tranz_acct;
	private String fullname;
	private String deb_cred;
	private String card_number;
	private String tran_type;
	private Long tran_amt;
	private String currency;
	private Date tran_date;
	private String country_code;
	private String mcc_code;
	private String mfo_full;
	private String merchant_title;
	private String passport_info;
	private Long comission;
	private String merchant_code;
	private String terminal_code;
	private String city;
	
	public TietoRecord() {
		super();
	}

	public TietoRecord(Long id, Long tieto_file_id, Integer state_id, String record_type, Long line_number,
			String mfo_short, String tranz_acct, String fullname, String deb_cred, String card_number, String tran_type,
			Long tran_amt, String currency, Date tran_date, String country_code, String mcc_code, String mfo_full,
			String merchant_title, String passport_info, Long comission, String merchant_code, String terminal_code,
			String city) {
		super();
		this.id = id;
		this.tieto_file_id = tieto_file_id;
		this.state_id = state_id;
		this.record_type = record_type;
		this.line_number = line_number;
		this.mfo_short = mfo_short;
		this.tranz_acct = tranz_acct;
		this.fullname = fullname;
		this.deb_cred = deb_cred;
		this.card_number = card_number;
		this.tran_type = tran_type;
		this.tran_amt = tran_amt;
		this.currency = currency;
		this.tran_date = tran_date;
		this.country_code = country_code;
		this.mcc_code = mcc_code;
		this.mfo_full = mfo_full;
		this.merchant_title = merchant_title;
		this.passport_info = passport_info;
		this.comission = comission;
		this.merchant_code = merchant_code;
		this.terminal_code = terminal_code;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTieto_file_id() {
		return tieto_file_id;
	}

	public void setTieto_file_id(Long tieto_file_id) {
		this.tieto_file_id = tieto_file_id;
	}

	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public Long getLine_number() {
		return line_number;
	}

	public void setLine_number(Long line_number) {
		this.line_number = line_number;
	}

	public String getMfo_short() {
		return mfo_short;
	}

	public void setMfo_short(String mfo_short) {
		this.mfo_short = mfo_short;
	}

	public String getTranz_acct() {
		return tranz_acct;
	}

	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDeb_cred() {
		return deb_cred;
	}

	public void setDeb_cred(String deb_cred) {
		this.deb_cred = deb_cred;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public Long getTran_amt() {
		return tran_amt;
	}

	public void setTran_amt(Long tran_amt) {
		this.tran_amt = tran_amt;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTran_date() {
		return tran_date;
	}

	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getMcc_code() {
		return mcc_code;
	}

	public void setMcc_code(String mcc_code) {
		this.mcc_code = mcc_code;
	}

	public String getMfo_full() {
		return mfo_full;
	}

	public void setMfo_full(String mfo_full) {
		this.mfo_full = mfo_full;
	}

	public String getMerchant_title() {
		return merchant_title;
	}

	public void setMerchant_title(String merchant_title) {
		this.merchant_title = merchant_title;
	}

	public String getPassport_info() {
		return passport_info;
	}

	public void setPassport_info(String passport_info) {
		this.passport_info = passport_info;
	}

	public Long getComission() {
		return comission;
	}

	public void setComission(Long comission) {
		this.comission = comission;
	}

	public String getMerchant_code() {
		return merchant_code;
	}

	public void setMerchant_code(String merchant_code) {
		this.merchant_code = merchant_code;
	}

	public String getTerminal_code() {
		return terminal_code;
	}

	public void setTerminal_code(String terminal_code) {
		this.terminal_code = terminal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
