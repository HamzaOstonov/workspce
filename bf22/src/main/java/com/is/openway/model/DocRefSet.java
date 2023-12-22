package com.is.openway.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class DocRefSet {
	@JsonProperty("Parm")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList < Parm > ParmObject ;
	//private Parm ParmObject;


	 // Getter Methods 
	@JsonProperty("Parm")
	 public ArrayList < Parm > getParm() {
	  return ParmObject;
	 }

	 // Setter Methods 
	@JsonProperty("Parm")
	 public void setParm(ArrayList < Parm > ParmObject) {
	  this.ParmObject = ParmObject;
	 }
	}