package com.is.kernel.states;

public class State
{
	private Long deal_group_id;
    private Long deal_id;
    private Long id;
    private Long name_id;
    private boolean not_exists;
    
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
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getName_id()
	{
		return name_id;
	}
	public void setName_id(Long name_id)
	{
		this.name_id = name_id;
	}
	public boolean isNot_exists()
	{
		return not_exists;
	}
	public void setNot_exists(boolean not_exists)
	{
		this.not_exists = not_exists;
	}
	
	public State(Long deal_group_id, Long deal_id, Long id, Long name_id,
			boolean not_exists)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.deal_id = deal_id;
		this.id = id;
		this.name_id = name_id;
		this.not_exists = not_exists;
	}
	
	public State()
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
