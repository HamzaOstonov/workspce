package com.is.tieto_globuz.customer;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.ItemType_Generic;
import globus.issuing_v_01_02_xsd.KeyType_Agreement;
import globus.issuing_v_01_02_xsd.ListType_AccountInfo;
import globus.issuing_v_01_02_xsd.ListType_CardInfo;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_Agreement;
import globus.issuing_v_01_02_xsd.RowType_CardInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_DeactivateCard_Request;
import globus.issuing_v_01_02_xsd.RowType_Generic;
import globus.issuing_v_01_02_xsd.RowType_GetRealCard_Request;
import globus.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import globus.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request;
import globus.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import globus.issuing_v_01_02_xsd.RowType_ReplaceCard;
import globus.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request;
import globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.python.constantine.platform.darwin.Sysconf;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.account.Account;
import com.is.clienta.utils.DbUtils;
import com.is.humo.AccountHumo;
import com.is.humo.Customer;
import com.is.humo.GlobuzAccount;
import com.is.humo.GlobuzAccountFilter;
import com.is.humo.GlobuzAccountService;
import com.is.humo.HumoCardSetting;
import com.is.humo.HumoCards;
import com.is.humo.HumoCardsFilter;
import com.is.humo.PacketOpenCard;
import com.is.humo.ResponsInfo;
import com.is.humo.SS_HUMO_TYPE_OF_CARD;
import com.is.tieto_globuz.EmpcSettings;
import com.is.tieto_globuz.Utils;
import com.is.tieto_globuz.tieto.AccInfo;
import com.is.tieto_globuz.tieto.CardInfo;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class HumoCardsService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM humo_cards ";
	private static PreparedStatement psCurrentDate=null;
	static String HUMO_BANK_C;
	static String HUMO_GROUPC;
	static String HUMO_BINCOD;
	private static String HUMO_HOST;
	private static String HUMO_USERNAME;
	private static String HUMO_PASSWORD;
	private static globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
	private static OperationConnectionInfo connectionInfo = null;
	static Long HUMO_CHIPAPPID;
	static String HUMO_BRANCHID;
	static Long HUMO_RANGEID;
	static Calendar calendar = null; 
	private static PreparedStatement psdeleteCards=null;
	private static PreparedStatement psUpdateCards=null;
	private static PreparedStatement psSelectStopCards=null;
	
	private final static Logger logger = Logger
			.getLogger(HumoCardsService.class);

	public static void initCards(Connection c) throws SQLException{
	
	
	psCurrentDate = c.prepareStatement("SELECT * FROM branch WHERE bank_id=?");
	
}
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(HumoCardsFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getClient())) {
			flfields.add(new FilterField(getCond(flfields) + "client=?", filter
					.getClient()));
		}
		if (!CheckNull.isEmpty(filter.getClient_b())) {
			flfields.add(new FilterField(getCond(flfields) + "client_b=?",
					filter.getClient_b()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter
					.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getCard())) {
			flfields.add(new FilterField(getCond(flfields) + "card=?", filter
					.getCard()));
		}
		if (!CheckNull.isEmpty(filter.getStatus1())) {
			flfields.add(new FilterField(getCond(flfields) + "status1=?",
					filter.getStatus1()));
		}
		if (!CheckNull.isEmpty(filter.getStatus2())) {
			flfields.add(new FilterField(getCond(flfields) + "status2=?",
					filter.getStatus2()));
		}
		if (!CheckNull.isEmpty(filter.getExpiry1())) {
			flfields.add(new FilterField(getCond(flfields) + "expiry1=?",
					filter.getExpiry1()));
		}
		if (!CheckNull.isEmpty(filter.getExpirity2())) {
			flfields.add(new FilterField(getCond(flfields) + "expirity2=?",
					filter.getExpirity2()));
		}
		if (!CheckNull.isEmpty(filter.getRenew())) {
			flfields.add(new FilterField(getCond(flfields) + "renew=?", filter
					.getRenew()));
		}
		if (!CheckNull.isEmpty(filter.getRenew_date())) {
			flfields.add(new FilterField(getCond(flfields) + "renew_date=?",
					filter.getRenew_date()));
		}
		if (!CheckNull.isEmpty(filter.getCard_name())) {
			flfields.add(new FilterField(getCond(flfields) + "card_name=?",
					filter.getCard_name()));
		}
		if (!CheckNull.isEmpty(filter.getMc_name())) {
			flfields.add(new FilterField(getCond(flfields) + "mc_name=?",
					filter.getMc_name()));
		}
		if (!CheckNull.isEmpty(filter.getM_name())) {
			flfields.add(new FilterField(getCond(flfields) + "m_name=?", filter
					.getM_name()));
		}
		if (!CheckNull.isEmpty(filter.getStop_cause())) {
			flfields.add(new FilterField(getCond(flfields) + "stop_cause=?",
					filter.getStop_cause()));
		}
		if (!CheckNull.isEmpty(filter.getRenewed_card())) {
			flfields.add(new FilterField(getCond(flfields) + "renewed_card=?",
					filter.getRenewed_card()));
		}
		if (!CheckNull.isEmpty(filter.getDesign_id())) {
			flfields.add(new FilterField(getCond(flfields) + "design_id=?",
					filter.getDesign_id()));
		}
		if (!CheckNull.isEmpty(filter.getInstant())) {
			flfields.add(new FilterField(getCond(flfields) + "instant=?",
					filter.getInstant()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(HumoCardsFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM humo_cards ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//ConnectionPool.close(c);
		}
		return n;

	}


	
	public static Calendar getCurrBankDate(String bankId,Connection c) {

		Date currDate = null;
		
		

		try {
			if(calendar==null){
		    calendar=Calendar.getInstance();
		    psCurrentDate = c.prepareStatement("SELECT * FROM branch WHERE bank_id=?");
			psCurrentDate.setString(1, bankId);
			ResultSet rs = psCurrentDate.executeQuery();
			if (rs.next()) {
				
				currDate=rs.getDate("curr_date");
				calendar.setTime(currDate);
				System.out.println("curr_date="+calendar);
			}
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return calendar;
	}
	
	public HumoCards getHumoCards(int humocardsId) {

		HumoCards humocards = new HumoCards();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM TF_humocards WHERE id=?");
			ps.setInt(1, humocardsId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				humocards = new HumoCards();

				humocards.setClient(rs.getString("client"));
				humocards.setClient_b(rs.getString("client_b"));
				humocards.setBranch(rs.getString("branch"));
				humocards.setCard(rs.getString("card"));
				humocards.setStatus1(rs.getString("status1"));
				humocards.setStatus2(rs.getString("status2"));
				humocards.setExpiry1(rs.getDate("expiry1"));
				humocards.setExpirity2(rs.getDate("expirity2"));
				humocards.setRenew(rs.getString("renew"));
				humocards.setRenew_date(rs.getDate("renew_date"));
				humocards.setCard_name(rs.getString("card_name"));
				humocards.setMc_name(rs.getString("mc_name"));
				humocards.setM_name(rs.getString("m_name"));
				humocards.setStop_cause(rs.getString("stop_cause"));
				humocards.setRenewed_card(rs.getString("renewed_card"));
				humocards.setDesign_id(rs.getInt("design_id"));
				humocards.setInstant(rs.getString("instant"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return humocards;
	}

	public static HumoCards create(HumoCards humocards) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			/*
			 * ps =
			 * c.prepareStatement("SELECT SQ_TF_humocards.NEXTVAL id FROM DUAL"
			 * ); ResultSet rs = ps.executeQuery(); if (rs.next()) {
			 * humocards.setL(rs.getLong("id")); } ps = c.prepareStatement(
			 * "INSERT INTO TF_humocards (client, client_b, branch, card, status1, status2, expiry1, expirity2, renew, renew_date, card_name, mc_name, m_name, stop_cause, renewed_card, design_id, instant, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)"
			 * );
			 */

			ps.setString(1, humocards.getClient());
			ps.setString(2, humocards.getClient_b());
			ps.setString(3, humocards.getBranch());
			ps.setString(4, humocards.getCard());
			ps.setString(5, humocards.getStatus1());
			ps.setString(6, humocards.getStatus2());
			ps.setDate(7, new java.sql.Date(humocards.getExpiry1().getTime()));
			ps.setDate(8, new java.sql.Date(humocards.getExpirity2().getTime()));
			ps.setString(9, humocards.getRenew());
			ps.setDate(10, new java.sql.Date(humocards.getRenew_date()
					.getTime()));
			ps.setString(11, humocards.getCard_name());
			ps.setString(12, humocards.getMc_name());
			ps.setString(13, humocards.getM_name());
			ps.setString(14, humocards.getStop_cause());
			ps.setString(15, humocards.getRenewed_card());
			ps.setInt(16, humocards.getDesign_id());
			ps.setString(17, humocards.getInstant());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			//ConnectionPool.close(c);
		}
		return humocards;
	}

	
	
	private static globus.IssuingWS.IssuingPortProxy initNp(String alias)
			throws ClientProtocolException, IOException,
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
				ConnectionPool.getValue("HUMO_HOST", alias),
				ConnectionPool.getValue("HUMO_USERNAME", alias),
				ConnectionPool.getValue("HUMO_PASSWORD", alias));
	}

	public static Res Get_acc_hole(String acc_bal, String first, String last,
			String client_id, String branch, String alias) {
		Res res = new Res();
		res.setCode(0);
		Connection c;
		try {
			c = ConnectionPool.getConnection(alias);

			// PreparedStatement ps =
			// c.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from tietoAccount t "
			// +
			// "where " +
			// "t.client = ? " +
			// "and t.branch = ? " +
			// "and t.acc_bal = ? " +
			// "and TO_NUMBER(t.id_order) >= TO_NUMBER(?) " +
			// "and TO_NUMBER(t.id_order) < TO_NUMBER(?)" +
			// "and t.currency = '000'");
			// ps.setString(1, client_id);
			// ps.setString(2, branch);
			// ps.setString(3, acc_bal);
			// ps.setString(4, first);
			// ps.setString(5, last);

			PreparedStatement ps = c
					.prepareStatement("select LPAD(TO_CHAR(MAX(TO_NUMBER(t.id_order))+1),3,'0')res, MAX(TO_NUMBER(t.id_order))+1 val from account t "
							+ "where "
							+ "t.client = ? "
							+ "and t.branch = ? "
							+ "and t.acc_bal = ? "
							+ "and t.currency = '000'"
							+ "and t.id_order >= '100'"
							+ "and t.id_order <= '300'");
			ps.setString(1, client_id);
			ps.setString(2, branch);
			ps.setString(3, acc_bal);

			ResultSet rs = ps.executeQuery();
			res.setName(first);
			if (rs.next()) {
				if ((rs.getString("res") != null)
						&& (rs.getString("res").compareTo("") != 0))
					res.setName(rs.getString("res"));
			}

		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		}

		return res;
	}

	public static Res openAcc(String ord, int group, String alias, String un,
			String pw, String branch, Customer customer, String accType) {
		Res res = new Res();

		try {
			String ccycd = "000";
			// String cl_id = tietocl.getClient_b();
			String cl_id = customer.getId_client();

			// Customer cst =
			// CustomerService.getCustomerById(tietocl.getClient_b(), branch,
			// alias);
			// Customer cst = CustomerService.getCustomerById(cl_id, branch,
			// alias);

//			String customerName = customer.getName().length() > 80 ? customer
//					.getName().substring(0, 79) : customer.getName();
					
					String customerName = (customer.getName()+" HUMO").length() > 80 ?(customer.getName()+" HUMO").substring(0, 79) : (customer.getName()+" HUMO");

			String ord_1 = Get_acc_hole(accType, ord, null,
					customer.getId_client(), branch, alias).getName();
			// alert("Открывается счет " + ord);
			// if(ord_1 != null) {
			// if(!ord_1.substring(0, 1).equals("7")) {
			// ord_1 = "700";
			// }
			// }

			// String customer_id = cst.getId_client();

			boolean brcomp = (branch.compareTo(ConnectionPool.getValue("HO",
					alias)) == 0);

			res = GlobuzAccountService.doAction_create_acc_in_br(un, pw,
					accType, customer.getId_client(), ccycd, ord,
					customerName, group, alias, branch, brcomp);

			if (res.getCode() != 0) {
				// alert("ОШИБКА\nОткрытие счета 22618 :\n" + res.getName());
				return res;
			} else {
				String result = res.getName();
				// res.setName("Открыт счет " + result);
			}

		} catch (Exception e) {
			res.setName("ERROR => " + e.getLocalizedMessage());
			res.setCode(0);
			return res;
		}
		return res;
	}

	// public static TietoCardSetting getTietoCardSetting(int
	// tietocardsettingId,
	// String alias) {
	//
	// TietoCardSetting tietocardsetting = new TietoCardSetting();
	// Connection c = null;
	//
	// try {
	// c = ConnectionPool.getConnection(alias);
	// PreparedStatement ps = c
	// .prepareStatement("SELECT * FROM BF_globuz_CARD_SETTING WHERE code=?");
	// ps.setInt(1, tietocardsettingId);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// tietocardsetting = new TietoCardSetting();
	//
	// tietocardsetting.setCode(rs.getInt("code"));
	// tietocardsetting.setName(rs.getString("name"));
	// tietocardsetting.setBin(rs.getString("bin"));
	// tietocardsetting.setRisk_level(rs.getString("risk_level"));
	// tietocardsetting.setFinancial_conditions(rs
	// .getString("financial_conditions"));
	// tietocardsetting.setMinimum_balance(rs
	// .getBigDecimal("minimum_balance"));
	// tietocardsetting
	// .setId_chip_app(rs.getBigDecimal("id_chip_app"));
	// tietocardsetting.setId_order_account(rs
	// .getString("id_order_account"));
	// tietocardsetting.setGroup_c(rs.getString("groupc"));
	// tietocardsetting.setBank_c(rs.getString("bank_c"));
	// tietocardsetting.setCard_condition(rs
	// .getString("card_condition"));
	// tietocardsetting.setDesign_id(rs.getBigDecimal("design_id"));
	// // tietocardsetting.setAcc_name_postfix(rs.getString("acc_postfix"));
	// // tietocardsetting.setHo_acc_group(rs.getInt("acc_group_head"));
	// // tietocardsetting.setBr_acc_group(rs.getInt("acc_group_fil"));
	// // tietocardsetting.setId_order_max(rs.getString("id_order_max"));
	// //
	// tietocardsetting.setAllow_multiple_card_per_acc(rs.getInt("allow_multiple_card_per_acc"));
	// //
	// tietocardsetting.setAcc_name_postfix(acc_name_postfix)(rs.getInt("acc_group_fil"));
	// }
	// } catch (Exception e) {
	// ISLogger.getLogger().error(CheckNull.getPstr(e));
	// LtLogger.getLogger().error(CheckNull.getPstr(e));
	// e.printStackTrace();
	// } finally {
	// ConnectionPool.close(c);
	// }
	// return tietocardsetting;
	// }

	public static HumoCardSetting getHumoCardSetting(String code, String alias,Connection c) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		//Connection c = null;

		try {
			//c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SS_HUMO_CARD_TYPE WHERE code=?");
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				humocardsetting = new HumoCardSetting();

				humocardsetting.setCode(rs.getString("code"));
				humocardsetting.setName(rs.getString("name"));
				humocardsetting.setCode_b(rs.getString("code_b"));
				humocardsetting.setCode_k(rs.getString("code_k"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return humocardsetting;
	}

	public static String createAccountHumo(String alias, Customer custemer,
			String un, String pw, String branch, String accType,String acc_num,String acc_cur) {

		Res res = new Res();
		// HumoCardSetting tcs = getHumoCardSetting(cardCode,alias);
		// String ord = tcs.getId_order_account();
		//String acc_num = ConnectionPool.getValue("HUMO_ID_ORDER_ACCOUNT", alias);
			
		res = openAcc(acc_num, 0, alias, un, pw, branch, custemer, accType);
		GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		acfilter.setClient(custemer.getId_client());
		acfilter.setAcc_bal(accType);
		acfilter.setBranch(custemer.getBranch());
		acfilter.setCurrency(acc_cur);
		System.out.println(" sozdan account dlya Humo");

		return res.getName();
	}

	public static RowType_Customer getRowType_Customer(Customer customer,String personCode, String cardType,String cl_type,Connection c) {

		Calendar calendar = Calendar.getInstance();
		
		RowType_Customer rtc = new RowType_Customer();
		
		
		if(customer.getP_first_name()==null){rtc.setF_NAMES("");}
		
		else if(customer.getP_first_name().length()>34) {
			
			//rtc.setF_NAMES(toTranslit(customer.getP_first_name()).substring(0,34));
			
			String ferst_name=toTranslit(customer.getP_first_name());
			
			ferst_name=ferst_name.substring(0,34);
			rtc.setF_NAMES("F_NAMES:"+ferst_name);
			
		System.out.println(ferst_name);
		}
		
		else{
			
			rtc.setF_NAMES(toTranslit(customer.getP_first_name()));
			
		}
		
		System.out.println("********************");
		//System.out.println("P_first_name:"+toTranslit(customer.getP_first_name()));
		rtc.setCL_TYPE(cl_type);
		rtc.setCLIENT_B(customer.getBranch()+customer.getId_client());

		String family=customer.getP_family();
		if(family==null){
			
			rtc.setSURNAME("");	
			
		}
		
		else{
			if(family.length()>36){
				
				rtc.setSURNAME(toTranslit(customer.getP_family().substring(0,36)));
			}
			
			else {
				rtc.setSURNAME(toTranslit(customer.getP_family()));
				
			}
			
		}
		//rtc.setSURNAME(customer.getP_family()==null ? "":toTranslit(customer.getP_family().substring(0,36)));
		
		System.out.println("********************");
		//System.out.println("P_family:"+toTranslit(customer.getP_family());
		
		Calendar calendarDocSince = Calendar.getInstance();
		
		
		if(customer.getP_passport_date_registration()!=null){
			
			calendarDocSince.setTime(customer.getP_passport_date_registration());
			rtc.setDOC_SINCE(calendarDocSince);
		
		}
		else{rtc.setDOC_SINCE(null);}
		
		
		Calendar calendarB_data = Calendar.getInstance();
		
		if(customer.getP_birthday()!=null){
			calendarB_data.setTime(customer.getP_birthday());
			rtc.setB_DATE(calendarB_data);
					
		}
		else{rtc.setB_DATE(null);}
		
		//rtc.setRESIDENT(customer.getCode_resident()==null?"1":customer.getCode_resident());
		
		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX(customer.getP_code_gender());
		rtc.setSERIAL_NO(customer.getP_passport_serial());
		rtc.setID_CARD(customer.getP_passport_number());
		rtc.setR_CITY(customer.getP_code_citizenship());
		rtc.setR_STREET(customer.getP_birth_place()==null ? "STREET" : customer.getP_birth_place());
		
		
		rtc.setR_E_MAILS(customer.getP_email_address());
		rtc.setR_MOB_PHONE(customer.getP_phone_mobile());
		rtc.setR_PHONE("");
		rtc.setR_CNTRY("UZB");
		rtc.setISSUED_BY(customer.getP_passport_place_registration()==null?"":toTranslit(customer.getP_passport_place_registration()));
		
		System.out.println("********************");
		//System.out.println("ISSUED_BY:"+toTranslit(customer.getP_passport_place_registration()));
				
		
		rtc.setPERSON_CODE(personCode);
		rtc.setDOC_TYPE("001");
		calendar.setTime(new Date());
		rtc.setREC_DATE(getCurrBankDate(customer.getBranch(),c));
		rtc.setCLN_CAT("006");
		System.out.println(rtc);
		
		return rtc;
	}

	public static RowType_AccountInfo_Base getRowType_AccountInfo_Base(
			String acc,String branch,Connection c) {

		Calendar calendar = Calendar.getInstance();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		base_info.setCREATED_DATE(getCurrBankDate(branch,c));
		base_info.setCCY("UZS");
		base_info.setCRD(BigDecimal.valueOf(0));
		base_info.setMIN_BAL(BigDecimal.valueOf(0));
		base_info.setC_ACCNT_TYPE("00");
		base_info.setNON_REDUCE_BAL(BigDecimal.valueOf(0));
		base_info.setSTATUS("0");
		base_info.setCOND_SET("001");
		base_info.setCYCLE("001");
		base_info.setSTAT_CHANGE("1");
		base_info.setCARD_ACCT(acc);
		
		System.out.println(base_info);
		return base_info;
	}
	
	public static RowType_AccountInfo_Base getRenewRowType_AccountInfo_Base(
			String id_client,String branch,String cardType,Connection c) {

		Calendar calendar = Calendar.getInstance();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		
		
		
		base_info.setACCOUNT_NO(BigDecimal.valueOf(getAccountNO(id_client,branch,cardType,c)));
		
		
		return base_info;
	}

	public static RowType_CardInfo_LogicalCard getRowType_CardInfo_LogicalCard(
			Customer customer,Long HUMO_RANGEID) {

		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCOND_SET("001");
		logicalCard.setRISK_LEVEL("A");
		logicalCard.setBASE_SUPP("1");
		
		if(customer.getP_first_name()==null){logicalCard.setF_NAMES("");}
		else if(customer.getP_first_name().length()>34){
			
			String first_name=toTranslit(customer.getP_first_name());
			
			first_name=first_name.substring(0,34);
			
			System.out.println("first_name2:"+first_name);
			
			logicalCard.setF_NAMES(first_name);
			
		}
		
		else{
			
			logicalCard.setF_NAMES(toTranslit(customer.getP_first_name()));
		}
		
		//logicalCard.setF_NAMES(customer.getP_first_name()==null? "":toTranslit(customer.getP_first_name()));
		
		logicalCard.setSURNAME(customer.getP_family()==null?"":toTranslit(customer.getP_family()));
		logicalCard.setMIDLE_NAME(customer.getP_patronymic()==null?"":toTranslit(customer.getP_patronymic()));
		logicalCard.setRANGE_ID(BigDecimal.valueOf(HUMO_RANGEID));
		

		return logicalCard;
	}

	public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCard(
			Customer customer, String cardFirstName) {

		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
//		physicalCard.setCARD_NAME(customer.getP_family() + " "
//				+ customer.getP_first_name());
		
		
	
		if(cardFirstName!=null){
							
			cardFirstName=toTranslit(cardFirstName);
			cardFirstName.replace("XXX", "");
			cardFirstName=cardFirstName.replace("KHKHKH", "");
		
			cardFirstName=cardFirstName;
	
		
			cardFirstName=cardFirstName.trim();
	if(cardFirstName.length()>24){
			
		cardFirstName=cardFirstName.substring(0,24);
		
		}
		}
	
		System.out.println("********CARD_NAME: "+cardFirstName);
	physicalCard.setCARD_NAME(cardFirstName);
		
	
		
	System.out.println("customer.getP_family():"+customer.getP_family());
	System.out.println("customer.getP_first_name():"+customer.getP_first_name());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

		return physicalCard;

	}
	
	public static RowType_Agreement getRowType_Agreement(String HUMO_BINCOD,
		String HUMO_BANK_C, String branchId, String cardCode, Customer customer ) {
		RowType_Agreement agrInfo = new RowType_Agreement();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		
		agrInfo.setBINCOD(HUMO_BINCOD);
		agrInfo.setBRANCH(branchId);
		agrInfo.setBANK_CODE(HUMO_BANK_C);
		agrInfo.setSTREET(customer.getR_STREET() == null ? "STREET" : customer
				.getR_STREET());
		agrInfo.setCITY(customer.getR_CITY() == null ? "UZB" : customer
				.getR_CITY());
		agrInfo.setCOUNTRY(customer.getR_CNTRY() == null ? "UZB" : customer
				.getR_CNTRY());
		
		
		
//		agrInfo.setSTREET(customer.getR_STREET() == null ? "STREET" : customer
//				.getR_STREET());
//		agrInfo.setCITY(customer.getR_CITY() == null ? "UZB" : customer
//				.getR_CITY());
//		agrInfo.setCOUNTRY(customer.getR_CNTRY() == null ? "UZB" : customer
//				.getR_CNTRY());
				
		
				
		agrInfo.setREP_LANG("1");
		agrInfo.setSTATUS("10");
		agrInfo.setPRODUCT("01");
		agrInfo.setENROLLED(calendar);
		// agrInfo.setCLIENT(customerInfo.value.getCLIENT());

		
		return agrInfo;
	}
	
	public static RowType_Agreement getNewRowType_Agreement(String HUMO_BINCOD,
			String HUMO_BANK_C, String branchId, String cardCode, Customer customer, String alias,String idClientHumo) {
		
				System.out.println("******customer:"+customer);	
		RowType_Agreement agrInfo = new RowType_Agreement();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			
			agrInfo.setCLIENT(idClientHumo);
			
			
			System.out.println("idClientHumo78787: "+idClientHumo);
			
			
			agrInfo.setBINCOD(HUMO_BINCOD);
			agrInfo.setBRANCH(branchId);
			agrInfo.setBANK_CODE(HUMO_BANK_C);
			agrInfo.setSTREET(customer.getR_STREET() == null ? "STREET" : customer
					.getR_STREET());
			agrInfo.setCITY(customer.getR_CITY() == null ? "UZB" : customer
					.getR_CITY());
			agrInfo.setCOUNTRY(customer.getR_CNTRY() == null ? "UZB" : customer
					.getR_CNTRY());
		
					
			
					
			agrInfo.setREP_LANG("1");
			agrInfo.setSTATUS("10");
			agrInfo.setPRODUCT("01");
			agrInfo.setENROLLED(calendar);
			// agrInfo.setCLIENT(customerInfo.value.getCLIENT());

			
			return agrInfo;
		}
	
	
	public static SS_HUMO_TYPE_OF_CARD getCardType(String vidCard, String branch,Connection c){
		
		SS_HUMO_TYPE_OF_CARD humoCardType=new SS_HUMO_TYPE_OF_CARD();
		
		//Connection c = null;
		ResultSet rs = null;

		try {
						
			//c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM SS_HUMO_type_of_card where branch=? and code=?");
			ps.setString(1, branch);
			ps.setString(2, vidCard);
		
			rs = ps.executeQuery();
			while (rs.next()) {
				
				humoCardType.setBank_c(rs.getString("bank_c"));
				humoCardType.setGroup_c(rs.getString("group_c"));
				humoCardType.setBin(rs.getString("bin"));
				humoCardType.setBranch(rs.getString("branch"));
				humoCardType.setChip_app_id(rs.getLong("Chip_app_id"));
				humoCardType.setBranch_id(rs.getString("branch_id"));
				humoCardType.setRange_id(rs.getLong("range_id"));
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}

		return humoCardType;
	}

	public static ResponsInfo createCardsHumo(Connection c,String alias,
			String branch, String clientId, String cardCode,String personCode,String vidCard,String cardName) throws Exception {
		Customer customer=null;
		String cl_type;
		
		if(cardCode.equals("002") || cardCode.equals("007") || cardCode.equals("008") ){
			
			cl_type="2";
			
			
			 customer =  HumoCardsService.getCustomerJ(c, branch,
						clientId);	
			 
			 System.out.println("getCustomer:"+customer);
			 
			 System.out.println("clientId:"+clientId);
			 
		}
		
		else if (cardCode.equals("002")){
		
			customer = HumoCardsService.getCustomer(c, branch,
					clientId);
			cl_type="1";
			System.out.println("getCustomer:"+customer);
	}
		
		
		else {
			
			ISLogger.getLogger().error("Не правилный код клиента");
			cl_type="0";
		}
		
		ResponsInfo responsInfo=new ResponsInfo();

		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();

		
		
		
//		String HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C", alias);
//		String HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC", alias);
//		String HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD", alias);
		
		SS_HUMO_TYPE_OF_CARD cardType=getCardType(vidCard, branch,c);
		
		System.out.println("vidCard:"+vidCard+" branch:"+branch);
		
		System.out.println("cardType:"+cardType.toString());
		
		 HUMO_BANK_C = cardType.getBank_c();
		 HUMO_GROUPC = cardType.getGroup_c();
		 HUMO_BINCOD = cardType.getBin();
		 //HUMO_BINCOD = "90000301";
		
		 HUMO_CHIPAPPID= cardType.getChip_app_id();
		 
		 System.out.println("HUMO_CHIPAPPID:"+HUMO_CHIPAPPID);
		 
		 HUMO_BRANCHID = cardType.getBranch_id();
		 
		 
		 
		 HUMO_RANGEID = cardType.getRange_id();
		 
		 
		 
		
		System.out.println("customer:"+customer);
		
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(
				getRowType_Customer(customer,personCode,cardCode,cl_type,c));
		
		String acc=null;
		
//		Map<String, String> hashMap=getAccInfo(clientId, branch);
//		
//		String exsist=isExsistAcc2(branch, hashMap.get("acc_bal"), hashMap.get("acc_cur"), clientId, hashMap.get("acc_num"));
//		
//		if(exsist==null){
//			
//			acc= createAccountHumo(alias, customer, un, pw, branch, hashMap.get("acc_bal"),hashMap.get("acc_num"),hashMap.get("acc_cur"));
//			
//		}
//		
//		else{
//			acc=exsist;
//		}
	
				 acc = getAcc(c,cardName.trim(),clientId, branch);
		
		//String acc = "14301000000000000001"; //optashash 
				 System.out.println("--------------");
		System.out.println("acc"+acc);
		 System.out.println("------------------");
		
				
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();
		
		//String acc =""; //optashash
		accountInfo.setBase_info(getRowType_AccountInfo_Base(acc,customer.getBranch(),c));
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		accountsListInfo.value = ltaccounts;
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(HUMO_CHIPAPPID));
		
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
		cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCard(customer,cardName));
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;

		try {

			globus.IssuingWS.IssuingPortProxy issuingPortProxy = initNp(alias);

			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();

			connectionInfo.setBANK_C(HUMO_BANK_C);
			connectionInfo.setGROUPC(HUMO_GROUPC);
			
			String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
					getRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,cardCode,customer));
			// OperationResponseInfo orInfo2 = issuingPortProxy.newAgreement(
			// connectionInfo, agreementInfo, accountsListInfo,
			// cardsListInfo);

			////////////////////////////////// Запрос в Humo //////////////////////////////////////////

			OperationResponseInfo orInfo2=null;
			if(acc!=null || acc!=""){
									
					 orInfo2 = issuingPortProxy
						.newCustomerAndAgreement(connectionInfo, customerInfo,
								customListInfo, agreementInfo, accountsListInfo,
								cardsListInfo);	
					 
					 responsInfo.setSuccessful(true);
					 
					 insertRangAndBranchId(c, clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardName.trim());
			
			}

			else{
				
				responsInfo.setResponseCode(BigInteger.valueOf(1));
				responsInfo.setErrorDescription("Карта HUMO не открыта. Причина: Ошибка при открытии нового счета");
				responsInfo.setErrorAction("Смотрите в лог");
				responsInfo.setSuccessful(false);
				
				ISLogger.getLogger().error("Карта HUMO не открыта. Причина: Ошибка при открытии нового счета");
				System.out.println("Карта HUMO не открыта. Причина: Ошибка при открытии нового счета");
				
			}
			
			// ////////////////////////////////////////////////////////////////////////////////////////////////

			if (orInfo2.getError_description() != null) {
				
				responsInfo.setResponseCode(orInfo2.getResponse_code());
				responsInfo.setErrorDescription(orInfo2.getError_description());
				responsInfo.setErrorAction(orInfo2.getError_action());
				responsInfo.setSuccessful(false);
				
				// res.setCode(0);
				// res.setName(orInfo2.getError_description());
				System.out.println("Response Info output:");
				System.out.println("-------------------------------");
				System.out.println("Response code = "
						+ orInfo2.getResponse_code());
				System.out.println("Error description = "
						+ orInfo2.getError_description());
				System.out.println("Error action = "
						+ orInfo2.getError_action());
				System.out.println("-------------------------------");

				ISLogger.getLogger().error(
						"\n\n Response code =" + orInfo2.getResponse_code()
								+ "\n\n");
				ISLogger.getLogger().error(
						"\n Error description = "
								+ orInfo2.getError_description() + "\n");

			} 
			
			if(responsInfo.getErrorDescription()==null){
				
				insertNewClient(getRowType_Customer(customer,personCode,cardCode,cl_type,c), customerInfo.value.getCLIENT(),c);
				insertNewClientAgreement(connectionInfo, agreementInfo,c);
				insertNewClientAccounts(accountsListInfo, cardsListInfo, acc,c);
				insertNewClientHumoCards(cardsListInfo, clientId, customer.getBranch(),alias,acc,c);
//							
				
				responsInfo.setSuccessful(true);
				
				System.out.println("Карта открыта в HUMO");
				
				
			}
		
		}
		
		catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		
		
		}
		return responsInfo;
	}
	
	public static void insertRangAndBranchId(Connection c,String clientId,String branch,String branchId,String rangeId,String cardType){
		
	
		int res=0;
		PreparedStatement ps = null;
		try {
	
			ps = c.prepareStatement("update HUMO_CARD_OPEN set range_id=?, branch_id=?  where client=? and branch=? and card_type=?");

			ps.setString(1, rangeId);
			ps.setString(2,branchId);
			ps.setString(3,clientId);
			ps.setString(4,branch);
			ps.setString(5,cardType);
	//	
	        
			res=ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
//				if (ps != null)
//					ps.close();
			} catch (Exception e) {
			}
			//ConnectionPool.close(c);
		}
		
		
		
		
	}
	
	public static int insertAccounts(
			ListType_AccountInfoHolder accountsListInfo,
			ListType_CardInfoHolder cardsListInfo, String clientIdHumo,String acc,Connection c) {
		//Connection c = null;
		int res=0;
		PreparedStatement ps = null;
		try {
			//c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?,?)");

			//System.out.println("accountsListInfo7777 " + accountsListInfo);
			System.out.println("Humo client="
					+ cardsListInfo.value.getRow(0).getLogicalCard()
							.getCLIENT());

//			ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
//					.getCLIENT()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
//							.getCLIENT());
			ps.setString(1, clientIdHumo);
			
			ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info()
					.getACCOUNT_NO()== null ? null : accountsListInfo.value.getRow(0).getBase_info()
							.getACCOUNT_NO());
//			ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
//					.getCARD_ACCT()== null ? "" : accountsListInfo.value.getRow(0).getBase_info()
//							.getCARD_ACCT());
			
			
			ps.setString(3, acc== null ? "" : acc);
			
			
			ps.setString(4, acc== null ? "" : acc);

			ps.setDate(5, accountsListInfo.value.getRow(0)
					.getBase_info().getAB_EXPIRITY()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
							.getBase_info().getAB_EXPIRITY().getTimeInMillis()));
			ps.setDate(6, accountsListInfo.value.getRow(0)
					.getBase_info().getCREATED_DATE()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
							.getBase_info().getCREATED_DATE().getTimeInMillis()));
            
			res=ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
