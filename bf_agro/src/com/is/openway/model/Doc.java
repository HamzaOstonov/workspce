package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonPropertyOrder({"TransType", "DocRefSet", "LocalDt", "Description", "Requestor", "Source", "Transaction" })
public class Doc {
	@JsonProperty("TransType")
	 private TransType TransTypeObject;
	@JsonProperty("DocRefSet")
	private DocRefSet DocRefSetObject;
	@JsonProperty("LocalDt")
	 private String LocalDt;
	@JsonProperty("Description")
	 private String Description;
	@JsonProperty("Requestor")
	private Requestor RequestorObject;
	@JsonProperty("Source")
	 private DocSource SourceObject;
	@JsonProperty("Transaction")
	 private Transaction TransactionObject;


	 // Getter Methods 
	@JsonProperty("TransType")
	 public TransType getTransType() {
	  return TransTypeObject;
	 }
	@JsonProperty("DocRefSet")
	 public DocRefSet getDocRefSet() {
	  return DocRefSetObject;
	 }
	@JsonProperty("LocalDt")
	 public String getLocalDt() {
	  return LocalDt;
	 }
	@JsonProperty("Description")
	 public String getDescription() {
	  return Description;
	 }
	@JsonProperty("Requestor")
	 public Requestor getRequestor() {
	  return RequestorObject;
	 }
	@JsonProperty("Source")
	 public DocSource getSource() {
	  return SourceObject;
	 }
	@JsonProperty("Transaction")
	 public Transaction getTransaction() {
	  return TransactionObject;
	 }

	 // Setter Methods 
	 @JsonProperty("TransType")
	 public void setTransType(TransType TransTypeObject) {
	  this.TransTypeObject = TransTypeObject;
	 }
	 @JsonProperty("DocRefSet")
	 public void setDocRefSet(DocRefSet DocRefSetObject) {
	  this.DocRefSetObject = DocRefSetObject;
	 }
	 @JsonProperty("LocalDt")
	 public void setLocalDt(String LocalDt) {
	  this.LocalDt = LocalDt;
	 }
	 @JsonProperty("Description")
	 public void setDescription(String Description) {
	  this.Description = Description;
	 }
	 @JsonProperty("Requestor")
	 public void setRequestor(Requestor RequestorObject) {
	  this.RequestorObject = RequestorObject;
	 }
	 @JsonProperty("Source")
	 public void setSource(DocSource SourceObject) {
	  this.SourceObject = SourceObject;
	 }
	 @JsonProperty("Transaction")
	 public void setTransaction(Transaction TransactionObject) {
	  this.TransactionObject = TransactionObject;
	 }
	}