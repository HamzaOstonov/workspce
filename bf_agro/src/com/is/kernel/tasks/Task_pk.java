package com.is.kernel.tasks;

public class Task_pk
{
	private Long deal_group_id;
	private Long id;
	
	public Long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(Long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Task_pk(Long deal_group_id, Long id)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.id = id;
	}
	
	public Task_pk()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "Task_pk [deal_group_id=" + deal_group_id + ", id=" + id + "]";
	}

	@Override
	public int hashCode()
	{
	    return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
	    return this.toString().equals(o.toString());
	}
}
