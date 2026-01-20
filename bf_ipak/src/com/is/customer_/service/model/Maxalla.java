package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Maxalla {
	@JsonProperty("Id")
	 private String Id;
	@JsonProperty("Guid")
	 private String Guid;
	 @JsonProperty("Value")
	 private String Value;
	 
	 @JsonProperty("Id")
	public String getId() {
		return Id;
	}
	 @JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}
	 @JsonProperty("Guid")
	public String getGuid() {
		return Guid;
	}
	 @JsonProperty("Guid")
	public void setGuid(String guid) {
		Guid = guid;
	}
	 @JsonProperty("Value")
	public String getValue() {
		return Value;
	}
	 @JsonProperty("Value")
	public void setValue(String value) {
		Value = value;
	}

	 
}
