package com.is.sign.keys;

import java.io.Serializable;
import java.util.Date;

public class Key implements Serializable {

    static final long serialVersionUID = 3L;

	private int key_type;
	private String key_code;
	private String key_sn;
	private String key_certn;
	private String version;
	private String signature_algoritm;
	private Long key_size;
	private String issuer;
	private String pkey;
	private Long sign_failure;
	private Date key_expired;
	private Date activate_date;
	private Date deactivate_date;
	private String branch;
	private int emp_id;
	private int state;
	private String user_branch;
	private int user_id;
	private String user_name;
	
    public Key() {
		super();
    }

	public Key(int key_type, String key_code, String key_sn, String key_certn, String version, String signature_algoritm, Long key_size, String issuer, String pkey, Long sign_failure, Date key_expired, Date activate_date, Date deactivate_date, String branch, int emp_id, int state) {
		super();
		this.key_type = key_type;
		this.key_code = key_code;
		this.key_sn = key_sn;
		this.key_certn = key_certn;
		this.version = version;
		this.signature_algoritm = signature_algoritm;
		this.key_size = key_size;
		this.issuer = issuer;
		this.pkey = pkey;
		this.sign_failure = sign_failure;
		this.key_expired = key_expired;
		this.activate_date = activate_date;
		this.deactivate_date = deactivate_date;
		this.branch = branch;
		this.emp_id = emp_id;
		this.state = state;
	}

	public Key(int key_type, String key_code, String key_sn, String key_certn, String version, String signature_algoritm, Long key_size, String issuer, String pkey, Long sign_failure, Date key_expired, Date activate_date, Date deactivate_date, String branch, int emp_id, int state, String user_name) {
		super();
		this.key_type = key_type;
		this.key_code = key_code;
		this.key_sn = key_sn;
		this.key_certn = key_certn;
		this.version = version;
		this.signature_algoritm = signature_algoritm;
		this.key_size = key_size;
		this.issuer = issuer;
		this.pkey = pkey;
		this.sign_failure = sign_failure;
		this.key_expired = key_expired;
		this.activate_date = activate_date;
		this.deactivate_date = deactivate_date;
		this.branch = branch;
		this.emp_id = emp_id;
		this.state = state;
		this.user_name = user_name;
	}
	
	public Key(int key_type, String key_code, String key_sn, String key_certn, String version, String signature_algoritm, Long key_size, String issuer, String pkey, Long sign_failure, Date key_expired, Date activate_date, Date deactivate_date, String branch, int emp_id, int state, String user_branch, int user_id, String user_name) {
		super();
		this.key_type = key_type;
		this.key_code = key_code;
		this.key_sn = key_sn;
		this.key_certn = key_certn;
		this.version = version;
		this.signature_algoritm = signature_algoritm;
		this.key_size = key_size;
		this.issuer = issuer;
		this.pkey = pkey;
		this.sign_failure = sign_failure;
		this.key_expired = key_expired;
		this.activate_date = activate_date;
		this.deactivate_date = deactivate_date;
		this.branch = branch;
		this.emp_id = emp_id;
		this.state = state;
		this.user_branch = user_branch;
		this.user_id = user_id;
		this.user_name = user_name;
	}

	public int getKey_type() { 
		return key_type;
	} 

	public void setKey_type(int key_type) { 
		this.key_type = key_type;
	} 

	public String getKey_code() { 
		return key_code;
	} 

	public void setKey_code(String key_code) { 
		this.key_code = key_code;
	} 

	public String getKey_sn() { 
		return key_sn;
	} 

	public void setKey_sn(String key_sn) { 
		this.key_sn = key_sn;
	} 

	public String getVersion() { 
		return version;
	} 

	public void setVersion(String version) { 
		this.version = version;
	} 

	public String getSignature_algoritm() { 
		return signature_algoritm;
	} 

	public void setSignature_algoritm(String signature_algoritm) { 
		this.signature_algoritm = signature_algoritm;
	} 

	public Long getKey_size() { 
		return key_size;
	} 

	public void setKey_size(Long key_size) { 
		this.key_size = key_size;
	} 

	public String getIssuer() { 
		return issuer;
	} 

	public void setIssuer(String issuer) { 
		this.issuer = issuer;
	} 

	public String getPkey() { 
		return pkey;
	} 

	public void setPkey(String pkey) { 
		this.pkey = pkey;
	} 

	public Long getSign_failure() { 
		return sign_failure;
	} 

	public void setSign_failure(Long sign_failure) { 
		this.sign_failure = sign_failure;
	} 

	public Date getKey_expired() { 
		return key_expired;
	} 

	public void setKey_expired(Date key_expired) { 
		this.key_expired = key_expired;
	} 

	public Date getActivate_date() { 
		return activate_date;
	} 

	public void setActivate_date(Date activate_date) { 
		this.activate_date = activate_date;
	} 

	public Date getDeactivate_date() { 
		return deactivate_date;
	} 

	public void setDeactivate_date(Date deactivate_date) { 
		this.deactivate_date = deactivate_date;
	} 

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public int getEmp_id() { 
		return emp_id;
	} 

	public void setEmp_id(int emp_id) { 
		this.emp_id = emp_id;
	} 

	public int getState() { 
		return state;
	} 

	public void setState(int state) { 
		this.state = state;
	}

	public String getUser_branch() {
		return user_branch;
	}

	public void setUser_branch(String user_branch) {
		this.user_branch = user_branch;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getKey_certn() {
		return key_certn;
	}

	public void setKey_certn(String key_certn) {
		this.key_certn = key_certn;
	} 

}
