package com.is.trpay;

import java.util.Date;

public class TrPayDocs {
    private Long id;
    private int pay_id;
    private String branch;
    private Date d_date;
    private String bank_cl;
    private String acc_cl;
    private String name_cl;
    private String bank_co;
    private String acc_co;
    private String name_co;
    private int summa;
    private String purpose;
    private String type_doc;
    private String pdc;
    private int parent_group_id;
    private int parent_id;
    private String cash_code;
    private Double id_transh_purp;
    private String schema_name;
    private int ord;
    private String g_branch;
    private Long g_docid;

    public TrPayDocs() {

    }

	public TrPayDocs(Long id, int pay_id, String branch, Date d_date,
			String bank_cl, String acc_cl, String name_cl, String bank_co,
			String acc_co, String name_co, int summa, String purpose,
			String type_doc, String pdc, int parent_group_id, int parent_id,
			String cash_code, Double id_transh_purp, String schema_name,
			int ord, String g_branch, Long g_docid) {
		super();
		this.id = id;
		this.pay_id = pay_id;
		this.branch = branch;
		this.d_date = d_date;
		this.bank_cl = bank_cl;
		this.acc_cl = acc_cl;
		this.name_cl = name_cl;
		this.bank_co = bank_co;
		this.acc_co = acc_co;
		this.name_co = name_co;
		this.summa = summa;
		this.purpose = purpose;
		this.type_doc = type_doc;
		this.pdc = pdc;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.cash_code = cash_code;
		this.id_transh_purp = id_transh_purp;
		this.schema_name = schema_name;
		this.ord = ord;
		this.g_branch = g_branch;
		this.g_docid = g_docid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPay_id() {
		return pay_id;
	}

	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public int getSumma() {
		return summa;
	}

	public void setSumma(int summa) {
		this.summa = summa;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType_doc() {
		return type_doc;
	}

	public void setType_doc(String type_doc) {
		this.type_doc = type_doc;
	}

	public String getPdc() {
		return pdc;
	}

	public void setPdc(String pdc) {
		this.pdc = pdc;
	}

	public int getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getCash_code() {
		return cash_code;
	}

	public void setCash_code(String cash_code) {
		this.cash_code = cash_code;
	}

	public Double getId_transh_purp() {
		return id_transh_purp;
	}

	public void setId_transh_purp(Double id_transh_purp) {
		this.id_transh_purp = id_transh_purp;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public String getG_branch() {
		return g_branch;
	}

	public void setG_branch(String g_branch) {
		this.g_branch = g_branch;
	}

	public Long getG_docid() {
		return g_docid;
	}

	public void setG_docid(Long g_docid) {
		this.g_docid = g_docid;
	}
    
}