//				if (ps != null)
//					ps.close();
			} catch (Exception e) {
			}
			//ConnectionPool.close(c);
		}
		
		return res;
	}

	public static int insertHumoCards(ListType_CardInfoHolder cardsListInfo,
			String clientId, String branch,String clientHumoId, String cardName, String alias,String acc,Connection c) {
		int res = 0;
		//Connection c = null;
		PreparedStatement ps = null;
		try {
			//c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
					+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

//			ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
//					.getCLIENT()== null ? "0" : cardsListInfo.value.getRow(0).getLogicalCard()
//							.getCLIENT());
			ps.setString(1, clientHumoId);	
			
			String psivdaPAN=cardsListInfo.value.getRow(0).getLogicalCard()
			.getCARD()== null ? "0" : cardsListInfo.value.getRow(0).getLogicalCard()
					.getCARD();
			ps.setString(2, clientId== null ? "0" : clientId);
			ps.setString(3,psivdaPAN); 
			ps.setString(4, branch== null ? "0" : branch);
			ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS1()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTATUS1());
			ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS2()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTATUS2());
			ps.setDate(7,cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
							.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
			ps.setDate(8, cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
					.getPhysicalCard().getEXPIRITY2().getTimeInMillis()));
			
			
			
			
			ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEW()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getRENEW());
