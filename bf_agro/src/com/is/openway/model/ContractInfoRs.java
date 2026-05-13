package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonRootName(value = "ContractRs")
public class ContractInfoRs {
	@JsonProperty("Contract")
	 private ContractResp ContractObject;
	@JsonProperty("Info")
	 private InfoBalance Info;


	 // Getter Methods 
	@JsonProperty("Contract")
	 public ContractResp getContract() {
	  return ContractObject;
	 }
	@JsonProperty("Info")
	 public InfoBalance getInfo() {
	  return Info;
	 }

	 // Setter Methods 
	 @JsonProperty("Contract")
	 public void setContract(ContractResp ContractObject) {
	  this.ContractObject = ContractObject;
	 }
	 @JsonProperty("Info")
	 public void setInfo(InfoBalance Info) {
	  this.Info = Info;
	 }
	}
