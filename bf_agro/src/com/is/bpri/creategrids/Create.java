package com.is.bpri.creategrids;

import java.util.Date;

public class Create {
	
	private Long id;
	private String name;
	private Long tid;
	private String tname;
	private String process;
	private String process_name;
	private String uname;
	private String branch;
	private Date date_bank;
	private Long state;
	private String state_name;
	public Create() {		
	}
	
	public Create(Long id, String name, Long tid, String tname, String process,
			String process_name, String uname, String branch, Date date_bank, Long state, String state_name) {
		super();
		this.id = id;
		this.name = name;
		this.tid = tid;
		this.tname = tname;
		this.process = process;
		this.process_name = process_name;
		this.uname = uname;
		this.branch = branch;
		this.date_bank = date_bank;
		this.state = state;
		this.state_name = state_name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getDate_bank() {
		return date_bank;
	}
	public void setDate_bank(Date date_bank) {
		this.date_bank = date_bank;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
}
