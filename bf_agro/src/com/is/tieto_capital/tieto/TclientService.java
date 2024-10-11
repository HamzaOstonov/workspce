package com.is.tieto_capital.tieto;

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
import com.is.tieto_capital.Constants;
import com.is.tieto_capital.cardApproval.CardApproval;
import com.is.tieto_capital.customer.CardCustomerSearch;
import com.is.tieto_capital.customer.Customer;
import com.is.tieto_capital.customer.CustomerService;
import com.is.tieto_capital.customer.Range;
import com.is.trpay.TrPay;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

import capitalBank.IssuingWS.IssuingPortProxy;
import capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails;
import capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters;
import capitalBank.issuing_v_01_02_xsd.ItemType_Generic;
import capitalBank.issuing_v_01_02_xsd.KeyType_Agreement;
import capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo;
import capitalBank.issuing_v_01_02_xsd.ListType_CardInfo;
import capitalBank.issuing_v_01_02_xsd.ListType_Generic;
import capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo;
import capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA;
import capitalBank.issuing_v_01_02_xsd.OperationResponseInfo;
import capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA;
import capitalBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo_Additional;
import capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_Agreement;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import capitalBank.issuing_v_01_02_xsd.RowType_Customer;
import capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_FilterCondition;
import capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response;
import capitalBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request;
import capitalBank.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;

public class TclientService
{
	private static Tclient rs;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 1971-06-13T00:00:00
	
	private static String EMPC_BANK_C = "";
	private static String EMPC_GROUPC = "";
	private static String EMPC_BINCOD = "";
	
	public TclientService(String alias)
	{
		EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		EMPC_BINCOD = TclientService.getEmpcBincodFromDB();	
	}
	
