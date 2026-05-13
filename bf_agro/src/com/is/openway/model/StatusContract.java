package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Status")
public class StatusContract {
	@JsonProperty("StatusClass")
	 private String StatusClass;
	@JsonProperty("StatusCode")
	 private String StatusCode;
	@JsonProperty("StatusDetails")
	 private String StatusDetails;
	@JsonProperty("ProductionStatus")
	 private String ProductionStatus;


	 // Getter Methods 
	@JsonProperty("StatusClass")
	 public String getStatusClass() {
	  return StatusClass;
	 }
	@JsonProperty("StatusCode")
	 public String getStatusCode() {
	  return StatusCode;
	 }
	@JsonProperty("StatusDetails")
	 public String getStatusDetails() {
	  return StatusDetails;
	 }
	@JsonProperty("ProductionStatus")
	 public String getProductionStatus() {
	  return ProductionStatus;
	 }

	 // Setter Methods 
	 @JsonProperty("StatusClass")
	 public void setStatusClass(String StatusClass) {
	  this.StatusClass = StatusClass;
	 }
	 @JsonProperty("StatusCode")
	 public void setStatusCode(String StatusCode) {
	  this.StatusCode = StatusCode;
	 }
	 @JsonProperty("StatusDetails")
	 public void setStatusDetails(String StatusDetails) {
	  this.StatusDetails = StatusDetails;
	 }
	 @JsonProperty("ProductionStatus")
	 public void setProductionStatus(String ProductionStatus) {
	  this.ProductionStatus = ProductionStatus;
	 }
	}
