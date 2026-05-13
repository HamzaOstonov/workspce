package com.is.file_reciever_srv.file_checks;

public class Parsed_string
{
	private String data;
	private long control_summ;
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public long getControl_summ()
	{
		return control_summ;
	}
	public void setControl_summ(long control_summ)
	{
		this.control_summ = control_summ;
	}
	public Parsed_string(String data, long control_summ)
	{
		super();
		this.data = data;
		this.control_summ = control_summ;
	}
}
