package com.is.nibbd.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class NibbdReaction {
	@Getter @Setter private long id;
	@Getter @Setter private String id_client;
	@Getter @Setter private String branch;
	@Getter @Setter private int state;
	@Getter @Setter private String clientIds;
	@Getter @Setter private int responsible_emp;
	
	public NibbdReaction() {
	}
	public NibbdReaction(String id_client, String branch, int state) {
		this.id_client = id_client;
		this.branch = branch;
		this.state = state;
	}
	
	public NibbdReaction(long id, String id_client, String branch, int state,int responsible_emp) {
		this.id = id;
		this.id_client = id_client;
		this.branch = branch;
		this.state = state;
		this.responsible_emp = responsible_emp;
	}
	
	public NibbdReaction(String clientIds) {
		this.clientIds = clientIds;
	}
}
