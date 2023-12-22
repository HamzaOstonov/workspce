package com.is.korona_pay;

import java.util.HashMap;
import java.util.Map;

public class PrintOrderReq {

	private String action;
	private String uIN;
	private Integer operation;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public PrintOrderReq() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAction() {
		return action;
		}

		public void setAction(String action) {
		this.action = action;
		}

		public String getUIN() {
		return uIN;
		}

		public void setUIN(String uIN) {
		this.uIN = uIN;
		}

		public Integer getOperation() {
		return operation;
		}

		public void setOperation(Integer operation) {
		this.operation = operation;
		}

		public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		}

}
