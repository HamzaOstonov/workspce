package com.is.utils;

public class OBJ_Res
{
	 private int code;
	 private Object obj;
	 
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public Object getObj()
	{
		return obj;
	}
	public void setObj(Object obj)
	{
		this.obj = obj;
	}
	public OBJ_Res(int code, Object obj)
	{
		super();
		this.code = code;
		this.obj = obj;
	}
	public OBJ_Res()
	{
		super();
	}
}
