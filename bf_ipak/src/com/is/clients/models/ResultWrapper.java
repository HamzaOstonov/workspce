package com.is.clients.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ResultWrapper {
	@Getter @Setter private boolean addToList;
//	@Getter @Setter private boolean hasDuplicates;
	@Getter @Setter private boolean existsInTable;
	@Getter @Setter private String id;
	@Getter @Setter private String branch;
	
	public ResultWrapper() {
		// TODO Auto-generated constructor stub
	}
	
}
