package com.is.tieto_capital.tieto;

import java.util.Date;

public class Agreement
{
	int AGRE_NOM;
	String BINCOD;
	String BANK_CODE;
	String BRANCH;
	int B_BR_ID;
	String OFFICE;
	int OFFICE_ID;
	String CITY;
	String CLIENT;
	String COMENT;
	String CONTRACT;
	String COUNTRY;
	Date CTIME;
	String DISTRIB_MODE;
	Date ENROLLED;
	String E_MAILS;
	int IN_FILE_NUM;
	String ISURANCE_TYPE;
	String POST_IND;
	String PRODUCT;
	String REP_LANG;
	String RISK_LEVEL;
	String STATUS;
	String STREET;
	String USRID;
	String U_COD4;
	String U_CODE5;
	String U_CODE6;
	String U_FIELD3;
	String U_FIELD4;
	
	public Agreement()
	{
		super();
	}
	
	public Agreement(int aGRE_NOM, String bINCOD, String bANK_CODE,
						String bRANCH, int b_BR_ID, String oFFICE, int oFFICE_ID,
						String cITY, String cLIENT, String cOMENT, String cONTRACT,
						String cOUNTRY, Date cTIME, String dISTRIB_MODE, Date eNROLLED,
						String e_MAILS, int iN_FILE_NUM, String iSURANCE_TYPE,
						String pOST_IND, String pRODUCT, String rEP_LANG,
						String rISK_LEVEL, String sTATUS, String sTREET, String uSRID,
						String u_COD4, String u_CODE5, String u_CODE6, String u_FIELD3,
						String u_FIELD4)
	{
		super();
		AGRE_NOM = aGRE_NOM;
		BINCOD = bINCOD;
		BANK_CODE = bANK_CODE;
		BRANCH = bRANCH;
		B_BR_ID = b_BR_ID;
		OFFICE = oFFICE;
		OFFICE_ID = oFFICE_ID;
		CITY = cITY;
		CLIENT = cLIENT;
		COMENT = cOMENT;
		CONTRACT = cONTRACT;
		COUNTRY = cOUNTRY;
		CTIME = cTIME;
		DISTRIB_MODE = dISTRIB_MODE;
		ENROLLED = eNROLLED;
		E_MAILS = e_MAILS;
		IN_FILE_NUM = iN_FILE_NUM;
		ISURANCE_TYPE = iSURANCE_TYPE;
		POST_IND = pOST_IND;
		PRODUCT = pRODUCT;
		REP_LANG = rEP_LANG;
		RISK_LEVEL = rISK_LEVEL;
		STATUS = sTATUS;
		STREET = sTREET;
		USRID = uSRID;
		U_COD4 = u_COD4;
		U_CODE5 = u_CODE5;
		U_CODE6 = u_CODE6;
		U_FIELD3 = u_FIELD3;
		U_FIELD4 = u_FIELD4;
	}
	
	public int getAGRE_NOM()
	{
		return AGRE_NOM;
	}
	
	public void setAGRE_NOM(int aGRE_NOM)
	{
		AGRE_NOM = aGRE_NOM;
	}
	
	public String getBINCOD()
	{
		return BINCOD;
	}
	
	public void setBINCOD(String bINCOD)
	{
		BINCOD = bINCOD;
	}
	
	public String getBANK_CODE()
	{
		return BANK_CODE;
	}
	
	public void setBANK_CODE(String bANK_CODE)
	{
		BANK_CODE = bANK_CODE;
	}
	
	public String getBRANCH()
	{
		return BRANCH;
	}
	
	public void setBRANCH(String bRANCH)
	{
		BRANCH = bRANCH;
	}
	
	public int getB_BR_ID()
	{
		return B_BR_ID;
	}
	
	public void setB_BR_ID(int b_BR_ID)
	{
		B_BR_ID = b_BR_ID;
	}
	
	public String getOFFICE()
	{
		return OFFICE;
	}
	
	public void setOFFICE(String oFFICE)
	{
		OFFICE = oFFICE;
	}
	
	public int getOFFICE_ID()
	{
		return OFFICE_ID;
	}
	
	public void setOFFICE_ID(int oFFICE_ID)
	{
		OFFICE_ID = oFFICE_ID;
	}
	
	public String getCITY()
	{
		return CITY;
	}
	
