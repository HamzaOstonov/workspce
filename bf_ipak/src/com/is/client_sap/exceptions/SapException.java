package com.is.client_sap.exceptions;

@SuppressWarnings("serial")
public class SapException extends Exception {
	public SapException() {
		super();
	}
	public SapException(String message) {
		super(message);
	}
	public SapException(Throwable thr) {
		super(thr);
	}
	public SapException(String message, Throwable thr) {
		super(message,thr);
	}
}
