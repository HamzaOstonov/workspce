package com.is.tietovisa.customer;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.SQLException;

import java.sql.Date;

import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.zkoss.zk.ui.Executions;

import com.is.tietovisa.Cons;
import com.is.tietovisa.PostUtils;
import com.is.tietovisa.StringUtils;
import com.is.tietovisa.Utils;
import com.is.tietovisa.model.AccInfo;
import com.is.tietovisa.model.AccountBalance;
import com.is.tietovisa.model.Branch;
import com.is.tietovisa.model.CardIbs;
import com.is.tietovisa.model.CardInfo;
import com.is.tietovisa.model.Card_design;
import com.is.tietovisa.model.Cond_card;
import com.is.tietovisa.model.GetRealCardResponse;
import com.is.tietovisa.model.ListTypeResponse;
import com.is.tietovisa.model.ListType_GenericHolder;
import com.is.tietovisa.model.Product;
import com.is.tietovisa.model.RowType_AccBalanceQueryByCard_Request;
import com.is.tietovisa.model.RowType_Customer;
import com.is.tietovisa.model.RowType_EditCustomer_Request;
import com.is.tietovisa.model.RowType_GetRealCard_Request;
import com.is.tietovisa.model.RowType_ListAccountsByCard_Request;
import com.is.tietovisa.model.RowType_ListAccounts_Request;
import com.is.tietovisa.model.RowType_ListCardsByAccount_Request;
import com.is.tietovisa.model.RowType_ListCustomerCards_Request;
import com.is.tietovisa.model.RowType_ListCustomers_Request;
import com.is.tietovisa.model.RowType_TransactionHist_Request;
import com.is.tietovisa.model.Tclient;
import com.is.tietovisa.model.TclientFilter;
import com.is.tietovisa.model.TransactionHistory;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
//import com.is.openwayutils.utils.RefData;
import com.is.utils.RefData;
import com.is.utils.Res;

//import com.is.tietovisautils.utils.Utils;

