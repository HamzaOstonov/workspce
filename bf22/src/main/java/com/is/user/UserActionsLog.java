package com.is.user;

import java.util.Date;

public class UserActionsLog {
    private Long id;
    private String branch;
    private int user_id;
    private String user_name;
    private String ip_address;
    private Date action_date;
    private int act_type;
    private Long entity_type;
    private String entity_id;

    public UserActionsLog() {
    	super();
    }
    
    public UserActionsLog(int user_id, String user_name,
			String ip_address, int act_type,
			Long entity_type, String entity_id, String branch) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
		this.branch = branch;
	}

	public UserActionsLog(Long id, int user_id, String user_name,
			String ip_address, Date action_date, int act_type,
			Long entity_type, String entity_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.action_date = action_date;
		this.act_type = act_type;
		this.entity_type = entity_type;
		this.entity_id = entity_id;
	}
	

	public UserActionsLog(String branch, int user_id, String user_name, String ip_address,
			int act_type) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ip_address = ip_address;
		this.act_type = act_type;
	}
	
	

	public UserActionsLog(Long id, String branch, int user_id,
			String user_name, String ip_address, Date action_date,
			int act_type, Long entity_type, String entity_id) {
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

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(Long entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
    
}
