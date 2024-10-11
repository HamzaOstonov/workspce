package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* ACCOUNT_NO	number		���������� ����� �����
* CARD_ACCT		string(34)	��������� ����
* CCY			string(19)	������ �����
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
