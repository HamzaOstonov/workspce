package com.is.currexchange;

import java.util.Date;

public class CurrExchange {
    private int id;
    private String bank_dt;
    private String acc_dt;
    private String card_dt;
    private String bank_ct;
    private String acc_ct;
    private String card_ct;
    private long amount;
    private Date ddate;
    private int course;
    private int operation;
    private int extid;
    private int state;
    private String errors;

    public CurrExchange() {

    }

	
	

	public CurrExchange(int id, String bank_dt, String acc_dt, String card_dt,
			String bank_ct, String acc_ct, String card_ct, long amount,
			Date ddate, int course, int operation, int extid, int state,
			String errors) {
		super();
		this.id = id;
		this.bank_dt = bank_dt;
		this.acc_dt = acc_dt;
		this.card_dt = card_dt;
		this.bank_ct = bank_ct;
		this.acc_ct = acc_ct;
		this.card_ct = card_ct;
		this.amount = amount;
		this.ddate = ddate;
		this.course = course;
		this.operation = operation;
		this.extid = extid;
		this.state = state;
		this.errors = errors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBank_dt() {
		return bank_dt;
	}

	public void setBank_dt(String bank_dt) {
		this.bank_dt = bank_dt;
	}

	public String getAcc_dt() {
		return acc_dt;
	}

	public void setAcc_dt(String acc_dt) {
		this.acc_dt = acc_dt;
	}

	public String getCard_dt() {
		return card_dt;
	}

	public void setCard_dt(String card_dt) {
		this.card_dt = card_dt;
	}

	public String getBank_ct() {
		return bank_ct;
	}

	public void setBank_ct(String bank_ct) {
		this.bank_ct = bank_ct;
	}

	public String getAcc_ct() {
		return acc_ct;
	}

	public void setAcc_ct(String acc_ct) {
		this.acc_ct = acc_ct;
	}

	public String getCard_ct() {
		return card_ct;
	}

	public void setCard_ct(String card_ct) {
		this.card_ct = card_ct;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public int getExtid() {
		return extid;
	}

	public void setExtid(int extid) {
		this.extid = extid;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
	
	

    
}
