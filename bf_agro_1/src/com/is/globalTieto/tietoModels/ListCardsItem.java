package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rush
* ACCOUNT_NO	decimal(20,0)	Внутренний номер счета
* CARD_ACCT		String(34)		Карточный счет (основной счёт)
* CCY			String(3)		Валюта счета (для основного счёта)
* CARD			String(19)		Номер карты (PAN)
* BASE_SUPP		String(1)		Основная (1) или дополнительная (2) карта
* STATUS		String(1)		Статус карты (0 – активна, 2 – закрыта, 1 – неактивна)
* STATUS2		String(1)		Статус второй карты (0 – активна, 2 – закрыта, 1 – неактивна)
* STOP_CAUSE	String(1)		Причина остановки карты
* EXPIRY		dateTime		Срок окончания действия карты
* EXPIRY2		dateTime		Срок окончания действия обнавленный карты
* COND_SET		String(3)		Набор условий карты
* RISK_LEVEL	String(5)		Уровень риска
* CLIENT_ID		String(8)		Внутренний идентификатор держателя карты
* CL_ROLE		String(1)		Роль держателя карты (1 – владелец счета, 2 – держатель карты)
* AGREEMENT_KEY	decimal(22,0)	Внутренний идентификатор договора
* CARD_NAME		String(27)		Имя на карте 


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
