package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectFor {
	@JsonProperty("ClientIDT")
	 private ClientIDT ClientIDTObject;


	 // Getter Methods 
	@JsonProperty("ClientIDT")
	 public ClientIDT getClientIDT() {
	  return ClientIDTObject;
	 }

	 // Setter Methods 
	@JsonProperty("ClientIDT")
	 public void setClientIDT(ClientIDT ClientIDTObject) {
	  this.ClientIDTObject = ClientIDTObject;
	 }
	}