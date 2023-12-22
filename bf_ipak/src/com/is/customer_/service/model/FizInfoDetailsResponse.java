package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizInfoDetailsResponse {
	 @JsonProperty("Result")
	private int Result;
	@JsonProperty("Data")
	private DataDetails DataObject;


	 // Getter Methods 
	 @JsonProperty("Result")
	 public int getResult() {
	  return Result;
	 }
	 @JsonProperty("Data")
	 public DataDetails getData() {
	  return DataObject;
	 }

	 // Setter Methods 
	 @JsonProperty("Result")
	 public void setResult(int Result) {
	  this.Result = Result;
	 }
	 @JsonProperty("Data")
	 public void setData(DataDetails DataObject) {
	  this.DataObject = DataObject;
	 }
}