	public static String getEmpcBincodFromDB()
	{
		String empcBincode = "";
		
		Connection c = null;		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT bin FROM bf_tieto_card_setting");
			while (rs.next())
			{
				empcBincode = rs.getString("bin");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return empcBincode;
	}
	
	public static List<Tclient> getTclient(String serial_no, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		if(EMPC_BANK_C.equals(""))
		{
			EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		}
		if(EMPC_GROUPC.equals(""))
		{
			EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		}
		if(EMPC_BINCOD.equals(""))
		{
			EMPC_BINCOD = TclientService.getEmpcBincodFromDB();	
		}
		
		List<Tclient> list = new ArrayList<Tclient>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters.setBANK_C(EMPC_BANK_C);
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_GenericHolder details = new ListType_GenericHolder(null);
			
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
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static Tclient getTclient_by_id(String client_id, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy, String alias)
	{
		if(EMPC_BANK_C.equals(""))
		{
			EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		}
		if(EMPC_GROUPC.equals(""))
		{
			EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		}
		if(EMPC_BINCOD.equals(""))
		{
			EMPC_BINCOD = TclientService.getEmpcBincodFromDB();	
		}
		
		Tclient rs = null;
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters.setBANK_C(EMPC_BANK_C);
			parameters.setCLIENT(client_id);
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_GenericHolder details = new ListType_GenericHolder(null);
			
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
			}
			
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return rs;
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
	
	public static int getCount(TclientFilter filter, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		int result = 0;
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters = getFilterParams(filter);
			parameters.setBANK_C(EMPC_BANK_C);
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_GenericHolder details = new ListType_GenericHolder(null);
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			
			result = details.value.getRow().length;
			
			System.out.println("result(COUNT details) => " + result);
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<Tclient> getTclientsFl(int pageIndex, int pageSize, TclientFilter filter, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		List<Tclient> list = new ArrayList<Tclient>();
		int v_lowerbound = pageIndex;
		int v_upperbound = v_lowerbound + pageSize;
		
		String prev_client_id = "00000000";
		
		
		if(EMPC_BANK_C.equals(""))
		{
			EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		}
		if(EMPC_GROUPC.equals(""))
		{
			EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		}
		if(EMPC_BINCOD.equals(""))
		{
			EMPC_BINCOD = TclientService.getEmpcBincodFromDB();	
		}		
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		
		RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
		parameters = getFilterParams(filter);
		parameters.setBANK_C(EMPC_BANK_C);
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder();
		
		try
		{
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
			e.printStackTrace();
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return list;
		
	}
	
	public static Res block_card(String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy,
		String reason, String reason_text, String card)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		
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
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res.setName(e.getMessage());
			res.setCode(0);
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static Res sendPayment(TrPay tp, String alias, String ccy, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		
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
				res.setName(details.value.getINTERNAL_NO().toString());
			}
			else
			{
				res.setCode(0);
				res.setName(details.value.getINTERNAL_NO().toString());
			}
		}
		catch (Exception e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(0);
			res.setName(responseInfo.value.getError_description());
			System.out.println(e.getLocalizedMessage());
		}
		return res;
	}
	
	// improved
	public static List<AccInfo> getAccInfo(Tclient tClient, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy,
			Boolean smart) {
		
		List<AccInfo> accInfoList = new ArrayList<AccInfo>();
		ItemType_Generic detailsElement;
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder();
		
		try {			
			
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);			
			
			parameters.setCLIENT(tClient.getClient());		
			
			
			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);			
			if((responseInfo.value.getError_description() != null) || (responseInfo.value.getResponse_code().intValue() != 0)) {
				
				LtLogger.getLogger().error("WebService request listAccounts error (function getAccInfo):\n" + 
						responseInfo.value.getError_description() +
						"\nErrorAction:\n" + responseInfo.value.getError_action());
			}
			
			for (int i = 0; i < details.value.getRow().length; i++) {
				
				AccInfo accInfo = new AccInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					
					detailsElement = details.value.getRow(i).getItem(j);
					
					if(detailsElement.getName().equals("ACCOUNT_NO")) {
						accInfo.setAccount_no(Long.parseLong(detailsElement.getValue()));
					}
					if(detailsElement.getName().equals("CLIENT")) {
						accInfo.setClient(detailsElement.getValue());
					}					
					if(detailsElement.getName().equals("CARD_ACCT")) {
						accInfo.setCard_acct(detailsElement.getValue());
						
					}					
					if(detailsElement.getName().equals("CTIME") && !detailsElement.getName().equals("")) {
						accInfo.setCtime(df.parse(detailsElement.getValue().substring(0, 10)));						
					}
					if(detailsElement.getName().equals("AC_STATUS")) {
						accInfo.setAc_status(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("CL_STATUS")) {
						accInfo.setCl_status(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("ACC_PRTY")) {
						accInfo.setAcc_prty(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("C_ACCNT_TYPE")) {
						accInfo.setC_accnt_type(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("CCY")) {
						accInfo.setCcy(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("AB_EXPIRITY") && !detailsElement.getName().equals("")) {
						accInfo.setAb_expirity(df.parse(detailsElement.getValue().substring(0, 10)));
					}
					if(detailsElement.getName().equals("F_NAMES")) {
						accInfo.setF_names(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("SURNAME")) {
						accInfo.setSurname(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("CITY")) {
						accInfo.setCity(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("STREET")) {
						accInfo.setStreet(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("TRANZ_ACCT")) {
						accInfo.setTranz_acct(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("CARD")) {
						accInfo.setCard(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("STATUS")) {
						accInfo.setStatus1(detailsElement.getValue());
						accInfo.setAc_status(detailsElement.getValue());						
					}
					if(detailsElement.getName().equals("PRODUCT")) {
						accInfo.setProduct(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("STREET")) {
						accInfo.setStreet(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("AGREEMENT_KEY")) {
						accInfo.setAgreement_key(BigDecimal.valueOf(Long.parseLong(detailsElement.getValue())));
					}
					if(detailsElement.getName().equals("AGRE_NOM")) {
						accInfo.setAgre_nom(detailsElement.getValue());
					}
					if(detailsElement.getName().equals("CONTRACT")) {
						accInfo.setContract(detailsElement.getValue());
					}					
				}
				
				
				accInfo.setCardlist(getCardInfo(accInfo, alias, issuingPortProxy, smart));
				
				accInfoList.add(accInfo);
			}

			
		}
		catch (Exception e)	{
			LtLogger.getLogger().error("WebService request listAccounts error (function getAccInfo):\n" + CheckNull.getPstr(e));
		}
		
		return accInfoList;
	}
	
	public static Res addCustomerAndAgreement(Customer new_customer, GlobuzAccount acc
		, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res res = new Res();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		
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
		
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		physicalCard.setCARD_NAME(rtc.getF_NAMES() + " " + rtc.getSURNAME());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
		
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		
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
				System.out.println("Клиент добавлен (ТИЕТО) => " + customerInfo.value.getCLIENT());
				res.setCode(1);
				res.setName("Клиент добавлен (ТИЕТО)");
			}
		}
		catch (RemoteException e)
		{
			res.setCode(0);
			res.setName(e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return res;
	}
	
	// improved
	private static Res getAccountBalance(CardInfo cardInfo, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Res result = new Res();
		ItemType_Generic detailsElement;
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_AccBalanceQueryByCard_Request parametersCard = new RowType_AccBalanceQueryByCard_Request();
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder(null);		
		
		
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);		
		
		parametersCard.setBANK_C(EMPC_BANK_C);
		parametersCard.setGROUPC(EMPC_GROUPC);
		parametersCard.setCARD(cardInfo.getCARD());
		
		try	{
			
			issuingPortProxy.queryAccountBalanceByCard(connectionInfo, parametersCard, responseInfo, details);
			if((responseInfo.value.getError_description() != null) || (responseInfo.value.getResponse_code().intValue() != 0)) {
				result.setCode(-11);
				result.setName("WebService request queryAccountBalanceByCard error:\n" + responseInfo.value.getError_description());
			}	
			
			for (int i = 0; i < details.value.getRow().length; i++)	{
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					
					detailsElement = details.value.getRow(i).getItem(j);
							
					if (detailsElement.getName().equals("AVAIL_AMOUNT")) {
						cardInfo.setACCOUNT_AVAIL_AMOUNT(BigDecimal.valueOf(Long.parseLong(detailsElement.getValue())));
					}
					
					if (detailsElement.getName().equals("LOCKED_AMOUNT")) {
						cardInfo.setACCOUNT_LOCKED_AMOUNT(BigDecimal.valueOf(Long.parseLong(detailsElement.getValue())));
					}
					
					if (detailsElement.getName().equals("END_BAL")) {
						cardInfo.setACCOUNT_END_BAL(BigDecimal.valueOf(Long.parseLong(detailsElement.getValue())));
					}
				}
			}					
		}
		catch (Exception e)	{
			result.setCode(-1);
			result.setName("WebService request queryAccountBalanceByCard error:\n" + e.getMessage());
		}
		
		return result;
	}
	
	// improved
	private static List<CardInfo> getCardInfo(AccInfo accInfo, String alias, 
			capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy,
			Boolean smart)
	{
		List<CardInfo> cardInfoList = new ArrayList<CardInfo>();
		ItemType_Generic detailsElement;
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();		
		ListType_GenericHolder details = new ListType_GenericHolder(null);
		
		try	{
			
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);			
			
			parameters.setACCOUNT_NO(BigInteger.valueOf(accInfo.getAccount_no()));
			parameters.setCARD_ACCT(accInfo.getCard_acct());
			parameters.setCCY(accInfo.getCcy());			

			
			issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
			if((responseInfo.value.getError_description() != null) || (responseInfo.value.getResponse_code().intValue() != 0)) {
				
				LtLogger.getLogger().error(("WebService request listCardsByAccount error:\n" + responseInfo.value.getError_description()));
			}	
			
			for (int i = 0; i < details.value.getRow().length; i++) {
				
				CardInfo cardInfo = new CardInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					
					detailsElement = details.value.getRow(i).getItem(j);
					
					if (detailsElement.getName().equals("ACCOUNT_NO")) {
						cardInfo.setACCOUNT_NO(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("CARD_ACCT")) {
						cardInfo.setCARD_ACCT(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("CARD")) {
						cardInfo.setCARD(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("BASE_SUPP")) {
						cardInfo.setBASE_SUPP(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("STATUS")) {
						cardInfo.setSTATUS(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("STATUS2")) {
						cardInfo.setSTATUS2(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("STOP_CAUSE")) {
						cardInfo.setSTOP_CAUSE(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("EXPIRY")) {
						cardInfo.setEXPIRY(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("EXPIRY2")) {
						cardInfo.setEXPIRY2(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("COND_SET")) {
						cardInfo.setCOND_SET(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("RISK_LEVEL")) {
						cardInfo.setRISK_LEVEL(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("CLIENT_ID")) {
						cardInfo.setCLIENT_ID(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("CL_ROLE")) {
						cardInfo.setCL_ROLE(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("AGREEMENT_KEY")) {
						cardInfo.setAGREEMENT_KEY(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("CARD_NAME")) {
						cardInfo.setCARD_String(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("BANK_C")) {
						cardInfo.setBANK_C(detailsElement.getValue());
					}
					if (detailsElement.getName().equals("GROUPC")) {
						cardInfo.setGROUPC(detailsElement.getValue());
					}
				}
				
				getAccountBalance(cardInfo, alias, issuingPortProxy);
				cardInfo.setBank_account(accInfo.getTranz_acct());
				cardInfo.setBank_account_status(accInfo.getStatus1());
				cardInfo.setBank_account_Ccy(accInfo.getCcy());
				if((cardInfo.getCARD_ACCT().substring(17).equals("444") && smart) || 
						((!cardInfo.getCARD_ACCT().substring(17).equals("444")) && (!smart)))
				cardInfoList.add(cardInfo);
			}
		}
		catch (Exception e)	{			
			LtLogger.getLogger().error("WebService request listCardsByAccount error:\n" + CheckNull.getPstr(e));
		}
		
		return cardInfoList;
	}
	
	public static List<AccInfo> getAccInfo_active(String client, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		
		List<AccInfo> list = new ArrayList<AccInfo>();
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			
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
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());						
					}
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details.value.getRow(i).getItem(j).getValue());
				}
				list.add(rs);
			}
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static AccInfo getAccInfoByCard(String Card_acct, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		AccInfo rs = null;
		Connection c = null;
		
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			
			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCARD_ACCT(Card_acct);
			
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			
			ListType_GenericHolder details = new ListType_GenericHolder(null);
			
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
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());						
					}
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details.value.getRow(i).getItem(j).getValue());
				}
			}
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return rs;
	}
	
	// improved
	public static TietoCardSetting getTietoCardSetting(String cardCode, String alias) {		
		
		TietoCardSetting tietoCardSetting = new TietoCardSetting();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try	{
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM bf_tieto_card_setting WHERE code = ?");
			ps.setString(1, cardCode); 
			rs = ps.executeQuery();
			if (rs.next()) {
				tietoCardSetting = new TietoCardSetting();
				
				tietoCardSetting.setCode(rs.getInt("code"));
				tietoCardSetting.setName(rs.getString("name"));
				tietoCardSetting.setBin(rs.getString("bin"));
				tietoCardSetting.setRisk_level(rs.getString("risk_level"));
				tietoCardSetting.setFinancial_conditions(rs.getString("financial_conditions"));
				tietoCardSetting.setMinimum_balance(rs.getBigDecimal("minimum_balance"));
				tietoCardSetting.setId_chip_app(rs.getBigDecimal("id_chip_app"));
				tietoCardSetting.setId_order_account(rs.getString("id_order_account"));
				tietoCardSetting.setGroup_c(rs.getString("groupc"));
				tietoCardSetting.setBank_c(rs.getString("bank_c"));
				tietoCardSetting.setCard_condition(rs.getString("card_condition"));
				tietoCardSetting.setDesign_id(rs.getBigDecimal("design_id"));
				tietoCardSetting.setRange_start(rs.getInt("range_start"));
				tietoCardSetting.setRange_end(rs.getInt("range_end"));
			}
		}
		catch (Exception e)	{
			LtLogger.getLogger().error("getTietoCardSetting from database error:\n" + CheckNull.getPstr(e));
		}
		finally	{
			ConnectionPool.close(c);
		}
		return tietoCardSetting;
	}
	
	// improved
	public static TietoCardSetting getTietoCardSettingByName(String name, String alias) {		
		
		TietoCardSetting tietoCardSetting = new TietoCardSetting();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try	{
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM bf_tieto_card_setting WHERE name = ?");
			ps.setString(1, name); 
			rs = ps.executeQuery();
			if (rs.next()) {
				tietoCardSetting = new TietoCardSetting();
				
				tietoCardSetting.setCode(rs.getInt("code"));
				tietoCardSetting.setName(rs.getString("name"));
				tietoCardSetting.setBin(rs.getString("bin"));
				tietoCardSetting.setRisk_level(rs.getString("risk_level"));
				tietoCardSetting.setFinancial_conditions(rs.getString("financial_conditions"));
				tietoCardSetting.setMinimum_balance(rs.getBigDecimal("minimum_balance"));
				tietoCardSetting.setId_chip_app(rs.getBigDecimal("id_chip_app"));
				tietoCardSetting.setId_order_account(rs.getString("id_order_account"));
				tietoCardSetting.setGroup_c(rs.getString("groupc"));
				tietoCardSetting.setBank_c(rs.getString("bank_c"));
				tietoCardSetting.setCard_condition(rs.getString("card_condition"));
				tietoCardSetting.setDesign_id(rs.getBigDecimal("design_id"));
				tietoCardSetting.setRange_start(rs.getInt("range_start"));
				tietoCardSetting.setRange_end(rs.getInt("range_end"));
			}
		}
		catch (Exception e)	{
			LtLogger.getLogger().error("getTietoCardSetting from database error:\n" + CheckNull.getPstr(e));
		}
		finally	{
			ConnectionPool.close(c);
		}
		return tietoCardSetting;
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
			e.printStackTrace();
			return false;
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return true;
	}
	
	public static List<Action> getActions(int userid, String user_branch, String alias)
	{
		
		List<Action> list = new ArrayList<Action>();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection(alias);
		
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
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public static Res checkBfTietoTrans()
	{
		Res res = new Res();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from bf_tieto_trans");
			ps.executeQuery();
			
			res.setCode(1);
			res.setName("Успешно");
		}
		catch (SQLException e)
		{
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
	
	public static Res insertIntoBfTietoTrans(Bf_tieto_trans bf_tieto_trans)
	{
		Res res = new Res();
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("insert into bf_tieto_trans (branch, genid, tietoid, empid, trdate, state)" +
					" values (?, ?, ?, ?, sysdate, ?)");
			
			ps.setString(1, bf_tieto_trans.getBRANCH());
			ps.setLong(2, bf_tieto_trans.getGENID());
			ps.setLong(3, bf_tieto_trans.getTIETOID());
			ps.setString(4, bf_tieto_trans.getEMPID());
			ps.setInt(5, bf_tieto_trans.getSTATE());
			
			ps.executeQuery();
			c.commit();
			
			res.setCode(1);
			res.setName("Успешно");
		}
		catch (SQLException e)
		{
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
			PreparedStatement ps = c.prepareStatement("SELECT name FROM s_str where alpha_3 = ?");
			ps.setString(1, ISO3);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				res = rs.getString("name");
			}
			if (res == null) res = ISO3;
		}
		catch (SQLException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return res;
		
	}
	
	public static Agreement getCardAgreement(BigDecimal agre_nom, String alias, capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy)
	{
		Agreement rs = null;
		try
		{
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			
			KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
			mainAgreementInfo.setAGRE_NOM(agre_nom);
			
			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
			
			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
			
			issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
			
			rs = new Agreement();
			
		}
		catch (Exception e)
		{
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
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally {
			ConnectionPool.close(c);
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
	
	public static List<Bf_tieto_trans> getBf_tieto_trans(String account, String branch, String alias)
	{
		List<Bf_tieto_trans> list = new ArrayList<Bf_tieto_trans>();
		Bf_tieto_trans bf_tieto_trans;
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select g.branch, g.acc_co, s.trdate, g.summa/100 summa, s.state " +
														"from bf_tieto_trans s, general g " +
														"where s.genid = g.id " +
														"and g.acc_co = ? " +
														"and s.branch = ? "
				);
			ps.setString(1, account);
			ps.setString(2, branch);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				bf_tieto_trans = new Bf_tieto_trans();
				bf_tieto_trans.setBRANCH(rs.getString("branch"));
				bf_tieto_trans.setAccount(rs.getString("acc_co"));
				bf_tieto_trans.setTRDATE(new java.sql.Date(rs.getDate("trdate").getTime()));
				bf_tieto_trans.setSUMMA(rs.getBigDecimal("summa"));
				bf_tieto_trans.setSTATE(rs.getInt("state"));
				list.add(bf_tieto_trans);
			}
			
		}
		catch (Exception e)
		{
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
	
	public static List<RefData> getPayments4Tieto(String branch, String acc_co, String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select g.id data, " +
							"g.purpose || ' [ Summa ' || g.summa/100 || ' ' ||s.kod_b || ' ]' label " +
							"from GENERAL g, S_VAL s " +
						"where g.branch = '" + branch + "'" +
						"and g.acc_co = '" + acc_co + "'" +
						"and s.kod = g.currency " +
						"and s.kod_b = 'UZS' " +
						"and not exists (select p.genid from bf_tieto_trans p " +
										" where g.branch = p.branch" +
										" and g.id = p.genid)  " +
							"order by g.d_date ";
		
		list = com.is.utils.RefDataService.getRefData(sql, alias);
		return list;
	}
	
	public static List<RefData> getTstopCauses(String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select t.couse data, t.name label from BF_CAPITAL_STOP_CAUSES t where t.couse in ('1','2','4','5') order by 1";
		//String sql = "select t.couse data, t.name label from BF_CAPITAL_STOP_CAUSES t where t.couse in ('1','2','3','4','5') order by 1";
		list = com.is.utils.RefDataService.getRefData(sql, alias);
		return list;
	}
	
	public static HashMap<String, String> getHTstopCauses(String alias)
	{
		
		System.out.println("EMPC_BANK_C =>" + EMPC_BANK_C);
		
		HashMap<String, String> _tstopCauses = new HashMap<String, String>();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			String sql = "select t.couse data, t.name label from BF_CAPITAL_STOP_CAUSES t";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
				_tstopCauses.put(rs.getString("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
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
			PreparedStatement ps = c.prepareStatement("select t.data from BF_TIETO_TRANS_STATES t where t.value = ?");
			ps.setInt(1, State);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				StateDesc = rs.getString("data");
			}
		}
		catch (SQLException e)
		{
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
	
	
	
	
	
	
	
	
	
	
	
	// 13.02.2017
	
	public static Res openCard(String alias, CardApproval cardApproval, Boolean smart) {
		
		Res result = new Res();
		
		IssuingPortProxy issuingPortProxy = null;
		
		try	{
			issuingPortProxy = new capitalBank.IssuingWS.IssuingPortProxy(
					ConnectionPool.getValue("EMPC_TIETO_HOST", alias),
					ConnectionPool.getValue("EMPC_TIETO_HOST_USERNAME", alias),
					ConnectionPool.getValue("EMPC_TIETO_HOST_PASSWORD", alias)
					);
		}
		catch (Exception e)	{
			result.setCode(-1);
			result.setName("IssuingPortProxy error" + CheckNull.getPstr(e));
			return result;
		}
		
		
		String newCardAccount = cardApproval.getNew_card_account();
		Tclient tClient = getTclient_by_id(cardApproval.getT_user_id(), issuingPortProxy, alias);
		String branch = cardApproval.getBranch();		


		if(!(newCardAccount.substring(0, 5)).equals(Constants.ACC_22618)) {
			result.setCode(-1);
			result.setName("Неверный счет карты: " + newCardAccount);
			return result;
		}
		
		Calendar calendar = Calendar.getInstance();
		String cardName = "";
		BigDecimal agreNom = null;
		boolean agreNomExists = false;
		Range range = null;
		
		Tclient tietoClient = tClient;
		TietoCardSetting tietoCardSetting = TclientService.getTietoCardSettingByName(cardApproval.getCard_type(), alias);
		List<AccInfo> accInfoList = null;
		
		String Contract = "V" + tietoCardSetting.getName().substring(0, 1) + branch + getNextCardNumber(alias);
		
		// newAgreement
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		
		RowType_AgreementHolder agreementInfoHolder = null;
		RowType_Agreement agreementInfo = new RowType_Agreement();
		
		ListType_AccountInfoHolder accountsListInfoHolder = null;
		ListType_AccountInfo accountsListInfo = new ListType_AccountInfo();
			RowType_AccountInfo[] rowAccountInfoArray = null;
			RowType_AccountInfo rowAccountInfo = null;			
				RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
				RowType_AccountInfo_Additional additional_info = new RowType_AccountInfo_Additional();
		
		ListType_CardInfoHolder cardsListInfoHolder = null;
		ListType_CardInfo cardsListInfo = new ListType_CardInfo();
			RowType_CardInfo[] rowCardInfoArray = null;
			RowType_CardInfo rowCardInfo = new RowType_CardInfo();
				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				RowType_CardInfo_EMV_Data EMV_Data = new RowType_CardInfo_EMV_Data();			
		
		// addInfo4Agreement
		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		
		OperationResponseInfo responseInfo = null;
		
		
		// additional card inspection
		range = CustomerService.getRange(alias, tietoCardSetting.getId_order_account());
		
		accInfoList = TclientService.getAccInfo(tClient, alias, issuingPortProxy, smart);
		for (int i = 0; i < accInfoList.size(); i++) {
			
			if(newCardAccount.equals(accInfoList.get(i).getCard_acct())) {
			
				for (int j = 0; j < accInfoList.get(i).getCardlist().size(); j++) {
					
					CardInfo cardInfo = accInfoList.get(i).getCardlist().get(j);
					
					if (cardInfo.getBank_account_status().equals(Constants.BANK_ACCOUNT_STATUS)) {
						
						agreNom = BigDecimal.valueOf(Long.parseLong(cardInfo.getAGREEMENT_KEY()));
						agreNomExists = true;
						break;
					}
				}
			}
		}
		
		try {
			// OperationConnectionInfo
			connectionInfo.setBANK_C(tietoCardSetting.getBank_c());
			connectionInfo.setGROUPC(tietoCardSetting.getGroup_c());
			
			// RowType_AgreementHolder
			agreementInfo.setBINCOD(tietoCardSetting.getBin());
			agreementInfo.setBANK_CODE(EMPC_BANK_C);			
			agreementInfo.setBRANCH(branch);
			agreementInfo.setCITY(CheckNull.isEmpty(tietoClient.getR_city()) ? "UZB" : tietoClient.getR_city());			
			agreementInfo.setPRODUCT(tietoCardSetting.getFinancial_conditions());			
			agreementInfo.setREP_LANG(Constants.AGREEMENT_REP_LANG);			
			agreementInfo.setRISK_LEVEL(tietoCardSetting.getRisk_level());			
			agreementInfo.setSTREET(CheckNull.isEmpty(tietoClient.getR_street()) ? "STREET" : tietoClient.getR_street());			
			agreementInfo.setSTATUS(Constants.AGREEMENT_STATUS);
			agreementInfo.setCONTRACT(Contract);			
			agreementInfo.setENROLLED(calendar);
			agreementInfo.setDISTRIB_MODE(Constants.AGREEMENT_DISTRIB_MODE);
			agreementInfo.setCLIENT(tietoClient.getClient());
			agreementInfo.setCOUNTRY(CheckNull.isEmpty(tietoClient.getR_cntry()) ? "UZB" : tietoClient.getR_cntry());
			
			agreementInfoHolder = new RowType_AgreementHolder(agreementInfo);
			
			
			// ListType_AccountInfoHolder
			base_info.setCARD_ACCT(newCardAccount);
			base_info.setC_ACCNT_TYPE(Constants.AGREEMENT_C_ACCNT_TYPE);
			base_info.setCCY(Constants.AGREEMENT_CCY);
			base_info.setSTAT_CHANGE(Constants.AGREEMENT_STAT_CHANGE);			
			base_info.setMIN_BAL(Constants.AGREEMENT_MIN_BAL);			
			base_info.setACC_PRTY(Constants.AGREEMENT_ACC_PRTY);
			base_info.setCOND_SET(tietoCardSetting.getFinancial_conditions());
			base_info.setCYCLE(Constants.AGREEMENT_CYCLE);
			base_info.setCRD(Constants.AGREEMENT_CRD);
			base_info.setNON_REDUCE_BAL(tietoCardSetting.getMinimum_balance());
			base_info.setSTATUS(Constants.AGREEMENT_BASE_INFO_STATUS);
			base_info.setLIM_INTR(Constants.AGREEMENT_LIM_INTR);
			
			
			rowAccountInfo = new RowType_AccountInfo(base_info, additional_info);
			rowAccountInfoArray = new RowType_AccountInfo[] { 
					rowAccountInfo 
			};
			accountsListInfo.setRow(rowAccountInfoArray);
			
			accountsListInfoHolder = new ListType_AccountInfoHolder(accountsListInfo);
			
			
			// ListType_CardInfoHolder
			logicalCard.setCOND_SET(tietoCardSetting.getCard_condition());
			logicalCard.setRISK_LEVEL(tietoCardSetting.getRisk_level());
			logicalCard.setBASE_SUPP(Constants.AGREEMENT_BASE_SUPP);
			logicalCard.setF_NAMES(tietoClient.getF_names());
			logicalCard.setSURNAME(tietoClient.getSurname());
			logicalCard.setCL_ROLE(null);
			/*
			 * Добавить в БД
			 * RangeId для Electron = 2 
			 * Classic, Gold, Business, Platinum, Infinity = 1
			 * Travel = 4
			 * Smart Money открываются внутри Web Service
			 * */
			if(tietoCardSetting.getFinancial_conditions().equals("004")) {
				logicalCard.setRANGE_ID(BigDecimal.valueOf(2));
			}
			if(tietoCardSetting.getFinancial_conditions().equals("011")) {
				logicalCard.setRANGE_ID(BigDecimal.valueOf(4));
			}
			else {
				logicalCard.setRANGE_ID(BigDecimal.valueOf(1));
			}
			
			if(cardApproval.getHolder_name() == null || cardApproval.getHolder_name().equals("")) {
				cardName = tietoClient.getF_names() + " " + tietoClient.getSurname();
				physicalCard.setCARD_NAME(
						cardName.length() <= Constants.MAX_LENGTH_OF_CARD_NAME 
						? cardName 
						: (tietoClient.getSurname() + " " + (tietoClient.getF_names()).substring(0, 1).toUpperCase())
				);
			}
			else {
				physicalCard.setCARD_NAME(cardApproval.getHolder_name());
			}
			physicalCard.setSTATUS1(Constants.AGREEMENT_STATUS1);
			physicalCard.setDESIGN_ID(tietoCardSetting.getDesign_id());
			
			EMV_Data.setCHIP_APP_ID(tietoCardSetting.getId_chip_app());
			
			rowCardInfo.setLogicalCard(logicalCard);
			rowCardInfo.setPhysicalCard(physicalCard);
			rowCardInfo.setEMV_Data(EMV_Data);
			
			rowCardInfoArray = new RowType_CardInfo[] {
					rowCardInfo
			};			
			cardsListInfo.setRow(rowCardInfoArray);
			
			cardsListInfoHolder = new ListType_CardInfoHolder(cardsListInfo);
			
			
			if(!agreNomExists) {
				
				// Sending request newAgreement
				responseInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfoHolder, accountsListInfoHolder, cardsListInfoHolder);			
				if((responseInfo.getError_description() != null) || (responseInfo.getResponse_code().intValue() != 0)) {
					result.setCode(-1);
					result.setName("Card hasn't been opened in TIETO\n" + responseInfo.getError_description());
					return result;
				}	
				
			}
			else {
				mainAgreementInfo.setAGRE_NOM(agreNom);
				
				// Sending request addInfo4Agreement
				responseInfo = issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, new ListType_AccountInfoHolder(), cardsListInfoHolder);
				if((responseInfo.getError_description() != null) || (responseInfo.getResponse_code().intValue() != 0)) {
					result.setCode(-1);
					result.setName("Card hasn't been opened in TIETO\n" + responseInfo.getError_description());
					return result;
				}
			}

			
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error("Card hasn't been opened, error:\n" + CheckNull.getPstr(e));
			result.setCode(-1);
			result.setName("Card hasn't been opened, error:\n" + e.getMessage());
			return result;
		}
		
		result.setName("Карта открыта: " + tietoCardSetting.getName());

		
		return result;
	}
	
	
	
	public static CardCustomerSearch retrieveFunction(IssuingPortProxy issuingPortProxy, String cardNumber) {
		
		CardCustomerSearch info = new CardCustomerSearch();
		
		OperationConnectionInfoWA connectionInfo = new OperationConnectionInfoWA();
		ComplexType_RetrieveDataParameters parameters = new ComplexType_RetrieveDataParameters();
		
		RowType_FilterCondition[] filterCondition = {new RowType_FilterCondition(), new RowType_FilterCondition(), new RowType_FilterCondition()};
		
		OperationResponseInfoWAHolder responseInfoHolder;
		ComplexType_RetrieveDataDetailsHolder detailsHolder;
		
		OperationResponseInfoWA responseInfo = new OperationResponseInfoWA();
		ComplexType_RetrieveDataDetails details = new ComplexType_RetrieveDataDetails();
		
		
		
		connectionInfo.setBANK_C("01");
		connectionInfo.setGROUPC("01");
		
		parameters.setQueryName("LIST_CARDS_BY_ACCOUNT");
		parameters.setQueryVariant("10");
		
	
		filterCondition[0].setKey("CARD");
		filterCondition[0].setValue(cardNumber);
		filterCondition[0].setMatchingRule(BigInteger.valueOf(6));
		
		filterCondition[1].setKey("COND_SET");
		filterCondition[1].setValue("019");
		filterCondition[1].setMatchingRule(BigInteger.valueOf(0));
		
		filterCondition[2].setKey("CARD_NAME");
		filterCondition[2].setValue(null);
		filterCondition[2].setMatchingRule(BigInteger.valueOf(0));
		parameters.setFilterCondition(filterCondition);
		
		
		responseInfoHolder = new OperationResponseInfoWAHolder(responseInfo);
		detailsHolder = new ComplexType_RetrieveDataDetailsHolder(details);
		
		try {
			issuingPortProxy.retrieveData(connectionInfo, parameters, responseInfoHolder, detailsHolder);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(detailsHolder.value.getData() != null) {
		
			for(int i = 0; i < detailsHolder.value.getData().getRow(0).getItem().length; ++i) {
				if(detailsHolder.value.getData().getRow(0).getItem(i).getName().equals("CLIENT_ID")) {
					info.setTietoClientId(detailsHolder.value.getData().getRow(0).getItem(i).getValue());
				}
				if(detailsHolder.value.getData().getRow(0).getItem(i).getName().equals("CARD_ACCT")) {
					info.setCardAcct(detailsHolder.value.getData().getRow(0).getItem(i).getValue());
				}
				if(detailsHolder.value.getData().getRow(0).getItem(i).getName().equals("CARD")) {
					info.setCardPan(detailsHolder.value.getData().getRow(0).getItem(i).getValue());
				}
			}
		
		}
//		return clientId + cardAcct;
		return info;
	}
	
	
	
	
	public static void msSqlTest() {
		Connection c = null;
		int code = 0;
		String description = null;
		
		try	{
			c = ConnectionPool.getConnection_mssql();
			CallableStatement cstmt = c.prepareCall("{Call dbo.CreateClient2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"); 

			cstmt.setString(1, "00974");
			cstmt.setString(2, "22618840799373826444");
			cstmt.setString(3, "4278330037777777");
			cstmt.setString(4, "test");
			cstmt.setString(5, "test");
			cstmt.setString(6, "test");
			cstmt.setString(7, "+998991234567");
			cstmt.setString(8, "1996-11-01");
			cstmt.setString(9, "Tashkent");
			cstmt.setString(10, "Tashkent");
			cstmt.setString(11, "Tashkent");
			cstmt.setString(12, "AA");
			cstmt.setString(13, "7777555");
			cstmt.setString(14, "Tashkent");
			cstmt.setString(15, "2012-01-01");
			cstmt.setString(16, "2012-12-31");
			cstmt.registerOutParameter(17, java.sql.Types.INTEGER);
			cstmt.registerOutParameter(18, java.sql.Types.VARCHAR);
			
			cstmt.execute();
			
			code = cstmt.getInt(17);
			description = cstmt.getString(18);
			

			if(code == 0) {
				c.commit();
			}

			
		}
		catch (Exception e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
			c = null;
		}
	}
	
	
	
	public static String realPan(IssuingPortProxy issuingPortProxy, String maskPan) {
		String realPan = null;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_GetRealCard_Request parameters = new RowType_GetRealCard_Request();
		
		OperationResponseInfo responseInfo = new OperationResponseInfo();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder(responseInfo);
		
		RowType_GetRealCard_Response details = new RowType_GetRealCard_Response();
		RowType_GetRealCard_ResponseHolder detailsHolder = new RowType_GetRealCard_ResponseHolder(details);
		
		
		connectionInfo.setBANK_C("01");
		connectionInfo.setGROUPC("01");
		parameters.setCARD(maskPan);
		
		try {
			issuingPortProxy.getRealCard(connectionInfo, parameters, responseInfoHolder, detailsHolder);
			
			if(responseInfoHolder.value.getError_description() != null) {
				ISLogger.getLogger().error(responseInfoHolder.value.getError_description());
			}
			else {
				realPan = detailsHolder.value.getRCARD();
			}
		} catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}		
		
		return realPan;
	}
	
	
	private static String getNextCardNumber(String branch) {
		
		String number = null;
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			c = ConnectionPool.getConnection(branch);
			st = c.createStatement();
			rs = st.executeQuery("select seq_bf_tieto_number_of_cards.nextval from dual");
			
			if(rs.next()) {
				number = rs.getString("NEXTVAL");
			}
		} 
		catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));			
		}		
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			try {
				rs.close();
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			try {
				c.close();
			} catch (SQLException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		}
		
		return number;
	}
	
}
