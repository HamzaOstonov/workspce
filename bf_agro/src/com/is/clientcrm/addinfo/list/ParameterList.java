package com.is.clientcrm.addinfo.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterList implements Serializable {

	static final long serialVersionUID = 3L;

	private String client_type;
	private String branch;
	private String client_id;
	private String param_id;
	private Long list_id;
	private int state;
	private List<ParameterListArray> parameters = new ArrayList<ParameterListArray>();

    public ParameterList() {
		super();
    }

    public ParameterList(String client_type, String branch, String client_id, String param_id, Long list_id, int state) {
		super();
		this.client_type = client_type;
		this.branch = branch;
		this.client_id = client_id;
		this.param_id = param_id;
		this.list_id = list_id;
		this.state = state;
    }

	public ParameterList(String client_type, String branch, String client_id, String param_id, Long list_id, int state, List<ParameterListArray> parameters) {
		super();
		this.client_type = client_type;
		this.branch = branch;
		this.client_id = client_id;
		this.param_id = param_id;
		this.list_id = list_id;
		this.state = state;
		this.parameters = parameters;
	}

	public String getClient_type() { 
		return client_type;
	} 

	public void setClient_type(String client_type) { 
		this.client_type = client_type;
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

	public String getParam_id() { 
		return param_id;
	} 

	public void setParam_id(String param_id) { 
		this.param_id = param_id;
	} 

	public Long getList_id() { 
		return list_id;
	} 

	public void setList_id(Long list_id) { 
		this.list_id = list_id;
	} 

	public int getState() { 
		return state;
	} 

	public void setState(int state) { 
		this.state = state;
	}

	public List<ParameterListArray> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterListArray> parameters) {
		this.parameters = parameters;
	} 
    
	public void addParameters(ParameterListArray parameter) {
		if (this.parameters == null) this.parameters = new ArrayList<ParameterListArray>();
		this.parameters.add(parameter);
	} 

}
