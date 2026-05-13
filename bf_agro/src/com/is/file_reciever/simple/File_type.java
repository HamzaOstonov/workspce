package com.is.file_reciever.simple;

public class File_type
{
	private long id;
	private String filename_pattern;
	private String comment;
	
	public File_type(long id, String filename_pattern, String comment)
	{
		super();
		this.id = id;
		this.filename_pattern = filename_pattern;
		this.comment = comment;
	}
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getFilename_pattern()
	{
		return filename_pattern;
	}
	public void setFilename_pattern(String filename_pattern)
	{
		this.filename_pattern = filename_pattern;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
