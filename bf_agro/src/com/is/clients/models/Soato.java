package com.is.clients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Soato {
	@Getter @Setter private String kod_soat;
	@Getter @Setter private String kod_gni;
	@Getter @Setter private String region;
	@Getter @Setter private String distr;
	
	public Soato() {
	}
}
