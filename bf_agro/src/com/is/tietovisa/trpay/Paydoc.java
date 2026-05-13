package com.is.tietovisa.trpay;

import java.sql.Date;

public class Paydoc {
	
	int ORD;
	String BRANCH;
	Date D_DATE;
	String BANK_CL;
	String ACC_CL;
	String NAME_CL;
	String BANK_CO;
	String ACC_CO;
	String NAME_CO;
	String PURPOSE;
	Double SUMMA;
	String TYPEDOC;
	String PDC;
	String CASH_CODE;
	int ID_TRANSH_PURP;
	String G_BRANCH;
	int G_DOCID;
	
		
	public Paydoc(int oRD, String bRANCH, Date d_DATE, String bANK_CL,
			String aCC_CL, String nAME_CL, String bANK_CO, String aCC_CO,
			String nAME_CO, String pURPOSE, Double sUMMA, String tYPEDOC,
			String pDC, String cASH_CODE, int iD_TRANSH_PURP, String g_BRANCH,
			int g_DOCID)
	{
		super();
		ORD = oRD;
		BRANCH = bRANCH;
		D_DATE = d_DATE;
		BANK_CL = bANK_CL;
		ACC_CL = aCC_CL;
		NAME_CL = nAME_CL;
		BANK_CO = bANK_CO;
		ACC_CO = aCC_CO;
		NAME_CO = nAME_CO;
		PURPOSE = pURPOSE;
		SUMMA = sUMMA;
		TYPEDOC = tYPEDOC;
		PDC = pDC;
		CASH_CODE = cASH_CODE;
		ID_TRANSH_PURP = iD_TRANSH_PURP;
		G_BRANCH = g_BRANCH;
		G_DOCID = g_DOCID;
	}
	
	
	public int getORD() {
		return ORD;
	}
	public void setORD(int oRD) {
		ORD = oRD;
	}
	public String getBRANCH() {
		return BRANCH;
	}
	public void setBRANCH(String bRANCH) {
		BRANCH = bRANCH;
	}
	public Date getD_DATE() {
		return D_DATE;
	}
	public void setD_DATE(Date d_DATE) {
		D_DATE = d_DATE;
	}
	public String getBANK_CL() {
		return BANK_CL;
	}
	public void setBANK_CL(String bANK_CL) {
		BANK_CL = bANK_CL;
	}
	public String getACC_CL() {
		return ACC_CL;
	}
	public void setACC_CL(String aCC_CL) {
		ACC_CL = aCC_CL;
	}
	public String getNAME_CL() {
		return NAME_CL;
	}
	public void setNAME_CL(String nAME_CL) {
		NAME_CL = nAME_CL;
	}
	public String getBANK_CO() {
		return BANK_CO;
	}
	public void setBANK_CO(String bANK_CO) {
		BANK_CO = bANK_CO;
	}
	public String getACC_CO() {
		return ACC_CO;
	}
	public void setACC_CO(String aCC_CO) {
		ACC_CO = aCC_CO;
	}
	public String getNAME_CO() {
		return NAME_CO;
	}
	public void setNAME_CO(String nAME_CO) {
		NAME_CO = nAME_CO;
	}
	public String getPURPOSE() {
		return PURPOSE;
	}
	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}
	public Double getSUMMA() {
		return SUMMA;
	}
	public void setSUMMA(Double sUMMA) {
		SUMMA = sUMMA;
	}
	public String getTYPEDOC() {
		return TYPEDOC;
	}
	public void setTYPEDOC(String tYPEDOC) {
		TYPEDOC = tYPEDOC;
	}
	public String getPDC() {
		return PDC;
	}
	public void setPDC(String pDC) {
		PDC = pDC;
	}
	public String getCASH_CODE() {
		return CASH_CODE;
	}
	public void setCASH_CODE(String cASH_CODE) {
		CASH_CODE = cASH_CODE;
	}
	public int getID_TRANSH_PURP() {
		return ID_TRANSH_PURP;
	}
	public void setID_TRANSH_PURP(int iD_TRANSH_PURP) {
		ID_TRANSH_PURP = iD_TRANSH_PURP;
	}
	public String getG_BRANCH() {
		return G_BRANCH;
	}
	public void setG_BRANCH(String g_BRANCH) {
		G_BRANCH = g_BRANCH;
	}
	public int getG_DOCID() {
		return G_DOCID;
	}
	public void setG_DOCID(int g_DOCID) {
		G_DOCID = g_DOCID;
	}
	
}
