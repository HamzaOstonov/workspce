package com.is.ti_terminal_accounts;

public class Terminal_account
{
	private String terminal_id;
	private String branch;
	private String account;
	
	public String getTerminal_id()
	{
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id)
	{
		this.terminal_id = terminal_id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	
	public Terminal_account(String terminal_id, String branch, String account)
	{
		super();
		this.terminal_id = terminal_id;
		this.branch = branch;
		this.account = account;
	}
	
	public Terminal_account()
	{
		super();
	}
}
