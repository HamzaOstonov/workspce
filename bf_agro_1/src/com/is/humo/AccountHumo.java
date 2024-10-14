package com.is.humo;

public class AccountHumo {
	private String branch;
    private String id;
    private String name;
	public AccountHumo(String branch, String id, String name) {
		super();
		this.branch = branch;
		this.id = id;
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
    
}
