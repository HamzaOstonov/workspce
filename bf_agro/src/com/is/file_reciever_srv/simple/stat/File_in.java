package com.is.file_reciever_srv.simple.stat;

import java.util.Date;

public class File_in extends File
{
	private int id_file_type;
	
	public File_in(long id, String name, int id_file_type, Date date_time)
	{
		super(id, name, date_time);
		this.id_file_type = id_file_type;
	}

	public int getId_file_type()
	{
		return id_file_type;
	}
	public void setId_file_type(int id_file_type)
	{
		this.id_file_type = id_file_type;
	}
}
