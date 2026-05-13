
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataReqContract {
	 @JsonProperty("Information")
	 private InformationResponse ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Information")
	 public InformationResponse getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Information")
	 public void setApplication(InformationResponse ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
