package com.is.tieto_globuz.tieto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import com.is.tieto_globuz.utils;
import com.is.tieto_globuz.customer.Customer;
import com.is.trpay.TrPay;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.NilProvider;
import com.is.utils.RefData;
import com.is.utils.Res;

import globus.issuing_v_01_02_xsd.KeyType_Agreement;
import globus.issuing_v_01_02_xsd.ListType_AccountInfo;
import globus.issuing_v_01_02_xsd.ListType_CardInfo;
import globus.issuing_v_01_02_xsd.ListType_Generic;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_Agreement;
import globus.issuing_v_01_02_xsd.RowType_CardInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import globus.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import globus.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import globus.issuing_v_01_02_xsd.RowType_ListCustomers_Request;
import globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;

public class TclientService
{
	private static NilProvider np = null;
	private static Tclient rs;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 1971-06-13T00:00:00
	private static ListType_Generic listType_Generic_lcm = null;
	private static ListType_GenericHolder details_list_customers = new ListType_GenericHolder(listType_Generic_lcm);
	
	private static String EMPC_BANK_C;
	private static String EMPC_GROUPC;
	private static String EMPC_BINCOD;
	
	public TclientService(String alias)
	{
		EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		EMPC_BINCOD = ConnectionPool.getValue("EMPC_BINCOD", alias);
	}
	
	public static String getSBank()
	{
		Connection c = null;
		String s_bank = "";
		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select t.u_file_code  from BF_GLOBUZ_BANK_CODES t " +
					"where t.bank_code = (select s.value from bf_sets s where s.id = 'EMPC_BANK_C')");
			while (rs.next())
			{
				s_bank = rs.getString("u_file_code");
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return s_bank;
	}
	
