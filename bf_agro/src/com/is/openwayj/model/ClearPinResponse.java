package com.is.openwayj.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClearPinResponse {
	  @JsonProperty("message")
	    private String message;

	    @JsonProperty("success")
	    private boolean success;

	    @JsonProperty("message")
		public String getMessage() {
			return message;
		}

	    @JsonProperty("success")
		public boolean isSuccess() {
			return success;
		}

		@JsonProperty("message")
		public void setMessage(String message) {
			this.message = message;
		}

		@JsonProperty("success")
		public void setSuccess(boolean success) {
			this.success = success;
		}
	    
}
