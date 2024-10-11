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
"query_id",
"inquire",
"respond"
})
public class Header {

@JsonProperty("query_id")
private String queryId;
@JsonProperty("inquire")
private String inquire;
@JsonProperty("respond")
private String respond;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Header() {
}

/**
*
* @param inquire
* @param respond
* @param queryId
*/
public Header(String queryId, String inquire, String respond) {
super();
this.queryId = queryId;
this.inquire = inquire;
this.respond = respond;
}

@JsonProperty("query_id")
public String getQueryId() {
return queryId;
}

@JsonProperty("query_id")
public void setQueryId(String queryId) {
this.queryId = queryId;
}

@JsonProperty("inquire")
public String getInquire() {
return inquire;
}

@JsonProperty("inquire")
public void setInquire(String inquire) {
this.inquire = inquire;
}

@JsonProperty("respond")
public String getRespond() {
return respond;
}

@JsonProperty("respond")
public void setRespond(String respond) {
this.respond = respond;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}
public String toString() {
	return "{" + "\"queryId\":\"" + queryId + "\", " + "\"inquire\":\"" + inquire + "\", " + "\"respond\":\"" + respond +"\" " + "}";
}

}