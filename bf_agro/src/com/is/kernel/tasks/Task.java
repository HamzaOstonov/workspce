package com.is.kernel.tasks;

import com.is.kernel.parameter.Parameters;
import com.is.kernel.task_handler.Abstract_task_handler;

public class Task
{
	private Long deal_group_id;
	private Long id;
	private String handler_class_name;
	
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
	public String getHandler_class_name()
	{
		return handler_class_name;
	}
	public void setHandler_class_name(String handler_class_name)
	{
		this.handler_class_name = handler_class_name;
	}
	
	public Abstract_task_handler get_handler() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return (Abstract_task_handler)(Class.forName(this.getHandler_class_name()).newInstance());
	}
	
	public Parameters execute(Parameters parameters) throws Exception
	{
		return this.get_handler().execute(parameters);
	}
	
	public Task(Long deal_group_id, Long id, String handler_class_name)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.id = id;
		this.handler_class_name = handler_class_name;
	}
	
	public Task()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "Task [deal_group_id=" + deal_group_id + ", id=" + id
				+ ", handler_class=" + handler_class_name + "]";
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
