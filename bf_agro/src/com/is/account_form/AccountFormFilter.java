package com.is.account_form;

//import java.time.Date;
import java.util.Date;

public class AccountFormFilter {
	private String firstname;
	private String lastname;
	private String middlename;
	private Date birthdate;
	private String gender;
	private String bankname;
	private String branch;
	private String position;
	public AccountFormFilter(String firstname, String lastname, String middlename, Date birthdate, String gender,
			String bankname, String branch, String position) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.middlename = middlename;
		this.birthdate = birthdate;
		this.gender = gender;
		this.bankname = bankname;
		this.branch = branch;
		this.position = position;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {	
		this.middlename = middlename;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	

	
}