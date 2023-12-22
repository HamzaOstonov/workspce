package com.is.tf.Act;

import java.util.Date;

public class Act
{
	private Long id;
	private String p0t7;
	private Date p1t7;
	private String p2t7;
	private String p3t7;
	private Double p4t7;
	private Double p5t7;
	
	private String p6t7;
	private Date p9t7;
	private Short p100t7;
	private Long id_contract; 
	
	public Act()
	{
		super();
	}
	
	public Act(Long id, String p0t7, Date p1t7, String p2t7, String p3t7,
			Double p4t7, Double p5t7, Long id_contract, String p6t7, Date p9t7, Short p100t7)
	{
		super();
		this.id = id;
		this.p0t7 = p0t7;
		this.p1t7 = p1t7;
		this.p2t7 = p2t7;
		this.p3t7 = p3t7;
		this.p4t7 = p4t7;
		this.p5t7 = p5t7;
		this.id_contract = id_contract;
		this.p6t7 = p6t7;
		this.p9t7 = p9t7;
		this.p100t7 = p100t7;
	}
	
	public Act(Long id, String p0t7, Date p1t7, String p2t7, String p3t7,
			Double p4t7, Double p5t7, Long id_contract, String p6t7)
	{
		super();
		this.id = id;
		this.p0t7 = p0t7;
		this.p1t7 = p1t7;
		this.p2t7 = p2t7;
		this.p3t7 = p3t7;
		this.p4t7 = p4t7;
		this.p5t7 = p5t7;
		this.id_contract = id_contract;
		this.p6t7 = p6t7;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getP0t7()
	{
		return p0t7;
	}
	
	public void setP0t7(String p0t7)
	{
		this.p0t7 = p0t7;
	}
	
	public Date getP1t7()
	{
		return p1t7;
	}
	
	public void setP1t7(Date p1t7)
	{
		this.p1t7 = p1t7;
	}
	
	public String getP2t7()
	{
		return p2t7;
	}
	
	public void setP2t7(String p2t7)
	{
		this.p2t7 = p2t7;
	}
	
	public String getP3t7()
	{
		return p3t7;
	}
	
	public void setP3t7(String p3t7)
	{
		this.p3t7 = p3t7;
	}
	
	public Double getP4t7()
	{
		return p4t7;
	}
	
	public void setP4t7(Double p4t7)
	{
		this.p4t7 = p4t7;
	}
	
	public Double getP5t7()
	{
		return p5t7;
	}
	
	public void setP5t7(Double p5t7)
	{
		this.p5t7 = p5t7;
	}

	public String getP6t7()
	{
		return p6t7;
	}

	public void setP6t7(String p6t7)
	{
		this.p6t7 = p6t7;
	}

	public Date getP9t7()
	{
		return p9t7;
	}

	public void setP9t7(Date p9t7)
	{
		this.p9t7 = p9t7;
	}

	public Short getP100t7()
	{
		return p100t7;
	}

	public void setP100t7(Short p100t7)
	{
		this.p100t7 = p100t7;
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
