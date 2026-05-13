package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/* SMS XABARNOMANI YOQISH*/
@JsonRootName(value = "Data")
public class DataAddress {
	@JsonProperty("Address")
	 private Address_old AddressObject;


	 // Getter Methods 
	@JsonProperty("Address")
	 public Address_old getAddress() {
	  return AddressObject;
	 }

	 // Setter Methods 
	@JsonProperty("Address")
	 public void setAddress(Address_old AddressObject) {
	  this.AddressObject = AddressObject;
	 }
}
