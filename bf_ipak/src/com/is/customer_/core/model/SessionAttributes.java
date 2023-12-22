package com.is.customer_.core.model;

public class SessionAttributes {
	private String schema;
	private String branch;
	private int uid;
	private String username;
	private String password;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SessionAttributes [schema=" + schema + ", branch=" + branch + ", uid=" + uid + ", username=" + username
				+ ", password=" + password + "]";
	}
}
