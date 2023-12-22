package com.is.openway.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName(value = "DataRs")
public class DataRsContract {
	@JsonProperty("ContractRs")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList < ContractRs > ContractRsObject ;
	


	 // Getter Methods 
	@JsonProperty("ContractRs")
	 public ArrayList < ContractRs >  getContractRs() {
	  return ContractRsObject;
	 }

	 // Setter Methods 
	@JsonProperty("ContractRs")
	 public void setContractRs(ArrayList < ContractRs >  ContractRsObject) {
	  this.ContractRsObject = ContractRsObject;
	 }
	}