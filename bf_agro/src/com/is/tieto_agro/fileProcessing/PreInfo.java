package com.is.tieto_agro.fileProcessing;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* branch			varchar2(5)		Филиал
* v_date			date			Дата транзакции
* parent_group_id	number(6)		parent_group_id
* tietoAccount			char(20)		Лицевой счет
* code_str			char(3)			Код страны
* vid_usl			varchar2(1)		Код вида услуг
* kod_pay			varchar2(1)		Признак платежа
* summa				number(20)		Сумма транзакции
* kod_mcc			varchar2(4)		Код Мсс
* general_id		number(10)		general_id
* card_num			varchar2(16)	Номер карты
* name_org_term		varchar2(100)	Наименование организации, где установлен терминал
* code_term			varchar2(15)	Код терминала

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
