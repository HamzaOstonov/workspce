package com.is.tieto_capital.refill.services;

import com.is.globalTieto.tietoModels.WSConnectionInfo;

import com.is.globalTieto.webServices.ConnectionInfoHandler;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConnectionInfoFromContext implements ConnectionInfoHandler {

	@Override
	public WSConnectionInfo getWSConnectionInfo() {
	
		return null;
	}

}