	public static List<Tclient> getTclient(String serial_no, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		
		List<Tclient> list = new ArrayList<Tclient>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters.setBANK_C(EMPC_BANK_C);
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				rs = new Tclient();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) rs.setStatus(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B")) rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE")) rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("B_DATE"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS")) rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE")) rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_STREET")) rs.setR_street(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CITY")) rs.setR_city(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY")) rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C")) rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
				}
				
				list.add(rs);
				
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static Tclient getTclient_by_id(String client_id, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String alias)
	{
		Tclient rs = null;
		try
		{
			ISLogger.getLogger().error("\n\n getTclient_by_id \n\n\n");
			
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters.setBANK_C(EMPC_BANK_C);
			parameters.setCLIENT(client_id);
			
			ISLogger.getLogger().error("\n\n getTclient_by_id client id = "+client_id + "\n\n\n");
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			
			ISLogger.getLogger().error("\n\n getTclient_by_id request good \n\n\n");
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				rs = new Tclient();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) rs.setStatus(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B")) rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE")) rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("B_DATE"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS")) rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE")) rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_STREET")) rs.setR_street(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CITY")) rs.setR_city(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY")) rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("R_PCODE"))
					// rs.setR_pcode(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("U_COD1"))
					// rs.setU_cod1(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C")) rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("A3VTS_COUNTY"))
					// rs.setA3vts_county(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("A3VTS_FLAG1"))
					// rs.setA3vts_flag1(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("A3VTS_FLAG2"))
					// rs.setA3vts_flag2(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("SUPPLEMENTARY_PAN"))
					// rs.setSSupplementary_pan(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("SUPPLEMENTARY_CVV2"))
					// rs.setSupplementary_cvv2(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("SUPPLEMENTARY_EXPIRY"))
					// rs.setSupplementary_expiry(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("NOTES"))
					// rs.setNotes(details.value.getRow(i).getItem(j).getValue());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("EMP_NAME "))
					// rs.setEmp_name(details.value.getRow(i).getItem(j).getValue());
				}
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error("\n\n getTclient_by_id error \n\n\n");
			
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		ISLogger.getLogger().error("\n\n getTclient_by_id good \n\n\n");
		
		return rs;
	}
	
	private static List<FilterField> getFilterFields(TclientFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		return flfields;
	}
	
	private static RowType_ListCustomers_Request getFilterParams(TclientFilter filter)
	{
		RowType_ListCustomers_Request params = new RowType_ListCustomers_Request();
		
		if (filter.getF_names() != null)
		{
			params.setF_NAMES(filter.getF_names());
		}
		
		if (filter.getSurname() != null)
		{
			params.setSURNAME(filter.getSurname());
		}
		
		if (filter.getClient() != null)
		{
			params.setCLIENT(filter.getClient());
		}
		
		return params;
	}
	
	public static int getCount(TclientFilter filter, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		int result = 0;
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters = getFilterParams(filter);
			parameters.setBANK_C(EMPC_BANK_C);
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			
			/*
			 * if (details_list_customers.value == null) {
			 * issuingPortProxy.listCustomers(connectionInfo, parameters,
			 * responseInfo, details); details_list_customers = details; } else
			 * { details = details_list_customers; }
			 */
			result = details.value.getRow().length;
			
			System.out.println("result(COUNT details) => " + result);
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Tclient> getTclientsFl(int pageIndex, int pageSize, TclientFilter filter, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		List<Tclient> list = new ArrayList<Tclient>();
		int v_lowerbound = pageIndex;
		int v_upperbound = v_lowerbound + pageSize;
		
		String prev_client_id = "00000002";
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
		
		RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
		parameters = getFilterParams(filter);
		parameters.setBANK_C(EMPC_BANK_C);
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
		
		try
		{
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			/*
			 * if (details_list_customers.value == null) {
			 * issuingPortProxy.listCustomers(connectionInfo, parameters,
			 * responseInfo, details); details_list_customers = details; } else
			 * { details = details_list_customers; }
			 */
			// if (details.value.getRow().length < pageSize) v_upperbound =
			// details.value.getRow().length;
			
			// for (int i = v_lowerbound; i < v_upperbound; i++)
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				rs = new Tclient();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) rs.setStatus(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B")) rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE")) rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("B_DATE"))
					{
						try
						{
							if (!details.value.getRow(i).getItem(j).getValue().equals("") || details.value.getRow(i).getItem(j).getValue() != null) rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
						catch (Exception e)
						{
							ISLogger.getLogger().error(CheckNull.getPstr(e));
							System.out.println("Exception in getTclientsFl (df.parse) =>" + e.getMessage());
							System.out.println("details.value => " + details.value.getRow(i).getItem(j).getValue());
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS")) rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE")) rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_STREET")) rs.setR_street(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CITY")) rs.setR_city(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY")) rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C")) rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
				}
				
				if (!prev_client_id.equals(rs.getClient()))
				{
					prev_client_id = rs.getClient();
					list.add(rs);
				}
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("Exception in getTclientsFl =>" + e.getLocalizedMessage() + e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return list;
		
	}
	
	public static Res block_card(String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy,
		String reason, String reason_text, String card)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
		
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();
		parameters.setBANK_C(EMPC_BANK_C);
		parameters.setGROUPC(EMPC_GROUPC);
		parameters.setSTOP_CAUSE(reason);
		parameters.setTEXT(reason_text);
		parameters.setCARD(card);
		
		OperationResponseInfo orInfo = null;
		try
		{
			orInfo = issuingPortProxy.addCardToStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0)
			{
				res.setCode(orInfo.getResponse_code().intValue());
				res.setName(orInfo.getError_action() + orInfo.getError_description());
			}
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res.setName(e.getMessage());
			res.setCode(0);
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static Res sendPayment(TrPay tp, String alias, String ccy, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
		
		RowType_ExecTransaction_Request parameters = new RowType_ExecTransaction_Request();
		parameters.setBANK_C(EMPC_BANK_C);
		parameters.setGROUPC(EMPC_GROUPC);
		parameters.setTRAN_TYPE("110");
		parameters.setCARD_ACCT_CCY(ccy);
		parameters.setTRAN_CCY(ccy);
		parameters.setPAYMENT_MODE("1");
		parameters.setTRAN_AMNT(BigDecimal.valueOf(tp.getAmount()));
		parameters.setACCOUNT_NO(BigDecimal.valueOf(Integer.parseInt(tp.getAccount_no())));
		parameters.setCARD_ACCT(tp.getCard_acc());
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		
		try
		{
			issuingPortProxy.executeTransaction(connectionInfo, parameters, responseInfo, details);
			if (details.value.getINTERNAL_NO() != null && responseInfo.value.getError_description() == null)
			{
				res.setCode(1);
				// res.setName("Произведено пополнение на сумму " +
				// BigDecimal.valueOf(tp.getAmount()/100) + " [" + ccy +
				// "] Номер транзакции = > " + details.value.getINTERNAL_NO());
				res.setName(details.value.getINTERNAL_NO().toString());
			}
			else
			{
				res.setCode(0);
				// res.setName("Операция не выполнена, INTERNAL_NO = > " +
				// details.value.getINTERNAL_NO());
				res.setName(details.value.getINTERNAL_NO().toString());
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(responseInfo.value.getError_description());
			System.out.println(e.getLocalizedMessage());
		}
		return res;
	}
	
	public static List<AccInfo> getAccInfo(Tclient client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		List<AccInfo> list = new ArrayList<AccInfo>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCLIENT(client.getClient());
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_GenericHolder details = new ListType_GenericHolder();
			
			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
			
			System.out.println("getAccInfo Error_action =>" + responseInfo.value.getError_action());
			System.out.println("getAccInfo Error_description =>" + responseInfo.value.getError_description());
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				AccInfo rs = new AccInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
					{
						rs.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					}
					
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS")) rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS")) rs.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY")) rs.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE")) rs.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY")) rs.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY")) rs.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT")) rs.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
					{
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
						
					}
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("STATUS2"
					// ))
					// rs.setStatus2(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details.value.getRow(i).getItem(j).getValue());
					
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("COND_SET"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("B_BR_ID"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("OFFICE_ID"
					// ))
					// MAIN_ROW
				}
				
				rs.setCardlist(getCardInfo(rs, alias, issuingPortProxy));
				/*
				 * if
				 * (client.getCard().equals(rs.getCardlist().get(0).getCARD())
				 * && !client.getCard().equals("")) { list.add(rs); }
				 */
				list.add(rs);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("Exception => " + e.getLocalizedMessage());
		}
		return list;
	}
	
	public static Res addCustomerAndAgreement(Customer new_customer, GlobuzAccount acc
		, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		RowType_Customer rtc = new RowType_Customer();
		Calendar cal_p = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		rtc.setF_NAMES(new_customer.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(new_customer.getId_client());
		rtc.setSURNAME(new_customer.getP_family());
		rtc.setM_NAME(new_customer.getP_patronymic());
		cal_p.setTime(new_customer.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal_p);
		cal.setTime(new_customer.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT(new_customer.getCode_resident());
		rtc.setSTATUS("10");
		/*
		 * Статус договора: 10 – активный; 20 – неподтверждённый; 30 –
		 * приостановленный (dormant); >=40 – закрытый (пояснение элемента см. В
		 * разделе: 4.2. AGREEMENTS). Для стандартных продуктов договор
		 * открывается неутверждённым (STATUS=20).
		 */
		rtc.setSEX(new_customer.getP_code_gender());
		rtc.setSERIAL_NO(new_customer.getP_passport_serial());
		rtc.setID_CARD(new_customer.getP_passport_number());
		rtc.setR_CITY(new_customer.getR_CITY());
		rtc.setR_STREET(new_customer.getR_STREET());
		rtc.setR_E_MAILS(new_customer.getR_E_MAILS());
		rtc.setR_MOB_PHONE(new_customer.getR_MOB_PHONE());
		rtc.setR_PHONE(new_customer.getR_PHONE());
		rtc.setR_CNTRY(new_customer.getR_CNTRY());
		rtc.setISSUED_BY(new_customer.getISSUED_BY());
		rtc.setPERSON_CODE(new_customer.getPERSON_CODE());
		rtc.setDOC_TYPE("001");
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		
		RowType_Agreement agreement = new RowType_Agreement();
		agreement.setBRANCH("001");
		// agreement.setCLIENT("00000283"); //rtc.getCLIENT_B());
		// agreement.setCLIENT(rtc.getCLIENT());
		agreement.setBANK_CODE(EMPC_BANK_C);
		agreement.setBINCOD(EMPC_BINCOD);
		agreement.setCITY("Tashkent");
		agreement.setENROLLED(calendar);
		agreement.setREP_LANG("1");
		agreement.setPRODUCT("01");
		agreement.setRISK_LEVEL("A");
		agreement.setSTATUS("10");
		agreement.setSTREET(rtc.getR_STREET());
		agreement.setE_MAILS(rtc.getR_E_MAILS());
		agreement.setCONTRACT("0000002782");
		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agreement);
		
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		base_info.setCREATED_DATE(calendar);
		base_info.setCCY("UZS");
		base_info.setCRD(BigDecimal.valueOf(100000));
		base_info.setMIN_BAL(BigDecimal.valueOf(0));
		base_info.setC_ACCNT_TYPE("00");
		base_info.setNON_REDUCE_BAL(BigDecimal.valueOf(0));
		base_info.setSTATUS("0");
		base_info.setCOND_SET("001");
		base_info.setCYCLE("001");
		base_info.setSTAT_CHANGE("1");
		base_info.setTRANZ_ACCT("22618000999017139701");
		
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();
		accountInfo.setBase_info(base_info);
		
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		accountsListInfo.value = ltaccounts;
		
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCOND_SET("001");
		logicalCard.setRISK_LEVEL("A");
		logicalCard.setBASE_SUPP("1");
		logicalCard.setF_NAMES(rtc.getF_NAMES());
		logicalCard.setSURNAME(rtc.getSURNAME());
		
		logicalCard.setRANGE_ID(BigDecimal.valueOf(2));
		
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		physicalCard.setCARD_NAME(rtc.getF_NAMES() + " " + rtc.getSURNAME());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
		
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		// eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(201));
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(logicalCard);
		cardInfo.setPhysicalCard(physicalCard);
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;
		
		try
		{
			orInfo = issuingPortProxy.newCustomerAndAgreement(
				connectionInfo, customerInfo, customListInfo,
				agreementInfo, accountsListInfo, cardsListInfo);
			
			System.out.println("Response Info output:");
			System.out.println("-------------------------------");
			System.out.println("Response code = " + orInfo.getResponse_code());
			System.out.println("Error description = " + orInfo.getError_description());
			System.out.println("Error action = " + orInfo.getError_action());
			System.out.println("-------------------------------");
			
			if (orInfo.getError_description() != null)
			{
				System.out.println("ERROR tieto add client " + orInfo.getResponse_code() + " client " + customerInfo.value.getCLIENT() + "Error=>" + orInfo.getError_description());
				res.setCode(0);
				res.setName(orInfo.getError_description());
			}
			else
			{
				// alert("Клиент добавлен (GLOBUZ)");
				System.out.println("Клиент добавлен (GLOBUZ)=>" + customerInfo.value.getCLIENT());
				res.setCode(1);
				res.setName("Клиент добавлен (GLOBUZ)");
			}
		}
		catch (RemoteException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(0);
			res.setName(e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return res;
	}
	
	public static Res addNewAgreement(Customer new_customer, GlobuzAccount acc, Tclient tc
		, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		boolean agre_exists = false;
		BigDecimal agre_nom = null;
		
		List<AccInfo> listAcc = TclientService.getAccInfo(tc, alias, issuingPortProxy);
		for (int i = 0; i < listAcc.size(); i++)
		{
			for (int k = 0; k < listAcc.get(i).getCardlist().size(); k++)
			{
				CardInfo cardInfo = listAcc.get(i).getCardlist().get(k);
				
				if (cardInfo.getBank_account_status().equals("0"))
				{
					agre_nom = BigDecimal.valueOf(Long.parseLong(cardInfo.getAGREEMENT_KEY()));
					agre_exists = true;
					break;
				}
			}
		}
		
		if (agre_exists)
		{
			try
			{
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(EMPC_BANK_C);
				connectionInfo.setGROUPC(EMPC_GROUPC);
				connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
				
				KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
				mainAgreementInfo.setAGRE_NOM(agre_nom);
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
				
				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
				logicalCard.setCOND_SET("001");
				logicalCard.setRISK_LEVEL("A");
				logicalCard.setBASE_SUPP("1");
				logicalCard.setF_NAMES(new_customer.getP_first_name());
				logicalCard.setSURNAME(new_customer.getP_family());
				
				logicalCard.setRANGE_ID(BigDecimal.valueOf(2));
				//!
				logicalCard.setCARD_TYPE("01");
				logicalCard.setBRANCH("001");
				//!
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				physicalCard.setCARD_NAME(new_customer.getP_first_name() + " " + new_customer.getP_family());				
				physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
				
				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				//!
				eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));
				//!
				RowType_CardInfo cardInfo = new RowType_CardInfo();
				cardInfo.setLogicalCard(logicalCard);
				cardInfo.setPhysicalCard(physicalCard);
				cardInfo.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				cards.setRow(new RowType_CardInfo[] { cardInfo });
				
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
				cardsListInfo.value = cards;
				
				OperationResponseInfo orInfo = issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
				
				if (orInfo.getError_description() != null)
				{
					res.setCode(0);
					res.setName(orInfo.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = " + orInfo.getResponse_code());
					System.out.println("Error description = " + orInfo.getError_description());
					System.out.println("Error action = " + orInfo.getError_action());
					System.out.println("-------------------------------");
				}
				else
				{
					res.setCode(1);
					res.setName("Карта открыта в EMPC");
				}
			}
			catch (RemoteException e)
			{
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				res.setCode(0);
				res.setName(e.getMessage());
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}
		
		else
		{
			try
			{
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(EMPC_BANK_C);
				connectionInfo.setGROUPC(EMPC_GROUPC);
				connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				
				RowType_Agreement agreement = new RowType_Agreement();
				//!
				agreement.setBRANCH("001");
				//!
				agreement.setCLIENT(tc.getClient());
				agreement.setBANK_CODE(tc.getBank_c());
				agreement.setBINCOD(EMPC_BINCOD);
				agreement.setCITY("Tashkent");
				agreement.setENROLLED(calendar);
				agreement.setREP_LANG("1");
				agreement.setPRODUCT("01");
				agreement.setRISK_LEVEL("A");
				agreement.setSTATUS(tc.getStatus());
				agreement.setSTREET(tc.getR_street());
				agreement.setE_MAILS(new_customer.getR_E_MAILS());
				agreement.setCONTRACT("00" + new_customer.getId_client());
				
				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agreement);
				
				RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
				base_info.setCREATED_DATE(calendar);
				base_info.setCCY("UZS");
				base_info.setCRD(BigDecimal.valueOf(0));
				base_info.setMIN_BAL(BigDecimal.valueOf(0));
				base_info.setC_ACCNT_TYPE("00");
				base_info.setNON_REDUCE_BAL(BigDecimal.valueOf(0));
				base_info.setSTATUS("0");
				base_info.setCOND_SET("001");
				base_info.setCYCLE("001");
				base_info.setSTAT_CHANGE("1");
				base_info.setTRANZ_ACCT(acc.getId());				
				
				RowType_AccountInfo accountInfo = new RowType_AccountInfo();
				accountInfo.setBase_info(base_info);
				
				ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
				ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
				accountsListInfo.value = ltaccounts;
				
				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
				logicalCard.setCOND_SET("001");
				logicalCard.setRISK_LEVEL("A");
				logicalCard.setBASE_SUPP("1");
				logicalCard.setF_NAMES(new_customer.getP_first_name());
				logicalCard.setSURNAME(new_customer.getP_family());
				
				logicalCard.setRANGE_ID(BigDecimal.valueOf(2));
				//!
				logicalCard.setCARD_TYPE("01");
				logicalCard.setBRANCH("001");
				//!
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				physicalCard.setCARD_NAME(new_customer.getP_first_name() + " " + new_customer.getP_family());			
				physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
				
				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				//!
				eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));
				//!
				RowType_CardInfo cardInfo = new RowType_CardInfo();
				cardInfo.setLogicalCard(logicalCard);
				cardInfo.setPhysicalCard(physicalCard);
				cardInfo.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				cards.setRow(new RowType_CardInfo[] { cardInfo });
				
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
				cardsListInfo.value = cards;
				
				OperationResponseInfo orInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
				
				if (orInfo.getError_description() != null)
				{
					res.setCode(0);
					res.setName(orInfo.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = " + orInfo.getResponse_code());
					System.out.println("Error description = " + orInfo.getError_description());
					System.out.println("Error action = " + orInfo.getError_action());
					System.out.println("-------------------------------");
				}
				else
				{
					res.setCode(1);
					res.setName("Карта открыта в EMPC");
				}
			}
			catch (RemoteException e)
			{
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				res.setCode(0);
				res.setName(e.getMessage());
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static Res getAccountBal(CardInfo rs, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		
		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
		
		RowType_AccBalanceQueryByCard_Request parametersCard = new RowType_AccBalanceQueryByCard_Request();
		parametersCard.setBANK_C(EMPC_BANK_C);
		parametersCard.setGROUPC(EMPC_GROUPC);
		parametersCard.setCARD(rs.getCARD());
		
		try
		{
			issuingPortProxy.queryAccountBalanceByCard(connectionInfo, parametersCard, responseInfo, details);
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("AVAIL_AMOUNT"))
					// rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					// System.out.println("AVAIL_AMOUNT => " +
					// details.value.getRow(i).getItem(j).getValue());
					rs.setACCOUNT_AVAIL_AMOUNT(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					
					if (details.value.getRow(i).getItem(j).getName().equals("LOCKED_AMOUNT"))
					// /System.out.println("LOCKED_AMOUNT => " +
					// details.value.getRow(i).getItem(j).getValue());
					rs.setACCOUNT_LOCKED_AMOUNT(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					
					if (details.value.getRow(i).getItem(j).getName().equals("END_BAL"))
					// /System.out.println("END_BAL => " +
					// details.value.getRow(i).getItem(j).getValue());
					rs.setACCOUNT_END_BAL(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
				}
			}
			System.out.println(responseInfo.value.getError_description());
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return res;
	}
	
	public static List<CardInfo> getCardInfo(AccInfo account, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		List<CardInfo> list = new ArrayList<CardInfo>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
			parameters.setACCOUNT_NO(BigInteger.valueOf(account.getAccount_no()));
			parameters.setCARD_ACCT(account.getCard_acct());
			parameters.setCCY(account.getCcy());
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			
			issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				CardInfo rs = new CardInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT")) rs.setCARD_ACCT(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCARD(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BASE_SUPP")) rs.setBASE_SUPP(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) rs.setSTATUS(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS2")) rs.setSTATUS2(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STOP_CAUSE")) rs.setSTOP_CAUSE(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY")) rs.setEXPIRY(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY2")) rs.setEXPIRY2(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("COND_SET")) rs.setCOND_SET(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("RISK_LEVEL")) rs.setRISK_LEVEL(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_ID")) rs.setCLIENT_ID(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_ROLE")) rs.setCL_ROLE(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAGREEMENT_KEY(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_NAME")) rs.setCARD_String(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C")) rs.setBANK_C(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("GROUPC")) rs.setGROUPC(details.value.getRow(i).getItem(j).getValue());
				}
				
				getAccountBal(rs, alias, issuingPortProxy);
				rs.setBank_account(account.getTranz_acct());
				rs.setBank_account_status(account.getStatus1());
				rs.setBank_account_Ccy(account.getCcy());
				
				list.add(rs);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<AccInfo> getAccInfo_active(String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		
		List<AccInfo> list = new ArrayList<AccInfo>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCLIENT(client);
			parameters.setSTATUS("0");
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			
			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				AccInfo rs = new AccInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT")) rs.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS")) rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS")) rs.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY")) rs.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE")) rs.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY")) rs.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY")) rs.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT")) rs.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
					{
						// if (details.value.getRow(i).getItem(j).getValue() ==
						// null) rs.setStatus1("0");
						// else
						// rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
						
					}
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("STATUS2"
					// ))
					// rs.setStatus2(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details.value.getRow(i).getItem(j).getValue());
					
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("COND_SET"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("B_BR_ID"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("OFFICE_ID"
					// ))
					// MAIN_ROW
				}
				list.add(rs);
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static AccInfo getAccInfoByCard(String Card_acct, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		AccInfo rs = null;
		Connection c = null;
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCARD_ACCT(Card_acct);
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			
			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
			
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				rs = new AccInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT")) rs.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS")) rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS")) rs.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY")) rs.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE")) rs.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY")) rs.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							rs.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY")) rs.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT")) rs.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
					{
						// if (details.value.getRow(i).getItem(j).getValue() ==
						// null) rs.setStatus1("0");
						// else
						// rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
						
					}
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("STATUS2"
					// ))
					// rs.setStatus2(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details.value.getRow(i).getItem(j).getValue());
					
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("COND_SET"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("B_BR_ID"
					// ))
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("OFFICE_ID"
					// ))
					// MAIN_ROW
				}
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return rs;
	}
	
	public static TietoCardSetting getTietoCardSetting(int tietocardsettingId, String alias)
	{
		
		TietoCardSetting tietocardsetting = new TietoCardSetting();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_globuz_CARD_SETTING WHERE code=?");
			ps.setInt(1, tietocardsettingId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				tietocardsetting = new TietoCardSetting();
				
				tietocardsetting.setCode(rs.getInt("code"));
				tietocardsetting.setName(rs.getString("name"));
				tietocardsetting.setBin(rs.getString("bin"));
				tietocardsetting.setRisk_level(rs.getString("risk_level"));
				tietocardsetting.setFinancial_conditions(rs.getString("financial_conditions"));
				tietocardsetting.setMinimum_balance(rs.getBigDecimal("minimum_balance"));
				tietocardsetting.setId_chip_app(rs.getBigDecimal("id_chip_app"));
				tietocardsetting.setId_order_account(rs.getString("id_order_account"));
				tietocardsetting.setGroup_c(rs.getString("groupc"));
				tietocardsetting.setBank_c(rs.getString("bank_c"));
				tietocardsetting.setCard_condition(rs.getString("card_condition"));
				tietocardsetting.setDesign_id(rs.getBigDecimal("design_id"));
				// tietocardsetting.setAcc_name_postfix(rs.getString("acc_postfix"));
				// tietocardsetting.setHo_acc_group(rs.getInt("acc_group_head"));
				// tietocardsetting.setBr_acc_group(rs.getInt("acc_group_fil"));
				// tietocardsetting.setId_order_max(rs.getString("id_order_max"));
				// tietocardsetting.setAllow_multiple_card_per_acc(rs.getInt("allow_multiple_card_per_acc"));
				// tietocardsetting.setAcc_name_postfix(acc_name_postfix)(rs.getInt("acc_group_fil"));
			}
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return tietocardsetting;
	}
	
	public static String getkass_acc(String branch, String alias)
	{
		
		String res = "";
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(
				"select acc.account res from BF_TR_ACC acc, BF_TR_TEMPLATE t " +
								"where t.operation_id = 1 " +
								"and acc.acc_template_id = t.acc_dt " +
								"and ROWNUM = 1 " +
								"and acc.branch = ? " +
								"order by t.ord");
			ps.setString(1, branch);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getString("res");
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res check_user(String alias, String Branch_id, String TIETO_CUSTOMER_ID)
	{
		Res res;
		String BANK_CUSTOMER_ID = "";
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("Select BANK_CUSTOMER_ID From BF_TIETO_CUSTOMERS TC " +
					"Where TC.BRANCH = ? and TC.TIETO_CUSTOMER_ID = ?");
			ps.setString(1, Branch_id);
			ps.setString(2, TIETO_CUSTOMER_ID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			BANK_CUSTOMER_ID = rs.getString("BANK_CUSTOMER_ID");
			res = new Res(1, BANK_CUSTOMER_ID);
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(0, e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static GeneralInfo getSummOfPayment(String alias, Long General_id)
	{
		GeneralInfo generalInfo = new GeneralInfo();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select g.id, g.summa, g.acc_co, g.doc_num " +
					"from GENERAL g where g.id = ?");
			ps.setLong(1, General_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				generalInfo.setSUMMA(BigDecimal.valueOf(Long.parseLong(rs.getString("summa"))));
				generalInfo.setID(Long.parseLong(rs.getString("id")));
				generalInfo.setACC_CO(rs.getString("acc_co"));
				generalInfo.setDOC_NUM(rs.getString("doc_num"));
			}
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return generalInfo;
	}
	
	public static Res check_allowed_card_action(int deal_group, int deal_id, int action_id, String card, String alias)
	{
		Res res = new Res(0, "");
		
		@SuppressWarnings("unused")
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			CallableStatement proc = c.prepareCall("{call BF.CHECK_ALLOWED_CARD_ACTION(?, ?, ?, ?) }");
			
			proc.setInt(1, deal_group);
			proc.setInt(2, deal_id);
			proc.setInt(3, action_id);
			proc.setString(4, card);
			
			proc.execute();
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static boolean check_card1(String new_card_type, List<AccInfo> cards, String alias)
	{
		String qstring = "";
		
		if (cards.size() != 0)
		{
			for (int i = 0; i < cards.size() - 1; i++)
			{
				qstring += cards.get(i).getCard_type();
				qstring += ",";
			}
			qstring += cards.get(cards.size() - 1).getCard_type();
		}
		else qstring = null;
		
		System.out.println("argument:" + qstring);
		
		// String res="";
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			CallableStatement proc = c.prepareCall("{ call BF.CHECK_OPEN_CARD(?,?) }");
			
			proc.setString(1, new_card_type);
			proc.setString(2, qstring);
			
			proc.execute();
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			return false;
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return true;
	}
	
	public static Res check_card(String new_card_type, List<AccInfo> cards, String alias)
	{
		Res res = new Res(0, "");
		String qstring = "";
		
		if (cards.size() != 0)
		{
			for (int i = 0; i < cards.size() - 1; i++)
			{
				qstring += cards.get(i).getCard_type();
				qstring += ",";
			}
			qstring += cards.get(cards.size() - 1).getCard_type();
		}
		else qstring = null;
		System.out.println("argument:" + qstring);
		
		// String res="";
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			CallableStatement proc = c.prepareCall("{ call BF.CHECK_OPEN_CARD(?,?) }");
			
			proc.setString(1, new_card_type);
			proc.setString(2, qstring);
			
			proc.execute();
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			// e.printStackTrace();
			res.setCode(-1);
			res.setName(e.getMessage());
			return res;
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static List<Action> getActions(int userid, String user_branch, String alias)
	{
		
		List<Action> list = new ArrayList<Action>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			// System.out.println("select a.* from bf_actions a where a.mid=11 and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="+userid+" and r.actionid=a.id)");
			
			PreparedStatement ps = c.prepareStatement("Select act.*" +
									" From   BF_USER_ROLES   UR," +
									" BF_ROLE_ACTIONS RA," +
									" bf_actions      act" +
									" Where  UR.USERID = ?" +
									" and UR.BRANCH = ?" +
									" and RA.ROLEID = UR.ROLEID" +
									" and act.id = ra.actionid" +
									" and act.deal_id = ra.deal_id" +
									" and RA.MID = 11" +
									" and act.mid = RA.MID");
			ps.setInt(1, userid);
			ps.setString(2, user_branch);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				list.add(new Action(
								rs.getInt("id"),
								rs.getInt("mid"),
								rs.getString("name"),
								rs.getString("icon"),
								rs.getInt("deal_id"),
								rs.getInt("rep_type_id")));
				
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public static Res checkBfGlobuzTrans()
	{
		Res res = new Res();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from bf_globuz_trans");
			ps.executeQuery();
			
			res.setCode(1);
			res.setName("Успешно");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res insertIntoBfGlobuzTrans(Bf_globuz_trans bf_globuz_trans)
	{
		Res res = new Res();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("insert into bf_globuz_trans(branch, genid, globid, empid, trdate, state)" +
					" values (?, ?, ?, ?, sysdate, ?)");
			
			ps.setString(1, bf_globuz_trans.getBRANCH());
			ps.setLong(2, bf_globuz_trans.getGENID());
			ps.setLong(3, bf_globuz_trans.getGLOBID());
			ps.setString(4, bf_globuz_trans.getEMPID());
			ps.setInt(5, bf_globuz_trans.getSTATE());
			
			ps.executeQuery();
			c.commit();
			
			res.setCode(1);
			res.setName("Успешно");
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static String getCountryNameISO3(String ISO3)
	{
		
		String res = null;
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getTConnection();
			PreparedStatement ps = c.prepareStatement("SELECT name_en FROM base_country where iso3 = ?");
			ps.setString(1, ISO3);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res = rs.getString("name_en");
			}
			if (res == null) res = ISO3;
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
		
	}
	
	public static Agreement getCardAgreement(BigDecimal agre_nom, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Agreement rs = null;
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(utils.getExternalSession());
			
			KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
			mainAgreementInfo.setAGRE_NOM(agre_nom);
			
			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
			
			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
			
			issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
			
			rs = new Agreement();
			
			/*
			 * for (int i = 0; i < details.value.getRow().length; i++) { rs =
			 * new Agreement(); for (int j = 0; j <
			 * details.value.getRow(i).getItem().lengt; j++) { //if
			 * (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"
			 * ))
			 * rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem
			 * (j).getValue())); } }
			 */
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		/*
		 * try { c = ConnectionPool.getTConnection(); PreparedStatement ps =
		 * c.prepareStatement
		 * ("SELECT * FROM izd_agreement t where t.agre_nom = ?"); ps.setInt(1,
		 * agre_nom); ResultSet rs = ps.executeQuery(); while (rs.next()) {
		 * res.setAGRE_NOM(agre_nom); res.setB_BR_ID(rs.getInt("b_br_id"));
		 * res.setBANK_CODE(rs.getString("BANK_CODE"));
		 * res.setBINCOD(rs.getString("BINCOD"));
		 * res.setBRANCH(rs.getString("BRANCH"));
		 * res.setCITY(rs.getString("CITY"));
		 * res.setCLIENT(rs.getString("CLIENT"));
		 * res.setCOMENT(rs.getString("COMENT"));
		 * res.setCONTRACT(rs.getString("CONTRACT"));
		 * res.setCOUNTRY(rs.getString("COUNTRY"));
		 * res.setCTIME(rs.getDate("CTIME"));
		 * res.setDISTRIB_MODE(rs.getString("DISTRIB_MODE"));
		 * res.setE_MAILS(rs.getString("E_MAILS"));
		 * res.setENROLLED(rs.getDate("ENROLLED"));
		 * res.setIN_FILE_NUM(rs.getInt("IN_FILE_NUM"));
		 * res.setISURANCE_TYPE(rs.getString("ISURANCE_TYPE"));
		 * res.setOFFICE(rs.getString("OFFICE"));
		 * res.setOFFICE_ID(rs.getInt("OFFICE_ID"));
		 * res.setPOST_IND(rs.getString("POST_IND"));
		 * res.setPRODUCT(rs.getString("PRODUCT"));
		 * res.setREP_LANG(rs.getString("REP_LANG"));
		 * res.setRISK_LEVEL(rs.getString("RISK_LEVEL"));
		 * res.setSTATUS(rs.getString("STATUS"));
		 * res.setSTREET(rs.getString("STREET"));
		 * res.setU_COD4(rs.getString("U_COD4"));
		 * res.setU_CODE5(rs.getString("U_CODE5"));
		 * res.setU_CODE6(rs.getString("U_CODE6"));
		 * res.setU_FIELD3(rs.getString("U_FIELD3"));
		 * res.setU_FIELD4(rs.getString("U_FIELD4"));
		 * res.setUSRID(rs.getString("USRID")); } } catch (SQLException e) {
		 * LtLogger.getLogger().error(CheckNull.getPstr(e));
		 * e.printStackTrace(); } finally { ConnectionPool.close(c); }
		 */
		return rs;
	}
	
	public static String get_report_file(int deal_id, int action_id, String alias)
	{
		String res = new String();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select t.report res from BF_TIETO_TR_ACTION_REPORT t " +
										" where t.deal_id = ? and t.action_id = ?");
			ps.setInt(1, deal_id);
			ps.setInt(2, action_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			res = rs.getString("res");
			
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static HashMap<String, String> get_client_ti_acc(String client, String product)
	{
		HashMap<String, String> res = new HashMap<String, String>();
		
		Connection c = null;
		
		/*
		 * try { c = ConnectionPool.getTConnection(); PreparedStatement ps =
		 * c.prepareStatement( "select " + "ac.account_no, " + "ac.card_acct, "
		 * + "ac.ac_status, " + "ac.cl_status, " + "ac.acc_prty, " +
		 * "ac.c_accnt_type, " + "ac.ccy, " + "ac.f_names, " + "ac.surname, " +
		 * "ac.city, " + "ac.street, " + "ac.tranz_acct, " + "a.agre_nom, " + ""
		 * + "t.card, " + "t.status1, " + "t.status2, " + "t.cond_set, " +
		 * "t.client_id client, " + "t.ctime, " + "t.expiry1 ab_expirity, " +
		 * "t.agreement_key, " + "" + "a.product, " + "a.contract, " + "p.name "
		 * + "" + "from izd_cards t, " + "izd_agreement a, " +
		 * "izd_offered_products p, " + "izd_acc_info ac " + "" +
		 * "where a.agre_nom = t.agreement_key " + "and t.bank_c = '01' " +
		 * "and t.groupc = '02' " + "and a.bank_c = '01' " +
		 * "and a.groupc = '02' " + "and p.bank_c = '01' " +
		 * "and p.groupc = '02' " + "and ac.bank_c = '01' " +
		 * "and ac.groupc = '02' " + "and p.code = a.product " +
		 * "and t.cl_acct_key=ac.tab_key " + "and t.client_id = ? " +
		 * "and a.product = ?" ); ps.setString(1, client); ps.setString(2,
		 * product); ResultSet rs = ps.executeQuery(); while (rs.next()) { if
		 * (!(res.containsKey(rs.getString("tranz_acct"))))
		 * res.put(rs.getString("tranz_acct"), ""); } } catch (SQLException e) {
		 * LtLogger.getLogger().error(CheckNull.getPstr(e));
		 * e.printStackTrace(); }
		 */

		return res;
	}
	
	public static List<Bf_globuz_trans> getBf_globuz_trans(String account, String branch, String alias)
	{
		List<Bf_globuz_trans> list = new ArrayList<Bf_globuz_trans>();
		Bf_globuz_trans bf_globuz_trans;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select g.branch, g.acc_co, s.trdate, g.summa/100 summa, s.state " +
														"from bf_globuz_trans s, general g " +
														"where s.genid = g.id " +
														"and g.acc_co = ? " +
														"and s.branch = ? "
				);
			ps.setString(1, account);
			ps.setString(2, branch);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				bf_globuz_trans = new Bf_globuz_trans();
				bf_globuz_trans.setBRANCH(rs.getString("branch"));
				bf_globuz_trans.setAccount(rs.getString("acc_co"));
				bf_globuz_trans.setTRDATE(new java.sql.Date(rs.getDate("trdate").getTime()));
				bf_globuz_trans.setSUMMA(rs.getBigDecimal("summa"));
				bf_globuz_trans.setSTATE(rs.getInt("state"));
				list.add(bf_globuz_trans);
			}
			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getPayments4GlobUz(String branch, String acc_co, String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select g.id data, " +
							"g.purpose || ' [ Summa ' || g.summa/100 || ' ' ||s.kod_b || ' ]' label " +
							"from GENERAL g, S_VAL s " +
						"where g.branch = '" + branch + "'" +
						"and g.acc_co = '" + acc_co + "'" +
						"and s.kod = g.currency " +
						"and s.kod_b = 'UZS' " +
						"and not exists (select p.genid from bf_globuz_trans p " +
										" where g.branch = p.branch" +
										" and g.id = p.genid)  " +
							"order by g.d_date ";
		
		list = com.is.utils.RefDataService.getRefData(sql, alias);
		return list;
	}
	
	public static List<RefData> getTstopCauses(String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select t.couse data, t.name label from BF_GLOBUZ_STOP_CAUSES t where t.couse in ('1','2','4','5') order by 1";
		list = com.is.utils.RefDataService.getRefData(sql, alias);
		return list;
	}
	
	public static HashMap<String, String> getHTstopCauses(String alias)
	{
		
		//System.out.println("EMPC_BANK_C =>" + EMPC_BANK_C);
		
		HashMap<String, String> _tstopCauses = new HashMap<String, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			String sql = "select t.couse data, t.name label from BF_GLOBUZ_STOP_CAUSES t";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				_tstopCauses.put(rs.getString("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return _tstopCauses;
	}
	
	public static String getStateDesc(int State, String alias)
	{
		String StateDesc = "";
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select t.data from BF_GLOBUZ_TRANS_STATES t where t.value = ?");
			ps.setInt(1, State);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				StateDesc = rs.getString("data");
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return StateDesc;
	}
	
	public static String getEMPC_BANK_C()
	{
		return EMPC_BANK_C;
	}
	
	public static void setEMPC_BANK_C(String eMPC_BANK_C)
	{
		EMPC_BANK_C = eMPC_BANK_C;
	}
	
	public static String getEMPC_GROUPC()
	{
		return EMPC_GROUPC;
	}
	
	public static void setEMPC_GROUPC(String eMPC_GROUPC)
	{
		EMPC_GROUPC = eMPC_GROUPC;
	}
	
	public static String getEMPC_BINCOD()
	{
		return EMPC_BINCOD;
	}
	
	public static void setEMPC_BINCOD(String eMPC_BINCOD)
	{
		EMPC_BINCOD = eMPC_BINCOD;
	}
	
}
