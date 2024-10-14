package com.is.ext_protocol;

import java.util.Date;

public class Ext_protocol
{
	private long deal_group_id;
	private long s_deal_id;
	private long obj_id;
	private Date v_date; 
	private String text; 
	private long message_id;
	private String message_text;
	public long getDeal_group_id()
	{
		return deal_group_id;
	}
	public void setDeal_group_id(long deal_group_id)
	{
		this.deal_group_id = deal_group_id;
	}
	public long getS_deal_id()
	{
		return s_deal_id;
	}
	public void setS_deal_id(long s_deal_id)
	{
		this.s_deal_id = s_deal_id;
	}
	public long getObj_id()
	{
		return obj_id;
	}
	public void setObj_id(long obj_id)
	{
		this.obj_id = obj_id;
	}
	public Date getV_date()
	{
		return v_date;
	}
	public void setV_date(Date v_date)
	{
		this.v_date = v_date;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public long getMessage_id()
	{
		return message_id;
	}
	public void setMessage_id(long message_id)
	{
		this.message_id = message_id;
	}
	public String getMessage_text()
	{
		return message_text;
	}
	public void setMessage_text(String message_text)
	{
		this.message_text = message_text;
	}
	public Ext_protocol(long deal_group_id, long s_deal_id, long obj_id,
			Date v_date, String text, long message_id, String message_text)
	{
		super();
		this.deal_group_id = deal_group_id;
		this.s_deal_id = s_deal_id;
		this.obj_id = obj_id;
		this.v_date = v_date;
		this.text = text;
		this.message_id = message_id;
		this.message_text = message_text;
	}
	public Ext_protocol()
	{
		super();
	}
}
