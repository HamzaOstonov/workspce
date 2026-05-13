package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransType {
	@JsonProperty("TransCode")
	 private TransCode TransCodeObject;


	 // Getter Methods 
	@JsonProperty("TransCode")
	 public TransCode getTransCode() {
	  return TransCodeObject;
	 }

	 // Setter Methods 
	@JsonProperty("TransCode")
	 public void setTransCode(TransCode TransCodeObject) {
	  this.TransCodeObject = TransCodeObject;
	 }
	}