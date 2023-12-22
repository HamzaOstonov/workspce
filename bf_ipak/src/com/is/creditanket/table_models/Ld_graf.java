package com.is.creditanket.table_models;

import java.math.BigDecimal;
import java.util.Date;

public class Ld_graf {

	private String branch;
	private Long id;
	private Long oper_id;
	private Long exp_id;
	private Date v_date;
	private Double summa;
	private Long status;
	private Long num;
	private Integer repay;
	private BigDecimal sum;
	
	public Ld_graf() {
	
	}
	
	public Ld_graf(String branch, Long id, Long oper_id, Long exp_id,
			Date v_date, Double summa, Long status, Long num, Integer repay,BigDecimal sum) {
		super();
		this.branch = branch;
		this.id = id;
		this.oper_id = oper_id;
		this.exp_id = exp_id;
		this.v_date = v_date;
		this.summa = summa;
		this.status = status;
		this.num = num;
		this.repay = repay;
		this.sum = sum;
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

	public Long getOper_id() {
		return oper_id;
	}

	public void setOper_id(Long oper_id) {
		this.oper_id = oper_id;
	}

	public Long getExp_id() {
		return exp_id;
	}

	public void setExp_id(Long exp_id) {
		this.exp_id = exp_id;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public Double getSumma() {
		return summa;
	}

	public void setSumma(Double summa) {
		this.summa = summa;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Integer getRepay() {
		return repay;
	}

	public void setRepay(Integer repay) {
		this.repay = repay;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
}
