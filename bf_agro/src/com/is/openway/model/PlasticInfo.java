package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlasticInfo {
	@JsonProperty("FirstName")
	 private String FirstName;
	@JsonProperty("LastName")
	 private String LastName;


	 // Getter Methods 
	@JsonProperty("FirstName")
	 public String getFirstName() {
	  return FirstName;
	 }
	@JsonProperty("LastName")
	 public String getLastName() {
	  return LastName;
	 }

	 // Setter Methods 
	 @JsonProperty("FirstName")
	 public void setFirstName(String FirstName) {
	  this.FirstName = FirstName;
	 }
	 @JsonProperty("LastName")
	 public void setLastName(String LastName) {
	  this.LastName = LastName;
	 }
	}
