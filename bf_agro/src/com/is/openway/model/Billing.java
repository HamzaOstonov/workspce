package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Billing {
	@JsonProperty("PhaseDate")
	 private String PhaseDate;


	 // Getter Methods 
	@JsonProperty("PhaseDate")
	 public String getPhaseDate() {
	  return PhaseDate;
	 }

	 // Setter Methods 
	@JsonProperty("PhaseDate")
	 public void setPhaseDate(String PhaseDate) {
	  this.PhaseDate = PhaseDate;
	 }
	}