package com.is.client_personmap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.Validator;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.Person;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;

public class PersonValidator extends Validator<Person> {
	private Person person;
	private DateTime operDay;
	private static final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static final String EMPTY_STRING = "";
	
	private boolean fullCheck;

	private PersonValidator(String alias, boolean fullCheck) {
		super();
		this.alias = alias;
		this.fullCheck = fullCheck;
	}

	public static PersonValidator instance(String alias, boolean isFullCheck) {
		return new PersonValidator(alias, isFullCheck);
	}

	public static PersonValidator fullCheck(String alias) {
		return new PersonValidator(alias, true);
	}

	@Override
	public boolean isValid(Person person) {
		this.person = person;
		Period period = null;
		/*if (CheckNull.isEmpty(person.getName())){
			message = "������� ������������";
			return false;
		}*/
		if (CheckNull.isEmpty(person.getFamily_local())) {
			message = "������� �������(�����)";
			return false;
		}
		if (CheckNull.isEmpty(person.getFirst_name_local())) {
			message = "������� ���(�����)";
			return false;
		}
		if (CheckNull.isEmpty(person.getPatronymic_local())) {
			message = "������� ��������(�����)";
			return false;
		}
		if (CheckNull.isEmpty(person.getFamily())) {
			message = "������� �������(����)";
			return false;
		}

		if (CheckNull.isEmpty(person.getFirst_name())) {
			message = "������� ���(����)";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getCode_resident())) {
			message = "������� �������������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getType_document())) {
			message = "������� ��� ���������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getPassport_serial())) {
			message = "������� ����� ��������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getPassport_number())) {
			message = "������� ����� ��������";
			return false;
		}
		if (fullCheck && !isName(person.getFamily())) {
			message = "�������(����) - ������ ��������� �����";
			return false;
		}
		if (fullCheck && !isName(person.getFirst_name())) {
			message = "���(����) - ������ ��������� �����";
			return false;
		}
		if (fullCheck && !isName(person.getPatronymic())) {
			message = "��������(����) - ������ ��������� �����";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getPost_address())) {
			message = "������� �����";
			return false;
		}
		if (fullCheck && !isPNumberCorrect(person.getPassport_number())) {
			message = "����������� ������ ����� ��������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getCode_citizenship())) {
			message = "������� �����������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getPassport_place_registration())) {
			message = "������� ����� ������ ���";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getBirth_place())) {
			message = "������� ����� ��������";
			return false;
		}
		if (fullCheck && ((person.getType_document().equals("1") || person.getType_document().equals("6"))
				&& person.getPassport_serial().length() != 2)) {
			// ISLogger.getLogger().error("person.getPassport_serial().length()
			// != 2 serial = " + person.getPassport_serial() + ",
			// person.getPassport_serial().length = " +
			// person.getPassport_serial().length());
			message = "����� �������� ������ �������� �� 2 �������";
			return false;
		}
		if (fullCheck && ((person.getType_document().equals("6") || person.getType_document().equals("1"))
				&& !isLatin(person.getPassport_serial()))) {
			message = "����� �������� ������ �������� �� ��������� ����";
			return false;
		}
		if (fullCheck && (person.getCode_citizenship().equals("860") || person.getCode_resident().equalsIgnoreCase("1"))) {
			if (!CheckNull.isEmpty(person.getNumber_tax_registration())
					&& !isTaxNumberCorrect(person.getNumber_tax_registration())) {
				message = "��� ������ �������� �� 9 ����, ���� ��������� �����������, ����� �� 20 ��������";
				return false;
			}
			if (!CheckNull.isEmpty(person.getNumber_tax_registration())
					&& !checkTaxNumberDigits(person.getNumber_tax_registration())) {
				message = "� ����������� ���� ��� ������ ���������� � ���� 4,5,6";
				return false;
			}
			if (!CheckNull.isEmpty(person.getNumber_tax_registration())
					&& !checkTaxNumberConsecutive(person.getNumber_tax_registration())) {
				message = "��� ������ ��������� �� ����� 8 ���������� ����";
				return false;
			}
		}
		// �������� �� ������������ ����� ���� ���������
		if (fullCheck && !isForeignCitizenResident()) {
			message = "����������� ��������� �� ����� ���� ����������";
			return false;
		}
		if (fullCheck && !isForeignCitizenOfUzbekistan()) {
			message = "����������� ��������� �� ����� ���� �����������  �����������";
			return false;
		}
		if (fullCheck && !isResidentTypeDocCorrect()) {
			message = "� �������������� ����������� ���� ������������ ��� ���������";
			return false;
		}
		if (fullCheck && !isResidencyCorrect()) {
			message = "������������� ����� ������ ��� ��������� � �����������/�������������";
			return false;
		}
		if (fullCheck && person.getType_document().equals("5") && person.getCode_citizenship().equals("860")) {
			message = "������������� ����� ����� ��������� ��� �� ���������� � ������������";
			return false;
		}
		if (fullCheck && person.getType_document().equals("5") && person.getCode_resident().equals("2")) {
			message = "������������� ����� ����� ��������� ��� �� ���������� � �������������";
			return false;
		}
		if (fullCheck && CheckNull.isEmpty(person.getPassport_date_registration())) {
			message = "������� ���� ������ ���������";
			return false;
		}
		if (fullCheck && person.getType_document().equalsIgnoreCase("6") && CheckNull.isEmpty(person.getPassport_date_expiration())) {
			message = "������� ���� �������� ���������";
			return false;
		}

		if (fullCheck) {
			boolean passDatesNotNull = person.getPassport_date_registration() != null
					&& person.getPassport_date_expiration() != null;

			period = new Period(new DateTime(person.getPassport_date_registration()),
					new DateTime(person.getPassport_date_expiration()).plusDays(1));

			if (passDatesNotNull && new DateTime(person.getPassport_date_registration())
					.isBefore(new DateTime(1992, 01, 01, 0, 0, 0, 0))) {
				message = "���� ������ �������� �� ������ ���� ������ 01.01.1992 ����";
				return false;
			}

			if (person.getType_document() != null && person.getType_document().equals("6") && period.getYears() != 10
					&& period.getMonths() == 0 && period.getWeeks() == 0 && period.getDays() == 0) {
				message = "�������� ����� �������� � ����� ��������� ��������� ������ ���� 10 ��� (�������������� �������)";
				return false;
			}
			/*
			 * if(fullCheck && person.getPassport_date_expiration() == null) {
			 * message = "������� ���� �������� ��������"; return false; }
			 */
			if (person.getPassport_date_registration() != null
					&& new DateTime(person.getPassport_date_registration()).isAfter(getOperDay())) {
				message = "���� ����������� ��������� �� ����� ���� ������ ���������� ����";
				return false;
			}
			if (person.getPassport_date_expiration() != null
					&& new DateTime(person.getPassport_date_expiration()).isBefore(getOperDay())) {
				message = "���� �������� �������� �� ������ ���� ������ ���� ������������� ���";
				return false;
			}
		}
		if (fullCheck && person.getBirthday() == null) {
			message = "������� ���� ��������";
			return false;
		}
		if (fullCheck) {
			boolean birthDayNotNull = person.getBirthday() != null;
			if (birthDayNotNull
					&& new DateTime(person.getBirthday().getTime()).isBefore(new DateTime(1910, 01, 01, 0, 0, 0, 0))) {
				message = "���� �������� �� ������ ���� ������ 01.01.1910 ����";
				return false;
			}
			if (birthDayNotNull && new DateTime(person.getBirthday().getTime()).isAfter(getOperDay())) {
				message = "���� �������� �� ����� ���� ������ ���������� ����";
				return false;
			}

			if (person.getCode_country() != null && !person.getCode_country().equals("860")) {
				if (!CheckNull.isEmpty(person.getCode_adr_region()) || !CheckNull.isEmpty(person.getCode_adr_distr())) {
					message = "������������� ����� ������� ������ � ������� � ��������";
					return false;
				}
			}
			if (person.getCode_country() != null && person.getCode_country().equals("860")) {
				if (CheckNull.isEmpty(person.getCode_adr_region())) {
					message = "������� ��� ������� ���������������";
					return false;
				}
				if (CheckNull.isEmpty(person.getCode_adr_distr())) {
					message = "������� ��� ������ ���������������";
					return false;
				}
			}
		}
		if (CustomerUtils.isAtaccamaOn()) {
			if (!person.isCheckedInAtaccama()) {
			    if (!checkInStopListAtaccama()) {
					   return false;
				    }
			}
		}
		if (!checkInStopList()) {
			return false;
		}

		return true;
	}

	private Boolean checkTaxNumberDigits(String text) {
		return text != null && person.getCode_citizenship().equals("860")
				&& (text.startsWith("4") || text.startsWith("5") || text.startsWith("6"));
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
		if (person.getType_document().equals("6")) {
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

	public boolean isForeignCitizenOfUzbekistan() {
		return !CheckNull.isEmpty(person.getType_document())
				&& !(person.getType_document().equals("4") && person.getCode_citizenship().equals("860"));
	}

	private boolean isResidentTypeDocCorrect() {
		if (!CheckNull.isEmpty(person.getType_document()) && person.getCode_resident().equals("2"))
			if (person.getType_document().equals("1") || person.getType_document().equals("6"))
				return false;
		return true;
	}

	public Boolean isResidencyCorrect() {
		if (person.getCode_citizenship().equals("999"))
			return true;
		if (person.getType_document().equals("1") || person.getType_document().equals("6")) {
			return person.getCode_citizenship().equals("860") && person.getCode_resident().equals("1");
		}
		return true;
	}

	public boolean isForeignCitizenResident() {
		return !CheckNull.isEmpty(person.getType_document())
				&& !(person.getType_document().equals("4") && person.getCode_resident().equals("1"));
	}

	private Boolean isTaxNumberCorrect(String text) {
		boolean checkLength = true;
		if (person.getCode_citizenship().equals("860")) {
			checkLength = text.length() == 9;
		} else {
			checkLength = text.length() > 20;
		}
		return !CheckNull.isEmpty(text) && (isDigit(text)) && checkLength;
	}

	private boolean isLatin(String c) {
		if (c == null)
			return false;
		return c.matches("[a-zA-Z]+");
		// return c.matches("[A-Za-z\\s\\-']+");
	}

	private boolean isName(String c) {
		if (c == null)
			return false;
		return c.matches("[A-Za-z\\s\\-']+");
	}

	private boolean isDigit(String c) {
		return c.matches("\\d+");
	}

	public static String dateToString(Date date) {
		return date != null ? df.format(date) : EMPTY_STRING;
	}
	
	private DateTime getOperDay() {
		if (operDay == null) {
			operDay = new DateTime(DbUtils.getOperDay(alias));
		}
		return operDay;
	}

	private Boolean isPNumberCorrect(String str) {
		if (CheckNull.isEmpty(str))
			return false;
		if (!isDigit(str))
			return false;
		if (person.getType_document().equals("1") || person.getType_document().equals("6")) {
			if (str.length() == 7)
				return true;
			if (str.length() > 9)
				return false;
			return false;
		}

		/*
		 * Pattern pattern = Pattern.compile("([\\d])\\1\\1\\1\\1\\1"); pattern
		 * = Pattern.compile("^([0-9])\\1{6,}$"); Matcher m =
		 * pattern.matcher(str);
		 */
		return true;
	}

	private boolean checkInStopList() {
		Connection c = null;
		CallableStatement cs = null;
		boolean ok = true;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{call stoplist.reaction(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, person.getBranch());
			cs.setString(2, "");
			cs.setString(3, person.personName());
			cs.setString(4, "");
			cs.setString(5, "");
			cs.setString(6, "");
			cs.setString(7, person.getFamily());
			cs.setString(8, person.getFirst_name());
			cs.setString(9, person.getPatronymic());
			// ISLogger.getLogger().error("personValidation checkInStopList
			// before exectute");
			cs.execute();
			// ISLogger.getLogger().error("personValidation checkInStopList
			// after exectute");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			message = e.getMessage();
			ok = false;
		} finally {
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		return ok;
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
            

            if (person.getId() != null) {
                setParam.setString(1, "ID");
                setParam.setString(2, person.getId());
                setParam.execute();
            }

            setParam.setString(1, "BRANCH");
            setParam.setString(2, person.getBranch());
            setParam.execute();

            setParam.setString(1, "NAME");
            setParam.setString(2, person.getName() !=null ? person.getName() : person.personName() );
            setParam.execute();
            if (person.getType_document() != null) {
                setParam.setString(1, "P_TYPE_DOCUMENT");
                setParam.setString(2, person.getType_document());
            }
            setParam.execute();
            setParam.setString(1, "CODE_COUNTRY");
            setParam.setString(2, person.getCode_country());
            setParam.execute();
            
            setParam.setString(1, "CODE_RESIDENT");
            setParam.setString(2, person.getCode_resident());
            setParam.execute();
            

            setParam.setString(1, "P_BIRTHDAY");
            setParam.setString(2, dateToString(person.getBirthday()));
            setParam.execute();
            setParam.setString(1, "P_POST_ADDRESS");
            setParam.setString(2, person.getPost_address() != null ? person.getPost_address().toUpperCase() : "");
            setParam.execute();
            
            setParam.setString(1, "P_PASSPORT_SERIAL");
            setParam.setString(2, person.getPassport_serial() != null ?
            		person.getPassport_serial().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_NUMBER");
            setParam.setString(2, person.getPassport_number());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
            setParam.setString(2, person.getPassport_place_registration() != null ? person.getPassport_place_registration().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_DATE_REGISTRATION");
            setParam.setString(2, dateToString(person.getPassport_date_registration()));
            setParam.execute();
            setParam.setString(1, "P_CODE_TAX_ORG");
            setParam.setString(2, person.getCode_tax_org());
            setParam.execute();
            setParam.setString(1, "P_NUMBER_TAX_REGISTRATION");
            setParam.setString(2, person.getNumber_tax_registration());
            setParam.execute();
            setParam.setString(1, "P_CODE_CITIZENSHIP");
            setParam.setString(2, person.getCode_citizenship());
            setParam.execute();
            setParam.setString(1, "P_BIRTH_PLACE");
            setParam.setString(2, person.getBirth_place() != null ? person.getBirth_place().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PHONE_HOME");
            setParam.setString(2, person.getPhone_home());
            setParam.execute();
            setParam.setString(1, "P_PHONE_MOBILE");
            setParam.setString(2, person.getPhone_mobile());
            setParam.execute();
            setParam.setString(1, "P_EMAIL_ADDRESS");
            setParam.setString(2, person.getEmail_address() != null ?
            		person.getEmail_address().toLowerCase() : "");
            setParam.execute();
            setParam.setString(1, "P_CODE_GENDER");
            setParam.setString(2, person.getCode_gender());
            setParam.execute();
            setParam.setString(1, "P_CODE_NATION");
            setParam.setString(2, person.getCode_nation());
            setParam.execute();
            setParam.setString(1, "P_CODE_BIRTH_REGION");
            setParam.setString(2, person.getCode_birth_region());
            setParam.execute();
            setParam.setString(1, "P_CODE_BIRTH_DISTR");
            setParam.setString(2, person.getCode_birth_distr());
            setParam.execute();
            setParam.setString(1, "P_TYPE_DOCUMENT");
            setParam.setString(2, person.getType_document());
            setParam.execute();
            setParam.setString(1, "P_PASSPORT_DATE_EXPIRATION");
            setParam.setString(2, dateToString(person.getPassport_date_expiration()));
            setParam.execute();
            setParam.setString(1, "P_CODE_ADR_REGION");
            setParam.setString(2, person.getCode_adr_region());
            setParam.execute();
            setParam.setString(1, "P_CODE_ADR_DISTR");
            setParam.setString(2, person.getCode_adr_distr());
            setParam.execute();
            setParam.setString(1, "P_INPS");
            setParam.setString(2, person.getInps());
            setParam.execute();
            setParam.setString(1, "P_PINFL");
            setParam.setString(2, person.getPinfl());
            setParam.execute();
            setParam.setString(1, "P_FAMILY");
            setParam.setString(2, person.getFamily() != null ? person.getFamily().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_FIRST_NAME");
            setParam.setString(2, person.getFirst_name() != null ? person.getFirst_name().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PATRONYMIC");
            setParam.setString(2, (person.getPatronymic() != null) ? person.getPatronymic().toUpperCase() : null);
            setParam.execute();
            setParam.setString(1, "P_FAMILY_LOCAL");
            setParam.setString(2, person.getFamily_local() != null ? person.getFamily_local().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_FIRST_NAME_LOCAL");
            setParam.setString(2, person.getFirst_name_local() != null ? person.getFirst_name_local().toUpperCase() : "");
            setParam.execute();
            setParam.setString(1, "P_PATRONYMIC_LOCAL");
            setParam.setString(2, (person.getPatronymic_local() != null) ? person.getPatronymic_local().toUpperCase() : null);
            setParam.execute();
            setParam.setString(1, "GROUP_ID");
            setParam.setString(2, "1"); //2022.08.25
            setParam.execute();
            setParam.setString(1, "S_DEAL_ID");
            setParam.setString(2, "2"); //2022.08.25
            setParam.execute();
            setParam.setString(1, "PARENT_ID_CLIENT_J");
            setParam.setString(2, (person.getParent_id_client_j() != null) ? person.getParent_id_client_j() : null); //2022.11.22
            setParam.execute();
            setParam.setString(1, "PERSON_ROLE");
            setParam.setString(2, (person.getPerson_role() != null) ? person.getPerson_role() : null); //2022.11.22
            setParam.execute();
            setParam.setString(1, "P_EMP_ID");
            setParam.setString(2, person.getEmp_id());
            setParam.execute();
            
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
}
