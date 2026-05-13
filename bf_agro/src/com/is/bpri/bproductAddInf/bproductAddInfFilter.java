package com.is.bpri.bproductAddInf;

import java.util.Date;

public class bproductAddInfFilter{
	
	private int bpr_id;
    private Date d_date;
    private Date d_confirm;
    private String state;
    private String client;
    private Long type_id;
    private Long emp_count;
    private String sub_name;
    private String sub_addr;
    private Long use_id;
    private String phone_dir;
    private String phone_chief;
    private Double stage_dir;
    private Double stage_chief;
    private int acc_state;
    private int is_sec;
    private Long sec_amount;
    private int is_ld;
    private int is_building;
    private int status_build_up_prc;
    private String doc_name_st;
    private String doc_num_st;
    private Date doc_date_st;
    private Date date_start;
    private String transh;
    private Long emp_id;
    private String head_of_bank;
    private String kred_id;
    private String klass_id;
    private int ld_type;


    public bproductAddInfFilter() {

    }

	public bproductAddInfFilter(int bpr_id,Date d_date, Date d_confirm,String state,String client, Long type_id,
	      Long emp_count,String sub_name,String sub_addr,Long use_id,String phone_dir,String phone_chief,
	      Double stage_dir,Double stage_chief,int acc_state, int is_sec,Long sec_amount,int is_ld,
	      int is_building, int status_build_up_prc,String doc_name_st,String doc_num_st, Date doc_date_st,
	      Date date_start,String transh, Long emp_id,String head_of_bank,String kred_id,String klass_id,
	      int ld_type) {
		super();
		this.bpr_id=bpr_id;
		this.d_date = d_date;
		this.d_confirm = d_confirm;
		this.state = state;
		this.client = client;
		this.type_id = type_id;
		this.emp_count = emp_count;
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
		this.head_of_bank = head_of_bank;		
		this.kred_id = kred_id;
		this.klass_id = klass_id;		
		this.ld_type = ld_type;		
	}

	
	public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
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

	public Long getEmp_count() {
		return emp_count;
	}

	public void setEmp_count(Long emp_count) {
		this.emp_count = emp_count;
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

	public Long getUse_id() {
		return use_id;
	}

	public void setUse_id(Long use_id) {
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

	public int getAcc_state() {
		return acc_state;
	}

	public void setAcc_state(int acc_state) {
		this.acc_state = acc_state;
	}

	public int getIs_sec() {
		return is_sec;
	}

	public void setIs_sec(int is_sec) {
		this.is_sec = is_sec;
	}

	public Long getSec_amount() {
		return sec_amount;
	}

	public void setSec_amount(Long sec_amount) {
		this.sec_amount = sec_amount;
	}

	public int getIs_ld() {
		return is_ld;
	}

	public void setIs_ld(int is_ld) {
		this.is_ld = is_ld;
	}

	public int getIs_building() {
		return is_building;
	}

	public void setIs_building(int is_building) {
		this.is_building = is_building;
	}

	public int getStatus_build_up_prc() {
		return status_build_up_prc;
	}

	public void setStatus_build_up_prc(int status_build_up_prc) {
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

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getHead_of_bank() {
		return head_of_bank;
	}

	public void setHead_of_bank(String head_of_bank) {
		this.head_of_bank = head_of_bank;
	}

	
	public String getKred_id() {
		return kred_id;
	}

	public void setKred_id(String kred_id) {
		this.kred_id = kred_id;
	}

	public String getKlass_id() {
		return klass_id;
	}

	public void setKlass_id(String klass_id) {
		this.klass_id = klass_id;
	}

	

	public int getLd_type() {
		return ld_type;
	}

	public void setLd_type(int ld_type) {
		this.ld_type = ld_type;
	}

	
    
}
