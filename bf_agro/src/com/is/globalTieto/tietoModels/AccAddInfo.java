package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * CARD_NUMB Decimal(6,0) ���������� ����������� ����
 * IC_DATE dateTime ���� ���������� ��������
 * LOST_CARD Decimal(6,0) ���������� ���������� (����������) ����
 * POST_DATE dateTime ���� ��������� ��������
 * BEGIN_BAL Decimal(12,0) ��������� ������ �����
 * DEBIT Decimal(12,0) �������� ������
 * CREDIT Decimal(12,0) ��������� ������
 * END_BAL Decimal(12,0) ������� ������ �����
 * AVAIL_AMT Decimal(12,0) ��������� ������ �����
 * DEBT Decimal(12,0) ����� �������� ��� ������������� ������������ �������
 * DEBT1 Decimal(12,0) ����� ������������� �� ���������� �����
 * PREV_DATE dateTime ���� ���������� ��������
 * MBEGIN_BAL Decimal(12,0) ������ �� ������ ������
 * MDEBIT Decimal(12,0) �������� ������ �� �����
 * MCREDIT Decimal(12,0) ��������� ������ �� �����
 * MEND_BAL Decimal(12,0) ������ ����� � ����� ������
 * MPREV_BAL Decimal(12,0) ������ �� ������ ����������� ������
 * BRUTTO Decimal(12,0) ����� ����� ���������� �� �����
 * USED_AMOUNT Decimal(12,0) �����, ������������� � ������������
 * DBEGIN_BAL decimal(12,0) ��������� ������ ���
 * DDEBIT decimal(12,0) �������� ������ �� ����
 * DCREDIT decimal(12,0) ��������� ������ �� ����
 * DEND_BAL decimal(12,0) �������� ������ ���
 * DEP_CAP_DATE dateTime [���������� ����] ���� ��������� ������������� ���������
 * DEP_INT_CUR Decimal(21,9) [���������� ����] ������� ���������� ������
 * DEP_INT_LAST decimal(21,9) [���������� ����] ���������� ���������� ������
 * DEP_INT_TOT decimal(21,9) [���������� ����] ����� ��������� �� ������ � �� ����������� ������������� 
 * DEP_INT_GTOT decimal(21,9) [���������� ����] ����� �������� � ������� �������� �����
 * DEP_CUR_TRNSF decimal(21,9) [���������� ����] ��������� ������������������ ����� ���������
 * DEP_INT_TRNSF decimal(21,9) ����� ������������������ ����� ���������
 * DEP_LPER_DATE dateTime ���� ���������� ������� ���������
 * REVERS_SUM Decimal(12,0) ����� ��������� � ��������� ���� ��������
 * END_BAL1 decimal(12,0) ��������� �������� ������
 * PAYM_AMOUNT decimal(12,0) ����� ���������� �������
 * PAYM_DATE dateTime ���� ���������� �������
 * PAYM_INTERNAL_NO decimal(12,0) ���������� ����� ���������� ������� � ������� izd_slip
 * TA_OTB decimal(12,0) OTB, ���������� �� ������� ������������ ����� 
 * MPREV_DEBIT decimal(12,0) �������� ������ ����������� ������������ �����
 * MPREV_CREDIT decimal(12,0) ��������� ������ ����������� ������������ �����
 * PROC_ID decimal(14,0) ������������� �������� ���������� ������
 * CRD_CHANGE_DATE dateTime ���� ��������� ��������� ���������� ������
 */
@NoArgsConstructor
@AllArgsConstructor
public class AccAddInfo {

	@Getter
	@Setter
	private BigDecimal CARD_NUMB;
	@Getter
	@Setter
	private Date IC_DATE;
	@Getter
	@Setter
	private BigDecimal LOST_CARD;
	@Getter
	@Setter
	private Date POST_DATE;
	@Getter
	@Setter
	private BigDecimal BEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal DEBIT;
	@Getter
	@Setter
	private BigDecimal CREDIT;
	@Getter
	@Setter
	private BigDecimal END_BAL;
	@Getter
	@Setter
	private BigDecimal AVAIL_AMT;
	@Getter
	@Setter
	private BigDecimal DEBT;
	@Getter
	@Setter
	private BigDecimal DEBT1;
	@Getter
	@Setter
	private Date PREV_DATE;
	@Getter
	@Setter
	private BigDecimal MBEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal MDEBIT;
	@Getter
	@Setter
	private BigDecimal MCREDIT;
	@Getter
	@Setter
	private BigDecimal MEND_BAL;
	@Getter
	@Setter
	private BigDecimal MPREV_BAL;
	@Getter
	@Setter
	private BigDecimal BRUTTO;
	@Getter
	@Setter
	private BigDecimal USED_AMOUNT;
	@Getter
	@Setter
	private BigDecimal DBEGIN_BAL;
	@Getter
	@Setter
	private BigDecimal DDEBIT;
	@Getter
	@Setter
	private BigDecimal DCREDIT;
	@Getter
	@Setter
	private BigDecimal DEND_BAL;
	@Getter
	@Setter
	private Date DEP_CAP_DATE;
	@Getter
	@Setter
	private BigDecimal DEP_INT_CUR;
	@Getter
	@Setter
	private BigDecimal DEP_INT_LAST;
	@Getter
	@Setter
	private BigDecimal DEP_INT_TOT;
	@Getter
	@Setter
	private BigDecimal DEP_INT_GTOT;
	@Getter
	@Setter
	private BigDecimal DEP_CUR_TRNSF;
	@Getter
	@Setter
	private BigDecimal DEP_INT_TRNSF;
	@Getter
	@Setter
	private Date DEP_LPER_DATE;
	@Getter
	@Setter
	private BigDecimal REVERS_SUM;
	@Getter
	@Setter
	private BigDecimal END_BAL1;
	@Getter
	@Setter
	private BigDecimal PAYM_AMOUNT;
	@Getter
	@Setter
	private Date PAYM_DATE;
	@Getter
	@Setter
	private BigDecimal PAYM_INTERNAL_NO;
	@Getter
	@Setter
	private BigDecimal TA_OTB;
	@Getter
	@Setter
	private BigDecimal MPREV_DEBIT;
	@Getter
	@Setter
	private BigDecimal MPREV_CREDIT;
	@Getter
	@Setter
	private BigDecimal PROC_ID;
	@Getter
	@Setter
	private Date CRD_CHANGE_DATE;
	
}
