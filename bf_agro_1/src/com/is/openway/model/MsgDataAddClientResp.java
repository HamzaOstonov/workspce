
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataAddClientResp {
	 @JsonProperty("Application")
	 private Application ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public Application getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(Application ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
