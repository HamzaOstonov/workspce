package com.is.search_clients.model;

import java.util.Date;


public class UserInput {
	private long id;
	private String branch;
	private String name;
	private String last_name;
	private String first_name;
	private String patronymic;
	private Date date_from;
	private Date date_to;
	private String pass_ser;
	private String pass_num;
	private int emp_id;
	private Date date_ins;
	private int state;
	private String type_document;

	public String getType_document() {
		return type_document;
	}

	public void setType_document(String type_document) {
		this.type_document = type_document;
	}

	public UserInput() {
		super();
	}

	public UserInput(String branch,Long id, Date date_ins, String last_name,
			String first_name, String patronymic) {
		this.branch = branch;
		this.id = id;
		this.date_ins = date_ins;
		this.last_name = last_name;
		this.first_name = first_name;
		this.patronymic = patronymic;
	}

	public UserInput(String name, Date date_from, Date date_to,
			String pass_ser, String pass_num) {
		this.name = name;
		this.date_from = date_from;
		this.date_to = date_to;
		this.pass_ser = pass_ser;
		this.pass_num = pass_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public String getPass_ser() {
		return pass_ser;
	}

	public void setPass_ser(String pass_ser) {
		this.pass_ser = pass_ser;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public Date getDate_ins() {
		return date_ins;
	}

	public void setDate_ins(Date date_ins) {
		this.date_ins = date_ins;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserInput [id=" + id + ", branch=" + branch + ", name=" + name
				+ ", last_name=" + last_name + ", first_name=" + first_name
				+ ", patronymic=" + patronymic + ", date_from=" + date_from
				+ ", date_to=" + date_to + ", pass_ser=" + pass_ser
				+ ", pass_num=" + pass_num + ", emp_id=" + emp_id
				+ ", date_ins=" + date_ins + ", state=" + state + "]";
	}
	
	public Boolean isAllFieldsNull(){
		if (date_from == null && date_to == null && (
				pass_ser == null || pass_ser.isEmpty()) && 
				(pass_num == null || pass_num.isEmpty()))
			return true;
		return false;
	}
}
