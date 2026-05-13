package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonRootName(value = "Application")
public class ApplicationUpdContract {
	@JsonProperty("RegNumber")
	 private String RegNumber;
	@JsonProperty("Institution")
	 private String Institution;
	@JsonProperty("OrderDprt")
	 private String OrderDprt;
	@JsonProperty("ObjectType")
	 private String ObjectType;
	@JsonProperty("ActionType")
	 private String ActionType;
	@JsonProperty("ResultDtls")
	 private ResultDtls ResultDtlsObject;
	@JsonProperty("ObjectFor")
	 private ObjectForContract ObjectForObject;
 	@JsonProperty("Data")
	 private DataContract DataObject;


	 // Getter Methods 
	@JsonProperty("RegNumber")
	 public String getRegNumber() {
	  return RegNumber;
	 }
	@JsonProperty("Institution")
	 public String getInstitution() {
	  return Institution;
	 }
	@JsonProperty("OrderDprt")
	 public String getOrderDprt() {
	  return OrderDprt;
	 }
	@JsonProperty("ObjectType")
	 public String getObjectType() {
	  return ObjectType;
	 }
	@JsonProperty("ActionType")
	 public String getActionType() {
	  return ActionType;
	 }
	@JsonProperty("ObjectFor")
	 public ObjectForContract getObjectFor() {
	  return ObjectForObject;
	 }

	@JsonProperty("ResultDtls")
	 public ResultDtls getResultDtls() {
	  return ResultDtlsObject;
	 }
	@JsonProperty("Data")
	 public DataContract getData() {
	  return DataObject;
	 }

	 // Setter Methods 
	 @JsonProperty("RegNumber")
	 public void setRegNumber(String RegNumber) {
	  this.RegNumber = RegNumber;
	 }
	 @JsonProperty("Institution")
	 public void setInstitution(String Institution) {
	  this.Institution = Institution;
	 }
		@JsonProperty("OrderDprt")
	 public void setOrderDprt(String OrderDprt) {
	  this.OrderDprt = OrderDprt;
	 }
		@JsonProperty("ObjectType")
	 public void setObjectType(String ObjectType) {
	  this.ObjectType = ObjectType;
	 }
		@JsonProperty("ActionType")
	 public void setActionType(String ActionType) {
	  this.ActionType = ActionType;
	 }
		@JsonProperty("ObjectFor")
	 public void setObjectFor(ObjectForContract ObjectForObject) {
	  this.ObjectForObject = ObjectForObject;
	 }
	 	@JsonProperty("ResultDtls")
	 public void setResultDtls(ResultDtls ResultDtlsObject) {
	  this.ResultDtlsObject = ResultDtlsObject;
	 }
		@JsonProperty("Data")
	 public void setData(DataContract DataObject) {
	  this.DataObject = DataObject;
	 }
	 
	 public ApplicationUpdContract() {
		    this.Institution="09058";
		    this.OrderDprt="01142";
		    this.ObjectType="Contract";
		    this.ActionType="Update";
	 }
}
