package com.is.creditapp;

import java.util.Date;

public class CreditAppFilter {
	
	private long id;
    private String branch;
    private String id_client;
    private String reqnum;
    private String reqtype;
    private String branch_id;
    private String kred_id;
    private String shifr_id;
    private String currency;
    private Long amount;
    private Date end_date;
    private String dbinn;
    private String dbbranch;
    private String dbaddress;
    private Date v_date;
    private String resolve_org;
    private String is_letter;
    private String sign_client;
    private String rezkl;
    private String client_type;
    private String inn_passport;
    private String fac_resident;
    private String fac_j_code_organ_direct;
    private String fac_class_id;
    private String inn_org;
    private String name_org;
    private String nwp;
    private String qw;
    private String etype;
    private String type_zm;
    private String bank_inps;
    private String code;
    
	public CreditAppFilter() {
		
	}

	public CreditAppFilter(long id, String branch, String id_client, String reqnum,
			String reqtype, String branch_id, String kred_id, String shifr_id,
			String currency, Long amount, Date end_date, String dbinn,
			String dbbranch, String dbaddress, Date v_date, String resolve_org,
			String is_letter, String sign_client, String rezkl,
			String client_type, String inn_passport, String fac_resident,
			String fac_j_code_organ_direct, String fac_class_id,
			String inn_org, String name_org, String nwp, String qw,
			String etype, String type_zm, String bank_inps,String code) {
		super();
		this.id = id;
		this.branch = branch;
		this.id_client = id_client;
		this.reqnum = reqnum;
		this.reqtype = reqtype;
		this.branch_id = branch_id;
		this.kred_id = kred_id;
		this.shifr_id = shifr_id;
		this.currency = currency;
		this.amount = amount;
		this.end_date = end_date;
		this.dbinn = dbinn;
		this.dbbranch = dbbranch;
		this.dbaddress = dbaddress;
		this.v_date = v_date;
		this.resolve_org = resolve_org;
		this.is_letter = is_letter;
		this.sign_client = sign_client;
		this.rezkl = rezkl;
		this.client_type = client_type;
		this.inn_passport = inn_passport;
		this.fac_resident = fac_resident;
		this.fac_j_code_organ_direct = fac_j_code_organ_direct;
		this.fac_class_id = fac_class_id;
		this.inn_org = inn_org;
		this.name_org = name_org;
		this.nwp = nwp;
		this.qw = qw;
		this.etype = etype;
		this.type_zm = type_zm;
		this.bank_inps = bank_inps;
		this.code = code;
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

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getReqnum() {
		return reqnum;
	}

	public void setReqnum(String reqnum) {
		this.reqnum = reqnum;
	}

	public String getReqtype() {
		return reqtype;
	}

	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getKred_id() {
		return kred_id;
	}

	public void setKred_id(String kred_id) {
		this.kred_id = kred_id;
	}

	public String getShifr_id() {
		return shifr_id;
	}

	public void setShifr_id(String shifr_id) {
		this.shifr_id = shifr_id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getDbinn() {
		return dbinn;
	}

	public void setDbinn(String dbinn) {
		this.dbinn = dbinn;
	}

	public String getDbbranch() {
		return dbbranch;
	}

	public void setDbbranch(String dbbranch) {
		this.dbbranch = dbbranch;
	}

	public String getDbaddress() {
		return dbaddress;
	}

	public void setDbaddress(String dbaddress) {
		this.dbaddress = dbaddress;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getResolve_org() {
		return resolve_org;
	}

	public void setResolve_org(String resolve_org) {
		this.resolve_org = resolve_org;
	}

	public String getIs_letter() {
		return is_letter;
	}

	public void setIs_letter(String is_letter) {
		this.is_letter = is_letter;
	}

	public String getSign_client() {
		return sign_client;
	}

	public void setSign_client(String sign_client) {
		this.sign_client = sign_client;
	}

	public String getRezkl() {
		return rezkl;
	}

	public void setRezkl(String rezkl) {
		this.rezkl = rezkl;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getInn_passport() {
		return inn_passport;
	}

	public void setInn_passport(String inn_passport) {
		this.inn_passport = inn_passport;
	}

	public String getFac_resident() {
		return fac_resident;
	}

	public void setFac_resident(String fac_resident) {
		this.fac_resident = fac_resident;
	}

	public String getFac_j_code_organ_direct() {
		return fac_j_code_organ_direct;
	}

	public void setFac_j_code_organ_direct(String fac_j_code_organ_direct) {
		this.fac_j_code_organ_direct = fac_j_code_organ_direct;
	}

	public String getFac_class_id() {
		return fac_class_id;
	}

	public void setFac_class_id(String fac_class_id) {
		this.fac_class_id = fac_class_id;
	}

	public String getInn_org() {
		return inn_org;
	}

	public void setInn_org(String inn_org) {
		this.inn_org = inn_org;
	}

	public String getName_org() {
		return name_org;
	}

	public void setName_org(String name_org) {
		this.name_org = name_org;
	}

	public String getNwp() {
		return nwp;
	}

	public void setNwp(String nwp) {
		this.nwp = nwp;
	}

	public String getQw() {
		return qw;
	}

	public void setQw(String qw) {
		this.qw = qw;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getType_zm() {
		return type_zm;
	}

	public void setType_zm(String type_zm) {
		this.type_zm = type_zm;
	}

	public String getBank_inps() {
		return bank_inps;
	}

	public void setBank_inps(String bank_inps) {
		this.bank_inps = bank_inps;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
