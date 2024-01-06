package com.is.clients.models;

public class AccountsResponse {

	private Result ResultObject;
	private Header HeaderObject;
	private AccountsResp ResponseObject;


	 // Getter Methods 

	 public Result getResult() {
	  return ResultObject;
	 }

	 public Header getHeader() {
	  return HeaderObject;
	 }

	 public AccountsResp getResponse() {
	  return ResponseObject;
	 }

	 // Setter Methods 

	 public void setResult(Result resultObject) {
	  this.ResultObject = resultObject;
	 }

	 public void setHeader(Header headerObject) {
	  this.HeaderObject = headerObject;
	 }

	 public void setResponse(AccountsResp responseObject) {
	  this.ResponseObject = responseObject;
	 }
}
