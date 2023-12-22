package com.is.nibbd.util;

import java.util.HashMap;


public enum NibbdQueries {
	
	IDENTIFICATION(	0, NibbdUtils.TYPE_CLIENT, 
					0, "Индентификация клиента"),//
	CLIENT_OPEN(	1, NibbdUtils.TYPE_CLIENT, 
					1, "Регистрация в НИББД"),
	ACCOUNT_OPEN(	2, NibbdUtils.TYPE_ACCOUNT, 
					0, "Открытие счета клиента по его коду в НИББД"),
	ACCOUNT_BLOCK(	3, NibbdUtils.TYPE_ACCOUNT, 
					1, "Временное закрытие(блокирование) счета"),
	ACCOUNT_UNBLOCK(4, NibbdUtils.TYPE_ACCOUNT, 
					2, "Разблокирование счета"),
	CLIENT_CHANGE(	5, NibbdUtils.TYPE_CLIENT, 
					2, "Изменение объективных данных клиента"),//
	ACCOUNT_MOVE(	6, NibbdUtils.TYPE_ACCOUNT, 
					3 ,"Перевод счета клиента в другой банк"),//
	ACCOUNT_CLOSE(	7, NibbdUtils.TYPE_ACCOUNT, 
					4, "Закрытие счета клиента"),
	ACCOUNTS_OUSIDE_FILIAL(8, NibbdUtils.TYPE_CLIENT, 
					3, "Получение сведений о счетах клиента"),
	CLIENT_STOP(	9, NibbdUtils.TYPE_CLIENT, 
					4, "Прекращение деятельности клиента");
	
	
	
	private int queryNumber;
	private int type;
	private int radioOrder;
	private String text;
	private static HashMap<Integer, NibbdQueries> lookup = new HashMap<Integer, NibbdQueries>();
	
	static {
		for(NibbdQueries q: NibbdQueries.values()) {
			lookup.put(q.getQueryNumber(), q);
		}
	}
	
	private NibbdQueries(int queryNumber, int type, int radioOrder, String text) {
		this.queryNumber = queryNumber;
		this.type = type;
		this.radioOrder = radioOrder;
		this.text = text;
	}
	
	public int getQueryNumber() {
		return queryNumber;
	}
	public int getType() {
		return type;
	}
	public int getRadioOrder() {
		return radioOrder;
	}
	public String getText() {
		return text;
	}
	
	public static NibbdQueries getQueryType(int num) {
		return lookup.get(num);
	}
}
