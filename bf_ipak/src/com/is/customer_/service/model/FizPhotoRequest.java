package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FizPhotoRequest {
	@JsonProperty("GetDataByPinppRequest")
	private GetDataByPinppRequestPhoto GetDataByPinppRequestObject;


 // Getter Methods 
	@JsonProperty("GetDataByPinppRequest")
 public GetDataByPinppRequestPhoto getGetDataByPinppRequest() {
  return GetDataByPinppRequestObject;
 }

 // Setter Methods 
	@JsonProperty("GetDataByPinppRequest")
 public void setGetDataByPinppRequest(GetDataByPinppRequestPhoto GetDataByPinppRequestObject) {
  this.GetDataByPinppRequestObject = GetDataByPinppRequestObject;
 }

}
