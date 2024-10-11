package com.is.tieto_capital;

import java.math.BigDecimal;

public class Constants {
	public static final String CURRENCY_USD = "840";
	public static final String ACC_22618 = "22618";
	public static final int MAX_LENGTH_OF_CARD_NAME = 22;
	public static final String COUNTRY_CODE = "860";
	public static final String COUNTRY_ALPHA = "UZB";
	
	// CUSTOMER	
	public static final String P_CODE_CLASS_CREDIT = "1";
	public static final String P_PASSPORT_TYPE = "N";
	public static final String CODE_SUBJECT = "P";
	public static final int SIGN_REGISTR = 2;
	public static final String CODE_FORM = "";
	public static final String CODE_TYPE = "08";
	
	// OPEN CUSTOMER
	// newCustomer
	public static final String CUSTOMER_CL_TYPE = "1";
	public static final String CUSTOMER_STATUS = "10";
	public static final String CUSTOMER_DOC_TYPE = "200";
	
	
	// OPEN CARD: newAgreement, addInfo4Agreement
	// agreementInfo
	public static final String AGREEMENT_REP_LANG = "3";
	public static final String AGREEMENT_STATUS = "10";
	public static final String AGREEMENT_DISTRIB_MODE = "01";
	
	// base_info
	public static final String AGREEMENT_C_ACCNT_TYPE = "00";
	public static final String AGREEMENT_CCY = "USD";
	public static final String AGREEMENT_STAT_CHANGE = "0";
	public static final BigDecimal AGREEMENT_MIN_BAL = BigDecimal.valueOf(0);
	public static final String AGREEMENT_ACC_PRTY = "1";
	public static final String AGREEMENT_CYCLE = "0";
	public static final BigDecimal AGREEMENT_CRD = BigDecimal.valueOf(0);
	public static final String AGREEMENT_BASE_INFO_STATUS = "0";
	public static final BigDecimal AGREEMENT_LIM_INTR = BigDecimal.valueOf(0);
	
	// logicalCard
	public static final String AGREEMENT_BASE_SUPP = "1";
	
	// physicalCard
	public static final String AGREEMENT_STATUS1 = "0";

	// cardInfo
	public static final String BANK_ACCOUNT_STATUS = "0";
	
	
	// GlobuzAccount status
	public static final String ACC_ACTIVE = "0";
	public static final String ACC_NOT_ACTIVE = "3";
	public static final String ACC_CLOSED = "4";
	
	
	// Approval card
	public static final int APPROVAL_INDETERMINATELY = 0;
		
	public static final int APPROVAL_STATE_OPEN = 1;
	public static final int APPROVAL_STATE_CONFIRM = 2;
	public static final int APPROVAL_STATE_APPROVE = 3;
	public static final int APPROVAL_STATE_CLOSE = 4;
	
	public static final int APPROVAL_TYPE_OPEN = 1;
	public static final int APPROVAL_TYPE_CONFIRM = 2;
	public static final int APPROVAL_TYPE_APPROVE = 3;
	public static final int APPROVAL_TYPE_CLOSE = 4;
	
	public static final int SIZE_OF_ACTION_ARRAY = 4;
}
