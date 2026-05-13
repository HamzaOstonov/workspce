package com.is.openwayutils.log;

import java.sql.Date;
import java.sql.Timestamp;

public class Log
{
	String Uid;
	String Uname;
	String Branch;
	String Ip_address;
	//String Action_date;
	Timestamp Time_ex;
	int Act_type;
	int Entity_type;
	String Entity_id;
	
	
	public Log(String uid, String uname, String branch, String ip_address, Timestamp time_ex,
			int act_type, int entity_type, String entity_id) {
		super();
		Uid = uid;
		Uname = uname;
		Branch = branch;
		Ip_address = ip_address;
		//Action_date = action_date;
		Act_type = act_type;
		Entity_type = entity_type;
		Entity_id = entity_id;
		Time_ex = time_ex;
	}
	
	public Timestamp getTime_ex() {
		return Time_ex;
	}

	public void setTime_ex(Timestamp time_ex) {
		Time_ex = time_ex;
	}

	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getIp_address() {
		return Ip_address;
	}
	public void setIp_address(String ip_address) {
		Ip_address = ip_address;
	}
	
	public int getAct_type() {
		return Act_type;
	}
	public void setAct_type(int act_type) {
		Act_type = act_type;
	}
	public int getEntity_type() {
		return Entity_type;
	}
	public void setEntity_type(int entity_type) {
		Entity_type = entity_type;
	}
	public String getEntity_id() {
		return Entity_id;
	}
	public void setEntity_id(String entity_id) {
		Entity_id = entity_id;
	}
}
