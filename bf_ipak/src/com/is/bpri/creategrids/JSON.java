package com.is.bpri.creategrids;

import java.util.List;

public class JSON {
	
	private String prc_id;
	private String user_id;
	private String currkey;
	private List<JSONModel> jsonobj;
	
	public JSON() {
		
	}

	public JSON(String prc_id, String user_id, String currkey,
			List<JSONModel> jsonobj) {
		super();
		this.prc_id = prc_id;
		this.user_id = user_id;
		this.currkey = currkey;
		this.jsonobj = jsonobj;
	}

	public String getPrc_id() {
		return prc_id;
	}

	public void setPrc_id(String prc_id) {
		this.prc_id = prc_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCurrkey() {
		return currkey;
	}

	public void setCurrkey(String currkey) {
		this.currkey = currkey;
	}

	public List<JSONModel> getJsonobj() {
		return jsonobj;
	}

	public void setJsonobj(List<JSONModel> jsonobj) {
		this.jsonobj = jsonobj;
	}
	
}
