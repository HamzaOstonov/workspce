package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizPhotoResponse {
	@JsonProperty("Result")
	private int Result;
	@JsonProperty("Data")
	private DataPhoto DataObject;


	 // Getter Methods 
	@JsonProperty("Result")
	 public int getResult() {
	  return Result;
	 }
	@JsonProperty("Data")
	 public DataPhoto getData() {
	  return DataObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Result")
	 public void setResult(int Result) {
	  this.Result = Result;
	 }
	 @JsonProperty("Data")
	 public void setData(DataPhoto DataObject) {
	  this.DataObject = DataObject;
	 }
}
