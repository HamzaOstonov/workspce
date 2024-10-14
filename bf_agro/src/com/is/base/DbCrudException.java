package com.is.base;

public class DbCrudException extends Exception{
	private static final long serialVersionUID = 1L;

	public DbCrudException() {
		super();
	}
	
	public DbCrudException(String msg) {
		super(msg);
	}
	
	public DbCrudException(String msg, Throwable thr) {
		super(msg,thr);
	}
	
	public DbCrudException(Throwable thr) {
		super(thr);
	}
}
