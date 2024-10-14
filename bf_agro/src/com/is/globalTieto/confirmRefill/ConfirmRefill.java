package com.is.globalTieto.confirmRefill;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* ID				number 			Id
* CARD_ACCT 		varchar2(20)	����� ����� �����
* CARD				varchar2(19)	����� ����� (PAN)
* TRANSACTION_AMNT	number			����� ����������
* BRANCH			varchar2(64)	������ �����
* CLIENT_TIETO		varchar2(128)	���������� ����� ��������� �����
* CLIENT_BANK		varchar2(128)	����� ������� � �����
* STATE				number(2)		���������
* PAYMENT_MODE		varchar2(1)		��������� �������� PAYMENT_MODE ���������: 0,1,2,3
* BANK_C			varchar2(2)		��� ����� � ��������� ������
* GROUPC			varchar2(2)		��� ������ ���� � ��������� ��� ������� �����
* CARD_ACC_CCY		varchar2(3)		������ ����� (���������� ���)
* TRAN_TYPE			varchar2(3)		��� ����������
* TRAN_CCY			varchar2(3)		��� ������ ����������
* CLIENT_NAME		varchar2(34)	��� �������
* CLIENT_SURNAME	varchar2(20)	������� �������
 */

@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRefill {
	@Getter
	@Setter
	Long id;
	@Getter
	@Setter
	String card_acct;
	@Getter
	@Setter
	String card;
	@Getter
	@Setter
	BigDecimal transaction_amnt;
	@Getter
	@Setter
	String branch;
	@Getter
	@Setter
	String client_tieto;
	@Getter
	@Setter
	String client_bank;
	@Getter
	@Setter
	BigDecimal state;
	@Getter
	@Setter
	String payment_mode;
	@Getter
	@Setter
	String bank_c;
	@Getter
	@Setter
	String groupc;
	@Getter
	@Setter
	String card_acc_ccy;
	@Getter
	@Setter
	String tran_type;
	@Getter
	@Setter
	String tran_ccy;
	@Getter
	@Setter
	String client_name;
	@Getter
	@Setter
	String client_surname;
	@Getter
    @Setter
    Long is_cash_payment;
}
