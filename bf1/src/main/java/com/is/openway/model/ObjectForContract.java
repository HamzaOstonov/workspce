package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ObjectFor")
public class ObjectForContract {
	@JsonProperty("ContractIDT")
	 private ContractIDT ClientIDTObject;


	 // Getter Methods 
	@JsonProperty("ContractIDT")
	 public ContractIDT getContractIDT() {
	  return ClientIDTObject;
	 }

	 // Setter Methods 
	@JsonProperty("ContractIDT")
	 public void setContractIDT(ContractIDT ClientIDTObject) {
	  this.ClientIDTObject = ClientIDTObject;
	 }
	}