package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

import com.is.file_reciever_srv.recievers.EMPC.entity.EMPC_file_record;

public class B_file_record extends EMPC_file_record
{
	private String type;
	private String content;
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public B_file_record(long eMPC_file_id, String type, String content)
	{
		super(eMPC_file_id);
		this.type = type;
		this.content = content;
	}
	
	public B_file_record()
	{
		super();
	}
	
	public B_file_record(long eMPC_file_id)
	{
		super(eMPC_file_id);
	}
}
