package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Product")
public class ProductContract {
	@JsonProperty("ProductCode1")
	private String ProductCode1;


	 // Getter Methods 
	@JsonProperty("ProductCode1")
	 public String getProductCode1() {
	  return ProductCode1;
	 }

	 // Setter Methods 
	@JsonProperty("ProductCode1")
	 public void setProductCode1(String ProductCode1) {
	  this.ProductCode1 = ProductCode1;
	 }
}
