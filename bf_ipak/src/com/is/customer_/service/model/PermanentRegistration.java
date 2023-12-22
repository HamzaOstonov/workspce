package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermanentRegistration {
	
	@JsonProperty("Cadastre")
	private String Cadastre;
	@JsonProperty("Country")
	private Country CountryObject;
	@JsonProperty("Region")
	private Region RegionObject;
	@JsonProperty("District")
	private District DistrictObject;
	@JsonProperty("Address")
	private String Address;
	@JsonProperty("RegistrationDate")
	private String RegistrationDate;


	 // Getter Methods 
	@JsonProperty("Cadastre")
	 public String getCadastre() {
	  return Cadastre;
	 }
	@JsonProperty("Country")
	 public Country getCountry() {
	  return CountryObject;
	 }
	@JsonProperty("Region")
	 public Region getRegion() {
	  return RegionObject;
	 }
	@JsonProperty("District")
	 public District getDistrict() {
	  return DistrictObject;
	 }
	@JsonProperty("Address")
	 public String getAddress() {
	  return Address;
	 }
	@JsonProperty("RegistrationDate")
	 public String getRegistrationDate() {
	  return RegistrationDate;
	 }

	 // Setter Methods 
	 @JsonProperty("Cadastre")
	 public void setCadastre(String Cadastre) {
	  this.Cadastre = Cadastre;
	 }
	@JsonProperty("Country")
	 public void setCountry(Country CountryObject) {
	  this.CountryObject = CountryObject;
	 }
	@JsonProperty("Region")
	 public void setRegion(Region RegionObject) {
	  this.RegionObject = RegionObject;
	 }
	@JsonProperty("District")
	 public void setDistrict(District DistrictObject) {
	  this.DistrictObject = DistrictObject;
	 }
	@JsonProperty("Address")
	 public void setAddress(String Address) {
	  this.Address = Address;
	 }
	@JsonProperty("RegistrationDate")
	 public void setRegistrationDate(String RegistrationDate) {
	  this.RegistrationDate = RegistrationDate;
	 }
}
