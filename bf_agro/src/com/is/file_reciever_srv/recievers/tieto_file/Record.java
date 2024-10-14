package com.is.file_reciever_srv.recievers.tieto_file;

public class Record
{
	private long id;
	private long record_type_id;
	private String rec_value;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getRecord_type_id()
	{
		return record_type_id;
	}
	public void setRecord_type_id(long record_type_id)
	{
		this.record_type_id = record_type_id;
	}
	public String getRec_value()
	{
		return rec_value;
	}
	public void setRec_value(String rec_value)
	{
		this.rec_value = rec_value;
	}
	
	public Record(long id, long record_type_id, String rec_value)
	{
		super();
		this.id = id;
		this.record_type_id = record_type_id;
		this.rec_value = rec_value;
	}
	
	public Record()
	{
		super();
	}
}
