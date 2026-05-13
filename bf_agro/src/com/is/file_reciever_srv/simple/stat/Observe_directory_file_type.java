package com.is.file_reciever_srv.simple.stat;

public class Observe_directory_file_type
{
	int id_directory;
	int id_file_type;
	public int getId_directory()
	{
		return id_directory;
	}
	public void setId_directory(int id_directory)
	{
		this.id_directory = id_directory;
	}
	public int getId_file_type()
	{
		return id_file_type;
	}
	public void setId_file_type(int id_file_type)
	{
		this.id_file_type = id_file_type;
	}
	public Observe_directory_file_type(int id_directory, int id_file_type)
	{
		super();
		this.id_directory = id_directory;
		this.id_file_type = id_file_type;
	}
}
