package com.is.dper_info.reports;

public class SS_dblink_branch {
	private String branch;
	private String db_alias;
	private String name;
	private String user_name;
	private String region;
	
	public SS_dblink_branch() {
		// TODO Auto-generated constructor stub
	}
	
	public SS_dblink_branch(String branch, String user_name) {
		super();
		this.branch = branch;
		this.user_name = user_name;
	}

	public SS_dblink_branch(String branch, String db_alias, String name,
			String user_name, String region) {
		super();
		this.branch = branch;
		this.db_alias = db_alias;
		this.name = name;
		this.user_name = user_name;
		this.region = region;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDb_alias() {
		return db_alias;
	}

	public void setDb_alias(String db_alias) {
		this.db_alias = db_alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
