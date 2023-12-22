package com.is.creditanket.grids_models;

public class GuarrGrid {

	private Long order;
	private String name_field;
	private String type_field;
	private String table_name_field;
	private Long max_lenght_field;
	private String guarr_id;
	private String model_field;
	private String sub_name;
	private String table_name;
	
	public GuarrGrid() {
	
	}

	public GuarrGrid(Long order, String name_field, String type_field,
			String table_name_field, Long max_lenght_field, String guarr_id,
			String model_field, String sub_name,String table_name) {
		super();
		this.order = order;
		this.name_field = name_field;
		this.type_field = type_field;
		this.table_name_field = table_name_field;
		this.max_lenght_field = max_lenght_field;
		this.guarr_id = guarr_id;
		this.model_field = model_field;
		this.sub_name = sub_name;
		this.table_name = table_name;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getName_field() {
		return name_field;
	}

	public void setName_field(String name_field) {
		this.name_field = name_field;
	}

	public String getType_field() {
		return type_field;
	}

	public void setType_field(String type_field) {
		this.type_field = type_field;
	}

	public String getTable_name_field() {
		return table_name_field;
	}

	public void setTable_name_field(String table_name_field) {
		this.table_name_field = table_name_field;
	}

	public Long getMax_lenght_field() {
		return max_lenght_field;
	}

	public void setMax_lenght_field(Long max_lenght_field) {
		this.max_lenght_field = max_lenght_field;
	}

	public String getGuarr_id() {
		return guarr_id;
	}

	public void setGuarr_id(String guarr_id) {
		this.guarr_id = guarr_id;
	}

	public String getModel_field() {
		return model_field;
	}

	public void setModel_field(String model_field) {
		this.model_field = model_field;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
}
