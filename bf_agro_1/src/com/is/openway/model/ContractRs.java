package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ContractRs {
	@JsonProperty("Contract")
	 private ContractResp ContractObject;
	@JsonProperty("Info")
	 private InfoContract Info;


	 // Getter Methods 
	@JsonProperty("Contract")
	 public ContractResp getContract() {
	  return ContractObject;
	 }
	@JsonProperty("Info")
	 public InfoContract getInfo() {
	  return Info;
	 }

	 // Setter Methods 
	 @JsonProperty("Contract")
	 public void setContract(ContractResp ContractObject) {
	  this.ContractObject = ContractObject;
	 }
	 @JsonProperty("Info")
	 public void setInfo(InfoContract Info) {
	  this.Info = Info;
	 }
	}
