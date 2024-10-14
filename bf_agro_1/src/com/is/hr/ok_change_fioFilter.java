package com.is.hr;

import java.util.Date;

public class ok_change_fioFilter {
	private int id;
    private String branch;
    private int personal_code;
    private String family;
    private String first_name;
    private String patronymic;
    private Date change_date;
    private String change_motive;
    private int emp_code;
    private Date ins_date;
    private String emp_code_name;

    public ok_change_fioFilter() {

    }

	public ok_change_fioFilter(int id, String branch, int personal_code, String family, String first_name, String patronymic,
			Date change_date, String change_motive, int emp_code, Date ins_date, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.family = family;
		this.first_name = first_name;
		this.patronymic = patronymic;
		this.change_date = change_date;
		this.change_motive = change_motive;
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

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getChange_date() {
		return change_date;
	}

	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

	public String getChange_motive() {
		return change_motive;
	}

	public void setChange_motive(String change_motive) {
		this.change_motive = change_motive;
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
