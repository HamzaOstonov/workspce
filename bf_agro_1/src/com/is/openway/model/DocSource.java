package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Source")
public class DocSource {
	@JsonProperty("ContractNumber")
	 private String ContractNumber;


	 // Getter Methods 
	@JsonProperty("ContractNumber")
	 public String getContractNumber() {
	  return ContractNumber;
	 }

	 // Setter Methods 
	@JsonProperty("ContractNumber")
	 public void setContractNumber(String ContractNumber) {
	  this.ContractNumber = ContractNumber;
	 }
	}