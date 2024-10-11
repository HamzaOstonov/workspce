package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Balance {
	@JsonProperty("Name")
	private String Name;
	@JsonProperty("Type")
	 private String Type;
	@JsonProperty("Amount")
	 private String Amount;
	@JsonProperty("Currency")
	 private String Currency;


	 // Getter Methods 
	@JsonProperty("Name")
	 public String getName() {
	  return Name;
	 }
	@JsonProperty("Type")
	 public String getType() {
	  return Type;
	 }
	@JsonProperty("Amount")
	 public String getAmount() {
	  return Amount;
	 }
	@JsonProperty("Currency")
	 public String getCurrency() {
	  return Currency;
	 }

	 // Setter Methods 
	 @JsonProperty("Name")
	 public void setName(String Name) {
	  this.Name = Name;
	 }
	 @JsonProperty("Type")
	 public void setType(String Type) {
	  this.Type = Type;
	 }
	 @JsonProperty("Amount")
	 public void setAmount(String Amount) {
	  this.Amount = Amount;
	 }
	 @JsonProperty("Currency")
	 public void setCurrency(String Currency) {
	  this.Currency = Currency;
	 }
}
