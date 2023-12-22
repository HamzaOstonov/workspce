package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizAddressResponse {
	@JsonProperty("Data")
	private DataAddress dataObject;
	@JsonProperty("AnswereId")
	 private int answereId;
	@JsonProperty("AnswereMessage")	
	 private String answereMessage;
	 private String answereComment;


	 // Getter Methods 
	 @JsonProperty("Data")
	 public DataAddress getDataObject() {
	  return dataObject;
	 }
	 @JsonProperty("AnswereId")
	 public int getAnswereId() {
	  return answereId;
	 }
	 @JsonProperty("AnswereMessage")
	 public String getAnswereMessage() {
	  return answereMessage;
	 }
	 @JsonProperty("AnswereComment")
	 public String getAnswereComment() {
	  return answereComment;
	 }

	 // Setter Methods 
	 @JsonProperty("Data")
	 public void setDataObject(DataAddress dataObject) {
	  this.dataObject = dataObject;
	 }
	 @JsonProperty("AnswereId")
	 public void setAnswereId(int answereId) {
	  this.answereId = answereId;
	 }
	 @JsonProperty("AnswereMessage")
	 public void setAnswereMessage(String answereMessage) {
	  this.answereMessage = answereMessage;
	 }
	 @JsonProperty("AnswereComment")
	 public void setAnswereComment(String answereComment) {
	  this.answereComment = answereComment;
	 }
}
