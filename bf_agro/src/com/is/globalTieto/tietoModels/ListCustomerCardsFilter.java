package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CLIENT		string(8)	Клиентский номер держателя карты
 * CLIENT_B		string(19)	Номер клиента в банке
 * BANK_C		string(2)	Код банка – владельца строки *

 */

@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerCardsFilter {
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String client_b;
	@Getter
	@Setter
	private String bank_c;

}
