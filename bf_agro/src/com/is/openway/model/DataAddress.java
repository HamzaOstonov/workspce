package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/* SMS XABARNOMANI YOQISH*/
@JsonRootName(value = "Data")
public class DataAddress {
	@JsonProperty("Address")
	 private Address AddressObject;


	 // Getter Methods 
	@JsonProperty("Address")
	 public Address getAddress() {
	  return AddressObject;
	 }

	 // Setter Methods 
	@JsonProperty("Address")
	 public void setAddress(Address AddressObject) {
	  this.AddressObject = AddressObject;
	 }
}
