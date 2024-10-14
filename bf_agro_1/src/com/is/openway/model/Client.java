package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Client {
	@JsonProperty("OrderDprt")
	 private String OrderDprt;
	@JsonProperty("ClientType")
	 private String ClientType;
	@JsonProperty("ClientCategory")
	 private String ClientCategory;
	@JsonProperty("ClientInfo")
	 private ClientInfo ClientInfoObject;
	@JsonProperty("PlasticInfo")
	 private PlasticInfo PlasticInfoObject;
	@JsonProperty("PhoneList")
	 private PhoneList PhoneListObject;
	@JsonProperty("DateOpen")
	 private String DateOpen;
	@JsonProperty("BaseAddress")
	 private BaseAddress BaseAddressObject;


	 // Getter Methods 
	@JsonProperty("OrderDprt")
	 public String getOrderDprt() {
	  return OrderDprt;
	 }
	@JsonProperty("ClientType")
	 public String getClientType() {
	  return ClientType;
	 }
	@JsonProperty("ClientCategory")
	 public String getClientCategory() {
	  return ClientCategory;
	 }
	 @JsonProperty("ClientInfo")
	 public ClientInfo getClientInfo() {
	  return ClientInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
	 public PlasticInfo getPlasticInfo() {
	  return PlasticInfoObject;
	 }
	 @JsonProperty("PhoneList")
	 public PhoneList getPhoneList() {
	  return PhoneListObject;
	 }
	 @JsonProperty("DateOpen")
	 public String getDateOpen() {
	  return DateOpen;
	 }
	 @JsonProperty("BaseAddress")
	 public BaseAddress getBaseAddress() {
	  return BaseAddressObject;
	 }

	 // Setter Methods 
	 @JsonProperty("OrderDprt")
	 public void setOrderDprt(String OrderDprt) {
	  this.OrderDprt = OrderDprt;
	 }
	 @JsonProperty("ClientType")
	 public void setClientType(String ClientType) {
	  this.ClientType = ClientType;
	 }
	 @JsonProperty("ClientCategory")
	 public void setClientCategory(String ClientCategory) {
	  this.ClientCategory = ClientCategory;
	 }
	 @JsonProperty("ClientInfo")
	 public void setClientInfo(ClientInfo ClientInfoObject) {
	  this.ClientInfoObject = ClientInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
	 public void setPlasticInfo(PlasticInfo PlasticInfoObject) {
	  this.PlasticInfoObject = PlasticInfoObject;
	 }
	 @JsonProperty("PhoneList")
	 public void setPhoneList(PhoneList PhoneListObject) {
	  this.PhoneListObject = PhoneListObject;
	 }
	 @JsonProperty("DateOpen")
	 public void setDateOpen(String DateOpen) {
	  this.DateOpen = DateOpen;
	 }
	 @JsonProperty("BaseAddress")
	 public void setBaseAddress(BaseAddress BaseAddressObject) {
	  this.BaseAddressObject = BaseAddressObject;
	 }
	}
