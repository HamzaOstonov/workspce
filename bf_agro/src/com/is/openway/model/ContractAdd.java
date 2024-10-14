package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonPropertyOrder({"ContractIDT", "ContractName", "CommentText", "Product", "ProductionParms", "PlasticInfo", "DateOpen", "AddContractInfo" })
@JsonRootName(value = "Contract")
public class ContractAdd {
	
	 @JsonProperty("ContractIDT")
	 private ContractIDT ContractIDTObject;
	 @JsonProperty("ContractName")
	 private String ContractName;
	 @JsonProperty("CommentText")
	 private String CommentText;
	 @JsonProperty("Product")
	 private ProductContract ProductObject;
	 @JsonProperty("ProductionParms")
	 private ProductionParms ProductionParmsObject;
	 @JsonProperty("PlasticInfo")
	 private PlasticInfo plasticInfo;
	 @JsonProperty("DateOpen")
	 private String DateOpen;
	 @JsonProperty("AddContractInfo")
	 private AddContractInfo AddContractInfoObject;

	 
	 // Getter Methods 
	 @JsonProperty("ContractIDT")
	 public ContractIDT getContractIDT() {
	  return ContractIDTObject;
	 }
	 @JsonProperty("ContractName")
	 public String getContractName() {
	  return ContractName;
	 }
	 @JsonProperty("CommentText")
	 public String getCommentText() {
	  return CommentText;
	 }
	 @JsonProperty("Product")
	 public ProductContract getProduct() {
	  return ProductObject;
	 }
	 @JsonProperty("DateOpen")
	 public String getDateOpen() {
	  return DateOpen;
	 }
	 @JsonProperty("AddContractInfo")
	 public AddContractInfo getAddContractInfo() {
	  return AddContractInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
     public PlasticInfo getPlasticInfo() {
			return plasticInfo;
	 }
	 @JsonProperty("ProductionParms")
	 public ProductionParms getProductionParmsObject() {
		return ProductionParmsObject;
	 }

	 // Setter Methods 
	 @JsonProperty("ContractIDT")
	 public void setContractIDT(ContractIDT ContractIDTObject) {
	  this.ContractIDTObject = ContractIDTObject;
	 }
	 @JsonProperty("ContractName")
	 public void setContractName(String ContractName) {
	  this.ContractName = ContractName;
	 }
	 @JsonProperty("CommentText")
	 public void setCommentText(String CommentText) {
	  this.CommentText = CommentText;
	 }
	 @JsonProperty("Product")
	 public void setProduct(ProductContract ProductObject) {
	  this.ProductObject = ProductObject;
	 }
	 @JsonProperty("DateOpen")
	 public void setDateOpen(String DateOpen) {
	  this.DateOpen = DateOpen;
	 }
	 @JsonProperty("AddContractInfo")
	 public void setAddContractInfo(AddContractInfo AddContractInfoObject) {
	  this.AddContractInfoObject = AddContractInfoObject;
	 }
	 @JsonProperty("PlasticInfo")
	public void setPlasticInfo(PlasticInfo plasticInfo) {
		this.plasticInfo = plasticInfo;
	}
	 @JsonProperty("ProductionParms")
	public void setProductionParmsObject(ProductionParms productionParmsObject) {
		ProductionParmsObject = productionParmsObject;
	}

}
