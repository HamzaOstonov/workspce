package com.is.customer_.service.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "DataByPinppRequest")
public class DataByPinppRequestPhoto {
	 private String pinpp;
	 private String doc_give_date;
	 private int langId;
	 private String is_consent_pers_data;


	 // Getter Methods 

	 public String getPinpp() {
	  return pinpp;
	 }

	 public String getDoc_give_date() {
	  return doc_give_date;
	 }

	 public int getLangId() {
	  return langId;
	 }

	 public String getIs_consent_pers_data() {
	  return is_consent_pers_data;
	 }

	 // Setter Methods 

	 public void setPinpp(String pinpp) {
	  this.pinpp = pinpp;
	 }

	 public void setDoc_give_date(String doc_give_date) {
	  this.doc_give_date = doc_give_date;
	 }

	 public void setLangId(int langId) {
	  this.langId = langId;
	 }

	 public void setIs_consent_pers_data(String is_consent_pers_data) {
	  this.is_consent_pers_data = is_consent_pers_data;
	 }
}
