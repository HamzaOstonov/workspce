package com.is.client_spec;

import lombok.Getter;
import lombok.Setter;

public class SpecClt {
	@Getter @Setter private String branch;
	@Getter @Setter private String id_special;
	@Getter @Setter private String id_client;
	@Getter @Setter private String value;
	@Getter @Setter private String prim;
	@Getter @Setter private String name;
	@Getter @Setter private String code_type;
	@Getter @Setter private String type_name;
	
	public SpecClt() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public SpecClt(String branch, String id_special, String id_client,
			String value, String prim, String name, String code_type,
			String type_name) {
		super();
		this.branch = branch;
		this.id_special = id_special;
		this.id_client = id_client;
		this.value = value;
		this.prim = prim;
		this.name = name;
		this.code_type = code_type;
		this.type_name = type_name;
	}

}
