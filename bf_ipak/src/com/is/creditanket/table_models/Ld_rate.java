package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_rate {

	private String branch;
	private Long id;
	private Long exp_id;
	private Long rate_id;
	private Double rate;
	private Double coeff;
	private Date date_open;
	private Date date_close;
	private String act;
	private boolean isNew = false;
	
	public Ld_rate() {
	
	}

	public Ld_rate(String branch, Long id, Long exp_id, Long rate_id,
			Double rate, Double coeff, Date date_open, Date date_close, String act) {
		super();
		this.branch = branch;
		this.id = id;
		this.exp_id = exp_id;
		this.rate_id = rate_id;
		this.rate = rate;
		this.coeff = coeff;
		this.date_open = date_open;
		this.date_close = date_close;
		this.act = act;
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

	public Long getRate_id() {
		return rate_id;
	}

	public void setRate_id(Long rate_id) {
		this.rate_id = rate_id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getCoeff() {
		return coeff;
	}

	public void setCoeff(Double coeff) {
		this.coeff = coeff;
	}

	public Date getDate_open() {
		return date_open;
	}

	public void setDate_open(Date date_open) {
		this.date_open = date_open;
	}

	public Date getDate_close() {
		return date_close;
	}

	public void setDate_close(Date date_close) {
		this.date_close = date_close;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
