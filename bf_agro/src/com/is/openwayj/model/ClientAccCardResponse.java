package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAccCardResponse {
	  @JsonProperty("message")
	    private String message;

	    @JsonProperty("success")
	    private boolean success;

	    @JsonProperty("body")
	    private ClientAccCardBody body;

	    @JsonProperty("message")
		public String getMessage() {
			return message;
		}

	    @JsonProperty("success")
		public boolean isSuccess() {
			return success;
		}
	    @JsonProperty("body")
		public ClientAccCardBody getBody() {
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
		public void setBody(ClientAccCardBody body) {
			this.body = body;
		}
	    
}
