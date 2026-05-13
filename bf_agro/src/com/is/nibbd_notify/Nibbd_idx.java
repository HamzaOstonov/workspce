package com.is.nibbd_notify;

import java.util.Date;

public class Nibbd_idx {
	private Long id;
	private String request_id;
	private String inn;
	private String name;
	private String address;
	private String bank;
	private String branch;
	private String account;
	private Long rest_amount;
	private Date opened;
	private Date closed;
	private Long debt_info;
	private String id_client;
	private Date last_oper_date;
	private Long emp_id;
	private Date sys_date;
	private Date cur_date;
	private Long file_id;
	private String state;
	private Long ext_record_id;
	private boolean is_sent_in_file;
	private boolean is_sent_inkassa;

	public Nibbd_idx() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getRest_amount() {
		return rest_amount;
	}

	public void setRest_amount(Long rest_amount) {
		this.rest_amount = rest_amount;
	}

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {
		this.opened = opened;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public Long getDebt_info() {
		return debt_info;
	}

	public void setDebt_info(Long debt_info) {
		this.debt_info = debt_info;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public Date getLast_oper_date() {
		return last_oper_date;
	}

	public void setLast_oper_date(Date last_oper_date) {
		this.last_oper_date = last_oper_date;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public Date getSys_date() {
		return sys_date;
	}

	public void setSys_date(Date sys_date) {
		this.sys_date = sys_date;
	}

	public Date getCur_date() {
		return cur_date;
	}

	public void setCur_date(Date cur_date) {
		this.cur_date = cur_date;
	}

	public Long getFile_id() {
		return file_id;
	}

	public void setFile_id(Long file_id) {
		this.file_id = file_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getExt_record_id() {
		return ext_record_id;
	}

	public void setExt_record_id(Long ext_record_id) {
		this.ext_record_id = ext_record_id;
	}

	public boolean isIs_sent_in_file() {
		return is_sent_in_file;
	}

	public void setIs_sent_in_file(boolean is_sent_in_file) {
		this.is_sent_in_file = is_sent_in_file;
	}

	public boolean isIs_sent_inkassa() {
		return is_sent_inkassa;
	}

	public void setIs_sent_inkassa(boolean is_sent_inkassa) {
		this.is_sent_inkassa = is_sent_inkassa;
	}

}
