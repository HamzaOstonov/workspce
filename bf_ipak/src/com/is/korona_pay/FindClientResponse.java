package com.is.korona_pay;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindClientResponse {

	@JsonProperty("Action")
	public String action;
	@JsonProperty("FirstName")
	public String firstName;
	@JsonProperty("LastName")
	public String lastName;
	@JsonProperty("MiddleName")
	public String middleName;
	@JsonProperty("LatinFirstName")
	public String latinFirstName;
	@JsonProperty("LatinLastName")
	public String latinLastName;
	@JsonProperty("LatinMiddleName")
	public String latinMiddleName;
	@JsonProperty("DocType")
	public String docType;
	@JsonProperty("DocNumber")
	public String docNumber;
	@JsonProperty("DocSeries")
	public String docSeries;
	@JsonProperty("DocIssueCountryIso")
	public String docIssueCountryIso;
	@JsonProperty("DocIssuer")
	public String docIssuer;
	@JsonProperty("DocIssuerCode")
	public String docIssuerCode;
	@JsonProperty("DocIssueDate")
	public String docIssueDate;
	@JsonProperty("DocExpireDate")
	public String docExpireDate;
	@JsonProperty("Citizenship")
	public String citizenship;
	@JsonProperty("IsResident")
	public String isResident;
	@JsonProperty("BirthCountryIso")
	public String birthCountryIso;
	@JsonProperty("BirthCity")
	public String birthCity;
	@JsonProperty("BirthDate")
	public String birthDate;
	@JsonProperty("INN")
	public String iNN;
	@JsonProperty("RegCountryIso")
	public String regCountryIso;
	@JsonProperty("RegRegion")
	public String regRegion;
	@JsonProperty("RegCity")
	public String regCity;
	@JsonProperty("RegAddress")
	public String regAddress;
	@JsonProperty("PostCode")
	public String postCode;
	@JsonProperty("Phone")
	public String phone;
	@JsonProperty("MigrationCardSeries")
	public String migrationCardSeries;
	@JsonProperty("MigrationCardNumber")
	public String migrationCardNumber;
	@JsonProperty("MigrationCardIssueDate")
	public String migrationCardIssueDate;
	@JsonProperty("MigrationCardExpireDate")
	public String migrationCardExpireDate;
	@JsonProperty("IPDLType")
	public String iPDLType;
	@JsonProperty("AbsClientId")
	public String absClientId;
	@JsonProperty("RetCode")
	public Integer retCode;
	@JsonProperty("RetMsg")
	public String retMsg;
	@JsonProperty("RetExtMsg")
	public String retExtMsg;
	
	public FindClientResponse () {}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLatinFirstName() {
		return latinFirstName;
	}

	public void setLatinFirstName(String latinFirstName) {
		this.latinFirstName = latinFirstName;
	}

	public String getLatinLastName() {
		return latinLastName;
	}

	public void setLatinLastName(String latinLastName) {
		this.latinLastName = latinLastName;
	}

	public String getLatinMiddleName() {
		return latinMiddleName;
	}

	public void setLatinMiddleName(String latinMiddleName) {
		this.latinMiddleName = latinMiddleName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocSeries() {
		return docSeries;
	}

	public void setDocSeries(String docSeries) {
		this.docSeries = docSeries;
	}

	public String getDocIssueCountryIso() {
		return docIssueCountryIso;
	}

	public void setDocIssueCountryIso(String docIssueCountryIso) {
		this.docIssueCountryIso = docIssueCountryIso;
	}

	public String getDocIssuer() {
		return docIssuer;
	}

	public void setDocIssuer(String docIssuer) {
		this.docIssuer = docIssuer;
	}

	public String getDocIssuerCode() {
		return docIssuerCode;
	}

	public void setDocIssuerCode(String docIssuerCode) {
		this.docIssuerCode = docIssuerCode;
	}

	public String getDocIssueDate() {
		return docIssueDate;
	}

	public void setDocIssueDate(String docIssueDate) {
		this.docIssueDate = docIssueDate;
	}

	public String getDocExpireDate() {
		return docExpireDate;
	}

	public void setDocExpireDate(String docExpireDate) {
		this.docExpireDate = docExpireDate;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getIsResident() {
		return isResident;
	}

	public void setIsResident(String isResident) {
		this.isResident = isResident;
	}

	public String getBirthCountryIso() {
		return birthCountryIso;
	}

	public void setBirthCountryIso(String birthCountryIso) {
		this.birthCountryIso = birthCountryIso;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getiNN() {
		return iNN;
	}

	public void setiNN(String iNN) {
		this.iNN = iNN;
	}

	public String getRegCountryIso() {
		return regCountryIso;
	}

	public void setRegCountryIso(String regCountryIso) {
		this.regCountryIso = regCountryIso;
	}

	public String getRegRegion() {
		return regRegion;
	}

	public void setRegRegion(String regRegion) {
		this.regRegion = regRegion;
	}

	public String getRegCity() {
		return regCity;
	}

	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMigrationCardSeries() {
		return migrationCardSeries;
	}

	public void setMigrationCardSeries(String migrationCardSeries) {
		this.migrationCardSeries = migrationCardSeries;
	}

	public String getMigrationCardNumber() {
		return migrationCardNumber;
	}

	public void setMigrationCardNumber(String migrationCardNumber) {
		this.migrationCardNumber = migrationCardNumber;
	}

	public String getMigrationCardIssueDate() {
		return migrationCardIssueDate;
	}

	public void setMigrationCardIssueDate(String migrationCardIssueDate) {
		this.migrationCardIssueDate = migrationCardIssueDate;
	}

	public String getMigrationCardExpireDate() {
		return migrationCardExpireDate;
	}

	public void setMigrationCardExpireDate(String migrationCardExpireDate) {
		this.migrationCardExpireDate = migrationCardExpireDate;
	}

	public String getiPDLType() {
		return iPDLType;
	}

	public void setiPDLType(String iPDLType) {
		this.iPDLType = iPDLType;
	}

	public String getAbsClientId() {
		return absClientId;
	}

	public void setAbsClientId(String absClientId) {
		this.absClientId = absClientId;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRetExtMsg() {
		return retExtMsg;
	}

	public void setRetExtMsg(String retExtMsg) {
		this.retExtMsg = retExtMsg;
	}
	
}
