package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Data")
public class DataRequest {
	@JsonProperty("DataByPinppRequest")
	 private DataByPinppRequest DataByPinppRequestObject;


	 // Getter Methods 
	@JsonProperty("DataByPinppRequest")
	 public DataByPinppRequest getDataByPinppRequest() {
	  return DataByPinppRequestObject;
	 }

	 // Setter Methods 
	@JsonProperty("DataByPinppRequest")
	 public void setDataByPinppRequest(DataByPinppRequest DataByPinppRequestObject) {
	  this.DataByPinppRequestObject = DataByPinppRequestObject;
	 }
}
