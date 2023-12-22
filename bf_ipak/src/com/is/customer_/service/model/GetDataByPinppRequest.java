package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDataByPinppRequest {
@JsonProperty("Data")
  private DataRequest DataObject;


  // Getter Methods 
	@JsonProperty("Data")
  public DataRequest getData() {
   return DataObject;
  }

  // Setter Methods 
	@JsonProperty("Data")
  public void setData(DataRequest DataObject) {
   this.DataObject = DataObject;
  }
}
