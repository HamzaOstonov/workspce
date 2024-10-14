package com.is.sign.log;
import java.io.Serializable;
import java.util.Date;

public class Signlog implements Serializable {

    static final long serialVersionUID = 5567L;

	private Long id;
	private Date dttime;
	private int group_id;
	private int deal_id;
	private int action_id;
	private Long object_id;
	private String sign_text;
	private String sign_data;
	private int key_type;
	private String key_code;
	private String key_sn;
	private String branch;
	private int user_id;
	private String username;
	private String scheme;
	private int state;
	private String err_msg;

    public Signlog() {
		super();
    }

	public Signlog(Long id, Date dttime, int group_id, int deal_id, int action_id, Long object_id, String sign_text, String sign_data, int key_type, String key_code, String key_sn, String branch, int user_id, String username, String scheme, int state, String err_msg) {
		super();
		this.id = id;
		this.dttime = dttime;
		this.group_id = group_id;
		this.deal_id = deal_id;
		this.action_id = action_id;
		this.object_id = object_id;
		this.sign_text = sign_text;
		this.sign_data = sign_data;
		this.key_type = key_type;
		this.key_code = key_code;
		this.key_sn = key_sn;
		this.branch = branch;
		this.user_id = user_id;
		this.username = username;
		this.scheme = scheme;
		this.state = state;
		this.err_msg = err_msg;
	}

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public Date getDttime() {
		return dttime;
	}

	public void setDttime(Date dttime) {
		this.dttime = dttime;
	}

	public int getGroup_id() { 
		return group_id;
	} 

	public void setGroup_id(int group_id) { 
		this.group_id = group_id;
	} 

	public int getDeal_id() { 
		return deal_id;
	} 

	public void setDeal_id(int deal_id) { 
		this.deal_id = deal_id;
	} 

	public int getAction_id() { 
		return action_id;
	} 

	public void setAction_id(int action_id) { 
		this.action_id = action_id;
	} 

	public Long getObject_id() { 
		return object_id;
	} 

	public void setObject_id(Long object_id) { 
		this.object_id = object_id;
	} 

	public String getSign_text() { 
		return sign_text;
	} 

	public void setSign_text(String sign_text) { 
		this.sign_text = sign_text;
	} 

	public String getSign_data() { 
		return sign_data;
	} 

	public void setSign_data(String sign_data) { 
		this.sign_data = sign_data;
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

	public String getUsername() { 
		return username;
	} 

	public void setUsername(String username) { 
		this.username = username;
	} 

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public String getScheme() { 
		return scheme;
	} 

	public void setScheme(String scheme) { 
		this.scheme = scheme;
	} 

	public int getState() { 
		return state;
	} 

	public void setState(int state) { 
		this.state = state;
	} 

	public String getErr_msg() { 
		return err_msg;
	} 

	public void setErr_msg(String err_msg) { 
		this.err_msg = err_msg;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	} 

}
