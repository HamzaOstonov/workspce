package com.is.customer_.local.validator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Created by root on 10.05.2017.
 * 16:09
 */

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;

/**
 * Class that controls UserInput
 */

public class CustomerValidator {
    private final static String BIOMETRIC = "6";
    private final static String OLD_PASSPORT = "1";

    private String message;
    private Customer customer;
    private SessionAttributes sessionAttributes;

    private CustomerValidator(Customer customer, SessionAttributes sessionAttributes) {
        this.customer = customer;
        this.sessionAttributes = sessionAttributes;
    }

    public static CustomerValidator getInstance(Customer customer, SessionAttributes sessionAttributes) {
        return new CustomerValidator(customer, sessionAttributes);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean validate() throws Exception {
        // Блок 1. Проверка на заполняемость обязательных полей
        if (CheckNull.isEmpty(customer.getP_type_document())) {
            message = "Введите тип документа";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_serial())) {
            message = "Введите серию документа";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_number())) {
            message = "Введите номер паспорта";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_code_nation())) {
            message = "Введите национальность";
            return false;
        }
        if (CheckNull.isEmpty(customer.getFullName())) {
            message = "Введите Ф.И.О";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_family_local())) {
            message = "Введите Фамилию(Поле 1)";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_first_name_local())) {
            message = "Введите Имя(Поле 1)";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_patronymic_local())) {
            message = "Введите Отчество(Поле 1)";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_family())) {
            message = "Введите Фамилию(Поле 2)";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_first_name())) {
            message = "Введите Имя(Поле 2)";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_code_citizenship())) {
            message = "Введите Корректный Код Гражданства";
            return false;
        }
        if (CheckNull.isEmpty(customer.getCode_resident())) {
            message = "Введите Код Резидентности";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_birthday())) {
            message = "Введите Дату Рождения";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_date_registration())) {
            message = "Введите Дату Регистрации Паспорта";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_code_gender())) {
            message = "Введите Пол";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_birth_place())) {
            message = "Введите Место Рождения";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_place_registration())) {
            message = "Введите Место Регистрации Документа";
            return false;
        }
        if (CheckNull.isEmpty(customer.getCode_country())) {
            message = "Введите код страны местожительства";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_code_adr_region())) {
            if (customer.getCode_country().equals("860")) {
                message = "Введите корректный регион местожительства";
                //message = customer.getP_code_adr_region();
                return false;
            }
        }
        if (CheckNull.isEmpty(customer.getP_code_adr_distr())) {
            if (customer.getCode_country().equals("860")) {
                message = "Введите район местожительства";
                return false;
            }
        }
        /*if (CheckNull.isEmpty(customer.getP_post_address_street())) {
            if (!customer.getP_type_document().equals("4")) {
                message = "Введите улицу местожительства";
                return false;
            }
        }
        if (CheckNull.isEmpty(customer.getP_post_address_house())) {
            if (!customer.getP_type_document().equals("4")) {
                message = "Введите дом местожительства";
                return false;
            }
        }*/
        if (CheckNull.isEmpty(customer.getP_post_address())) {
            message = "Введите корректный адрес";
            return false;
        }

        if (CheckNull.isEmpty(customer.getP_code_capacity())) {
            message = "Введите код трудоспособности";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_date_expiration())) {
            /*if (customer.getP_type_document().equals("1")) {
                DateTime birthday = new DateTime(customer.getP_birthday());
                DateTime registration = new DateTime(customer.getP_passport_date_registration());
                registration = registration.plusDays(1);
                Period period = new Period(birthday, registration);
                if (period.getYears() < 45)
                    if (CheckNull.isEmpty(customer.getP_passport_date_expiration())) {
                        message = "Введите дату действительности паспорта";
                        return false;
                    }
            } else {
                message = "Введите дату действительности паспорта";
                return false;
            }*/
            //message = "Введите дату действительности паспорта";
            //return false;
        	if (customer.getP_type_document().equals("6"))
        	{
        		message = "Введите дату действительности паспорта";
                return false;
        	}
        }

        // Блок 2. Проверка на корректность ввода Ф.И.О
        if (!isLetter(customer.getP_family_local())) {
            message = "Поле 1 - Фамилия - Только Буквы";
            return false;
        }
        if (!isLetter(customer.getP_first_name_local())) {
            message = "Поле 1 - Имя - Только Буквы";
            return false;
        }
        if (!isLetter(customer.getP_patronymic_local())) {
            message = "Поле 1 - Отчество - Только Буквы";
            return false;
        }
        if (!isLatin(customer.getP_family())) {
            message = "Поле 2 - Фамилия - Только латинские буквы";
            return false;
        }
        if (!isLatin(customer.getP_first_name())) {
            message = "Поле 2 - Имя - Только Латинские буквы";
            return false;
        }
        if (!isLatin(customer.getP_patronymic())) {
            message = "Поле 2 - Отчество - Только Латинские буквы";
            return false;
        }
        // Проверка на корректность ввода паспортных данных
        if (!isPSerialCorrect(customer.getP_passport_serial())) {
            message = "Некорректно введена серия паспорта";
            return false;
        }
        if (!isPNumberCorrect(customer.getP_passport_number())) {
            message = "Некорректно введен номер паспорта";
            return false;
        }
        // Только для биометрического
        if (customer.getP_type_document().equals("6")) {
            Date d1 = CustomerUtils.stringToDate("01.01.2011");
            Date d2 = customer.getP_passport_date_registration();

            if (d2.getTime() < d1.getTime()) {
                message = "Не верный тип документа – дата выдачи документа не может быть раньше 01.01.2011г";
                return false;
            }
        }
        if (!isExpirationDateCorrect()) {
            message = "Не верный тип документа – срок действия документа не соответствует выбранному типу документа";
            return false;
        }
        if (!isInfoDateCorrect(customer.getP_passport_date_registration())) {
            message = "Дата выдачи документа не может быть больше банковской даты";
            return false;
        }
        if (!isInfoDateCorrect(customer.getP_birthday())) {
            message = "Дата Рождения не может быть больше банковской даты";
            return false;
        }
        if (!isBirthDateCorrect(customer.getP_birthday())) {
            message = "Дата Рождения не должна быть раньше 01.01.1910 года";
            return false;
        }
        if (!(isRegistrationDateGreaterThanBirthday(customer.getP_passport_date_registration(),
                customer.getP_birthday()))) {
            message = "Дата Регистрации Документа должна быть больше дня рождения";
            return false;
        }
        if (!isRegistrationDateCorrect(
                customer.getP_passport_date_registration(), customer.getP_type_document())) {
            message = "Даты выдачи паспорта не должна быть раньше 01.01.1995";
            return false;
        }

        // 19.09.2018 - Nurlan
        /*if (!isPReceiveDateCorrect(customer.getP_birthday(),
                customer.getP_passport_date_registration())) {
            message = "Разность между днем рождения и датой регистрации документа должна быть больше 16 лет";
            return false;
        }*/


        // Если тип документа - Свидетельство о Рождении
        if (customer.getP_type_document().equals("7")) {
            Date date = CustomerUtils.getOperDate(sessionAttributes);
            Date birthday = customer.getP_birthday();
            DateTime d1 = new DateTime(birthday);
            DateTime d2 = new DateTime(date);
            Period period = new Period(d1, d2);
            if (Math.abs(period.getYears()) > 16) {
                message = "Не верный тип документа лицу старше 16 лет";
                return false;
            }

        }
        // Проверка на корректность ввода контактных данных
        if (!CheckNull.isEmpty(customer.getP_phone_home())) {
            if (!(isPhoneCorrect(customer.getP_phone_home()))) {
                message = "Номер Телефона должен состоять из 12 цифр(Домашний)";
                return false;
            }
            if (customer.getP_phone_home().startsWith("0")) {
                message = "Номер телефона не может начинаться с 0";
                return false;
            }
        }
        if (!CheckNull.isEmpty(customer.getP_phone_mobile())) {
            if (!(isPhoneCorrect(customer.getP_phone_mobile()))) {
                message = "Номер телефона должен состоять из 12 цифр(Мобильный)";
                return false;
            }
            if (customer.getP_phone_mobile().startsWith("0")) {
                message = "Номер телефона не может начинаться с 0";
                return false;
            }
        }

        if (!isEmailAddressCorrect()) {
            message = "Введите корректный e-mail address";
            return false;
        }

        // Проверка на корректность ИНН
        /*if ((businessPartner.getState() == null || businessPartner.getState().isEmpty()
                || businessPartner.getState().equals("0"))
                && isTaxNumberExists(businessPartner.getP_number_tax_registration())) {
            message = "Такой Инн уже существует";
            return false;
        }*/
        if (!isTaxNumberCorrect(customer.getP_number_tax_registration())) {
            message = "Инн должен состоять из 9 цифр";
            return false;
        }
        if (!checkTaxNumberDigits(customer.getP_number_tax_registration())) {
            message = "У физического лица инн должен начинаться с 4,5 или 6";
            return false;
        }
        if (!checkTaxNumberConsecutive(customer.getP_number_tax_registration())) {
            message = "Инн должен содержать не более 8 одинаковых цифр";
            return false;
        }
        if (!CheckNull.isEmpty(customer.getP_number_tax_registration())) {
            if (CheckNull.isEmpty(customer.getP_code_tax_org())) {
                message = "Введите код налоговой";
                return false;
            }
        }
        // Проверка на соответствие полей типу документа
        if (!isForeignCitizenResident()) {
            message = "Инностраный гражданин не может быть резидентом";
            return false;
        }
        if (!isForeignCitizenOfUzbekistan()) {
            message = "Иностранный гражданин не может быть гражданином Узбекистана";
            return false;
        }
        if (!isResidentTypeDocCorrect()) {
            message = "Несоответвие между типом документа и резиденством";
            return false;
        }
        if (customer.getP_type_document().equals("6")) {
            if (!customer.getP_code_citizenship().equals("860")) {
                message = "Несоотвествие между типом документа и гражданством";
                return false;
            }
        }
        // Проверка документа - вид на жительство
        if (customer.getP_type_document().equals("5") && customer.getP_code_citizenship().equals("860")) {
            message = "Несоотвествие между типом документа вид на жительство и гражданством";
            return false;
        }
        if (customer.getP_type_document().equals("5") && customer.getCode_resident().equals("2")) {
            message = "Несоотвествие между типом документа вид на жительство и резиденством";
            return false;
        }
        // Проверка на обязательность полей по статусу трудоспособности
        if (!CheckNull.isEmpty(customer.getP_code_capacity())) {
            if (customer.getP_code_capacity().equals("0803")
                    || customer.getP_code_capacity().equals("0804")) {
                if (CheckNull.isEmpty(customer.getP_num_certif_capacity())) {
                    message = "Введите номер удостоверения неработоспособности";
                    return false;
                }
                if (CheckNull.isEmpty(customer.getP_pension_sertif_serial())) {
                    message = "Введите серию неработоспособности";
                    return false;
                }
                if (CheckNull.isEmpty(customer.getP_capacity_status_date())) {
                    message = "Введите дату выдачи статуса неработоспособнсти.";
                    return false;
                }
                if (CheckNull.isEmpty(customer.getP_capacity_status_place())) {
                    message = "Введите место выдачи статуса неработоспособности.";
                    return false;
                }

                if (customer.getP_capacity_status_date().getTime()
                        > CustomerUtils.getOperDate(sessionAttributes).getTime()) {
                    message = "Дата статуса трудоспособности не может быть больше даты опер дня";
                    return false;
                }
            }

        }

        // Проверка на корректность ИНПС, только при биометрическом паспорте
        if ((customer.getP_type_document().equals("1") || customer.getP_type_document().equals("6"))
                && (!CheckNull.isEmpty(customer.getP_inps()))) {

            if (!isInpsCorrect(customer.getP_inps())) {
                message = "ИНПС должен состоять из 14 знаков";
                return false;
            }
            /*if (businessPartner.getP_code_gender().equals("1")) {
                if (!businessPartner.getP_inps().startsWith("3")) {
                    message = "У мужчин ИНПС начинается с 3";
                    return false;
                }
            }
            if (businessPartner.getP_code_gender().equals("2")) {
                if (!businessPartner.getP_inps().startsWith("4")) {
                    message = "У женщин ИНПС начинается с 4";
                    return false;
                }
            }*/
            if (!inpsDateCorrect(customer.getP_inps() )) {
                message = "Дата Рождения не соотвествует ИНПС";
                return false;
            }
            
        }
        if (customer.isDocumentDateRegistrationValid(CustomerUtils.getOperDate(sessionAttributes))) {
            message = "Дата регистрации документа не может быть больше опер дня";
            return false;
        }
        if (!customer.getCode_country().equals("860")) {
            if (!StringUtils.isEmpty(customer.getP_code_adr_region())
                    || !StringUtils.isEmpty(customer.getP_code_adr_distr())) {
                message = "Некорректный адрес страны";
                return false;
            }
        }
        //ПИНФЛ объязателность
    	if (customer.getP_type_document().equals("1") || customer.getP_type_document().equals("6"))
    	{
    		if (customer.getId()==null ) { //только для новых 
    		  if (CheckNull.isEmpty(customer.getP_pinfl())) {
    		    message = "Введите ПИНФЛ";
                return false;
    		  } else {
    			
                if (!isInpsCorrect(customer.getP_pinfl())) {
                    message = "ПИНФЛ должен состоять из 14 знаков";
                    return false;
                }
                if (!inpsDateCorrect(customer.getP_pinfl() )) {
                    message = "Дата Рождения не соотвествует ПИНФЛ";
                    return false;
                }
    		  }
    		}
    	}
        // dobavim usloviya, chtoby po 2 raza ne proveryalsa, cherez doaction toje est proverki. 
    	// direktor/buxgalter/uchreditel_fizicheskiy proveryaetsa cherez doaction-reaksiya.
    	// if (customer.getParent_id_client_j()==null /*esli Parent_id_client_j ne pustoy to eto klient direktor/buxgalter/uchreditel_fizicheskiy yurlisa*/) {
    	/* elyor20221214*/
    	if(CustomerUtils.isAtaccamaOn()) {
    		try {
    			passedStopListAtaccama();
    		} catch (Exception e) {
    			message = e.getMessage();
    			return false;
    		}
    	}
    	//}
        try {
            passedStopList();
        } catch (Exception e) {
            message = e.getMessage();
            return false;
        }
        /*
		 * // Проверка на существуемость клиента if (!checkForIndividualPartner)
		 * if (businessPartner.getState() == 0 &&
		 * isClientExists(businessPartner.getP_passport_serial(),
		 * businessPartner.getP_passport_number())) { message =
		 * "Такой клиент уже существует"; return false; }
		 */
        return true;
    }

    private boolean passedStopList() throws Exception {
        Connection c = null;
        boolean flag = true;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            CallableStatement callableStatement = c.prepareCall("{call stoplist.reaction(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, customer.getBranch());
            callableStatement.setString(2, customer.getId());
            callableStatement.setString(3, customer.getFullName());
            callableStatement.setString(4, customer.getP_passport_serial());
            callableStatement.setString(5, customer.getP_passport_number());
            callableStatement.setString(6, customer.getP_type_document());
            callableStatement.setString(7, customer.getP_family());
            callableStatement.setString(8, customer.getP_first_name());
            callableStatement.setString(9, customer.getP_patronymic());
            callableStatement.execute();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        } finally {
            ConnectionPool.close(c);
        }
        return flag;
    }

    private boolean passedStopListAtaccama() throws Exception {
        Connection c = null;
        CallableStatement callableStatement=null;
        CallableStatement setParam=null;
        CallableStatement clearParam = null;
        boolean flag = true;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call proc_ataccama()}");

            clearParam = c.prepareCall("{ call Param.clearparam() }");
            clearParam.execute();

            setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
            if (customer.getLongId() != null) {
                setParam.setString(1, "ID");
                setParam.setLong(2, customer.getLongId());
                setParam.execute();
            }
            if (customer.getId() != null) {
                setParam.setString(1, "ID_CLIENT");
                setParam.setString(2, customer.getId());
                setParam.execute();
            }

            setParam.setString(1, "BRANCH");
            setParam.setString(2, customer.getBranch());
            setParam.execute();

            setParam.setString(1, "NAME");
            setParam.setString(2, customer.getFullName().toUpperCase());
            setParam.execute();
            if (customer.getP_type_document() != null) {
                setParam.setString(1, "P_TYPE_DOCUMENT");
                setParam.setString(2, customer.getP_type_document().toUpperCase());
            }
            setParam.execute();
            setParam.setString(1, "CODE_COUNTRY");
            setParam.setString(2, customer.getCode_country());
            setParam.execute();
            setParam.setString(1, "CODE_TYPE");
            setParam.setString(2, customer.getCode_type());
            setParam.execute();
            setParam.setString(1, "CODE_RESIDENT");
            setParam.setString(2, customer.getCode_resident());
            setParam.execute();
            setParam.setString(1, "CODE_SUBJECT");
            setParam.setString(2, customer.getCode_subject());
            setParam.execute();
            setParam.setString(1, "SIGN_REGISTR");
            setParam.setString(2, customer.getSign_registr() + "");
            setParam.execute();
            setParam.setString(1, "CODE_FORM");
            setParam.setString(2, customer.getCode_form());
            setParam.execute();
            setParam.setString(1, "DATE_OPEN");
            setParam.setString(2, CustomerUtils.dateToString(customer.getDate_open()));
            setParam.execute();
            setParam.setString(1, "DATE_CLOSE");
            setParam.setString(2, CustomerUtils.dateToString(customer.getDate_close()));
            setParam.execute();
            if (!(customer.getState() == null || customer.getState().isEmpty())) {
                setParam.setString(1, "STATE");
                setParam.setInt(2, Integer.parseInt(customer.getState()));
            }
            setParam.execute();
            setParam.setString(1, "P_BIRTHDAY");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_birthday()));
            setParam.execute();
            setParam.setString(1, "P_POST_ADDRESS");
            setParam.setString(2, customer.getP_post_address() != null ? customer.getP_post_address().toUpperCase() : customer.getP_post_address());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_TYPE");
            setParam.setString(2, customer.getP_passport_type());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_SERIAL");
            setParam.setString(2, customer.getP_passport_serial() != null ?
            		customer.getP_passport_serial().toUpperCase() : customer.getP_passport_serial());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_NUMBER");
            setParam.setString(2, customer.getP_passport_number());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
            setParam.setString(2, customer.getP_passport_place_registration() != null ? customer.getP_passport_place_registration().toUpperCase() : customer.getP_passport_place_registration());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_DATE_REGISTRATION");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_passport_date_registration()));
            setParam.execute();
            setParam.setString(1, "P_CODE_TAX_ORG");
            setParam.setString(2, customer.getP_code_tax_org());
            setParam.execute();
            setParam.setString(1, "P_NUMBER_TAX_REGISTRATION");
            setParam.setString(2, customer.getP_number_tax_registration());
            setParam.execute();
            setParam.setString(1, "P_CODE_BANK");
            setParam.setString(2, customer.getP_code_bank());
            setParam.execute();
            setParam.setString(1, "P_CODE_CLASS_CREDIT");
            setParam.setString(2, customer.getP_code_class_credit());
            setParam.execute();
            setParam.setString(1, "P_CODE_CITIZENSHIP");
            setParam.setString(2, customer.getP_code_citizenship());
            setParam.execute();
            setParam.setString(1, "P_BIRTH_PLACE");
            setParam.setString(2, customer.getP_birth_place() != null ? customer.getP_birth_place().toUpperCase() : customer.getP_birth_place());
            setParam.execute();
            setParam.setString(1, "P_CODE_CAPACITY");
            setParam.setString(2, customer.getP_code_capacity());
            setParam.execute();
            setParam.setString(1, "P_CAPACITY_STATUS_DATE");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_capacity_status_date()));
            setParam.execute();
            setParam.setString(1, "P_CAPACITY_STATUS_PLACE");
            setParam.setString(2, customer.getP_capacity_status_place() != null ? customer.getP_capacity_status_place().toUpperCase() : customer.getP_capacity_status_place());
            setParam.execute();
            setParam.setString(1, "P_NUM_CERTIF_CAPACITY");
            setParam.setString(2, customer.getP_num_certif_capacity());
            setParam.execute();
            setParam.setString(1, "P_PHONE_HOME");
            setParam.setString(2, customer.getP_phone_home());
            setParam.execute();
            setParam.setString(1, "P_PHONE_MOBILE");
            setParam.setString(2, customer.getP_phone_mobile());
            setParam.execute();
            setParam.setString(1, "P_EMAIL_ADDRESS");
            setParam.setString(2, customer.getP_email_address() != null ?
            		customer.getP_email_address().toLowerCase() : customer.getP_email_address());
            setParam.execute();
            setParam.setString(1, "P_PENSION_SERTIF_SERIAL");
            setParam.setString(2, customer.getP_pension_sertif_serial() != null ? customer.getP_pension_sertif_serial()
                    .toUpperCase() : customer.getP_pension_sertif_serial());
            setParam.execute();
            setParam.setString(1, "P_CODE_GENDER");
            setParam.setString(2, customer.getP_code_gender());
            setParam.execute();
            setParam.setString(1, "P_CODE_NATION");
            setParam.setString(2, customer.getP_code_nation());
            setParam.execute();
            setParam.setString(1, "P_CODE_BIRTH_REGION");
            setParam.setString(2, customer.getP_code_birth_region());
            setParam.execute();
            setParam.setString(1, "P_CODE_BIRTH_DISTR");
            setParam.setString(2, customer.getP_code_birth_distr());
            setParam.execute();
            setParam.setString(1, "P_TYPE_DOCUMENT");
            setParam.setString(2, customer.getP_type_document());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_DATE_EXPIRATION");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_passport_date_expiration()));
            setParam.execute();
            setParam.setString(1, "P_CODE_ADR_REGION");
            setParam.setString(2, customer.getP_code_adr_region());
            setParam.execute();
            setParam.setString(1, "P_CODE_ADR_DISTR");
            setParam.setString(2, customer.getP_code_adr_distr());
            setParam.execute();
            setParam.setString(1, "P_INPS");
            setParam.setString(2, customer.getP_inps());
            setParam.execute();
            setParam.setString(1, "P_PINFL");
            setParam.setString(2, customer.getP_pinfl());
            setParam.execute();
            setParam.setString(1, "P_FAMILY");
            setParam.setString(2, customer.getP_family() != null ? customer.getP_family().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_FIRST_NAME");
            setParam.setString(2, customer.getP_first_name() != null ? customer.getP_first_name().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PATRONYMIC");
            setParam.setString(2, (customer.getP_patronymic() != null) ? customer.getP_patronymic().toUpperCase() : null);
            setParam.execute();
            
            setParam.setString(1, "P_DRIVE_PERMIT_SER");
            setParam.setString(2, customer.getP_drive_permit_ser());
            setParam.execute();
            setParam.setString(1, "P_DRIVE_PERMIT_NUM");
            setParam.setString(2, customer.getP_drive_permit_num());
            setParam.execute();
            setParam.setString(1, "P_DRIVE_PERMIT_REG_D");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_drive_permit_reg_d()));
            setParam.execute();
            setParam.setString(1, "P_DRIVE_PERMIT_EXP_D");
            setParam.setString(2, CustomerUtils.dateToString(customer.getP_drive_permit_exp_d()));
            setParam.execute();
            setParam.setString(1, "P_DRIVE_PERMIT_PLACE");
            setParam.setString(2, customer.getP_drive_permit_place());
            setParam.execute();
            setParam.setString(1, "P_AGREEMENT");
            setParam.setString(2, customer.getP_agreement());
            setParam.execute();

            /*2022.03.02*/
            setParam.setString(1, "P_FAMILY_LOCAL");
            setParam.setString(2, customer.getP_family_local() != null ? customer.getP_family_local().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_FIRST_NAME_LOCAL");
            setParam.setString(2, customer.getP_first_name_local() != null ? customer.getP_first_name_local().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PATRONYMIC_LOCAL");
            setParam.setString(2, (customer.getP_patronymic_local() != null) ? customer.getP_patronymic_local().toUpperCase() : null);
            setParam.execute();
            setParam.setString(1, "EMP_ID");
            setParam.setString(2, (customer.getEmp_id() != null) ? customer.getEmp_id() : null);
            setParam.execute();
            setParam.setString(1, "GROUP_ID");
            setParam.setString(2, "1"); //2022.08.25
            setParam.execute();
            setParam.setString(1, "S_DEAL_ID");
            setParam.setString(2, "2"); //2022.08.25
            setParam.execute();
            setParam.setString(1, "PARENT_ID_CLIENT_J");
            setParam.setString(2, (customer.getParent_id_client_j() != null) ? customer.getParent_id_client_j() : null); //2022.11.22
            setParam.execute();
            setParam.setString(1, "PERSON_ROLE");
            setParam.setString(2, (customer.getPerson_role() != null) ? customer.getPerson_role() : null); //2022.12.28
            setParam.execute();
            
            /*end 2022.03.02*/

            callableStatement.execute();
            c.commit();
        } catch (Exception e) {
            c.rollback();
            throw e;
        } finally {
        	clearParam.close();
            setParam.close();
            callableStatement.close();
            ConnectionPool.close(c);
        }
        return flag;
    }

    // Допускает ввод специального символа ', а также символа 'пробел' и 'дефис'
    private Boolean isLetter(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i)))
                if (text.charAt(i) != 32 && text.charAt(i) != 39 && text.charAt(i) != 45)
                    return false;
        }
        return true;
    }

    // Разрешает только латинские буквы при вводе типа документа - паспорт
    public Boolean isLatin(String str) {
        if (CheckNull.isEmpty(str))
            return true;
        if (customer.getP_type_document().equals("1") || customer.getP_type_document().equals("4")
                || customer.getP_type_document().equals("6"))
            return str.matches("[A-Za-z\\s\\-']+");
        return true;
    }

    /**
     * Если тип документа, 1 или 6, то серия паспорта состоит из 2 латинских
     * букв
     *
     * @param str
     * @return
     */
    public Boolean isPSerialCorrect(String str) {
        if (CheckNull.isEmpty(str))
            return false;
        if (customer.getP_type_document().equals("1") || customer.getP_type_document().equals("6")) {
            if (str.length() == 2 && isAlpha(str))
                return true;
            return false;
        }
        return true;
    }

    /**
     * Снимает контроль на проверку номера паспорта, если тип документа не равен
     * 1 или 6
     *
     * @param str
     * @return
     */
    public Boolean isPNumberCorrect(String str) {
        if (CheckNull.isEmpty(str))
            return false;
        if (customer.getP_type_document().equals("1") || customer.getP_type_document().equals("6")) {
            if (str.length() == 7 && isDigit(str))
                return true;
            return false;
        }
        if (str.length() > 9)
            return false;

        /*Pattern pattern = Pattern.compile("([\\d])\\1\\1\\1\\1\\1");
        pattern = Pattern.compile("^([0-9])\\1{6,}$");
        Matcher m = pattern.matcher(str);*/
        return true;
    }

    /**
     * При типе документе 6, разница между датой действительности и датой
     * получения докумета не больше 10 лет
     *
     * @param registration
     * @param expiration
     * @return
     */
    public Boolean isExpirationDateCorrect() {
        Date registration = customer.getP_passport_date_registration();
        Date expiration = customer.getP_passport_date_expiration();

        if (customer.getP_type_document().equals("6")) {
            if (registration != null && expiration != null) {
                Calendar cal = Calendar.getInstance();
                Date f = registration;
                cal.setTime(f);
                cal.add(Calendar.YEAR, 10);
                if (cal.getTime().getTime() <= expiration.getTime())
                    return false;
                else
                    return true;
            }
        }
        return true;

    }

    /**
     * Проверка на соотвествие опер дня и дня рождения
     *
     * @param birthday
     * @return
     */
    public Boolean isInfoDateCorrect(Date birthday) {
        if (CheckNull.isEmpty(birthday))
            return false;
        Connection c = null;
        Date infoDate = null;
        try {
            c = ConnectionPool.getConnection();
            CallableStatement cs = c.prepareCall("{? = call info.getday()}");
            cs.registerOutParameter(1, java.sql.Types.DATE);
            cs.executeUpdate();
            infoDate = cs.getDate(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }
        if (infoDate == null)
            return true;
        if (birthday.getTime() > infoDate.getTime())
            return false;
        return true;
    }

    /**
     * Дата рождения не должна быть раньше 1 января 1910 года
     *
     * @param d1
     * @return
     */
    public Boolean isBirthDateCorrect(Date d1) {
        if (d1 != null) {
            Calendar c = Calendar.getInstance();
            c.set(1910, Calendar.JANUARY, 1);

            if (d1.getTime() < c.getTimeInMillis())
                return false;
        }
        return true;
    }

    public Boolean isRegistrationDateGreaterThanBirthday(Date d1, Date d2) {
        if (d1 != null & d2 != null) {
            if (d1.getTime() > d2.getTime())
                return true;
            return false;
        }
        // If date equals null, then pass checking
        return true;
    }

    // Если разница между датами меньше 16 лет, то возвращает false
    public Boolean isPReceiveDateCorrect(Date d1, Date d2) {
        if (customer.getP_type_document().equals("6")) {
            if (d1 != null && d2 != null) {
                DateTime t1 = new DateTime(d1);
                DateTime t2 = new DateTime(d2);
                Period period = new Period(t1, t2);
                if (period.getYears() < 16)
                    return false;
            }
        }
        return true;
    }

    public boolean isOldPassportDateRegistrationCorrect(Date p_birthday, Date p_passport_date_expiration) {
        if (p_birthday != null && p_passport_date_expiration != null) {
            DateTime t1 = new DateTime(p_birthday);
            DateTime t2 = new DateTime(p_passport_date_expiration);
            t2 = t2.plusDays(1);

            Period period = new Period(t1, t2);
            if ((period.getYears() == 25 || period.getYears() >= 45)
                    && (period.getMonths() == 0 && period.getWeeks() == 0 && period.getDays() == 0))
                return true;
            else {
                return false;
            }
        }
        return true;
    }

    public Boolean isPhoneCorrect(String str) {
        if (!CheckNull.isEmpty(str))
            if (str.length() == 12)
                return str.matches("\\d+");
            else
                return false;
        return true;
    }

    public boolean isEmailAddressCorrect() {
        if (!CheckNull.isEmpty(customer.getP_email_address())) {
			/*
			 * Pattern pattern = Pattern.compile("^.+@.+\\..+$"); Matcher
			 * matcher = pattern.matcher(email);
			 */
            return EmailValidator.getInstance(true).isValid(customer.getP_email_address());
        }
        return true;
    }

    public Boolean isTaxNumberExists(String text) {
        if (CheckNull.isEmpty(text)) {
            return false;
        }
        Connection c = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            PreparedStatement ps = c
                    .prepareStatement("SELECT COUNT(*) FROM CLIENT_P WHERE NUMBER_TAX_REGISTRATION = ?");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                count = rs.getInt(1);

        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        if (count != 0)
            return true;
        return false;
    }

    public Boolean isTaxNumberCorrect(String text) {
        if (!CheckNull.isEmpty(text)) {
            if (!(isDigit(text)) || !(text.length() == 9)) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkTaxNumberDigits(String text) {
        if (text != null && !text.isEmpty()) {
            if (text.startsWith("4"))
                return true;
            else if (text.startsWith("5"))
                return true;
            else if (text.startsWith("6"))
                return true;
            else
                return false;

        }
        return true;
    }

    // Проверка на 8 одинаковых последовательных чисел
    public Boolean checkTaxNumberConsecutive(String text) {
        if (text != null && !text.isEmpty()) {
            char c = 0;
            boolean flag = false;
            int count = 0;
            for (int i = 0; i < text.length(); i++) {
                if (flag)
                    if (count == text.length() - 2)
                        return false;
                c = text.charAt(i);
                if (i != text.length() - 1) {
                    if (c == text.charAt(i + 1)) {
                        flag = true;
                        count = count + 1;
                    } else {
                        flag = false;
                        count = 0;
                    }
                }
            }
        }
        return true;
    }

    public Boolean isForeignCitizenResident() {
        if (customer.getP_type_document().equals("4")) {
            if (customer.getCode_resident().equals("1"))
                return false;
        }
        return true;
    }

    public boolean isForeignCitizenOfUzbekistan() {
        if (customer.getP_type_document().equals("4")) {
            if (customer.getP_code_citizenship().equals("860"))
                return false;
        }
        return true;
    }

    public boolean isResidentTypeDocCorrect() {
        if (customer.getCode_resident().equals("2"))
            if (customer.getP_type_document().equals("1")
                    || customer.getP_type_document().equals("6"))
                return false;
        return true;
    }

    public Boolean isResidencyCorrect() {
        if (!customer.getP_code_citizenship().equals("860")) {
            if (customer.getCode_resident().equals("1"))
                return false;
        }
        return true;
    }

    private Boolean isInpsCorrect(String text) {
        if (text != null && !text.isEmpty()) {
            if (!(text.length() == 14)) {
                return false;
            }
        }
        return true;
    }

    public boolean inpsDateCorrect(String inpc_) {
        
            String inps = inpc_;
            String day = inps.substring(1, 3);
            String month = inps.substring(3, 5);
            String year = inps.substring(5, 7);
            DateTime dateTime = new DateTime(customer.getP_birthday());
            String dayB = String.valueOf(dateTime.getDayOfMonth());
            if (dayB.length() == 1)
                dayB = "0" + dayB;
            String monthB = String.valueOf(dateTime.getMonthOfYear());
            if (monthB.length() == 1)
                monthB = "0" + monthB;
            String yearB = String.valueOf(dateTime.getYear());
            yearB = yearB.substring(2, yearB.length());
            if (day.equals(dayB) && month.equals(monthB) && year.equals(yearB)) {
                return true;
            } else
                return false;
        
    }

    public static Boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public Boolean isDigit(String str) {
        return str.matches("[0-9]+");
    }

    private Boolean isRegistrationDateCorrect(Date d1, String documentType) {
        if (d1 != null) {
            if (documentType.equals(BIOMETRIC) || documentType.equals(OLD_PASSPORT)) {
                Calendar c = Calendar.getInstance();
                c.set(1995, Calendar.JANUARY, 1);
                if (d1.getTime() < c.getTimeInMillis())
                    return false;
            }
        }
        return true;
    }

    // Если ИНПС начинается с 3, то это мужчина, иначе женщина
    public Boolean isInpsGenderCorrect(String inps) {
        if (inps != null && !inps.isEmpty()) {
            if (customer.getP_code_gender().equals("1")) {
                return inps.startsWith("3");
            } else if (customer.getP_code_gender().equals("2")) {
                return inps.startsWith("4");
            }
        }
        return true;
    }

    public Boolean isClientExists(String ser, String num) {
        Connection c = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            PreparedStatement ps = c.prepareStatement(
                    "SELECT COUNT(*) FROM CLIENT_P WHERE PASSPORT_SERIAL = ? AND PASSPORT_NUMBER = ?");
            ps.setString(1, ser.toUpperCase());
            ps.setString(2, num);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                count = rs.getInt(1);
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        if (count != 0)
            return true;
        return false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean checkForConfirmation() {
        if (CheckNull.isEmpty(customer.getP_passport_date_registration())) {
            message = "Введите дату выдачи документа";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_date_expiration())) {
            if (customer.getP_type_document().equals("1")) {
                DateTime birthday = new DateTime(customer.getP_birthday());
                DateTime registration = new DateTime(customer.getP_passport_date_registration());
                registration = registration.plusDays(1);
                Period period = new Period(birthday, registration);
                System.out.println(period.getYears());
                System.out.println(period.getMonths());
                System.out.println(period.getWeeks());
                System.out.println(period.getDays());
                if (period.getYears() < 45)
                    if (CheckNull.isEmpty(customer.getP_passport_date_expiration())) {
                        message = "Введите дату действительности паспорта";
                        return false;
                    }
            //} else {
            } else if (customer.getP_type_document().equals("6")) {
                message = "Введите дату действительности паспорта";
                return false;
            }
        }
        if (!isExpirationDateCorrect()) {
            message = "Срок действителен до должен быть не более 10 лет";
            return false;
        }
        if (!(isRegistrationDateGreaterThanBirthday(customer.getP_passport_date_registration(),
                customer.getP_birthday()))) {
            message = "Дата Выдачи Документа Должна Быть Больше Даты Рождения";
            return false;
        }
        if (!isRegistrationDateCorrect(customer.getP_passport_date_registration(), customer.getP_type_document())) {
            message = "Дата Выдачи Документа не должна быть раньше 01.01.1992 года";
            return false;
        }

        if (!isPReceiveDateCorrect(customer.getP_birthday(),
                customer.getP_passport_date_registration())) {
            message = "Разность датой получения паспорта и датой рождения не должна быть меньше, чем 16 лет";
            return false;
        }
        //
        if (customer.getP_type_document().equals("1")) {
            if (!isOldPassportDateRegistrationCorrect(customer.getP_birthday(),
                    customer.getP_passport_date_expiration())) {
                message = "Дата действия паспорта должна быть: от даты рождения 25 лет минус 1 день или 45 лет минус 1 день";
                return false;
            }
        }
        return true;
    }

    public Boolean checkConfirmed() {
        if (CheckNull.isEmpty(customer.getP_passport_date_registration())) {
            message = "Дата Выдачи Документа не может быть пустой";
            return false;
        }
        if (CheckNull.isEmpty(customer.getP_passport_date_expiration())) {
            message = "Дата действительности документа не может быть пустой";
            return false;
        }
        return true;
    }

}

