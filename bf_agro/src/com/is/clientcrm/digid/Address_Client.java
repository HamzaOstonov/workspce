
package com.is.clientcrm.digid;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Kadastr",
    "Country",
    "CountryICAO",
    "CountryName",
    "CountryNameUz",
    "Region",
    "RegionName",
    "RegionNameUz",
    "District",
    "DistrictName",
    "DistrictNameUz",
    "Address",
    "House",
    "Flat",
    "Block",
    "LiveFromDate",
    "Additional"
})
public class Address_Client {

    @JsonProperty("Kadastr")
    private Object kadastr;
    @JsonProperty("Country")
    private Integer country;
    @JsonProperty("CountryICAO")
    private String countryICAO;
    @JsonProperty("CountryName")
    private String countryName;
    @JsonProperty("CountryNameUz")
    private String countryNameUz;
    @JsonProperty("Region")
    private Integer region;
    @JsonProperty("RegionName")
    private String regionName;
    @JsonProperty("RegionNameUz")
    private String regionNameUz;
    @JsonProperty("District")
    private Integer district;
    @JsonProperty("DistrictName")
    private String districtName;
    @JsonProperty("DistrictNameUz")
    private String districtNameUz;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("House")
    private String house;
    @JsonProperty("Flat")
    private String flat;
    @JsonProperty("Block")
    private String block;
    @JsonProperty("LiveFromDate")
    private String liveFromDate;
    @JsonProperty("Additional")
    private Object additional;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Address_Client() {
    }

    /**
     * 
     * @param country
     * @param districtNameUz
     * @param countryNameUz
     * @param address
     * @param districtName
     * @param regionName
     * @param additional
     * @param countryICAO
     * @param regionNameUz
     * @param house
     * @param kadastr
     * @param flat
     * @param district
     * @param block
     * @param countryName
     * @param region
     * @param liveFromDate
     */
    public Address_Client(Object kadastr, Integer country, String countryICAO, String countryName, String countryNameUz, Integer region, String regionName, String regionNameUz, Integer district, String districtName, String districtNameUz, String address, String house, String flat, String block, String liveFromDate, Object additional) {
        super();
        this.kadastr = kadastr;
        this.country = country;
        this.countryICAO = countryICAO;
        this.countryName = countryName;
        this.countryNameUz = countryNameUz;
        this.region = region;
        this.regionName = regionName;
        this.regionNameUz = regionNameUz;
        this.district = district;
        this.districtName = districtName;
        this.districtNameUz = districtNameUz;
        this.address = address;
        this.house = house;
        this.flat = flat;
        this.block = block;
        this.liveFromDate = liveFromDate;
        this.additional = additional;
    }

    @JsonProperty("Kadastr")
    public Object getKadastr() {
        return kadastr;
    }

    @JsonProperty("Kadastr")
    public void setKadastr(Object kadastr) {
        this.kadastr = kadastr;
    }

    @JsonProperty("Country")
    public Integer getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(Integer country) {
        this.country = country;
    }

    @JsonProperty("CountryICAO")
    public String getCountryICAO() {
        return countryICAO;
    }

    @JsonProperty("CountryICAO")
    public void setCountryICAO(String countryICAO) {
        this.countryICAO = countryICAO;
    }

    @JsonProperty("CountryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("CountryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("CountryNameUz")
    public String getCountryNameUz() {
        return countryNameUz;
    }

    @JsonProperty("CountryNameUz")
    public void setCountryNameUz(String countryNameUz) {
        this.countryNameUz = countryNameUz;
    }

    @JsonProperty("Region")
    public Integer getRegion() {
        return region;
    }

    @JsonProperty("Region")
    public void setRegion(Integer region) {
        this.region = region;
    }

    @JsonProperty("RegionName")
    public String getRegionName() {
        return regionName;
    }

    @JsonProperty("RegionName")
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @JsonProperty("RegionNameUz")
    public String getRegionNameUz() {
        return regionNameUz;
    }

    @JsonProperty("RegionNameUz")
    public void setRegionNameUz(String regionNameUz) {
        this.regionNameUz = regionNameUz;
    }

    @JsonProperty("District")
    public Integer getDistrict() {
        return district;
    }

    @JsonProperty("District")
    public void setDistrict(Integer district) {
        this.district = district;
    }

    @JsonProperty("DistrictName")
    public String getDistrictName() {
        return districtName;
    }

    @JsonProperty("DistrictName")
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @JsonProperty("DistrictNameUz")
    public String getDistrictNameUz() {
        return districtNameUz;
    }

    @JsonProperty("DistrictNameUz")
    public void setDistrictNameUz(String districtNameUz) {
        this.districtNameUz = districtNameUz;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("House")
    public String getHouse() {
        return house;
    }

    @JsonProperty("House")
    public void setHouse(String house) {
        this.house = house;
    }

    @JsonProperty("Flat")
    public String getFlat() {
        return flat;
    }

    @JsonProperty("Flat")
    public void setFlat(String flat) {
        this.flat = flat;
    }

    @JsonProperty("Block")
    public String getBlock() {
        return block;
    }

    @JsonProperty("Block")
    public void setBlock(String block) {
        this.block = block;
    }

    @JsonProperty("LiveFromDate")
    public String getLiveFromDate() {
        return liveFromDate;
    }

    @JsonProperty("LiveFromDate")
    public void setLiveFromDate(String liveFromDate) {
        this.liveFromDate = liveFromDate;
    }

    @JsonProperty("Additional")
    public Object getAdditional() {
        return additional;
    }

    @JsonProperty("Additional")
    public void setAdditional(Object additional) {
        this.additional = additional;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
