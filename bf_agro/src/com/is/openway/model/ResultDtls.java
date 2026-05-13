package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDtls {
	@JsonProperty("Parm")
	 private Parm parmObject;


	 // Getter Methods 
	@JsonProperty("Parm")
	 public Parm getParmObject() {
	  return parmObject;
	 }

	 // Setter Methods 
	@JsonProperty("Parm")
	 public void setParmObject(Parm parmObject) {
	  this.parmObject = parmObject;
	 }
	}
