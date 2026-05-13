package com.is.tieto_capital.customer;

public class TietoCustomer
{
	private String branch;
	private String bank_customer_id;
	private String tieto_customer_id;
	private String head_customer_id;
	
	public TietoCustomer()
	{
		super();
	}
	
	public TietoCustomer(String branch, String bank_customer_id,
			String tieto_customer_id, String head_customer_id)
	{
		super();
		this.branch = branch;
		this.bank_customer_id = bank_customer_id;
		this.tieto_customer_id = tieto_customer_id;
		this.head_customer_id = head_customer_id;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	public String getBank_customer_id()
	{
		return bank_customer_id;
	}
	
	public void setBank_customer_id(String bank_customer_id)
	{
		this.bank_customer_id = bank_customer_id;
	}
	
	public String getTieto_customer_id()
	{
		return tieto_customer_id;
	}
	
	public void setTieto_customer_id(String tieto_customer_id)
	{
		this.tieto_customer_id = tieto_customer_id;
	}
	
	public String getHead_customer_id()
	{
		return head_customer_id;
	}
	
	public void setHead_customer_id(String head_customer_id)
	{
		this.head_customer_id = head_customer_id;
	}
	
}
