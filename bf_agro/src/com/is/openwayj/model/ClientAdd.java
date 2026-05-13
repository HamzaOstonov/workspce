package com.is.openwayj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonPropertyOrder({"ClientType", "ClientInfo", "PlasticInfo", "PhoneList", "DateOpen", "BaseAddress" })
@JsonRootName(value = "Client")
public class ClientAdd {
	@JsonProperty("ClientType")
	 private String ClientType;
	@JsonProperty("ClientInfo")
	 private ClientInfo ClientInfoObject;
	@JsonProperty("PlasticInfo")
	 private PlasticInfo_old PlasticInfoObject;
	@JsonProperty("PhoneList")
	 private PhoneList PhoneListObject;
	@JsonProperty("DateOpen")
	 private String DateOpen;
	@JsonProperty("BaseAddress")
	 private BaseAddress_old BaseAddressObject;


	 // Getter Methods 
	@JsonProperty("ClientType")
	 public String getClientType() {
	  return ClientType;
	 }
	 @JsonProperty("ClientInfo")
	 public ClientInfo getClientInfo() {
	  return ClientInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
	 public PlasticInfo_old getPlasticInfo() {
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
	 public BaseAddress_old getBaseAddress() {
	  return BaseAddressObject;
	 }

	 // Setter Methods 
	 @JsonProperty("ClientType")
	 public void setClientType(String ClientType) {
	  this.ClientType = ClientType;
	 }
	 @JsonProperty("ClientInfo")
	 public void setClientInfo(ClientInfo ClientInfoObject) {
	  this.ClientInfoObject = ClientInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
	 public void setPlasticInfo(PlasticInfo_old PlasticInfoObject) {
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
	 public void setBaseAddress(BaseAddress_old BaseAddressObject) {
	  this.BaseAddressObject = BaseAddressObject;
	 }

}
