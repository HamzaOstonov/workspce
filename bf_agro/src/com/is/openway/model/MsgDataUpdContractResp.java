
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataUpdContractResp {
	 @JsonProperty("Application")
	 private ApplicationUpdContractResp ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationUpdContractResp getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationUpdContractResp ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