//			ps.setString(10, cardsListInfo.value.getRow(0).getPhysicalCard()
//					.getCARD_NAME()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
//							.getCARD_NAME());
			cardName=cardName.trim();
			if(cardName.length()>24){
					
				cardName=cardName.substring(0,24);
				
				}
			
			
			ps.setString(10, cardName);
//			ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard()
//					.getMC_NAME()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
//							.getMC_NAME());
//			ps.setString(12, cardsListInfo.value.getRow(0).getLogicalCard()
//					.getM_NAME()== null ? "0" : cardsListInfo.value.getRow(0).getLogicalCard()
//							.getM_NAME());
			
			ps.setString(11, "");
	ps.setString(12, "");
			
			
			
			ps.setString(13, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTOP_CAUSE()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTOP_CAUSE());
			ps.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEWED_CARD()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getRENEWED_CARD());
			ps.setBigDecimal(15, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getDESIGN_ID()== null ? null : cardsListInfo.value.getRow(0)
							.getPhysicalCard().getDESIGN_ID());
			ps.setString(16, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getINSTANT()== null ? "0" : cardsListInfo.value.getRow(0).getPhysicalCard()
							.getINSTANT());
			
			ps.setString(17,acc);
			ps.setString(18, acc);
			
			//ps.setString(17, getRealCardNumber(psivdaPAN, initNp(alias), HUMO_BANK_C, HUMO_GROUPC));

			res = ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
