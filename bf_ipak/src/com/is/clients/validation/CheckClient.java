package com.is.clients.validation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.joda.time.Period;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.Validator;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.client_personmap.PersonValidator;
import com.is.client_personmap.model.Person;
import com.is.clients.models.ClientJ;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;

public class CheckClient extends Validator<ClientJ> {
    private static DateTime operDay;
    private ClientJ client;
    private boolean ignoreDateValidation;
    private int clientAction;

    public CheckClient(String alias, boolean ignoreDate, int clientAction) {
        this.alias = alias;
        this.ignoreDateValidation = ignoreDate;
        this.clientAction = clientAction;
    }

    public static CheckClient checkCleanClient(String alias, int clientAction) {
        return new CheckClient(alias, false, clientAction);
    }

    public static CheckClient checkExisting(String alias, int clientAction) {
        return new CheckClient(alias, true, clientAction);
    }

    @Override
    public boolean isValid(ClientJ client) {
        this.client = client;
        client.setCheckedInAtaccama(false);
        if (!checkCommonInfo()) {
            return false;
        }
        if (isOrdinaryJur() && !checkJur()) {
            return false;
        }
        if (!isBankType() && !checkObjectiveData()) {
            return false;
        }
        if (isIp() && !checkIp()) {
            return false;
        }
        /*if (isBankType() && !checkBankClient()) {
            return false;
        }*/
        if (!isBankType() && !checkRest()) {
            return false;
        }

        if (clientAction == ClientUtil.ACTION_OPEN
                && !isIp()
                && !isBankType()
                && client.getJ_sign_dep_acc().equals(ClientUtil.CHECKBOX_N)) {
        	//2023.03.17 hamza. shu yerda yurlisoni kompleks tekshirtirishimiz kerak
    		if (CustomerUtils.isAtaccamaOn()) {
    			if (!checkInStopListAtaccama()) {
    				return false;
    			} else {
    				client.setCheckedInAtaccama(true);
    				client.getDirector().setCheckedInAtaccama(true);
    				client.getAccountant().setCheckedInAtaccama(true);
    			}
    		}
        	
            Validator<Person> personValidator = PersonValidator.fullCheck(alias);
            client.getDirector().setEmp_id(client.getEmp_id()); //2023.02.27
            if (!personValidator.isValid(client.getDirector())) {
                message = "������ ���������: " + personValidator.getMessage();
                return false;
            }
            client.getAccountant().setEmp_id(client.getEmp_id()); //2023.02.27
            if (!personValidator.isValid(client.getAccountant())) {
                message = "������ ����������: " + personValidator.getMessage();
                return false;
            }
        }
        return true;
    }

