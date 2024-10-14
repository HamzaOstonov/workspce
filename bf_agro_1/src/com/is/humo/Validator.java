package com.is.humo;

public abstract class Validator<T> {
	protected String alias;
	protected String message;
	
	public String getMessage() {
		return message;
	}
	
	public abstract boolean isValid(T founder);
	
}
