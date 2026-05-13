package com.is.useractionlog;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class UserActionLogFilter implements Serializable {

	static final long serialVersionUID = 1231231232L;

	private String id;
	private String branch;
	private String user_id;
	private String user_name;
	private String ip_address;
	private Date action_date_from;
	private Date action_date_to;
	private String act_type;
	private String entity_type;
	private String entity_id;
	private HashMap<String, String> parameters = new HashMap<String, String>();

	public UserActionLogFilter() {
		super();
	}

	public UserActionLogFilter(String id, String branch, String user_id, String user_name, String ip_address,
			Date action_date_from, Date action_date_to, String act_type, String entity_type, String entity_id) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date_from = action_date_from;
		this.action_date_to = action_date_to;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
	}

	public UserActionLogFilter(String id, String branch, String user_id, String user_name, String ip_address,
			Date action_date_from, Date action_date_to, String act_type, String entity_type, String entity_id,
			HashMap<String, String> parameters) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date_from = action_date_from;
		this.action_date_to = action_date_to;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public Date getAction_date_from() {
		return action_date_from;
	}

	public void setAction_date_from(java.util.Date date) {
		this.action_date_from = date;
	}

	public Date getAction_date_to() {
		return action_date_to;
	}

	public void setAction_date_to(java.util.Date date) {
		this.action_date_to = date;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}

}
