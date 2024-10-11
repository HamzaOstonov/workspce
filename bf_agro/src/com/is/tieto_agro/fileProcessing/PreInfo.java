package com.is.tieto_agro.fileProcessing;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* branch			varchar2(5)		������
* v_date			date			���� ����������
* parent_group_id	number(6)		parent_group_id
* tietoAccount			char(20)		������� ����
* code_str			char(3)			��� ������
* vid_usl			varchar2(1)		��� ���� �����
* kod_pay			varchar2(1)		������� �������
* summa				number(20)		����� ����������
* kod_mcc			varchar2(4)		��� ���
* general_id		number(10)		general_id
* card_num			varchar2(16)	����� �����
* name_org_term		varchar2(100)	������������ �����������, ��� ���������� ��������
* code_term			varchar2(15)	��� ���������

 */
@NoArgsConstructor
@AllArgsConstructor
public class PreInfo {
	@Getter
	@Setter
	String branch;
	@Getter
	@Setter
	Date v_date;
	@Getter
	@Setter
	Long parent_group_id;
	@Getter
	@Setter
	String account;
	@Getter
	@Setter
	String code_str;
	@Getter
	@Setter
	String vid_usl;
	@Getter
	@Setter
	String kod_pay;
	@Getter
	@Setter
	Long summa;
	@Getter
	@Setter
	String kod_mcc;
	@Getter
	@Setter
	Long general_id;
	@Getter
	@Setter
	String card_num;
	@Getter
	@Setter
	String name_org_term;
	@Getter
	@Setter
	String code_term;

}
