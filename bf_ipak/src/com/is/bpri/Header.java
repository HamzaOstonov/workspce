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
"received",
"code",
"type",
"inquire_id",
"answered"
})
public class Header {

@JsonProperty("received")
private String received;
@JsonProperty("code")
private String code;
@JsonProperty("type")
private String type;
@JsonProperty("inquire_id")
private String inquireId;
@JsonProperty("answered")
private String answered;
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
* @param code
* @param answered
* @param inquireId
* @param received
* @param type
*/
public Header(String received, String code, String type, String inquireId, String answered) {
super();
this.received = received;
this.code = code;
this.type = type;
this.inquireId = inquireId;
this.answered = answered;
}

@JsonProperty("received")
public String getReceived() {
return received;
}

@JsonProperty("received")
public void setReceived(String received) {
this.received = received;
}

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("inquire_id")
public String getInquireId() {
return inquireId;
}

@JsonProperty("inquire_id")
public void setInquireId(String inquireId) {
this.inquireId = inquireId;
}

@JsonProperty("answered")
public String getAnswered() {
return answered;
}

@JsonProperty("answered")
public void setAnswered(String answered) {
this.answered = answered;
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