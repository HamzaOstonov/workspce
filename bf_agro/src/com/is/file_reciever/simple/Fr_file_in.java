package com.is.file_reciever.simple;

import java.util.Date;

public class Fr_file_in extends Fr_file
{
	private long id_file_type;

	public Fr_file_in(long id, String name, Date date_time, long id_file_type)
	{
		super(id, name, date_time);
		this.id_file_type = id_file_type;
	}

	public Fr_file_in()
	{
		super();
	}

	public long getId_file_type()
	{
		return id_file_type;
	}
	public void setId_file_type(long id_file_type)
	{
		this.id_file_type = id_file_type;
	}
}
