package com.is.accounting;

public class AccountingFilter {

    private String branch;
    private Long id;
    private Long operation_id;
    private Long sets_id;
    private String doc_type;
    private String acc_dt;
    private String acc_ct;
    private String acc_dt_name;
    private String acc_ct_name;
    private String purpose;
    private String purpose_code;
    private String cash_code;
    private String cash_sub_code;
    private int ord;
    private String mfo_ct;
    private String inn_ct;
    
    public AccountingFilter() {
    	super();
    }

	public AccountingFilter(String branch, Long id, Long operation_id,
			Long sets_id, String doc_type, String acc_dt, String acc_ct,
			String acc_dt_name, String acc_ct_name, String purpose,
			String purpose_code, String cash_code, String cash_sub_code,
			int ord, String mfo_ct, String inn_ct) {
		super();
		this.branch = branch;
		this.id = id;
		this.operation_id = operation_id;
		this.sets_id = sets_id;
		this.doc_type = doc_type;
		this.acc_dt = acc_dt;
		this.acc_ct = acc_ct;
		this.acc_dt_name = acc_dt_name;
		this.acc_ct_name = acc_ct_name;
		this.purpose = purpose;
		this.purpose_code = purpose_code;
		this.cash_code = cash_code;
		this.cash_sub_code = cash_sub_code;
		this.ord = ord;
		this.mfo_ct = mfo_ct;
		this.inn_ct = inn_ct;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(Long operation_id) {
		this.operation_id = operation_id;
	}

	public Long getSets_id() {
		return sets_id;
	}

	public void setSets_id(Long sets_id) {
		this.sets_id = sets_id;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getAcc_dt() {
		return acc_dt;
	}

	public void setAcc_dt(String acc_dt) {
		this.acc_dt = acc_dt;
	}

	public String getAcc_ct() {
		return acc_ct;
	}

	public void setAcc_ct(String acc_ct) {
		this.acc_ct = acc_ct;
	}

	public String getAcc_dt_name() {
		return acc_dt_name;
	}

	public void setAcc_dt_name(String acc_dt_name) {
		this.acc_dt_name = acc_dt_name;
	}

	public String getAcc_ct_name() {
		return acc_ct_name;
	}

	public void setAcc_ct_name(String acc_ct_name) {
		this.acc_ct_name = acc_ct_name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose_code() {
		return purpose_code;
	}

	public void setPurpose_code(String purpose_code) {
		this.purpose_code = purpose_code;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
	}

	public String getCash_sub_code() {
		return cash_sub_code;
	}

	public void setCash_sub_code(String cash_sub_code) {
		this.cash_sub_code = cash_sub_code;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public String getMfo_ct() {
		return mfo_ct;
	}

	public void setMfo_ct(String mfo_ct) {
		this.mfo_ct = mfo_ct;
	}

	public String getInn_ct() {
		return inn_ct;
	}

	public void setInn_ct(String inn_ct) {
		this.inn_ct = inn_ct;
	}
    
    

}

