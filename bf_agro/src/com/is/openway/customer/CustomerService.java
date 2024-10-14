package com.is.openway.customer;

import java.util.Calendar;
import java.util.HashMap;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.openwayutils.utils.RefDataService;
import com.is.openwayutils.utils.FilterField;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.is.utils.Res;
import java.sql.SQLException;

import java.sql.Date;
import com.is.utils.RefData;
import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

import com.is.openway.PostUtils;
import com.is.openway.Utils;
import com.is.openway.XmlUtils;
import com.is.openway.model.AccInfo;
import com.is.openway.model.ActivityPeriod;
import com.is.openway.model.AddContractInfo;
import com.is.openway.model.Application;
import com.is.openway.model.ApplicationContract;
import com.is.openway.model.ApplicationContractCard;
import com.is.openway.model.BaseAddress;
import com.is.openway.model.CardInfo;
import com.is.openway.model.Client;
import com.is.openway.model.ClientAdd;
import com.is.openway.model.ClientIDT;
import com.is.openway.model.ClientInfo;
import com.is.openway.model.ClientInfoIdt;
import com.is.openway.model.ContractAdd;
import com.is.openway.model.ContractIDT;

import com.is.openway.model.ApplicationUpdContract;
import com.is.openway.model.DataAdd;
import com.is.openway.model.DataContract;
import com.is.openway.model.MsgDataAddClient;
import com.is.openway.model.MsgDataAddContract;
import com.is.openway.model.MsgDataAddContractCard;
import com.is.openway.model.MsgDataUpdContract;
import com.is.openway.model.ObjectForContract;
import com.is.openway.model.Phone;
import com.is.openway.model.PhoneList;
import com.is.openway.model.PlasticInfo;
import com.is.openway.model.ProductContract;
import com.is.openway.model.ProductionParms;
import com.is.openway.model.UFXMsgAddClient;
import com.is.openway.model.UFXMsgAddContractAcc;
import com.is.openway.model.UFXMsgAddContractCard;
import com.is.openway.model.UFXMsgReqClient;
import com.is.openway.model.UFXMsgReqClientResp;
import com.is.openway.model.InformationReq;
import com.is.openway.model.MsgDataReq;
import com.is.openway.model.ObjectFor;
import com.is.openway.model.Parm;
import com.is.openway.model.ResultDtls;
import com.is.openway.model.Source;
import com.is.openway.model.UFXMsgReqContractResp;
import com.is.openway.model.UFXMsgUpdContractAcc;

import com.is.openway.model.UFXMsgUpdClient;

//import com.is.openwayutils.utils.Utils;
import com.is.openwayutils.utils.CheckNull;
import com.is.ISLogger;
import com.is.ConnectionPool;
//import com.rabbitmq.client.AMQP.Confirm.Select;


public class CustomerService {

	private static String psql1;
	private static String psql2;
	private static String msql;
	private static String msql2;
	//private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	private static SimpleDateFormat mdf = new SimpleDateFormat("yyMMdd-HHmmss");
	private static List<RefData> listTypeDocument;
	private static List<RefData> listClientTypeWay4;
	private static List<RefData> listAccBal;
	private static List<RefData> listProductCode1Way4;
	private static List<RefData> listSubProductCode1Way4;
	private static HashMap<String, List<RefData>> listSubProductByProduct;
	private static List<RefData> listTax;
	private static List<RefData> listRegion;
	private static List<RefData> listDistr;
	private static HashMap<String, List<RefData>> listDistrByRegion;

	private static List<RefData> listCountry;
	private static List<RefData> listWayCert;
	private static List<RefData> listWayCountries;
	
	static {
		CustomerService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
		CustomerService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
		CustomerService.msql = "select * from ";
		CustomerService.msql2 = "v_bf_openway_customer";
	}

	public CustomerService() {
		// this.cvc = new CustomerViewCtrl();
	}

