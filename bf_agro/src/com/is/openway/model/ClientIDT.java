package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientIDT {
	@JsonProperty("ClientInfo")
	 private ClientInfoIdt ClientInfoObject;


	 // Getter Methods 
	@JsonProperty("ClientInfo")
	 public ClientInfoIdt getClientInfo() {
	  return ClientInfoObject;
	 }

	 // Setter Methods 
	@JsonProperty("ClientInfo")
	 public void setClientInfo(ClientInfoIdt ClientInfoObject) {
	  this.ClientInfoObject = ClientInfoObject;
	 }
	}