//				if (ps != null)
//					ps.close();
			} catch (Exception e) {
			}
			//ConnectionPool.close(c);
		}
		return res;
	}

	public static int insertAgreement(OperationConnectionInfo connectionInfo,RowType_AgreementHolder agreementInfo,
			String clintIdHumo,BigDecimal agrNom,Connection c) {
		//Connection c = null;
		int res=0;
		PreparedStatement ps = null;
			
		try {
			
			
			//c = ConnectionPool.getConnection();
			//System.out.println("agreementInfo:"+agreementInfo);
			ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
			//ps.setString(1, agreementInfo.value.getAGRE_NOM()== null ? "0" : agreementInfo.value.getAGRE_NOM().toString());
			
			System.out.println("agrNom: "+agrNom.toString());
			ps.setString(1, agrNom.toString());
			
			
			//ps.setString(2, agreementInfo.value.getCLIENT()== null ? "0" : agreementInfo.value.getCLIENT());
			ps.setString(2, clintIdHumo);
			System.out.println("9999cliintIdHumo: "+clintIdHumo);
			ps.setString(3, connectionInfo.getGROUPC()== null ? "" : connectionInfo.getGROUPC());
			ps.setString(4, agreementInfo.value.getBINCOD()== null ? "0" : agreementInfo.value.getBINCOD());
			ps.setString(5, agreementInfo.value.getBANK_CODE()== null ? "0" : agreementInfo.value.getBANK_CODE());
			ps.setString(6, agreementInfo.value.getBRANCH()== null ? "0" : agreementInfo.value.getBRANCH());
			ps.setString(7, connectionInfo.getBANK_C()== null ? "0" : connectionInfo.getBANK_C());
			ps.setString(8, agreementInfo.value.getPRODUCT()== null ? "0" : agreementInfo.value.getPRODUCT());
			res=ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (c != null)
					c.rollback();
			} catch (Exception ex) {
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			try {
//				if (ps != null)
//					ps.close();
			} catch (Exception e) {
			}
			//ConnectionPool.close(c);
		}
		
		return res;
	}

	public static int insertClient(RowType_Customer customerInfo, String client,Connection c)
			throws Exception {
		//Connection c = null;
		int res=0;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			//c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
					+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
					+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, client== null ? "" : client);
			ps.setString(2, customerInfo.getF_NAMES()== null ? "0" : customerInfo.getF_NAMES());
			ps.setString(3, customerInfo.getCL_TYPE()== null ? "0" : customerInfo.getCL_TYPE());
			ps.setString(4, customerInfo.getCLIENT_B()== null ? "0" : customerInfo.getCLIENT_B());
			ps.setString(5, customerInfo.getSURNAME()== null ? "0" : customerInfo.getSURNAME());
			//ps.setString(6, customerInfo.getM_NAME()== null ? "0" : customerInfo.getM_NAME());
			ps.setString(6, "0");
			
			ps.setDate(7, customerInfo.getDOC_SINCE()== null ? null : new java.sql.Date(customerInfo.getDOC_SINCE()
							.getTimeInMillis()));
			ps.setDate(8, customerInfo.getB_DATE()== null ? null : new java.sql.Date(customerInfo.getB_DATE()
							.getTimeInMillis()));
			ps.setString(9, customerInfo.getRESIDENT()== null ? "0" : customerInfo.getRESIDENT());
			ps.setString(10, customerInfo.getSTATUS()== null ? "0" : customerInfo.getSTATUS());
			ps.setString(11, customerInfo.getSEX()== null ? "0" : customerInfo.getSEX());
			ps.setString(12, customerInfo.getSERIAL_NO()== null ? "0" : customerInfo.getSERIAL_NO());
			ps.setString(13, customerInfo.getID_CARD()== null ? "0" : customerInfo.getID_CARD());
			ps.setString(14, customerInfo.getR_CITY()== null ? "0" : customerInfo.getR_CITY());
			ps.setString(15, customerInfo.getR_STREET()== null ? "0" : toTranslit(customerInfo.getR_STREET()));
			ps.setString(16, customerInfo.getR_E_MAILS()== null ? "0" : customerInfo.getR_E_MAILS());
			ps.setString(17, customerInfo.getR_MOB_PHONE()== null ? "0" : customerInfo.getR_MOB_PHONE());
			ps.setString(18, customerInfo.getR_PHONE()== null ? "0" : customerInfo.getR_PHONE());
			ps.setString(19, customerInfo.getR_CNTRY()== null ? "0" : customerInfo.getR_CNTRY());
			ps.setString(20, customerInfo.getISSUED_BY()== null ? "0" : customerInfo.getISSUED_BY());
			ps.setString(21, customerInfo.getPERSON_CODE()== null ? "0" : customerInfo.getPERSON_CODE());
			ps.setString(22, customerInfo.getDOC_TYPE()== null ? "0" : customerInfo.getDOC_TYPE());
			// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
			// ? null
			// : new java.sql.Date(customerInfo.value
			// .getREC_DATE().getTime().getTime()));
			ps.setDate(23, customerInfo.getREC_DATE()== null ? null : new java.sql.Date(customerInfo.getREC_DATE()
							.getTimeInMillis()));

			res=ps.executeUpdate();
			c.commit();
			bool = true;
						
		} finally {
			if (!bool)
				try {
					if (c != null)
						c.rollback();
				} catch (Exception ex) {
				}
			try {
//				if (ps != null)
//					ps.close();
			} catch (Exception e) {
			}
			//ConnectionPool.close(c);
		}
		
		return res;
	}

	
	
	public static Customer getCustomerJ(Connection c,
			String branch, String itemId) {
		Customer customer =null;
	
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			
			//cs = c.prepareCall("{call info.init()}");
			//cs.execute();
			ps = c.prepareStatement("SELECT t.*, Transymbol2(t.short_name) as short_name2, substr(Transymbol2(t.director_name),1,34) as director_name2  FROM v_client_humo t WHERE t.branch = ? and t.id_client=?");
			
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				
								
				customer = new Customer(rs.getLong("id"),
						rs.getString("branch"), 
						rs.getString("id_client"),
						rs.getString("short_name2"), 
						rs.getString("code_country"),
						rs.getString("code_type"),
						rs.getString("code_resident"),
						rs.getString("code_subject"),
						rs.getInt("sign_registr"), 
						rs.getString("code_form"),
						rs.getDate("open"), 
						rs.getDate("close"),
						rs.getInt("main_state"), 
						rs.getDate("DIRECTOR_BIRTHDAY"),
						rs.getString("DIRECTOR_ADDRESS"),
						rs.getString("DIRECTOR_TYPE_DOCUMENT"),
						rs.getString("DIRECTOR_PASSP_SERIAL"),
						rs.getString("DIRECTOR_PASSP_NUMBER"),
						rs.getString("DIRECTOR_PASSP_PLACE_REG"),
						rs.getDate("DIRECTOR_PASSP_DATE_REG"),
						
						
						
						rs.getString("code_tax_org"),
						rs.getString("number_tax_registration"),
						rs.getString("code_bank"),
						rs.getString("code_class_credit"),
						rs.getString("DIRECTOR_CODE_CITIZENSHIP"),
						rs.getString("DIRECTOR_BIRTH_PLACE"),
						null,//p_code_capacity
						null,//p_capacity_status_date
						null,//p_capacity_status_place
						null,//p_num_certif_capacity
						rs.getString("PHONE"),
						null,//p_phone_mobile
						rs.getString("email"),
						null,//p_pension_sertif_serial
						null,//p_code_gender
						null,//p_code_nation
						rs.getString("DIRECTOR_BIRTH_PLACE"),
						rs.getString("DIRECTOR_ADDRESS"),//p_code_birth_distr
						null,//p_type_document
						rs.getDate("DIRECTOR_PASSP_DATE_END"),
						rs.getString("DIRECTOR_ADDRESS"),
						rs.getString("DIRECTOR_ADDRESS"),//p_code_adr_distr
						null, //p_inps
						null,//p_family
						rs.getString("DIRECTOR_NAME2"),//p_first_name
						null);//p_patronymic
				
				
				
				
				
				
//				customer = new CustomerJ(
//						
//						 rs.getString("num"),
//                         rs.getString("mfo"),
//                         rs.getString("id_client"),
//                         rs.getString("name_cl"),
//                         rs.getString("code_country"),
//                         rs.getString("code_type"),
//                         rs.getString("code_resident"),
//                         rs.getString("code_subject"),
//                         rs.getString("sign_registr"),
//                         rs.getString("code_form"),
//                         rs.getString("open"),
//                         rs.getString("close"),
//                         rs.getString("main_state"),
//                         rs.getString("branch"),
//                         rs.getString("id"),
//                         rs.getString("name"),
//                         rs.getString("short_name"),
//                         rs.getDate("date_registration"),
//                         rs.getString("number_registration_doc"),
//                         rs.getString("code_tax_org"),
//                         rs.getString("number_tax_registration"),
//                         rs.getString("code_sector"),
//                         rs.getString("code_organ_direct"),
//                         rs.getString("code_head_organization"),
//                         rs.getString("code_class_credit"),
//                         rs.getString("director_name"),
//                         rs.getString("director_passport"),
//                         rs.getString("chief_accounter_name"),
//                         rs.getString("chief_accounter_passport"),
//                         rs.getString("code_bank"),
//                         rs.getString("account"),
//                         rs.getString("post_address"),
//                         rs.getString("phone"),
//                         rs.getString("fax"),
//                         rs.getString("email"),
//                         rs.getString("sign_trade"),
//                         rs.getString("state"),
//                         rs.getString("kod_err"),
//                         rs.getString("file_name"),
//                         rs.getString("place_regist_name"),
//                         rs.getString("opf"),
//                         rs.getString("soato"),
//                         rs.getString("okpo"),
//                         rs.getString("inn_head_organization"),
//                         rs.getString("region"),
//                         rs.getString("distr"),
//                         rs.getString("small_business"),
//                         rs.getString("patent_expiration"),
//                         rs.getString("director_type_document"),
//                         rs.getString("director_passp_serial"),
//                         rs.getString("director_passp_number"),
//                         rs.getDate("director_passp_date_reg"),
//                         rs.getString("director_passp_place_reg"),
//                         rs.getDate("director_passp_date_end"),
//                         rs.getString("accountant_type_document"),
//                         rs.getString("accountant_passp_serial"),
//                         rs.getString("accountant_passp_number"),
//                         rs.getDate("accountant_passp_date_reg"),
//                         rs.getString("accountant_passp_place_reg"),
//                         rs.getDate("accountant_passp_date_end"),
//                         rs.getString("responsible_emp"),
//                         rs.getString("director_code_citizenship"),
//                         rs.getString("director_birthday"),
//                         rs.getString("director_birth_place"),
//                         rs.getString("director_address"),
//                         rs.getString("accountant_code_citizenship"),
//                         rs.getString("accountant_birthday"),
//                         rs.getString("accountant_birth_place"),
//                         rs.getString("accountant_address"),
//                         rs.getString("sign_vip"),
//                         rs.getString("code_sector_old"),
//                         rs.getString("type_non_resident"),
//                         rs.getString("sign_dep_acc"),
//                         rs.getString("swift_id"),
//                         rs.getString("type_activity"),
//                         rs.getString("tieto_customer_id"));
						
						
						
						
						
//						
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
			//DbUtils.closeStmt(cs);
//			DbUtils.closeStmt(ps);
//			DbUtils.closeResultSet(rs);
		}

		//System.out.println("Familya " + customer.getP_family());
		return customer;
	}

	
	public static Customer getCustomer(Connection c,
			String branch, String itemId) {
		Customer customer = new Customer();
		
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		
		try {
			
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("SELECT t.*, Transymbol2(t.name) as name2 FROM v_bfcustomer t WHERE t.branch = ? and t.id_client=?");
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getLong("id"),
						rs.getString("branch"), 
						rs.getString("id_client"),
						rs.getString("name2"), 
						rs.getString("code_country"),
						rs.getString("code_type"),
						rs.getString("code_resident"),
						rs.getString("code_subject"),
						rs.getInt("sign_registr"), 
						rs.getString("code_form"),
						rs.getDate("date_open"), 
						rs.getDate("date_close"),
						rs.getInt("state"), 
						rs.getDate("p_birthday"),
						rs.getString("p_post_address"),
						rs.getString("p_passport_type"),
						rs.getString("p_passport_serial"),
						rs.getString("p_passport_number"),
						rs.getString("p_passport_place_registration"),
						rs.getDate("p_passport_date_registration"),
						rs.getString("p_code_tax_org"),
						rs.getString("p_number_tax_registration"),
						rs.getString("p_code_bank"),
						rs.getString("p_code_class_credit"),
						rs.getString("p_code_citizenship"),
						rs.getString("p_birth_place"),
						rs.getString("p_code_capacity"),
						rs.getDate("p_capacity_status_date"),
						rs.getString("p_capacity_status_place"),
						rs.getString("p_num_certif_capacity"),
						rs.getString("p_phone_home"),
						rs.getString("p_phone_mobile"),
						rs.getString("p_email_address"),
						rs.getString("p_pension_sertif_serial"),
						rs.getString("p_code_gender"),
						rs.getString("p_code_nation"),
						rs.getString("p_code_birth_region"),
						rs.getString("p_code_birth_distr"),
						rs.getString("p_type_document"),
						rs.getDate("p_passport_date_expiration"),
						rs.getString("p_code_adr_region"),
						rs.getString("p_code_adr_distr"),
						rs.getString("p_inps"), 
						rs.getString("p_family"),
						rs.getString("p_first_name"),
						rs.getString("p_patronymic"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
			//DbUtils.closeStmt(cs);
			//DbUtils.closeStmt(ps);
			//DbUtils.closeResultSet(rs);
		}

		System.out.println("Familya " + customer.getP_family());
		return customer;
	}

	public static void update(HumoCards humocards) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE TF_humocards SET client=?, client_b=?, branch=?, card=?, status1=?, status2=?, expiry1=?, expirity2=?, renew=?, renew_date=?, card_name=?, mc_name=?, m_name=?, stop_cause=?, renewed_card=?, design_id=?, instant=?,  WHERE id=?");

			ps.setString(1, humocards.getClient());
			ps.setString(2, humocards.getClient_b());
			ps.setString(3, humocards.getBranch());
			ps.setString(4, humocards.getCard());
			ps.setString(5, humocards.getStatus1());
			ps.setString(6, humocards.getStatus2());
			ps.setDate(7, new java.sql.Date(humocards.getExpiry1().getTime()));
			ps.setDate(8, new java.sql.Date(humocards.getExpirity2().getTime()));
			ps.setString(9, humocards.getRenew());
			ps.setDate(10, new java.sql.Date(humocards.getRenew_date()
					.getTime()));
			ps.setString(11, humocards.getCard_name());
			ps.setString(12, humocards.getMc_name());
			ps.setString(13, humocards.getM_name());
			ps.setString(14, humocards.getStop_cause());
			ps.setString(15, humocards.getRenewed_card());
			ps.setInt(16, humocards.getDesign_id());
			ps.setString(17, humocards.getInstant());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			//ConnectionPool.close(c);
		}

	}

	// public static void remove(HumoCards humocards) {
	//
	// Connection c = null;
	//
	// try {
	// c = ConnectionPool.getConnection();
	// PreparedStatement ps =
	// c.prepareStatement("DELETE FROM TF_humocards WHERE id=?");
	// ps.setLong(1, humocards.getId());
	// ps.executeUpdate();
	// c.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// ConnectionPool.close(c);
	// }
	// }

	public static List<AccountHumo> getAccount(String alias, String branch,
			String idClient) {

		List<AccountHumo> list = new ArrayList<AccountHumo>();
		Connection c = null;
		ResultSet rs = null;

		try {
			// c = ConnectionPool.getConnection( alias);
			// PreparedStatement ps =
			// c.prepareStatement("select * FROM BF_TR_ACC_TEMPLATE WHERE id=?");
			// ps.setInt(1,tracc.getAcc_template_id());
			// rs = ps.executeQuery();
			// if (rs.next()) {
			// whr = rs.getString("acc_mask");
			// nm = rs.getString("acc_name");
			// }
			
			
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM Account where branch=? and client=?");

			ps.setString(1, branch);
			ps.setString(2, idClient);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AccountHumo(

				rs.getString("branch"), rs.getString("id"), rs
						.getString("name")));
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}

		return list;
	}
	
	
	
	
	

	public static List<RefData> getCardType(String branch) {
		System.out.println("777");
		return RefDataService
				.getRefData(
						"select code data, name label from ss_humo_p_card_type order by code",
						branch);
		
		
		
	}
	
	public static List<RefData> getJCardType(String branch) {
		return RefDataService
				.getRefData(
						"select code data, name label from ss_humo_j_card_type order by code",
						branch);
		
   }
	
	public static List<RefData> getVidCard(String branch) {
		return RefDataService
				.getRefData(
						"select code data, name label from SS_HUMO_J_type_of_card order by code",
						branch);
		
   }
	
	public static String getClientType(String branch, String searchClients,String un,String pw,String alias) {

		
		System.out.println("searchClients88:"+searchClients);
		String type = null;
		Connection c = null;
		ResultSet rs = null;

		try {
						
			c = ConnectionPool.getConnection(alias);
			ISLogger.getLogger().error(" ALIAS777 "+alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT code_subject FROM client where branch=? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, searchClients);
		
			rs = ps.executeQuery();
			while (rs.next()) {
				

				type=rs.getString("code_subject");
				
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}

		
		
		return type;
	}
	

	public static String getCodeB(String alias, String code) {

		String codeB = null;
		Connection c = null;
		ResultSet rs = null;

		try {
						
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			PreparedStatement ps = c
					.prepareStatement("SELECT code_b FROM ss_humo_card_type where code=?");

			ps.setString(1, code);
		
			rs = ps.executeQuery();
			while (rs.next()) {
				

				codeB=rs.getString("code_b");
				
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}

		
		
		return codeB;
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
	
	
	  public static String getAcc(Connection c,String typeCard, String clientId, String branch) {

			String acc = null;
			
			ResultSet rs = null;
			PreparedStatement ps =null;

			try {
							
				
				//Statement s = c.createStatement();
				 ps = c
						//.prepareStatement("SELECT ACCOUNT FROM HUMO_NEW_EMP_FIZIK where client_id=? and branch=?");
				
						.prepareStatement("SELECT ACCOUNT FROM HUMO_CARD_OPEN where client=? and branch=? and card_type=?");
				
				ps.setString(1, clientId);
				ps.setString(2, branch);
				ps.setString(3, typeCard);
			
				rs = ps.executeQuery();
				while (rs.next()) {
					

					acc=rs.getString("ACCOUNT");
					
				
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//close(ps);
				//ConnectionPool.close(c);
			}

			
			
			return acc;
		}

	  
	  public static ResponsInfo newAgreementHumo(Connection c,String alias,
				String branch, String clientId, String cardCode,String personCode,String vidCard,String firstCardName) throws Exception {
			Customer customer=null;
			String cl_type;
			SS_HUMO_TYPE_OF_CARD cardType=getCardType(vidCard, branch,c);
			 HUMO_BANK_C = cardType.getBank_c();
			 HUMO_GROUPC = cardType.getGroup_c();
			 HUMO_BINCOD = cardType.getBin();
			 //HUMO_BINCOD = "90000301";
			
			 HUMO_CHIPAPPID= cardType.getChip_app_id();
			 HUMO_BRANCHID = cardType.getBranch_id();
			 
			 
			 
			 HUMO_RANGEID = cardType.getRange_id();
			
			
			
			if(cardCode.equals("002") || cardCode.equals("007") || cardCode.equals("008") ){
				
				cl_type="2";
				
				
				 customer =  HumoCardsService.getCustomerJ(c, branch,
							clientId);	
				 
				 System.out.println("getCustomer:"+customer);
				 
				 System.out.println("clientId:"+clientId);
				 
			}
			
			else{
			
				customer = HumoCardsService.getCustomer(c, branch,
						clientId);
				cl_type="1";
				System.out.println("getCustomer:"+customer);
		}
			
			
			

			ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
			SS_HUMO_TYPE_OF_CARD cardType1=getCardType(vidCard, branch,c);
			
			System.out.println("vidCard:"+vidCard+" branch:"+branch);
			
			System.out.println("cardType:"+cardType1.toString());
			
			 HUMO_BANK_C = cardType1.getBank_c();
			 HUMO_GROUPC = cardType1.getGroup_c();
			 HUMO_BINCOD = cardType1.getBin();
			 HUMO_BRANCHID = cardType1.getBranch_id();
			 ResponsInfo responsInfo=new ResponsInfo();
		

			
			try {

				globus.IssuingWS.IssuingPortProxy issuingPortProxy = initNp(alias);

				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();

				connectionInfo.setBANK_C(HUMO_BANK_C);
				connectionInfo.setGROUPC(HUMO_GROUPC);
				
				String externalSesionId=getExternalSession();
				System.out.println("externalSesionId:"+externalSesionId);
				connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
						getNewRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,cardCode,customer,alias,getClientIdHumo(clientId, branch,c)));
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder(); 
			
				
				
				////////////////////////////////// Запрос в Humo newAgremmentHumo //////////////////////////////////////////
				
				OperationResponseInfo operationResponsInfo = issuingPortProxy
				.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			 
				// ////////////////////////////////////////////////////////////////////////////////////////////////
			
				
				
				if(operationResponsInfo.getError_description()==null){
					System.out.println("+++agr:"+agreementInfo.value.toString());
					BigDecimal agrNom=agreementInfo.value.getAGRE_NOM();
					int resClient=0,resAgreement=0,resAccount=0,resCards=0;
					resAgreement=insertAgreement(connectionInfo,agreementInfo,getClientIdHumo(clientId, branch,c),agrNom,c);
					System.out.println("resAgreement:"+resAgreement);
					
					responsInfo.setSuccessful(true);
					
					System.out.println("New Agreement создан:"+agreementInfo.value.getAGRE_NOM()+" client: "+agreementInfo.value.getCLIENT());
					//String acc = getAcc(un,pw,alias,clientId, branch);
					
					//acc= createAccountHumo(alias, customer, un, pw, branch, getCodeB(alias, cardCode));
					
					
					
					String acc2 = getAcc(c,firstCardName.trim(),clientId, branch);
					
//					Map<String, String> hashMap=getAccInfo(clientId, branch);
//					
//					String exsist2=isExsistAcc2(branch, hashMap.get("acc_bal"), hashMap.get("acc_cur"), clientId, hashMap.get("acc_num"));
//					
//				if(exsist2==null){
//						
//						acc2=createAccountHumo(alias, customer, un, pw, branch, hashMap.get("acc_bal"),hashMap.get("acc_num"),hashMap.get("acc_cur"));
//				}
//				
//				else{
//					acc2=exsist2;	
//					
//				}
//				
//				
					
					if(acc2!=null){
					addInfo4AgreementHumo(c,alias, branch, clientId, cardCode, personCode, vidCard, acc2, agreementInfo.value.getAGRE_NOM(),HUMO_CHIPAPPID,firstCardName);
					}
					insertRangAndBranchId(c, clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),firstCardName.trim());
				}
//				
				else{
					responsInfo.setResponseCode(operationResponsInfo.getResponse_code());
					responsInfo.setErrorDescription(operationResponsInfo.getError_description());
					responsInfo.setErrorAction(operationResponsInfo.getError_action());
					responsInfo.setSuccessful(false);
					
					// res.setCode(0);
					// res.setName(orInfo2.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = "
							+ operationResponsInfo.getResponse_code());
					System.out.println("Error description = "
							+ operationResponsInfo.getError_description());
					System.out.println("Error action = "
							+ operationResponsInfo.getError_action());
					System.out.println("-------------------------------");

					ISLogger.getLogger().error(
							"\n\n Response code =" + operationResponsInfo.getResponse_code()
									+ "\n\n");
					ISLogger.getLogger().error(
							"\n Error description = "
							+ operationResponsInfo.getError_description() + "\n");
				}
			
			}
			
			catch (RemoteException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			
			
			}
			return responsInfo;
		}
	  
	  public static ResponsInfo addInfo4AgreementHumo(Connection c,String alias,
				String branch, String clientId, String cardCode,String personCode,String vidCard,String acc,BigDecimal agreNom,Long humoChipAppID, String firstCardName) throws Exception {
			Customer customer=null;
			String cl_type;
			
			if(cardCode.equals("002") || cardCode.equals("007") || cardCode.equals("008") ){
				
				cl_type="2";
				
				
				 customer =  HumoCardsService.getCustomerJ(c, branch,
							clientId);	
				 
				 System.out.println("getCustomer:"+customer);
				 
				 System.out.println("clientId:"+clientId);
				 
			}
			
			else{
			
				customer = HumoCardsService.getCustomer(c, branch,
						clientId);
				cl_type="1";
				System.out.println("getCustomer:"+customer);
		}
			
			
			

			ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();

		
			SS_HUMO_TYPE_OF_CARD cardType=getCardType(vidCard, branch,c);
			
			System.out.println("vidCard:"+vidCard+" branch:"+branch);
			
			System.out.println("cardType:"+cardType.toString());
			
			 HUMO_BANK_C = cardType.getBank_c();
			 HUMO_GROUPC = cardType.getGroup_c();
			 HUMO_BINCOD = cardType.getBin();
			 HUMO_BRANCHID = cardType.getBranch_id();
			 ResponsInfo responsInfo=new ResponsInfo();
		

			
			try {

				globus.IssuingWS.IssuingPortProxy issuingPortProxy = initNp(alias);

				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();

				connectionInfo.setBANK_C(HUMO_BANK_C);
				connectionInfo.setGROUPC(HUMO_GROUPC);
				
				String externalsesionId=getExternalSession();
				System.out.println("externalsesionId:"+externalsesionId);
				connectionInfo.setEXTERNAL_SESSION_ID(externalsesionId);

				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
						getRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,cardCode,customer));
				RowType_AccountInfo accountInfo = new RowType_AccountInfo();
				
				//String acc =""; //optashash
				accountInfo.setBase_info(getRowType_AccountInfo_Base(acc,customer.getBranch(),c));
				ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
				ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
				System.out.println("****ltaccounts "+ltaccounts);
				accountsListInfo.value = ltaccounts;
				
			
				RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
				eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));
				
				
				RowType_CardInfo cardInfo = new RowType_CardInfo();
				cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
				cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCard(customer,firstCardName));
				cardInfo.setEMV_Data(eMV_Data);
				ListType_CardInfo cards = new ListType_CardInfo();
				cards.setRow(new RowType_CardInfo[] { cardInfo });
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
				cardsListInfo.value = cards;
			
			KeyType_Agreement mainAgreementInfo= new KeyType_Agreement();
			
			mainAgreementInfo.setAGRE_NOM(agreNom);
				
				
				////////////////////////////////// Запрос в Humo newAgremmentHumo //////////////////////////////////////////
			System.out.println("77777:"+cardsListInfo.value.getRow(0).getPhysicalCard().getCARD_NAME());
			
			
				OperationResponseInfo operationResponsInfo = issuingPortProxy
				.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
			 
				
				System.out.println("++++++++++cardsListInfo: "+cardsListInfo.value.toString());
				// ////////////////////////////////////////////////////////////////////////////////////////////////

				
				
				if(responsInfo.getErrorDescription()==null){
					
					int resClient=0,resAgreement=0,resAccount=0,resCards=0;
					//resAgreement=insertAgreement(connectionInfo, agreementInfo);
					System.out.println("resAgreement:"+resAgreement);
					resAccount=insertAccounts(accountsListInfo, cardsListInfo,getClientIdHumo(clientId, branch,c), acc,c);
					System.out.println("resAccount:"+resAccount);
					
					resCards= insertHumoCards(cardsListInfo,clientId, customer.getBranch(),getClientIdHumo(clientId, branch,c),firstCardName+customer.getName(),alias,acc,c);
					System.out.println("resCards:"+resCards);
					responsInfo.setSuccessful(true);
					
					System.out.println("Доб карта открыта в HUMO:"+agreementInfo.value.getAGRE_NOM()+" client: "+agreementInfo.value.getCLIENT());
					
				}
				
				else{
					
					responsInfo.setResponseCode(operationResponsInfo.getResponse_code());
					responsInfo.setErrorDescription(operationResponsInfo.getError_description());
					responsInfo.setErrorAction(operationResponsInfo.getError_action());
					responsInfo.setSuccessful(false);
					
					// res.setCode(0);
					// res.setName(orInfo2.getError_description());
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = "
							+ operationResponsInfo.getResponse_code());
					System.out.println("Error description = "
							+ operationResponsInfo.getError_description());
					System.out.println("Error action = "
							+ operationResponsInfo.getError_action());
					System.out.println("-------------------------------");

					ISLogger.getLogger().error(
							"\n\n Response code =" + operationResponsInfo.getResponse_code()
									+ "\n\n");
					ISLogger.getLogger().error(
							"\n Error description = "
							+ operationResponsInfo.getError_description() + "\n");
				}
			
			}
			
			catch (RemoteException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			
			
			}
			return responsInfo;
		}
	  
	  
	  public static ArrayList<String> getSizeAgreement(String clientB,String branch) {

		  ArrayList<String> array=new ArrayList<String>();
			HumoCards humocards = new HumoCards();
			Connection c = null;

			try {
				c = ConnectionPool.getConnection();
				PreparedStatement ps = c
						.prepareStatement("select count(*) ct FROM bf_empc_agreement where client=? and branch=?");
				ps.setString(1, clientB);
				ps.setString(2, branch);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					array.add(rs.getString("client"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//ConnectionPool.close(c);
			}
			
			if(array.size()>1){
				array=null;	
				
			}
			
			return array;
		}

	  public static String getClientIdHumo(String clientB,String branch,Connection c) throws SQLException {

		    String clientIdHumo=null;
			HumoCards humocards = new HumoCards();
			//Connection c = null;
			ResultSet rs=null;
			PreparedStatement ps=null;

			try {
				//c = ConnectionPool.getConnection();
				ps = c
						.prepareStatement("select client FROM humo_cards where client_b=? and branch=?");
				ps.setString(1, clientB);
				ps.setString(2, branch);
				
				rs = ps.executeQuery();
				if(rs.next()) {
					clientIdHumo=rs.getString("client");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
//				rs.close();
//                ps.close();
//                c.close();
			}
			
		System.out.println("===client: "+clientIdHumo);
			return clientIdHumo;
		}
	  
	 
	  
	  public static void insertNewClientAccounts(
				ListType_AccountInfoHolder accountsListInfo,
				ListType_CardInfoHolder cardsListInfo, String acc,Connection c) {
			//Connection c = null;
			PreparedStatement ps = null;
			try {
				c = ConnectionPool.getConnection();
				ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
						+ " (?,?,?,?,?,?)");

				//System.out.println("accountsListInfo7777 " + accountsListInfo);
				System.out.println("Humo client="
						+ cardsListInfo.value.getRow(0).getLogicalCard()
								.getCLIENT());

				ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
						.getCLIENT()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
								.getCLIENT());
				ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info()
						.getACCOUNT_NO()== null ? null : accountsListInfo.value.getRow(0).getBase_info()
								.getACCOUNT_NO());
//				ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
//						.getCARD_ACCT()== null ? "" : accountsListInfo.value.getRow(0).getBase_info()
//								.getCARD_ACCT());
				
				ps.setString(3, acc== null ? "" : acc);
				
				ps.setString(4, acc== null ? "" : acc);

				ps.setDate(5, accountsListInfo.value.getRow(0)
						.getBase_info().getAB_EXPIRITY()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
								.getBase_info().getAB_EXPIRITY().getTimeInMillis()));
				ps.setDate(6, accountsListInfo.value.getRow(0)
						.getBase_info().getCREATED_DATE()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
								.getBase_info().getCREATED_DATE().getTimeInMillis()));
	            
				ps.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					if (c != null)
						c.rollback();
				} catch (Exception ex) {
				}
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} finally {
				try {
//					if (ps != null)
//						ps.close();
				} catch (Exception e) {
				}
				//ConnectionPool.close(c);
			}
		}

		public static void insertNewClientHumoCards(ListType_CardInfoHolder cardsListInfo,
				String clientId, String branch, String alias,String acc,Connection c) {
			//Connection c = null;
			PreparedStatement ps = null;
			try {
				//c = ConnectionPool.getConnection();
				ps = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
						+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct) values"
						+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
						.getCLIENT()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
								.getCLIENT());
				ps.setString(2, clientId== null ? "" : clientId);
				ps.setString(3, cardsListInfo.value.getRow(0).getLogicalCard()
						.getCARD()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
								.getCARD());
				ps.setString(4, branch== null ? "" : branch);
				ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTATUS1()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTATUS1());
				ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTATUS2()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTATUS2());
				ps.setDate(7,cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
								.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
				ps.setDate(8, cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
						.getPhysicalCard().getEXPIRITY2().getTimeInMillis()));
				
				
				
				
				ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getRENEW()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getRENEW());
				
				String psevdaPAN=cardsListInfo.value.getRow(0).getPhysicalCard()
				.getCARD_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
						.getCARD_NAME();
				ps.setString(10,psevdaPAN);
