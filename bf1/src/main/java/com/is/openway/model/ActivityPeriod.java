package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityPeriod {
	@JsonProperty("DateFrom")
	private String DateFrom;

	 // Getter Methods 
	@JsonProperty("DateFrom")
	 public String getDateFrom() {
	  return DateFrom;
	 }

	 // Setter Methods 
	@JsonProperty("DateFrom")
	 public void setDateFrom(String DateFrom) {
	  this.DateFrom = DateFrom;
	 }
}
