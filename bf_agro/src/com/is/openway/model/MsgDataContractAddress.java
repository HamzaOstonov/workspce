package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
/* SMS XABARNOMANI YOQISH*/

@JsonRootName(value = "MsgData")
public class MsgDataContractAddress {
	 @JsonProperty("Application")
	 private ApplicationContractAddress ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationContractAddress getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationContractAddress ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }

}