    private boolean checkInStopListAtaccama() {
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement setParam=null;
        CallableStatement clearParam = null;

		boolean ok = true;
		try {
			c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall("{call proc_ataccama()}");

            clearParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
            clearParam.execute();

            setParam = c.prepareCall("{ call Param.SetParam(?,?) }");

            setParam.setString(1, "BRANCH");
            setParam.setString(2, client.getBranch());
            setParam.execute();

            setParam.setString(1, "NAME");
            setParam.setString(2, client.getName());
            setParam.execute();

            setParam.setString(1, "ID");
            setParam.setString(2, "");
            setParam.execute();

            setParam.setString(1, "ID_CLIENT");
            setParam.setString(2, "");
            setParam.execute();

            setParam.setString(1, "STATE");
            setParam.setString(2, "0");
            setParam.execute();

            setParam.setString(1, "PARENT_ID_CLIENT_J");
            setParam.setString(2, "");
            setParam.execute();
            
            setParam.setString(1, "P_PARENT_ID_CLIENT_J");
            setParam.setString(2, "");
            setParam.execute();

            setParam.setString(1, "CODE_SUBJECT");
            setParam.setString(2, "J");
            setParam.execute();

            setParam.setString(1, "EMP_ID");
            setParam.setString(2, client.getEmp_id());
            setParam.execute();

            setParam.setString(1, "J_NUMBER_TAX_REGISTRATION");
            setParam.setString(2, client.getJ_number_tax_registration());
            setParam.execute();

            setParam.setString(1, "J_DATE_REGISTRATION");
            setParam.setDate(2, Util.sqlDate((Date) client.getJ_date_registration()));
            setParam.execute();
            //direktor
            setParam.setString(1, "J_DIRECTOR_FIRST_NAME");
            setParam.setString(2, client.getDirector().getFirst_name_local());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_PATRONYMIC");
            setParam.setString(2, client.getDirector().getPatronymic_local());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_FAMILY");
            setParam.setString(2, client.getDirector().getFamily_local());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_BIRTHDAY");
            setParam.setDate(2,  Util.sqlDate((Date) client.getDirector().getBirthday()));
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_CODE_GENDER");
            setParam.setString(2, client.getDirector().getCode_gender());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_CODE_CITIZENSHIP");
            setParam.setString(2, client.getDirector().getCode_citizenship());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_TYPE_DOCUMENT");
            setParam.setString(2, client.getDirector().getType_document());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_PASSP_SERIAL");
            setParam.setString(2, client.getDirector().getPassport_serial());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_PASSP_NUMBER");
            setParam.setString(2, client.getDirector().getPassport_number());
            setParam.execute();

            setParam.setString(1, "J_DIRECTOR_PASSP_DATE_REG");
            setParam.setDate(2,  Util.sqlDate((Date)client.getDirector().getPassport_date_registration()));
            setParam.execute();
            
            setParam.setString(1, "J_DIRECTOR_PASSP_PLACE_REG");
            setParam.setString(2, client.getDirector().getPassport_place_registration());
            setParam.execute();
            
            //accountant
            setParam.setString(1, "J_ACCOUNTANT_FIRST_NAME");
            setParam.setString(2, client.getAccountant().getFirst_name_local());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_PATRONYMIC");
            setParam.setString(2, client.getAccountant().getPatronymic_local());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_FAMILY");
            setParam.setString(2, client.getAccountant().getFamily_local());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_BIRTHDAY");
            setParam.setDate(2,  Util.sqlDate((Date) client.getAccountant().getBirthday()));
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_CODE_GENDER");
            setParam.setString(2, client.getAccountant().getCode_gender());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_CODE_CITIZENSHIP");
            setParam.setString(2, client.getAccountant().getCode_citizenship());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_TYPE_DOCUMENT");
            setParam.setString(2, client.getAccountant().getType_document());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_PASSP_SERIAL");
            setParam.setString(2, client.getAccountant().getPassport_serial());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_PASSP_NUMBER");
            setParam.setString(2, client.getAccountant().getPassport_number());
            setParam.execute();

            setParam.setString(1, "J_ACCOUNTANT_PASSP_DATE_REG");
            setParam.setDate(2,  Util.sqlDate((Date)client.getAccountant().getPassport_date_registration()));
            setParam.execute();
            
            setParam.setString(1, "J_ACCOUNTANT_PASSP_PLACE_REG");
            setParam.setString(2, client.getAccountant().getPassport_place_registration());
            setParam.execute();
            

            /*ID NULL
            ID_CLIENT 0
            STATE 0
            PARENT_ID_CLIENT_J NULL
            P_PARENT_ID_CLIENT_J NULL
            CODE_SUBJECT 'J'
            BRANCH 
            EMP_ID
            J_NUMBER_TAX_REGISTRATION
            NAME
            J_DATE_REGISTRATION
            J_DIRECTOR_FIRST_NAME
            J_DIRECTOR_PATRONYMIC
            J_DIRECTOR_FAMILY
            J_DIRECTOR_BIRTHDAY
            J_DIRECTOR_CODE_GENDER
            J_DIRECTOR_CODE_CITIZENSHIP
            J_DIRECTOR_TYPE_DOCUMENT
            J_DIRECTOR_PASSP_SERIAL
            J_DIRECTOR_PASSP_NUMBER
            J_DIRECTOR_PASSP_DATE_REG
            J_DIRECTOR_PASSP_PLACE_REG
            J_ACCOUNTANT_FIRST_NAME
            J_ACCOUNTANT_PATRONYMIC
            J_ACCOUNTANT_FAMILY
            J_ACCOUNTANT_BIRTHDAY
            J_ACCOUNTANT_CODE_GENDER
            J_ACCOUNTANT_CODE_CITIZENSHIP
            J_ACCOUNTANT_TYPE_DOCUMENT
            J_ACCOUNTANT_PASSP_SERIAL
            J_ACCOUNTANT_PASSP_NUMBER
            J_ACCOUNTANT_PASSP_DATE_REG
            J_ACCOUNTANT_PASSP_PLACE_REG
            */
			cs.execute();
			
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			message = e.getMessage();
			ok = false;
		} finally {
            DbUtils.closeStmt(clearParam);
            DbUtils.closeStmt(setParam);
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		return ok;
	}
    
    private boolean checkCommonInfo() {
        if (CheckNull.isEmpty(client.getName())) {
            message = "������� ������������";
            return false;
        }
        if (client.getName() != null && client.getName().length() > 80) {
            message = "����� ���� ������������ �� ������ ��������� 80 ��������";
            return false;
        }
        if (CheckNull.isEmpty(client.getCode_country())) {
            message = "������� ��� ������ �����������";
            return false;
        }
        if (CheckNull.isEmpty(client.getCode_resident())) {
            message = "������� ��� ������������";
            return false;
        }
        if (CheckNull.isEmpty(client.getCode_type())) {
            message = "������� ��� ��� �������";
            return false;
        }
        // ���� ������ ���� �������� �� ��������� �� �������������
        if (!client.getCode_type().equals("05")){
            if (client.getAddressCountry() != null && client.getCode_resident() != null &&
                    client.getAddressCountry().equals("860") && !client.getCode_resident().equals("1")){
                message = "������ ������ �� ������������ �������������";
                return false;
            }
        }
        return true;
    }

//	public String checkForNibbd(){
//		String res = checkObjectiveData();
//		if (res.length() == 0){
//			res = checkJur();
//		}
//		if (res.length() == 0){
//			res = checkAcc();
//		}
//		return res;
//	}

    private boolean checkObjectiveData() {

        if (CheckNull.isEmpty(client.getJ_date_registration())) {
            message = "������� ���� �����������";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_number_registration_doc())) {
            message = "������� ��������������� �����";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_place_regist_name())) {
            message = "������� ����� �����������";
            return false;
        }
        if (client.getJ_place_regist_name().length() > 50) {
            message = "���� \"����� �����������\" ������ ��������� �� ����� 50 ��������. ����� ���� - " + client.getJ_place_regist_name().length();
            return false;
        }
        if (CheckNull.isEmpty(client.getCode_form())) {
            message = "������� ����� �������������";
            return false;
        }

