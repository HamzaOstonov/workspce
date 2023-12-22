package com.is.client_personmap.model;

public class PersonMapFilter extends PersonMap {
	private String personMapIds;

	public PersonMapFilter() {
	}
	public PersonMapFilter(String personMapIds) {
		this.personMapIds = personMapIds;
	}
	public String getPersonMapIds() {
		return personMapIds;
	}
}
