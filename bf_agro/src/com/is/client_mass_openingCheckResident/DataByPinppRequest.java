package com.is.client_mass_openingCheckResident; 

import java.util.LinkedHashMap;
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
"queryID",
"pinpp",
"document",
"langId",
"orgtin",
"fio",
"pin",
"is_consent"
})
@Generated("jsonschema2pojo")
public class DataByPinppRequest {

@JsonProperty("queryID")
private String queryID;
@JsonProperty("pinpp")
private String pinpp;
@JsonProperty("document")
private String document;
@JsonProperty("langId")
private Integer langId;
@JsonProperty("orgtin")
private String orgtin;
@JsonProperty("fio")
private String fio;
@JsonProperty("pin")
private String pin;
@JsonProperty("is_consent")
private String isConsent;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("queryID")
public String getQueryID() {
return queryID;
}

@JsonProperty("queryID")
public void setQueryID(String queryID) {
this.queryID = queryID;
}

public DataByPinppRequest withQueryID(String queryID) {
this.queryID = queryID;
return this;
}

@JsonProperty("pinpp")
public String getPinpp() {
return pinpp;
}

@JsonProperty("pinpp")
public void setPinpp(String pinpp) {
this.pinpp = pinpp;
}

public DataByPinppRequest withPinpp(String pinpp) {
this.pinpp = pinpp;
return this;
}

@JsonProperty("document")
public String getDocument() {
return document;
}

@JsonProperty("document")
public void setDocument(String document) {
this.document = document;
}

public DataByPinppRequest withDocument(String document) {
this.document = document;
return this;
}

@JsonProperty("langId")
public Integer getLangId() {
return langId;
}

@JsonProperty("langId")
public void setLangId(Integer langId) {
this.langId = langId;
}

public DataByPinppRequest withLangId(Integer langId) {
this.langId = langId;
return this;
}

@JsonProperty("orgtin")
public String getOrgtin() {
return orgtin;
}

@JsonProperty("orgtin")
public void setOrgtin(String orgtin) {
this.orgtin = orgtin;
}

public DataByPinppRequest withOrgtin(String orgtin) {
this.orgtin = orgtin;
return this;
}

@JsonProperty("fio")
public String getFio() {
return fio;
}

@JsonProperty("fio")
public void setFio(String fio) {
this.fio = fio;
}

public DataByPinppRequest withFio(String fio) {
this.fio = fio;
return this;
}

@JsonProperty("pin")
public String getPin() {
return pin;
}

@JsonProperty("pin")
public void setPin(String pin) {
this.pin = pin;
}

public DataByPinppRequest withPin(String pin) {
this.pin = pin;
return this;
}

@JsonProperty("is_consent")
public String getIsConsent() {
return isConsent;
}

@JsonProperty("is_consent")
public void setIsConsent(String isConsent) {
this.isConsent = isConsent;
}

public DataByPinppRequest withIsConsent(String isConsent) {
this.isConsent = isConsent;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public DataByPinppRequest withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
