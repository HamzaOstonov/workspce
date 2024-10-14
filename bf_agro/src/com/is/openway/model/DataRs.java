package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataRs {
	@JsonProperty("ClientRs")
	 private ClientRs ClientRsObject;


	 // Getter Methods 

	 public ClientRs getClientRs() {
	  return ClientRsObject;
	 }

	 // Setter Methods 

	 public void setClientRs(ClientRs ClientRsObject) {
	  this.ClientRsObject = ClientRsObject;
	 }
	}