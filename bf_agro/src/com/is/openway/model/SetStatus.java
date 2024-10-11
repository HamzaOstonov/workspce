package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "SetStatus")
public class SetStatus {
	@JsonProperty("StatusCode")
	 private String StatusCode;
	@JsonProperty("StatusComment")
	 private String StatusComment;


	 // Getter Methods 
	 @JsonProperty("StatusCode")
	 public String getStatusCode() {
	  return StatusCode;
	 }
	 @JsonProperty("StatusComment")
	 public String getStatusComment() {
	  return StatusComment;
	 }

	 // Setter Methods 
	 @JsonProperty("StatusCode")
	 public void setStatusCode(String StatusCode) {
	  this.StatusCode = StatusCode;
	 }
	 @JsonProperty("StatusComment")
	 public void setStatusComment(String StatusComment) {
	  this.StatusComment = StatusComment;
	 }

}
