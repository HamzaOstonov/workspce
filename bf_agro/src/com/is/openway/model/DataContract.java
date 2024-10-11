package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName(value = "Data")
public class DataContract {
	@JsonProperty("Contract")
	private ContractAdd contractObject;


	 // Getter Methods 
	@JsonProperty("Contract")
	 public ContractAdd getContract() {
	  return contractObject;
	 }

	 // Setter Methods 
	@JsonProperty("Contract")
	 public void setContract(ContractAdd contractObject) {
	  this.contractObject = contractObject;
	 }
}
