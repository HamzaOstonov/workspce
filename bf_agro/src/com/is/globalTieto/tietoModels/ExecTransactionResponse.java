package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*	INTERNAL_NO	Decimal(12,0)	Внутренний номер строки
*/

@NoArgsConstructor
@AllArgsConstructor
public class ExecTransactionResponse {
	@Getter
	@Setter
	private BigDecimal internal_no;
}
