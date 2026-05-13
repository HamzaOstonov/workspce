
package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonRootName(value = "Contract")
public class ContractResp {
	@JsonProperty("OrderDprt")
	 private String OrderDprt;
	@JsonProperty("ClientType")
	 private String ClientType;
	@JsonProperty("ClientCategory")
	 private String ClientCategory;
	 @JsonProperty("ContractIDT")
	 private ContractIDT ContractIDTObject;
	 @JsonProperty("Currency")
	 private String Currency;
	 @JsonProperty("ContractName")
	 private String ContractName;
	 @JsonProperty("CommentText")
	 private String CommentText;
	 @JsonProperty("Product")
	 private ProductContractResp ProductObject;
	 @JsonProperty("ProductionParms")
	 private ProductionParms ProductionParmsObject;
	 @JsonProperty("PlasticInfo")
	 private PlasticInfo PlasticInfoObject;
	 @JsonProperty("DateOpen")
	 private String DateOpen;
	 @JsonProperty("AddContractInfo")
	 private AddContractInfo AddContractInfoObject;
	
		 
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
	 @JsonProperty("ContractIDT")
	 public ContractIDT getContractIDT() {
	  return ContractIDTObject;
	 }
	 @JsonProperty("Currency")
	 	public String getCurrency() {
		return Currency;
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
	 public ProductContractResp getProduct() {
	  return ProductObject;
	 }
	 @JsonProperty("ProductionParms")
	 public ProductionParms getProductionParmsObject() {
		return ProductionParmsObject;
	}
	 @JsonProperty("PlasticInfo")
	 public PlasticInfo getPlasticInfoObject() {
		return PlasticInfoObject;
	}
	 @JsonProperty("DateOpen")
	 public String getDateOpen() {
	  return DateOpen;
	 }
	 @JsonProperty("AddContractInfo")
	 public AddContractInfo getAddContractInfo() {
	  return AddContractInfoObject;
	 }


	
	// Setter Methods 
	@JsonProperty("OrderDprt")
	public void setOrderDprt(String orderDprt) {
		OrderDprt = orderDprt;
	}
	
	@JsonProperty("ClientType")
    public void setClientType(String clientType) {
		ClientType = clientType;
	}
	
	@JsonProperty("ClientCategory")
	public void setClientCategory(String clientCategory) {
		ClientCategory = clientCategory;
	}
	 @JsonProperty("ContractIDT")
	 public void setContractIDT(ContractIDT ContractIDTObject) {
	  this.ContractIDTObject = ContractIDTObject;
	 }
	 @JsonProperty("Currency")
	 public void setCurrency(String currency) {
		Currency = currency;
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
	 public void setProduct(ProductContractResp ProductObject) {
	  this.ProductObject = ProductObject;
	 }
	 @JsonProperty("ProductionParms")
	 public void setProductionParmsObject(ProductionParms productionParmsObject) {
		ProductionParmsObject = productionParmsObject;
	}
	 @JsonProperty("PlasticInfo")
	 public void setPlasticInfoObject(PlasticInfo plasticInfoObject) {
		PlasticInfoObject = plasticInfoObject;
	}
	 @JsonProperty("DateOpen")
	 public void setDateOpen(String DateOpen) {
	  this.DateOpen = DateOpen;
	 }
	 @JsonProperty("AddContractInfo")
	 public void setAddContractInfo(AddContractInfo AddContractInfoObject) {
	  this.AddContractInfoObject = AddContractInfoObject;
	 }
		

}

