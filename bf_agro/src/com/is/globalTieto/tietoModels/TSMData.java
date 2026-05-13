package com.is.globalTieto.tietoModels;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * TSM_CLIENT_ID string (20) Идентификатор TSM клиента.
 * TSM_AUTH_CODE string (20) Авторизационный код TSM карточки.
 * TSM_AUTH_EXPIRY dateTime Срок годности авторизационного кода. 
 */
@NoArgsConstructor
@AllArgsConstructor
public class TSMData {

	@Getter
	@Setter
	private String TSM_CLIENT_ID;
	@Getter
	@Setter
	private String TSM_AUTH_CODE;
	@Getter
	@Setter
	private Date TSM_AUTH_EXPIRY;
	
}
