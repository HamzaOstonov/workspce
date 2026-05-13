package com.is.sd_books.model;

public class Credentials {

	private String login;

	private String password;

	private String alias;

	private String branch;

	public Credentials(String login, String password, String alias,
			String branch) {
		super();
		this.login = login;
		this.password = password;
		this.alias = alias;
		this.branch = branch;
	}

	public Credentials(String login, String password, String alias) {
		this.login = login;
		this.password = password;
		this.alias = alias;
	}

	public static Credentials newInstance(String login, String password,
			String alias, String branch) {
		return new Credentials(login, password, alias, branch);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getAlias() {
		return alias;
	}

	public String getBranch() {
		return branch;
	}

	@Override
	public String toString() {
		return "Credentials [" + (login != null ? "login=" + login + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (alias != null ? "alias=" + alias + ", " : "")
				+ (branch != null ? "branch=" + branch : "") + "]";
	}
}
