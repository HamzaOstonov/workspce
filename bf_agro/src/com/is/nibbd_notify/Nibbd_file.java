package com.is.nibbd_notify;

import java.util.Date;

public class Nibbd_file
{
	private Long id;
	private String branch;
	private Date cur_date;
	private String file_name;
	private String file_name_arc;
	private String emp_id;
	private String state;
	private String reys_no;
	
	public Nibbd_file()
	{
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getCur_date() {
		return cur_date;
	}

	public void setCur_date(Date cur_date) {
		this.cur_date = cur_date;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_name_arc() {
		return file_name_arc;
	}

	public void setFile_name_arc(String file_name_arc) {
		this.file_name_arc = file_name_arc;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setReys_no(String reys_no) {
		this.reys_no = reys_no;
	}

	public String getReys_no() {
		return reys_no;
	}
	
}
