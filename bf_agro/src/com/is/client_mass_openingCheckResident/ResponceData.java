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
"personaldata"
})
@Generated("jsonschema2pojo")
public class ResponceData {

@JsonProperty("personaldata")
private Personaldata personaldata;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("personaldata")
public Personaldata getPersonaldata() {
return personaldata;
}

@JsonProperty("personaldata")
public void setPersonaldata(Personaldata personaldata) {
this.personaldata = personaldata;
}

public ResponceData withPersonaldata(Personaldata personaldata) {
this.personaldata = personaldata;
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

public ResponceData withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}