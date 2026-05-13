package com.is.useractionlog;

import java.io.Serializable;

public class UserActionsAddinfo implements Serializable {

    static final long serialVersionUID = 2L;

	private Long id;
	private String a_key;
	private String a_value;



    public UserActionsAddinfo() {
		super();
    }

    public UserActionsAddinfo(Long id, String a_key, String a_value) {
		super();
		this.id = id;
		this.a_key = a_key;
		this.a_value = a_value;


    }
    

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getA_key() { 
		return a_key;
	} 

	public void setA_key(String a_key) { 
		this.a_key = a_key;
	} 

	public String getA_value() { 
		return a_value;
	} 

	public void setA_value(String a_value) { 
		this.a_value = a_value;
	} 

}

