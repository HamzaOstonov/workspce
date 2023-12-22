package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ClientInfo {
	@JsonProperty("ClientNumber")
	 private String ClientNumber;
	@JsonProperty("RegNumberType")
	 private String RegNumberType;
	@JsonProperty("RegNumber")
	 private String RegNumber;
	@JsonProperty("RegNumberDetails")
	 private String RegNumberDetails;
	@JsonProperty("SocialNumber")
	 private String SocialNumber;
	@JsonProperty("ShortName")
	 private String ShortName;
	@JsonProperty("TaxpayerIdentifier")
	 private String TaxpayerIdentifier;
	@JsonProperty("FirstName")
	 private String FirstName;
	@JsonProperty("LastName")
	 private String LastName;
	@JsonProperty("SecurityName")
	 private String SecurityName;
	@JsonProperty("Country")
	 private String Country;
	@JsonProperty("Citizenship")
	 private String Citizenship;
	@JsonProperty("Language")
	 private String Language;
	@JsonProperty("MaritalStatus")
	 private String MaritalStatus;
	@JsonProperty("Position")
	 private String Position;
	@JsonProperty("CompanyName")
	 private String CompanyName;
	@JsonProperty("CompanyTradeName")
	 private String CompanyTradeName;
	@JsonProperty("BirthDate")
	 private String BirthDate;
	@JsonProperty("BirthPlace")
	 private String BirthPlace;
	@JsonProperty("BirthName")
	 private String BirthName;
	@JsonProperty("Gender")
	 private String Gender;


	 // Getter Methods 
	@JsonProperty("ClientNumber")
	 public String getClientNumber() {
	  return ClientNumber;
	 }
	@JsonProperty("RegNumberType")
	 public String getRegNumberType() {
	  return RegNumberType;
	 }
	@JsonProperty("RegNumber")
	 public String getRegNumber() {
	  return RegNumber;
	 }
	@JsonProperty("RegNumberDetails")
	 public String getRegNumberDetails() {
	  return RegNumberDetails;
	 }
	@JsonProperty("SocialNumber")
	 public String getSocialNumber() {
	  return SocialNumber;
	 }
	@JsonProperty("ShortName")
	 public String getShortName() {
	  return ShortName;
	 }
	@JsonProperty("TaxpayerIdentifier")
	 public String getTaxpayerIdentifier() {
	  return TaxpayerIdentifier;
	 }
	@JsonProperty("FirstName")
	 public String getFirstName() {
	  return FirstName;
	 }
	@JsonProperty("LastName")
	 public String getLastName() {
	  return LastName;
	 }
	@JsonProperty("SecurityName")
	 public String getSecurityName() {
	  return SecurityName;
	 }
	@JsonProperty("Country")
	 public String getCountry() {
	  return Country;
	 }
	@JsonProperty("Citizenship")
	 public String getCitizenship() {
	  return Citizenship;
	 }
	@JsonProperty("Language")
	 public String getLanguage() {
	  return Language;
	 }
	@JsonProperty("MaritalStatus")
	 public String getMaritalStatus() {
	  return MaritalStatus;
	 }
	@JsonProperty("Position")
	 public String getPosition() {
	  return Position;
	 }
	@JsonProperty("CompanyName")
	 public String getCompanyName() {
	  return CompanyName;
	 }
	@JsonProperty("CompanyTradeName")
	 public String getCompanyTradeName() {
	  return CompanyTradeName;
	 }
	@JsonProperty("BirthDate")
	 public String getBirthDate() {
	  return BirthDate;
	 }
	@JsonProperty("BirthPlace")
	 public String getBirthPlace() {
	  return BirthPlace;
	 }
	@JsonProperty("BirthName")
	 public String getBirthName() {
	  return BirthName;
	 }
	@JsonProperty("Gender")
	 public String getGender() {
	  return Gender;
	 }

	 // Setter Methods 
	 @JsonProperty("ClientNumber")
	 public void setClientNumber(String ClientNumber) {
	  this.ClientNumber = ClientNumber;
	 }
		@JsonProperty("RegNumberType")
	 public void setRegNumberType(String RegNumberType) {
	  this.RegNumberType = RegNumberType;
	 }
		@JsonProperty("RegNumber")
	 public void setRegNumber(String RegNumber) {
	  this.RegNumber = RegNumber;
	 }
		@JsonProperty("RegNumberDetails")
	 public void setRegNumberDetails(String RegNumberDetails) {
	  this.RegNumberDetails = RegNumberDetails;
	 }
		@JsonProperty("SocialNumber")
	 public void setSocialNumber(String SocialNumber) {
	  this.SocialNumber = SocialNumber;
	 }
		@JsonProperty("ShortName")
	 public void setShortName(String ShortName) {
	  this.ShortName = ShortName;
	 }
	 @JsonProperty("TaxpayerIdentifier")
	 public void setTaxpayerIdentifier(String TaxpayerIdentifier) {
	  this.TaxpayerIdentifier = TaxpayerIdentifier;
	 }
	 @JsonProperty("FirstName")
	 public void setFirstName(String FirstName) {
	  this.FirstName = FirstName;
	 }
	 @JsonProperty("LastName")
	 public void setLastName(String LastName) {
	  this.LastName = LastName;
	 }
	 @JsonProperty("SecurityName")
	 public void setSecurityName(String SecurityName) {
	  this.SecurityName = SecurityName;
	 }
	 @JsonProperty("Country")
	 public void setCountry(String Country) {
	  this.Country = Country;
	 }
		@JsonProperty("Citizenship")
	 public void setCitizenship(String Citizenship) {
	  this.Citizenship = Citizenship;
	 }
		@JsonProperty("Language")
	 public void setLanguage(String Language) {
	  this.Language = Language;
	 }
		@JsonProperty("MaritalStatus")
	 public void setMaritalStatus(String MaritalStatus) {
	  this.MaritalStatus = MaritalStatus;
	 }
		@JsonProperty("Position")
	 public void setPosition(String Position) {
	  this.Position = Position;
	 }
		@JsonProperty("CompanyName")
	 public void setCompanyName(String CompanyName) {
	  this.CompanyName = CompanyName;
	 }
		@JsonProperty("CompanyTradeName")
	 public void setCompanyTradeName(String CompanyTradeName) {
	  this.CompanyTradeName = CompanyTradeName;
	 }
		@JsonProperty("BirthDate")
	 public void setBirthDate(String BirthDate) {
	  this.BirthDate = BirthDate;
	 }
		@JsonProperty("BirthPlace")
	 public void setBirthPlace(String BirthPlace) {
	  this.BirthPlace = BirthPlace;
	 }
		@JsonProperty("BirthName")
	 public void setBirthName(String BirthName) {
	  this.BirthName = BirthName;
	 }
		@JsonProperty("Gender")
	 public void setGender(String Gender) {
	  this.Gender = Gender;
	 }
	}