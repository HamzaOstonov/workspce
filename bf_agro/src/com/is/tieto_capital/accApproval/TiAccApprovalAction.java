package com.is.tieto_capital.accApproval;

public class TiAccApprovalAction {
	
	private Integer id;
	private String name;
	private String action_method;
	
	public TiAccApprovalAction() {
		super();
	}

	public TiAccApprovalAction(Integer id, String name, String action_method) {
		super();
		this.id = id;
		this.name = name;
		this.action_method = action_method;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction_method() {
		return action_method;
	}

	public void setAction_method(String action_method) {
		this.action_method = action_method;
	}
	
}
