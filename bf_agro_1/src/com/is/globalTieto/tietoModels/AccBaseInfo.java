package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@AllArgsConstructor
public class AccBaseInfo {

	@Getter
	@Setter
	private BigDecimal ACCOUNT_NO;
	@Getter
	@Setter
	private String ACC_PRTY;
	@Getter
	@Setter
	private String C_ACCNT_TYPE;
	@Getter
	@Setter
	private String CARD_ACCT;
	@Getter
	@Setter
	private String CCY;
	@Getter
	@Setter
	private String TRANZ_ACCT;
	@Getter
	@Setter
	private String CYCLE;
	@Getter
	@Setter
	private BigDecimal MIN_BAL;
	@Getter
	@Setter
	private String COND_SET;
	@Getter
	@Setter
	private String STATUS;
	@Getter
	@Setter
	private String STAT_CHANGE;
	@Getter
	@Setter
	private String STA_COMENT;
	@Getter
	@Setter
	private BigDecimal AUTH_BONUS;
	@Getter
	@Setter
	private Date AB_EXPIRITY;
	@Getter
	@Setter
	private BigDecimal CRD;
	@Getter
	@Setter
	private Date CRD_EXPIRY;
	@Getter
	@Setter
	private BigDecimal ATM_LIMIT;
	@Getter
	@Setter
	private BigDecimal NON_REDUCE_BAL;
	@Getter
	@Setter
	private String ADJUST_FLAG;
	@Getter
	@Setter
	private String PAY_CODE;
	@Getter
	@Setter
	private String PAY_FREQ;
	@Getter
	@Setter
	private String CALCUL_MODE;
	@Getter
	@Setter
	private BigDecimal PAY_AMNT;
	@Getter
	@Setter
	private BigDecimal PAY_INTR;
	@Getter
	@Setter
	private BigDecimal LIM_AMNT;
	@Getter
	@Setter
	private BigDecimal LIM_INTR;
	@Getter
	@Setter
	private Date CREATED_DATE;
	@Getter
	@Setter
	private Date STOP_DATE;
	@Getter
	@Setter
	private String MESSAGE;
	@Getter
	@Setter
	private String U_COD7;
	@Getter
	@Setter
	private String U_COD8;
	@Getter
	@Setter
	private String UFIELD_5;
	@Getter
	@Setter
	private String U_FIELD6;
	@Getter
	@Setter
	private BigDecimal DEPOSIT;
	@Getter
	@Setter
	private String DEPOSIT_COMENT;
	@Getter
	@Setter
	private String DEPOSIT_ACCOUNT;
	@Getter
	@Setter
	private BigDecimal AGR_AMOUNT;
	@Getter
	@Setter
	private Date DEP_EXP_DATE;
	@Getter
	@Setter
	private String DEP_OPEN_F;
	@Getter
	@Setter
	private String DEP_FRONT_F;
	@Getter
	@Setter
	private BigDecimal DEP_OPER_ACCT;
	@Getter
	@Setter
	private BigDecimal DEP_OPER_ACCTB;
	@Getter
	@Setter
	private String DEP_OPER_BACCT;
	@Getter
	@Setter
	private BigDecimal IN_FILE_NUM;
	@Getter
	@Setter
	private BigDecimal OPEN_INSTL;
	@Getter
	@Setter
	private BigDecimal INSTL_LINE;
	@Getter
	@Setter
	private String INSTL_CONDSET;
	
}
