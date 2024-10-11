package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* CARD			String(19)		����� �����
* EXPIRY1		dateTime		���� ���������� ����� �������� �����
* CARD_STATUS	String(1)		������ �����
* ACCOUNT_NO	decimal(20,0)	���������� ����� �����
* CARD_ACCT		String(34)		����� ����� �����
* ACC_STATUS	String(1)		������ �����
* CCY			String(3)		������ ����� (���������� ����)
* END_BAL		decimal(12,0)	�������� ������ �����
* LOCKED_AMOUNT	decimal(22,0)	������������� ����� 
* AVAIL_AMOUNT	decimal(12,0)	��������� �����
* C_ACCNT_TYPE	String(2)		��� ����� (��������� �������� ���� � �������: 4.3. ACCOUNTS)
* STOP_CAUSE	String(1)		������� ��������� ����� (��������� �������� ��. � �������: 4.4. CARDS)
* MAIN_ROW		decimal(20,0)	������ �� ���� � ��������� �����������
* BANK_C		String(2)		��� ����� � ��������� ������
* GROUPC		String(2)		��� ������ ���� � ��������� ��� ������� �����
* CCY_EXP		string(1)		���������� ������ � ���������� ���������� ���� � ����� ������ �����
* CRD			decimal(12,0)	��������� ����� �����.
* CRD_EXPIRY	dateTime		���� ���������� ����� �������� �������. 
 */

@NoArgsConstructor
@AllArgsConstructor
public class CardBalance {
	@Getter
	@Setter
	private  String card;
	@Getter
	@Setter
	private  Date expiry1;
	@Getter
	@Setter
	private  String card_status;
	@Getter
	@Setter
	private  BigDecimal account_no;
	@Getter
	@Setter
	private  String card_acct;
	@Getter
	@Setter
	private  String acc_status;
	@Getter
	@Setter
	private  String ccy;
	@Getter
	@Setter
	private  BigDecimal end_bal;
	@Getter
	@Setter
	private  BigDecimal locked_amount;
	@Getter
	@Setter
	private  BigDecimal avail_amount;
	@Getter
	@Setter
	private  String c_accnt_type;
	@Getter
	@Setter
	private  String stop_cause;
	@Getter
	@Setter
	private  BigDecimal main_row;
	@Getter
	@Setter
	private  String bank_c;
	@Getter
	@Setter
	private  String groupc;
	@Getter
	@Setter
	private  String ccy_exp;
	@Getter
	@Setter
	private  BigDecimal crd;
	@Getter
	@Setter
	private  Date crd_expiry;

}
