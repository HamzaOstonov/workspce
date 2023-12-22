package com.is.file_reciever_view.simple;

import java.util.Date;

public class Created_object
{
	long id;
	long in_record_id;
	String branch;
	long deal_group_id;
	long obj_id;
	Date v_date;
	public Created_object(long id, long in_record_id, String branch,
			long deal_group_id, long obj_id, Date v_date)
	{
		super();
		this.id = id;
		this.in_record_id = in_record_id;
		this.branch = branch;
		this.deal_group_id = deal_group_id;
		this.obj_id = obj_id;
		this.v_date = v_date;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getIn_record_id()
	{
		return in_record_id;
	}
	public void setIn_record_id(long in_record_id)
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
	public long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public long getObj_id()
	{
		return obj_id;
	}
	public void setObj_id(long obj_id)
	{
		this.obj_id = obj_id;
	}
	public Date getV_date()
	{
		return v_date;
	}
	public void setV_date(Date v_date)
	{
		this.v_date = v_date;
	}
}
