package com.is.file_reciever_srv.recievers.NIKI;

public class NIKI_file
{
	private long id;
	private String branch;
	private int state;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getBranch()
	{
		return branch;
	}
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	
	public NIKI_file(long id, String branch, int state)
	{
		super();
		this.id = id;
		this.branch = branch;
		this.state = state;
	}
	public NIKI_file()
	{
		super();
	}
}
