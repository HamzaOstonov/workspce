package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Client {

	 @JsonProperty("client_type")
	    private String clientType;

	    @JsonProperty("client_number")
	    private String clientNumber;

	    @JsonProperty("reg_number_type")
	    private String regNumberType;

	    @JsonProperty("reg_number")
	    private String regNumber;

	    @JsonProperty("reg_number_details")
	    private String regNumberDetails;

	    @JsonProperty("social_number")
	    private String socialNumber;

	    @JsonProperty("short_name")
	    private String shortName;

	    @JsonProperty("taxpayer_identifier")
	    private String taxpayerIdentifier;

	    @JsonProperty("first_name")
	    private String firstName;

	    @JsonProperty("last_name")
	    private String lastName;

	    @JsonProperty("middle_name")
	    private String middleName;

	    @JsonProperty("security_name")
	    private String securityName;

	    @JsonProperty("country")
	    private String country;

	    @JsonProperty("citizenship")
	    private String citizenship;

	    @JsonProperty("language")
	    private String language;

	    @JsonProperty("birth_place")
	    private String birthPlace;

	    @JsonProperty("birth_date")
	    private String birthDate;

	    @JsonProperty("birth_name")
	    private String birthName;

	    @JsonProperty("gender")
	    private String gender;

	    @JsonProperty("plastic_info")
	    private PlasticInfo plasticInfo;

	    @JsonProperty("phone_list")
	    private List<Phone> phoneList;

	    @JsonProperty("date_open")
	    private String dateOpen;

	    @JsonProperty("base_address")
	    private BaseAddress baseAddress;


	    @JsonProperty("client_type")
		public String getClientType() {
			return clientType;
		}
	    @JsonProperty("client_number")
		public String getClientNumber() {
			return clientNumber;
		}
	    @JsonProperty("reg_number_type")
		public String getRegNumberType() {
			return regNumberType;
		}
	    @JsonProperty("reg_number")
		public String getRegNumber() {
			return regNumber;
		}
	    @JsonProperty("reg_number_details")
		public String getRegNumberDetails() {
			return regNumberDetails;
		}
	    @JsonProperty("social_number")
		public String getSocialNumber() {
			return socialNumber;
		}
	    @JsonProperty("short_name")
		public String getShortName() {
			return shortName;
		}
	    @JsonProperty("taxpayer_identifier")
		public String getTaxpayerIdentifier() {
			return taxpayerIdentifier;
		}
	    @JsonProperty("first_name")
		public String getFirstName() {
			return firstName;
		}
	    @JsonProperty("last_name")
		public String getLastName() {
			return lastName;
		}
	    @JsonProperty("middle_name")
		public String getMiddleName() {
			return middleName;
		}
	    @JsonProperty("security_name")
		public String getSecurityName() {
			return securityName;
		}
	    @JsonProperty("country")
		public String getCountry() {
			return country;
		}
	    @JsonProperty("citizenship")
		public String getCitizenship() {
			return citizenship;
		}
	    @JsonProperty("language")
		public String getLanguage() {
			return language;
		}
    
	    @JsonProperty("birth_place")
		public String getBirthPlace() {
			return birthPlace;
		}
	    @JsonProperty("birth_date")

		public String getBirthDate() {
			return birthDate;
		}
	    @JsonProperty("birth_name")
		public String getBirthName() {
			return birthName;
		}
	    @JsonProperty("gender")
		public String getGender() {
			return gender;
		}
	    @JsonProperty("plastic_info")
		public PlasticInfo getPlasticInfo() {
			return plasticInfo;
		}
	    @JsonProperty("phone_list")
		public List<Phone> getPhoneList() {
			return phoneList;
		}
	    @JsonProperty("date_open")
		public String getDateOpen() {
			return dateOpen;
		}
	    @JsonProperty("base_address")
		public BaseAddress getBaseAddress() {
			return baseAddress;
		}

	    @JsonProperty("client_type")
		public void setClientType(String clientType) {
			this.clientType = clientType;
		}
	    @JsonProperty("client_number")
		public void setClientNumber(String clientNumber) {
			this.clientNumber = clientNumber;
		}
	    @JsonProperty("reg_number_type")
		public void setRegNumberType(String regNumberType) {
			this.regNumberType = regNumberType;
		}
	    @JsonProperty("reg_number")
		public void setRegNumber(String regNumber) {
			this.regNumber = regNumber;
		}
	    @JsonProperty("reg_number_details")
		public void setRegNumberDetails(String regNumberDetails) {
			this.regNumberDetails = regNumberDetails;
		}
	    @JsonProperty("social_number")
		public void setSocialNumber(String socialNumber) {
			this.socialNumber = socialNumber;
		}
	    @JsonProperty("short_name")
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
	    @JsonProperty("taxpayer_identifier")
		public void setTaxpayerIdentifier(String taxpayerIdentifier) {
			this.taxpayerIdentifier = taxpayerIdentifier;
		}
	    @JsonProperty("first_name")
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	    @JsonProperty("last_name")
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    @JsonProperty("middle_name")
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
	    @JsonProperty("security_name")
		public void setSecurityName(String securityName) {
			this.securityName = securityName;
		}
	    @JsonProperty("country")
		public void setCountry(String country) {
			this.country = country;
		}
	    @JsonProperty("citizenship")
		public void setCitizenship(String citizenship) {
			this.citizenship = citizenship;
		}
	    @JsonProperty("language")
		public void setLanguage(String language) {
			this.language = language;
		}
	    @JsonProperty("birth_place")
		public void setBirthPlace(String birthPlace) {
			this.birthPlace = birthPlace;
		}
	    @JsonProperty("birth_date")
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
	    @JsonProperty("birth_name")
		public void setBirthName(String birthName) {
			this.birthName = birthName;
		}
	    @JsonProperty("gender")
		public void setGender(String gender) {
			this.gender = gender;
		}
	    @JsonProperty("plastic_info")
		public void setPlasticInfo(PlasticInfo plasticInfo) {
			this.plasticInfo = plasticInfo;
		}
	    @JsonProperty("phone_list")
		public void setPhoneList(List<Phone> phoneList) {
			this.phoneList = phoneList;
		}
	    @JsonProperty("date_open")
		public void setDateOpen(String dateOpen) {
			this.dateOpen = dateOpen;
		}
	    @JsonProperty("base_address")
		public void setBaseAddress(BaseAddress baseAddress) {
			this.baseAddress = baseAddress;
		}
	    
	    
}
