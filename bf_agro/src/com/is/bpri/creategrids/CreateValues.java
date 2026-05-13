package com.is.bpri.creategrids;

public class CreateValues {
	
	private Long cid;
	private Long tid;
	private Long oid;
	private String value;
	private String en_name;
	private String param;
	
	public CreateValues() {
		
	}

	public CreateValues(Long cid, Long tid, Long oid, String value,String param) {
		super();
		this.cid = cid;
		this.tid = tid;
		this.oid = oid;
		this.value = value;
		this.param = param;
	}
	
	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
