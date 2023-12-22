package com.is.file_reciever_srv.recievers.EMPC.entity;

public class EMPC_file
{
	private long id;
	private long fr_file_id;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getFr_file_id()
	{
		return fr_file_id;
	}
	
	public void setFr_file_id(long fr_file_id)
	{
		this.fr_file_id = fr_file_id;
	}
	
	public EMPC_file(long id, long fr_file_id)
	{
		super();
		this.id = id;
		this.fr_file_id = fr_file_id;
	}
	
	public EMPC_file()
	{
		super();
	}
}
