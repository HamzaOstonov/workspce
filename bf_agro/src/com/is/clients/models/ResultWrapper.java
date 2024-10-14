package com.is.clients.models;

public class ResultWrapper {
	private boolean hasDuplicates;
	private boolean existsInTable;
	private boolean isDirAndAccountant;
	private String id;
	private String branch;
	
	public ResultWrapper() {
		// TODO Auto-generated constructor stub
	}

	public boolean isHasDuplicates() {
		return hasDuplicates;
	}

	public void setHasDuplicates(boolean hasDuplicates) {
		this.hasDuplicates = hasDuplicates;
	}

	public boolean isExistsInTable() {
		return existsInTable;
	}

	public void setExistsInTable(boolean existsInTable) {
		this.existsInTable = existsInTable;
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

	public boolean isDirAndAccountant() {
		return isDirAndAccountant;
	}

	public void setDirAndAccountant(boolean isDirAndAccountant) {
		this.isDirAndAccountant = isDirAndAccountant;
	}
	
	
}
