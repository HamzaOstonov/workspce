package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Status {
	@JsonProperty("RespClass")
	 private String RespClass;
	@JsonProperty("RespCode")
	 private String RespCode;
	@JsonProperty("RespText")
	 private String RespText;
	@JsonProperty("PostingStatus")
	 private String PostingStatus;

	 // Getter Methods 

	 public String getRespClass() {
	  return RespClass;
	 }

	 public String getRespCode() {
	  return RespCode;
	 }

	 public String getRespText() {
	  return RespText;
	 }
	 @JsonProperty("PostingStatus")
		public String getPostingStatus() {
			return PostingStatus;
		}
	 // Setter Methods 

	 public void setRespClass(String RespClass) {
	  this.RespClass = RespClass;
	 }

	 public void setRespCode(String RespCode) {
	  this.RespCode = RespCode;
	 }

	 public void setRespText(String RespText) {
	  this.RespText = RespText;
	 }
	 @JsonProperty("PostingStatus")
	public void setPostingStatus(String postingStatus) {
		PostingStatus = postingStatus;
	}
	
	}