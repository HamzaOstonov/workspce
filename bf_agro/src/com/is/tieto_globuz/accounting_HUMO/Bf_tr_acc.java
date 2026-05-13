package com.is.tieto_globuz.accounting_HUMO;

public class Bf_tr_acc
{
	private long id;
	private String branch;
	private long acc_template_id;
	private String acc_mfo;
	private String account;
	private String acc_name;
	private String acc_name_and_inn;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public long getAcc_template_id()
	{
		return acc_template_id;
	}
	public void setAcc_template_id(long acc_template_id)
	{
		this.acc_template_id = acc_template_id;
	}
	public String getAcc_mfo()
	{
		return acc_mfo;
	}
	public void setAcc_mfo(String acc_mfo)
	{
		this.acc_mfo = acc_mfo;
	}
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	public String getAcc_name()
	{
		return acc_name;
	}
	public void setAcc_name(String acc_name)
	{
		this.acc_name = acc_name;
	}
	public String getAcc_name_and_inn()
	{
		return acc_name_and_inn;
	}
	public void setAcc_name_and_inn(String acc_name_and_inn)
	{
		this.acc_name_and_inn = acc_name_and_inn;
	}
	
	public Bf_tr_acc(long id, String branch, long acc_template_id,
			String acc_mfo, String account, String acc_name,
			String acc_name_and_inn)
	{
		super();
		this.id = id;
		this.branch = branch;
		this.acc_template_id = acc_template_id;
		this.acc_mfo = acc_mfo;
		this.account = account;
		this.acc_name = acc_name;
		this.acc_name_and_inn = acc_name_and_inn;
	}
	
	public Bf_tr_acc()
	{
		super();
	}
	@Override
	public String toString()
	{
		return "Bf_tr_acc [id=" + id + ", branch=" + branch
				+ ", acc_template_id=" + acc_template_id + ", acc_mfo="
				+ acc_mfo + ", account=" + account + ", acc_name=" + acc_name
				+ "]";
	}
	
}
