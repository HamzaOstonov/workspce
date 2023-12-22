package com.is.clients.addinfo;

import java.io.Serializable;

public class ParameterFilter implements Serializable {

    static final long serialVersionUID = 31L;

	private String branch;
	private String client_id;
	private String param_value;
	private String client_type;
	private Long param_group_id;
	private Long param_id;
	private String param_select;
	private String param_type;
	private String param_mask;
	private String param_def_value;
	private Long param_ord;
	private int param_mandatory;
	private int isnew;

    public ParameterFilter() {
		super();
    }

	public ParameterFilter(String branch, String client_id, String param_value,
			String client_type, Long param_group_id, Long param_id,
			String param_select, String param_type,
			String param_mask, String param_def_value, Long param_ord,
			int param_mandatory, int isnew) {
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

	public Long getParam_group_id() { 
		return param_group_id;
	} 

	public void setParam_group_id(Long param_group_id) { 
		this.param_group_id = param_group_id;
	} 

	public Long getParam_id() { 
		return param_id;
	} 

	public void setParam_id(Long param_id) { 
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

}