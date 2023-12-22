package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "Data")
public class DataAddress {
	@JsonProperty("PermanentRegistration")
	private PermanentRegistration PermanentRegistrationObject;
	@JsonProperty("TemproaryRegistrations")	
    private TemproaryRegistration[] temproaryRegistrationsObj;


	 // Getter Methods 
	@JsonProperty("PermanentRegistration")
	 public PermanentRegistration getPermanentRegistration() {
	  return PermanentRegistrationObject;
	 }
	//@JsonProperty("TemproaryRegistrations")
	 //public TemproaryRegistrations getTemproaryRegistrations() {
	 // return temproaryRegistrationsObj;
	 //}

	 // Setter Methods 
		@JsonProperty("PermanentRegistration")
	 public void setPermanentRegistration(PermanentRegistration PermanentRegistrationObject) {
	  this.PermanentRegistrationObject = PermanentRegistrationObject;
	 }
	//	@JsonProperty("TemproaryRegistrations")
	// public void setTemproaryRegistrations(TemproaryRegistrations temproaryRegistrations) {
	//  this.temproaryRegistrationsObj = temproaryRegistrations;
	// }
		@JsonProperty("TemproaryRegistrations")	
		public void setTemproaryRegistrationsObj(
				TemproaryRegistration[] temproaryRegistrationsObj) {
			this.temproaryRegistrationsObj = temproaryRegistrationsObj;
		}
		@JsonProperty("TemproaryRegistrations")	
		public TemproaryRegistration[] getTemproaryRegistrationsObj() {
			return temproaryRegistrationsObj;
		}
}