	public static Res doAction(final String un, final String pw,
			final Customer customer, final int actionid,
			final int utv_actionid, final String alias, final Boolean selfBranch) {
		Res res = null;
		//final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = /* get_alias_ho( */alias/* ) */;
		CallableStatement inf = null;
		CallableStatement getp = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		final ResultSet rs = null;
		final PreparedStatement ps = null;
		System.out.println("customer = " + customer);
		try {
			if (halias.compareTo(alias) == 0 && !selfBranch) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			getp = c.prepareCall("{? = call Param.GetParam(?) }");
			getp.registerOutParameter(1, 12);
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, 12);
			System.out.println("hm");
			if (customer.getId() != 0L) {
				System.out.println("id = " + customer.getId());
				cs.setString(1, "ID");
				cs.setLong(2, customer.getId());
				cs.execute();
			}
			cs.setString(1, "BRANCH");
			cs.setString(2, customer.getBranch());
			cs.execute();
			System.out.println("BRANCH = " + customer.getBranch());
			if (customer.getId() != 0L) {
				System.out.println("cl = " + customer.getId_client());
				cs.setString(1, "ID_CLIENT");
				cs.setString(2, customer.getId_client());
				cs.execute();
			}
			cs.setString(1, "NAME");
			cs.setString(2, customer.getName().toUpperCase());
			cs.execute();
			System.out.println("NAME = " + customer.getName());
			cs.setString(1, "CODE_COUNTRY");
			cs.setString(2, customer.getCode_country());
			cs.execute();
			System.out.println("CODE_COUNTRY = " + customer.getCode_country());
			cs.setString(1, "CODE_TYPE");
			cs.setString(2, customer.getCode_type());
			cs.execute();
			System.out.println("CODE_TYPE = " + customer.getCode_type());
			cs.setString(1, "CODE_RESIDENT");
			cs.setString(2, customer.getCode_resident());
			cs.execute();
			System.out
					.println("CODE_RESIDENT = " + customer.getCode_resident());
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			System.out.println("CODE_SUBJECT = " + customer.getCode_subject());
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(
					2,
					new StringBuilder(
							String.valueOf(customer.getSign_registr()))
							.toString());
			cs.execute();
			System.out.println("SIGN_REGISTR = " + customer.getSign_registr());
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			System.out.println("CODE_FORM = " + customer.getCode_form());
			cs.setString(1, "DATE_OPEN");
			cs.setString(
					2,
					(customer.getDate_open() != null) ? bdf.format(customer
							.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(
					2,
					(customer.getDate_close() != null) ? bdf.format(customer
							.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2,
					new StringBuilder(String.valueOf(customer.getState()))
							.toString());
			cs.execute();
			System.out.println("state = " + customer.getState());
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(
					2,
					(customer.getP_birthday() != null) ? bdf.format(customer
							.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			System.out.println("P_POST_ADDRESS = "
					+ customer.getP_post_address());
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			System.out.println("P_PASSPORT_TYPE = "
					+ customer.getP_passport_type());
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			System.out.println("P_PASSPORT_SERIAL = "
					+ customer.getP_passport_serial());
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			System.out.println("P_PASSPORT_NUMBER = "
					+ customer.getP_passport_number());
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			System.out.println("P_PASSPORT_PLACE_REGISTRATION = "
					+ customer.getP_passport_place_registration());
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(
					2,
					(customer.getP_passport_date_registration() != null) ? bdf
							.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			System.out.println("P_CODE_TAX_ORG = "
					+ customer.getP_code_tax_org());
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			System.out.println("P_NUMBER_TAX_REGISTRATION ="
					+ customer.getP_number_tax_registration());
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			System.out.println("P_CODE_BANK = " + customer.getP_code_bank());
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			System.out.println("P_CODE_CLASS_CREDIT = "
					+ customer.getP_code_class_credit());
			cs.setString(1, "P_CODE_CITIZENSHIP");
			cs.setString(2, customer.getP_code_citizenship());
			cs.execute();
			cs.setString(1, "P_BIRTH_PLACE");
			cs.setString(2, customer.getP_birth_place());
			cs.execute();
			cs.setString(1, "P_CODE_CAPACITY");
			cs.setString(2, customer.getP_code_capacity());
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_DATE");
			cs.setString(
					2,
					(customer.getP_capacity_status_date() != null) ? bdf
							.format(customer.getP_capacity_status_date())
							: null);
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_PLACE");
			cs.setString(2, customer.getP_capacity_status_place());
			cs.execute();
			cs.setString(1, "P_NUM_CERTIF_CAPACITY");
			cs.setString(2, customer.getP_num_certif_capacity());
			cs.execute();
			cs.setString(1, "P_PHONE_HOME");
			cs.setString(2, customer.getP_phone_home());
			cs.execute();
			cs.setString(1, "P_PHONE_MOBILE");
			cs.setString(2, customer.getP_phone_mobile());
			cs.execute();
			cs.setString(1, "P_EMAIL_ADDRESS");
			cs.setString(2, customer.getP_email_address());
			cs.execute();
			cs.setString(1, "P_PENSION_SERTIF_SERIAL");
			cs.setString(2, customer.getP_pension_sertif_serial());
			cs.execute();
			cs.setString(1, "P_CODE_GENDER");
			cs.setString(2, customer.getP_code_gender());
			cs.execute();
			cs.setString(1, "P_CODE_NATION");
			cs.setString(2, customer.getP_code_nation());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_REGION");
			cs.setString(2, customer.getP_code_birth_region());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_DISTR");
			cs.setString(2, customer.getP_code_birth_distr());
			cs.execute();
			cs.setString(1, "P_TYPE_DOCUMENT");
			cs.setString(2, customer.getP_type_document());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
			cs.setString(
					2,
					(customer.getP_passport_date_expiration() != null) ? bdf
							.format(customer.getP_passport_date_expiration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_ADR_REGION");
			cs.setString(2, customer.getP_code_adr_region());
			cs.execute();
			cs.setString(1, "P_CODE_ADR_DISTR");
			cs.setString(2, customer.getP_code_adr_distr());
			cs.execute();
			cs.setString(1, "P_INPS");
			cs.setString(2, customer.getP_inps());
			cs.execute();
			cs.setString(1, "P_PINFL");
			cs.setString(2, customer.getP_pinfl());
			cs.execute();
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family().toUpperCase());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name().toUpperCase());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic().toUpperCase());
			cs.execute();
			cs.setString(1, "P_ZIP_CODE");
			cs.setString(2, customer.getP_zip_code());
			cs.execute();
			System.out.println("action id = " + actionid);
			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();
			ccs.execute();
			final String id = ccs.getString(1);
			ccs = c.prepareCall("{? = call Param.getparam('ID_CLIENT') }");
			ccs.registerOutParameter(1, 12);
			ccs.execute();
			final String client_id = ccs.getString(1);
			System.out.println("RETURNDED CLIENT = " + client_id);
			System.out.println("RETURN ID = " + id);
			if (utv_actionid != 0) {
				cs.setString(1, "ID");
				cs.setString(2, id);
				cs.execute();
				cs.setString(1, "ID_CLIENT");
				cs.setString(2, client_id);
				cs.execute();
				System.out.println("cl_id = " + client_id);
				System.out.println("utv_actionid = " + utv_actionid);
				acs.setInt(1, 1);
				acs.setInt(2, 2);
				acs.setInt(3, utv_actionid);
				acs.execute();
			}
			c.commit();
			res = new Res(0, id);
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			
			e.printStackTrace();
			Utils.rollback(c);
			res = new Res(-1, e.getMessage());
			return res;
		} finally {
			Utils.close(ccs);
			Utils.close(acs);
			Utils.close(cs);
			Utils.close(getp);
			Utils.close(inf);
			Utils.close(ps);
			Utils.close(rs);
			ConnectionPool.close(c);
		}
		Utils.close(ccs);
		Utils.close(acs);
		Utils.close(cs);
		Utils.close(getp);
		Utils.close(inf);
		Utils.close(ps);
		Utils.close(rs);
		ConnectionPool.close(c);
		return res;
	}

	public static Res doAction_utv(final String un, final String pw,
			final Customer customer, final int actionid, final String alias,
			final Boolean selfBranch) {
		Res res = null;
		//final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = /* get_alias_ho( */alias/* ) */;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		try {
			if (halias.compareTo(alias) == 0 && !selfBranch) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, 12);
			cs.setString(1, "ID");
			cs.setLong(2, customer.getId());
			cs.execute();
			cs.setString(1, "BRANCH");
			cs.setString(2, customer.getBranch());
			cs.execute();
			cs.setString(1, "ID_CLIENT");
			cs.setString(2, customer.getId_client());
			cs.execute();
			cs.setString(1, "NAME");
			cs.setString(2, customer.getName());
			cs.execute();
			cs.setString(1, "CODE_COUNTRY");
			cs.setString(2, customer.getCode_country());
			cs.execute();
			cs.setString(1, "CODE_TYPE");
			cs.setString(2, customer.getCode_type());
			cs.execute();
			cs.setString(1, "CODE_RESIDENT");
			cs.setString(2, customer.getCode_resident());
			cs.execute();
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(
					2,
					new StringBuilder(
							String.valueOf(customer.getSign_registr()))
							.toString());
			cs.execute();
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			cs.setString(1, "DATE_OPEN");
			cs.setString(
					2,
					(customer.getDate_open() != null) ? bdf.format(customer
							.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(
					2,
					(customer.getDate_close() != null) ? bdf.format(customer
							.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2,
					new StringBuilder(String.valueOf(customer.getState()))
							.toString());
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(
					2,
					(customer.getP_birthday() != null) ? bdf.format(customer
							.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(
					2,
					(customer.getP_passport_date_registration() != null) ? bdf
							.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			cs.setString(1, "P_CODE_CITIZENSHIP");
			cs.setString(2, customer.getP_code_citizenship());
			cs.execute();
			cs.setString(1, "P_BIRTH_PLACE");
			cs.setString(2, customer.getP_birth_place());
			cs.execute();
			cs.setString(1, "P_CODE_CAPACITY");
			cs.setString(2, customer.getP_code_capacity());
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_DATE");
			cs.setString(
					2,
					(customer.getP_capacity_status_date() != null) ? bdf
							.format(customer.getP_capacity_status_date())
							: null);
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_PLACE");
			cs.setString(2, customer.getP_capacity_status_place());
			cs.execute();
			cs.setString(1, "P_NUM_CERTIF_CAPACITY");
			cs.setString(2, customer.getP_num_certif_capacity());
			cs.execute();
			cs.setString(1, "P_PHONE_HOME");
			cs.setString(2, customer.getP_phone_home());
			cs.execute();
			cs.setString(1, "P_PHONE_MOBILE");
			cs.setString(2, customer.getP_phone_mobile());
			cs.execute();
			cs.setString(1, "P_EMAIL_ADDRESS");
			cs.setString(2, customer.getP_email_address());
			cs.execute();
			cs.setString(1, "P_PENSION_SERTIF_SERIAL");
			cs.setString(2, customer.getP_pension_sertif_serial());
			cs.execute();
			cs.setString(1, "P_CODE_GENDER");
			cs.setString(2, customer.getP_code_gender());
			cs.execute();
			cs.setString(1, "P_CODE_NATION");
			cs.setString(2, customer.getP_code_nation());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_REGION");
			cs.setString(2, customer.getP_code_birth_region());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_DISTR");
			cs.setString(2, customer.getP_code_birth_distr());
			cs.execute();
			cs.setString(1, "P_TYPE_DOCUMENT");
			cs.setString(2, customer.getP_type_document());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
			cs.setString(
					2,
					(customer.getP_passport_date_expiration() != null) ? bdf
							.format(customer.getP_passport_date_expiration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_ADR_REGION");
			cs.setString(2, customer.getP_code_adr_region());
			cs.execute();
			cs.setString(1, "P_CODE_ADR_DISTR");
			cs.setString(2, customer.getP_code_adr_distr());
			cs.execute();
			cs.setString(1, "P_INPS");
			cs.setString(2, customer.getP_inps());
			cs.execute();
			cs.setString(1, "P_PINFL");
			cs.setString(2, customer.getP_pinfl());
			cs.execute();
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic());
			cs.execute();
			cs.setString(1, "P_ZIP_CODE");
			cs.setString(2, customer.getP_zip_code());
			cs.execute();

			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();
			c.rollback();
			ccs.execute();
			res = new Res(0, ccs.getString(1));
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			
			res = new Res(-1, e.getMessage());
			return res;
		} finally {
			Utils.close(ccs);
			Utils.close(acs);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static String doAction(final String un, final String pw,
			final String branch, final String id, final int actionid,
			final String alias, final Boolean selfBranch) {
		String res = "";
		//final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = /* get_alias_ho( */alias/* ) */;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (halias.compareTo(alias) == 0 && !selfBranch) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer WHERE branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ccs.execute();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
					final String cn = rs.getMetaData().getColumnName(i);
					if (rs.getString(cn) != null) {
						cs.setString(1, cn);
						if (rs.getMetaData().getColumnTypeName(i)
								.equals("DATE")) {
							cs.setString(2, bdf.format(rs.getDate(cn)));
						} else {
							cs.setString(2, rs.getString(cn));
						}
						cs.execute();
					}
				}
				acs.setInt(1, 2);
				acs.setInt(2, 2);
				acs.setInt(3, actionid);
				acs.execute();
				c.commit();
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			
			res = e.getMessage();
			return res;
		} finally {
			Utils.close(acs);
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(ccs);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	private static String getCond(final List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		}
		return " where ";
	}

	private static List<FilterField> getFilterFields(final CustomerFilter filter) {
		final List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(Long.valueOf(filter.getId()))) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id=?", (Object) filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id_client=?", (Object) filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "branch=?", (Object) filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "upper(name) like ?", (Object) ("%"
					+ filter.getName().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_country=?", (Object) filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_type=?", (Object) filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_resident=?", (Object) filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_subject=?", (Object) filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "sign_registr=?", (Object) filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_form=?", (Object) filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "date_open=?", (Object) filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "date_close =?", (Object) filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "state=?", (Object) filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_birthday=?", (Object) new Date(filter.getP_birthday()
					.getTime())));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_post_address=?", (Object) filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_type=?", (Object) filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(p_passport_serial=? or p_passport_serial||p_passport_number = ?)", 
					(Object) filter
					.getP_passport_serial()));
			flfields.add(new FilterField("", (Object) (
					 filter.getP_passport_serial() )));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_number=?", (Object) filter
					.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_place_registration=?", (Object) filter
					.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_registration=?", (Object) filter
					.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_tax_org=?", (Object) filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_number_tax_registration=?", (Object) filter
					.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_bank=?", (Object) filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_class_credit=?", (Object) filter
					.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_citizenship=?", (Object) filter
					.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_birth_place=?", (Object) filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_capacity=?", (Object) filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_capacity_status_date=?", (Object) filter
					.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_capacity_status_place=?", (Object) filter
					.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_num_certif_capacity=?", (Object) filter
					.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_phone_home=?", (Object) filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_phone_mobile=?", (Object) filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_email_address=?", (Object) filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_pension_sertif_serial=?", (Object) filter
					.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_gender=?", (Object) filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_nation=?", (Object) filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_birth_region=?", (Object) filter
					.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_birth_distr=?", (Object) filter
					.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_type_document=?", (Object) filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_expiration=?", (Object) filter
					.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_region=?", (Object) filter
					.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_distr=?", (Object) filter
					.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_inps=?", (Object) filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_pinfl like ?", "%"+(Object) filter.getP_pinfl()+"%"));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like ?"
					+ " or upper(p_family) like ?)", (Object) ("%"
					+ filter.getP_family().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_family().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like ?"
					+ " or upper(p_first_name) like ?)", (Object) ("%"
					+ filter.getP_first_name().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_first_name().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like ?"
					+ " or upper(p_patronymic) like ?)", (Object) ("%"
					+ filter.getP_patronymic().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_patronymic().toUpperCase() + "%")));
		}
		String notNull = "";
		if (!CheckNull.isEmpty(filter.getTietoIdIsNotNull())) {
			notNull = " and tieto_customer_id is not null ";
		}
		if (!CheckNull.isEmpty(filter.getTieto_customer_id())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "tieto_customer_id=?", (Object) filter
					.getTieto_customer_id()));
		}
		flfields.add(new FilterField(String.valueOf(getCond(flfields))
				+ "rownum<? " + notNull, (Object) 1001));
		/*if (!(filter.getCard() == null)) {
			if (!filter.getCard().matches("[0-9]+")) {
				if (filter.getCard().contains("%")) {
					flfields.add(new FilterField(
							String.valueOf(getCond(flfields))
									+ "id_client in (select client_b from humo_cards c where c.branch = "
									+ filter.getBranch()
									+ " and c.card like ?)", filter.getCard()));
				} else {
					flfields.add(new FilterField(
							String.valueOf(getCond(flfields))
									+ "id_client in (select client_b from humo_cards c where c.branch = "
									+ filter.getBranch() + " and c.card = ?)",
							filter.getCard()));
				}
			} else {
				flfields.add(new FilterField(
						String.valueOf(getCond(flfields))
								+ "id_client in (select client_b from humo_cards c where c.branch = "
								+ filter.getBranch() + " and c.real_card = ?)",
						filter.getCard()));
			}
		}*/
		return flfields;
	}

	public static int getCount(final CustomerFilter filter, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		int n = 0;
		final List<FilterField> flFields = getFilterFields(filter);
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_bf_humo_customer");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); ++k) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return n;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(inf);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return n;
	}

	public static UFXMsgReqClient makeClientInquiry(String pClientNumber, String pPinfl, String pUrl) {
		UFXMsgReqClient cl = new UFXMsgReqClient();
		// cl.setDirection("Rq");
		// cl.setMsg_type("Application");
		// cl.setScheme("WAY4Appl");
		// cl.setVersion("2.3.81");
		cl.setMsgId("sssssss");

		Source src = new Source();
		// src.setApp("UZPSB");
		cl.setSource(src);

		MsgDataReq msgData = new MsgDataReq();
		InformationReq inf = new InformationReq();
		inf.setRegNumber("12312300345");
	
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Client");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		inf.setResultDtls(rdtl);
		ObjectFor objFor = new ObjectFor();
		ClientIDT clIdt = new ClientIDT();
		ClientInfoIdt clInfIdt = new ClientInfoIdt();
		clInfIdt.setClientNumber(pClientNumber);
		clInfIdt.setSocialNumber(pPinfl);
		clIdt.setClientInfo(clInfIdt);
		objFor.setClientIDT(clIdt);
		inf.setObjectFor(objFor);
		msgData.setInformationObject(inf);
		cl.setMsgDataReq(msgData);
		return cl;

	}
	public static UFXMsgReqClient makeContractInquiry(String pClientNumber, String pPinfl, String pUrl) {
		UFXMsgReqClient cl = new UFXMsgReqClient();
		// cl.setDirection("Rq");
		// cl.setMsg_type("Application");
		// cl.setScheme("WAY4Appl");
		// cl.setVersion("2.3.81");
		cl.setMsgId("sssssss");

		Source src = new Source();
		// src.setApp("UZPSB");
		cl.setSource(src);

		MsgDataReq msgData = new MsgDataReq();
		InformationReq inf = new InformationReq();
		inf.setRegNumber("12312300345");
	    inf.setObjectType("Contract");
	    inf.setActionType("Inquiry");
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Status");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		inf.setResultDtls(rdtl);
		ObjectFor objFor = new ObjectFor();
		ClientIDT clIdt = new ClientIDT();
		ClientInfoIdt clInfIdt = new ClientInfoIdt();
		clInfIdt.setClientNumber(pClientNumber);
		clInfIdt.setSocialNumber(pPinfl);
		clIdt.setClientInfo(clInfIdt);
		objFor.setClientIDT(clIdt);
		inf.setObjectFor(objFor);
		msgData.setInformationObject(inf);
		cl.setMsgDataReq(msgData);
		return cl;

	}
	
	public static UFXMsgAddClient makeAddClient(Customer cust, String alias) {
		UFXMsgAddClient addCl = new UFXMsgAddClient();
		addCl.setMsgId("ADD-NEW-CLIENT-01142-001");
		Source src = new Source();
		addCl.setSource(src);

		MsgDataAddClient msgData = new MsgDataAddClient();
		Application app = new Application();

		//например ADDCL_MADAD_20211206_XXXX, где XXX Ц пор€дковый номер запроса в течение дн€
		
		
		app.setRegNumber("ADDCL-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+
				//Strings.padStart(""+getSeqApplRegNumber(), 4, '0')
	             String.format("%04d", getSeqApplRegNumber()) 
		);
		//.replace(' ', '0');
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Response");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		DataAdd dataObj = new DataAdd();
		ClientAdd client = new ClientAdd();
		client.setClientType(cust.getO_client_type());
		ClientInfo clInf = new ClientInfo();
		clInf.setClientNumber(cust.getBranch()+cust.getId_client());
		clInf.setRegNumberType(getWayCertCode(cust.getP_type_document(), alias));
		clInf.setRegNumber(cust.getP_passport_serial()+cust.getP_passport_number());
		clInf.setRegNumberDetails(cust.getP_passport_place_registration()+", "+bdf.format(cust.getP_passport_date_registration())); 
		clInf.setSocialNumber(cust.getP_pinfl());
		clInf.setShortName(cust.getP_family()+" "+cust.getP_first_name()+" "+cust.getP_patronymic());
		clInf.setTaxpayerIdentifier(cust.getP_number_tax_registration());
		clInf.setFirstName(cust.getP_first_name());
		clInf.setLastName(cust.getP_family());
		clInf.setSecurityName(cust.getO_security_name());
		clInf.setCountry(getWayCountryCode(cust.getCode_country(), alias));		
		clInf.setCitizenship(getWayCountryCode(cust.getP_code_citizenship(), alias));	
		//clInf.setLanguage(cust.get);
		//clInf.setMaritalStatus(cust.get);
		//clInf.setPosition(cust.get);
		//clInf.setCompanyName(cust.get);
		//clInf.setCompanyTradeName(cust.get);
		clInf.setBirthDate( df.format(cust.getP_birthday()));
		clInf.setBirthPlace(cust.getP_birth_place());
		//clInf.setBirthName(cust.get);
		clInf.setGender( (cust.getP_code_gender()=="1" || cust.getP_code_gender().equals("1")) ? "Male" : "Female" );
		client.setClientInfo(clInf);
		PlasticInfo plInfo = new PlasticInfo();
		plInfo.setFirstName(cust.getP_first_name());
		plInfo.setLastName(cust.getP_family());
		client.setPlasticInfo(plInfo);
		PhoneList phoneList = new PhoneList();
		ArrayList < Phone > phoneArrList = new ArrayList<Phone>();
		if (cust.getP_phone_home()!=null) {
		  Phone phone = new Phone();
		  phone.setPhoneType("Home");
		  phone.setPhoneNumber(cust.getP_phone_home());
		  phoneArrList.add(phone);
		}
		Phone phone = new Phone();
		phone.setPhoneType("Mobile");
		phone.setPhoneNumber(cust.getP_phone_mobile());
		phoneArrList.add(phone);
		phoneList.setPhoneListObject(phoneArrList);
		client.setPhoneList(phoneList);
		//client.setDateOpen(df.format(Calendar.getInstance().getTime()) );
		client.setDateOpen(df.format(Utils.getInfoDate(alias, new Res())));
		BaseAddress baseAddr = new BaseAddress();
		baseAddr.setEMail(cust.getP_email_address());
		baseAddr.setCity(cust.getO_city());
		baseAddr.setPostalCode(cust.getP_zip_code());
		baseAddr.setAddressLine1(cust.getP_post_address());
		baseAddr.setAddressLine2(cust.getO_post_address_fact());
		ActivityPeriod actAddrPeriod = new ActivityPeriod();
		actAddrPeriod.setDateFrom(df.format(cust.getO_address_fact_date()));
		
		baseAddr.setActivityPeriodObject(actAddrPeriod);
		client.setBaseAddress(baseAddr);
		dataObj.setClient(client);
		app.setData(dataObj);
		msgData.setApplication(app);
		addCl.setMsgDataReq(msgData);
		return addCl;

	}
	
	public static UFXMsgUpdClient makeUpdClient(Customer cust, String alias) {
		UFXMsgUpdClient addCl = new UFXMsgUpdClient();
		addCl.setMsgId("UPD-EXS-CLIENT-01142-001");
		Source src = new Source();
		addCl.setSource(src);

		MsgDataAddClient msgData = new MsgDataAddClient();
		Application app = new Application();

		//например ADDCL_MADAD_20211206_XXXX, где XXX Ц пор€дковый номер запроса в течение дн€
		
		
		app.setRegNumber("UPDCL-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+
				//Strings.padStart(""+getSeqApplRegNumber(), 4, '0')
	             String.format("%04d", getSeqApplRegNumber()) 
		);
		app.setActionType("Update");
	
		ObjectFor objFor = new ObjectFor();
		ClientIDT clIdt = new ClientIDT();
		ClientInfoIdt clInfIdt = new ClientInfoIdt();
		clInfIdt.setClientNumber(cust.getBranch() + cust.getId_client());
		clInfIdt.setSocialNumber(cust.getP_pinfl());
		clIdt.setClientInfo(clInfIdt);
		objFor.setClientIDT(clIdt);
		app.setObjectFor(objFor);
		
		DataAdd dataObj = new DataAdd();
		ClientAdd client = new ClientAdd();
		client.setClientType(cust.getO_client_type());
		ClientInfo clInf = new ClientInfo();
		clInf.setClientNumber(cust.getBranch() + cust.getId_client());
		clInf.setRegNumberType(getWayCertCode(cust.getP_type_document(), alias));
		clInf.setRegNumber(cust.getP_passport_serial()+cust.getP_passport_number());
		clInf.setRegNumberDetails(cust.getP_passport_place_registration()+", "+bdf.format(cust.getP_passport_date_registration())); 
		clInf.setSocialNumber(cust.getP_pinfl());
		clInf.setShortName(cust.getP_family()+" "+cust.getP_first_name()+" "+cust.getP_patronymic());
		clInf.setTaxpayerIdentifier(cust.getP_number_tax_registration());
		clInf.setFirstName(cust.getP_first_name());
		clInf.setLastName(cust.getP_family());
		clInf.setSecurityName(cust.getO_security_name());
		clInf.setCountry(getWayCountryCode(cust.getCode_country(), alias));		
		clInf.setCitizenship(getWayCountryCode(cust.getP_code_citizenship(), alias));	
		//clInf.setLanguage(cust.get);
		//clInf.setMaritalStatus(cust.get);
		//clInf.setPosition(cust.get);
		//clInf.setCompanyName(cust.get);
		//clInf.setCompanyTradeName(cust.get);
		clInf.setBirthDate( df.format(cust.getP_birthday()));
		clInf.setBirthPlace(cust.getP_birth_place());
		//clInf.setBirthName(cust.get);
		clInf.setGender( (cust.getP_code_gender()=="1" || cust.getP_code_gender().equals("1")) ? "Male" : "Female" );
		client.setClientInfo(clInf);
		PlasticInfo plInfo = new PlasticInfo();
		plInfo.setFirstName(cust.getP_first_name());
		plInfo.setLastName(cust.getP_family());
		client.setPlasticInfo(plInfo);
		PhoneList phoneList = new PhoneList();
		ArrayList < Phone > phoneArrList = new ArrayList<Phone>();
		if (cust.getP_phone_home()!=null) {
		  Phone phone = new Phone();
		  phone.setPhoneType("Home");
		  phone.setPhoneNumber(cust.getP_phone_home());
		  phoneArrList.add(phone);
		}
		Phone phone = new Phone();
		phone.setPhoneType("Mobile");
		phone.setPhoneNumber(cust.getP_phone_mobile());
		phoneArrList.add(phone);
		phoneList.setPhoneListObject(phoneArrList);
		client.setPhoneList(phoneList);
		//client.setDateOpen(df.format(Calendar.getInstance().getTime()) );
		client.setDateOpen(df.format(Utils.getInfoDate(alias, new Res())));
		BaseAddress baseAddr = new BaseAddress();
		baseAddr.setEMail(cust.getP_email_address());
		baseAddr.setCity(cust.getO_city());
		baseAddr.setPostalCode(cust.getP_zip_code());
		baseAddr.setAddressLine1(cust.getP_post_address());
		baseAddr.setAddressLine2(cust.getO_post_address_fact());
		ActivityPeriod actAddrPeriod = new ActivityPeriod();
		actAddrPeriod.setDateFrom(df.format(cust.getO_address_fact_date()));
		
		baseAddr.setActivityPeriodObject(actAddrPeriod);
		client.setBaseAddress(baseAddr);
		dataObj.setClient(client);
		app.setData(dataObj);
		msgData.setApplication(app);
		addCl.setMsgDataReq(msgData);
		return addCl;

	}
	
	public static UFXMsgAddContractAcc makeAddContract(AccInfo acc, String alias) {
		UFXMsgAddContractAcc addCl = new UFXMsgAddContractAcc();
		addCl.setMsgId("ADD-CONTRACT-ACC-01142-001");
		Source src = new Source();
		addCl.setSource(src);

		MsgDataAddContract msgData = new MsgDataAddContract();
		ApplicationContract app = new ApplicationContract();

		//например ADDCL_MADAD_20211206_XXXX, где XXX Ц пор€дковый номер запроса в течение дн€
			
		app.setRegNumber("ADDCONTRACTACC-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+
				//Strings.padStart(""+getSeqApplRegNumber(), 4, '0')
	             String.format("%04d", getSeqApplRegNumber()) 
		);
	
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Response");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		ObjectFor objFor = new ObjectFor();
		ClientIDT clIdt = new ClientIDT();
		ClientInfoIdt clInfIdt = new ClientInfoIdt();
		clInfIdt.setClientNumber(acc.getBranch()+acc.getClient());
		clInfIdt.setSocialNumber(acc.getSocialNumber());
		clIdt.setClientInfo(clInfIdt);
		objFor.setClientIDT(clIdt);
		app.setObjectFor(objFor);
		
		DataContract dataObj = new DataContract();
		ContractAdd contr = new ContractAdd();
		ContractIDT contrIdt = new ContractIDT();
		contrIdt.setRBSNumber(acc.getRbsNumberIbs());
		contr.setContractIDT(contrIdt);
		contr.setContractName(acc.getContractName());
		contr.setCommentText(acc.getCommentText());
		
		ProductContract product = new ProductContract();
		product.setProductCode1(acc.getProductCode1());
		contr.setProduct(product);
		
		//contr.setDateOpen(df.format(Utils.getInfoDate(alias, new Res())));
		contr.setDateOpen(df.format(Calendar.getInstance().getTime()));
		
		AddContractInfo contrInf= new AddContractInfo();
		//contrInf.setAddInfo01("ACC_NUM="+acc.getBranch()+acc.getId());
		contrInf.setAddInfo01("CKC="+acc.getBranch()+acc.getId());
		contr.setAddContractInfo(contrInf);

		dataObj.setContract(contr);
		app.setData(dataObj);
		msgData.setApplication(app);
		addCl.setMsgDataReq(msgData);
		return addCl;
	}
	
	public static UFXMsgUpdContractAcc makeUpdContract(AccInfo acc, String alias) {
		UFXMsgUpdContractAcc addCl = new UFXMsgUpdContractAcc();
		addCl.setMsgId("UPD-CONTRACT-ACC-01142-001");
		Source src = new Source();
		addCl.setSource(src);

		MsgDataUpdContract msgData = new MsgDataUpdContract();
		ApplicationUpdContract app = new ApplicationUpdContract();
			
		app.setRegNumber("UPDCONTRACTACC-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+
				//Strings.padStart(""+getSeqApplRegNumber(), 4, '0')
	             String.format("%04d", getSeqApplRegNumber()) 
		);
	
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Response");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contrIdt1 = new ContractIDT();
		contrIdt1.setContractNumber(acc.getContractNumber());
		Client clt = new Client();
		ClientInfo cltInfo = new ClientInfo();
		cltInfo.setClientNumber(/*acc.getBranch()+*/acc.getClientNumber());
		cltInfo.setSocialNumber(acc.getSocialNumber());
		clt.setClientInfo(cltInfo);
		contrIdt1.setClientObject(clt);
		ClientInfoIdt clInfIdt = new ClientInfoIdt();
		clInfIdt.setClientNumber(acc.getBranch()+acc.getClient());
		clInfIdt.setSocialNumber(acc.getSocialNumber());
		objFor.setContractIDT(contrIdt1);
		app.setObjectFor(objFor);
		
		DataContract dataObj = new DataContract();
		ContractAdd contr = new ContractAdd();
		ContractIDT contrIdt2 = new ContractIDT();
		contrIdt2.setRBSNumber(acc.getRbsNumberIbs());
		contr.setContractIDT(contrIdt2);
		contr.setContractName(acc.getContractName());
		contr.setCommentText(acc.getCommentText());
		
		ProductContract product = new ProductContract();
		product.setProductCode1(acc.getProductCode1());
		contr.setProduct(product);
		
		//contr.setDateOpen(df.format(Utils.getInfoDate(alias, new Res())));
		contr.setDateOpen(df.format(Calendar.getInstance().getTime()));
		
		AddContractInfo contrInf= new AddContractInfo();
		//contrInf.setAddInfo01("ACC_NUM="+acc.getBranch()+acc.getId());
		contrInf.setAddInfo01("CKC="+acc.getBranch()+acc.getId());
		contr.setAddContractInfo(contrInf);

		dataObj.setContract(contr);
		app.setData(dataObj);
		msgData.setApplication(app);
		addCl.setMsgDataReq(msgData);
		return addCl;
	}
	
	public static UFXMsgAddContractCard makeAddContractCard(CardInfo card, String alias) {
		UFXMsgAddContractCard addCl = new UFXMsgAddContractCard();
		addCl.setMsgId("ADD-CONTRACT-CARD-01142-001");
		Source src = new Source();
		addCl.setSource(src);

		MsgDataAddContractCard msgData = new MsgDataAddContractCard();
		ApplicationContractCard app = new ApplicationContractCard();
	
		app.setRegNumber("ADDCONTRACTCARD-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+
	             String.format("%04d", getSeqApplRegNumber()) 
		);
	
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Response");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contIdt = new ContractIDT();
		contIdt.setContractNumber(card.getContractNumber());
		Client clt= new Client();
		ClientInfo cltInfo = new ClientInfo(); 
		cltInfo.setClientNumber(card.getBranch()+card.getClient_id());
		cltInfo.setSocialNumber(card.getSocialNumber());
		clt.setClientInfo(cltInfo);
		contIdt.setClientObject(clt);
		objFor.setContractIDT(contIdt);
		app.setObjectFor(objFor);
				
		DataContract dataObj = new DataContract();
		ContractAdd contr = new ContractAdd();
		ContractIDT contrIdt = new ContractIDT();
		contrIdt.setRBSNumber(card.getRbsNumberIbs());
		contr.setContractIDT(contrIdt);
		contr.setContractName(card.getContractName());
		contr.setCommentText(card.getCommentText());
		
		ProductContract product = new ProductContract();
		product.setProductCode1(card.getProductCode1());
		contr.setProduct(product);
		
		//ProductionParms prodParms = new ProductionParms();
		//prodParms.setCardExpiry("2406");
		//contr.setProductionParmsObject(prodParms);
		
		PlasticInfo plInfo = new PlasticInfo();
		plInfo.setFirstName(card.getFirstName());
		plInfo.setLastName(card.getLastName());
		contr.setPlasticInfo(plInfo);
		
		//contr.setDateOpen(df.format(Utils.getInfoDate(alias, new Res())));
		contr.setDateOpen(df.format(Calendar.getInstance().getTime()));
		dataObj.setContract(contr);
		app.setData(dataObj);
		msgData.setApplication(app);
		addCl.setMsgDataReq(msgData);
		return addCl;

	}
	
	public static void prepareFakeValues(Customer cust) {
		cust.setCode_country("860");
		cust.setP_type_document("6");
		cust.setP_passport_serial("AA");
		cust.setP_passport_number("4901588");
		try {
		cust.setP_passport_date_registration(bdf.parse("28.03.2014"));
		cust.setP_passport_date_expiration(bdf.parse("27.03.2024"));
		cust.setP_birthday(bdf.parse("22.04.1965"));
		cust.setO_address_fact_date(bdf.parse("01.01.2000"));
		} catch (Exception ex) {
        }
		cust.setP_passport_place_registration("TOSHKENT SHAHAR MIROBOD TUMANI IIB");
		cust.setP_pinfl("32204650200011");
		cust.setP_family("KARAJANOV");
		cust.setP_first_name("NORMAKHAMMAD");
		cust.setP_patronymic("ABDIEVICH");
		cust.setP_number_tax_registration("493125971");
		cust.setO_security_name("SECURITY");
		cust.setP_birth_place("SAMARQAND VILOYATI");
		cust.setP_code_gender("1");
		cust.setP_phone_mobile("998909372174");
		cust.setP_email_address("aaa@uz");
		cust.setP_code_adr_region("26");
		cust.setP_code_adr_distr("198");
		cust.setO_city("TASHKENT");
		cust.setP_zip_code("1111");
		cust.setP_post_address("Katta Mirobod d 47 kv 3");
		cust.setO_post_address_fact("dumbira obod");
		cust.setP_code_nation("01");
		
	}
	
	public static UFXMsgReqClientResp getCustomers_openway(String pBranch, String pClientId, String pPinfl, String pUrl, final String alias) {
		UFXMsgReqClientResp clResp = null;
		ISLogger.getLogger().error("getCustomers_openway: "
				+ pBranch+","+pClientId+","+pPinfl+","+pUrl+","+alias);
		
		// maqsad list of customers yoki bitta listni qaytarib berishimiz kerak
		// pinfl bush bulsa xato qaytaramiz:
		if (pPinfl==null || pPinfl=="" || pPinfl.equals("")){
			clResp= new UFXMsgReqClientResp();
			clResp.set_resp_code("-1");
			clResp.set_resp_text("ѕараметр ѕ»Ќ‘Ћ пустой.");
			return clResp;
		}
		
		// request uchun xml string tayyorlaymiz
		// CustomerFilter ni bersak ClientInquiry klass yasaydigan funksiya
		UFXMsgReqClient cl = makeClientInquiry(pBranch + pClientId, pPinfl, pUrl);
		// ba'zi polyalarni urnatamiz agar kerak bulsa

		// class dan xml string qaytaradigan funksiya
		XmlUtils xmlUtils = new XmlUtils();
		String v_xml = "_";
		try {
			v_xml = xmlUtils.serializeXmlFromObject(cl);
		} catch (Exception e) {
			
			System.out.println("error serialize xml " + e.getMessage());
			ISLogger.getLogger().error("error serialize xml " + e.getMessage());
			
			if (clResp==null)
				clResp= new UFXMsgReqClientResp();
			clResp.set_resp_code("-1");
			clResp.set_resp_text("error serialize xml " + e.getMessage());
			return clResp;
		}
		ISLogger.getLogger().error("getCustomers_openway v_xml: " + v_xml);
		
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		//String v_url = "http://213.230.121.32:8090";
		String v_res2 = "";
		try {
			v_res2 = postUtils.sendData(pUrl, v_xml);
			System.out.println("v_res2 = " + v_res2);
			ISLogger.getLogger().error("getCustomers_openway v_res2: " + v_res2);
			
		} catch (Exception e) {
			System.out.println("postUtils.sendData err " + e.getMessage());
			ISLogger.getLogger().error("postUtils.sendData err " + e.getMessage());
			if (clResp==null)
				clResp= new UFXMsgReqClientResp();
			clResp.set_resp_code("-1");
			clResp.set_resp_text("postUtils.sendData err " + e.getMessage());
			return clResp;
		}

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2, UFXMsgReqClientResp.class);
		} catch (Exception e) {
			ISLogger.getLogger().error("xmlMapper.readValue err " + e.getMessage());
			System.out.println("xmlMapper.readValue err " + e.getMessage());
			if (clResp==null)
				clResp= new UFXMsgReqClientResp();
			clResp.set_resp_code("-1");
			clResp.set_resp_text("xmlMapper.readValue err " + e.getMessage());
			return clResp;
		}
		// va yasagan objectimizni Customer kurinishga utkazib yoki Customer
		// classga qiymatlarini utkazamiz
		// va Customer ni listga tiqib, list ni qaytaramiz
		
