package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
* ACCOUNT_NO	decimal(20,0)	���������� ����� �����
* CARD_ACCT		String(34)		��������� ���� (�������� ����)
* CCY			String(3)		������ ����� (��� ��������� �����)
* CARD			String(19)		����� ����� (PAN)
* BASE_SUPP		String(1)		�������� (1) ��� �������������� (2) �����
* STATUS		String(1)		������ ����� (0 � �������, 2 � �������, 1 � ���������)
* STATUS2		String(1)		������ ������ ����� (0 � �������, 2 � �������, 1 � ���������)
* STOP_CAUSE	String(1)		������� ��������� �����
* EXPIRY		dateTime		���� ��������� �������� �����
* EXPIRY2		dateTime		���� ��������� �������� ����������� �����
* COND_SET		String(3)		����� ������� �����
* RISK_LEVEL	String(5)		������� �����
* CLIENT_ID		String(8)		���������� ������������� ��������� �����
* CL_ROLE		String(1)		���� ��������� ����� (1 � �������� �����, 2 � ��������� �����)
* AGREEMENT_KEY	decimal(22,0)	���������� ������������� ��������
* CARD_NAME		String(27)		��� �� ����� 


 */
@NoArgsConstructor
@AllArgsConstructor
public class ListCardsItem {
	@Getter
	@Setter
	private  BigDecimal account_no;
	@Getter
	@Setter
	private  String card_acct;
	@Getter
	@Setter
	private  String ccy;
	@Getter
	@Setter
	private  String card;
	@Getter
	@Setter
	private  String base_supp;
	@Getter
	@Setter
	private  String status;
	@Getter
	@Setter
	private  String status2;
	@Getter
	@Setter
	private  String stop_cause;
	@Getter
	@Setter
	private  Date expiry;
	@Getter
	@Setter
	private  Date expiry2;
	@Getter
	@Setter
	private  String cond_set;
	@Getter
	@Setter
	private  String risk_level;
	@Getter
	@Setter
	private  String client_id;
	@Getter
	@Setter
	private  String cl_role;
	@Getter
	@Setter
	private  BigDecimal agreement_key;
	@Getter
	@Setter
	private  String card_name;


}
