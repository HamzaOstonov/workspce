package com.is.clients.models;

public class FounderMap {
	private long idPersonMap;
	private String branch;
	private long id_cl_add;
	private int id_founder;
	
	public FounderMap() {
	}

	public FounderMap(long idPersonMap, String branch, long id_cl_add, int id_founder) {
		super();
		this.idPersonMap = idPersonMap;
		this.branch = branch;
		this.id_cl_add = id_cl_add;
		this.id_founder = id_founder;
	}
	
	public FounderMap(long idPersonMap, long id_cl_add, int id_founder) {
		super();
		this.idPersonMap = idPersonMap;
		this.id_cl_add = id_cl_add;
		this.id_founder = id_founder;
	}

	public long getIdPersonMap() {
		return idPersonMap;
	}

	public void setIdPersonMap(long idPersonMap) {
		this.idPersonMap = idPersonMap;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public long getId_cl_add() {
		return id_cl_add;
	}

	public void setId_cl_add(long id_cl_add) {
		this.id_cl_add = id_cl_add;
	}

	public int getId_founder() {
		return id_founder;
	}

	public void setId_founder(int id_founder) {
		this.id_founder = id_founder;
	}
	
}