		return clResp;
		
	}

	public static UFXMsgReqContractResp getCustomersContract_openway(String pBranch, String pClientId, String pPinfl, String pUrl, final String alias) {
		UFXMsgReqContractResp clResp = null;
		
		// maqsad list of customers yoki bitta listni qaytarib berishimiz kerak
		// request uchun xml string tayyorlaymiz
		// CustomerFilter ni bersak ClientInquiry klass yasaydigan funksiya
		UFXMsgReqClient cl = makeContractInquiry( pBranch + pClientId, pPinfl, pUrl);
		// ba'zi polyalarni urnatamiz agar kerak bulsa

		// class dan xml string qaytaradigan funksiya
		XmlUtils xmlUtils = new XmlUtils();
		String v_xml = "_";
		try {
			v_xml = xmlUtils.serializeXmlFromObject(cl);
		} catch (Exception e) {
			// Block of code to handle errors
			System.out.println("error serialize xml " + e.getMessage());
			if (clResp==null)
				clResp= new UFXMsgReqContractResp();
			clResp.setResp_code("-1");
			clResp.setResp_text("error serialize xml " + e.getMessage());
			return clResp;
		}

		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		String v_res2 = "";
		try {
			v_res2 = postUtils.sendData(pUrl, v_xml);
			System.out.println("v_res2 = " + v_res2);
		} catch (Exception e) {
			System.out.println("postUtils.sendData err " + e.getMessage());
			if (clResp==null)
				clResp= new UFXMsgReqContractResp();
			clResp.setResp_code("-1");
			clResp.setResp_text("postUtils.sendData err " + e.getMessage());
			return clResp;
		}
		/*String v_res2="<?xml version=\"1.0\" encoding=\"UTF-8\"?> "+
		"<UFXMsg direction=\"Rs\" msg_type=\"Application\" scheme=\"WAY4Appl\" version=\"2.3.81\" resp_class=\"I\" resp_code=\"0\" resp_text=\"Successfully processed\">"+
		"	<MsgId>sssssss</MsgId>"+
		"	<Source app=\"UZPSB\"/>"+
		"	<MsgData>"+
		"		<Information>"+
		"			<RegNumber>12312300345</RegNumber>"+
		"			<Institution>09058</Institution>"+
		"			<OrderDprt>01142</OrderDprt>"+
		"			<ObjectType>Contract</ObjectType>"+
		"			<ActionType>Inquiry</ActionType>"+
		"			<ResultDtls>"+
		"				<Parm>"+
		"					<ParmCode>Status</ParmCode>"+
		"					<Value>Y</Value>"+
		"				</Parm>"+
		"			</ResultDtls>"+
		"			<ObjectFor>"+
		"				<ClientIDT>"+
		"					<ClientInfo>"+
		"						<ClientNumber>60000001</ClientNumber>"+
		"						<SocialNumber>56789012340078</SocialNumber>"+
		"					</ClientInfo>"+
		"				</ClientIDT>"+
		"			</ObjectFor>"+
		"			<DataRs>"+
		"				<ContractRs>"+
		"					<Contract>"+
		"						<OrderDprt>01142</OrderDprt>"+
		"						<ClientType>PR</ClientType>"+
		"						<ClientCategory>Private</ClientCategory>"+
		"						<ContractIDT>"+
		"							<ContractNumber>9058-P-842656</ContractNumber>"+
		"							<CBSNumber>112600022618000166000004703</CBSNumber>"+
		"						</ContractIDT>"+
		"						<Currency>USD</Currency>"+
		"						<ContractName>JAMES SULLIVAN</ContractName>"+
		"						<CommentText>From the Creators of TOYstory</CommentText>"+
		"						<Product>"+
		"							<AddInfo>"+
		"								<Parm>"+
		"									<ParmCode>ContractCategory</ParmCode>"+
		"									<Value>Account</Value>"+
		"								</Parm>"+
		"							</AddInfo>"+
		"						</Product>"+
		"						<PlasticInfo>"+
		"							<FirstName>HENRY</FirstName>"+
		"							<LastName>WATERNOOSE</LastName>"+
		"						</PlasticInfo>"+
		"						<DateOpen>2021-12-06</DateOpen>"+
		"						<AddContractInfo>"+
		"							<AddInfo01>ACC_NUM=112600022618000166000001700;</AddInfo01>"+
		"						</AddContractInfo>"+
		"					</Contract>"+
		"					<Info>"+
		"						<Status>"+
		"							<StatusClass>Valid</StatusClass>"+
		"							<StatusCode>00</StatusCode>"+
		"							<StatusDetails>Account OK</StatusDetails>"+
		"						</Status>"+
		"					</Info>"+
		"				</ContractRs>"+
		"							"+
		"			</DataRs>"+
		"			<Status>"+
		"				<RespClass>Information</RespClass>"+
		"				<RespCode>0</RespCode>"+
		"				<RespText>Successfully processed</RespText>"+
		"			</Status>"+
		"		</Information>"+
		"	</MsgData>"+
		"</UFXMsg>";*/

		System.out.println("v_res2: "+v_res2);
		
		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2, UFXMsgReqContractResp.class);
		} catch (Exception e) {
			// Block of code to handle errors
			System.out.println("xmlMapper.readValue err(UFXMsgReqContractResp) " + e.getMessage());
			if (clResp==null)
				clResp= new UFXMsgReqContractResp();
			clResp.setResp_code("-1");
			clResp.setResp_text("xmlMapper.readValue err(UFXMsgReqContractResp) " + e.getMessage());
			return clResp;
		}
		// va yasagan objectimizni Customer kurinishga utkazib yoki Customer
		// classga qiymatlarini utkazamiz
		// va Customer ni listga tiqib, list ni qaytaramiz
		
		return clResp;
		
	}

	public static List<Customer> getCustomersFl(final int pageIndex, final int pageSize, final CustomerFilter filter, final String alias) {
		System.out.println("1");
		final List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		final int v_lowerbound = pageIndex + 1;
		final int v_upperbound = v_lowerbound + pageSize - 1;
		final List<FilterField> flFields = getFilterFields(filter);
		final StringBuffer sql = new StringBuffer();
		sql.append(CustomerService.psql1);
		sql.append(CustomerService.msql);
		sql.append(CustomerService.msql2);
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(CustomerService.psql2);
		ISLogger.getLogger().error("getCustomersFl sql string: "
				+ sql.toString());
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			int params;
			for (params = 0; params < flFields.size(); ++params) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
				ISLogger.getLogger().error("FILTER OBJECT: "
						+ flFields.get(params).getColobject());
			}
			++params;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			CallableStatement inf = null;
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			rs = ps.executeQuery();
			while (rs.next()) {
                final Customer customer = new Customer();
                
                customer.setId(rs.getLong("id"));
                customer.setBranch(rs.getString("branch"));
                customer.setId_client( rs.getString("id_client"));
                customer.setName(rs.getString("name"));
                        
                customer.setCode_country(rs.getString("code_country"));
                customer.setCode_type(rs.getString("code_type"));
                customer.setCode_resident(rs.getString("code_resident"));
                customer.setCode_subject(rs.getString("code_subject"));
                customer.setSign_registr(rs.getInt("sign_registr"));
                customer.setCode_form(rs.getString("code_form"));
                customer.setDate_open(rs.getDate("date_open"));
                customer.setDate_close(rs.getDate("date_close"));
                customer.setState(rs.getInt("state"));
                customer.setP_birthday(rs.getDate("p_birthday"));
                customer.setP_post_address(rs.getString("p_post_address"));
                customer.setP_passport_type(rs.getString("p_passport_type"));
                customer.setP_passport_serial(rs.getString("p_passport_serial"));
                customer.setP_passport_number(rs.getString("p_passport_number"));
                customer.setP_passport_place_registration(rs.getString("p_passport_place_registration"));
                customer.setP_passport_date_registration(rs.getDate("p_passport_date_registration"));
                customer.setP_code_tax_org(rs.getString("p_code_tax_org"));
                customer.setP_number_tax_registration(rs.getString("p_number_tax_registration"));
                customer.setP_code_bank(rs.getString("p_code_bank"));
                customer.setP_code_class_credit(rs.getString("p_code_class_credit"));
                customer.setP_code_citizenship(rs.getString("p_code_citizenship"));
                customer.setP_birth_place(rs.getString("p_birth_place"));
                customer.setP_code_capacity(rs.getString("p_code_capacity"));
                customer.setP_capacity_status_date(rs.getDate("p_capacity_status_date"));
                customer.setP_capacity_status_place(rs.getString("p_capacity_status_place"));
                customer.setP_num_certif_capacity(rs.getString("p_num_certif_capacity"));
                customer.setP_phone_home(rs.getString("p_phone_home"));
                customer.setP_phone_mobile(rs.getString("p_phone_mobile"));
                customer.setP_email_address(rs.getString("p_email_address"));
                customer.setP_pension_sertif_serial(rs.getString("p_pension_sertif_serial"));
                customer.setP_code_gender(rs.getString("p_code_gender"));
                customer.setP_code_nation(rs.getString("p_code_nation"));
                customer.setP_code_birth_region(rs.getString("p_code_birth_region"));
                customer.setP_code_birth_distr(rs.getString("p_code_birth_distr"));
                customer.setP_type_document(rs.getString("p_type_document"));
                customer.setP_passport_date_expiration(rs.getDate("p_passport_date_expiration"));
                customer.setP_code_adr_region(rs.getString("p_code_adr_region"));
                customer.setP_code_adr_distr(rs.getString("p_code_adr_distr"));
                customer.setP_inps(rs.getString("p_inps"));
                customer.setP_family(rs.getString("p_family"));
                customer.setP_first_name(rs.getString("p_first_name"));
                customer.setP_patronymic(rs.getString("p_patronymic"));
                customer.setP_pinfl(rs.getString("p_pinfl"));
                customer.setP_zip_code(rs.getString("p_zip_code")); 
                customer.setTieto_customer_id(rs.getString("tieto_customer_id"));
                customer.setO_city(rs.getString("o_city"));
                customer.setO_client_type(rs.getString("o_client_type"));
                customer.setO_security_name(rs.getString("o_security_name"));
                customer.setO_post_address_fact(rs.getString("o_post_address_fact"));
                customer.setO_address_fact_date(rs.getDate("o_address_fact_date"));
                
				list.add(customer);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error("getCustomersFl SQLException =>"
					+ e.getMessage());
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		return list;
	}

	public static Customer getCustomer(final int customerId, final String alias) {
		Customer customer = new Customer();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer WHERE id=?");
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id_client"));
				customer.setName(rs.getString("name"));
				customer.setCode_country(rs.getString("code_country"));
				customer.setCode_type(rs.getString("code_type"));
				customer.setCode_resident(rs.getString("code_resident"));
				customer.setCode_subject(rs.getString("code_subject"));
				customer.setSign_registr(rs.getInt("sign_registr"));
				customer.setCode_form(rs.getString("code_form"));
				customer.setDate_open(rs.getDate("date_open"));
				customer.setDate_close(rs.getDate("date_close"));
				customer.setState(rs.getInt("state"));
				customer.setP_birthday(rs.getDate("p_birthday"));
				customer.setP_post_address(rs.getString("p_post_address"));
				customer.setP_passport_type(rs.getString("p_passport_type"));
				customer.setP_passport_serial(rs.getString("p_passport_serial"));
				customer.setP_passport_number(rs.getString("p_passport_number"));
				customer.setP_passport_place_registration(rs
						.getString("p_passport_place_registration"));
				customer.setP_passport_date_registration(rs
						.getDate("p_passport_date_registration"));
				customer.setP_code_tax_org(rs.getString("p_code_tax_org"));
				customer.setP_number_tax_registration(rs
						.getString("p_number_tax_registration"));
				customer.setP_code_bank(rs.getString("p_code_bank"));
				customer.setP_code_class_credit(rs
						.getString("p_code_class_credit"));
				customer.setP_code_citizenship(rs
						.getString("p_code_citizenship"));
				customer.setP_birth_place(rs.getString("p_birth_place"));
				customer.setP_code_capacity(rs.getString("p_code_capacity"));
				customer.setP_capacity_status_date(rs
						.getDate("p_capacity_status_date"));
				customer.setP_capacity_status_place(rs
						.getString("p_capacity_status_place"));
				customer.setP_num_certif_capacity(rs
						.getString("p_num_certif_capacity"));
				customer.setP_phone_home(rs.getString("p_phone_home"));
				customer.setP_phone_mobile(rs.getString("p_phone_mobile"));
				customer.setP_email_address(rs.getString("p_email_address"));
				customer.setP_pension_sertif_serial(rs
						.getString("p_pension_sertif_serial"));
				customer.setP_code_gender(rs.getString("p_code_gender"));
				customer.setP_code_nation(rs.getString("p_code_nation"));
				customer.setP_code_birth_region(rs
						.getString("p_code_birth_region"));
				customer.setP_code_birth_distr(rs
						.getString("p_code_birth_distr"));
				customer.setP_type_document(rs.getString("p_type_document"));
				customer.setP_passport_date_expiration(rs
						.getDate("p_passport_date_expiration"));
				customer.setP_code_adr_region(rs.getString("p_code_adr_region"));
				customer.setP_code_adr_distr(rs.getString("p_code_adr_distr"));
				customer.setP_inps(rs.getString("p_inps"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return customer;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return customer;
	}

	public static Customer getCustomerById_tbl(final String customerId, final String branch, final String alias) {
		Customer customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer WHERE id=? and branch=?");
			ps.setString(1, customerId);
			ps.setString(2, branch);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id_client"));
				customer.setName(rs.getString("name"));
				customer.setCode_country(rs.getString("code_country"));
				customer.setCode_type(rs.getString("code_type"));
				customer.setCode_resident(rs.getString("code_resident"));
				customer.setCode_subject(rs.getString("code_subject"));
				customer.setSign_registr(rs.getInt("sign_registr"));
				customer.setCode_form(rs.getString("code_form"));
				customer.setDate_open(rs.getDate("date_open"));
				customer.setDate_close(rs.getDate("date_close"));
				customer.setState(rs.getInt("state"));
				customer.setP_birthday(rs.getDate("p_birthday"));
				customer.setP_post_address(rs.getString("p_post_address"));
				customer.setP_passport_type(rs.getString("p_passport_type"));
				customer.setP_passport_serial(rs.getString("p_passport_serial"));
				customer.setP_passport_number(rs.getString("p_passport_number"));
				customer.setP_passport_place_registration(rs.getString("p_passport_place_registration"));
				customer.setP_passport_date_registration(rs.getDate("p_passport_date_registration"));
				customer.setP_code_tax_org(rs.getString("p_code_tax_org"));
				customer.setP_number_tax_registration(rs.getString("p_number_tax_registration"));
				customer.setP_code_bank(rs.getString("p_code_bank"));
				customer.setP_code_class_credit(rs.getString("p_code_class_credit"));
				customer.setP_code_citizenship(rs.getString("p_code_citizenship"));
				customer.setP_birth_place(rs.getString("p_birth_place"));
				customer.setP_code_capacity(rs.getString("p_code_capacity"));
				customer.setP_capacity_status_date(rs.getDate("p_capacity_status_date"));
				customer.setP_capacity_status_place(rs.getString("p_capacity_status_place"));
				customer.setP_num_certif_capacity(rs.getString("p_num_certif_capacity"));
				customer.setP_phone_home(rs.getString("p_phone_home"));
				customer.setP_phone_mobile(rs.getString("p_phone_mobile"));
				customer.setP_email_address(rs.getString("p_email_address"));
				customer.setP_pension_sertif_serial(rs.getString("p_pension_sertif_serial"));
				customer.setP_code_gender(rs.getString("p_code_gender"));
				customer.setP_code_nation(rs.getString("p_code_nation"));
				customer.setP_code_birth_region(rs.getString("p_code_birth_region"));
				customer.setP_code_birth_distr(rs.getString("p_code_birth_distr"));
				customer.setP_type_document(rs.getString("p_type_document"));
				customer.setP_passport_date_expiration(rs.getDate("p_passport_date_expiration"));
				customer.setP_code_adr_region(rs.getString("p_code_adr_region"));
				customer.setP_code_adr_distr(rs.getString("p_code_adr_distr"));
				customer.setP_inps(rs.getString("p_inps"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
				customer.setP_pinfl(rs.getString("pinfl"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return customer;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return customer;
	}

	public static String getCustomersClientID(final String customerId, final String branch, final String alias) {
		String v_res = null;
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT id_client FROM client WHERE branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, customerId);
			
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				v_res = rs.getString("id_client");
				
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return v_res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return v_res;
	}

	
	public static Res insertClient(Customer client) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            ISLogger.getLogger().error("insert bf_openway_clients");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            		"begin update bf_openway_clients set o_client_type=?, o_security_name=?, o_city=?, o_post_address_fact=?, o_address_fact_date=? "+
            		"where branch=? and id_client=?; if sql%notfound then "+
            		"insert into bf_openway_clients(o_client_type,o_security_name,"
            		                    + "o_city,o_post_address_fact,o_address_fact_date,date_open,branch,id_client) values"
            		                    + " (?,?,"
            		                    + "?,?,?, nvl(info.getday,sysdate),?,?); "+
            		"end if; "+
            		"end;"
            );
            ps.setString(1, client.getO_client_type());
            ps.setString(2, client.getO_security_name());
            ps.setString(3, client.getO_city());
            ps.setString(4, client.getO_post_address_fact());
            ps.setDate(5,  new java.sql.Date(client.getO_address_fact_date().getTime()));
            ps.setString(6, client.getBranch());
            ps.setString(7, client.getId_client());
            ps.setString(8, client.getO_client_type());
            ps.setString(9, client.getO_security_name());
            ps.setString(10, client.getO_city());
            ps.setString(11, client.getO_post_address_fact());
            ps.setDate(12,  new java.sql.Date(client.getO_address_fact_date().getTime()));
            ps.setString(13, client.getBranch());
            ps.setString(14, client.getId_client());
            
            ps.execute();
            
            c.commit();
            //bool = true;
            res = new Res(0, "OK");
            
        } 
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	res = new Res(-1, e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
    

	public static Res insertAccount(AccInfo acc) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            ISLogger.getLogger().error("insert bf_openway_contract_acc");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            		"begin update bf_openway_contract_acc set contract_name=?, comment_text=?, product_code=?, date_open=nvl(info.getday,sysdate) "+
            		"where branch=? and id_client=? and rbs_number=?; if sql%notfound then "+
            		"insert into bf_openway_contract_acc(contract_name,comment_text,product_code,date_open,branch,id_client,rbs_number) values("
            		                    + "?, ?, ?, nvl(info.getday,sysdate), ?, ?, ?); "+
            		"end if; "+
            		"end;"
            );

            ps.setString(1, acc.getContractName());
            ps.setString(2, acc.getCommentText());
            ps.setString(3,  acc.getProductCode1());            
            ps.setString(4, acc.getBranch());
            ps.setString(5, acc.getClient());
            ps.setString(6, acc.getRbsNumberIbs());
            
            ps.setString(7, acc.getContractName());
            ps.setString(8, acc.getCommentText());
            ps.setString(9, acc.getProductCode1());
            ps.setString(10, acc.getBranch());
            ps.setString(11, acc.getClient());
            ps.setString(12, acc.getRbsNumberIbs());
            
            ps.execute();
            
            c.commit();
            //bool = true;
            res = new Res(0, "OK");
            
        } 
        
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	res = new Res(-1, e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static Res updateAccount(AccInfo acc) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            ISLogger.getLogger().error("insert bf_openway_contract_acc");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            		"update bf_openway_contract_acc set cbs_number=? "+
            		"where branch=? and id_client=? and rbs_number=?"
            );
            ps.setString(1, acc.getCbsNumber());
            ps.setString(2, acc.getBranch());
            ps.setString(3, acc.getClient());
            ps.setString(4, acc.getRbsNumberIbs());
            
            ps.execute();
            
            c.commit();
            //bool = true;
            res = new Res(0, "OK");
            
        } 
        
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	res = new Res(-1, e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static List<AccInfo> getContractAccList_ABS(final String pBranch, final String pId_client, final String alias) {
		
		final List<AccInfo> list = new ArrayList<AccInfo>();
		Connection c = null;
		
		final StringBuffer sql = new StringBuffer();
		sql.append("select * from bf_openway_contract_acc where branch=? and id_client=?");
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			ps.setString(1, pBranch);
			ps.setString(2, pId_client);
			
			rs = ps.executeQuery();
			while (rs.next()) {
                final AccInfo acc = new AccInfo();
                acc.setCommentText(rs.getString("comment_text"));
                acc.setBranch(rs.getString("branch"));
                acc.setClient(rs.getString("id_client"));
                acc.setProductCode1(rs.getString("product_code"));
                acc.setRbsNumberIbs(rs.getString("rbs_number"));
				list.add(acc);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error("getCustomersFl SQLException =>"
					+ e.getMessage());
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		return list;
	}
	
	public static List<CardInfo> getContractCardList_ABS(final String pBranch, final String pId_client, final String pProduct_code, final String alias) {
		
		final List<CardInfo> list = new ArrayList<CardInfo>();
		Connection c = null;
		
		final StringBuffer sql = new StringBuffer();
		sql.append("select * from bf_openway_contract_card where branch=? and id_client=? and product_code=?");
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			ps.setString(1, pBranch);
			ps.setString(2, pId_client);
			ps.setString(3, pProduct_code);
			
			rs = ps.executeQuery();
			while (rs.next()) {
                final CardInfo card = new CardInfo();
                card.setCommentText(rs.getString("comment_text"));
                card.setBranch(rs.getString("branch"));
                card.setProductCode1(rs.getString("sub_product_code"));
                card.setRbsNumberIbs(rs.getString("rbs_number"));
                card.setCbsNumber(rs.getString("cbs_number"));
                
                card.setBranch(pBranch);
                card.setClient_id(pId_client);
                card.setMasterProductCode1(pProduct_code);
                
				list.add(card);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error("getCustomersFl SQLException =>"
					+ e.getMessage());
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		return list;
	}

public static List<CardInfo> getContractCardList_ABS(final String pBranch, final String pId_client, final String alias) {
		
		final List<CardInfo> list = new ArrayList<CardInfo>();
		Connection c = null;
		
		final StringBuffer sql = new StringBuffer();
		sql.append("select * from bf_openway_contract_card where branch=? and id_client=?");
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			ps.setString(1, pBranch);
			ps.setString(2, pId_client);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
                final CardInfo card = new CardInfo();
                card.setCommentText(rs.getString("comment_text"));
                card.setBranch(rs.getString("branch"));
                card.setProductCode1(rs.getString("sub_product_code"));
                card.setRbsNumberIbs(rs.getString("rbs_number"));
                card.setCbsNumber(rs.getString("cbs_number"));
                
                card.setBranch(pBranch);
                card.setClient_id(pId_client);
                card.setMasterProductCode1(rs.getString("product_code"));
                
				list.add(card);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error("getContractCardList_ABS SQLException =>"
					+ e.getMessage());
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		return list;
	}

	public static Res insertCard(CardInfo card) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            ISLogger.getLogger().error("insert bf_openway_contract_card");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            		"begin update bf_openway_contract_card set contract_name=?, comment_text=?, date_open=nvl(info.getday,sysdate) "+
            		"where branch=? and id_client=? and product_code=? and sub_product_code=? and rbs_number=?; if sql%notfound then "+
            		"insert into bf_openway_contract_card(contract_name,comment_text,date_open,branch,id_client,product_code,sub_product_code,rbs_number) values("
            		                    + "?, ?, nvl(info.getday,sysdate), ?, ?, ?, ?, ?); "+
            		"end if; "+
            		"end;"
            );

            ps.setString(1, card.getContractName());
            ps.setString(2, card.getCommentText());
            ps.setString(3, card.getBranch());
            ps.setString(4, card.getClient_id());
            ps.setString(5, card.getMasterProductCode1());
            ps.setString(6, card.getProductCode1());
            ps.setString(7, card.getRbsNumberIbs());
            
            ps.setString(8, card.getContractName());
            ps.setString(9, card.getCommentText());
            ps.setString(10, card.getBranch());
            ps.setString(11, card.getClient_id());
            ps.setString(12, card.getMasterProductCode1());
            ps.setString(13, card.getProductCode1());
            ps.setString(14, card.getRbsNumberIbs());
            
            ps.execute();
            
            c.commit();
            //bool = true;
            res = new Res(0, "OK");
            
        } 
        
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	res = new Res(-1, e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static Res updateCard(CardInfo card) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            ISLogger.getLogger().error("update bf_openway_contract_card");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            		"update bf_openway_contract_card set cbs_number=? "+
            		"where branch=? and id_client=? and product_code=? and sub_product_code=? and rbs_number=?"
            );
            ps.setString(1, card.getCbsNumber());
            ps.setString(2, card.getBranch());
            ps.setString(3, card.getClient_id());
            ps.setString(4, card.getMasterProductCode1());
            ps.setString(5, card.getProductCode1());
            ps.setString(6, card.getRbsNumberIbs());
            
            ps.execute();
            
            c.commit();
            //bool = true;
            res = new Res(0, "OK");
            
        } 
        
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	res = new Res(-1, e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static void deleteTable(String branch, String filename)
			throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete open_clients_temp where excel = ? and e.branch = ?");
			ps.setString(1, filename);
			ps.setString(2, branch);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static Res create_lnk(final String branch, final String branchId,
			final String tietoCustomerId, final String currentAccount,
			final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		Res res = new Res();
		try {
			ISLogger.getLogger().error(
					"create_lnk data branch: " + branch + " bank_customer_id: "
							+ branchId + " tieto_id: " + tietoCustomerId
							+ " cur_acc: " + currentAccount);
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("INSERT INTO bf_xumo_customers (id,branch, bank_customer_id, tieto_customer_id, cur_acc) VALUES (seq_bf_xumo_customers.Nextval, ?,?,?,?)");
			ps.setString(1, branch);
			ps.setString(2, branchId);
			ps.setString(3, tietoCustomerId);
			ps.setString(4, currentAccount);
			ps.execute();
			c.commit();
			res.setCode(0);
			res.setName("”спешно!");
		} catch (Exception e) {
			ISLogger.getLogger().error(
					("create_lnk error: " + (Object) CheckNull.getPstr(e)));
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String get_contract(final String branch,
			final String card_code, final String alias) {
		String res = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			final PreparedStatement proc = c
					.prepareStatement("select BF.GET_CONTRACT_NUMBER(?,?) from dual");
			proc.setString(1, branch);
			proc.setString(2, card_code);
			final ResultSet rs = proc.executeQuery();
			if (rs.next()) {
				res = new String(rs.getString(1));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static List<RefData> getMfo(final String branch) {
		return (List<RefData>) RefDataService
				.getRefData(
						"select bank_id data, bank_name label from S_mfo order by bank_id",
						branch);
	}

	public static String getNewestAccount(String clientId, String branch,
			int firstOrd, int lastOrd) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String account = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id from (SELECT *\n"
					+ "  FROM Account\n" + " where branch = ?\n"
					+ "   and acc_bal like '22618'\n"
					+ "   and currency = '000'\n" + "   and client = ?\n"
					+ "   and id_order >= ?\n" + "   and id_order < ?\n"
					+ "   and state = '2'\n" + "   order by id_order desc)\n"
					+ "   where rownum = 1");
			ps.setString(1, branch);
			ps.setString(2, clientId);
			ps.setInt(3, firstOrd);
			ps.setInt(4, lastOrd);
			rs = ps.executeQuery();
			if (rs.next()) {
				account = rs.getString(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return account;

	}

	public static boolean isHumoVisaCreateAccessGranted(String branch) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isAccessGranted = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from humo_visa_access where branch = ? and state = 1");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				isAccessGranted = rs.getInt(1) > 0 ? true : false;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return isAccessGranted;
	}

	/* справочниклар бошланиши, spravochniklar */
	public static List<RefData> getCountry(final String alias) {
		if (listCountry==null || listCountry.size()==0)
		 listCountry = (List<RefData>) Utils
				.getRefData(
						"select s.code_str data, s.name label from S_STR s where s.act='A' order by 2",
						alias);
		return listCountry ;
	}

	public static List<RefData> getDistr(final String alias) {
		if (listDistr==null || listDistr.size()==0)
		listDistr= (List<RefData>) Utils
				.getRefData(
						"select distr data, distr_name label from s_distr where act='A' order by 2",
						alias);
		 return listDistr;
	}
	
	public static List<RefData> getDistrByRegion(String region_id, final String alias) {

		/*if (regions_districts == null) {
			HashMap<String, List<RefData>> regs_dists = new HashMap<String, List<RefData>>();
			for (RefData rd : regions) {
				List<RefData> distrList = new ArrayList<RefData>();
				distrList = ClientDictionaries.getDistrByRegion(tm.getConnection(), rd.getData(), alias);
				regs_dists.put(rd.getData(), distrList);
			}
			regions_districts = regs_dists;
		}*/
		if (listDistrByRegion == null ) 
			listDistrByRegion = new HashMap<String, List<RefData>>();
		if ( !listDistrByRegion.containsKey(region_id) ) {
			List<RefData> lst = (List<RefData>) Utils
			.getRefData(
					"select distr data, distr_name label from s_distr where region_id=? and act='A' order by 2", region_id,
					alias);
			
		   listDistrByRegion.put(region_id, lst);
		}
		return listDistrByRegion.get(region_id);
	}

	public static List<RefData> getRegion(final String alias) {
		if (listRegion==null || listRegion.size()==0)
		listRegion= (List<RefData>) Utils
				.getRefData(
						"select region_id data, region_nam label from s_region where act='A' order by 2",
						alias);
		return listRegion;
	}

	public static List<RefData> getTax(final String branch) {
		if (listTax==null || listTax.size()==0)
		listTax = (List<RefData>) Utils
				.getRefData(
						"select gni_id data, name_gni label from s_gni where act <> 'Z' order by 2",
						branch);
		return listTax;
	}

	public static List<RefData> getType_document(final String alias) {
		// shuerda dokumentlarni qaytaradigan static variable bulishi kerak
		// va u variable bush bulsa bazaga murojaat qilaylik yani
		// Utils.getRefData ga
		// bush bulmasa uzini qaytarib beraylik
		if (listTypeDocument == null || listTypeDocument.size() == 0)
			listTypeDocument = (List<RefData>) Utils
					.getRefData(
							"select s.code_cert data, s.name_cert label from S_CERTIFICATE s where act='A' order by 2",
							alias);
		return listTypeDocument;

	}

	public static List<RefData> getClient_type_way4(final String alias) {
		if (listClientTypeWay4==null || listClientTypeWay4.size()==0)
		listClientTypeWay4 = (List<RefData>) Utils
				.getRefData(
						"select 'PR' data,	'‘изическое лицо - резидент(PR)' label, 0 ord from dual union all "
								+ "select 'PNR' data,	'‘изическое лицо - нерезидент(PNR)' label, 1 ord from dual union all "
								+ "select 'RPR' data,	'‘изическое лицо, пенсионер(RPR)' label, 2 ord from dual order by ord",
						alias);
		return listClientTypeWay4;
	}

	public static List<RefData> getWayCertificates(final String alias) {
		if (listWayCert==null || listWayCert.size()==0)
			listWayCert = (List<RefData>) Utils
				.getRefData(
						"select s.CODE_CERT_NCI data, s.CODE_CERT_WAY label from BF_OPENWAY_SS_CERT s order by 1",
						alias);
		return listWayCert ;
	}
	public static List<RefData> getWayCountries(final String alias) {
		if (listWayCountries==null || listWayCountries.size()==0)
			listWayCountries = (List<RefData>) Utils
				.getRefData(
						"select s.CODE_str data, s.alpha_3 label from s_str s order by decode(s.CODE_str, '860','000', s.CODE_str)",
						alias);
		return listWayCountries ;
	}
	public static List<RefData> getAcc_bal(final String alias) {
		if (listAccBal==null || listAccBal.size()==0)
		listAccBal = (List<RefData>) Utils
				.getRefData(
						"select '22618' data,	'22618' label, 0 ord from dual",
						alias);
		return listAccBal;
	}	
	
	public static List<RefData> getProduct_code1_way4(final String alias) {
		if (listProductCode1Way4==null || listProductCode1Way4.size()==0)
			listProductCode1Way4 = (List<RefData>) Utils
				.getRefData(
						/*"select 'ISS_DB_USD' data,	'ISS_DB_USD' label, 0 ord from dual",*/
						/*"select CODE_PROD_WAY data, NAME_PROD_IBS label, 0 ord from bf_openway_product where CODE_PROD_WAY like '%USD%' and state='A'",*/
						"select CODE_PROD_WAY data, NAME_PROD_IBS label, 0 ord from bf_openway_product where state='A' order by 1",						
						alias);
		return listProductCode1Way4;
	}
	/*public static List<RefData> getSubProduct_code1_way4(final String alias) {
		if (listSubProductCode1Way4==null || listSubProductCode1Way4.size()==0)
			listSubProductCode1Way4 = (List<RefData>) Utils
				.getRefData(
						//"select 'VISA_CLASSIC_USD' data, 'VISA_CLASSIC_USD' label, 0 ord from dual",
						"select CODE_SUBPROD_WAY data, NAME_PROD_IBS label, 0 ord from bf_openway_product where CODE_SUBPROD_WAY like '%USD%' and state='A'",
						alias);
		return listSubProductCode1Way4;
	}*/
	public static List<RefData> getSubProductByProduct(String product_code, final String alias) {
	
		if (listSubProductByProduct == null ) 
			listSubProductByProduct = new HashMap<String, List<RefData>>();
		if ( !listSubProductByProduct.containsKey(product_code) ) {
			List<RefData> lst = (List<RefData>) Utils
			.getRefData(
					"select CODE_SUBPROD_WAY data, NAME_SUBPROD_IBS label, 0 ord from bf_openway_subproduct "+
					"where CODE_PROD_WAY = ? and state='A' order by CODE_SUBPROD_WAY", product_code,
					alias);
			
			listSubProductByProduct.put(product_code, lst);
		}
		return listSubProductByProduct.get(product_code);
	}
	
	/* справочниклар тугаши*/
   
	public static String getWayCertCode(String nciCertCode, String alias) {
		String v_res="OTHER";
		List<RefData> tmp = getWayCertificates(alias);
		for (int i = 0; i < tmp.size(); i++) {
			  //System.out.println(i);
			if ( tmp.get(i).getData() == nciCertCode || tmp.get(i).getData().equals(nciCertCode)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getWayCountryCode(String nciCountryCode, String alias) {
		String v_res="";
		List<RefData> tmp = getWayCountries(alias);
		
		for (int i = 0; i < tmp.size(); i++) {
			
			if ( tmp.get(i).getData() == nciCountryCode || tmp.get(i).getData().equals(nciCountryCode)) {
				v_res=tmp.get(i).getLabel();
				break;
			} 
			}
		return v_res;
	}	
	
	public static Integer getSeqApplRegNumber()  {
           Connection c = null;
           PreparedStatement ps = null;
           ResultSet rs = null;
           Integer v_res = 0;
           try {
                   c = ConnectionPool.getConnection();
                   ps = c.prepareStatement("SELECT seq_bf_appl_regnumber.NEXTVAL id FROM DUAL");
                   rs = ps.executeQuery();
                   if (rs.next()) {
                	   v_res=rs.getInt("id");
                   }
                   
           } catch (Exception e) {
                   com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
           } finally {
                   Utils.close(ps);
       			Utils.close(rs);
       			ConnectionPool.close(c);
           }
           return v_res;
    }

	public static Integer getSeqCardRbsNumber()  {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer v_res = 0;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT seq_bf_card_rbsnumber.NEXTVAL id FROM DUAL");
                rs = ps.executeQuery();
                if (rs.next()) {
             	   v_res=rs.getInt("id");
                }
                
        } catch (Exception e) {
                com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                Utils.close(ps);
    			Utils.close(rs);
    			ConnectionPool.close(c);
        }
        return v_res;
 }

}