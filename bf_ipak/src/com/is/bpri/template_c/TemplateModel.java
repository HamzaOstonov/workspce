package com.is.bpri.template_c;

public class TemplateModel {
	
	private Long id;
	private String name;
	private String file_name;
	
	public TemplateModel() {
		
	}
	
	public TemplateModel(Long id, String name, String file_name) {
		super();
		this.id = id;
		this.name = name;
		this.file_name = file_name;
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

	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}	
}

