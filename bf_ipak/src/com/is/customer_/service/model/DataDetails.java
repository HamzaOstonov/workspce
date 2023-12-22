package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Data")
public class DataDetails {
	@JsonProperty("personaldata")
  private Personaldata PersonaldataObject;


  // Getter Methods 
	@JsonProperty("personaldata")
  public Personaldata getPersonaldata() {
   return PersonaldataObject;
  }

  // Setter Methods 
	@JsonProperty("personaldata")
  public void setPersonaldata(Personaldata personaldataObject) {
   this.PersonaldataObject = personaldataObject;
  }
  
}
