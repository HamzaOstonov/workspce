package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.globalTieto.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * APARTMENT string(5) ����� ��������
 * BUILDING string(15) ����� ������
 * CALL_ID string(10) ����� ������ ����������
 * CCY_FOR_INCOM string(3) ������ ��� �������� ������ � ���������
 * CLIENT string(8) ���������� ����� ��������� �����. 
 * CLIENT_B string(19) ����� ������� � �����
 * CLN_CAT string(3) ��������� �������
 * CL_TYPE string(1) ��� �������: �1� � Private; �2� � Corporate. *
 * CMPG_NAME string(24) �������� ������������� ����� 
 * CMP_NAME string(34) �������� ��������
 * CNT_E_MAILS string(100) ����� ����������� ����� ����������� ����
 * CNT_FAX string(15) ����� ����� ����������� ����
 * CNT_MOB_PHONE string(15) ��������� ������� ����������� ���� 
 * CNT_PHONE string(15) ���������� �������
 * CNT_TITLE string(2) ��� ������ (���������) ����������� ����
 * COMENT string(400) ����������� ������������
 * CONTACT string(25) ���, ������� ����������� ����
 * CO_CODE string(5) ��� �������������� �������
 * CO_POSITON string(26) ������������ �������������� �������
 * CR_CITY string(20) ����� �������� � �����
 * CR_CNTRY string(3) ����� �������� � ��� ������
 * CR_E_MAILS string(100) ����� ����������� ����� ��������
 * CR_PCODE string(7) ����� �������� � �������� ������
 * CR_STREET string(60) ����� �������� � �����, ���, ��������
 * CTIME dateTime ���� � ����� (�� ��������� sysdate) �������� ��� ����������
 * C_SINCE string(4) ������ ����� � ... (����)
 * DOC_SINCE dateTime ����������������� �������� �������� � 
 * DOC_TYPE string(3) ��� ������������������ ���������
 * EMP_ADR string(120) ����� ������������ �������
 * EMP_CODE string(5) ��� ������������ ������� 
 * EMP_DATE dateTime ���� ������������
 * EMP_E_MAILS string(100) ����� ����������� ����� ������������ �������
 * EMP_FAX string(15) ����� ����� ������������ �������
 * EMP_NAME string(34) ��� ������������ ������� 
 * F_NAMES string(34) ���
 * HOME_NUMBER string(15) �������� �����
 * ID_CARD string(16) ����� ������������������ ��������� ��������
 * IMM_PROP_VALUE Decimal(12,0) ��������� ������������
 * IN_FILE_NUM Decimal(22,0) ������������� ������� ������ (Batch task ID)
 * ISSUED_BY string(120) �������� ������������������ ��������� ��������
 * MARITAL_STATUS string(1) �������� ������ �������� ����
 * MIDLE_NAME string(20) ������ ���
 * MNG_E_MAILS string(100) ����� ����������� ����� ��������� 
 * MNG_FAX string(15) ����� ����� ���������
 * MNG_MOB_PHONE string(15) ����� ���������� �������� ���������
 * MNG_NAME string(25) ��� � ������� ���������
 * MNG_PHONE string(15) ������� ���������
 * MNG_POSIT string(25) ��������� ���������
 * MNG_TITLE string(2) ��� ������ (���������) ���������
 * M_NAME string(20) ������� ������� ��������� �����
 * PERSON_CODE string(20) ��� �������� (��� ����������� �������)
 * POSITION string(26) ��������� �������
 * REC_DATE dateTime ���� ������ ������ ��������
 * REGION string(2) ��� �������
 * REG_NR string(25) ��������������� ����� ��������
 * RESIDENT string(1) �������� ��� ����������: �1� � ��������; �2� � ����������. *
 * RESIDENT_SINCE dateTime �������� �
 * R_CITY string(20) ����� ���������� � �����
 * R_CNTRY string(3) ����� ���������� � ���
 * R_E_MAILS string(100) ����� ����������� �����
 * R_FAX string(15) ����� ���������� � ����� �����
 * R_MOB_PHONE string(15) ����� ���������� �������� �������
 * R_PCODE string(7) ����� ���������� � �������� ������
 * R_PHONE string(15) ����� ��������� �������� �������
 * R_STREET string(95) ����� ���������� � �����, ���, ��������
 * SEARCH_NAME string(55) ��� ��� ������ � ������� �������� ��� ������� 
 * SERIAL_NO string(15) �������� ����� ��������� ������������� ��������
 * SEX string(1) ��� ������� (1 � �������, 2 � �������)
 * STATUS string(2) ������ ������� <'3929' �������, ����� ������ *
 * STREET1 string(95) �����
 * SURNAME string(20) �������
 * TITLE string(2) ��� ������ (���������)
 * USRID string(6) ����������������� ����� ������������
 * U_COD1 string(3) ����������� ������������� code_1
 * U_COD2 string(6) ����������� ������������� code_2
 * U_COD3 string(6) ����������� ������������� code_3
 * U_FIELD1 string(20) ����������� ������������� field 1
 * U_FIELD2 string(20) ����������� ������������� field_2
 * WORK_PHONE string(15) ����� �������� �������� �������
 * YEAR_INC Decimal(12,0) ������� �����
 * AMEX_MEMBER_SINCE dateTime ����� ����� ������ �������� (null). ����, ����� ������ ������������� � AmEx .
 * DCI_MEMBER_SINCE dateTime ����, ����� ������ ������������� � Diners Club .
 * REWARD_NO Varchar2(10) ����� ����� ������� (Reward GlobuzAccount Number).
 * NATIONALITY string(3) �������������� (���).
 */
