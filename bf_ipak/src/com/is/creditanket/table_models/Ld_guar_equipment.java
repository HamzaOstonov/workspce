package com.is.creditanket.table_models;

import java.util.Date;

public class Ld_guar_equipment {

	private String branch;
	private Long id;
	private Long pk;
	private Long pk_ld_guar;
	private Long id_nn;
	private String name;
	private String manufacturer;
	private String invent_num;
	private String reason;
	private String doc_num;
	private Date doc_date;
	private Double price_market;
	private Double price_zalog;
	private String date_made;
	private Date date_operation;
	private String eq_type;
	private String country;
	private String eq_place_region;
	private Integer eq_place_town;
	private String eq_place_distr;
	private String eq_place_adres;
	private String eq_place_x;
	private String eq_place_y;
	
	public Ld_guar_equipment() {
		
	}

	public Ld_guar_equipment(String branch, Long id, Long pk, Long pk_ld_guar,
			Long id_nn, String name, String manufacturer, String invent_num,
			String reason, String doc_num, Date doc_date, Double price_market,
			Double price_zalog, String date_made, Date date_operation,
			String eq_type, String country, String eq_place_region,
			Integer eq_place_town, String eq_place_distr,
			String eq_place_adres, String eq_place_x, String eq_place_y) {
		super();
		this.branch = branch;
		this.id = id;
		this.pk = pk;
		this.pk_ld_guar = pk_ld_guar;
		this.id_nn = id_nn;
		this.name = name;
		this.manufacturer = manufacturer;
		this.invent_num = invent_num;
		this.reason = reason;
		this.doc_num = doc_num;
		this.doc_date = doc_date;
		this.price_market = price_market;
		this.price_zalog = price_zalog;
		this.date_made = date_made;
		this.date_operation = date_operation;
		this.eq_type = eq_type;
		this.country = country;
		this.eq_place_region = eq_place_region;
		this.eq_place_town = eq_place_town;
		this.eq_place_distr = eq_place_distr;
		this.eq_place_adres = eq_place_adres;
		this.eq_place_x = eq_place_x;
		this.eq_place_y = eq_place_y;
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

	public Long getId_nn() {
		return id_nn;
	}

	public void setId_nn(Long id_nn) {
		this.id_nn = id_nn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getPrice_market() {
		return price_market;
	}

	public void setPrice_market(Double price_market) {
		this.price_market = price_market;
	}

	public Double getPrice_zalog() {
		return price_zalog;
	}

	public void setPrice_zalog(Double price_zalog) {
		this.price_zalog = price_zalog;
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

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEq_place_region() {
		return eq_place_region;
	}

	public void setEq_place_region(String eq_place_region) {
		this.eq_place_region = eq_place_region;
	}

	public Integer getEq_place_town() {
		return eq_place_town;
	}

	public void setEq_place_town(Integer eq_place_town) {
		this.eq_place_town = eq_place_town;
	}

	public String getEq_place_distr() {
		return eq_place_distr;
	}

	public void setEq_place_distr(String eq_place_distr) {
		this.eq_place_distr = eq_place_distr;
	}

	public String getEq_place_adres() {
		return eq_place_adres;
	}

	public void setEq_place_adres(String eq_place_adres) {
		this.eq_place_adres = eq_place_adres;
	}

	public String getEq_place_x() {
		return eq_place_x;
	}

	public void setEq_place_x(String eq_place_x) {
		this.eq_place_x = eq_place_x;
	}

	public String getEq_place_y() {
		return eq_place_y;
	}

	public void setEq_place_y(String eq_place_y) {
		this.eq_place_y = eq_place_y;
	}
	
}
