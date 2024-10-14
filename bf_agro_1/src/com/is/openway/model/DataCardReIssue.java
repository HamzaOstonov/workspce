package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "Data")
public class DataCardReIssue {
	@JsonProperty("ProduceCard")
	private ProduceCard produceCardObject;


	 // Getter Methods 
	@JsonProperty("ProduceCard")
	 public ProduceCard getProduceCard() {
	  return produceCardObject;
	 }

	 // Setter Methods 
	@JsonProperty("ProduceCard")
	 public void setProduceCard(ProduceCard ProduceCardObject) {
	  this.produceCardObject = ProduceCardObject;
	 }

}
