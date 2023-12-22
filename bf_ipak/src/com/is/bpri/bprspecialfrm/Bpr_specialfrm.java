package com.is.bpri.bprspecialfrm;

import java.io.Serializable;

public class Bpr_specialfrm implements Serializable{
	
	static final long serialVersionUID = 103844514947365244L;
	
	private int id;
	private int bpr_id;
    private String bpr_spec;
    private String bpr_spec_value;

    public Bpr_specialfrm() {

    }

    public Bpr_specialfrm(int id,int bpr_id, String bpr_spec, String bpr_spec_value) {
    	this.id = id;
    	this.bpr_id = bpr_id;
        this.bpr_spec = bpr_spec;
        this.bpr_spec_value = bpr_spec_value;
    }

	public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getBpr_spec() {
		return bpr_spec;
	}

	public void setBpr_spec(String bpr_spec) {
		this.bpr_spec = bpr_spec;
	}

	public String getBpr_spec_value() {
		return bpr_spec_value;
	}

	public void setBpr_spec_value(String bpr_spec_value) {
		this.bpr_spec_value = bpr_spec_value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}
