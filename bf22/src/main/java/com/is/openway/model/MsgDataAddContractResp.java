
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataAddContractResp {
	 @JsonProperty("Application")
	 private ApplicationResponse ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationResponse getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationResponse ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
