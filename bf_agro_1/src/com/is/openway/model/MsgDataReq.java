package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataReq {
	 @JsonProperty("Information")
	 private InformationReq informationObject;


	 // Getter Methods 
	 @JsonProperty("Information")
	 public InformationReq getInformationObject() {
	  return informationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Information")
	 public void setInformationObject(InformationReq InformationObject) {
	  this.informationObject = InformationObject;
	 }
	}