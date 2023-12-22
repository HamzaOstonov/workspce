package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FizDocsResponse {

	@JsonProperty("result")
	private int result;
	@JsonProperty("data")
	private FizDocsData dataObject;
	@JsonProperty("comments")
	private String comments;
	
	@JsonProperty("result")
	public int getResult() {
		return result;
	}
	@JsonProperty("data")
	public FizDocsData getData() {
		return dataObject;
	}
	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}
	
	@JsonProperty("result")
	public void setResult(int result) {
		this.result = result;
	}
	@JsonProperty("data")
	public void setData(FizDocsData dataObject) {
		this.dataObject = dataObject;
	}
	@JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
