package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizInfoDetailsRequest {
	@JsonProperty("GetDataByPinppRequest")
  private GetDataByPinppRequest GetDataByPinppRequestObject;
  

  // Getter Methods 
	@JsonProperty("GetDataByPinppRequest")
  public GetDataByPinppRequest getGetDataByPinppRequest() {
   return GetDataByPinppRequestObject;
  }

  // Setter Methods 
	@JsonProperty("GetDataByPinppRequest")
  public void setGetDataByPinppRequest(GetDataByPinppRequest GetDataByPinppRequestObject) {
   this.GetDataByPinppRequestObject = GetDataByPinppRequestObject;
  }
}
