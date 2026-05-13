package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Body {
	
	@JsonProperty("client_number")
    private String clientNumber;

    @JsonProperty("registration_type")
    private String registrationType;

    @JsonProperty("registration_number")
    private String registrationNumber;

    @JsonProperty("registration_details")
    private String registrationDetails;

    @JsonProperty("social_number")
    private String socialNumber;

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

    @JsonProperty("position")
    private String position;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("birth_place")
    private String birthPlace;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("date_open")
    private String dateOpen;

    @JsonProperty("phone_list")
    private List<Phone> phoneList;

    @JsonProperty("address")
    private Address address;

    
    @JsonProperty("client_number")
	public String getClientNumber() {
		return clientNumber;
	}
    @JsonProperty("registration_type")
	public String getRegistrationType() {
		return registrationType;
	}
    
    @JsonProperty("registration_number")
	public String getRegistrationNumber() {
		return registrationNumber;
	}

    @JsonProperty("registration_details")
    public String getRegistrationDetails() {
		return registrationDetails;
	}

    @JsonProperty("social_number")
    public String getSocialNumber() {
		return socialNumber;
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

    @JsonProperty("position")
	public String getPosition() {
		return position;
	}

    @JsonProperty("birth_date")
	public String getBirthDate() {
		return birthDate;
	}

    @JsonProperty("birth_place")
	public String getBirthPlace() {
		return birthPlace;
	}

    @JsonProperty("gender")
	public String getGender() {
		return gender;
	}

    @JsonProperty("date_open")
	public String getDateOpen() {
		return dateOpen;
	}
    
    @JsonProperty("phone_list")
	public List<Phone> getPhoneList() {
		return phoneList;
	}

    @JsonProperty("address")
	public Address getAddress() {
		return address;
	}

    @JsonProperty("client_number")
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
    
    @JsonProperty("registration_type")
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
    
    @JsonProperty("registration_number")
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

    @JsonProperty("registration_details")
	public void setRegistrationDetails(String registrationDetails) {
		this.registrationDetails = registrationDetails;
	}

    @JsonProperty("social_number")
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
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

    @JsonProperty("position")
	public void setPosition(String position) {
		this.position = position;
	}

    @JsonProperty("birth_date")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

    @JsonProperty("birth_place")
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

    @JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

    @JsonProperty("date_open")
	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}

    @JsonProperty("phone_list")
	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

    @JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

    
}