        if (new DateTime(client.getJ_date_registration()).isAfter(getOperDay())) {
            message = "���� ����������� �� ����� ���� ������ ���������� ����";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_region())) {
            message = "������� ��� �������";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_distr())) {
            message = "������� ��� ������";
            return false;
        }
        return true;
    }

    private boolean checkRest() {
        if (CheckNull.isEmpty(client.substringShortName())) {
            message = "������� ������� ���";
            return false;
        }
        if (client.getJ_short_name() != null && client.getJ_short_name().length() > 25) {
            message = "����� ���� ������� ��� �� ������ ��������� 25 ��������";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_code_organ_direct())) {
            message = "������� ����� ����������";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_code_bank())) {
            message = "������� ��� �����";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_post_address())) {
            message = "������� �����";
            return false;
        }
        if (client.getJ_fax() != null && client.getJ_fax().length() > 12) {
            message = "����� ���� ���� �� ������ ��������� 12 ��������";
            return false;
        }
        if (client.getJ_email() != null && client.getJ_email().length() > 40) {
            message = "����� ���� ����� �� ������ ��������� 40 ��������";
            return false;
        }
        if (!CheckNull.isEmpty(client.getJ_phone()) && !isPhoneCorrect(client.getJ_phone())) {
            message = "�� ������ ������ ������ �������� - ���� ������ 12 ���� (998....)";
            return false;
        }
        if (!CheckNull.isEmpty(client.getJ_email()) && !EmailValidator.getInstance(true).isValid(client.getJ_email())){
            message = "������������ �������� � ����:  - email!";
            return false;
        }
        return true;
    }


    private boolean checkJur() {
        if (!isIp() && CheckNull.isEmpty(client.getJ_number_tax_registration())) {
            message = "������� ���";
            return false;
        }
        if (!isIp() && client.getJ_number_tax_registration() != null && client.getJ_number_tax_registration().length() != 9) {
            message = "����� ���� ��� ������ ���� ����� 9 ��������";
            return false;
        }
        if (!isIp() && client.getJ_number_tax_registration() != null && client.getJ_number_tax_registration().charAt(0) != '2' &&
                client.getJ_number_tax_registration().charAt(0) != '3' && client.getJ_number_tax_registration().charAt(0) != '9') {
            message = "��� ������������ ���� ������ ���������� �� 2,3 ��� 9";
            return false;
        }
        if (CheckNull.isEmpty(client.getJ_opf())) {
            message = "������� ���.-�������� �����";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getJ_code_sector())) {
            message = "������� ��� �������";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getJ_code_tax_org())) {
            message = "������� ��� ���������(��)";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getJ_code_head_organization())) {
            message = "������� ��� ��������� �����������";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getJ_inn_head_organization())) {
            message = "������� ��� ���. �����������";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getJ_soato())) {
            message = "������� ��� ������";
            return false;
        }
