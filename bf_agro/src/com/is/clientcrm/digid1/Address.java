package com.is.clientcrm.digid1;

public class Address {
 private Answere answere;
 private String Kadastr;
 private int Country;
 private String CountryICAO;
 private String CountryName;
 private String CountryNameUz;
 private int Region;
 private String RegionName;
 private String RegionNameUz;
 private int District;
 private String DistrictName;
 private String DistrictNameUz;
 private String Address;
 private String House;
 private String Flat;
 private String Block;
 private String LiveFromDate;
 private String Additional;
public Address(Answere answere, String kadastr, int country, String countryICAO, String countryName,
		String countryNameUz, int region, String regionName, String regionNameUz, int district, String districtName,
		String districtNameUz, String address, String house, String flat, String block, String liveFromDate,
		String additional) {
	super();
	this.answere = answere;
	Kadastr = kadastr;
	Country = country;
	CountryICAO = countryICAO;
	CountryName = countryName;
	CountryNameUz = countryNameUz;
	Region = region;
	RegionName = regionName;
	RegionNameUz = regionNameUz;
	District = district;
	DistrictName = districtName;
	DistrictNameUz = districtNameUz;
	Address = address;
	House = house;
	Flat = flat;
	Block = block;
	LiveFromDate = liveFromDate;
	Additional = additional;
}
public Answere getAnswere() {
	return answere;
}
public void setAnswere(Answere answere) {
	this.answere = answere;
}
public String getKadastr() {
	return Kadastr;
}
public void setKadastr(String kadastr) {
	Kadastr = kadastr;
}
public int getCountry() {
	return Country;
}
public void setCountry(int country) {
	Country = country;
}
public String getCountryICAO() {
	return CountryICAO;
}
public void setCountryICAO(String countryICAO) {
	CountryICAO = countryICAO;
}
public String getCountryName() {
	return CountryName;
}
public void setCountryName(String countryName) {
	CountryName = countryName;
}
public String getCountryNameUz() {
	return CountryNameUz;
}
public void setCountryNameUz(String countryNameUz) {
	CountryNameUz = countryNameUz;
}
public int getRegion() {
	return Region;
}
public void setRegion(int region) {
	Region = region;
}
public String getRegionName() {
	return RegionName;
}
public void setRegionName(String regionName) {
	RegionName = regionName;
}
public String getRegionNameUz() {
	return RegionNameUz;
}
public void setRegionNameUz(String regionNameUz) {
	RegionNameUz = regionNameUz;
}
public int getDistrict() {
	return District;
}
public void setDistrict(int district) {
	District = district;
}
public String getDistrictName() {
	return DistrictName;
}
public void setDistrictName(String districtName) {
	DistrictName = districtName;
}
public String getDistrictNameUz() {
	return DistrictNameUz;
}
public void setDistrictNameUz(String districtNameUz) {
	DistrictNameUz = districtNameUz;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getHouse() {
	return House;
}
public void setHouse(String house) {
	House = house;
}
public String getFlat() {
	return Flat;
}
public void setFlat(String flat) {
	Flat = flat;
}
public String getBlock() {
	return Block;
}
public void setBlock(String block) {
	Block = block;
}
public String getLiveFromDate() {
	return LiveFromDate;
}
public void setLiveFromDate(String liveFromDate) {
	LiveFromDate = liveFromDate;
}
public String getAdditional() {
	return Additional;
}
public void setAdditional(String additional) {
	Additional = additional;
}
 
 
}
