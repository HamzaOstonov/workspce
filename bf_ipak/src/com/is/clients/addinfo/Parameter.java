package com.is.clients.addinfo;

import java.io.Serializable;

public class Parameter implements Serializable {

    static final long serialVersionUID = 3L;

	private String branch;
	private String client_id;
	private String param_value;
	private String client_type;
	private String param_group_id;
	private String param_id;
	private String param_select;
	private String param_type;
	private String param_mask;
	private String param_def_value;
	private Long param_ord;
	private int param_mandatory;
	private int isnew;
	private int param_align;
	private String param_actions;
	private String param_enable;
	private int param_act_runatstart;
	private Boolean changed;
	private int param_visible;
	
    public Parameter() {
		super();
    }

	public Parameter(String branch, String client_id, String param_value,
			String client_type, String param_group_id, String param_id,
			String param_select, String param_type,
			String param_mask, String param_def_value, Long param_ord,
			int param_mandatory, int isnew, int param_align, String param_actions,
			String param_enable, int param_act_runatstart, int param_visible) {
		super();
		this.branch = branch;
		this.client_id = client_id;
		this.param_value = param_value;
		this.client_type = client_type;
		this.param_group_id = param_group_id;
		this.param_id = param_id;
		this.param_select = param_select;
		this.param_type = param_type;
		this.param_mask = param_mask;
		this.param_def_value = param_def_value;
		this.param_ord = param_ord;
		this.param_mandatory = param_mandatory;
		this.isnew = isnew;
		this.param_align = param_align;
		this.param_actions = param_actions;
		this.param_enable = param_enable;
		this.param_act_runatstart = param_act_runatstart;
		this.changed = false;
		this.param_visible = param_visible;
	}

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public String getClient_id() { 
		return client_id;
	} 

	public void setClient_id(String client_id) { 
		this.client_id = client_id;
	} 

	public String getParam_value() { 
		return param_value;
	} 

	public void setParam_value(String param_value) { 
		this.param_value = param_value;
	} 

	public String getClient_type() { 
		return client_type;
	} 

	public void setClient_type(String client_type) { 
		this.client_type = client_type;
	} 

	public String getParam_group_id() { 
		return param_group_id;
	} 

	public void setParam_group_id(String param_group_id) { 
		this.param_group_id = param_group_id;
	} 

	public String getParam_id() { 
		return param_id;
	} 

	public void setParam_id(String param_id) { 
		this.param_id = param_id;
	} 

	public String getParam_select() { 
		return param_select;
	} 

	public void setParam_select(String param_select) { 
		this.param_select = param_select;
	} 

	public String getParam_type() { 
		return param_type;
	} 

	public void setParam_type(String param_type) { 
		this.param_type = param_type;
	} 

	public String getParam_mask() { 
		return param_mask;
	} 

	public void setParam_mask(String param_mask) { 
		this.param_mask = param_mask;
	} 

	public String getParam_def_value() { 
		return param_def_value;
	} 

	public void setParam_def_value(String param_def_value) { 
		this.param_def_value = param_def_value;
	} 

	public Long getParam_ord() { 
		return param_ord;
	} 

	public void setParam_ord(Long param_ord) { 
		this.param_ord = param_ord;
	} 

	public int getParam_mandatory() { 
		return param_mandatory;
	} 

	public void setParam_mandatory(int param_mandatory) { 
		this.param_mandatory = param_mandatory;
	}

	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}

	public int getParam_align() {
		return param_align;
	}

	public void setParam_align(int param_align) {
		this.param_align = param_align;
	}

	public String getParam_actions() {
		return param_actions;
	}

	public void setParam_actions(String param_actions) {
		this.param_actions = param_actions;
	}

	public String getParam_enable() {
		return param_enable;
	}

	public void setParam_enable(String param_enable) {
		this.param_enable = param_enable;
	}

	public int getParam_act_runatstart() {
		return param_act_runatstart;
	}

	public void setParam_act_runatstart(int param_act_runatstart) {
		this.param_act_runatstart = param_act_runatstart;
	}

	public Boolean isChanged() {
		return changed;
	}

	public void setChanged(Boolean changed) {
		this.changed = changed;
	}

	public int getParam_visible() {
		return param_visible;
	}

	public void setParam_visible(int param_visible) {
		this.param_visible = param_visible;
	} 

}