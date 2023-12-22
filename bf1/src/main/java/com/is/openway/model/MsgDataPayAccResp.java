
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataPayAccResp {
	 @JsonProperty("Doc")
	 private DocResp ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Doc")
	 public DocResp getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Doc")
	 public void setApplication(DocResp ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
