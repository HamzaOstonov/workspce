package com.is.sign;

public class Res {
	private int code;
	private String name;
	private Object obj;

	public Res() {
		super();
	}

	public Res(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Res(int code, String name, Object obj) {
		super();
		this.code = code;
		this.name = name;
		this.obj = obj;
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
