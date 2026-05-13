package com.is.clients.models;

import java.util.Date;

public class History {
	private Date date_correct;
	private String name;
	private String full_name;
	private Date date_time;
	
	public History() {
		// TODO Auto-generated constructor stub
	}
	
	public History(Date date_correct, String name, String full_name,
			Date date_time) {
		super();
		this.date_correct = date_correct;
		this.name = name;
		this.full_name = full_name;
		this.date_time = date_time;
	}

	public Date getDate_correct() {
		return date_correct;
	}

	public void setDate_correct(Date date_correct) {
		this.date_correct = date_correct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
	
}
