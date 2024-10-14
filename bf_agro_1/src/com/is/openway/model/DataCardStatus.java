package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "Data")
public class DataCardStatus {
	@JsonProperty("SetStatus")
	private SetStatus SetStatusObject;


	 // Getter Methods 
	@JsonProperty("SetStatus")
	 public SetStatus getSetStatus() {
	  return SetStatusObject;
	 }

	 // Setter Methods 
	@JsonProperty("SetStatus")
	 public void setSetStatus(SetStatus SetStatusObject) {
	  this.SetStatusObject = SetStatusObject;
	 }

}
