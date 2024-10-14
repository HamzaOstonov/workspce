package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	@JsonProperty("Client")
	private Client ClientObject;


	 // Getter Methods 
	@JsonProperty("Client")
	 public Client getClient() {
	  return ClientObject;
	 }

	 // Setter Methods 
	@JsonProperty("Client")
	 public void setClient(Client ClientObject) {
	  this.ClientObject = ClientObject;
	 }
}
