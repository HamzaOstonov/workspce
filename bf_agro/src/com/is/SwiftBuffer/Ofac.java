package com.is.SwiftBuffer;

public class Ofac {
private String programlist;
private String full_name;
private String perc;
private String sdntype;



public Ofac(String programlist, String full_name, String perc, String sdntype) {
	super();
	this.programlist = programlist;
	this.full_name = full_name;
	this.perc = perc;
	this.sdntype = sdntype;
}



public Ofac() {
	super();
}



public String getProgramlist() {
	return programlist;
}



public void setProgramlist(String programlist) {
	this.programlist = programlist;
}



public String getFull_name() {
	return full_name;
}



public void setFull_name(String full_name) {
	this.full_name = full_name;
}



public String getPerc() {
	return perc;
}



public void setPerc(String perc) {
	this.perc = perc;
}



public String getSdntype() {
	return sdntype;
}



public void setSdntype(String sdntype) {
	this.sdntype = sdntype;
}





}
