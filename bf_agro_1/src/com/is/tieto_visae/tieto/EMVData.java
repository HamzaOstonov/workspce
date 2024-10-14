package com.is.tieto_visae.tieto;

import java.math.BigDecimal;



/**
 * @author D
 * SEQUENCE_NR String(3) �������� ����� ������������������ ����� (�� ��������� 1).
 * LAST_SEQ_NR String(3) ���������� ����� ������������������ �����
 * DESIGN_ID Decimal(10,0) �������� �� ������������
 * CHIP_APP_ID Decimal(10,0) ������������� ���������� ��� �����
 * INDIVIDUALIZED_PREV String(1) ������������������� ��� ���. (�� ��������� N)
 * DKI_0 String(3) DKI, ����� ����� ��������� � ������ �������������� 
 * DKI_1 String(3) ������ ��������� ����������������� ������
 * DKI_2 String(3) ������ ��������� ����������������� ������
 * TRACK2_EQUIV_DATA String(40) Track 2 ������������� ������ ��� ���-������ 
 * INDIVIDUALIZED String(1) ������ ���� ������������������� �� ��� (Y/N). (�� ��������� N)
 */

public class EMVData {

	
	
	public String getSEQUENCE_NR() {
		return SEQUENCE_NR;
	}


	public void setSEQUENCE_NR(String sEQUENCE_NR) {
		SEQUENCE_NR = sEQUENCE_NR;
	}


	public String getLAST_SEQ_NR() {
		return LAST_SEQ_NR;
	}


	public void setLAST_SEQ_NR(String lAST_SEQ_NR) {
		LAST_SEQ_NR = lAST_SEQ_NR;
	}


	public BigDecimal getDESIGN_ID() {
		return DESIGN_ID;
	}


	public void setDESIGN_ID(BigDecimal dESIGN_ID) {
		DESIGN_ID = dESIGN_ID;
	}


	public BigDecimal getCHIP_APP_ID() {
		return CHIP_APP_ID;
	}


	public void setCHIP_APP_ID(BigDecimal cHIP_APP_ID) {
		CHIP_APP_ID = cHIP_APP_ID;
	}


	public String getINDIVIDUALIZED_PREV() {
		return INDIVIDUALIZED_PREV;
	}


	public void setINDIVIDUALIZED_PREV(String iNDIVIDUALIZED_PREV) {
		INDIVIDUALIZED_PREV = iNDIVIDUALIZED_PREV;
	}


	public String getDKI_0() {
		return DKI_0;
	}


	public void setDKI_0(String dKI_0) {
		DKI_0 = dKI_0;
	}


	public String getDKI_1() {
		return DKI_1;
	}


	public void setDKI_1(String dKI_1) {
		DKI_1 = dKI_1;
	}


	public String getDKI_2() {
		return DKI_2;
	}


	public void setDKI_2(String dKI_2) {
		DKI_2 = dKI_2;
	}


	public String getTRACK2_EQUIV_DATA() {
		return TRACK2_EQUIV_DATA;
	}


	public void setTRACK2_EQUIV_DATA(String tRACK2_EQUIV_DATA) {
		TRACK2_EQUIV_DATA = tRACK2_EQUIV_DATA;
	}


	public String getINDIVIDUALIZED() {
		return INDIVIDUALIZED;
	}


	public void setINDIVIDUALIZED(String iNDIVIDUALIZED) {
		INDIVIDUALIZED = iNDIVIDUALIZED;
	}


	public EMVData() {
		super();
	}


	public EMVData(String sEQUENCE_NR, String lAST_SEQ_NR,
			BigDecimal dESIGN_ID, BigDecimal cHIP_APP_ID,
			String iNDIVIDUALIZED_PREV, String dKI_0, String dKI_1,
			String dKI_2, String tRACK2_EQUIV_DATA, String iNDIVIDUALIZED) {
		super();
		SEQUENCE_NR = sEQUENCE_NR;
		LAST_SEQ_NR = lAST_SEQ_NR;
		DESIGN_ID = dESIGN_ID;
		CHIP_APP_ID = cHIP_APP_ID;
		INDIVIDUALIZED_PREV = iNDIVIDUALIZED_PREV;
		DKI_0 = dKI_0;
		DKI_1 = dKI_1;
		DKI_2 = dKI_2;
		TRACK2_EQUIV_DATA = tRACK2_EQUIV_DATA;
		INDIVIDUALIZED = iNDIVIDUALIZED;
	}


	private String SEQUENCE_NR;
	
	
	private String LAST_SEQ_NR;
	
	
	private BigDecimal DESIGN_ID;
	
	
	private BigDecimal CHIP_APP_ID;
	
	
	private String INDIVIDUALIZED_PREV;
	
	
	private String DKI_0;
	
	
	private String DKI_1;
	
	
	private String DKI_2;
	
	
	private String TRACK2_EQUIV_DATA;
	
	
	private String INDIVIDUALIZED;
	
}