//		if(client.getJ_soato()!=null && client.getJ_soato().length()>8){
//			message = "����� ���� ��� ������ �� ������ ��������� 8 ��������";
//		}
        if (!isIp() && CheckNull.isEmpty(client.getJ_okpo())) {
            message = "������� ��� ������������ ����";
            return false;
        }
        if (!isIp() && client.getJ_okpo() != null && client.getJ_okpo().length() > 8) {
            message = "����� ���� ��� ������������ ���� �� ������ ��������� 8 ��������";
            return false;
        }

        if (!isIp() && !client.getJ_code_head_organization().equals("0") &&
                client.getJ_code_head_organization().length() != 8) {
            message = "��� ��������� ����������� ����� ���� - 0, ��� 8 ������";
            return false;
        }
        if (!isIp()
                && ((client.getJ_code_head_organization().equals("0") &&
                !client.getJ_inn_head_organization().equals("0"))
                ||
                (!client.getJ_code_head_organization().equals("0") &&
                        client.getJ_inn_head_organization().length() != 9))) {

            message = "��� ��������� ����������� ������ ���� - 0, ���� ��� ��������� ����������� 0, ����� 9 ������(��������)";
            return false;
        }
        return true;
    }


    private boolean checkIp() {
        if (CheckNull.isEmpty(client.getP_last_name_cyr())){
            message = "������� �������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_first_name_cyr())){
            message = "������� ���";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_patronymic_cyr())){
            message = "������� ��������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_code_gender())) {
            message = "������� ���";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_birthday())) {
            message = "������� ���� ��������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_type_document())) {
            message = "������� ��� ���������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_passport_serial())) {
            message = "������� ����� ���������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_passport_number())) {
            message = "������� ����� ���������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_passport_date_registration())) {
            message = "������� ���� ���������";
            return false;
        }
        if (client.getP_type_document().equalsIgnoreCase("6") && CheckNull.isEmpty(client.getP_passport_date_expiration())) {
            message = "������� ������������ ��";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_passport_place_registration())) {
            message = "������� ��� �����";
            return false;
        }

        if (CheckNull.isEmpty(client.getP_post_address())) {
            message = "������� ����� ��������";
            return false;
        }
