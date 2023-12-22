package com.is.bpri.reference;

public class ReferenceFields {
	
	private String sid;
	private String id;
	private String value;
	
	public ReferenceFields() {
		
	}

	public ReferenceFields(String sid,String id, String value) {
		super();
		this.sid = sid;
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
}
