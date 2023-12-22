package com.is.tietovisa.customer;

import java.util.Calendar;
import java.util.HashMap;

import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.SQLException;

import java.sql.Date;

import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

import com.is.tietovisa.PostUtils;
import com.is.tietovisa.Utils;
import com.is.tietovisa.model.ListType_GenericHolder;
import com.is.tietovisa.model.RowType_ListCustomers_Request;
import com.is.tietovisa.model.Tclient;
import com.is.tietovisa.model.TclientFilter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

//import com.is.tietovisautils.utils.Utils;

import com.is.ISLogger;
import com.is.openway.XmlUtils;

import com.is.openway.model.UFXMsgReqClientResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
//import com.rabbitmq.client.AMQP.Confirm.Select;

public class CustomerService {

	private static String psql1;
	private static String psql2;
	private static String msql;
	private static String msql2;
	// private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	private static SimpleDateFormat mdf = new SimpleDateFormat("yyMMdd-HHmmss");
	//private static SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd"); 1979-07-10T00:00:00
	
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
		CustomerService.msql2 = "v_bf_tietovisa_client";
	}

	public CustomerService() {
		// this.cvc = new CustomerViewCtrl();
	}

	public static List<Customer> getCustomersFl(final int pageIndex, final int pageSize, final CustomerFilter filter,
			final String alias) {
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
		//ISLogger.getLogger().error("getCustomersFl sql string: " + sql.toString());
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			int params;
			for (params = 0; params < flFields.size(); ++params) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
				//ISLogger.getLogger().error("FILTER OBJECT: " + flFields.get(params).getColobject());
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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_zip_code(rs.getString("p_zip_code"));
				customer.setId_tieto(rs.getString("id_tieto"));
				customer.setT_r_city(rs.getString("t_city"));
				customer.setT_client_b(rs.getString("t_client_b"));
				customer.setT_cl_type(rs.getString("t_cl_type"));

				list.add(customer);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error("getCustomersFl SQLException =>" + e.getMessage());
			list.clear();
			Customer cust = new Customer();
			cust.setSign_error_record(true);
			cust.setName("Error getting IBS customers. Please contact administrator of system.");
			list.add(cust);
			//return list;
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

	public static List<Customer> getCustomersNciAndTietoFl(final int pageIndex, final int pageSize,
			final CustomerFilter filter, final String alias) {

		List<Customer> list = new ArrayList<Customer>();
		//getting nci customers
		final List<Customer> list_nci = getCustomersFl(pageIndex, pageSize, filter, alias);
		//getting tieto customers
		List<Customer> list_tieto = CustomerService.getCustomers_tieto(filter, alias, filter.getEndpoint(), true);
        
		/*if (list_nci.size()==1 && list_nci.get(0).isSign_error_record() && list_tieto.size()==1 && list_tieto.get(0).isSign_error_record() ) {
			Customer cust = new Customer();
			cust.setName("Error getting customers. Please contact administrator system.");
			cust.setSign_error_record(true);
			list.add(cust);
			return list;	
		} else if (list_nci.size()==1 && list_nci.get(0).isSign_error_record() ) {
			//Customer cust = new Customer();
			//cust.setName("No data found. Please change filter and try again");
			//cust.setSign_error_record(true);
			return list;	
		}*/
		//listlarni umumlashtiramiz/qushamiz
		for (int i = 0; i < list_nci.size(); i++) {
			Customer cust=list_nci.get(i).clone(list_nci.get(i));
			
			if (list_nci.get(i).getId_tieto()!=null) {
				for (int j = 0; j < list_tieto.size(); j++) {
					if ( !list_tieto.get(j).isSign_error_record() && list_tieto.get(j).getT_client().equals( list_nci.get(i).getId_tieto() )) /*sign=1*/ {
						cust.copyTietoFields(list_tieto.get(j), cust);
						cust.setSign_record("1");						
						list_tieto.remove(j);
						break;
					}
				}
				if (cust.getSign_record()==null) /*sign=2*/ {
					cust.setSign_record("2");
				}
			} else {
				for (int j = 0; j < list_tieto.size(); j++) {
					if ( !list_tieto.get(j).isSign_error_record() && list_tieto.get(j).getT_client_b().equals( list_nci.get(i).getBranch()+ list_nci.get(i).getId_client() )) /*sign=3*/ {
						cust.copyTietoFields(list_tieto.get(j), cust);
						cust.setSign_record("3");						
						list_tieto.remove(j);
						break;
					}
				}
				if (cust.getSign_record()==null) /*sign=4*/ {
					cust.setSign_record("4");
				}
			}
			list.add(cust);
		}
		for (int i = 0; i < list_tieto.size(); i++) {
			Customer cust= new Customer();
			cust.copyTietoFields(list_tieto.get(i), cust);
			cust.setSign_record("5");
			list.add(cust);
		}
				
		return list;
	}

	public static List<Customer> getCustomers_tieto(CustomerFilter filter, String alias, String pUrl,
			Boolean ignoreDuplicates) {
		List<Customer> list = new ArrayList<Customer>();
		RowType_ListCustomers_Request rt_listCustReq = new RowType_ListCustomers_Request();
		rt_listCustReq = getFilterParams(filter);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();

		String v_json = "_";

		try {
			v_json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rt_listCustReq);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error("error serialize json " + e22.getMessage());
			Customer rs = new Customer();
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("getCustomers_openway v_json: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
			System.out.println("v_res = " + v_res);
			ISLogger.getLogger().error("getCustomers_tieto v_res: " + v_res);
		} catch (Exception e) {
			System.out.println("postUtils.sendData err " + e.getMessage());
			ISLogger.getLogger().error("postUtils.sendData err " + e.getMessage());
			Customer rs = new Customer();			
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		if ((v_res=="" || v_res.equals("")) ) {
			Customer rs = new Customer();			
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			ListType_GenericHolder rslt = null;
			try {
				rslt = mapper.readValue(v_res, ListType_GenericHolder.class);
			} catch (Exception e) {
				ISLogger.getLogger().error("mapper.readValue err " + e.getMessage());
				System.out.println("mapper.readValue err " + e.getMessage());
				Customer rs = new Customer();
				rs.setSign_error_record(true);
				rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
			ISLogger.getLogger().error(rslt.toString());
			list = makeListFromListType_GenericHolder(rslt, ignoreDuplicates);
		}
		return list;
	}

	private static RowType_ListCustomers_Request getFilterParams(CustomerFilter filter) {
		RowType_ListCustomers_Request params = new RowType_ListCustomers_Request();
		params.setBANK_C("01");
		if (filter.getName() != null) {
			params.setF_NAMES(filter.getName());
		}

		if (filter.getP_family() != null) {
			params.setSURNAME(filter.getP_family());
		}

		if (filter.getT_client() != null) {
			params.setCLIENT(filter.getT_client());
		}

		/*
		 * if (filter.getB_date() != null) { // SimpleDateFormat format = new
		 * SimpleDateFormat("dd.MM.yyyy"); // format.format(); Calendar cal =
		 * Calendar.getInstance(); cal.setTime(filter.getB_date());
		 * cal.add(Calendar.DAY_OF_MONTH, +1); params.setB_DATE(cal); }
		 */
		return params;
	}

	public static List<Customer> makeListFromListType_GenericHolder(ListType_GenericHolder details,
			Boolean ignoreDuplicates) {
		List<Customer> list = new ArrayList<Customer>();

		if (details.value == null)
			return list;
		String prev_client_id = "_";
		for (int i = 0; i < details.value.length; i++) {
			Customer rs = new Customer();

			for (int j = 0; j < details.value[i].length; j++) {
				if (details.value[i][j].getName().equals("CLIENT"))
					rs.setT_client(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("STATUS"))
					rs.setT_status(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("CLIENT_B"))
					rs.setT_client_b(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("F_NAMES"))
					rs.setT_f_names(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("SURNAME"))
					rs.setT_surname(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("PERSON_CODE"))
					rs.setT_person_code(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("B_DATE") && details.value[i][j].getValue()!=null && details.value[i][j].getValue().trim().length()>=10)
					try {
    				    rs.setT_b_date(df.parse(details.value[i][j].getValue().trim().substring(0, 10)));    				  
					} catch (Exception e) {						
					}					
				if (details.value[i][j].getName().equals("CARD"))
					rs.setT_card(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("BANK_C"))
					rs.setT_bank_c(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_STREET"))
					rs.setT_r_street(details.value[i][j].getValue());
			}
			if (!ignoreDuplicates || !prev_client_id.equals(rs.getT_client())) {
				list.add(rs);
			}
			prev_client_id = rs.getT_client();
		}
		return list;
	}

	public static Res doAction(final String un, final String pw, final Customer customer, final int actionid,
			final int utv_actionid, final String alias, final Boolean selfBranch) {
		Res res = null;
		// final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
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
			System.out.println("CODE_RESIDENT = " + customer.getCode_resident());
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			System.out.println("CODE_SUBJECT = " + customer.getCode_subject());
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getSign_registr())).toString());
			cs.execute();
			System.out.println("SIGN_REGISTR = " + customer.getSign_registr());
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			System.out.println("CODE_FORM = " + customer.getCode_form());
			cs.setString(1, "DATE_OPEN");
			cs.setString(2, (customer.getDate_open() != null) ? bdf.format(customer.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(2, (customer.getDate_close() != null) ? bdf.format(customer.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getState())).toString());
			cs.execute();
			System.out.println("state = " + customer.getState());
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(2, (customer.getP_birthday() != null) ? bdf.format(customer.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			System.out.println("P_POST_ADDRESS = " + customer.getP_post_address());
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			System.out.println("P_PASSPORT_TYPE = " + customer.getP_passport_type());
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			System.out.println("P_PASSPORT_SERIAL = " + customer.getP_passport_serial());
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			System.out.println("P_PASSPORT_NUMBER = " + customer.getP_passport_number());
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			System.out.println("P_PASSPORT_PLACE_REGISTRATION = " + customer.getP_passport_place_registration());
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(2,
					(customer.getP_passport_date_registration() != null)
							? bdf.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			System.out.println("P_CODE_TAX_ORG = " + customer.getP_code_tax_org());
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			System.out.println("P_NUMBER_TAX_REGISTRATION =" + customer.getP_number_tax_registration());
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			System.out.println("P_CODE_BANK = " + customer.getP_code_bank());
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			System.out.println("P_CODE_CLASS_CREDIT = " + customer.getP_code_class_credit());
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
			cs.setString(2,
					(customer.getP_capacity_status_date() != null) ? bdf.format(customer.getP_capacity_status_date())
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
			cs.setString(2,
					(customer.getP_passport_date_expiration() != null)
							? bdf.format(customer.getP_passport_date_expiration())
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

	public static Res doAction_utv(final String un, final String pw, final Customer customer, final int actionid,
			final String alias, final Boolean selfBranch) {
		Res res = null;
		// final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
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
			cs.setString(2, new StringBuilder(String.valueOf(customer.getSign_registr())).toString());
			cs.execute();
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			cs.setString(1, "DATE_OPEN");
			cs.setString(2, (customer.getDate_open() != null) ? bdf.format(customer.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(2, (customer.getDate_close() != null) ? bdf.format(customer.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getState())).toString());
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(2, (customer.getP_birthday() != null) ? bdf.format(customer.getP_birthday()) : null);
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
			cs.setString(2,
					(customer.getP_passport_date_registration() != null)
							? bdf.format(customer.getP_passport_date_registration())
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
			cs.setString(2,
					(customer.getP_capacity_status_date() != null) ? bdf.format(customer.getP_capacity_status_date())
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
			cs.setString(2,
					(customer.getP_passport_date_expiration() != null)
							? bdf.format(customer.getP_passport_date_expiration())
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

	public static String doAction(final String un, final String pw, final String branch, final String id,
			final int actionid, final String alias, final Boolean selfBranch) {
		String res = "";
		// final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
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
						if (rs.getMetaData().getColumnTypeName(i).equals("DATE")) {
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
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "id=?", (Object) filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(
					new FilterField(String.valueOf(getCond(flfields)) + "id_client=?", (Object) filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "branch=?", (Object) filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "upper(name) like ?",
					(Object) ("%" + filter.getName().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "code_country=?",
					(Object) filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(
					new FilterField(String.valueOf(getCond(flfields)) + "code_type=?", (Object) filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "code_resident=?",
					(Object) filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "code_subject=?",
					(Object) filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sign_registr=?",
					(Object) filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(
					new FilterField(String.valueOf(getCond(flfields)) + "code_form=?", (Object) filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(
					new FilterField(String.valueOf(getCond(flfields)) + "date_open=?", (Object) filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "date_close =?",
					(Object) filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "state=?", (Object) filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_birthday=?",
					(Object) new Date(filter.getP_birthday().getTime())));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_post_address=?",
					(Object) filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_passport_type=?",
					(Object) filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields))
							+ "(p_passport_serial=? or p_passport_serial||p_passport_number = ?)",
					(Object) filter.getP_passport_serial()));
			flfields.add(new FilterField("", (Object) (filter.getP_passport_serial())));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_passport_number=?",
					(Object) filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_passport_place_registration=?",
					(Object) filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_passport_date_registration=?",
					(Object) filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_tax_org=?",
					(Object) filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_number_tax_registration=?",
					(Object) filter.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_bank=?",
					(Object) filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_class_credit=?",
					(Object) filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_citizenship=?",
					(Object) filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_birth_place=?",
					(Object) filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_capacity=?",
					(Object) filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_capacity_status_date=?",
					(Object) filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_capacity_status_place=?",
					(Object) filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_num_certif_capacity=?",
					(Object) filter.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_phone_home=?",
					(Object) filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_phone_mobile=?",
					(Object) filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_email_address=?",
					(Object) filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_pension_sertif_serial=?",
					(Object) filter.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_gender=?",
					(Object) filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_nation=?",
					(Object) filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_birth_region=?",
					(Object) filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_birth_distr=?",
					(Object) filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_type_document=?",
					(Object) filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_passport_date_expiration=?",
					(Object) filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_adr_region=?",
					(Object) filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_code_adr_distr=?",
					(Object) filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_inps=?", (Object) filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "p_pinfl like ?",
					"%" + (Object) filter.getP_pinfl() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?" + " or upper(p_family) like ?)",
					(Object) ("%" + filter.getP_family().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%" + filter.getP_family().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?" + " or upper(p_first_name) like ?)",
					(Object) ("%" + filter.getP_first_name().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%" + filter.getP_first_name().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?" + " or upper(p_patronymic) like ?)",
					(Object) ("%" + filter.getP_patronymic().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%" + filter.getP_patronymic().toUpperCase() + "%")));
		}
		String notNull = "";
		if (!CheckNull.isEmpty(filter.getTietoIdIsNotNull())) {
			notNull = " and tieto_customer_id is not null ";
		}
		if (!CheckNull.isEmpty(filter.getT_client())) {
			flfields.add(
					new FilterField(String.valueOf(getCond(flfields)) + "t_client=?", (Object) filter.getT_client()));
		}
		flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "rownum<? " + notNull, (Object) 1001));
		/*
		 * if (!(filter.getCard() == null)) { if (!filter.getCard().matches("[0-9]+")) {
		 * if (filter.getCard().contains("%")) { flfields.add(new FilterField(
		 * String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card like ?)", filter.getCard())); } else {
		 * flfields.add(new FilterField( String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card = ?)", filter.getCard())); } } else {
		 * flfields.add(new FilterField( String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.real_card = ?)", filter.getCard())); } }
		 */
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
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
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

	public static Integer getSeqApplRegNumber() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer v_res = 0;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT seq_bf_appl_regnumber.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				v_res = rs.getInt("id");
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

	public static Integer getSeqCardRbsNumber() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer v_res = 0;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT seq_bf_card_rbsnumber.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				v_res = rs.getInt("id");
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