//		if(CheckNull.isEmpty(client.getP_code_adr_region())){
//			message = "������� ������ ��������";
//		}
//		if(CheckNull.isEmpty(client.getP_code_adr_distr())){
//			message = "������� ����� ��������";
//		}
        if (CheckNull.isEmpty(client.getP_birth_place())) {
            message = "������� ����� ��������";
            return false;
        }
        if (CheckNull.isEmpty(client.getP_code_citizenship())) {
            message = "������� ��� �����������";
            return false;
        }
        //if (CheckNull.isEmpty(client.getP_code_tax_org())) {
        //   message = "������� ��� ���������(��-��)";
        //   return false;
        //}
        if (CheckNull.isEmpty(client.getJ_code_tax_org())) {
            message = "������� ��� ���������(��-��)";
            return false;
        }

        if (CheckNull.isEmpty(client.getJ_type_activity())){
            message = "������� ��� ������������";
            return false;
        }

        // �������� �� ������������ ���
        //		if (!isTaxNumberExists(client.getP_number_tax_registration())) {
        //			message = "����� ��� ��� ����������";
        //		}
        //if (!isTaxNumberCorrect(client.getP_number_tax_registration())) {
        //    message = "��� ������ �������� �� 9 ����";
        //    return false;
        //}
        //if (!checkTaxNumberDigits(client.getP_number_tax_registration())) {
        //    message = "� ����������� ���� ��� ������ ���������� � ���� 4,5,6";
        //    return false;
        //}
        //if (!checkTaxNumberConsecutive(client.getP_number_tax_registration())) {
        //    message = "��� ������ ��������� �� ����� 8 ���������� ����";
        //    return false;
        //}
        //if (!CheckNull.isEmpty(client.getP_number_tax_registration())) {
        //    if (CheckNull.isEmpty(client.getP_code_tax_org())) {
        //        message = "������� ��� ���������";
        //        return false;
        //    }
        //}
        // �������� �� ������������ ����� ���� ���������
        /*if (!isForeignCitizenResident()) {
            message = "����������� ��������� �� ����� ���� ����������";
            return false;
        }*/
        if (!isForeignCitizenOfUzbekistan()) {
            message = "����������� ��������� �� ����� ���� �����������  �����������";
            return false;
        }
        if (!isResidentTypeDocCorrect()) {
            message = "� �������������� ����������� ���� ������������ ��� ���������";
            return false;
        }
        if (!isCitizenshipCorrect()) {
            message = "������������� ����� ������ ��� ��������� � �����������";
            return false;
        }
        // ���� ��� ��������� - ��� �� ����������
        if (client.getP_type_document().equals("5")) {
            if (client.getP_code_citizenship().equals("860")) {
                message = "������������� ����� ������ ��� ��������� ��� �� ���������� � �����������";
                return false;
            }
            if (!client.getCode_resident().equals("1")) {
                message = "������������� ����� ������ ��� ��������� ��� �� ���������� � �������������";
                return false;
            }
        }
        // �������� �� �������������� �������
        if (!CheckNull.isEmpty(client.getState()) && client.getState().equals("0") && isClientExists(client.getP_passport_serial(), client.getP_passport_number())) {
            message = "����� ������ ��� ����������!";
            return false;
        }

        if (!client.getP_type_document().equals("4") && client.getP_passport_serial().length() != 2) {
            message = "����� �������� ������ �������� �� 2 �������";
            return false;
        }
        if (!Util.isLetter(client.getP_passport_serial())) {
            message = "����� �������� ������ �������� �� ��������� ����";
            return false;
        }
        if (new DateTime(client.getP_passport_date_registration()).
                isBefore(
                        new DateTime(1992, 01, 01, 0, 0, 0, 0))) {
            message = "���� ������ �������� �� ������ ���� ������ 01.01.1992 ����";
            return false;
        }
        if (new DateTime(client.getP_passport_date_registration()).isAfter(getOperDay())) {
            message = "���� ������ �������� �� ������ ���� ������ ���� ������������� ���";
            return false;
        }

        if (!ignoreDateValidation && client.getP_type_document().equals("6")) {
            DateTime dateReg = new DateTime(client.getP_passport_date_registration());
            DateTime dateExp = new DateTime(client.getP_passport_date_expiration());
            dateExp = dateExp.plusDays(1);
            if (new Period(dateReg, dateExp).getYears() != 10) {
                message = "���� �������� �������� ������ ����: �� ���� �������� 10 ��� ����� 1 ���� (�������������� �������)";
                return false;
            }
        }
        /*if (!ignoreDateValidation && client.getP_type_document().equals("1")) {
            DateTime dateReg = new DateTime(client.getP_passport_date_registration());
            DateTime dateExp = new DateTime(client.getP_passport_date_expiration());
            dateExp = dateExp.plusDays(1);
            if (new Period(dateReg, dateExp).getYears() != 25) {
                message = "���� �������� �������� ������ ����: �� ���� �������� 25 ��� ����� 1 ���� (������ �������)";
                return false;
            }
        }*/
