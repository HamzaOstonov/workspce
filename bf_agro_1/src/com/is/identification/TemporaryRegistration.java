
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
    "date_from",
    "date_till",
    "region_id",
    "country_id",
    "district_id",
    "region_id_cbu",
    "country_id_cbu",
    "district_id_cbu"
})
@Generated("jsonschema2pojo")
public class TemporaryRegistration {

    @JsonProperty("region")
    private Object region;
    @JsonProperty("address")
    private Object address;
    @JsonProperty("country")
    private Object country;
    @JsonProperty("cadastre")
    private Object cadastre;
    @JsonProperty("district")
    private Object district;
    @JsonProperty("date_from")
    private Object dateFrom;
    @JsonProperty("date_till")
    private Object dateTill;
    @JsonProperty("region_id")
    private Object regionId;
    @JsonProperty("country_id")
    private Object countryId;
    @JsonProperty("district_id")
    private Object districtId;
    @JsonProperty("region_id_cbu")
    private Object regionIdCbu;
    @JsonProperty("country_id_cbu")
    private Object countryIdCbu;
    @JsonProperty("district_id_cbu")
    private Object districtIdCbu;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TemporaryRegistration() {
    }

    /**
     * 
     * @param country
     * @param address
     * @param dateFrom
     * @param cadastre
     * @param countryId
     * @param districtId
     * @param countryIdCbu
     * @param regionId
     * @param regionIdCbu
     * @param district
     * @param dateTill
     * @param region
     * @param districtIdCbu
     */
    public TemporaryRegistration(Object region, Object address, Object country, Object cadastre, Object district, Object dateFrom, Object dateTill, Object regionId, Object countryId, Object districtId, Object regionIdCbu, Object countryIdCbu, Object districtIdCbu) {
        super();
        this.region = region;
        this.address = address;
        this.country = country;
        this.cadastre = cadastre;
        this.district = district;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
        this.regionId = regionId;
        this.countryId = countryId;
        this.districtId = districtId;
        this.regionIdCbu = regionIdCbu;
        this.countryIdCbu = countryIdCbu;
        this.districtIdCbu = districtIdCbu;
    }

    @JsonProperty("region")
    public Object getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(Object region) {
        this.region = region;
    }

    @JsonProperty("address")
    public Object getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Object address) {
        this.address = address;
    }

    @JsonProperty("country")
    public Object getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Object country) {
        this.country = country;
    }

    @JsonProperty("cadastre")
    public Object getCadastre() {
        return cadastre;
    }

    @JsonProperty("cadastre")
    public void setCadastre(Object cadastre) {
        this.cadastre = cadastre;
    }

    @JsonProperty("district")
    public Object getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(Object district) {
        this.district = district;
    }

    @JsonProperty("date_from")
    public Object getDateFrom() {
        return dateFrom;
    }

    @JsonProperty("date_from")
    public void setDateFrom(Object dateFrom) {
        this.dateFrom = dateFrom;
    }

    @JsonProperty("date_till")
    public Object getDateTill() {
        return dateTill;
    }

    @JsonProperty("date_till")
    public void setDateTill(Object dateTill) {
        this.dateTill = dateTill;
    }

    @JsonProperty("region_id")
    public Object getRegionId() {
        return regionId;
    }

    @JsonProperty("region_id")
    public void setRegionId(Object regionId) {
        this.regionId = regionId;
    }

    @JsonProperty("country_id")
    public Object getCountryId() {
        return countryId;
    }

    @JsonProperty("country_id")
    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("district_id")
    public Object getDistrictId() {
        return districtId;
    }

    @JsonProperty("district_id")
    public void setDistrictId(Object districtId) {
        this.districtId = districtId;
    }

    @JsonProperty("region_id_cbu")
    public Object getRegionIdCbu() {
        return regionIdCbu;
    }

    @JsonProperty("region_id_cbu")
    public void setRegionIdCbu(Object regionIdCbu) {
        this.regionIdCbu = regionIdCbu;
    }

    @JsonProperty("country_id_cbu")
    public Object getCountryIdCbu() {
        return countryIdCbu;
    }

    @JsonProperty("country_id_cbu")
    public void setCountryIdCbu(Object countryIdCbu) {
        this.countryIdCbu = countryIdCbu;
    }

    @JsonProperty("district_id_cbu")
    public Object getDistrictIdCbu() {
        return districtIdCbu;
    }

    @JsonProperty("district_id_cbu")
    public void setDistrictIdCbu(Object districtIdCbu) {
        this.districtIdCbu = districtIdCbu;
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
        sb.append(TemporaryRegistration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("dateFrom");
        sb.append('=');
        sb.append(((this.dateFrom == null)?"<null>":this.dateFrom));
        sb.append(',');
        sb.append("dateTill");
        sb.append('=');
        sb.append(((this.dateTill == null)?"<null>":this.dateTill));
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
