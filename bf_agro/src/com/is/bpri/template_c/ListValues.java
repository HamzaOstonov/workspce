package com.is.bpri.template_c;

public class ListValues {
	
	private String oid;
	private String id;
	private String value;	
	public ListValues() {

	}
	public ListValues(String oid, String id, String value) {
		super();
		this.oid = oid;
		this.id = id;
		this.value = value;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
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
}
