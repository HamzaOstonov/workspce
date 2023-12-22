package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Personaldata {
	@JsonProperty("row")
  private Row RowObject;


  // Getter Methods 
	@JsonProperty("row")
  public Row getRow() {
   return RowObject;
  }

  // Setter Methods 
	@JsonProperty("row")
  public void setRow(Row rowObject) {
   this.RowObject = rowObject;
  }
  
}