import com.is.ISLogger;


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
	//private static SimpleDateFormat mdf = new SimpleDateFormat("yyMMdd-HHmmss");
	//private static SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd");
	// 1979-07-10T00:00:00
	private static SimpleDateFormat ldf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static List<RefData> listTypeDocument;
	private static List<RefData> listClientTypeWay4;
	private static List<RefData> listAccBal;
	private static List<RefData> listProducts;
	private static List<RefData> listBinCodes;
	private static List<RefData> listIzdBranches;
	private static List<RefData> listAccCondSets;
	private static List<RefData> listCardCondSets;
	private static List<RefData> listRiskLevels;
	private static List<RefData> listDesigns;
	private static List<RefData> listStatesNciAccount;
	private static HashMap<String, String> mapStatesNciAccount;	
	private static List<Product> listProducts2;
	private static HashMap<String, Product> mapProducts2;
	private static List<Branch> listBranchs;
	private static List<Cond_card> listCondCards;
	private static List<Card_design> listCardDesigns;
	
	private static List<RefData> listStopCauses;
	private static HashMap<String, String> mapStopCauses;
	private static List<RefData> listLockStopCauses;
	private static List<RefData> listStatesTieto;
	private static HashMap<String, String> mapStatesTieto;
	private static HashMap<String, List<RefData>> listSubProductByProduct;
	private static List<RefData> listTax;
	private static List<RefData> listRegion;
	private static List<RefData> listDistr;
	private static HashMap<String, List<RefData>> listDistrByRegion;

	private static List<RefData> listCountry;
	private static List<RefData> listCardCondSet_DesignIds;
	private static List<RefData> listTietoRegions;
	private static List<RefData> listTietoCountryTypes;
	private static List<RefData> listWayCountries;
	private static List<RefData> listRezCl;
	private static List<RefData> listGender;
	private static List<RefData> listNation;
    //private static String constBankC;
    //private static String constGroupC;
    public static HashMap<String, String> mapConst;
    String[] columnNamesList;
	static {
		CustomerService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
		CustomerService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
		CustomerService.msql = "select * from ";
		CustomerService.msql2 = "v_bf_tietovisa_client";
	}

	public CustomerService() {
		// this.cvc = new CustomerViewCtrl();
		 this.columnNamesList = new String[] {"Posting.date","Tran.time","Approval code","Merchant","Tran type","Amount in tran.","ccy","Amount in account.","ccy","Tran.fee code","Tran.fee"};

	}

	public static List<Customer> getCustomersFl(final int pageIndex,
			final int pageSize, final CustomerFilter filter, final String alias) {
		//ISLogger.getLogger().error("getCustomersFl start! ");
		
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
		//ISLogger.getLogger().error("getCustomersFl sql string: " +	 sql.toString());
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			int params;
			for (params = 0; params < flFields.size(); ++params) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
				// ISLogger.getLogger().error("FILTER OBJECT: " +
				// flFields.get(params).getColobject());
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
				customer.setP_zip_code(rs.getString("p_zip_code"));
				customer.setId_tieto(rs.getString("id_tieto"));
				customer.setT_r_city(rs.getString("t_city"));
				customer.setT_client_b(rs.getString("t_client_b"));
				customer.setT_cl_type(rs.getString("t_cl_type"));
				customer.setSign_record("4");
				if (rs.getString("id_tieto")!=null && !rs.getString("id_tieto").equals(""))
					customer.setSign_record("2");

				list.add(customer);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					"getCustomersFl SQLException =>" + e.getMessage());
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			list.clear();
			Customer cust = new Customer();
			cust.setSign_error_record(true);
			cust.setName("Error getting IBS customers. Please contact administrator of system.");
			list.add(cust);
			
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
		ISLogger.getLogger().error("getCustomersFl list size = "+list.size());
		
		return list;
		
	}

	public static Long getCardSmsPhoneNumber(final String card) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long phoneNumber = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select phone from bf_visa_CARD_SMS where card = ? and state=1");
			ps.setString(1, card);
			rs = ps.executeQuery();
			if (rs.next()) {
				phoneNumber = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return phoneNumber;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Utils.close(rs);
		Utils.close(ps);
		ConnectionPool.close(c);
		//ISLogger.getLogger().error("select phone: " + phoneNumber);
		return phoneNumber;
	}
	
	public static Res insertCardSmsState(final String card, final String phone, final int state, String alias) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            ISLogger.getLogger().error("insert bf_visa_CARD_SMS");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
                    "begin update bf_visa_CARD_SMS set phone=?, state=? " +
                    "where card=?; if sql%notfound then  " +
                    "insert into bf_visa_CARD_SMS(phone,state,card) values (?,?,?); " + 
  	            	"	end if; "+
  	            	"	end;"
            );
            ps.setString(1, phone);
            ps.setInt(2, state);
            ps.setString(3, card);
            ps.setString(4, phone);
            ps.setInt(5, state);
            ps.setString(6, card);
            ps.execute();
            
            c.commit();
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

	private static void insertCardSmsState(final String card, final String phone, final int state, final Connection c) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into bf_visa_CARD_SMS (card, phone, state) values (?,?,?)");
			ps.setString(1, card);
			ps.setString(2, phone);
			ps.setInt(3, state);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
		Utils.close(ps);
	}

	
	public static boolean isTietoFilterNull (CustomerFilter filter) {
		if ( (filter.getP_family() == null || filter.getP_family().equals("") ) && 
				(filter.getP_first_name() == null || filter.getP_first_name().equals("") ) && 
				(filter.getId_tieto() == null || filter.getId_tieto().equals("") ) && 
				(filter.getT_client_b() == null || filter.getT_client_b().equals("") ) &&   
				(filter.getP_birthday() == null || filter.getP_birthday().equals("") ) &&
				(filter.getP_pinfl() == null || filter.getP_pinfl().equals("") ) &&
				(filter.getP_phone_mobile() == null || filter.getP_phone_mobile().equals("") )
			)
			return true;		
		return false;
	} 
	
	public static List<Customer> getCustomersNciAndTietoFl(final int pageIndex,
			final int pageSize, final CustomerFilter filter, final String alias) {

		List<Customer> list = new ArrayList<Customer>();
		// getting nci customers
		final List<Customer> list_nci = getCustomersFl(pageIndex, pageSize,
				filter, alias);

		// getting tieto customers
		List<Customer> list_tieto;
		if ( !isTietoFilterNull(filter))
			list_tieto = CustomerService.getCustomers_tieto(filter,
				alias, filter.getEndpoint(), true);
		 else 
			list_tieto = new ArrayList<Customer>();
		/*
		 * if (list_nci.size()==1 && list_nci.get(0).isSign_error_record() &&
		 * list_tieto.size()==1 && list_tieto.get(0).isSign_error_record() ) {
		 * Customer cust = new Customer(); cust.setName(
		 * "Error getting customers. Please contact administrator system.");
		 * cust.setSign_error_record(true); list.add(cust); return list; } else
		 * if (list_nci.size()==1 && list_nci.get(0).isSign_error_record() ) {
		 * //Customer cust = new Customer();
		 * //cust.setName("No data found. Please change filter and try again");
		 * //cust.setSign_error_record(true); return list; }
		 */
		// listlarni umumlashtiramiz/qushamiz
		for (int i = 0; i < list_nci.size(); i++) {
			Customer cust = list_nci.get(i).clone(list_nci.get(i));

			if (list_nci.get(i).getId_tieto() != null) {
				for (int j = 0; j < list_tieto.size(); j++) {
					if (!list_tieto.get(j).isSign_error_record()
							&& list_tieto.get(j).getId_tieto()
									.equals(list_nci.get(i).getId_tieto())) /*
																			 * sign=
																			 * 1
																			 */{
						cust.copyTietoFields(list_tieto.get(j), cust);
						cust.setSign_record("1");
						list_tieto.remove(j);
						break;
					}
				}
				if (cust.getSign_record() == null) /* sign=2 */{
					cust.setSign_record("2");
				}
			} else {
				for (int j = 0; j < list_tieto.size(); j++) {
					if (!list_tieto.get(j).isSign_error_record()
							&& list_tieto
									.get(j)
									.getT_client_b()
									.equals(list_nci.get(i).getBranch()
											+ list_nci.get(i).getId_client())) /*
																				 * sign
																				 * =
																				 * 3
																				 */{
						cust.copyTietoFields(list_tieto.get(j), cust);
						cust.setSign_record("3");
						list_tieto.remove(j);
						break;
					}
				}
				if (cust.getSign_record() == null) /* sign=4 */{
					cust.setSign_record("4");
				}
			}
			list.add(cust);
		}
		for (int i = 0; i < list_tieto.size(); i++) {
			Customer cust = new Customer();
			cust.copyTietoFields(list_tieto.get(i), cust);
			cust.setSign_record("5");
			list.add(cust);
		}

		return list;
	}

	public static List<Customer> getCustomersTietoFl(final int pageIndex,
			final int pageSize, final CustomerFilter filter, final String alias) {
		
		// getting tieto customers
		List<Customer> list_tieto;
		
		list_tieto = CustomerService.getCustomers_tieto(filter,
				alias, filter.getEndpoint(), true);		

		return list_tieto;
	}

	public static List<Customer> getCustomers_tieto(CustomerFilter filter,
			String alias, String pUrl, Boolean ignoreDuplicates) {
		List<Customer> list = new ArrayList<Customer>();
		RowType_ListCustomers_Request rt_listCustReq = new RowType_ListCustomers_Request();
		rt_listCustReq = getFilterParams(filter);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_listCustReq);
		} catch (Exception e22) {
			ISLogger.getLogger().error(
					"ListCustomers. making query json. error: " + e22.getMessage());
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			Customer rs = new Customer();
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("ListCustomers query json text: " + v_json);
		System.out.println("ListCustomers query json text: " + v_json);
		
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("ListCustomers postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"ListCustomers postUtils.sendData err: " + e.getMessage());
			Customer rs = new Customer();
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		ISLogger.getLogger().error("ListCustomers response: " + v_res);
		System.out.println("ListCustomers response = " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			Customer rs = new Customer();
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			//ListType_GenericHolder rslt = null;
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"ListCustomers response mapper err: " + e.getMessage());
				System.out.println("ListCustomers response mapper err: " + e.getMessage());
				Customer rs = new Customer();
				rs.setSign_error_record(true);
				rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
		}
		if (!rslt.getErr_code().equals("0")) {
			ISLogger.getLogger().error(
					"ListCustomers response err: " + rslt.getErr_text());
			Customer rs = new Customer();
			rs.setSign_error_record(true);
			rs.setT_f_names("Error getting TIETO customers. Please contact administrator of system.");
			list.add(rs);
			return list;		
		}
		
		list = makeListFromListType_GenericHolder(rslt.getLt_holder(), ignoreDuplicates);
		
		return list;
	}

	private static RowType_ListCustomers_Request getFilterParams(
			CustomerFilter filter) {
		RowType_ListCustomers_Request params = new RowType_ListCustomers_Request();
		params.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		if (filter.getP_first_name() != null) {
			params.setF_NAMES(filter.getName());
		}
		if (filter.getP_family() != null) {
			params.setSURNAME(filter.getP_family());
		}
		if (filter.getId_tieto() != null) {
			params.setCLIENT(filter.getId_tieto());
		}
		if (filter.getT_client_b() != null) {
			params.setCLIENT_B(filter.getT_client_b());
		}
		//if (filter.getClient_b() != null) {
		//	params.setCLIENT_B(filter.getClient_b());
		//}
		if (filter.getP_phone_mobile() != null) {		
			params.setR_MOB_PHONE(filter.getP_phone_mobile());
		}
		if (filter.getP_pinfl() != null) {		
			params.setPERSON_CODE(filter.getP_pinfl());
		}
		if (filter.getP_birthday() != null) { 
			 //SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy"); 
			 // format.format(); 
			 Calendar cal =	  Calendar.getInstance(); cal.setTime(filter.getP_birthday());
		     cal.add(Calendar.DAY_OF_MONTH, +1); 
		     params.setB_DATE(cal); 
		}		 
		return params;
	}

	public static List<Customer> makeListFromListType_GenericHolder(
			ListType_GenericHolder details, Boolean ignoreDuplicates) {
		List<Customer> list = new ArrayList<Customer>();

		if (details.value == null)
			return list;
		String prev_client_id = "_";
		for (int i = 0; i < details.value.length; i++) {
			Customer rs = new Customer();

			for (int j = 0; j < details.value[i].length; j++) {
				if (details.value[i][j].getName().equals("CLIENT"))
					rs.setId_tieto(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("STATUS"))
					rs.setT_status(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("CLIENT_B"))
					rs.setT_client_b(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("F_NAMES"))
					rs.setT_f_names(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("SURNAME"))
					rs.setT_surname(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("MIDLE_NAME"))
					rs.setT_midle_name(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("PERSON_CODE"))
					rs.setT_person_code(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("B_DATE")
						&& details.value[i][j].getValue() != null
						&& details.value[i][j].getValue().trim().length() >= 10)
					try {
						rs.setT_b_date(df.parse(details.value[i][j].getValue()
								.trim().substring(0, 10)));
					} catch (Exception e) {
					}
				if (details.value[i][j].getName().equals("DOC_SINCE")
							&& details.value[i][j].getValue() != null
							&& details.value[i][j].getValue().trim().length() >= 10)
						try {
							rs.setT_doc_since(df.parse(details.value[i][j].getValue()
									.trim().substring(0, 10)));
						} catch (Exception e) {
						}
			    if (details.value[i][j].getName().equals("SERIAL_NO"))
					rs.setT_serial_no(details.value[i][j].getValue());
			    if (details.value[i][j].getName().equals("ISSUED_BY"))
					rs.setT_issued_by(details.value[i][j].getValue());			    
				if (details.value[i][j].getName().equals("RESIDENT"))
					rs.setT_resident(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_PHONE"))
					rs.setT_r_phone(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_MOB_PHONE"))
					rs.setT_rmob_phone(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_CITY"))
					rs.setT_r_city(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_CNTRY"))
					rs.setT_r_cntry(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_PCODE"))
					rs.setT_r_pcode(details.value[i][j].getValue());				
				if (details.value[i][j].getName().equals("REGION"))
					rs.setT_region(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_STREET"))
					rs.setT_r_street(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("R_E_MAILS"))
					rs.setT_r_emails(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("SEX"))
					rs.setT_sex(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("CARD"))
					rs.setT_card(details.value[i][j].getValue());
				if (details.value[i][j].getName().equals("BANK_C"))
					rs.setT_bank_c(details.value[i][j].getValue());
				rs.setSign_record("5");
			}
			if (!ignoreDuplicates || !prev_client_id.equals(rs.getId_tieto())) {
				list.add(rs);
			}
			prev_client_id = rs.getId_tieto();
		}
		return list;
	}

	public static Res getRealCard_tieto(String cardNumber, String alias, String pUrl) {
		Res res= new Res();
		RowType_GetRealCard_Request rt_Req = new RowType_GetRealCard_Request();

		rt_Req.setCARD(cardNumber);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error("GetRealCard. making json query error: " + e22.getMessage());
			res.setCode(1);
			res.setName("Error getting real card. Please contact administrator of system.");
			return res;
		} finally {
		}
		ISLogger.getLogger().error("GetRealCard query json text: " + v_json);
		System.out.println("GetRealCard query json text: " + v_json);
				
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("GetRealCard postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"GetRealCard postUtils.sendData err: " + e.getMessage());
			res.setCode(1);
			res.setName("Error getting real card. Please contact administrator of system.");
			return res;
		}
		ISLogger.getLogger().error(	"GetRealCard response: " + v_res);
		System.out.println("GetRealCard response = " + v_res);
		
		GetRealCardResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			res.setCode(1);
			res.setName("Error getting real card. Please contact administrator of system.");
			return res;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			try {
				rslt = mapper.readValue(v_res, GetRealCardResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error("GetRealCard result mapper err: " + e.getMessage());
				System.out.println("GetRealCard result mapper err: " + e.getMessage());
				res.setCode(1);
				res.setName("Error getting real card. Please contact administrator of system.");
				return res;
			}
			if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
				if (rslt.getOperResponseInfo() != null && rslt.getOperResponseInfo().getResponse_code()==BigInteger.valueOf(0)){
					if (rslt.getRtrc_parameters() != null && rslt.getRtrc_parameters().getRCARD() !=null){
						res.setCode(0);
						res.setName(rslt.getRtrc_parameters().getRCARD());
					}
				}
				else {
					res.setCode(1);
					res.setName("Error getting real card. "+rslt.getOperResponseInfo().getResponse_code()+" : " + rslt.getOperResponseInfo().getError_description()+". Please contact administrator of system.");
					return res;
				}

			} else {
				res.setCode(1);
				res.setName("Error getting real card. "+rslt.getErr_text()+". Please contact administrator of system.");
				return res;
			}
		}
		return res;
	}
	
	public static List<AccInfo> getAccounts_tieto(String tietoId,
			String clientB, String alias, String pUrl) {
		List<AccInfo> list = new ArrayList<AccInfo>();
		RowType_ListAccounts_Request rt_Req = new RowType_ListAccounts_Request();

		rt_Req.setCLIENT(tietoId);
		rt_Req.setCLIENT_B(clientB);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"ListAccounts. making json query error: " + e22.getMessage());
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("ListAccounts query json text: " + v_json);
		System.out.println("ListAccounts query json text: " + v_json);
				
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("ListAccounts postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"ListAccounts postUtils.sendData err: " + e.getMessage());
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		ISLogger.getLogger().error(	"ListAccounts response: " + v_res);
		System.out.println("ListAccounts response = " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"ListAccounts result mapper err: " + e.getMessage());
				System.out.println("ListAccounts result mapper err: " + e.getMessage());
				AccInfo rs = new AccInfo();
				rs.setSign_error_record(true);
				rs.setF_names("Error getting TIETO accounts. Please contact administrator of system.");
				list.add(rs);
				return list;
			}			
		}
		list = makeAccInfoList(rslt.getLt_holder());
		return list;
	}

	public static ArrayList<AccInfo> makeAccInfoList(
			ListType_GenericHolder details) {

		ArrayList<AccInfo> list = new ArrayList<AccInfo>();

		if (details.value == null)
			return list;

		for (int i = 0; i < details.value.length; i++) {
			AccInfo addItem = new AccInfo();

			for (int j = 0; j < details.value[i].length; j++) {

				String name = details.value[i][j].getName();
				String value = details.value[i][j].getValue();

				if (name.equals("CARD_ACCT") && !value.equals("")) {
					addItem.setCard_acct(value);
				} else if (name.equals("TRANZ_ACCT") && !value.equals("")) {
					addItem.setTranz_acct(value);
				} else if (name.equals("STATUS") && !value.equals("")) {
					addItem.setStatus(value);
				} else if (name.equals("ACC_PRTY") && !value.equals("")) {
					addItem.setAcc_prty(value);
				} else if (name.equals("C_ACCNT_TYPE") && !value.equals("")) {
					addItem.setC_accnt_type(value);
				} else if (name.equals("CCY") && !value.equals("")) {
					addItem.setCcy(value);
				} else if (name.equals("COND_SET") && !value.equals("")) {
					addItem.setCond_set(value);
				} else if (name.equals("CLIENT_B") && !value.equals("")) {
					addItem.setClient_b(value);
				} else if (name.equals("CLIENT") && !value.equals("")) {
					addItem.setClient(value);
				} else if (name.equals("F_NAMES") && !value.equals("")) {
					addItem.setF_names(value);
				} else if (name.equals("SURNAME") && !value.equals("")) {
					addItem.setSurname(value);
				} else if (name.equals("B_BR_ID") && !value.equals("")) {
					addItem.setB_br_id(value);
				} else if (name.equals("OFFICE_ID") && !value.equals("")) {
					addItem.setOffice_id(value);
				} else if (name.equals("MAIN_ROW") && !value.equals("")) {
					addItem.setMain_row(value);
				} else if (name.equals("ACCOUNT_NO") && !value.equals("")) {
					addItem.setAccount_no(Long.valueOf(value));
				} else if (name.equals("CARD_NAME") && !value.equals("")) {
					addItem.setCard_name(value);
				} else if (name.equals("BANK_C") && !value.equals("")) {
					addItem.setBank_c(value);
				} else if (name.equals("GROUPC") && !value.equals("")) {
					addItem.setGroupc(value);
				}
			}
			list.add(addItem);
		}
		return list;
	}
	
	public static List<AccInfo> getAccountsByCard_tieto(String cardNo,
			String alias, String pUrl) {
		List<AccInfo> list = new ArrayList<AccInfo>();
		RowType_ListAccountsByCard_Request rt_Req = new RowType_ListAccountsByCard_Request();

		rt_Req.setCARD(cardNo);
		rt_Req.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		rt_Req.setGROUPC(CustomerService.mapConst.get(Cons.groupc));

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"ListAccountsByCard. making json query error: " + e22.getMessage());
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts by card. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("ListAccountsByCard query json text: " + v_json);
		System.out.println("ListAccountsByCard query json text: " + v_json);
				
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("ListAccountsByCard postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"ListAccountsByCard postUtils.sendData err: " + e.getMessage());
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts by card. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		ISLogger.getLogger().error(	"ListAccountsByCard response: " + v_res);
		System.out.println("ListAccountsByCard response = " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			AccInfo rs = new AccInfo();
			rs.setSign_error_record(true);
			rs.setF_names("Error getting TIETO accounts by card. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"ListAccountsByCard result mapper err: " + e.getMessage());
				System.out.println("ListAccountsByCard result mapper err: " + e.getMessage());
				AccInfo rs = new AccInfo();
				rs.setSign_error_record(true);
				rs.setF_names("Error getting TIETO accounts by card. Please contact administrator of system.");
				list.add(rs);
				return list;
			}			
		}
		list = makeAccInfoList(rslt.getLt_holder());
		return list;
	}
	
	public static List<CardInfo> getCards_tieto (BigInteger accountNo,
			 String alias, String pUrl) throws ParseException {
		List<CardInfo> list = new ArrayList<CardInfo>();
		RowType_ListCardsByAccount_Request rt_Req = new RowType_ListCardsByAccount_Request();
		
		rt_Req.setACCOUNT_NO(accountNo);
		//rt_Req.setCLIENT_B(clientB);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"ListCardsByAccount. making json query error : " + e22.getMessage());
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("ListCardsByAccount query json text: " + v_json);
		System.out.println("ListCardsByAccount query json text: " + v_json);
		
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("ListCardsByAccount postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"ListCardsByAccount postUtils.sendData err: " + e.getMessage());
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		System.out.println("ListCardsByAccount response = " + v_res);
		ISLogger.getLogger().error("ListCardsByAccount response: " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"ListCardsByAccount result mapper err: " + e.getMessage());
				System.out.println("ListCardsByAccount result mapper err: " + e.getMessage());
				CardInfo rs = new CardInfo();
				rs.setSign_error_record(true);
				rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
		}
		
		list = makeCardInfoList(rslt.getLt_holder());

		return list;
	}
	
	public static ArrayList<CardInfo> makeCardInfoList  (
			ListType_GenericHolder details) throws ParseException {

		ArrayList<CardInfo> list = new ArrayList<CardInfo>();

		if (details == null)
			return list;

		if (details.value == null)
			return list;
		//shuerda 1 ta qushimcha vaqtinchalik array yasaymiz
		//maqsan agre_nom bir xil bulgan kartalar ichidan amal qilish muddati eng kattasinigina aktivniy karta hisoblaymiz
		//qolganlarini Reissued deb belgi quyamiz.
		ArrayList<CardInfo> tempList = new ArrayList<CardInfo>();

		for (int i = 0; i < details.value.length; i++) {
			CardInfo addItem = new CardInfo();
			addItem.setSign_reissued(false);

			for (int j = 0; j < details.value[i].length; j++) {

				String name = details.value[i][j].getName();
				String value = details.value[i][j].getValue();

				if (name.equals("ACCOUNT_NO") && !value.equals("")) {
					addItem.setACCOUNT_NO(value);
				} else if (name.equals("CARD_ACCT") && !value.equals("")) {
					addItem.setCARD_ACCT(value);
				} else if (name.equals("CCY") && !value.equals("")) {
					addItem.setCurrency(value);
				} else if (name.equals("CARD") && !value.equals("")) {
					addItem.setCARD(value);
				} else if (name.equals("BASE_SUPP") && !value.equals("")) {
					addItem.setBASE_SUPP(value);
				} else if (name.equals("STATUS") && !value.equals("")) {
					addItem.setSTATUS(value);
				} else if (name.equals("STATUS1") && !value.equals("")) {
					addItem.setSTATUS1(value);
				} else if (name.equals("STATUS2") && !value.equals("")) {
					addItem.setSTATUS2(value);
				} else if (name.equals("STOP_CAUSE") && !value.equals("")) {
					addItem.setSTOP_CAUSE(value);
				} else if (name.equals("EXPIRY") && !value.equals("")) {
					addItem.setEXPIRY(value);
				} else if (name.equals("EXPIRY1") && !value.equals("")) {
					addItem.setEXPIRY1(value);
				} else if (name.equals("EXPIRY2") && !value.equals("")) {
					addItem.setEXPIRY2(value);
				} else if (name.equals("COND_SET") && !value.equals("")) {
					addItem.setCOND_SET(value);
				} else if (name.equals("RISK_LEVEL") && !value.equals("")) {
					addItem.setRISK_LEVEL(value);
				} else if (name.equals("CLIENT_ID") && !value.equals("")) {
					addItem.setClient_id(value);
				} else if (name.equals("CLIENT") && !value.equals("")) {
					addItem.setClient(value);
				} else if (name.equals("CL_ROLE") && !value.equals("")) {
					addItem.setCL_ROLE(value);
				} else if (name.equals("AGREEMENT_KEY") && !value.equals("")) {
					addItem.setAGREEMENT_KEY(value);
				} else if (name.equals("CARD_NAME") && !value.equals("")) {
					addItem.setCARD_NAME(value);
				}
			}
			
			list.add(addItem);
			tempList.add(addItem);
		}
		
		//ISLogger.getLogger().error("not err. "+ addItem.getCARD()+"-"+ addItem.getCARD_NAME() +"-"+addItem.getAGREEMENT_KEY()+"-"+addItem.getEXPIRY1()+"-"+tempList.size());
		for (int i = 0; i < list.size(); i++) {
			for (int k = 0; k < tempList.size(); k++) {
				if  (tempList.get(k).getAGREEMENT_KEY().equals(list.get(i).getAGREEMENT_KEY()) || tempList.get(k).getAGREEMENT_KEY()==list.get(i).getAGREEMENT_KEY()) 
				{
					java.util.Date	d1 = df.parse(list.get(i).getEXPIRY1());
					java.util.Date	d2 = df.parse(tempList.get(k).getEXPIRY1());
				
					//if(d1.compareTo(d2) < 0)  = bu true bulsa d2 katta degani
	                if (d1.compareTo(d2) < 0)  
					{
	                	list.get(i).setSign_reissued(true);
					}
				} 
			}
		}
		
		return list;
	}

	
	public static List<CardInfo> getCustomerCards_tieto(String clientNo,
			 String alias, String pUrl) throws ParseException {
		List<CardInfo> list = new ArrayList<CardInfo>();
		RowType_ListCustomerCards_Request rt_Req = new RowType_ListCustomerCards_Request();

		rt_Req.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		rt_Req.setCLIENT(clientNo);	

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"ListCustomerCards. making json query error : " + e22.getMessage());
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("ListCustomerCards query json text: " + v_json);
		System.out.println("ListCustomerCards query json text: " + v_json);
		
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("ListCustomerCards postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"ListCustomerCards postUtils.sendData err: " + e.getMessage());
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		System.out.println("ListCustomerCards response = " + v_res);
		ISLogger.getLogger().error("ListCustomerCards response: " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			CardInfo rs = new CardInfo();
			rs.setSign_error_record(true);
			rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"ListCustomerCards result mapper err: " + e.getMessage());
				System.out.println("ListCustomerCards result mapper err: " + e.getMessage());
				CardInfo rs = new CardInfo();
				rs.setSign_error_record(true);
				rs.setCARD_NAME("Error getting TIETO cards. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
		}
		
		list = makeCardInfoList(rslt.getLt_holder());

		return list;
	}
	
	public static ArrayList<AccountBalance> makeAccountBalanceList(
			ListType_GenericHolder details) {

		ArrayList<AccountBalance> list = new ArrayList<AccountBalance>();

		if (details == null)
			return list;

		if (details.value == null)
			return list;

		for (int i = 0; i < details.value.length; i++) {
			AccountBalance addItem = new AccountBalance();

			for (int j = 0; j < details.value[i].length; j++) {

				String name = details.value[i][j].getName();
				String value = details.value[i][j].getValue();
				
			    if (name.equals("ACC_STATUS") && !value.equals("")) { addItem.setACC_STATUS(value);
				} else if (name.equals("ACCOUNT_NO") && !value.equals("")) { addItem.setACCOUNT_NO(value);
				} else if (name.equals("AVAIL_AMOUNT") && !value.equals("")) { addItem.setAVAIL_AMOUNT(value);
				} else if (name.equals("BANK_C") && !value.equals("")) { addItem.setBANK_C(value);
				} else if (name.equals("C_ACCNT_TYPE") && !value.equals("")) { addItem.setC_ACCNT_TYPE(value);
				} else if (name.equals("CARD") && !value.equals("")) { addItem.setCARD(value);
				} else if (name.equals("CARD_ACCT") && !value.equals("")) { addItem.setCARD_ACCT(value);
				} else if (name.equals("CARD_LAST_ACTIV_DATE") && !value.equals("")) { addItem.setCARD_LAST_ACTIV_DATE(value);
				} else if (name.equals("CARD_STATUS") && !value.equals("")) { addItem.setCARD_STATUS(value);
				} else if (name.equals("CCY") && !value.equals("")) { addItem.setCCY(value);
				} else if (name.equals("CCY_EXP") && !value.equals("")) { addItem.setCCY_EXP(value);
				} else if (name.equals("CRD") && !value.equals("")) { addItem.setCRD(value);
				} else if (name.equals("CRD_EXPIRY") && !value.equals("")) { addItem.setCRD_EXPIRY(value);
				} else if (name.equals("END_BAL") && !value.equals("")) { addItem.setEND_BAL(value);
				} else if (name.equals("EXPIRY1") && !value.equals("")) { addItem.setEXPIRY1(value);
				} else if (name.equals("GROUPC") && !value.equals("")) { addItem.setGROUPC(value);
				} else if (name.equals("LOCKED_AMOUNT") && !value.equals("")) { addItem.setLOCKED_AMOUNT(value);
				} else if (name.equals("MAIN_ROW") && !value.equals("")) { addItem.setMAIN_ROW(value);
				} else if (name.equals("STOP_CAUSE") && !value.equals("")) { addItem.setSTOP_CAUSE(value);

				}
			}
			list.add(addItem);
		}
		return list; 
	}
	
	public static List<AccountBalance> getAccountBalance_tieto(String cardNo, java.util.Date begDate, java.util.Date endDate,  
			 String alias, String pUrl) {
		List<AccountBalance> list = new ArrayList<AccountBalance>();
		RowType_AccBalanceQueryByCard_Request rt_Req = new RowType_AccBalanceQueryByCard_Request();

		rt_Req.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		rt_Req.setGROUPC(CustomerService.mapConst.get(Cons.groupc));
		rt_Req.setCARD(cardNo);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"queryAccountBalanceByCard. making json query error : " + e22.getMessage());
			AccountBalance rs = new AccountBalance();
			rs.setSign_error_record(true);
			rs.setCARD("Error getting TIETO Account balance. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("queryAccountBalanceByCard query json text: " + v_json);
		System.out.println("queryAccountBalanceByCard query json text: " + v_json);
		
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("queryAccountBalanceByCard postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"queryAccountBalanceByCard postUtils.sendData err: " + e.getMessage());
			AccountBalance rs = new AccountBalance();
			rs.setSign_error_record(true);
			rs.setCARD("Error getting TIETO Account balance. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		System.out.println("queryAccountBalanceByCard response = " + v_res);
		ISLogger.getLogger().error("queryAccountBalanceByCard response: " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			AccountBalance rs = new AccountBalance();
			rs.setSign_error_record(true);
			rs.setCARD("Error getting TIETO Account balance. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"queryAccountBalanceByCard result mapper err: " + e.getMessage());
				System.out.println("queryAccountBalanceByCard result mapper err: " + e.getMessage());
				AccountBalance rs = new AccountBalance();
				rs.setSign_error_record(true);
				rs.setCARD("Error getting TIETO Account balance. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
		}

		if (!rslt.getErr_code().equals("0")) {
			ISLogger.getLogger().error(
					"queryAccountBalanceByCard response err: " + rslt.getErr_text());
			AccountBalance rs = new AccountBalance();
			rs.setSign_error_record(true);
			rs.setCARD("Error getting TIETO Account balance. Please contact administrator of system.");
			list.add(rs);
			return list;		
		}
		
		list = makeAccountBalanceList(rslt.getLt_holder());

		return list;
	}

	
	public static ArrayList<TransactionHistory> makeTransactionHistoryList(
			ListType_GenericHolder details) {

		ArrayList<TransactionHistory> list = new ArrayList<TransactionHistory>();

		if (details == null)
			return list;

		if (details.value == null)
			return list;

		for (int i = 0; i < details.value.length; i++) {
			TransactionHistory addItem = new TransactionHistory();

			addItem.setAMOUNT_NET_FORMAT("");
			addItem.setTRAN_AMT_FORMAT("");
			addItem.setTR_FEE_FORMAT("");
			
			for (int j = 0; j < details.value[i].length; j++) {

				String name = details.value[i][j].getName();
				String value = details.value[i][j].getValue();
				
				if (name.equals("ABVR_NAME") && !value.equals("")) { addItem.setABVR_NAME(value);
				} else if (name.equals("ACCNT_CCY") && !value.equals("")) { addItem.setACCNT_CCY(value);
				} else if (name.equals("ACCOUNT_NO") && !value.equals("")) { addItem.setACCOUNT_NO(value);
				} else if (name.equals("ACQREF_NR") && !value.equals("")) { addItem.setACQREF_NR(value);
				} else if (name.equals("ADD_INFO") && !value.equals("")) { addItem.setADD_INFO(value);
				} else if (name.equals("AMOUNT_NET") && !value.equals("")) { addItem.setAMOUNT_NET(value);
				addItem.setAMOUNT_NET_FORMAT(String.format("%,.2f", Double.parseDouble(addItem.getAMOUNT_NET())/100));
				} else if (name.equals("APR_CODE") && !value.equals("")) { addItem.setAPR_CODE(value);
				} else if (name.equals("APR_SCR") && !value.equals("")) { addItem.setAPR_SCR(value);
				} else if (name.equals("BANK_C") && !value.equals("")) { addItem.setBANK_C(value);
				} else if (name.equals("CARD") && !value.equals("")) { addItem.setCARD(value);
				} else if (name.equals("CARD_ACCT") && !value.equals("")) { addItem.setCARD_ACCT(value);
				} else if (name.equals("CCY_EXP") && !value.equals("")) { addItem.setCCY_EXP(value);
				} else if (name.equals("CITY") && !value.equals("")) { addItem.setCITY(value);
				} else if (name.equals("CL_ACCT_KEY") && !value.equals("")) { addItem.setCL_ACCT_KEY(value);
				} else if (name.equals("CLIENT") && !value.equals("")) { addItem.setCLIENT(value);
				} else if (name.equals("COUNTERPARTY") && !value.equals("")) { addItem.setCOUNTERPARTY(value);
				} else if (name.equals("COUNTRY") && !value.equals("")) { addItem.setCOUNTRY(value);
				} else if (name.equals("CTIME") && !value.equals("")) { addItem.setCTIME(value);
				} else if (name.equals("DEAL_DESC") && !value.equals("")) { addItem.setDEAL_DESC(value);
				} else if (name.equals("EXP_DATE") && !value.equals("")) { addItem.setEXP_DATE(value);
				} else if (name.equals("FLD_104") && !value.equals("")) { addItem.setFLD_104(value);
				} else if (name.equals("GROUPC") && !value.equals("")) { addItem.setGROUPC(value);
				} else if (name.equals("INTERNAL_NO") && !value.equals("")) { addItem.setINTERNAL_NO(value);
				} else if (name.equals("LOCKING_FLAG") && !value.equals("")) { addItem.setLOCKING_FLAG(value);
				} else if (name.equals("MCC_CODE") && !value.equals("")) { addItem.setMCC_CODE(value);
				} else if (name.equals("MERCHANT") && !value.equals("")) { addItem.setMERCHANT(value);
				} else if (name.equals("POINT_CODE") && !value.equals("")) { addItem.setPOINT_CODE(value);
				} else if (name.equals("POST_DATE") && !value.equals("")) { addItem.setPOST_DATE(value);
				} else if (name.equals("REC_DATE") && !value.equals("")) { addItem.setREC_DATE(value);
				} else if (name.equals("ROW_NUMB") && !value.equals("")) { addItem.setROW_NUMB(value);
				} else if (name.equals("STAN") && !value.equals("")) { addItem.setSTAN(value);
				} else if (name.equals("TERM_ID") && !value.equals("")) { addItem.setTERM_ID(value);
				} else if (name.equals("TR_CODE") && !value.equals("")) { addItem.setTR_CODE(value);
				} else if (name.equals("TR_CODE2") && !value.equals("")) { addItem.setTR_CODE2(value);
				} else if (name.equals("TR_FEE") && !value.equals("")) { addItem.setTR_FEE(value);
				addItem.setTR_FEE_FORMAT(String.format("%,.2f", Double.parseDouble(addItem.getTR_FEE())/100));
				} else if (name.equals("TR_FEE2") && !value.equals("")) { addItem.setTR_FEE2(value);
				} else if (name.equals("TRAN_AMT") && !value.equals("")) { addItem.setTRAN_AMT(value);
				addItem.setTRAN_AMT_FORMAT(String.format("%,.2f", Double.parseDouble(addItem.getTRAN_AMT())/100));
				} else if (name.equals("TRAN_CCY") && !value.equals("")) { addItem.setTRAN_CCY(value);
				} else if (name.equals("TRAN_DATE_TIME") && !value.equals("")) { addItem.setTRAN_DATE_TIME(value);
				} else if (name.equals("TRAN_TYPE") && !value.equals("")) { addItem.setTRAN_TYPE(value);
				}
			}
			list.add(addItem);
		}
		return list;
	}

	public static List<TransactionHistory> getCardHistory_tieto_fake(String cardNo, java.util.Date begDate, java.util.Date endDate,  
			 String alias, String pUrl) {
		List<TransactionHistory> list = new ArrayList<TransactionHistory>();

		TransactionHistory addItem = new TransactionHistory();
		 addItem.setACCNT_CCY("value");
		 addItem.setACCOUNT_NO("акконут но");
		 addItem.setACQREF_NR("5465465");
		 addItem.setADD_INFO("value");
		 addItem.setAMOUNT_NET("value");
		 addItem.setAPR_CODE("value");
		 addItem.setAPR_SCR("value");
		 addItem.setBANK_C("value");
		 addItem.setCARD("value");
		 addItem.setCARD_ACCT("value");
		 addItem.setCCY_EXP("value");
		 addItem.setCITY("value");
		 addItem.setCL_ACCT_KEY("value");
		 addItem.setCLIENT("value");
		 addItem.setCOUNTERPARTY("value");
		 addItem.setCOUNTRY("value");
		 addItem.setCTIME("value");
		 addItem.setDEAL_DESC("value");
		 addItem.setEXP_DATE("value");
		 addItem.setFLD_104("value");
		 addItem.setGROUPC("value");
		 addItem.setINTERNAL_NO("value");
		 addItem.setLOCKING_FLAG("value");
		 addItem.setMCC_CODE("value");
		 addItem.setMERCHANT("value");
		 addItem.setPOINT_CODE("value");
		 addItem.setPOST_DATE("value");
		 addItem.setREC_DATE("value");
		 addItem.setROW_NUMB("value");
		 addItem.setSTAN("value");
		 addItem.setTERM_ID("value");
		 addItem.setTR_CODE("value");
		 addItem.setTR_CODE2("value");
		 addItem.setTR_FEE("value");
		 addItem.setTR_FEE2("value");
		 addItem.setTRAN_AMT("value");
		 addItem.setTRAN_CCY("value");
		 addItem.setTRAN_DATE_TIME("value");
		 addItem.setTRAN_TYPE("value");
		 list.add(addItem);
		 
		  addItem = new TransactionHistory();
		 addItem.setACCNT_CCY("value");
		 addItem.setACCOUNT_NO("акконут но");
		 addItem.setACQREF_NR("5465465");
		 addItem.setADD_INFO("value");
		 addItem.setAMOUNT_NET("value");
		 addItem.setAPR_CODE("value");
		 addItem.setAPR_SCR("value");
		 addItem.setBANK_C("value");
		 addItem.setCARD("value");
		 addItem.setCARD_ACCT("value");
		 addItem.setCCY_EXP("value");
		 addItem.setCITY("value");
		 addItem.setCL_ACCT_KEY("value");
		 addItem.setCLIENT("value");
		 addItem.setCOUNTERPARTY("value");
		 addItem.setCOUNTRY("value");
		 addItem.setCTIME("value");
		 addItem.setDEAL_DESC("value");
		 addItem.setEXP_DATE("value");
		 addItem.setFLD_104("value");
		 addItem.setGROUPC("value");
		 addItem.setINTERNAL_NO("value");
		 addItem.setLOCKING_FLAG("value");
		 addItem.setMCC_CODE("value");
		 addItem.setMERCHANT("value");
		 addItem.setPOINT_CODE("value");
		 addItem.setPOST_DATE("value");
		 addItem.setREC_DATE("value");
		 addItem.setROW_NUMB("value");
		 addItem.setSTAN("value");
		 addItem.setTERM_ID("value");
		 addItem.setTR_CODE("value");
		 addItem.setTR_CODE2("value");
		 addItem.setTR_FEE("value");
		 addItem.setTR_FEE2("value");
		 addItem.setTRAN_AMT("value");
		 addItem.setTRAN_CCY("value");
		 addItem.setTRAN_DATE_TIME("value");
		 addItem.setTRAN_TYPE("value");
		 list.add(addItem);
		 
		  addItem = new TransactionHistory();
		 addItem.setACCNT_CCY("value");
		 addItem.setACCOUNT_NO("акконут но");
		 addItem.setACQREF_NR("5465465");
		 addItem.setADD_INFO("value");
		 addItem.setAMOUNT_NET("value");
		 addItem.setAPR_CODE("value");
		 addItem.setAPR_SCR("value");
		 addItem.setBANK_C("value");
		 addItem.setCARD("value");
		 addItem.setCARD_ACCT("value");
		 addItem.setCCY_EXP("value");
		 addItem.setCITY("value");
		 addItem.setCL_ACCT_KEY("value");
		 addItem.setCLIENT("value");
		 addItem.setCOUNTERPARTY("value");
		 addItem.setCOUNTRY("value");
		 addItem.setCTIME("value");
		 addItem.setDEAL_DESC("value");
		 addItem.setEXP_DATE("value");
		 addItem.setFLD_104("value");
		 addItem.setGROUPC("value");
		 addItem.setINTERNAL_NO("value");
		 addItem.setLOCKING_FLAG("value");
		 addItem.setMCC_CODE("value");
		 addItem.setMERCHANT("value");
		 addItem.setPOINT_CODE("value");
		 addItem.setPOST_DATE("value");
		 addItem.setREC_DATE("value");
		 addItem.setROW_NUMB("value");
		 addItem.setSTAN("value");
		 addItem.setTERM_ID("value");
		 addItem.setTR_CODE("value");
		 addItem.setTR_CODE2("value");
		 addItem.setTR_FEE("value");
		 addItem.setTR_FEE2("value");
		 addItem.setTRAN_AMT("value");
		 addItem.setTRAN_CCY("value");
		 addItem.setTRAN_DATE_TIME("value");
		 addItem.setTRAN_TYPE("value");
		 list.add(addItem);
		 
		return list;
	}
	
	public static List<TransactionHistory> getCardHistory_tieto(String cardNo, java.util.Date begDate, java.util.Date endDate,  
			 String alias, String pUrl) {
		List<TransactionHistory> list = new ArrayList<TransactionHistory>();
		RowType_TransactionHist_Request rt_Req = new RowType_TransactionHist_Request();

		rt_Req.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		rt_Req.setGROUPC(CustomerService.mapConst.get(Cons.groupc));
		rt_Req.setCARD(cardNo);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begDate);
		rt_Req.setBEGIN_DATE(calendar);
		calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		rt_Req.setEND_DATE(calendar);

		PostUtils postUtils = new PostUtils();

		// class dan json string qaytaradigan funksiya
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(rt_Req);
		} catch (Exception e22) {
			e22.printStackTrace();
			System.out.println("e22 = " + e22.getMessage());
			ISLogger.getLogger().error(
					"queryTransactionHistory. making json query error : " + e22.getMessage());
			TransactionHistory rs = new TransactionHistory();
			rs.setSign_error_record(true);
			rs.setPOST_DATE("Error getting TIETO Transaction history. Please contact administrator of system.");
			list.add(rs);
			return list;
		} finally {
		}
		ISLogger.getLogger().error("queryTransactionHistory query json text: " + v_json);
		System.out.println("queryTransactionHistory query json text: " + v_json);
		
		String v_res = "";
		try {
			v_res = postUtils.sendData(pUrl, v_json);
		} catch (Exception e) {
			System.out.println("queryTransactionHistory postUtils.sendData err: " + e.getMessage());
			ISLogger.getLogger().error(
					"queryTransactionHistory postUtils.sendData err: " + e.getMessage());
			TransactionHistory rs = new TransactionHistory();
			rs.setSign_error_record(true);
			rs.setPOST_DATE("Error getting TIETO Transaction history. Please contact administrator of system.");
			list.add(rs);
			return list;
		}
		System.out.println("queryTransactionHistory response = " + v_res);
		ISLogger.getLogger().error("queryTransactionHistory response: " + v_res);
		
		ListTypeResponse rslt = null;
		if ((v_res == "" || v_res.equals(""))) {
			TransactionHistory rs = new TransactionHistory();
			rs.setSign_error_record(true);
			rs.setPOST_DATE("Error getting TIETO Transaction history. Please contact administrator of system.");
			list.add(rs);
			return list;
		} else {
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			
			try {
				rslt = mapper.readValue(v_res, ListTypeResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"queryTransactionHistory result mapper err: " + e.getMessage());
				System.out.println("queryTransactionHistory result mapper err: " + e.getMessage());
				TransactionHistory rs = new TransactionHistory();
				rs.setSign_error_record(true);
				rs.setPOST_DATE("Error getting TIETO transaction history. Please contact administrator of system.");
				list.add(rs);
				return list;
			}
		}

		if (!rslt.getErr_code().equals("0")) {
			ISLogger.getLogger().error(
					"queryTransactionHistory response err: " + rslt.getErr_text());
			TransactionHistory rs = new TransactionHistory();
			rs.setSign_error_record(true);
			rs.setPOST_DATE("Error getting TIETO Transaction history. Please contact administrator of system.");
			list.add(rs);
			return list;		
		}
		
		list = makeTransactionHistoryList(rslt.getLt_holder());

		return list;
	}

	
    public byte[] getHistoryExcel(final List<TransactionHistory> items, final List<AccountBalance> accbal, final String clientName, final String cardNumber, final String dateFrom, final String dateTo, String alias ) throws IOException {
        XSSFWorkbook workBook = null;
        try {
            final List<String> list = Arrays.asList(this.columnNamesList);
            workBook = new XSSFWorkbook();
            int rowIdx = 1;
            final Sheet workSheet = (Sheet)workBook.createSheet("Card History");
            Row detailRow = workSheet.createRow(rowIdx++);
            //detailRow.createCell(0).setCellValue(String.format("\u0412\u044b\u043f\u0438\u0441\u043a\u0430 \u043f\u043e \u043a\u0430\u0440\u0442\u0435 %s \u0437\u0430 \u043f\u0435\u0440\u0438\u043e\u0434 \u0441 %s \u043f\u043e %s. Intelligent Solutions", cardNumber, dateFrom, dateTo));
            detailRow.createCell(0).setCellValue(String.format("Posting period: %s - %s", dateFrom, dateTo));
            detailRow = workSheet.createRow(rowIdx++);
            detailRow.createCell(0).setCellValue(String.format("Account#: %s", accbal.get(0).getACCOUNT_NO()));
            detailRow = workSheet.createRow(rowIdx++);
            detailRow.createCell(0).setCellValue(String.format("Client name: %s", clientName));
            //detailRow.createCell(4).setCellValue(String.format("Current balance: %s", accbal.get(0).getEND_BAL()));
            detailRow.createCell(4).setCellValue(String.format("Current balance: %s", (new BigDecimal(accbal.get(0).getEND_BAL()).divide(new BigDecimal("100")).toString())));
            detailRow = workSheet.createRow(rowIdx++);
            if (cardNumber.substring(0, 10).equals("4187800000"))
            	detailRow.createCell(0).setCellValue(String.format("Account cond: %s", "Classic"));
            else if (cardNumber.substring(0, 10).equals("4187800030"))
            	detailRow.createCell(0).setCellValue(String.format("Account cond: %s", "Gold"));
            else if (cardNumber.substring(0, 10).equals("4187800060"))
            	detailRow.createCell(0).setCellValue(String.format("Account cond: %s", "Platinum"));
            else
            	detailRow.createCell(0).setCellValue(String.format("Account cond: %s", ""));
            	
            //detailRow.createCell(4).setCellValue(String.format("Available amount: %s", accbal.get(0).getAVAIL_AMOUNT()));
            detailRow.createCell(4).setCellValue(String.format("Available amount: %s", (new BigDecimal(accbal.get(0).getAVAIL_AMOUNT()).divide(new BigDecimal("100")).toString())));
            detailRow = workSheet.createRow(rowIdx++);
            detailRow.createCell(0).setCellValue(String.format("Date: %s", df.format(Utils.getInfoDate(alias, new com.is.utils.Res())))); 

            detailRow.createCell(4).setCellValue(String.format("Credit limit: %s", ""));
            
            rowIdx++;
            final Row initialRow = workSheet.createRow(rowIdx++);
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            for (int column = 0; column < list.size(); ++column) {
                final Cell cell = initialRow.createCell(column);
                final XSSFCellStyle style = workBook.createCellStyle();
                final XSSFFont font = workBook.createFont();
                font.setBold(true);
                style.setFont((Font)font);
                cell.setCellStyle((CellStyle)style);
                cell.setCellValue((String)list.get(column));
            }
            
            for (final TransactionHistory rowsItem : items) {
                final Row row = workSheet.createRow(rowIdx++);
                row.createCell(0).setCellValue((rowsItem.getPOST_DATE() == null) ? "" : rowsItem.getPOST_DATE());
                row.createCell(1).setCellValue((rowsItem.getTRAN_DATE_TIME() == null) ? "" : rowsItem.getTRAN_DATE_TIME());
                //row.createCell(2).setCellValue((rowsItem.getACQREF_NR() == null) ? "" : rowsItem.getACQREF_NR());
                row.createCell(2).setCellValue((rowsItem.getAPR_CODE() == null) ? "" : rowsItem.getAPR_CODE());
                //row.createCell(3).setCellValue((rowsItem.getMERCHANT() == null) ? "" : rowsItem.getMERCHANT());
                row.createCell(3).setCellValue(((rowsItem.getABVR_NAME() == null) ? "" : rowsItem.getABVR_NAME()) +" " + ((rowsItem.getCITY() == null) ? "" : rowsItem.getCITY()));
                row.createCell(4).setCellValue((rowsItem.getTRAN_TYPE() == null) ? "" : rowsItem.getTRAN_TYPE());
                row.createCell(5).setCellValue((rowsItem.getTRAN_AMT() == null) ? "" : (new BigDecimal(rowsItem.getTRAN_AMT()).divide(new BigDecimal("100"))).toString());
                row.createCell(6).setCellValue((rowsItem.getTRAN_CCY() == null) ? "" : rowsItem.getTRAN_CCY().toString());
                row.createCell(7).setCellValue((rowsItem.getAMOUNT_NET() == null) ? "" : (new BigDecimal(rowsItem.getAMOUNT_NET()).divide(new BigDecimal("100"))).toString());
                row.createCell(8).setCellValue((rowsItem.getACCNT_CCY() == null) ? "" : rowsItem.getACCNT_CCY());
                row.createCell(9).setCellValue((rowsItem.getTR_CODE() == null) ? "" : rowsItem.getTR_CODE());
                row.createCell(10).setCellValue((rowsItem.getTR_FEE() == null) ? "" : (new BigDecimal(rowsItem.getTR_FEE()).divide(new BigDecimal("100"))).toString()); 
            }
            workBook.write((OutputStream)out);
            return out.toByteArray();
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getMessage(), (Throwable)e);
            return null;
        }
        finally {
            workBook.close();
        }
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


	public static Res doAction(final String un, final String pw,
			final Customer customer, final int actionid,
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
			cs.setString(2, customer.getP_patronymic()!=null ? customer.getP_patronymic().toUpperCase() : customer.getP_patronymic());
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
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields))
							+ "(p_passport_serial=? or p_passport_serial||p_passport_number = ?)",
					(Object) filter.getP_passport_serial()));
			flfields.add(new FilterField("", (Object) (filter
					.getP_passport_serial())));
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
					+ "p_pinfl like ?", "%" + (Object) filter.getP_pinfl()
					+ "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like ?" + " or upper(p_family) like ?)",
					(Object) ("%" + filter.getP_family().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_family().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?"
							+ " or upper(p_first_name) like ?)", (Object) ("%"
							+ filter.getP_first_name().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_first_name().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?"
							+ " or upper(p_patronymic) like ?)", (Object) ("%"
							+ filter.getP_patronymic().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_patronymic().toUpperCase() + "%")));
		}
		String notNull = "";
		if (!CheckNull.isEmpty(filter.getTietoIdIsNotNull())) {
			notNull = " and tieto_customer_id is not null ";
		}
		if (!CheckNull.isEmpty(filter.getId_tieto())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id_tieto=?", (Object) filter.getId_tieto()));
		}
		flfields.add(new FilterField(String.valueOf(getCond(flfields))
				+ "rownum<? " + notNull, (Object) 1001));
		/*
		 * if (!(filter.getCard() == null)) { if
		 * (!filter.getCard().matches("[0-9]+")) { if
		 * (filter.getCard().contains("%")) { flfields.add(new FilterField(
		 * String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card like ?)", filter.getCard())); }
		 * else { flfields.add(new FilterField(
		 * String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card = ?)", filter.getCard())); } } else
		 * { flfields.add(new FilterField( String.valueOf(getCond(flfields)) +
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
			com.is.ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
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
			com.is.ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			Utils.close(rs);
			ConnectionPool.close(c);
		}
		return v_res;
	}

	
	public static Res insertClient(Customer client, String alias) {
			Res res = null;
	        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            ISLogger.getLogger().error("insert bf_tietovisa_client");
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement(
	            		"begin update bf_tietovisa_client set t_cl_type='1', t_city=?, t_region=? "+
	            		"where branch=? and id_client=?; if sql%notfound then "+
	            		"insert into bf_tietovisa_client(t_cl_type,t_city,"
	            		                    + "t_region,date_open,branch,id_client) values"
	            		                    + " ('1',?,?,"
	            		                    + "nvl(info.getday,sysdate),?,?); "+
	            		"end if; "+
	            		"end;"
	            );
	            ps.setString(1, client.getT_r_city());
	            ps.setString(2, getTietoRegionType(client.getP_code_adr_region(), alias));
	            ps.setString(3, client.getBranch());
	            ps.setString(4, client.getId_client());
	            ps.setString(5, client.getT_r_city());
	            ps.setString(6, getTietoRegionType(client.getP_code_adr_region(), alias));
	            ps.setString(7, client.getBranch());
	            ps.setString(8, client.getId_client());
	            
	            ps.execute();
	            
	            c.commit();
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

	
	public static Res updateClientTietoId(Customer client, String alias) {
			Res res = null;
	        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            ISLogger.getLogger().error("update bf_tietovisa_client");
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement(
	            		"begin update bf_tietovisa_client set id_tieto=? "+
	            		"where branch=? and id_client=?; "+
	            		"end;"
	            );
	            ps.setString(1, client.getId_tieto());
	            ps.setString(2, client.getBranch());
	            ps.setString(3, client.getId_client());            
	            ps.execute();	            
	            c.commit();
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
	
	public static Res insertCardToIbs(CardIbs cardIbs, String alias) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            ISLogger.getLogger().error("insert visa_cards");
            c = ConnectionPool.getConnection();

            ps = c.prepareStatement(
            		"begin insert into visa_cards (client_id, client_b, branch, card, "+
            		"status1, status2, card_regist_date, expiry1, "+
            		"expirity2, renew, renew_date, card_name, "+
            		"stop_cause, real_card, tranz_acct, card_acct, "+
            		"pinfl, cond_set) "+
            		"values (?, ?, ?, ?, "+
            		"?, ?, ?, ?, "+            		
            		"?, ?, ?, ?, "+            		
            		"?, ?, ?, ?, "+
            		"?, ?); end;"
            );

            ps.setString(1, cardIbs.getClient_id());
            ps.setString(2, cardIbs.getClient_b());
            ps.setString(3, cardIbs.getBranch());
            ps.setString(4, cardIbs.getCard());
            ps.setString(5, cardIbs.getStatus1());
            ps.setString(6, cardIbs.getStatus2());
            //if (cardIbs.getCard_regist_date()!=null)
            //ps.setDate(7, new java.sql.Date(cardIbs.getCard_regist_date().getTime()));
            ps.setDate(7, cardIbs.getCard_regist_date()==null ? null : new java.sql.Date(cardIbs.getCard_regist_date().getTime()));
            //if (cardIbs.getExpiry1()!=null)
            ps.setDate(8, cardIbs.getExpiry1()==null ? null : new java.sql.Date(cardIbs.getExpiry1().getTime()));
            //if (cardIbs.getExpirity2()!=null)
            ps.setDate(9, cardIbs.getExpirity2()==null ? null : new java.sql.Date(cardIbs.getExpirity2().getTime()));
            ps.setString(10, cardIbs.getRenew());
            //if (cardIbs.getRenew_date()!=null)
            ps.setDate(11, cardIbs.getRenew_date()==null ? null : new java.sql.Date(cardIbs.getRenew_date().getTime()));            
            ps.setString(12, cardIbs.getCard_name());
            ps.setString(13, cardIbs.getStop_cause());
            ps.setString(14, cardIbs.getReal_card());
            ps.setString(15, cardIbs.getTranz_acct());
            ps.setString(16, cardIbs.getCard_acct());
            ps.setString(17, cardIbs.getPinfl());
            ps.setString(18, cardIbs.getCond_set());
            
            ps.execute();
            
            c.commit();
            res = new Res(0, "OK");
            
        } 
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
            ISLogger.getLogger().error("insert visa_cards error: "+e.getMessage()+" - "+e.getCause());
        	res = new Res(-1, e.getMessage()+" - "+e.getCause());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }

	public static Res changeCardToIbs(String curr_card, CardIbs newCardIbs, String alias) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;
        try {
            ISLogger.getLogger().error("update visa_cards and insert visa_cards_history");
            c = ConnectionPool.getConnection();

            /*
             * visa_cards dagi zapisni visa_cards_history ga utkazamiz. visa_cards ga perevipusk qilinganni yozamiz
             * */
            ps = c.prepareStatement(
            		"begin "+
            		"  insert into visa_cards_history (nexval, client_id, client_b, branch, card,  "+
            		"                status1, status2, card_regist_date, expiry1,  "+
            		"                expirity2, renew, renew_date, card_name,  "+
            		"                stop_cause, real_card, tranz_acct, card_acct,  "+
            		"                pinfl, cond_set) "+
            		"  select sq_visa_cards_history.nextval, client_id, client_b, branch, card,  "+
            		"                status1, status2, card_regist_date, expiry1,  "+
            		"                expirity2, renew, renew_date, card_name,  "+
            		"                stop_cause, real_card, tranz_acct, card_acct,  "+
            		"                pinfl, cond_set from visa_cards where card=?; "+
            		"  update visa_cards set "+
            		"    card=?, real_card=?, renew='R', renew_date=? "+
            		"    where card=?; "+
            		"end; "
            		);

            ps.setString(1, curr_card);
            ps.setString(2, newCardIbs.getCard());
            ps.setString(3, newCardIbs.getReal_card());
            ps.setDate(4, newCardIbs.getRenew_date()==null ? null : new java.sql.Date(newCardIbs.getRenew_date().getTime()));
            ps.setString(5, curr_card);
            
            ps.execute();
            
            c.commit();
            res = new Res(0, "OK");
            
        } 
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
            ISLogger.getLogger().error("update visa_cards and insert visa_cards_history error: "+e.getMessage()+" - "+e.getCause());
        	res = new Res(-1, e.getMessage()+" - "+e.getCause());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
	
	public static RowType_EditCustomer_Request makeEditClientMobilePhone(Customer cust, String alias) {
		RowType_EditCustomer_Request addCl = new RowType_EditCustomer_Request();
		addCl.setCLIENT(cust.getId_tieto()); // when adding new client, tieto ignores this parameter 
		addCl.setR_MOB_PHONE(cust.getT_rmob_phone());
		addCl.setBANK_C( cust.getT_bank_c()!=null? cust.getT_bank_c() : CustomerService.mapConst.get(Cons.bank_c) );
		return addCl;
	}
	
	public static RowType_EditCustomer_Request makeEditClient(Customer cust, String alias) {
		RowType_EditCustomer_Request addCl = new RowType_EditCustomer_Request();
		addCl.setCLIENT(cust.getId_tieto()); // when adding new client, tieto ignores this parameter 
		addCl.setCLIENT_B(cust.getT_client_b());
		addCl.setCL_TYPE("1");
		if (cust.getT_cln_cat()!=null && !cust.getT_cln_cat().equals("") ) {
			addCl.setCLN_CAT(cust.getT_cln_cat());	
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		addCl.setREC_DATE(calendar); //data pervoy zapisi dogovora. sysdate quyamiz
		addCl.setF_NAMES(cust.getP_first_name());
		addCl.setSURNAME(cust.getP_family());
		addCl.setMIDLE_NAME(cust.getP_patronymic());
		calendar = Calendar.getInstance();
		calendar.setTime(cust.getP_birthday());
		addCl.setB_DATE(calendar);  //data rojdeniya
		calendar = Calendar.getInstance();
		calendar.setTime(cust.getP_passport_date_registration());
		addCl.setDOC_SINCE(calendar); 
		addCl.setRESIDENT(cust.getCode_resident());
		//addCl.setID_CARD("id_CARD");
		addCl.setR_PHONE(cust.getP_phone_home());
		addCl.setR_STREET(cust.getP_post_address());
		addCl.setR_CITY(cust.getT_r_city());
		addCl.setR_CNTRY( getTietoCountryType( cust.getCode_country(), alias));
		addCl.setR_PCODE(cust.getP_zip_code());
		addCl.setREGION(getTietoRegionType( cust.getP_code_adr_region(), alias ));
		addCl.setPERSON_CODE(cust.getP_pinfl());
		addCl.setSEX(cust.getP_code_gender());
		addCl.setSERIAL_NO(StringUtils.secureNull(cust.getP_passport_serial())+StringUtils.secureNull(cust.getP_passport_number()));
		addCl.setISSUED_BY(cust.getP_passport_place_registration());
		addCl.setR_E_MAILS(cust.getP_email_address());
		addCl.setR_MOB_PHONE(cust.getP_phone_mobile());
		addCl.setSTATUS(cust.getT_status());
		addCl.setBANK_C( cust.getT_bank_c()!=null? cust.getT_bank_c() : CustomerService.mapConst.get(Cons.bank_c) );
					
		return addCl;

	}
	
	public static RowType_Customer makeAddClient(Customer cust, String alias) {
		RowType_Customer addCl = new RowType_Customer();
		addCl.setCLIENT(cust.getId_tieto()); // when adding new client, tieto ignores this parameter 
		addCl.setCLIENT_B(cust.getBranch()+cust.getId_client());
		addCl.setCL_TYPE("1");
		addCl.setCLN_CAT("001");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		addCl.setREC_DATE(calendar); //data pervoy zapisi dogovora. sysdate quyamiz
		addCl.setF_NAMES(cust.getP_first_name());
		addCl.setSURNAME(cust.getP_family());
		addCl.setMIDLE_NAME(cust.getP_patronymic());
		calendar = Calendar.getInstance();
		calendar.setTime(cust.getP_birthday());
		addCl.setB_DATE(calendar);  //data rojdeniya
		calendar = Calendar.getInstance();
		calendar.setTime(cust.getP_passport_date_registration());
		addCl.setDOC_SINCE(calendar); 
		addCl.setRESIDENT(cust.getCode_resident());
		//addCl.setID_CARD("id_CARD");
		addCl.setR_PHONE(cust.getP_phone_home());
		addCl.setR_STREET(cust.getP_post_address());
		addCl.setR_CITY(cust.getT_r_city());
		addCl.setR_CNTRY( getTietoCountryType( cust.getCode_country(), alias));
		addCl.setR_PCODE(cust.getP_zip_code());
		addCl.setREGION(getTietoRegionType( cust.getP_code_adr_region(), alias ));
		addCl.setPERSON_CODE(cust.getP_pinfl());
		addCl.setSEX(cust.getP_code_gender());
		addCl.setSERIAL_NO(cust.getP_passport_serial()+cust.getP_passport_number());
		addCl.setISSUED_BY(cust.getP_passport_place_registration());
		addCl.setR_E_MAILS(cust.getP_email_address());
		addCl.setR_MOB_PHONE(cust.getP_phone_mobile());
		addCl.setSTATUS("10");
					
		return addCl;

	}
	
	/*инициалицация параметров begin*/
	public static void initConst(final String alias) {
	
		if (mapConst != null) 
			return;
		mapConst = new HashMap<String, String>();
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value;
		String name;
		
		try {
			
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select name, value from bf_visa_connection_sets");
			rs = ps.executeQuery();

			while (rs.next()) {
				value = rs.getString("value");
				name = rs.getString("name");

				//if (name.equals(Cons.endpoint)) {
				//	mapConst.put(Cons.endpoint, value);
				//}
				/*if (name.equals(Constants.FIELD_LOGIN)) {
					connectionInfo.setLogin(value);
				}
				if (name.equals(Constants.FIELD_PASSWORD)) {
					connectionInfo.setPassword(value);
				}*/
				if (name.equals(Cons.bank_c)) {
					mapConst.put(Cons.bank_c, value);
				}
				if (name.equals(Cons.groupc)) {
					mapConst.put(Cons.groupc, value);
				}
				if (name.equals(Cons.url_newcustomer)) {
					mapConst.put(Cons.url_newcustomer, value);
				}
				if (name.equals(Cons.url_editcustomer)) {
					mapConst.put(Cons.url_editcustomer, value);
				}
				if (name.equals(Cons.url_listcustomers)) {
					mapConst.put(Cons.url_listcustomers, value);
				}
				if (name.equals(Cons.url_listaccounts)) {
					mapConst.put(Cons.url_listaccounts, value);
				}
				if (name.equals(Cons.url_listcardsbyaccount)) {
					mapConst.put(Cons.url_listcardsbyaccount, value);
				}
				if (name.equals(Cons.url_listcustomercards)) {
					mapConst.put(Cons.url_listcustomercards, value);
				}
				if (name.equals(Cons.url_newagreement)) {
					mapConst.put(Cons.url_newagreement, value);
				}
				if (name.equals(Cons.url_addcardtostop)) {
					mapConst.put(Cons.url_addcardtostop, value);
				}
				if (name.equals(Cons.url_removecardfromstop)) {
					mapConst.put(Cons.url_removecardfromstop, value);
				}
				if (name.equals(Cons.url_replacecard)) {
					mapConst.put(Cons.url_replacecard, value);
				}
				if (name.equals(Cons.url_cardhistory)) {
					mapConst.put(Cons.url_cardhistory, value);
				}
				if (name.equals(Cons.url_accountbalance)) {
					mapConst.put(Cons.url_accountbalance, value);
				}
				if (name.equals(Cons.url_listaccountsbycard)) {
					mapConst.put(Cons.url_listaccountsbycard, value);
				}
				if (name.equals(Cons.url_resetpincounter)) {
					mapConst.put(Cons.url_resetpincounter, value);
				}
				if (name.equals(Cons.url_getrealcard)) {
					mapConst.put(Cons.url_getrealcard, value);
				}
				if (name.equals(Cons.url_exectransaction)) {
					mapConst.put(Cons.url_exectransaction, value);
				}

				if (name.equals(Cons.url_smsservice)) {
					mapConst.put(Cons.url_smsservice, value);
				}
				if (name.equals(Cons.smsservice_login)) {
					mapConst.put(Cons.smsservice_login, value);
				}
				if (name.equals(Cons.smsservice_password)) {
					mapConst.put(Cons.smsservice_password, value);
				}
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}		
	}
	/*инициалицация параметров end*/
	
	/* справочниклар бошланиши, spravochniklar */
	public static List<RefData> getCountry(final String alias) {
		if (listCountry == null || listCountry.size() == 0)
			listCountry = (List<RefData>) Utils
					.getRefData(
							"select s.code_str data, s.name label from S_STR s where s.act='A' order by 2",
							alias);
		return listCountry;
	}

	public static List<RefData> getGender(final String alias) {
		if (listGender == null || listGender.size() == 0)
			listGender = (List<RefData>) Utils
					.getRefData(
							"select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX",
							alias);
		return listGender;
	}

	public static List<RefData> getNation(final String alias) {
		if (listNation == null || listNation.size() == 0)
			listNation = (List<RefData>) Utils
					.getRefData(
							"select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id",
							alias);
		return listNation;
	}

	public static List<RefData> getRezCl(final String alias) {
		if (listRezCl == null || listRezCl.size() == 0)
			listRezCl = (List<RefData>) Utils
					.getRefData(
							"select kod_rez data, type_rez label from S_REZKL order by kod_rez",
							alias);
		return listRezCl;
	}

	public static List<RefData> getDistr(final String alias) {
		if (listDistr == null || listDistr.size() == 0)
			listDistr = (List<RefData>) Utils
					.getRefData(
							"select distr data, distr_name label from s_distr where act='A' order by 2",
							alias);
		return listDistr;
	}

	public static List<RefData> getDistrByRegion(String region_id,
			final String alias) {

		/*
		 * if (regions_districts == null) { HashMap<String, List<RefData>>
		 * regs_dists = new HashMap<String, List<RefData>>(); for (RefData rd :
		 * regions) { List<RefData> distrList = new ArrayList<RefData>();
		 * distrList = ClientDictionaries.getDistrByRegion(tm.getConnection(),
		 * rd.getData(), alias); regs_dists.put(rd.getData(), distrList); }
		 * regions_districts = regs_dists; }
		 */
		if (listDistrByRegion == null)
			listDistrByRegion = new HashMap<String, List<RefData>>();
		if (!listDistrByRegion.containsKey(region_id)) {
			List<RefData> lst = (List<RefData>) Utils
					.getRefData(
							"select distr data, distr_name label from s_distr where region_id=? and act='A' order by 2",
							region_id, alias);

			listDistrByRegion.put(region_id, lst);
		}
		return listDistrByRegion.get(region_id);
	}

	public static List<RefData> getRegion(final String alias) {
		if (listRegion == null || listRegion.size() == 0)
			listRegion = (List<RefData>) Utils
					.getRefData(
							"select region_id data, region_nam label from s_region where act='A' order by 2",
							alias);
		return listRegion;
	}

	public static List<RefData> getTax(final String branch) {
		if (listTax == null || listTax.size() == 0)
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

	public static List<Product> getProducts2(final String alias) {		
		if (listProducts2 == null || listProducts2.size() == 0){			
			listProducts2 = new LinkedList<Product>();
	        Connection c = null;
	        Statement s = null;
	        ResultSet rs = null;
	        try {
	            c = ConnectionPool.getConnection(alias);
	            s = c.createStatement();
	            rs = s.executeQuery("select ica.cond_set, ica.ccy, ica.name, ica.name2, ica.acc_id_order, ica.non_reduce_bal, " +
	            		"ibt.risk_level, ibt.bin_code " +
	            		"from bf_izd_cond_accnt ica, bf_izd_bin_table ibt " +
	            		"where ibt.cardname(+)=ica.name");
	            
	            while (rs.next()) {
	            	Product prod = new Product();
	            	prod.setAcc_id_order(rs.getInt("acc_id_order"));
	            	//prod.setAct(rs.getString("label"));
	            	prod.setCcy(rs.getString("ccy"));
	            	prod.setCond_set(rs.getString("cond_set"));
	            	prod.setName(rs.getString("name"));
	            	prod.setName2(rs.getString("name2"));
	            	prod.setNon_reduce_bal(rs.getInt("non_reduce_bal"));
	            	prod.setRisk_level(rs.getString("risk_level"));
	            	prod.setBin_code(rs.getString("bin_code"));
	            	
	            	listProducts2.add(prod);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	            return listProducts2;
	        }
	        finally {
	            close(rs);
	            close(s);
	            ConnectionPool.close(c);
	        }
	        close(rs);
	        close(s);
	        ConnectionPool.close(c);	     
		}
		return listProducts2;
	}
	
    /*public static HashMap<String, Product> getMapProducts2(String alias) {
		List<Product> tmp = getProducts2(alias);
		
		if (mapProducts2==null) {
			mapProducts2=new HashMap<String, Product>();
			for (int i = 0; i < tmp.size(); i++) {
				mapProducts2.put(tmp.get(i).getCond_set(),
						tmp.get(i));
			}
		}
		return mapProducts2;
	}*/

    public static Product getProductByName(String name, String alias) {
		List<Product> tmp = getProducts2(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getName().equals(name)) {
				return tmp.get(i);
			}			
		}
		return new Product();
	}
    
    public static Product getProductByCondSet(String cond_set, String alias) {
		List<Product> tmp = getProducts2(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getCond_set().equals(cond_set)) {
				return tmp.get(i);
			}			
		}
		return new Product();
	}

    public static List<Branch> getBranchs(final String alias) {		
		if (listBranchs == null || listBranchs.size() == 0){			
			listBranchs = new LinkedList<Branch>();
	        Connection c = null;
	        Statement s = null;
	        ResultSet rs = null;
	        try {
	            c = ConnectionPool.getConnection(alias);
	            s = c.createStatement();
	            rs = s.executeQuery("select * " +	            	
	            		"from bf_izd_branches order by 1");
	            
	            while (rs.next()) {
	            	Branch branch = new Branch();
	           
	            	branch.setBranch(rs.getString("branch"));
	               	branch.setBranch_name(rs.getString("branch_name"));
	            	branch.setCity(rs.getString("city"));
	            	branch.setMfo(rs.getString("mfo"));
	            	branch.setB_br_id(rs.getString("b_br_id"));
	            	
	            	
	            	listBranchs.add(branch);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	            return listBranchs;
	        }
	        finally {
	            close(rs);
	            close(s);
	            ConnectionPool.close(c);
	        }
	        close(rs);
	        close(s);
	        ConnectionPool.close(c);	     
		}
		return listBranchs;
	}
	
    public static Branch getBranchByMfo(String mfo, String alias) {
		List<Branch> tmp = getBranchs(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getMfo().equals(mfo)) {
				return tmp.get(i);
			}			
		}
		return new Branch();
	}
    
    public static List<Cond_card> getCond_cards(final String alias) {		
		if (listCondCards == null || listCondCards.size() == 0){			
			listCondCards = new LinkedList<Cond_card>();
	        Connection c = null;
	        Statement s = null;
	        ResultSet rs = null;
	        try {
	            c = ConnectionPool.getConnection(alias);
	            s = c.createStatement();
	            rs = s.executeQuery("select * " +	            	
	            		"from bf_izd_cond_card order by 1");
	            
	            while (rs.next()) {
	            	Cond_card cc = new Cond_card();	     
	       
	            	cc.setCond_set(rs.getString("cond_set"));
	               	cc.setCcy(rs.getString("ccy"));
	            	cc.setName(rs.getString("name"));
	            	cc.setBin_code(rs.getString("bin_code"));
	            	cc.setCardname(rs.getString("cardname"));	         
	            	
	            	listCondCards.add(cc);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	            return listCondCards;
	        }
	        finally {
	            close(rs);
	            close(s);
	            ConnectionPool.close(c);
	        }
	        close(rs);
	        close(s);
	        ConnectionPool.close(c);	     
		}
		return listCondCards;
	}
    public static Cond_card getCond_cardByCardname(String cardname, String alias) {
		List<Cond_card> tmp = getCond_cards(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getCardname()!=null && tmp.get(i).getCardname().equals(cardname)) {
				return tmp.get(i);
			}			
		}
		return new Cond_card();
	}

    public static Cond_card getCond_cardByCond(String condCard, String alias) {
		List<Cond_card> tmp = getCond_cards(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getCond_set()!=null && tmp.get(i).getCond_set().equals(condCard)) {
				return tmp.get(i);
			}			
		}
		return new Cond_card();
	}
    
    public static List<Card_design> getCard_designs(final String alias) {		
		if (listCardDesigns == null || listCardDesigns.size() == 0){			
			listCardDesigns = new LinkedList<Card_design>();
	        Connection c = null;
	        Statement s = null;
	        ResultSet rs = null;
	        try {
	            c = ConnectionPool.getConnection(alias);
	            s = c.createStatement();
	            rs = s.executeQuery("select * " +	            	
	            		"from bf_izd_card_designs order by 1");
	            
	            while (rs.next()) {
	            	Card_design cc = new Card_design();	     
	             	cc.setDesign_id(rs.getInt("design_id"));
	               	cc.setDesign_type(rs.getString("design_type"));
	            	cc.setDesign_type_name(rs.getString("design_type_name"));
	            	cc.setCardname(rs.getString("cardname"));	            	         
	            	
	            	listCardDesigns.add(cc);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	            return listCardDesigns;
	        }
	        finally {
	            close(rs);
	            close(s);
	            ConnectionPool.close(c);
	        }
	        close(rs);
	        close(s);
	        ConnectionPool.close(c);	     
		}
		return listCardDesigns;
	}
    public static Card_design getCard_designByCardname(String cardname, String alias) {
		List<Card_design> tmp = getCard_designs(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).getCardname()!=null && tmp.get(i).getCardname().equals(cardname)) {
				return tmp.get(i);
			}			
		}
		return new Card_design();
	}
	public static HashMap<String, String> getMapStatesNciAccount(String alias) {
		if (listStatesNciAccount == null || listStatesNciAccount.size() == 0)
			listStatesNciAccount = (List<RefData>) Utils
					.getRefData(
							"select distinct id data, name label from state_account order by 1",
							alias);
		if (mapStatesNciAccount==null) {
			mapStatesNciAccount=new HashMap<String, String>();
			for (int i = 0; i < listStatesNciAccount.size(); i++) {
				mapStatesNciAccount.put(listStatesNciAccount.get(i).getData(),
						listStatesNciAccount.get(i).getLabel());
			}
		}
		return mapStatesNciAccount;
	}

	public static HashMap<String, String> getMapStopCauses(String alias) {
		if (listStopCauses == null || listStopCauses.size() == 0)
			listStopCauses = (List<RefData>) Utils
					.getRefData(
							"select t.couse data, t.couse||'-'||t.name label from BF_visa_STOP_CAUSES t order by t.couse",
							alias);

		if (mapStopCauses == null) {
			mapStopCauses = new HashMap<String, String>();
			for (int i = 0; i < listStopCauses.size(); i++) {
				mapStopCauses.put(listStopCauses.get(i).getData(),
						listStopCauses.get(i).getLabel());
			}
		}
		return mapStopCauses;
	}
	
	public static List<RefData> getListStopCauses(String alias) {
		if (listStopCauses == null || listStopCauses.size() == 0)
			listStopCauses = (List<RefData>) Utils
					.getRefData(
							"select t.couse data, t.couse||'-'||t.name label from BF_visa_STOP_CAUSES t order by t.couse",
							alias);
		return listStopCauses;
	}

	public static List<RefData> getListLockStopCauses(String alias) {
		if (listLockStopCauses == null || listLockStopCauses.size() == 0)
			listLockStopCauses = (List<RefData>) Utils
					.getRefData(
							"select '5' data, '5-Временная блокировка(Do not honor)' label from dual union all select 'A' data, 'A-Закрыть карта(Bank will)' label from dual order by data",
							alias);
		return listLockStopCauses;
	}

	public static HashMap<String, String> getMapStatesTieto(String alias) {
		if (listStatesTieto == null || listStatesTieto.size() == 0)
			listStatesTieto = (List<RefData>) Utils
					.getRefData(
							"select 0 data, '0-Active' label from dual union all select 1 data, '1-Suspended' label from dual union all select 2 data, '2-Closed' label from dual",
							alias);
		if (mapStatesTieto==null) {
			mapStatesTieto=new HashMap<String, String>();
			for (int i = 0; i < listStatesTieto.size(); i++) {
				mapStatesTieto.put(listStatesTieto.get(i).getData(),
						listStatesTieto.get(i).getLabel());
			}
		}
		return mapStatesTieto;
	}

	public static List<RefData> getClient_type_way4(final String alias) {
		if (listClientTypeWay4 == null || listClientTypeWay4.size() == 0)
			listClientTypeWay4 = (List<RefData>) Utils
					.getRefData(
							"select 'PR' data,	'Физическое лицо - резидент(PR)' label, 0 ord from dual union all "
									+ "select 'PNR' data, 'Физическое лицо - нерезидент(PNR)' label, 1 ord from dual union all "
									+ "select 'RPR' data, 'Физическое лицо, пенсионер(RPR)' label, 2 ord from dual order by ord",
							alias);
		return listClientTypeWay4;
	}

	public static List<RefData> getCardCondSet_DesignIDs(final String alias) {
		if (listCardCondSet_DesignIds  == null || listCardCondSet_DesignIds.size() == 0)
			listCardCondSet_DesignIds = (List<RefData>) Utils
					.getRefData(
							"select t1.cond_set data, t2.design_id label from bf_izd_cond_card t1, bf_izd_card_designs t2 where t1.cardname=t2.cardname order by 1",
							alias);
		return listCardCondSet_DesignIds;
	}

	public static List<RefData> getTietoRegions(final String alias) {
		if (listTietoRegions == null || listTietoRegions.size() == 0)
			listTietoRegions = (List<RefData>) Utils
					.getRefData(
							"select s.code_region_nci data, s.code_tieto label from bf_tietovisa_ss_region s order by 1",
							alias);
		return listTietoRegions;
	}
	
	public static List<RefData> getTietoCountryTypes(final String alias) {
		if (listTietoCountryTypes == null || listTietoCountryTypes.size() == 0)
			listTietoCountryTypes = (List<RefData>) Utils
					.getRefData(
	 				        "select s.CODE_str data, s.alpha_3 label from s_str s order by decode(s.CODE_str, '860','000', s.CODE_str)",
							alias);
		return listTietoCountryTypes;
	}
	
	public static List<RefData> getWayCountries(final String alias) {
		if (listWayCountries == null || listWayCountries.size() == 0)
			listWayCountries = (List<RefData>) Utils
					.getRefData(
							"select s.CODE_str data, s.alpha_3 label from s_str s order by decode(s.CODE_str, '860','000', s.CODE_str)",
							alias);
		return listWayCountries;
	}

	public static List<RefData> getAcc_bal(final String alias) {
		if (listAccBal == null || listAccBal.size() == 0)
			listAccBal = (List<RefData>) Utils.getRefData(
					"select '22618' data,	'22618' label, 0 ord from dual",
					alias);
		return listAccBal;
	}

	public static List<RefData> getAgreeProducts(final String alias) {
		
		if (listProducts == null || listProducts.size() == 0)
			listProducts = (List<RefData>) Utils
					.getRefData(
							"select name data, name label, 0 ord from bf_izd_cond_accnt where act='A' order by name",
							alias);
		return listProducts;
	}

	public static List<RefData> getBinCodes(final String alias) {		
		if (listBinCodes == null || listBinCodes.size() == 0)
			listBinCodes = (List<RefData>) Utils
					.getRefData(
							"select bin_code data, bin_code||'-'||card_name label, 0 ord from bf_izd_bin_table order by bin_code",
							alias);
		return listBinCodes;
	}
	
	public static List<RefData> getIzdBranches(final String alias) {
		if (listIzdBranches == null || listIzdBranches.size() == 0)
			listIzdBranches = (List<RefData>) Utils
					.getRefData(
							"select branch data, branch||'-'||branch_name label, 0 ord from bf_izd_branches order by branch",
							alias);
		return listIzdBranches;
	}
	
	public static List<RefData> getAccCondSets(final String alias) {
		if (listAccCondSets == null || listAccCondSets.size() == 0)
			listAccCondSets = (List<RefData>) Utils
					.getRefData(
							"select cond_set data, cond_set||'-'||ccy||'-'||name label, 0 ord from bf_izd_cond_accnt order by cond_set",
							alias);
		return listAccCondSets;
	}
	
	public static List<RefData> getCardCondSets(final String alias) {
		if ( listCardCondSets == null || listCardCondSets.size() == 0)
			listCardCondSets = (List<RefData>) Utils
					.getRefData(
							"select cond_set data, cond_set||'-'||ccy||'-'||name label, 0 ord from bf_izd_cond_card order by cond_set",
							alias);
		return listCardCondSets;
	}
	
	public static List<RefData> getRiskLevels(final String alias) {
		if (  listRiskLevels == null || listRiskLevels.size() == 0)
			listRiskLevels = (List<RefData>) Utils
					.getRefData(
							"select distinct risk_level data, short_name label from bf_izd_risktab order by 1",
							alias);
		return listRiskLevels;
	}

	public static List<RefData> getDesigns(final String alias) {
		if ( listDesigns == null || listDesigns.size() == 0)
			listDesigns = (List<RefData>) Utils
					.getRefData(
							"select design_id data, design_id||'-'||design_type||'-'||design_type_name label, 0 ord from bf_izd_card_designs order by 1",
							alias);
		return listDesigns;
	}
	
	public static List<RefData> getSubProductByProduct(String product_code,
			final String alias) {
		if (listSubProductByProduct == null)
			listSubProductByProduct = new HashMap<String, List<RefData>>();
		if (!listSubProductByProduct.containsKey(product_code)) {
			List<RefData> lst = (List<RefData>) Utils
					.getRefData(
							"select CODE_SUBPROD_WAY data, NAME_SUBPROD_IBS label, 0 ord from bf_openway_subproduct "
									+ "where CODE_PROD_WAY = ? and state='A' order by CODE_SUBPROD_WAY",
							product_code, alias);

			listSubProductByProduct.put(product_code, lst);
		}
		return listSubProductByProduct.get(product_code);
	}

	/* справочниклар тугаши */

	/* ёрдамчи функциялар бошланиши*/

	public static String getNationText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getNation(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getResidentText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getRezCl(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getDistrText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getDistr(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getRegionText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getRegion(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getGenderText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getGender(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getCountryText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getCountry(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getDocumentText(String code, String alias) {
		String v_res="";
		if (code==null || code.equals("")) 
			return v_res;	
		List<RefData> tmp = getType_document(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == code || tmp.get(i).getData().equals(code)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	//select t1.cond_set, t2.design_id from bf_izd_cond_card t1, bf_izd_card_designs t2
	//where t1.cardname=t2.cardname
	public static String getDesignIdByCardCondSet(String cardCondSet, String alias) {
		String v_res="";
		if (cardCondSet==null || cardCondSet.equals("")) 
			return v_res;	
		List<RefData> tmp = getCardCondSet_DesignIDs(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == cardCondSet || tmp.get(i).getData().equals(cardCondSet)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	
	public static String getTietoRegionType(String nciRegionCode, String alias) {
		String v_res="";
		if (nciRegionCode==null || nciRegionCode.equals("")) 
			return v_res;	
		List<RefData> tmp = getTietoRegions(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == nciRegionCode || tmp.get(i).getData().equals(nciRegionCode)) {
				v_res=tmp.get(i).getLabel();
				break;
			}
			}
		return v_res;
	}
	public static String getTietoCountryType(String nciCountryCode, String alias) {
		String v_res="";
		if (nciCountryCode==null || nciCountryCode.equals("")) 
			return v_res;	
		List<RefData> tmp = getTietoCountryTypes(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == nciCountryCode || tmp.get(i).getData().equals(nciCountryCode)) {
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
	
	private Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/*private java.util.Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}*/
	
	public static String getCurrencyFromAccCondSet(String accCondSet, String alias) {
		String v_res="";
		if (accCondSet==null || accCondSet.equals("")) 
			return v_res;	
		List<RefData> tmp = getAccCondSets(alias); 
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getData() == accCondSet || tmp.get(i).getData().equals(accCondSet)) {
				v_res=tmp.get(i).getLabel();
				//select cond_set data, cond_set||'-'||ccy||'-'||name label, 0 ord from bf_izd_cond_accnt order by cond_set
				v_res=v_res.substring( v_res.indexOf("-") );
				v_res=v_res.substring(1,4);
				break;
			}
			}
		return v_res;
	}
	
	
	/* ёрдамчи функциялар тугаши*/
	

	 public static void UsrLog(UserActionsLog useractionslog) {
	      ObjectMapper objectMapper = new ObjectMapper();
	      Connection c = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;

	      try {
	         objectMapper.writeValueAsString("useractionslog: " + useractionslog);
	         c = ConnectionPool.getConnection();
	         ps = c.prepareStatement("SELECT SEQ_BF_USR_ACTLOG.NEXTVAL id FROM DUAL");
	         rs = ps.executeQuery();
	         if (rs.next()) {
	            useractionslog.setId(rs.getLong("id"));
	         }

	         ps = c.prepareStatement("INSERT INTO BF_USER_ACTIONS_LOG (id, user_id, user_name, ip_address, action_date, act_type, entity_type, entity_id, branch) VALUES (?,?,?,?,sysdate,?,?,?,?)");
	         ps.setLong(1, useractionslog.getId());
	         ps.setInt(2, useractionslog.getUser_id());
	         ps.setString(3, useractionslog.getUser_name().toUpperCase());
	         ps.setString(4, useractionslog.getIp_address());
	         ps.setInt(5, useractionslog.getAct_type());
	         ps.setFloat(6, (float)useractionslog.getEntity_type());
	         ps.setString(7, useractionslog.getEntity_id());
	         ps.setString(8, useractionslog.getBranch() != null ? useractionslog.getBranch() : "00000");
	         ps.executeUpdate();
	         c.commit();
	      } catch (Exception var9) {
	         ISLogger.getLogger().error(CheckNull.getPstr(var9));
	      } finally {
	         Utils.close(rs);
	         Utils.close(ps);
	         ConnectionPool.close(c);
	      }

	   }
	 
	 public static String getIp() {
	      HttpServletRequest hr = (HttpServletRequest)Executions.getCurrent().getNativeRequest();
	      String ipAddress = hr.getHeader("x-forwarded-for");
	      if (ipAddress == null) {
	         ipAddress = hr.getHeader("X_FORWARDED_FOR");
	         if (ipAddress == null) {
	            ipAddress = hr.getRemoteAddr();
	         }
	      }
	      return ipAddress;
	   }
	 

	   public static List<CardActions> getCardActions(String card, String branch) {
	      Connection c = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      ArrayList<CardActions> cardActions = new ArrayList();
	      String sql = "select * from bf_user_actions_log where branch = " + branch + " and entity_id like '%" + card + "%' order by action_date desc";

	      try {
	         c = ConnectionPool.getConnection();
	         ps = c.prepareStatement(sql);
	         rs = ps.executeQuery();

	         while(rs.next()) {
	            CardActions action = new CardActions();
	            action.setAction(rs.getString("entity_id"));
	            action.setCard(card);
	            
	            Timestamp timestamp = rs.getTimestamp("action_date");
	            if (timestamp != null)
	                //date = new java.util.Date(timestamp.getTime()));
	                action.setDate(ldf.format(timestamp.getTime()));  
	            //action.setDate(ldf.format(rs.getDatetime("action_date"))); 
	            action.setUser(rs.getString("user_name"));
	            cardActions.add(action);
	         }
	      } catch (SQLException var11) {
	         var11.printStackTrace();
	      } finally {
	         Utils.close(rs);
	         Utils.close(ps);
	         ConnectionPool.close(c);
	      }

	      return cardActions;
	   }

	public static void close(final CallableStatement cs) {
        try {
            if (cs != null) {
                cs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
}