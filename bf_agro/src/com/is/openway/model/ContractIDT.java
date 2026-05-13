package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ContractIDT {
	@JsonProperty("RBSNumber")
	 private String RBSNumber;
	@JsonProperty("ContractNumber")
	 private String ContractNumber;
	@JsonProperty("CBSNumber")
	 private String CBSNumber;
	@JsonProperty("Client")
	private Client clientObject;

	 // Getter Methods 
	@JsonProperty("RBSNumber")
	 public String getRBSNumber() {
	  return RBSNumber;
	 }
	@JsonProperty("ContractNumber")
	 public String getContractNumber() {
	  return ContractNumber;
	 }
	@JsonProperty("CBSNumber")
	 public String getCBSNumber() {
	  return CBSNumber;
	 }
	@JsonProperty("Client")
	public Client getClientObject() {
		return clientObject;
	}

	// Setter Methods 
	@JsonProperty("RBSNumber")
	 public void setRBSNumber(String RBSNumber) {
	  this.RBSNumber = RBSNumber;
	 }
	@JsonProperty("ContractNumber")
	 public void setContractNumber(String ContractNumber) {
	  this.ContractNumber = ContractNumber;
	 }
	@JsonProperty("CBSNumber")
	 public void setCBSNumber(String CBSNumber) {
	  this.CBSNumber = CBSNumber;
	 }
	@JsonProperty("Client")
	public void setClientObject(Client clientObject) {
		this.clientObject = clientObject;
	}
}
