package com.is.clients.models;

import java.util.Date;

public class License {
	private java.lang.String license_type_id;

    /* Лицензии: Идентификатор */
	private java.lang.String license_id;

    /* Лицензии: Действительно по */
	private java.util.Date license_valid_to;

    /* Лицензии: Дата выдачи */
	private java.util.Date license_issue_date;

    /* Лицензии: Кем выдан */
	private java.lang.String license_issued_by;
	
	/* Лицензии: type */
	private java.lang.String license_type;

    /* Лицензии: other */
	private java.lang.String license_type_other;
	
	private java.lang.String license_issued_by_other; 
	
	public License() {
		
	}
	
	public License(String license_type_id, String license_id, Date license_valid_to, Date license_issue_date,
			String license_issued_by, String license_type, String license_type_other, String license_issued_by_other) {
		super();
		this.license_type_id = license_type_id;
		this.license_id = license_id;
		this.license_valid_to = license_valid_to;
		this.license_issue_date = license_issue_date;
		this.license_issued_by = license_issued_by;
		this.license_type = license_type;
		this.license_type_other = license_type_other;
		this.license_issued_by_other = license_issued_by_other;
	}

	public java.lang.String getLicense_type_id() {
		return license_type_id;
	}

	public void setLicense_type_id(java.lang.String license_type_id) {
		this.license_type_id = license_type_id;
	}

	public java.lang.String getLicense_id() {
		return license_id;
	}

	public void setLicense_id(java.lang.String license_id) {
		this.license_id = license_id;
	}

	public java.util.Date getLicense_valid_to() {
		return license_valid_to;
	}

	public void setLicense_valid_to(java.util.Date license_valid_to) {
		this.license_valid_to = license_valid_to;
	}

	public java.util.Date getLicense_issue_date() {
		return license_issue_date;
	}

	public void setLicense_issue_date(java.util.Date license_issue_date) {
		this.license_issue_date = license_issue_date;
	}

	public java.lang.String getLicense_issued_by() {
		return license_issued_by;
	}

	public void setLicense_issued_by(java.lang.String license_issued_by) {
		this.license_issued_by = license_issued_by;
	}

	public java.lang.String getLicense_type() {
		return license_type;
	}

	public void setLicense_type(java.lang.String license_type) {
		this.license_type = license_type;
	}

	public java.lang.String getLicense_type_other() {
		return license_type_other;
	}

	public void setLicense_type_other(java.lang.String license_type_other) {
		this.license_type_other = license_type_other;
	}

	public java.lang.String getLicense_issued_by_other() {
		return license_issued_by_other;
	}

	public void setLicense_issued_by_other(java.lang.String license_issued_by_other) {
		this.license_issued_by_other = license_issued_by_other;
	}
	
	
}
