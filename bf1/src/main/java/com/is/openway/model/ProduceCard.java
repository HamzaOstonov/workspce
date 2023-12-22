package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "ProduceCard")
public class ProduceCard {
	@JsonProperty("ProductionEvent")
	 private String productionEvent;

	 // Getter Methods 
	 @JsonProperty("ProductionEvent")
	 public String getProductionEvent() {
	  return productionEvent;
	 }
	
	 // Setter Methods 
	 @JsonProperty("ProductionEvent")
	 public void setProductionEvent(String ProductionEvent) {
	  this.productionEvent = ProductionEvent;
	 }	

}
