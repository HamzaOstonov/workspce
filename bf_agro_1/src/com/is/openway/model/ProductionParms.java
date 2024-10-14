package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionParms {
	@JsonProperty("CardExpiry")
	private String CardExpiry;
	@JsonProperty("SequenceNumber")
	 private String SequenceNumber;


	 // Getter Methods 
	@JsonProperty("CardExpiry")
	 public String getCardExpiry() {
	  return CardExpiry;
	 }
	@JsonProperty("SequenceNumber")
	 public String getSequenceNumber() {
	  return SequenceNumber;
	 }

	 // Setter Methods 
		@JsonProperty("CardExpiry")
	 public void setCardExpiry(String CardExpiry) {
	  this.CardExpiry = CardExpiry;
	 }
		@JsonProperty("SequenceNumber")
	 public void setSequenceNumber(String SequenceNumber) {
	  this.SequenceNumber = SequenceNumber;
	 }
}
