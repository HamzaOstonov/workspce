package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parm {
	@JsonProperty("ParmCode")
	 private String ParmCode;
	@JsonProperty("Value")
	 private String Value;


	 // Getter Methods 
	@JsonProperty("ParmCode")
	 public String getParmCode() {
	  return ParmCode;
	 }
	 @JsonProperty("Value")
	 public String getValue() {
	  return Value;
	 }

	 // Setter Methods 
	 @JsonProperty("ParmCode")
	 public void setParmCode(String ParmCode) {
	  this.ParmCode = ParmCode;
	 }
	 @JsonProperty("Value")
	 public void setValue(String Value) {
	  this.Value = Value;
	 }
	}