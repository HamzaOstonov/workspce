package com.is.tf.garant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.tf.garantsumchange.Garantsumchange;
import com.is.tf.garanttimechange.garanttimechange;

public class Garant
{
	private Long id;
	private int p0t18;
	private String p1t18;
	private String p2t18;
	private String p3t18;
	private Date p4t18;
	private String p5t18;
	private Double p6t18;
	private Double p7t18;
	private Double p8t18;
	private Double p9t18;
	private Date p10t18;
	private String p11t18;
	private Long id_contract;
	private String p100t18;
	private String p12t18;
	private String p13t18;
	private String p14t18;
	private String p15t18;
	private Date p16t18;
	private List<Garantsumchange> sumchanges = new ArrayList<Garantsumchange>();
	private List<garanttimechange> timechanges = new ArrayList<garanttimechange>();
	
	public Garant()
	{
		super();
	}
	
	public Garant(Long id, int p0t18, String p1t18, String p2t18, String p3t18,
			Date p4t18, String p5t18, Double p6t18, Double p7t18, Double p8t18,
			Double p9t18, Date p10t18, String p11t18, Long id_contract,
			String p100t18, String p12t18, String p13t18, String p14t18,
			String p15t18, Date p16t18,
			List<Garantsumchange> sumchanges, List<garanttimechange> timechanges)
	{
		super();
		this.id = id;
		this.p0t18 = p0t18;
		this.p1t18 = p1t18;
		this.p2t18 = p2t18;
		this.p3t18 = p3t18;
		this.p4t18 = p4t18;
		this.p5t18 = p5t18;
		this.p6t18 = p6t18;
		this.p7t18 = p7t18;
		this.p8t18 = p8t18;
		this.p9t18 = p9t18;
		this.p10t18 = p10t18;
		this.p11t18 = p11t18;
		this.id_contract = id_contract;
		this.p100t18 = p100t18;
		this.p12t18 = p12t18;
		this.p13t18 = p13t18;
		this.p14t18 = p14t18;
		this.p15t18 = p15t18;
		this.p16t18 = p16t18;
		this.sumchanges = sumchanges;
		this.timechanges = timechanges;
	}
	
	public Garant(Long id, int p0t18, String p1t18, String p2t18, String p3t18,
			Date p4t18, String p5t18, Double p6t18, Double p7t18, Double p8t18,
			Double p9t18, Date p10t18, String p11t18, Long id_contract,
			String p100t18, String p12t18, String p13t18, String p14t18,
			String p15t18, Date p16t18)
	{
		super();
		this.id = id;
		this.p0t18 = p0t18;
		this.p1t18 = p1t18;
		this.p2t18 = p2t18;
		this.p3t18 = p3t18;
		this.p4t18 = p4t18;
		this.p5t18 = p5t18;
		this.p6t18 = p6t18;
		this.p7t18 = p7t18;
		this.p8t18 = p8t18;
		this.p9t18 = p9t18;
		this.p10t18 = p10t18;
		this.p11t18 = p11t18;
		this.id_contract = id_contract;
		this.p100t18 = p100t18;
		this.p12t18 = p12t18;
		this.p13t18 = p13t18;
		this.p14t18 = p14t18;
		this.p15t18 = p15t18;
		this.p16t18 = p16t18;
		
	}
	
	public Garant(String p2t18, String p3t18, Date p4t18, String p5t18,
			Double p6t18, Double p7t18, Double p8t18, Double p9t18,
			Date p10t18, String p11t18, String p12t18)
	{
		super();
		this.p2t18 = p2t18;
		this.p3t18 = p3t18;
		this.p4t18 = p4t18;
		this.p5t18 = p5t18;
		this.p6t18 = p6t18;
		this.p7t18 = p7t18;
		this.p8t18 = p8t18;
		this.p9t18 = p9t18;
		this.p10t18 = p10t18;
		this.p11t18 = p11t18;
		this.p12t18 = p12t18;
		// this.p15t18 = p15t18;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public int getP0t18()
	{
		return p0t18;
	}
	
	public void setP0t18(int p0t18)
	{
		this.p0t18 = p0t18;
	}
	
	public String getP1t18()
	{
		return p1t18;
	}
	
	public void setP1t18(String p1t18)
	{
		this.p1t18 = p1t18;
	}
	
	public String getP2t18()
	{
		return p2t18;
	}
	
	public void setP2t18(String p2t18)
	{
		this.p2t18 = p2t18;
	}
	
	public String getP3t18()
	{
		return p3t18;
	}
	
	public void setP3t18(String p3t18)
	{
		this.p3t18 = p3t18;
	}
	
	public Date getP4t18()
	{
		return p4t18;
	}
	
	public void setP4t18(Date p4t18)
	{
		this.p4t18 = p4t18;
	}
	
	public String getP5t18()
	{
		return p5t18;
	}
	
	public void setP5t18(String p5t18)
	{
		this.p5t18 = p5t18;
	}
	
	public Double getP6t18()
	{
		return p6t18;
	}
	
	public void setP6t18(Double p6t18)
	{
		this.p6t18 = p6t18;
	}
	
	public Double getP7t18()
	{
		return p7t18;
	}
	
	public void setP7t18(Double p7t18)
	{
		this.p7t18 = p7t18;
	}
	
	public Double getP8t18()
	{
		return p8t18;
	}
	
	public void setP8t18(Double p8t18)
	{
		this.p8t18 = p8t18;
	}
	
	public Double getP9t18()
	{
		return p9t18;
	}
	
	public void setP9t18(Double p9t18)
	{
		this.p9t18 = p9t18;
	}
	
	public Date getP10t18()
	{
		return p10t18;
	}
	
	public void setP10t18(Date p10t18)
	{
		this.p10t18 = p10t18;
	}
	
	public String getP11t18()
	{
		return p11t18;
	}
	
	public void setP11t18(String p11t18)
	{
		this.p11t18 = p11t18;
	}
	
	public Long getId_contract()
	{
		return id_contract;
	}
	
	public void setId_contract(Long id_contract)
	{
		this.id_contract = id_contract;
	}
	
	public String getP100t18()
	{
		return p100t18;
	}
	
	public void setP100t18(String p100t18)
	{
		this.p100t18 = p100t18;
	}
	
	public String getP12t18()
	{
		return p12t18;
	}
	
	public void setP12t18(String p12t18)
	{
		this.p12t18 = p12t18;
	}
	
	public String getP13t18()
	{
		return p13t18;
	}
	
	public void setP13t18(String p13t18)
	{
		this.p13t18 = p13t18;
	}
	
	public String getP14t18()
	{
		return p14t18;
	}
	
	public void setP14t18(String p14t18)
	{
		this.p14t18 = p14t18;
	}
	
	public String getP15t18()
	{
		return p15t18;
	}
	
	public void setP15t18(String p15t18)
	{
		this.p15t18 = p15t18;
	}
	
	public Date getP16t18()
	{
		return p16t18;
	}
	
	public void setP16t18(Date p16t18)
	{
		this.p16t18 = p16t18;
	}
	
	public List<Garantsumchange> getSumchanges()
	{
		return sumchanges;
	}
	
	public void setSumchanges(List<Garantsumchange> sumchanges)
	{
		this.sumchanges = sumchanges;
	}
	
	public List<garanttimechange> getTimechanges()
	{
		return timechanges;
	}
	
	public void setTimechanges(List<garanttimechange> timechanges)
	{
		this.timechanges = timechanges;
	}
	
}
