package com.is.tietovisa.model;

import java.util.Date;

public class Refill_log {
	
	  private Long id;
	  private Long pay_id;
	  private String user_branch; 
	  private String user_id;
	  private String user_name;
	  private String err;
	  private Date sysd;
	  
	public Refill_log() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPay_id() {
		return pay_id;
	}

	public void setPay_id(Long pay_id) {
		this.pay_id = pay_id;
	}

	public String getUser_branch() {
		return user_branch;
	}

	public void setUser_branch(String user_branch) {
		this.user_branch = user_branch;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setSysd(Date sysd) {
		this.sysd = sysd;
	}

	public Date getSysd() {
		return sysd;
	}
	  
	  

}
