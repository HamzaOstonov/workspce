package com.is.creditanket.grids_models;

import java.math.BigDecimal;
import java.util.Date;

public class RateGrid {

	private String exp_id;
	private String rate_id;
	private BigDecimal rate;
	private BigDecimal coeff;
	private Date date_open;
	private Date date_close;
	private String act;
	
	public RateGrid() {
	
	}

	public RateGrid(String exp_id, String rate_id, BigDecimal rate,
			BigDecimal coeff, Date date_open, Date date_close, String act) {
		super();
		this.exp_id = exp_id;
		this.rate_id = rate_id;
		this.rate = rate;
		this.coeff = coeff;
		this.date_open = date_open;
		this.date_close = date_close;
		this.act = act;
	}

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getRate_id() {
		return rate_id;
	}

	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getCoeff() {
		return coeff;
	}

	public void setCoeff(BigDecimal coeff) {
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
	
}
