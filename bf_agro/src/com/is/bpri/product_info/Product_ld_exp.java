package com.is.bpri.product_info;

public class Product_ld_exp {
	
	private String exp_id;
	private String rate;
	
	public Product_ld_exp() {
	
	}

	public Product_ld_exp(String exp_id, String rate) {
		this.exp_id = exp_id;
		this.rate = rate;
	}

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
