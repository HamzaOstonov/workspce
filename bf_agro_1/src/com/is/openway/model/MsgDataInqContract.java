package com.is.openway.model;
/*kartani balansi,*/
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataInqContract {
	 @JsonProperty("Information")
	 private InformationContract ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Information")
	 public InformationContract getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Information")
	 public void setApplication(InformationContract ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
