package com.is.creditanket.table_models;

public class Ld_exp {
	
	private String branch;
	private Long id;
	private Long exp_id;
	private Long rate_type;
	private Long rate_method;
	private Long pay_method;
	private Long ext_days;
	private Ld_rate[] ld_rates;
	
	public Ld_exp() {
	
	}

	public Ld_exp(String branch, Long id, Long exp_id, Long rate_type,
			Long rate_method, Long pay_method, Long ext_days, Ld_rate[] ld_rates) {
		super();
		this.branch = branch;
		this.id = id;
		this.exp_id = exp_id;
		this.rate_type = rate_type;
		this.rate_method = rate_method;
		this.pay_method = pay_method;
		this.ext_days = ext_days;
		this.ld_rates = ld_rates;
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

	public Long getExp_id() {
		return exp_id;
	}

	public void setExp_id(Long exp_id) {
		this.exp_id = exp_id;
	}

	public Long getRate_type() {
		return rate_type;
	}

	public void setRate_type(Long rate_type) {
		this.rate_type = rate_type;
	}

	public Long getRate_method() {
		return rate_method;
	}

	public void setRate_method(Long rate_method) {
		this.rate_method = rate_method;
	}

	public Long getPay_method() {
		return pay_method;
	}

	public void setPay_method(Long pay_method) {
		this.pay_method = pay_method;
	}

	public Long getExt_days() {
		return ext_days;
	}

	public void setExt_days(Long ext_days) {
		this.ext_days = ext_days;
	}

	public Ld_rate[] getLd_rates() {
		return ld_rates;
	}

	public void setLd_rates(Ld_rate[] ld_tares) {
		this.ld_rates = ld_tares;
	}
	
}
