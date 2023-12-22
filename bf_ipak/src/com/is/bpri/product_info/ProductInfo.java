package com.is.bpri.product_info;

public class ProductInfo {

	private Product_ld_char ld_char;
	private Product_ld_exp [] ld_exps;
	private String [] ld_guarr_type;
	
	public ProductInfo() {
	
	}

	public ProductInfo(Product_ld_char ld_char, Product_ld_exp[] ld_exps,
			String[] ld_guarr_type) {
		super();
		this.ld_char = ld_char;
		this.ld_exps = ld_exps;
		this.ld_guarr_type = ld_guarr_type;
	}

	public Product_ld_char getLd_char() {
		return ld_char;
	}

	public void setLd_char(Product_ld_char ld_char) {
		this.ld_char = ld_char;
	}

	public Product_ld_exp[] getLd_exps() {
		return ld_exps;
	}

	public void setLd_exps(Product_ld_exp[] ld_exps) {
		this.ld_exps = ld_exps;
	}

	public String[] getLd_guarr_type() {
		return ld_guarr_type;
	}

	public void setLd_guarr_type(String[] ld_guarr_type) {
		this.ld_guarr_type = ld_guarr_type;
	}
	
}
