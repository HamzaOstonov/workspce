package com.is.openwayj.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientContractsResponse {
	  @JsonProperty("message")
	    private String message;

	    @JsonProperty("success")
	    private boolean success;

	    @JsonProperty("body")
	    private List<Contract> body;


	    @JsonProperty("message")
		public String getMessage() {
			return message;
		}

	    @JsonProperty("success")
		public boolean isSuccess() {
			return success;
		}
	    @JsonProperty("body")
		public List<Contract> getBody() {
			return body;
		}

		@JsonProperty("message")
		public void setMessage(String message) {
			this.message = message;
		}

		@JsonProperty("success")
		public void setSuccess(boolean success) {
			this.success = success;
		}
		@JsonProperty("body")
		public void setBody(List<Contract> body) {
			this.body = body;
		}
	    
}
