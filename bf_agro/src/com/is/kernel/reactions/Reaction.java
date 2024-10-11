package com.is.kernel.reactions;

public class Reaction
{
	private Long deal_group_id;
	private Long deal_id;
	private Long action_id;
	private Long task_group_id;
	private Long task_id;
	private Long ord;
	
	public Long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(Long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public Long getDeal_id()
	{
		return deal_id;
	}
	public void setDeal_id(Long deal_id)
	{
		this.deal_id = deal_id;
	}
	public Long getAction_id()
	{
		return action_id;
	}
	public void setAction_id(Long action_id)
	{
		this.action_id = action_id;
	}
	public Long getTask_group_id()
	{
		return task_group_id;
	}
	public void setTask_group_id(Long task_group_id)
	{
		this.task_group_id = task_group_id;
	}
	public Long getTask_id()
	{
		return task_id;
	}
	public void setTask_id(Long task_id)
	{
		this.task_id = task_id;
	}
	public Long getOrd()
	{
		return ord;
	}
	public void setOrd(Long ord)
	{
		this.ord = ord;
	}
	
	public Reaction(Long deal_group_id, Long deal_id, Long action_id,
			Long task_group_id, Long task_id, Long ord)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.action_id = action_id;
		this.task_group_id = task_group_id;
		this.task_id = task_id;
		this.ord = ord;
	}
	
	public Reaction()
	{
		super();
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
