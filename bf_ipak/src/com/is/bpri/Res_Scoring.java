package com.is.bpri;

public class Res_Scoring {
 private Double code;
 private String name;
 
 public Res_Scoring() {
	 super();
 }
 
public Res_Scoring(Double code, String name) {
	super();
	this.code = code;
	this.name = name;
}

public Double getCode() {
	return code;
}

public void setCode(Double code) {
	this.code = code;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
	

}
