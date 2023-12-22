package com.is.customer_.sap.service.endpoint;

public class SAPEndpoint {
	private String username;
	private String password;
	private String contentEndpoint;
	private String customerEndpoint;
	private String relationshipEndpoint;
	private String rolesEndpoint;
	private String contentDeleteEndpoint;	

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

	public String getContentEndpoint() {
		return contentEndpoint;
	}

	public void setContentEndpoint(String contentEndpoint) {
		this.contentEndpoint = contentEndpoint;
	}

	public String getCustomerEndpoint() {
		return customerEndpoint;
	}

	public void setCustomerEndpoint(String customerEndpoint) {
		this.customerEndpoint = customerEndpoint;
	}

	public String getRelationshipEndpoint() {
		return relationshipEndpoint;
	}

	public void setRelationshipEndpoint(String relationshipEndpoint) {
		this.relationshipEndpoint = relationshipEndpoint;
	}

	public String getRolesEndpoint() {
		return rolesEndpoint;
	}

	public void setRolesEndpoint(String rolesEndpoint) {
		this.rolesEndpoint = rolesEndpoint;
	}

	public String getContentDeleteEndpoint() {
		return contentDeleteEndpoint;
	}

	public void setContentDeleteEndpoint(String contentDeleteEndpoint) {
		this.contentDeleteEndpoint = contentDeleteEndpoint;
	}
}
