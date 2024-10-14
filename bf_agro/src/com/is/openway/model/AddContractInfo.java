package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddContractInfo {
	@JsonProperty("AddInfo01")
	private String AddInfo01;


	 // Getter Methods 
	@JsonProperty("AddInfo01")
	 public String getAddInfo01() {
	  return AddInfo01;
	 }

	 // Setter Methods 
	@JsonProperty("AddInfo01")
	 public void setAddInfo01(String AddInfo01) {
	  this.AddInfo01 = AddInfo01;
	 }
}
