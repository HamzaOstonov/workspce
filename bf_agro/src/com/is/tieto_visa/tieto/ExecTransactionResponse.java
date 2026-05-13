package com.is.tieto_visa.tieto;

import java.math.BigDecimal;


/**
*	INTERNAL_NO	Decimal(12,0)	Внутренний номер строки
*/


public class ExecTransactionResponse {
	private BigDecimal internal_no;

	public BigDecimal getInternal_no() {
		return internal_no;
	}

	public void setInternal_no(BigDecimal internal_no) {
		this.internal_no = internal_no;
	}

	public ExecTransactionResponse(BigDecimal internal_no) {
		super();
		this.internal_no = internal_no;
	}

	public ExecTransactionResponse() {
		super();
	}
	
	
}
