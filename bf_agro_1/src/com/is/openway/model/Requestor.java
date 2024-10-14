package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Requestor {
	@JsonProperty("RBSNumber")
	 private String RBSNumber;


	 // Getter Methods 
	@JsonProperty("RBSNumber")
	 public String getRBSNumber() {
	  return RBSNumber;
	 }

	 // Setter Methods 
	@JsonProperty("RBSNumber")
	 public void setRBSNumber(String RBSNumber) {
	  this.RBSNumber = RBSNumber;
	 }
	}