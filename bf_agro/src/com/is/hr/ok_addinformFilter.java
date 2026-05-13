package com.is.hr;

import java.util.Date;

public class ok_addinformFilter {
	private int id;
    private String branch;
    private int personal_code;
    private Date addinform_date;
    private String character_addinform;
    private int emp_code;
    private Date ins_date;
    private String estimation;
    private int addinform_code;
    private String emp_code_name;

    public ok_addinformFilter() {

    }

	public ok_addinformFilter(int id, String branch, int personal_code, Date addinform_date, String character_addinform,
			int emp_code, Date ins_date, String estimation, int addinform_code, String emp_code_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.personal_code = personal_code;
		this.addinform_date = addinform_date;
		this.character_addinform = character_addinform;
		this.emp_code = emp_code;
		this.ins_date = ins_date;
		this.estimation = estimation;
		this.addinform_code = addinform_code;
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

	public Date getAddinform_date() {
		return addinform_date;
	}

	public void setAddinform_date(Date addinform_date) {
		this.addinform_date = addinform_date;
	}

	public String getCharacter_addinform() {
		return character_addinform;
	}

	public void setCharacter_addinform(String character_addinform) {
		this.character_addinform = character_addinform;
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

	public String getEstimation() {
		return estimation;
	}

	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}

	public int getAddinform_code() {
		return addinform_code;
	}

	public void setAddinform_code(int addinform_code) {
		this.addinform_code = addinform_code;
	}

	public String getEmp_code_name() {
		return emp_code_name;
	}

	public void setEmp_code_name(String emp_code_name) {
		this.emp_code_name = emp_code_name;
	}
    
}
