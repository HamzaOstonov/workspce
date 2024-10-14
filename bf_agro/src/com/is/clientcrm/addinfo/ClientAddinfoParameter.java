package com.is.clientcrm.addinfo;
import java.io.Serializable;
import java.util.Date;

public class ClientAddinfoParameter implements Serializable {

    static final long serialVersionUID = 2L;
    private String client_type;
    private String branch;
    private String client_id;
    private Long deal_id;
    private Long state;
    private Long id_cl_add;
	
    public ClientAddinfoParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientAddinfoParameter(String client_type, String branch, String client_id, Long deal_id, Long state, Long id_cl_add) {
		super();
		this.client_type = client_type;
		this.branch = branch;
		this.client_id = client_id;
		this.deal_id = deal_id;
		this.state = state;
		this.id_cl_add = id_cl_add;
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

	public Long getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(Long deal_id) {
		this.deal_id = deal_id;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getId_cl_add() {
		return id_cl_add;
	}

	public void setId_cl_add(Long id_cl_add) {
		this.id_cl_add = id_cl_add;
	}
	
}
