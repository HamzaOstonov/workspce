package com.is.clients.addinfo.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.is.utils.RefData;

public class ParameterListArray {
	static final long serialVersionUID = 3L;

	private String client_type;
	private String param_id;
	private String param_list_teg;
	private String param_select;
	private String param_type;
	private String param_mask;
	private String param_def_value;
	private Long param_ord;
	private int param_mandatory;
	private int param_align;
	private String param_constraints;
	private String param_enable;
	private int param_visible;
	private int param_visible_t;
	private String param_actions;
	private int param_act_runatstart;
	private String param_value;
	private int state;
	private List<RefData> reflist = new ArrayList<RefData>();

    public ParameterListArray() {
		super();
    }

	public ParameterListArray(String client_type, String param_id, String param_list_teg, String param_select, String param_type, String param_mask, String param_def_value, Long param_ord, int param_mandatory, int param_align, String param_constraints, String param_enable, int param_visible, int param_visible_t, String param_actions, int param_act_runatstart, String param_value, int state) {
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
		this.param_value = param_value;
		this.state = state;
	}

	public ParameterListArray(String client_type, String param_id, String param_list_teg, String param_select, String param_type, String param_mask, String param_def_value, Long param_ord, int param_mandatory, int param_align, String param_constraints, String param_enable, int param_visible, int param_visible_t, String param_actions, int param_act_runatstart, String param_value, int state, List<RefData> reflist) {
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
		this.param_value = param_value;
		this.state = state;
		this.reflist = reflist;
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

	public int getParam_mandatory() {
		return param_mandatory;
	}

	public void setParam_mandatory(int param_mandatory) {
		this.param_mandatory = param_mandatory;
	}

	public int getParam_align() {
		return param_align;
	}

	public void setParam_align(int param_align) {
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

	public int getParam_visible() {
		return param_visible;
	}

	public void setParam_visible(int param_visible) {
		this.param_visible = param_visible;
	}

	public int getParam_visible_t() {
		return param_visible_t;
	}

	public void setParam_visible_t(int param_visible_t) {
		this.param_visible_t = param_visible_t;
	}

	public String getParam_actions() {
		return param_actions;
	}

	public void setParam_actions(String param_actions) {
		this.param_actions = param_actions;
	}

	public int getParam_act_runatstart() {
		return param_act_runatstart;
	}

	public void setParam_act_runatstart(int param_act_runatstart) {
		this.param_act_runatstart = param_act_runatstart;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<RefData> getReflist() {
		return reflist;
	}

	public void setReflist(List<RefData> reflist) {
		this.reflist = reflist;
	}

}

