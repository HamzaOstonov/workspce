package com.is.useractionlog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserActionLog implements Serializable {

    static final long serialVersionUID = 1231231231L;

	private Long id;
	private String branch;
	private int user_id;
	private String user_name;
	private String ip_address;
	private Date action_date;
	private int act_type;
	private int entity_type;
	private String entity_id;
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private List<UserActionsAddinfo> addinfo = new ArrayList<UserActionsAddinfo>();
	private String action_name;
	private String entity_name;
	
    public UserActionLog() {
		super();
    }

	public UserActionLog(Long id, String branch, int user_id, String user_name, String ip_address, Date action_date, int act_type, int entity_type, String entity_id) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
	}

	public UserActionLog(Long id, String branch, int user_id, String user_name, String ip_address, Date action_date, int act_type, int entity_type, String entity_id, HashMap<String, String> parameters) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.parameters = parameters;
	}

	public UserActionLog(Long id, String branch, int user_id, String user_name, String ip_address, Date action_date, int act_type, int entity_type, String entity_id, HashMap<String, String> parameters, String action_name, String entity_name) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.parameters = parameters;
		this.action_name = action_name;
		this.entity_name = entity_name;
	}
	
	public UserActionLog(String branch, int user_id, String user_name, String ip_address, Date action_date, int act_type, int entity_type, String entity_id, HashMap<String, String> parameters) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.parameters = parameters;
	}
	
	public UserActionLog(String branch, int user_id, String user_name, String ip_address, int act_type, int entity_type, String entity_id, HashMap<String, String> parameters) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = new Date();
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.parameters = parameters;
	}
	
	public UserActionLog(String branch, int user_id, String user_name, String ip_address, int act_type, int entity_type, String entity_id) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = new Date();
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
	}

	public UserActionLog(Long id, String branch, int user_id, String user_name, String ip_address, Date action_date, int act_type, int entity_type, String entity_id, List<UserActionsAddinfo> addinfo) {
		super();
		this.id = id;
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.addinfo = addinfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public Date getAction_date() {
		return action_date;
	}

	public void setAction_date(Date action_date) {
		this.action_date = action_date;
	}

	public int getAct_type() {
		return act_type;
	}

	public void setAct_type(int act_type) {
		this.act_type = act_type;
	}

	public int getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(int entity_type) {
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

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public List<UserActionsAddinfo> getAddinfo() {
		return addinfo;
	}

	public void setAddinfo(List<UserActionsAddinfo> addinfo) {
		this.addinfo = addinfo;
	}

}
