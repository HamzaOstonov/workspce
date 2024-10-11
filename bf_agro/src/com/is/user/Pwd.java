package com.is.user;

public class Pwd {
 private String digest; 
 private String salt;
public Pwd() {
	super();
}
public Pwd(String digest, String salt) {
	super();
	this.digest = digest;
	this.salt = salt;
}
public String getDigest() {
	return digest;
}
public void setDigest(String digest) {
	this.digest = digest;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
 
}
