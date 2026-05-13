package com.is.bpri;

public class BprDealIdTypes
{
	private int bpr_id;
	private int group_id;
	private int deal_id;
	
	
	public BprDealIdTypes(){}
	
	public BprDealIdTypes(int bpr_id, int group_id, int deal_id)
	{
		super();
		this.bpr_id = bpr_id;
		this.group_id = group_id;
		this.deal_id = deal_id;
	}
	
	public int getBpr_id()
	{
		return bpr_id;
	}
	public void setBpr_id(int bpr_id)
	{
		this.bpr_id= bpr_id;
	}
	
	public int getGroup_id()
	{
		return group_id;
	}
	
	public void setGroup_id(int group_id)
	{
		this.group_id = group_id;
	}
	
	public int getDeal_id()
	{
		return deal_id;
	}
	
	public void setDeal_id(int deal_id)
	{
		this.deal_id = deal_id;
	}
	
	
	
}
