package com.is.korona_pay;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrintOrdersRequest {

	@JsonProperty("Action")
	public String action;
	
	@JsonProperty("UIN")
	public String uIN;
	
	@JsonProperty("Operation")
	public int operation;
	
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	public PrintOrdersRequest () {
		
	}

	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getuIN() {
		return uIN;
	}

	
	public void setuIN(String uIN) {
		this.uIN = uIN;
	}

	
	public int getOperation() {
		return operation;
	}

	
	public void setOperation(int operation) {
		this.operation = operation;
	}
	
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
		}
	

		public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		}
	
}
