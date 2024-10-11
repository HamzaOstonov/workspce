
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
    "Name",
    "Surname",
    "BirthDate",
    "Nationality",
    "Sex",
    "ExpiryDate",
    "DocumentNumber",
    "DocumentType",
    "Issuer",
    "Pinpp",
    "Additional"
})
public class PersonPassport {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Surname")
    private String surname;
    @JsonProperty("BirthDate")
    private String birthDate;
    @JsonProperty("Nationality")
    private String nationality;
    @JsonProperty("Sex")
    private String sex;
    @JsonProperty("ExpiryDate")
    private String expiryDate;
    @JsonProperty("DocumentNumber")
    private String documentNumber;
    @JsonProperty("DocumentType")
    private String documentType;
    @JsonProperty("Issuer")
    private String issuer;
    @JsonProperty("Pinpp")
    private String pinpp;
    @JsonProperty("Additional")
    private Object additional;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("Surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonProperty("BirthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("BirthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("Nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonProperty("Nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonProperty("Sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("Sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("ExpiryDate")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("ExpiryDate")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @JsonProperty("DocumentNumber")
    public String getDocumentNumber() {
        return documentNumber;
    }

    @JsonProperty("DocumentNumber")
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @JsonProperty("DocumentType")
    public String getDocumentType() {
        return documentType;
    }

    @JsonProperty("DocumentType")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("Issuer")
    public String getIssuer() {
        return issuer;
    }

    @JsonProperty("Issuer")
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @JsonProperty("Pinpp")
    public String getPinpp() {
        return pinpp;
    }

    @JsonProperty("Pinpp")
    public void setPinpp(String pinpp) {
        this.pinpp = pinpp;
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
