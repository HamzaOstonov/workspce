package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CARD_ACCT	String(34)		����� ����� ����� *
 * TRANZ_ACCT	String(34)		����� ����� ��� ������������� �� ���������� �������
 * STATUS		String(1)		������ ����� 0,3,4 *
 * ACC_PRTY		String(1)		��������� �����
 * C_ACCNT_TYPE	String(2)		��� �����
 * CCY			String(3)		������ ����� (���������� ���) *
 * COND_SET		String(3)		������� �����
 * CLIENT_B		String(19)		��� �������
 * CLIENT		String(8)		���������� ����� ��������� �����
 * F_NAMES		String(34)		���
 * SURNAME		String(20)		�������
 * B_BR_ID		Decimal(7,0)	������������� �������
 * OFFICE_ID	Decimal(7,0)	������������� �����
 * MAIN_ROW		Decimal(20,0)	���������� ������������� ��� ���� ������, ��������� � ������ (���������), ����� ����� loyalty 
 * ACCOUNT_NO	Decimal(20,0)	���������� ����� �����
 * BANK_C		String(2)		��� ����� � ��������� ������
 * GROUPC		String(2)		��� ������ ���� � ��������� ��� ������� �����
 * CARD_ACCT_B	String(2)		����� ����� ����� � �����

 */
@NoArgsConstructor
@AllArgsConstructor
public class ListAccountsItem {
	@Getter
	@Setter
	private String card_acct;
	@Getter
	@Setter
	private String tranz_acct;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String acc_prty;
	@Getter
	@Setter
	private String c_accnt_type;
	@Getter
	@Setter
	private String ccy;
	@Getter
	@Setter
	private String cond_set;
	@Getter
	@Setter
	private String client_b;
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String f_names;
	@Getter
	@Setter
	private String surname;
	@Getter
	@Setter
	private BigDecimal b_br_id;
	@Getter
	@Setter
	private BigDecimal office_id;
	@Getter
	@Setter
	private BigDecimal main_row;
	@Getter
	@Setter
	private BigDecimal account_no;
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String groupc;

}
