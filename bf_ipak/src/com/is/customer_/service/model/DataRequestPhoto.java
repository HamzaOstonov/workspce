package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Data")
public class DataRequestPhoto {
	@JsonProperty("DataByPinppRequest")
	private DataByPinppRequestPhoto DataByPinppRequestObject;

	// Getter Methods 
	@JsonProperty("DataByPinppRequest")	
	public DataByPinppRequestPhoto getDataByPinppRequest() {
	  return DataByPinppRequestObject;
	}

	// Setter Methods 
	@JsonProperty("DataByPinppRequest")	
	public void setDataByPinppRequest(DataByPinppRequestPhoto DataByPinppRequestObject) {
	  this.DataByPinppRequestObject = DataByPinppRequestObject;
	}
}
