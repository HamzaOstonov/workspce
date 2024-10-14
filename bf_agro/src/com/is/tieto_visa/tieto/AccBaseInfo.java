package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author D
 * ACCOUNT_NO decimal(20,0) ���������� ����� ����� (ORASEQ)
 * ACC_PRTY string(1) ��������� ����� (�� ��������� 1)
 * C_ACCNT_TYPE string(2) ��� ���� �����
 * CARD_ACCT string(34) ����� ����� �����.
 * CCY string(3) ������ �����
 * TRANZ_ACCT string(34) ����� ����� ��� ������������� �� ���������� �������.
 * CYCLE string(10) ����������� ����
 * MIN_BAL decimal(10,0) ���������� ������������� ������, ������������� ������ ��� ����������� ����� ����� � ���������� ������� ������������ �����.
 * COND_SET string(3) ����� ���������� �������, ����� ��� ������ ������ (�� ��������� 0)
 * STATUS string(1) ������ ����� 0,3,4, ��� �generic� ��������� ���� ���������� ������� ��� closed
 * STAT_CHANGE string(1) ������� ��������� ������� ����� � ������ ��� ��������������.
 * STA_COMENT string(40) ����������� � ��������� ������� �����
 * AUTH_BONUS Decimal(12,0) �������������� ����� �����������
 * AB_EXPIRITY dateTime ���� ���������� �������� �������������� ����� ����������� (�� ��������� sysdate)
 * CRD Decimal(12,0) ��������� ����� ����� �����.
 * CRD_EXPIRY dateTime ���� ���������� �������� ������� 
 * ATM_LIMIT Decimal(12,0) �������� ����� ��� ��������� � ����������� ����� (�� ��������� 0)
 * NON_REDUCE_BAL Decimal(12,0) ������������� ������ (�� ��������� 0).
 * ADJUST_FLAG string(1) ������� �������������� ���������� (0 � ���������,1 � ��������� � ������ �����) (�� ��������� 0)
 * PAY_CODE string(1) ��� �������: 1 � �������������� �� �����, 2 � ������ (�� ��������� 0)
 * PAY_FREQ string(1) ������� ���������� �������� (�� ��������� 0)
 * CALCUL_MODE string(2) ����� ������� ������������� ����� (�� ��������� 0)
 * PAY_AMNT Decimal(12,0) ������������ ����� (�� ��������� 0).
 * PAY_INTR Decimal(6,2) % �� ���������� ������. ���� % ������������ ��� ������� ������������ ����� (�� ��������� 0).
 * LIM_AMNT Decimal(12,0) ����������� �����, ������������ ������������� ������� (�� ��������� 0)
 * LIM_INTR Decimal(6,2) ����������� % �� ��������� �����, ������������ ������������� �������
 * CREATED_DATE dateTime ���� �������� ����� (�� ��������� sysdate)
 * STOP_DATE dateTime ���� ��������� ����� 
 * MESSAGE string(120) ��������� ����� ��������� ������� �����
 * U_COD7 string(3) ����������� ������������� ��� 7
 * U_COD8 string(6) ����������� ������������� ��� 8
 * UFIELD_5 string(20) ����������� ������������� ���� 5
 * U_FIELD6 string(25) ����������� ������������� ���� 6
 * DEPOSIT Decimal(12,0) ����� �������� �������
 * DEPOSIT_COMENT string(30) ����������� � ����� ��������
 * DEPOSIT_ACCOUNT string(34) ����� ����� ��������
 * AGR_AMOUNT Decimal(12,0) ����, ������� ��������� � ���������� ����������������. 
 * DEP_EXP_DATE dateTime ����, ������� ��������� � ���������� ����������������. 
 * DEP_OPEN_F string(1) ����, ������� ��������� � ���������� ����������������. 
 * DEP_FRONT_F string(1) ����, ������� ��������� � ���������� ����������������. 
 * DEP_OPER_ACCT Decimal(20,0) ����, ������� ��������� � ���������� ����������������. 
 * DEP_OPER_ACCTB Decimal(1,0) ����, ������� ��������� � ���������� ����������������. 
 * DEP_OPER_BACCT string(21) ����, ������� ��������� � ���������� ����������������. 
 * IN_FILE_NUM Decimal(7,0) ������������� ������� ������ (Batch task ID)
 * OPEN_INSTL Decimal(5,0) ������������ ����������� ����� �������� ����� � ��������� (Installments)
 * INSTL_LINE Decimal(7,2) ��������� ������� �� CRD ��� ������ � ��������� (Installments).
 * INSTL_CONDSET string(3) ������� ������ � ��������� (Installment).
 */

public class AccBaseInfo {

	
	
	private BigDecimal ACCOUNT_NO;
	
	
	private String ACC_PRTY;
	
	
	private String C_ACCNT_TYPE;
	
	
	private String CARD_ACCT;
	
	
	private String CCY;
	
	
	private String TRANZ_ACCT;
	
	
	private String CYCLE;
	
	
	private BigDecimal MIN_BAL;
	
	
	private String COND_SET;
	
	
	private String STATUS;
	
	
	private String STAT_CHANGE;
	
	
	private String STA_COMENT;
	
	
	private BigDecimal AUTH_BONUS;
	
	
	private Date AB_EXPIRITY;
	
	
	private BigDecimal CRD;
	
	
	private Date CRD_EXPIRY;
	
	
	private BigDecimal ATM_LIMIT;
	
	
	private BigDecimal NON_REDUCE_BAL;
	
	
	private String ADJUST_FLAG;
	
	
	private String PAY_CODE;
	
	
	private String PAY_FREQ;
	
	
	private String CALCUL_MODE;
	
	
	private BigDecimal PAY_AMNT;
	
	
	private BigDecimal PAY_INTR;
	
	
	private BigDecimal LIM_AMNT;
	
	
	private BigDecimal LIM_INTR;
	
	
	private Date CREATED_DATE;
	
	
	private Date STOP_DATE;
	
	
	private String MESSAGE;
	
	
	private String U_COD7;
	
	
	private String U_COD8;
	
	
	private String UFIELD_5;
	
	
	private String U_FIELD6;
	
	
	private BigDecimal DEPOSIT;
	
	
	private String DEPOSIT_COMENT;
	
	
	private String DEPOSIT_ACCOUNT;
	
	
	private BigDecimal AGR_AMOUNT;
	
	
	private Date DEP_EXP_DATE;
	
	
	private String DEP_OPEN_F;
	
	
	private String DEP_FRONT_F;
	
	
	private BigDecimal DEP_OPER_ACCT;
	
	
	private BigDecimal DEP_OPER_ACCTB;
	
	
	private String DEP_OPER_BACCT;
	
	
	private BigDecimal IN_FILE_NUM;
	
	
	private BigDecimal OPEN_INSTL;
	
	
	private BigDecimal INSTL_LINE;
	
	
	private String INSTL_CONDSET;
	
}
