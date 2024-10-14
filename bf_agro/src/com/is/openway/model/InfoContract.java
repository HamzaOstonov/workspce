package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Info")
public class InfoContract {
	@JsonProperty("Status")
	 StatusContract StatusObject;


	 // Getter Methods 
	@JsonProperty("Status")
	 public StatusContract getStatus() {
	  return StatusObject;
	 }

	 // Setter Methods 
	@JsonProperty("Status")
	 public void setStatus(StatusContract StatusObject) {
	  this.StatusObject = StatusObject;
	 }
	}