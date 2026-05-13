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
"AnswereId",
"AnswereComment"
})
public class Answere {

@JsonProperty("AnswereId")
private Integer answereId;
@JsonProperty("AnswereComment")
private String answereComment;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
* 
*/
public Answere() {
}

/**
* 
* @param answereComment
* @param answereId
*/
public Answere(Integer answereId, String answereComment) {
super();
this.answereId = answereId;
this.answereComment = answereComment;
}

@JsonProperty("AnswereId")
public Integer getAnswereId() {
return answereId;
}

@JsonProperty("AnswereId")
public void setAnswereId(Integer answereId) {
this.answereId = answereId;
}

@JsonProperty("AnswereComment")
public String getAnswereComment() {
return answereComment;
}

@JsonProperty("AnswereComment")
public void setAnswereComment(String answereComment) {
this.answereComment = answereComment;
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