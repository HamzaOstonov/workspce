package com.is.clientcrm.res_gsp;

public class Request_Gsp {
	
 private String lang;
 private String pas_ser;
 private String pas_num;
 private String pinfl;
 private String inn;
 private String date_birth;
 private String id_client;
 private String id_request;
 
 public Request_Gsp(){
	 
 }

public Request_Gsp(String lang, String pas_ser, String pas_num, String pinfl, String inn,String date_birth, String id_client,
		String id_request) {
	super();
	this.lang = lang;
	this.pas_ser = pas_ser;
	this.pas_num = pas_num;
	this.pinfl = pinfl;
	this.inn = inn;
	this.date_birth = date_birth;
	this.id_client = id_client;
	this.id_request = id_request;
}

public String getLang() {
	return lang;
}

public void setLang(String lang) {
	this.lang = lang;
}

public String getPas_ser() {
	return pas_ser;
}

public void setPas_ser(String pas_ser) {
	this.pas_ser = pas_ser;
}

public String getPas_num() {
	return pas_num;
}

public void setPas_num(String pas_num) {
	this.pas_num = pas_num;
}

public String getPinfl() {
	return pinfl;
}

public void setPinfl(String pinfl) {
	this.pinfl = pinfl;
}

public String getInn() {
	return inn;
}

public void setInn(String inn) {
	this.inn = inn;
}

public String getDate_birth() {
	return date_birth;
}

public void setDate_birth(String date_birth) {
	this.date_birth = date_birth;
}

public String getId_client() {
	return id_client;
}

public void setId_client(String id_client) {
	this.id_client = id_client;
}

public String getId_request() {
	return id_request;
}

public void setId_request(String id_request) {
	this.id_request = id_request;
}

@Override
public String toString() {
	return "Request_Gsp [lang=" + lang + ", pas_ser=" + pas_ser + ", pas_num=" + pas_num + ", pinfl=" + pinfl + ", inn="
			+ inn + ", id_client=" + id_client + ", id_request=" + id_request + "]";
}
 
}
