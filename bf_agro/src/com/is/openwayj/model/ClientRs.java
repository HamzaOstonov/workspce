package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRs {
	@JsonProperty("Client")
	 private Client_old ClientObject;
	@JsonProperty("Info")
	 private String Info;


	 // Getter Methods 

	 public Client_old getClient() {
	  return ClientObject;
	 }

	 public String getInfo() {
	  return Info;
	 }

	 // Setter Methods 

	 public void setClient(Client_old ClientObject) {
	  this.ClientObject = ClientObject;
	 }

	 public void setInfo(String Info) {
	  this.Info = Info;
	 }
	}
