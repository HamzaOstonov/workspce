package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/*blokirovka kartu, sbros pin kod*/

@JsonRootName(value = "MsgData")
public class MsgDataUpdStatus {
	@JsonProperty("Application")
	 private ApplicationStatus ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationStatus getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationStatus ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
