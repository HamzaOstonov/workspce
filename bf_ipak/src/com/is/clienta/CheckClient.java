package com.is.clienta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.clienta.utils.DbUtils;
import com.is.utils.CheckNull;

public class CheckClient extends Validator<ClientA> {
	private static DateTime operDay;
	private ClientA client;
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
	public boolean isValid(ClientA client) {
		this.client = client;
		if (!checkCommonInfo()) {
			// ISLogger.getLogger().error("CHeckClient :: checkCommonInfo =
			// false");
			return false;
		}
		if (isOrdinaryJur() && !checkJur()) {
			// ISLogger.getLogger().error("CHeckClient :: isOrdinaryJur() &&
			// !checkJur() = false");
			return false;
		}
		if (!isBankType() && !isPh() && !checkObjectiveData()) {
			// ISLogger.getLogger().error("CHeckClient :: !isBankType() &&
			// !checkObjectiveData() = false");
			return false;
		}
		if (isIp() && !checkIp()) {
			// ISLogger.getLogger().error("CHeckClient :: isIp() && !checkIp() =
			// false");
			return false;
		}
		if (isBankType() && !checkBankClient()) {
			// ISLogger.getLogger().error("CHeckClient :: isBankType() &&
			// !checkBankClient() = false");
			return false;
		}
		if (!isBankType() && !isPh() && !checkRest()) {
			// ISLogger.getLogger().error("CHeckClient :: !isBankType() &&
			// !checkRest() = false");
			return false;
		}
		if (isPh() && !checkPh()) {
			return false;
		}

		//if (clientAction == ClientUtil.ACTION_OPEN && !isIp() && !isBankType()
		//		&& client.getJ_sign_dep_acc().equals(ClientUtil.CHECKBOX_N)) {
		//}

		return true;
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
		if (!client.getCode_type().equals("05")) {
			if (client.getAddressCountry() != null && client.getCode_resident() != null
					&& client.getAddressCountry().equals("860") && !client.getCode_resident().equals("1")) {
				message = "������ ������ �� ������������ �������������";
				return false;
			}
		}
		return true;
	}

	// public String checkForNibbd(){
	// String res = checkObjectiveData();
	// if (res.length() == 0){
	// res = checkJur();
	// }
	// if (res.length() == 0){
	// res = checkAcc();
	// }
	// return res;
	// }

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
			message = "���� \"����� �����������\" ������ ��������� �� ����� 50 ��������. ����� ���� - "
					+ client.getJ_place_regist_name().length();
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
		if (!CheckNull.isEmpty(client.getJ_phone()) && !isPhoneCorrect()) {
			message = "�� ������ ������ ������ �������� - ���� ������ 12 ���� (998....)";
			return false;
		}

