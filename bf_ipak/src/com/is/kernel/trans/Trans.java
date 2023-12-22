package com.is.kernel.trans;

public class Trans
{
	private Long deal_group_id;
    private Long deal_id;
    private Long action_id;
    private Long begin_state_id;
    private Long end_state_id;
    
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
	public Long getBegin_state_id()
	{
		return begin_state_id;
	}
	public void setBegin_state_id(Long begin_state_id)
	{
		this.begin_state_id = begin_state_id;
	}
	public Long getEnd_state_id()
	{
		return end_state_id;
	}
	public void setEnd_state_id(Long end_state_id)
	{
		this.end_state_id = end_state_id;
	}
	
	public Trans(Long deal_group_id, Long deal_id, Long action_id,
			Long begin_state_id, Long end_state_id)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.action_id = action_id;
		this.begin_state_id = begin_state_id;
		this.end_state_id = end_state_id;
	}
	
	public Trans()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "Trans [deal_group_id=" + deal_group_id + ", deal_id=" + deal_id
				+ ", id=" + action_id + ", begin_state_id="
				+ begin_state_id + ", end_state_id=" + end_state_id + "]";
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
