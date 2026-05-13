package com.is.accounts;

import java.util.Date;
import java.io.Serializable;

public class AccountFilter implements Serializable {

    static final long serialVersionUID = 314543135353635157L;

	private String branch;
	private String id;
	private String acc_bal;
	private String currency;
	private String client;
	private String id_order;
	private String name;
	private String sgn;
	private String bal;
	private Long sign_registr;
	private Long s_in;
	private Long s_out;
	private Long dt;
	private Long ct;
	private Long s_in_tmp;
	private Long s_out_tmp;
	private Long dt_tmp;
	private Long ct_tmp;
	private Date l_date;
	private Date date_open;
	private Date date_close;
	private Long acc_group_id;
	private Long state;
	private Long kod_err;
	private String file_name;
	private String accounts;
	private String close_sgn;

    public AccountFilter() {
		super();
    }

    public AccountFilter(String branch, String id, String acc_bal, String currency, String client, String id_order, String name, String sgn, String bal, Long sign_registr, Long s_in, Long s_out, Long dt, Long ct, Long s_in_tmp, Long s_out_tmp, Long dt_tmp, Long ct_tmp, Date l_date, Date date_open, Date date_close, Long acc_group_id, Long state, Long kod_err, String file_name,String close_sgn) {
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
		this.kod_err = kod_err;
		this.file_name = file_name;
		this.close_sgn = close_sgn;
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

	public Long getSign_registr() { 
		return sign_registr;
	} 

	public void setSign_registr(Long sign_registr) { 
		this.sign_registr = sign_registr;
	} 

	public Long getS_in() { 
		return s_in;
	} 

	public void setS_in(Long s_in) { 
		this.s_in = s_in;
	} 

	public Long getS_out() { 
		return s_out;
	} 

	public void setS_out(Long s_out) { 
		this.s_out = s_out;
	} 

	public Long getDt() { 
		return dt;
	} 

	public void setDt(Long dt) { 
		this.dt = dt;
	} 

	public Long getCt() { 
		return ct;
	} 

	public void setCt(Long ct) { 
		this.ct = ct;
	} 

	public Long getS_in_tmp() { 
		return s_in_tmp;
	} 

	public void setS_in_tmp(Long s_in_tmp) { 
		this.s_in_tmp = s_in_tmp;
	} 

	public Long getS_out_tmp() { 
		return s_out_tmp;
	} 

	public void setS_out_tmp(Long s_out_tmp) { 
		this.s_out_tmp = s_out_tmp;
	} 

	public Long getDt_tmp() { 
		return dt_tmp;
	} 

	public void setDt_tmp(Long dt_tmp) { 
		this.dt_tmp = dt_tmp;
	} 

	public Long getCt_tmp() { 
		return ct_tmp;
	} 

	public void setCt_tmp(Long ct_tmp) { 
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

	public Long getAcc_group_id() { 
		return acc_group_id;
	} 

	public void setAcc_group_id(Long acc_group_id) { 
		this.acc_group_id = acc_group_id;
	} 

	public Long getState() { 
		return state;
	} 

	public void setState(Long state) { 
		this.state = state;
	} 

	public Long getKod_err() { 
		return kod_err;
	} 

	public void setKod_err(Long kod_err) { 
		this.kod_err = kod_err;
	} 

	public String getFile_name() { 
		return file_name;
	} 

	public void setFile_name(String file_name) { 
		this.file_name = file_name;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getClose_sgn() {
		return close_sgn;
	}

	public void setClose_sgn(String close_sgn) {
		this.close_sgn = close_sgn;
	} 
	

}
