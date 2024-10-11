package com.is.bpri.asoki;

import java.util.Date;

public class Asoki {

	private Long id;
	private Long request_id;
	private Long niki_id;
	private String branch;
	private Date request_date;
	private String state_name;
	
	public Asoki() {

	}

	public Asoki(Long id, Long request_id, Long niki_id, String branch,
			Date request_date, String state_name) {
		this.id = id;
		this.request_id = request_id;
		this.niki_id = niki_id;
		this.branch = branch;
		this.request_date = request_date;
		this.state_name = state_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public Long getNiki_id() {
		return niki_id;
	}

	public void setNiki_id(Long niki_id) {
		this.niki_id = niki_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getRequest_date() {
		return request_date;
	}

	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	
}
