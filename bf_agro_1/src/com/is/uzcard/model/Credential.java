package com.is.uzcard.model;

public class Credential {
	private String un;
	private String pwd;
	private String alias;
	private String branch;
	

	@Override
	public String toString() {
		return "Credential [" + (un != null ? "un=" + un + ", " : "") + (pwd != null ? "pwd=" + pwd + ", " : "")
				+ (alias != null ? "alias=" + alias + ", " : "") + (branch != null ? "branch=" + branch : "") + "]";
	}

	public Credential(String un, String pwd, String alias, String branch) {
		super();
		this.un = un;
		this.pwd = pwd;
		this.alias = alias;
		this.branch = branch;
	}

	public String getUn() {
		return un;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
