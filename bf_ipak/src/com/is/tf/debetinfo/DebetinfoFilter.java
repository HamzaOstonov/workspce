package com.is.tf.debetinfo;

import java.util.Date;

public class DebetinfoFilter {
	private Long id;
    private int p0t31;
    private String p2t31;
    private Double p3t31;
    private int p5t31;
    private String p6t31;
    private int p7t31;
    private Long id_contract;
    private String p100t31;
    private String p4t31;
    private String p8t31;
    private String p11t31;
    private String p12t31;
    private Date p13t31;
    private String p1t31;

    public DebetinfoFilter() {
    	super();
    }

	public DebetinfoFilter(Long id, int p0t31, String p2t31, Double p3t31, int p5t31,
			String p6t31, int p7t31, Long id_contract, String p100t31,
			String p4t31, String p8t31, String p11t31, String p12t31,
			Date p13t31, String p1t31) {
		super();
		this.id = id;
		this.p0t31 = p0t31;
		this.p2t31 = p2t31;
		this.p3t31 = p3t31;
		this.p5t31 = p5t31;
		this.p6t31 = p6t31;
		this.p7t31 = p7t31;
		this.id_contract = id_contract;
		this.p100t31 = p100t31;
		this.p4t31 = p4t31;
		this.p8t31 = p8t31;
		this.p11t31 = p11t31;
		this.p12t31 = p12t31;
		this.p13t31 = p13t31;
		this.p1t31 = p1t31;
	}
	
	public DebetinfoFilter(String p2t31, Double p3t31, int p5t31,
			String p6t31, String p4t31, String p8t31, String p11t31, 
			String p12t31, Date p13t31, String p1t31) 
	{
		super();
		this.p2t31 = p2t31;
		this.p3t31 = p3t31;
		this.p5t31 = p5t31;
		this.p6t31 = p6t31;
		this.p4t31 = p4t31;
		this.p8t31 = p8t31;
		this.p11t31 = p11t31;
		this.p12t31 = p12t31;
		this.p13t31 = p13t31;
		this.p1t31 = p1t31;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getP0t31() {
		return p0t31;
	}

	public void setP0t31(int p0t31) {
		this.p0t31 = p0t31;
	}

	public String getP2t31() {
		return p2t31;
	}

	public void setP2t31(String p2t31) {
		this.p2t31 = p2t31;
	}

	public Double getP3t31() {
		return p3t31;
	}

	public void setP3t31(Double p3t31) {
		this.p3t31 = p3t31;
	}

	public int getP5t31() {
		return p5t31;
	}

	public void setP5t31(int p5t31) {
		this.p5t31 = p5t31;
	}

	public String getP6t31() {
		return p6t31;
	}

	public void setP6t31(String p6t31) {
		this.p6t31 = p6t31;
	}

	public int getP7t31() {
		return p7t31;
	}

	public void setP7t31(int p7t31) {
		this.p7t31 = p7t31;
	}

	public Long getId_contract() {
		return id_contract;
	}

	public void setId_contract(Long id_contract) {
		this.id_contract = id_contract;
	}

	public String getP100t31() {
		return p100t31;
	}

	public void setP100t31(String p100t31) {
		this.p100t31 = p100t31;
	}

	public String getP4t31() {
		return p4t31;
	}

	public void setP4t31(String p4t31) {
		this.p4t31 = p4t31;
	}

	public String getP8t31() {
		return p8t31;
	}

	public void setP8t31(String p8t31) {
		this.p8t31 = p8t31;
	}

	public String getP11t31() {
		return p11t31;
	}

	public void setP11t31(String p11t31) {
		this.p11t31 = p11t31;
	}

	public String getP12t31() {
		return p12t31;
	}

	public void setP12t31(String p12t31) {
		this.p12t31 = p12t31;
	}

	public Date getP13t31() {
		return p13t31;
	}

	public void setP13t31(Date p13t31) {
		this.p13t31 = p13t31;
	}

	public String getP1t31() {
		return p1t31;
	}

	public void setP1t31(String p1t31) {
		this.p1t31 = p1t31;
	}

	

		
}
