package com.is.clients.models;

public class LockedAccountsResponse {

	private Result ResultObject;
	private Header HeaderObject;
	private LockedAccountsResp ResponseObject;


	 // Getter Methods 

	 public Result getResult() {
	  return ResultObject;
	 }

	 public Header getHeader() {
	  return HeaderObject;
	 }

	 public LockedAccountsResp getResponse() {
	  return ResponseObject;
	 }

	 // Setter Methods 

	 public void setResult(Result resultObject) {
	  this.ResultObject = resultObject;
	 }

	 public void setHeader(Header headerObject) {
	  this.HeaderObject = headerObject;
	 }

	 public void setResponse(LockedAccountsResp responseObject) {
	  this.ResponseObject = responseObject;
	 }
}
