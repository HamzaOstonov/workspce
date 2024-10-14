package com.is.file_reciever_srv.simple.stat;

public class Observe_directory
{
	private String dir;
	private int id;
    private String comment_;
    
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getComment_()
	{
		return comment_;
	}
	public void setComment_(String comment_)
	{
		this.comment_ = comment_;
	}
	public Observe_directory(int id, String dir, String comment_)
	{
		super();
		this.dir = dir;
		this.id = id;
		this.comment_ = comment_;
	}    
}
