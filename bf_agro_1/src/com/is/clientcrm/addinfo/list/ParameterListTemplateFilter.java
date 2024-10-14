package com.is.clientcrm.addinfo.list;

import java.io.Serializable;

public class ParameterListTemplateFilter implements Serializable {

    static final long serialVersionUID = 2L;

	private String client_type;
	private String param_id;
	private String param_list_teg;
	private String param_select;
	private String param_type;
	private String param_mask;
	private String param_def_value;
	private Long param_ord;
	private Long param_mandatory;
	private Long param_align;
	private String param_constraints;
	private String param_enable;
	private String param_visible;
	private Long param_visible_t;
	private String param_actions;
	private Long param_act_runatstart;



    public ParameterListTemplateFilter() {
		super();
    }

    public ParameterListTemplateFilter(String client_type, String param_id, String param_list_teg, String param_select, String param_type, String param_mask, String param_def_value, Long param_ord, Long param_mandatory, Long param_align, String param_constraints, String param_enable, String param_visible, Long param_visible_t, String param_actions, Long param_act_runatstart) {
		super();
		this.client_type = client_type;
		this.param_id = param_id;
		this.param_list_teg = param_list_teg;
		this.param_select = param_select;
		this.param_type = param_type;
		this.param_mask = param_mask;
		this.param_def_value = param_def_value;
		this.param_ord = param_ord;
		this.param_mandatory = param_mandatory;
		this.param_align = param_align;
		this.param_constraints = param_constraints;
		this.param_enable = param_enable;
		this.param_visible = param_visible;
		this.param_visible_t = param_visible_t;
		this.param_actions = param_actions;
		this.param_act_runatstart = param_act_runatstart;


    }
    

	public String getClient_type() { 
		return client_type;
	} 

	public void setClient_type(String client_type) { 
		this.client_type = client_type;
	} 

	public String getParam_id() { 
		return param_id;
	} 

	public void setParam_id(String param_id) { 
		this.param_id = param_id;
	} 

	public String getParam_list_teg() { 
		return param_list_teg;
	} 

	public void setParam_list_teg(String param_list_teg) { 
		this.param_list_teg = param_list_teg;
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

	public Long getParam_mandatory() { 
		return param_mandatory;
	} 

	public void setParam_mandatory(Long param_mandatory) { 
		this.param_mandatory = param_mandatory;
	} 

	public Long getParam_align() { 
		return param_align;
	} 

	public void setParam_align(Long param_align) { 
		this.param_align = param_align;
	} 

	public String getParam_constraints() { 
		return param_constraints;
	} 

	public void setParam_constraints(String param_constraints) { 
		this.param_constraints = param_constraints;
	} 

	public String getParam_enable() { 
		return param_enable;
	} 

	public void setParam_enable(String param_enable) { 
		this.param_enable = param_enable;
	} 

	public String getParam_visible() { 
		return param_visible;
	} 

	public void setParam_visible(String param_visible) { 
		this.param_visible = param_visible;
	} 

	public Long getParam_visible_t() { 
		return param_visible_t;
	} 

	public void setParam_visible_t(Long param_visible_t) { 
		this.param_visible_t = param_visible_t;
	} 

	public String getParam_actions() { 
		return param_actions;
	} 

	public void setParam_actions(String param_actions) { 
		this.param_actions = param_actions;
	} 

	public Long getParam_act_runatstart() { 
		return param_act_runatstart;
	} 

	public void setParam_act_runatstart(Long param_act_runatstart) { 
		this.param_act_runatstart = param_act_runatstart;
	} 
    

}
