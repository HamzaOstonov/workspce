package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientCategory {
	  @JsonProperty("client_category")
      private String clientCategory;

	  @JsonProperty("client_category")
	public String getClientCategory() {
		return clientCategory;
	}

	  @JsonProperty("client_category")
	public void setClientCategory(String clientCategory) {
		this.clientCategory = clientCategory;
	}
	  
	  
	  

}
