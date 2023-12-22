package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_forms {
	
	private String branch;
    private Long id;
    private Date d_date;
    private Date d_confirm;
    private Integer state;
    private String client;
    private Long type_id;
    private String kred_id;
    private Integer emp_count;
    private String klass_id;
    private String sub_name;
    private String sub_addr;
    private Integer use_id;
    private String phone_dir;
    private String phone_chief;
    private Double stage_dir;
    private Double stage_chief;
    private Integer acc_state;
    private Integer is_sec;
    private Long sec_amount;
    private Integer is_ld;
    private Integer is_building;
    private Integer status_build_up_prc;
    private String doc_name_st;
    private String doc_num_st;
    private Date doc_date_st;
    private Date date_start;
    private String transh;
    private Integer emp_id;
    private Integer ld_type;
    private String head_of_bank;
    private Integer state_sap;
	
    public Ld_forms() {
		
	}

	public Ld_forms(String branch, Long id, Date d_date, Date d_confirm,
			Integer state, String client, Long type_id, String kred_id,
			Integer emp_count, String klass_id, String sub_name,
			String sub_addr, Integer use_id, String phone_dir,
			String phone_chief, Double stage_dir, Double stage_chief,
			Integer acc_state, Integer is_sec, Long sec_amount, Integer is_ld,
			Integer is_building, Integer status_build_up_prc,
			String doc_name_st, String doc_num_st, Date doc_date_st,
			Date date_start, String transh, Integer emp_id, Integer ld_type,
			String head_of_bank, Integer state_sap) {
		super();
		this.branch = branch;
		this.id = id;
		this.d_date = d_date;
		this.d_confirm = d_confirm;
		this.state = state;
		this.client = client;
		this.type_id = type_id;
		this.kred_id = kred_id;
		this.emp_count = emp_count;
		this.klass_id = klass_id;
		this.sub_name = sub_name;
		this.sub_addr = sub_addr;
		this.use_id = use_id;
		this.phone_dir = phone_dir;
		this.phone_chief = phone_chief;
		this.stage_dir = stage_dir;
		this.stage_chief = stage_chief;
		this.acc_state = acc_state;
		this.is_sec = is_sec;
		this.sec_amount = sec_amount;
		this.is_ld = is_ld;
		this.is_building = is_building;
		this.status_build_up_prc = status_build_up_prc;
		this.doc_name_st = doc_name_st;
		this.doc_num_st = doc_num_st;
		this.doc_date_st = doc_date_st;
		this.date_start = date_start;
		this.transh = transh;
		this.emp_id = emp_id;
		this.ld_type = ld_type;
		this.head_of_bank = head_of_bank;
		this.state_sap = state_sap;
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

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}

	public Date getD_confirm() {
		return d_confirm;
	}

	public void setD_confirm(Date d_confirm) {
		this.d_confirm = d_confirm;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getKred_id() {
		return kred_id;
	}

	public void setKred_id(String kred_id) {
		this.kred_id = kred_id;
	}

	public Integer getEmp_count() {
		return emp_count;
	}

	public void setEmp_count(Integer emp_count) {
		this.emp_count = emp_count;
	}

	public String getKlass_id() {
		return klass_id;
	}

	public void setKlass_id(String klass_id) {
		this.klass_id = klass_id;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getSub_addr() {
		return sub_addr;
	}

	public void setSub_addr(String sub_addr) {
		this.sub_addr = sub_addr;
	}

	public Integer getUse_id() {
		return use_id;
	}

	public void setUse_id(Integer use_id) {
		this.use_id = use_id;
	}

	public String getPhone_dir() {
		return phone_dir;
	}

	public void setPhone_dir(String phone_dir) {
		this.phone_dir = phone_dir;
	}

	public String getPhone_chief() {
		return phone_chief;
	}

	public void setPhone_chief(String phone_chief) {
		this.phone_chief = phone_chief;
	}

	public Double getStage_dir() {
		return stage_dir;
	}

	public void setStage_dir(Double stage_dir) {
		this.stage_dir = stage_dir;
	}

	public Double getStage_chief() {
		return stage_chief;
	}

	public void setStage_chief(Double stage_chief) {
		this.stage_chief = stage_chief;
	}

	public Integer getAcc_state() {
		return acc_state;
	}

	public void setAcc_state(Integer acc_state) {
		this.acc_state = acc_state;
	}

	public Integer getIs_sec() {
		return is_sec;
	}

	public void setIs_sec(Integer is_sec) {
		this.is_sec = is_sec;
	}

	public Long getSec_amount() {
		return sec_amount;
	}

	public void setSec_amount(Long sec_amount) {
		this.sec_amount = sec_amount;
	}

	public Integer getIs_ld() {
		return is_ld;
	}

	public void setIs_ld(Integer is_ld) {
		this.is_ld = is_ld;
	}

	public Integer getIs_building() {
		return is_building;
	}

	public void setIs_building(Integer is_building) {
		this.is_building = is_building;
	}

	public Integer getStatus_build_up_prc() {
		return status_build_up_prc;
	}

	public void setStatus_build_up_prc(Integer status_build_up_prc) {
		this.status_build_up_prc = status_build_up_prc;
	}

	public String getDoc_name_st() {
		return doc_name_st;
	}

	public void setDoc_name_st(String doc_name_st) {
		this.doc_name_st = doc_name_st;
	}

	public String getDoc_num_st() {
		return doc_num_st;
	}

	public void setDoc_num_st(String doc_num_st) {
		this.doc_num_st = doc_num_st;
	}

	public Date getDoc_date_st() {
		return doc_date_st;
	}

	public void setDoc_date_st(Date doc_date_st) {
		this.doc_date_st = doc_date_st;
	}

	public Date getDate_start() {
		return date_start;
	}

	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	public String getTransh() {
		return transh;
	}

	public void setTransh(String transh) {
		this.transh = transh;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Integer getLd_type() {
		return ld_type;
	}

	public void setLd_type(Integer ld_type) {
		this.ld_type = ld_type;
	}

	public String getHead_of_bank() {
		return head_of_bank;
	}

	public void setHead_of_bank(String head_of_bank) {
		this.head_of_bank = head_of_bank;
	}

	public Integer getState_sap() {
		return state_sap;
	}

	public void setState_sap(Integer state_sap) {
		this.state_sap = state_sap;
	}
    
}
