package com.is.openway.model;
/*kartani balansi,*/
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "MsgData")
public class MsgDataInqContractResp {
	 @JsonProperty("Information")
	 private InformationContractResp ApplicationObject;


	 // Getter Methods 
	 @JsonProperty("Information")
	 public InformationContractResp getApplication() {
	  return ApplicationObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Information")
	 public void setApplication(InformationContractResp ApplicationObject) {
	  this.ApplicationObject = ApplicationObject;
	 }
}
