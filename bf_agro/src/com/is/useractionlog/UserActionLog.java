package com.is.useractionlog;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class UserActionLog implements Serializable {

	public class PagingListModel {

	}

	static final long serialVersionUID = 1231231231L;

	private String id;
	private String branch;
	private String user_id;
	private String user_name;
	private String ip_address;
	private java.sql.Date action_date;
	private String act_type;
	private String entity_type;
	private String entity_id;
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private List<UserActionsAddinfo> addinfo = new ArrayList<UserActionsAddinfo>();
	private String action_name;
	private String entity_name;
	private String resultValue;
	private String type_log;
	private String log_text;
	private String group_id;
	private String user_mac;
	private String action;
	private String userhost;
	private String userport;
	private String appip;
	private String user_ip;
	private String clientver;
	private String username;
	private String userip;
	private String usermac;
	private java.sql.Date resultValueDate;
	private java.sql.Date v_date;
	private java.sql.Date date_time;
	private java.sql.Date actiondate;
	private String rep_deal_id;
	private String rep_id;
	private String par_name;
	private String par_value;
	private String comp_name;

	//bf_user_actions_log
	public UserActionLog(String id, String branch, String user_id, String user_name, String ip_address,
			Date action_date, String act_type, String entity_type, String entity_id) {
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
	
	//general_log
	public UserActionLog(String branch,	String user_id, String user_name, String user_mac, String user_ip, 
			String type_log, String log_text, Date date_time, Date v_date, String group_id) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_mac = user_mac;
		this.user_ip = user_ip;
		this.type_log = type_log;
		this.log_text = log_text;
		this.v_date = v_date;
		this.date_time = date_time;
		this.group_id = group_id;
	}
	
	//log_protocol
	public UserActionLog(String branch, String username, String action, Date actiondate, String userip,  
			String usermac,	String userhost, String userport, String appip, String clientver) {
		super();
		this.branch = branch;
		this.username = username;
		this.action = action;
		this.actiondate = actiondate;
		this.userip = userip;
		this.usermac = usermac;
		this.userhost = userhost;
		this.userport = userport;
		this.appip = appip;
		this.clientver = clientver;
	}
	
	//report_history
	public UserActionLog(String id, String rep_deal_id, String rep_id, String par_name, String par_value, 
			String user_id, String comp_name, Date v_date, Date date_time) {
		super();
		this.id = id;
		this.rep_deal_id = rep_deal_id;
		this.rep_id = rep_id;
		this.par_name = par_name;
		this.par_value = par_value;
		this.user_id = user_id;
		this.comp_name = comp_name;
		this.v_date = v_date;
		this.date_time = date_time;
	}

	public String getRep_deal_id() {
		return rep_deal_id;
	}

	public void setRep_deal_id(String rep_deal_id) {
		this.rep_deal_id = rep_deal_id;
	}

	public String getRep_id() {
		return rep_id;
	}

	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}

	public String getPar_name() {
		return par_name;
	}

	public void setPar_name(String par_name) {
		this.par_name = par_name;
	}

	public String getPar_value() {
		return par_value;
	}

	public void setPar_value(String par_value) {
		this.par_value = par_value;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getUsermac() {
		return usermac;
	}

	public void setUsermac(String usermac) {
		this.usermac = usermac;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public java.sql.Date getActiondate() {
		return actiondate;
	}

	public void setActiondate(java.sql.Date actiondate) {
		this.actiondate = actiondate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserhost() {
		return userhost;
	}

	public void setUserhost(String userhost) {
		this.userhost = userhost;
	}

	public String getUserport() {
		return userport;
	}

	public void setUserport(String userport) {
		this.userport = userport;
	}

	public String getAppip() {
		return appip;
	}

	public void setAppip(String appip) {
		this.appip = appip;
	}

	public String getClientver() {
		return clientver;
	}

	public void setClientver(String clientver) {
		this.clientver = clientver;
	}

	public String getType_log() {
		return type_log;
	}

	public void setType_log(String type_log) {
		this.type_log = type_log;
	}

	public String getLog_text() {
		return log_text;
	}

	public void setLog_text(String log_text) {
		this.log_text = log_text;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public java.sql.Date getResultValueDate() {
		return resultValueDate;
	}

	public void setResultValueDate(java.sql.Date resultValueDate) {
		this.resultValueDate = resultValueDate;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserActionLog() {
		super();
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

	public java.sql.Date getAction_date() {
		return action_date;
	}

	public void setAction_date(java.sql.Date action_date) {
		this.action_date = action_date;
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

	public String getUser_mac() {
		return user_mac;
	}

	public void setUser_mac(String user_mac) {
		this.user_mac = user_mac;
	}
	
	public List<UserActionsAddinfo> getAddinfo() {
		return addinfo;
	}

	public void setAddinfo(List<UserActionsAddinfo> addinfo) {
		this.addinfo = addinfo;
	}
	
	public void setV_date(java.sql.Date v_date) {
		this.v_date = v_date;
	}

	public java.sql.Date getV_date() {
		return v_date;

	}

	public String getAction_date_from() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAction_date_to() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDate_time(java.sql.Date date_time) {
		this.date_time = date_time;
	}

	public java.sql.Date getDate_time() {
		return date_time;
	}
	
}
