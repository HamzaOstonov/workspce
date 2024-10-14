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
 * @author Rush
 * ID				decimal			Id
 * AGRE_NOM			decimal(22,0)	���������� ����� ��������
 * BINCOD			string(9)		BIN ���
 * BANK_CODE		string(2)		��� ����� � ��� ��������
 * BRANCH			string(5)		��� �������
 * B_BR_ID			decimal(22,0)	������������� ������� � ���������� �����
 * OFFICE			string(8)		�������� �����
 * OFFICE_ID		decimal(7,0)	��� �����
 * CITY				string(20)		�������� ����� � �����
 * CLIENT			string(8)		����� ������� � ����� *
 * COMENT			string(200)		����������� ������������
 * CONTRACT			string(15)		����� ��������.
 * COUNTRY			string(3)		�������� ����� � ������
 * CTIME			dateTime		���� � ����� (�� ��������� sysdate) ����� ��� ���������� 
 * DISTRIB_MODE		string(2)		Client Report Distribution Mode
 * ENROLLED			dateTime		���� ������ ������� � �������� *
 * E_MAILS			string(100)		����� ����������� ����� ��� ����������� ������ �������
 * IN_FILE_NUM		decimal(7,0)	������������� ������� ������ (Batch task ID)
 * ISURANCE_TYPE	string(2)		��� ���������
 * POST_IND			string(7)		�������� ����� � �������� ������
 * PRODUCT			string(10)		��� �������� *
 * REP_LANG			string(1)		���� ������
 * RISK_LEVEL		string(5)		������� �����
 * STATUS			string(2)		������ ��������
 * STREET			string(60)		�������� ����� � �����, ���, ��������
 * USRID			string(6)		����������������� ����� ������������
 * U_COD4			string(10)		����������� ������������� ��� 4
 * U_CODE5			string(6)		����������� ������������� ��� 5
 * U_CODE6			string(6)		����������� ������������� ��� 6
 * U_FIELD3			string(20)		����������� ������������� ���� 3
 * U_FIELD4			string(20)		����������� ������������� ���� 4
 * COMBI_ID			Decimal(10,0)	���� ������������ ������ ��� ������������� �������
 * COMBI_TYPE		String(20)		���� ������������ ������ ��� ������������� �������
 */
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private BigDecimal agre_nom;
	@Getter
	@Setter
	private String bincod;
	@Getter
	@Setter
	private String bank_code;
	@Getter
	@Setter
	private String branch;
	@Getter
	@Setter
	private BigDecimal b_br_id;
	@Getter
	@Setter
	private String office;
	@Getter
	@Setter
	private BigDecimal office_id;
	@Getter
	@Setter
	private String city;
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String coment;
	@Getter
	@Setter
	private String contract;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private Date ctime;
	@Getter
	@Setter
	private String distrib_mode;
	@Getter
	@Setter
	private Date enrolled;
	@Getter
	@Setter
	private String e_mails;
	@Getter
	@Setter
	private BigDecimal in_file_num;
	@Getter
	@Setter
	private String isurance_type;
	@Getter
	@Setter
	private String post_ind;
	@Getter
	@Setter
	private String product;
	@Getter
	@Setter
	private String rep_lang;
	@Getter
	@Setter
	private String risk_level;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String street;
	@Getter
	@Setter
	private String usrid;
	@Getter
	@Setter
	private String u_cod4;
	@Getter
	@Setter
	private String u_code5;
	@Getter
	@Setter
	private String u_code6;
	@Getter
	@Setter
	private String u_field3;
	@Getter
	@Setter
	private String u_field4;
	@Getter
	@Setter
	private BigDecimal combi_id;
	@Getter
	@Setter
	private String combi_type;
	@Getter
	@Setter
	private List<Account> accounts = new ArrayList<Account>();
}
