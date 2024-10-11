package com.is.tieto_visae.account;

import java.util.Date;

public class Account {
    private String branch;
    private String id;
    private String acc_bal;
    private String currency;
    private String client;
    private String id_order;
    private String name;
    private String sgn;
    private String bal;
    private int sign_registr;
    private long s_in;
    private long s_out;
    private long dt;
    private long ct;
    private long s_in_tmp;
    private long s_out_tmp;
    private long dt_tmp;
    private long ct_tmp;
    private Date l_date;
    private Date date_open;
    private Date date_close;
    private int acc_group_id;
    private int state;
    private String state_desc;

    public Account() {
    	super();
    }

	public Account(String branch, String id, String acc_bal, String currency,
			String client, String id_order, String name, String sgn,
			String bal, int sign_registr, long s_in, long s_out, long dt,
			long ct, long s_in_tmp, long s_out_tmp, long dt_tmp, long ct_tmp,
			Date l_date, Date date_open, Date date_close, int acc_group_id,
			int state) {
		super();
		this.branch = branch;
		this.id = id;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.client = client;
		this.id_order = id_order;
		this.name = name;
		this.sgn = sgn;
		this.bal = bal;
		this.sign_registr = sign_registr;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.s_in_tmp = s_in_tmp;
		this.s_out_tmp = s_out_tmp;
		this.dt_tmp = dt_tmp;
		this.ct_tmp = ct_tmp;
		this.l_date = l_date;
		this.date_open = date_open;
		this.date_close = date_close;
		this.acc_group_id = acc_group_id;
		this.state = state;
	}
	
	public Account(String branch, String id, String acc_bal, String currency,
			String client, String id_order, String name, String sgn,
			String bal, int sign_registr, long s_in, long s_out, long dt,
			long ct, long s_in_tmp, long s_out_tmp, long dt_tmp, long ct_tmp,
			Date l_date, Date date_open, Date date_close, int acc_group_id,
			int state, String state_desc) {
		super();
		this.branch = branch;
		this.id = id;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.client = client;
		this.id_order = id_order;
		this.name = name;
		this.sgn = sgn;
		this.bal = bal;
		this.sign_registr = sign_registr;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.s_in_tmp = s_in_tmp;
		this.s_out_tmp = s_out_tmp;
		this.dt_tmp = dt_tmp;
		this.ct_tmp = ct_tmp;
		this.l_date = l_date;
		this.date_open = date_open;
		this.date_close = date_close;
		this.acc_group_id = acc_group_id;
		this.state = state;
		this.state_desc = state_desc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcc_bal() {
		return acc_bal;
	}

	public void setAcc_bal(String acc_bal) {
		this.acc_bal = acc_bal;
	}

	public String getState_desc() {
		return state_desc;
	}

	public void setState_desc(String state_desc) {
		this.state_desc = state_desc;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getId_order() {
		return id_order;
	}

	public void setId_order(String id_order) {
		this.id_order = id_order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSgn() {
		return sgn;
	}

	public void setSgn(String sgn) {
		this.sgn = sgn;
	}

	public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}

	public int getSign_registr() {
		return sign_registr;
	}

	public void setSign_registr(int sign_registr) {
		this.sign_registr = sign_registr;
	}

	public long getS_in() {
		return s_in;
	}

	public void setS_in(long s_in) {
		this.s_in = s_in;
	}

	public long getS_out() {
		return s_out;
	}

	public void setS_out(long s_out) {
		this.s_out = s_out;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public long getCt() {
		return ct;
	}

	public void setCt(long ct) {
		this.ct = ct;
	}

	public long getS_in_tmp() {
		return s_in_tmp;
	}

	public void setS_in_tmp(long s_in_tmp) {
		this.s_in_tmp = s_in_tmp;
	}

	public long getS_out_tmp() {
		return s_out_tmp;
	}

	public void setS_out_tmp(long s_out_tmp) {
		this.s_out_tmp = s_out_tmp;
	}

	public long getDt_tmp() {
		return dt_tmp;
	}

	public void setDt_tmp(long dt_tmp) {
		this.dt_tmp = dt_tmp;
	}

	public long getCt_tmp() {
		return ct_tmp;
	}

	public void setCt_tmp(long ct_tmp) {
		this.ct_tmp = ct_tmp;
	}

	public Date getL_date() {
		return l_date;
	}

	public void setL_date(Date l_date) {
		this.l_date = l_date;
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

	public int getAcc_group_id() {
		return acc_group_id;
	}

	public void setAcc_group_id(int acc_group_id) {
		this.acc_group_id = acc_group_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


}
