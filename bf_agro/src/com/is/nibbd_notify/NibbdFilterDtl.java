package com.is.nibbd_notify;

import java.util.Date;

public class NibbdFilterDtl {	
    private Long id;
	private Long id_idx;
	private String debt_file;
	private Date date_file;
	private String sid;
	private String bid;
	private String doc_type;
	private String doc_number;
	private Date doc_date;
	private String payee_account;
	private Long doc_amount;
	private Long rest_amount;
	private String purpose_type;
	private String purpose_code;
	private String purposes_code;
	private String purpose;
	private String payee_mfo;

	public NibbdFilterDtl() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_idx() {
		return id_idx;
	}

	public void setId_idx(Long id_idx) {
		this.id_idx = id_idx;
	}

	public String getDebt_file() {
		return debt_file;
	}

	public void setDebt_file(String debt_file) {
		this.debt_file = debt_file;
	}

	public Date getDate_file() {
		return date_file;
	}

	public void setDate_file(Date date_file) {
		this.date_file = date_file;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getDoc_number() {
		return doc_number;
	}

	public void setDoc_number(String doc_number) {
		this.doc_number = doc_number;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public String getPayee_account() {
		return payee_account;
	}

	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}

	public Long getDoc_amount() {
		return doc_amount;
	}

	public void setDoc_amount(Long doc_amount) {
		this.doc_amount = doc_amount;
	}

	public Long getRest_amount() {
		return rest_amount;
	}

	public void setRest_amount(Long rest_amount) {
		this.rest_amount = rest_amount;
	}

	public String getPurpose_type() {
		return purpose_type;
	}

	public void setPurpose_type(String purpose_type) {
		this.purpose_type = purpose_type;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getPurposes_code() {
		return purposes_code;
	}

	public void setPurposes_code(String purposes_code) {
		this.purposes_code = purposes_code;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setPayee_mfo(String payee_mfo) {
		this.payee_mfo = payee_mfo;
	}

	public String getPayee_mfo() {
		return payee_mfo;
	}

    
}
