package com.is.tieto_visa.tieto;

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
