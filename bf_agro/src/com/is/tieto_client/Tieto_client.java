package com.is.tieto_client;

import java.util.Date;

public class Tieto_client {

	private String CLIENT;
	private String BANK_C;
	private Date REC_DATE;
	private String F_NAMES;
	private String SURNAME;
	private String TITLE;
	private String M_NAME;
	private Date B_DATE;
	private String RESIDENT;
	private Integer R_PHONE;
	
	public Tieto_client(String cLIENT, String bANK_C, Date rEC_DATE,
			String f_NAMES, String sURNAME, String tITLE, String m_NAME,
			Date b_DATE, String rESIDENT, Integer r_PHONE) {
		super();
		CLIENT = cLIENT;
		BANK_C = bANK_C;
		REC_DATE = rEC_DATE;
		F_NAMES = f_NAMES;
		SURNAME = sURNAME;
		TITLE = tITLE;
		M_NAME = m_NAME;
		B_DATE = b_DATE;
		RESIDENT = rESIDENT;
		R_PHONE = r_PHONE;
	}
	

	public Tieto_client() {
		super();
	}


	public String getCLIENT() {
		return CLIENT;
	}

	public void setCLIENT(String cLIENT) {
		CLIENT = cLIENT;
	}

	public String getBANK_C() {
		return BANK_C;
	}

	public void setBANK_C(String bANK_C) {
		BANK_C = bANK_C;
	}

	public Date getREC_DATE() {
		return REC_DATE;
	}

	public void setREC_DATE(Date rEC_DATE) {
		REC_DATE = rEC_DATE;
	}

	public String getF_NAMES() {
		return F_NAMES;
	}

	public void setF_NAMES(String f_NAMES) {
		F_NAMES = f_NAMES;
	}

	public String getSURNAME() {
		return SURNAME;
	}

	public void setSURNAME(String sURNAME) {
		SURNAME = sURNAME;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public Date getB_DATE() {
		return B_DATE;
	}

	public void setB_DATE(Date b_DATE) {
		B_DATE = b_DATE;
	}

	public String getRESIDENT() {
		return RESIDENT;
	}

	public void setRESIDENT(String rESIDENT) {
		RESIDENT = rESIDENT;
	}

	public Integer getR_PHONE() {
		return R_PHONE;
	}

	public void setR_PHONE(Integer r_PHONE) {
		R_PHONE = r_PHONE;
	}

	@Override
	public String toString() {
		return "Tieto_client [CLIENT=" + CLIENT + ", BANK_C=" + BANK_C
				+ ", REC_DATE=" + REC_DATE + ", F_NAMES=" + F_NAMES
				+ ", SURNAME=" + SURNAME + ", TITLE=" + TITLE + ", M_NAME="
				+ M_NAME + ", B_DATE=" + B_DATE + ", RESIDENT=" + RESIDENT
				+ ", R_PHONE=" + R_PHONE + "]";
	}
	
}
