package com.is.sd_books.model;

import java.sql.Date;

public class Percentage {

	private long id;
	private Date v_date;
	private String branch;
	private long book_id;
	private int code;
	private double saldo;
	private int pc;
	private long emp;
	private Date bank_date;
	private long general_id;
	private int fx_code;
	private String note;
	private double turn_cr;
	private double turn_db;
	
	public Percentage() {
		super();
	}


	public Percentage(long id, Date v_date, String branch, long book_id,
			int code, double saldo, int pc, long emp, Date bank_date,
			long general_id, int fx_code, String note, double turn_cr,
			double turn_db) {
		super();
		this.id = id;
		this.v_date = v_date;
		this.branch = branch;
		this.book_id = book_id;
		this.code = code;
		this.saldo = saldo;
		this.pc = pc;
		this.emp = emp;
		this.bank_date = bank_date;
		this.general_id = general_id;
		this.fx_code = fx_code;
		this.note = note;
		this.turn_cr = turn_cr;
		this.turn_db = turn_db;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getV_date() {
		return v_date;
	}

	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public long getEmp() {
		return emp;
	}

	public void setEmp(long emp) {
		this.emp = emp;
	}

	public Date getBank_date() {
		return bank_date;
	}

	public void setBank_date(Date bank_date) {
		this.bank_date = bank_date;
	}

	public long getGeneral_id() {
		return general_id;
	}

	public void setGeneral_id(long general_id) {
		this.general_id = general_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getFx_code() {
		return fx_code;
	}

	public void setFx_code(int fx_code) {
		this.fx_code = fx_code;
	}

	public double getTurn_cr() {
		return turn_cr;
	}

	public void setTurn_cr(double turn_cr) {
		this.turn_cr = turn_cr;
	}

	public double getTurn_db() {
		return turn_db;
	}

	public void setTurn_db(double turn_db) {
		this.turn_db = turn_db;
	}
}
