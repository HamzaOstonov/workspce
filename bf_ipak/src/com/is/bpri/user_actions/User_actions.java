package com.is.bpri.user_actions;

import java.util.Date;

public class User_actions {
	
	private Integer id;
	private Integer user_id;
	private String user_name;
	private Date action_date;
	private Integer act_type;
	private String act_name;
	private Integer gange_id;
	private String client_id;
	
	public User_actions() {
		
	}

	public User_actions(Integer id, Integer user_id, String user_name,
			Date action_date, Integer act_type, Integer gange_id,String act_name,String client_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.action_date = action_date;
		this.act_type = act_type;
		this.gange_id = gange_id;
		this.act_name = act_name;
		this.client_id = client_id;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getAction_date() {
		return action_date;
	}

	public void setAction_date(Date action_date) {
		this.action_date = action_date;
	}

	public Integer getAct_type() {
		return act_type;
	}

	public void setAct_type(Integer act_type) {
		this.act_type = act_type;
	}

	public Integer getGange_id() {
		return gange_id;
	}

	public void setGange_id(Integer gange_id) {
		this.gange_id = gange_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
}
