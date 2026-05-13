package com.is.file_reciever_srv.recievers.cb_cash_operaions;

public class Cb_cash_processing_record
{
	private Long id;
	private Long request_id;
	private Integer action_id;
	private String code;
	private String message;
	private boolean critical;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getRequest_id()
	{
		return request_id;
	}
	public void setRequest_id(Long request_id)
	{
		this.request_id = request_id;
	}
	public Integer getAction_id()
	{
		return action_id;
	}
	public void setAction_id(Integer action_id)
	{
		this.action_id = action_id;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public boolean isCritical()
	{
		return critical;
	}
	public void setCritical(boolean critical)
	{
		this.critical = critical;
	}
	
	public Cb_cash_processing_record(Long id, Long request_id, Integer action_id,
			String code, String message, boolean critical)
	{
		super();
		this.id = id;
		this.request_id = request_id;
		this.action_id = action_id;
		this.code = code;
		this.message = message;
		this.critical = critical;
	}
	
	public Cb_cash_processing_record()
	{
		super();
	}
}