//		if(new Period(
//				new DateTime(client.getP_passport_date_registration()), 
//				new DateTime(client.getP_birthday())).getYears() <16){
//			message = "�������� ����� ����� ��������� �������� � ����� �������� �� ������ ���� ������, ��� 16 ���";
//		}
        if (new DateTime(client.getP_passport_date_expiration()).isBefore(getOperDay())) {
            message = "���� �������� �������� �� ������ ���� ������ ���� ������������� ���";
            return false;
        }
        if (new DateTime(client.getP_birthday().getTime()).
                isBefore(
                        new DateTime(1910, 01, 01, 0, 0, 0, 0))) {
            message = "���� �������� �� ������ ���� ������ 01.01.1910 ����";
            return false;
        }
        if (new DateTime(client.getP_birthday().getTime()).
                isAfter(
                        getOperDay())) {
            message = "���� �������� �� ����� ���� ������ ���������� ����";
            return false;
        }
        /*if (!CheckNull.isEmpty(client.getP_email_address()) && !EmailValidator.getInstance(true).isValid(client.getP_email_address())){
            message = "������������ �������� � ����:  email !";
            return false;
        }*/
        if (!CheckNull.isEmpty(client.getP_phone_home()) && !isPhoneCorrect(client.getP_phone_home())){
            message = "�� ������ ������ ������ �������� - ���� ������ 12 ���� (998....)";
            return false;
        }
        if (!CheckNull.isEmpty(client.getP_phone_mobile()) && !isPhoneCorrect(client.getP_phone_mobile())){
            message = "�� ������ ������ ������ �������� - ���� ������ 12 ���� (998....)";
            return false;
        }
        return true;
    }

//	private boolean checkDirectorAndAccountant() {
//		
//	}

    private boolean checkBankClient() {
        if (CheckNull.isEmpty(client.getI_number_tax_registration())) {
            message = "������� ���";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_opf())) {
            message = "������� ���.-�������� �����";
            return false;
        }
        if (!isIp() && CheckNull.isEmpty(client.getI_sector())) {
            message = "������� ��� �������";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_organ_direct())) {
            message = "������� ����� ����������";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_form())) {
            message = "������� ����� �������������";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_post_address())) {
            message = "������� �����";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_director_name())) {
            message = "������� ��� ���������";
            return false;
        }
        if (CheckNull.isEmpty(client.getI_chief_accounter_name())) {
            message = "������� ��� ��. ����������";
            return false;
        }

        return true;
    }
