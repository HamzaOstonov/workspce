package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName(value = "Data")
public class DataAdd {
	@JsonProperty("Client")
	private ClientAdd ClientObject;


	 // Getter Methods 
	@JsonProperty("Client")
	 public ClientAdd getClient() {
	  return ClientObject;
	 }

	 // Setter Methods 
	@JsonProperty("Client")
	 public void setClient(ClientAdd ClientObject) {
	  this.ClientObject = ClientObject;
	 }
}
