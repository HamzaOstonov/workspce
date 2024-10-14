
package com.is.openway.model;
/*contract card add ga javob ham shu*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataAddContractCard {
	 @JsonProperty("Application")
	 private ApplicationContractCard ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Application")
	 public ApplicationContractCard getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Application")
	 public void setApplication(ApplicationContractCard ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
