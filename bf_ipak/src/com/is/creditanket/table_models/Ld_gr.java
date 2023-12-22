package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_gr {

	private String branch;
	private Long id;
	private Long oper_id;
	private String exp_id;
	private Integer graf_method;
	private Integer ext_method;
	private Integer num;
	private Integer pay_period;
	private Integer day;
	private Date date_from;
	private Date date_to;
	private Double dsumma;
	private Long pk;
	private Ld_graf[] ldgrafs;
	
	public Ld_gr() {
	
	}

	public Ld_gr(String branch, Long id, Long oper_id, String exp_id,
			Integer graf_method, Integer ext_method, Integer num,
			Integer pay_period, Integer day, Date date_from, Date date_to,
			Double dsumma, Long pk, Ld_graf[] ldgrafs) {
		super();
		this.branch = branch;
		this.id = id;
		this.oper_id = oper_id;
		this.exp_id = exp_id;
		this.graf_method = graf_method;
		this.ext_method = ext_method;
		this.num = num;
		this.pay_period = pay_period;
		this.day = day;
		this.date_from = date_from;
		this.date_to = date_to;
		this.dsumma = dsumma;
		this.pk = pk;
		this.ldgrafs = ldgrafs;
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

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public Integer getGraf_method() {
		return graf_method;
	}

	public void setGraf_method(Integer graf_method) {
		this.graf_method = graf_method;
	}

	public Integer getExt_method() {
		return ext_method;
	}

	public void setExt_method(Integer ext_method) {
		this.ext_method = ext_method;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPay_period() {
		return pay_period;
	}

	public void setPay_period(Integer pay_period) {
		this.pay_period = pay_period;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Date getDate_from() {
		return date_from;
	}

	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDate_to() {
		return date_to;
	}

	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}

	public Double getDsumma() {
		return dsumma;
	}

	public void setDsumma(Double dsumma) {
		this.dsumma = dsumma;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Ld_graf[] getLdgrafs() {
		return ldgrafs;
	}

	public void setLdgrafs(Ld_graf[] ldgrafs) {
		this.ldgrafs = ldgrafs;
	}
	
}