//				ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard()
//						.getMC_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
//								.getMC_NAME());
//				ps.setString(12, cardsListInfo.value.getRow(0).getPhysicalCard()
//						.getMC_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
//								.getMC_NAME());
				
				ps.setString(11, "");
				ps.setString(12, "");
				
				ps.setString(13, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTOP_CAUSE()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTOP_CAUSE());
				ps.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getRENEWED_CARD()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getRENEWED_CARD());
				ps.setBigDecimal(15, cardsListInfo.value.getRow(0)
						.getPhysicalCard().getDESIGN_ID()== null ? null : cardsListInfo.value.getRow(0)
								.getPhysicalCard().getDESIGN_ID());
				ps.setString(16, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getINSTANT()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getINSTANT());
				
				ps.setString(17, acc);
				ps.setString(18,acc);
				
				
				//ps.setString(17,getRealCardNumber(psevdaPAN, initNp(alias), HUMO_BANK_C, HUMO_GROUPC));


				ps.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					if (c != null)
						c.rollback();
				} catch (Exception ex) {
				}
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} finally {
				try {
//					if (ps != null)
//						ps.close();
				} catch (Exception e) {
				}
				//ConnectionPool.close(c);
			}
		}

		public static void insertNewClientAgreement(OperationConnectionInfo connectionInfo,
				RowType_AgreementHolder agreementInfo,Connection c) {
			//Connection c = null;
			PreparedStatement ps = null;
			try {
				//c = ConnectionPool.getConnection();
				ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
				ps.setString(1, agreementInfo.value.getAGRE_NOM().toString()== null ? "" : agreementInfo.value.getAGRE_NOM().toString());
				ps.setString(2, agreementInfo.value.getCLIENT()== null ? "" : agreementInfo.value.getCLIENT());
				ps.setString(3, connectionInfo.getGROUPC()== null ? "" : connectionInfo.getGROUPC());
				ps.setString(4, agreementInfo.value.getBINCOD()== null ? "" : agreementInfo.value.getBINCOD());
				ps.setString(5, agreementInfo.value.getBANK_CODE()== null ? "" : agreementInfo.value.getBANK_CODE());
				ps.setString(6, agreementInfo.value.getBRANCH()== null ? "" : agreementInfo.value.getBRANCH());
				ps.setString(7, connectionInfo.getBANK_C()== null ? "" : connectionInfo.getBANK_C());
				ps.setString(8, agreementInfo.value.getPRODUCT()== null ? "" : agreementInfo.value.getPRODUCT());
				ps.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					if (c != null)
						c.rollback();
				} catch (Exception ex) {
				}
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} finally {
				try {
//					if (ps != null)
//						ps.close();
				} catch (Exception e) {
				}
				//ConnectionPool.close(c);
			}
		}

		public static void insertNewClient(RowType_Customer customerInfo, String client,Connection c)
				throws Exception {
			//Connection c = null;
			PreparedStatement ps = null;
			boolean bool = false;
			try {
				//c = ConnectionPool.getConnection();
				ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
						+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
						+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
						+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps.setString(1, client== null ? "" : client);
				ps.setString(2, customerInfo.getF_NAMES()== null ? "" : customerInfo.getF_NAMES());
				ps.setString(3, customerInfo.getCL_TYPE()== null ? "" : customerInfo.getCL_TYPE());
				ps.setString(4, customerInfo.getCLIENT_B()== null ? "" : customerInfo.getCLIENT_B());
				ps.setString(5, customerInfo.getSURNAME()== null ? "" : customerInfo.getSURNAME());
				ps.setString(6, customerInfo.getM_NAME()== null ? "" : customerInfo.getM_NAME());
				ps.setDate(7, customerInfo.getDOC_SINCE()== null ? null : new java.sql.Date(customerInfo.getDOC_SINCE()
								.getTimeInMillis()));
				ps.setDate(8, customerInfo.getB_DATE()== null ? null : new java.sql.Date(customerInfo.getB_DATE()
								.getTimeInMillis()));
				ps.setString(9, customerInfo.getRESIDENT()== null ? "" : customerInfo.getRESIDENT());
				ps.setString(10, customerInfo.getSTATUS()== null ? "" : customerInfo.getSTATUS());
				ps.setString(11, customerInfo.getSEX()== null ? "" : customerInfo.getSEX());
				ps.setString(12, customerInfo.getSERIAL_NO()== null ? "" : customerInfo.getSERIAL_NO());
				ps.setString(13, customerInfo.getID_CARD()== null ? "" : customerInfo.getID_CARD());
				ps.setString(14, customerInfo.getR_CITY()== null ? "" : customerInfo.getR_CITY());
				ps.setString(15, customerInfo.getR_STREET()== null ? "" : toTranslit(customerInfo.getR_STREET()));
				ps.setString(16, customerInfo.getR_E_MAILS()== null ? "" : customerInfo.getR_E_MAILS());
				ps.setString(17, customerInfo.getR_MOB_PHONE()== null ? "" : customerInfo.getR_MOB_PHONE());
				ps.setString(18, customerInfo.getR_PHONE()== null ? "" : customerInfo.getR_PHONE());
				ps.setString(19, customerInfo.getR_CNTRY()== null ? "" : customerInfo.getR_CNTRY());
				ps.setString(20, customerInfo.getISSUED_BY()== null ? "" : customerInfo.getISSUED_BY());
				ps.setString(21, customerInfo.getPERSON_CODE()== null ? "" : customerInfo.getPERSON_CODE());
				ps.setString(22, customerInfo.getDOC_TYPE()== null ? "" : customerInfo.getDOC_TYPE());
				// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
				// ? null
				// : new java.sql.Date(customerInfo.value
				// .getREC_DATE().getTime().getTime()));
				ps.setDate(23, customerInfo.getREC_DATE()== null ? null : new java.sql.Date(customerInfo.getREC_DATE()
								.getTimeInMillis()));

				ps.execute();
				c.commit();
				bool = true;
			} finally {
				if (!bool)
					try {
						if (c != null)
							c.rollback();
					} catch (Exception ex) {
					}
				try {
//					if (ps != null)
//						ps.close();
				} catch (Exception e) {
				}
				//ConnectionPool.close(c);
			}
		}
		 static String getRealCardNumber(String pseudoCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
			 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
			 try{
			   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		       connectionInfo.setBANK_C(bankC);
		       connectionInfo.setGROUPC(groupC);
		       String externalSesionId=getExternalSession();
				System.out.println("externalSesionId:"+externalSesionId);
				connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

		       RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

		      
		    
		       try{
		    	   proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
		       } catch (NullPointerException e){
		    	   return response.value.getRCARD();
		       }
		       
		       
		       
		      }catch(Exception e){
		    	  
		    	  ISLogger.getLogger().error(e);
		    	  
		      }
		      
		      
		       return response.value.getRCARD();
		       
		   }
		   
		   public static Map<String, String> getPsevdaPANInHashmap() throws SQLException{
				
				List<RefData> terminal = null;
		       Connection c = null;
		       PreparedStatement ps = null;
		       ResultSet rs = null;
		   	Map<String, String> hashMap = new HashMap<String, String>();
		       try {
		     	 
		   		
		     	  terminal = new ArrayList<RefData>();
		               c = ConnectionPool.getConnection();
		               //ps = c.prepareStatement("select t.card,t.client from humo_cards t");
		               
		               ps = c.prepareStatement("select b.card,b.client from humo_cards b where b.real_card is null");
		              // ps = c.prepareStatement("select pan,b.mfo_bank from WRONG_HUMO_CARDS b where b.real_card is null");
		               rs = ps.executeQuery();
		             
		               while (rs.next()) {
		              	 		
		               	hashMap.put(rs.getString("card"), rs.getString("client"));	
		            	   
		            	   //hashMap.put(rs.getString("pan"), rs.getString("mfo_bank"));	
		              
		               }	
		                  	

		       } catch (Exception e) {
		    	    ISLogger.getLogger().error(e);
		               e.printStackTrace();
		       } finally {
		     	        rs.close();
		               ps.close();
		               c.close();
		             
		       }
		       return hashMap; 
			}
		
