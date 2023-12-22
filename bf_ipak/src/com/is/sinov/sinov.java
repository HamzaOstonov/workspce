package com.is.sinov;

import java.io.Serializable;

public class sinov implements Serializable {

    static final long serialVersionUID = 103844514947365244L;


    private String id;
    private String branch;
    private String name;

    public sinov() {

   }

	public sinov(String id, String branch, String name) {
		super();
		this.id = id;
		this.branch = branch;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    

}   