package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizDocsData {
	@JsonProperty("transaction_id")
	private float transaction_id;
	@JsonProperty("current_pinpp")
	private String current_pinpp;
	@JsonProperty("pinpps")
	private String[] pinpps;
	@JsonProperty("current_document")
	private String current_document;
	@JsonProperty("documents")
	private Document[] documentObj;
	@JsonProperty("surnamelat")
	private String surnamelat;
	@JsonProperty("namelat")
	private String namelat;
	@JsonProperty("patronymlat")
	private String patronymlat;
	@JsonProperty("surnamecyr")
	private String surnamecyr;
	@JsonProperty("namecyr")
	private String namecyr;
	@JsonProperty("patronymcyr")
	private String patronymcyr;
	@JsonProperty("engsurname")
	private String engsurname;
	@JsonProperty("engname")
	private String engname;
	@JsonProperty("birth_date")
	private String birth_date;
	@JsonProperty("birthplace")
	private String birthplace;
	@JsonProperty("birthcountry")
	private String birthcountry;
	@JsonProperty("birthcountryid")
	private String birthcountryid;
	@JsonProperty("livestatus")
	private int livestatus;
	@JsonProperty("nationality")
	private String nationality;
	@JsonProperty("nationalityid")
	private String nationalityid;
	@JsonProperty("citizenship")
	private String citizenship;
	@JsonProperty("citizenshipid")
	private String citizenshipid;
	@JsonProperty("sex")
	private float sex;
	@JsonProperty("photo")
	private String photo;

	// Getter Methods

	public float getTransaction_id() {
		return transaction_id;
	}

	public String getCurrent_pinpp() {
		return current_pinpp;
	}
	
	public String[] getPinpps() {
		return pinpps;
	}

	public String getCurrent_document() {
		return current_document;
	}

	public Document[] getDocumentObj() {
		return documentObj;
	}

	public String getSurnamelat() {
		return surnamelat;
	}

	public String getNamelat() {
		return namelat;
	}

	public String getPatronymlat() {
		return patronymlat;
	}

	public String getSurnamecyr() {
		return surnamecyr;
	}

	public String getNamecyr() {
		return namecyr;
	}

	public String getPatronymcyr() {
		return patronymcyr;
	}

	public String getEngsurname() {
		return engsurname;
	}

	public String getEngname() {
		return engname;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public String getBirthcountry() {
		return birthcountry;
	}

	public String getBirthcountryid() {
		return birthcountryid;
	}

	public float getLivestatus() {
		return livestatus;
	}

	public String getNationality() {
		return nationality;
	}

	public String getNationalityid() {
		return nationalityid;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public String getCitizenshipid() {
		return citizenshipid;
	}

	public float getSex() {
		return sex;
	}

	public String getPhoto() {
		return photo;
	}

	// Setter Methods

	public void setTransaction_id(float transaction_id) {
		this.transaction_id = transaction_id;
	}

	public void setCurrent_pinpp(String current_pinpp) {
		this.current_pinpp = current_pinpp;
	}
	
	public void setPinpps(String[] pinpps) {
		this.pinpps = pinpps;
	}

	public void setCurrent_document(String current_document) {
		this.current_document = current_document;
	}

	public void setDocumentObj(Document[] documentObj) {
		this.documentObj = documentObj;
	}

	public void setSurnamelat(String surnamelat) {
		this.surnamelat = surnamelat;
	}

	public void setNamelat(String namelat) {
		this.namelat = namelat;
	}

	public void setPatronymlat(String patronymlat) {
		this.patronymlat = patronymlat;
	}

	public void setSurnamecyr(String surnamecyr) {
		this.surnamecyr = surnamecyr;
	}

	public void setNamecyr(String namecyr) {
		this.namecyr = namecyr;
	}

	public void setPatronymcyr(String patronymcyr) {
		this.patronymcyr = patronymcyr;
	}

	public void setEngsurname(String engsurname) {
		this.engsurname = engsurname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public void setBirthcountry(String birthcountry) {
		this.birthcountry = birthcountry;
	}

	public void setBirthcountryid(String birthcountryid) {
		this.birthcountryid = birthcountryid;
	}

	public void setLivestatus(int livestatus) {
		this.livestatus = livestatus;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setNationalityid(String nationalityid) {
		this.nationalityid = nationalityid;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public void setCitizenshipid(String citizenshipid) {
		this.citizenshipid = citizenshipid;
	}

	public void setSex(float sex) {
		this.sex = sex;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
