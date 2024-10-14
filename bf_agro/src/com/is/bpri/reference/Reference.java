package com.is.bpri.reference;

public class Reference {
	
	private Long id;
	private String name;
	private String uname;
	private String module_id;
	
	public Reference() {
		
	}

	public Reference(Long id, String name, String uname,String module_id) {
		super();
		this.id = id;
		this.name = name;
		this.uname = uname;
		this.module_id = module_id;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	
}
