package com.is.bpri;

import java.util.Date;

public class BproductFilter {
	
    private Integer id;
    private String branch;
    private String customer;
    private Integer prodid;
    private Date vdate;
    private String currency;
    private Double amount;
    private Integer emp_id;
    private Integer state;

    public BproductFilter() {

    }

	public BproductFilter(Integer id, String branch, String customer, Integer prodid,
			Date vdate, String currency, Double amount, Integer emp_id, Integer state) {
		super();
		this.id = id;
		this.branch = branch;
		this.customer = customer;
		this.prodid = prodid;
		this.vdate = vdate;
		this.currency = currency;
		this.amount = amount;
		this.emp_id = emp_id;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getProdid() {
		return prodid;
	}

	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
}
