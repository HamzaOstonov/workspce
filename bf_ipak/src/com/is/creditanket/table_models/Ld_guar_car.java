package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_guar_car {

	private String branch;
	private Long id;
	private Long pk;
	private Long pk_ld_guar;
	private String car_type;
	private String car_marka;
	private String car_model;
	private String date_made;
	private Long mileage;
	private String engine_num;
	private String body_num;
	private String color;
	private String state_number;
	private String doc_ser_num;
	private Date doc_date;
	private String code_country;
	private Integer position;
	private String chassis_number;
	
	public Ld_guar_car() {
		
	}

	public Ld_guar_car(String branch, Long id, Long pk, Long pk_ld_guar,
			String car_type, String car_marka, String car_model, String date_made,
			Long mileage, String engine_num, String body_num, String color,
			String state_number, String doc_ser_num, Date doc_date,
			String code_country, Integer position, String chassis_number) {
		super();
		this.branch = branch;
		this.id = id;
		this.pk = pk;
		this.pk_ld_guar = pk_ld_guar;
		this.car_type = car_type;
		this.car_marka = car_marka;
		this.car_model = car_model;
		this.date_made = date_made;
		this.mileage = mileage;
		this.engine_num = engine_num;
		this.body_num = body_num;
		this.color = color;
		this.state_number = state_number;
		this.doc_ser_num = doc_ser_num;
		this.doc_date = doc_date;
		this.code_country = code_country;
		this.position = position;
		this.chassis_number = chassis_number;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getPk_ld_guar() {
		return pk_ld_guar;
	}

	public void setPk_ld_guar(Long pk_ld_guar) {
		this.pk_ld_guar = pk_ld_guar;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public String getCar_marka() {
		return car_marka;
	}

	public void setCar_marka(String car_marka) {
		this.car_marka = car_marka;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getDate_made() {
		return date_made;
	}

	public void setDate_made(String date_made) {
		this.date_made = date_made;
	}

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public String getEngine_num() {
		return engine_num;
	}

	public void setEngine_num(String engine_num) {
		this.engine_num = engine_num;
	}

	public String getBody_num() {
		return body_num;
	}

	public void setBody_num(String body_num) {
		this.body_num = body_num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getState_number() {
		return state_number;
	}

	public void setState_number(String state_number) {
		this.state_number = state_number;
	}

	public String getDoc_ser_num() {
		return doc_ser_num;
	}

	public void setDoc_ser_num(String doc_ser_num) {
		this.doc_ser_num = doc_ser_num;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public String getCode_country() {
		return code_country;
	}

	public void setCode_country(String code_country) {
		this.code_country = code_country;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getChassis_number() {
		return chassis_number;
	}

	public void setChassis_number(String chassis_number) {
		this.chassis_number = chassis_number;
	}
	
}
