package com.is.hr;

import java.util.Date;

public class ok_academicFilter {
	private int id;
	private String branch;
	private int personal_code;
	private int academic_code;
	private int academic_date;
	private int emp_code;
	private Date ins_date;
	private String emp_code_name;

	public ok_academicFilter() {

	}

	public ok_academicFilter(int id, String branch, int personal_code, int academic_code, int academic_date,
			int emp_code, Date ins_date, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.academic_code = academic_code;
		this.academic_date = academic_date;
		this.emp_code = emp_code;
		this.ins_date = ins_date;
		this.emp_code_name = emp_code_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getPersonal_code() {
		return personal_code;
	}

	public void setPersonal_code(int personal_code) {
		this.personal_code = personal_code;
	}

	public int getAcademic_code() {
		return academic_code;
	}

	public void setAcademic_code(int academic_code) {
		this.academic_code = academic_code;
	}

	public int getAcademic_date() {
		return academic_date;
	}

	public void setAcademic_date(int academic_date) {
		this.academic_date = academic_date;
	}

	public int getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(int emp_code) {
		this.emp_code = emp_code;
	}

	public Date getIns_date() {
		return ins_date;
	}

	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}

	public String getEmp_code_name() {
		return emp_code_name;
	}

	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}

}
