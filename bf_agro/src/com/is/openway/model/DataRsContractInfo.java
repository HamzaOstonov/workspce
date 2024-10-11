package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName(value = "DataRs")
public class DataRsContractInfo {
	@JsonProperty("ContractRs")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ContractInfoRs ContractRsObject ;
	

	 // Getter Methods 
	@JsonProperty("ContractRs")
	 public ContractInfoRs getContractRs() {
	  return ContractRsObject;
	 }

	 // Setter Methods 
	@JsonProperty("ContractRs")
	 public void setContractRs(ContractInfoRs ContractRsObject) {
	  this.ContractRsObject = ContractRsObject;
	 }
	}