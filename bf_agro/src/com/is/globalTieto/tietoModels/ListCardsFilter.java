package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* ACCOUNT_NO	number		Внутренний номер счета
* CARD_ACCT		string(34)	Карточный счет
* CCY			string(19)	Валюта счета
 */
@NoArgsConstructor
@AllArgsConstructor
public class ListCardsFilter {
	@Getter
	@Setter
	private BigDecimal account_no;
	@Getter
	@Setter
	private String card_acct;
	@Getter
	@Setter
	private String ccy;
}
