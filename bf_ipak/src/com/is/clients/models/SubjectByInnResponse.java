package com.is.clients.models;

public class SubjectByInnResponse {

	private Result ResultObject;
	private Header HeaderObject;
	private Response ResponseObject;


	 // Getter Methods 

	 public Result getResult() {
	  return ResultObject;
	 }

	 public Header getHeader() {
	  return HeaderObject;
	 }

	 public Response getResponse() {
	  return ResponseObject;
	 }

	 // Setter Methods 

	 public void setResult(Result resultObject) {
	  this.ResultObject = resultObject;
	 }

	 public void setHeader(Header headerObject) {
	  this.HeaderObject = headerObject;
	 }

	 public void setResponse(Response responseObject) {
	  this.ResponseObject = responseObject;
	 }
}
