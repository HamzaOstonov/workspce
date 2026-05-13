package com.is.bpri.bpr_authorized_acc;

public class Bpr_authirizedacc {
	
	private Integer id;
	private Integer bpr_id;
	private String branch;
	private String acc;
	
	public Bpr_authirizedacc() {
		
	}

	public Bpr_authirizedacc(Integer id, Integer bpr_id, String branch,
			String acc) {
		super();
		this.id = id;
		this.bpr_id = bpr_id;
		this.branch = branch;
		this.acc = acc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(Integer bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}
	
}
