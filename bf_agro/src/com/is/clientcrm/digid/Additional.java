
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
    "Inn",
    "InnDate",
    "TaxCode",
    "TaxName",
    "Inps",
    "InpsDate",
    "InpsDocument",
    "InpsIssuedBy",
    "Additional"
})
public class Additional {

    @JsonProperty("Inn")
    private String inn;
    @JsonProperty("InnDate")
    private String innDate;
    @JsonProperty("TaxCode")
    private String taxCode;
    @JsonProperty("TaxName")
    private Object taxName;
    @JsonProperty("Inps")
    private Object inps;
    @JsonProperty("InpsDate")
    private Object inpsDate;
    @JsonProperty("InpsDocument")
    private Object inpsDocument;
    @JsonProperty("InpsIssuedBy")
    private Object inpsIssuedBy;
    @JsonProperty("Additional")
    private Object additional;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Additional() {
    }

    /**
     * 
     * @param innDate
     * @param additional
     * @param inn
     * @param inps
     * @param inpsDate
     * @param inpsIssuedBy
     * @param inpsDocument
     * @param taxCode
     * @param taxName
     */
    public Additional(String inn, String innDate, String taxCode, Object taxName, Object inps, Object inpsDate, Object inpsDocument, Object inpsIssuedBy, Object additional) {
        super();
        this.inn = inn;
        this.innDate = innDate;
        this.taxCode = taxCode;
        this.taxName = taxName;
        this.inps = inps;
        this.inpsDate = inpsDate;
        this.inpsDocument = inpsDocument;
        this.inpsIssuedBy = inpsIssuedBy;
        this.additional = additional;
    }

    @JsonProperty("Inn")
    public String getInn() {
        return inn;
    }

    @JsonProperty("Inn")
    public void setInn(String inn) {
        this.inn = inn;
    }

    @JsonProperty("InnDate")
    public String getInnDate() {
        return innDate;
    }

    @JsonProperty("InnDate")
    public void setInnDate(String innDate) {
        this.innDate = innDate;
    }

    @JsonProperty("TaxCode")
    public String getTaxCode() {
        return taxCode;
    }

    @JsonProperty("TaxCode")
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @JsonProperty("TaxName")
    public Object getTaxName() {
        return taxName;
    }

    @JsonProperty("TaxName")
    public void setTaxName(Object taxName) {
        this.taxName = taxName;
    }

    @JsonProperty("Inps")
    public Object getInps() {
        return inps;
    }

    @JsonProperty("Inps")
    public void setInps(Object inps) {
        this.inps = inps;
    }

    @JsonProperty("InpsDate")
    public Object getInpsDate() {
        return inpsDate;
    }

    @JsonProperty("InpsDate")
    public void setInpsDate(Object inpsDate) {
        this.inpsDate = inpsDate;
    }

    @JsonProperty("InpsDocument")
    public Object getInpsDocument() {
        return inpsDocument;
    }

    @JsonProperty("InpsDocument")
    public void setInpsDocument(Object inpsDocument) {
        this.inpsDocument = inpsDocument;
    }

    @JsonProperty("InpsIssuedBy")
    public Object getInpsIssuedBy() {
        return inpsIssuedBy;
    }

    @JsonProperty("InpsIssuedBy")
    public void setInpsIssuedBy(Object inpsIssuedBy) {
        this.inpsIssuedBy = inpsIssuedBy;
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
