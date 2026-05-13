package com.is.bpri.bpr_employee;

public class BprEmployee {

	private Long id;
	private String label;
	private String value;
	private String branch;
	
	public BprEmployee() {
	
	}

	public BprEmployee(Long id, String label, String value,String branch) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
}
