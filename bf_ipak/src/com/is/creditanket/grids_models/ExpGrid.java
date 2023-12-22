package com.is.creditanket.grids_models;

public class ExpGrid {

	private String branch;
	private Long id;
	private String exp_id;
	private String rate_type;
	private String rate_method;
	private String pay_method;
	private RateGrid[] rates;
	
	public ExpGrid() {
	
	}

	public ExpGrid(String branch, Long id, String exp_id, String rate_type,
			String rate_method, String pay_method, RateGrid[] rates) {
		super();
		this.branch = branch;
		this.id = id;
		this.exp_id = exp_id;
		this.rate_type = rate_type;
		this.rate_method = rate_method;
		this.pay_method = pay_method;
		this.rates = rates;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getRate_type() {
		return rate_type;
	}

	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}

	public String getRate_method() {
		return rate_method;
	}

	public void setRate_method(String rate_method) {
		this.rate_method = rate_method;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public RateGrid[] getRates() {
		return rates;
	}

	public void setRates(RateGrid[] rates) {
		this.rates = rates;
	}
	
}
