package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSMSRequest {

	   @JsonProperty("request_id")
	    private String requestId;

	    @JsonProperty("object_for")
	    private ObjectForUpdateSMS objectFor;

	    @JsonProperty("request_id")
		public String getRequestId() {
			return requestId;
		}

	    @JsonProperty("object_for")
		public ObjectForUpdateSMS getObjectFor() {
			return objectFor;
		}

		@JsonProperty("request_id")
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		@JsonProperty("object_for")
		public void setObjectFor(ObjectForUpdateSMS objectFor) {
			this.objectFor = objectFor;
		}

	    
    
}
