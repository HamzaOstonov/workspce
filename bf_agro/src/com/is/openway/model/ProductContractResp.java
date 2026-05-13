
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Product")
public class ProductContractResp {
	@JsonProperty("AddInfo")
	private AddInfo addInfo;


	 // Getter Methods 
	@JsonProperty("AddInfo")
	 public AddInfo getAddInfo() {
	  return addInfo;
	 }

	 // Setter Methods 
	@JsonProperty("AddInfo")
	 public void setAddInfo(AddInfo addInfo) {
	  this.addInfo = addInfo;
	 }
}
