package com.is.tieto_visa.tieto;

public class WSConnectionInfo {
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getLogin() {
		return login;
	}
	public WSConnectionInfo() {
		super();
	}
	public WSConnectionInfo(String host, String login, String password, String bankC, String groupC) {
		super();
		this.host = host;
		this.login = login;
		this.password = password;
		this.bankC = bankC;
		this.groupC = groupC;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBankC() {
		return bankC;
	}
	public void setBankC(String bankC) {
		this.bankC = bankC;
	}
	public String getGroupC() {
		return groupC;
	}
	public void setGroupC(String groupC) {
		this.groupC = groupC;
	}
	private String host;
	private String login;
	private String password;
	private String bankC;
	private String groupC;

}
