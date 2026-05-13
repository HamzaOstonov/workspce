// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

import globus.issuing_v_01_02_xsd.ItemType_Generic;
import globus.issuing_v_01_02_xsd.KeyType_Agreement;
import globus.issuing_v_01_02_xsd.ListType_AccountInfo;
import globus.issuing_v_01_02_xsd.ListType_CardInfo;
import globus.issuing_v_01_02_xsd.ListType_Generic;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_Agreement;
import globus.issuing_v_01_02_xsd.RowType_CardInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_EditCustomer_Request;
import globus.issuing_v_01_02_xsd.RowType_Generic;
import globus.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import globus.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import globus.issuing_v_01_02_xsd.RowType_ListCustomers_Request;
import globus.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request;
import globus.issuing_v_01_02_xsd.RowType_ReplaceCard;
import globus.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;

import java.util.Calendar;
import java.util.Map;
import java.rmi.RemoteException;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.RowType_GetRealCard_Request;

import globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.db2.jcc.am.in;
import com.is.utils.RefDataService;
import globus.IssuingWS.IssuingPortProxy;

//import com.is.mailer.users.UserActionsLog;
import com.is.SwiftBuffer.SwiftBuffer;
import com.is.account.AccountService.actions_for_acc;
import com.is.comjpayment.ComJpayment;
import com.is.kernel.parameter.Parameters;
import com.is.search_clients.domain.Details;
import com.is.tieto_globuz.accounting_HUMO.TransactionService;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService;
import com.is.tieto_globuz.tieto.AccInfo;
import com.is.tieto_globuz.tieto.TclientService;
import com.is.utils.FilterField;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.is.utils.Res;
import java.sql.SQLException;
import com.is.LtLogger;
import globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;

import java.sql.Date;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import com.is.tieto_globuz.tieto.Tclient;
import globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import com.is.utils.RefData;
import java.util.List;
import com.is.tieto_globuz.tieto.CardInfo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis2.transport.http.RESTRequestEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.record.formula.functions.True;
import org.python.antlr.PythonParser.and_expr_return;
import org.python.antlr.PythonParser.else_clause_return;
import org.python.antlr.PythonParser.for_stmt_return;
import org.python.antlr.PythonParser.return_stmt_return;
import org.python.google.common.base.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;

