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
 * @author D
 * APARTMENT string(5) Номер квартиры
 * BUILDING string(15) Номер здания
 * CALL_ID string(10) Номер ссылки интерфейса
 * CCY_FOR_INCOM string(3) Валюта для годового дохода и имущества
 * CLIENT string(8) Клиентский номер держателя карты. 
 * CLIENT_B string(19) Номер клиента в банке
 * CLN_CAT string(3) Категория клиента
 * CL_TYPE string(1) Тип клиента: ‘1’ – Private; ‘2’ – Corporate. *
 * CMPG_NAME string(24) Название корпоративной карты 
 * CMP_NAME string(34) Название компании
 * CNT_E_MAILS string(100) Адрес электронной почты контактного лица
 * CNT_FAX string(15) Номер факса контактного лица
 * CNT_MOB_PHONE string(15) Мобильный телефон контактного лица 
 * CNT_PHONE string(15) Контактный телефон
 * CNT_TITLE string(2) Код звания (обращения) контактного лица
 * COMENT string(400) Комментарий пользователя
 * CONTACT string(25) Имя, фамилия контактного лица
 * CO_CODE string(5) Код корпоративного клиента
 * CO_POSITON string(26) Расположение корпоративного клиента
 * CR_CITY string(20) Адрес компании – город
 * CR_CNTRY string(3) Адрес компании – код страны
 * CR_E_MAILS string(100) Адрес электронной почты компании
 * CR_PCODE string(7) Адрес компании – почтовый индекс
 * CR_STREET string(60) Адрес компании – улица, дом, квартира
 * CTIME dateTime Дата и время (по умолчанию sysdate) внесения или обновления
 * C_SINCE string(4) Клиент банка с ... (дата)
 * DOC_SINCE dateTime Идентификационный документ личности с 
 * DOC_TYPE string(3) Код идентификационного документа
 * EMP_ADR string(120) Адрес работодателя клиента
 * EMP_CODE string(5) Код работодателя клиента 
 * EMP_DATE dateTime Дата работодателя
 * EMP_E_MAILS string(100) Адрес электронной почты работодателя клиента
 * EMP_FAX string(15) Номер факса работодателя клиента
 * EMP_NAME string(34) Имя работодателя клиента 
 * F_NAMES string(34) Имя
 * HOME_NUMBER string(15) Домашний номер
 * ID_CARD string(16) Номер идентификационного документа личности
 * IMM_PROP_VALUE Decimal(12,0) Стоимость недвижимости
 * IN_FILE_NUM Decimal(22,0) Идентификатор задания пакета (Batch task ID)
 * ISSUED_BY string(120) Издатель идентификационного документа личности
 * MARITAL_STATUS string(1) Семейный статус частного лица
 * MIDLE_NAME string(20) Второе имя
 * MNG_E_MAILS string(100) Адрес электронной почты менеджера 
 * MNG_FAX string(15) Номер факса менеджера
 * MNG_MOB_PHONE string(15) Номер мобильного телефона менеджера
 * MNG_NAME string(25) Имя и фамилия менеджера
 * MNG_PHONE string(15) Телефон менеджера
 * MNG_POSIT string(25) Должность менеджера
 * MNG_TITLE string(2) Код звания (обращения) менеджера
 * M_NAME string(20) Девичья фамилия держателя карты
 * PERSON_CODE string(20) Код личности (код плательщика налогов)
 * POSITION string(26) Должность клиента
 * REC_DATE dateTime Дата первой записи договора
 * REGION string(2) Код региона
 * REG_NR string(25) Регистрационный номер компании
 * RESIDENT string(1) Резидент или нерезидент: ‘1’ – резидент; ‘2’ – нерезидент. *
 * RESIDENT_SINCE dateTime Резидент с
 * R_CITY string(20) Адрес проживания – город
 * R_CNTRY string(3) Адрес проживания – код
 * R_E_MAILS string(100) Адрес электронной почты
 * R_FAX string(15) Адрес проживания – номер факса
 * R_MOB_PHONE string(15) Номер мобильного телефона клиента
 * R_PCODE string(7) Адрес проживания – почтовый индекс
 * R_PHONE string(15) Номер домашнего телефона клиента
 * R_STREET string(95) Адрес проживания – улица, дом, квартира
 * SEARCH_NAME string(55) Имя для поиска в верхнем регистре без кавычек 
 * SERIAL_NO string(15) Серийный номер документа идентификации личности
 * SEX string(1) Пол клиента (1 – мужской, 2 – женский)
 * STATUS string(2) Статус клиента <'3929' активен, иначе закрыт *
 * STREET1 string(95) Улица
 * SURNAME string(20) Фамилия
 * TITLE string(2) Код звания (обращения)
 * USRID string(6) Идентификационный номер пользователя
 * U_COD1 string(3) Заполненный пользователем code_1
 * U_COD2 string(6) Заполненный пользователем code_2
 * U_COD3 string(6) Заполненный пользователем code_3
 * U_FIELD1 string(20) Заполненный пользователем field 1
 * U_FIELD2 string(20) Заполненный пользователем field_2
 * WORK_PHONE string(15) Номер рабочего телефона клиента
 * YEAR_INC Decimal(12,0) Годовой доход
 * AMEX_MEMBER_SINCE dateTime Может иметь пустое значение (null). Дата, когда клиент присоединился к AmEx .
 * DCI_MEMBER_SINCE dateTime Дата, когда клиент присоединился к Diners Club .
 * REWARD_NO Varchar2(10) Номер счёта награды (Reward GlobuzAccount Number).
 * NATIONALITY string(3) Национальность (код).
 */
