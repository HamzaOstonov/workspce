package com.is.bpri;

import java.util.Date;

public class Bproduct {
	
    private int id;
    private String branch;
    private int customer;
    private int prodid;
    private Date vdate;
    private String currency;
    private int amount;
    private int emp_id;
    private int state;

    public Bproduct() {

    }

	public Bproduct(int id, String branch, int customer, int prodid,
			Date vdate, String currency, int amount, int emp_id, int state) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    
    

}
