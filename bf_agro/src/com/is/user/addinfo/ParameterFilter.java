package com.is.user.addinfo;

import java.io.Serializable;

public class ParameterFilter implements Serializable {

    static final long serialVersionUID = 3L;

	private String branch;
	private Long user_id;
	private String param_value;
	private Long param_group_id;
	private Long param_id;
	private String param_name;
	private String param_name_en;
	private String param_name_ru;
	private String param_name_uz;
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

	public ParameterFilter(String branch, Long user_id, String param_value,
			Long param_group_id, Long param_id,
			String param_name, String param_name_en, String param_name_ru,
			String param_name_uz, String param_select, String param_type,
			String param_mask, String param_def_value, Long param_ord,
			int param_mandatory, int isnew) {
		super();
		this.branch = branch;
		this.user_id = user_id;
		this.param_value = param_value;
		this.param_group_id = param_group_id;
		this.param_id = param_id;
		this.param_name = param_name;
		this.param_name_en = param_name_en;
		this.param_name_ru = param_name_ru;
		this.param_name_uz = param_name_uz;
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

	public Long getUser_id() { 
		return user_id;
	} 

	public void setUser_id(Long user_id) { 
		this.user_id = user_id;
	} 

	public String getParam_value() { 
		return param_value;
	} 

	public void setParam_value(String param_value) { 
		this.param_value = param_value;
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

	public String getParam_name_en() { 
		return param_name_en;
	} 

	public void setParam_name_en(String param_name_en) { 
		this.param_name_en = param_name_en;
	} 

	public String getParam_name_ru() { 
		return param_name_ru;
	} 

	public void setParam_name_ru(String param_name_ru) { 
		this.param_name_ru = param_name_ru;
	} 

	public String getParam_name_uz() { 
		return param_name_uz;
	} 

	public void setParam_name_uz(String param_name_uz) { 
		this.param_name_uz = param_name_uz;
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

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	} 

}