@NoArgsConstructor
@AllArgsConstructor
public class TietoCustomer {
	
	@Getter
	@Setter
	private String APARTMENT;
	@Getter
	@Setter
	private String BUILDING;
	@Getter
	@Setter
	private Date B_DATE;
	@Getter
	@Setter
	private String CALL_ID;
	@Getter
	@Setter
	private String CCY_FOR_INCOM;
	@Getter
	@Setter
	private String CLIENT;
	@Getter
	@Setter
	private String CLIENT_B;
	@Getter
	@Setter
	private String CLN_CAT;
	@Getter
	@Setter
	private String CL_TYPE;
	@Getter
	@Setter
	private String CMPG_NAME;
	@Getter
	@Setter
	private String CMP_NAME;
	@Getter
	@Setter
	private String CNT_E_MAILS;
	@Getter
	@Setter
	private String CNT_FAX;
	@Getter
	@Setter
	private String CNT_MOB_PHONE;
	@Getter
	@Setter
	private String CNT_PHONE;
	@Getter
	@Setter
	private String CNT_TITLE;
	@Getter
	@Setter
	private String COMENT;
	@Getter
	@Setter
	private String CONTACT;
	@Getter
	@Setter
	private String CO_CODE;
	@Getter
	@Setter
	private String CO_POSITON;
	@Getter
	@Setter
	private String CR_CITY;
	@Getter
	@Setter
	private String CR_CNTRY;
	@Getter
	@Setter
	private String CR_E_MAILS;
	@Getter
	@Setter
	private String CR_PCODE;
	@Getter
	@Setter
	private String CR_STREET;
	@Getter
	@Setter
	private Date CTIME;
	@Getter
	@Setter
	private String C_SINCE;
	@Getter
	@Setter
	private Date DOC_SINCE;
	@Getter
	@Setter
	private String DOC_TYPE;
	@Getter
	@Setter
	private String EMP_ADR;
	@Getter
	@Setter
	private String EMP_CODE;
	@Getter
	@Setter
	private Date EMP_DATE;
	@Getter
	@Setter
	private String EMP_E_MAILS;
	@Getter
	@Setter
	private String EMP_FAX;
	@Getter
	@Setter
	private String EMP_NAME;
	@Getter
	@Setter
	private String F_NAMES;
	@Getter
	@Setter
	private String HOME_NUMBER;
	@Getter
	@Setter
	private String ID_CARD;
	@Getter
	@Setter
	private BigDecimal IMM_PROP_VALUE;
	@Getter
	@Setter
	private BigDecimal IN_FILE_NUM;
	@Getter
	@Setter
	private String ISSUED_BY;
	@Getter
	@Setter
	private String MARITAL_STATUS;
	@Getter
	@Setter
	private String MIDLE_NAME;
	@Getter
	@Setter
	private String MNG_E_MAILS;
	@Getter
	@Setter
	private String MNG_FAX;
	@Getter
	@Setter
	private String MNG_MOB_PHONE;
	@Getter
	@Setter
	private String MNG_NAME;
	@Getter
	@Setter
	private String MNG_PHONE;
	@Getter
	@Setter
	private String MNG_POSIT;
	@Getter
	@Setter
	private String MNG_TITLE;
	@Getter
	@Setter
	private String M_NAME;
	@Getter
	@Setter
	private String PERSON_CODE;
	@Getter
	@Setter
	private String POSITION;
	@Getter
	@Setter
	private Date REC_DATE;
	@Getter
	@Setter
	private String REGION;
	@Getter
	@Setter
	private String REG_NR;
	@Getter
	@Setter
	private String RESIDENT;
	@Getter
	@Setter
	private Date RESIDENT_SINCE;
	@Getter
	@Setter
	private String R_CITY;
	@Getter
	@Setter
	private String R_CNTRY;
	@Getter
	@Setter
	private String R_E_MAILS;
	@Getter
	@Setter
	private String R_FAX;
	@Getter
	@Setter
	private String R_MOB_PHONE;
	@Getter
	@Setter
	private String R_PCODE;
	@Getter
	@Setter
	private String R_PHONE;
	@Getter
	@Setter
	private String R_STREET;
	@Getter
	@Setter
	private String SEARCH_NAME;
	@Getter
	@Setter
	private String SERIAL_NO;
	@Getter
	@Setter
	private String SEX;
	@Getter
	@Setter
	private String STATUS;
	@Getter
	@Setter
	private String STREET1;
	@Getter
	@Setter
	private String SURNAME;
	@Getter
	@Setter
	private String TITLE;
	@Getter
	@Setter
	private String USRID;
	@Getter
	@Setter
	private String U_COD1;
	@Getter
	@Setter
	private String U_COD2;
	@Getter
	@Setter
	private String U_COD3;
	@Getter
	@Setter
	private String U_FIELD1;
	@Getter
	@Setter
	private String U_FIELD2;
	@Getter
	@Setter
	private String WORK_PHONE;
	@Getter
	@Setter
	private BigDecimal YEAR_INC;
	@Getter
	@Setter
	private Date AMEX_MEMBER_SINCE;
	@Getter
	@Setter
	private Date DCI_MEMBER_SINCE;
	@Getter
	@Setter
	private String REWARD_NO;
	@Getter
	@Setter
	private String NATIONALITY;
	
	@Getter
	@Setter
	private List<Account> accounts = new ArrayList<Account>();
	
}
