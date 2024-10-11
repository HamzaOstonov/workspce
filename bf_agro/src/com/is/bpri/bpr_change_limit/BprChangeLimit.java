package com.is.bpri.bpr_change_limit;
import java.io.Serializable;

public class BprChangeLimit implements Serializable 
{
    static final long serialVersionUID = 103844514947365244L;

		
    private int id;
	private String bpr_id;
	private String month_value;
    private String procent;
    private String day;

    public BprChangeLimit() 
    {

    }

    public BprChangeLimit(int id, String bpr_id, String month_value, String procent,String day)
	{
		super();
		this.id = id;
		this.bpr_id = bpr_id;
		this.month_value = month_value;
		this.procent = procent;
		this.day = day;
	}
    
    
    public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBpr_id()
	{
		return bpr_id;
	}

	public void setBpr_id(String bpr_id)
	{
		this.bpr_id = bpr_id;
	}

	public String getMonth_value()
	{
		return month_value;
	}

	public void setMonth_value(String month_value)
	{
		this.month_value = month_value;
	}

	public String getProcent()
	{
		return procent;
	}

	public void setProcent(String procent)
	{
		this.procent = procent;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
