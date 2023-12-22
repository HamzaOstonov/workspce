package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDataByPinppRequestPhoto {
	@JsonProperty("Data")
	 private DataRequestPhoto DataObject;


	 // Getter Methods 
	@JsonProperty("Data")
	 public DataRequestPhoto getData() {
	  return DataObject;
	 }

	 // Setter Methods 
	@JsonProperty("Data")
	 public void setData(DataRequestPhoto DataObject) {
	  this.DataObject = DataObject;
	 }
}