//		public static boolean isExsistAcc(String branch,String accBal, String acc_cur,String clientId,String acc_num) throws SQLException{
//			
//			Connection c = null;
//		       PreparedStatement ps = null;
//		       ResultSet rs = null;
//		       boolean isExsistAcc=false;
//			
//			 try {
//		     	 
//			   		
//		     	  
//		               c = ConnectionPool.getConnection();
//		               ps = c.prepareStatement("select id from account a where a.branch = ? and a.client=? and a.id like ?");
//		               ps.setString(1, branch);
//		               ps.setString(2, clientId);
//		               ps.setString(3, accBal+acc_cur+"_"+clientId+acc_num);
//		               rs = ps.executeQuery();
//		               System.out.println("searchACC:"+accBal+acc_cur+"_"+clientId+acc_num);
//		               
//		               while (rs.next()) {
//		              	 		System.out.println("isExsistAcc: "+rs.getString("id"));
//		            	   isExsistAcc=true;
//	
//		              
//		               }	
//		                  	
//
//		       } catch (Exception e) {
//		               e.printStackTrace();
//		       } finally {
//		     	        rs.close();
//		               ps.close();
//		               c.close();
//		             
//		       }
//		       return isExsistAcc; 
//			}
	
public static String isExsistAcc2(String branch,String accBal, String acc_cur,String clientId,String acc_num) throws SQLException{
			
			Connection c = null;
		       PreparedStatement ps = null;
		       ResultSet rs = null;
		       boolean isExsistAcc=false;
		       String acc=null;
			
			 try {
		     	 
			   		
		     	  
		               c = ConnectionPool.getConnection();
		               ps = c.prepareStatement("select id from account a where a.branch = ? and a.client=? and a.id like ?");
		               ps.setString(1, branch);
		               ps.setString(2, clientId);
		               ps.setString(3, accBal+acc_cur+"_"+clientId+acc_num);
		               rs = ps.executeQuery();
		               System.out.println("searchACC:"+accBal+acc_cur+"_"+clientId+acc_num);
		               
		               while (rs.next()) {
		              	 		System.out.println("isExsistAcc: "+rs.getString("id"));
		            	   isExsistAcc=true;
		            	   acc=rs.getString("id");
			              
		               }	
		                  	

		       } catch (Exception e) {
		               e.printStackTrace();
		       } finally {
		     	        rs.close();
		               ps.close();
		               c.close();
		             
		       }
		       return acc; 
			}
	
//	public static Map<String, String> getAccInfo(String idClient, String branch) throws SQLException{
//		
//		Connection c = null;
//	       PreparedStatement ps = null;
//	       ResultSet rs = null;
//	   	Map<String, String> hashMap = new HashMap<String, String>();
//		
//		 try {
//	     	       c = ConnectionPool.getConnection();
//	               ps = c.prepareStatement("select * from HUMO_NEW_EMP_CARD a where a.branch = ? and client_id=?");
//	               ps.setString(1, branch);
//	               ps.setString(2, idClient);
//	             
//	               rs = ps.executeQuery();
//	               
//	               if (rs.next()) {
//	              	 		
//	            	   hashMap.put("acc_bal", rs.getString("acc_bal"));
//	            	   hashMap.put("acc_cur", rs.getString("acc_cur"));
//	            	   hashMap.put("acc_num", rs.getString("acc_num"));
//	            	   hashMap.put("acc_bal", rs.getString("acc_bal"));
//	            	   hashMap.put("acc_name", rs.getString("acc_name"));
//
//	              
//	               }	
//	                  	
//
//	       } catch (Exception e) {
//	               e.printStackTrace();
//	       } finally {
//	     	       rs.close();
//	               ps.close();
//	               c.close();
//	             
//	       }
//	       return hashMap; 
//		
//		
//	}
//		
	public static String getPAN(BigInteger accNom,IssuingPortProxy proxy,String HUMO_BANK_C,String HUMO_GROUP_C ) throws RemoteException{
		
	 OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
     connectionInfo.setBANK_C(HUMO_BANK_C);
     connectionInfo.setGROUPC(HUMO_GROUP_C);
     String externalSesionId=getExternalSession();
		System.out.println("externalSesionId:"+externalSesionId);
		connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

     //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

//     RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
//  
//     proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
   
		 RowType_ListCardsByAccount_Request param3=new RowType_ListCardsByAccount_Request();
		 param3.setACCOUNT_NO(accNom);
		 
		 OperationResponseInfoHolder responseInfo=new OperationResponseInfoHolder();
		 ListType_GenericHolder details=new ListType_GenericHolder();
		 proxy.listCardsByAccount(connectionInfo, param3, responseInfo, details);

          return details.value.getRow(0).getItem(3).getValue();
     
	}
	
	
	public static String getListAccountsByCard(String card,IssuingPortProxy proxy,String HUMO_BANK_C,String HUMO_GROUP_C ) throws RemoteException{
		
		 OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	     connectionInfo.setBANK_C(HUMO_BANK_C);
	     connectionInfo.setGROUPC(HUMO_GROUP_C);
	     String accountNo=null;
	     
	     try{
	     String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
	

	     //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

//	     RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
	//  
//	     proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
	   
			 RowType_ListAccountsByCard_Request param3=new RowType_ListAccountsByCard_Request();
			 param3.setCARD(card);
			 param3.setBANK_C(HUMO_BANK_C);
			 param3.setGROUPC(HUMO_GROUP_C);
			 
			 OperationResponseInfoHolder responseInfo=new OperationResponseInfoHolder();
			 ListType_GenericHolder details=new ListType_GenericHolder();
			 proxy.listAccountsByCard(connectionInfo, param3, responseInfo, details);

			RowType_Generic[] rows=details.value.getRow();
			
			for (RowType_Generic r:rows){
			if(r.getItem(0).getValue().equals(card))
			{
				accountNo=r.getItem(17).getValue();
			}
			}
}catch(Exception e){
	
	ISLogger.getLogger().error(e);
	System.out.println(e);
	
}
			return accountNo;
		}
	
	
public static Map<String, String> getAccountsInHashmap() throws SQLException{
		
		Connection c = null;
	       PreparedStatement ps = null;
	       ResultSet rs = null;
	   	Map<String, String> hashMapClient = new HashMap<String, String>();
	 	//Map<String, String> hashMapAcc= new HashMap<String, String>();
		 try {
	     	       c = ConnectionPool.getConnection();
	               //ps = c.prepareStatement("select * from bf_empc_accounts");
	     	      ps = c.prepareStatement("Select * From bf_empc_accounts a where not exists (select * from humo_cards c where c.account_no = a.account_no)");
	                       
	               rs = ps.executeQuery();
	               
	               while (rs.next()) {
	              	 
	            	   hashMapClient.put(rs.getString("account_no"), rs.getString("client"));
	            	   
	                  }	
	                  	

	       } catch (Exception e) {
	               e.printStackTrace();
	       } finally {
	     	       rs.close();
	               ps.close();
	               c.close();
	             
	       }
	       return hashMapClient; 
		
		
	}
	

public static void updateINHumoCards(String account_no,String card,String client) throws SQLException {

	Connection c = null;
	PreparedStatement ps = null;
	try {
		c = ConnectionPool.getConnection();
		 ps = c.prepareStatement("UPDATE humo_cards SET account_no=?  WHERE card=? and client=?");

		ps.setString(1, account_no);
		ps.setString(2, card);
		ps.setString(3, client);
		ps.executeUpdate();
		c.commit();
	} catch (SQLException e) {
		e.printStackTrace();

	} finally {
		
		ps.close();
		//ConnectionPool.close(c);
		
	}

}


//public static void createCards(String un,String pw,String alias) throws Exception{
//	
//	
//	
//	
//	Map<String, String> hashMap = getNewClientsInHashmap();
//	//int j=0;
//	for (Map.Entry entry : hashMap.entrySet()) {
//		
//		System.out.println();
//		System.out.println("++++++++++++++++++");
//		System.out.println(j++);
//		System.out.println("++++++++++++++++++");
//		System.out.println();
//		ResponsInfo responsInfo=HumoCardsService.createCardsHumo(un, pw, alias, entry.getValue().toString(),entry.getKey().toString(), "006","123","3","GMU ");
// 		
//   	 if(responsInfo.getSuccessful()==true)
//		{
//   		 
//			insertNewCards(entry.getKey().toString(),entry.getValue().toString(),"2","");
//		}
//
//		else{
//		
//				insertNewCards(entry.getKey().toString(),entry.getValue().toString(),"1",responsInfo.getErrorDescription());	
//		}
//			
//	}
//}


public static void crDCards(Connection c,String alias) throws Exception{
	
	try{
	ArrayList<PacketOpenCard> nCards=getDCards(c);
	
	for(PacketOpenCard card:nCards){
		
		  ResponsInfo responsInfo=HumoCardsService.newAgreementHumo(c,alias,card.getBranch(),card.getClientB(), "002","123",card.getCardTypeNamber(),card.getCardType()+" ");
	    	
	      	 if(responsInfo.getSuccessful()==true)
				{
	      		 
		 		updateStatusCard(card.getClientB(),card.getBranch(),"2","",card.getCardType());
				 		
				}

				else{
				
					updateStatusCard(card.getClientB(),card.getBranch(),"1",responsInfo.getErrorDescription(),card.getCardType());	
				}
	
	}	
	}catch(Exception e){
		
		ISLogger.getLogger().error(e);
		e.printStackTrace();
	}
}


public static int updateStatusCard(String client_id,String branch,String state, String err,String cardType) {
	Connection c = null;
	int res=0;
	PreparedStatement ps = null;
	try {
		
		
		System.out.println("client_id"+client_id);
		System.out.println("branch"+branch);
		System.out.println("state"+state);
		System.out.println("err"+err);
		
		
		c = ConnectionPool.getConnection();
		ps = c.prepareStatement("update HUMO_CARD_OPEN set state_d=?, err_d=?, state_n=8  where client=? and branch=? and card_type=?");

		ps.setString(1, state);
		ps.setString(2,err);
		ps.setString(3,client_id);
		ps.setString(4,branch);
		ps.setString(5,cardType);
//	
        
		res=ps.executeUpdate();
		c.commit();
	} catch (Exception e) {
		e.printStackTrace();
		try {
			if (c != null)
				c.rollback();
		} catch (Exception ex) {
		}
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	} finally {
		try {
//			if (ps != null)
//				ps.close();
		} catch (Exception e) {
		}
		//ConnectionPool.close(c);
	}
	
	return res;
}
public static void crNCards(Connection c,String alias) throws Exception{
	

	ArrayList<PacketOpenCard> nCards=getNCards(c);
	
	for(PacketOpenCard card:nCards){
		
		if(card.getCardType().equals("F")){
		
			ResponsInfo responsInfo=HumoCardsService.createCardsHumo(c, alias, card.getBranch(),card.getClientB(), "002","123",card.getCardTypeNamber(),"");
			
		}
		
		ResponsInfo responsInfo=HumoCardsService.createCardsHumo(c, alias, card.getBranch(),card.getClientB(), "002","123",card.getCardTypeNamber(),card.getCardType()+" ");
		 if(responsInfo.getSuccessful()==true)
				{
		   		 
					insertNewCards(card.getClientB(),card.getBranch(),"2","",card.getCardType());
										
				}
		
				else{				
						insertNewCards(card.getClientB(),card.getBranch(),"1",responsInfo.getErrorDescription(),card.getCardType());	
				}
	
	}	
   	 
}


           public static void createCards(Connection c,String alias) throws Exception{
	
        	   
        	   
        	   try{
        		   
        	    crNCards(c,alias);
        	    crDCards(c,alias);
        	    
        	   }catch(Exception e){
        		 
        		 ISLogger.getLogger().error(e);
        		 e.printStackTrace();  
        		  
        	   }
        	   
        	   finally{
        		   
        		   
        		   
        		     }

             }


