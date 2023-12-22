package com.is.account;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    @Getter @Setter private String branch;
    @Getter @Setter private String id;
    @Getter @Setter private String acc_bal;
    @Getter @Setter private String currency;
    @Getter @Setter private String client;
    @Getter @Setter private String id_order;
    @Getter @Setter private String name;
    @Getter @Setter private String sgn;
    @Getter @Setter private String bal;
    @Getter @Setter private int sign_registr;
    @Getter @Setter private BigDecimal s_in;
    @Getter @Setter private BigDecimal s_out;
    @Getter @Setter private BigDecimal dt;
    @Getter @Setter private BigDecimal ct;
    @Getter @Setter private BigDecimal s_in_tmp;
    @Getter @Setter private BigDecimal s_out_tmp;
    @Getter @Setter private BigDecimal dt_tmp;
    @Getter @Setter private BigDecimal ct_tmp;
    @Getter @Setter private Date l_date;
    @Getter @Setter private Date date_open;
    @Getter @Setter private Date date_close;
    @Getter @Setter private String acc_group_id;
    @Getter @Setter private int state;
    @Getter @Setter private String state_desc;
    @Getter @Setter private String client_name;

    public Account() {
    	super();
    }
    
    private Account(String id) {
    	this.id = id;
    }

    public Account(String branch, String id, String acc_bal, String currency, String client, String id_order, String name, String sgn, String bal, int sign_registr, BigDecimal s_in, BigDecimal s_out, BigDecimal dt, BigDecimal ct, BigDecimal s_in_tmp, BigDecimal s_out_tmp, BigDecimal dt_tmp, BigDecimal ct_tmp, Date l_date, Date date_open, Date date_close, int acc_group_id, int state) {

    }

    public static Account instanceWithId(String id) {
    	return new Account(id);
    }
    
	public Account(String branch, String id, String acc_bal, String currency,
                   String client, String id_order, String name, String sgn,
                   String bal, int sign_registr, BigDecimal s_in, BigDecimal s_out, BigDecimal dt,
                   BigDecimal ct, BigDecimal s_in_tmp, BigDecimal s_out_tmp, BigDecimal dt_tmp, BigDecimal ct_tmp,
                   Date l_date, Date date_open, Date date_close, String acc_group_id,
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
                   String bal, int sign_registr, BigDecimal s_in, BigDecimal s_out, BigDecimal dt,
                   BigDecimal ct, BigDecimal s_in_tmp, BigDecimal s_out_tmp, BigDecimal dt_tmp, BigDecimal ct_tmp,
                   Date l_date, Date date_open, Date date_close, String acc_group_id,
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

	public Account(String branch, String id, String client, String name, BigDecimal s_in, BigDecimal s_out,
                   BigDecimal dt, BigDecimal ct, Date l_date, int state) {
		super();
		this.branch = branch;
		this.id = id;
		this.client = client;
		this.name = name;
		this.s_in = s_in;
		this.s_out = s_out;
		this.dt = dt;
		this.ct = ct;
		this.l_date = l_date;
		this.state = state;
	}
	
	
}
