
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataUpdContract {
	 @JsonProperty("Application")
	 private ApplicationUpdContract ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationUpdContract getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationUpdContract ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
