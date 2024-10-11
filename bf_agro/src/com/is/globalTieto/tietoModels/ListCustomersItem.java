package com.is.globalTieto.tietoModels;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
 * CLIENT					string(8)	���������� ����� ��������� �����.
 * STATUS					string(2)	������ �������
 * CLIENT_B					string(19)	����� ������� � �����.
 * F_NAMES					string(34)	���.
 * SURNAME					string(20)	�������.
 * PERSON_CODE				string(20)	��� �������� (��� ����������� �������).
 * B_DATE					dateTime	���� �������� �������.
 * R_STREET					string(95)	����� ���������� � �����, ���, ��������.
 * R_CITY					string(20)	����� ���������� � �����.
 * R_CNTRY					string(3)	����� ���������� � ������.
 * R_PCODE					string(7)	����� ���������� � �������� ������.
 * R_E_MAILS				string(100)	����� ����������� �����.
 * R_MOB_PHONE				string(15)	����� ���������� ��������.
 * CARD						string(19)	����� ��������.
 * BANK_C					string(2)	��� �����.
 * U_COD1					string(3)	����������� ������������� ��� 1.
 * A3VTS_COUNTY				string(95)	�����.
 * A3VTS_FLAG1				string(1)	������ ������ �� Apartment number.
 * A3VTS_FLAG2				string(1)	������ ������ �� Apartment number.
 * SUPPLEMENTARY_PAN		string(20)	����������� ������������� ���� 1.
 * SUPPLEMENTARY_CVV2		string(20)	����������� ������������� ���� 2.
 * SUPPLEMENTARY_EXPIRY		dateTime	����, ����� ������ ����� ������ � ������������.
 * NOTES					string(400)	����������� ������������.
 * EMP_NAME					string(34)	�������� ������������ �������.

 */
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomersItem {
	@Getter
	@Setter
	private String client;
	@Getter
	@Setter
	private String status;
	@Getter
	@Setter
	private String client_b;
	@Getter
	@Setter
	private String f_names;
	@Getter
	@Setter
	private String surname;
	@Getter
	@Setter
	private String person_code;
	@Getter
	@Setter
	private Date b_date;
	@Getter
	@Setter
	private String r_street;
	@Getter
	@Setter
	private String r_city;
	@Getter
	@Setter
	private String r_cntry;
	@Getter
	@Setter
	private String r_pcode;
	@Getter
	@Setter
	private String r_e_mails;
	@Getter
	@Setter
	private String r_mob_phone;
	@Getter
	@Setter
	private String card;
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String u_cod1;
	@Getter
	@Setter
	private String a3vts_county;
	@Getter
	@Setter
	private String a3vts_flag1;
	@Getter
	@Setter
	private String a3vts_flag2;
	@Getter
	@Setter
	private String supplementary_pan;
	@Getter
	@Setter
	private String supplementary_cvv2;
	@Getter
	@Setter
	private Date supplementary_expiry;
	@Getter
	@Setter
	private String notes;
	@Getter
	@Setter
	private String emp_name;	
}
