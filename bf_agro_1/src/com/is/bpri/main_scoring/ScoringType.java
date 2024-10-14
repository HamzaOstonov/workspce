package com.is.bpri.main_scoring;

public class ScoringType {
	
	private Long id;
	private String name;
	private String path;
	private String class_name;
	private String method;

	public ScoringType() {
	
	}

	public ScoringType(Long id, String name, String path, String class_name,
			String method) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.class_name = class_name;
		this.method = method;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
