package com.is.nibbd.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Protocol {
	@Getter @Setter private String branch;
	@Getter @Setter private String protocol;
	@Getter @Setter private Date date_oper;
	@Getter @Setter private Date date_sys;
	@Getter @Setter private String name_file;
	
	public Protocol() {
	}

	public Protocol(String branch, String protocol, Date date_oper) {
		super();
		this.branch = branch;
		this.protocol = protocol;
		this.date_oper = date_oper;
	}
	
}
