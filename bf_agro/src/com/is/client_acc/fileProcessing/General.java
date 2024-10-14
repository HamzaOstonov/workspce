package com.is.client_acc.fileProcessing;

public class General {
	static final long serialVersionUID = 103844514947365244L;


    private String id;
    private String branch;
    private String doc_num;
    private String d_date;
    private String bank_cl;
    private String acc_cl;
    private String name_cl;
    private String bank_co;
    private String acc_co;
    private String name_co;
    private String purpose;
    private String summa;
    private String currency;
    private String type_doc;
    private String s_deal_id;
    private String v_date;
    private String pdc;
    private String cash_code;
    private String state;
    private String parent_group_id;
    private String parent_id;
    private String child_id;
    private String kod_err;
    private String file_name;
    private String err_general;
    private String emp_id;
    private String id_transh;
    private String id_transh_purp;
    private String val_date;
    private String batch_type_id;

    public General() {

    }

    public General( String id, String branch, String doc_num, String d_date, String bank_cl, String acc_cl, String name_cl, String bank_co, String acc_co, String name_co, String purpose, String summa, String currency, String type_doc, String s_deal_id, String v_date, String pdc, String cash_code, String state, String parent_group_id, String parent_id, String child_id, String kod_err, String file_name, String err_general, String emp_id, String id_transh, String id_transh_purp, String val_date, String batch_type_id) {

                this.id = id;
                this.branch = branch;
                this.doc_num = doc_num;
                this.d_date = d_date;
                this.bank_cl = bank_cl;
                this.acc_cl = acc_cl;
                this.name_cl = name_cl;
                this.bank_co = bank_co;
                this.acc_co = acc_co;
                this.name_co = name_co;
                this.purpose = purpose;
                this.summa = summa;
                this.currency = currency;
                this.type_doc = type_doc;
                this.s_deal_id = s_deal_id;
                this.v_date = v_date;
                this.pdc = pdc;
                this.cash_code = cash_code;
                this.state = state;
                this.parent_group_id = parent_group_id;
                this.parent_id = parent_id;
                this.child_id = child_id;
                this.kod_err = kod_err;
                this.file_name = file_name;
                this.err_general = err_general;
                this.emp_id = emp_id;
                this.id_transh = id_transh;
                this.id_transh_purp = id_transh_purp;
                this.val_date = val_date;
                this.batch_type_id = batch_type_id;
        }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String d_date) {
		this.d_date = d_date;
	}

	public String getBank_cl() {
		return bank_cl;
	}

	public void setBank_cl(String bank_cl) {
		this.bank_cl = bank_cl;
	}

	public String getAcc_cl() {
		return acc_cl;
	}

	public void setAcc_cl(String acc_cl) {
		this.acc_cl = acc_cl;
	}

	public String getName_cl() {
		return name_cl;
	}

	public void setName_cl(String name_cl) {
		this.name_cl = name_cl;
	}

	public String getBank_co() {
		return bank_co;
	}

	public void setBank_co(String bank_co) {
		this.bank_co = bank_co;
	}

	public String getAcc_co() {
		return acc_co;
	}

	public void setAcc_co(String acc_co) {
		this.acc_co = acc_co;
	}

	public String getName_co() {
		return name_co;
	}

	public void setName_co(String name_co) {
		this.name_co = name_co;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSumma() {
		return summa;
	}

	public void setSumma(String summa) {
		this.summa = summa;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType_doc() {
		return type_doc;
	}

	public void setType_doc(String type_doc) {
		this.type_doc = type_doc;
	}

	public String getS_deal_id() {
		return s_deal_id;
	}

	public void setS_deal_id(String s_deal_id) {
		this.s_deal_id = s_deal_id;
	}

	public String getV_date() {
		return v_date;
	}

	public void setV_date(String v_date) {
		this.v_date = v_date;
	}

	public String getPdc() {
		return pdc;
	}

	public void setPdc(String pdc) {
		this.pdc = pdc;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(String parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getChild_id() {
		return child_id;
	}

	public void setChild_id(String child_id) {
		this.child_id = child_id;
	}

	public String getKod_err() {
		return kod_err;
	}

	public void setKod_err(String kod_err) {
		this.kod_err = kod_err;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getErr_general() {
		return err_general;
	}

	public void setErr_general(String err_general) {
		this.err_general = err_general;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getId_transh() {
		return id_transh;
	}

	public void setId_transh(String id_transh) {
		this.id_transh = id_transh;
	}

	public String getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(String id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public String getVal_date() {
		return val_date;
	}

	public void setVal_date(String val_date) {
		this.val_date = val_date;
	}

	public String getBatch_type_id() {
		return batch_type_id;
	}

	public void setBatch_type_id(String batch_type_id) {
		this.batch_type_id = batch_type_id;
	}

}
