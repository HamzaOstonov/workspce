package com.is.providers;
import java.util.Date;



public class Res {
 private int code;
 private String name;
 private Date timeStamp;
 
 public Res() {
	 super();
 }

public Res(int code, String name, Date timeStamp) {
	super();
	this.code = code;
	this.name = name;
	this.timeStamp = timeStamp;
}



public Res(int code, String name) {
	super();
	this.code = code;
	this.name = name;
}

public int getCode() {
	return code;
}

public void setCode(int code) {
	this.code = code;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getTimeStamp() {
	return timeStamp;
}

public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
}
 
}
