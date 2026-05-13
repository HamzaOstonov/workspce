package com.is.openwayj.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceCardRequest {

	  @JsonProperty("card_info")
	  private List<CardInfoForBalance> card_info;
	  @JsonProperty("request_id")
		private String requestId;
	  
	  
	  
	  @JsonProperty("request_id")
		public String getRequestId() {
		return requestId;
		}

		@JsonProperty("request_id")
		public void setRequestId(String requestId) {
		this.requestId = requestId;
		}

		@JsonProperty("card_info")
		public void setCard_info(List<CardInfoForBalance> card_info) {
			this.card_info = card_info;
		}
		@JsonProperty("card_info")
		public List<CardInfoForBalance> getCard_info() {
			return card_info;
		}
		
}
