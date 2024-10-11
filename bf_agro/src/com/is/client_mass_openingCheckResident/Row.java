package com.is.client_mass_openingCheckResident;

public class Row {
	private String queryld;
	 private String pinpp;
	 private String document;
	 private String surnamelatin;
	 private String namelatin;
	 private String patronymlatin;
	 private String engsurname;
	 private String engname;
	 private String birth_date;
	 private String birthplace;
	 private String birthplaceid;
	 private String birthcountry;
	 private float birthcountryid;
	 private float livestatus;
	 private String nationality;
	 private String nationalityid;
	 private String citizenship;
	 private float citizenshipid;
	 private float sex;
	 private String docgiveplace;
	 private float docgiveplaceid;
	 private String docdatebegin;
	 private String docdateend;

	 
	 public Row() {
			super();
		}

	 public Row(String queryld, String pinpp, String document, String surnamelatin, String namelatin,
			String patronymlatin, String engsurname, String engname, String birth_date, String birthplace,
			String birthplaceid, String birthcountry, float birthcountryid, float livestatus, String nationality,
			String nationalityid, String citizenship, float citizenshipid, float sex, String docgiveplace,
			float docgiveplaceid, String docdatebegin, String docdateend) {
		super();
		this.queryld = queryld;
		this.pinpp = pinpp;
		this.document = document;
		this.surnamelatin = surnamelatin;
		this.namelatin = namelatin;
		this.patronymlatin = patronymlatin;
		this.engsurname = engsurname;
		this.engname = engname;
		this.birth_date = birth_date;
		this.birthplace = birthplace;
		this.birthplaceid = birthplaceid;
		this.birthcountry = birthcountry;
		this.birthcountryid = birthcountryid;
		this.livestatus = livestatus;
		this.nationality = nationality;
		this.nationalityid = nationalityid;
		this.citizenship = citizenship;
		this.citizenshipid = citizenshipid;
		this.sex = sex;
		this.docgiveplace = docgiveplace;
		this.docgiveplaceid = docgiveplaceid;
		this.docdatebegin = docdatebegin;
		this.docdateend = docdateend;
	}




	// Getter Methods 

	
	public String getQueryld() {
	  return queryld;
	 }

	 public String getPinpp() {
	  return pinpp;
	 }

	 public String getDocument() {
	  return document;
	 }

	 public String getSurnamelatin() {
	  return surnamelatin;
	 }

	 public String getNamelatin() {
	  return namelatin;
	 }

	 public String getPatronymlatin() {
	  return patronymlatin;
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

	 public String getBirthplaceid() {
	  return birthplaceid;
	 }

	 public String getBirthcountry() {
	  return birthcountry;
	 }

	 public float getBirthcountryid() {
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

	 public float getCitizenshipid() {
	  return citizenshipid;
	 }

	 public float getSex() {
	  return sex;
	 }

	 public String getDocgiveplace() {
	  return docgiveplace;
	 }

	 public float getDocgiveplaceid() {
	  return docgiveplaceid;
	 }

	 public String getDocdatebegin() {
	  return docdatebegin;
	 }

	 public String getDocdateend() {
	  return docdateend;
	 }

	 // Setter Methods 

	 public void setQueryld(String queryld) {
	  this.queryld = queryld;
	 }

	 public void setPinpp(String pinpp) {
	  this.pinpp = pinpp;
	 }

	 public void setDocument(String document) {
	  this.document = document;
	 }

	 public void setSurnamelatin(String surnamelatin) {
	  this.surnamelatin = surnamelatin;
	 }

	 public void setNamelatin(String namelatin) {
	  this.namelatin = namelatin;
	 }

	 public void setPatronymlatin(String patronymlatin) {
	  this.patronymlatin = patronymlatin;
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

	 public void setBirthplaceid(String birthplaceid) {
	  this.birthplaceid = birthplaceid;
	 }

	 public void setBirthcountry(String birthcountry) {
	  this.birthcountry = birthcountry;
	 }

	 public void setBirthcountryid(float birthcountryid) {
	  this.birthcountryid = birthcountryid;
	 }

	 public void setLivestatus(float livestatus) {
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

	 public void setCitizenshipid(float citizenshipid) {
	  this.citizenshipid = citizenshipid;
	 }

	 public void setSex(float sex) {
	  this.sex = sex;
	 }

	 public void setDocgiveplace(String docgiveplace) {
	  this.docgiveplace = docgiveplace;
	 }

	 public void setDocgiveplaceid(float docgiveplaceid) {
	  this.docgiveplaceid = docgiveplaceid;
	 }

	 public void setDocdatebegin(String docdatebegin) {
	  this.docdatebegin = docdatebegin;
	 }

	 public void setDocdateend(String docdateend) {
	  this.docdateend = docdateend;
	 }
}
