package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Transaction {
	@JsonProperty("Currency")
	 private String Currency;
	@JsonProperty("Amount")
	 private String Amount;
	@JsonProperty("Extra")
	 private Extra ExtraObject;


	 // Getter Methods 
	@JsonProperty("Currency")
	 public String getCurrency() {
	  return Currency;
	 }
	@JsonProperty("Amount")
	 public String getAmount() {
	  return Amount;
	 }
	@JsonProperty("Extra")
	 public Extra getExtra() {
	  return ExtraObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Currency")
	 public void setCurrency(String Currency) {
	  this.Currency = Currency;
	 }
	 @JsonProperty("Amount")
	 public void setAmount(String Amount) {
	  this.Amount = Amount;
	 }
	 @JsonProperty("Extra")
	 public void setExtra(Extra ExtraObject) {
	  this.ExtraObject = ExtraObject;
	 }
	}