import com.is.tieto_globuz.EmpcSettings;
import com.is.tieto_globuz.Utils;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.ConnectionPool;
//import com.rabbitmq.client.AMQP.Confirm.Select;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class CustomerService {

	private static String psql1;
	private static String psql2;
	private static String msql;
	private static String msql2;
	private CustomerViewCtrl cvc;
	private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	static {
		CustomerService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
		CustomerService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
		CustomerService.msql = "SELECT * FROM ";
		CustomerService.msql2 = "v_bf_humo_customer";
	}

	public CustomerService() {
		this.cvc = new CustomerViewCtrl();
	}

	public static Long getCardSmsPhoneNumber(final String card) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long phoneNumber = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select phone from humo_card_sms_state where card = ?");
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
		ISLogger.getLogger().error("select phone: " + phoneNumber);
		return phoneNumber;
	}

	public static void updateStateCard(final CardInfo cardInfo) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set stop_cause = ?, status1 = ?, status2 = ? where card = ? and client = ?");
			ps.setString(1, cardInfo.getSTOP_CAUSE());
			ps.setString(2, cardInfo.getSTATUS());
			ps.setString(3, cardInfo.getSTATUS2());
			ps.setString(4, cardInfo.getCARD());
			ps.setString(5, cardInfo.getCLIENT_ID());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			Utils.rollback(c);
			return;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Utils.close(ps);
		ConnectionPool.close(c);
	}

	public static void saveCardSmsState(final String card, String phone, final int state, final Connection c) throws Exception {
		phone = phone.replace("+", "");
		ISLogger.getLogger().error("phone state: " + state);
		final int cnt = getCntSavedCardSmsState(card, c);
		if (cnt == 0) {
			insertCardSmsState(card, phone, state, c);
		} else {
			updateCardSmsState(card, phone, state, c);
		}
	}

	private static void updateCardSmsState(final String card, String phone, final int state, final Connection c) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update humo_card_sms_state set phone = ?, state = ? where card = ?");
			ps.setString(1, phone);
			ps.setInt(2, state);
			ps.setString(3, card);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
		Utils.close(ps);
	}

	private static void insertCardSmsState(final String card, final String phone, final int state, final Connection c) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into humo_card_sms_state (card, phone, state) values (?,?,?)");
			ps.setString(1, card);
			ps.setString(2, phone);
			ps.setInt(3, state);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
		Utils.close(ps);
	}

	private static int getCntSavedCardSmsState(final String card, final Connection c) throws Exception {
		int cnt = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select count(*) from humo_card_sms_state where card = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		Utils.close(rs);
		Utils.close(ps);
		return cnt;
	}

	public static List<RefData> getProduct(final String branch) {
		return (List<RefData>) Utils.getRefData("select code data, name label from BF_globuz_CARD_SETTING where flag_user_view='1' and FLAG_CLIENT_TYPE='P' order by code", branch);
	}

	public static List<RefData> getClnCat(final String branch) {
		return (List<RefData>) Utils.getRefData("select id data, client_type_name label from ss_empc_client_types where client_subject = 'P'", branch);
	}

	public static List<RefData> getCountry(final String alias) {
		return (List<RefData>) Utils.getRefData("select s.code_str data, s.name label from S_STR s order by 2", alias);
	}

	public static List<RefData> getDistr(final String alias) {
		return (List<RefData>) Utils.getRefData("select distr data, distr_name label from s_distr order by 2", alias);
	}

	public static List<RefData> getRegion(final String alias) {
		return (List<RefData>) Utils.getRefData("select region_id data, region_nam label from s_region order by 2", alias);
	}

	public static List<RefData> getTax(final String branch) {
		return (List<RefData>) Utils.getRefData("select gni_id data, name_gni label from s_gni where act <> 'Z' order by 2", branch);
	}

	public static List<RefData> getType_document(final String alias) {
		return (List<RefData>) Utils.getRefData("select s.code_cert data, s.name_cert label from S_CERTIFICATE s order by 2", alias);
	}
	
	public static List<RefData> getCustomerId(final String alias, final String branch) {
		return (List<RefData>) Utils.getRefData("select customer_id data, customer_id ||' - '|| customer_name label from humo_accrual_acc where branch = '"+branch+"'", alias);
	}

	// ////////////////////////АГРО code 4: физические карты, code 5: кредитные
	// карты, code 7: пенсионные карты, code 8: кобрендовые
	// карты//////////////////
	/////////ИПАК//////////////////4 6/////////////////
	///////ИНФИН///////////////////4 5/////////////////
	public static List<RefData> getCardTypes(final String alias, final String bankC) {
		String sql1 = "select distinct t.code data, t.description label from SS_HUMO_TYPE_OF_CARD t where t.code in (";
		String sql2 = "";
		if (bankC.equals("03")){
			sql2 = "'4', '5', '7', '8', '9', '11')";
		}else if(bankC.equals("17")){
			sql2 = "'4', '6')";
		}else{
			sql2 = "'4', '5')";
		}
		return (List<RefData>) RefDataService.getRefData(sql1+sql2, alias);
	}

	public static Res insertAgreement(final OperationConnectionInfo connectionInfo, final RowType_AgreementHolder agreementInfo, final Connection c) throws Exception {
		Res res = new Res();
	    PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
			ps.setString(1, agreementInfo.value.getAGRE_NOM() == null ? "0"
					: agreementInfo.value.getAGRE_NOM().toString());
			ps.setString(2, agreementInfo.value.getCLIENT());
			ps.setString(3, connectionInfo.getGROUPC());
			ps.setString(4, agreementInfo.value.getBINCOD());
			ps.setString(5, agreementInfo.value.getBANK_CODE());
			ps.setString(6, agreementInfo.value.getBRANCH());
			ps.setString(7, connectionInfo.getBANK_C());
			ps.setString(8, agreementInfo.value.getPRODUCT());
			ps.execute();
		}catch (Exception e) {
            ISLogger.getLogger().error("insertAgreement error ", e);
            res.setCode(-1);
            res.setName(e.getLocalizedMessage());
            return res;
        } finally {
			Utils.close(ps);
		}
		return res;
	}

	public static int getCountCards(final Tclient tietocl, final GlobuzAccount customer) {
		int cnt = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println("client: " + tietocl.getClient());
			System.out.println("id : " + customer.getId());
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from bf_empc_accounts a where a.client = ? and a.tranz_acct = ?");
			ps.setString(1, tietocl.getClient());
			ps.setString(2, customer.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("COUNT CARDS: " + cnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return cnt;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Utils.close(rs);
		Utils.close(ps);
		ConnectionPool.close(c);
		return cnt;
	}

	public static Res insertClient(final RowType_CustomerHolder customerInfo, String alias) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		Res res = new Res();
		try {
			c = ConnectionPool.getConnection();
            Statement st = c.createStatement();
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
			ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS,R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, customerInfo.value.getCLIENT());
			ps.setString(2, customerInfo.value.getF_NAMES());
			ps.setString(3, customerInfo.value.getCL_TYPE());
			ps.setString(4, customerInfo.value.getCLIENT_B());
			ps.setString(5, customerInfo.value.getSURNAME());
			ps.setString(6, customerInfo.value.getM_NAME());
			ps.setDate(7, (customerInfo.value.getDOC_SINCE().getTime() == null) ? null
					: new Date(customerInfo.value.getDOC_SINCE().getTime().getTime()));
			ps.setDate(8, (customerInfo.value.getB_DATE().getTime() == null) ? null
					: new Date(customerInfo.value.getB_DATE().getTime().getTime()));
			ps.setString(9, customerInfo.value.getRESIDENT());
			ps.setString(10, customerInfo.value.getSTATUS());
			ps.setString(11, customerInfo.value.getSEX());
			ps.setString(12, customerInfo.value.getSERIAL_NO());
			ps.setString(13, customerInfo.value.getID_CARD());
			ps.setString(14, customerInfo.value.getR_CITY());
			ps.setString(15, customerInfo.value.getR_STREET());
			ps.setString(16, customerInfo.value.getR_E_MAILS());
			ps.setString(17, customerInfo.value.getR_MOB_PHONE());
			ps.setString(18, customerInfo.value.getR_PHONE());
			ps.setString(19, customerInfo.value.getR_CNTRY());
			ps.setString(20, customerInfo.value.getISSUED_BY());
			ps.setString(21, customerInfo.value.getPERSON_CODE());
			ps.setString(22, customerInfo.value.getDOC_TYPE());
			ps.setDate(23, (customerInfo.value.getREC_DATE().getTime() == null) ? null
					: new Date(customerInfo.value.getREC_DATE().getTime().getTime()));
			ps.execute();
			c.commit();
			res.setCode(0);
			res.setName("Успешно!");
		}catch (Exception e) {
			ISLogger.getLogger().error("insertClient error ", e);
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		}finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res insertClient(final RowType_CustomerHolder customerInfo, final Connection c, String alias) throws Exception {
		PreparedStatement ps = null;
		Res res = new Res();
		try {
            Statement st = c.createStatement();
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
			ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS,R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, customerInfo.value.getCLIENT());
			ps.setString(2, customerInfo.value.getF_NAMES());
			ps.setString(3, customerInfo.value.getCL_TYPE());
			ps.setString(4, customerInfo.value.getCLIENT_B());
			ps.setString(5, customerInfo.value.getSURNAME());
			ps.setString(6, customerInfo.value.getM_NAME());
			ps.setDate(7, (customerInfo.value.getDOC_SINCE().getTime() == null) ? null
					: new Date(customerInfo.value.getDOC_SINCE().getTime().getTime()));
			ps.setDate(8, (customerInfo.value.getB_DATE().getTime() == null) ? null
					: new Date(customerInfo.value.getB_DATE().getTime().getTime()));
			ps.setString(9, customerInfo.value.getRESIDENT());
			ps.setString(10, customerInfo.value.getSTATUS());
			ps.setString(11, customerInfo.value.getSEX());
			ps.setString(12, customerInfo.value.getSERIAL_NO());
			ps.setString(13, customerInfo.value.getID_CARD());
			ps.setString(14, customerInfo.value.getR_CITY());
			ps.setString(15, customerInfo.value.getR_STREET());
			ps.setString(16, customerInfo.value.getR_E_MAILS());
			ps.setString(17, customerInfo.value.getR_MOB_PHONE());
			ps.setString(18, customerInfo.value.getR_PHONE());
			ps.setString(19, customerInfo.value.getR_CNTRY());
			ps.setString(20, customerInfo.value.getISSUED_BY());
			ps.setString(21, customerInfo.value.getPERSON_CODE());
			ps.setString(22, customerInfo.value.getDOC_TYPE());
			ps.setDate(23, (customerInfo.value.getREC_DATE().getTime() == null) ? null
					: new Date(customerInfo.value.getREC_DATE().getTime().getTime()));
			ps.execute();
			res.setCode(0);
			res.setName("Успешно!");
		}catch (Exception e) {
			ISLogger.getLogger().error("insertClient error ", e);
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		}finally {
			Utils.close(ps);
		}
		return res;
	}

	public static void updateClient(final RowType_EditCustomer_Request customerInfo) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bf_EMPC_clients set F_NAMES= ?, CL_TYPE= ?, CLIENT_B= ?, SURNAME= ?, M_NAME= ?, DOC_SINCE= ?, B_DATE= ?, RESIDENT= ?, STATUS= ?, SEX= ?, SERIAL_NO= ?, ID_CARD= ?, R_CITY= ?, R_STREET= ?, R_E_MAILS= ?, R_MOB_PHONE= ?, R_PHONE= ?, R_CNTRY= ?, ISSUED_BY= ?, PERSON_CODE= ?, DOC_TYPE= ?, REC_DATE = ? where client = ?");
			ps.setString(1, customerInfo.getF_NAMES());
			ps.setString(2, customerInfo.getCL_TYPE());
			ps.setString(3, customerInfo.getCLIENT_B());
			ps.setString(4, customerInfo.getSURNAME());
			ps.setString(5, customerInfo.getM_NAME());
			ps.setDate(6, (customerInfo.getDOC_SINCE().getTime() == null) ? null
					: new Date(customerInfo.getDOC_SINCE().getTime().getTime()));
			ps.setDate(7, (customerInfo.getB_DATE().getTime() == null) ? null
					: new Date(customerInfo.getB_DATE().getTime().getTime()));
			ps.setString(8, customerInfo.getRESIDENT());
			ps.setString(9, customerInfo.getSTATUS());
			ps.setString(10, customerInfo.getSEX());
			ps.setString(11, customerInfo.getSERIAL_NO());
			ps.setString(12, customerInfo.getID_CARD());
			ps.setString(13, customerInfo.getR_CITY());
			ps.setString(14, customerInfo.getR_STREET());
			ps.setString(15, customerInfo.getR_E_MAILS());
			ps.setString(16, customerInfo.getR_MOB_PHONE());
			ps.setString(17, customerInfo.getR_PHONE());
			ps.setString(18, customerInfo.getR_CNTRY());
			ps.setString(19, customerInfo.getISSUED_BY());
			ps.setString(20, customerInfo.getPERSON_CODE());
			ps.setString(21, customerInfo.getDOC_TYPE());
			ps.setDate(22, (customerInfo.getREC_DATE().getTime() == null) ? null
					: new Date(customerInfo.getREC_DATE().getTime().getTime()));
			ps.setString(23, customerInfo.getCLIENT());
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error("CustomerService.updateClient error: "
					+ e.getLocalizedMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Utils.close(ps);
	}

	public static Res insertCards(final ListType_CardInfoHolder cardsListInfo, final ListType_AccountInfoHolder accInfo, final String client_b, final String branch, final Connection c) throws Exception {
		Res res = new Res();
	    PreparedStatement ps = null;
		final java.util.Date exp1 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1() == null) ? null
				: cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTime();
		final java.util.Date exp2 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2() == null) ? null
				: cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2().getTime();
		try {
			ps = c.prepareStatement("insert into humo_cards (client, client_b, branch, card, status1, status2, expiry1, expirity2, renew, renew_date, card_name, mc_name, m_name, stop_cause, renewed_card, design_id, instant, card_acct, tranz_acct, account_no) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard().getCLIENT());
			ps.setString(2, client_b);
			ps.setString(3, branch);
			ps.setString(4, cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
			ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard().getSTATUS1());
			ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard().getSTATUS2());
			ps.setDate(7, (exp1 == null) ? null : new Date(exp1.getTime()));
			ps.setDate(8, (exp2 == null) ? null : new Date(exp2.getTime()));
			ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard().getRENEW());
			ps.setDate(10, (exp1 == null) ? null : new Date(exp1.getTime()));
			ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard().getCARD_NAME());
			ps.setString(12, cardsListInfo.value.getRow(0).getPhysicalCard().getMC_NAME());
			ps.setString(13, cardsListInfo.value.getRow(0).getLogicalCard().getM_NAME());
			ps.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard().getSTOP_CAUSE());
			ps.setString(15, cardsListInfo.value.getRow(0).getPhysicalCard().getRENEWED_CARD());
			ps.setBigDecimal(16, cardsListInfo.value.getRow(0).getPhysicalCard().getDESIGN_ID());
			ps.setString(17, cardsListInfo.value.getRow(0).getPhysicalCard().getINSTANT());
			ps.setString(18, accInfo.value.getRow(0).getBase_info().getCARD_ACCT());
			ps.setString(19, accInfo.value.getRow(0).getBase_info().getTRANZ_ACCT());
			ps.setBigDecimal(20, accInfo.value.getRow(0).getBase_info().getACCOUNT_NO());
			ps.execute();
			res.setCode(0);
			res.setName("Успешно!");
		} catch (Exception e) {
			ISLogger.getLogger().error("insertCards error ", e);
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		} finally {
			Utils.close(ps);
		}
		return res;
	}

	public static Res insertAccounts(final ListType_AccountInfoHolder accountsListInfo, final String client, final Connection c, Date date) throws Exception {
		Res res = new Res();
	    PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values (?,?,?,?,?,?)");
			ps.setString(1, client);
			
			ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info().getACCOUNT_NO());
			ps.setString(3, accountsListInfo.value.getRow(0).getBase_info().getCARD_ACCT());
			ps.setString(4, accountsListInfo.value.getRow(0).getBase_info().getTRANZ_ACCT());
			if (!(accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY() == null)) {
				ps.setDate(5, (accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY().getTime() == null) ? date
						: new Date(accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY().getTime().getTime()));
			} else {
				ps.setDate(5, date);
			}
			if (!(accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE() == null)) {
				ps.setDate(6, (accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE().getTime() == null) ? date
						: new Date(accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE().getTime().getTime()));
			} else {
				ps.setDate(6, date);
			}
			ps.execute();
			res.setCode(0);
			res.setName("Успешно!");
		}catch (Exception e) {
            ISLogger.getLogger().error("insertAccounts error ", e);
            res.setCode(-1);
            res.setName(e.getLocalizedMessage());
            return res;
        } finally {
			Utils.close(ps);
		}
		return res;
	}

	public static link get_link_tieto(final String Tieto_id, final String Branch, final String alias) {
		link res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id,  t.cur_acc cur_acc   from bf_xumo_customers t where t.tieto_customer_id=? and t.branch=?");
			ps.setString(1, Tieto_id);
			ps.setString(2, Branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"), rs.getString("tieto_id"), rs.getString("bank_id"), rs.getString("cur_acc"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static Res getTieto_branch(final String branch, final String alias) {
		final Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select BF.GET_TIETO_BRANCH(?) res from dual");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				res.setCode(0);
				res.setName(rs.getString("res"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			res.setCode(-1);
			res.setName(e.getMessage());
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static link get_link_branch(final String Client_id, final String Branch, final String alias) {
		link res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.cur_acc cur_acc from bf_xumo_customers t where t.bank_customer_id=? and t.branch=?");
			ps.setString(1, Client_id);
			ps.setString(2, Branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"), rs.getString("tieto_id"), rs.getString("bank_id"), rs.getString("cur_acc"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static link get_link_tieto(final String Tieto_id, final String alias) {
		link res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.head_customer_id head_id, t.cur_acc cur_acc from bf_xumo_customers t where t.tieto_customer_id=?");
			ps.setString(1, Tieto_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"), rs.getString("tieto_id"), rs.getString("bank_id"), rs.getString("cur_acc"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static String get_alias_ho(final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String res = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select user_name from ss_dblink_branch t where t.branch = (select bs.VALUE from bf_sets bs where bs.id = 'HO')");
			rs = ps.executeQuery();
			rs.next();
			res = rs.getString("user_name");
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static List<Customer> getCustomer(final String pn, final String alias) {
		final List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer where p_passport_number=?");
			ps.setString(1, pn);
			rs = ps.executeQuery();
			while (rs.next()) {
                list.add(new Customer(rs.getLong("id"), rs.getString("branch"),
                        rs.getString("id_client"), rs.getString("name"),
                        rs.getString("code_country"), rs.getString("code_type"),
                        rs.getString("code_resident"), rs.getString("code_subject"),
                        rs.getInt("sign_registr"), rs.getString("code_form"),
                        rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("state"),
                        rs.getDate("p_birthday"), rs.getString("p_post_address"),
                        rs.getString("p_passport_type"), rs.getString("p_passport_serial"),
                        rs.getString("p_passport_number"),
                        rs.getString("p_passport_place_registration"),
                        rs.getDate("p_passport_date_registration"), rs.getString("p_code_tax_org"),
                        rs.getString("p_number_tax_registration"), rs.getString("p_code_bank"),
                        rs.getString("p_code_class_credit"), rs.getString("p_code_citizenship"),
                        rs.getString("p_birth_place"), rs.getString("p_code_capacity"),
                        rs.getDate("p_capacity_status_date"),
                        rs.getString("p_capacity_status_place"),
                        rs.getString("p_num_certif_capacity"), rs.getString("p_phone_home"),
                        rs.getString("p_phone_mobile"), rs.getString("p_email_address"),
                        rs.getString("p_pension_sertif_serial"), rs.getString("p_code_gender"),
                        rs.getString("p_code_nation"), rs.getString("p_code_birth_region"),
                        rs.getString("p_code_birth_distr"), rs.getString("p_type_document"),
                        rs.getDate("p_passport_date_expiration"),
                        rs.getString("p_code_adr_region"), rs.getString("p_code_adr_distr"),
                        rs.getString("p_inps"), rs.getString("p_family"),
                        rs.getString("p_first_name"), rs.getString("p_patronymic"),
                        rs.getString("pinfl")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return list;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return list;
	}

	public static Map<String, String> getListCard() {

		Map<String, String> hashMap = new HashMap<String, String>();
		Connection c = null;
		PreparedStatement psListCard = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			psListCard = c.prepareStatement("select k.client,k.card from humo_cards k where k.update_status is null and exists (select 'x' from (select c.account_no from humo_accno_tmp t, bf_empc_accounts a, humo_cards c where c.client= a.client and t.tranz_acct=a.tranz_acct) t where t.account_no=k.account_no)");

			rs = psListCard.executeQuery();
			while (rs.next()) {
				hashMap.put(rs.getString("card"), rs.getString("client"));

			}

		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			if (c != null) {
				ConnectionPool.close(c);
			}
			if (psListCard != null) {
				Utils.close(psListCard);
			}
			if (rs != null) {
				Utils.close(rs);
			}

		}

		return hashMap;
	}

	static CardStatus getCardStatus(String pseudoCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {

		CardStatus cardStatus = new CardStatus();
		String status1 = null;
		String status2 = null;
		String STOP_CAUSE = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = Utils.getExternalSession();
			// System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			RowType_QueryCardInfo_Request parameters = new RowType_QueryCardInfo_Request();
			ListType_GenericHolder details = new ListType_GenericHolder();
			parameters.setBANK_C(bankC);
			parameters.setGROUPC(groupC);
			parameters.setCARD(pseudoCard);

			proxy.queryCardInfo(connectionInfo, parameters, responseInfo, details);

			RowType_Generic row = details.value.getRow(0);

			for (ItemType_Generic i : row.getItem()) {
				if (i.getName().equals("STATUS1")) {
					status1 = i.getValue();
				}

				if (i.getName().equals("STATUS2")) {
					status2 = i.getValue();
				}
				if (i.getName().equals("STOP_CAUSE")) {
					STOP_CAUSE = i.getValue();
				}
			}

			cardStatus.setStatus1(status1);
			cardStatus.setStatus2(status2);
			cardStatus.setStop_cause(STOP_CAUSE);

		} catch (Exception e) {

			ISLogger.getLogger().error(e);

		}

		return cardStatus;

	}

	public static Res doAction(final String un, final String pw, final Customer customer, final int actionid, final int utv_actionid, final String alias, final Boolean selfBranch) {
		Res res = null;
		final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = get_alias_ho(alias);
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
			cs.setString(2, (customer.getDate_open() != null) ? bdf.format(customer.getDate_open())
					: null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(2, (customer.getDate_close() != null) ? bdf.format(customer.getDate_close())
					: null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getState())).toString());
			cs.execute();
			System.out.println("state = " + customer.getState());
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(2, (customer.getP_birthday() != null) ? bdf.format(customer.getP_birthday())
					: null);
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
			cs.setString(2, (customer.getP_passport_date_registration() != null) ? bdf.format(customer.getP_passport_date_registration())
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
			cs.setString(2, (customer.getP_capacity_status_date() != null) ? bdf.format(customer.getP_capacity_status_date())
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
			cs.setString(2, (customer.getP_passport_date_expiration() != null) ? bdf.format(customer.getP_passport_date_expiration())
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
			cs.setString(2, customer.getPinfl());
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
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
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

	public static Res doAction_utv(final String un, final String pw, final Customer customer, final int actionid, final String alias, final Boolean selfBranch) {
		Res res = null;
		final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = get_alias_ho(alias);
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
			cs.setString(2, (customer.getDate_open() != null) ? bdf.format(customer.getDate_open())
					: null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(2, (customer.getDate_close() != null) ? bdf.format(customer.getDate_close())
					: null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getState())).toString());
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(2, (customer.getP_birthday() != null) ? bdf.format(customer.getP_birthday())
					: null);
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
			cs.setString(2, (customer.getP_passport_date_registration() != null) ? bdf.format(customer.getP_passport_date_registration())
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
			cs.setString(2, (customer.getP_capacity_status_date() != null) ? bdf.format(customer.getP_capacity_status_date())
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
			cs.setString(2, (customer.getP_passport_date_expiration() != null) ? bdf.format(customer.getP_passport_date_expiration())
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
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic());
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
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
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

	public static Res doAction_open_closed(final String un, final String pw, final String client_id, final int actionid, final String branch, final String alias, final String self_branch) {
		Res res = null;
		final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = get_alias_ho(alias);
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		try {
			if (halias.compareTo(alias) == 0
					&& self_branch.compareTo(ConnectionPool.getValue("HO", alias)) != 0) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			final PreparedStatement inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, 12);
			final Customer customer = getCustomerById(client_id, branch, alias);
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
			cs.setString(2, (customer.getDate_open() != null) ? bdf.format(customer.getDate_open())
					: null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(2, (customer.getDate_close() != null) ? bdf.format(customer.getDate_close())
					: null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, new StringBuilder(String.valueOf(customer.getState())).toString());
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(2, (customer.getP_birthday() != null) ? bdf.format(customer.getP_birthday())
					: null);
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
			cs.setString(2, (customer.getP_passport_date_registration() != null) ? bdf.format(customer.getP_passport_date_registration())
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
			cs.setString(2, (customer.getP_capacity_status_date() != null) ? bdf.format(customer.getP_capacity_status_date())
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
			cs.setString(2, (customer.getP_passport_date_expiration() != null) ? bdf.format(customer.getP_passport_date_expiration())
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
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic());
			cs.execute();
			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();
			c.commit();
			ccs.execute();
			res = new Res(0, ccs.getString(1));
		} catch (Exception e2) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e1));
				e1.printStackTrace();
			}
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			res = new Res(-1, e2.getMessage());
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

	public static String doAction(final String un, final String pw, final String branch, final String id, final int actionid, final String alias, final Boolean selfBranch) {
		String res = "";
		final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		final String halias = get_alias_ho(alias);
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
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
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
					+ "p_birthday=?", (Object) new Date(filter.getP_birthday().getTime())));
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
					+ "p_passport_serial=?", (Object) filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_number=?", (Object) filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_place_registration=?", (Object) filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_registration=?", (Object) filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_tax_org=?", (Object) filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_number_tax_registration=?", (Object) filter.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_bank=?", (Object) filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_class_credit=?", (Object) filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_citizenship=?", (Object) filter.getP_code_citizenship()));
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
					+ "p_capacity_status_date=?", (Object) filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_capacity_status_place=?", (Object) filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_num_certif_capacity=?", (Object) filter.getP_num_certif_capacity()));
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
					+ "p_pension_sertif_serial=?", (Object) filter.getP_pension_sertif_serial()));
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
					+ "p_code_birth_region=?", (Object) filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_birth_distr=?", (Object) filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_type_document=?", (Object) filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_expiration=?", (Object) filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_region=?", (Object) filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_distr=?", (Object) filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_inps=?", (Object) filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like '%"
					+ filter.getP_family().toUpperCase()
					+ "%' or upper(p_family) like ?)", (Object) ("%"
					+ filter.getP_family().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like '%"
					+ filter.getP_first_name().toUpperCase()
					+ "%' or upper(p_first_name) like ?)", (Object) ("%"
					+ filter.getP_first_name().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like '%"
					+ filter.getP_patronymic().toUpperCase()
					+ "%' or upper(p_patronymic) like ?)", (Object) ("%"
					+ filter.getP_patronymic().toUpperCase() + "%")));
		}
		String notNull = "";
		if (!CheckNull.isEmpty(filter.getTietoIdIsNotNull())) {
			notNull = " and tieto_customer_id is not null ";
		}
		if (!CheckNull.isEmpty(filter.getTieto_customer_id())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "tieto_customer_id=?", (Object) filter.getTieto_customer_id()));
		}
		flfields.add(new FilterField(String.valueOf(getCond(flfields))
				+ "rownum<? " + notNull, (Object) 1001));
		if (!(filter.getCard() == null)) {
			if (!filter.getCard().matches("[0-9]+")) {
				if (filter.getCard().contains("%")) {
					flfields.add(new FilterField(String.valueOf(getCond(flfields))
							+ "id_client in (select client_b from humo_cards c where c.branch = "
							+ filter.getBranch() + " and c.card like ?)", filter.getCard()));
					// flfields.add(new
					// FilterField(String.valueOf(getCond(flfields)) +
					// "branch in (select branch from humo_cards c where c.card like ?)",
					// filter.getCard()));
				} else {
					flfields.add(new FilterField(String.valueOf(getCond(flfields))
							+ "id_client in (select client_b from humo_cards c where c.branch = "
							+ filter.getBranch() + " and c.card = ?)", filter.getCard()));
					// flfields.add(new
					// FilterField(String.valueOf(getCond(flfields)) +
					// "branch in (select branch from humo_cards c where c.card = ?)",
					// filter.getCard()));
				}
			} else {
				flfields.add(new FilterField(String.valueOf(getCond(flfields))
						+ "id_client in (select client_b from humo_cards c where c.branch = "
						+ filter.getBranch() + " and c.real_card = ?)", filter.getCard()));
				// flfields.add(new
				// FilterField(String.valueOf(getCond(flfields)) +
				// "branch in (select branch from humo_cards c where c.real_card = ?)",
				// filter.getCard()));
			}
		}
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
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
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
                final Customer customer = new Customer(rs.getLong("id"), rs.getString("branch"),
                        rs.getString("id_client"), rs.getString("name"),
                        rs.getString("code_country"), rs.getString("code_type"),
                        rs.getString("code_resident"), rs.getString("code_subject"),
                        rs.getInt("sign_registr"), rs.getString("code_form"),
                        rs.getDate("date_open"), rs.getDate("date_close"), rs.getInt("state"),
                        rs.getDate("p_birthday"), rs.getString("p_post_address"),
                        rs.getString("p_passport_type"), rs.getString("p_passport_serial"),
                        rs.getString("p_passport_number"),
                        rs.getString("p_passport_place_registration"),
                        rs.getDate("p_passport_date_registration"), rs.getString("p_code_tax_org"),
                        rs.getString("p_number_tax_registration"), rs.getString("p_code_bank"),
                        rs.getString("p_code_class_credit"), rs.getString("p_code_citizenship"),
                        rs.getString("p_birth_place"), rs.getString("p_code_capacity"),
                        rs.getDate("p_capacity_status_date"),
                        rs.getString("p_capacity_status_place"),
                        rs.getString("p_num_certif_capacity"), rs.getString("p_phone_home"),
                        rs.getString("p_phone_mobile"), rs.getString("p_email_address"),
                        rs.getString("p_pension_sertif_serial"), rs.getString("p_code_gender"),
                        rs.getString("p_code_nation"), rs.getString("p_code_birth_region"),
                        rs.getString("p_code_birth_distr"), rs.getString("p_type_document"),
                        rs.getDate("p_passport_date_expiration"),
                        rs.getString("p_code_adr_region"), rs.getString("p_code_adr_distr"),
                        rs.getString("p_inps"), rs.getString("p_family"),
                        rs.getString("p_first_name"), rs.getString("p_patronymic"), rs.getString("pinfl"));
				customer.setTieto_customer_id(rs.getString("tieto_customer_id"));
				list.add(customer);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
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
				customer.setPinfl(rs.getString("pinfl"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
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

	public static Customer getCustomerById(final String customerId, final String alias) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = new Customer();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer WHERE id_client=?");
			ps.setString(1, customerId);
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
				customer.setPinfl(rs.getString("pinfl"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return customer;
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		try {
			rs.close();
			ps.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionPool.close(c);
		return customer;
	}
	
	public static void deleteBfXumoCustomers(String clientB, String branch, String alias) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        ISLogger.getLogger().error(
	                "deleteBfXumoCustomers bankClientId: "
	                        + clientB + "\nbranch: " + branch + "\nalias: " + alias);
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("delete bf_xumo_customers where bank_customer_id = ? and branch = ?");
            ps.setString(1, clientB);
            ps.setString(2, branch);
            ps.execute();
            c.commit();
        } catch (SQLException e) {
            ISLogger.getLogger().error("deleteBfXumoCustomers error: ", e);
            e.printStackTrace();
        }finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
	}

	public static boolean checkClient(final String clientId, final String branch, final String un, final String pwd, final String alias) throws SQLException {
		ISLogger.getLogger().error("checkClient");
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExists = false;
		try {

			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from bf_xumo_customers where bank_customer_id = ? and branch = ?");
			ps.setString(1, clientId);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("tieto_customer_id") == null) {
					String tietoCustomerId = checkClientExistenceInBfEmpcClients(clientId, branch, alias);
					if (tietoCustomerId == null) {
						ISLogger.getLogger().error("isExists null");
						ps = c.prepareStatement("delete bf_xumo_customers where bank_customer_id = ? and branch = ?");
						ps.setString(1, clientId);
						ps.setString(2, branch);
						ps.execute();
						isExists = false;
					} else {
						if (getAccount(branch, clientId).isEmpty()) {
							updateBfXumoCustomers(tietoCustomerId, clientId, branch, un, pwd, alias);
							isExists = true;
						} else {
							updateBfXumoCustomers(tietoCustomerId, clientId, branch, alias, getAccount(branch, clientId));
							isExists = true;
						}
					}
				} else {
					ISLogger.getLogger().error("isExists true");
					isExists = true;
				}

			} else {
				String tietoCustomerId = checkClientExistenceInBfEmpcClients(clientId, branch, alias);
				if (tietoCustomerId == null) {
					isExists = false;
				} else {
					if (getAccount(branch, clientId).isEmpty()) {
						create(new TietoCustomer(branch, clientId, tietoCustomerId), un, pwd, alias);
						isExists = true;
					} else {
						create(new TietoCustomer(branch, clientId, tietoCustomerId), getAccount(branch, clientId), alias);
						isExists = true;
					}
				}
			}
			c.commit();
		} catch (Exception e) {
			c.rollback();
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return isExists;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}

		return isExists;
	}

	public static void deleteTable(String branch, String filename) throws SQLException {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static Customer getCustomerById(final String customerId, final String branch, final String alias) {
		Customer customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		try {
			c = ConnectionPool.getConnection(alias);
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			ps = c.prepareStatement("SELECT * FROM v_bf_humo_customer WHERE id_client=? and branch = ?");
			ps.setString(1, customerId);
			ps.setString(2, branch);
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
				customer.setP_family((rs.getString("p_family") == null) ? "q"
						: rs.getString("p_family"));
				customer.setP_first_name((rs.getString("p_first_name") == null) ? "q"
						: rs.getString("p_first_name"));
				customer.setP_patronymic((rs.getString("p_patronymic") == null) ? "q"
						: rs.getString("p_patronymic"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
				customer.setPinfl(rs.getString("pinfl"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return customer;
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		try {
			rs.close();
			ps.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
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
				customer.setPinfl(rs.getString("pinfl"));
				customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return customer;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return customer;
	}

	public static Res removeT\u0441(final String un, final String pw, final int id, final String alias) {
		Res res = null;
		Connection c = null;
		PreparedStatement ps = null;
		final String halias = get_alias_ho(alias);
		try {
			if (halias.compareTo(alias) == 0) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("DELETE FROM bf_xumo_customers where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			c.commit();
			res = new Res(0, "");
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(-1, e.getMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static void Tc(final TietoCustomer tietocustomer, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bf_xumo_customers SET bank_customer_id=? WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, tietocustomer.getBankCustomerId());
			ps.setString(3, tietocustomer.getBranch());
			ps.setString(4, tietocustomer.getTietoCustomerId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
	}

    public static Res create(final TietoCustomer tietocustomer, final String un,
            final String pwd, final String alias) {
    	Res res = new Res();
        Connection c = null;
        PreparedStatement ps = null;
        try {
        ISLogger.getLogger().error(
                "insertBfXumoCustomers clientId: " + tietocustomer.getTietoCustomerId()
                        + "\nbankClientId: " + tietocustomer.getBankCustomerId() + "\nbranch: "
                        + tietocustomer.getBranch() + "\nalias: " + alias);
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("INSERT INTO BF_XUMO_CUSTOMERS (ID, BRANCH, BANK_CUSTOMER_ID, TIETO_CUSTOMER_ID)" + 
        	" VALUES (seq_bf_xumo_customers.Nextval, ?, ?, ?)");
            ps.setString(1, tietocustomer.getBranch());
            ps.setString(2, tietocustomer.getBankCustomerId());
            ps.setString(3, tietocustomer.getTietoCustomerId());
            int rowsInserted = ps.executeUpdate();
            c.commit();
            if(rowsInserted == 1) {
            	res.setCode(0);
            	res.setName("Успешно!");
            }else {
            	res.setCode(-1);
            	res.setName("rows inserted " + rowsInserted);
            }
        } catch (Exception e) {
            ISLogger.getLogger().error("tietoCustomer create error: " + e.getLocalizedMessage());
            res.setCode(-1);
            res.setName(e.getLocalizedMessage());
            return res;
        } finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
    
    public static void insertTietoCustomer(TietoCustomer tietoCustomer) {
        Connection c = null;
        PreparedStatement ps = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if(Strings.isNullOrEmpty(tietoCustomer.getTietoCustomerId())) {
            ISLogger.getLogger().error("insertTietoCustomer tietoCustomerId is null or empty!");
            return;
        }
        try {
            ISLogger.getLogger().error("insertTietoCustomer tietoCustomer: "+objectMapper.writeValueAsString(tietoCustomer));
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("insert into bf_xumo_customers (id, tieto_customer_id, bank_customer_id, branch) " +
            		"values (seq_bf_xumo_customers.Nextval, ?, ?, ?)");
            ps.setString(1, tietoCustomer.getTietoCustomerId());
            ps.setString(2, tietoCustomer.getBankCustomerId());
            ps.setString(3, tietoCustomer.getBranch());
            ISLogger.getLogger().error("insertTietoCustomer rows inserted: " + ps.executeUpdate());
            c.commit();
        } catch (SQLException e) {
            ISLogger.getLogger().error("insertTietoCustomer error ", e);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
    }
    
    public static void insertBankCustomerId(TietoCustomer tietoCustomer) {
        Connection c = null;
        PreparedStatement ps = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if(Strings.isNullOrEmpty(tietoCustomer.getTietoCustomerId())) {
            ISLogger.getLogger().error("insertBankCustomerId tietoCustomerId is null or empty!");
            return;
        }
        try {
            ISLogger.getLogger().error("insertBankCustomerId tietoCustomer: "+objectMapper.writeValueAsString(tietoCustomer));
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("update bf_xumo_customers set bank_customer_id = ? where tieto_customer_id = ?");
            ps.setString(1, tietoCustomer.getBankCustomerId());
            ps.setString(2, tietoCustomer.getTietoCustomerId());
            ISLogger.getLogger().error("insertBankCustomerId rows updated: "+ps.executeUpdate());
            c.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
    }
	
	   public static void create(final TietoCustomer tietocustomer, Connection c, String alias) throws SQLException {
	        PreparedStatement ps = null;
	        try {
	            Statement st = c.createStatement();
	            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
	              ISLogger.getLogger().error("insertBfXumoCustomers clientId: "+tietocustomer.getTietoCustomerId()
	                        +"\nbankClientId: "+tietocustomer.getBankCustomerId()
	                        +"\nbranch: "+tietocustomer.getBranch());
	            ps = c.prepareStatement("INSERT INTO BF_XUMO_CUSTOMERS (ID, BRANCH, BANK_CUSTOMER_ID, TIETO_CUSTOMER_ID) VALUES (seq_bf_xumo_customers.Nextval,?,?,?)");
	            ps.setString(1, tietocustomer.getBranch());
	            ps.setString(2, tietocustomer.getBankCustomerId());
	            ps.setString(3, tietocustomer.getTietoCustomerId());
	            ISLogger.getLogger().error("tietoCustomer create ps: "+ps.toString());
	            ps.execute();
	        } finally {
	            Utils.close(ps);
	        }
	    }

	public static void create(final TietoCustomer tietocustomer, String account, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			ps = null;
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO bf_xumo_customers (id, branch, bank_customer_id, tieto_customer_id, cur_acc) VALUES (seq_bf_xumo_customers.Nextval,?,?,?)");
			ps.setString(1, tietocustomer.getBranch());
			ps.setString(2, tietocustomer.getBankCustomerId());
			ps.setString(3, tietocustomer.getTietoCustomerId());
			ps.setString(4, account);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
	}

	public static Res create_lnk(final String branch, final String branchId, final String tietoCustomerId, final String currentAccount, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		Res res = new Res();
		try {
			ISLogger.getLogger().error("create_lnk data branch: " + branch
					+ " bank_customer_id: " + branchId + " tieto_id: "
					+ tietoCustomerId + " cur_acc: " + currentAccount);
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("INSERT INTO bf_xumo_customers (id,branch, bank_customer_id, tieto_customer_id, cur_acc) VALUES (seq_bf_xumo_customers.Nextval, ?,?,?,?)");
			ps.setString(1, branch);
			ps.setString(2, branchId);
			ps.setString(3, tietoCustomerId);
			ps.setString(4, currentAccount);
			ps.execute();
			c.commit();
			res.setCode(0);
			res.setName("Успешно!");
		} catch (Exception e) {
			ISLogger.getLogger().error(("create_lnk error: " + (Object) CheckNull.getPstr(e)));
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static void update_lnk_ti_by_br(final String branch, final String br_id, final String tieto_id, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ISLogger.getLogger().error("update_lnk_ti_by_br branch: "+branch
		        +"\nbank_customer_id: "+br_id
		        +"\ntieto_customer_id: "+tieto_id);
		if (get_link_branch(br_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("insert into bf_xumo_customers (id, tieto_customer_id, branch, bank_customer_id) values(seq_bf_xumo_customers.Nextval, ?,?,?)");
				ps.setString(1, tieto_id);
				ps.setString(2, branch);
				ps.setString(3, br_id);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
				LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
				e.printStackTrace();
				return;
			} finally {
				ConnectionPool.close(c);
			}
			ConnectionPool.close(c);
			return;
		}
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_xumo_customers set tieto_customer_id = ? where branch = ? and bank_customer_id = ?");
			ps.setString(1, tieto_id);
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
	}

	public static Res update_lnk_ho_by_ti(final String branch, final String ho_id, final String tieto_id, final String alias) {
		final String ho = get_HO_by_tieto(tieto_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_tieto(tieto_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("insert into bf_xumo_customers (id,head_customer_id, branch, tieto_customer_id) values(seq_bf_xumo_customers.Nextval, ?,?,?)");
				ps.setString(1, (ho == null) ? ho_id : ho);
				ps.setString(2, branch);
				ps.setString(3, tieto_id);
				ps.executeUpdate();
				c.commit();
				final Res res = new Res(1, "Inserted into bf_xumo_customers...");
			} catch (Exception e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
				LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
				e.printStackTrace();
				final Res res = new Res(0, e.getMessage());
				return res;
			} finally {
				ConnectionPool.close(c);
			}
			ConnectionPool.close(c);
		}
		Res res;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_xumo_customers set head_customer_id = ? where branch = ? and tieto_customer_id = ?");
			ps.setString(1, (ho == null) ? ho_id : ho);
			ps.setString(2, branch);
			ps.setString(3, tieto_id);
			ps.executeUpdate();
			c.commit();
			res = new Res(1, "updated bf_xumo_customers...");
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(0, e.getMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static Res update_lnk_ho_by_br(final String branch, final String ho_id, final String br_id, final String alias) {
		final String tieto = get_tieto_by_ho(ho_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_branch(br_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("insert into bf_xumo_customers (id,head_customer_id, branch, bank_customer_id"
						+ ((tieto != null) ? ", tieto_customer_id" : "")
						+ ") values(seq_bf_xumo_customers.Nextval, ?,?,?"
						+ ((tieto != null) ? ",?" : "") + ")");
				ps.setString(1, ho_id);
				ps.setString(2, branch);
				ps.setString(3, br_id);
				if (tieto != null) {
					ps.setString(4, tieto);
				}
				ps.executeUpdate();
				c.commit();
				final Res res = new Res(1, "OK");
			} catch (Exception e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
				LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
				e.printStackTrace();
				final Res res = new Res(0, e.getMessage());
				return res;
			} finally {
				ConnectionPool.close(c);
			}
			ConnectionPool.close(c);
		}
		Res res;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_xumo_customers set head_customer_id = ?"
					+ ((tieto != null) ? (", tieto_customer_id='" + tieto + "'")
							: "")
					+ " where branch = ? and bank_customer_id = ?");
			ps.setString(1, ho_id);
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
			res = new Res(1, "OK");
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(0, e.getMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static Res update_lnk_br_by_ti(final String branch, final String br, final String tieto_id, final String alias) {
		final String ho = get_HO_by_tieto(tieto_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (get_link_tieto(tieto_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("select * from bf_xumo_customers where branch = ? and tieto_customer_id = ?");
				ps.setString(1, branch);
				ps.setString(2, tieto_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					final String t_cl_id = rs.getString("TIETO_CUSTOMER_ID");
					final String id = rs.getString("ID");
					if (t_cl_id == null || t_cl_id.equals("")) {
						TclientService.updateTietoCutomers(tieto_id, branch, id, c);
					}
				} else {
					TclientService.insertTietoCustomers(tieto_id, br, branch, c);
				}
				c.commit();
				final Res res = new Res(1, "OK");
			} catch (Exception e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
				LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
				e.printStackTrace();
				final Res res = new Res(0, e.getMessage());
				return res;
			} finally {
				Utils.close(rs);
				Utils.close(ps);
				ConnectionPool.close(c);
			}
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Res res;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_xumo_customers set bank_customer_id = ?"
					+ ((ho == null) ? ""
							: (", head_customer_id = '" + ho + "'"))
					+ " where branch = ? and tieto_customer_id = ?");
			ps.setString(1, br);
			ps.setString(2, branch);
			ps.setString(3, tieto_id);
			ps.executeUpdate();
			c.commit();
			res = new Res(1, "OK");
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(0, e.getMessage());
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static TietoCustomer getTietoCustomer(final TietoCustomer tietocustomerId, final String alias) {
		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM bf_xumo_customers WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, tietocustomerId.getBranch());
			ps.setString(2, tietocustomerId.getTietoCustomerId());
			rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();
				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBankCustomerId(rs.getString("bank_customer_id"));
				tietocustomer.setTietoCustomerId(rs.getString("tieto_customer_id"));
			} else {
				ps = c.prepareStatement("SELECT distinct tieto_customer_id FROM bf_xumo_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTietoCustomerId());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTietoCustomerId(rs.getString("tieto_customer_id"));
				}
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return tietocustomer;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return tietocustomer;
	}

	public static TietoCustomer getTietoCustomer(final String tietocustomerId, final String branch_id, final String alias) {
		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM bf_xumo_customers WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, branch_id);
			ps.setString(2, tietocustomerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();
				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBankCustomerId(rs.getString("bank_customer_id"));
				tietocustomer.setTietoCustomerId(rs.getString("tieto_customer_id"));
			} else {
				ps = c.prepareStatement("SELECT distinct tieto_customer_id FROM bf_xumo_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTietoCustomerId());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTietoCustomerId(rs.getString("tieto_customer_id"));
				}
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return tietocustomer;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return tietocustomer;
	}

	public static String get_HO_by_tieto(final String tieto_id, final String alias) {
		String res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("Select * From   (Select * From   bf_xumo_customers T Where  T.TIETO_CUSTOMER_ID = ? and T.BRANCH = '00444' UNION ALL Select * From   bf_xumo_customers T Where  T.TIETO_CUSTOMER_ID = ?) Where  ROWNUM = 1 ");
			ps.setString(1, tieto_id);
			ps.setString(2, tieto_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("head_customer_id");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static String get_BR_by_tieto(final String tieto_id, final String alias) {
		String res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("Select * From bf_xumo_customers T Where T.TIETO_CUSTOMER_ID = ? ");
			ps.setString(1, tieto_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("bank_customer_id");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static String get_tieto_by_ho(final String ho_id, final String alias) {
		String res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("Select * From   (Select * From   bf_xumo_customers T Where  T.head_customer_id = ? and T.BRANCH = '00444' UNION ALL Select * From   bf_xumo_customers T Where  T.head_customer_id = ?) Where  ROWNUM < 2 ");
			ps.setString(1, ho_id);
			ps.setString(2, ho_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("TIETO_CUSTOMER_ID");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static String get_contract(final String branch, final String card_code, final String alias) {
		String res = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			final PreparedStatement proc = c.prepareStatement("select BF.GET_CONTRACT_NUMBER(?,?) from dual");
			proc.setString(1, branch);
			proc.setString(2, card_code);
			final ResultSet rs = proc.executeQuery();
			if (rs.next()) {
				res = new String(rs.getString(1));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static Res update_lnk_set_acc(final String un, final String pwd, final String branch, final String br_id, final GlobuzAccount acc, final String alias, final IssuingPortProxy issuingPortProxy) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			ps = c.prepareStatement("update bf_xumo_customers set cur_acc = ? where branch = ? and bank_customer_id = ?");
			ps.setString(1, acc.getId());
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
			res = new Res(1, "Успешно !");
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(0, e.getMessage());
			return res;
		} finally {
			try {
				ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		try {
			ps.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ConnectionPool.close(c);
		return res;
	}

	public static Res Get_acc_hole(final String acc_bal, final String first, String last, final String client_id, final String branch, final String alias) {
		final Res res = new Res();
		res.setCode(0);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Label_0359: {
			try {
				c = ConnectionPool.getConnection(alias);
				// final String lastString = (last == null) ? "" :
				// "and t.id_order < ?";
				ISLogger.getLogger().error("GET ACC HOLE ALIAS: " + alias);
				if (first.equals(Utils.getValue("HUMO_ACC_ORD_FROM"))) {
					System.out.println("ACCORD FROM");
					ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t where t.client = ? and t.branch = ? and t.acc_bal = ? and t.currency = '000' and t.id_order >= ?  and t.id_order < ?");
				} else {

					System.out.println("ACCORD FROM ELSE");
					ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t where t.client = ? and t.branch = ? and t.acc_bal = ? and t.currency = '000' and t.id_order >= ? and t.id_order < ?");
				}
				if (alias.equals("BANK395") && first.equals("100")) {
					last = "199";
				}
				ps.setString(1, client_id);
				ps.setString(2, branch);
				ps.setString(3, acc_bal);
				ps.setString(4, first);
				ps.setString(5, last);
				ISLogger.getLogger().error((Object) ("first = " + first));
				/*
				 * if (last != null) { ps.setString(5, last); }
				 */
				rs = ps.executeQuery();
				res.setName(first);
				if (rs.next() && rs.getString("res") != null
						&& rs.getString("res").compareTo("") != 0) {
					System.out.println("res: " + rs.getString("res"));
					res.setName(rs.getString("res"));

					if (Integer.parseInt(res.getName()) > Integer.parseInt(last)) {
						res.setName("Зарегистрировано максимальное количество счетов данного типа");
						res.setCode(-1);
						return res;
					}
/*					} else if ((first.equals("222") && Integer.parseInt(res.getName()) > 230)) {
						return null;
					}*/
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
				e.printStackTrace();
				LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
				res.setCode(-1);
				res.setName(e.getMessage());
				break Label_0359;
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				ConnectionPool.close(c);
			}
			try {
				rs.close();
				ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res Get_acc_hole(final String acc_bal, final String first, final String last, final String client_id, final String branch, final String alias, final String firstCredit, String lastCredit) {
		final Res res = new Res();
		res.setCode(0);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Label_0359: {
			try {
				c = ConnectionPool.getConnection(alias);
				// final String lastString = (last == null) ? "" :
				// "and t.id_order < ?";
				if (first.equals("200")) {
					ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t where t.client = ? and t.branch = ? and t.acc_bal = ? and t.currency = '000' and t.id_order >= ?  and t.id_order < '221'");
				} else if (first.equals("222")) {
					ps = c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t where t.client = ? and t.branch = ? and t.acc_bal = ? and t.currency = '000' and t.id_order >= ? and t.id_order < '229'");
				}
				ps.setString(1, client_id);
				ps.setString(2, branch);
				ps.setString(3, acc_bal);
				ps.setString(4, first);
				ISLogger.getLogger().error((Object) ("first = " + first));
				/*
				 * if (last != null) { ps.setString(5, last); }
				 */
				rs = ps.executeQuery();
				res.setName(first);
				if (rs.next() && rs.getString("res") != null
						&& rs.getString("res").compareTo("") != 0) {
					res.setName(rs.getString("res"));
					if ((first.equals("200") && Integer.parseInt(res.getName()) > 221)) {
						return null;
					} else if ((first.equals("222") && Integer.parseInt(res.getName()) > 230)) {
						return null;
					}
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
				e.printStackTrace();
				LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
				res.setCode(-1);
				res.setName(e.getMessage());
				break Label_0359;
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				ConnectionPool.close(c);
			}
			try {
				rs.close();
				ps.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		ISLogger.getLogger().error((Object) ("res name = " + res.getName()));
		return res;
	}

	public static List<RefData> getMfo(final String branch) {
		return (List<RefData>) RefDataService.getRefData("select bank_id data, bank_name label from S_mfo order by bank_id", branch);
	}

	public static List<GlobuzAccount> get_card_accounts_new_card(final String client_id, final String branch, final String card_code, final String t_client_id) {
		final List<GlobuzAccount> res = new ArrayList<GlobuzAccount>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from account t where t.client = ? and t.branch = ? and t.acc_bal between '22617' and '22618' and t.currency = '000'");
			ps.setString(1, client_id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				res.add(new GlobuzAccount(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), (java.util.Date) rs.getDate("l_date"), (java.util.Date) rs.getDate("date_open"), (java.util.Date) rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			System.out.println("SQLException =>" + e.getMessage());
			System.out.println("SQLState =>" + e.getSQLState());
			System.out.println("ErrorMessage =>" + e.getMessage());
		} catch (Exception exp) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(exp));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(exp));
			exp.printStackTrace();
			System.out.println("Exception =>" + exp.getMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String GetCur20206_tclient(final String client_id, final String branch, final Connection c) {
		String res = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select t.cur_acc res from bf_xumo_customers t where t.tieto_customer_id = ? and t.branch = ?");
			ps.setString(1, client_id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("res");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
			return res;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
		}
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
		}
		return res;
	}

	public static GlobuzAccount getAccount(final String accoun_n, final String alias, final String branch, final Connection c) {
		GlobuzAccount list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("SELECT * FROM Account t where t.id = ? and t.branch = ?");
			ps.setString(1, accoun_n);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				list = new GlobuzAccount(rs.getString("branch"), rs.getString("id"), rs.getString("acc_bal"), rs.getString("currency"), rs.getString("client"), rs.getString("id_order"), rs.getString("name"), rs.getString("sgn"), rs.getString("bal"), rs.getInt("sign_registr"), rs.getLong("s_in"), rs.getLong("s_out"), rs.getLong("dt"), rs.getLong("ct"), rs.getLong("s_in_tmp"), rs.getLong("s_out_tmp"), rs.getLong("dt_tmp"), rs.getLong("ct_tmp"), (java.util.Date) rs.getDate("l_date"), (java.util.Date) rs.getDate("date_open"), (java.util.Date) rs.getDate("date_close"), rs.getInt("acc_group_id"), rs.getInt("state"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
			return list;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
		}
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
		}
		return list;
	}

	public static String multimoneytostr(final BigDecimal amount, final String language, final String currency, final Connection c) {
		String res = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select bf_money_to_str.MultiMoneyToStr(?, ?, ?) res from dual");
			ps.setBigDecimal(1, amount);
			ps.setString(2, language);
			ps.setString(3, currency);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("res");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
			return res;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
		}
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
		}
		return res;
	}

	public static double GetCourse(final String currency_1, final String currency_2, final Connection c) {
		double res = 0.0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select info.GetCourse(?, ?, 1, sysdate) res from dual");
			ps.setString(1, currency_1);
			ps.setString(2, currency_2);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getDouble("res");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
			return res;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
		}
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
		}
		return res;
	}

	public static HashMap<String, String> getCurr(final String alias) {
		final HashMap<String, String> hashMap = new HashMap<String, String>();
		List<RefData> list = new ArrayList<RefData>();
		list = (List<RefData>) RefDataService.getRefData("select s.kod data, s.kod_b label from s_val s where s.kod_b is not null", alias);
		for (int i = 0; i < list.size(); ++i) {
			hashMap.put(list.get(i).getData(), list.get(i).getLabel());
		}
		return hashMap;
	}

	static String getRealCardNumber(final String pseudoCard, final IssuingPortProxy proxy, final String bankC, final String groupC) throws RemoteException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
	    final RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		String realCard = "";
			final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			final String externalSesionId = Utils.getExternalSession();
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
			final RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);
			try {
		         ISLogger.getLogger().error("getRealCard request: "
		                    +"\n"+objectMapper.writeValueAsString(connectionInfo)
		                    +"\n"+objectMapper.writeValueAsString(request));		           
				proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
                ISLogger.getLogger().error("getRealCard response: "
                        +"\n"+objectMapper.writeValueAsString(connectionInfo)
                        +"\n"+objectMapper.writeValueAsString(request)
                        +"\n"+objectMapper.writeValueAsString(response));
			}
				catch (NullPointerException npe) {
                ISLogger.getLogger().error("getRealCard NullPointerException: "
                        +"\n"+objectMapper.writeValueAsString(connectionInfo)
                        +"\n"+objectMapper.writeValueAsString(request)
                        +"\n"+objectMapper.writeValueAsString(response));
            } finally {
				realCard = response.value.getRCARD();
			}
		return realCard;
	}

	public static String getRealCardByCard(String alias, String card) throws SQLException {
		ISLogger.getLogger().error("CARD GET CARD: " + card);
		System.out.println("ALIAS GETCARD: " + alias);
		System.out.println("CARD GETCARD: " + card);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String real_card = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select real_card from humo_cards where card = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			if (rs.next()) {
				real_card = rs.getString("real_card");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		ISLogger.getLogger().error("CARD GET REAL CARD: " + real_card);
		System.out.println("REALCARD GETCARD: " + real_card);
		return real_card;
	}

	public static Map<String, String> getPsevdaPANInHashmap() {
		List<RefData> terminal = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final Map<String, String> hashMap = new HashMap<String, String>();
		try {
			terminal = new ArrayList<RefData>();
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select b.card,b.client from humo_cards b where b.real_card is null");
			rs = ps.executeQuery();
			while (rs.next()) {
				hashMap.put(rs.getString("card"), rs.getString("client"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) e);
			e.printStackTrace();
			return hashMap;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return hashMap;
	}

	static OperationResponseInfo resetPINCounter(final String PAN, final IssuingPortProxy proxy, final String bankC, final String groupC, final String un, final String pw, final String alias, final String expiry) throws RemoteException {
		ObjectMapper objectMapper = new ObjectMapper();
		final RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;
		try {
			final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			final String externalSesionId = Utils.getExternalSession();
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
			final RowType_ResetPINCounter_Request param = new RowType_ResetPINCounter_Request();
			param.setCARD(PAN);
			param.setEXPIRY(expiry);
			param.setTEXT("zbrosPIN");
			ISLogger.getLogger().error("resetPINCounter request "
					+ "\n" + objectMapper.writeValueAsString(connectionInfo)
					+ "\n" + objectMapper.writeValueAsString(param));
			res = proxy.resetPINCounter(connectionInfo, param);
			ISLogger.getLogger().error("resetPINCounter response: "
					+ "\n" + objectMapper.writeValueAsString(res));
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) ("resetPINCounter:" + e));
		}
		return res;
	}

	public static String getCardDate(final String PANCard, final String un, final String pw, final String alias) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String date = null;
		String srok = null;
		Connection c1 = null;
		try {
			c1 = ConnectionPool.getConnection(un, pw, alias);
			ps = c1.prepareStatement("SELECT EXPIRY1 FROM HUMO_CARDS where card=?");
			ps.setString(1, PANCard);
			rs = ps.executeQuery();
			while (rs.next()) {
				date = rs.getString("EXPIRY1");
				System.out.println("date:" + date);
				srok = String.valueOf(date.substring(2, 4))
						+ date.substring(5, 7);
				System.out.println("srok:" + srok);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) ("EXPIRY1:" + e));
			return srok;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c1);
		}
		return srok;
	}

	public static class link {
		public int id;
		public String Branch;
		public String Tieto_id;
		public String Branch_id;
		public String Cur_acc;

		public link(final int Id, final String branch, final String tieto_id,
				final String branch_id, final String cur_acc) {
			this.id = Id;
			this.Branch = branch;
			this.Tieto_id = tieto_id;
			this.Branch_id = branch_id;
			this.Cur_acc = cur_acc;
		}
	}

	// ///////////////////////////ПЕРЕВЫПУСК/////////////////////////////////////////////////////////////////////

	/*
	 * public static ReplaceCardInfo openCard(CardInfo card, String alias)
	 * throws SQLException { ReplaceCardInfo new_card = null;
	 * 
	 * try { Connection c = null; PreparedStatement ps; ResultSet rs; String
	 * HUMO_BANK_C; String HUMO_GROUPC; String HUMO_BINCOD; String HUMO_HOST;
	 * String HUMO_USERNAME; String HUMO_PASSWORD; String HUMO_BRANCHID; Long
	 * HUMO_RANGEID; String CLIENT_B = ""; String CARD_NAME = ""; String BRANCH
	 * = ""; String REAL_CARD = ""; String CLIENT = ""; String ACCOUNT_NO = "";
	 * 
	 * globus.IssuingWS.IssuingPortProxy issuingPortProxy;
	 * OperationConnectionInfo connectionInfo; c =
	 * ConnectionPool.getConnection(alias);
	 * System.out.println("CARD: "+card.getCARD()); ps =
	 * c.prepareStatement("select * from humo_cards where card = '"
	 * +card.getCARD()+"'"); rs = ps.executeQuery(); while(rs.next()){ BRANCH =
	 * rs.getString("BRANCH"); CLIENT_B = rs.getString("CLIENT_B"); CARD_NAME =
	 * rs.getString("CARD_NAME"); REAL_CARD = rs.getString("REAL_CARD"); CLIENT
	 * = rs.getString("CLIENT"); ACCOUNT_NO = rs.getString("ACCOUNT_NO"); }
	 * HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C"); HUMO_GROUPC =
	 * ConnectionPool.getValue("HUMO_GROUPC"); HUMO_BINCOD =
	 * ConnectionPool.getValue("HUMO_BINCOD"); HUMO_HOST =
	 * ConnectionPool.getValue("HUMO_HOST"); HUMO_USERNAME =
	 * ConnectionPool.getValue("HUMO_USERNAME"); HUMO_PASSWORD =
	 * ConnectionPool.getValue("HUMO_PASSWORD"); issuingPortProxy =
	 * getPortProxy(HUMO_HOST, HUMO_USERNAME,HUMO_PASSWORD); connectionInfo =
	 * getConInfo(HUMO_BANK_C, HUMO_GROUPC); new_card = replaceCard(c,
	 * issuingPortProxy, connectionInfo, REAL_CARD, BRANCH, CLIENT_B, CLIENT,
	 * ACCOUNT_NO, CARD_NAME);
	 * 
	 * c.commit(); ConnectionPool.close(c); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } finally { } return
	 * new_card; }
	 * 
	 * 
	 * public static ReplaceCardInfo replaceCard(Connection c,
	 * globus.IssuingWS.IssuingPortProxy portProxy, OperationConnectionInfo
	 * conInfo, String real_card, String branch, String client_b, String client,
	 * String account_no, String card_name) throws Exception { String newCard =
	 * ""; ReplaceCardInfo cardInfo = null; ReplaceCardInfo cardStopInfo = null;
	 * globus.IssuingWS.IssuingPortProxy issuingPortProxy;
	 * OperationConnectionInfo connectionInfo; String HUMO_BANK_C =
	 * ConnectionPool.getValue("HUMO_BANK_C"); String HUMO_GROUPC =
	 * ConnectionPool.getValue("HUMO_GROUPC"); String HUMO_BINCOD =
	 * ConnectionPool.getValue("HUMO_BINCOD"); String HUMO_HOST =
	 * ConnectionPool.getValue("HUMO_HOST"); String HUMO_USERNAME =
	 * ConnectionPool.getValue("HUMO_USERNAME"); String HUMO_PASSWORD =
	 * ConnectionPool.getValue("HUMO_PASSWORD"); issuingPortProxy =
	 * getPortProxy(HUMO_HOST, HUMO_USERNAME,HUMO_PASSWORD); connectionInfo =
	 * getConInfo(HUMO_BANK_C, HUMO_GROUPC); addCardToStop(real_card,
	 * HUMO_BANK_C, HUMO_GROUPC, issuingPortProxy, connectionInfo);
	 * 
	 * cardInfo = getNewCard(real_card, portProxy, conInfo);
	 * 
	 * 
	 * if (cardInfo.getError_description() == null) {
	 * System.out.println("cardInfo responce"); newCard = cardInfo.getCard();
	 * ISLogger.getLogger().error("NEWCARD: "+newCard); Calendar NEW_EXPIRY =
	 * cardInfo.getNEW_EXPIRY(); updateHumoCards(c, real_card, newCard,
	 * NEW_EXPIRY); insertCardHistory(c, client, client_b, branch, card_name,
	 * real_card, account_no);
	 * 
	 * } return cardInfo; }
	 * 
	 * public static void insertCardHistory(Connection c, String client, String
	 * client_b, String branch, String card_name, String real_card, String
	 * account_no) throws SQLException{ PreparedStatement ps; java.sql.Date
	 * sqlDate = new java.sql.Date(System.currentTimeMillis()); final
	 * SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy"); ps =
	 * c.prepareStatement(
	 * "insert into humo_individuals_card_history (id, client, client_b, branch, card_name, real_card, account_no, replace_date) values(IND_CARD_HISTORY_ID_SEQ.Nextval, ?, ?, ?, ?, ?, ?, ?)"
	 * ); ps.setString(1, client); ps.setString(2, client_b); ps.setString(3,
	 * branch); ps.setString(4, card_name); ps.setString(5, real_card);
	 * ps.setString(6, account_no); ps.setDate(7, sqlDate); ps.execute(); }
	 * 
	 * public static void addCardToStop(String card, String bank_c, String
	 * group_c, globus.IssuingWS.IssuingPortProxy portProxy,
	 * OperationConnectionInfo conInfo) throws KeyManagementException,
	 * ClientProtocolException, NoSuchAlgorithmException, KeyStoreException,
	 * IOException {
	 * 
	 * RowType_AddCardToStopList_Request parameters = new
	 * RowType_AddCardToStopList_Request(); //OperationResponseInfoHolder
	 * responseInfo = new OperationResponseInfoHolder();
	 * RowType_ReplaceCardHolder details = new RowType_ReplaceCardHolder();
	 * ReplaceCardInfo replaceCardInfo = new ReplaceCardInfo();
	 * OperationResponseInfo responseInfo;
	 * ISLogger.getLogger().error("STOP CARD "+card);
	 * ISLogger.getLogger().error("STOP CARD bank c "+bank_c);
	 * ISLogger.getLogger().error("STOP CARD group c "+group_c);
	 * 
	 * parameters.setCARD(card); parameters.setSTOP_CAUSE("2");
	 * parameters.setTEXT("Perevipusk"); parameters.setBANK_C(bank_c);
	 * parameters.setGROUPC(group_c); responseInfo =
	 * portProxy.addCardToStop(conInfo, parameters);
	 * if(responseInfo.getResponse_code().equals(0)){
	 * 
	 * } else{
	 * ISLogger.getLogger().error("ADD CARD TO STOP ERROR: "+responseInfo
	 * .getError_description()); return; }
	 * 
	 * }
	 * 
	 * public static ReplaceCardInfo getNewCard(String card,
	 * globus.IssuingWS.IssuingPortProxy portProxy, OperationConnectionInfo
	 * conInfo) throws KeyManagementException, ClientProtocolException,
	 * NoSuchAlgorithmException, KeyStoreException, IOException {
	 * System.out.println("getNewCard card "+card);
	 * System.out.println("getNewCard portProxy "+portProxy);
	 * System.out.println("getNewCard conInfo "+conInfo);
	 * System.out.println("getNewCard card");
	 * ISLogger.getLogger().error("getNewCard card "+card);
	 * ISLogger.getLogger().error("getNewCard portProxy "+portProxy);
	 * ISLogger.getLogger().error("getNewCard conInfo "+conInfo);
	 * RowType_ReplaceCard parameters = new RowType_ReplaceCard();
	 * OperationResponseInfoHolder responseInfo = new
	 * OperationResponseInfoHolder(); RowType_ReplaceCardHolder details = new
	 * RowType_ReplaceCardHolder(); ReplaceCardInfo replaceCardInfo = new
	 * ReplaceCardInfo(); BigInteger responseCode;
	 * 
	 * parameters.setCARD(card);
	 * 
	 * portProxy.replaceCard(conInfo, parameters, responseInfo, details);
	 * 
	 * responseCode = responseInfo.value.getResponse_code();
	 * ISLogger.getLogger()
	 * .error("RESPONSE CODE: "+responseInfo.value.getResponse_code());
	 * ISLogger.
	 * getLogger().error("RESPONSE CARD: "+details.value.getNEW_CARD());
	 * ISLogger
	 * .getLogger().error("RESPONSE EXPIRY: "+details.value.getNEW_EXPIRY());
	 * ISLogger
	 * .getLogger().error("RESPONSE CODE: "+responseInfo.value.getResponse_code
	 * ()); if (responseInfo.value.getError_description() == null) {
	 * replaceCardInfo.setCard(details.value.getNEW_CARD());
	 * replaceCardInfo.setNEW_EXPIRY(details.value.getNEW_EXPIRY());
	 * replaceCardInfo.setResponse_code("0");
	 * 
	 * }
	 * 
	 * else { ISLogger.getLogger().error("REPLACE CARD ERROR: ");
	 * replaceCardInfo.setResponse_code(responseCode.toString());
	 * replaceCardInfo.setError_action(responseInfo.value .getError_action());
	 * replaceCardInfo.setError_description(responseInfo.value
	 * .getError_description());
	 * 
	 * }
	 * 
	 * return replaceCardInfo;
	 * 
	 * }
	 * 
	 * public static OperationConnectionInfo getConInfo(String HUMO_BANK_C,
	 * String HUMO_GROUPC) { OperationConnectionInfo conInfo = new
	 * OperationConnectionInfo();
	 * 
	 * conInfo.setBANK_C(HUMO_BANK_C); conInfo.setGROUPC(HUMO_GROUPC);
	 * conInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
	 * 
	 * return conInfo;
	 * 
	 * }
	 * 
	 * private static globus.IssuingWS.IssuingPortProxy getPortProxy( String
	 * HUMO_HOST, String HUMO_USERNAME, String HUMO_PASSWORD) throws
	 * ClientProtocolException, IOException, NoSuchAlgorithmException,
	 * KeyStoreException, KeyManagementException {
	 * globus.IssuingWS.IssuingPortProxy issuingPortProxy;
	 * 
	 * final TrustManager[] trustAllCerts = new TrustManager[] { new
	 * X509TrustManager() {
	 * 
	 * public java.security.cert.X509Certificate[] getAcceptedIssuers() { return
	 * null; }
	 * 
	 * public void checkClientTrusted( java.security.cert.X509Certificate[]
	 * certs, String authType) { }
	 * 
	 * public void checkServerTrusted( java.security.cert.X509Certificate[]
	 * certs, String authType) { } } }; try { SSLContext sc =
	 * SSLContext.getInstance("TLSv1.2"); sc.init(null, trustAllCerts, new
	 * java.security.SecureRandom()); HostnameVerifier allHostsValid = new
	 * HostnameVerifier() {
	 * 
	 * public boolean verify(String hostname, SSLSession session) { return true;
	 * } }; HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	 * 
	 * } catch (Exception ex) { }
	 * 
	 * return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(
	 * HUMO_HOST, HUMO_USERNAME, HUMO_PASSWORD); }
	 * 
	 * 
	 * public static void updateHumoCards(Connection c,String oldCard,String
	 * newCard,Calendar NEW_EXPIRY) { PreparedStatement psUpdateHumoCards =
	 * null; try {
	 * 
	 * psUpdateHumoCards = c
	 * .prepareStatement("update humo_cards set real_card=?,EXPIRY1=? " +
	 * " where real_card=?");
	 * 
	 * psUpdateHumoCards.setString(1, newCard); psUpdateHumoCards.setDate(2,
	 * (java.sql.Date)NEW_EXPIRY.getTime()); psUpdateHumoCards.setString(3,
	 * oldCard);
	 * 
	 * 
	 * psUpdateHumoCards.execute(); c.commit(); } catch (Exception e) {
	 * e.printStackTrace();
	 * 
	 * ISLogger.getLogger().error(CheckNull.getPstr(e)); } }
	 */

	public static String getRealCard(String alias, String card, String account_no) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String real_card = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select real_card from humo_cards where card = ? and account_no = ?");
			ps.setString(1, card);
			ps.setString(2, account_no);
			rs = ps.executeQuery();
			if (rs.next()) {
				real_card = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return real_card;
	}

	public static boolean checkAccount(String acc, String alias, String branch) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) count from card where branch = ? and substr(def_atm_account, -20) = ?");
			ps.setString(1, branch);
			ps.setString(2, acc);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1) == 0 ? false : true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getBincode(String card) throws SQLException {
		String bincode = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select substr(c.real_card, 0, 8) bincode from humo_cards c where c.card = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			while (rs.next()) {
				bincode = rs.getString("bincode");
				System.out.println("bincode: " + bincode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return bincode;
	}

	public static void insertBudgetInfo(ArrayList<String> list, Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select count(*) count from humo_client_budget where branch = ? and budget_account = ? and budget_inn = ? and employee_id = ?");
			ps.setString(1, list.get(0));
			ps.setString(2, list.get(1));
			ps.setString(3, list.get(2));
			ps.setString(4, list.get(3));
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("count").equals("0")) {
					ps = c.prepareStatement("insert into humo_client_budget (branch, budget_account, budget_inn, employee_id) values (?, ?, ?, ?)");
					ps.setString(1, list.get(0));
					ps.setString(2, list.get(1));
					ps.setString(3, list.get(2));
					ps.setString(4, list.get(3));
					ps.execute();
					ps.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}

	public static void insertWorkInfo(ArrayList<String> list, Connection c, String order_from, String order_previous, String alias) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select count(*) count from humo_client_work where branch = ? and customer_id = ? and employee_id = ?");

			ps.setString(1, list.get(0));
			ps.setString(2, list.get(1));
			ps.setString(3, list.get(2));
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("count").equals("0")) {
					ps = c.prepareStatement("insert into humo_client_work (branch, customer_id, employee_id, acc) values (?, ?, ?, ?)");
					ps.setString(1, list.get(0));
					ps.setString(2, list.get(1));
					ps.setString(3, list.get(2));
					ps.setString(4, Utils.getValueFromSql("select id from account where client = '"
							+ list.get(1)
							+ "' and branch = '"
							+ list.get(0)
							+ "' and substr(id, 17, 3) between '"
							+ order_from
							+ "' and '" + order_previous + "'", alias));
					ps.execute();
					ps.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}

	public static boolean checkS_BudsprExistence(String account, Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			ps = c.prepareStatement("select count(*) count from s_budspr where account = ?");
			ps.setString(1, account);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("count").equals("0")) {
					rs.close();
					ps.close();
					return res;
				} else {
					res = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return res;
	}

	public static List<TempClient> getTemp(String selectedExcel, String branch) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TempClient> tempList = new ArrayList<TempClient>();
		try {
			c = ConnectionPool.getConnection();
			if (selectedExcel == null) {
				ps = c.prepareStatement("select * from open_clients_temp where branch = ? and state = '1'");
				ps.setString(1, branch);
			} else {
				ps = c.prepareStatement("select * from open_clients_temp where excel = ? and branch = ?");
				System.out.println("selectedExcel: " + selectedExcel);
				System.out.println("branch: " + branch);
				ps.setString(1, selectedExcel);
				ps.setString(2, branch);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				TempClient tempClient = new TempClient();
				tempClient.setBranch(rs.getString("branch"));
				tempClient.setClient_id(rs.getString("client_id"));
				if (rs.getString("state").equals("1")) {
					tempClient.setState("Успешно загружен");
				} else if (rs.getString("state").equals("3")) {
					tempClient.setState("Загружен с ошибкой");
				} else if (rs.getString("state").equals("2")) {
					tempClient.setState("Успешно зарегистрирован");
				} else if (rs.getString("state").equals("4")) {
					tempClient.setState("Зарегистрирован с ошибкой");
				}
				tempClient.setText(rs.getString("err_msg"));
				tempClient.setExcel(rs.getString("excel"));
				tempList.add(tempClient);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return tempList;
	}

	public static List<String> getExcelList(String branch, String currentExcel) {
		Connection c = null;
		List<String> excelList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select distinct excel excel from open_clients_temp where state = '1' and branch = '"
					+ branch + "'");
			rs = ps.executeQuery();
			if (!(currentExcel == null)) {
				excelList.add(currentExcel);
			}
			while (rs.next()) {
				excelList.add(rs.getString("excel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return excelList;
	}

	public static void deleteExcel(String branch, String currentExcel) {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete open_clients_temp where excel = ? and branch = ?");
			ps.setString(1, currentExcel);
			ps.setString(2, branch);
			ps.execute();
			ps.close();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static boolean checkOpenClientsTempExistence(String excel, String branch) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = true;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) count from open_clients_temp where excel = ? and branch = ?");
			ps.setString(1, excel);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("count").equals("0")) {
					res = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String checkTableState(String branch, String excel) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String state = "different";
		List<String> stateList = new ArrayList<String>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select state from open_clients_temp where excel = ? and branch = ?");
			ps.setString(1, excel);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				state = rs.getString("state");
				if (state.equals("1")) {
					return state;
				}
				stateList.add(state);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		for (int i = 0; i < stateList.size() - 1; i++) {
			if (!(stateList.get(i).equals(stateList.get(i + 1)))) {
				state = "different";
				return state;
			} else {
				state = stateList.get(i).toString();
			}
		}
		System.out.println("state: " + state);
		return state;
	}

	public static boolean checkAccountExistence(Connection c, String branch, String client, String order_from, String order_previous) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = true;
		try {
			ps = c.prepareStatement("select count(*) count from account where client = ? and branch = ? and substr(id, 1, 5) = '22618' and substr(id, 18, 3) between ? and ?");
			ps.setString(1, client);
			ps.setString(2, branch);
			ps.setString(3, order_from);
			ps.setString(4, order_previous);
			rs = ps.executeQuery();
			while (rs.next()) {
				ISLogger.getLogger().error("COUNT ACCOUNTS: "
						+ rs.getString("count"));
				if (rs.getString("count").equals("0")) {
					res = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return res;
	}

	public static String getAccount(Connection c, String branch, String client, String order_from, String order_previous) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String acc = null;
		try {
			ps = c.prepareStatement("select id from account where client = ? and branch = ? and substr(id, 1, 8) = '22618000' and substr(id, 18, 3) between ? and ?");
			ps.setString(1, client);
			ps.setString(2, branch);
			ps.setString(3, order_from);
			ps.setString(4, order_previous);
			rs = ps.executeQuery();
			if (rs.next()) {
				acc = rs.getString("id");
				ISLogger.getLogger().error("getAccounts acc: " + acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return acc;
	}

	public static void sendBlockReason(String phone, String textReason, String un, String pwd, String alias) {
		CallableStatement cs = null;
		Connection c = null;
		try {
			System.out.println("VIZVAL SEND BLOCK");
			c = ConnectionPool.getConnection(un, pwd, alias);
			cs = c.prepareCall("{ call PROC_SMS.pSENDSMS(?, ?, ?, ?, ?, ?) }");
			cs.setString(1, "198");
			cs.setString(2, phone);
			cs.setString(3, "");
			cs.setString(4, textReason);
			cs.setString(5, "0");
			cs.registerOutParameter(6, Types.INTEGER);
			cs.execute();
			c.commit();
			ISLogger.getLogger().error("response sms: " + Types.INTEGER);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			ISLogger.getLogger().error("ERROR SENDSMS " + e);
			e.printStackTrace();
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}

	}
	public static void checkCard(List<AccInfo> accInfos, IssuingPortProxy issuingPortProxy, String branch, 
			String alias, HashMap<String, EmpcSettings> settings) {
		
	}
	

	public static void checkCard(List<AccInfo> list, String clientB, IssuingPortProxy issuingPortProxy, String branch, String alias, HashMap<String, EmpcSettings> settings) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();

			String firstPattern = "yyyy-MM-dd";
			String secondPattern = "dd.MM.yyyy";
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(firstPattern);
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(secondPattern);
			ObjectMapper objectMapper = new ObjectMapper();

			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.get(i).getCardlist().size(); j++) {
					try {
						ISLogger.getLogger().error("checkCard for bank client " + clientB + "\nCARD INFO: "
								+ objectMapper.writeValueAsString(list.get(i).getCardlist().get(j)));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps = c.prepareStatement("select * from humo_cards c where c.card = ?");
					ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
					ISLogger.getLogger().error("checkCard CARD NUMBER: "
							+ list.get(i).getCardlist().get(j).getCARD());
					rs = ps.executeQuery();
					if (!rs.next()) {
						ISLogger.getLogger().error("checkCard no card with pan "
								+ list.get(i).getCardlist().get(j).getCARD()
								+ " in humo_cards!");
						ps = c.prepareStatement("select * from humo_cards_history where card = ?");
						ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
						rs = ps.executeQuery();
						if (!rs.next()) {
							String real_card = "";
							real_card = HumoCardsService.getRealCardNumber(list.get(i).getCardlist().get(j).getCARD(), issuingPortProxy, settings.get(branch).getBank_c(), settings.get(branch).getGroup_c());
							ISLogger.getLogger().error("checkCard getRealCard: "
									+ real_card);
							ps = c.prepareStatement("select * from humo_cards c where c.real_card = ?");
							ps.setString(1, real_card);
							rs = ps.executeQuery();
							if (rs.next()) {
                                if (rs.getString("card") != null) {
                                    if (!rs.getString("card").equals(
                                            list.get(i).getCardlist().get(j).getCARD())) {
                                        ISLogger.getLogger().error("UPDATE INFO FOR CARD");
                                        ps = c.prepareStatement("update humo_cards set card = ? where real_card = ?");
                                        ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
                                        ps.setString(2, real_card);
                                        ps.execute();
                                        c.commit();
                                    }
                                } else {
                                    ps = c.prepareStatement("update humo_cards set card = ? where real_card = ?");
                                    ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
                                    ps.setString(2, real_card);
                                    ps.execute();
                                    c.commit();
                                }
							} else if (list.get(i).getCardlist().get(j).getSTATUS().equals("2")) {
							    ISLogger.getLogger().error("Card "+list.get(i).getCardlist().get(j).getCARD()
							            +"\nwith real PAN "+real_card+" is blocked. Checking whether it was replaced");
		                         ps = c.prepareStatement("select count(c.real_card) from bf_empc_accounts a, humo_cards c where a.card_acct = ? and a.account_no = c.account_no");
		                            ps.setString(1, list.get(i).getCardlist().get(j).getCARD_ACCT());
		                            rs = ps.executeQuery();
		                            if(rs.next()) {
		                                if(rs.getInt(1) > 0) {
		                                    ISLogger.getLogger().error("Card "+list.get(i).getCardlist().get(j).getCARD()
		                                            +"\nwith real PAN "+real_card+" is replaced. Inserting into humo_cards_history");
		                                    ps = c.prepareStatement("insert into humo_cards_history (client, card, status1, status2, expiry1, stop_cause, real_card, card_acct, account_no, card_name, branch, client_b, mc_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		                                    ps.setString(1, list.get(i).getCardlist().get(j).getCLIENT_ID());
		                                    ps.setString(2, list.get(i).getCardlist().get(j).getCARD());
		                                    ps.setString(3, list.get(i).getCardlist().get(j).getSTATUS());
		                                    ps.setString(4, list.get(i).getCardlist().get(j).getSTATUS2());
		                                    ps.setString(5, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
		                                            : simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
		                                    ps.setString(6, list.get(i).getCardlist().get(j).getSTOP_CAUSE());
		                                    ps.setString(7, real_card);
		                                    ps.setString(8, list.get(i).getCardlist().get(j).getCARD_ACCT());
		                                    ps.setString(9, list.get(i).getCardlist().get(j).getACCOUNT_NO());
		                                    ps.setString(10, list.get(i).getCardlist().get(j).getCARD_String());
		                                    ps.setString(11, branch);
		                                    ps.setString(12, clientB);
		                                    ps.setString(13, list.get(i).getCardlist().get(j).getCARD_String());
		                                    ps.execute();
		                                    c.commit();
		                                }
		                            }
							} else if (list.get(i).getCardlist().get(j).getCARD_ACCT().substring(2, 7).equals(branch)) {
								ISLogger.getLogger().error("checkCard no realCard with pan "
										+ list.get(i).getCardlist().get(j).getCARD()
										+ " and real PAN "
										+ real_card
										+ " in humo_cards!");
								ISLogger.getLogger().error("checkCard INSERT MISSING CARD");
								ISLogger.getLogger().error("EXPIRY2: "
										+ list.get(i).getCardlist().get(j).getEXPIRY2().length());
								ps = c.prepareStatement("insert into humo_cards (client, card, status1, status2, expiry1, stop_cause, real_card, card_acct, account_no, card_name, branch, client_b, mc_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
								ps.setString(1, list.get(i).getCardlist().get(j).getCLIENT_ID());
								ps.setString(2, list.get(i).getCardlist().get(j).getCARD());
								ps.setString(3, list.get(i).getCardlist().get(j).getSTATUS());
								ps.setString(4, list.get(i).getCardlist().get(j).getSTATUS2());
								ps.setString(5, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
										: simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
								ps.setString(6, list.get(i).getCardlist().get(j).getSTOP_CAUSE());
								ps.setString(7, real_card);
								ps.setString(8, list.get(i).getCardlist().get(j).getCARD_ACCT());
								ps.setString(9, list.get(i).getCardlist().get(j).getACCOUNT_NO());
								ps.setString(10, list.get(i).getCardlist().get(j).getCARD_String());
								ps.setString(11, branch);
								ps.setString(12, clientB);
								ps.setString(13, list.get(i).getCardlist().get(j).getCARD_String());
								ps.execute();
								c.commit();
							}
						}
					} else {
						updateStatuses(list.get(i).getCardlist().get(j).getCARD(), list.get(i).getCardlist().get(j).getSTATUS(), list.get(i).getCardlist().get(j).getSTATUS2());
						ISLogger.getLogger().error("checkCard exists card with pan "
								+ list.get(i).getCardlist().get(j).getCARD()
								+ " in humo_cards!");
						if (Utils.getValueFromSql("select client_b from humo_cards where card = '"
								+ list.get(i).getCardlist().get(j).getCARD()
								+ "'", alias) == null) {
							ps = c.prepareStatement("update humo_cards set client_b = ? where card = ?");
							ps.setString(1, clientB);
							ps.setString(2, list.get(i).getCardlist().get(j).getCARD());
							ps.executeUpdate();
							c.commit();
						}
						if (Utils.getValueFromSql("select expiry1 from humo_cards where card = '"
								+ list.get(i).getCardlist().get(j).getCARD()
								+ "'", alias) == null) {
							ps = c.prepareStatement("update humo_cards set expiry1 = ? where card = ?");
							ps.setString(1, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
									: simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
							ps.setString(2, list.get(i).getCardlist().get(j).getCARD());
							ps.executeUpdate();
							c.commit();
						}
					}
					ps = c.prepareStatement("select * from bf_empc_accounts a where account_no = ?");
					ps.setString(1, list.get(i).getCardlist().get(j).getACCOUNT_NO());
					rs = ps.executeQuery();
					if (!rs.next()) {
						ISLogger.getLogger().error("checkCard no data account_no "
								+ list.get(i).getCardlist().get(j).getACCOUNT_NO()
								+ " and clientId: "
								+ list.get(i).getCardlist().get(j).getCLIENT_ID()
								+ " and tranz_acct: "
								+ list.get(i).getCardlist().get(j).getBank_account()
								+ " in bf_empc_accounts ");
						ps = c.prepareStatement("insert into bf_empc_accounts (client, account_no, card_acct, tranz_acct) values (?, ?, ?, ?)");
						ps.setString(1, list.get(i).getCardlist().get(j).getCLIENT_ID());
						ps.setString(2, list.get(i).getCardlist().get(j).getACCOUNT_NO());
						ps.setString(3, list.get(i).getCardlist().get(j).getCARD_ACCT());
						ps.setString(4, list.get(i).getCardlist().get(j).getBank_account());
						ISLogger.getLogger().error("insert into bf_empc_accounts (client, account_no, card_acct, tranz_acct) values " +
								"('"+list.get(i).getCardlist().get(j).getCLIENT_ID()+"', " +
										"'"+list.get(i).getCardlist().get(j).getACCOUNT_NO()+"', " +
												"'"+list.get(i).getCardlist().get(j).getCARD_ACCT()+"', " +
														"'"+list.get(i).getCardlist().get(j).getBank_account()+"')");
						ps.execute();
						c.commit();
					}
					c.commit();
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch blockЫ
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	
	public static void updateCardStatuses(CardInfo cardInfo){
		ISLogger.getLogger().error("pin assigned to card "+cardInfo.getCARD());
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set status1 = ?, status2 = ?, stop_cause = ? where card = ? and account_no = ?");
			ps.setString(1, cardInfo.getSTATUS());
			ps.setString(2, cardInfo.getSTATUS2());
			ps.setString(3, cardInfo.getSTOP_CAUSE());
			ps.setString(4, cardInfo.getCARD());
			ps.setString(5, cardInfo.getACCOUNT_NO());
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
			ConnectionPool.close(c);
		}

	}

/*	public static void checkCardForFix(List<AccInfo> list, IssuingPortProxy issuingPortProxy, String branch, String alias, Label label) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();

			String firstPattern = "yyyy-MM-dd";
			String secondPattern = "dd.MM.yyyy";
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(firstPattern);
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(secondPattern);
			ObjectMapper objectMapper = new ObjectMapper();
			final String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			final String groupC = ConnectionPool.getValue("HUMO_GROUPC");
			for (int i = 0; i < list.size(); i++) {
				String index = String.valueOf(i);
				label.setValue(index);
				String sql1 = "select real_card from humo_cards c where c.real_card in (";
				String sql2 = ");";
				for (int j = 0; j < list.get(i).getCardlist().size(); j++) {
					try {
						ISLogger.getLogger().error("CARD INFO: "
								+ objectMapper.writeValueAsString(list.get(i).getCardlist().get(j)));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String real_card = getRealCardNumber(list.get(i).getCardlist().get(j).getCARD(), issuingPortProxy, bankC, groupC);
					ISLogger.getLogger().error("fixCard check real_card: "
							+ real_card);
					ps = c.prepareStatement("select * from humo_cards c where c.card = ? and c.client = ?");
					ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
					ps.setString(2, list.get(i).getCardlist().get(j).getCLIENT_ID());
					ISLogger.getLogger().error("FIX CARDS list.get(i).getCardlist().get(j).getCARD(): "
							+ list.get(i).getCardlist().get(j).getCARD());
					System.out.println("list.get(i).getCardlist().get(j).getCARD(): "
							+ list.get(i).getCardlist().get(j).getCARD());
					rs = ps.executeQuery();
					if (rs.next()) {
						ps = c.prepareStatement("select * from humo_cards c where c.card = ? and c.real_card = ? and client = ?");
						ps.setString(1, list.get(i).getCardlist().get(j).getCARD());
						ps.setString(2, real_card);
						ps.setString(3, list.get(i).getCardlist().get(j).getCLIENT_ID());
						rs = ps.executeQuery();
						if (!rs.next()) {
							ISLogger.getLogger().error("Update fixCards info for CARD: "
									+ list.get(i).getCardlist().get(j).getCARD());
							ps = c.prepareStatement("update humo_cards set client_b = ?, expiry1 = ?, account_no = ?, real_card = ? where card = ? and client = ?");
							ps.setString(1, clientB);
							ps.setString(2, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
									: simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
							ps.setString(3, list.get(i).getCardlist().get(j).getACCOUNT_NO());
							ps.setString(4, real_card);
							ps.setString(5, list.get(i).getCardlist().get(j).getCARD());
							ps.setString(6, list.get(i).getCardlist().get(j).getCLIENT_ID());
							ps.executeUpdate();
						}

						c.commit();
					} else {
						ps = c.prepareStatement("select * from humo_cards c where c.real_card = ? and c.client = ?");
						ps.setString(1, real_card);
						ps.setString(2, list.get(i).getCardlist().get(j).getCLIENT_ID());
						rs = ps.executeQuery();
						if (!rs.next()) {
							ISLogger.getLogger().error("INSERT FIX CARD");
							ps = c.prepareStatement("insert into humo_cards (client, card, status1, status2, expiry1, stop_cause, real_card, card_acct, account_no, card_name, branch, client_b, mc_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							ISLogger.getLogger().error("FIX CARD BANK ACCOUNT: "
									+ list.get(i).getCardlist().get(j).getBank_account());
							ps.setString(1, list.get(i).getCardlist().get(j).getCLIENT_ID());
							ps.setString(2, list.get(i).getCardlist().get(j).getCARD());
							ps.setString(3, list.get(i).getCardlist().get(j).getSTATUS());
							ps.setString(4, list.get(i).getCardlist().get(j).getSTATUS2());
							ps.setString(5, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
									: simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
							ps.setString(6, list.get(i).getCardlist().get(j).getSTOP_CAUSE());
							ps.setString(7, real_card);
							ps.setString(8, list.get(i).getCardlist().get(j).getCARD_ACCT());
							ps.setString(9, list.get(i).getCardlist().get(j).getACCOUNT_NO());
							ps.setString(10, list.get(i).getCardlist().get(j).getCARD_String());
							ps.setString(11, branch);
							ps.setString(12, list.get(i).getCardlist().get(j).getBank_account() == null ? ""
									: list.get(i).getCardlist().get(j).getBank_account().length() > 16 ? list.get(i).getCardlist().get(j).getBank_account().substring(9, 17)
											: "");
							ps.setString(13, list.get(i).getCardlist().get(j).getCARD_String());
							ps.execute();
							c.commit();
						}
					}
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}*/

	public static void fixCard(List<AccInfo> list, IssuingPortProxy issuingPortProxy, String branch, String alias, Label label) throws JsonProcessingException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			final String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			final String groupC = ConnectionPool.getValue("HUMO_GROUPC");
			for (int i = 0; i < list.size(); i++) {
				String index = String.valueOf(i);
				label.setValue(index);
				String sql1 = "select real_card from humo_cards c where c.client = '"
						+ list.get(i).getClient() + "'";
				String sql2 = " and c.real_card not in (";
				String sql3 = ");";
				for (int j = 0; j < list.get(i).getCardlist().size(); j++) {
					ISLogger.getLogger().error("fixCard card: "
							+ list.get(i).getCardlist().get(j).getCARD());
					String real_card = getRealCardNumber(list.get(i).getCardlist().get(j).getCARD(), issuingPortProxy, bankC, groupC);
					ISLogger.getLogger().error("fixCard real_card: "
							+ real_card);
					if (j + 1 == list.get(i).getCardlist().size()) {
						sql2 += "'" + real_card + "'";
					} else {
						sql2 += "'" + real_card + "',";
					}
				}
				String sql = sql1 + sql2 + sql3;
				ISLogger.getLogger().error("fixCard sql string: " + sql);
				ps = c.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					String wrongCard = rs.getString("real_card");
					ps = c.prepareStatement("delete humo_cards c where c.real_card = ? and c.client = ?");
					ps.setString(1, wrongCard);
					ps.setString(2, list.get(i).getClient());
					ps.execute();
				}
			}
			c.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

/*	public static void getMissingInfo(List<AccInfo> list, IssuingPortProxy issuingPortProxy, String branch, String alias, Label label) {
		String firstPattern = "yyyy-MM-dd";
		String secondPattern = "dd.MM.yyyy";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(firstPattern);
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(secondPattern);
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			final String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			final String groupC = ConnectionPool.getValue("HUMO_GROUPC");
			for (int i = 0; i < list.size(); i++) {
				String index = String.valueOf(i);
				label.setValue(index);
				for (int j = 0; j < list.get(i).getCardlist().size(); j++) {
					String real_card = getRealCardNumber(list.get(i).getCardlist().get(j).getCARD(), issuingPortProxy, bankC, groupC);
					ISLogger.getLogger().error("getMissingInfo real_card: "
							+ real_card);
					ISLogger.getLogger().error("getMissingInfo EXPIRY1: "
							+ list.get(i).getCardlist().get(j).getEXPIRY());
					ISLogger.getLogger().error("getMissingInfo EXPIRY2: "
							+ list.get(i).getCardlist().get(j).getEXPIRY2());
					ISLogger.getLogger().error("getMissingInfo client_b: "
							+ list.get(i).getCardlist().get(j).getBank_account().substring(9, 17));
					ps = c.prepareStatement("update humo_cards c set c.client_b = ?, c.expiry1 = ?, c.branch = ? where c.real_card = ?");
					ps.setString(1, list.get(i).getCardlist().get(j).getBank_account().substring(9, 17));
					ps.setString(2, list.get(i).getCardlist().get(j).getEXPIRY() == null ? ""
							: simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 10))));
					// ps.setString(3,list.get(i).getCardlist().get(j).getEXPIRY2()
					// == null ? "" :
					// simpleDateFormat2.format(simpleDateFormat1.parse(list.get(i).getCardlist().get(j).getEXPIRY2().substring(0,
					// 10))));
					ps.setString(3, branch);
					ps.setString(4, real_card);
					ps.execute();
					c.commit();
					ps = c.prepareStatement("update bf_empc_accounts a set a.created_date = ? where a.account_no = ? and a.client = ? and card_acct = ?");
					String createdDate = String.valueOf(Integer.parseInt(list.get(i).getCardlist().get(j).getEXPIRY().substring(0, 4)) - 4)
							+ list.get(i).getCardlist().get(j).getEXPIRY().substring(4, 8)
							+ "01";
					ps.setString(1, simpleDateFormat2.format(simpleDateFormat1.parse(createdDate)));
					ps.setString(2, list.get(i).getCardlist().get(j).getACCOUNT_NO());
					ps.setString(3, list.get(i).getCardlist().get(j).getCLIENT_ID());
					ps.setString(3, list.get(i).getCardlist().get(j).getCARD_ACCT());
					ps.execute();
					c.commit();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}*/

	public static List<String> getNullB() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select client from humo_cards where client_b is null and client is not null");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("client"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error("CustomerService.getNullB ERROR: " + e);
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

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
			// ps.setDate(5,useractionslog.getAction_date());
			ps.setInt(5, useractionslog.getAct_type());
			ps.setFloat(6, useractionslog.getEntity_type());
			ps.setString(7, useractionslog.getEntity_id());
			ps.setString(8, useractionslog.getBranch() != null ? useractionslog.getBranch()
					: "00000");
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		// return useractionslog;
	}

	public static String getIp() {
		HttpServletRequest hr = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String ipAddress = hr.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = hr.getHeader("X_FORWARDED_FOR");
			if (ipAddress == null) {
				ipAddress = hr.getRemoteAddr();
			}
		}
		return ipAddress;
	}

	public static List<String> getCardsForFix() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select distinct client from humo_cards where real_card in (select real_card from (select count(c.real_card) count, c.real_card from humo_cards c group by c.real_card) where count > 1)");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("client"));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error("CustomerService.getCardsForFix ERROR: "
					+ e);
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

    public static Res addNewAgreement(HashMap<String, EmpcSettings> settingsForCard, String branch,
            Customer newCustomer, GlobuzAccount acc, Tclient tc, String alias,
            globus.IssuingWS.IssuingPortProxy issuingPortProxy, int uid, String un,
            String soapEndpointUrl, String soapAction, String productType, Res isMiniBank) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Res res = new Res();
		boolean agreementExists = false;
		BigDecimal agreementKey = null;
		List<AccInfo> listAcc = null;
		try {
			listAcc = getAccInfo(branch, tc.getClient(), alias, issuingPortProxy, settingsForCard);
		} catch (Exception e1) {
			ISLogger.getLogger().error("getAccInfo error ", e1);
		}
		if(listAcc != null) {
		    agreementKey = getAgreementKey(listAcc);
		    agreementExists = true;
		}
		if (agreementExists) {
			ISLogger.getLogger().error("Agreement exists for client " + newCustomer.getTieto_customer_id());
			try {
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(settingsForCard.get(branch).getBank_c());
				ISLogger.getLogger().error("Issuing bank_c: "
						+ settingsForCard.get(branch).getBank_c());
				connectionInfo.setGROUPC(settingsForCard.get(branch).getGroup_c());
				ISLogger.getLogger().error("Issuing group_c: "
						+ settingsForCard.get(branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

				KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
				mainAgreementInfo.setAGRE_NOM(agreementKey);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new java.util.Date());
				globus.issuing_v_01_02_xsd.ListType_AccountInfo accInfo = new ListType_AccountInfo();
				globus.issuing_v_01_02_xsd.RowType_AccountInfo[] rows = new globus.issuing_v_01_02_xsd.RowType_AccountInfo[1];
				globus.issuing_v_01_02_xsd.RowType_AccountInfo row = new RowType_AccountInfo();
				globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
				base_info.setC_ACCNT_TYPE("00");
				base_info.setCARD_ACCT(acc.getId());
				base_info.setCCY("UZS");
				base_info.setCYCLE("001");
				base_info.setMIN_BAL(BigDecimal.ZERO);
				base_info.setSTAT_CHANGE("1");
				base_info.setCRD(BigDecimal.ZERO);
				base_info.setNON_REDUCE_BAL(BigDecimal.ZERO);
				base_info.setLIM_INTR(BigDecimal.ZERO);
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    base_info.setCOND_SET(productType.equals("9") ? "015" : "001");
                } else {
                    base_info.setCOND_SET("001");
                }
				base_info.setSTATUS("0");
				base_info.setACC_PRTY("1");
				base_info.setTRANZ_ACCT(acc.getId());
				row.setBase_info(base_info);
				ObjectMapper mapper = new ObjectMapper();
				ISLogger.getLogger().error("base_info: "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(base_info));
				rows[0] = row;
				accInfo.setRow(rows);
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder(accInfo);
				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    logicalCard.setCOND_SET(productType.equals("9") ? "015" : "001");
                } else {
                    logicalCard.setCOND_SET("001");
                }
				logicalCard.setRISK_LEVEL("A");
				logicalCard.setBASE_SUPP("1");
				logicalCard.setF_NAMES(newCustomer.getP_first_name());
				logicalCard.setSURNAME(newCustomer.getP_family());
				logicalCard.setRANGE_ID(new BigDecimal(settingsForCard.get(branch).getRange_id()));
				logicalCard.setCARD_TYPE("01");
				logicalCard.setBRANCH(settingsForCard.get(branch).getBranch_id());
				//Если минибанк, то заполняем U_FIELD8 в LogicalCard. Если обычный МФО, то U_FIELD вообще не отправляется!
				//If card supposed to be open in minibank, we fill U_FIELD8 with minibank branch. If it's simle branch, we don't send U_FIELD8 at all!
				if(isMiniBank.getCode() == 0) {
					logicalCard.setU_FIELD8(branch + "-" + isMiniBank.getName());
				}
				
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
                physicalCard.setCARD_NAME((newCustomer.getP_first_name() + " " + newCustomer.getP_family()).length() > 24 ? 
                        (newCustomer.getP_first_name() + " " + newCustomer.getP_family()).substring(0, 23)
                        : newCustomer.getP_first_name() + " " + newCustomer.getP_family());
                physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(340 < Integer.parseInt(acc.getId().substring(17)) && Integer.parseInt(acc.getId().substring(17)) < 350 ? BigDecimal.valueOf(3) : BigDecimal.valueOf(1));
				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				eMV_Data.setCHIP_APP_ID(new BigDecimal(settingsForCard.get(branch).getChip_app_id()));
				RowType_CardInfo cardInfo = new RowType_CardInfo();
				cardInfo.setLogicalCard(logicalCard);
				cardInfo.setPhysicalCard(physicalCard);
				cardInfo.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				cards.setRow(new RowType_CardInfo[] { cardInfo });
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
				cardsListInfo.value = cards;
				ISLogger.getLogger().error("cardInfo: "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardInfo));
				globus.issuing_v_01_02_xsd.RowType_Agreement agrInfo = new RowType_Agreement();
				Calendar cal = Calendar.getInstance();
				agrInfo.setAGRE_NOM(agreementKey);
				agrInfo.setBINCOD(settingsForCard.get(branch).getBincod());
				agrInfo.setSTREET(newCustomer.getR_STREET() == null ? "STREET"
						: newCustomer.getR_STREET());
				agrInfo.setCITY(newCustomer.getR_CITY() == null ? "UZB"
						: newCustomer.getR_CITY());
				agrInfo.setCOUNTRY(newCustomer.getR_CNTRY() == null ? "UZB"
						: newCustomer.getR_CNTRY());
				agrInfo.setREP_LANG("1");
				agrInfo.setSTATUS("10");
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    agrInfo.setPRODUCT(productType.equals("9") ? "02" : "01");
                } else {
                    agrInfo.setPRODUCT("01");
                }           
				agrInfo.setENROLLED(cal);
				agrInfo.setCLIENT(newCustomer.getTieto_customer_id());
				agrInfo.setBRANCH(settingsForCard.get(branch).getBranch_id());
				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agrInfo);
				OperationResponseInfo responseInfo = new OperationResponseInfo();
				ISLogger.getLogger().error("request addNewAgreement agrExists for client "+newCustomer.getTieto_customer_id()+"\n"
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo)
						+ "\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo)
						+ "\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo)
						+ "\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
				try {
				responseInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
				} catch(NullPointerException npe) {
                    ISLogger.getLogger().error("newAgreement error ", npe);
                        ISLogger.getLogger().error(
                                objectMapper.writeValueAsString("NullPointerException newAgreement agrExists for client "
                                        + newCustomer.getTieto_customer_id() + "\n"
                                        + objectMapper.writeValueAsString(agreementInfo))
                                        + "\n" + objectMapper.writeValueAsString(accountsListInfo)
                                        + "\n" + objectMapper.writeValueAsString(cardsListInfo));
				} catch (RemoteException re) {
                    ISLogger.getLogger().error(
                            objectMapper.writeValueAsString("RemoteException newAgreement agrExists for client "
                                    + newCustomer.getTieto_customer_id() + "\n"
                                    + objectMapper.writeValueAsString(agreementInfo))
                                    + "\n" + objectMapper.writeValueAsString(accountsListInfo)
                                    + "\n" + objectMapper.writeValueAsString(cardsListInfo));
                }
	            ISLogger.getLogger().error("response addNewAgreement agrExists for client " + newCustomer.getTieto_customer_id() +"\n"
	                    + objectMapper.writeValueAsString(responseInfo)
	                    + "\n" + objectMapper.writeValueAsString(agreementInfo)
	                    + "\n" + objectMapper.writeValueAsString(accountsListInfo)
	                    + "\n" + objectMapper.writeValueAsString(cardsListInfo));
                if(responseInfo.getResponse_code().intValue() != 0) {
                    res.setCode(-1);
                    res.setName(responseInfo.getError_description());
                    return res;
                }
				if (cardsListInfo.value.getRow(0).getLogicalCard().getCARD() == null) {
					res.setCode(0);
					res.setName("Проверьте результат запроса в окне Операции с картами");
				} else {
					String phoneNumber = getUzcardPhoneNumber(newCustomer.getId_client(), branch);
					if (!phoneNumber.isEmpty()) {
						final java.util.Date exp1 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1() == null) ? null
								: cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTime();
						Res smsRes = activateSms(cardsListInfo.value.getRow(0).getLogicalCard().getCARD(), String.valueOf(exp1.getTime()).substring(5, 7)
								+ String.valueOf(exp1.getTime()).substring(2, 4), "on", phoneNumber, settingsForCard, branch, issuingPortProxy, cardsListInfo, uid, un, soapEndpointUrl, soapAction);
						ISLogger.getLogger().error("smsRes: "
								+ smsRes.getName());
					}
					Connection c = null;
					try {
						ISLogger.getLogger().error("PERED INSERTAMI");
						c = ConnectionPool.getConnection();
						CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Открыта карта No ["
								+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD()
								+ "]"));
                        ISLogger.getLogger().error(
                                "response newAgreement agrExists for client "
                                        + newCustomer.getTieto_customer_id()
                                        + "\n"
                                        + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo) 
                                        + "\n"
                                        + objectMapper.writeValueAsString(accountsListInfo));
						Res resCard = CustomerService.insertCards(cardsListInfo, accountsListInfo, newCustomer.getId_client(), newCustomer.getBranch(), c);
						java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						Res resAccount = CustomerService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
						Res resAgreement = CustomerService.insertAgreement(connectionInfo, agreementInfo, c);
						c.commit();
	                    ISLogger.getLogger().error("AGREEMENT EXISTS NEW CARD: "
	                                + cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
	                    Res resRealCard = psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
	                    if(resCard.getCode() != 0 || resAccount.getCode() != 0 || resAgreement.getCode() != 0 || resRealCard.getCode() != 0) {
	                        List<AccInfo> showCardsList = TclientService.getAccInfoShowCards(branch,
	                                newCustomer.getTieto_customer_id(), alias, issuingPortProxy);
	                        checkCard(showCardsList, newCustomer.getId_client(), issuingPortProxy, branch, alias, settingsForCard);
	                    }
						res.setCode(1);
						res.setName("Карта открыта в EMPC");
					} catch (Exception e) {
						e.printStackTrace();
						res.setCode(0);
						res.setName(e.getMessage());
						ISLogger.getLogger().error(CheckNull.getPstr(e));
						Utils.rollback(c);
					} finally {
						ConnectionPool.close(c);
					}
				}
			} catch (Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				res.setCode(0);
				res.setName(e.getMessage());
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}

		else {
            ISLogger.getLogger().error("Agreement doesn't exist for client " + newCustomer.getTieto_customer_id());
			try {
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(settingsForCard.get(branch).getBank_c());
				connectionInfo.setGROUPC(settingsForCard.get(branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new java.util.Date());

				RowType_Agreement agreement = new RowType_Agreement();
				// !
				agreement.setBRANCH(settingsForCard.get(branch).getBranch_id());
				// !
				agreement.setCLIENT(tc.getClient());
				agreement.setBANK_CODE(tc.getBank_c());
				agreement.setBINCOD(settingsForCard.get(branch).getBincod());
				agreement.setCITY("Tashkent");
				agreement.setENROLLED(calendar);
				agreement.setREP_LANG("1");
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    agreement.setPRODUCT(productType.equals("9") ? "02" : "01");
                } else {
                    agreement.setPRODUCT("01");
                }
				agreement.setRISK_LEVEL("A");
				agreement.setSTATUS(tc.getStatus());
				agreement.setSTREET(tc.getR_street());
				agreement.setE_MAILS(newCustomer.getR_E_MAILS());
				agreement.setCONTRACT("00" + newCustomer.getId_client());
				agreement.setSTREET("STREET");
				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agreement);
				RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
				base_info.setCREATED_DATE(calendar);
				base_info.setCCY("UZS");
				base_info.setCRD(BigDecimal.valueOf(0));
				base_info.setMIN_BAL(BigDecimal.valueOf(0));
				base_info.setC_ACCNT_TYPE("00");
				base_info.setNON_REDUCE_BAL(BigDecimal.valueOf(0));
				base_info.setSTATUS("0");
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    base_info.setCOND_SET(productType.equals("9") ? "015" : "001");
                } else {
                    base_info.setCOND_SET("001");
                }
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
                if (settingsForCard.get(branch).getBank_c().equals("03")) {
                    logicalCard.setCOND_SET(productType.equals("9") ? "015" : "001");
                } else {
                    logicalCard.setCOND_SET("001");
                }
				logicalCard.setRISK_LEVEL("A");
				logicalCard.setBASE_SUPP("1");
				logicalCard.setF_NAMES(newCustomer.getP_first_name());
				logicalCard.setSURNAME(newCustomer.getP_family());

				logicalCard.setRANGE_ID(new BigDecimal(settingsForCard.get(branch).getRange_id()));
				logicalCard.setCARD_TYPE("01");
				logicalCard.setBRANCH(settingsForCard.get(branch).getBranch_id());
				//Если минибанк, то заполняем U_FIELD8 в LogicalCard. Если обычный МФО, то U_FIELD вообще не отправляется!
				//If card supposed to be open in minibank, we fill U_FIELD8 with minibank branch. If it's simle branch, we don't send U_FIELD8 at all!
				if(isMiniBank.getCode() == 0) {
					logicalCard.setU_FIELD8(branch + "-" + isMiniBank.getName());
				}
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				physicalCard.setCARD_NAME((newCustomer.getP_first_name() + " " + newCustomer.getP_family()).length() > 24 ? (newCustomer.getP_first_name()
						+ " " + newCustomer.getP_family()).substring(0, 23)
						: newCustomer.getP_first_name() + " "
								+ newCustomer.getP_family());
				physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(340 < Integer.parseInt(acc.getId().substring(17)) && Integer.parseInt(acc.getId().substring(17)) < 350 ? BigDecimal.valueOf(3) : BigDecimal.valueOf(1));

				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				// !
				eMV_Data.setCHIP_APP_ID(new BigDecimal(settingsForCard.get(branch).getChip_app_id()));
				// !
				RowType_CardInfo cardInfo = new RowType_CardInfo();
				cardInfo.setLogicalCard(logicalCard);
				cardInfo.setPhysicalCard(physicalCard);
				cardInfo.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				cards.setRow(new RowType_CardInfo[] { cardInfo });

				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
				OperationResponseInfo responseInfo = new OperationResponseInfo();
				cardsListInfo.value = cards;
                ISLogger.getLogger().error("request addNewAgreement agrNotExists for client "+ newCustomer.getTieto_customer_id()
                        + "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo)
                        + "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo)
                        + "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo)
                        + "\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
                try {
                    responseInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
                } catch(NullPointerException npe) {
                    ISLogger.getLogger().error("newAgreement agrNotExists error ", npe);
                    ISLogger.getLogger().error(
                            objectMapper.writeValueAsString("NullPointerException newAgreement agrNotExists for client "
                                    + newCustomer.getTieto_customer_id() + "\n"
                                    + objectMapper.writeValueAsString(agreementInfo))
                                    + objectMapper.writeValueAsString(accountsListInfo)
                                    + objectMapper.writeValueAsString(cardsListInfo));
            }
                ISLogger.getLogger().error("response addNewAgreement agrNotExists for client " + newCustomer.getTieto_customer_id()
                        + "\n" + objectMapper.writeValueAsString(responseInfo)
                        + "\n" + objectMapper.writeValueAsString(agreementInfo)
                        + "\n" + objectMapper.writeValueAsString(accountsListInfo)
                        + "\n" + objectMapper.writeValueAsString(cardsListInfo));
                if(responseInfo.getResponse_code().intValue() != 0) {
                    res.setCode(-1);
                    res.setName(responseInfo.getError_description());
                    return res;
                }

            if (cardsListInfo.value.getRow(0).getLogicalCard().getCARD() == null) {
                res.setCode(0);
                res.setName("Проверьте результат запроса в окне Операции с картами");
            } else {
					String phoneNumber = getUzcardPhoneNumber(newCustomer.getId_client(), branch);
					if (!phoneNumber.isEmpty()) {
						final java.util.Date exp1 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1() == null) ? null
								: cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTime();
						Res smsRes = activateSms(cardsListInfo.value.getRow(0).getLogicalCard().getCARD(), String.valueOf(exp1.getTime()).substring(5, 7)
								+ String.valueOf(exp1.getTime()).substring(2, 4), "on", phoneNumber, settingsForCard, branch, issuingPortProxy, cardsListInfo, uid, un, soapEndpointUrl, soapAction);
						ISLogger.getLogger().error("smsRes: "
								+ smsRes.getName());
					}
					Connection c = null;
					try {
						ISLogger.getLogger().info("PERED INSERTAMI");
						CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Открыта карта No ["
								+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD()
								+ "]"));
						c = ConnectionPool.getConnection();
                        Res resCard = CustomerService.insertCards(cardsListInfo, accountsListInfo, newCustomer.getId_client(), newCustomer.getBranch(), c);
                        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        Res resAccount = CustomerService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
                        Res resAgreement = CustomerService.insertAgreement(connectionInfo, agreementInfo, c);
                        c.commit();
                        ISLogger.getLogger().error("AGREEMENT agrNotExists NEW CARD: "
                                    + cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
                        Res resRealCard = psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
                        if(resCard.getCode() != 0 || resAccount.getCode() != 0 || resAgreement.getCode() != 0 || resRealCard.getCode() != 0) {
                            List<AccInfo> showCardsList = TclientService.getAccInfoShowCards(branch,
                                    newCustomer.getTieto_customer_id(), alias, issuingPortProxy);
                            checkCard(showCardsList, newCustomer.getId_client(), issuingPortProxy, branch, alias, settingsForCard);
                        }
                        res.setCode(1);
                        res.setName("Карта открыта в EMPC");
					} catch (Exception e) {
						e.printStackTrace();
						res.setCode(0);
						res.setName(e.getMessage());
						ISLogger.getLogger().error(CheckNull.getPstr(e));
						Utils.rollback(c);
					} finally {
						ConnectionPool.close(c);
					}
				}
			} catch (RemoteException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				res.setCode(0);
				res.setName(e.getMessage());
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}
		return res;
	}

	/*
	 * public static void fixAgreement(IssuingPortProxy issuingPortProxy, String
	 * branch, String alias, HashMap<String, EmpcSettings> settings){ Connection
	 * c = null; ResultSet rs = null; try { c = ConnectionPool.getConnection();
	 * PreparedStatement ps = c.prepareStatement(
	 * "select l.branch, l.card_acct, l.account_no, l.card, l.real_card, l.* from  HUMO_CARDS l  where  l.account_no in "
	 * + "(select k.account_no from  HUMO_CARDS  h," +
	 * "bf_empc_accounts  k where  h.account_no=k.account_no and substr (k.card_acct,3,5) <>h.branch"
	 * +
	 * " and  substr (h.real_card,1,8) in ('98600301', '98600303','98600304','98600305') and  k.card_acct not like'22618%')"
	 * ); } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally{ ConnectionPool.close(c); }
	 * 
	 * }
	 */

	public static Res createCustomerAndAgreement(int uid, String un, String pwd, String orderF, String orderP, HashMap<String, EmpcSettings> settings, String branch, String clientId, String cln_cat, String tel_pwd, String alias, Connection c, globus.IssuingWS.IssuingPortProxy issuingPortProxy) throws Exception {
		ISLogger.getLogger().error("CREATE CUSTOMER AND AGREEMENT");
		ObjectMapper objectMapper = new ObjectMapper();
		Res res = new Res();
		String msg = null;
		String account = null;
		BigDecimal agre_nom = null;
		List<AccInfo> listAcc = null;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder();
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder();
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		final Customer new_customer = CustomerService.getClientpById(clientId, branch, alias);
		customerInfo = getNewClient(clientId, branch, cln_cat, tel_pwd, alias, settings, new_customer, un, pwd);
		if (customerInfo == null) {
			res.setCode(1);
			res.setName("Данный клиент уже открыт в Хумо.");
			return res;
		}
		if(customerInfo.value.getPERSON_CODE() == null) {
		    res.setCode(1);
		    res.setName("У данного клиента не указан ПИНФЛ. Регистрация в Хумо не может быть проведена.");
		    return res;
		}
		if(customerInfo.value.getSERIAL_NO() == null || customerInfo.value.getID_CARD() == null) {
		    res.setCode(1);
		    res.setName("Не указаны паспортные данные клиента. Регистрация в Хумо не может быть проведена.");
		}
		account = CustomerService.getAccount(c, branch, clientId, orderF, orderP);
		if (account == null) {
			ISLogger.getLogger().error("acc is null");
			String acc = "22618";
			if (cln_cat.equals("003")) {
				acc = "22617";
			}
			res = open_acc(un, pwd, orderF, orderP, clientId, branch, 0, acc);
			if (res.getCode() != 0) {
				if (res.getName() != null) {
					if (res.getName().equals("Идёт открытие/закрытие дня")) {
						return res;
					}
				}
			}
			account = res.getName();
		}
		final GlobuzAccount gba = new GlobuzAccount();
		gba.setId(account);
		gba.setBranch(branch);
		res = GlobuzAccountService.doAction_acc(un, pwd, 2, 2, gba, 2, alias, Boolean.valueOf(true));
		if (res.getCode() != 0) {
			msg = res.getName();
			System.out.println("msg2 = " + msg);
		}
		res = CustomerService.update_lnk_set_acc(un, pwd, branch, clientId, gba, alias, null);
		if (res.getCode() != 1) {
			msg = res.getName();
			System.out.println("mstg 3 = " + msg);
		}

		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		mainAgreementInfo.setAGRE_NOM(agre_nom);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		globus.issuing_v_01_02_xsd.ListType_AccountInfo accInfo = new ListType_AccountInfo();
		globus.issuing_v_01_02_xsd.RowType_AccountInfo[] rows = new globus.issuing_v_01_02_xsd.RowType_AccountInfo[1];
		globus.issuing_v_01_02_xsd.RowType_AccountInfo row = new RowType_AccountInfo();
		globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();

		base_info.setC_ACCNT_TYPE("00");
		base_info.setCARD_ACCT(account);
		base_info.setCCY("UZS");
		base_info.setCYCLE("001");
		base_info.setMIN_BAL(BigDecimal.ZERO);
		base_info.setSTAT_CHANGE("1");
		base_info.setCRD(BigDecimal.ZERO);
		base_info.setNON_REDUCE_BAL(BigDecimal.ZERO);
		base_info.setLIM_INTR(BigDecimal.ZERO);
		base_info.setCOND_SET("001");
		base_info.setSTATUS("0");
		base_info.setACC_PRTY("1");
		base_info.setTRANZ_ACCT(account);
		row.setBase_info(base_info);
		ObjectMapper mapper = new ObjectMapper();
		ISLogger.getLogger().error("base_info: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(base_info));
		rows[0] = row;
		accInfo.setRow(rows);
		accountsListInfo = new ListType_AccountInfoHolder(accInfo);

		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCOND_SET("001");
		logicalCard.setRISK_LEVEL("A");
		logicalCard.setBASE_SUPP("1");
		logicalCard.setF_NAMES(new_customer.getP_first_name());
		logicalCard.setSURNAME(new_customer.getP_family());
		logicalCard.setRANGE_ID(new BigDecimal(settings.get(branch).getRange_id()));
		logicalCard.setCARD_TYPE("01");
		logicalCard.setBRANCH(settings.get(branch).getBranch_id());
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		physicalCard.setCARD_NAME((new_customer.getP_first_name() + " " + new_customer.getP_family()).length() > 24 ? (new_customer.getP_first_name()
				+ " " + new_customer.getP_family()).substring(0, 23)
				: new_customer.getP_first_name() + " "
						+ new_customer.getP_family());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		eMV_Data.setCHIP_APP_ID(new BigDecimal(settings.get(branch).getChip_app_id()));
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(logicalCard);
		cardInfo.setPhysicalCard(physicalCard);
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;
		ISLogger.getLogger().error("cardInfo: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardInfo));
		globus.issuing_v_01_02_xsd.RowType_Agreement agrInfo = new RowType_Agreement();

		Calendar cal = Calendar.getInstance();
		agrInfo.setAGRE_NOM(agre_nom);
		agrInfo.setBINCOD(settings.get(branch).getBincod());
		agrInfo.setBRANCH(settings.get(branch).getBranch_id());
		agrInfo.setBANK_CODE(settings.get(branch).getBank_c());
		agrInfo.setSTREET(new_customer.getR_STREET() == null ? "STREET"
				: new_customer.getR_STREET());
		agrInfo.setCITY(new_customer.getR_CITY() == null ? "UZB"
				: new_customer.getR_CITY());
		agrInfo.setCOUNTRY(new_customer.getR_CNTRY() == null ? "UZB"
				: new_customer.getR_CNTRY());
		agrInfo.setREP_LANG("1");
		agrInfo.setSTATUS("10");
		agrInfo.setPRODUCT("01");
		agrInfo.setENROLLED(cal);
		agrInfo.setCLIENT(new_customer.getTieto_customer_id());
		ISLogger.getLogger().error("agrInfo: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agrInfo));
		agreementInfo = new RowType_AgreementHolder(agrInfo);
		OperationResponseInfo response = null;
		ISLogger.getLogger().error("newCustomerAndAgreement connectionInfo: "
				+ objectMapper.writeValueAsString(connectionInfo));
		ISLogger.getLogger().error("newCustomerAndAgreement customerInfo: "
				+ objectMapper.writeValueAsString(customerInfo));
		ISLogger.getLogger().error("newCustomerAndAgreement customListInfo: "
				+ objectMapper.writeValueAsString(customListInfo));
		ISLogger.getLogger().error("newCustomerAndAgreement agreementInfo: "
				+ objectMapper.writeValueAsString(agreementInfo));
		ISLogger.getLogger().error("newCustomerAndAgreement accountsListInfo: "
				+ objectMapper.writeValueAsString(accountsListInfo));
		ISLogger.getLogger().error("newCustomerAndAgreement cardsListInfo: "
				+ objectMapper.writeValueAsString(cardsListInfo));
		response = issuingPortProxy.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
		if (response == null || response.getError_description() != null) {
			res.setCode(0);
			res.setName("Ошибка при отправке данных в Хумо."
					+ response.getError_description());
			ISLogger.getLogger().error("Ошибка при отправке данных в Хумо: "
					+ response.getError_description());
			return res;
		} else {
			try {
				CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Открыта карта No ["
						+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD()
						+ "]"));
				ISLogger.getLogger().error("createCustomerAndAgreement INSERT");
				Res resInsertClient = CustomerService.insertClient(customerInfo, c, alias);
				Res resInsertCards = new Res();
				if(!checkClientExistenceByAccountNo(accountsListInfo.value.getRow(0).getBase_info().getACCOUNT_NO().toString())){
					resInsertCards = CustomerService.insertCards(cardsListInfo, accountsListInfo, new_customer.getId_client(), new_customer.getBranch(), c);
				}
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Res resInsertAccounts = CustomerService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
				CustomerService.insertAgreement(connectionInfo, agreementInfo, c);
				c.commit();
				Res resCreateLink = CustomerService.create_lnk(branch, new_customer.getId_client(), agreementInfo.value.getCLIENT(), accountsListInfo.value.getRow(0).getBase_info().getTRANZ_ACCT(), alias);
				if(resInsertClient.getCode() != 0 || resCreateLink.getCode() != 0) {
					ISLogger.getLogger().error("resInsertClient for bank client " + new_customer.getId_client()
							+ "\n" + objectMapper.writeValueAsString(resInsertClient)
							+ "\nresCreateLink "
							+ "\n" + objectMapper.writeValueAsString(resCreateLink));
					getClientFromTietoByClientB(issuingPortProxy, new_customer.getId_client(), settings.get(branch).getBank_c(), settings.get(branch).getGroup_c(), branch, un, pwd, alias);
				}
				if(resInsertCards.getCode() != 0 || resInsertAccounts.getCode() != 0) {
					ISLogger.getLogger().error("resInsertCards for bank client " + new_customer.getId_client()
							+ "\n" + objectMapper.writeValueAsString(resInsertCards)
							+ "\n" + objectMapper.writeValueAsString(resInsertAccounts));
					List<AccInfo> showCardsList = TclientService.getAccInfoShowCards(branch,
                            new_customer.getId_client(), alias, issuingPortProxy);
					checkCard(showCardsList, new_customer.getId_client(), issuingPortProxy, branch, alias, settings);
				}
				ISLogger.getLogger().error("createCustomerAndAgreement NEW CARD: "
						+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
				psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
				res.setCode(1);
				res.setName("Клиент и карта открыты!");
			} catch (Exception e) {
				e.printStackTrace();
				res.setCode(0);
				res.setName(e.getMessage());
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				Utils.rollback(c);
			}

		}
		return res;
	}

    private static RowType_CustomerHolder getNewClient(final String clientId, final String branch,
            final String cln_cat, final String tel_pwd, final String alias,
            HashMap<String, EmpcSettings> settings, Customer new_customer, String un, String pwd) throws Exception {
		if (CustomerService.checkClient(clientId, branch, un, pwd, alias)) {
			ISLogger.getLogger().error("checkClient client exists");
			return null;
		}
		OperationResponseInfo orInfo = null;
		final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		final Calendar calendar = Calendar.getInstance();
		java.util.Date date = new java.util.Date();
		calendar.setTime(date);
		final RowType_Customer rtc = new RowType_Customer();
		final Calendar cal_p = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();
		// final Customer new_customer =
		// CustomerService.getClientpById(clientId, branch, alias);
		new_customer.setP_first_name(new_customer.getP_first_name() == null ? ""
				: new_customer.getP_first_name());
		final String first_name = Utils.toTranslit(new_customer.getP_first_name());
		rtc.setF_NAMES(first_name);
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(String.valueOf(branch) + clientId);
		final String surname = Utils.toTranslit(new_customer.getP_family());
		rtc.setSURNAME(surname == null ? "" : surname);
		cal_p.setTime(new_customer.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal_p);
		cal.setTime(new_customer.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT(new_customer.getCode_resident());
		rtc.setSTATUS("10");
		rtc.setSEX(new_customer.getP_code_gender());
		rtc.setSERIAL_NO(String.valueOf(new_customer.getP_passport_serial()));
		rtc.setID_CARD(new_customer.getP_passport_number());
		rtc.setR_CITY(new_customer.getP_code_citizenship());
		if (new_customer.getP_birth_place() == null) {
			new_customer.setP_birth_place("Uzbekiston");
		}
		final String street = Utils.toTranslit(new_customer.getP_birth_place());
		rtc.setR_STREET(street == null ? "" : street);
		rtc.setR_E_MAILS(new_customer.getP_email_address());
		rtc.setR_MOB_PHONE(new_customer.getP_phone_mobile());
		rtc.setR_PHONE(new_customer.getP_phone_home());
		final String cntry = (new_customer.getP_code_citizenship() == null) ? "UZB"
				: ((new_customer.getP_code_citizenship().compareTo("860") == 0) ? "UZB"
						: null);
		rtc.setR_CNTRY(cntry);
		final String place_registr = Utils.toTranslit(new_customer.getP_passport_place_registration());
		rtc.setISSUED_BY(place_registr == null ? "" : place_registr);
		rtc.setPERSON_CODE(new_customer.getPinfl());
		rtc.setCLN_CAT(cln_cat);
		rtc.setDOC_TYPE("001");
		rtc.setREC_DATE(calendar);
		final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		return customerInfo;
	}

	public static Res open_acc(String un, String pwd, String ord, final String last, final String client, final String branch, final int group, String acc) {
		Res res = new Res();
		try {
			final String ccycd = "000";
			final String cl_id = client;
			final String alias = Utils.getALias(branch);
			final Customer cst = CustomerService.getCustomerById(cl_id, branch, alias);

			if (cst == null) {
				res.setCode(-1);
				res.setName("Не найден клиент с номером " + cl_id
						+ " в филиале " + branch + " в схеме " + alias);
				return res;
			}
			ISLogger.getLogger().error("VER10 CLIENT ID: " + cl_id
					+ " CUSTOMER ID: " + cst.getId() + " CUSTOMER ID_CLIENT: "
					+ cst.getId_client() + " CUSTOMER NAME: " + cst.getName());

			final String customerName = (cst.getName().length() > 80) ? cst.getName().substring(0, 79)
					: cst.getName();
			ord = CustomerService.Get_acc_hole(acc, ord, last, cst.getId_client(), branch, alias).getName();
			final String customer_id = cst.getId_client();
			final boolean brcomp = branch.compareTo(ConnectionPool.getValue("HO", alias)) == 0;
			res = GlobuzAccountService.doAction_create_acc_in_br(un, pwd, acc, customer_id, ccycd, ord, customerName, group, alias, branch, Boolean.valueOf(brcomp));
			if (res.getCode() != 0) {
				return res;
			}
			final String result = res.getName();
			res.setName(result);
		} catch (Exception e) {
			res.setName("ERROR => " + e.getLocalizedMessage());
			res.setCode(-1);
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		return res;
	}

	public static Customer getClientpById(final String customerId, final String branch, final String alias) {
		ObjectMapper objectMapper = new ObjectMapper();
		Customer customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		try {
			c = ConnectionPool.getConnection(alias);
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			ps = c.prepareStatement("select p.branch,"
					+ "p.id, p.name, a.code_country, a.code_type, a.code_resident, a.code_subject, a.sign_registr, a.code_form, a.date_open, a.date_close,"
					+ "a.state, p.birthday, p.POST_ADDRESS, p.PASSPORT_TYPE, p.PASSPORT_SERIAL, p.PASSPORT_NUMBER, p.PASSPORT_PLACE_REGISTRATION, p.PASSPORT_DATE_REGISTRATION,"
					+ "p.CODE_TAX_ORG, p.NUMBER_TAX_REGISTRATION, p.CODE_BANK, p.CODE_CLASS_CREDIT, p.CODE_CITIZENSHIP, p.BIRTH_PLACE, p.code_capacity,"
					+ "p.capacity_status_date, p.capacity_status_place, p.num_certif_capacity, p.phone_home, p.phone_mobile, p.email_address, p.pension_sertif_serial,"
					+ "p.code_gender, p.code_nation, p.code_birth_region, p.code_birth_distr, p.type_document, p.passport_date_expiration, p.code_adr_region,"
					+ "p.code_adr_distr, p.inps, p.family as family, p.first_name as first_name, p.patronymic as patronymic, p.pinfl as pinfl"
					+ " from client_p p, client a where a.code_subject='P' and a.BRANCH = p.BRANCH and a.ID_CLIENT = p.ID and a.code_type = '08' and p.branch = ? and p.id = ?");
			ps.setString(1, branch);
			ps.setString(2, customerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id"));
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
				customer.setP_birthday(rs.getDate("birthday"));
				customer.setP_post_address(rs.getString("post_address"));
				customer.setP_passport_type(rs.getString("passport_type"));
				customer.setP_passport_serial(rs.getString("passport_serial"));
				customer.setP_passport_number(rs.getString("passport_number"));
				customer.setP_passport_place_registration(rs.getString("passport_place_registration"));
				customer.setP_passport_date_registration(rs.getDate("passport_date_registration"));
				customer.setP_code_tax_org(rs.getString("code_tax_org"));
				customer.setP_number_tax_registration(rs.getString("number_tax_registration"));
				customer.setP_code_bank(rs.getString("code_bank"));
				customer.setP_code_class_credit(rs.getString("code_class_credit"));
				customer.setP_code_citizenship(rs.getString("code_citizenship"));
				customer.setP_birth_place(rs.getString("birth_place"));
				customer.setP_code_capacity(rs.getString("code_capacity"));
				customer.setP_capacity_status_date(rs.getDate("capacity_status_date"));
				customer.setP_capacity_status_place(rs.getString("capacity_status_place"));
				customer.setP_num_certif_capacity(rs.getString("num_certif_capacity"));
				customer.setP_phone_home(rs.getString("phone_home"));
				customer.setP_phone_mobile(rs.getString("phone_mobile"));
				customer.setP_email_address(rs.getString("email_address"));
				customer.setP_pension_sertif_serial(rs.getString("pension_sertif_serial"));
				customer.setP_code_gender(rs.getString("code_gender"));
				customer.setP_code_nation(rs.getString("code_nation"));
				customer.setP_code_birth_region(rs.getString("code_birth_region"));
				customer.setP_code_birth_distr(rs.getString("code_birth_distr"));
				customer.setP_type_document(rs.getString("type_document"));
				customer.setP_passport_date_expiration(rs.getDate("passport_date_expiration"));
				customer.setP_code_adr_region(rs.getString("code_adr_region"));
				customer.setP_code_adr_distr(rs.getString("code_adr_distr"));
				customer.setP_inps(rs.getString("inps"));
				customer.setP_family((rs.getString("family") == null) ? "q"
						: rs.getString("family"));
				customer.setP_first_name((rs.getString("first_name") == null) ? "q"
						: rs.getString("first_name"));
				customer.setP_patronymic((rs.getString("patronymic") == null) ? "q"
						: rs.getString("patronymic"));
				customer.setP_number_tax_registration(rs.getString("pinfl") == null ? "1" : rs.getString("pinfl"));
				customer.setPinfl(rs.getString("pinfl"));
			}
			ISLogger.getLogger().error("newCustome and agreement: "
					+ objectMapper.writeValueAsString(customer));
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();

			return customer;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(inf);
			ConnectionPool.close(c);
		}
		return customer;
	}

	public static boolean checkAccountAssertion(String account, String branch, String alias) {
		boolean res = false;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select state from account where branch = ? and id = ?");
			ps.setString(1, branch);
			ps.setString(2, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("state").equals("2") ? true : false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String checkClientExistenceInBfEmpcClients(String clientId, String branch, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from bf_empc_clients where client_b = ? or client_b = ? and client is not null");
			ps.setString(1, clientId);
			ps.setString(2, branch + clientId);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("client");
			} else {
				return null;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error("CustomerService.checkClientExistenceInBfEmpcClients error: "
					+ e.getLocalizedMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return null;
	}

	public static void updateBfXumoCustomers(String tietoCustomerId, String bankCustomerId, String branch, String alias, String account) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bf_xumo_customers set tieto_customer_id = ?, cur_acc = ? where branch = ? and bank_customer_id = ?");
			ps.setString(1, tietoCustomerId);
			ps.setString(2, account);
			ps.setString(3, branch);
			ps.setString(4, bankCustomerId);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error("updateBfXumoCustomers error: "
					+ e.getLocalizedMessage());
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

    public static void updateBfXumoCustomers(String tietoCustomerId, String bankCustomerId,
            String branch, String un, String pwd, String alias) {
        Connection c = null;
        PreparedStatement ps = null;
        ISLogger.getLogger().error(
                "updateBfXumoCustomers clientId: " + tietoCustomerId + "\nbankClientId: "
                        + bankCustomerId + "\nbranch: " + branch + "\nalias: " + alias);
        String sql = "update bf_xumo_customers set tieto_customer_id = '"+tietoCustomerId+"' where bank_customer_id = '"+bankCustomerId+
        "' and branch ='"+branch+"'";
        ISLogger.getLogger().error("updateBfXumoCustomers sql: "+sql);
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(sql);
            ISLogger.getLogger().error("updateBfXumoCustomers rows updated: " + ps.executeUpdate());
            c.commit();
        } catch (Exception e) {
            ISLogger.getLogger().error("updateBfXumoCustomers error: " + e.getLocalizedMessage());
        } finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
    }
    
    public static void updateBfXumoCustomers(TietoCustomer tietoCustomer, Connection c) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update bf_xumo_customers\n" + 
            		"   set tieto_customer_id =\n" + 
            		"       (select client from bf_empc_clients where client_b = ?)\n" + 
            		" where bank_customer_id = ?\n" + 
            		"   and branch = ?");
            ps.setString(1, tietoCustomer.getBranch() + tietoCustomer.getBankCustomerId());
            ps.setString(2, tietoCustomer.getBankCustomerId());
            ps.setString(3, tietoCustomer.getBranch());
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            ISLogger.getLogger().error("updateBfXumoCustomers error: " + e.getLocalizedMessage());
        }finally {
            Utils.close(ps);
        }
    }

	public static Res psevdapan(String card) {
	    Res res = new Res();
		try {
			final IssuingPortProxy issuingPortProxy = new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"), ConnectionPool.getValue("HUMO_USERNAME"), ConnectionPool.getValue("HUMO_PASSWORD"));
			final String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			final String groupC = ConnectionPool.getValue("HUMO_GROUPC");
			final String realCardNumber = CustomerService.getRealCardNumber(card, issuingPortProxy, bankC, groupC);
			ISLogger.getLogger().error("psevdapan card " + card + " real_card " + realCardNumber);
			if(card.substring(12, 16).equals(realCardNumber.substring(12, 16))){
			res = insertRealCard(card, realCardNumber);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error("psevdapan ERROR: "
					+ e.getLocalizedMessage());
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		}
		return res;
	}

	public static Res insertRealCard(final String psevdaPAN, final String realCard) {
	    Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		ISLogger.getLogger().error("update humo_cards set real_card = " + realCard + " where card = " + psevdaPAN);
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set real_card = ? where card = ?");
			ps.setString(1, realCard);
			ps.setString(2, psevdaPAN);
			ps.executeUpdate();
			c.commit();
			res.setCode(0);
			res.setName("Успешно!");
		} catch (SQLException e) {
			ISLogger.getLogger().error("insertRealCard ERROR: "
					+ e.getLocalizedMessage());
			res.setCode(-1);
			res.setName(e.getLocalizedMessage());
			return res;
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getAccount(String branch, String bankCustomerId) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String account = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id from account where branch = ? and client = ?");
			ps.setString(1, branch);
			ps.setString(2, bankCustomerId);
			rs = ps.executeQuery();
			if (rs.next()) {
				account = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return account;
	}

	// TIETO ID CLIENT по зашифрованному номеру карты CARD
	public static String getTietoClientByCard(String card) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tietoClient = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select client from humo_cards where card = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			while (rs.next()) {
				tietoClient = rs.getString("client");
				ISLogger.getLogger().error("getTietoClientByCard card: " + card
						+ " client: " + tietoClient);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return tietoClient;
	}

	public static List<CardActions> getCardActions(String card, String branch) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CardActions> cardActions = new ArrayList<CardActions>();
		String sql = "select * from bf_user_actions_log where branch = "
				+ branch + " and entity_id like '%" + card
				+ "%' order by action_date desc";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CardActions action = new CardActions();
				action.setAction(rs.getString("entity_id"));
				action.setCard(card);
				action.setDate(rs.getDate("action_date").toString());
				action.setUser(rs.getString("user_name"));
				cardActions.add(action);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cardActions;
	}

	public static String getUzcardPhoneNumber(String clientB, String branch) {
		Connection c = null;
		String phoneNumber = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			String sql = ("select t.card_number, \n"
					+ "       substr(t.additional_data, 5, 12) tel\n"
					+ "  from CARD_ONLINE_NF_MESSAGE t, state_card s\n"
					+ " where t.state = s.id\n"
					+ "   and s.deal_id = 21\n"
					+ "   and t.processing_code = 970000\n"
					+ "and t.state=4\n"
					+ "   and rownum=1\n"
					+ "   and t.branch = '"
					+ branch
					+ "'\n"
					+ "   and t.card_number = (select card_number from card where branch = '"
					+ branch + "' and substr(def_atm_account, -11, 8) = '"
					+ clientB + "')\n" + "   order by t.stan desc");
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				phoneNumber = rs.getString("tel");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return phoneNumber;
	}

	public static Res activateSms(String cardNumber, String expiry, String action, String phoneNumber, HashMap<String, EmpcSettings> settings, String branch, IssuingPortProxy issuingPortProxy, ListType_CardInfoHolder cardsListInfo, int uid, String un, String soapEndpointUrl, String soapAction) {
		Res res = new Res();
		try {
			// SOAP Запрос
			final String message = operationWithCard(action, cardNumber, expiry, phoneNumber, issuingPortProxy, cardsListInfo, soapEndpointUrl, soapAction, branch, settings);
			final RowType_Customer rtc = new RowType_Customer();
			OperationResponseInfo orInfo = null;
			final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
			final RowType_EditCustomer_Request customerRequest = new RowType_EditCustomer_Request(CustomerService.getTietoClientByCard(cardNumber), settings.get(branch).getBank_c(), rtc.getCLIENT_B(), rtc.getCL_TYPE(), rtc.getCLN_CAT(), rtc.getREC_DATE(), rtc.getF_NAMES(), rtc.getSURNAME(), rtc.getTITLE(), rtc.getM_NAME(), rtc.getB_DATE(), rtc.getRESIDENT(), rtc.getID_CARD(), rtc.getDOC_TYPE(), action.equals("on") ? cardNumber
					: "", rtc.getEMP_NAME(), rtc.getPOSITION(), rtc.getEMP_DATE(), rtc.getWORK_PHONE(), rtc.getR_STREET(), rtc.getR_CITY(), rtc.getR_CNTRY(), rtc.getR_PCODE(), rtc.getC_SINCE(), rtc.getCMP_NAME(), rtc.getCMPG_NAME(), rtc.getCO_POSITON(), rtc.getCONTACT(), rtc.getCNT_TITLE(), rtc.getCNT_PHONE(), rtc.getCNT_FAX(), rtc.getMNG_POSIT(), rtc.getMNG_NAME(), rtc.getMNG_PHONE(), rtc.getMNG_TITLE(), rtc.getMNG_FAX(), rtc.getREG_NR(), rtc.getCR_STREET(), rtc.getCR_CITY(), rtc.getCR_CNTRY(), rtc.getCR_PCODE(), rtc.getCOMENT(), rtc.getREGION(), rtc.getPERSON_CODE(), rtc.getRESIDENT_SINCE(), rtc.getYEAR_INC(), rtc.getCCY_FOR_INCOM(), rtc.getIMM_PROP_VALUE(), rtc.getSEARCH_NAME(), rtc.getMARITAL_STATUS(), rtc.getCO_CODE(), rtc.getEMP_CODE(), rtc.getSEX(), rtc.getSERIAL_NO(), rtc.getDOC_SINCE(), rtc.getISSUED_BY(), rtc.getEMP_ADR(), rtc.getEMP_FAX(), rtc.getEMP_E_MAILS(), rtc.getR_E_MAILS(), action.equals("on") ? phoneNumber
					: "", rtc.getR_FAX(), rtc.getCNT_E_MAILS(), rtc.getCNT_MOB_PHONE(), rtc.getMNG_MOB_PHONE(), rtc.getMNG_E_MAILS(), rtc.getCR_E_MAILS(), rtc.getIN_FILE_NUM(), rtc.getU_COD1(), rtc.getU_COD2(), rtc.getU_COD3(), rtc.getU_FIELD1(), rtc.getU_FIELD2(), rtc.getCALL_ID(), rtc.getHOME_NUMBER(), rtc.getBUILDING(), rtc.getSTREET1(), rtc.getAPARTMENT(), rtc.getMIDLE_NAME(), rtc.getSTATUS(), (String) null, rtc.getAMEX_MEMBER_SINCE(), rtc.getDCI_MEMBER_SINCE(), rtc.getREWARD_NO());
			ObjectMapper mapper = new ObjectMapper();
			ISLogger.getLogger().error("editCustomer uzCard number to Humo: "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerRequest));

			// ИЗМЕНЯЕМ НОМЕР В ДАННЫХ КЛИЕНТА ЧЕРЕЗ editCustomer
			try {
				orInfo = issuingPortProxy.editCustomer(connectionInfo, customerRequest);
			} catch (RemoteException e2) {
				LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e2));
				e2.printStackTrace();
			}
			if ((message == null || message.equals("null"))
					&& orInfo.getError_description() == null) {
				if (action.equals("off")) {

				}

				// Логгирование после подключения/отключения СМС уведомлений
				CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Подключение СМС уведомлений по карте No ["
						+ cardNumber + "]. Действие: СМС " + action + ""));
				res.setName("Успешно");
			} else {
				res.setName("Ошибка: " + message
						+ orInfo.getError_description());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		return res;
	}

	public static String operationWithCard(final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy, ListType_CardInfoHolder cardsListInfo, String soapEndpointUrl, String soapAction, String branch, HashMap<String, EmpcSettings> settings) {
		final String message = callSoapWebService(soapEndpointUrl, soapAction, cardsListInfo.value.getRow(0).getLogicalCard().getCLIENT(), cardsListInfo.value.getRow(0).getPhysicalCard().getCARD_NAME(), state_card, card_number, expiry, phoneNumber, issuingPortProxy, branch, settings);
		return message;
	}

	public static void createSoapEnvelope(final SOAPMessage soapMessage, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy, HashMap<String, EmpcSettings> settings, String branch) throws SOAPException, RemoteException, ParseException {
		final SOAPPart soapPart = soapMessage.getSOAPPart();
		final SOAPEnvelope envelope = soapPart.getEnvelope();
		final String myNamespace = "urn";
		final String myNamespaceURI = String.valueOf(myNamespace) + ":"
				+ "AccessGateway";
		envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		final SOAPBody soapBody = envelope.getBody();
		final SOAPElement soapBodyElem = soapBody.addChildElement("import", myNamespace);
		final SOAPElement state = soapBodyElem.addChildElement("state");
		state.addTextNode(state_card);
		final SOAPElement cardholderID = soapBodyElem.addChildElement("cardholderID");
		ISLogger.getLogger().error("settings.get(this.branch).getBank_c(): "
				+ settings.get(branch).getBank_c());
		ISLogger.getLogger().error("settings.get(this.branch).getGROUPC(): "
				+ settings.get(branch).getGroup_c());
		cardholderID.addTextNode(String.valueOf(t_client_id) + "-"
				+ settings.get(branch).getBank_c());
		if (state_card.equals("on")) {
			final SOAPElement cardholderName = soapBodyElem.addChildElement("cardholderName");
			cardholderName.addTextNode(card_holder_name);
			final SOAPElement language = soapBodyElem.addChildElement("language");
			language.addTextNode("ru_translit");
			final SOAPElement charge = soapBodyElem.addChildElement("Charge");
			final SOAPElement agreementCharge = charge.addChildElement("agreementCharge");
			agreementCharge.addTextNode("MONTH.FEE.OFF");
			final SOAPElement card = soapBodyElem.addChildElement("Card");
			final SOAPElement card_state = card.addChildElement("state");
			card_state.addTextNode(state_card);
			final SOAPElement card_pan = card.addChildElement("pan");
			String cardReal = "";
			SimpleDateFormat dfExpiry = new SimpleDateFormat("MMyy");
			try {
				final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(settings.get(branch).getBank_c());
				connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
				final RowType_GetRealCard_Request parameters = new RowType_GetRealCard_Request();
				parameters.setCARD(card_number);
				final OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
				final RowType_GetRealCard_ResponseHolder details = new RowType_GetRealCard_ResponseHolder();
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					ISLogger.getLogger().error("createSoapEnvelope connectionInfo : "
							+ objectMapper.writeValueAsString(connectionInfo));
					ISLogger.getLogger().error("createSoapEnvelope parameters : "
							+ objectMapper.writeValueAsString(parameters));
					ISLogger.getLogger().error("createSoapEnvelope responseInfo : "
							+ objectMapper.writeValueAsString(responseInfo));
					ISLogger.getLogger().error("createSoapEnvelope details : "
							+ objectMapper.writeValueAsString(details));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				issuingPortProxy.getRealCard(connectionInfo, parameters, responseInfo, details);
				cardReal = details.value.getRCARD();

				card_pan.addTextNode(cardReal);
				final SOAPElement card_expiry = card.addChildElement("expiry");
				final java.util.Date temp_expiry = dfExpiry.parse(expiry);
				card_expiry.addTextNode(dfExpiry.format((temp_expiry)));
				final SOAPElement service = card.addChildElement("Service");
				final SOAPElement serviceID = service.addChildElement("serviceID");
				serviceID.addTextNode("MB-ALL");
				final SOAPElement phone = soapBodyElem.addChildElement("Phone");
				final SOAPElement phone_state = phone.addChildElement("state");
				phone_state.addTextNode("on");
				final SOAPElement phone_msisdn = phone.addChildElement("msisdn");
				phone_msisdn.addTextNode(phoneNumber);
				final SOAPElement phone_deliveryChannel = phone.addChildElement("deliveryChannel");
				phone_deliveryChannel.addTextNode("-");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String callSoapWebService(final String soapEndpointUrl, final String soapAction, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy, String branch, HashMap<String, EmpcSettings> settings) {
		String message = null;
		Connection c = null;
		try {
			final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			final SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			try {
				final SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, t_client_id, card_holder_name, state_card, card_number, expiry, phoneNumber, issuingPortProxy, settings, branch), (Object) soapEndpointUrl);
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				soapResponse.writeTo((OutputStream) out);
				final String strMsg = new String(out.toByteArray());
				ISLogger.getLogger().error((Object) strMsg);
			} catch (SOAPException e) {
				e.printStackTrace();
				message = e.getMessage();
				ISLogger.getLogger().error("SOAP EXCEPTION: "
						+ (Object) CheckNull.getPstr((Exception) e));
			} finally {
				if (soapConnection != null) {
					soapConnection.close();
				}
			}
			c = ConnectionPool.getConnection();
			// ISLogger.getLogger().error("state card: " + state_card);
			CustomerService.saveCardSmsState(card_number, phoneNumber, state_card.equals("on") ? 1
					: 2, c);
			c.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
			Utils.rollback(c);
			System.out.println("E2: " + (Object) CheckNull.getPstr(e2));
			ISLogger.getLogger().error("VER 8 JAN GET PSTR E2: "
					+ (Object) CheckNull.getPstr(e2));
			message = "Ошибка";
			return message;
		} finally {
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return message;
	}

	public static SOAPMessage createSOAPRequest(final String soapAction, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy, HashMap<String, EmpcSettings> settings, String branch) throws Exception {
		final MessageFactory messageFactory = MessageFactory.newInstance();
		final SOAPMessage soapMessage = messageFactory.createMessage();
		createSoapEnvelope(soapMessage, t_client_id, card_holder_name, state_card, card_number, expiry, phoneNumber, issuingPortProxy, settings, branch);
		final String loginPassword = String.valueOf(Utils.getValue("EMPC_TIETO_HOST_USERNAME"))
				+ ":" + Utils.getValue("EMPC_TIETO_HOST_PASSWORD");
		final MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		headers.addHeader("Authorization", "Basic "
				+ new String(Base64.encodeBase64(loginPassword.getBytes())));
		soapMessage.saveChanges();
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo((OutputStream) System.out);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo((OutputStream) out);
		final String strMsg = new String(out.toByteArray());
		ObjectMapper mapper = new ObjectMapper();
		// System.out.println("soapRequest: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(soapMessage));
		// ISLogger.getLogger().error("soapRequest: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(soapMessage));

		// ISLogger.getLogger().error((Object)strMsg);
		// System.out.println("\n");
		ISLogger.getLogger().error("uzCard number to Humo soapMessage: "
				+ soapMessage);
		return soapMessage;
	}

	public static String getRealCardByHumoLd(String card, String alias) {
		String realCard = "";
		try {
			URL url = new URL(Utils.getValueFromSql("select value from bf_sets where id = 'HUMO_GET'", alias)
					+ "getRealCard?CARD=" + card);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			realCard = in.readLine();
			ISLogger.getLogger().error("realCard by HumoLd: " + realCard);
			in.close();
		} catch (Exception e) {
			ISLogger.getLogger().error("HumoLD getRealCard error: "
					+ e.getLocalizedMessage());
		}
		return realCard;
	}
	
	public static boolean checkHumoCardsHistoryExistence(String cardNumber){
		boolean result = true;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from humo_cards_history where card = ?");
			ps.setString(1, cardNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1) == 0 ? false : true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static void updateStatuses(String card, String status1, String status2){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set status1 = ?, status2 = ? where card = ?");
			ps.setString(1, status1);
			ps.setString(2, status2);
			ps.setString(3, card);
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    Utils.close(ps);
		    ConnectionPool.close(c);
		}
	}
	
	public static void addCompanyEmployee(String branch, String customerId, String employeeId){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into humo_client_work (branch, customer_id, employee_id) values (?, ?, ?)");
			ps.setString(1, branch);
			ps.setString(2, customerId);
			ps.setString(3, employeeId);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void editCompanyEmployee(String branch, String customerId, String employeeId){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_client_work set customer_id = ? where branch = ? and employee_id = ?");
			ps.setString(1, customerId);
			ps.setString(2, branch);
			ps.setString(3, employeeId);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static boolean checkCompanyEmployeeExistence(String employeeId, String branch){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from humo_client_work where employee_id = ? and branch = ?");
			ps.setString(1, employeeId);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1) > 0 ? true : false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static void setCompanyEmployeeAccount(String account, String employeeId, String customerId, String branch, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			int res = Integer.valueOf(Utils.getValueFromSql("select count(*) from humo_client_work where employee_id = "+employeeId+" and branch = "+branch+"", alias)); 
			if (res == 0) {
				ps = c.prepareStatement("insert into humo_client_work (branch, customer_id, employee_id, acc) values (?, ?, ?, ?)");
				ps.setString(1, branch);
				ps.setString(2, customerId);
				ps.setString(3, employeeId);
				ps.setString(4, account);
				ps.execute();
			} else {
				ps = c.prepareStatement("update humo_client_work set acc = ? where employee_id = ? and branch = ?");
				ps.setString(1, account);
				ps.setString(2, employeeId);
				ps.setString(3, branch);
				ps.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static String getNewestAccount(String clientId, String branch, int firstOrd, int lastOrd){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String account = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id from (SELECT *\n" +
                    "  FROM Account\n" +
                    " where branch = ?\n" +
                    "   and acc_bal like '22618'\n" +
                    "   and currency = '000'\n" +
                    "   and client = ?\n" +
                    "   and id_order >= ?\n" +
                    "   and id_order < ?\n" +
                    "   and state = '2'\n" +
                    "   order by id_order desc)\n" +
                    "   where rownum = 1");
			ps.setString(1, branch);
			ps.setString(2, clientId);
			ps.setInt(3, firstOrd);
			ps.setInt(4, lastOrd);
			rs = ps.executeQuery();
			if(rs.next()){
				account = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return account;
		
	}
	
	public static List<Customer> customersSearch(String customerId, String branch, String alias) {
		String sql1 = "select customer_id data, customer_id ||' - '|| customer_name label from humo_accrual_acc where branch = '"
				+ branch + "'";
		String sql2 = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Customer> customersList = new ArrayList<Customer>();
		if (customerId.contains("%")) {
			sql2 = " and customer_id like '" + customerId + "'";
		} else if (customerId.length() == 8) {
			sql2 = " and customer_id = '" + customerId + "'";
		} else {
			customerId = customerId + "%";
			sql2 = " and customer_id like '" + customerId + "'";
		}
		System.out.println("customerSearch sql: " + sql1 + sql2);
		try {
			c = ConnectionPool.getConnection();

			ps = c.prepareStatement(sql1 + sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId_client(rs.getString("data"));
				customer.setName(rs.getString("label"));
				customersList.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return customersList;
	}
	
	
	public static boolean checkClientExistenceByAccountNo(String accountNo){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from humo_cards where account_no = ?");
			ps.setString(1, accountNo);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1) > 0 ? true : false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static Customer getCustomerNames(String clientId){
		Customer customer = new Customer();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select f_names, surname from bf_empc_clients where client = ?");
			ps.setString(1, clientId);
			rs = ps.executeQuery();
			while(rs.next()){
				customer.setP_first_name(rs.getString("f_names"));
				customer.setP_family(rs.getString("surname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return customer;
	}
	
	public static List<CardInfo> getCardsWithUnassignedPin(){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CardInfo> cardsWithUnassignedPinList = new ArrayList<CardInfo>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select c.card, c.account_no\n" +
					"  from humo_cards c\n" +
					" where status1 = '1'\n" +
					"   and stop_cause = '6'\n" +
					"   and exists\n" +
					" (select * from bf_empc_accounts a where c.account_no = a.account_no\n" +
					" and a.created_date = (select to_date(current_date, 'dd.mm.yy') from dual))");
			rs = ps.executeQuery();
			while(rs.next()){
				CardInfo cardInfo = new CardInfo();
				cardInfo.setCARD(rs.getString(1));
				cardInfo.setACCOUNT_NO(rs.getString(2));
				cardsWithUnassignedPinList.add(cardInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cardsWithUnassignedPinList;
	}
	
	public static List<CardInfo> getCardStatuses(String accountNo, IssuingPortProxy issuingPortProxy, String branch, String bankC, String groupC) {
		ArrayList<CardInfo> cardInfoList = new ArrayList<CardInfo>();
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		ObjectMapper objectMapper = new ObjectMapper();
		connectionInfo.setBANK_C(bankC);
		connectionInfo.setGROUPC(groupC);
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		try {
			ISLogger.getLogger().error("getCardInfo: "
					+ objectMapper.writeValueAsString(connectionInfo));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
		parameters.setACCOUNT_NO(BigInteger.valueOf(Long.parseLong(accountNo)));
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder();
		try {
			issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
			if (details.value.getRow() != null) {
				for (int i = 0; i < details.value.getRow().length; i++) {
					CardInfo cardInfo = new CardInfo();
					for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
						boolean isPinAssigned = false;
						if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
							cardInfo.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
							cardInfo.setCARD(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STATUS")){
							isPinAssigned = true;
							cardInfo.setSTATUS(details.value.getRow(i).getItem(j).getValue());
						}
						if (details.value.getRow(i).getItem(j).getName().equals("STATUS2"))
							cardInfo.setSTATUS2(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STOP_CAUSE"))
							cardInfo.setSTOP_CAUSE(details.value.getRow(i).getItem(j).getValue());
						if(isPinAssigned){
						cardInfoList.add(cardInfo);
						}
						isPinAssigned = false;
					}
				}
			}
		} catch (NullPointerException e) {
			if (details.value.getRow() != null) {
				for (int i = 0; i < details.value.getRow().length; i++) {
					CardInfo cardInfo = new CardInfo();
					for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
						if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
							cardInfo.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
							cardInfo.setCARD(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
							cardInfo.setSTATUS(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STATUS2"))
							cardInfo.setSTATUS2(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STOP_CAUSE"))
							cardInfo.setSTOP_CAUSE(details.value.getRow(i).getItem(j).getValue());
						cardInfoList.add(cardInfo);
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cardInfoList;
	}
	
	   public static HashMap<String, EmpcSettings> getSettingsForMinibank(String branch, String code) {
		   ObjectMapper objectMapper = new ObjectMapper();
	        Connection c = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
	        final HashMap<String, EmpcSettings> hsettings = new HashMap<String, EmpcSettings>();
	        ISLogger.getLogger().error("Get settings for minibank: "+"\nbranch: "+branch+"\ncode: "+code);
	        try {
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement("select * from ss_humo_type_of_card_minibank where branch = ? and branch_mini = ?");
	            ps.setString(1, branch);
	            ps.setString(2, branch+"-"+code);
	            rs = ps.executeQuery();
	            if (rs.next()){
	                EmpcSettings settings = new EmpcSettings(rs.getString("bin"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
	                hsettings.put(rs.getString("branch"), settings);
	            }else {
	            	EmpcSettings settings = new EmpcSettings("0", "0", "0", "0", "0", "0");
	            	hsettings.put(branch, settings);
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
	        }
	        finally {
	            Utils.close(rs);
	            Utils.close(ps);
	            ConnectionPool.close(c);
	        }
	        try {
				ISLogger.getLogger().error("getSettingsForMinibank: " + objectMapper.writeValueAsString(hsettings));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return hsettings;
	    }
	   
	public static Res getSubsidiaryUserCode(String branch, int uId, String alias) {
		Res res = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select code from ss_subsidiary_user where branch = ? and id_user = ?");
			ps.setString(1, branch);
			ps.setInt(2, uId);
			rs = ps.executeQuery();
			if (rs.next()) {
				res.setCode(0);
				res.setName(rs.getString(1));
			} else {
				res.setCode(-1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}
	   
    public static void getClientFromTietoByClientB(
            globus.IssuingWS.IssuingPortProxy issuingPortProxy, String clientB, String bankC,
            String groupC, String branch, String un, String pwd, String alias) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper objectMapper = new ObjectMapper();
        OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
        Tclient humoClient = new Tclient();
        connectionInfo.setBANK_C(bankC);
        connectionInfo.setGROUPC(groupC);
        connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
        RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
        parameters.setBANK_C(bankC);
        parameters.setCLIENT_B(branch + clientB);
        OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
        ListType_Generic listType_Generic = null;
        ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
        issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
        ISLogger.getLogger().error("\n getTclientByClientB request good \n");
        for (int i = 0; i < details.value.getRow().length; i++) {
            for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
                if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
                    humoClient.setClient(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
                    humoClient.setStatus(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B"))
                    humoClient.setClient_b(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
                    humoClient.setF_names(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
                    humoClient.setSurname(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE"))
                    humoClient.setPersone_code(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("B_DATE")) {
                    if (!details.value.getRow(i).getItem(j).getName().equals("")) {
                        humoClient.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(
                                0, 10)));
                    }
                }
                if (details.value.getRow(i).getItem(j).getName().equals("DOC_SINCE")) {
                    if (!details.value.getRow(i).getItem(j).getName().equals("")) {
                        if (details.value.getRow(i).getItem(j).getName().length() > 10) {
                            humoClient.setDoc_since(df.parse(details.value.getRow(i).getItem(j).getValue().substring(
                                    0, 10)));
                        }
                    }
                }
                if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS"))
                    humoClient.setR_emails(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE"))
                    humoClient.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("R_STREET"))
                    humoClient.setR_street(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("R_CITY"))
                    humoClient.setR_city(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY"))
                    humoClient.setR_cntry(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
                    humoClient.setCard(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
                    humoClient.setBank_c(details.value.getRow(i).getItem(j).getValue());
                if (details.value.getRow(i).getItem(j).getName().equals("ISSUED_BY"))
                    humoClient.setIssued_by(details.value.getRow(i).getItem(j).getValue());
                humoClient.setCl_type("1");
                if (details.value.getRow(i).getItem(j).getName().equals("DOC_TYPE"))
                    humoClient.setDoc_type(details.value.getRow(i).getItem(j).getValue());
                humoClient.setM_name("EMPTY");
                humoClient.setResident("1");
                if (details.value.getRow(i).getItem(j).getName().equals("ID_CARD"))
                    humoClient.setId_card(details.value.getRow(i).getItem(j).getValue());
            }
        }
        ISLogger.getLogger().error(
                "Update data for client: " + objectMapper.writeValueAsString(humoClient));
        if (checkXumoCustomerExistence(clientB, branch)) {
            ISLogger.getLogger().error("bf_xumo_customers "+branch+clientB+" exists");
            //deleteBfXumoCustomers(clientB, branch, alias);
            updateBfXumoCustomers(humoClient.getClient(), clientB, branch, un, pwd, alias);
        }else {
            ISLogger.getLogger().error("bf_xumo_customers "+branch+clientB+" doesn't exist");
            TietoCustomer tietoCustomer = new TietoCustomer();
            tietoCustomer.setBankCustomerId(clientB);
            tietoCustomer.setBranch(branch);
            tietoCustomer.setTietoCustomerId(humoClient.getClient());
            insertTietoCustomer(tietoCustomer);
            //insertBankCustomerId(tietoCustomer);
        }
        if (!checkEmpcClientExistence(clientB, branch)) {
            insertClientFromTieto(humoClient, branch);
        }
/*        TietoCustomer checkTietoCustomer = getTietoCustomerByClientB(clientB, branch);
        if(checkTietoCustomer.getBankCustomerId() != null && checkTietoCustomer.getTietoCustomerId() == null) {
            ISLogger.getLogger().error("bf_xumo_customers "+branch+clientB+" doesn't have tieto_customer_id");
            deleteBfXumoCustomers(clientB, branch, alias);
            TietoCustomer tietoCustomer = new TietoCustomer();
            tietoCustomer.setBankCustomerId(clientB);
            tietoCustomer.setBranch(branch);
            tietoCustomer.setTietoCustomerId(humoClient.getClient());
            create(tietoCustomer, un, pwd, alias);
        }else if (checkTietoCustomer.getBankCustomerId() == null && checkTietoCustomer.getTietoCustomerId() == null) {
            ISLogger.getLogger().error("bf_xumo_customers "+branch+clientB+" doesn't exist");
            TietoCustomer tietoCustomer = new TietoCustomer();
            tietoCustomer.setBankCustomerId(clientB);
            tietoCustomer.setBranch(branch);
            tietoCustomer.setTietoCustomerId(humoClient.getClient());
            create(tietoCustomer, un, pwd, alias);
        }*/
    }
    
    private static void insertClientFromTieto(Tclient client, String branch) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        boolean bool = false;
        try {
            ISLogger.getLogger().error("insert bf_empc_clients");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
                    + "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
                    + ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, client.getClient());
            ps.setString(2, client.getF_names());
            ps.setString(3, client.getCl_type());
            ps.setString(4, client.getClient_b());
            ps.setString(5, client.getSurname());
            ps.setString(6, client.getM_name());
            ps.setDate(7, client.getDoc_since() == null ? null
                    : new java.sql.Date(client.getDoc_since().getTime()));
            ps.setDate(8, client.getB_date() == null ? null
                    : new java.sql.Date(client.getB_date().getTime()));
            ps.setString(9, client.getResident());
            ps.setString(10, client.getStatus());
            ps.setString(11, client.getSex());
            ps.setString(12, client.getSerial_no());
            ps.setString(13, client.getId_card());
            ps.setString(14, client.getR_city());
            ps.setString(15, client.getR_street());
            ps.setString(16, client.getR_emails());
            ps.setString(17, client.getRmob_phone());
            ps.setString(18, client.getR_phone());
            ps.setString(19, client.getR_cntry());
            ps.setString(20, client.getIssued_by());
            ps.setString(21, client.getPersone_code());
            ps.setString(22, client.getDoc_type());
            ps.setDate(23, client.getRec_date() == null ? null
                    : new java.sql.Date(client.getRec_date().getTime()));
            ps.execute();
            // synTietoCustomers(client.getClient(), client.getClient_b(),
            // branch, c);
            c.commit();
            bool = true;
        } finally {
            if (!bool)
                try {
                    if (c != null)
                        c.rollback();
                } catch (Exception ex) {
                }
            Utils.close(ps);
            ConnectionPool.close(c);
        }
    }
    
    public static boolean checkEmpcClientExistence(String clientB, String branch) {
        boolean isEmpcClientExists = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select count(*) from bf_empc_clients where client_b = ?");
            ps.setString(1, branch + clientB);
            rs = ps.executeQuery();
            if(rs.next()) {
                isEmpcClientExists = rs.getInt(1) == 0 ? false : true; 
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return isEmpcClientExists;
    }
    
    public static TietoCustomer getTietoCustomerByClientB(String clientB, String branch) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TietoCustomer tietoCustomer = new TietoCustomer();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from bf_xumo_customers where bank_customer_id = ? and branch = ?");
            ps.setString(1, clientB);
            ps.setString(2, branch);
            rs = ps.executeQuery();
            if(rs.next()) {
                tietoCustomer.setBankCustomerId(rs.getString("bank_customer_id"));
                tietoCustomer.setBranch(rs.getString("branch"));
                tietoCustomer.setTietoCustomerId(rs.getString("tieto_customer_id"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return tietoCustomer;
    }
    
    public static boolean checkXumoCustomerExistence(String clientB, String branch) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select count(*) from bf_xumo_customers where bank_customer_id = ? and branch = ?");
            ps.setString(1, clientB);
            ps.setString(2, branch);
            rs = ps.executeQuery();
            if(rs.next()) {
                result = rs.getInt(1) > 0 ? true : false; 
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return result;
    }
    
    public static List<AccInfo> getAccInfo(String branch, String client, String alias,
            globus.IssuingWS.IssuingPortProxy issuingPortProxy, HashMap<String, EmpcSettings> settings)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AccInfo> list = new ArrayList<AccInfo>();
        try {
            OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
            connectionInfo.setBANK_C(settings.get(branch).getBank_c());
            connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
            connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
            RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
            parameters.setCLIENT(client);
            OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
            ListType_GenericHolder details = new ListType_GenericHolder();
            ISLogger.getLogger().error("getAccInfo request for client "+client
                    + "\n"+objectMapper.writeValueAsString(connectionInfo)
                    + objectMapper.writeValueAsString(parameters));
            issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
             ISLogger.getLogger().error("getAccInfo response for client "+client
                        + "\n" + objectMapper.writeValueAsString(connectionInfo)
                        + "\n" + objectMapper.writeValueAsString(parameters)
                        + "\n" + objectMapper.writeValueAsString(details));
            if (details.value.getRow() != null) {
                for (int i = 0; i < details.value.getRow().length; i++) {
                    AccInfo accInfo = new AccInfo();
                    for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
                    {
                        if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
                            accInfo.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
                        if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
                            accInfo.setClient(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT")) {
                            accInfo.setCard_acct(details.value.getRow(i).getItem(j).getValue());
                        }
                        if (details.value.getRow(i).getItem(j).getName().equals("CTIME")) {
                            if (!details.value.getRow(i).getItem(j).getName().equals("")) {
                                accInfo.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
                            }
                        }
                        if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS"))
                            accInfo.setAc_status(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS"))
                            accInfo.setCl_status(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY"))
                            accInfo.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE"))
                            accInfo.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CCY"))
                            accInfo.setCcy(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY")) {
                            if (!details.value.getRow(i).getItem(j).getName().equals("")) {
                                accInfo.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
                            }
                        }
                        if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
                            accInfo.setF_names(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
                            accInfo.setSurname(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CITY"))
                            accInfo.setCity(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
                            accInfo.setStreet(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT"))
                            accInfo.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
                            accInfo.setCard(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) {
                            accInfo.setStatus1(details.value.getRow(i).getItem(j).getValue());
                            accInfo.setAc_status(details.value.getRow(i).getItem(j).getValue());

                        }
                        if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT"))
                            accInfo.setProduct(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
                            accInfo.setStreet(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY"))
                            accInfo.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
                        if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM"))
                            accInfo.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT"))
                            accInfo.setContract(details.value.getRow(i).getItem(j).getValue());
                    }
                    ISLogger.getLogger().error("accInfo: "+objectMapper.writeValueAsString(accInfo));
                    accInfo.setCardlist(getCardInfo(branch, accInfo, alias, issuingPortProxy, settings));
                    ISLogger.getLogger().error("accInfo listAccounts: "
                            + objectMapper.writeValueAsString(accInfo));
                    list.add(accInfo);
                }
            }else{
                ISLogger.getLogger().error("details.value.getRow is null");
            }
            for (int k = 0; k < list.size(); k++) {
                AccInfo acc = list.get(k);
                for (int l = 0; l < acc.getCardlist().size(); l++) {
                    CardInfo card = acc.getCardlist().get(l);
                    ISLogger.getLogger().error("card.getCARD() = "
                            + card.getCARD());
                }
            }
        } catch (NullPointerException e) {
            ISLogger.getLogger().error("ERRORRRR getAccInfo: ", e);
            // TODO: handle exception
        } catch (Exception e) {
            LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
            System.out.println("Exception => " + e.getLocalizedMessage());
            ISLogger.getLogger().error("EXCEPTION: ", e);
        }
        return list;
    }
    
    public static BigDecimal getAgreementKey(List<AccInfo> listAccInfos) {
        BigDecimal agreementKey = null;
        for (int i = 0; i < listAccInfos.size(); i++) {
            for (int k = 0; k < listAccInfos.get(i).getCardlist().size(); k++) {
                CardInfo cardInfo = listAccInfos.get(i).getCardlist().get(k);
                agreementKey = BigDecimal.valueOf(Long.parseLong(cardInfo.getAGREEMENT_KEY()));      
            }
        }
        return agreementKey;
    }
    
    public static List<CardInfo> getCardInfo(String branch, AccInfo account, String alias,
            globus.IssuingWS.IssuingPortProxy issuingPortProxy, HashMap<String, EmpcSettings> settings) {
        List<CardInfo> list = new ArrayList<CardInfo>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
            connectionInfo.setBANK_C(settings.get(branch).getBank_c());
            connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
            connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
            ISLogger.getLogger().error("getCardInfo: "+objectMapper.writeValueAsString(connectionInfo));
            RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
            parameters.setACCOUNT_NO(BigInteger.valueOf(account.getAccount_no()));
            parameters.setCARD_ACCT(account.getCard_acct());
            parameters.setCCY(account.getCcy());
            OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
            ListType_GenericHolder details = new ListType_GenericHolder();
            try {
                issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
            } catch (NullPointerException e) {
                if (details.value.getRow() != null) {
                    for (int i = 0; i < details.value.getRow().length; i++) {
                        CardInfo rs = new CardInfo();
                        for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
                            if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
                                rs.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
                                rs.setCARD_ACCT(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("CARD")) {
                                rs.setCARD(details.value.getRow(i).getItem(j).getValue());
                            }
                            if (details.value.getRow(i).getItem(j).getName().equals("BASE_SUPP"))
                                rs.setBASE_SUPP(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
                                rs.setSTATUS(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("STATUS2"))
                                rs.setSTATUS2(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("STOP_CAUSE"))
                                rs.setSTOP_CAUSE(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY"))
                                rs.setEXPIRY(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY2"))
                                rs.setEXPIRY2(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("COND_SET"))
                                rs.setCOND_SET(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("RISK_LEVEL"))
                                rs.setRISK_LEVEL(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_ID"))
                                rs.setCLIENT_ID(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("CL_ROLE"))
                                rs.setCL_ROLE(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY"))
                                rs.setAGREEMENT_KEY(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("CARD_NAME"))
                                rs.setCARD_String(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
                                rs.setBANK_C(details.value.getRow(i).getItem(j).getValue());
                            if (details.value.getRow(i).getItem(j).getName().equals("GROUPC"))
                                rs.setGROUPC(details.value.getRow(i).getItem(j).getValue());
                        }
                        //getAccountBal(branch, rs, alias, issuingPortProxy);
                        rs.setBank_account(account.getTranz_acct());
                        rs.setBank_account_status(account.getStatus1());
                        rs.setBank_account_Ccy(account.getCcy());
                        list.add(rs);
                    }
                }
            }
            if (details.value.getRow() != null) {
                for (int i = 0; i < details.value.getRow().length; i++) {
                    CardInfo rs = new CardInfo();
                    for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
                        if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
                            rs.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
                            rs.setCARD_ACCT(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CARD")) {
                            rs.setCARD(details.value.getRow(i).getItem(j).getValue());
                        }
                        if (details.value.getRow(i).getItem(j).getName().equals("BASE_SUPP"))
                            rs.setBASE_SUPP(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
                            rs.setSTATUS(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STATUS2"))
                            rs.setSTATUS2(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("STOP_CAUSE"))
                            rs.setSTOP_CAUSE(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY"))
                            rs.setEXPIRY(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("EXPIRY2"))
                            rs.setEXPIRY2(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("COND_SET"))
                            rs.setCOND_SET(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("RISK_LEVEL"))
                            rs.setRISK_LEVEL(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_ID"))
                            rs.setCLIENT_ID(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CL_ROLE"))
                            rs.setCL_ROLE(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY"))
                            rs.setAGREEMENT_KEY(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("CARD_NAME"))
                            rs.setCARD_String(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
                            rs.setBANK_C(details.value.getRow(i).getItem(j).getValue());
                        if (details.value.getRow(i).getItem(j).getName().equals("GROUPC"))
                            rs.setGROUPC(details.value.getRow(i).getItem(j).getValue());
                    }
                    //getAccountBal(branch, rs, alias, issuingPortProxy);
                    rs.setBank_account(account.getTranz_acct());
                    rs.setBank_account_status(account.getStatus1());
                    rs.setBank_account_Ccy(account.getCcy());
                    list.add(rs);
                }
            }
        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            LtLogger.getLogger().error(CheckNull.getPstr(e));
            e.printStackTrace();
        }
        return list;
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
            if(rs.next()) {
                isAccessGranted = rs.getInt(1) > 0 ? true : false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return isAccessGranted;
    }
    
    public static ArrayList<String> getCardsWithoutRealCard() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> cardList = new ArrayList<String>();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select card from humo_cards where real_card is null");
            rs = ps.executeQuery();
            while (rs.next()) {
                cardList.add(rs.getString("card"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return cardList;
    }
    
    public static IssuingPortProxy initNp() throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        final IssuingPortProxy issuingPortProxy = new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"), ConnectionPool.getValue("HUMO_USERNAME"), ConnectionPool.getValue("HUMO_PASSWORD"));
        ISLogger.getLogger().error("INITNP HUMO HOST: "
                + ConnectionPool.getValue("HUMO_HOST"));
        ISLogger.getLogger().error("INITNP HUMO UN: "
                + ConnectionPool.getValue("HUMO_USERNAME"));
        ISLogger.getLogger().error("INITNP HUMO PW: "
                + ConnectionPool.getValue("HUMO_PASSWORD"));
        return issuingPortProxy;
    }
    
/*	public static void crDoc(String branch, String operationId, String amount) {
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection();
			cs = c.prepareCall("{call HUMO_TR_DOC.PAYDOC_TRANS(?,?,?,?,?,?)}");
			cs.setString(1, branch);
			cs.setString(2, operationId);
			cs.setString(3, amount);
			cs.setString(4, record.getId().toString());
			cs.setString(5, "198");
			cs.registerOutParameter(6, java.sql.Types.VARCHAR);

			cs.executeUpdate();

			ISLogger.getLogger().info("DOC PARAM:" + cs.getString(6));
			System.out.println("DOC PARAM:" + cs.getString(6));

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}*/
}