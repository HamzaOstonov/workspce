package com.is.bpri.creategrids;

public class GridCols {
	private Long cid;
	private Long oid;
	private String labelname;
	private String cvalue;
	private String type_field;
	private String svalue;
	
	public GridCols() {		
	}

	public GridCols(Long cid,Long oid, String labelname, String cvalue, String type_field, String svalue) {
		super();
		this.cid = cid;
		this.oid = oid;
		this.labelname = labelname;
		this.cvalue = cvalue;
		this.type_field = type_field;
		this.svalue = svalue;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getLabelName() {
		return labelname;
	}
	public void setLabelName(String labelname) {
		this.labelname = labelname;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}	
	public String getType_field() {
		return type_field;
	}
	public void setType_field(String type_field) {
		this.type_field = type_field;
	}	
	public String getSvalue() {
		return svalue;
	}
	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}		
}
