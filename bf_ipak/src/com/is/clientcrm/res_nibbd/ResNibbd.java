package com.is.clientcrm.res_nibbd;

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
"result",
"header",
"response"
})
public class ResNibbd {

@JsonProperty("result")
private Result result;
@JsonProperty("header")
private Header header;
@JsonProperty("response")
private Response response;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public ResNibbd() {
}

/**
*
* @param result
* @param response
* @param header
*/
public ResNibbd(Result result, Header header, Response response) {
super();
this.result = result;
this.header = header;
this.response = response;
}

@JsonProperty("result")
public Result getResult() {
return result;
}

@JsonProperty("result")
public void setResult(Result result) {
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

@JsonProperty("response")
public Response getResponse() {
return response;
}

@JsonProperty("response")
public void setResponse(Response response) {
this.response = response;
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
  return "{"
      + "\"result\":  " + result + ", "
      + "\"header\":  " + header + ", "
      + "\"response\":  \"" + response + "\" "
      + "}";
}


}