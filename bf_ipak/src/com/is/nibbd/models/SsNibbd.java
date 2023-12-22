package com.is.nibbd.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SsNibbd {
	@Getter @Setter private int queryNo;
	@Getter @Setter private int field;
	@Getter @Setter private String id;
	@Getter @Setter private String value;
	@Getter @Setter private String act;
	
	public SsNibbd() {
		// TODO Auto-generated constructor stub
	}
	
}
