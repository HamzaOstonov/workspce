package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CARD_ACCT	string(34)		����� ����� ����� *
 * TRANZ_ACCT	string(34)		����� ����� ��� ������������� � ���������� ������� *
 * STATUS		string(1)		������ ����� 0,3,4
 * ACC_PRTY		string(1)		��������� ����� 
 * C_ACCNT_TYPE	string(2)		��� ��� �����
 * CCY			string(3)		������ �����
 * COND_SET		string(3)		����� ���������� �������, ����� ��� ������ ������
 * CLIENT_B		string(19)		����� ������� � ����� *
 * CLIENT		string(8)		���������� ����� ��������� ����� *
 * F_NAMES		string(34)		��� *
 * SURNAME		string(20)		������� *
 * B_BR_ID		decimal(7,0)	���������� ����� ������� ����� *
 * OFFICE_ID	decimal(7,0)	���������� ����� ����� ������� ����� *
 * MAIN_ROW		decimal(20,0)	���������� ������������� ��� ���� ������, ��������� � ������ (���������), ����� ����� loyalty *
 * ACCOUNT_NO	decimal(20,0)	���������� ����� ����� *

 */
@NoArgsConstructor
@AllArgsConstructor
public class ListAccountsFilter {
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

}
