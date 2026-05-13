package com.is.client_acc.fileProcessing;

import java.util.Date;

public class ClientaccFilter {
	  String id;
	  String branch;
	  String bal_code;
	  int days;
	  String emp_id;
	  String num_doc;
	  Date date_doc;
	  String purpose;
	  Date bank_date;
	  
	public String getId() {
		return id;
	}
	public String getBranch() {
		return branch;
	}
	public String getBal_code() {
		return bal_code;
	}
	public int getDays() {
		return days;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public String getNum_doc() {
		return num_doc;
	}
	public Date getDate_doc() {
		return date_doc;
	}
	public String getPurpose() {
		return purpose;
	}
	public Date getBank_date() {
		return bank_date;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setBal_code(String bal_code) {
		this.bal_code = bal_code;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}
	public void setDate_doc(Date date_doc) {
		this.date_doc = date_doc;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setBank_date(Date bank_date) {
		this.bank_date = bank_date;
	}
	
}
