package com.is.bpri.ldguarrgrids;

import java.util.Date;

public class LdEquipment {
	
	private int id;
	private String bpr_id;
	private String id_nn;
	private String eq_type;
	private String name;
	private String country;
	private String date_made;
	private Date date_operation;
	private String manufacturer;
	private String invent_num;
	private String reason;
	private String doc_num;
	private Date doc_date;
	private String price_market;
	private String price_zalog;
	private String eq_type_text;
	private String country_text;
	
	public LdEquipment() {
		
	}

	public LdEquipment(int id,String bpr_id,String id_nn, String eq_type, String name,
			String country, String date_made, Date date_operation,
			String manufacturer, String invent_num, String reason,
			String doc_num, Date doc_date, String price_market,
			String price_zalog,String eq_type_text,String country_text) {
		super();
		this.id = id;
		this.bpr_id = bpr_id;
		this.id_nn = id_nn;
		this.eq_type = eq_type;
		this.name = name;
		this.country = country;
		this.date_made = date_made;
		this.date_operation = date_operation;
		this.manufacturer = manufacturer;
		this.invent_num = invent_num;
		this.reason = reason;
		this.doc_num = doc_num;
		this.doc_date = doc_date;
		this.price_market = price_market;
		this.price_zalog = price_zalog;
		this.eq_type_text = eq_type_text;
		this.country_text = country_text;
	}

	public String getId_nn() {
		return id_nn;
	}

	public void setId_nn(String id_nn) {
		this.id_nn = id_nn;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate_made() {
		return date_made;
	}

	public void setDate_made(String date_made) {
		this.date_made = date_made;
	}

	public Date getDate_operation() {
		return date_operation;
	}

	public void setDate_operation(Date date_operation) {
		this.date_operation = date_operation;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getInvent_num() {
		return invent_num;
	}

	public void setInvent_num(String invent_num) {
		this.invent_num = invent_num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDoc_num() {
		return doc_num;
	}

	public void setDoc_num(String doc_num) {
		this.doc_num = doc_num;
	}

	public Date getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}

	public String getPrice_market() {
		return price_market;
	}

	public void setPrice_market(String price_market) {
		this.price_market = price_market;
	}

	public String getPrice_zalog() {
		return price_zalog;
	}

	public void setPrice_zalog(String price_zalog) {
		this.price_zalog = price_zalog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(String bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getEq_type_text() {
		return eq_type_text;
	}

	public void setEq_type_text(String eq_type_text) {
		this.eq_type_text = eq_type_text;
	}

	public String getCountry_text() {
		return country_text;
	}

	public void setCountry_text(String country_text) {
		this.country_text = country_text;
	}
	
}
