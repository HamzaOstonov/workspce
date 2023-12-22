package com.is.clients;

public enum BfModules {
	ACCOUNT(5),
	ADDINFO(206),
	SPEC_CLT(207),
	SPEC_ACC(208);
	
	private int moduleId;
	private BfModules() {}
	private BfModules(int moduleId) {
		this.moduleId = moduleId;
	}
	
	public int getModuleId() {
		return moduleId;
	}
	
}
