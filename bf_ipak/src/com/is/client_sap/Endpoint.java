package com.is.client_sap;

public class Endpoint {
	private String endpoint;
	private String username;
	private String password;

	public Endpoint() {
	}
	
	public Endpoint(String endpoint, String username, String password) {
		super();
		this.endpoint = endpoint;
		this.username = username;
		this.password = password;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
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
		return "Endpoint [endpoint=" + endpoint + ", username=" + username + ", password=" + password + "]";
	}

}
