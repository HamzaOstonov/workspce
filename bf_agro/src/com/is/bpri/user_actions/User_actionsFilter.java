package com.is.bpri.user_actions;


public class User_actionsFilter {
	
	private int id;
	private int user_id;
	private String user_name;
	private String action_date;
	private Integer act_type;
	private String act_name;
	private int gange_id;
	private String client_id;
	
	public User_actionsFilter() {
		
	}

	public User_actionsFilter(int id, int user_id, String user_name,
			String action_date, Integer act_type, String act_name, int gange_id,String client_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.action_date = action_date;
		this.act_type = act_type;
		this.act_name = act_name;
		this.gange_id = gange_id;
		this.client_id = client_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAction_date() {
		return action_date;
	}

	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}

	public Integer getAct_type() {
		return act_type;
	}

	public void setAct_type(Integer act_type) {
		this.act_type = act_type;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public int getGange_id() {
		return gange_id;
	}

	public void setGange_id(int gange_id) {
		this.gange_id = gange_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
}
