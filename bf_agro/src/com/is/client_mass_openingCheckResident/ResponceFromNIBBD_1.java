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
"Result",
"Data"
})
@Generated("jsonschema2pojo")
public class ResponceFromNIBBD_1 {

@JsonProperty("Result")
private Integer result;
@JsonProperty("Data")
private ResponceData responceData;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("Result")
public Integer getResult() {
return result;
}

@JsonProperty("Result")
public void setResult(Integer result) {
this.result = result;
}

public ResponceFromNIBBD_1 withResult(Integer result) {
this.result = result;
return this;
}

@JsonProperty("Data")
public ResponceData getResponceData() {
return responceData;
}

@JsonProperty("Data")
public void setResponceData(ResponceData responceData) {
this.responceData = responceData;
}

public ResponceFromNIBBD_1 withData(ResponceData responceData) {
this.responceData = responceData;
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

public ResponceFromNIBBD_1 withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}