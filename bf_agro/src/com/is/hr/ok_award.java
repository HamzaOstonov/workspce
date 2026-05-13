package com.is.hr;

import java.util.Date;

public class ok_award {
	private int id;
    private String branch;
    private int personal_code;
    private int award_date;
    private String award_info;
    private int emp_code;
    private Date ins_date;
    private int award_date_mm;
    private int award_date_dd;
    private String emp_code_name;

    public ok_award() {

    }

	public ok_award(int id, String branch, int personal_code, int award_date, String award_info, int emp_code,
			Date ins_date, int award_date_mm, int award_date_dd, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.award_date = award_date;
		this.award_info = award_info;
		this.emp_code = emp_code;
		this.ins_date = ins_date;
		this.award_date_mm = award_date_mm;
		this.award_date_dd = award_date_dd;
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

	public int getAward_date() {
		return award_date;
	}

	public void setAward_date(int award_date) {
		this.award_date = award_date;
	}

	public String getAward_info() {
		return award_info;
	}

	public void setAward_info(String award_info) {
		this.award_info = award_info;
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

	public int getAward_date_mm() {
		return award_date_mm;
	}

	public void setAward_date_mm(int award_date_mm) {
		this.award_date_mm = award_date_mm;
	}

	public int getAward_date_dd() {
		return award_date_dd;
	}

	public void setAward_date_dd(int award_date_dd) {
		this.award_date_dd = award_date_dd;
	}

	public String getEmp_code_name() {
		return emp_code_name;
	}

	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}
    
}
