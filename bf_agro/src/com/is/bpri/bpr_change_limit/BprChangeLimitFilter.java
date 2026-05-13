package com.is.bpri.bpr_change_limit;


public class BprChangeLimitFilter
{    
	
	private int id;
	private int bpr_id;
	private int month_value;
	private int procent;

public BprChangeLimitFilter() 
{

}

public BprChangeLimitFilter(int id, int bpr_id, int month_value, int procent)
{
	super();
	this.id = id;
	this.bpr_id = bpr_id;
	this.month_value = month_value;
	this.procent = procent;
}


public int getId()
{
	return id;
}

public void setId(int id)
{
	this.id = id;
}

public int getBpr_id()
{
	return bpr_id;
}

public void setBpr_id(int bpr_id)
{
	this.bpr_id = bpr_id;
}

public int getMonth_value()
{
	return month_value;
}

public void setMonth_value(int month_value)
{
	this.month_value = month_value;
}

public int getProcent()
{
	return procent;
}

public void setProcent(int procent)
{
	this.procent = procent;
}


}