@NoArgsConstructor
@AllArgsConstructor
public class TietoCustomer {
	
	@Getter
	@Setter
	private String APARTMENT;
	@Getter
	@Setter
	private String BUILDING;
	@Getter
	@Setter
	private Date B_DATE;
	@Getter
	@Setter
	private String CALL_ID;
	@Getter
	@Setter
	private String CCY_FOR_INCOM;
	@Getter
	@Setter
	private String CLIENT;
	@Getter
	@Setter
	private String CLIENT_B;
	@Getter
	@Setter
	private String CLN_CAT;
	@Getter
	@Setter
	private String CL_TYPE;
	@Getter
	@Setter
	private String CMPG_NAME;
	@Getter
	@Setter
	private String CMP_NAME;
	@Getter
	@Setter
	private String CNT_E_MAILS;
	@Getter
	@Setter
	private String CNT_FAX;
	@Getter
	@Setter
	private String CNT_MOB_PHONE;
	@Getter
	@Setter
	private String CNT_PHONE;
	@Getter
	@Setter
	private String CNT_TITLE;
	@Getter
	@Setter
	private String COMENT;
	@Getter
	@Setter
	private String CONTACT;
	@Getter
	@Setter
	private String CO_CODE;
	@Getter
	@Setter
	private String CO_POSITON;
	@Getter
	@Setter
	private String CR_CITY;
	@Getter
	@Setter
	private String CR_CNTRY;
	@Getter
	@Setter
	private String CR_E_MAILS;
	@Getter
	@Setter
	private String CR_PCODE;
	@Getter
	@Setter
	private String CR_STREET;
	@Getter
	@Setter
	private Date CTIME;
	@Getter
	@Setter
	private String C_SINCE;
	@Getter
	@Setter
	private Date DOC_SINCE;
	@Getter
	@Setter
	private String DOC_TYPE;
	@Getter
	@Setter
	private String EMP_ADR;
	@Getter
	@Setter
	private String EMP_CODE;
	@Getter
	@Setter
	private Date EMP_DATE;
	@Getter
	@Setter
	private String EMP_E_MAILS;
	@Getter
	@Setter
	private String EMP_FAX;
	@Getter
	@Setter
	private String EMP_NAME;
	@Getter
	@Setter
	private String F_NAMES;
	@Getter
	@Setter
	private String HOME_NUMBER;
	@Getter
	@Setter
	private String ID_CARD;
	@Getter
	@Setter
	private BigDecimal IMM_PROP_VALUE;
	@Getter
	@Setter
	private BigDecimal IN_FILE_NUM;
	@Getter
	@Setter
	private String ISSUED_BY;
	@Getter
	@Setter
	private String MARITAL_STATUS;
	@Getter
	@Setter
	private String MIDLE_NAME;
	@Getter
	@Setter
	private String MNG_E_MAILS;
	@Getter
	@Setter
	private String MNG_FAX;
	@Getter
	@Setter
	private String MNG_MOB_PHONE;
	@Getter
	@Setter
	private String MNG_NAME;
	@Getter
	@Setter
	private String MNG_PHONE;
	@Getter
	@Setter
	private String MNG_POSIT;
	@Getter
	@Setter
	private String MNG_TITLE;
	@Getter
	@Setter
	private String M_NAME;
	@Getter
	@Setter
	private String PERSON_CODE;
	@Getter
	@Setter
	private String POSITION;
	@Getter
	@Setter
	private Date REC_DATE;
	@Getter
	@Setter
	private String REGION;
	@Getter
	@Setter
	private String REG_NR;
	@Getter
	@Setter
	private String RESIDENT;
	@Getter
	@Setter
	private Date RESIDENT_SINCE;
	@Getter
	@Setter
	private String R_CITY;
	@Getter
	@Setter
	private String R_CNTRY;
	@Getter
	@Setter
	private String R_E_MAILS;
	@Getter
	@Setter
	private String R_FAX;
	@Getter
	@Setter
	private String R_MOB_PHONE;
	@Getter
	@Setter
	private String R_PCODE;
	@Getter
	@Setter
	private String R_PHONE;
	@Getter
	@Setter
	private String R_STREET;
	@Getter
	@Setter
	private String SEARCH_NAME;
	@Getter
	@Setter
	private String SERIAL_NO;
	@Getter
	@Setter
	private String SEX;
	@Getter
	@Setter
	private String STATUS;
	@Getter
	@Setter
	private String STREET1;
	@Getter
	@Setter
	private String SURNAME;
	@Getter
	@Setter
	private String TITLE;
	@Getter
	@Setter
	private String USRID;
	@Getter
	@Setter
	private String U_COD1;
	@Getter
	@Setter
	private String U_COD2;
	@Getter
	@Setter
	private String U_COD3;
	@Getter
	@Setter
	private String U_FIELD1;
	@Getter
	@Setter
	private String U_FIELD2;
	@Getter
	@Setter
	private String WORK_PHONE;
	@Getter
	@Setter
	private BigDecimal YEAR_INC;
	@Getter
	@Setter
	private Date AMEX_MEMBER_SINCE;
	@Getter
	@Setter
	private Date DCI_MEMBER_SINCE;
	@Getter
	@Setter
	private String REWARD_NO;
	@Getter
	@Setter
	private String NATIONALITY;
	
	@Getter
	@Setter
	private List<Account> accounts = new ArrayList<Account>();
	
}
