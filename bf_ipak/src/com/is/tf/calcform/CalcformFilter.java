package com.is.tf.calcform;

import java.util.Date;

public class CalcformFilter
{
	private Long id;
	private String p0t16;
	private String p1t16;
	private String p2t16;
	private Date p3t16;
	private String p4t16;
	private Date p5t16;
	private String p6t16;
	private String p10t16;
	private Date p11t16;
	private Short p100t16;
	private Long id_contract;
	
	public CalcformFilter()
	{
		super();
	}
	
	
	
	public CalcformFilter(Long id, String p0t16, String p1t16, String p2t16, Date p3t16, String p4t16, Date p5t16, String p6t16, String p10t16, Date p11t16, Short p100t16, Long id_contract)
	{
		super();
		this.id = id;
		this.p0t16 = p0t16;
		this.p1t16 = p1t16;
		this.p2t16 = p2t16;
		this.p3t16 = p3t16;
		this.p4t16 = p4t16;
		this.p5t16 = p5t16;
		this.p6t16 = p6t16;
		this.p10t16 = p10t16;
		this.p11t16 = p11t16;
		this.p100t16 = p100t16;
		this.id_contract = id_contract;
	}



	public CalcformFilter(String p1t16, String p2t16,
			Date p3t16, String p4t16, Date p5t16, String p6t16, String p10t16)
	{
		super();
		this.p1t16 = p1t16;
		this.p2t16 = p2t16;
		this.p3t16 = p3t16;
		this.p4t16 = p4t16;
		this.p5t16 = p5t16;
		this.p6t16 = p6t16;
		this.p10t16 = p10t16;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getP0t16()
	{
		return p0t16;
	}
	
	public void setP0t16(String p0t16)
	{
		this.p0t16 = p0t16;
	}
	
	public String getP1t16()
	{
		return p1t16;
	}
	
	public void setP1t16(String p1t16)
	{
		this.p1t16 = p1t16;
	}
	
	public String getP2t16()
	{
		return p2t16;
	}
	
	public void setP2t16(String p2t16)
	{
		this.p2t16 = p2t16;
	}
	
	public Date getP3t16()
	{
		return p3t16;
	}
	
	public void setP3t16(Date p3t16)
	{
		this.p3t16 = p3t16;
	}
	
	public String getP4t16()
	{
		return p4t16;
	}
	
	public void setP4t16(String p4t16)
	{
		this.p4t16 = p4t16;
	}
	
	public Date getP5t16()
	{
		return p5t16;
	}
	
	public void setP5t16(Date p5t16)
	{
		this.p5t16 = p5t16;
	}
	
	public String getP6t16()
	{
		return p6t16;
	}
	
	public void setP6t16(String p6t16)
	{
		this.p6t16 = p6t16;
	}
	
	public String getP10t16()
	{
		return p10t16;
	}
	
	public void setP10t16(String p10t16)
	{
		this.p10t16 = p10t16;
	}
	
	public Date getP11t16()
	{
		return p11t16;
	}
	
	public void setP11t16(Date p11t16)
	{
		this.p11t16 = p11t16;
	}
	
	public Short getP100t16()
	{
		return p100t16;
	}
	
	public void setP100t16(Short p100t16)
	{
		this.p100t16 = p100t16;
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
