
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataAddContract {
	 @JsonProperty("Application")
	 private ApplicationContract ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationContract getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationContract ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