public static Map<String, String> getNewClientsInHashmap() throws SQLException{
	
	List<RefData> terminal = null;
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	Map<String, String> hashMap = new HashMap<String, String>();
    try {
  	 
		
  	  terminal = new ArrayList<RefData>();
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN c where exists (select 'x' from V_CLIENT_HUMO b WHERE b.branch = c.branch and b.id_client=c.client_id and b.state=2) and c.state_x='0'");
            rs = ps.executeQuery();
          
            while (rs.next()) {
     
            	hashMap.put(rs.getString("client_id"), rs.getString("branch"));	
           
            }	
               	

    } catch (Exception e) {
            e.printStackTrace();
    } finally {
//  	        rs.close();
//            ps.close();
           // c.close();
          
    }
    return hashMap; 
}

public static int insertNewCards(String client_id,String branch,String state, String err,String cardType) {
	Connection c = null;
	int res=0;
	PreparedStatement ps = null;
	try {
		c = ConnectionPool.getConnection();
		ps = c.prepareStatement("update HUMO_CARD_OPEN set state_n=?, err_n=?  where client=? and branch=? and card_type=?");

		ps.setString(1, state);
		ps.setString(2,err);
		ps.setString(3,client_id);
		ps.setString(4,branch);
		ps.setString(5,cardType);
//	
        
		res=ps.executeUpdate();
		c.commit();
	} catch (Exception e) {
		e.printStackTrace();
		try {
			if (c != null)
				c.rollback();
		} catch (Exception ex) {
		}
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	} finally {
		try {
//			if (ps != null)
//				ps.close();
		} catch (Exception e) {
		}
		//ConnectionPool.close(c);
	}
	
	return res;
}

   public static ArrayList<PacketOpenCard> getNCards(Connection c) throws SQLException{
	   
	   ArrayList<PacketOpenCard> listCards=new ArrayList<PacketOpenCard>();
	 PacketOpenCard cards=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	
	 
	 ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN c where exists (select 'x' from V_CLIENT_HUMO b WHERE b.branch = c.branch and b.id_client=c.client and b.state=2) and c.state_n='0'");
     rs = ps.executeQuery();
	 
     while(rs.next()){
    	 
    	 cards=new PacketOpenCard();
    	 cards.setCardTypeNamber(getCardTypeNumber(rs.getString("CARD_TYPE"))); 
    	 cards.setClientB(rs.getString("CLIENT"));
    	 cards.setBranch(rs.getString("BRANCH"));
    	 cards.setAccount(rs.getString("ACCOUNT"));
    	 cards.setCardType(rs.getString("CARD_TYPE"));
    	 listCards.add(cards);
     }
     
     return listCards;
   }
	 
