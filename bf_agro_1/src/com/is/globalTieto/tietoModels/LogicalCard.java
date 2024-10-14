package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * CARD string(19) ����� �����
 * CLIENT string(8) ���������� ����� ��������� �����
 * CL_ROLE string(1) ���� �������.
 * CARD_TYPE string(2) ��� ����� � ��������, ��������� � ��.
 * BASE_SUPP string(1) ������� ��� �������������� �����
 * COND_SET string(3) ����� ������� �����
 * RISK_LEVEL string(5) ������� �����
 * CARD_SERVICES_SET string(16) ��� ������ �������� �����
 * REC_DATE datetime ���� ����������� �����
 * M_NAME string(20) ������� ������� ��������� �����
 * RELATION string(25) ��������� ����� ����������� �������������� � ������� �����
 * ID_CARD string(16) ����� ��������� ������������� ��������
 * B_DATE datetime ���� ��������
 * CALL_ID string(10) ���� ������ ����������
 * F_NAMES string(34) ���
 * SURNAME string(20) �������
 * F_NAME1 string(20) ���
 * MIDLE_NAME string(20) ��������
 * SERIAL_NO string(20) �������� ����� ��������� ������������� ��������
 * DOC_SINCE datetime ���� ������� ���������
 * CMPG_NAME string(24) �������� ������������� ����� ��� Branch ��� �������� ������� 
 * CRD_HOLD_MSG string(99) ��������� ����� ���������
 * INSURANC_TYPE string(2) ��� ���������
 * INSURANC_DATE datetime ���� ���������� ������������� �������
 * U_COD9 string(3) ����������� ������������� ��� 9
 * U_COD10 string(6) ����������� ������������� ��� 10
 * U_FIELD7 string(20) ����������� ������������� ���� 7
 * U_FIELD8 string(20) ����������� ������������� ���� 8
 * IN_FILE_NUM Decimal(7,0) ������������� ������� ������ (Batch task ID)
 * OUT_FILE_NUM Decimal(20,0) �������������� ������ ���������� �����
 * USRID string(6) ����������������� ����� ������������
 * CTIME datetime ���� � ����� (�� ��������� sysdate) ���������� ����� ��� ����������� 
 * EFFECTIVE_DATE1 dateTime ����������� ���� ��� Amex � ����, ����� ����� ���������� �������������;
 * COND_SET_2 string(3) ���������� �������� (�������������) ������ ������ ������� (Condition set). 3 �������.
 * COND_CHANGE_DATE dateTime ���� ����� ������ ������� ��������.
 * CHANGE_BACK_DATE dateTime ���� �������� ����������� ������ ������� ��������.
 * BRANCH String(5) ��� �������.
 * U_FIELD11 string(20) ����������� ������������� ���� 11
 * U_FIELD12 string(20) ����������� ������������� ���� 12
 * U_FIELD13 string(20) ����������� ������������� ���� 13
 * U_FIELD14 string(20) ����������� ������������� ���� 14
 * NO_NAME String(1)
 * RANGE_ID Decimal(5,0) ������������� ��������� ��������� �������.
 */
@NoArgsConstructor
@AllArgsConstructor
public class LogicalCard {

	@Getter
	@Setter
	private String CARD;
	@Getter
	@Setter
	private String CLIENT;
	@Getter
	@Setter
	private String CL_ROLE;
	@Getter
	@Setter
	private String CARD_TYPE;
	@Getter
	@Setter
	private String BASE_SUPP;
	@Getter
	@Setter
	private String COND_SET;
	@Getter
	@Setter
	private String RISK_LEVEL;
	@Getter
	@Setter
	private String CARD_SERVICES_SET;
	@Getter
	@Setter
	private Date REC_DATE;
	@Getter
	@Setter
	private String M_NAME;
	@Getter
	@Setter
	private String RELATION;
	@Getter
	@Setter
	private String ID_CARD;
	@Getter
	@Setter
	private Date B_DATE;
	@Getter
	@Setter
	private String CALL_ID;
	@Getter
	@Setter
	private String F_NAMES;
	@Getter
	@Setter
	private String SURNAME;
	@Getter
	@Setter
	private String F_NAME1;
	@Getter
	@Setter
	private String MIDLE_NAME;
	@Getter
	@Setter
	private String SERIAL_NO;
	@Getter
	@Setter
	private Date DOC_SINCE;
	@Getter
	@Setter
	private String CMPG_NAME;
	@Getter
	@Setter
	private String CRD_HOLD_MSG;
	@Getter
	@Setter
	private String INSURANC_TYPE;
	@Getter
	@Setter
	private Date INSURANC_DATE;
	@Getter
	@Setter
	private String U_COD9;
	@Getter
	@Setter
	private String U_COD10;
	@Getter
	@Setter
	private String U_FIELD7;
	@Getter
	@Setter
	private String U_FIELD8;
	@Getter
	@Setter
	private BigDecimal IN_FILE_NUM;
	@Getter
	@Setter
	private BigDecimal OUT_FILE_NUM;
	@Getter
	@Setter
	private String USRID;
	@Getter
	@Setter
	private Date CTIME;
	@Getter
	@Setter
	private Date EFFECTIVE_DATE1;
	@Getter
	@Setter
	private String COND_SET_2;
	@Getter
	@Setter
	private Date COND_CHANGE_DATE;
	@Getter
	@Setter
	private Date CHANGE_BACK_DATE;
	@Getter
	@Setter
	private String BRANCH;
	@Getter
	@Setter
	private String U_FIELD11;
	@Getter
	@Setter
	private String U_FIELD12;
	@Getter
	@Setter
	private String U_FIELD13;
	@Getter
	@Setter
	private String U_FIELD14;
	@Getter
	@Setter
	private String NO_NAME;
	@Getter
	@Setter
	private BigDecimal RANGE_ID;
	
}
