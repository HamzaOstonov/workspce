package com.is.tf.endoperation;

import java.util.Date;

public class Endoperation
{
	private Long id;
	private String p0t12;
	private Date p1t12;
	private String p2t12;
	private Date p3t12;
	private Long id_contract;
	private Short p100t12;
	
	public Endoperation()
	{
		super();
	}
	
	
	public Endoperation(Long id, String p0t12, Date p1t12, String p2t12, Date p3t12, Long id_contract, Short p100t12)
	{
		super();
		this.id = id;
		this.p0t12 = p0t12;
		this.p1t12 = p1t12;
		this.p2t12 = p2t12;
		this.p3t12 = p3t12;
		this.id_contract = id_contract;
		this.p100t12 = p100t12;
	}


	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getP0t12()
	{
		return p0t12;
	}
	
	public void setP0t12(String p0t12)
	{
		this.p0t12 = p0t12;
	}
	
	public Date getP1t12()
	{
		return p1t12;
	}
	
	public void setP1t12(Date p1t12)
	{
		this.p1t12 = p1t12;
	}
	
	public String getP2t12()
	{
		return p2t12;
	}
	
	public void setP2t12(String p2t12)
	{
		this.p2t12 = p2t12;
	}

	public Long getId_contract()
	{
		return id_contract;
	}

	public void setId_contract(Long id_contract)
	{
		this.id_contract = id_contract;
	}

	public Short getP100t12()
	{
		return p100t12;
	}

	public void setP100t12(Short p100t12)
	{
		this.p100t12 = p100t12;
	}


	public Date getP3t12()
	{
		return p3t12;
	}


	public void setP3t12(Date p3t12)
	{
		this.p3t12 = p3t12;
	}
	
}
