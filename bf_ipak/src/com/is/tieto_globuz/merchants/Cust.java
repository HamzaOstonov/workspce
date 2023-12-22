package com.is.tieto_globuz.merchants;

public class Cust
{
	private String Nibbd;
	private String Full_Name;
	/**
	 * 
	 */
	public Cust()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nibbd
	 * @param full_Name
	 */
	public Cust(String nibbd, String full_Name)
	{
		super();
		this.Nibbd = nibbd;
		this.Full_Name = full_Name;
	}
	public String getNibbd()
	{
		return this.Nibbd;
	}
	public void setNibbd(String nibbd)
	{
		this.Nibbd = nibbd;
	}
	public String getFull_Name()
	{
		return this.Full_Name;
	}
	public void setFull_Name(String full_Name)
	{
		this.Full_Name = full_Name;
	}
	
	
}
