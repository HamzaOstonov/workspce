package com.is.clients.services;


import com.is.client_personmap.PersonMapService;
import com.is.clients.dao.DaoFactory;

import lombok.Getter;

public class ServiceFactory {
	private String alias;
	private String un;
	private String pw;
	
	@Getter(lazy=true) private final DictionaryKeeper dictionaryKeeper = DictionaryKeeper.getInstance(alias) ;
	@Getter(lazy=true) private final DaoFactory daoFactory = DaoFactory.getInstance(alias);
	@Getter(lazy=true) private final UtilityService utilityService = UtilityService.getInstance(alias);
	@Getter(lazy=true) private final PersonMapService personMapService = PersonMapService.instance(alias);
	@Getter(lazy=true) private final ClientJService clientJService = ClientJService.getInstance(un, pw, alias);
	
	private ServiceFactory(String alias,String un,String pw) {
		this.alias = alias;
		this.un = un;
		this.pw = pw;
	}
	
	public static ServiceFactory getInstance(String alias,String un,String pw) {
		return new ServiceFactory(alias,un,pw);
	}
}
