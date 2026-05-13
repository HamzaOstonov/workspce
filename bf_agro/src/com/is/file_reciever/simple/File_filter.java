package com.is.file_reciever.simple;

import java.util.Date;

public class File_filter
{
	private Long file_type;
	private Date file_date;
	private Date date_from;
	private Date date_to;
	
	public File_filter(Long file_type, Date file_date)
	{
		super();
		this.file_type = file_type;
		this.file_date = file_date;
	}
	public Date getDate_from()
	{
		return date_from;
	}
	public void setDate_from(Date date_from)
	{
		this.date_from = date_from;
	}
	public Date getDate_to()
	{
		return date_to;
	}
	public void setDate_to(Date date_to)
	{
		this.date_to = date_to;
	}
	public Long getFile_type()
	{
		return file_type;
	}
	public void setFile_type(Long file_type)
	{
		this.file_type = file_type;
	}
	public Date getFile_date()
	{
		return file_date;
	}
	public void setFile_date(Date file_date)
	{
		this.file_date = file_date;
	}
	public File_filter()
	{
		super();
	}
}
