
package com.is.identification;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "region",
    "address",
    "country",
    "cadastre",
    "district",
    "region_id",
    "country_id",
    "district_id",
    "region_id_cbu",
    "country_id_cbu",
    "district_id_cbu",
    "registration_date"
})
@Generated("jsonschema2pojo")
public class PermanentRegistration {

    @JsonProperty("region")
    private String region;
    @JsonProperty("address")
    private String address;
    @JsonProperty("country")
    private String country;
    @JsonProperty("cadastre")
    private String cadastre;
    @JsonProperty("district")
    private String district;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("country_id")
    private String countryId;
    @JsonProperty("district_id")
    private String districtId;
    @JsonProperty("region_id_cbu")
    private String regionIdCbu;
    @JsonProperty("country_id_cbu")
    private String countryIdCbu;
    @JsonProperty("district_id_cbu")
    private String districtIdCbu;
    @JsonProperty("registration_date")
    private String registrationDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PermanentRegistration() {
    }

    /**
     * 
     * @param country
     * @param address
     * @param districtId
     * @param countryIdCbu
     * @param regionId
     * @param regionIdCbu
     * @param district
     * @param registrationDate
     * @param region
     * @param cadastre
     * @param countryId
     * @param districtIdCbu
     */
    public PermanentRegistration(String region, String address, String country, String cadastre, String district, String regionId, String countryId, String districtId, String regionIdCbu, String countryIdCbu, String districtIdCbu, String registrationDate) {
        super();
        this.region = region;
        this.address = address;
        this.country = country;
        this.cadastre = cadastre;
        this.district = district;
        this.regionId = regionId;
        this.countryId = countryId;
        this.districtId = districtId;
        this.regionIdCbu = regionIdCbu;
        this.countryIdCbu = countryIdCbu;
        this.districtIdCbu = districtIdCbu;
        this.registrationDate = registrationDate;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("cadastre")
    public String getCadastre() {
        return cadastre;
    }

    @JsonProperty("cadastre")
    public void setCadastre(String cadastre) {
        this.cadastre = cadastre;
    }

    @JsonProperty("district")
    public String getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(String district) {
        this.district = district;
    }

    @JsonProperty("region_id")
    public String getRegionId() {
        return regionId;
    }

    @JsonProperty("region_id")
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @JsonProperty("country_id")
    public String getCountryId() {
        return countryId;
    }

    @JsonProperty("country_id")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("district_id")
    public String getDistrictId() {
        return districtId;
    }

    @JsonProperty("district_id")
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @JsonProperty("region_id_cbu")
    public String getRegionIdCbu() {
        return regionIdCbu;
    }

    @JsonProperty("region_id_cbu")
    public void setRegionIdCbu(String regionIdCbu) {
        this.regionIdCbu = regionIdCbu;
    }

    @JsonProperty("country_id_cbu")
    public String getCountryIdCbu() {
        return countryIdCbu;
    }

    @JsonProperty("country_id_cbu")
    public void setCountryIdCbu(String countryIdCbu) {
        this.countryIdCbu = countryIdCbu;
    }

    @JsonProperty("district_id_cbu")
    public String getDistrictIdCbu() {
        return districtIdCbu;
    }

    @JsonProperty("district_id_cbu")
    public void setDistrictIdCbu(String districtIdCbu) {
        this.districtIdCbu = districtIdCbu;
    }

    @JsonProperty("registration_date")
    public String getRegistrationDate() {
        return registrationDate;
    }

    @JsonProperty("registration_date")
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PermanentRegistration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("region");
        sb.append('=');
        sb.append(((this.region == null)?"<null>":this.region));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("cadastre");
        sb.append('=');
        sb.append(((this.cadastre == null)?"<null>":this.cadastre));
        sb.append(',');
        sb.append("district");
        sb.append('=');
        sb.append(((this.district == null)?"<null>":this.district));
        sb.append(',');
        sb.append("regionId");
        sb.append('=');
        sb.append(((this.regionId == null)?"<null>":this.regionId));
        sb.append(',');
        sb.append("countryId");
        sb.append('=');
        sb.append(((this.countryId == null)?"<null>":this.countryId));
        sb.append(',');
        sb.append("districtId");
        sb.append('=');
        sb.append(((this.districtId == null)?"<null>":this.districtId));
        sb.append(',');
        sb.append("regionIdCbu");
        sb.append('=');
        sb.append(((this.regionIdCbu == null)?"<null>":this.regionIdCbu));
        sb.append(',');
        sb.append("countryIdCbu");
        sb.append('=');
        sb.append(((this.countryIdCbu == null)?"<null>":this.countryIdCbu));
        sb.append(',');
        sb.append("districtIdCbu");
        sb.append('=');
        sb.append(((this.districtIdCbu == null)?"<null>":this.districtIdCbu));
        sb.append(',');
        sb.append("registrationDate");
        sb.append('=');
        sb.append(((this.registrationDate == null)?"<null>":this.registrationDate));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
