package com.is.file_reciever.simple;

import java.util.Date;

public class Fr_file
{
	private long id;
	private String name;
	private Date date_time;
	
	public Fr_file(long id, String name, Date date_time)
	{
		super();
		this.id = id;
		this.name = name;
		this.date_time = date_time;
	}
	
	public Fr_file()
	{
		super();
	}
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Date getDate_time()
	{
		return date_time;
	}
	public void setDate_time(Date date_time)
	{
		this.date_time = date_time;
	}
}
