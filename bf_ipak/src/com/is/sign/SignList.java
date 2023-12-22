package com.is.sign;

public class SignList {
	private Long object_id;
	private String object_num;
	private String sign_text;
	private String sign_data;
	private int res_code;
	private String err_message;
	private Long sign_log_id;
	
	public SignList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignList(Long object_id, String object_num, String sign_text) {
		super();
		this.object_id = object_id;
		this.object_num = object_num;
		this.sign_text = sign_text;
		this.sign_data = "";
		this.res_code = 0;
		this.err_message = "";
		this.sign_log_id = 0L;
	}
	
	public SignList(Long object_id, String object_num, String sign_text, int res_code, String err_message) {
		super();
		this.object_id = object_id;
		this.object_num = object_num;
		this.sign_text = sign_text;
		this.sign_data = "";
		this.res_code = res_code;
		this.err_message = err_message;
		this.sign_log_id = 0L;
	}

	public SignList(Long object_id, String object_num, String sign_text, String sign_data, int res_code, String err_message, Long sign_log_id) {
		super();
		this.object_id = object_id;
		this.object_num = object_num;
		this.sign_text = sign_text;
		this.sign_data = sign_data;
		this.res_code = res_code;
		this.err_message = err_message;
		this.sign_log_id = sign_log_id;
	}

	public Long getObject_id() {
		return object_id;
	}

	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}

	public String getObject_num() {
		return object_num;
	}

	public void setObject_num(String object_num) {
		this.object_num = object_num;
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

	public int getRes_code() {
		return res_code;
	}

	public void setRes_code(int res_code) {
		this.res_code = res_code;
	}

	public String getErr_message() {
		return err_message;
	}

	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}

	public Long getSign_log_id() {
		return sign_log_id;
	}

	public void setSign_log_id(Long sign_log_id) {
		this.sign_log_id = sign_log_id;
	}
}
