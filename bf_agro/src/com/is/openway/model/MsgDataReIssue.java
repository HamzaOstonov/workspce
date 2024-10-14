
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/*perevipusk kartu*/

@JsonRootName(value = "MsgData")
public class MsgDataReIssue {
	@JsonProperty("Application")
	 private ApplicationReIssue ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationReIssue getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationReIssue ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
