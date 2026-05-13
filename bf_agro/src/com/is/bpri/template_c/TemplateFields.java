package com.is.bpri.template_c;

public class TemplateFields {
	
	private Long tid;
	private Long oid;
	private String label_name;
	private String type_field;
	private String sid;
	private String label_name_en;
	private Long conformity_id;
	private int required_field;
	private String model;
	
	public TemplateFields() {
		
	}
	
	public TemplateFields(Long tid, Long oid, String label_name,
			String type_field,String sid,String label_name_en,Long conformity_id,int required_field,String model) {
		super();
		this.tid = tid;
		this.oid = oid;
		this.label_name = label_name;
		this.type_field = type_field;
		this.sid = sid;
		this.label_name_en = label_name_en;
		this.conformity_id = conformity_id;
		this.required_field = required_field;
		this.model = model;
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
	
	public String getLabel_name() {
		return label_name;
	}
	
	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	
	public String getType_field() {
		return type_field;
	}
	
	public void setType_field(String type_field) {
		this.type_field = type_field;
	}
	
	public String getSid() {
		return sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getLabel_name_en() {
		return label_name_en;
	}
	
	public void setLabel_name_en(String label_name_en) {
		this.label_name_en = label_name_en;
	}

	public Long getConformity_id() {
		return conformity_id;
	}

	public void setConformity_id(Long conformity_id) {
		this.conformity_id = conformity_id;
	}

	public int getRequired_field() {
		return required_field;
	}

	public void setRequired_field(int required_field) {
		this.required_field = required_field;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}
