
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataPayAcc {
	 @JsonProperty("Doc")
	 private Doc ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Doc")
	 public Doc getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Doc")
	 public void setApplication(Doc ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
