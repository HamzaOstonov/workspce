package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * CARD string(19) Номер карты
 * CLIENT string(8) Клиентский номер держателя карты
 * CL_ROLE string(1) Роль клиента.
 * CARD_TYPE string(2) Тип карты – дебетная, кредитная и пр.
 * BASE_SUPP string(1) Базовая или дополнительная карта
 * COND_SET string(3) Набор условий карты
 * RISK_LEVEL string(5) Уровень риска
 * CARD_SERVICES_SET string(16) Код набора сервисов карты
 * REC_DATE datetime Дата регистрации карты
 * M_NAME string(20) Девичья фамилия держателя карты
 * RELATION string(25) Отношение между владельцами дополнительной и базовой карты
 * ID_CARD string(16) Номер документа идентификации личности
 * B_DATE datetime Дата рождения
 * CALL_ID string(10) Поле ссылки интерфейса
 * F_NAMES string(34) Имя
 * SURNAME string(20) Фамилия
 * F_NAME1 string(20) Имя
 * MIDLE_NAME string(20) Отчество
 * SERIAL_NO string(20) Серийный номер документа идентификации личности
 * DOC_SINCE datetime Дата издания документа
 * CMPG_NAME string(24) Название корпоративной карты или Branch для частного клиента 
 * CRD_HOLD_MSG string(99) Сообщение карта держателю
 * INSURANC_TYPE string(2) Тип страховки
 * INSURANC_DATE datetime Дата последнего страховочного платежа
 * U_COD9 string(3) Заполняемый пользователем код 9
 * U_COD10 string(6) Заполняемый пользователем код 10
 * U_FIELD7 string(20) Заполняемое пользователем поле 7
 * U_FIELD8 string(20) Заполняемое пользователем поле 8
 * IN_FILE_NUM Decimal(7,0) Идентификатор задания пакета (Batch task ID)
 * OUT_FILE_NUM Decimal(20,0) Эмбоссирование номера исходящего файла
 * USRID string(6) Идентификационный номер пользователя
 * CTIME datetime Дата и время (по умолчанию sysdate) последнего ввода или модификации 
 * EFFECTIVE_DATE1 dateTime Специальное поле для Amex – дата, когда карта становится действительна;
 * COND_SET_2 string(3) Уникальное значение (идентификация) нового набора условий (Condition set). 3 символа.
 * COND_CHANGE_DATE dateTime дата смены набора условий карточек.
 * CHANGE_BACK_DATE dateTime дата возврата предыдущего набора условий карточек.
 * BRANCH String(5) Код филиала.
 * U_FIELD11 string(20) Заполняемое пользователем поле 11
 * U_FIELD12 string(20) Заполняемое пользователем поле 12
 * U_FIELD13 string(20) Заполняемое пользователем поле 13
 * U_FIELD14 string(20) Заполняемое пользователем поле 14
 * NO_NAME String(1)
 * RANGE_ID Decimal(5,0) Идентификатор диапазона карточных номеров.
 */
@NoArgsConstructor
@AllArgsConstructor
public class LogicalCard {

	@Getter
	@Setter
	private String CARD;
	@Getter
	@Setter
	private String CLIENT;
	@Getter
	@Setter
	private String CL_ROLE;
	@Getter
	@Setter
	private String CARD_TYPE;
	@Getter
	@Setter
	private String BASE_SUPP;
	@Getter
	@Setter
	private String COND_SET;
	@Getter
	@Setter
	private String RISK_LEVEL;
	@Getter
	@Setter
	private String CARD_SERVICES_SET;
	@Getter
	@Setter
	private Date REC_DATE;
	@Getter
	@Setter
	private String M_NAME;
	@Getter
	@Setter
	private String RELATION;
	@Getter
	@Setter
	private String ID_CARD;
	@Getter
	@Setter
	private Date B_DATE;
	@Getter
	@Setter
	private String CALL_ID;
	@Getter
	@Setter
	private String F_NAMES;
	@Getter
	@Setter
	private String SURNAME;
	@Getter
	@Setter
	private String F_NAME1;
	@Getter
	@Setter
	private String MIDLE_NAME;
	@Getter
	@Setter
	private String SERIAL_NO;
	@Getter
	@Setter
	private Date DOC_SINCE;
	@Getter
	@Setter
	private String CMPG_NAME;
	@Getter
	@Setter
	private String CRD_HOLD_MSG;
	@Getter
	@Setter
	private String INSURANC_TYPE;
	@Getter
	@Setter
	private Date INSURANC_DATE;
	@Getter
	@Setter
	private String U_COD9;
	@Getter
	@Setter
	private String U_COD10;
	@Getter
	@Setter
	private String U_FIELD7;
	@Getter
	@Setter
	private String U_FIELD8;
	@Getter
	@Setter
	private BigDecimal IN_FILE_NUM;
	@Getter
	@Setter
	private BigDecimal OUT_FILE_NUM;
	@Getter
	@Setter
	private String USRID;
	@Getter
	@Setter
	private Date CTIME;
	@Getter
	@Setter
	private Date EFFECTIVE_DATE1;
	@Getter
	@Setter
	private String COND_SET_2;
	@Getter
	@Setter
	private Date COND_CHANGE_DATE;
	@Getter
	@Setter
	private Date CHANGE_BACK_DATE;
	@Getter
	@Setter
	private String BRANCH;
	@Getter
	@Setter
	private String U_FIELD11;
	@Getter
	@Setter
	private String U_FIELD12;
	@Getter
	@Setter
	private String U_FIELD13;
	@Getter
	@Setter
	private String U_FIELD14;
	@Getter
	@Setter
	private String NO_NAME;
	@Getter
	@Setter
	private BigDecimal RANGE_ID;
	
}
