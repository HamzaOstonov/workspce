package com.is.sign.dvs;

import java.io.Serializable;
import java.util.Date;

public class DvsUserAccess implements Serializable {

    static final long serialVersionUID = 11222123223121112L;

	private Long user_id;
	private String user_name;
	private String full_name;
	private String key_code;
	private Long emp_id;
	private Date key_expired;

    public DvsUserAccess() {
		super();
    }

    public DvsUserAccess(Long user_id, String user_name, String full_name, String key_code, Long emp_id, Date key_expired) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.full_name = full_name;
		this.key_code = key_code;
		this.emp_id = emp_id;
		this.key_expired = key_expired;


    }
    

	public Long getUser_id() { 
		return user_id;
	} 

	public void setUser_id(Long user_id) { 
		this.user_id = user_id;
	} 

	public String getUser_name() { 
		return user_name;
	} 

	public void setUser_name(String user_name) { 
		this.user_name = user_name;
	} 

	public String getFull_name() { 
		return full_name;
	} 

	public void setFull_name(String full_name) { 
		this.full_name = full_name;
	} 

	public String getKey_code() { 
		return key_code;
	} 

	public void setKey_code(String key_code) { 
		this.key_code = key_code;
	} 

	public Long getEmp_id() { 
		return emp_id;
	} 

	public void setEmp_id(Long emp_id) { 
		this.emp_id = emp_id;
	} 

	public Date getKey_expired() { 
		return key_expired;
	} 

	public void setKey_expired(Date key_expired) { 
		this.key_expired = key_expired;
	} 
    

}
