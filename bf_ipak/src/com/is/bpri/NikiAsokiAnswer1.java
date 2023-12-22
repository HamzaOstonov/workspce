package com.is.bpri;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.is.bpri.Response;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"result",
"bureau"
})
public class NikiAsokiAnswer1 {

@JsonProperty("result")
private Result result;
@JsonProperty("bureau")
private Bureau bureau;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public NikiAsokiAnswer1() {
}

/**
*
* @param result
* @param bureau
*/
public NikiAsokiAnswer1(Result result, Bureau bureau) {
super();
this.result = result;
this.bureau = bureau;
}

@JsonProperty("result")
public Result getResult() {
return result;
}

@JsonProperty("result")
public void setResult(Result result) {
this.result = result;
}

@JsonProperty("bureau")
public Bureau getBureau() {
return bureau;
}

@JsonProperty("bureau")
public void setBureau(Bureau bureau) {
this.bureau = bureau;
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