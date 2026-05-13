package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	@JsonProperty("Client")
	private Client_old ClientObject;


	 // Getter Methods 
	@JsonProperty("Client")
	 public Client_old getClient() {
	  return ClientObject;
	 }

	 // Setter Methods 
	@JsonProperty("Client")
	 public void setClient(Client_old ClientObject) {
	  this.ClientObject = ClientObject;
	 }
}
