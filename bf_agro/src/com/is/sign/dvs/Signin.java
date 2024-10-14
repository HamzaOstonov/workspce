package com.is.sign.dvs;

public class Signin {

	private Long id;
	private String query_line;
	private String crp_line;
	private String crp_date;
	private String curr_state;
	private String err_code;
	private String err_message;
	private int upd_cnt;

    public Signin() {
		super();
    }

    public Signin(Long id, String query_line, String crp_line, String crp_date, String curr_state, String err_code) {
		super();
		this.id = id;
		this.query_line = query_line;
		this.crp_line = crp_line;
		this.crp_date = crp_date;
		this.curr_state = curr_state;
		this.err_code = err_code;
    }
    
	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getQuery_line() { 
		return query_line;
	} 

	public void setQuery_line(String query_line) { 
		this.query_line = query_line;
	} 

	public String getCrp_line() { 
		return crp_line;
	} 

	public void setCrp_line(String crp_line) { 
		this.crp_line = crp_line;
	} 

	public String getCrp_date() { 
		return crp_date;
	} 

	public void setCrp_date(String crp_date) { 
		this.crp_date = crp_date;
	} 

	public String getCurr_state() { 
		return curr_state;
	} 

	public void setCurr_state(String curr_state) { 
		this.curr_state = curr_state;
	} 

	public String getErr_code() { 
		return err_code;
	} 

	public void setErr_code(String err_code) { 
		this.err_code = err_code;
	}

	public String getErr_message() {
		return err_message;
	}

	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}

	public int getUpd_cnt() {
		return upd_cnt;
	}

	public void setUpd_cnt(int upd_cnt) {
		this.upd_cnt = upd_cnt;
	} 

}
