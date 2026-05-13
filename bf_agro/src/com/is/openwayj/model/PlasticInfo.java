package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlasticInfo {

	 @JsonProperty("first_name")
	    private String firstName;

	    @JsonProperty("last_name")
	    private String lastName;

	    
	    @JsonProperty("first_name")
		public String getFirstName() {
			return firstName;
		}
	    @JsonProperty("last_name")
		public String getLastName() {
			return lastName;
		}

		@JsonProperty("first_name")
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		@JsonProperty("last_name")
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	    
	    
	    
}
