package com.is.bpri;

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
"response",
"result",
"header"
})
public class Bureau {

@JsonProperty("response")
private Response response;
@JsonProperty("result")
private Result_ result;
@JsonProperty("header")
private Header header;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Bureau() {
}

/**
*
* @param result
* @param response
* @param header
*/
public Bureau(Response response, Result_ result, Header header) {
super();
this.response = response;
this.result = result;
this.header = header;
}

@JsonProperty("response")
public Response getResponse() {
return response;
}

@JsonProperty("response")
public void setResponse(Response response) {
this.response = response;
}

@JsonProperty("result")
public Result_ getResult() {
return result;
}

@JsonProperty("result")
public void setResult(Result_ result) {
this.result = result;
}

@JsonProperty("header")
public Header getHeader() {
return header;
}

@JsonProperty("header")
public void setHeader(Header header) {
this.header = header;
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