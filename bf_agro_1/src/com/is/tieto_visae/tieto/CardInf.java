package com.is.tieto_visae.tieto;

import java.math.BigDecimal;

public class CardInf
{
	private String ACCOUNT_NO;
	private String CARD_ACCT;
	private String CARD;
	private String BASE_SUPP;
	private String STATUS;
	private String STATUS2;
	private String STOP_CAUSE;
	private String EXPIRY;
	private String EXPIRY2;
	private String COND_SET;
	private String RISK_LEVEL;
	private String CLIENT_ID;
	private String CL_ROLE;
	private String AGREEMENT_KEY;
	private String CARD_String;
	private String BANK_C;
	private String GROUPC;
	private BigDecimal ACCOUNT_AVAIL_AMOUNT;
	private BigDecimal ACCOUNT_LOCKED_AMOUNT;
	private BigDecimal ACCOUNT_END_BAL;
	private String Bank_account;
	private String Bank_account_status;
	private String Bank_account_Ccy;
	
	public CardInf(String aCCOUNT_NO, String cARD_ACCT, String cARD, String bASE_SUPP, String sTATUS, String sTATUS2, String sTOP_CAUSE, String eXPIRY, String eXPIRY2, String cOND_SET, String rISK_LEVEL, String cLIENT_ID, String cL_ROLE, String aGREEMENT_KEY, String cARD_String, String bANK_C, String gROUPC)
	{
		super();
		this.ACCOUNT_NO = aCCOUNT_NO;
		this.CARD_ACCT = cARD_ACCT;
		this.CARD = cARD;
		this.BASE_SUPP = bASE_SUPP;
		this.STATUS = sTATUS;
		this.STATUS2 = sTATUS2;
		this.STOP_CAUSE = sTOP_CAUSE;
		this.EXPIRY = eXPIRY;
		this.EXPIRY2 = eXPIRY2;
		this.COND_SET = cOND_SET;
		this.RISK_LEVEL = rISK_LEVEL;
		this.CLIENT_ID = cLIENT_ID;
		this.CL_ROLE = cL_ROLE;
		this.AGREEMENT_KEY = aGREEMENT_KEY;
		this.CARD_String = cARD_String;
		this.BANK_C = bANK_C;
		this.GROUPC = gROUPC;
	}
	
	public CardInf()
	{
		super();
	}
	
	public String getACCOUNT_NO()
	{
		return this.ACCOUNT_NO;
	}
	
	public void setACCOUNT_NO(String aCCOUNT_NO)
	{
		this.ACCOUNT_NO = aCCOUNT_NO;
	}
	
	public String getCARD_ACCT()
	{
		return this.CARD_ACCT;
	}
	
	public void setCARD_ACCT(String cARD_ACCT)
	{
		this.CARD_ACCT = cARD_ACCT;
	}
	
	public String getCARD()
	{
		return this.CARD;
	}
	
	public void setCARD(String cARD)
	{
		this.CARD = cARD;
	}
	
	public String getBASE_SUPP()
	{
		return this.BASE_SUPP;
	}
	
	public void setBASE_SUPP(String bASE_SUPP)
	{
		this.BASE_SUPP = bASE_SUPP;
	}
	
	public String getSTATUS()
	{
		return this.STATUS;
	}
	
	public void setSTATUS(String sTATUS)
	{
		this.STATUS = sTATUS;
	}
	
	public String getSTATUS2()
	{
		return this.STATUS2;
	}
	
	public void setSTATUS2(String sTATUS2)
	{
		this.STATUS2 = sTATUS2;
	}
	
	public String getSTOP_CAUSE()
	{
		return this.STOP_CAUSE;
	}
	
	public void setSTOP_CAUSE(String sTOP_CAUSE)
	{
		this.STOP_CAUSE = sTOP_CAUSE;
	}
	
	public String getEXPIRY()
	{
		return this.EXPIRY;
	}
	
	public void setEXPIRY(String eXPIRY)
	{
		this.EXPIRY = eXPIRY;
	}
	
	public String getEXPIRY2()
	{
		return this.EXPIRY2;
	}
	
	public void setEXPIRY2(String eXPIRY2)
	{
		this.EXPIRY2 = eXPIRY2;
	}
	
	public String getCOND_SET()
	{
		return this.COND_SET;
	}
	
	public void setCOND_SET(String cOND_SET)
	{
		this.COND_SET = cOND_SET;
	}
	
	public String getRISK_LEVEL()
	{
		return this.RISK_LEVEL;
	}
	
	public void setRISK_LEVEL(String rISK_LEVEL)
	{
		this.RISK_LEVEL = rISK_LEVEL;
	}
	
	public String getCLIENT_ID()
	{
		return this.CLIENT_ID;
	}
	
	public void setCLIENT_ID(String cLIENT_ID)
	{
		this.CLIENT_ID = cLIENT_ID;
	}
	
	public String getCL_ROLE()
	{
		return this.CL_ROLE;
	}
	
	public void setCL_ROLE(String cL_ROLE)
	{
		this.CL_ROLE = cL_ROLE;
	}
	
	public String getAGREEMENT_KEY()
	{
		return this.AGREEMENT_KEY;
	}
	
	public void setAGREEMENT_KEY(String aGREEMENT_KEY)
	{
		this.AGREEMENT_KEY = aGREEMENT_KEY;
	}
	
	public String getCARD_String()
	{
		return this.CARD_String;
	}
	
	public void setCARD_String(String cARD_String)
	{
		this.CARD_String = cARD_String;
	}
	
	public String getBANK_C()
	{
		return this.BANK_C;
	}
	
	public void setBANK_C(String bANK_C)
	{
		this.BANK_C = bANK_C;
	}
	
	public String getGROUPC()
	{
		return this.GROUPC;
	}
	
	public void setGROUPC(String gROUPC)
	{
		this.GROUPC = gROUPC;
	}
	
	public BigDecimal getACCOUNT_AVAIL_AMOUNT()
	{
		return this.ACCOUNT_AVAIL_AMOUNT;
	}
	
	public void setACCOUNT_AVAIL_AMOUNT(BigDecimal aCCOUNT_AVAIL_AMOUNT)
	{
		this.ACCOUNT_AVAIL_AMOUNT = aCCOUNT_AVAIL_AMOUNT;
	}
	
	public BigDecimal getACCOUNT_LOCKED_AMOUNT()
	{
		return this.ACCOUNT_LOCKED_AMOUNT;
	}
	
	public void setACCOUNT_LOCKED_AMOUNT(BigDecimal aCCOUNT_LOCKED_AMOUNT)
	{
		this.ACCOUNT_LOCKED_AMOUNT = aCCOUNT_LOCKED_AMOUNT;
	}
	
	public BigDecimal getACCOUNT_END_BAL()
	{
		return this.ACCOUNT_END_BAL;
	}
	
	public void setACCOUNT_END_BAL(BigDecimal aCCOUNT_END_BAL)
	{
		this.ACCOUNT_END_BAL = aCCOUNT_END_BAL;
	}
	
	public String getBank_account()
	{
		return this.Bank_account;
	}
	
	public void setBank_account(String bank_account)
	{
		this.Bank_account = bank_account;
	}
	
	public String getBank_account_status()
	{
		return this.Bank_account_status;
	}
	
	public void setBank_account_status(String bank_account_status)
	{
		this.Bank_account_status = bank_account_status;
	}

	public String getBank_account_Ccy()
	{
		return this.Bank_account_Ccy;
	}

	public void setBank_account_Ccy(String bank_account_Ccy)
	{
		this.Bank_account_Ccy = bank_account_Ccy;
	}	
	
}

