package com.is.tieto_globuz.tieto;
import globus.IssuingWS.IssuingPortProxy;
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
import globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.MidiDevice.Info;

import org.apache.http.client.ClientProtocolException;
import org.apache.naming.java.javaURLContextFactory;
import org.apache.poi.hssf.record.formula.functions.True;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jruby.javasupport.Java;
import org.python.antlr.PythonParser.else_clause_return;
import org.python.antlr.PythonParser.if_stmt_return;
import org.python.antlr.PythonParser.return_stmt_return;
import org.python.google.common.base.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.db2.jcc.am.co;
import com.ibm.db2.jcc.am.v;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_globuz.EmpcSettings;
import com.is.tieto_globuz.Utils;
import com.is.tieto_globuz.customer.AddCstViewCtrl;
import com.is.tieto_globuz.customer.Customer;
import com.is.tieto_globuz.customer.CustomerService;
import com.is.tieto_globuz.customer.UserActionsLog;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountFilter;
import com.is.trpay.TrPay;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class TclientService
{
	private static Tclient rs;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df2 = new SimpleDateFormat("dd.mm.yyyy");// 1971-06-13T00:00:00
	private static SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");
	private OperationConnectionInfo connectionInfo = null;
	private static String HUMO_BANK_C;
	private static String HUMO_GROUPC;
	private static String HUMO_BINCOD;
	private static String HUMO_HOST;
	private static String HUMO_USERNAME;
	private static String HUMO_PASSWORD;
	private String CLIENT_B;
	private String BRANCH;
	private String CARD_TYPE;
	private Long HUMO_CHIPAPPID;
	private String HUMO_BRANCHID;
	private Long HUMO_RANGEID;
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
	private PreparedStatement psUpdateHumoCards=null;
	static ObjectMapper objectMapper = new ObjectMapper();
	private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static HashMap<String,EmpcSettings> settings = null;//new HashMap<String,EmpcSettings>();
	private static HashMap<String,EmpcSettings> settingsByCardType = null;
	private static HashMap<String,EmpcSettings> settingsByBin = null;
	public static void initSettings() throws Exception
	{
			settings = Utils.getHSettings();
		if(settings == null) {
			throw new Exception("Settings not found");
		}
		
		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
        HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
        HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");
	}
	
	public static void initSettings(String branch, String bincode) throws Exception
	{
			settings = getHSettings(branch, bincode);
		if(settings == null) {
			throw new Exception("Settings not found");
		}
		
		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
        HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
        HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");
	}
	
    public static HashMap<String, EmpcSettings> getHSettings(String branch, String bincod) {
        final HashMap<String, EmpcSettings> hsettings = new HashMap<String, EmpcSettings>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from SS_HUMO_TYPE_OF_CARD where branch = ? and bin = ?");
            ps.setString(1, branch);
            ps.setString(2, bincod);
            rs = ps.executeQuery();
            while (rs.next()) {
                final EmpcSettings settings = new EmpcSettings(rs.getString("bin"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
                hsettings.put(rs.getString("branch"), settings);
                System.out.println(String.valueOf(rs.getString("branch")) + "----" + rs.getString("chip_app_id"));
            }
            System.out.println("hsettings " + hsettings);
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return hsettings;
        }
        finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        Utils.close(rs);
        Utils.close(ps);
        ConnectionPool.close(c);
        return hsettings;
    }
    
    public static void initSettingsByCardType(String branch, String cardType) throws Exception
	{
			settingsByCardType = getHSettingsByCardType(branch, cardType);
		if(settingsByCardType == null) {
			throw new Exception("Settings not found");
		}
		
		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
        HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
        HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");
	}
    
    public static void initSettingsByBin(String branch, String bin) throws Exception
	{
		ISLogger.getLogger().error("initSettingsByBin");
			settingsByBin = getHSettingsByBin(branch, bin);
		if(settingsByBin == null) {
			throw new Exception("Settings not found");
		}
		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
        HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
        HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");
	}
	
    public static HashMap<String, EmpcSettings> getHSettingsByCardType(String branch, String cardType) {
        final HashMap<String, EmpcSettings> hsettings = new HashMap<String, EmpcSettings>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from SS_HUMO_TYPE_OF_CARD where branch = ? and code = ?");
            ps.setString(1, branch);
            ps.setString(2, cardType);
            rs = ps.executeQuery();
            while (rs.next()) {
                final EmpcSettings settings = new EmpcSettings(rs.getString("bin"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
                hsettings.put(rs.getString("branch"), settings);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return hsettings;
        }
        finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        Utils.close(rs);
        Utils.close(ps);
        ConnectionPool.close(c);
        return hsettings;
    }
    
    public static HashMap<String, EmpcSettings> getHSettingsByBin(String branch, String bin) {
        final HashMap<String, EmpcSettings> hsettings = new HashMap<String, EmpcSettings>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from SS_HUMO_TYPE_OF_CARD where branch = ? and bin = ?");
            ps.setString(1, branch);
            ps.setString(2, bin);
            rs = ps.executeQuery();
            while (rs.next()) {
            	ISLogger.getLogger().error("hsettings by bin rs.next");
                final EmpcSettings settings = new EmpcSettings(rs.getString("bin"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
                hsettings.put(rs.getString("branch"), settings);
                ISLogger.getLogger().error("hSettings by bin: "+objectMapper.writeValueAsString(settings));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return hsettings;
        }
        finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        Utils.close(rs);
        Utils.close(ps);
        ConnectionPool.close(c);
        return hsettings;
    }
    
    public static EmpcSettings getSettingsByBin(String branch, String bin) {
        Connection c = null;
        EmpcSettings settingsByBincode = new EmpcSettings();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from SS_HUMO_TYPE_OF_CARD where branch = ? and bin = ?");
            ps.setString(1, branch);
            ps.setString(2, bin);
            rs = ps.executeQuery();
            if (rs.next()){
            	ISLogger.getLogger().error("settings by bin rs.next");
                settingsByBincode = new EmpcSettings(rs.getString("bin"), rs.getString("group_c"), rs.getString("bank_c"), rs.getString("range_id"), rs.getString("chip_app_id"), rs.getString("branch_id"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
        }
        finally {
            ConnectionPool.close(c);
        }
		return settingsByBincode;
    }
	
	private static final Map<String, String> letters = new HashMap<String, String>();
	  static {
	      letters.put("А", "A");
	      letters.put("Б", "B");
	      letters.put("В", "V");
	      letters.put("Г", "G");
	      letters.put("Д", "D");
	      letters.put("Е", "E");
	      letters.put("Ё", "Yo");
	      letters.put("Ж", "Dj");
	      letters.put("З", "Z");
	      letters.put("И", "I");
	      letters.put("Й", "I");
	      letters.put("К", "K");
	      letters.put("Л", "L");
	      letters.put("М", "M");
	      letters.put("Н", "N");
	      letters.put("О", "O");
	      letters.put("П", "P");
	      letters.put("Р", "R");
	      letters.put("С", "S");
	      letters.put("Т", "T");
	      letters.put("У", "U");
	      letters.put("Ф", "F");
	      letters.put("Х", "Kh");
	      letters.put("Ц", "C");
	      letters.put("Ч", "Ch");
	      letters.put("Ш", "Sh");
	      letters.put("Щ", "Sch");
	      letters.put("Ъ", "'");
	      letters.put("Ы", "Y");
	      letters.put("Ъ", "'");
	      letters.put("Э", "E");
	      letters.put("Ю", "Yu");
	      letters.put("Я", "Ya");
	      letters.put("а", "a");
	      letters.put("б", "b");
	      letters.put("в", "v");
	      letters.put("г", "g");
	      letters.put("д", "d");
	      letters.put("е", "e");
	      letters.put("ё", "e");
	      letters.put("ж", "zh");
	      letters.put("з", "z");
	      letters.put("и", "i");
	      letters.put("й", "i");
	      letters.put("к", "k");
	      letters.put("л", "l");
	      letters.put("м", "m");
	      letters.put("н", "n");
	      letters.put("о", "o");
	      letters.put("п", "p");
	      letters.put("р", "r");
	      letters.put("с", "s");
	      letters.put("т", "t");
	      letters.put("у", "u");
	      letters.put("ф", "f");
	      letters.put("х", "h");
	      letters.put("ц", "c");
	      letters.put("ч", "ch");
	      letters.put("ш", "sh");
	      letters.put("щ", "sch");
	      letters.put("ъ", "'");
	      letters.put("ы", "y");
	      letters.put("ъ", "'");
	      letters.put("э", "e");
	      letters.put("ю", "yu");
	      letters.put("я", "ya");
	  }
	
	public void init( Connection c) throws Exception{
         HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
         HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
         HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");
/*		psInsertAgreement = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
		psInsertRange = c.prepareStatement("update HUMO_CARD_OPEN_RENEW set range_id=?, branch_id=?,state_n=?,err_n=?, err_d=?,state_d=?  where client=? and branch=? and card_type=?");
		psInsertClient = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
				+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
				+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		psNewAgremment = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
		psInsertHumoCards = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
				+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct,account_no) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		psNewAccounts = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
				+ " (?,?,?,?,?,?)");

		psUpdateHumoCards = c.prepareStatement("update humo_cards set card=?,expiry1=?,account_no=?,real_card='' "
				+ " where client_b=? and branch=? and card_name=?");*/
	}
	
	public static String getSBank() {
		Connection c = null;
		String s_bank = "";
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("select t.u_file_code  from BF_GLOBUZ_BANK_CODES t "
					+ "where t.bank_code = (select s.value from bf_sets s where s.id = 'EMPC_BANK_C')");
			while (rs.next()) {
				s_bank = rs.getString("u_file_code");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(s);
			ConnectionPool.close(c);
		}
		return s_bank;
	}
	
	public static List<Tclient> getTclient(String branch, String serial_no, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		List<Tclient> list = new ArrayList<Tclient>();

		try {
			HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
			HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(HUMO_BANK_C);
			connectionInfo.setGROUPC(HUMO_GROUPC);
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters.setBANK_C(HUMO_BANK_C);
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			for (int i = 0; i < details.value.getRow().length; i++) {
				rs = new Tclient();
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
						rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
						rs.setStatus(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B"))
						rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
						rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
						rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE"))
						rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("B_DATE")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS"))
						rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE"))
						rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_STREET"))
						rs.setR_street(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CITY"))
						rs.setR_city(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY"))
						rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
						rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
						rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
				}
				list.add(rs);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;
	}
	
	public static Tclient getTclient_by_id(String client_id, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String branch, String alias) {
		Tclient rs = null;
		try {
			ISLogger.getLogger().error("\n\n getTclient_by_id \n\n\n");
			rs = getClientFromBankById(branch, client_id, issuingPortProxy, alias);
			if (rs == null)
				getClientFromTietoById(client_id, issuingPortProxy, rs, branch, alias);

		} catch (Exception e) {
			ISLogger.getLogger().error("\n getTclient_by_id error \n");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		ISLogger.getLogger().error("\n getTclient_by_id good \n");
		return rs;
	}
	
	public static Tclient getTclient_by_id(String client_id, String client_b, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String branch, String alias) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			ISLogger.getLogger().error("\n getTclient_by_id: " + client_id);
			rs = getClientFromBankById(branch, client_id, client_b, issuingPortProxy, alias);
			if (rs == null)
				getClientFromTietoById(client_id, issuingPortProxy, rs, branch, alias);
			if (!checkTietoCustomers(client_id, client_b, branch, c)) {
				ISLogger.getLogger().error("insert bf_xumo_customers");
				insertTietoCustomers(client_id, client_b, branch, c);
			}
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error("\n\n getTclient_by_id error \n\n\n" + e);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		ISLogger.getLogger().error("\n getTclient_by_id good\n");
		ConnectionPool.close(c);
		return rs;
	}
	
	public static Tclient getClientFromBankById(String branch, String client_id, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tclient cl = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bf_EMPC_clients where client = ?");
			ps.setString(1, client_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				cl = new Tclient();
				cl.setClient(rs.getString("CLIENT"));
				cl.setStatus(rs.getString("STATUS"));
				cl.setClient_b(rs.getString("CLIENT_B"));
				cl.setF_names(rs.getString("F_NAMES"));
				cl.setSurname(rs.getString("SURNAME"));
				cl.setPersone_code(rs.getString("PERSON_CODE"));
				cl.setB_date(rs.getDate("B_DATE"));
				cl.setR_emails(rs.getString("R_E_MAILS"));
				cl.setRmob_phone(rs.getString("R_MOB_PHONE"));
				cl.setR_street(rs.getString("R_STREET"));
				cl.setR_city(rs.getString("R_CITY"));
				cl.setR_cntry(rs.getString("R_CNTRY"));
				System.out.println("branch " + branch + " settings " + settings);
				cl.setBank_c(settings.get(branch).getBank_c());
				// if
				// (details.value.getRow(i).getItem(j).getName().equals("CARD"))
				// rs.setCard(details.value.getRow(i).getItem(j).getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cl;
	}
	
	public static Tclient getClientFromBankById(String branch, String client_id, String client_b, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tclient cl = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bf_EMPC_clients where client = ? and client_b = ?");
			ps.setString(1, client_id);
			ps.setString(2, client_b);
			rs = ps.executeQuery();
			if (rs.next()) {
				cl = new Tclient();
				cl.setClient(rs.getString("CLIENT"));
				cl.setStatus(rs.getString("STATUS"));
				cl.setClient_b(rs.getString("CLIENT_B"));
				cl.setF_names(rs.getString("F_NAMES"));
				cl.setSurname(rs.getString("SURNAME"));
				cl.setPersone_code(rs.getString("PERSON_CODE"));
				cl.setB_date(rs.getDate("B_DATE"));
				cl.setR_emails(rs.getString("R_E_MAILS"));
				cl.setRmob_phone(rs.getString("R_MOB_PHONE"));
				cl.setR_street(rs.getString("R_STREET"));
				cl.setR_city(rs.getString("R_CITY"));
				cl.setR_cntry(rs.getString("R_CNTRY"));
				System.out.println("branch " + branch + " settings " + settings);
				cl.setBank_c(settings.get(branch).getBank_c());
				// if
				// (details.value.getRow(i).getItem(j).getName().equals("CARD"))
				// rs.setCard(details.value.getRow(i).getItem(j).getValue());
			} else {
				ps = c.prepareStatement("select * from bf_EMPC_clients where client = ? and client_b = ?");
				ps.setString(1, client_id);
				ps.setString(2, branch + client_b);
				rs = ps.executeQuery();
				if (rs.next()) {
					cl = new Tclient();
					cl.setClient(rs.getString("CLIENT"));
					cl.setStatus(rs.getString("STATUS"));
					cl.setClient_b(rs.getString("CLIENT_B"));
					cl.setF_names(rs.getString("F_NAMES"));
					cl.setSurname(rs.getString("SURNAME"));
					cl.setPersone_code(rs.getString("PERSON_CODE"));
					cl.setB_date(rs.getDate("B_DATE"));
					cl.setR_emails(rs.getString("R_E_MAILS"));
					cl.setRmob_phone(rs.getString("R_MOB_PHONE"));
					cl.setR_street(rs.getString("R_STREET"));
					cl.setR_city(rs.getString("R_CITY"));
					cl.setR_cntry(rs.getString("R_CNTRY"));
					System.out.println("branch " + branch + " settings "
							+ settings);
					cl.setBank_c(settings.get(branch).getBank_c());
					// if
					// (details.value.getRow(i).getItem(j).getName().equals("CARD"))
					// rs.setCard(details.value.getRow(i).getItem(j).getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cl;
	}
	
	public static void getClientFromTietoById(String client_id, globus.IssuingWS.IssuingPortProxy issuingPortProxy, Tclient rs, String branch, String alias) throws Exception {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		System.out.println("CLIENT ID = " + client_id);
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
		parameters.setBANK_C(settings.get(branch).getBank_c());
		parameters.setCLIENT(client_id);
		ISLogger.getLogger().error("\n getTclient_by_id client id = "
				+ client_id + "\n");
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
		issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
		ISLogger.getLogger().error("\n\n getTclient_by_id request good \n\n\n");
		for (int i = 0; i < details.value.getRow().length; i++) {
			rs = new Tclient();
			for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
				if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
					rs.setClient(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
					rs.setStatus(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B"))
					rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
					rs.setF_names(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
					rs.setSurname(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE"))
					rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("B_DATE")) {
					if (!details.value.getRow(i).getItem(j).getName().equals("")) {
						rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
					}
				}
				if (details.value.getRow(i).getItem(j).getName().equals("DOC_SINCE")) {
					if (!details.value.getRow(i).getItem(j).getName().equals("")) {
						if (details.value.getRow(i).getItem(j).getName().length() > 10) {
							rs.setDoc_since(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
				}
				if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS"))
					rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE"))
					rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("R_STREET"))
					rs.setR_street(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("R_CITY"))
					rs.setR_city(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY"))
					rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
					rs.setCard(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
					rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
				if (details.value.getRow(i).getItem(j).getName().equals("ISSUED_BY"))
					rs.setIssued_by(details.value.getRow(i).getItem(j).getValue());
				rs.setCl_type("1");
				if (details.value.getRow(i).getItem(j).getName().equals("DOC_TYPE"))
					rs.setDoc_type(details.value.getRow(i).getItem(j).getValue());
				rs.setM_name("EMPTY");
				rs.setResident("1");
				if (details.value.getRow(i).getItem(j).getName().equals("ID_CARD"))
					rs.setId_card(details.value.getRow(i).getItem(j).getValue());
			}
		}
		insertClientFromTieto(rs, branch);
	}
	
	public static Tclient getClientFromTietoById(String tieto_client, String bank_client, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String branch, String alias) throws Exception {
		Tclient rs = null;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
		parameters.setBANK_C(settings.get(branch).getBank_c());
		if (tieto_client == null)
			parameters.setCLIENT_B(bank_client);
		else
			parameters.setCLIENT(tieto_client);
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
		issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
		if (details.value.getRow() != null) {
			for (int i = 0; i < details.value.getRow().length; i++) {
				rs = new Tclient();
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
						rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
						rs.setStatus(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B"))
						rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
						rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
						rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE"))
						rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("B_DATE")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("DOC_SINCE")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setDoc_since(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS"))
						rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE"))
						rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_STREET"))
						rs.setR_street(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CITY"))
						rs.setR_city(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY"))
						rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
						rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
						rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ISSUED_BY"))
						rs.setIssued_by(details.value.getRow(i).getItem(j).getValue());
					rs.setCl_type("1");
					if (details.value.getRow(i).getItem(j).getName().equals("DOC_TYPE"))
						rs.setDoc_type(details.value.getRow(i).getItem(j).getValue());
					rs.setM_name("EMPTY");
					rs.setResident("1");
					if (details.value.getRow(i).getItem(j).getName().equals("ID_CARD"))
						rs.setId_card(details.value.getRow(i).getItem(j).getValue());
				}
			}
		}
		return rs;
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
	
	public static void synTietoCustomers(String t_client_id, String client_b, String branch, Connection c) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean bool = false;
		boolean error = true;
		try {
			if (c == null) {
				bool = true;
				c = ConnectionPool.getConnection();
			}
			ISLogger.getLogger().error("Client update = " + client_b);
			ps = c.prepareStatement("select * from bf_xumo_customers where branch = ? and bank_customer_id = ?");
			ps.setString(1, branch);
			ps.setString(2, client_b.length() == 13 ? client_b.substring(5)
					: client_b);
			rs = ps.executeQuery();
			if (rs.next()) {
				String t_cl_id = rs.getString("TIETO_CUSTOMER_ID");
				String id = rs.getString("ID");
				if (t_cl_id == null || t_cl_id.equals(""))
					updateTietoCutomers(t_client_id, branch, id, c);
			} else {
				insertTietoCustomers(t_client_id, client_b, branch, c);
			}
			error = false;
			if (bool)
				c.commit();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			if (bool) {
				if (error)
					Utils.rollback(c);
				ConnectionPool.close(c);
			}
		}
	}
	
//	public static void synTietoCustomersFromBank(String t_client_id, String client_b, String branch, Connection c) throws Exception{
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		boolean bool = false;
//		boolean error = true;
//		try {
//			if(c == null) {
//				bool = true;
//				c = ConnectionPool.getConnection();
//			}
//			ps = c.prepareStatement("select * from bf_xumo_customers where branch = ? and bank_customer_id = ?");
//			ps.setString(1, branch);
//			ps.setString(2, client_b);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				String t_cl_id = rs.getString("TIETO_CUSTOMER_ID");
//				String id = rs.getString("ID");
//				if(t_cl_id == null || t_cl_id.equals("")) updateTietoCutomers(t_client_id, branch, id, c);
//			} else {
//				insertTietoCustomers(t_client_id, client_b, branch, c);
//			}
//			error = false;
//		} finally {
//			Utils.close(rs);
//			Utils.close(ps);
//			if(bool) {
//				if(error) Utils.rollback(c);
//				ConnectionPool.close(c);
//			}
//		}
//	}
	
	public static void insertTietoCustomers(String t_client, String b_client, String branch, Connection c) throws Exception {
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			ps = c.prepareStatement("insert into bf_xumo_customers (id,branch,bank_customer_id,tieto_customer_id) values (seq_bf_xumo_customers.nextval,?,?,?)");
			ps.setString(1, branch);
			ps.setString(2, b_client.length() == 8 ? b_client
					: b_client.substring(5));
			ps.setString(3, t_client.length() == 8 ? t_client
					: t_client.substring(5));
			ps.execute();
			bool = true;
		} finally {
			if (!bool)
				Utils.rollback(c);
			Utils.close(ps);
		}
	}
	
	public static boolean checkTietoCustomers(String t_client, String b_client, String branch, Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean bool = false;
		try {
			ps = c.prepareStatement("select * from bf_xumo_customers where branch = ? and bank_customer_id = ? and tieto_customer_id = ?");
			ps.setString(1, branch);
			ps.setString(2, b_client.length() == 8 ? b_client
					: b_client.substring(5));
			ps.setString(3, t_client.length() == 8 ? t_client
					: t_client.substring(5));
			rs = ps.executeQuery();
			if (rs.next()) {
				bool = true;
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error("TCLIENTSERVICE.CHECKTIETOCUSTOMER ERROR: "
					+ e);
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return bool;
	}
	
	public static void updateTietoCutomers(String t_customer_id, String branch, String id, Connection c) throws Exception {
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			ps = c.prepareStatement("update bf_xumo_customers set tieto_customer_id = ? where id = ?");
			ps.setString(1, t_customer_id.length() == 8 ? t_customer_id
					: t_customer_id.substring(5));
			ps.setString(2, id);
			ps.execute();
			bool = true;
		} finally {
			if (!bool)
				Utils.rollback(c);
			Utils.close(ps);
		}
	}
	
//	private static List<FilterField> getFilterFields(TclientFilter filter)
//	{
//		List<FilterField> flfields = new ArrayList<FilterField>();
//		return flfields;
//	}
	
	private static RowType_ListCustomers_Request getFilterParams(TclientFilter filter) {
		RowType_ListCustomers_Request params = new RowType_ListCustomers_Request();

		if (filter.getF_names() != null) {
			params.setF_NAMES(filter.getF_names());
		}

		if (filter.getSurname() != null) {
			params.setSURNAME(filter.getSurname());
		}

		if (filter.getClient() != null) {
			params.setCLIENT(filter.getClient());
		}
		if (filter.getClient_b() != null) {
			params.setCLIENT_B(filter.getClient_b());
		}

		return params;
	}
	
	public static int getCount(TclientFilter filter, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		int result = 0;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			// connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			// connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setBANK_C(ConnectionPool.getValue("EMPC_BANK_C"));
			connectionInfo.setGROUPC(ConnectionPool.getValue("EMPC_GROUPC"));
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
			parameters = getFilterParams(filter);
			parameters.setBANK_C(ConnectionPool.getValue("EMPC_BANK_C"));
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
			issuingPortProxy.listCustomers(connectionInfo, parameters, responseInfo, details);
			result = details.value.getRow().length;

			System.out.println("result(COUNT details) => " + result);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}

		return result;
	}
	
	public static List<Tclient> getTclientsFl(int pageIndex, int pageSize, TclientFilter filter, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		List<Tclient> list = new ArrayList<Tclient>();
		// int v_lowerbound = pageIndex;
		// int v_upperbound = v_lowerbound + pageSize;

		String prev_client_id = "00000002";

		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		// connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		// connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setBANK_C(ConnectionPool.getValue("EMPC_BANK_C"));
		connectionInfo.setGROUPC(ConnectionPool.getValue("EMPC_GROUPC"));

		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

		RowType_ListCustomers_Request parameters = new RowType_ListCustomers_Request();
		parameters = getFilterParams(filter);
		parameters.setBANK_C(ConnectionPool.getValue("EMPC_BANK_C"));

		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		// ListType_Generic listType_Generic = null;

		ListType_GenericHolder details = new ListType_GenericHolder();

		try {
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
			if (details.value.getRow() != null) {
				for (int i = 0; i < details.value.getRow().length; i++) {

					rs = new Tclient();

					for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
						if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
							rs.setClient(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
							rs.setStatus(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("CLIENT_B"))
							rs.setClient_b(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
							rs.setF_names(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
							rs.setSurname(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("PERSON_CODE"))
							rs.setPersone_code(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("B_DATE")) {
							try {
								if (!details.value.getRow(i).getItem(j).getValue().equals("")
										|| details.value.getRow(i).getItem(j).getValue() != null)
									rs.setB_date(df.parse(details.value.getRow(i).getItem(j).getValue().length() > 10 ? details.value.getRow(i).getItem(j).getValue().substring(0, 10)
											: details.value.getRow(i).getItem(j).getValue()));
							} catch (Exception e) {
								ISLogger.getLogger().error(CheckNull.getPstr(e));
								ISLogger.getLogger().error("details.value => "
										+ details.value.getRow(i).getItem(j).getValue());
								System.out.println("details.value => "
										+ details.value.getRow(i).getItem(j).getValue());
							}
						}
						if (details.value.getRow(i).getItem(j).getName().equals("R_E_MAILS"))
							rs.setR_emails(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("R_MOB_PHONE"))
							rs.setRmob_phone(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("R_STREET"))
							rs.setR_street(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("R_CITY"))
							rs.setR_city(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("R_CNTRY"))
							rs.setR_cntry(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
							rs.setCard(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("BANK_C"))
							rs.setBank_c(details.value.getRow(i).getItem(j).getValue());
					}

					if (!prev_client_id.equals(rs.getClient())) {
						prev_client_id = rs.getClient();
						list.add(rs);
					}
				}
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("Exception in getTclientsFl =>"
					+ e.getLocalizedMessage() + e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return list;

	}
	
	public static Res block_card(String branch, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String reason, String reason_text, String card) {
		Res res = new Res();

		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();
		parameters.setBANK_C(settings.get(branch).getBank_c());
		parameters.setGROUPC(settings.get(branch).getGroup_c());
		parameters.setSTOP_CAUSE(reason);
		parameters.setTEXT(reason_text);
		parameters.setCARD(card);

		OperationResponseInfo orInfo = null;
		try {
			orInfo = issuingPortProxy.addCardToStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0) {
				res.setCode(orInfo.getResponse_code().intValue());
				res.setName(orInfo.getError_action()
						+ orInfo.getError_description());
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res.setName(e.getMessage());
			res.setCode(0);
			e.printStackTrace();
		}

		return res;
	}
	
	public static Res sendPayment(String branch, TrPay tp, String alias, String ccy, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String generalId, int uid, String un) {
		Res res = new Res();
		Connection c = null;
		CallableStatement csParam = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection();
			String sessionIdFromTable = getSessionId(c, branch, tp.getCard_acc(), tp.getAccount_no(), generalId, tp.getPan());
			sessionIdFromTable = sessionIdFromTable == null ? ""
					: sessionIdFromTable;
			if (sessionIdFromTable.isEmpty()) {
				ISLogger.getLogger().error("sessionIdFromTable is EMPTY");
			}
			ISLogger.getLogger().error("sessionIdFromTable: "
					+ sessionIdFromTable);
			csParam = c.prepareCall("{ call param.SetParam('P_SESS_ID', ?)}");
			csParam.setString(1, sessionIdFromTable);
			csParam.execute();
			String realCard = Utils.getValueFromSql("select real_card from humo_cards c where account_no = '"
					+ tp.getAccount_no() + "'", alias);
			Date date = new Date();
			ISLogger.getLogger().error("sendPayment real_card: " + realCard);
			ISLogger.getLogger().error("sendPayment amount: "
					+ tp.getAmount().toString());
			ISLogger.getLogger().error("sendPayment generalId: " + generalId);
			ISLogger.getLogger().error("sendPayment branch: " + branch);
			ISLogger.getLogger().error("sendPayment uid: " + uid);
			ISLogger.getLogger().error("sendPayment un: " + un);
			ISLogger.getLogger().error("sendPayment ip: " + getIp());
			ISLogger.getLogger().error("sendPayment time: " + date.getTime());
			cs = c.prepareCall("{ ? = call HUMO_EMPS_DC.refill_card(?,?,?,?,?) }");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			cs.setString(2, realCard);
			cs.setString(3, tp.getAmount().toString());
			cs.setString(4, "110");
			cs.setString(5, generalId);
			cs.setString(6, branch);
			cs.execute();
			if (sessionIdFromTable.isEmpty() || sessionIdFromTable.equals("")) {
				csParam = c.prepareCall("{? = call Param.getparam('P_SESS_ID') }");
				csParam.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
				csParam.execute();
				String sessionIdFromParam = csParam.getString(1);
				ISLogger.getLogger().error("sessionIdFromParam: "
						+ sessionIdFromParam);
				if (!Strings.isNullOrEmpty(sessionIdFromParam)) {
					csParam = c.prepareCall("{? = call Param.getparam('P_ERROR') }");
					csParam.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
					csParam.execute();
					String error = csParam.getString(1);
					ISLogger.getLogger().error("sendPayment error: " + error);
					res.setName(error);
				}
				if (checkPaymentInTableExistense(c, generalId, branch, tp.getCard_acc())) {
					ISLogger.getLogger().error("checkPaymentInTableExistence true!");
					insertSessionId(c, branch, tp.getCard_acc(), tp.getAccount_no(), generalId, tp.getPan(), sessionIdFromParam);
				} else {
					ISLogger.getLogger().error("checkPaymentInTableExistence false!");
					insertSinglePaymentInTable(c, branch, tp.getCard_acc().substring(9, 17), tp.getCl_name(), tp.getCard_acc(), tp.getAmount().toString(), "6", sessionIdFromParam);
				}
			}

			if (cs.getInt(1) > 0) {
				res.setCode(0);
				res.setName(String.valueOf(cs.getInt(1)));
				Bf_globuz_trans bf_globuz_trans = new Bf_globuz_trans();
				bf_globuz_trans.setEMPID(String.valueOf(uid));
				bf_globuz_trans.setGENID(Long.parseLong(generalId));
				bf_globuz_trans.setGLOBID(Long.parseLong(res.getName()));
				bf_globuz_trans.setBRANCH(branch);
				bf_globuz_trans.setSTATE(2);
				insertIntoBfGlobuzTrans(bf_globuz_trans, c);

			} else {
				res.setCode(-1);
			}
			c.commit();
			cs.clearParameters();
			csParam.clearParameters();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			System.out.println(e.getLocalizedMessage());
		} finally {
			Utils.close(csParam);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}
	//***************************************************************************
//	public static List<AccInfo> getAccInfo(String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) throws ParseException 
//	{
//		
//		List<AccInfo> list = new ArrayList<AccInfo>();
//		
//		//try
//	//	{
//			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
//			System.err.println("EMPC_BANK_C = "+EMPC_BANK_C);
//			connectionInfo.setBANK_C(EMPC_BANK_C);
//			connectionInfo.setGROUPC(EMPC_GROUPC);
//			System.out.println("EMPC_GROUPC = "+EMPC_GROUPC);
//			String info = utils.getExternalSession();
//			connectionInfo.setEXTERNAL_SESSION_ID(info);
//			System.out.println(info);
//			
//			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
//			System.out.println("client = "+client);
//			parameters.setCLIENT(client);
//		
//		
//			
//					
//			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
//			
////		} catch (RemoteException e) {
////			e.printStackTrace();
////		}
//			//moy kod>>
//			//ListType_Generic listTypeGenericMoy=new ListType_Generic();
//			//<<moy kod
//			
//			//System.out.println("getAccInfo Error_action =>" + responseInfo.value.getError_action());
//			//System.out.println("getAccInfo Error_description =>" + responseInfo.value.getError_description());
//			
//			//ObjectMapper mapper = new ObjectMapper();
//			//System.out.println("details_list_customers "+mapper.writeValueAsString(details_list_customers));
//			
//			
////			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic_lcm);
//			
//			
//			try {
//				issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details_list_customers);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
////			issuingPortProxy.newCard(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
//			
//			//System.out.println("details.value.getRow() = "+details.value.getRow());
//
//			//System.out.println(details_list_customers.toString());
//			
//			
//			
//			
////			String value = mapper.writeValueAsString(details_list_customers);
////			System.out.println("value = \n"+value);
////			ISLogger.getLogger().error(mapper.writeValueAsString(responseInfo));
////			ISLogger.getLogger().error(value);
//			
//			
//			for (int i = 0; i < details_list_customers.value.getRow().length; i++)
//				
//							
//				
//				
//				
//			{
//				AccInfo rs = new AccInfo();
//				
//				for (int j = 0; j < details_list_customers.value.getRow(i).getItem().length; j++)
//				{
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setAccount_no(Long.parseLong(details_list_customers.value.getRow(i).getItem(j).getValue()));
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CLIENT")) rs.setClient(details_list_customers.value.getRow(i).getItem(j).getValue());
//					
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
//					{
//						rs.setCard_acct(details_list_customers.value.getRow(i).getItem(j).getValue());
//					}
//					
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CTIME"))
//					{
//						if (!details_list_customers.value.getRow(i).getItem(j).getName().equals(""))
//						{
//							rs.setCtime(df.parse(details_list_customers.value.getRow(i).getItem(j).getValue().substring(0, 10)));
//						}
//					}
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("AC_STATUS")) rs.setAc_status(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CL_STATUS")) rs.setCl_status(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("ACC_PRTY")) rs.setAcc_prty(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE")) rs.setC_accnt_type(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CCY")) rs.setCcy(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY"))
//					{
//						if (!details_list_customers.value.getRow(i).getItem(j).getName().equals(""))
//						{
//							rs.setAb_expirity(df.parse(details_list_customers.value.getRow(i).getItem(j).getValue().substring(0, 10)));
//						}
//					}
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("F_NAMES")) rs.setF_names(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("SURNAME")) rs.setSurname(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CITY")) rs.setCity(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT")) rs.setTranz_acct(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CARD")) rs.setCard(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("STATUS"))
//					{
//						rs.setStatus1(details_list_customers.value.getRow(i).getItem(j).getValue());
//						rs.setAc_status(details_list_customers.value.getRow(i).getItem(j).getValue());
//						
//					}
//					// if
//					// (details.value.getRow(i).getItem(j).getName().equals("STATUS2"
//					// ))
//					// rs.setStatus2(details.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("PRODUCT")) rs.setProduct(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("STREET")) rs.setStreet(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details_list_customers.value.getRow(i).getItem(j).getValue())));
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) rs.setAgre_nom(details_list_customers.value.getRow(i).getItem(j).getValue());
//					if (details_list_customers.value.getRow(i).getItem(j).getName().equals("CONTRACT")) rs.setContract(details_list_customers.value.getRow(i).getItem(j).getValue());
//					
//					// if
//					// (details.value.getRow(i).getItem(j).getName().equals("COND_SET"
//					// ))
//					// if
//					// (details.value.getRow(i).getItem(j).getName().equals("B_BR_ID"
//					// ))
//					// if
//					// (details.value.getRow(i).getItem(j).getName().equals("OFFICE_ID"
//					// ))
//					// MAIN_ROW
//				}
//				
//			
//				rs.setCardlist(getCardInfo(rs, alias, issuingPortProxy));
//				/*
//				 * if
//				 * (client.getCard().equals(rs.getCardlist().get(0).getCARD())
//				 * && !client.getCard().equals("")) { list.add(rs); }
//				 */
//				list.add(rs);
//			}
//			return list;
//		}
	
	//*****************************************
	
	
	
	
	
		//catch (Exception e)
		//{
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			ObjectMapper mapper = new ObjectMapper();
//			String value = mapper.writeValueAsString(details_list_customers);
//			ISLogger.getLogger().info(value);
//			LtLogger.getLogger().error(CheckNull.getPstr(e));
//			LtLogger.getLogger().info(value);
//			e.printStackTrace();
//			System.out.println("Exception => " + e.getLocalizedMessage());
		//}
		
	//}
	
	
	
	//public static List<AccInfo> getAccInfo(Tclient client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
	public static List<AccInfo> getAccInfo(String branch, String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)

	{
		ISLogger.getLogger().error("ZAWOL V PROCEDURU");
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
	                    + "\n"+objectMapper.writeValueAsString(connectionInfo)
	                    + objectMapper.writeValueAsString(parameters)
	                    + objectMapper.writeValueAsString(details));
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
					accInfo.setCardlist(getCardInfo(branch, accInfo, alias, issuingPortProxy));
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
	
	
	

	public static List<AccInfo> getAccInfoShowCards(String branch, String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)

	{
		ISLogger.getLogger().error("ZAWOL V PROCEDURU");
		List<AccInfo> list = new ArrayList<AccInfo>();

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			ISLogger.getLogger().error("bankc: "
					+ settings.get(branch).getBank_c());
			ISLogger.getLogger().error("groupc: "
					+ settings.get(branch).getGroup_c());
			System.out.println("bankc: " + settings.get(branch).getBank_c());
			System.out.println("groupc: " + settings.get(branch).getGroup_c());
			connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			// connectionInfo.setFAULT_MODE(BigDecimal.valueOf(0));
			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCLIENT(client);
			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_GenericHolder details = new ListType_GenericHolder();
			ISLogger.getLogger().error("getAccInfoShowCards issuingPortProxy: "+issuingPortProxy.toString());
			ISLogger.getLogger().error("getAccInfoShowCards listAccounts connectionInfo: "
					+ objectMapper.writeValueAsString(connectionInfo));
			ISLogger.getLogger().error("getAccInfoShowCards listAccounts parameters: "
					+ objectMapper.writeValueAsString(parameters));
			ISLogger.getLogger().error("getAccInfoShowCards listAccounts details: "
					+ objectMapper.writeValueAsString(details));
			try {
				issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
			} catch (NullPointerException e) {
				if (details.value.getRow() != null) {
					for (int i = 0; i < details.value.getRow().length; i++) {
						AccInfo accInfo = new AccInfo();
						for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
							if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
								accInfo.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
							if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
								accInfo.setClient(details.value.getRow(i).getItem(j).getValue());
							if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
								accInfo.setCard_acct(details.value.getRow(i).getItem(j).getValue());
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
							ISLogger.getLogger().error("getAccInfoShowCards accInfo: "
									+ objectMapper.writeValueAsString(accInfo));
						}
						accInfo.setCardlist(getCardInfo(branch, accInfo, alias, issuingPortProxy));
						list.add(accInfo);
					}
				}
				return list;
			}
			if (details.value.getRow() != null) {
				for (int i = 0; i < details.value.getRow().length; i++) {
					AccInfo accInfo = new AccInfo();
					for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
						if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
							accInfo.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
						if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
							accInfo.setClient(details.value.getRow(i).getItem(j).getValue());
						if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
							accInfo.setCard_acct(details.value.getRow(i).getItem(j).getValue());
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
						ISLogger.getLogger().error("getAccInfoShowCards accInfo: "
								+ objectMapper.writeValueAsString(accInfo));
					}
					accInfo.setCardlist(getCardInfo(branch, accInfo, alias, issuingPortProxy));
					list.add(accInfo);
				}
			}
			for (int k = 0; k < list.size(); k++) {
				AccInfo acc = list.get(k);
				for (int l = 0; l < acc.getCardlist().size(); l++) {
					CardInfo card = acc.getCardlist().get(l);
					// System.out.println("card.getCARD() = "+card.getCARD());
				}
			}
		} catch (NullPointerException e) {
			ISLogger.getLogger().error("getAccInfoShowCards ERRORRRR: "
					+ e.getLocalizedMessage());
			// TODO: handle exception
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("Exception => " + e.getLocalizedMessage());
			ISLogger.getLogger().error("getAccInfoShowCards EXCEPTION: "
					+ e.getLocalizedMessage());
		}
		return list;
	}
	
	
	
	
	
	
	
	
	public static Res addCustomerAndAgreement(Customer new_customer, GlobuzAccount acc, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy, String branch) {
		Res res = new Res();
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;

		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		RowType_Customer rtc = new RowType_Customer();
		Calendar cal_p = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		rtc.setF_NAMES(new_customer.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(branch + new_customer.getId_client());
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
		agreement.setBRANCH(settings.get(branch).getBranch_id());
		agreement.setBANK_CODE(settings.get(branch).getBank_c());
		agreement.setBINCOD(settings.get(branch).getBincod());
		agreement.setCITY("Tashkent");
		agreement.setENROLLED(calendar);
		agreement.setREP_LANG("1");
		agreement.setPRODUCT("01");
		agreement.setRISK_LEVEL("A");
		agreement.setSTATUS("10");
		agreement.setSTREET(rtc.getR_STREET());
		agreement.setE_MAILS(rtc.getR_E_MAILS());
		agreement.setCONTRACT("0000002782");
		// RowType_AgreementHolder agreementInfo = new
		// RowType_AgreementHolder(agreement);

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

		logicalCard.setRANGE_ID(new BigDecimal(settings.get(branch).getRange_id()));

		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		physicalCard.setCARD_NAME((rtc.getF_NAMES() + " " + rtc.getSURNAME()).length() > 24 ? (rtc.getF_NAMES()
				+ " " + rtc.getSURNAME()).substring(0, 23)
				: rtc.getF_NAMES() + " " + rtc.getSURNAME());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		eMV_Data.setCHIP_APP_ID(new BigDecimal(settings.get(branch).getChip_app_id()));
		// eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(201));

		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(logicalCard);
		cardInfo.setPhysicalCard(physicalCard);
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });

		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;

		try {
			orInfo = issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
			// orInfo = issuingPortProxy.newCustomerAndAgreement(
			// connectionInfo, customerInfo, customListInfo,
			// agreementInfo, accountsListInfo, cardsListInfo);

			System.out.println("Response Info output:");
			System.out.println("-------------------------------");
			System.out.println("Response code = " + orInfo.getResponse_code());
			System.out.println("Error description = "
					+ orInfo.getError_description());
			System.out.println("Error action = " + orInfo.getError_action());
			System.out.println("-------------------------------");

			if (orInfo.getError_description() != null) {
				System.out.println("ERROR tieto add client "
						+ orInfo.getResponse_code() + " client "
						+ customerInfo.value.getCLIENT() + "Error=>"
						+ orInfo.getError_description());
				res.setCode(0);
				res.setName(orInfo.getError_description());
			} else {
				// alert("Клиент добавлен (GLOBUZ)");
				System.out.println("Клиент добавлен (GLOBUZ)=>"
						+ customerInfo.value.getCLIENT());
				res.setCode(1);
				res.setName("Клиент добавлен (GLOBUZ)");

				// ClientsService.create(new_customer);

			}
		} catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(0);
			res.setName(e.getMessage());
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return res;
	}
	
	public static Res addNewAgreement(HashMap<String, EmpcSettings> settingsForCard, String branch, Customer new_customer, GlobuzAccount acc, Tclient tc, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) throws JsonProcessingException {
		Res res = new Res();
		boolean agre_exists = false;
		BigDecimal agre_nom = null;
		ISLogger.getLogger().info("Add new agreement branch: "
				+ settingsForCard.get(branch));
		// BigDecimal agre_nom = new BigDecimal("4694");

		// moy kod
		ISLogger.getLogger().error("21JAN:branch " + branch);
		ISLogger.getLogger().error("21JAN:tc.getClient() " + tc.getClient());
		ISLogger.getLogger().error("21JAN:alias " + alias);
		List<AccInfo> listAcc = null;
		try {
			listAcc = TclientService.getAccInfo(branch, tc.getClient(), alias, issuingPortProxy);
		} catch (Exception e1) {
			System.out.println("getaccinfo error " + e1);
			e1.printStackTrace();
		}
		for (int i = 0; i < listAcc.size(); i++) {
			System.out.println("first for");
			for (int k = 0; k < listAcc.get(i).getCardlist().size(); k++) {
				System.out.println("second for");
				CardInfo cardInfo = listAcc.get(i).getCardlist().get(k);

				agre_nom = BigDecimal.valueOf(Long.parseLong(cardInfo.getAGREEMENT_KEY()));
				System.out.println("AGRE_NOM: " + agre_nom);
				agre_exists = true;
				break;

			}
		}

		if (agre_exists) {
			ISLogger.getLogger().error("AGREEMENT EXISTS!");
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
				mainAgreementInfo.setAGRE_NOM(agre_nom);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());

				globus.issuing_v_01_02_xsd.ListType_AccountInfo accInfo = new ListType_AccountInfo();

				globus.issuing_v_01_02_xsd.RowType_AccountInfo[] rows = new globus.issuing_v_01_02_xsd.RowType_AccountInfo[1];

				globus.issuing_v_01_02_xsd.RowType_AccountInfo row = new RowType_AccountInfo();

				globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
				System.out.println("ACC + " + acc.getId());
				System.out.println("bankc + "
						+ settingsForCard.get(branch).getBank_c());
				System.out.println("group_c + "
						+ settingsForCard.get(branch).getGroup_c());
				System.out.print("utils ");
				System.out.println(Utils.getValue("EMPC_TIETO_HOST"));
				base_info.setC_ACCNT_TYPE("00");
				base_info.setCARD_ACCT(acc.getId());
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
				// base_info.setACCOUNT_NO(new BigDecimal(acc.getId()));
				base_info.setTRANZ_ACCT(acc.getId());

				row.setBase_info(base_info);
				ObjectMapper mapper = new ObjectMapper();
				ISLogger.getLogger().error("base_info: "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(base_info));

				rows[0] = row;

				accInfo.setRow(rows);

				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder(accInfo);

				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
				logicalCard.setCOND_SET("001");
				logicalCard.setRISK_LEVEL("A");
				logicalCard.setBASE_SUPP("1");
				logicalCard.setF_NAMES(new_customer.getP_first_name());
				logicalCard.setSURNAME(new_customer.getP_family());
				logicalCard.setRANGE_ID(new BigDecimal(settingsForCard.get(branch).getRange_id()));
				// !
				logicalCard.setCARD_TYPE("01");

				logicalCard.setBRANCH(settingsForCard.get(branch).getBranch_id());
				// !
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				physicalCard.setCARD_NAME((new_customer.getP_first_name() + " " + new_customer.getP_family()).length() > 24 ? (new_customer.getP_first_name()
						+ " " + new_customer.getP_family()).substring(0, 23)
						: new_customer.getP_first_name() + " "
								+ new_customer.getP_family());
				physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				// !
				eMV_Data.setCHIP_APP_ID(new BigDecimal(settingsForCard.get(branch).getChip_app_id()));
				// eMV_Data.setCHIP_APP_ID(ConnectionPool.getValue(""));
				// !
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

				ISLogger.getLogger().error(mapper.writeValueAsString("10 connectionInfo="
						+ connectionInfo));
				ISLogger.getLogger().error(mapper.writeValueAsString("10 mainAgreementInfo: "
						+ mainAgreementInfo));
				ISLogger.getLogger().error(mapper.writeValueAsString("10 accountsListInfo="
						+ accountsListInfo));
				ISLogger.getLogger().error(mapper.writeValueAsString("10 cardsListInfo="
						+ cardsListInfo));
				globus.issuing_v_01_02_xsd.RowType_Agreement agrInfo = new RowType_Agreement();

				Calendar cal = Calendar.getInstance();
				agrInfo.setAGRE_NOM(agre_nom);
				agrInfo.setBINCOD(settingsForCard.get(branch).getBincod());
				ISLogger.getLogger().error("Add new agreeement bincod: "
						+ settingsForCard.get(branch).getBincod());
				agrInfo.setBRANCH(settingsForCard.get(branch).getBranch_id());
				ISLogger.getLogger().error("Add new agreement branch_id2: "
						+ settingsForCard.get(branch).getBranch_id());
				agrInfo.setBANK_CODE(settingsForCard.get(branch).getBank_c());
				ISLogger.getLogger().error("Add new agreement bank_c: "
						+ settingsForCard.get(branch).getBank_c());
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

				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agrInfo);
				// System.out.println(accountsListInfo);
				System.out.println("10 agreementInfo="
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
				System.out.println("10 connectionInfo1="
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo));
				ISLogger.getLogger().error("10 connectionInfo1 "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo));
				ISLogger.getLogger().error("10 agreementInfo1 "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
				ISLogger.getLogger().error("10 accountsListInfo1 "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
				ISLogger.getLogger().error("10 cardsListInfo1 "
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
				if (Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_FROM")) <= Integer.parseInt(acc.getId().substring(17))
						&& Integer.parseInt(acc.getId().substring(17)) < Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM"))) {
					if (!Utils.getValueFromSql("select code from ss_humo_type_of_card where bin = '"
							+ settingsForCard.get(branch).getBincod()
							+ "' and branch = '" + branch + "'", alias).equals("4")) {
						res.setCode(0);
						res.setName("Неверные данные");
					}
				}
				if (Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM")) <= Integer.parseInt(acc.getId().substring(17))
						&& Integer.parseInt(acc.getId().substring(17)) < Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_PREVIOUS"))) {
					if (!Utils.getValueFromSql("select code from ss_humo_type_of_card where bin = '"
							+ settingsForCard.get(branch).getBincod()
							+ "' and branch = '" + branch + "'", alias).equals("5")) {
						res.setCode(0);
						res.setName("Неверные данные");
					}
				}
				OperationResponseInfo orInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
				// OperationResponseInfo orInfo =
				// issuingPortProxy.addInfo4Agreement(connectionInfo,
				// mainAgreementInfo, accountsListInfo, cardsListInfo);
				ISLogger.getLogger().error("10 agreementInfo after="
						+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));

				if (orInfo == null || orInfo.getError_description() != null) {
					ISLogger.getLogger().error("ADD NEW AGREEMENT ERROR DESC AGR EXISTS: "
							+ orInfo.getError_description());
					res.setCode(0);
					res.setName(orInfo == null ? "Не удалось отправить запрос в ЕМПЦ"
							: orInfo.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					// System.out.println("Response code = " +
					// orInfo.getResponse_code());
					// System.out.println("Error description = " +
					// orInfo.getError_description());
					// System.out.println("Error action = " +
					// orInfo.getError_action());
					// System.out.println("-------------------------------");
				} else {
					Connection c = null;
					try {
						ISLogger.getLogger().error("PERED INSERTAMI");
						c = ConnectionPool.getConnection();
						ISLogger.getLogger().error("10 cardsListInfo"
								+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
						ISLogger.getLogger().error("10 accountsListInfo"
								+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
						CustomerService.insertCards(cardsListInfo, accountsListInfo, new_customer.getId_client(), new_customer.getBranch(), c);
						java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						CustomerService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
						CustomerService.insertAgreement(connectionInfo, agreementInfo, c);
						c.commit();
						ISLogger.getLogger().error("10 NEW CARD: "
								+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
						CustomerService.psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
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
			} catch (Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				res.setCode(0);
				res.setName(e.getMessage());
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			}
		}

		else {
			ISLogger.getLogger().error("AGREEMENT DOESN'T EXISTS!");
			try {
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				connectionInfo.setBANK_C(settingsForCard.get(branch).getBank_c());
				connectionInfo.setGROUPC(settingsForCard.get(branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());

				RowType_Agreement agreement = new RowType_Agreement();
				// !
				agreement.setBRANCH(settingsForCard.get(branch).getBranch_id());
				// !
				agreement.setCLIENT(tc.getClient());
				agreement.setBANK_CODE(tc.getBank_c());
				ISLogger.getLogger().error("Add new agreeement bincod: "
						+ settingsForCard.get(branch).getBincod());
				agreement.setBINCOD(settingsForCard.get(branch).getBincod());
				agreement.setCITY("Tashkent");
				agreement.setENROLLED(calendar);
				agreement.setREP_LANG("1");
				agreement.setPRODUCT("01");
				agreement.setRISK_LEVEL("A");
				agreement.setSTATUS(tc.getStatus());
				agreement.setSTREET(tc.getR_street());
				agreement.setE_MAILS(new_customer.getR_E_MAILS());
				agreement.setCONTRACT("00" + new_customer.getId_client());
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

				logicalCard.setRANGE_ID(new BigDecimal(settingsForCard.get(branch).getRange_id()));
				// !
				logicalCard.setCARD_TYPE("01");
				logicalCard.setBRANCH("001");
				// !
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				physicalCard.setCARD_NAME((new_customer.getP_first_name() + " " + new_customer.getP_family()).length() > 24 ? (new_customer.getP_first_name()
						+ " " + new_customer.getP_family()).substring(0, 23)
						: new_customer.getP_first_name() + " "
								+ new_customer.getP_family());
				physicalCard.setSTATUS1("1");
				physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

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
				cardsListInfo.value = cards;

				ISLogger.getLogger().error("NET AGREEMENTA agreementInfo="
						+ objectMapper.writeValueAsString(agreementInfo));
				ISLogger.getLogger().error("NET AGREEMENTA connectionInfo="
						+ objectMapper.writeValueAsString(connectionInfo));
				ISLogger.getLogger().error("NET AGREEMENTA accountsListInfo="
						+ objectMapper.writeValueAsString(accountsListInfo));
				ISLogger.getLogger().error("NET AGREEMENTA cardsListInfo="
						+ objectMapper.writeValueAsString(cardsListInfo));
				if (Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_FROM")) <= Integer.parseInt(acc.getId().substring(17))
						&& Integer.parseInt(acc.getId().substring(17)) < Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM"))) {
					if (!Utils.getValueFromSql("select code from ss_humo_type_of_card where bin = '"
							+ settingsForCard.get(branch).getBincod()
							+ "' and branch = '" + branch + "'", alias).equals("4")) {
						res.setCode(0);
						res.setName("Неверные данные");
					}
				}
				if (Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM")) <= Integer.parseInt(acc.getId().substring(17))
						&& Integer.parseInt(acc.getId().substring(17)) < Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_PREVIOUS"))) {
					if (!Utils.getValueFromSql("select code from ss_humo_type_of_card where bin = '"
							+ settingsForCard.get(branch).getBincod()
							+ "' and branch = '" + branch + "'", alias).equals("5")) {
						res.setCode(0);
						res.setName("Неверные данные");
					}
				}
				OperationResponseInfo orInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);

				if (orInfo == null || orInfo.getError_description() != null) {
					ISLogger.getLogger().error("ADD NEW AGREEMENT ERROR DESC AGR DOESN'T EXIST: "
							+ orInfo.getError_description());
					res.setCode(0);
					res.setName(orInfo.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = "
							+ orInfo.getResponse_code());
					System.out.println("Error description = "
							+ orInfo.getError_description());
					System.out.println("Error action = "
							+ orInfo.getError_action());
					System.out.println("-------------------------------");
				} else {
					Connection c = null;
					try {
						ObjectMapper mapper = new ObjectMapper();
						ISLogger.getLogger().info("PERED INSERTAMI");
						c = ConnectionPool.getConnection();
						ISLogger.getLogger().error("NET AGREEMENTA cardsListInfo"
								+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
						ISLogger.getLogger().error("NET AGREEMENTA accountsListInfo"
								+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
						CustomerService.insertCards(cardsListInfo, accountsListInfo, new_customer.getId_client(), new_customer.getBranch(), c);
						java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						CustomerService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
						CustomerService.insertAgreement(connectionInfo, agreementInfo, c);
						c.commit();
						ISLogger.getLogger().error("NET AGREEMENTA NEW CARD: "
								+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
						CustomerService.psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
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
	
	
	
	public static Res getAccountBal(String branch, CardInfo rs, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		Res res = new Res();
		ObjectMapper objectMapper = new ObjectMapper();
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ISLogger.getLogger().error("queryAccountBalanceByCard sId: "+connectionInfo.getEXTERNAL_SESSION_ID());
		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
		RowType_AccBalanceQueryByCard_Request parametersCard = new RowType_AccBalanceQueryByCard_Request();
		parametersCard.setBANK_C(settings.get(branch).getBank_c());
		parametersCard.setGROUPC(settings.get(branch).getGroup_c());
		parametersCard.setCARD(rs.getCARD());
		ISLogger.getLogger().error("paramparam");
		try {
			objectMapper.writeValueAsString(parametersCard);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			issuingPortProxy.queryAccountBalanceByCard(connectionInfo, parametersCard, responseInfo, details);

			for (int i = 0; i < details.value.getRow().length; i++) {
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
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
			// System.out.println(responseInfo.value.getError_description().contains("\""));
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return res;
	}
	
    public static List<CardInfo> getCardInfo(String branch, AccInfo account, String alias,
            globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		List<CardInfo> list = new ArrayList<CardInfo>();

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
						getAccountBal(branch, rs, alias, issuingPortProxy);
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
					getAccountBal(branch, rs, alias, issuingPortProxy);
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
	

	
	public static List<AccInfo> getAccInfo_active(String branch, String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		ISLogger.getLogger().error("ZAWOL V GETACC_INFO");
		List<AccInfo> list = new ArrayList<AccInfo>();

		try {
			ISLogger.getLogger().error("ZAWOL V TRY GETACC_INFO");
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCLIENT(client);
			parameters.setSTATUS("0");

			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();

			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);

			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);

			for (int i = 0; i < details.value.getRow().length; i++) {
				AccInfo rs = new AccInfo();

				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
						rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
						rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
						rs.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS"))
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS"))
						rs.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY"))
						rs.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE"))
						rs.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY"))
						rs.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
						rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
						rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY"))
						rs.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
						rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT"))
						rs.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
						rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) {
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());

					}
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT"))
						rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
						rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY"))
						rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM"))
						rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT"))
						rs.setContract(details.value.getRow(i).getItem(j).getValue());
				}
				list.add(rs);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error("GET ACC INFO ACTIVE ERROR: "
					+ CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return list;

	}
	
	public static AccInfo getAccInfoByCard(String branch, String Card_acct, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		AccInfo rs = null;
		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

			RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
			parameters.setCARD_ACCT(Card_acct);

			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();

			ListType_Generic listType_Generic = null;
			ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);

			issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);

			for (int i = 0; i < details.value.getRow().length; i++) {
				rs = new AccInfo();

				for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO"))
						rs.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT"))
						rs.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
						rs.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS"))
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS"))
						rs.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY"))
						rs.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE"))
						rs.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY"))
						rs.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY")) {
						if (!details.value.getRow(i).getItem(j).getName().equals("")) {
							rs.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES"))
						rs.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME"))
						rs.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY"))
						rs.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
						rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT"))
						rs.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD"))
						rs.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS")) {
						rs.setStatus1(details.value.getRow(i).getItem(j).getValue());
						rs.setAc_status(details.value.getRow(i).getItem(j).getValue());

					}
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT"))
						rs.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET"))
						rs.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY"))
						rs.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM"))
						rs.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT"))
						rs.setContract(details.value.getRow(i).getItem(j).getValue());
				}
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return rs;
	}
	
	public static TietoCardSetting getTietoCardSetting(int tietocardsettingId, String alias) {

		TietoCardSetting tietocardsetting = new TietoCardSetting();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT * FROM BF_globuz_CARD_SETTING WHERE code=?");
			ps.setInt(1, tietocardsettingId);
			rs = ps.executeQuery();
			if (rs.next()) {
				tietocardsetting = new TietoCardSetting();

				tietocardsetting.setCode(rs.getInt("code"));
				tietocardsetting.setName(rs.getString("name"));
				tietocardsetting.setBin(rs.getString("bin"));
				tietocardsetting.setRisk_level(rs.getString("risk_level"));
				tietocardsetting.setFinancial_conditions(rs.getString("financial_conditions"));
				tietocardsetting.setMinimum_balance(rs.getBigDecimal("minimum_balance"));
				tietocardsetting.setId_chip_app(rs.getBigDecimal("id_chip_app"));
				tietocardsetting.setId_order_account(rs.getString("id_order_account"));
				ISLogger.getLogger().error("tietocardsetting.getOrder = "
						+ tietocardsetting.getId_order_account());
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
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return tietocardsetting;
	}
	
	public static String getkass_acc(String branch, String alias) {

		String res = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select acc.account res from BF_TR_ACC acc, BF_TR_TEMPLATE t "
					+ "where t.operation_id = 1 "
					+ "and acc.acc_template_id = t.acc_dt "
					+ "and ROWNUM = 1 "
					+ "and acc.branch = ? " + "order by t.ord");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			rs.next();
			res = rs.getString("res");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static Res check_user(String alias, String Branch_id, String TIETO_CUSTOMER_ID)
	{
		Res res;
		String BANK_CUSTOMER_ID = "";
		ResultSet rs = null;
		Connection c = null;
		PreparedStatement ps = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("Select BANK_CUSTOMER_ID From bf_xumo_customers TC " +
					"Where TC.BRANCH = ? and TC.TIETO_CUSTOMER_ID = ?");
			ps.setString(1, Branch_id);
			ps.setString(2, TIETO_CUSTOMER_ID);
			rs = ps.executeQuery();
			if(rs.next()) {
				BANK_CUSTOMER_ID = rs.getString("BANK_CUSTOMER_ID");
			}
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
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static GeneralInfo getSummOfPayment(String alias, Long General_id, Connection c) {
		ISLogger.getLogger().error("12FEB GENINFO IN METHOD");

		GeneralInfo generalInfo = new GeneralInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		String res = "";
		try {
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			ps = c.prepareStatement("select info.getBankType from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString(1);
			}
			ISLogger.getLogger().error("bankType: " + res);
			if (res.equals("051") || res.equals("020")) {
				ps = c.prepareStatement("select g.id, g.summa, g.acc_co, g.doc_num "
						+ "from (select * from GENERAL union all select * from GENERAL_ARH) g where g.id = ?");
				ps.setLong(1, General_id);
				rs = ps.executeQuery();
			} else {
				ps = c.prepareStatement("select g.id, g.summa, g.acc_co, g.doc_num "
						+ "from (select * from "
						+ alias
						+ ".GENERAL union all select * from "
						+ alias
						+ ".GENERAL_ARH) g where g.id = ?");
				ps.setLong(1, General_id);
				rs = ps.executeQuery();
			}
			while (rs.next()) {

				// String [] splitter;
				// splitter =
				// String.valueOf(rs.getString("summa")).split("\\.");
				// generalInfo.setSUMMA(BigDecimal.valueOf(Long.parseLong(splitter[0])));
				generalInfo.setSUMMA(BigDecimal.valueOf(Long.parseLong(rs.getString("summa"))));
				generalInfo.setID(General_id);
				generalInfo.setACC_CO(rs.getString("acc_co"));
				generalInfo.setDOC_NUM(rs.getString("doc_num"));
				ObjectMapper objectMapper = new ObjectMapper();
				ISLogger.getLogger().error("12FEB GENERAL INFO: "
						+ objectMapper.writeValueAsString(generalInfo));
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(inf);
		}
		return generalInfo;
	}
	
	public static Res check_allowed_card_action(int deal_group, int deal_id, int action_id, String card, String alias) {
		Res res = new Res(0, "");

		Connection c = null;
		CallableStatement proc = null;
		try {
			c = ConnectionPool.getConnection(alias);

			proc = c.prepareCall("{call BF.CHECK_ALLOWED_CARD_ACTION(?, ?, ?, ?) }");

			proc.setInt(1, deal_group);
			proc.setInt(2, deal_id);
			proc.setInt(3, action_id);
			proc.setString(4, card);

			proc.execute();

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		} finally {
			Utils.close(proc);
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static boolean check_card1(String new_card_type, List<AccInfo> cards, String alias) {
		String qstring = "";

		if (cards.size() != 0) {
			for (int i = 0; i < cards.size() - 1; i++) {
				qstring += cards.get(i).getCard_type();
				qstring += ",";
			}
			qstring += cards.get(cards.size() - 1).getCard_type();
		} else
			qstring = null;

		System.out.println("argument:" + qstring);

		// String res="";

		Connection c = null;
		CallableStatement proc = null;
		try {
			c = ConnectionPool.getConnection(alias);

			proc = c.prepareCall("{ call BF.CHECK_OPEN_CARD(?,?) }");

			proc.setString(1, new_card_type);
			proc.setString(2, qstring);

			proc.execute();

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			return false;
		} finally {
			Utils.close(proc);
			ConnectionPool.close(c);
		}

		return true;
	}
	
	public static Res check_card(String new_card_type, List<AccInfo> cards, String alias) {
		ISLogger.getLogger().error("ZAWOL V CHECK_CARD");
		Res res = new Res(0, "");
		String qstring = "";

		if (cards.size() != 0) {
			for (int i = 0; i < cards.size() - 1; i++) {
				qstring += cards.get(i).getCard_type();
				qstring += ",";
			}
			qstring += cards.get(cards.size() - 1).getCard_type();
		} else
			qstring = null;
		System.out.println("argument:" + qstring);
		Connection c = null;
		CallableStatement proc = null;
		try {
			c = ConnectionPool.getConnection(alias);
			proc = c.prepareCall("{ call BF.CHECK_OPEN_CARD(?,?) }");
			proc.setString(1, new_card_type);
			proc.setString(2, qstring);
			proc.execute();

		} catch (Exception e) {
			ISLogger.getLogger().error("CHECK CARD ERROR: "
					+ CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			// e.printStackTrace();
			res.setCode(-1);
			res.setName(e.getMessage());
			return res;
		} finally {
			Utils.close(proc);
			ConnectionPool.close(c);
		}
		return res;
	}
	
	public static List<Action> getActions(int userid, String user_branch, String alias)
	{
		
		List<Action> list = new ArrayList<Action>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			// System.out.println("select a.* from bf_actions a where a.mid=11 and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="+userid+" and r.actionid=a.id)");
			
			ps = c.prepareStatement("Select act.*" +
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
			rs = ps.executeQuery();
			
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
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
		
	}
	
	public static Res checkBfGlobuzTrans(Connection c)
	{
		Res res = new Res();
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement("select * from bf_globuz_trans");
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
		}finally{
			Utils.close(ps);
		}
		return res;
	}
	
	public static Res insertIntoBfGlobuzTrans(Bf_globuz_trans bf_globuz_trans, Connection c)
	{
		ISLogger.getLogger().error("INSERT INTO BF_GLOBUZ_TRANS");
		Res res = new Res();
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement("insert into bf_globuz_trans(branch, genid, globid, empid, trdate, state)" +
					" values (?, ?, ?, ?, sysdate, ?)");
			ISLogger.getLogger().error("bf_globuz_trans.getBRANCH(): "+bf_globuz_trans.getBRANCH());
			ISLogger.getLogger().error("bf_globuz_trans.getGENID(): "+bf_globuz_trans.getGENID());
			ISLogger.getLogger().error("bf_globuz_trans.getGLOBID(): "+bf_globuz_trans.getGLOBID());
			ISLogger.getLogger().error("bf_globuz_trans.getEMPID(): "+bf_globuz_trans.getEMPID());
			ISLogger.getLogger().error("bf_globuz_trans.getSTATE(): "+bf_globuz_trans.getSTATE());
			ps.setString(1, bf_globuz_trans.getBRANCH());
			ps.setLong(2, bf_globuz_trans.getGENID());
			ps.setLong(3, bf_globuz_trans.getGLOBID());
			ps.setString(4, bf_globuz_trans.getEMPID());
			ps.setInt(5, bf_globuz_trans.getSTATE());			
			ps.executeQuery();			
			res.setCode(1);
			res.setName("Успешно");
			Utils.close(ps);
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
		}finally{
			Utils.close(ps);
		}
		return res;
	}
	
	   public static void insertIntoBfGlobuzTrans(Bf_globuz_trans bfGlobuzTrans){
	       Connection c = null;
	       PreparedStatement ps = null;
	        try
	        {
	            ISLogger.getLogger().error("bf_globuz_trans: "+objectMapper.writeValueAsString(bfGlobuzTrans));
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement("insert into bf_globuz_trans(branch, genid, globid, empid, trdate, state)" +
	                    " values (?, ?, ?, ?, sysdate, ?)");
	            ps.setString(1, bfGlobuzTrans.getBRANCH());
	            ps.setLong(2, bfGlobuzTrans.getGENID());
	            ps.setLong(3, bfGlobuzTrans.getGLOBID());
	            ps.setString(4, bfGlobuzTrans.getEMPID());
	            ps.setInt(5, bfGlobuzTrans.getSTATE());           
	            ps.executeQuery();          
	               c.commit();
	        }
	        catch (SQLException e){
	            ISLogger.getLogger().error(CheckNull.getPstr(e));
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
	            ConnectionPool.close(c);
	            Utils.close(ps);
	        }
	    }

	public static String getCountryNameISO3(String ISO3) {
		String res = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getTConnection();
			ps = c.prepareStatement("SELECT name_en FROM base_country where iso3 = ?");
			ps.setString(1, ISO3);
			rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString("name_en");
			}
			if (res == null)
				res = ISO3;
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;

	}
	////////ПЕРЕВЫПУСК///////////////////ПЕРЕВЫПУСК//////////////////////
/*	public  ResponsInfo newAgreementHumo(Connection c,String alias,
			String branch, String clientId,
			String firstCardName, Customer	 customer, String clientIdHumo,String acc,String cardType) throws Exception {
		String	cl_type="2";
			System.out.println("777777newAgreementHumo7777777777777777");
			
			// customer =  HumoCardsService.getCustomerJ(c, branch,clientId);	

		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		
		 ResponsInfo responsInfo=new ResponsInfo();
	

		
		try {

			//external session
			String externalSesionId=ExternalSession.getExternalSession();
			//connectionInfo
			connectionInfo = new OperationConnectionInfo();
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
			connectionInfo.setBANK_C(HUMO_BANK_C);
			connectionInfo.setGROUPC(HUMO_GROUPC);
			//agreementInfo
			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
					getNewRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,"001", customer, alias, clientIdHumo));
			RowType_AccountInfo accountInfo = new RowType_AccountInfo();
			ISLogger.getLogger().info("accountInfo: "+accountInfo);
			//baseInfo
			accountInfo.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,customer.getBranch(),c));
			ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
			ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
			
			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
			//accountsListInfo.value = ltaccounts;
			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder(); 
			
			//новый договор
			System.out.println("DOWEL DO OTKRITIYA DOGOVORA");
			ISLogger.getLogger().info("DOWEL DO OTKRITIYA DOGOVORA");
			issuingPortProxy = initNp();
			//OperationResponseInfo operationResponsInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			
		
			
			//если нет ошибок

				System.out.println("NET OWIBOK V DOGOVORE");
				ISLogger.getLogger().info("NET OWIBOK V DOGOVORE");
				KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
				//mainAgreementInfo.setAGRE_NOM(agreementInfo.value.getAGRE_NOM());
				mainAgreementInfo.setAGRE_NOM(BigDecimal.valueOf(1625));
		
				
				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
			eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));//надо поменят	
				
				RowType_AccountInfo accountInfoDobKarta = new RowType_AccountInfo();

				accountInfoDobKarta.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,"00394",c));
				ListType_AccountInfo ltaccountsDobKarta = new ListType_AccountInfo();
				ltaccountsDobKarta.setRow(new RowType_AccountInfo[] { accountInfoDobKarta });
				
				ListType_AccountInfoHolder accountsListInfoDobKarta = new ListType_AccountInfoHolder();
				accountsListInfoDobKarta.value = ltaccountsDobKarta;
				
				RowType_CardInfo cardInfoDobKarta = new RowType_CardInfo();
				//logical card
				cardInfoDobKarta.setLogicalCard(getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
				//physical card
				cardInfoDobKarta.setPhysicalCard(getRowType_CardInfo_PhysicalCard(customer,firstCardName));
				//обращение в issuing
				cardInfoDobKarta.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				//обращение в issuing
				cards.setRow(new RowType_CardInfo[] { cardInfoDobKarta });
				ListType_CardInfoHolder cardsListInfoDobKarta = new ListType_CardInfoHolder();
				cardsListInfoDobKarta.value = cards;
				
				
				
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				
				System.out.println("****************connectionInfo****************");
				System.out.println(connectionInfo.toString());
				System.out.println("****************mainAgreementInfo****************");
				System.out.println(mainAgreementInfo.toString());
				System.out.println("****************accountsListInfoDobKarta****************");
				System.out.println(accountsListInfoDobKarta.toString());
				System.out.println("****************cardsListInfoDobKarta****************");
				System.out.println(cardsListInfoDobKarta.toString());
				//addInfo4Agreement
				OperationResponseInfo operationResponsInfoAdd = issuingPortProxy
				.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfoDobKarta, cardsListInfoDobKarta);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
				BigDecimal agrNom=agreementInfo.value.getAGRE_NOM();
				int resClient=0,resAgreement=0,resAccount=0,resCards=0;
				
				//resAgreement=insertAgreement(connectionInfo,agreementInfo,clientIdHumo,agrNom);
				
				
				if(operationResponsInfoAdd.getError_description()==null){
				
				
				responsInfo.setSuccessful(true);
				
				System.out.println("New Agreement создан:"+agreementInfo.value.getAGRE_NOM()+" client: "+agreementInfo.value.getCLIENT());
				//заносятся данные в HUMO_CARD_OPEN_RENEW
				insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"","","2","1");
				//заносятся данные в bf_empc_AGREEMENT
				insertNewClientAgreement(connectionInfo, agreementInfo,c);
				//заносятся данные в bf_EMPC_accounts
				insertNewClientAccounts(accountsListInfoDobKarta, cardsListInfoDobKarta, acc,c,clientIdHumo);
				
				if(firstCardName!=null){
					
					firstCardName=HumoCardsService.toTranslit(firstCardName);
					firstCardName.replace("XXX", "");
					firstCardName=firstCardName.replace("KHKHKH", "");
				
					firstCardName=firstCardName;
			
				
					firstCardName=firstCardName.trim();
			if(firstCardName.length()>24){
					
				firstCardName=firstCardName.substring(0,24);
				
				}
				}
				
				//достаёт номер карты из HUMO_CARDS
				String pan=isExsistCard(c, clientId, branch, firstCardName);
				
				System.out.println("ExsistCard:"+clientId+" "+branch+" "+firstCardName+" "+pan);
				ISLogger.getLogger().info("ExsistCard:"+clientId+" "+branch+" "+firstCardName+" "+pan);
				
				//если есть карта
				if(pan!=null){
					ISLogger.getLogger().info("update card");
					System.out.println("startStopList");
					//закрывается старая карта
					addStopList(pan, issuingPortProxy, HUMO_BANK_C, HUMO_GROUPC);
					System.out.println("endStopList");
					System.out.println("StartUpdate");
					//апдейтится информация по карте 
					updateHumoCards(cardsListInfoDobKarta, clientId, branch, c, accountsListInfoDobKarta, firstCardName);
					System.out.println("isExsistCard true");
					
				
				}
				//если нет карты
				else{
					ISLogger.getLogger().info("insert card");
					//инсерт в humo_cards
					insertNewClientHumoCards(cardsListInfoDobKarta, clientId, customer.getBranch(),alias,acc,c,clientIdHumo,accountsListInfoDobKarta);
					
					}
				
				responsInfo.setSuccessful(true);
				System.out.println("insert dob karta");
				
				}else{
					System.out.println("ERROR addInfo4Agreement111:"+operationResponsInfoAdd.getError_description());
					//insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"",operationResponsInfoAdd.getError_description(),"0","0");
				}
			
//			
		
				//System.out.println("EROR newAgreement222:"+operationResponsInfo.getError_description());
				//insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"",operationResponsInfo.getError_description(),"0","0");
			
		
		}
		
		catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		
		
		}
		return responsInfo;
	}*/
	
	static OperationResponseInfo addStopList(String realCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);

			// System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			RowType_AddCardToStopList_Request param = new RowType_AddCardToStopList_Request();

			param.setCARD(realCard);
			param.setSTOP_CAUSE("1");
			param.setTEXT("stolen");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);

			res = proxy.addCardToStop(connectionInfo, param);

			// proxy.getRealCard(connectionInfo, request, new
			// OperationResponseInfoHolder(), response);

		} catch (Exception e) {

			ISLogger.getLogger().error(e);

		}

		return res;

	}
	
	public static RowType_Agreement getNewRowType_Agreement(String HUMO_BINCOD, String HUMO_BANK_C, String branchId, Customer customer, String alias, String idClientHumo) {

		System.out.println("******customer:" + customer);
		RowType_Agreement agrInfo = new RowType_Agreement();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		agrInfo.setCLIENT(idClientHumo);

		System.out.println("idClientHumo78787: " + idClientHumo);

		agrInfo.setBINCOD(HUMO_BINCOD);
		agrInfo.setBRANCH(branchId);
		agrInfo.setBANK_CODE(HUMO_BANK_C);
		agrInfo.setSTREET(customer.getR_STREET() == null ? "STREET"
				: customer.getR_STREET());
		agrInfo.setCITY(customer.getR_CITY() == null ? "UZB"
				: customer.getR_CITY());
		agrInfo.setCOUNTRY(customer.getR_CNTRY() == null ? "UZB"
				: customer.getR_CNTRY());

		agrInfo.setREP_LANG("1");
		agrInfo.setSTATUS("10");
		agrInfo.setPRODUCT("01");
		agrInfo.setENROLLED(calendar);
		// agrInfo.setCLIENT(customerInfo.value.getCLIENT());

		return agrInfo;
	}
	
	
/*	public  void insertNewClient(RowType_Customer customerInfo, String client,Connection c)
	throws Exception {

boolean bool = false;
try {

	
	psInsertClient.setString(1, client== null ? "" : client);
	psInsertClient.setString(2, customerInfo.getF_NAMES()== null ? "" : customerInfo.getF_NAMES());
	psInsertClient.setString(3, customerInfo.getCL_TYPE()== null ? "" : customerInfo.getCL_TYPE());
	psInsertClient.setString(4, customerInfo.getCLIENT_B()== null ? "" : customerInfo.getCLIENT_B());
	psInsertClient.setString(5, customerInfo.getSURNAME()== null ? "" : customerInfo.getSURNAME());
	psInsertClient.setString(6, customerInfo.getM_NAME()== null ? "" : customerInfo.getM_NAME());
	psInsertClient.setDate(7, customerInfo.getDOC_SINCE()== null ? null : new java.sql.Date(customerInfo.getDOC_SINCE()
					.getTimeInMillis()));
	psInsertClient.setDate(8, customerInfo.getB_DATE()== null ? null : new java.sql.Date(customerInfo.getB_DATE()
					.getTimeInMillis()));
	psInsertClient.setString(9, customerInfo.getRESIDENT()== null ? "" : customerInfo.getRESIDENT());
	psInsertClient.setString(10, customerInfo.getSTATUS()== null ? "" : customerInfo.getSTATUS());
	psInsertClient.setString(11, customerInfo.getSEX()== null ? "" : customerInfo.getSEX());
	psInsertClient.setString(12, customerInfo.getSERIAL_NO()== null ? "" : customerInfo.getSERIAL_NO());
	psInsertClient.setString(13, customerInfo.getID_CARD()== null ? "" : customerInfo.getID_CARD());
	psInsertClient.setString(14, customerInfo.getR_CITY()== null ? "" : customerInfo.getR_CITY());
	psInsertClient.setString(15, customerInfo.getR_STREET()== null ? "" : com.is.humo.HumoCardsService.toTranslit(customerInfo.getR_STREET()));
	psInsertClient.setString(16, customerInfo.getR_E_MAILS()== null ? "" : customerInfo.getR_E_MAILS());
	psInsertClient.setString(17, customerInfo.getR_MOB_PHONE()== null ? "" : customerInfo.getR_MOB_PHONE());
	psInsertClient.setString(18, customerInfo.getR_PHONE()== null ? "" : customerInfo.getR_PHONE());
	psInsertClient.setString(19, customerInfo.getR_CNTRY()== null ? "" : customerInfo.getR_CNTRY());
	psInsertClient.setString(20, customerInfo.getISSUED_BY()== null ? "" : customerInfo.getISSUED_BY());
	psInsertClient.setString(21, customerInfo.getPERSON_CODE()== null ? "" : customerInfo.getPERSON_CODE());
	psInsertClient.setString(22, customerInfo.getDOC_TYPE()== null ? "" : customerInfo.getDOC_TYPE());
	// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
	// ? null
	// : new java.sql.Date(customerInfo.value
	// .getREC_DATE().getTime().getTime()));
	psInsertClient.setDate(23, customerInfo.getREC_DATE()== null ? null : new java.sql.Date(customerInfo.getREC_DATE()
					.getTimeInMillis()));

	psInsertClient.execute();
	c.commit();
	bool = true;

	} catch (Exception e) {
		e.printStackTrace();
	}
	
}*/
	
	
	public void updateHumoCards(ListType_CardInfoHolder cardsListInfo, String clientId, String branch, Connection c, ListType_AccountInfoHolder accountsListInfo, String cardName) {

		try {
			psUpdateHumoCards.setString(1, cardsListInfo.value.getRow(0).getLogicalCard().getCARD() == null ? ""
					: cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
			psUpdateHumoCards.setDate(2, cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1() == null ? null
					: new java.sql.Date(cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTimeInMillis()));
			System.out.println("EXPIRY1: "
					+ new java.sql.Date(cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTimeInMillis()));
			psUpdateHumoCards.setBigDecimal(3, accountsListInfo.value.getRow(0).getBase_info().getACCOUNT_NO() == null ? null
					: accountsListInfo.value.getRow(0).getBase_info().getACCOUNT_NO());
			psUpdateHumoCards.setString(4, clientId);
			psUpdateHumoCards.setString(5, branch);
			psUpdateHumoCards.setString(6, cardName);
			psUpdateHumoCards.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	
/*	public  void insertNewClientAgreement(OperationConnectionInfo connectionInfo,
			RowType_AgreementHolder agreementInfo,Connection c) {
	
		try {
			
			psNewAgremment.setString(1, agreementInfo.value.getAGRE_NOM().toString()== null ? "" : agreementInfo.value.getAGRE_NOM().toString());
			psNewAgremment.setString(2, agreementInfo.value.getCLIENT()== null ? "" : agreementInfo.value.getCLIENT());
			psNewAgremment.setString(3, connectionInfo.getGROUPC()== null ? "" : connectionInfo.getGROUPC());
			psNewAgremment.setString(4, agreementInfo.value.getBINCOD()== null ? "" : agreementInfo.value.getBINCOD());
			psNewAgremment.setString(5, agreementInfo.value.getBANK_CODE()== null ? "" : agreementInfo.value.getBANK_CODE());
			psNewAgremment.setString(6, agreementInfo.value.getBRANCH()== null ? "" : agreementInfo.value.getBRANCH());
			psNewAgremment.setString(7, connectionInfo.getBANK_C()== null ? "" : connectionInfo.getBANK_C());
			psNewAgremment.setString(8, agreementInfo.value.getPRODUCT()== null ? "" : agreementInfo.value.getPRODUCT());
			psNewAgremment.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();			
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} 
	}*/

	
	public static RowType_CardInfo_LogicalCard getRowType_CardInfo_LogicalCard(Customer customer, Long HUMO_RANGEID) {
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCOND_SET("001");
		logicalCard.setRISK_LEVEL("A");
		logicalCard.setBASE_SUPP("1");
		logicalCard.setBRANCH("001");
		logicalCard.setCARD_TYPE("01");
		if (customer.getP_first_name() == null) {
			logicalCard.setF_NAMES("");
		} else if (customer.getP_first_name().length() > 34) {
			String first_name = toTranslit(customer.getP_first_name());
			first_name = first_name.substring(0, 34);
			System.out.println("first_name2:" + first_name);
			logicalCard.setF_NAMES(first_name);
		} else {
			logicalCard.setF_NAMES(toTranslit(customer.getP_first_name()));
		}
		logicalCard.setSURNAME(customer.getP_family() == null ? ""
				: toTranslit(customer.getP_family()));
		logicalCard.setMIDLE_NAME(customer.getP_patronymic() == null ? ""
				: toTranslit(customer.getP_patronymic()));
		logicalCard.setRANGE_ID(BigDecimal.valueOf(2));
		return logicalCard;
	}
	
	
	public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCard(Customer customer, String cardFirstName) {

		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		// physicalCard.setCARD_NAME(customer.getP_family() + " "
		// + customer.getP_first_name());

		if (cardFirstName != null) {

			cardFirstName = toTranslit(cardFirstName);
			cardFirstName.replace("XXX", "");
			cardFirstName = cardFirstName.replace("KHKHKH", "");

			cardFirstName = cardFirstName;

			cardFirstName = cardFirstName.trim();
			if (cardFirstName.length() > 24) {

				cardFirstName = cardFirstName.substring(0, 24);

			}
		}

		System.out.println("********CARD_NAME: " + cardFirstName);
		physicalCard.setCARD_NAME(cardFirstName);

		System.out.println("customer.getP_family():" + customer.getP_family());
		System.out.println("customer.getP_first_name():"
				+ customer.getP_first_name());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

		return physicalCard;

	}
	
	public static String toTranslit(String text) {
	      StringBuilder sb = new StringBuilder(text.length());
	      for (int i = 0; i<text.length(); i++) {
	          String l = text.substring(i, i+1);
	          if (letters.containsKey(l)) {
	              sb.append(letters.get(l));
	          }
	          else {
	              sb.append(l);
	          }
	      }
	      return sb.toString();
	  }
	
	public static Agreement getCardAgreement(String branch, BigDecimal agre_nom, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy) {
		Agreement rs = null;
		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(settings.get(branch).getBank_c());
			connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

			KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
			mainAgreementInfo.setAGRE_NOM(agre_nom);

			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();

			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();

			globus.issuing_v_01_02_xsd.OperationResponseInfo resp = issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
		} catch (Exception e) {
			ISLogger.getLogger().error("GET CARD AGREEMENT: "
					+ CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		return rs;
	}
	
	public static String get_report_file(int deal_id, int action_id, String alias) {
		String res = new String();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.report res from BF_TIETO_TR_ACTION_REPORT t "
					+ " where t.deal_id = ? and t.action_id = ?");
			ps.setInt(1, deal_id);
			ps.setInt(2, action_id);
			rs = ps.executeQuery();
			rs.next();
			res = rs.getString("res");

		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}

		return res;
	}
	
	public static HashMap<String, String> get_client_ti_acc(String client, String product)
	{
		HashMap<String, String> res = new HashMap<String, String>();
		
//		Connection c = null;
		
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
	
	public static List<Bf_globuz_trans> getBf_globuz_trans(String account, String branch, String alias) {
		List<Bf_globuz_trans> list = new ArrayList<Bf_globuz_trans>();
		Bf_globuz_trans bf_globuz_trans;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select g.branch, g.acc_co, s.trdate, g.summa/100 summa, s.state "
					+ "from bf_globuz_trans s, general g "
					+ "where s.genid = g.id "
					+ "and g.acc_co = ? "
					+ "and s.branch = ? ");
			ps.setString(1, account);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				bf_globuz_trans = new Bf_globuz_trans();
				bf_globuz_trans.setBRANCH(rs.getString("branch"));
				bf_globuz_trans.setAccount(rs.getString("acc_co"));
				bf_globuz_trans.setTRDATE(new java.sql.Date(rs.getDate("trdate").getTime()));
				bf_globuz_trans.setSUMMA(rs.getBigDecimal("summa"));
				bf_globuz_trans.setSTATE(rs.getInt("state"));
				list.add(bf_globuz_trans);
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<String> getUploadedExcelFull(String branch, String alias) {
		List<String> list = new ArrayList<String>();
		list.add(" ");
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select distinct e.file_name filename from humo_accrual_employee e where e.branch = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				String filename = "";
				filename = rs.getString("filename");
				list.add(filename);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<String> getUploadedExcelNewOnly(String branch, String alias) {
		List<String> list = new ArrayList<String>();
		list.add(" ");
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select distinct e.file_name filename from humo_accrual_employee e where e.branch = ? and not exists (select distinct hac.file_name\n" +
                    "  from humo_accrual_employee hac\n" +
                    " where hac.state = '6'\n" +
                    "   and not exists (select ee.file_name\n" +
                    "          from humo_accrual_employee ee\n" +
                    "         where ee.state in ('0', '3', '5')\n" +
                    "           and ee.file_name = hac.file_name) and e.file_name = hac.file_name)");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			while (rs.next()) {
				String filename = "";

				filename = rs.getString("filename");
				list.add(filename);

				System.out.println("list: " + list.get(i));

				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getPayments4GlobUz(String branch, String acc_co, String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select g.id data,  g.purpose || ' [ Summa ' || g.summa/100 || ' ' ||s.kod_b || ' ]' label from (select * from GENERAL union all select * from GENERAL_ARH) g, S_VAL s " +
						"where g.branch = '" + branch + "'" +
						"and g.acc_co = '" + acc_co + "'" +
						"and s.kod = g.currency " +
						"and s.kod_b = 'UZS' " +
						"and g.state = 3" +
						"and g.parent_group_id not in ('95', '198')"+
						"and not exists (select p.genid from bf_globuz_trans p " +
										" where g.branch = p.branch" +
										" and g.id = p.genid)  " +
							"order by g.d_date ";
		
		list = Utils.getRefData(sql, alias);
		return list;
	}
	
	public static List<RefData> getTstopCauses(String alias)
	{
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select t.couse data, t.name label from BF_GLOBUZ_STOP_CAUSES t where t.couse in ('1','2','4','5') order by 1";
		list = Utils.getRefData(sql, alias);
		return list;
	}
	
	public static HashMap<String, String> getHTstopCauses(String alias) {
		HashMap<String, String> _tstopCauses = new HashMap<String, String>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			s = c.createStatement();
			String sql = "select t.couse data, t.name label from BF_GLOBUZ_STOP_CAUSES t";
			rs = s.executeQuery(sql);
			while (rs.next())
				_tstopCauses.put(rs.getString("data"), rs.getString("label"));
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(s);
			ConnectionPool.close(c);
		}
		return _tstopCauses;
	}
	
	public static String getStateDesc(int State, String alias) {
		String StateDesc = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.data from BF_GLOBUZ_TRANS_STATES t where t.value = ?");
			ps.setInt(1, State);
			rs = ps.executeQuery();

			while (rs.next()) {
				StateDesc = rs.getString("data");
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return StateDesc;
	}
	
	private static int getCntInMainTable(String branch, String customer_id, String acc, Connection c) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			ps = c.prepareStatement("select count(*) from HUMO_ACCRUAL_ACC where customer_id = ? and account1 = ? and branch = ?");
			ps.setString(1, customer_id);
			ps.setString(2, acc);
			ps.setString(3, branch);
			rs = ps.executeQuery();
			if(rs.next()) cnt = rs.getInt(1);
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return cnt;
	}
	
    private static void insMainTable(String branch, String customer_id, String acc, String purpose,
            String alias, Connection c) throws Exception {
        PreparedStatement ps = null;
        try {
            int cnt = getCntInMainTable(branch, customer_id, acc, c);
            if (cnt == 0) {
                ps = c.prepareStatement("insert into HUMO_ACCRUAL_ACC (BRANCH, CUSTOMER_ID, CUSTOMER_NAME, ACCOUNT1, PURPOSE, TYPE_DOC) values (?,?,?,?,?,?)");
                ps.setString(1, branch);
                ps.setString(2, customer_id);
                ps.setString(3, Utils.getValueFromSql(
                        "select name from client c where c.id_client = '" + customer_id
                                + "' and branch = '" + branch + "'", Utils.getValueFromSql(
                                "select user_name from ss_dblink_branch where branch = '" + branch
                                        + "'", alias)));
                ps.setString(4, acc);
                ps.setString(5, purpose);
                ps.setString(6, "06");
                ps.execute();
            } else {
                ps = c.prepareStatement("update HUMO_ACCRUAL_ACC set PURPOSE = ? where CUSTOMER_ID = ? and BRANCH = ? and ACCOUNT1 = ?");
                ps.setString(1, purpose);
                ps.setString(2, customer_id);
                ps.setString(3, branch);
                ps.setString(4, acc);
                ps.execute();
            }
        } finally {
            Utils.close(ps);
        }
    }
	
	public static boolean checkGeneralPay(String branch){
		boolean res = true;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) count from humo_accrual_employee e where e.branch = ? and e.state in ('0', '1', '2', '3')");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
				System.out.println("count: "+count);
				ISLogger.getLogger().error("count: "+count);
				if(count != 0){
					System.out.println("count is not 0");
					/*res.setCode(0);
					res.setName("В базе есть непроведённые пополнения.");*/
					res = false;
					return res;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
			return res;
	}
	
	public static boolean checkAccount(String account, Connection c){
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = true;
		int count = 0;
		try {
			ps = c.prepareStatement("select count(*) count from card where SUBSTR(def_atm_account, -20) = '"+account+"'");
			rs = ps.executeQuery();
			while(rs.next()){
				count = Integer.parseInt(rs.getString("count"));
			}
			if(count == 0){
				res = true;
			}
			else{
				res = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
		}
		return res;
	}

	public static Res saveExcel(PacketPayment packetPayment, Label label, InputStream in, String alias, String un, String pwd){
        Connection c = null;
        Row currentRow = null;
        Res res = new Res();
        PreparedStatement ps = null;
        label.setValue(" ");
        //java.sql.Date date = getDayForExcel(un, pwd, alias);
        java.sql.Date date = getDayByBranch(alias);
        try {
            //c = ConnectionPool.getConnection(un, pwd, alias);
          Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"), ConnectionPool.getValue("HUMO_CON_LOGIN"), ConnectionPool.getValue("HUMO_CON_PAS"));
            c.setAutoCommit(false);
            //c = ConnectionPool.getConnection();
            XSSFWorkbook book = new XSSFWorkbook(in);
            Sheet datatypeSheet = book.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int index = 0;
            insMainTable(packetPayment.getBranch(), packetPayment.getCodeOrganization(),
                    packetPayment.getAccount(), packetPayment.getPaymentPurpose(), alias, c);
            while (iterator.hasNext()) {
                currentRow = iterator.next();
                ++index;
                label.setValue(String.valueOf(index));
                Cell accCell = currentRow.getCell(0);
                Cell nameCell = currentRow.getCell(1);
                Cell salaryCell = currentRow.getCell(2);
                
                //22618 СЧЁТ
                String acc = getCellValue(accCell);
                if (acc == null || acc.equals("") || !acc.startsWith("22618")
                        || !(checkAccount(acc, c))) {
                    c.rollback();
                    String msg = "Неверный формат счёта";
                    res.setName(msg);
                    res.setCode(-1);
                    throw new Exception(msg);
                }
                
                //ФИО КЛИЕНТА
                String name = getCellValue(nameCell);
                if (name == null || name.equals("") || name.length() > 180) {
                    c.rollback();
                    String msg = "Неверный формат имени";
                    res.setName(msg);
                    res.setCode(-1);
                    throw new Exception(msg);
                }
                
                //СУММА
                String salary = getCellValue(salaryCell);
                Double salaryBD = TclientService.parseSalary(salary);
                if(salaryBD == null) {
                    String msg = "Неверный формат суммы";
                    res.setCode(-1);
                    res.setName(msg);
                    throw new Exception(msg);
                }
                    ps = c.prepareStatement("insert into HUMO_ACCRUAL_EMPLOYEE (ID, BRANCH, CUSTOMER_ID, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ACCOUNT, SUMMA, DATE_OPER, STATE, FILE_NAME, general_pay) values (SQ_HUMO_ACCRUAL_EMPLOYEE.nextval,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, packetPayment.getBranch());
                    ps.setString(2, packetPayment.getCodeOrganization());
                    ps.setString(3, acc.substring(9, 17));
                    ps.setString(4, name);
                    ps.setString(5, acc.substring(0, 20));
                    ps.setDouble(6, (Math.round(salaryBD * 100)));
                    ps.setDate(7, date);
                    ps.setInt(8, 0);
                    ps.setString(9, packetPayment.getName());
                    ps.setString(10, packetPayment.getGeneralPay().toString());
                    ps.execute(); 
            }
            boolean result = createPacketPayment(packetPayment, c);
            if(!result) {
                Utils.rollback(c);
                res.setCode(-1);
                res.setName("Не удалось загрузить ведомость.");
                return res;
            }
            c.commit();
        } catch (Exception e) {
            Utils.rollback(c);
            e.printStackTrace();
            res.setCode(-1);
            if (CheckNull.getPstr(e).startsWith(
                            "java.sql.SQLException: ORA-00001")) {
                res.setName("Счёт клиента в таблице не должен повторяться! Таблица не загружена.");
            } else {
                res.setName(CheckNull.getPstr(e));
            }
            label.setValue("0");
            ISLogger.getLogger().error("SAVE EXCEL ERROR: "+CheckNull.getPstr(e));
        } finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
	}
	
  	private static String getCellValue(Cell currentCell){
	    return currentCell.getCellType()==0 ?
	        (currentCell.getNumericCellValue()+"") :
	          currentCell.getStringCellValue();
  	}
  	
  	public static Res fillCardsInBranch(PacketPayment packetPayment, String alias, String un, String pwd){
  		Connection c = null;
  		CallableStatement cs = null;
  		CallableStatement csParam = null;
  		Res res = new Res();
  		String alias1 = "";
  		try {
  		    //c = ConnectionPool.getConnection();
            c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"),
                    ConnectionPool.getValue("HUMO_CON_LOGIN"),
                    ConnectionPool.getValue("HUMO_CON_PAS"));
            alias1 = Utils.getValueFromSql(
                    "select user_name from ss_dblink_branch where branch = '"
                            + packetPayment.getBranch() + "'", alias);
            ISLogger.getLogger().error(
                    "branch: " + packetPayment.getBranch() + " alias: " + alias + " alias1: "
                            + alias1 + " username: " + un);
            Statement st = c.createStatement();
            st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias1);
            c.setAutoCommit(false);
            // c = ConnectionPool.getConnection();
            if (getCntFill(c) <= 0) {
                res.setCode(-1);
                res.setName("Нет карт для зачисления");
                return res;
            }
			Utils.infoInit(c);
			
	         csParam = c.prepareCall("{ call Param.SetParam(?,?) }");
	            setParam("BRANCH", packetPayment.getBranch(), csParam);
	            setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
	            setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
	            setParam("STATE", "19", csParam);
	            cs = c.prepareCall("{ call proc_humo_accrual.PayForDoc() }");
	            cs.execute();
	            c.commit();
/*			if(packetPayment.getState() == 0) {
			csParam = c.prepareCall("{ call Param.SetParam(?,?) }");
			setParam("BRANCH", packetPayment.getBranch(), csParam);
			setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
			setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
			setParam("STATE", "1", csParam);
			cs = c.prepareCall("{ call proc_humo_accrual.PayForDoc() }");
			cs.execute();
			
			setParam("BRANCH", packetPayment.getBranch(), csParam);
			setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
			setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
			setParam("STATE", "2", csParam);
			cs.execute();
			
			setParam("BRANCH", packetPayment.getBranch(), csParam);
			setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
			setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
			setParam("STATE", "3", csParam);
			cs.execute();
			c.commit();
			}else if(packetPayment.getState() == 1) {
		        csParam = c.prepareCall("{ call Param.SetParam(?,?) }");
	            setParam("BRANCH", packetPayment.getBranch(), csParam);
	            setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
	            setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
	            setParam("STATE", "2", csParam);
			    cs = c.prepareCall("{ call proc_humo_accrual.PayForDoc() }");
	            cs.execute();
	            setParam("BRANCH", packetPayment.getBranch(), csParam);
	            setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
	            setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
	            setParam("STATE", "3", csParam);
	            cs = c.prepareCall("{ call proc_humo_accrual.PayForDoc() }");
	            cs.execute();
	            c.commit();
			}else if(packetPayment.getState() == 2) {
		        csParam = c.prepareCall("{ call Param.SetParam(?,?) }");
	            setParam("BRANCH", packetPayment.getBranch(), csParam);
	            setParam("CLIENT_ID", packetPayment.getCodeOrganization(), csParam);
	            setParam("GENERAL_PAY", packetPayment.getGeneralPay().toString(), csParam);
	            setParam("STATE", "3", csParam);
	            cs = c.prepareCall("{ call proc_humo_accrual.PayForDoc() }");
	            cs.execute();
	            c.commit();
			}*/
			res.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(-1);
			res.setName("branch: "+packetPayment.getBranch()+"\nalias: "+alias+"\nalias1: "+alias1+"\nusername: "+un+"\nError: "+CheckNull.getPstr(e));
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		return res;
  	}
  	
  	private static int getCntFill(Connection c) throws Exception{
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		int cnt = 0;
  		try {
  			ps = c.prepareStatement("select count(*) from HUMO_ACCRUAL_EMPLOYEE where state = 0");
  			rs = ps.executeQuery();
  			if(rs.next())  cnt = rs.getInt(1);
  		} finally {
  			Utils.close(rs);
  			Utils.close(ps);
  		}
  		return cnt;
  	}
  	
  	public static boolean checkHumoAccrualEmployee(String branch, String customerId) throws Exception{
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		boolean res = true;
  		try {
  			c = ConnectionPool.getConnection();
  			ps = c.prepareStatement("select count(*) from HUMO_ACCRUAL_EMPLOYEE where state = 0 and branch = ? and customer_id = ?");
  			ps.setString(1, branch);
  			ps.setString(2, customerId);
  			rs = ps.executeQuery();
  			if(rs.next())  res = rs.getInt(1) > 0 ? false : true;
  		} finally {
  			Utils.close(rs);
  			Utils.close(ps);
  			ConnectionPool.close(c);
  		}
  		return res;
  	}
  	/*public static List<AccrualEmployee> getUploadedData(final String branch, final String alias) {
        return (List<AccrualEmployee>)RefDataService.getRefData("select * from humo_accrual_employee e where e.branch = "+branch+" order by e.date_oper desc", alias);
    }*/
  	public static List<AccrualEmployee> getUploadedFull(String alias, String branch){
  		List<AccrualEmployee> list = new ArrayList<AccrualEmployee>();
  		HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
  		stateMap.put(0, "Доступно к зачислению на счёт");
  		stateMap.put(1, "Доступно к зачислению на счёт");
  		stateMap.put(2, "Доступно к зачислению на счёт");
  		stateMap.put(3, "Зачислено на счёт. Доступно к зачислению на карту");
        stateMap.put(19, "Зачислено на счёт. Доступно к зачислению на карту");
  		stateMap.put(5, "Ошибка пополнения");
  		stateMap.put(6, "Успешно зачислено на карту");
  		stateMap.put(7, "Успешно списано");
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from humo_accrual_employee e where e.branch = ?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			while(rs.next()){
                AccrualEmployee payment = new AccrualEmployee();
                payment.setBranch(rs.getString("BRANCH"));
                payment.setCustomerId(rs.getString("CUSTOMER_ID"));
                payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
                payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
                payment.setAmount(rs.getString("SUMMA"));
                payment.setOperDate(df3.format(rs.getDate("DATE_OPER")));
                payment.setState(rs.getInt("STATE"));
                payment.setGeneralId(rs.getLong("GENERAL_ID"));
                payment.setGeneralPay(rs.getLong("GENERAL_PAY"));
                payment.setFileName(rs.getString("FILE_NAME"));
                payment.setReport(rs.getString("REPORT"));
                payment.setAccountNo(rs.getString("ACCOUNT_NO"));
                payment.setRealCard(rs.getString("REAL_CARD"));
                payment.setClient(rs.getString("CLIENT"));
                payment.setStateText(stateMap.get(payment.getState()));
                list.add(payment);      				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		System.out.println("list size: "+list.size());
  		return list;
  	}
  	
  	public static List<AccrualEmployee> getUploadedNewOnly(String alias, String branch){
  		List<AccrualEmployee> list = new ArrayList<AccrualEmployee>();
  		HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
  		stateMap.put(0, "Доступно к зачислению на счёт");
  		stateMap.put(1, "Доступно к зачислению на счёт");
  		stateMap.put(2, "Доступно к зачислению на счёт");
  		stateMap.put(3, "Зачислено на счёт. Доступно к зачислению на карту");
        stateMap.put(19, "Зачислено на счёт. Доступно к зачислению на карту");
  		stateMap.put(5, "Ошибка пополнения");
  		stateMap.put(6, "Успешно зачислено на карту");
  		stateMap.put(7, "Успешно списано");
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String dateFormatted = df.format(date);
  		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from humo_accrual_employee where branch = ? and date_oper = to_date(? , 'dd.mm.yyyy')");
/*			ps = c.prepareStatement("select * from humo_accrual_employee e where e.branch = ? and not exists (select distinct hac.file_name\n" +
                                "  from humo_accrual_employee hac\n" +
                                " where date_oper = to_date(? , 'dd.mm.yyyy')\n" +
                                " and hac.state = '6'\n" +
                                "   and not exists (select ee.file_name\n" +
                                "          from humo_accrual_employee ee\n" +
                                "         where ee.state in ('0', '3', '5')\n" +
                                "           and ee.file_name = hac.file_name) and e.file_name = hac.file_name)");*/
			ps.setString(1, branch);
			ps.setString(2, dateFormatted);
			rs = ps.executeQuery();
			while(rs.next()){
				AccrualEmployee payment = new AccrualEmployee();
				payment.setBranch(rs.getString("BRANCH"));
				payment.setCustomerId(rs.getString("CUSTOMER_ID"));
				payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
				payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
				payment.setAmount(rs.getString("SUMMA"));
				payment.setOperDate(df3.format(rs.getDate("DATE_OPER")));
				payment.setState(rs.getInt("STATE"));
				payment.setGeneralId(rs.getLong("GENERAL_ID"));
				payment.setGeneralPay(rs.getLong("GENERAL_PAY"));
				payment.setFileName(rs.getString("FILE_NAME"));
				payment.setReport(rs.getString("REPORT"));
				payment.setAccountNo(rs.getString("ACCOUNT_NO"));
				payment.setRealCard(rs.getString("REAL_CARD"));
				payment.setClient(rs.getString("CLIENT"));
				payment.setStateText(stateMap.get(payment.getState()));
				list.add(payment);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		    Utils.close(rs);
		    Utils.close(ps);
			ConnectionPool.close(c);
		}
		System.out.println("list size: "+list.size());
  		return list;
  	}
  	
  	
  	
  	public static List<AccrualEmployee> getUploaded(String alias, String branch, String filename){  		
  		List<AccrualEmployee> list = new ArrayList<AccrualEmployee>();  		
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
  		stateMap.put(0, "Доступно к зачислению на счёт");
  		stateMap.put(1, "Доступно к зачислению на счёт");
  		stateMap.put(2, "Доступно к зачислению на счёт");
  		stateMap.put(3, "Зачислено на счёт. Доступно к зачислению на карту");
        stateMap.put(19, "Зачислено на счёт. Доступно к зачислению на карту");
  		stateMap.put(5, "Ошибка пополнения");
  		stateMap.put(6, "Успешно зачислено на карту");
  		stateMap.put(7, "Успешно списано");
  		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from humo_accrual_employee e where e.branch = ? and file_name = ?");
			ps.setString(1, branch);
			ps.setString(2, filename);
			rs = ps.executeQuery();
			while(rs.next()){
                AccrualEmployee payment = new AccrualEmployee();
                payment.setBranch(rs.getString("BRANCH"));
                payment.setCustomerId(rs.getString("CUSTOMER_ID"));
                payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
                payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
                payment.setAmount(rs.getString("SUMMA"));
                payment.setOperDate(df3.format(rs.getDate("DATE_OPER")));
                payment.setState(rs.getInt("STATE"));
                payment.setGeneralId(rs.getLong("GENERAL_ID"));
                payment.setGeneralPay(rs.getLong("GENERAL_PAY"));
                payment.setFileName(rs.getString("FILE_NAME"));
                payment.setReport(rs.getString("REPORT"));
                payment.setAccountNo(rs.getString("ACCOUNT_NO"));
                payment.setRealCard(rs.getString("REAL_CARD"));
                payment.setClient(rs.getString("CLIENT"));
                list.add(payment);      		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		System.out.println("list size: "+list.size());
  		return list;
  	}
  	
  	
  	public static List<AccrualEmployee> getUploadedFilter(String sql, String alias, String branch){
  		List<AccrualEmployee> list = new ArrayList<AccrualEmployee>();  		
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
  		stateMap.put(0, "Доступно к зачислению на счёт");
  		stateMap.put(1, "Доступно к зачислению на счёт");
  		stateMap.put(2, "Доступно к зачислению на счёт");
  		stateMap.put(3, "Зачислено на счёт. Доступно к зачислению на карту");
        stateMap.put(19, "Зачислено на счёт. Доступно к зачислению на карту");
  		stateMap.put(5, "Ошибка пополнения");
  		stateMap.put(6, "Успешно зачислено на карту");
  		stateMap.put(7, "Успешно списано");
  		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
                AccrualEmployee payment = new AccrualEmployee();
                payment.setBranch(rs.getString("BRANCH"));
                payment.setCustomerId(rs.getString("CUSTOMER_ID"));
                payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
                payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
                payment.setAmount(rs.getString("SUMMA"));
                payment.setOperDate(df3.format(rs.getDate("DATE_OPER")));
                payment.setState(rs.getInt("STATE"));
                payment.setGeneralId(rs.getLong("GENERAL_ID"));
                payment.setGeneralPay(rs.getLong("GENERAL_PAY"));
                payment.setFileName(rs.getString("FILE_NAME"));
                payment.setReport(rs.getString("REPORT"));
                payment.setAccountNo(rs.getString("ACCOUNT_NO"));
                payment.setRealCard(rs.getString("REAL_CARD"));
                payment.setClient(rs.getString("CLIENT"));
                list.add(payment);      
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		System.out.println("list size: "+list.size());
  		return list;
  	}
  	
  	private static void setParam(String paramName, String paramValue, CallableStatement cs) throws Exception{
		ISLogger.getLogger().error("PARAM = "+paramName+"\n"+"Value = "+paramValue);
		cs.setString(1, paramName);
		cs.setString(2, paramValue);
		cs.execute();
	}
  	
  	//ПАКЕТНОЕ ПОПОЛНЕНИЕ
    public static int sendFillToHumo(PacketPayment currentPacketPayment, List<AccrualEmployee> paymentList, int uid,
            String alias, String un, String pwd) {
        int successfulPaymentsCounter = 0;
        for (int i = 0; i < paymentList.size(); i++) {
            try {
                Res res = new Res();
                if (paymentList.get(i).getAmount().equals("0")) {
                    throw new Exception(
                            "Сумма должна быть больше 0, выберите другой документ для пополнения");
                }
                if (isTransactionExecuted(paymentList.get(i), alias)) {
                    res.setName("Успешно!");
                    res.setCode(0);
                    updateTransaction(paymentList.get(i).getGeneralId(), alias, res);
                    ISLogger.getLogger().error(
                            "payment below is already executed. --> PAYMENT:\n"
                                    + objectMapper.writeValueAsString(paymentList.get(i)));
                } else {
                    // res = TclientService.checkBfGlobuzTrans(c2);
                    /*
                     * label.setVisible(true); label.setValue("Count payments: "
                     * + String.valueOf(i));
                     */
                    // ///////////////////ПОПОЛНЕНИЕ/////////////PAY//////////////////////////
                    res = TclientService.pay(paymentList.get(i), res, un, pwd, alias).getRs();
                    ISLogger.getLogger().error("res.getCode(): " + res.getCode());
                    if (res.getCode() == 0) {
                        successfulPaymentsCounter += 1;
                        ISLogger.getLogger().error("Successfull payment!");
                        updateTransaction(paymentList.get(i).getGeneralId(), alias, res);
                        Bf_globuz_trans bfGlobuzTrans = new Bf_globuz_trans();
                        bfGlobuzTrans.setEMPID(String.valueOf(uid));
                        bfGlobuzTrans.setGENID(paymentList.get(i).getGeneralId());
                        bfGlobuzTrans.setGLOBID(Long.parseLong(res.getName()));
                        bfGlobuzTrans.setBRANCH(paymentList.get(i).getBranch());
                        bfGlobuzTrans.setSTATE(2);
                        TclientService.insertIntoBfGlobuzTrans(bfGlobuzTrans);
                    } else {
                        ISLogger.getLogger().error("Error payment!");
                        // GLOBID = Long.parseLong(res.getName());
                        updateTransaction(paymentList.get(i).getGeneralId(), alias, res);
                    }
                }

            } catch (Exception e) {
                ISLogger.getLogger().error("sendFillToHumo error: ", e);
            }
        }
        ISLogger.getLogger().error("Successful payments count: " + String.valueOf(successfulPaymentsCounter));
        return successfulPaymentsCounter;
    }
    
    // //////////////////////CURRENT PAYMENT/////////////////////ОДИН В ПАКЕТНОМ//////////////////////////////
    public static Res sendFillToHumo(PacketPayment currentPacketPayment, Label label, int uid,
            String alias, AccrualEmployee currentPayment,
            String un, String pwd) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Res res = new Res();
        Long GLOBID = 0l;
        int STATE = 2;
        AccrualEmployee payment = new AccrualEmployee();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from HUMO_ACCRUAL_EMPLOYEE where state = ? and customer_id = ? and general_pay = ? and branch = ? and employee_id = ? and employee_account = ?");
            ps.setInt(1, 3);
            ps.setString(2, currentPacketPayment.getCodeOrganization());
            ps.setString(3, currentPacketPayment.getGeneralPay().toString());
            ps.setString(4, currentPacketPayment.getBranch());
            ps.setString(5, currentPayment.getEmployeeId());
            ps.setString(6, currentPayment.getEmployeeAccount());
            rs = ps.executeQuery();
            int index = 0;
            if (rs.next()) {
                ++index;
                String[] splitter;
                splitter = String.valueOf(rs.getString("summa")).split("\\.");
                payment.setAmount(splitter[0]);
                payment.setGeneralId(rs.getLong("general_id"));
                payment.setBranch(currentPacketPayment.getBranch());
                payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
                payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
                payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                payment.setClient(rs.getString("CLIENT"));
                CardInfo cardInfo = getCardInfoByTranzAcct(c, currentPacketPayment.getBranch(),
                        payment.getEmployeeAccount());
                if (cardInfo.getSTATUS().equals("2")) {
                    String errorString = "Карта " + cardInfo.getCARD()
                            + " на данном счёте заблокирована.";
                    Res result = new Res();
                    result.setCode(1);
                    result.setName(errorString);
                    updateTransaction(c, payment.getGeneralId(), alias, result);
                } else {
                    payment.setRealCard(cardInfo.getCARD_ACCT());
                    payment.setCard(cardInfo.getCARD());
                    payment.setAccountNo(cardInfo.getACCOUNT_NO());
                    payment.setClient(cardInfo.getCLIENT_ID());
                    payment.setFileName(rs.getString("FILE_NAME"));
                    insertAdditionalData(payment, alias, c);
                    ObjectMapper objectMapper = new ObjectMapper();
                    ISLogger.getLogger().error(
                            "PACKET PAYMENT: " + objectMapper.writeValueAsString(payment));
                    if (payment.getAmount() == null || payment.getGeneralId() == null
                            || payment.getBranch() == null || payment.getEmployeeAccount() == null
                            || payment.getEmployeeName() == null || payment.getEmployeeId() == null
                            || payment.getAccountNo() == null || payment.getCard() == null
                            || payment.getRealCard() == null || payment.getClient() == null
                            || payment.getFileName() == null) {
                        String errorString = "Ошибка! Нет данных по";
                        if (payment.getAmount() == null) {
                            errorString.concat(" сумме,");
                        }
                        if (payment.getGeneralId() == null) {
                            errorString.concat(" проводке,");
                        }
                        if (payment.getBranch() == null) {
                            errorString.concat(" бранчу,");
                        }
                        if (payment.getEmployeeAccount() == null) {
                            errorString.concat(" счёту,");
                        }
                        if (payment.getEmployeeName() == null) {
                            errorString.concat(" имени клиента,");
                        }
                        if (payment.getEmployeeId() == null) {
                            errorString.concat(" коду клиента,");
                        }
                        if (payment.getAccountNo() == null) {
                            errorString.concat(" account no,");
                        }
                        if (payment.getCard() == null) {
                            errorString.concat(" зашифрованному номеру карты,");
                        }
                        if (payment.getRealCard() == null) {
                            errorString.concat(" номеру карты,");
                        }
                        if (payment.getClient() == null) {
                            errorString.concat(" id клиента,");
                        }
                        errorString.subSequence(0, errorString.length() - 1);
                        Res result = new Res();
                        result.setCode(1);
                        result.setName(errorString);
                        updateTransaction(c, payment.getGeneralId(), alias, result);
                        c.commit();
                        return result;
                    }
                }
                Utils.close(rs);
                Utils.close(ps);

                if (payment.getAmount().equals("0")) {
                    throw new Exception(
                            "Сумма должна быть больше 0, выберите другой документ для пополнения");
                }
                // GeneralInfo generalInfo =
                // TclientService.getSummOfPayment(alias,
                // Long.parseLong(payment.getGeneral_id()), c);
                res = TclientService.checkBfGlobuzTrans(c);
                if (res.getCode() == 1) {
                    res = TclientService.pay(payment, res, un, pwd, alias).getRs();
                    updateTransaction(c, payment.getGeneralId(), alias, res);
                    if (res.getCode() == 0) {
                        GLOBID = Long.parseLong(res.getName());
                        updateTransaction(c, payment.getGeneralId(), alias, res);
                        STATE = 1;
                        Bf_globuz_trans bf_globuz_trans = new Bf_globuz_trans();
                        bf_globuz_trans.setEMPID(String.valueOf(uid));
                        bf_globuz_trans.setGENID(payment.getGeneralId());
                        bf_globuz_trans.setGLOBID(GLOBID);
                        bf_globuz_trans.setBRANCH(payment.getBranch());
                        bf_globuz_trans.setSTATE(STATE);
                        TclientService.insertIntoBfGlobuzTrans(bf_globuz_trans, c);
                    }
                } else {
                    throw new Exception("Нету таблицы bf_globuz_trans");
                }
                if (index == 0)
                    res.setCode(-4);
                c.commit();
            }
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
            res.setCode(-1);
            res.setName(CheckNull.getPstr(e));
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    }
  	
  	private static void insertAdditionalData(AccrualEmployee payment, String alias, Connection c) throws SQLException{
			PreparedStatement ps = null;
  		try {
			ps = c.prepareStatement("update humo_accrual_employee e set e.account_no = ?, e.real_card = ?, e.client = ? where e.employee_account = ? and e.file_name = ?");
			ps.setString(1, payment.getAccountNo());
			ps.setString(2, payment.getRealCard());
			ps.setString(3, payment.getClient());
			ps.setString(4, payment.getEmployeeAccount());
			ps.setString(5, payment.getFileName());
			ps.execute();
			c.commit();
  		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
		}
  	}
  	
    public static PayResp pay(AccrualEmployee payment, Res res, String un, String pwd, String alias)
            throws SQLException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CallableStatement csParam = null;
        Connection cPayConnection = null;
        PayResp pay = new PayResp();
        String sessionIdFromTable = "";
        String sessionIdFromParam = "";
        CallableStatement ccs = null;
        try {
            cPayConnection = ConnectionPool.getConnection(alias);
            sessionIdFromTable = getSessionId(cPayConnection, payment);
            sessionIdFromTable = sessionIdFromTable == null ? "" : sessionIdFromTable;
            ccs = cPayConnection.prepareCall("{ call param.SetParam('P_SESS_ID', null)}");
            ccs.execute();
            if (!sessionIdFromTable.isEmpty()) {
                ISLogger.getLogger().error("sessionIdFromTable: " + sessionIdFromTable);
                csParam = cPayConnection.prepareCall("{ call param.SetParam('P_SESS_ID', ?)}");
                csParam.setString(1, sessionIdFromTable);
                csParam.execute();
            }
            CallableStatement cs = cPayConnection.prepareCall("{ ? = call HUMO_EMPS_DC.refill_card(?,?,?,?,?) }");
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            cs.setString(2, payment.getRealCard());
            cs.setString(3, payment.getAmount());
            cs.setString(4, "110");
            cs.setString(5, payment.getGeneralId().toString());
            cs.setString(6, payment.getBranch());
            cs.execute();
            if (sessionIdFromTable.isEmpty() || sessionIdFromTable.equals("")) {
                csParam = cPayConnection.prepareCall("{? = call Param.getparam(?) }");
                csParam.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
                csParam.setString(2, "P_SESS_ID");
                csParam.execute();
                sessionIdFromParam = csParam.getString(1);
                ISLogger.getLogger().error("sessionIdFromParam: " + sessionIdFromParam);
                insertSessionId(cPayConnection, payment, sessionIdFromParam);
            }
            // HUMO_TR_DOC.PAYDOC_TRANS(
            // HUMO_TR_DOC.CREATE_DOC_BANK(
            if (cs.getInt(1) > 0) {

                res.setCode(0);
                //res.setName("Успешно!");
                res.setName(String.valueOf(cs.getInt(1)));

            } else {
                res.setCode(-1);
                res.setName("Не удалось пополнить");
            }

        } catch (SQLException e) {
            ISLogger.getLogger().error(
                    "TClientService.pay SQL Exception: " + e.getLocalizedMessage());
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            res.setCode(-1);
            res.setName(e.getMessage());

        } finally {
            ccs.clearParameters();
            ccs.close();
            csParam.clearParameters();
            csParam.close();
            ConnectionPool.close(cPayConnection);
        }
        pay.setPaym(payment);
        pay.setRs(res);
        ConnectionPool.close(cPayConnection);
        ISLogger.getLogger().error(
                "pay: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pay));
        return pay;
    }
	
	
	public static PayResp payWriteOff(AccrualEmployee payment, Res res, String branch, int uid, String un) throws SQLException, JsonProcessingException {
		Connection c = null;
		ObjectMapper mapper = new ObjectMapper();
		CallableStatement csParam = null;
		PayResp pay = new PayResp();
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection();
			String sessionIdFromTable = getSessionId(c, payment);
			cs = c.prepareCall("{ ? = call HUMO_EMPS_DC.writeoff_card(?,?,?,?,?) }");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			cs.setString(2, payment.getRealCard());
			cs.setString(3, payment.getAmount());
			cs.setString(4, "124");
			cs.setString(5, payment.getGeneralId().toString());
			cs.setString(6, payment.getBranch());
			cs.execute();
			csParam = c.prepareCall("{? = call Param.getparam('P_SESS_ID') }");
			csParam.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			csParam.execute();
			String sessionIdFromParam = csParam.getString(1);
			ISLogger.getLogger().error("sessionIdFromParam: "
					+ sessionIdFromParam);
			if (cs.getInt(1) > 0) {
				res.setCode(0);
				res.setName(String.valueOf(cs.getInt(1)));
				updateCancelPaymentInfo(c, payment, sessionIdFromTable, sessionIdFromParam);
				UsrLog(new UserActionsLog(null, branch, uid, un, getIp(), new Date(), 6, 1, "Списание с карты "
						+ payment.getRealCard()
						+ " суммы "
						+ payment.getAmount() + ""));
			} else {
				res.setCode(-1);
				res.setName("Не удалось списать");
			}
		} catch (SQLException e) {
			csParam = c.prepareCall("{? = call Param.getparam('P_SESS_ID') }");
			csParam.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			csParam.execute();
			String sessionIdFromParam = csParam.getString(1);
			ISLogger.getLogger().error("sessionIdFromParam: "
					+ sessionIdFromParam);
			insertSessionId(c, payment, sessionIdFromParam);
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
			c.rollback();
		} finally {
			cs.clearParameters();
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		 

		pay.setPaym(payment);
		pay.setRs(res);

		try {
			ISLogger.getLogger().error("writeOff: "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pay));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionPool.close(c);
		return pay;
	}
	
	  public static String getSessionId(Connection c, AccrualEmployee payment){
	      String sessionId = "";
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      try {
	        ISLogger.getLogger().error("PAYMENT GETSESSION ID: "+objectMapper.writeValueAsString(payment));
	        ps = c.prepareStatement("select e.session_id session_id from humo_accrual_employee e where e.file_name = ? and e.employee_id = ? and e.branch = ? and e.employee_account = ? and e.account_no = ? and e.real_card = ? and general_id = ?");
	        ps.setString(1, payment.getFileName());
	        ps.setString(2, payment.getEmployeeId());
	        ps.setString(3, payment.getBranch());
	        ps.setString(4, payment.getEmployeeAccount());
	        ps.setString(5, payment.getAccountNo());
	        ps.setString(6, payment.getRealCard());
	        ps.setString(7, payment.getGeneralId().toString());
	        rs = ps.executeQuery();
	        if(rs.next()){
	        sessionId = rs.getString("session_id");
	        }
	      } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      } catch (JsonProcessingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }finally{
	        Utils.close(rs);
	        Utils.close(ps);
	      }
	      return sessionId;
	    }
	
	
	public static String getSessionId(Connection c, String branch, String employeeAccount, String accountNo, String generalId, String realCard) throws JsonProcessingException{
		String sessionId = "";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("select e.session_id session_id from humo_accrual_employee e where e.branch = ? and e.employee_account = ? and e.account_no = ? and e.real_card = ? and general_id = ?");
			ps.setString(1, branch);
			ps.setString(2, employeeAccount);
			ps.setString(3, accountNo);
			ps.setString(4, realCard);
			ps.setString(5, generalId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
			sessionId = rs.getString("session_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
		}
		return sessionId;
	}
	
	
	
	public static void insertSessionId(Connection c, AccrualEmployee payment, String sessionId){
		PreparedStatement ps = null;
		try {
			ps = null;
			ps = c.prepareStatement("update humo_accrual_employee e set e.session_id = ? where e.file_name = ? and e.employee_id = ? and e.branch = ?");
			ps.setString(1, sessionId);
			ps.setString(2, payment.getFileName());
			ps.setString(3, payment.getEmployeeId());
			ps.setString(4, payment.getBranch());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
		}
	}
	
	public static void insertSessionId(Connection c, String branch, String employeeAccount, String accountNo, String generalId, String realCard, String sessionId){
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update humo_accrual_employee e set e.session_id = ? where e.employee_account = ? and e.branch = ? and e.account_no = ? and e.real_card = ? and e.general_id = ?");
			ps.setString(1, sessionId);
			ps.setString(2, employeeAccount);
			ps.setString(3, branch);
			ps.setString(4, accountNo);
			ps.setString(5, realCard);
			ps.setString(5, generalId);
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
		}
	}
	
	/**
	 * @param c
	 * @param payment
	 * @param oldSessionId
	 * @param newSessionId
	 */
	public static void updateCancelPaymentInfo(Connection c, AccrualEmployee payment, String oldSessionId, String newSessionId){
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update humo_accrual_employee e set e.session_id = ?, e.report = ?, e.state = '7' where e.file_name = ? and e.employee_id = ? and e.branch = ?");
			ps.setString(1, newSessionId);
			ps.setString(2, "Списание эквивалентной суммы после пополнения по session_id: "+oldSessionId);
			ps.setString(3, payment.getFileName());
			ps.setString(4, payment.getEmployeeId());
			ps.setString(5, payment.getBranch());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(ps);
		}
	}
  	
  	public static String getTableState(String branch, String file_name){
  		Connection c = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		String state = "different";
  		List<String> stateList = new ArrayList<String>();
  		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select e.state state from humo_accrual_employee e where e.file_name = ? and e.branch = ?");
			ps.setString(1, file_name);
			ps.setString(2, branch);
		    rs = ps.executeQuery();
			while(rs.next()){
			state = rs.getString("state");
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
		for(int i = 0; i < stateList.size()-1; i++){
			if(!(stateList.get(i).equals(stateList.get(i+1)))){
				ISLogger.getLogger().error(stateList.get(i)+" "+stateList.get(i+1));
				state = "different";
				return state;
			}
			else{
				state = stateList.get(i).toString();
			}
		}
		return state;
  	}
  	
	public static boolean checkTableState(int state, String branch, String fileName) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) count from humo_accrual_employee where file_name = ? and state = ? and branch = ?");
			ps.setString(1, fileName);
			ps.setInt(2, state);
			ps.setString(3, branch);
			rs = ps.executeQuery();
			if (rs.next()) {
				boolean res = rs.getInt("count") > 0 ? true : false;
				return res;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return check;
	}
  	
  	
	public static void deleteTable(String branch, String filename) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete humo_accrual_employee e where e.file_name = ? and e.branch = ?");
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
	
	   public static void deletePacketPayment(String branch, String filename) {
	        Connection c = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement("delete humo_packet_payments e where e.name = ? and e.branch = ?");
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
/*  	public static void insertGenId(String branch, String alias, String gen_id) throws SQLException{
  		Connection c = null;
  		PreparedStatement ps = null;
  		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("insert into humo_general_id (general_id, branch) values (?, ?)");
			ps.setString(1, gen_id);
			ps.setString(2, branch);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			ps.close();
			c.close();
		}
  	}*/
  	


  	
  	
  	
  	private static void updateTransaction(Connection c, Long genId, String alias, Res res) throws Exception{
  		ISLogger.getLogger().error("updateTransaction genId: "+genId);
  		ISLogger.getLogger().error("updateTransaction res: "+res.getCode());
  		PreparedStatement ps = null;
  		try {  			
  			ps = c.prepareStatement("update HUMO_ACCRUAL_EMPLOYEE set STATE = ?, REPORT = ? where GENERAL_ID = ?");
  			ps.setInt(1, res.getCode() != 0 ? 5 : 6); // 5 - error, 6 - good    //18.02.2020 при неудачном пополнении оставлять тройку
  			ps.setString(2, res.getName());
  			ps.setLong(3, genId);
  			ps.execute();
  			c.commit();
  		}
  		catch (SQLException e){
  			ISLogger.getLogger().error("updateTransaction ERROR :"+e.getLocalizedMessage());
  		} finally {
			Utils.close(ps);
		}
  	}
  	
    private static void updateTransaction(Long genId, String alias, Res res) throws Exception{
        ISLogger.getLogger().error("updateTransaction genId: "+genId);
        ISLogger.getLogger().error("updateTransaction res: "+res.getCode());
        Connection c = null;
        PreparedStatement ps = null;
        try {     
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("update HUMO_ACCRUAL_EMPLOYEE set STATE = ?, REPORT = ? where GENERAL_ID = ?");
            ps.setInt(1, res.getCode() != 0 ? 5 : 6); // 5 - error, 6 - good    //18.02.2020 при неудачном пополнении оставлять тройку
            ps.setString(2, res.getCode() != 0 ? res.getName() : "Успешно!");
            ps.setLong(3, genId);
            ps.execute();
            c.commit();
        }
        catch (SQLException e){
            ISLogger.getLogger().error("updateTransaction ERROR :"+e.getLocalizedMessage());
        } finally {
            Utils.close(ps);
        }
    }
  	
	public static void payAgain(String filename) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_accrual_employee e set e.state = '3' where e.file_name = ? and e.state = '5'");
			ps.setString(1, filename);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
  	
  	
	public static void payAgain(String filename, String employee_id, String employee_account) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_accrual_employee e set e.state = '3' where e.state = '5' and e.file_name = ? and e.employee_id = ?, e.employee_account = ?");
			ps.setString(1, filename);
			ps.setString(2, employee_id);
			ps.setString(3, employee_account);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
  	
  	
  	
  	public static boolean getUsedFile(String fileName){
  		Connection c = null;
  		boolean result = false;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from HUMO_ACCRUAL_EMPLOYEE where FILE_NAME = ?");
			ps.setString(1, fileName);
			rs = ps.executeQuery();
			if(rs.next()) 
				if(rs.getInt(1) > 0) result = true;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
  	}

	public static HashMap<String, EmpcSettings> getSettings() {
		return settings;
	}
	
	public static HashMap<String, EmpcSettings> getSettingsByCardType() {
		return settingsByCardType;
	}
	
	public static HashMap<String, EmpcSettings> getSettingsByBin() {
		return settingsByBin;
	}

	public static void setSettings(HashMap<String, EmpcSettings> settings) {
		TclientService.settings = settings;
	}

/*	private static globus.IssuingWS.IssuingPortProxy initNp()throws ClientProtocolException, IOException,
	NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
globus.IssuingWS.IssuingPortProxy issuingPortProxy;

final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(
			java.security.cert.X509Certificate[] certs, String authType) {
	}

	public void checkServerTrusted(
			java.security.cert.X509Certificate[] certs, String authType) {
	}
} };
try {
	SSLContext sc = SSLContext.getInstance("TLSv1.2");
	sc.init(null, trustAllCerts, new java.security.SecureRandom());
	HostnameVerifier allHostsValid = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

} catch (Exception ex) {
}

return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(
		ConnectionPool.getValue("HUMO_HOST"),
		ConnectionPool.getValue("HUMO_USERNAME"),
		ConnectionPool.getValue("HUMO_PASSWORD"));
}*/
	
	public static String getRealCard(String alias, String card){
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String realCard = "";
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select real_card from humo_cards where card = ?");
			ps.setString(1, card);
			rs = ps.executeQuery();
			if(rs.next()){
				realCard = rs.getString("real_card");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return realCard;
    }
	
    private static List<FilterField> getFilterFields(final PaymentFilter filter) {
        final List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getAccount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "account_no=?", (Object)filter.getAccount()));
        }
        if (!CheckNull.isEmpty(filter.getAmount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "summa=?", (Object)filter.getAmount()));
        }
        if (!CheckNull.isEmpty(filter.getCardNumber())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "real_card=?", (Object)filter.getCardNumber()));
        }
        if (!CheckNull.isEmpty(filter.getDate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "term_type=?", (Object)filter.getDate()));
        }
        /*if (!CheckNull.isEmpty(filter.getPoint_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "point_code=?", (Object)filter.getPoint_code()));
        }*/
        if (!CheckNull.isEmpty(filter.getEmployee_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "employee_id=?", (Object)filter.getEmployee_id()));
        }
        if (!CheckNull.isEmpty(filter.getState())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "state=?", (Object)filter.getState()));
        }
        return flfields;
    }
    
    private static String getCond(final List<FilterField> flfields) {
        if (flfields.size() > 0) {
            return " and ";
        }
        return " where ";
    }
    
	public static java.sql.Date get_day(Connection c) throws Exception {
		CallableStatement ps = null;
		java.sql.Date res = null;
		try {
			ps = c.prepareCall("{? = call info.getDay() }");
			ps.registerOutParameter(1, java.sql.Types.DATE);
			ps.execute();
			res = ps.getDate(1);
		} catch (Exception e) {
			throw e;
		} finally {
			Utils.close(ps);
		}
		return res;
	}
    
    
    /*public static List<GlobuzAccount> getUploadedFl(int pageIndex, int pageSize, PaymentFilter filter, String alias)
	{
		
		List<PaymentFilter> list = new ArrayList<PaymentFilter>();
		Connection c = null;
		//int v_lowerbound = pageIndex + 1;
		List<FilterField> flFields = getFilterFields(filter);
		
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0)
		{
			
			for (int i = 0; i < flFields.size(); i++)
			{
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		
		try
		{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++)
			{
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(new GlobuzAccount(
											rs.getString("branch"),
											rs.getString("id"),
											rs.getString("acc_bal"),
											rs.getString("currency"),
											rs.getString("client"),
											rs.getString("id_order"),
											rs.getString("name"),
											rs.getString("sgn"),
											rs.getString("bal"),
											rs.getInt("sign_registr"),
											rs.getLong("s_in"),
											rs.getLong("s_out"),
											rs.getLong("dt"),
											rs.getLong("ct"),
											rs.getLong("s_in_tmp"),
											rs.getLong("s_out_tmp"),
											rs.getLong("dt_tmp"),
											rs.getLong("ct_tmp"),
											rs.getDate("l_date"),
											rs.getDate("date_open"),
											rs.getDate("date_close"),
											rs.getInt("acc_group_id"),
											rs.getInt("state")));
			}
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			
		}
		finally
		{
			ConnectionPool.close(c);
		}
		//ISLogger.getLogger().error("LIST: "+list.get(0));
		return list;
		
	}*/
    
    private static List<FilterField> getFilterFields(GlobuzAccountFilter filter)
	{
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getBranch()))
		{
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_bal()))
		{
			flfields.add(new FilterField(getCond(flfields) + "acc_bal=?", filter.getAcc_bal()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency()))
		{
			flfields.add(new FilterField(getCond(flfields) + "currency=?", filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getClient()))
		{
			flfields.add(new FilterField(getCond(flfields) + "client=?", filter.getClient()));
		}
		if (!CheckNull.isEmpty(filter.getId_order()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_order >= ?", filter.getId_order()));
		}
		if (!CheckNull.isEmpty(filter.getLast_order()))
		{
			flfields.add(new FilterField(getCond(flfields) + "id_order < ?", filter.getLast_order()));
		}
		if (!CheckNull.isEmpty(filter.getName()))
		{
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getSgn()))
		{
			flfields.add(new FilterField(getCond(flfields) + "sgn=?", filter.getSgn()));
		}
		if (!CheckNull.isEmpty(filter.getBal()))
		{
			flfields.add(new FilterField(getCond(flfields) + "bal=?", filter.getBal()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr()))
		{
			flfields.add(new FilterField(getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getS_in()))
		{
			flfields.add(new FilterField(getCond(flfields) + "s_in=?", filter.getS_in()));
		}
		if (!CheckNull.isEmpty(filter.getS_out()))
		{
			flfields.add(new FilterField(getCond(flfields) + "s_out=?", filter.getS_out()));
		}
		if (!CheckNull.isEmpty(filter.getDt()))
		{
			flfields.add(new FilterField(getCond(flfields) + "dt=?", filter.getDt()));
		}
		if (!CheckNull.isEmpty(filter.getCt()))
		{
			flfields.add(new FilterField(getCond(flfields) + "ct=?", filter.getCt()));
		}
		if (!CheckNull.isEmpty(filter.getS_in_tmp()))
		{
			flfields.add(new FilterField(getCond(flfields) + "s_in_tmp=?", filter.getS_in_tmp()));
		}
		if (!CheckNull.isEmpty(filter.getS_out_tmp()))
		{
			flfields.add(new FilterField(getCond(flfields) + "s_out_tmp=?", filter.getS_out_tmp()));
		}
		if (!CheckNull.isEmpty(filter.getDt_tmp()))
		{
			flfields.add(new FilterField(getCond(flfields) + "dt_tmp=?", filter.getDt_tmp()));
		}
		if (!CheckNull.isEmpty(filter.getCt_tmp()))
		{
			flfields.add(new FilterField(getCond(flfields) + "ct_tmp=?", filter.getCt_tmp()));
		}
		if (!CheckNull.isEmpty(filter.getL_date()))
		{
			flfields.add(new FilterField(getCond(flfields) + "l_date=?", filter.getL_date()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open()))
		{
			flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close()))
		{
			flfields.add(new FilterField(getCond(flfields) + "date_close=?", filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_group_id()))
		{
			flfields.add(new FilterField(getCond(flfields) + "acc_group_id=?", filter.getAcc_group_id()));
		}
		if (!CheckNull.isEmpty(filter.getState()))
		{
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		
		return flfields;
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
	}
	
	public static String getIp(){
		HttpServletRequest hr = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		String ipAddress = hr.getHeader("x-forwarded-for");
		if (ipAddress == null)
		{
			ipAddress = hr.getHeader("X_FORWARDED_FOR");
			if (ipAddress == null)
			{
				ipAddress = hr.getRemoteAddr();
			}
		}
		return ipAddress;
	}
	
	
	
	public static boolean checkPaymentInTableExistense(Connection c, String generalId, String branch, String employeeAccount) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			ps = c.prepareStatement("select * from humo_accrual_employee e where e.general_id = ? and e.branch = ? and e.employee_account = ?");
			ps.setString(1, generalId);
			ps.setString(2, branch);
			ps.setString(3, employeeAccount);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return res;
	}
	
	public static void insertSinglePaymentInTable(Connection c, String branch, String employeeId, String employeeName, String employeeAccount, String amount, String state, String sessionId) {
		ISLogger.getLogger().error("insertSinglePaymentInTable!");
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into HUMO_ACCRUAL_EMPLOYEE (ID, BRANCH, CUSTOMER_ID, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ACCOUNT, SUMMA, DATE_OPER, STATE, sessionId) values (SQ_HUMO_ACCRUAL_EMPLOYEE.nextval,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, branch);
			ps.setString(2, "SingleP");
			ps.setString(3, employeeId);
			ps.setString(4, employeeName);
			ps.setString(5, employeeAccount);
			ps.setString(6, amount);
			Date date = new Date();
			ps.setDate(7, new java.sql.Date(date.getTime()));
			ps.setString(8, state);
			ps.setString(9, sessionId);
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.close(ps);
		}
	}
	
	public static java.sql.Date getDayForExcel(String un, String pwd, String alias){
		Connection c = null;
		java.sql.Date date = null;
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			date = get_day(c);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.close(c);
		}
		return date;
	}
	public static java.sql.Date getDayByBranch(String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date date = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select t.curr_date from branch t");
			rs = ps.executeQuery();
			if(rs.next()){
				date = rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return date;
	}
	
	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
	  {
	      if ( x > 0) {
	          return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
	      } else {
	          return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	      }
	  }
	
	public static CardInfo getCardInfoByTranzAcct(Connection c, String branch, String tranzAcct){
		CardInfo cardInfo = new CardInfo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select c.card, c.real_card, c.account_no, c.client, c.status1 from humo_cards c, bf_empc_accounts a where a.tranz_acct = ? and c.branch = ? and a.account_no = c.account_no");
			ps.setString(1, tranzAcct);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while(rs.next()){
				cardInfo.setCARD(rs.getString("card"));
				cardInfo.setCARD_ACCT(rs.getString("real_card"));
				cardInfo.setACCOUNT_NO(rs.getString("account_no"));
				cardInfo.setCLIENT_ID(rs.getString("client"));
				cardInfo.setSTATUS(rs.getString("status1"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Utils.close(rs);
			Utils.close(ps);
		}
		return cardInfo;
	}
	
    public static CardInfo getLatestCardByTranzAcct(Connection c, String branch, String tranzAcct){
        CardInfo cardInfo = new CardInfo();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select card, real_card, account_no, client, status1\r\n" + 
            		"     from humo_cards\r\n" + 
            		"    where account_no = (select max(account_no)\r\n" + 
            		"                          from bf_empc_accounts\r\n" + 
            		"                         where tranz_acct = ?)");
            ps.setString(1, tranzAcct);
            rs = ps.executeQuery();
            if(rs.next()){
                cardInfo.setCARD(rs.getString("card"));
                cardInfo.setCARD_ACCT(rs.getString("real_card"));
                cardInfo.setACCOUNT_NO(rs.getString("account_no"));
                cardInfo.setCLIENT_ID(rs.getString("client"));
                cardInfo.setSTATUS(rs.getString("status1"));                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            Utils.close(rs);
            Utils.close(ps);
        }
        return cardInfo;
    }
	
	public static boolean checkSpecialAccIsBlocked(String branch, String account) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean result = false;
	    try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select count(*) from specialacc where branch = ? and acc = ? and id_special in ('36', '37')");
            ps.setString(1, branch);
            ps.setString(2, account);
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
	
	public static List<PacketPayment> getPacketPaymentsNewOnly(String branch, String alias){
	       HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
	        stateMap.put(0, "Загружено");
	        stateMap.put(1, "В обработке");
	        stateMap.put(2, "2");
	        stateMap.put(3, "Зачислено на счета. Доступно к зачислению на карты");
	        stateMap.put(19, "Зачислено на счета. Проводится зачисление на карты");
	        stateMap.put(4, "Частично обработано");
	        stateMap.put(5, "Ошибка пополнения");
	        stateMap.put(6, "Успешно обработано");
	        stateMap.put(7, "Успешно списано");
	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<PacketPayment> packetPaymentsOfToday = new ArrayList<PacketPayment>();
	    try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from humo_packet_payments where branch = ? and load_date = to_date(? , 'dd.mm.yyyy')");
            ps.setString(1, branch);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String dateFormatted = df.format(date);
            ps.setString(2, dateFormatted);
            rs = ps.executeQuery();
            while (rs.next()) {
                PacketPayment packetPayment = new PacketPayment();
                packetPayment.setId(rs.getLong("id"));
                packetPayment.setBranch(rs.getString("branch"));
                packetPayment.setAccount(rs.getString("account"));
                packetPayment.setCodeOrganization(rs.getString("org_code"));
                packetPayment.setPaymentPurpose(rs.getString("purpose"));
                packetPayment.setGeneralPay(rs.getLong("general_pay"));
                packetPayment.setState(rs.getInt("state"));
                packetPayment.setName(rs.getString("name"));
                packetPayment.setDate(rs.getDate("load_date"));
                packetPayment.setStateText(stateMap.get(packetPayment.getState()));
                packetPaymentsOfToday.add(packetPayment);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return packetPaymentsOfToday;
	}
	
	   public static List<PacketPayment> getPacketPaymentsAll(String branch, String alias){
           HashMap<Integer, String> stateMap = new HashMap<Integer, String>();
            stateMap.put(0, "Загружено");
            stateMap.put(1, "В обработке");
            stateMap.put(2, "2");
            stateMap.put(3, "Зачислено на счета. Доступно к зачислению на карты");
            stateMap.put(19, "Зачислено на счета. Проводится зачисление на карты");
            stateMap.put(4, "Частично обработано");
            stateMap.put(5, "Ошибка пополнения");
            stateMap.put(6, "Успешно обработано");
            stateMap.put(7, "Успешно списано");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PacketPayment> packetPaymentsOfToday = new ArrayList<PacketPayment>();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from humo_packet_payments where branch = ?");
            ps.setString(1, branch);
            rs = ps.executeQuery();
            while (rs.next()) {
                PacketPayment packetPayment = new PacketPayment();
                packetPayment.setId(rs.getLong("id"));
                packetPayment.setBranch(rs.getString("branch"));
                packetPayment.setAccount(rs.getString("account"));
                packetPayment.setCodeOrganization(rs.getString("org_code"));
                packetPayment.setPaymentPurpose(rs.getString("purpose"));
                packetPayment.setGeneralPay(rs.getLong("general_pay"));
                packetPayment.setState(rs.getInt("state"));
                packetPayment.setName(rs.getString("name"));
                packetPayment.setDate(rs.getDate("load_date"));
                packetPayment.setStateText(stateMap.get(packetPayment.getState()));
                packetPaymentsOfToday.add(packetPayment);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return packetPaymentsOfToday;
    }
	
	public static void updateExcelState(PacketPayment packetPayment) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("update humo_packet_payments set state = ? where name = ?");
            ps.setInt(1, packetPayment.getState());
            ps.setString(2, packetPayment.getName());
            ps.execute();
            c.commit();
        } catch (SQLException e) {
            Utils.rollback(c);
            e.printStackTrace();
        }finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
	}
	
	   public static void updateExcelStateProcessing(PacketPayment packetPayment) {
	       ISLogger.getLogger().error("updateExcelStateProcessing");
	        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement("update humo_packet_payments set state = ? where name = ? and branch = ?");
	            ps.setInt(1, 1);
	            ps.setString(2, packetPayment.getName());
	            ps.setString(3, packetPayment.getBranch());
	            ps.execute();
	            c.commit();
	        } catch (SQLException e) {
	            Utils.rollback(c);
	            e.printStackTrace();
	        }finally {
	            Utils.close(ps);
	            ConnectionPool.close(c);
	        }
	    }
	   
	   @SuppressWarnings("finally")
    public static int checkPacketPaymentState(PacketPayment packetPayment) {
	       Connection c = null;
	       PreparedStatement ps = null;
	       ResultSet rs = null;
	       int state = -1;
	       try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select state from humo_packet_payments where name = ? and branch = ?");
            ps.setString(1, packetPayment.getName());
            ps.setString(2, packetPayment.getBranch());
            rs = ps.executeQuery();
            if(rs.next()) {
                state = rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
            return state;
        }
	   }
	
	public static Double parseSalary(String salary) {
	    if(Strings.isNullOrEmpty(salary)) {
	        return null;
	    }
        if (!salary.contains("E")) {
            String[] splitter = String.valueOf(salary).split("\\.");
            String[] splitter2 = String.valueOf(salary).split("\\,");
            int i = 0, i2 = 0;
            if (splitter.length > 1) {
                i = splitter[1].length();
            }
            if (splitter2.length > 1) {
                i2 = splitter2[1].length();
            }
            if (i > 2 || i2 > 2) {
                return null;
            }
        }
        double salaryBD;
        try {
            salaryBD = Double.parseDouble(salary);
        } catch (Exception e) {
            return null;
        }
        return salaryBD;
	}
	
    public static List<AccrualEmployee> getPaymentList(PacketPayment currentPacketPayment,
            Boolean isEqual, String alias) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AccrualEmployee> paymentList = new ArrayList<AccrualEmployee>();
        String sqlEqual = "select * from HUMO_ACCRUAL_EMPLOYEE where state = ? and customer_id = ? and general_pay = ? and branch = ? and file_name = ?";
        String sqlNotEqual = "select * from HUMO_ACCRUAL_EMPLOYEE where state <> ? and customer_id = ? and general_pay = ? and branch = ? and file_name = ?";
        try {
            //c = ConnectionPool.getConnection();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"),
                    ConnectionPool.getValue("HUMO_CON_LOGIN"),
                    ConnectionPool.getValue("HUMO_CON_PAS"));
            c.setAutoCommit(false);
            // c = ConnectionPool.getConnection();
            ps = c.prepareStatement(isEqual ? sqlEqual : sqlNotEqual);
            ps.setInt(1, isEqual ? currentPacketPayment.getState() : 6);
            ps.setString(2, currentPacketPayment.getCodeOrganization());
            ps.setString(3, currentPacketPayment.getGeneralPay().toString());
            ps.setString(4, currentPacketPayment.getBranch());
            ps.setString(5, currentPacketPayment.getName());
            rs = ps.executeQuery();
            int index = 0;
            while (rs.next()) {
                AccrualEmployee payment = new AccrualEmployee();
                ++index;
                String[] splitter;
                splitter = String.valueOf(rs.getString("summa")).split("\\.");
                payment.setAmount(splitter[0]);
                payment.setGeneralId(rs.getLong("general_id"));
                payment.setBranch(currentPacketPayment.getBranch());
                payment.setEmployeeAccount(rs.getString("EMPLOYEE_ACCOUNT"));
                payment.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
                payment.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                CardInfo cardInfo = getLatestCardByTranzAcct(c, currentPacketPayment.getBranch(),
                        payment.getEmployeeAccount());
                /*CardInfo cardInfo = getCardInfoByTranzAcct(c, currentPacketPayment.getBranch(),
                        payment.getEmployeeAccount());*/
                
                // AGRO АГРО и МАДАТ МАДАД MADAD без проверки на блокировку карты
                ISLogger.getLogger().error("HUMO_BANK_C in payment: "+HUMO_BANK_C);
                if (HUMO_BANK_C.equals("32") || HUMO_BANK_C.equals("03")) {
                    payment.setRealCard(cardInfo.getCARD_ACCT());
                    payment.setCard(cardInfo.getCARD());
                    payment.setAccountNo(cardInfo.getACCOUNT_NO());
                    payment.setClient(cardInfo.getCLIENT_ID());
                    payment.setFileName(rs.getString("FILE_NAME"));
                    insertAdditionalData(payment, alias, c);
                    ObjectMapper objectMapper = new ObjectMapper();
                    ISLogger.getLogger().error(
                            "PACKET PAYMENT: " + objectMapper.writeValueAsString(payment));
                    if (payment.getAmount() == null || payment.getGeneralId() == null
                            || payment.getBranch() == null || payment.getEmployeeAccount() == null
                            || payment.getEmployeeName() == null || payment.getEmployeeId() == null
                            || payment.getAccountNo() == null || payment.getRealCard() == null
                            || payment.getClient() == null || payment.getFileName() == null) {
                        String errorString = "Ошибка! Нет данных по";
                        if (payment.getAmount() == null) {
                            errorString = errorString + " сумме,";
                        }
                        if (payment.getGeneralId() == null) {
                            errorString = errorString + " проводке,";
                        }
                        if (payment.getBranch() == null) {
                            errorString = errorString + " бранчу,";
                        }
                        if (payment.getEmployeeAccount() == null) {
                            errorString = errorString + " счёту,";
                        }
                        if (payment.getEmployeeName() == null) {
                            errorString = errorString + " имени клиента,";
                        }
                        if (payment.getEmployeeId() == null) {
                            errorString = errorString + " коду клиента,";
                        }
                        if (payment.getAccountNo() == null) {
                            errorString = errorString + " account no,";
                        }
                        if (payment.getRealCard() == null) {
                            errorString = errorString + " номеру карты,";
                        }
                        if (payment.getClient() == null) {
                            errorString = errorString + " id клиента,";
                        }
                        errorString.subSequence(0, errorString.length() - 1);
                        Res result = new Res();
                        result.setCode(1);
                        result.setName(errorString);
                        updateTransaction(c, payment.getGeneralId(), alias, result);
                        c.commit();
                    } else {
                        paymentList.add(payment);
                    }
                } else {
                    if (cardInfo.getSTATUS().equals("2")) {
                        String errorString = "Карта " + cardInfo.getCARD()
                                + " на данном счёте заблокирована.";
                        Res result = new Res();
                        result.setCode(1);
                        result.setName(errorString);
                        updateTransaction(c, payment.getGeneralId(), alias, result);
                    } else {
                        payment.setRealCard(cardInfo.getCARD_ACCT());
                        payment.setCard(cardInfo.getCARD());
                        payment.setAccountNo(cardInfo.getACCOUNT_NO());
                        payment.setClient(cardInfo.getCLIENT_ID());
                        payment.setFileName(rs.getString("FILE_NAME"));
                        insertAdditionalData(payment, alias, c);
                        ObjectMapper objectMapper = new ObjectMapper();
                        ISLogger.getLogger().error(
                                "PACKET PAYMENT: " + objectMapper.writeValueAsString(payment));
                        if (payment.getAmount() == null || payment.getGeneralId() == null
                                || payment.getBranch() == null
                                || payment.getEmployeeAccount() == null
                                || payment.getEmployeeName() == null
                                || payment.getEmployeeId() == null
                                || payment.getAccountNo() == null || payment.getRealCard() == null
                                || payment.getClient() == null || payment.getFileName() == null) {
                            String errorString = "Ошибка! Нет данных по";
                            if (payment.getAmount() == null) {
                                errorString = errorString + " сумме,";
                            }
                            if (payment.getGeneralId() == null) {
                                errorString = errorString + " проводке,";
                            }
                            if (payment.getBranch() == null) {
                                errorString = errorString + " бранчу,";
                            }
                            if (payment.getEmployeeAccount() == null) {
                                errorString = errorString + " счёту,";
                            }
                            if (payment.getEmployeeName() == null) {
                                errorString = errorString + " имени клиента,";
                            }
                            if (payment.getEmployeeId() == null) {
                                errorString = errorString + " коду клиента,";
                            }
                            if (payment.getAccountNo() == null) {
                                errorString = errorString + " account no,";
                            }
                            if (payment.getRealCard() == null) {
                                errorString = errorString + " номеру карты,";
                            }
                            if (payment.getClient() == null) {
                                errorString = errorString + " id клиента,";
                            }
                            errorString.subSequence(0, errorString.length() - 1);
                            Res result = new Res();
                            result.setCode(1);
                            result.setName(errorString);
                            updateTransaction(c, payment.getGeneralId(), alias, result);
                            c.commit();
                        } else {
                            paymentList.add(payment);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Utils.rollback(c);
            ISLogger.getLogger().error("getPaymentList error: ", e);
        } finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return paymentList;
    }
	
	@SuppressWarnings("finally")
    public static boolean createPacketPayment(PacketPayment packetPayment, Connection c) {
	    PreparedStatement ps = null;
	    boolean result = true;
	    try {
            ps = c.prepareStatement("insert into humo_packet_payments (id,\r\n" + 
            		"                                  branch,\r\n" + 
            		"                                  account,\r\n" + 
            		"                                  org_code,\r\n" + 
            		"                                  purpose,\r\n" + 
            		"                                  general_pay,\r\n" + 
            		"                                  state,\r\n" + 
            		"                                  name,\r\n" + 
            		"                                  load_date) values (SEQ_humo_packet_payments.Nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, packetPayment.getBranch());
            ps.setString(2, packetPayment.getAccount());
            ps.setString(3, packetPayment.getCodeOrganization());
            ps.setString(4, packetPayment.getPaymentPurpose());
            ps.setLong(5, packetPayment.getGeneralPay());
            ps.setInt(6, packetPayment.getState());
            ps.setString(7, packetPayment.getName());
            ps.setDate(8, new java.sql.Date(packetPayment.getDate().getTime()));
            ps.execute();
        } catch (SQLException e) {
            ISLogger.getLogger().error("createPacketPayment error: ", e);
            Utils.rollback(c);
            result = false;
            e.printStackTrace();
        }finally {
            Utils.close(ps);
            return result;
        }
	}
	
	public static boolean isTransactionExecuted(AccrualEmployee payment, String alias) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean isTransactionExecuted = false;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select count(*) from bf_globuz_trans where branch = ? and genid = ?");
            ps.setString(1, payment.getBranch());
            ps.setLong(2, payment.getGeneralId());
            rs = ps.executeQuery();
            if(rs.next()) {
                isTransactionExecuted = rs.getInt(1) > 0 ? true : false;
            }
        } catch (SQLException e) {
            ISLogger.getLogger().error("isTransactionExecuted error: ", e);
            e.printStackTrace();
        }finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return isTransactionExecuted;
	}
	
/*    if (!salary.contains("E")) {
        String[] splitter = String.valueOf(salary).split("\\.");
        String[] splitter2 = String.valueOf(salary).split("\\,");
        int i = 0, i2 = 0;
        if (splitter.length > 1) {
            i = splitter[1].length();
        }
        if (splitter2.length > 1) {
            i2 = splitter2[1].length();
        }
        if (i > 2 || i2 > 2) {
            c.rollback();
            String msg = "Неверный формат суммы";
            res.setName(msg);
            res.setCode(-1);
            throw new Exception(msg);
        }
    }
    if (salary == null || salary.equals("")) {
        c.rollback();
        String msg = "Неверная сумма";
        res.setCode(-1);
        res.setName(msg);
        throw new Exception(msg);
    }
    double salaryBD;
    try {
        salaryBD = Double.parseDouble(salary);
    } catch (Exception e) {
        c.rollback();
        e.printStackTrace();
        ISLogger.getLogger().error(CheckNull.getPstr(e));
        String msg = "Неверный формат суммы";
        res.setCode(-1);
        res.setName(msg);
        throw new Exception(msg);
    }*/
	
/*	public static Res saveExcel(PacketPayment packetPayment, Label label, InputStream in, String alias, String un, String pwd) {

        Connection c = null;
        Row currentRow = null;
        Res res = new Res();
        PreparedStatement ps = null;
        label.setValue(" ");
        boolean isAcc = false;
        boolean isRealCard = false;
        boolean isCard = false;
        //java.sql.Date date = getDayForExcel(un, pwd, alias);
        java.sql.Date date = getDayByBranch(alias);
        try {
            c = ConnectionPool.getConnection(un, pwd, alias);
          Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"), ConnectionPool.getValue("HUMO_CON_LOGIN"), ConnectionPool.getValue("HUMO_CON_PAS"));
            c.setAutoCommit(false);
            //c = ConnectionPool.getConnection();
            XSSFWorkbook book = new XSSFWorkbook(in);
            Sheet datatypeSheet = book.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int index = 0;
            insMainTable(packetPayment.getBranch(), packetPayment.getCodeOrganization(),
                    packetPayment.getAccount(), packetPayment.getPaymentPurpose(), alias, c);
            while (iterator.hasNext()) {
                currentRow = iterator.next();
                ++index;
                label.setValue(String.valueOf(index));
                Cell accCell = currentRow.getCell(0);
                Cell nameCell = currentRow.getCell(1);
                Cell salaryCell = currentRow.getCell(2);
                String acc = getCellValue(accCell);
                System.out.println("Number: " + index + "SCHET: " + acc);
                if (index == 1) {
                    if (acc == null || acc.equals("") || !acc.startsWith("22618") || !(checkAccount(acc, c))) {
                        isAcc = false;
                        if (acc.length() != 16 || !acc.startsWith("9860")) {
                            c.rollback();
                            String msg = "Неверный формат ячейки";
                            res.setName(msg);
                            res.setCode(-1);
                            throw new Exception(msg);
                        } else if (acc.matches("^[0-9]+$")) {
                            System.out.println("isRealCard");
                            isRealCard = true;
                        } else {
                            System.out.println("isCard");
                            isCard = true;
                        }
                        
                         * c.rollback(); String msg = "Неверный формат счёта";
                         * res.setName(msg); res.setCode(-1); throw new
                         * Exception(msg);
                         
                    } else {
                        isAcc = true;
                    }
                } else {
                    if (isAcc == true) {
                        if (acc == null || acc.equals("") || !acc.startsWith("22618")
                                || !(checkAccount(acc, c))) {
                            c.rollback();
                            String msg = "Неверный формат счёта";
                            res.setName(msg);
                            res.setCode(-1);
                            throw new Exception(msg);
                        }
                    } else if (isRealCard == true) {
                        if (acc.length() != 16 || !acc.startsWith("9860")
                                || !acc.matches("^[0-9]+$")) {
                            c.rollback();
                            String msg = "Неверный формат карты";
                            res.setName(msg);
                            res.setCode(-1);
                            throw new Exception(msg);
                        }
                    } else if (isCard == true) {
                        if (acc.length() != 16 || !acc.startsWith("9860")
                                || acc.matches("^[0-9]+$")) {
                            c.rollback();
                            String msg = "Неверный формат карты";
                            res.setName(msg);
                            res.setCode(-1);
                            throw new Exception(msg);
                        }
                    }
                }
                String name = getCellValue(nameCell);
                if (name == null || name.equals("") || name.length() > 180) {
                    c.rollback();
                    String msg = "Неверный формат имени";
                    res.setName(msg);
                    res.setCode(-1);
                    throw new Exception(msg);
                }
                String salary = getCellValue(salaryCell);
                if (!salary.contains("E")) {
                    System.out.println("salary: " + salary);
                    String[] splitter = String.valueOf(salary).split("\\.");
                    String[] splitter2 = String.valueOf(salary).split("\\,");
                    System.out.println("splitter: " + splitter.length);
                    System.out.println("splitter2: " + splitter2.length);
                    int i = 0, i2 = 0;
                    if (splitter.length > 1) {
                        i = splitter[1].length();
                        System.out.println("i: " + i);
                    }
                    if (splitter2.length > 1) {
                        i2 = splitter2[1].length();
                        System.out.println("i2: " + i2);
                    }

                    if (i > 2 || i2 > 2) {
                        c.rollback();
                        System.out.println("i check: " + i);
                        String msg = "Неверный формат суммы";
                        res.setName(msg);
                        res.setCode(-1);
                        throw new Exception(msg);
                    }
                }
                System.out.println("salary: " + salary);
                if (salary == null || salary.equals("")) {
                    c.rollback();
                    String msg = "Неверная сумма";
                    res.setCode(-1);
                    res.setName(msg);
                    throw new Exception(msg);
                }
                double salaryBD;
                try {
                    salaryBD = Double.parseDouble(salary);
                    System.out.println("salaryBD: " + salaryBD);

                } catch (Exception e) {
                    c.rollback();
                    e.printStackTrace();
                    ISLogger.getLogger().error(CheckNull.getPstr(e));
                    String msg = "Неверный формат суммы";
                    res.setCode(-1);
                    res.setName(msg);
                    throw new Exception(msg);
                }
                String[] splitter = String.valueOf(salaryBD * 100).split("\\.");
                System.out.println("String.valueOf(salaryBD * 100): "
                        + salaryBD * 100);
                if (isAcc == true) {
                    CardInfo cardInfo = getCardInfoByTranzAcct(packetPayment.getBranch(), acc);
                    ps = c.prepareStatement("insert into HUMO_ACCRUAL_EMPLOYEE (ID, BRANCH, CUSTOMER_ID, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ACCOUNT, SUMMA, DATE_OPER, STATE, FILE_NAME, general_pay, card, real_card, account_no, client) values (SQ_HUMO_ACCRUAL_EMPLOYEE.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, packetPayment.getBranch());
                    ps.setString(2, packetPayment.getCodeOrganization());
                    ps.setString(3, acc.substring(9, 17));
                    ps.setString(4, name);
                    ps.setString(5, acc.substring(0, 20));
                    ps.setDouble(6, (Math.round(salaryBD * 100)));
                    System.out.println("splitter[0]: " + splitter[0]);
                    ps.setDate(7, date);
                    ps.setInt(8, 0);
                    ps.setString(9, packetPayment.getName());
                    ps.setString(10, packetPayment.getGeneralPay().toString());
                    ps.setString(11, cardInfo.getCARD());
                    ps.setString(12, cardInfo.getCARD_ACCT());
                    ps.setString(13, cardInfo.getACCOUNT_NO());
                    ps.setString(14, cardInfo.getCLIENT_ID());
                    ps.execute();
                } else if (isRealCard == true) {
                    System.out.println("acc: " + acc);
                    String tranzAcct = Utils.getValueFromSql("select a.tranz_acct from bf_empc_accounts a, humo_cards c where c.real_card = '"
                            + acc + "' and c.account_no = a.account_no", alias);
                    System.out.println("tranzAcct: " + tranzAcct);
                    ps = c.prepareStatement("insert into HUMO_ACCRUAL_EMPLOYEE (ID, BRANCH, CUSTOMER_ID, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ACCOUNT, SUMMA, DATE_OPER, STATE, FILE_NAME, general_pay, real_card) values (SQ_HUMO_ACCRUAL_EMPLOYEE.nextval,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, packetPayment.getBranch());
                    ps.setString(2, packetPayment.getCodeOrganization());
                    ps.setString(3, tranzAcct.substring(9, 17));
                    ps.setString(4, name);
                    ps.setString(5, tranzAcct);
                    ps.setDouble(6, (Math.round(salaryBD * 100)));
                    System.out.println("splitter[0]: " + splitter[0]);
                    ps.setDate(7, date);
                    ps.setInt(8, 0);
                    ps.setString(9, packetPayment.getName());
                    ps.setString(10, packetPayment.getGeneralPay().toString());
                    ps.setString(11, acc);
                    ps.execute();
                } else if (isCard == true) {
                    System.out.println("acc: " + acc);
                    String tranzAcct = Utils.getValueFromSql("select a.tranz_acct from bf_empc_accounts a, humo_cards c where c.card = '"
                            + acc + "' and c.account_no = a.account_no", alias);
                    String realCard = Utils.getValueFromSql("select real_card from humo_cards where card = '"+acc+"'", alias);
                    System.out.println("tranzAcct: " + tranzAcct);
                    ps = c.prepareStatement("insert into HUMO_ACCRUAL_EMPLOYEE (ID, BRANCH, CUSTOMER_ID, EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ACCOUNT, SUMMA, DATE_OPER, STATE, FILE_NAME, general_pay, card, real_card) values (SQ_HUMO_ACCRUAL_EMPLOYEE.nextval,?,?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, packetPayment.getBranch());
                    ps.setString(2, packetPayment.getCodeOrganization());
                    ps.setString(3, tranzAcct.substring(9, 17));
                    ps.setString(4, name);
                    ps.setString(5, tranzAcct);
                    ps.setDouble(6, (Math.round(salaryBD * 100)));
                    System.out.println("splitter[0]: " + splitter[0]);
                    ps.setDate(7, date);
                    ps.setInt(8, 0);
                    ps.setString(9, packetPayment.getName());
                    ps.setString(10, packetPayment.getGeneralPay().toString());
                    ps.setString(11, acc);
                    ps.setString(12, realCard);
                    ps.execute();
                }
            }
            c.commit();
        } catch (Exception e) {
            Utils.rollback(c);
            e.printStackTrace();
            res.setCode(-1);
            if (CheckNull.getPstr(e).startsWith(
                            "java.sql.SQLException: ORA-00001")) {
                res.setName("Счёт клиента в таблице не должен повторяться! Таблица не загружена.");
            } else {
                res.setName(CheckNull.getPstr(e));
            }
            label.setValue("0");
            ISLogger.getLogger().error("SAVE EXCEL ERROR: "+CheckNull.getPstr(e));
        } finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return res;
    
	}*/
	
	
}