//	
//	private String checkAcc(){
//		return !CheckNull.isEmpty(client.getJ_account())&&
//				client.getJ_account().matches("^[1-9]{1}\\d{19}")?"":
//			"��������� ���� ������ �������� �� 20 �������� ������";
//	}

    private Boolean checkTaxNumberDigits(String text) {
        return text != null && (text.startsWith("4")
                || text.startsWith("5") || text.startsWith("6"));
    }

    // �������� �� 8 ���������� ���������������� �����
    private Boolean checkTaxNumberConsecutive(String text) {
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

    private boolean isPhoneCorrect(String phone) {
        return phone.startsWith("998")
                && phone.length() == 12
                && Util.isDigit(phone);
    }

    public Boolean isClientExists(String ser, String num) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(
                    "SELECT COUNT(*) FROM CLIENT_P WHERE PASSPORT_SERIAL = ? AND PASSPORT_NUMBER = ?");
            ps.setString(1, ser.toUpperCase());
            ps.setString(2, num);
            rs = ps.executeQuery();
            if (rs.next())
                count = rs.getInt(1);
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        if (count != 0)
            return true;
        else
            return false;
    }

    /*public Boolean isPhoneCorrect(String str) {
        return str != null && str.matches("\\d+") && str.length() == 12;
    }*/

    public Boolean isPDateExpirationCorrect(Date d1, Date d2) {
        if (client.getP_type_document().equals("6")) {
            if (d1 != null && d2 != null) {
                Calendar cal = Calendar.getInstance();
                Date f = d1;
                cal.setTime(f);
                cal.add(Calendar.YEAR, 10);
                if (cal.getTime().getTime() < d2.getTime())
                    return false;
                else
                    return true;
            }
        }
        return true;
    }

    private boolean isForeignCitizenOfUzbekistan() {
        return !(client.getP_type_document().equals("4") &&
                client.getP_code_citizenship().equals("860"));
    }

    private boolean isResidentTypeDocCorrect() {
        if (client.getCode_resident().equals("2"))
            if (client.getP_type_document().equals("1") || client.getP_type_document().equals("6"))
                return false;
        return true;
    }

    public Boolean isCitizenshipCorrect() {
        if (client.getP_type_document().equals("5"))
            return !client.getP_code_citizenship().equals("860");
        if (client.getP_type_document().equals("1")
                || client.getP_type_document().equals("6")
                || client.getP_type_document().equals("2")
                || client.getP_type_document().equals("3"))
            return client.getP_code_citizenship().equals("860");
        else if (client.getP_type_document().equals("4"))
            return !client.getP_code_citizenship().equals("860");
        else
            return true;
    }

    public Boolean isForeignCitizenResident() {
        return !(client.getP_type_document().equals("4") &&
                client.getCode_resident().equals("1"));
    }

    private Boolean isTaxNumberCorrect(String text) {
        return !CheckNull.isEmpty(text) &&
                (Util.isDigit(text)) && (text.length() == 9);
    }

    private boolean isIp() {
        return client.getCode_type().equals("11") || client.getCode_type().equals("21");
    }

    private boolean isOrdinaryJur() {
        return !( client.getCode_type().equals("11") || client.getCode_type().equals("21")); //&& !client.getCode_type().equals("07");
    }

    private boolean isBankType() {
        return client.getCode_type().equals("07");
    }

    private DateTime getOperDay() {
        //if (operDay == null) {
            operDay = new DateTime(DbUtils.getOperDay(alias));
        //}
        return operDay;
    }


    public static boolean isTaxNumberValid(String taxNumber, String schema) {
        Connection c = null;
        CallableStatement callableStatement = null;
        int controlDigit = 0;
        try {
            c = ConnectionPool.getConnection(schema);
            callableStatement = c.prepareCall("{? = call kernel.checkInn(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, taxNumber);
            callableStatement.execute();

            controlDigit = callableStatement.getInt(1);
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(callableStatement);
            ConnectionPool.close(c);
        }

        return Integer.parseInt(
                taxNumber.substring(
                        taxNumber.length() - 1))
                == controlDigit ? true : false;
    }

}
