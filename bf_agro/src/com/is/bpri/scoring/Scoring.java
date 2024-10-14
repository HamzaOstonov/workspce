package com.is.bpri.scoring;

public class Scoring {
	
	private Integer group_id;
	private Integer id;
	private String name;
	private String name_ru;
	private String name_en;
	private String name_uz;
	private String selects;
	private String type;
	private String def_value;
	private String ord;
	private String mandatory;
	private String param_type;
	private String bpr_id;
	
	public Scoring() {
		
	}

	public Scoring(Integer group_id, Integer id, String name, String name_ru,
			String name_en, String name_uz, String selects, String type,
			String def_value, String ord, String mandatory, String param_type,
			String bpr_id) {
		super();
		this.group_id = group_id;
		this.id = id;
		this.name = name;
		this.name_ru = name_ru;
		this.name_en = name_en;
		this.name_uz = name_uz;
		this.selects = selects;
		this.type = type;
		this.def_value = def_value;
		this.ord = ord;
		this.mandatory = mandatory;
		this.param_type = param_type;
		this.bpr_id = bpr_id;
	}
	
	

	public Scoring(Integer group_id, Integer id, String name, String name_ru,
			String name_en, String name_uz, String selects, String type,
			String def_value, String ord, String mandatory, String param_type) {
		super();
		this.group_id = group_id;
		this.id = id;
		this.name = name;
		this.name_ru = name_ru;
		this.name_en = name_en;
		this.name_uz = name_uz;
		this.selects = selects;
		this.type = type;
		this.def_value = def_value;
		this.ord = ord;
		this.mandatory = mandatory;
		this.param_type = param_type;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_ru() {
		return name_ru;
	}

	public void setName_ru(String name_ru) {
		this.name_ru = name_ru;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_uz() {
		return name_uz;
	}

	public void setName_uz(String name_uz) {
		this.name_uz = name_uz;
	}

	public String getSelects() {
		return selects;
	}

	public void setSelects(String selects) {
		this.selects = selects;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDef_value() {
		return def_value;
	}

	public void setDef_value(String def_value) {
		this.def_value = def_value;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getParam_type() {
		return param_type;
	}

	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}

	public String getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(String bpr_id) {
		this.bpr_id = bpr_id;
	}
	
}
