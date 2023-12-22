package com.is.file_reciever.simple;

import java.util.Date;

public class Ext_out_file
{
	long id;
	long fr_id;
	String branch;
	String file_name;
	Long file_num;
	Date date_out;
	Date v_date;
	long state_id;
	long file_type_id;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getFr_id()
	{
		return fr_id;
	}
	public void setFr_id(long fr_id)
	{
		this.fr_id = fr_id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public String getFile_name()
	{
		return file_name;
	}
	public void setFile_name(String file_name)
	{
		this.file_name = file_name;
	}
	public Long getFile_num()
	{
		return file_num;
	}
	public void setFile_num(Long file_num)
	{
		this.file_num = file_num;
	}
	public Date getDate_out()
	{
		return date_out;
	}
	public void setDate_out(Date date_out)
	{
		this.date_out = date_out;
	}
	public Date getV_date()
	{
		return v_date;
	}
	public void setV_date(Date v_date)
	{
		this.v_date = v_date;
	}
	public long getState_id()
	{
		return state_id;
	}
	public void setState_id(long state_id)
	{
		this.state_id = state_id;
	}
	public long getFile_type_id()
	{
		return file_type_id;
	}
	public void setFile_type_id(long file_type_id)
	{
		this.file_type_id = file_type_id;
	}
	public Ext_out_file(long id, long fr_id, String branch, String file_name,
			Long file_num, Date date_out, Date v_date, long state_id,
			long file_type_id)
	{
		super();
		this.id = id;
		this.fr_id = fr_id;
		this.branch = branch;
		this.file_name = file_name;
		this.file_num = file_num;
		this.date_out = date_out;
		this.v_date = v_date;
		this.state_id = state_id;
		this.file_type_id = file_type_id;
	}
	public Ext_out_file()
	{
		super();
	}
}
