package com.is.tf.shipment;

import java.util.Date;

public class ShipmentFilter
{
	private Long id;
	private String p0t14;
	private Date p1t14;
	private String p2t14;
	private String p3t14;
	private Double p4t14;
	
	private Date p8t14;
	private String p7t14;
	private Short p100t14;
	private Long id_contract;
	
	public ShipmentFilter()
	{
		super();
	}
	
	public ShipmentFilter(Long id, String p0t14, Date p1t14, String p2t14,
			String p3t14, Double p4t14)
	{
		super();
		this.id = id;
		this.p0t14 = p0t14;
		this.p1t14 = p1t14;
		this.p2t14 = p2t14;
		this.p3t14 = p3t14;
		this.p4t14 = p4t14;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getP0t14()
	{
		return p0t14;
	}
	
	public void setP0t14(String p0t14)
	{
		this.p0t14 = p0t14;
	}
	
	public Date getP1t14()
	{
		return p1t14;
	}
	
	public void setP1t14(Date p1t14)
	{
		this.p1t14 = p1t14;
	}
	
	public String getP2t14()
	{
		return p2t14;
	}
	
	public void setP2t14(String p2t14)
	{
		this.p2t14 = p2t14;
	}
	
	public String getP3t14()
	{
		return p3t14;
	}
	
	public void setP3t14(String p3t14)
	{
		this.p3t14 = p3t14;
	}
	
	public Double getP4t14()
	{
		return p4t14;
	}
	
	public void setP4t14(Double p4t14)
	{
		this.p4t14 = p4t14;
	}

	public Date getP8t14()
	{
		return p8t14;
	}

	public void setP8t14(Date p8t14)
	{
		this.p8t14 = p8t14;
	}

	public String getP7t14()
	{
		return p7t14;
	}

	public void setP7t14(String p7t14)
	{
		this.p7t14 = p7t14;
	}

	public Short getP100t14()
	{
		return p100t14;
	}

	public void setP100t14(Short p100t14)
	{
		this.p100t14 = p100t14;
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
