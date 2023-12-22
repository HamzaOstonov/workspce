package com.is.sd_books.model;

import java.util.Date;

public class Deposit {
	private String id_client;
	private String filial;
	private String dep;
	private String operTurn;
	private double sum;
	private Date operDate;
	private int emp_id;
	private String p_ser;
	private String p_num;

	public Deposit() {
		super();
	}

	public Deposit(String filial, String dep, String operTurn, double sum,
			String id_client, Date operDate, String p_ser, String p_num,
			int emp_id) {
		super();
		this.filial = filial;
		this.dep = dep;
		this.operTurn = operTurn;
		this.sum = sum;
		this.id_client = id_client;
		this.operDate = operDate;
		this.emp_id = emp_id;
		this.p_ser = p_ser;
		this.p_num = p_num;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getOperTurn() {
		return operTurn;
	}

	public void setOperTurn(String operTurn) {
		this.operTurn = operTurn;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getP_ser() {
		return p_ser;
	}

	public void setP_ser(String p_ser) {
		this.p_ser = p_ser;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	@Override
	public String toString() {
		return "Deposit [id_client=" + id_client + ", filial=" + filial
				+ ", dep=" + dep + ", operTurn=" + operTurn + ", sum=" + sum
				+ ", operDate=" + operDate + ", emp_id=" + emp_id + ", p_ser="
				+ p_ser + ", p_num=" + p_num + "]";
	}
}
