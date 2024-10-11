package com.is.clients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Action {
	@Getter @Setter private int dealId;
	@Getter @Setter private int id;
	@Getter @Setter private String name;
	@Getter @Setter private int manual;
	
	public Action() {
	}
}
