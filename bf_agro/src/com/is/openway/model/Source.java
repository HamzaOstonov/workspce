package com.is.openway.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Source {
	@JacksonXmlProperty(isAttribute = true)
	 private String app;

	 // Getter Methods 

	 public String getApp() {
	  return app;
	 }

	 // Setter Methods 

	 public void setApp(String app) {
	  this.app = app;
	 }
	 
	 public Source(){
		 this.app="UZPSB";	 
	 }
	 
	}