public static ArrayList<PacketOpenCard> getDCards(Connection c) throws SQLException{
	   
	   ArrayList<PacketOpenCard> listCards=new ArrayList<PacketOpenCard>();
	 PacketOpenCard cards=null;
	 PreparedStatement ps=null;
	 ResultSet rs=null;
	
	 
	 ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN c where exists (select 'x' from V_CLIENT_HUMO b WHERE b.branch = c.branch and b.id_client=c.client and b.state=2) and c.state_n='1'");
     rs = ps.executeQuery();
	 
     while(rs.next()){
    	 
    	 cards=new PacketOpenCard();
    	 cards.setCardTypeNamber(getCardTypeNumber(rs.getString("CARD_TYPE"))); 
    	 cards.setClientB(rs.getString("CLIENT"));
    	 cards.setBranch(rs.getString("BRANCH"));
    	 cards.setAccount(rs.getString("ACCOUNT"));
    	 cards.setCardType(rs.getString("CARD_TYPE"));
    	 listCards.add(cards);
     }
     
     return listCards;
   }
	public static String getCardTypeNumber(String cardType){
		String cardTypeNumber=null;
		
		if(cardType.equals("PYMM") || cardType.equals("GYMM") || cardType.equals("KYMM")|| cardType.equals("YMM")){
			
			cardTypeNumber="2";
		}
		
		else if(cardType.equals("PMU") || cardType.equals("GMU") || cardType.equals("KMU")|| cardType.equals("MU")){
			cardTypeNumber="3";
			
		}
		
		else if(cardType.equals("F")){
			
			cardTypeNumber="4";
		}
		
		else {
		
			cardTypeNumber="9";
			
			}
		
		
		return cardTypeNumber;
	}
	   
	
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public static void close(PreparedStatement ps) {
		try {
			if(ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public static void close(CallableStatement ps) {
		try {
			if(ps != null) ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	
	public static boolean isExsistNoFoundCard(Connection c, String type, String mfo, String client) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps=null;
		boolean isExsestCard=false;

		try {
			
			 ps = c.prepareStatement("SELECT * FROM HUMO_NO_FOUND_CARD_OPEN WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard=true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return isExsestCard;
	}
	
	public static boolean isExsistCard(Connection c, String type, String mfo, String client) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps=null;
		boolean isExsestCard=false;

		try {
			
			 ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard=true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return isExsestCard;
	}
	
	
	public static boolean isExsistCardRenew(Connection c, String type, String mfo, String client) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps=null;
		boolean isExsestCard=false;

		try {
			
			 ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN_RENEW WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard=true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return isExsestCard;
	}

	 static  OperationResponseInfo addStopList(String realCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
		 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		 OperationResponseInfo res=null;
		 
		 try{
		   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	       connectionInfo.setBANK_C(bankC);
	       connectionInfo.setGROUPC(groupC);
	       String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

	       //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

			RowType_AddCardToStopList_Request param=new RowType_AddCardToStopList_Request();
			
			param.setCARD(realCard);
			param.setSTOP_CAUSE("1");
			param.setTEXT("stolen");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);
			
			  res=proxy.addCardToStop(connectionInfo, param);
	    
			  
			  
	      
	       
	       
	       
	      }catch(Exception e){
	    	  
	    	  ISLogger.getLogger().error(e);
	    	  
	      }
	      
	      
	       return res;
	       
	   }
	 
	 
	 static  OperationResponseInfo deleteStopList(String realCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
		 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		 OperationResponseInfo res=null;
		 
		 try{
		   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	       connectionInfo.setBANK_C(bankC);
	       connectionInfo.setGROUPC(groupC);
	       String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

	       //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

			RowType_RemoveCardFromStop_Request param=new RowType_RemoveCardFromStop_Request();
			
			param.setCARD(realCard);
			param.setTEXT("change");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);
			
			  res=proxy.removeCardFromStop(connectionInfo, param);
	    
	       //proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
	       
	       
	       
	      }catch(Exception e){
	    	  
	    	  ISLogger.getLogger().error(e);
	    	  
	      }
	      
	      
	       return res;
	       
	   }
	 
	
	 static  OperationResponseInfo deActivateCard(String PAN, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
		 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		 OperationResponseInfo res=null;
		 
		 try{
		   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	       connectionInfo.setBANK_C(bankC);
	       connectionInfo.setGROUPC(groupC);
	       String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

	       //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

			RowType_DeactivateCard_Request param= new RowType_DeactivateCard_Request();
			
			param.setCARD(PAN);
			
			
			  res=proxy.deactivateCard(connectionInfo, param);
	    
	       //proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
	       
	       
	       
	      }catch(Exception e){
	    	  
	    	  ISLogger.getLogger().error(e);
	    	  
	      }
	      
	      
	       return res;
	       
	   }
	 
	 
	 static  OperationResponseInfo resetPINCounter(String PAN, IssuingPortProxy proxy, String bankC, String groupC,String un,String pw,String alias) throws RemoteException {
		 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		 OperationResponseInfo res=null;
		 
		 try{
		   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	       connectionInfo.setBANK_C(bankC);
	       connectionInfo.setGROUPC(groupC);
	       String externalSesionId=getExternalSession();
		   System.out.println("externalSesionId:"+externalSesionId);
		   connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

	       //RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(pseudoCard);

			RowType_ResetPINCounter_Request param= new RowType_ResetPINCounter_Request();
			
			param.setCARD(PAN);
			param.setEXPIRY(getCardDate(PAN,un,pw,alias));
			param.setTEXT("zbrosPIN");
			
			
			
			  res=proxy.resetPINCounter(connectionInfo, param);
	    
	       //proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
	       
	       
	       
	      }catch(Exception e){
	    	  
	    	  ISLogger.getLogger().error("resetPINCounter:"+e);
	    	  
	      }
	      
	      
	       return res;
	       
	   }
	 
	 
	 public static String getCardDate(String PANCard,String un,String pw, String alias) throws SQLException{
		
		
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String date=null;
		 String srok=null;
		 Connection c1=null;
		 try{
			 c1=ConnectionPool.getConnection(un,pw,alias);
		 ps = c1.prepareStatement("SELECT EXPIRY1 FROM HUMO_CARDS where card=?");
		 ps.setString(1, PANCard);
	     rs = ps.executeQuery();
		 
	     while(rs.next()){
	    	 date=rs.getString("EXPIRY1");
    	      System.out.println("date:"+date);
	    	 srok=date.substring(2,4)+""+date.substring(5,7);
	    	 System.out.println("srok:"+srok);
	    	 
	     }
	     
		}catch(Exception e){
			e.printStackTrace();
			ISLogger.getLogger().error("EXPIRY1:"+e);
			
		}
		
		finally{
			
			ConnectionPool.close(c1);
		}
		return srok;
		 
	 }
	 
	   
	   public static Map<String, String> getAccountNoInHashmap() throws SQLException{
			
			List<RefData> terminal = null;
	       Connection c = null;
	       PreparedStatement ps = null;
	       ResultSet rs = null;
	   	Map<String, String> hashMap = new HashMap<String, String>();
	       try {
	     	 
	   		
	     	  terminal = new ArrayList<RefData>();
	               c = ConnectionPool.getConnection();
	            
	               
	               ps = c.prepareStatement("select c.real_card,c.tranz_acct from humo_cards c where c.account_no is null and c.card is null");
	            
	               rs = ps.executeQuery();
	             
	               while (rs.next()) {
	              	 		
	               	hashMap.put(rs.getString("real_card"), rs.getString("tranz_acct"));	
	               	ISLogger.getLogger().info("real_card: "+rs.getString("real_card")+" ");
	            	   
	              
	               }	
	                  	

	       } catch (Exception e) {
	    	    ISLogger.getLogger().error(e);
	               e.printStackTrace();
	       } finally {
	     	       rs.close();
	               ps.close();
	               c.close();
	             
	       }
	       return hashMap; 
		}
	 
	   public static Map<String, String> getListCard() throws SQLException{
			
	   	Map<String, String> hashMap = new HashMap<String, String>();
	   	Connection c=null;
	   	PreparedStatement psListCard=null;
	   	ResultSet rs=null;
	       try {
	    	   
	    	   
	    	    c = ConnectionPool.getConnection();
	    	    psListCard  = c.prepareStatement("select k.client,k.card from humo_cards k where k.update_status is null and k.real_card like '98600302%'");
              
	    	   
	    	   rs = psListCard.executeQuery();
	               while (rs.next()) {
	              	 		
	               	hashMap.put(rs.getString("card"), rs.getString("client"));	
	            	   
	            	 
	              
	               }	
	                  	

	       } catch (Exception e) {
	    	    ISLogger.getLogger().error(e);
	               e.printStackTrace();
	       } finally {
	     	 if(c!=null){
	     		ConnectionPool.close(c); 
	     	 }
	     		if (psListCard!=null){
	     			
	     			psListCard.close();
	     		}
	     		if(rs!=null){
	     			rs.close();
	     		}
	     		
	     		 
	     	 }
	       
	       return hashMap; 
		}
	   
		 static CardStatus getCardStatus(String pseudoCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
			
			 CardStatus cardStatus=new CardStatus();
			 String status1=null;
			 String status2=null;
			 String STOP_CAUSE=null;
			 
			 
			 try{
			   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		       connectionInfo.setBANK_C(bankC);
		       connectionInfo.setGROUPC(groupC);
		       String externalSesionId=getExternalSession();
				//System.out.println("externalSesionId:"+externalSesionId);
				connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

		 
				OperationResponseInfoHolder responseInfo=new OperationResponseInfoHolder();
				RowType_QueryCardInfo_Request parameters=new RowType_QueryCardInfo_Request();
				ListType_GenericHolder details=new ListType_GenericHolder();
				parameters.setBANK_C(bankC);
				parameters.setGROUPC(groupC);
				parameters.setCARD(pseudoCard);
		      
			
		       proxy.queryCardInfo(connectionInfo, parameters, responseInfo, details);
		       
		        RowType_Generic row=details.value.getRow(0);
			
			for (ItemType_Generic i:row.getItem()){
			if(i.getName().equals("STATUS1"))
			{
				status1=i.getValue();
			}
			
			if(i.getName().equals("STATUS2"))
			{
				status2=i.getValue();
			}
			if(i.getName().equals("STOP_CAUSE"))
			{
				STOP_CAUSE=i.getValue();
			}
			}
		       
		       
		       
		       cardStatus.setStatus1(status1);
		       cardStatus.setStatus2(status2);
		       cardStatus.setStop_cause(STOP_CAUSE);
		       
		       
		      }catch(Exception e){
		    	  
		    	  ISLogger.getLogger().error(e);
		    	  
		      }
		      
		      
		       return cardStatus;
		       
		   }
		 
		 public static int insertBfAccounts(Connection c, String account_no,String tranz_acct) {
				
				int res=0;
				PreparedStatement ps = null;
				try {
					
					
					
					c = ConnectionPool.getConnection();
					ps = c.prepareStatement("insert into bf_EMPC_accounts(ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?)");
					ISLogger.getLogger().info("insert into bf_EMPC_accounts(ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " ("+account_no+","+tranz_acct+","+tranz_acct+","+new java.sql.Date(new Date().getTime())+","+new java.sql.Date(new Date().getTime()));
					ps.setString(1, account_no);
					ps.setString(2,tranz_acct);
					ps.setString(3,tranz_acct);
					ps.setDate(4,new java.sql.Date(new Date().getTime()));
					ps.setDate(5,new java.sql.Date(new Date().getTime()));
					
					        
					res=ps.executeUpdate();
					c.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						if (c != null)
							c.rollback();
					} catch (Exception ex) {
					}
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
				
				return res;
			}
		 public static int insertAccountNo(Connection c,String account_no,String real_card, String tranz_acct) {
				
				int res=0;
				PreparedStatement ps = null;
				try {
					
					ps = c.prepareStatement("update humo_cards set account_no=? where real_card=? and tranz_acct=?");
					ISLogger.getLogger().info("insert update humo_cards set account_no="+account_no+" where real_card="+real_card+" and tranz_acct="+tranz_acct);

					ps.setString(1, account_no);
					ps.setString(2, real_card);
					ps.setString(3, tranz_acct);
				
				
			        
					res=ps.executeUpdate();
					c.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						if (c != null)
							c.rollback();
					} catch (Exception ex) {
					}
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				} 
				
				return res;
			}
		 
		 public static Long getAccountNO(String client_b,String branch, String cardType,Connection c) {

				Long account_no=null;
				ResultSet rs = null;

				try {

					PreparedStatement ps = c
							.prepareStatement("SELECT min(account_no)as account_no  FROM humo_cards where client_b=? and branch=? and card_name like ?");

					ps.setString(1, client_b);
					ps.setString(2, branch);
					ps.setString(3, cardType+"%");
				
				
					rs = ps.executeQuery();
					while (rs.next()) {
						account_no=rs.getLong("account_no");
					}
					System.out.println("cardType: "+cardType);
					System.out.println("account_no "+account_no);

				} catch (SQLException e) {
					e.printStackTrace();
					
				} 
				return account_no;
		 }
		 
		 public static int updateStatus(String realCard,String alias) throws SQLException {
				
				int res=0;
				PreparedStatement ps = null;
				Connection c=null;
				try {
					c=ConnectionPool.getConnection(alias);
					ps = c.prepareStatement("update humo_cards set status1=?,status2=? where card=?");
					ps.setString(1, "2");
					ps.setString(2, "2");
					ps.setString(3, realCard);
					
					
					
				    res=ps.executeUpdate();
					c.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						if (c != null)
							c.rollback();
					} catch (Exception ex) {
					}
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				} 
				
				finally{
					if (c!=null){
						ConnectionPool.close(c);
					}
					
					if (ps!=null){
						ps.close();
					}
					
				}
				
				return res;
			}
		 public static String getExternalSession() {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
				return dateFormat.format(new Date());
			}
		 
    public static String replaceCard(Connection c, String card,
            HashMap<String, EmpcSettings> settings, String branch, String alias) throws Exception {
        HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
        HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
        HUMO_HOST = ConnectionPool.getValue("HUMO_HOST");
        HUMO_USERNAME = ConnectionPool.getValue("HUMO_USERNAME");
        HUMO_PASSWORD = ConnectionPool.getValue("HUMO_PASSWORD");
        issuingPortProxy = getPortProxy(HUMO_HOST, HUMO_USERNAME, HUMO_PASSWORD);
        connectionInfo = getConInfo(HUMO_BANK_C, HUMO_GROUPC);
        String message = null;
        ReplaceCardInfo cardInfo = null;
        ObjectMapper objectMapper = new ObjectMapper();
        cardInfo = getNewCard(card, issuingPortProxy, connectionInfo);
        if (cardInfo.getError_description() == null) {
            String newReal_Card = cardInfo.getReal_card();
            String newCard = cardInfo.getCard();
            Calendar NEW_EXPIRY = cardInfo.getNEW_EXPIRY();
            String account_no = Utils.getValueFromSql(
                    "select account_no from humo_cards where real_card = '" + card + "'", alias);
            String card_acct = Utils.getValueFromSql(
                    "select a.card_acct from bf_empc_accounts a, humo_cards c where c.real_card = '"
                            + card + "' and c.client = a.client and c.account_no = a.account_no",
                    alias);

            List<String> list = getCardInfo(branch, account_no, card_acct, "860", alias,
                    issuingPortProxy, settings);
            if (list == null) {
                updateHumoCards(c, card, newReal_Card, "", NEW_EXPIRY);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    ISLogger.getLogger().error("LIST CARDS: " + i + " " + list.get(i));
                    if (list.get(i).substring(12).equals(newReal_Card.substring(12))) {
                        updateHumoCards(c, card, newReal_Card, list.get(i), NEW_EXPIRY);
                        message = "Карта успешно перевыпущена";
                        return message;
                    }
                }
            }
        } else {

            message = "Error_action: " + cardInfo.getError_action() + " Error_description: "
                    + cardInfo.getError_description();

        }

        return message;

    }
			
			
			public static List<String> getCardInfo(String branch, String account_no, String card_acct, String ccy, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy, HashMap<String, EmpcSettings> settings)
			{
				List<String> list = new ArrayList<String>();
				
				try
				{
					
					ObjectMapper objectMapper = new ObjectMapper();
					OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
					connectionInfo.setBANK_C(settings.get(branch).getBank_c());
					connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
					connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
					connectionInfo.setFAULT_MODE(BigDecimal.ZERO);
					
					RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
					parameters.setACCOUNT_NO(BigInteger.valueOf(Integer.parseInt(account_no)));
					parameters.setCARD_ACCT(card_acct);
					parameters.setCCY(ccy);
					OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
					
//					ListType_Generic listType_Generic = null;
					ListType_GenericHolder details = new ListType_GenericHolder();
					ISLogger.getLogger().error("listCardsByAccount issuingPortProxy: "+objectMapper.writeValueAsString(issuingPortProxy));
					ISLogger.getLogger().error("listCardsByAccount connectionInfo: "+objectMapper.writeValueAsString(connectionInfo));
					ISLogger.getLogger().error("listCardsByAccount parameters: "+objectMapper.writeValueAsString(parameters));
					ISLogger.getLogger().error("listCardsByAccount responseInfo: "+objectMapper.writeValueAsString(responseInfo));
					ISLogger.getLogger().error("listCardsByAccount details: "+objectMapper.writeValueAsString(details));
					issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
			if (details != null) {
				if (details.value != null) {
					if (details.value.getRow() != null) {
						for (int i = 0; i < details.value.getRow().length; i++) {
							for (int j = 0; j < details.value.getRow(i).getItem().length; j++) {
								if (details.value.getRow(i).getItem(j).getName().equals("CARD")) {
									ISLogger.getLogger().error("LIST CARDS CARD: "+ details.value.getRow(i).getItem(j).getValue());
									list.add(details.value.getRow(i).getItem(j).getValue());
								}

							}
						}
					}
				}
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
			
			
			
			public static String isExsistCardInHumoCards(Connection c, String clientB, String mfo,
					String cardType) {

				String pan = null;
				PreparedStatement ps = null;
				boolean isExsestCard = false;

				try {

					ps = c.prepareStatement("SELECT * FROM HUMO_CARDS where client_B=? and branch=? and card_name like ?");
					ps.setString(1, clientB);
					ps.setString(2, mfo);
					ps.setString(3, cardType + "%");
					
					System.out.println("clientB: "+clientB+" mfo: "+mfo+" cardType: "+cardType);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						isExsestCard = true;
						pan = rs.getString("real_card");
						
					}

				} catch (Exception e) {
					ISLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
				} finally {

				}
				return pan;

			}
			
			public static String  stopCard(Connection c,String card,globus.IssuingWS.IssuingPortProxy portProxy,String bankC,String groupC){

				PreparedStatement ps = null;
				String real_card=null;
				String message=null;
			

				try {
		           
					ps = c.prepareStatement("SELECT client_b, branch, card_name,real_card FROM HUMO_CARDS where real_card = ?");
					ps.setString(1, card);
					
				
					
					ResultSet rs = ps.executeQuery();
				   if (rs.next()) {
						real_card=rs.getString("real_card");
						message = deleteCards(c,real_card,rs.getString("client_b"), rs.getString("branch"),rs.getString("card_name"),portProxy,bankC,groupC);
					}

				} catch (Exception e) {
					ISLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
				} finally {

				}
				
				return message;
				
				
			}
			
			public static ReplaceCardInfo getNewCard(String card,
					globus.IssuingWS.IssuingPortProxy portProxy,
					OperationConnectionInfo conInfo) throws KeyManagementException,
					ClientProtocolException, NoSuchAlgorithmException,
					KeyStoreException, IOException {
				ObjectMapper objectMapper = new ObjectMapper();
				RowType_ReplaceCard parameters = new RowType_ReplaceCard();
				OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
				RowType_ReplaceCardHolder details = new RowType_ReplaceCardHolder();
				ReplaceCardInfo replaceCardInfo = new ReplaceCardInfo();
				BigInteger responseCode;
				parameters.setCARD(card);
				ISLogger.getLogger().error("replaceCard request "
				        + "\n" + objectMapper.writeValueAsString(conInfo)
				        + "\n" + objectMapper.writeValueAsString(parameters));
				portProxy.replaceCard(conInfo, parameters, responseInfo, details);
				ISLogger.getLogger().error("replaceCard response "
				        + "\n" + objectMapper.writeValueAsString(responseInfo));
				responseCode = responseInfo.value.getResponse_code();			
				if (responseInfo.value.getError_description()==null) {
	                ISLogger.getLogger().error("replaceCard response details "
	                        + "\n" + objectMapper.writeValueAsString(details.value));
					replaceCardInfo.setReal_card(details.value.getNEW_CARD());
					replaceCardInfo.setCard(details.value.getCARD());
					replaceCardInfo.setNEW_EXPIRY(details.value.getNEW_EXPIRY());
					replaceCardInfo.setError_description(null);
					
					

				}

				else {
					replaceCardInfo.setResponse_code(responseCode.toString());
					replaceCardInfo.setError_action(responseInfo.value
							.getError_action());
					replaceCardInfo.setError_description(responseInfo.value
							.getError_description());

				}

				return replaceCardInfo;

			}
			
    public static void updateHumoCards(Connection c, String oldCard, String newReal_Card,
            String newCard, Calendar NEW_EXPIRY) {
        PreparedStatement psUpdateHumoCards = null;
        ISLogger.getLogger().error("update humo_cards after card replacement:"
                +"\nOld card: "+oldCard
                +"\nNew card: "+newCard
                +"\nNew real card: "+newReal_Card);
        try {
            psUpdateHumoCards = c.prepareStatement("update humo_cards set real_card=?,card=?, EXPIRY1=?,status1='1',status2='0', renew_date=?"
                    + " where real_card=?");
            psUpdateHumoCards.setString(1, newReal_Card);
            psUpdateHumoCards.setString(2, newCard);
            psUpdateHumoCards.setDate(3, new java.sql.Date(NEW_EXPIRY.getTimeInMillis()));
            psUpdateHumoCards.setDate(4, new java.sql.Date(new Date().getTime()));
            psUpdateHumoCards.setString(5, oldCard);
            psUpdateHumoCards.executeUpdate();
            c.commit();
        } catch (Exception e) {
            e.printStackTrace();

            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            Utils.close(psUpdateHumoCards);
        }
    }
		
			public static String deleteCards(Connection c,String real_card,String client_b, String branch,String cardName,globus.IssuingWS.IssuingPortProxy portProxy,String bankC,String groupC) {

				int res = 0;
				String realCard=null;
				String message=null;
				OperationResponseInfo resInfo=null;

				try {
					
					psSelectStopCards=c.prepareStatement("select real_card from humo_cards where client_b=? and branch=? and card_name=? and real_card<>?");
					
					psSelectStopCards.setString(1, client_b);
					psSelectStopCards.setString(2, branch);
					psSelectStopCards.setString(3, cardName);
					psSelectStopCards.setString(4, real_card);
					
					
					ResultSet rs = psSelectStopCards.executeQuery();
					
					while (rs.next()){
						realCard = rs.getString("real_card");
						resInfo=addStopList(realCard,portProxy,bankC,groupC);
						if(resInfo.getError_description()!=null)
						{
							message=resInfo.getError_description();
							System.out.println("stop eroror"+message);
							return message;
							
						}
						
						else{
							changeStatusCard(c, realCard,"2");
							System.out.println("stop ok");
						}
					}
					
					
					
					
					 psUpdateCards = c.prepareStatement("update humo_cards set card='888' where real_card<> ? and client_b=? and branch=? and card_name=?");
						
					 psUpdateCards.setString(1, real_card);
					 psUpdateCards.setString(2, client_b);
					 psUpdateCards.setString(3, branch);
					 psUpdateCards.setString(4, cardName);
					 psUpdateCards.executeUpdate();
					 c.commit();
					
					 
					psdeleteCards=c.prepareStatement("delete from  humo_cards where real_card<> ? and client_b=? and branch=? and card_name= ? and card='888'");
                    psdeleteCards.setString(1, real_card);
					psdeleteCards.setString(2, client_b);
					psdeleteCards.setString(3, branch);
					psdeleteCards.setString(4, cardName);
					psdeleteCards.executeUpdate();
					c.commit();

				} catch (Exception e) {
					e.printStackTrace();

					ISLogger.getLogger().error(CheckNull.getPstr(e));
					message=CheckNull.getPstr(e);
					
					
					
				}
           return message;
			}	
			
			
			private static globus.IssuingWS.IssuingPortProxy getPortProxy(
					String HUMO_HOST, String HUMO_USERNAME, String HUMO_PASSWORD)
					throws ClientProtocolException, IOException,
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
						HUMO_HOST, HUMO_USERNAME, HUMO_PASSWORD);
			}
			
			public static OperationConnectionInfo getConInfo(String HUMO_BANK_C,
					String HUMO_GROUPC) {
				OperationConnectionInfo conInfo = new OperationConnectionInfo();

				conInfo.setBANK_C(HUMO_BANK_C);
				conInfo.setGROUPC(HUMO_GROUPC);
				conInfo.setEXTERNAL_SESSION_ID(getExternalSession());

				return conInfo;

			}
		public String addToStopList(Connection c,globus.IssuingWS.IssuingPortProxy portProxy, String clientB,String mfo,String cardType,String bankC,String groupC) throws RemoteException, SQLException{
			
			OperationResponseInfo operInfo=null;
			String realCard=null;
			String message=null;
			ResultSet rs=null;
			StringBuilder errors=new StringBuilder();
			
			psSelectStopCards=c.prepareStatement("select real_card from humo_cards where client_b=? and branch=? and card_name like ?");
    		psSelectStopCards.setString(1, clientB);
			psSelectStopCards.setString(2, mfo);
			psSelectStopCards.setString(3, cardType+"%");
            rs = psSelectStopCards.executeQuery();
			
			while (rs.next()){
				realCard = rs.getString("real_card");
				operInfo=addStopList(realCard,portProxy,bankC,groupC);
				
				if (operInfo.getError_description()!=null){
					message="Error_action: "+operInfo.getError_action()+"getError_description: "+operInfo.getError_description();
					errors.append(message+" ");
				}
				
			}
		return errors.toString();
		}
		
		
		 public static int changeStatusCard(Connection c,String realCard,String status) throws SQLException {
				
				int res=0;
				PreparedStatement ps = null;
	
				try {
					
					ps = c.prepareStatement("update humo_cards set status1=?,status2=? where real_card=?");
					ps.setString(1, status);
					ps.setString(2, status);
					ps.setString(3, realCard);
	
					res=ps.executeUpdate();
					c.commit();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						if (c != null)
							c.rollback();
					} catch (Exception ex) {
					}
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				} 
				
				finally{
					if (c!=null){
						ConnectionPool.close(c);
					}
					
					if (ps!=null){
						ps.close();
					}
					
				}
				
				return res;
			}
}
