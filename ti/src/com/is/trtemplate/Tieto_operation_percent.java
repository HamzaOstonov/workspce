package com.is.trtemplate;

public class Tieto_operation_percent
{
	private double percent;
	private int rounding_type;
	public double getPercent()
	{
		return percent;
	}
	public void setPercent(double percent)
	{
		this.percent = percent;
	}
	public int getRounding_type()
	{
		return rounding_type;
	}
	public void setRounding_type(int rounding_type)
	{
		this.rounding_type = rounding_type;
	}
	public Tieto_operation_percent(double percent, int rounding_type)
	{
		super();
		this.percent = percent;
		this.rounding_type = rounding_type;
	}
}
