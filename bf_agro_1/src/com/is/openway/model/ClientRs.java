package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRs {
	@JsonProperty("Client")
	 private Client ClientObject;
	@JsonProperty("Info")
	 private String Info;


	 // Getter Methods 

	 public Client getClient() {
	  return ClientObject;
	 }

	 public String getInfo() {
	  return Info;
	 }

	 // Setter Methods 

	 public void setClient(Client ClientObject) {
	  this.ClientObject = ClientObject;
	 }

	 public void setInfo(String Info) {
	  this.Info = Info;
	 }
	}
