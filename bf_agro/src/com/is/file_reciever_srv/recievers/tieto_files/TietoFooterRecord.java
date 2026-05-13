package com.is.file_reciever_srv.recievers.tieto_files;

import java.util.Date;

public class TietoFooterRecord
{
	private Long id;
	private Long tieto_file_id;
	private String record_type;
	private Long line_number;
	private Date create_date;
	private String field;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getTieto_file_id()
	{
		return tieto_file_id;
	}
	public void setTieto_file_id(Long tieto_file_id)
	{
		this.tieto_file_id = tieto_file_id;
	}
	public String getRecord_type()
	{
		return record_type;
	}
	public void setRecord_type(String record_type)
	{
		this.record_type = record_type;
	}
	public Long getLine_number()
	{
		return line_number;
	}
	public void setLine_number(Long line_number)
	{
		this.line_number = line_number;
	}
	public Date getCreate_date()
	{
		return create_date;
	}
	public void setCreate_date(Date create_date)
	{
		this.create_date = create_date;
	}
	public String getField()
	{
		return field;
	}
	public void setField(String field)
	{
		this.field = field;
	}
	
	public TietoFooterRecord(Long id, Long tieto_file_id, String record_type,
			Long line_number, Date create_date, String field)
	{
		super();
		this.id = id;
		this.tieto_file_id = tieto_file_id;
		this.record_type = record_type;
		this.line_number = line_number;
		this.create_date = create_date;
		this.field = field;
	}
	
	public TietoFooterRecord()
	{
		super();
	}
}
