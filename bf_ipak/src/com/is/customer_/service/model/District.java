package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class District {
	@JsonProperty("Id")
	 private String Id;
	@JsonProperty("Value")
	 private String Value;
	 @JsonProperty("IdValue")
	 private String IdValue;


	 // Getter Methods 
	 @JsonProperty("Id")
	 public String getId() {
	  return Id;
	 }
	 @JsonProperty("Value")
	 public String getValue() {
	  return Value;
	 }
	 @JsonProperty("IdValue")
	 public String getIdValue() {
	  return IdValue;
	 }

	 // Setter Methods 
	 @JsonProperty("Id")
	 public void setId(String Id) {
	  this.Id = Id;
	 }
	 @JsonProperty("Value")
	 public void setValue(String Value) {
	  this.Value = Value;
	 }
	 @JsonProperty("IdValue")
	 public void setIdValue(String IdValue) {
	  this.IdValue = IdValue;
	 }
}
