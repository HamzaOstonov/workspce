package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Extra {
	@JsonProperty("Type")
	 private String Type;
	@JsonProperty("Details")
	 private String Details;


	 // Getter Methods 
	@JsonProperty("Type")
	 public String getType() {
	  return Type;
	 }
	@JsonProperty("Details")
	 public String getDetails() {
	  return Details;
	 }

	 // Setter Methods 
	 @JsonProperty("Type")
	 public void setType(String Type) {
	  this.Type = Type;
	 }
	 @JsonProperty("Details")
	 public void setDetails(String Details) {
	  this.Details = Details;
	 }
	}