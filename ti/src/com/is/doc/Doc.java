package com.is.doc;

import java.util.Date;

public class Doc {
    private long id;
    private String branch;
    private String doc_num;
    private Date d_date;
    private String bank_cl;
    private String acc_cl;
    private String name_cl;
    private String bank_co;
    private String acc_co;
    private String name_co;
    private String purpose;
    private long summa;
    private String currency;
    private String type_doc;
    private int s_deal_id;
    private Date v_date;
    private String pdc;
    private String cash_code;
    private int state;
    private int parent_group_id;
    private long parent_id;
    private long child_id;
    private int kod_err;
    private String file_name;
    private int err_general;
    private int emp_id;
    private int id_transh;
    private int id_transh_purp;
    private Date val_date;

    public Doc() {
    	super();
    }

	public Doc(long id, String branch, String doc_num, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, String purpose, long summa,
			String currency, String type_doc, int s_deal_id, Date v_date,
			String pdc, String cash_code, int state, int parent_group_id,
			long parent_id, long child_id, int kod_err, String file_name,
			int err_general, int emp_id, int id_transh, int id_transh_purp,
			Date val_date) {
		super();
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
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
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

	public long getSumma() {
		return summa;
	}

	public void setSumma(long summa) {
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

	public int getS_deal_id() {
		return s_deal_id;
	}

	public void setS_deal_id(int s_deal_id) {
		this.s_deal_id = s_deal_id;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public long getChild_id() {
		return child_id;
	}

	public void setChild_id(long child_id) {
		this.child_id = child_id;
	}

	public int getKod_err() {
		return kod_err;
	}

	public void setKod_err(int kod_err) {
		this.kod_err = kod_err;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getErr_general() {
		return err_general;
	}

	public void setErr_general(int err_general) {
		this.err_general = err_general;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getId_transh() {
		return id_transh;
	}

	public void setId_transh(int id_transh) {
		this.id_transh = id_transh;
	}

	public int getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(int id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public Date getVal_date() {
		return val_date;
	}

	public void setVal_date(Date val_date) {
		this.val_date = val_date;
	}
    
	
    
    
}
