package com.is.trtemplate;

public class TrTemplate {
    private int id;
    private int operation_id;
    private int acc_dt;
    private int acc_ct;
    private String currency;
    private String doc_type;
    private String cash_code;
    private String purpose;
    private String purpose_code;
    private int ord;
    private int id_transh_purp;
    private int pay_type;
    private int trans_type;
    private double perc_for_tr;
    private int rounding_type;

    public TrTemplate() {
    	super();
    }

	public TrTemplate(int id, int operation_id, int acc_dt, int acc_ct,
			String currency, String doc_type, String cash_code, String purpose,
			String purpose_code, int ord, int id_transh_purp, int pay_type, 
			int trans_type, double perc_for_tr, int rounding_type) {
		super();
		this.id = id;
		this.operation_id = operation_id;
		this.acc_dt = acc_dt;
		this.acc_ct = acc_ct;
		this.currency = currency;
		this.doc_type = doc_type;
		this.cash_code = cash_code;
		this.purpose = purpose;
		this.purpose_code = purpose_code;
		this.ord = ord;
		this.id_transh_purp = id_transh_purp;
		this.pay_type = pay_type;
		this.trans_type = trans_type;
		this.perc_for_tr = perc_for_tr;
		this.rounding_type = rounding_type;
	}
	
	

	public double getPerc_for_tr() {
		return perc_for_tr;
	}

	public void setPerc_for_tr(double perc_for_tr) {
		this.perc_for_tr = perc_for_tr;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public int getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(int trans_type) {
		this.trans_type = trans_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperation_id() {
		return operation_id;
	}

	public void setOperation_id(int operation_id) {
		this.operation_id = operation_id;
	}

	public int getAcc_dt() {
		return acc_dt;
	}

	public void setAcc_dt(int acc_dt) {
		this.acc_dt = acc_dt;
	}

	public int getAcc_ct() {
		return acc_ct;
	}

	public void setAcc_ct(int acc_ct) {
		this.acc_ct = acc_ct;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
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

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public int getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(int id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public int getRounding_type()
	{
		return rounding_type;
	}

	public void setRounding_type(int rounding_type)
	{
		this.rounding_type = rounding_type;
	}
    
}
