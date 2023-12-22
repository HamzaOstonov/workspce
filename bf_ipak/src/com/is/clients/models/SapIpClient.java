package com.is.clients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SapIpClient {
	
	@Getter @Setter private String id_client;
	@Getter @Setter private String branch;
	@Getter @Setter private String id_ip_sap;
	
	public SapIpClient() {
		// TODO Auto-generated constructor stub
	}
	
}
