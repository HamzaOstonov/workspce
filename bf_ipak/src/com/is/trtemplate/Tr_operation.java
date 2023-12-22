package com.is.trtemplate;

public class Tr_operation
{
	private Long id;
	private String description;
	private Long parent_group_id;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Long getParent_group_id()
	{
		return parent_group_id;
	}
	public void setParent_group_id(Long parent_group_id)
	{
		this.parent_group_id = parent_group_id;
	}
	
	public Tr_operation(Long id, String description, Long parent_group_id)
	{
		super();
		this.id = id;
		this.description = description;
		this.parent_group_id = parent_group_id;
	}
	
	public Tr_operation()
	{
		super();
	}
}
