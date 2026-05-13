package com.is.file_reciever_srv.recievers.tieto_file;

public class Dynamic_parameter
{
	private String put_param_name;
	private String get_param_name;
	private String modifier_class;
	
	public String getPut_param_name()
	{
		return put_param_name;
	}
	public void setPut_param_name(String put_param_name)
	{
		this.put_param_name = put_param_name;
	}
	public String getGet_param_name()
	{
		return get_param_name;
	}
	public void setGet_param_name(String get_param_name)
	{
		this.get_param_name = get_param_name;
	}
	public String getModifier_class()
	{
		return modifier_class;
	}
	public void setModifier_class(String modifier_class)
	{
		this.modifier_class = modifier_class;
	}
	
	public Dynamic_parameter(String put_param_name, String get_param_name, String modifier_class)
	{
		super();
		this.put_param_name = put_param_name;
		this.get_param_name = get_param_name;
		this.modifier_class = modifier_class;
	}
	
	public Dynamic_parameter()
	{
		super();
	}
}
