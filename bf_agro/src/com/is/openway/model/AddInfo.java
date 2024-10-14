package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddInfo {
	@JsonProperty("Parm")
	private Parm ParmObject;


	 // Getter Methods 
	@JsonProperty("Parm")
	 public Parm getParm() {
	  return ParmObject;
	 }

	 // Setter Methods 
	@JsonProperty("Parm")
	 public void setParm(Parm ParmObject) {
	  this.ParmObject = ParmObject;
	 }
}
