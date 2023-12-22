package com.is.file_reciever_view.energo;

import java.util.Date;

public class Ext_created_object
{
	private Long id;
	private Long in_record_id;
	private String branch;
	private Long deal_group_id;
	private Long object_id;
	private Date v_date;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getIn_record_id()
	{
		return in_record_id;
	}
	public void setIn_record_id(Long in_record_id)
	{
		this.in_record_id = in_record_id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public Long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(Long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public Long getObject_id()
	{
		return object_id;
	}
	public void setObject_id(Long object_id)
	{
		this.object_id = object_id;
	}
	public Date getV_date()
	{
		return v_date;
	}
	public void setV_date(Date v_date)
	{
		this.v_date = v_date;
	}
	public Ext_created_object(Long id, Long in_record_id, String branch,
			Long deal_group_id, Long object_id, Date v_date)
	{
		super();
		this.id = id;
		this.in_record_id = in_record_id;
		this.branch = branch;
		this.deal_group_id = deal_group_id;
		this.object_id = object_id;
		this.v_date = v_date;
	}
	public Ext_created_object()
	{
		super();
	}
}