		return true;
	}

	private boolean checkJur() {
		if (!isIp() && CheckNull.isEmpty(client.getJ_number_tax_registration())) {
			message = "������� ���";
			return false;
		}
		if (!isIp() && client.getJ_number_tax_registration() != null
				&& client.getJ_number_tax_registration().length() != 9) {
			message = "����� ���� ��� ������ ���� ����� 9 ��������";
			return false;
		}
		if (!isIp() && client.getJ_number_tax_registration() != null
				&& client.getJ_number_tax_registration().charAt(0) != '2'
				&& client.getJ_number_tax_registration().charAt(0) != '3') {
			message = "��� ������������ ���� ������ ���������� �� 2 ��� 3";
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
			message = "������� ��� ���������";
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
		// if(client.getJ_soato()!=null && client.getJ_soato().length()>8){
		// message = "����� ���� ��� ������ �� ������ ��������� 8 ��������";
		// }
		if (!isIp() && CheckNull.isEmpty(client.getJ_okpo())) {
			message = "������� ��� ������������ ����";
			return false;
		}
		if (!isIp() && client.getJ_okpo() != null && client.getJ_okpo().length() > 8) {
			message = "����� ���� ��� ������������ ���� �� ������ ��������� 8 ��������";
			return false;
		}

		if (!isIp() && !client.getJ_code_head_organization().equals("0")
				&& client.getJ_code_head_organization().length() != 8) {
			message = "��� ��������� ����������� ����� ���� - 0, ��� 8 ������";
			return false;
		}
		if (!isIp() && ((client.getJ_code_head_organization().equals("0")
				&& !client.getJ_inn_head_organization().equals("0"))
				|| (!client.getJ_code_head_organization().equals("0")
						&& client.getJ_inn_head_organization().length() != 9))) {

			message = "��� ��������� ����������� ������ ���� - 0, ���� ��� ��������� ����������� 0, ����� 9 ������(��������)";
			return false;
		}
		return true;
	}

	private boolean checkIp() {
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
		if (CheckNull.isEmpty(client.getP_passport_date_expiration())) {
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
		if (CheckNull.isEmpty(client.getP_birth_place())) {
			message = "������� ����� ��������";
			return false;
		}
		if (CheckNull.isEmpty(client.getP_code_citizenship())) {
			message = "������� ��� �����������";
			return false;
		}
		if (CheckNull.isEmpty(client.getP_code_tax_org())) {
			message = "������� ��� ���������";
			return false;
		}
		if (CheckNull.isEmpty(client.getJ_code_tax_org())) {
			message = "������� ��� ���������";
			return false;
		}

		// �������� �� ������������ ����� ���� ���������
		if (!isForeignCitizenResident()) {
			message = "����������� ��������� �� ����� ���� ����������";
			return false;
		}
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
		if (!CheckNull.isEmpty(client.getState()) && client.getState().equals("0")
				&& isClientExists(client.getP_passport_serial(), client.getP_passport_number())) {
			message = "����� ������ ��� ����������!";
			return false;
		}

		if (client.getP_passport_serial().length() != 2) {
			message = "����� �������� ������ �������� �� 2 �������";
			return false;
		}

		if (new DateTime(client.getP_passport_date_registration()).isBefore(new DateTime(1992, 01, 01, 0, 0, 0, 0))) {
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
		if (!ignoreDateValidation && client.getP_type_document().equals("1")) {
			DateTime dateReg = new DateTime(client.getP_passport_date_registration());
			DateTime dateExp = new DateTime(client.getP_passport_date_expiration());
			dateExp = dateExp.plusDays(1);
			if (new Period(dateReg, dateExp).getYears() != 25) {
				message = "���� �������� �������� ������ ����: �� ���� �������� 25 ��� ����� 1 ���� (������ �������)";
				return false;
			}
		}

		if (new DateTime(client.getP_passport_date_expiration()).isBefore(getOperDay())) {
			message = "���� �������� �������� �� ������ ���� ������ ���� ������������� ���";
			return false;
		}
		if (new DateTime(client.getP_birthday().getTime()).isBefore(new DateTime(1910, 01, 01, 0, 0, 0, 0))) {
			message = "���� �������� �� ������ ���� ������ 01.01.1910 ����";
			return false;
		}
		if (new DateTime(client.getP_birthday().getTime()).isAfter(getOperDay())) {
			message = "���� �������� �� ����� ���� ������ ���������� ����";
			return false;
		}
		return true;
	}

	
	private boolean checkPh() {
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
		if (CheckNull.isEmpty(client.getP_passport_date_expiration())) {
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
		if (CheckNull.isEmpty(client.getP_birth_place())) {
			message = "������� ����� ��������";
			return false;
		}
		if (CheckNull.isEmpty(client.getP_code_citizenship())) {
			message = "������� ��� �����������";
			return false;
		}
		if (CheckNull.isEmpty(client.getP_code_tax_org())) {
			message = "������� ��� ���������";
			return false;
		}
		// �������� �� ������������ ����� ���� ���������
		if (!isForeignCitizenResident()) {
			message = "����������� ��������� �� ����� ���� ����������";
			return false;
		}
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
		//if (!CheckNull.isEmpty(client.getState()) && client.getState().equals("0")
		//		&& isClientExists(client.getP_passport_serial(), client.getP_passport_number())) {
		//	message = "����� ������ ��� ����������!";
		//	return false;
		//}

		if (client.getP_passport_serial().length() != 2) {
			message = "����� �������� ������ �������� �� 2 �������";
			return false;
		}

		if (new DateTime(client.getP_passport_date_registration()).isBefore(new DateTime(1992, 01, 01, 0, 0, 0, 0))) {
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
		if (!ignoreDateValidation && client.getP_type_document().equals("1")) {
			DateTime dateReg = new DateTime(client.getP_passport_date_registration());
			DateTime dateExp = new DateTime(client.getP_passport_date_expiration());
			dateExp = dateExp.plusDays(1);
			if (new Period(dateReg, dateExp).getYears() != 25) {
				message = "���� �������� �������� ������ ����: �� ���� �������� 25 ��� ����� 1 ���� (������ �������)";
				return false;
			}
		}

		if (new DateTime(client.getP_passport_date_expiration()).isBefore(getOperDay())) {
			message = "���� �������� �������� �� ������ ���� ������ ���� ������������� ���";
			return false;
		}
		if (new DateTime(client.getP_birthday().getTime()).isBefore(new DateTime(1910, 01, 01, 0, 0, 0, 0))) {
			message = "���� �������� �� ������ ���� ������ 01.01.1910 ����";
			return false;
		}
		if (new DateTime(client.getP_birthday().getTime()).isAfter(getOperDay())) {
			message = "���� �������� �� ����� ���� ������ ���������� ����";
			return false;
		}
		return true;
	}

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
	// private String checkAcc(){
	// return !CheckNull.isEmpty(client.getJ_account())&&
	// client.getJ_account().matches("^[1-9]{1}\\d{19}")?"":
	// "��������� ���� ������ �������� �� 20 �������� ������";
	// }

	private Boolean checkTaxNumberDigits(String text) {
		return text != null && (text.startsWith("4") || text.startsWith("5") || text.startsWith("6"));
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

	private boolean isPhoneCorrect() {
		return client.getJ_phone().startsWith("998") && client.getJ_phone().length() == 12
		// && Util.isDigit(client.getJ_phone())
		;
	}

	public Boolean isClientExists(String ser, String num) {
		Connection c = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
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
		else
			return false;
	}

	public Boolean isPhoneCorrect(String str) {
		return str != null && str.matches("\\d+") && str.length() == 12;
	}

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
		return !(client.getP_type_document().equals("4") && client.getP_code_citizenship().equals("860"));
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
		if (client.getP_type_document().equals("1") || client.getP_type_document().equals("6")
				|| client.getP_type_document().equals("2") || client.getP_type_document().equals("3"))
			return client.getP_code_citizenship().equals("860");
		else if (client.getP_type_document().equals("4"))
			return !client.getP_code_citizenship().equals("860");
		else
			return true;
	}

	public Boolean isForeignCitizenResident() {
		return !(client.getP_type_document().equals("4") && client.getCode_resident().equals("1"));
	}

	private Boolean isTaxNumberCorrect(String text) {
		return !CheckNull.isEmpty(text) &&
		/* (Util.isDigit(text)) && */ (text.length() == 9);
	}

	private boolean isIp() {
		return client.getCode_type().equals("11");
	}

	private boolean isPh() {
		return client.getCode_type().equals("08");
	}

	private boolean isOrdinaryJur() {
		return !client.getCode_type().equals("11") && !client.getCode_type().equals("07") && !client.getCode_type().equals("08");
	}

	private boolean isBankType() {
		return client.getCode_type().equals("07");
	}

	private DateTime getOperDay() {
		if (operDay == null) {
			operDay = new DateTime(DbUtils.getOperDay(alias));
		}
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

		return Integer.parseInt(taxNumber.substring(taxNumber.length() - 1)) == controlDigit ? true : false;
	}

}
