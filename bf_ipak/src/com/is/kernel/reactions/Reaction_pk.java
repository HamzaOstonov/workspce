package com.is.kernel.reactions;

public class Reaction_pk
{
	private Long deal_group_id;
	private Long deal_id;
	private Long action_id;
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
	public Long getOrd()
	{
		return ord;
	}
	public void setOrd(Long ord)
	{
		this.ord = ord;
	}
	
	public Reaction_pk(Long deal_group_id, Long deal_id, Long action_id,
			Long ord)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.action_id = action_id;
		this.ord = ord;
	}
	
	public Reaction_pk()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "Reaction_pk [deal_group_id=" + deal_group_id + ", deal_id="
				+ deal_id + ", id=" + action_id + ", ord=" + ord + "]";
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