	public void setCITY(String cITY)
	{
		CITY = cITY;
	}
	
	public String getCLIENT()
	{
		return CLIENT;
	}
	
	public void setCLIENT(String cLIENT)
	{
		CLIENT = cLIENT;
	}
	
	public String getCOMENT()
	{
		return COMENT;
	}
	
	public void setCOMENT(String cOMENT)
	{
		COMENT = cOMENT;
	}
	
	public String getCONTRACT()
	{
		return CONTRACT;
	}
	
	public void setCONTRACT(String cONTRACT)
	{
		CONTRACT = cONTRACT;
	}
	
	public String getCOUNTRY()
	{
		return COUNTRY;
	}
	
	public void setCOUNTRY(String cOUNTRY)
	{
		COUNTRY = cOUNTRY;
	}
	
	public Date getCTIME()
	{
		return CTIME;
	}
	
	public void setCTIME(Date cTIME)
	{
		CTIME = cTIME;
	}
	
	public String getDISTRIB_MODE()
	{
		return DISTRIB_MODE;
	}
	
	public void setDISTRIB_MODE(String dISTRIB_MODE)
	{
		DISTRIB_MODE = dISTRIB_MODE;
	}
	
	public Date getENROLLED()
	{
		return ENROLLED;
	}
	
	public void setENROLLED(Date eNROLLED)
	{
		ENROLLED = eNROLLED;
	}
	
	public String getE_MAILS()
	{
		return E_MAILS;
	}
	
	public void setE_MAILS(String e_MAILS)
	{
		E_MAILS = e_MAILS;
	}
	
	public int getIN_FILE_NUM()
	{
		return IN_FILE_NUM;
	}
	
	public void setIN_FILE_NUM(int iN_FILE_NUM)
	{
		IN_FILE_NUM = iN_FILE_NUM;
	}
	
	public String getISURANCE_TYPE()
	{
		return ISURANCE_TYPE;
	}
	
	public void setISURANCE_TYPE(String iSURANCE_TYPE)
	{
		ISURANCE_TYPE = iSURANCE_TYPE;
	}
	
	public String getPOST_IND()
	{
		return POST_IND;
	}
	
	public void setPOST_IND(String pOST_IND)
	{
		POST_IND = pOST_IND;
	}
	
	public String getPRODUCT()
	{
		return PRODUCT;
	}
	
	public void setPRODUCT(String pRODUCT)
	{
		PRODUCT = pRODUCT;
	}
	
	public String getREP_LANG()
	{
		return REP_LANG;
	}
	
	public void setREP_LANG(String rEP_LANG)
	{
		REP_LANG = rEP_LANG;
	}
	
	public String getRISK_LEVEL()
	{
		return RISK_LEVEL;
	}
	
	public void setRISK_LEVEL(String rISK_LEVEL)
	{
		RISK_LEVEL = rISK_LEVEL;
	}
	
	public String getSTATUS()
	{
		return STATUS;
	}
	
	public void setSTATUS(String sTATUS)
	{
		STATUS = sTATUS;
	}
	
	public String getSTREET()
	{
		return STREET;
	}
	
	public void setSTREET(String sTREET)
	{
		STREET = sTREET;
	}
	
	public String getUSRID()
	{
		return USRID;
	}
	
	public void setUSRID(String uSRID)
	{
		USRID = uSRID;
	}
	
	public String getU_COD4()
	{
		return U_COD4;
	}
	
	public void setU_COD4(String u_COD4)
	{
		U_COD4 = u_COD4;
	}
	
	public String getU_CODE5()
	{
		return U_CODE5;
	}
	
	public void setU_CODE5(String u_CODE5)
	{
		U_CODE5 = u_CODE5;
	}
	
	public String getU_CODE6()
	{
		return U_CODE6;
	}
	
	public void setU_CODE6(String u_CODE6)
	{
		U_CODE6 = u_CODE6;
	}
	
	public String getU_FIELD3()
	{
		return U_FIELD3;
	}
	
	public void setU_FIELD3(String u_FIELD3)
	{
		U_FIELD3 = u_FIELD3;
	}
	
	public String getU_FIELD4()
	{
		return U_FIELD4;
	}
	
	public void setU_FIELD4(String u_FIELD4)
	{
		U_FIELD4 = u_FIELD4;
	}
	
}
