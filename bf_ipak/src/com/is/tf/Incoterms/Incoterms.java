package com.is.tf.Incoterms;

import java.util.Date;

public class Incoterms
{
	private Long id;
	private String p0t9;
	private String p1t9;
	private String p2t9;
	private Long id_contract;
	private String p3t9;
	private String p7t9;
	private int p8t9;
	private Date p9t9;
	private Short p100t9;
	
	public Incoterms()
	{
		super();
	}
	
	public Incoterms(Long id, String p0t9, String p1t9, String p2t9, String p3t9, String p7t9, int p8t9, Date p9t9, Short p100t9)
	{
		super();
		this.id = id;
		this.p0t9 = p0t9;
		this.p1t9 = p1t9;
		this.p2t9 = p2t9;
		this.p3t9 = p3t9;
		this.p7t9 = p7t9;
		this.p8t9 = p8t9;
		this.p9t9 = p9t9;
		this.p100t9 = p100t9;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getP0t9()
	{
		return p0t9;
	}
	
	public void setP0t9(String p0t9)
	{
		this.p0t9 = p0t9;
	}
	
	public String getP1t9()
	{
		return p1t9;
	}
	
	public void setP1t9(String p1t9)
	{
		this.p1t9 = p1t9;
	}
	
	public String getP2t9()
	{
		return p2t9;
	}
	
	public void setP2t9(String p2t9)
	{
		this.p2t9 = p2t9;
	}
	
	public String getP3t9()
	{
		return p3t9;
	}
	
	public void setP3t9(String p3t9)
	{
		this.p3t9 = p3t9;
	}
	
	public String getP7t9()
	{
		return p7t9;
	}
	
	public void setP7t9(String p7t9)
	{
		this.p7t9 = p7t9;
	}
	
	public int getP8t9()
	{
		return p8t9;
	}
	
	public void setP8t9(int p8t9)
	{
		this.p8t9 = p8t9;
	}
	
	public Date getP9t9()
	{
		return p9t9;
	}
	
	public void setP9t9(Date p9t9)
	{
		this.p9t9 = p9t9;
	}
	
	public Short getP100t9()
	{
		return p100t9;
	}
	
	public void setP100t9(Short p100t9)
	{
		this.p100t9 = p100t9;
	}

	public Long getId_contract()
	{
		return id_contract;
	}

	public void setId_contract(Long id_contract)
	{
		this.id_contract = id_contract;
	}
	
}
