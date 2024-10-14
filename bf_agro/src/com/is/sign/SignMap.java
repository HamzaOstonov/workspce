package com.is.sign;

public class SignMap {
	private Long sign_id;
	private Long object_id;
	private int object_type;
	private int state;
	
	public SignMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SignMap(Long sign_id, Long object_id, int object_type, int state) {
		super();
		this.sign_id = sign_id;
		this.object_id = object_id;
		this.object_type = object_type;
		this.state = state;
	}
	
	public Long getSign_id() {
		return sign_id;
	}
	
	public void setSign_id(Long sign_id) {
		this.sign_id = sign_id;
	}
	
	public Long getObject_id() {
		return object_id;
	}
	
	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}
	
	public int getObject_type() {
		return object_type;
	}
	
	public void setObject_type(int object_type) {
		this.object_type = object_type;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
}
