package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemproaryRegistration {

	@JsonProperty("Cadastre")
	private String Cadastre;
	@JsonProperty("Country")
	private String Country;
	@JsonProperty("Region")
	private Region RegionObject;
	@JsonProperty("District")
	private District DistrictObject;
	@JsonProperty("Address")
	private String Address;
	@JsonProperty("RegistrationDate")
	private String RegistrationDate;
	@JsonProperty("DateFrom")
	private String DateFrom;
	@JsonProperty("DateTill")
	private String DateTill;

	// Getter Methods 
	@JsonProperty("Cadastre")
	 public String getCadastre() {
	  return Cadastre;
	 }
	@JsonProperty("Country")
	 public String getCountry() {
	  return Country;
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
	 public void setCountry(String CountryObject) {
	  this.Country = CountryObject;
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
	@JsonProperty("DateFrom")
	public String getDateFrom() {
		return DateFrom;
	}
	@JsonProperty("DateFrom")
	public void setDateFrom(String dateFrom) {
		DateFrom = dateFrom;
	}
	@JsonProperty("DateTill")	
	public String getDateTill() {
		return DateTill;
	}
	@JsonProperty("DateTill")	
	public void setDateTill(String dateTill) {
		DateTill = dateTill;
	}
	
	
}
