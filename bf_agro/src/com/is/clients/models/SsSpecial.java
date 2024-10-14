package com.is.clients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SsSpecial {
	@Getter @Setter private int id;
	@Getter @Setter private String name;
	@Getter @Setter private String mask;
	@Getter @Setter private String limit;
	@Getter @Setter private int manual;
	
	public SsSpecial() {
	}
	
}
