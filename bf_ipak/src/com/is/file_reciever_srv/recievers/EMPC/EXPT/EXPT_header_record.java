package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.util.Date;

public class EXPT_header_record
{
	private Long id;
	private Long empc_file_id;
	private String record_type;
	private Long line_number;
	private Date create_date;
	private String user_id;
	private String bank_c;
	private String groupc;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getEmpc_file_id()
	{
		return empc_file_id;
	}
	public void setEmpc_file_id(Long empc_file_id)
	{
		this.empc_file_id = empc_file_id;
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
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}
	public String getBank_c()
	{
		return bank_c;
	}
	public void setBank_c(String bank_c)
	{
		this.bank_c = bank_c;
	}
	public String getGroupc()
	{
		return groupc;
	}
	public void setGroupc(String groupc)
	{
		this.groupc = groupc;
	}
	
	public EXPT_header_record(Long id, Long empc_file_id, String record_type,
			Long line_number, Date create_date, String user_id, String bank_c,
			String groupc)
	{
		super();
		this.id = id;
		this.empc_file_id = empc_file_id;
		this.record_type = record_type;
		this.line_number = line_number;
		this.create_date = create_date;
		this.user_id = user_id;
		this.bank_c = bank_c;
		this.groupc = groupc;
	}
	
	public EXPT_header_record()
	{
		super();
	}
}
