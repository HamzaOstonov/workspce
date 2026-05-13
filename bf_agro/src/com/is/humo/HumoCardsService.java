package com.is.humo;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.ItemType_Generic;
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
import globus.issuing_v_01_02_xsd.RowType_DeactivateCard_Request;
import globus.issuing_v_01_02_xsd.RowType_Generic;
import globus.issuing_v_01_02_xsd.RowType_GetRealCard_Request;
import globus.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
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
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.joda.time.chrono.IslamicChronology;
import org.jruby.RubyProcess.Sys;
import org.python.constantine.platform.darwin.Sysconf;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.account.Account;
import com.is.clienta.utils.DbUtils;
import com.is.humo.Customer;
import com.is.humo.GlobuzAccount;
import com.is.humo.GlobuzAccountFilter;
import com.is.humo.GlobuzAccountService;
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
	private static String msql = "select * from (SELECT c.*,a.tranz_acct as schet,a.created_date FROM humo_cards c,bf_empc_accounts a where c.account_no=a.account_no)";
	private static PreparedStatement psCurrentDate = null;
	static String HUMO_BANK_C;
	static String HUMO_GROUPC;
	static String HUMO_BINCOD;
	private static String HUMO_HOST;
	private static String HUMO_USERNAME;
	private static String HUMO_PASSWORD;
	

	static Long HUMO_CHIPAPPID;
	static String HUMO_BRANCHID;
	static Long HUMO_RANGEID;
	static Calendar calendar = null;
	private static PreparedStatement psdeleteCards = null;
	private static PreparedStatement psNewAccounts = null;
	private static PreparedStatement psUpdateCards = null;
	private static PreparedStatement psSelectStopCards = null;
	private static PreparedStatement psInsertHumoCards = null;
	private static globus.IssuingWS.IssuingPortProxy issuingPortProxy=null;
	private static OperationConnectionInfo connectionInfo=null;
	public static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private final static Logger logger = Logger
			.getLogger(HumoCardsService.class);

	public List<HumoCards> getHumoCards() throws SQLException {

		List<HumoCards> list = new ArrayList<HumoCards>();
		Connection c = null;
		Statement s=null;
		ResultSet rs=null;
		try {
			c = ConnectionPool.getConnection();
			 s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM humo_cards");
			while (rs.next()) {

				list.add(new HumoCards(rs.getString("client"), rs
						.getString("client_b"), rs.getString("branch"), rs
						.getString("card"), rs.getString("status1"), rs
						.getString("status2"), rs.getDate("expiry1"), rs
						.getDate("expirity2"), rs.getString("renew"), rs
						.getDate("renew_date"), rs.getString("card_name"), rs
						.getString("mc_name"), rs.getString("m_name"), rs
						.getString("stop_cause"), rs.getString("renewed_card"),
						rs.getInt("design_id"), rs.getString("instant"), rs
								.getString("real_card"),"888","777"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			s.close();
			rs.close();
			ConnectionPool.close(c);
			
		}
		return list;

	}

	public static void initCards(Connection c) throws SQLException {

		psCurrentDate = c
				.prepareStatement("SELECT * FROM branch WHERE bank_id=?");

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

	public static int getCount(HumoCardsFilter filter)  {

		Connection c = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
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
			 ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			 rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			Utils.close(ps);
			Utils.close(rs);
			
			 ConnectionPool.close(c);
		}
		return n;

	}

	public static List<HumoCards> getHumoCardssFl(int pageIndex, int pageSize,
			HumoCardsFilter filter)  {
		ResultSet rs=null;
		PreparedStatement ps=null;
		List<HumoCards> list = new ArrayList<HumoCards>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			 ps = c.prepareStatement(sql.toString());
			System.out.println("SQL: "+sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			 rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new HumoCards(rs.getString("client"), rs
						.getString("client_b"), rs.getString("branch"), rs
						.getString("card"), rs.getString("status1"), rs
						.getString("status2"), rs.getDate("expiry1"), rs
						.getDate("expirity2"), rs.getString("renew"), rs
						.getDate("renew_date"), rs.getString("card_name"), rs
						.getString("mc_name"), rs.getString("m_name"), rs
						.getString("stop_cause"), rs.getString("renewed_card"),
						rs.getInt("design_id"), rs.getString("instant"), rs
								.getString("real_card"),rs.getString("schet"),rs.getString("created_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			
			Utils.close(ps);
			Utils.close(rs);
			
			 ConnectionPool.close(c);
		}
		return list;

	}

	public static Calendar getCurrBankDate(String bankId, Connection c,ResultSet rs) throws SQLException {

		Date currDate = null;
		
		try {
			if (calendar == null) {
				calendar = Calendar.getInstance();
				psCurrentDate = c
						.prepareStatement("SELECT * FROM branch WHERE bank_id=?");
				psCurrentDate.setString(1, bankId);
				 rs = psCurrentDate.executeQuery();
				if (rs.next()) {

					currDate = rs.getDate("curr_date");
					calendar.setTime(currDate);
					System.out.println("curr_date=" + calendar);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
          rs.close();
		}
		return calendar;
	}

	public HumoCards getHumoCards(int humocardsId) throws SQLException {

		HumoCards humocards = new HumoCards();
		Connection c = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			c = ConnectionPool.getConnection();
			 ps = c
					.prepareStatement("SELECT * FROM TF_humocards WHERE id=?");
			ps.setInt(1, humocardsId);
			 rs = ps.executeQuery();
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
			// ConnectionPool.close(c);
			ps.close();
			rs.close();
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
			// ConnectionPool.close(c);
		}
		return humocards;
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

			// String customerName = customer.getName().length() > 80 ? customer
			// .getName().substring(0, 79) : customer.getName();

			String customerName = (customer.getName() + " HUMO").length() > 80 ? (customer
					.getName() + " HUMO").substring(0, 79) : (customer
					.getName() + " HUMO");

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
					accType, customer.getId_client(), ccycd, ord, customerName,
					group, alias, branch, brcomp);

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

	public static HumoCardSetting getHumoCardSetting(String code, String alias,
			Connection c) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		// Connection c = null;

		try {
			// c = ConnectionPool.getConnection(alias);
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
			// ConnectionPool.close(c);
		}
		return humocardsetting;
	}

	public static String createAccountHumo(String alias, Customer custemer,
			String un, String pw, String branch, String accType,
			String acc_num, String acc_cur) {

		Res res = new Res();
		// HumoCardSetting tcs = getHumoCardSetting(cardCode,alias);
		// String ord = tcs.getId_order_account();
		// String acc_num = ConnectionPool.getValue("HUMO_ID_ORDER_ACCOUNT",
		// alias);

		res = openAcc(acc_num, 0, alias, un, pw, branch, custemer, accType);
		GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		acfilter.setClient(custemer.getId_client());
		acfilter.setAcc_bal(accType);
		acfilter.setBranch(custemer.getBranch());
		acfilter.setCurrency(acc_cur);
		System.out.println(" sozdan account dlya Humo");

		return res.getName();
	}

	public static RowType_Customer getRowType_Customer(Customer customer,
			String personCode, String cl_type,String cardCode, Connection c,ResultSet rs) throws SQLException {

		Calendar calendar = Calendar.getInstance();

		RowType_Customer rtc = new RowType_Customer();

		if (customer.getP_first_name() == null) {
			rtc.setF_NAMES("");
		}

		else if (customer.getP_first_name().length() > 34) {

			// rtc.setF_NAMES(toTranslit(customer.getP_first_name()).substring(0,34));

			String ferst_name = toTranslit(customer.getP_first_name());

			ferst_name = ferst_name.substring(0, 34);
			rtc.setF_NAMES("F_NAMES:" + ferst_name);

			System.out.println(ferst_name);
		}

		else {

			rtc.setF_NAMES(toTranslit(customer.getP_first_name()));

		}

		System.out.println("********************");
		// System.out.println("P_first_name:"+toTranslit(customer.getP_first_name()));
		rtc.setCL_TYPE("2");
		rtc.setCLIENT_B(customer.getBranch() + customer.getId_client());

		String family = customer.getP_family();
		if (family == null) {

			rtc.setSURNAME("");

		}

		else {
			if (family.length() > 36) {

				rtc.setSURNAME(toTranslit(customer.getP_family().substring(0,
						36)));
			}

			else {
				rtc.setSURNAME(toTranslit(customer.getP_family()));

			}

		}
		// rtc.setSURNAME(customer.getP_family()==null ?
		// "":toTranslit(customer.getP_family().substring(0,36)));

		System.out.println("********************");
		// System.out.println("P_family:"+toTranslit(customer.getP_family());

		Calendar calendarDocSince = Calendar.getInstance();

		if (customer.getP_passport_date_registration() != null) {

			calendarDocSince
					.setTime(customer.getP_passport_date_registration());
			rtc.setDOC_SINCE(calendarDocSince);

		} else {
			rtc.setDOC_SINCE(null);
		}

		Calendar calendarB_data = Calendar.getInstance();

		if (customer.getP_birthday() != null) {
			calendarB_data.setTime(customer.getP_birthday());
			rtc.setB_DATE(calendarB_data);

		} else {
			rtc.setB_DATE(null);
		}

		// rtc.setRESIDENT(customer.getCode_resident()==null?"1":customer.getCode_resident());

		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX(customer.getP_code_gender());
		rtc.setSERIAL_NO(customer.getP_passport_serial());
		rtc.setID_CARD(customer.getP_passport_number());
		rtc.setR_CITY(customer.getP_code_citizenship());
		rtc.setR_STREET(customer.getP_birth_place() == null ? "STREET"
				: customer.getP_birth_place());

		rtc.setR_E_MAILS(customer.getP_email_address());
		rtc.setR_MOB_PHONE(customer.getP_phone_mobile());
		rtc.setR_PHONE("");
		rtc.setR_CNTRY("UZB");
		rtc.setISSUED_BY(customer.getP_passport_place_registration() == null ? ""
				: toTranslit(customer.getP_passport_place_registration()));

		System.out.println("********************");
		// System.out.println("ISSUED_BY:"+toTranslit(customer.getP_passport_place_registration()));

		//rtc.setPERSON_CODE(customer.get);
		rtc.setDOC_TYPE("001");
		calendar.setTime(new Date());
		rtc.setREC_DATE(getCurrBankDate(customer.getBranch(), c,rs));
		rtc.setCLN_CAT(cardCode);
		System.out.println(rtc);

		return rtc;
	}

	public static RowType_AccountInfo_Base getRowType_AccountInfo_Base(
			String acc, String branch, Connection c,ResultSet rs) throws SQLException {

		Calendar calendar = Calendar.getInstance();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		base_info.setCREATED_DATE(getCurrBankDate(branch, c,rs));
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
			String id_client, String branch, String cardType, Connection c) throws SQLException {

		Calendar calendar = Calendar.getInstance();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();

		base_info.setACCOUNT_NO(BigDecimal.valueOf(getAccountNO(id_client,branch, cardType, c)));

		return base_info;
	}

	public static RowType_CardInfo_LogicalCard getRowType_CardInfo_LogicalCard(
			Customer customer, Long HUMO_RANGEID) {

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

		}

		else {

			logicalCard.setF_NAMES(toTranslit(customer.getP_first_name()));
		}

		// logicalCard.setF_NAMES(customer.getP_first_name()==null?
		// "":toTranslit(customer.getP_first_name()));

		logicalCard.setSURNAME(customer.getP_family() == null ? ""
				: toTranslit(customer.getP_family()));
		logicalCard.setMIDLE_NAME(customer.getP_patronymic() == null ? ""
				: toTranslit(customer.getP_patronymic()));
		logicalCard.setRANGE_ID(BigDecimal.valueOf(HUMO_RANGEID));

		return logicalCard;
	}

	public static RowType_CardInfo_LogicalCard getRowType_CardInfo_LogicalCard2(
			CustomerHumo customer, String HUMO_RANGEID,String HUMO_BRANCH_ID) {

		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCOND_SET("001");
		logicalCard.setRISK_LEVEL("A");
		logicalCard.setBASE_SUPP("1");
		logicalCard.setBRANCH("001");
		logicalCard.setCARD_TYPE("01");

		if (customer.getName() == null) {
			logicalCard.setF_NAMES("");
		} else if (customer.getName().length() > 34) {

			String first_name = toTranslit(customer.getName());

			first_name = first_name.substring(0, 34);

			logicalCard.setF_NAMES(first_name);

		}

		else {

			logicalCard.setF_NAMES(toTranslit(customer.getName()));
		}

		logicalCard.setSURNAME(customer.getSurname() == null ? ""
				: toTranslit(customer.getSurname()));
		
		logicalCard.setRANGE_ID(BigDecimal.valueOf(Long.valueOf(HUMO_RANGEID)));
		logicalCard.setBRANCH(HUMO_BRANCH_ID);

		return logicalCard;
	}
	public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCard(
			Customer customer, String cardFirstName) {

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
	public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCardKorp(
			Customer customer) {

		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		// physicalCard.setCARD_NAME(customer.getP_family() + " "
		// + customer.getP_first_name());

		String cardFirstName=customer.getName();
		
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
	public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCard2(
			CustomerHumo customer) {

		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		System.out.println("CARD_NAME "+customer.getCardName());
		ISLogger.getLogger().error("CARD_NAME "+customer.getCardName());

		physicalCard.setCARD_NAME(customer.getCardName());
		physicalCard.setSTATUS1("1");
		physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

		return physicalCard;

	}
	public static RowType_Agreement getRowType_Agreement(String HUMO_BINCOD,
			String HUMO_BANK_C, String branchId, Customer customer) {
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
		agrInfo.setREP_LANG("1");
		agrInfo.setSTATUS("10");
		agrInfo.setPRODUCT("01");
		agrInfo.setENROLLED(calendar);
		// agrInfo.setCLIENT(customerInfo.value.getCLIENT());

		return agrInfo;
	}

	public static RowType_Agreement getNewRowType_Agreement(String HUMO_BINCOD,
			String HUMO_BANK_C, String branchId, String cardCode,
			Customer customer, String alias, String idClientHumo) {

		System.out.println("******customer:" + customer);
		RowType_Agreement agrInfo = new RowType_Agreement();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		agrInfo.setCLIENT(idClientHumo);

		System.out.println("idClientHumo78787: " + idClientHumo);

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

	public static SS_HUMO_TYPE_OF_CARD getCardType(String vidCard,
			String branch, Connection c) throws SQLException {

		SS_HUMO_TYPE_OF_CARD humoCardType = new SS_HUMO_TYPE_OF_CARD();

		// Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps=null;
		try {

			// c = ConnectionPool.getConnection();
			 ps= c
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
			// ConnectionPool.close(c);
			ps.close();
			rs.close();
		}

		return humoCardType;
	}

	public static ResponsInfo createCardsHumoKorp(Connection c, ResultSet rs,String alias,
			String branch, String clientId, String cardCode,String personCode,
			String vidCard, String acc,globus.IssuingWS.IssuingPortProxy issuingPortProxy,OperationConnectionInfo connectionInfo,String HUMO_BANK_C) throws Exception {
		ResponsInfo responsInfo = new ResponsInfo();
		try {	
		 // acc = getAcc(c,cardName.trim(),clientId, branch);
	    OperationResponseInfo orInfo = null;	
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		ListType_CardInfo cards = new ListType_CardInfo();
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
	
		
		SS_HUMO_TYPE_OF_CARD cardType = getCardType(vidCard, branch, c);
		HUMO_CHIPAPPID = cardType.getChip_app_id();
		HUMO_BRANCHID = cardType.getBranch_id();
		HUMO_RANGEID = cardType.getRange_id();
		HUMO_BINCOD=cardType.getBin();
		HumoClientInfo humoClientInfo = getClientInfo(c,branch,clientId,cardCode);
		Customer customer = humoClientInfo.getCustomer();
		String clType=humoClientInfo.getClType();
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(getRowType_Customer(customer, personCode, clType,cardCode, c, rs));
        accountInfo.setBase_info(getRowType_AccountInfo_Base(acc,customer.getBranch(), c,rs));
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
		accountsListInfo.value = ltaccounts;
		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(HUMO_CHIPAPPID));
        cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
		cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCardKorp(customer));
		cardInfo.setEMV_Data(eMV_Data);
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		cardsListInfo.value = cards;
    
       
	
		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(getRowType_Agreement(HUMO_BINCOD, HUMO_BANK_C, HUMO_BRANCHID, customer));
			

///////////////////////////////// Запрос в Humo //////////////////////////////////////////////
	    
		 orInfo = issuingPortProxy.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo,agreementInfo, accountsListInfo, cardsListInfo);

////////////////////////////////////////////////////////////////////////////////////////////////

			if (orInfo.getError_description() != null) {

				responsInfo.setResponseCode(orInfo.getResponse_code());
				responsInfo.setErrorDescription(orInfo.getError_description());
				responsInfo.setErrorAction(orInfo.getError_action());
				responsInfo.setSuccessful(false);

				System.out.println("Response Info:");
				System.out.println("-------------------------------");
				System.out.println("Response code = "+ orInfo.getResponse_code());
				System.out.println("Error action = "+ orInfo.getError_action());
				System.out.println("Error description = "+ orInfo.getError_description());
				System.out.println("-------------------------------");

				ISLogger.getLogger().error("Response Info:");
				ISLogger.getLogger().error("-------------------------------");
				ISLogger.getLogger().error("Response code = "+ orInfo.getResponse_code());
				ISLogger.getLogger().error("Error action = "+ orInfo.getError_action());
				ISLogger.getLogger().error("Error description = "+ orInfo.getError_description());
				ISLogger.getLogger().error("-------------------------------");

			}

			else  {

				insertNewClient(getRowType_Customer(customer, personCode, clType,cardCode, c,rs),customerInfo.value.getCLIENT(), c);
				insertNewClientAgreement(connectionInfo, agreementInfo, c);
				insertNewClientAccounts(accountsListInfo, cardsListInfo, acc, c);
				insertNewClientHumoCards(cardsListInfo, clientId,customer.getBranch(), alias, acc, c,accountsListInfo);
				responsInfo.setSuccessful(true);
				System.out.println("Карта открыта в HUMO");

			}

		}catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		}
		return responsInfo;
	}

	public static void insertRangAndBranchId(Connection c, String clientId,
			String branch, String branchId, String rangeId, String cardType) {

		int res = 0;
		PreparedStatement ps = null;
		try {

			ps = c.prepareStatement("update HUMO_CARD_OPEN set range_id=?, branch_id=?  where client=? and branch=? and card_type=?");

			ps.setString(1, rangeId);
			ps.setString(2, branchId);
			ps.setString(3, clientId);
			ps.setString(4, branch);
			ps.setString(5, cardType);
			//

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

	}

	public static int insertAccounts(
			ListType_AccountInfoHolder accountsListInfo,
			ListType_CardInfoHolder cardsListInfo, String clientIdHumo,
			String acc, Connection c) {
		// Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?,?)");

			// System.out.println("accountsListInfo7777 " + accountsListInfo);
			System.out.println("Humo client="
					+ cardsListInfo.value.getRow(0).getLogicalCard()
							.getCLIENT());

			// ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
			// .getCLIENT()== null ? "" :
			// cardsListInfo.value.getRow(0).getLogicalCard()
			// .getCLIENT());
			ps.setString(1, clientIdHumo);

			ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info()
					.getACCOUNT_NO() == null ? null : accountsListInfo.value
					.getRow(0).getBase_info().getACCOUNT_NO());
			// ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
			// .getCARD_ACCT()== null ? "" :
			// accountsListInfo.value.getRow(0).getBase_info()
			// .getCARD_ACCT());

			ps.setString(3, acc == null ? "" : acc);

			ps.setString(4, acc == null ? "" : acc);

			ps.setDate(5, accountsListInfo.value.getRow(0).getBase_info()
					.getAB_EXPIRITY() == null ? null : new java.sql.Date(
					accountsListInfo.value.getRow(0).getBase_info()
							.getAB_EXPIRITY().getTimeInMillis()));
			ps.setDate(6, accountsListInfo.value.getRow(0).getBase_info()
					.getCREATED_DATE() == null ? null : new java.sql.Date(
					accountsListInfo.value.getRow(0).getBase_info()
							.getCREATED_DATE().getTimeInMillis()));

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

		return res;
	}

	public static int insertHumoCards(ListType_CardInfoHolder cardsListInfo,
			String clientId, String branch, String clientHumoId,
			String cardName, String alias, String acc, Connection c) {
		int res = 0;
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
					+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			// ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
			// .getCLIENT()== null ? "0" :
			// cardsListInfo.value.getRow(0).getLogicalCard()
			// .getCLIENT());
			ps.setString(1, clientHumoId);

			String psivdaPAN = cardsListInfo.value.getRow(0).getLogicalCard()
					.getCARD() == null ? "0" : cardsListInfo.value.getRow(0)
					.getLogicalCard().getCARD();
			ps.setString(2, clientId == null ? "0" : clientId);
			ps.setString(3, psivdaPAN);
			ps.setString(4, branch == null ? "0" : branch);
			ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS1() == null ? "0" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS1());
			ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS2() == null ? "0" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS2());
			ps.setDate(7, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getEXPIRY1() == null ? null : new java.sql.Date(
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getEXPIRY1().getTimeInMillis()));
			ps.setDate(8, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getEXPIRITY2() == null ? null : new java.sql.Date(
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getEXPIRITY2().getTimeInMillis()));

			ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEW() == null ? "0" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getRENEW());
			// ps.setString(10, cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getCARD_NAME()== null ? "0" :
			// cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getCARD_NAME());
			cardName = cardName.trim();
			if (cardName.length() > 24) {

				cardName = cardName.substring(0, 24);

			}

			ps.setString(10, cardName);
			// ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME()== null ? "0" :
			// cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME());
			// ps.setString(12, cardsListInfo.value.getRow(0).getLogicalCard()
			// .getM_NAME()== null ? "0" :
			// cardsListInfo.value.getRow(0).getLogicalCard()
			// .getM_NAME());

			ps.setString(11, "");
			ps.setString(12, "");

			ps.setString(13, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTOP_CAUSE() == null ? "0" : cardsListInfo.value
					.getRow(0).getPhysicalCard().getSTOP_CAUSE());
			ps.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEWED_CARD() == null ? "0" : cardsListInfo.value
					.getRow(0).getPhysicalCard().getRENEWED_CARD());
			ps.setBigDecimal(15, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getDESIGN_ID() == null ? null
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getDESIGN_ID());
			ps.setString(16, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getINSTANT() == null ? "0" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getINSTANT());

			ps.setString(17, acc);
			ps.setString(18, acc);

			// ps.setString(17, getRealCardNumber(psivdaPAN, initNp(alias),
			// HUMO_BANK_C, HUMO_GROUPC));

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}
		return res;
	}

	public static int insertAgreement(OperationConnectionInfo connectionInfo,
			RowType_AgreementHolder agreementInfo, String clintIdHumo,
			BigDecimal agrNom, Connection c) {
		// Connection c = null;
		int res = 0;
		PreparedStatement ps = null;

		try {

			// c = ConnectionPool.getConnection();
			// System.out.println("agreementInfo:"+agreementInfo);
			ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
			// ps.setString(1, agreementInfo.value.getAGRE_NOM()== null ? "0" :
			// agreementInfo.value.getAGRE_NOM().toString());

			System.out.println("agrNom: " + agrNom.toString());
			ps.setString(1, agrNom.toString());

			// ps.setString(2, agreementInfo.value.getCLIENT()== null ? "0" :
			// agreementInfo.value.getCLIENT());
			ps.setString(2, clintIdHumo);
			System.out.println("9999cliintIdHumo: " + clintIdHumo);
			ps.setString(3, connectionInfo.getGROUPC() == null ? ""
					: connectionInfo.getGROUPC());
			ps.setString(4, agreementInfo.value.getBINCOD() == null ? "0"
					: agreementInfo.value.getBINCOD());
			ps.setString(5, agreementInfo.value.getBANK_CODE() == null ? "0"
					: agreementInfo.value.getBANK_CODE());
			ps.setString(6, agreementInfo.value.getBRANCH() == null ? "0"
					: agreementInfo.value.getBRANCH());
			ps.setString(7, connectionInfo.getBANK_C() == null ? "0"
					: connectionInfo.getBANK_C());
			ps.setString(8, agreementInfo.value.getPRODUCT() == null ? "0"
					: agreementInfo.value.getPRODUCT());
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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

		return res;
	}

	public static int insertClient(RowType_Customer customerInfo,
			String client, Connection c) throws Exception {
		// Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
					+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
					+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, client == null ? "" : client);
			ps.setString(2, customerInfo.getF_NAMES() == null ? "0"
					: customerInfo.getF_NAMES());
			ps.setString(3, customerInfo.getCL_TYPE() == null ? "0"
					: customerInfo.getCL_TYPE());
			ps.setString(4, customerInfo.getCLIENT_B() == null ? "0"
					: customerInfo.getCLIENT_B());
			ps.setString(5, customerInfo.getSURNAME() == null ? "0"
					: customerInfo.getSURNAME());
			// ps.setString(6, customerInfo.getM_NAME()== null ? "0" :
			// customerInfo.getM_NAME());
			ps.setString(6, "0");

			ps.setDate(7, customerInfo.getDOC_SINCE() == null ? null
					: new java.sql.Date(customerInfo.getDOC_SINCE()
							.getTimeInMillis()));
			ps.setDate(8, customerInfo.getB_DATE() == null ? null
					: new java.sql.Date(customerInfo.getB_DATE()
							.getTimeInMillis()));
			ps.setString(9, customerInfo.getRESIDENT() == null ? "0"
					: customerInfo.getRESIDENT());
			ps.setString(10, customerInfo.getSTATUS() == null ? "0"
					: customerInfo.getSTATUS());
			ps.setString(11,
					customerInfo.getSEX() == null ? "0" : customerInfo.getSEX());
			ps.setString(12, customerInfo.getSERIAL_NO() == null ? "0"
					: customerInfo.getSERIAL_NO());
			ps.setString(13, customerInfo.getID_CARD() == null ? "0"
					: customerInfo.getID_CARD());
			ps.setString(14, customerInfo.getR_CITY() == null ? "0"
					: customerInfo.getR_CITY());
			ps.setString(15, customerInfo.getR_STREET() == null ? "0"
					: toTranslit(customerInfo.getR_STREET()));
			ps.setString(16, customerInfo.getR_E_MAILS() == null ? "0"
					: customerInfo.getR_E_MAILS());
			ps.setString(17, customerInfo.getR_MOB_PHONE() == null ? "0"
					: customerInfo.getR_MOB_PHONE());
			ps.setString(18, customerInfo.getR_PHONE() == null ? "0"
					: customerInfo.getR_PHONE());
			ps.setString(19, customerInfo.getR_CNTRY() == null ? "0"
					: customerInfo.getR_CNTRY());
			ps.setString(20, customerInfo.getISSUED_BY() == null ? "0"
					: customerInfo.getISSUED_BY());
			ps.setString(21, customerInfo.getPERSON_CODE() == null ? "0"
					: customerInfo.getPERSON_CODE());
			ps.setString(22, customerInfo.getDOC_TYPE() == null ? "0"
					: customerInfo.getDOC_TYPE());
			// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
			// ? null
			// : new java.sql.Date(customerInfo.value
			// .getREC_DATE().getTime().getTime()));
			ps.setDate(23, customerInfo.getREC_DATE() == null ? null
					: new java.sql.Date(customerInfo.getREC_DATE()
							.getTimeInMillis()));

			res = ps.executeUpdate();
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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

		return res;
	}

	public static Customer getCustomerJ(Connection c, String branch,String client) throws SQLException {
		Customer customer = null;

		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			ps = c.prepareStatement("SELECT t.*, Transymbol2(t.short_name) as short_name2, substr(Transymbol2(t.director_name),1,34) as director_name2  FROM v_client_humo t WHERE t.branch = ? and t.id_client=?");

			ps.setString(1, branch);
			ps.setString(2, client);
			rs = ps.executeQuery();
			if (rs.next()) {

				customer = new Customer(
						rs.getLong("id"),
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
						null,// p_code_capacity
						null,// p_capacity_status_date
						null,// p_capacity_status_place
						null,// p_num_certif_capacity
						rs.getString("PHONE"),
						null,// p_phone_mobile
						rs.getString("email"),
						null,// p_pension_sertif_serial
						null,// p_code_gender
						null,// p_code_nation
						rs.getString("DIRECTOR_BIRTH_PLACE"),
						rs.getString("DIRECTOR_ADDRESS"),// p_code_birth_distr
						null,// p_type_document
						rs.getDate("DIRECTOR_PASSP_DATE_END"),
						rs.getString("DIRECTOR_ADDRESS"),
						rs.getString("DIRECTOR_ADDRESS"),// p_code_adr_distr
						null, // p_inps
						null,// p_family
						rs.getString("DIRECTOR_NAME2"),// p_first_name
						null);// p_patronymic

			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			// ConnectionPool.close(c);
			// DbUtils.closeStmt(cs);
			// DbUtils.closeStmt(ps);
			// DbUtils.closeResultSet(rs);
			ps.close();
			rs.close();
		}

		// System.out.println("Familya " + customer.getP_family());
		return customer;
	}

	public static Customer getCustomer(Connection c, String branch,
			String itemId) throws SQLException {
		Customer customer = new Customer();

		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		try {

			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("SELECT t.*, Transymbol2(t.name) as name2 FROM V_HUMO_CLIENT_P t WHERE t.branch = ? and t.id_client=?");
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getLong("id"),
						rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name2"), rs.getString("code_country"),
						rs.getString("code_type"),
						rs.getString("code_resident"),
						rs.getString("code_subject"),
						rs.getInt("sign_registr"), rs.getString("code_form"),
						rs.getDate("date_open"), rs.getDate("date_close"),
						rs.getInt("state"), rs.getDate("p_birthday"),
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
						rs.getString("p_inps"), rs.getString("p_family"),
						rs.getString("p_first_name"),
						rs.getString("p_patronymic"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			// ConnectionPool.close(c);
			// DbUtils.closeStmt(cs);
			// DbUtils.closeStmt(ps);
			// DbUtils.closeResultSet(rs);
			ps.close();
			rs.close();
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
			// ConnectionPool.close(c);
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
			String idClient) throws SQLException {

		List<AccountHumo> list = new ArrayList<AccountHumo>();
		Connection c = null;
		ResultSet rs = null;
		Statement s=null;

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
			 s = c.createStatement();
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
			s.close();
			rs.close();
			// ConnectionPool.close(c);
		}

		return list;
	}

	public static List<RefData> getCardType(String branch) {
		System.out.println("777");
		return RefDataService
				.getRefData(
						//"select code data, name label from ss_humo_p_card_type order by code",
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

	public static String getClientType(String branch, String searchClients,
			String un, String pw, String alias) throws SQLException {

		System.out.println("searchClients88:" + searchClients);
		String type = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps=null;

		try {

			c = ConnectionPool.getConnection(alias);
			ISLogger.getLogger().error(" ALIAS777 " + alias);
			 ps = c
					.prepareStatement("SELECT code_subject FROM client where branch=? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, searchClients);

			rs = ps.executeQuery();
			while (rs.next()) {

				type = rs.getString("code_subject");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			// ConnectionPool.close(c);
		}

		return type;
	}

	public static String getCodeB(String alias, String code) throws SQLException {

		String codeB = null;
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps=null;

		try {

			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			 ps = c
					.prepareStatement("SELECT code_b FROM ss_humo_card_type where code=?");

			ps.setString(1, code);

			rs = ps.executeQuery();
			while (rs.next()) {

				codeB = rs.getString("code_b");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			// ConnectionPool.close(c);
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
		for (int i = 0; i < text.length(); i++) {
			String l = text.substring(i, i + 1);
			if (letters.containsKey(l)) {
				sb.append(letters.get(l));
			} else {
				sb.append(l);
			}
		}
		return sb.toString();
	}

	public static String getAcc(Connection c, String typeCard, String clientId,
			String branch) {

		String acc = null;

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			// Statement s = c.createStatement();
			ps = c
			// .prepareStatement("SELECT ACCOUNT FROM HUMO_NEW_EMP_FIZIK where client_id=? and branch=?");

			.prepareStatement("SELECT ACCOUNT FROM HUMO_CARD_OPEN where client=? and branch=? and card_type=?");

			ps.setString(1, clientId);
			ps.setString(2, branch);
			ps.setString(3, typeCard);

			rs = ps.executeQuery();
			while (rs.next()) {

				acc = rs.getString("ACCOUNT");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close(ps);
			// ConnectionPool.close(c);
		}

		return acc;
	}

//	public static ResponsInfo newAgreementHumo(Connection c, String alias,
//			String branch, String clientId, String cardCode, String personCode,
//			String vidCard, String firstCardName) throws Exception {
//		Customer customer = null;
//		String cl_type;
//		SS_HUMO_TYPE_OF_CARD cardType = getCardType(vidCard, branch, c);
//		HUMO_BANK_C = cardType.getBank_c();
//		HUMO_GROUPC = cardType.getGroup_c();
//		HUMO_BINCOD = cardType.getBin();
//		// HUMO_BINCOD = "90000301";
//
//		HUMO_CHIPAPPID = cardType.getChip_app_id();
//		HUMO_BRANCHID = cardType.getBranch_id();
//
//		HUMO_RANGEID = cardType.getRange_id();
//
//		if (cardCode.equals("002") || cardCode.equals("007")
//				|| cardCode.equals("008")) {
//
//			cl_type = "2";
//
//			customer = HumoCardsService.getCustomerJ(c, branch, clientId);
//
//			System.out.println("getCustomer:" + customer);
//
//			System.out.println("clientId:" + clientId);
//
//		}
//
//		else {
//
//			customer = HumoCardsService.getCustomer(c, branch, clientId);
//			cl_type = "1";
//			System.out.println("getCustomer:" + customer);
//		}
//
//		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
//		SS_HUMO_TYPE_OF_CARD cardType1 = getCardType(vidCard, branch, c);
//
//		System.out.println("vidCard:" + vidCard + " branch:" + branch);
//
//		System.out.println("cardType:" + cardType1.toString());
//
//		HUMO_BANK_C = cardType1.getBank_c();
//		HUMO_GROUPC = cardType1.getGroup_c();
//		HUMO_BINCOD = cardType1.getBin();
//		HUMO_BRANCHID = cardType1.getBranch_id();
//		ResponsInfo responsInfo = new ResponsInfo();
//
//		try {
//
//			globus.IssuingWS.IssuingPortProxy issuingPortProxy = initNp(alias);
//
//			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
//
//			connectionInfo.setBANK_C(HUMO_BANK_C);
//			connectionInfo.setGROUPC(HUMO_GROUPC);
//
//			String externalSesionId = getExternalSession();
//			System.out.println("externalSesionId:" + externalSesionId);
//			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
//
//			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
//					getNewRowType_Agreement(HUMO_BINCOD, HUMO_BANK_C,
//							HUMO_BRANCHID, cardCode, customer, alias,
//							getClientIdHumo(clientId, branch, c)));
//			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
//			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
//
//			// //////////////////////////////// Запрос в Humo newAgremmentHumo
//			// //////////////////////////////////////////
//
//			OperationResponseInfo operationResponsInfo = issuingPortProxy
//					.newAgreement(connectionInfo, agreementInfo,
//							accountsListInfo, cardsListInfo);
//
//			// ////////////////////////////////////////////////////////////////////////////////////////////////
//
//			if (operationResponsInfo.getError_description() == null) {
//				System.out.println("+++agr:" + agreementInfo.value.toString());
//				BigDecimal agrNom = agreementInfo.value.getAGRE_NOM();
//				int resClient = 0, resAgreement = 0, resAccount = 0, resCards = 0;
//				resAgreement = insertAgreement(connectionInfo, agreementInfo,
//						getClientIdHumo(clientId, branch, c), agrNom, c);
//				System.out.println("resAgreement:" + resAgreement);
//
//				responsInfo.setSuccessful(true);
//
//				System.out.println("New Agreement создан:"
//						+ agreementInfo.value.getAGRE_NOM() + " client: "
//						+ agreementInfo.value.getCLIENT());
//				// String acc = getAcc(un,pw,alias,clientId, branch);
//
//				// acc= createAccountHumo(alias, customer, un, pw, branch,
//				// getCodeB(alias, cardCode));
//
//				String acc2 = getAcc(c, firstCardName.trim(), clientId, branch);
//
//				// Map<String, String> hashMap=getAccInfo(clientId, branch);
//				//
//				// String exsist2=isExsistAcc2(branch, hashMap.get("acc_bal"),
//				// hashMap.get("acc_cur"), clientId, hashMap.get("acc_num"));
//				//
//				// if(exsist2==null){
//				//
//				// acc2=createAccountHumo(alias, customer, un, pw, branch,
//				// hashMap.get("acc_bal"),hashMap.get("acc_num"),hashMap.get("acc_cur"));
//				// }
//				//
//				// else{
//				// acc2=exsist2;
//				//
//				// }
//				//
//				//
//
//				if (acc2 != null) {
//					addInfo4AgreementHumo(c, alias, branch, clientId, cardCode,
//							personCode, vidCard, acc2,
//							agreementInfo.value.getAGRE_NOM(), HUMO_CHIPAPPID,
//							firstCardName);
//				}
//				insertRangAndBranchId(c, clientId, branch, HUMO_BRANCHID,
//						HUMO_RANGEID.toString(), firstCardName.trim());
//			}
//			//
//			else {
//				responsInfo.setResponseCode(operationResponsInfo
//						.getResponse_code());
//				responsInfo.setErrorDescription(operationResponsInfo
//						.getError_description());
//				responsInfo.setErrorAction(operationResponsInfo
//						.getError_action());
//				responsInfo.setSuccessful(false);
//
//				// res.setCode(0);
//				// res.setName(orInfo2.getError_description());
//				System.out.println("Response Info output:");
//				System.out.println("-------------------------------");
//				System.out.println("Response code = "
//						+ operationResponsInfo.getResponse_code());
//				System.out.println("Error description = "
//						+ operationResponsInfo.getError_description());
//				System.out.println("Error action = "
//						+ operationResponsInfo.getError_action());
//				System.out.println("-------------------------------");
//
//				ISLogger.getLogger().error(
//						"\n\n Response code ="
//								+ operationResponsInfo.getResponse_code()
//								+ "\n\n");
//				ISLogger.getLogger().error(
//						"\n Error description = "
//								+ operationResponsInfo.getError_description()
//								+ "\n");
//			}
//
//		}
//
//		catch (RemoteException e) {
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			LtLogger.getLogger().error(CheckNull.getPstr(e));
//			e.printStackTrace();
//
//		}
//		return responsInfo;
//	}

//	public static ResponsInfo addInfo4AgreementHumo(Connection c, String alias,
//			String branch, String clientId, String cardCode, String personCode,
//			String vidCard, String acc, BigDecimal agreNom, Long humoChipAppID,
//			String firstCardName) throws Exception {
//		Customer customer = null;
//		String cl_type;
//
//	
//
//		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
//
//		SS_HUMO_TYPE_OF_CARD cardType = getCardType(vidCard, branch, c);
//
//		System.out.println("vidCard:" + vidCard + " branch:" + branch);
//
//		System.out.println("cardType:" + cardType.toString());
//
//		HUMO_BANK_C = cardType.getBank_c();
//		HUMO_GROUPC = cardType.getGroup_c();
//		HUMO_BINCOD = cardType.getBin();
//		HUMO_BRANCHID = cardType.getBranch_id();
//		ResponsInfo responsInfo = new ResponsInfo();
//
//		try {
//
//			globus.IssuingWS.IssuingPortProxy issuingPortProxy = initNp(alias);
//
//			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
//
//			connectionInfo.setBANK_C(HUMO_BANK_C);
//			connectionInfo.setGROUPC(HUMO_GROUPC);
//
//			String externalsesionId = getExternalSession();
//			System.out.println("externalsesionId:" + externalsesionId);
//			connectionInfo.setEXTERNAL_SESSION_ID(externalsesionId);
//
//			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
//					getRowType_Agreement(HUMO_BINCOD, HUMO_BANK_C,
//							HUMO_BRANCHID, cardCode, customer));
//			RowType_AccountInfo accountInfo = new RowType_AccountInfo();
//
//			// String acc =""; //optashash
//			accountInfo.setBase_info(getRowType_AccountInfo_Base(acc,
//					customer.getBranch(), c));
//			ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
//			ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
//			ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
//			System.out.println("****ltaccounts " + ltaccounts);
//			accountsListInfo.value = ltaccounts;
//
//			RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
//			eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));
//
//			RowType_CardInfo cardInfo = new RowType_CardInfo();
//			cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard(customer,
//					HUMO_RANGEID));
//			cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCard(customer,
//					firstCardName));
//			cardInfo.setEMV_Data(eMV_Data);
//			ListType_CardInfo cards = new ListType_CardInfo();
//			cards.setRow(new RowType_CardInfo[] { cardInfo });
//			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
//			cardsListInfo.value = cards;
//
//			KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
//
//			mainAgreementInfo.setAGRE_NOM(agreNom);
//
//			// //////////////////////////////// Запрос в Humo newAgremmentHumo
//			// //////////////////////////////////////////
//			System.out.println("77777:"
//					+ cardsListInfo.value.getRow(0).getPhysicalCard()
//							.getCARD_NAME());
//
//			OperationResponseInfo operationResponsInfo = issuingPortProxy
//					.addInfo4Agreement(connectionInfo, mainAgreementInfo,
//							accountsListInfo, cardsListInfo);
//
//			System.out.println("++++++++++cardsListInfo: "
//					+ cardsListInfo.value.toString());
//			// ////////////////////////////////////////////////////////////////////////////////////////////////
//
//			if (responsInfo.getErrorDescription() == null) {
//
//				int resClient = 0, resAgreement = 0, resAccount = 0, resCards = 0;
//				// resAgreement=insertAgreement(connectionInfo, agreementInfo);
//				System.out.println("resAgreement:" + resAgreement);
//				resAccount = insertAccounts(accountsListInfo, cardsListInfo,
//						getClientIdHumo(clientId, branch, c), acc, c);
//				System.out.println("resAccount:" + resAccount);
//
//				resCards = insertHumoCards(cardsListInfo, clientId,
//						customer.getBranch(),
//						getClientIdHumo(clientId, branch, c), firstCardName
//								+ customer.getName(), alias, acc, c);
//				System.out.println("resCards:" + resCards);
//				responsInfo.setSuccessful(true);
//
//				System.out.println("Доб карта открыта в HUMO:"
//						+ agreementInfo.value.getAGRE_NOM() + " client: "
//						+ agreementInfo.value.getCLIENT());
//
//			}
//
//			else {
//
//				responsInfo.setResponseCode(operationResponsInfo
//						.getResponse_code());
//				responsInfo.setErrorDescription(operationResponsInfo
//						.getError_description());
//				responsInfo.setErrorAction(operationResponsInfo
//						.getError_action());
//				responsInfo.setSuccessful(false);
//
//				// res.setCode(0);
//				// res.setName(orInfo2.getError_description());
//				System.out.println("Response Info output:");
//				System.out.println("-------------------------------");
//				System.out.println("Response code = "
//						+ operationResponsInfo.getResponse_code());
//				System.out.println("Error description = "
//						+ operationResponsInfo.getError_description());
//				System.out.println("Error action = "
//						+ operationResponsInfo.getError_action());
//				System.out.println("-------------------------------");
//
//				ISLogger.getLogger().error(
//						"\n\n Response code ="
//								+ operationResponsInfo.getResponse_code()
//								+ "\n\n");
//				ISLogger.getLogger().error(
//						"\n Error description = "
//								+ operationResponsInfo.getError_description()
//								+ "\n");
//			}
//
//		}
//
//		catch (RemoteException e) {
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			LtLogger.getLogger().error(CheckNull.getPstr(e));
//			e.printStackTrace();
//
//		}
//		return responsInfo;
//	}

	public static ArrayList<String> getSizeAgreement(String clientB,
			String branch) throws SQLException {

		ArrayList<String> array = new ArrayList<String>();
		HumoCards humocards = new HumoCards();
		Connection c = null;
		ResultSet rs=null;
		PreparedStatement ps=null;

		try {
			c = ConnectionPool.getConnection();
			 ps = c
					.prepareStatement("select count(*) ct FROM bf_empc_agreement where client=? and branch=?");
			ps.setString(1, clientB);
			ps.setString(2, branch);

			 rs = ps.executeQuery();
			while (rs.next()) {
				array.add(rs.getString("client"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			// ConnectionPool.close(c);
		}

		if (array.size() > 1) {
			array = null;

		}

		return array;
	}

	public static String getClientIdHumo(String clientB, String branch,
			Connection c) throws SQLException {

		String clientIdHumo = null;
		HumoCards humocards = new HumoCards();
		// Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select client FROM humo_cards where client_b=? and branch=?");
			ps.setString(1, clientB);
			ps.setString(2, branch);

			rs = ps.executeQuery();
			if (rs.next()) {
				clientIdHumo = rs.getString("client");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			 
			// c.close();
		}

		System.out.println("===client: " + clientIdHumo);
		return clientIdHumo;
	}

	public static void insertNewClientAccounts(
			ListType_AccountInfoHolder accountsListInfo,
			ListType_CardInfoHolder cardsListInfo, String acc, Connection c) {
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?,?)");

			// System.out.println("accountsListInfo7777 " + accountsListInfo);
			

			ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
					.getCLIENT() == null ? "" : cardsListInfo.value.getRow(0)
					.getLogicalCard().getCLIENT());
			ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info()
					.getACCOUNT_NO() == null ? null : accountsListInfo.value
					.getRow(0).getBase_info().getACCOUNT_NO());
			// ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
			// .getCARD_ACCT()== null ? "" :
			// accountsListInfo.value.getRow(0).getBase_info()
			// .getCARD_ACCT());

			ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
					.getCARD_ACCT()== null ? "" :accountsListInfo.value.getRow(0).getBase_info()
							.getCARD_ACCT());

			ps.setString(4, acc == null ? "" : acc);

			ps.setDate(5, accountsListInfo.value.getRow(0).getBase_info()
					.getAB_EXPIRITY() == null ? null : new java.sql.Date(
					accountsListInfo.value.getRow(0).getBase_info()
							.getAB_EXPIRITY().getTimeInMillis()));
			ps.setDate(6, accountsListInfo.value.getRow(0).getBase_info()
					.getCREATED_DATE() == null ? null : new java.sql.Date(
					accountsListInfo.value.getRow(0).getBase_info()
							.getCREATED_DATE().getTimeInMillis()));

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}
	}

	public static void insertNewClientHumoCards(
			ListType_CardInfoHolder cardsListInfo, String clientId,
			String branch, String alias, String acc, Connection c,ListType_AccountInfoHolder accountsListInfo) {
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
					+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct,ACCOUNT_NO) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
					.getCLIENT() == null ? "" : cardsListInfo.value.getRow(0)
					.getLogicalCard().getCLIENT());
			ps.setString(2, clientId == null ? "" : clientId);
			ps.setString(3, cardsListInfo.value.getRow(0).getLogicalCard()
					.getCARD() == null ? "" : cardsListInfo.value.getRow(0)
					.getLogicalCard().getCARD());
			ps.setString(4, branch == null ? "" : branch);
			ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS1() == null ? "" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS1());
			ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getSTATUS2() == null ? "" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS2());
			ps.setDate(7, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getEXPIRY1() == null ? null : new java.sql.Date(
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getEXPIRY1().getTimeInMillis()));
			ps.setDate(8, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getEXPIRITY2() == null ? null : new java.sql.Date(
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getEXPIRITY2().getTimeInMillis()));

			ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEW() == null ? "" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getRENEW());

			String psevdaPAN = cardsListInfo.value.getRow(0).getPhysicalCard()
					.getCARD_NAME() == null ? "" : cardsListInfo.value
					.getRow(0).getPhysicalCard().getCARD_NAME();
			ps.setString(10, psevdaPAN);
			// ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME()== null ? "" :
			// cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME());
			// ps.setString(12, cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME()== null ? "" :
			// cardsListInfo.value.getRow(0).getPhysicalCard()
			// .getMC_NAME());

			ps.setString(11, "");
			ps.setString(12, "");

			ps.setString(13,
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTOP_CAUSE() == null ? "" : cardsListInfo.value
							.getRow(0).getPhysicalCard().getSTOP_CAUSE());
			ps.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getRENEWED_CARD() == null ? "" : cardsListInfo.value
					.getRow(0).getPhysicalCard().getRENEWED_CARD());
			ps.setBigDecimal(15, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getDESIGN_ID() == null ? null
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getDESIGN_ID());
			ps.setString(16, cardsListInfo.value.getRow(0).getPhysicalCard()
					.getINSTANT() == null ? "" : cardsListInfo.value.getRow(0)
					.getPhysicalCard().getINSTANT());

			ps.setString(17, accountsListInfo.value.getRow(0).getBase_info()
					.getCARD_ACCT()== null ? "" :accountsListInfo.value.getRow(0).getBase_info()
							.getCARD_ACCT());
			ps.setString(18, acc);
			ps.setBigDecimal(19, accountsListInfo.value.getRow(0).getBase_info()
					.getACCOUNT_NO() == null ? null : accountsListInfo.value
					.getRow(0).getBase_info().getACCOUNT_NO());
			

			// ps.setString(17,getRealCardNumber(psevdaPAN, initNp(alias),
			// HUMO_BANK_C, HUMO_GROUPC));

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}
	}

	public static void insertNewClientAgreement(
			OperationConnectionInfo connectionInfo,
			RowType_AgreementHolder agreementInfo, Connection c) {
		// Connection c = null;
		PreparedStatement ps = null;
		try {
			
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
			ps.setString(1,
					agreementInfo.value.getAGRE_NOM() == null ? ""
							: agreementInfo.value.getAGRE_NOM().toString());
			ps.setString(2, agreementInfo.value.getCLIENT() == null ? ""
					: agreementInfo.value.getCLIENT());
			ps.setString(3, connectionInfo.getGROUPC() == null ? ""
					: connectionInfo.getGROUPC());
			ps.setString(4, agreementInfo.value.getBINCOD() == null ? ""
					: agreementInfo.value.getBINCOD());
			ps.setString(5, agreementInfo.value.getBANK_CODE() == null ? ""
					: agreementInfo.value.getBANK_CODE());
			ps.setString(6, agreementInfo.value.getBRANCH() == null ? ""
					: agreementInfo.value.getBRANCH());
			ps.setString(7, connectionInfo.getBANK_C() == null ? ""
					: connectionInfo.getBANK_C());
			ps.setString(8, agreementInfo.value.getPRODUCT() == null ? ""
					: agreementInfo.value.getPRODUCT());
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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}
	}

	public static void insertNewClient(RowType_Customer customerInfo,
			String client, Connection c) throws Exception {
		// Connection c = null;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
					+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
					+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, client == null ? "" : client);
			ps.setString(2, customerInfo.getF_NAMES() == null ? ""
					: customerInfo.getF_NAMES());
			ps.setString(3, customerInfo.getCL_TYPE() == null ? ""
					: customerInfo.getCL_TYPE());
			ps.setString(4, customerInfo.getCLIENT_B() == null ? ""
					: customerInfo.getCLIENT_B());
			ps.setString(5, customerInfo.getSURNAME() == null ? ""
					: customerInfo.getSURNAME());
			ps.setString(6, customerInfo.getM_NAME() == null ? ""
					: customerInfo.getM_NAME());
			ps.setDate(7, customerInfo.getDOC_SINCE() == null ? null
					: new java.sql.Date(customerInfo.getDOC_SINCE()
							.getTimeInMillis()));
			ps.setDate(8, customerInfo.getB_DATE() == null ? null
					: new java.sql.Date(customerInfo.getB_DATE()
							.getTimeInMillis()));
			ps.setString(9, customerInfo.getRESIDENT() == null ? ""
					: customerInfo.getRESIDENT());
			ps.setString(10, customerInfo.getSTATUS() == null ? ""
					: customerInfo.getSTATUS());
			ps.setString(11,
					customerInfo.getSEX() == null ? "" : customerInfo.getSEX());
			ps.setString(12, customerInfo.getSERIAL_NO() == null ? ""
					: customerInfo.getSERIAL_NO());
			ps.setString(13, customerInfo.getID_CARD() == null ? ""
					: customerInfo.getID_CARD());
			ps.setString(14, customerInfo.getR_CITY() == null ? ""
					: customerInfo.getR_CITY());
			ps.setString(15, customerInfo.getR_STREET() == null ? ""
					: toTranslit(customerInfo.getR_STREET()));
			ps.setString(16, customerInfo.getR_E_MAILS() == null ? ""
					: customerInfo.getR_E_MAILS());
			ps.setString(17, customerInfo.getR_MOB_PHONE() == null ? ""
					: customerInfo.getR_MOB_PHONE());
			ps.setString(18, customerInfo.getR_PHONE() == null ? ""
					: customerInfo.getR_PHONE());
			ps.setString(19, customerInfo.getR_CNTRY() == null ? ""
					: customerInfo.getR_CNTRY());
			ps.setString(20, customerInfo.getISSUED_BY() == null ? ""
					: customerInfo.getISSUED_BY());
			ps.setString(21, customerInfo.getPERSON_CODE() == null ? ""
					: customerInfo.getPERSON_CODE());
			ps.setString(22, customerInfo.getDOC_TYPE() == null ? ""
					: customerInfo.getDOC_TYPE());
			// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
			// ? null
			// : new java.sql.Date(customerInfo.value
			// .getREC_DATE().getTime().getTime()));
			ps.setDate(23, customerInfo.getREC_DATE() == null ? null
					: new java.sql.Date(customerInfo.getREC_DATE()
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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}
	}

	static String getRealCardNumber(String pseudoCard, IssuingPortProxy proxy,
			String bankC, String groupC) throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			ISLogger.getLogger().error("externalSesionId: " + externalSesionId);
			ISLogger.getLogger().error("pseudoCard: "+pseudoCard);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			RowType_GetRealCard_Request request = new RowType_GetRealCard_Request(
					pseudoCard);

			proxy.getRealCard(connectionInfo, request,
					new OperationResponseInfoHolder(), response);
		    
			 ISLogger.getLogger().error("response.value.getRCARD(): "+response.value.getRCARD());

		} catch (Exception e) {

			   e.printStackTrace();
		      ISLogger.getLogger().error("777 "+CheckNull.getPstr(e));

		}

		return response.value.getRCARD();

	}

	public static Map<String, String> getPsevdaPANInHashmap()
			throws SQLException {

		List<RefData> terminal = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> hashMap = new HashMap<String, String>();
		try {

			terminal = new ArrayList<RefData>();
			c = ConnectionPool.getConnection();
			// ps =
			// c.prepareStatement("select t.card,t.client from humo_cards t");

			ps = c.prepareStatement("select b.card,b.client from humo_cards b where b.real_card is null and b.card is not null");
			// ps =
			// c.prepareStatement("select pan,b.mfo_bank from WRONG_HUMO_CARDS b where b.real_card is null");
			rs = ps.executeQuery();
			ISLogger.getLogger().error("get is null card");
			while (rs.next()) {

				hashMap.put(rs.getString("card"), rs.getString("client"));
				ISLogger.getLogger().error("card: "+rs.getString("card")+"client: "+rs.getString("client"));
				// hashMap.put(rs.getString("pan"), rs.getString("mfo_bank"));

			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			c.close();

		}
		return hashMap;
	}

	// public static boolean isExsistAcc(String branch,String accBal, String
	// acc_cur,String clientId,String acc_num) throws SQLException{
	//
	// Connection c = null;
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// boolean isExsistAcc=false;
	//
	// try {
	//
	//
	//
	// c = ConnectionPool.getConnection();
	// ps =
	// c.prepareStatement("select id from account a where a.branch = ? and a.client=? and a.id like ?");
	// ps.setString(1, branch);
	// ps.setString(2, clientId);
	// ps.setString(3, accBal+acc_cur+"_"+clientId+acc_num);
	// rs = ps.executeQuery();
	// System.out.println("searchACC:"+accBal+acc_cur+"_"+clientId+acc_num);
	//
	// while (rs.next()) {
	// System.out.println("isExsistAcc: "+rs.getString("id"));
	// isExsistAcc=true;
	//
	//
	// }
	//
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// rs.close();
	// ps.close();
	// c.close();
	//
	// }
	// return isExsistAcc;
	// }

	public static String isExsistAcc2(String branch, String accBal,
			String acc_cur, String clientId, String acc_num)
			throws SQLException {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isExsistAcc = false;
		String acc = null;

		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id from account a where a.branch = ? and a.client=? and a.id like ?");
			ps.setString(1, branch);
			ps.setString(2, clientId);
			ps.setString(3, accBal + acc_cur + "_" + clientId + acc_num);
			rs = ps.executeQuery();
			System.out.println("searchACC:" + accBal + acc_cur + "_" + clientId
					+ acc_num);

			while (rs.next()) {
				System.out.println("isExsistAcc: " + rs.getString("id"));
				isExsistAcc = true;
				acc = rs.getString("id");

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

	// public static Map<String, String> getAccInfo(String idClient, String
	// branch) throws SQLException{
	//
	// Connection c = null;
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// Map<String, String> hashMap = new HashMap<String, String>();
	//
	// try {
	// c = ConnectionPool.getConnection();
	// ps =
	// c.prepareStatement("select * from HUMO_NEW_EMP_CARD a where a.branch = ? and client_id=?");
	// ps.setString(1, branch);
	// ps.setString(2, idClient);
	//
	// rs = ps.executeQuery();
	//
	// if (rs.next()) {
	//
	// hashMap.put("acc_bal", rs.getString("acc_bal"));
	// hashMap.put("acc_cur", rs.getString("acc_cur"));
	// hashMap.put("acc_num", rs.getString("acc_num"));
	// hashMap.put("acc_bal", rs.getString("acc_bal"));
	// hashMap.put("acc_name", rs.getString("acc_name"));
	//
	//
	// }
	//
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// rs.close();
	// ps.close();
	// c.close();
	//
	// }
	// return hashMap;
	//
	//
	// }
	//
	public static String getPAN(BigInteger accNom, IssuingPortProxy proxy,
			String HUMO_BANK_C, String HUMO_GROUP_C) throws RemoteException {

		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(HUMO_BANK_C);
		connectionInfo.setGROUPC(HUMO_GROUP_C);
		String externalSesionId = getExternalSession();
		System.out.println("externalSesionId:" + externalSesionId);
		connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

		// RowType_GetRealCard_Request request = new
		// RowType_GetRealCard_Request(pseudoCard);

		// RowType_GetRealCard_ResponseHolder response = new
		// RowType_GetRealCard_ResponseHolder();
		//
		// proxy.getRealCard(connectionInfo, request, new
		// OperationResponseInfoHolder(), response);

		RowType_ListCardsByAccount_Request param3 = new RowType_ListCardsByAccount_Request();
		param3.setACCOUNT_NO(accNom);

		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder();
		proxy.listCardsByAccount(connectionInfo, param3, responseInfo, details);

		return details.value.getRow(0).getItem(3).getValue();

	}

	public static String getListAccountsByCard(String card,
			IssuingPortProxy proxy, String HUMO_BANK_C, String HUMO_GROUP_C)
			throws RemoteException {

		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(HUMO_BANK_C);
		connectionInfo.setGROUPC(HUMO_GROUP_C);
		String accountNo = null;

		try {
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			// RowType_GetRealCard_ResponseHolder response = new
			// RowType_GetRealCard_ResponseHolder();
			//
			// proxy.getRealCard(connectionInfo, request, new
			// OperationResponseInfoHolder(), response);

			RowType_ListAccountsByCard_Request param3 = new RowType_ListAccountsByCard_Request();
			param3.setCARD(card);
			param3.setBANK_C(HUMO_BANK_C);
			param3.setGROUPC(HUMO_GROUP_C);

			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			ListType_GenericHolder details = new ListType_GenericHolder();
			proxy.listAccountsByCard(connectionInfo, param3, responseInfo,
					details);

			RowType_Generic[] rows = details.value.getRow();

			for (RowType_Generic r : rows) {
				if (r.getItem(0).getValue().equals(card)) {
					accountNo = r.getItem(17).getValue();
				}
			}
		} catch (Exception e) {

			ISLogger.getLogger().error(e);
			System.out.println(e);

		}
		return accountNo;
	}

	public static Map<String, String> getAccountsInHashmap()
			throws SQLException {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> hashMapClient = new HashMap<String, String>();
		// Map<String, String> hashMapAcc= new HashMap<String, String>();
		try {
			c = ConnectionPool.getConnection();
			// ps = c.prepareStatement("select * from bf_empc_accounts");
			ps = c.prepareStatement("Select * From bf_empc_accounts a where not exists (select * from humo_cards c where c.account_no = a.account_no)");

			rs = ps.executeQuery();

			while (rs.next()) {

				hashMapClient.put(rs.getString("account_no"),
						rs.getString("client"));

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

	public static void updateINHumoCards(String account_no, String card,
			String client) throws SQLException {

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
			// ConnectionPool.close(c);

		}

	}

	// public static void createCards(String un,String pw,String alias) throws
	// Exception{
	//
	//
	//
	//
	// Map<String, String> hashMap = getNewClientsInHashmap();
	// //int j=0;
	// for (Map.Entry entry : hashMap.entrySet()) {
	//
	// System.out.println();
	// System.out.println("++++++++++++++++++");
	// System.out.println(j++);
	// System.out.println("++++++++++++++++++");
	// System.out.println();
	// ResponsInfo responsInfo=HumoCardsService.createCardsHumo(un, pw, alias,
	// entry.getValue().toString(),entry.getKey().toString(),
	// "006","123","3","GMU ");
	//
	// if(responsInfo.getSuccessful()==true)
	// {
	//
	// insertNewCards(entry.getKey().toString(),entry.getValue().toString(),"2","");
	// }
	//
	// else{
	//
	// insertNewCards(entry.getKey().toString(),entry.getValue().toString(),"1",responsInfo.getErrorDescription());
	// }
	//
	// }
	// }

//	public static void crDCards(Connection c, String alias) throws Exception {
//
//		try {
//			ArrayList<PacketOpenCard> nCards = getDCards(c);
//
//			for (PacketOpenCard card : nCards) {
//
//				ResponsInfo responsInfo = HumoCardsService.newAgreementHumo(c,
//						alias, card.getBranch(), card.getClientB(), "002",
//						"123", card.getCardTypeNamber(), card.getCardType()
//								+ " ");
//
//				if (responsInfo.getSuccessful() == true) {
//
//					updateStatusCard(card.getClientB(), card.getBranch(), "2",
//							"", card.getCardType());
//
//				}
//
//				else {
//
//					updateStatusCard(card.getClientB(), card.getBranch(), "1",
//							responsInfo.getErrorDescription(),
//							card.getCardType());
//				}
//
//			}
//		} catch (Exception e) {
//
//			ISLogger.getLogger().error(e);
//			e.printStackTrace();
//		}
//	}

	public static int updateStatusCard(String client_id, String branch,
			String state, String err, String cardType) {
		Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		try {

			System.out.println("client_id" + client_id);
			System.out.println("branch" + branch);
			System.out.println("state" + state);
			System.out.println("err" + err);

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update HUMO_CARD_OPEN set state_d=?, err_d=?, state_n=8  where client=? and branch=? and card_type=?");

			ps.setString(1, state);
			ps.setString(2, err);
			ps.setString(3, client_id);
			ps.setString(4, branch);
			ps.setString(5, cardType);
			//

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

		return res;
	}

	public static Map<String, String> getNewClientsInHashmap()
			throws SQLException {

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
			 ps.close();
			 rs.close();
			
			 c.close();

		}
		return hashMap;
	}

	public static int insertNewCards(String client_id, String branch,
			String state, String err, String cardType) {
		Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update HUMO_CARD_OPEN set state_n=?, err_n=?  where client=? and branch=? and card_type=?");

			ps.setString(1, state);
			ps.setString(2, err);
			ps.setString(3, client_id);
			ps.setString(4, branch);
			ps.setString(5, cardType);
			//

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
				// if (ps != null)
				// ps.close();
			} catch (Exception e) {
			}
			// ConnectionPool.close(c);
		}

		return res;
	}

	public static ArrayList<PacketOpenCard> getNCards(Connection c)
			throws SQLException {

		ArrayList<PacketOpenCard> listCards = new ArrayList<PacketOpenCard>();
		PacketOpenCard cards = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
try{
		ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN c where exists (select 'x' from V_CLIENT_HUMO b WHERE b.branch = c.branch and b.id_client=c.client and b.state=2) and c.state_n='0'");
		rs = ps.executeQuery();

		while (rs.next()) {

			cards = new PacketOpenCard();
			cards.setCardTypeNamber(getCardTypeNumber(rs.getString("CARD_TYPE")));
			cards.setClientB(rs.getString("CLIENT"));
			cards.setBranch(rs.getString("BRANCH"));
			cards.setAccount(rs.getString("ACCOUNT"));
			cards.setCardType(rs.getString("CARD_TYPE"));
			listCards.add(cards);
		}
		
}catch(Exception e){
	
}
finally{
	ps.close();
	rs.close();
}
		return listCards;
	}

	public static ArrayList<PacketOpenCard> getDCards(Connection c)
			throws SQLException {

		ArrayList<PacketOpenCard> listCards = new ArrayList<PacketOpenCard>();
		PacketOpenCard cards = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
try{
		ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN c where exists (select 'x' from V_CLIENT_HUMO b WHERE b.branch = c.branch and b.id_client=c.client and b.state=2) and c.state_n='1'");
		rs = ps.executeQuery();

		while (rs.next()) {

			cards = new PacketOpenCard();
			cards.setCardTypeNamber(getCardTypeNumber(rs.getString("CARD_TYPE")));
			cards.setClientB(rs.getString("CLIENT"));
			cards.setBranch(rs.getString("BRANCH"));
			cards.setAccount(rs.getString("ACCOUNT"));
			cards.setCardType(rs.getString("CARD_TYPE"));
			listCards.add(cards);
		}
}catch(Exception e){
	
	
}
finally{
	ps.close();
	rs.close();
}
		return listCards;
	}

	public static String getCardTypeNumber(String cardType) {
		String cardTypeNumber = null;

		if (cardType.equals("PYMM") || cardType.equals("GYMM")
				|| cardType.equals("KYMM") || cardType.equals("YMM")) {

			cardTypeNumber = "2";
		}

		else if (cardType.equals("PMU") || cardType.equals("GMU")
				|| cardType.equals("KMU") || cardType.equals("MU")) {
			cardTypeNumber = "3";

		}

		else if (cardType.equals("F")) {

			cardTypeNumber = "4";
		}

		else {

			cardTypeNumber = "9";

		}

		return cardTypeNumber;
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public static void close(CallableStatement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public static boolean isExsistNoFoundCard(Connection c, String type,
			String mfo, String client) {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps = null;
		boolean isExsestCard = false;
		ResultSet rs=null;

		try {

			ps = c.prepareStatement("SELECT * FROM HUMO_NO_FOUND_CARD_OPEN WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard = true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			// ConnectionPool.close(c);
		}
		return isExsestCard;
	}

	public static boolean isExsistCard(Connection c, String type, String mfo,
			String client) throws SQLException {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps = null;
		ResultSet rs=null;
		boolean isExsestCard = false;

		try {

			ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard = true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			// ConnectionPool.close(c);
		}
		return isExsestCard;
	}

	public static boolean isExsistCardRenew(Connection c, String type,
			String mfo, String client) throws SQLException {

		HumoCardSetting humocardsetting = new HumoCardSetting();
		PreparedStatement ps = null;
		boolean isExsestCard = false;
		ResultSet rs=null;

		try {

			ps = c.prepareStatement("SELECT * FROM HUMO_CARD_OPEN_RENEW WHERE card_type=? and branch=? and client=?");
			ps.setString(1, type);
			ps.setString(2, mfo);
			ps.setString(3, client);
			rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard = true;
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
			// ConnectionPool.close(c);
		}
		return isExsestCard;
	}

	static OperationResponseInfo addStopList(String realCard,
			IssuingPortProxy proxy, String bankC, String groupC)
			throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			RowType_AddCardToStopList_Request param = new RowType_AddCardToStopList_Request();

			param.setCARD(realCard);
			param.setSTOP_CAUSE("1");
			param.setTEXT("stolen");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);

			res = proxy.addCardToStop(connectionInfo, param);
            System.out.println("card " +realCard+ " stopped");
		} catch (Exception e) {

			ISLogger.getLogger().error(e);

		}

		return res;

	}

	static OperationResponseInfo deleteStopList(String realCard,
			IssuingPortProxy proxy, String bankC, String groupC)
			throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			RowType_RemoveCardFromStop_Request param = new RowType_RemoveCardFromStop_Request();

			param.setCARD(realCard);
			param.setTEXT("change");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);

			res = proxy.removeCardFromStop(connectionInfo, param);

			// proxy.getRealCard(connectionInfo, request, new
			// OperationResponseInfoHolder(), response);

		} catch (Exception e) {

			ISLogger.getLogger().error(e);

		}

		return res;

	}

	static OperationResponseInfo deActivateCard(String PAN,
			IssuingPortProxy proxy, String bankC, String groupC)
			throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			RowType_DeactivateCard_Request param = new RowType_DeactivateCard_Request();

			param.setCARD(PAN);

			res = proxy.deactivateCard(connectionInfo, param);

			// proxy.getRealCard(connectionInfo, request, new
			// OperationResponseInfoHolder(), response);

		} catch (Exception e) {

			ISLogger.getLogger().error(e);

		}

		return res;

	}

	static OperationResponseInfo resetPINCounter(String PAN,
			IssuingPortProxy proxy, String bankC, String groupC, String un,
			String pw, String alias) throws RemoteException {
		RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		OperationResponseInfo res = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			System.out.println("externalSesionId:" + externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			// RowType_GetRealCard_Request request = new
			// RowType_GetRealCard_Request(pseudoCard);

			RowType_ResetPINCounter_Request param = new RowType_ResetPINCounter_Request();

			param.setCARD(PAN);
			param.setEXPIRY(getCardDate(PAN, un, pw, alias));
			param.setTEXT("zbrosPIN");

			res = proxy.resetPINCounter(connectionInfo, param);

			// proxy.getRealCard(connectionInfo, request, new
			// OperationResponseInfoHolder(), response);

		} catch (Exception e) {

			ISLogger.getLogger().error("resetPINCounter:" + e);

		}

		return res;

	}

	public static String getCardDate(String PANCard, String un, String pw,
			String alias) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String date = null;
		String srok = null;
		Connection c1 = null;
		try {
			c1 = ConnectionPool.getConnection(un, pw, alias);
			ps = c1.prepareStatement("SELECT EXPIRY1 FROM HUMO_CARDS where real_card=?");
			ps.setString(1, PANCard);
			rs = ps.executeQuery();

			while (rs.next()) {
				date = rs.getString("EXPIRY1");
				System.out.println("date:" + date);
				srok = date.substring(2, 4) + "" + date.substring(5, 7);
				System.out.println("srok:" + srok);

			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("EXPIRY1:" + e);

		}

		finally {
              ps.close();
              rs.close();
			ConnectionPool.close(c1);
		}
		return srok;

	}

	public static Map<String, String> getAccountNoInHashmap()
			throws SQLException {

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

				hashMap.put(rs.getString("real_card"),
						rs.getString("tranz_acct"));
				ISLogger.getLogger().info(
						"real_card: " + rs.getString("real_card") + " ");

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

	public static Map<String, String> getListCard() throws SQLException {

		Map<String, String> hashMap = new HashMap<String, String>();
		Connection c = null;
		PreparedStatement psListCard = null;
		ResultSet rs = null;
		try {

			c = ConnectionPool.getConnection();
			psListCard = c
					.prepareStatement("select k.client,k.card from humo_cards k where k.update_status is null and k.real_card like '98600302%'");

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

				psListCard.close();
			}
			if (rs != null) {
				rs.close();
			}

		}

		return hashMap;
	}

	static CardStatus getCardStatus(String pseudoCard, IssuingPortProxy proxy,
			String bankC, String groupC) throws RemoteException {

		CardStatus cardStatus = new CardStatus();
		String status1 = null;
		String status2 = null;
		String STOP_CAUSE = null;

		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(bankC);
			connectionInfo.setGROUPC(groupC);
			String externalSesionId = getExternalSession();
			// System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
			RowType_QueryCardInfo_Request parameters = new RowType_QueryCardInfo_Request();
			ListType_GenericHolder details = new ListType_GenericHolder();
			parameters.setBANK_C(bankC);
			parameters.setGROUPC(groupC);
			parameters.setCARD(pseudoCard);

			proxy.queryCardInfo(connectionInfo, parameters, responseInfo,
					details);

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

	public static int insertBfAccounts(Connection c, String account_no,
			String tranz_acct) {

		int res = 0;
		PreparedStatement ps = null;
		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into bf_EMPC_accounts(ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?)");
			ISLogger.getLogger()
					.info("insert into bf_EMPC_accounts(ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
							+ " ("
							+ account_no
							+ ","
							+ tranz_acct
							+ ","
							+ tranz_acct
							+ ","
							+ new java.sql.Date(new Date().getTime())
							+ ","
							+ new java.sql.Date(new Date().getTime()));
			ps.setString(1, account_no);
			ps.setString(2, tranz_acct);
			ps.setString(3, tranz_acct);
			ps.setDate(4, new java.sql.Date(new Date().getTime()));
			ps.setDate(5, new java.sql.Date(new Date().getTime()));

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
		}

		return res;
	}

	public static int insertAccountNo(Connection c, String account_no,
			String real_card, String tranz_acct) {

		int res = 0;
		PreparedStatement ps = null;
		try {

			ps = c.prepareStatement("update humo_cards set account_no=? where real_card=? and tranz_acct=?");
			ISLogger.getLogger().info(
					"insert update humo_cards set account_no=" + account_no
							+ " where real_card=" + real_card
							+ " and tranz_acct=" + tranz_acct);

			ps.setString(1, account_no);
			ps.setString(2, real_card);
			ps.setString(3, tranz_acct);

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
		}

		return res;
	}

	public static Long getAccountNO(String client_b, String branch,
			String cardType, Connection c) throws SQLException {

		Long account_no = null;
		ResultSet rs = null;
		PreparedStatement ps=null;

		try {

			ps = c
					.prepareStatement("SELECT min(account_no)as account_no  FROM humo_cards where client_b=? and branch=? and card_name like ?");

			ps.setString(1, client_b);
			ps.setString(2, branch);
			ps.setString(3, cardType + "%");

			rs = ps.executeQuery();
			while (rs.next()) {
				account_no = rs.getLong("account_no");
			}
			System.out.println("cardType: " + cardType);
			System.out.println("account_no " + account_no);

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally{
			ps.close();
			rs.close();
			
		}
		return account_no;
	}

	public static int updateStatus(String realCard, String alias,String status1, String status2)
			throws SQLException {

		int res = 0;
		PreparedStatement ps = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update humo_cards set status1=?,status2=? where real_card=?");
			ps.setString(1, status1);
			ps.setString(2, status2);
			ps.setString(3, realCard);

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
		}

		finally {
			if (c != null) {
				ConnectionPool.close(c);
			}

			if (ps != null) {
				ps.close();
			}

		}

		return res;
	}

	public static String getExternalSession() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		return dateFormat.format(new Date());
	}

	public static String replaceCard(Connection c, String card,String client,String alias)
			throws Exception {

		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
		HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");

		HUMO_HOST = ConnectionPool.getValue("HUMO_HOST");
		HUMO_USERNAME = ConnectionPool.getValue("HUMO_USERNAME");
		HUMO_PASSWORD = ConnectionPool.getValue("HUMO_PASSWORD");
		issuingPortProxy = getPortProxy(HUMO_HOST, HUMO_USERNAME, HUMO_PASSWORD);
		connectionInfo = getConInfo(HUMO_BANK_C, HUMO_GROUPC);
		String message = null;
		ReplaceCardInfo cardInfo = null;
try{
		message = stopCard(c, card, issuingPortProxy, HUMO_BANK_C, HUMO_GROUPC);
        ISLogger.getLogger().error("stopCard: "+message);
		//cardInfo = getNewCard(card, issuingPortProxy, connectionInfo);
		
		CardSetting cardSetting=getCardSettingInfo(c,card);
		
		cardInfo = getNewCard2(c,card, issuingPortProxy, connectionInfo, client,cardSetting.getChipAppId(),cardSetting.getRangeId(),cardSetting.getBranchId());
		 ISLogger.getLogger().error("Error_action: "+cardInfo.getError_action());
		 ISLogger.getLogger().error("Error_description: "+cardInfo.getError_description());
		 ISLogger.getLogger().error("getResponse_code: "+cardInfo.getResponse_code());
		 ISLogger.getLogger().error("NewCard: "+cardInfo.getCard());
		if (cardInfo.getError_description() == null) {
			String newCard = cardInfo.getCard();
			Calendar NEW_EXPIRY = cardInfo.getNEW_EXPIRY();
			HumoCardsService.addStopList(card, issuingPortProxy, HUMO_BANK_C, HUMO_GROUPC);
			HumoCardsService.updateStatus(card,alias,"2","2"); 
			updateHumoCards(c, card, newCard, NEW_EXPIRY);
			insertRealCard();
			String newRealCard=getNewRealCard(c,newCard);
			ISLogger.getLogger().error("newRealCard: "+newRealCard);
			updateAgement(c, card, newRealCard);
			
			message = "Карта успешно перевыпустился";
		} else {

			message = "Error_action: " + cardInfo.getError_action()
					+ " Error_description: " + cardInfo.getError_description();

		}
}catch(Exception e){
	 ISLogger.getLogger().error("ERROR replaceCard: "+CheckNull.getPstr(e));
}

		return message;

	}
	


	public static String isExsistCardInHumoCards(Connection c, String clientB,
			String mfo, String cardType) throws SQLException {

		String pan = null;
		PreparedStatement ps = null;
		boolean isExsestCard = false;
		ResultSet rs=null;
		try {

			ps = c.prepareStatement("SELECT * FROM HUMO_CARDS where client_B=? and branch=? and card_name like ?");
			ps.setString(1, clientB);
			ps.setString(2, mfo);
			ps.setString(3, cardType + "%");

			System.out.println("clientB: " + clientB + " mfo: " + mfo
					+ " cardType: " + cardType);
			 rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard = true;
				pan = rs.getString("real_card");

			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
        ps.close();
        rs.close();
		}
		return pan;

	}

	public static String stopCard(Connection c, String card,
			globus.IssuingWS.IssuingPortProxy portProxy, String bankC,
			String groupC) throws SQLException {

		PreparedStatement ps = null;
		String real_card = null;
		String message = null;
		ResultSet rs=null;
		try {

			ps = c.prepareStatement("SELECT client_b, branch, card_name,real_card FROM HUMO_CARDS where real_card = ?");
			ps.setString(1, card);

			System.out.println("karta "+card);
			
			 rs = ps.executeQuery();
			if (rs.next()) {
				real_card = rs.getString("real_card");
				message = deleteCards(c, real_card, rs.getString("client_b"),
						rs.getString("branch"), rs.getString("card_name"),
						portProxy, bankC, groupC);
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
      ps.close();
      rs.close();
		}

		return message;

	}

	public static ReplaceCardInfo getNewCard(String card,
			globus.IssuingWS.IssuingPortProxy portProxy,
			OperationConnectionInfo conInfo) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException,
			KeyStoreException, IOException {

		RowType_ReplaceCard parameters = new RowType_ReplaceCard();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ReplaceCardHolder details = new RowType_ReplaceCardHolder();
		ReplaceCardInfo replaceCardInfo = new ReplaceCardInfo();
		BigInteger responseCode;

		parameters.setCARD(card);

		portProxy.replaceCard(conInfo, parameters, responseInfo, details);

		responseCode = responseInfo.value.getResponse_code();

		System.out.println("responseCode: " + responseCode);
		System.out.println("Error_description: "
				+ responseInfo.value.getError_description());

		ISLogger.getLogger().error("responseCode: " + responseCode);
		ISLogger.getLogger().error(
				"Error_action: " + responseInfo.value.getError_action());
		ISLogger.getLogger().error(
				"Error_description: "
						+ responseInfo.value.getError_description());
		// if (responseCode.equals(0)) {

		if (responseInfo.value.getError_description() == null) {

			replaceCardInfo.setCard(details.value.getNEW_CARD());
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

	public static ReplaceCardInfo getNewCard2(Connection c,String card,
			globus.IssuingWS.IssuingPortProxy portProxy,
			OperationConnectionInfo conInfo,String client,String HUMO_CHIPAPPID,String HUMO_RANGEID,String HUMO_BRANCH_ID) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException,
			KeyStoreException, IOException, SQLException {

		RowType_ReplaceCard parameters = new RowType_ReplaceCard();
		//OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ReplaceCardHolder details = new RowType_ReplaceCardHolder();
		ReplaceCardInfo replaceCardInfo = new ReplaceCardInfo();
		BigInteger responseCode;

		parameters.setCARD(card);

		//portProxy.replaceCard(conInfo, parameters, responseInfo, details);
		KeyType_Agreement mainAgreementInfo=new KeyType_Agreement();
	    ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();
		RowType_AccountInfo_Base baseInfo=new RowType_AccountInfo_Base();
		
		Long accountNo=getAccountNO(c, card);
		baseInfo.setACCOUNT_NO(BigDecimal.valueOf(accountNo));
		
		
//		baseInfo.setCCY("UZS");
//		baseInfo.setCRD(BigDecimal.valueOf(0));
//		baseInfo.setMIN_BAL(BigDecimal.valueOf(0));
//		baseInfo.setC_ACCNT_TYPE("00");
//		baseInfo.setNON_REDUCE_BAL(BigDecimal.valueOf(0));
//		baseInfo.setSTATUS("0");
//		baseInfo.setCOND_SET("001");
//		baseInfo.setCYCLE("001");
//		baseInfo.setSTAT_CHANGE("1");
//	
		
		
		System.out.println("ACCOUNT_NO: "+accountNo);
		
	    accountInfo.setBase_info(baseInfo);
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
	 //   accountsListInfo.value = ltaccounts;
	    

	    ListType_CardInfoHolder cardsListInfo=new ListType_CardInfoHolder();
	    RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
	    
	    Long agreNom=getAgreNom(c,client,HUMO_RANGEID,card);
	    
	    if(agreNom!=0L){
	    	mainAgreementInfo.setAGRE_NOM(BigDecimal.valueOf(agreNom));	
	    	System.out.println("agreNom "+agreNom);
	    }
	    
	    else {
//	    	Customer customer = new Customer();
//	    	CustomerHumo customerHumo=getCustomerHumo(c,client,card);
//	    	
//	    	
//	    	customer.setR_STREET(customerHumo.getStreet());
//	    	customer.setR_CITY(customerHumo.getCity());
//	    	customer.setR_CNTRY(customerHumo.getCntry());
//	    	
//	    	RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
//					com.is.humo.HumoCardsService.getNewRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,"",customer,"",client));
//	    	
//	    	
//	    	OperationResponseInfo operationResponsInfo = issuingPortProxy
//			.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
//	    	mainAgreementInfo.setAGRE_NOM(agreementInfo.value.getAGRE_NOM());
	    	
	    	ISLogger.getLogger().error("Для этого карты не найден AGRE_NOM");
	    	
	    }
		
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		ListType_CardInfo cards = new ListType_CardInfo();
		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(Long.valueOf(HUMO_CHIPAPPID)));
        cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard2(getCustomerHumo(c,client,card),HUMO_RANGEID,HUMO_BRANCH_ID));
		cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCard2(getCustomerHumo(c,client,card)));
		ISLogger.getLogger().error("client: "+client);
		ISLogger.getLogger().error("card: "+card);
		cardInfo.setEMV_Data(eMV_Data);
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		cardsListInfo.value = cards;
		
		OperationResponseInfo responseInfo =portProxy.addInfo4Agreement(conInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
		

		//responseCode = responseInfo.getResponse_code();

		
		if (responseInfo.getError_description() == null) {

			replaceCardInfo.setCard(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
			replaceCardInfo.setNEW_EXPIRY(cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1());
			replaceCardInfo.setError_description(null);

		}

		else {
			//replaceCardInfo.setResponse_code(responseCode.toString());
			replaceCardInfo.setError_action(responseInfo.getError_action());
			replaceCardInfo.setError_description(responseInfo.getError_description());

		}

		return replaceCardInfo;

	}
	
	public static void updateHumoCards(Connection c, String oldCard,
			String newCard, Calendar NEW_EXPIRY) {
		PreparedStatement psUpdateHumoCards = null;
		try {

			psUpdateHumoCards = c
					.prepareStatement("update humo_cards set card=?, EXPIRY1=?,status1='1',status2='0', renew_date=?, real_card=''"
							+ " where real_card=?");

			psUpdateHumoCards.setString(1, newCard);
			psUpdateHumoCards.setDate(2,
					new java.sql.Date(NEW_EXPIRY.getTimeInMillis()));
			psUpdateHumoCards.setDate(3,
					new java.sql.Date(new Date().getTime()));
			psUpdateHumoCards.setString(4, oldCard);

			ISLogger.getLogger().error(
					"oldCard: " + oldCard + "newCard:" + newCard + " EXPIRY1:"
							+ new java.sql.Date(NEW_EXPIRY.getTimeInMillis()));

			psUpdateHumoCards.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public static String deleteCards(Connection c, String real_card,
			String client_b, String branch, String cardName,
			globus.IssuingWS.IssuingPortProxy portProxy, String bankC,
			String groupC) throws SQLException {

		int res = 0;
		String realCard = null;
		String message = null;
		OperationResponseInfo resInfo = null;
		ResultSet rs=null;
		try {

			psSelectStopCards = c
					.prepareStatement("select real_card from humo_cards where client_b=? and branch=? and card_name=? and real_card<>?");

			psSelectStopCards.setString(1, client_b);
			psSelectStopCards.setString(2, branch);
			psSelectStopCards.setString(3, cardName);
			psSelectStopCards.setString(4, real_card);

			 rs = psSelectStopCards.executeQuery();

			while (rs.next()) {
				realCard = rs.getString("real_card");
				resInfo = addStopList(realCard, portProxy, bankC, groupC);
				if (resInfo.getError_description() != null) {
					message = resInfo.getError_description();
					System.out.println("stop eroror" + message);
					return message;

				}

				else {
					changeStatusCard(c, realCard, "2");
					System.out.println("stop ok");
				}
			}

			psUpdateCards = c
					.prepareStatement("update humo_cards set card='888' where real_card<> ? and client_b=? and branch=? and card_name=?");

			psUpdateCards.setString(1, real_card);
			psUpdateCards.setString(2, client_b);
			psUpdateCards.setString(3, branch);
			psUpdateCards.setString(4, cardName);
			psUpdateCards.executeUpdate();
			c.commit();

			psdeleteCards = c
					.prepareStatement("delete from  humo_cards where real_card<> ? and client_b=? and branch=? and card_name= ? and card='888'");
			psdeleteCards.setString(1, real_card);
			psdeleteCards.setString(2, client_b);
			psdeleteCards.setString(3, branch);
			psdeleteCards.setString(4, cardName);
			psdeleteCards.executeUpdate();
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
			message = CheckNull.getPstr(e);

		}
		
		finally{
			rs.close();
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
		String sesId=getExternalSession();
		conInfo.setEXTERNAL_SESSION_ID(sesId);
		ISLogger.getLogger().error("sesId: "+sesId);

		return conInfo;

	}

	public String addToStopList(Connection c,
			globus.IssuingWS.IssuingPortProxy portProxy, String clientB,
			String mfo, String cardType, String bankC, String groupC)
			throws RemoteException, SQLException {

		OperationResponseInfo operInfo = null;
		String realCard = null;
		String message = null;
		ResultSet rs = null;
		StringBuilder errors = new StringBuilder();
try{
		psSelectStopCards = c
				.prepareStatement("select real_card from humo_cards where client_b=? and branch=? and card_name like ?");
		psSelectStopCards.setString(1, clientB);
		psSelectStopCards.setString(2, mfo);
		psSelectStopCards.setString(3, cardType + "%");
		rs = psSelectStopCards.executeQuery();

		while (rs.next()) {
			realCard = rs.getString("real_card");
			operInfo = addStopList(realCard, portProxy, bankC, groupC);

			if (operInfo.getError_description() != null) {
				message = "Error_action: " + operInfo.getError_action()
						+ "getError_description: "
						+ operInfo.getError_description();
				errors.append(message + " ");
			}

		}
}catch(Exception ex){}
finally{
	
	rs.close();
}
		return errors.toString();
	}

	public static int changeStatusCard(Connection c, String realCard,
			String status) throws SQLException {

		int res = 0;
		PreparedStatement ps = null;

		try {

			ps = c.prepareStatement("update humo_cards set status1=?,status2=? where real_card=?");
			ps.setString(1, status);
			ps.setString(2, status);
			ps.setString(3, realCard);

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
		}

		finally {
			if (c != null) {
				ConnectionPool.close(c);
			}

			if (ps != null) {
				ps.close();
			}

		}

		return res;
	}

	public static String addCard(Connection c,ResultSet rs,String clientB, String branch, String cardCode,String personCode,
			String acc, String alias, String vidCard) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException,
			KeyStoreException, IOException 
			{
		
		
		String message = null;
		
        globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
        OperationConnectionInfo connectionInfo = null;
		try {
			HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
			HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
			HUMO_HOST = ConnectionPool.getValue("HUMO_HOST");
			HUMO_USERNAME = ConnectionPool.getValue("HUMO_USERNAME");
			HUMO_PASSWORD = ConnectionPool.getValue("HUMO_PASSWORD");
			issuingPortProxy = getPortProxy(HUMO_HOST, HUMO_USERNAME,HUMO_PASSWORD);
			connectionInfo = getConInfo(HUMO_BANK_C, HUMO_GROUPC);

			//c = ConnectionPool.getConnection(alias);
			ResponsInfo resInfo=createCardsHumoKorp(c,rs, alias, branch, clientB, cardCode,personCode, vidCard, acc,issuingPortProxy,connectionInfo,HUMO_BANK_C);
			message=resInfo.getErrorDescription();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
		}

		return message;
	}

//	public ResponsInfo createCardsHumo(Connection c, String acc, String branch,
//			String clientId, String cardCode, String personCode,
//			String cardName, Customer customer, String cardType,
//			Long HUMO_CHIPAPPID, Long HUMO_RANGEID, String HUMO_BINCOD,
//			String HUMO_BANK_C, String HUMO_BRANCHID) throws Exception {
//
//		String cl_type = "2";
//
//		ResponsInfo responsInfo = new ResponsInfo();
//		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
//
//		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(
//				com.is.humo.HumoCardsService.getRowType_Customer(customer,
//						personCode, cl_type, c));
//
//		RowType_AccountInfo accountInfo = new RowType_AccountInfo();
//
//		accountInfo.setBase_info(com.is.humo.HumoCardsService
//				.getRowType_AccountInfo_Base(acc, customer.getBranch(), c));
//		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
//		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
//
//		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
//		accountsListInfo.value = ltaccounts;
//		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
//
//		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(HUMO_CHIPAPPID));
//
//		RowType_CardInfo cardInfo = new RowType_CardInfo();
//		cardInfo.setLogicalCard(com.is.humo.HumoCardsService
//				.getRowType_CardInfo_LogicalCard(customer, HUMO_RANGEID));
//		cardInfo.setPhysicalCard(com.is.humo.HumoCardsService
//				.getRowType_CardInfo_PhysicalCard(customer, cardName));
//		cardInfo.setEMV_Data(eMV_Data);
//		ListType_CardInfo cards = new ListType_CardInfo();
//		cards.setRow(new RowType_CardInfo[] { cardInfo });
//		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
//		cardsListInfo.value = cards;
//
//		try {
//			String externalSesionId = getExternalSession();
//
//			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
//
//			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
//					com.is.humo.HumoCardsService.getRowType_Agreement(
//							HUMO_BINCOD, HUMO_BANK_C, HUMO_BRANCHID, cardCode,
//							customer));
//			System.out.println("Connection new customer" + !c.isClosed());
//			OperationResponseInfo orInfo2 = null;
//
//			// //////////////////////////////////////////////////////////////////////////////////
//			ISLogger.getLogger().error("start newCustomerAndAgreement");
//			orInfo2 = issuingPortProxy.newCustomerAndAgreement(connectionInfo,
//					customerInfo, customListInfo, agreementInfo,
//					accountsListInfo, cardsListInfo);
//			ISLogger.getLogger().error("end newCustomerAndAgreement");
//			// /////////////////////////////////////////////////////////////////////////////////////
//			responsInfo.setSuccessful(true);
//
//			if (orInfo2.getError_description() != null) {
//
//				responsInfo.setResponseCode(orInfo2.getResponse_code());
//				responsInfo.setErrorDescription(orInfo2.getError_description());
//				responsInfo.setErrorAction(orInfo2.getError_action());
//				responsInfo.setSuccessful(false);
//
//				System.out.println("Ошибка "
//						+ responsInfo.getErrorDescription());
//
//			}
//
//			else {
//
//				insertNewClient(
//						com.is.humo.HumoCardsService.getRowType_Customer(
//								customer, personCode, cl_type, c),
//						customerInfo.value.getCLIENT(), c);
//				insertNewClientAgreement(connectionInfo, agreementInfo, c);
//				insertNewClientAccounts(accountsListInfo, cardsListInfo, acc,
//						c, "");
//				insertNewClientHumoCards2(cardsListInfo, clientId,
//						customer.getBranch(), acc, c, "", accountsListInfo);
//				responsInfo.setSuccessful(true);
//
//				System.out.println("insert noviy karta");
//			}
//
//		}
//
//		catch (RemoteException e) {
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			e.printStackTrace();
//
//		}
//		return responsInfo;
//	}

	public void insertNewClientAccounts(
			ListType_AccountInfoHolder accountsListInfo,
			ListType_CardInfoHolder cardsListInfo, String acc, Connection c,
			String clientIdHumo) {

		try {
            
			psNewAccounts.setString(1, clientIdHumo == "" ? cardsListInfo.value
					.getRow(0).getLogicalCard().getCLIENT() : clientIdHumo);
			psNewAccounts.setBigDecimal(2, accountsListInfo.value.getRow(0)
					.getBase_info().getACCOUNT_NO() == null ? null
					: accountsListInfo.value.getRow(0).getBase_info()
							.getACCOUNT_NO());

			psNewAccounts.setString(3, acc == null ? "" : acc);

			psNewAccounts.setString(4, acc == null ? "" : acc);

			psNewAccounts
					.setDate(5, accountsListInfo.value.getRow(0).getBase_info()
							.getAB_EXPIRITY() == null ? null
							: new java.sql.Date(accountsListInfo.value
									.getRow(0).getBase_info().getAB_EXPIRITY()
									.getTimeInMillis()));
			psNewAccounts.setDate(6,
					accountsListInfo.value.getRow(0).getBase_info()
							.getCREATED_DATE() == null ? null
							: new java.sql.Date(accountsListInfo.value
									.getRow(0).getBase_info().getCREATED_DATE()
									.getTimeInMillis()));

			psNewAccounts.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));

		}
	}

	public void insertNewClientHumoCards2(
			ListType_CardInfoHolder cardsListInfo, String clientId,
			String branch, String acc, Connection c, String clientIdHumo,
			ListType_AccountInfoHolder accountsListInfo) {

		try {

			psInsertHumoCards.setString(1,
					clientIdHumo == "" ? cardsListInfo.value.getRow(0)
							.getLogicalCard().getCLIENT() : clientIdHumo);
			psInsertHumoCards.setString(2, clientId == null ? "" : clientId);
			psInsertHumoCards.setString(3, cardsListInfo.value.getRow(0)
					.getLogicalCard().getCARD() == null ? ""
					: cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
			psInsertHumoCards.setString(4, branch == null ? "" : branch);
			psInsertHumoCards.setString(5, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS1() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTATUS1());
			psInsertHumoCards.setString(6, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTATUS2() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTATUS2());
			psInsertHumoCards.setDate(7, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getEXPIRY1() == null ? null
					: new java.sql.Date(cardsListInfo.value.getRow(0)
							.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
			psInsertHumoCards.setDate(8,
					cardsListInfo.value.getRow(0).getPhysicalCard()
							.getEXPIRITY2() == null ? null : new java.sql.Date(
							cardsListInfo.value.getRow(0).getPhysicalCard()
									.getEXPIRITY2().getTimeInMillis()));

			psInsertHumoCards.setString(9, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getRENEW() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getRENEW());

			String psevdaPAN = cardsListInfo.value.getRow(0).getPhysicalCard()
					.getCARD_NAME() == null ? "" : cardsListInfo.value
					.getRow(0).getPhysicalCard().getCARD_NAME();
			psInsertHumoCards.setString(10, psevdaPAN);
			psInsertHumoCards.setString(11, "");
			psInsertHumoCards.setString(12, "");

			psInsertHumoCards.setString(13, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getSTOP_CAUSE() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getSTOP_CAUSE());
			psInsertHumoCards.setString(14, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getRENEWED_CARD() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getRENEWED_CARD());
			psInsertHumoCards.setBigDecimal(15, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getDESIGN_ID() == null ? null
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getDESIGN_ID());
			psInsertHumoCards.setString(16, cardsListInfo.value.getRow(0)
					.getPhysicalCard().getINSTANT() == null ? ""
					: cardsListInfo.value.getRow(0).getPhysicalCard()
							.getINSTANT());

			psInsertHumoCards.setString(17, acc);
			psInsertHumoCards.setString(18, acc);
			psInsertHumoCards.setBigDecimal(19, accountsListInfo.value
					.getRow(0).getBase_info().getACCOUNT_NO() == null ? null
					: accountsListInfo.value.getRow(0).getBase_info()
							.getACCOUNT_NO());

			psInsertHumoCards.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public static HumoClientInfo getClientInfo(Connection c, String branch,String clientId,String cardCode) throws SQLException {
		
		HumoClientInfo humoClientInfo=new HumoClientInfo();
		Customer customer=null;
		String clType=null;
		
		
		if (cardCode.equals("006") || cardCode.equals("007") || cardCode.equals("008")) {

			clType = "2";
			customer = HumoCardsService.getCustomerJ(c, branch, clientId);
		}

		else if (cardCode.equals("002")) {
			customer = HumoCardsService.getCustomer(c, branch, clientId);
			clType = "1";
		}

		else {

			ISLogger.getLogger().error("Не правилный код клиента");
			clType = "0";
		}

		humoClientInfo.setCustomer(customer);
		humoClientInfo.setClType(clType);
		
		return humoClientInfo;
		
	}
	
	public static Long getAgreNom(Connection c,String client,String rangeId,String card) throws SQLException{
		
		Long agreNom=0L;
		PreparedStatement psAgreNom=null;
		ResultSet rs=null;
		try{
		psAgreNom = c
		.prepareStatement("select agre_nom from bf_empc_agreement t where t.client=? and t.range_id=? and t.card=?");
		psAgreNom.setString(1, client);
		psAgreNom.setString(2, rangeId);
		psAgreNom.setString(3, card);
         rs = psAgreNom.executeQuery();
        
        System.out.println("client "+client);
        System.out.println("rangeId "+rangeId);
        if (rs.next()) {
       
        	agreNom=rs.getLong("agre_nom");
			
	     }
		}catch(Exception e){} 
		finally{
			psAgreNom.close();
			rs.close();
			
		}
        return agreNom;
	} 
	
	public static CustomerHumo getCustomerHumo (Connection c,String client,String card) throws SQLException{
		
		PreparedStatement pscustomerInfo=null;
		CustomerHumo customerHumo=new CustomerHumo();
		String name,surname,cardName;
		ResultSet rs=null;
		
		try{
		pscustomerInfo = c
		.prepareStatement("select cl.f_names,cl.surname,ca.card_name,cl.r_street,cl.r_city,cl.r_cntry from humo_cards ca, bf_empc_clients cl where ca.client=cl.client and cl.client=? and ca.real_card=?");
		pscustomerInfo.setString(1, client);
		pscustomerInfo.setString(2, card);
         rs = pscustomerInfo.executeQuery();
        if (rs.next()) {
        	customerHumo.setName(rs.getString("f_names"));
        	customerHumo.setSurname(rs.getString("surname"));
        	customerHumo.setCardName(rs.getString("card_name"));
        	customerHumo.setStreet(rs.getString("r_street"));
        	customerHumo.setCity(rs.getString("r_city"));
        	customerHumo.setCntry(rs.getString("r_cntry"));
        	
	     }
		}catch(Exception e){
			
		}
		finally{
			pscustomerInfo.close();
			rs.close();
		}
       return customerHumo;
	}
	
	public static CardSetting getCardSettingInfo(Connection c,String card) throws SQLException{
		CardSetting cardSetting	= new CardSetting();
		
	PreparedStatement psSettingInfo;
	PreparedStatement psCardType=null;
	String cardName=null;
	String branch=null;
	String code=null;
	String typeCard=null;
	String rangeId=null;
	String branchId=null;
	String chipAppId=null;
	ResultSet rs=null;
	ResultSet rsSetting=null;
	
	
	try{
	psCardType = c
	.prepareStatement("select c.branch,c.card_name from humo_cards c where c.real_card=?");
	psCardType.setString(1, card);
     rs = psCardType.executeQuery();
    if (rs.next()) {
    	cardName=rs.getString("card_name");
    	branch=rs.getString("branch");
}
    
    typeCard=cardName.substring(0, cardName.indexOf(' '));

    ISLogger.getLogger().error("typeCard:"+typeCard);
   
    if(typeCard.equals("PYMM")||typeCard.equals("GYMM")||typeCard.equals("KYMM")||typeCard.equals("YMM")){
    	code="2";	
    	
    }
    else if (typeCard.equals("PMU")||typeCard.equals("GMU")||typeCard.equals("KMU")||typeCard.equals("MU")){
    	
    	code="3";	
    }
    
    else if (typeCard.equals("YAGONA")){
    	
    	code="6";	
    	ISLogger.getLogger().error("YAGONA");
    }
    
    else{
    	ISLogger.getLogger().error("Не правилной тип карты");
    }
		
    
    psSettingInfo = c
	.prepareStatement("select t.range_id,t.branch_id,t.chip_app_id from ss_humo_type_of_card t where t.code=? and t.branch=?");
	psSettingInfo.setString(1, code);
	psSettingInfo.setString(2, branch);
	
	System.out.println("code: "+code+"branch:"+branch);
     rsSetting= psSettingInfo.executeQuery();
    if (rsSetting.next()) {
    	rangeId=rsSetting.getString("range_id");
    	branchId=rsSetting.getString("branch_id");
    	chipAppId=rsSetting.getString("chip_app_id");
       	
    }
    
    cardSetting.setBranchId(branchId);
    cardSetting.setRangeId(rangeId);
    cardSetting.setChipAppId(chipAppId);
    
	}catch(Exception e){}
	finally{
		psCardType.close();
		rs.close();
		rsSetting.close();
	}
		
		return cardSetting;
	}
	
	
	public static void insertRealCard() throws SQLException, RemoteException {

		try {
			IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
					ConnectionPool.getValue("HUMO_HOST"),
					ConnectionPool.getValue("HUMO_USERNAME"),
					ConnectionPool.getValue("HUMO_PASSWORD"));

			String bankC = ConnectionPool.getValue("HUMO_BANK_C");
			String groupC = ConnectionPool.getValue("HUMO_GROUPC");

			Map<String, String> hashMap = HumoCardsService
					.getPsevdaPANInHashmap();

			for (Map.Entry entry : hashMap.entrySet()) {

				String realCardNumber = HumoCardsService.getRealCardNumber(
						entry.getKey().toString(), issuingPortProxy, bankC,
						groupC);

				System.out.println("realcard:" + realCardNumber);
				insertRealCard(entry.getKey().toString(), realCardNumber);

			}

		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			System.out.println("REAL_CARD:" + e);

		}

	}
	public static int insertRealCard(String psevdaPAN, String realcard) {
		Connection c = null;
		int res = 0;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update humo_cards set real_card=? where card=?");
			// ps =
			// c.prepareStatement("update WRONG_HUMO_CARDS set real_card=? where pan=?");

			ps.setString(1, realcard);
			ps.setString(2, psevdaPAN);

			//

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
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			ConnectionPool.close(c);
		}

		return res;
	}
	
public static Long getAccountNO(Connection c,String card) throws SQLException{
		
		Long account_no=0L;
		PreparedStatement psAccount_no=null;
		ResultSet rs=null;
		
		
		try{
		psAccount_no = c
		.prepareStatement("select account_no from humo_cards t where t.real_card=?");
		psAccount_no.setString(1, card);

         rs = psAccount_no.executeQuery();
    
        if (rs.next()) {
       
        	account_no=rs.getLong("account_no");
			
	     }
		}catch(Exception e){
			
		}
		finally{
			psAccount_no.close();
			rs.close();
			
		}
        return account_no;
	} 

public static void updateAgement(Connection c, String oldCard,
		String newCard) {
	PreparedStatement psUpdateHumoCards = null;
	try {

		psUpdateHumoCards = c
				.prepareStatement("update BF_EMPC_AGREEMENT set card=? where card=?");

		
		//String realCard=getNewRealCard(c,newCard);
		psUpdateHumoCards.setString(1, newCard);
		psUpdateHumoCards.setString(2, oldCard);
		
		//ISLogger.getLogger().error(" oldCard3333 "+oldCard);
		//ISLogger.getLogger().error(" realCard3333 "+realCard);
	


		psUpdateHumoCards.executeUpdate();
		c.commit();
	} catch (Exception e) {
		e.printStackTrace();

		ISLogger.getLogger().error(CheckNull.getPstr(e));
	}
}

public static String getNewRealCard(Connection c,String card) throws SQLException{
	
	String realCard="";
	PreparedStatement psRealCard=null;
	ResultSet rs=null;
	
	try{
	psRealCard = c
	.prepareStatement("select t.real_card from humo_cards t where t.card=?");
	psRealCard.setString(1, card);

     rs = psRealCard.executeQuery();

    while (rs.next()) {
   
    	realCard=rs.getString("real_card");
		
     }
	}catch(Exception e){
		
	}
	finally{
		psRealCard.close();
		rs.close();
	}
    return realCard;
} 
public static  boolean checkAccount(final String acc, final String alias) {
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean bool = false;
    try {
        c = ConnectionPool.getConnection(alias);
        ps = c.prepareStatement("select id from account where id = ? and state = 2");
        ps.setString(1, acc);
        ISLogger.getLogger().error((Object)("ACC ACC: " + acc));
        rs = ps.executeQuery();
        bool = rs.next();
    }
    catch (Exception e) {
        e.printStackTrace();
        ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
        return bool;
    }
    finally {
        Utils.close(rs);
        Utils.close(ps);
        ConnectionPool.close(c);
    }
    Utils.close(rs);
    Utils.close(ps);
    ConnectionPool.close(c);
    return bool;
}


public static RowType_Customer getRowType_CustomerMoment(String client_b,Calendar calendar) {
	
	RowType_Customer rtc = new RowType_Customer();
	rtc.setCL_TYPE("1");
	rtc.setCLIENT_B("00394"+client_b);
	rtc.setRESIDENT("1");
	rtc.setSTATUS("10");
	rtc.setR_CITY("CITY");
	rtc.setR_STREET("STREET");
	rtc.setR_E_MAILS("");
	rtc.setR_MOB_PHONE("");
	rtc.setR_PHONE("");
	rtc.setR_CNTRY("UZB");
	rtc.setREC_DATE(calendar);
	rtc.setCLN_CAT("002");
	

	return rtc;
}

public static RowType_Agreement getRowType_AgreementMoment(Calendar calendar) {
	RowType_Agreement agrInfo = new RowType_Agreement();
	agrInfo.setBINCOD("98600301");
	agrInfo.setBRANCH("001");
	agrInfo.setBANK_CODE("03");
	agrInfo.setSTREET("STREET");
	agrInfo.setCITY("CITY");
	agrInfo.setCOUNTRY("UZB");
	agrInfo.setREP_LANG("1");
	agrInfo.setSTATUS("10");
	agrInfo.setPRODUCT("01");
	agrInfo.setENROLLED(calendar);
	

	return agrInfo;
}

public static RowType_AccountInfo_Base getRowType_AccountInfo_BaseMoment(String acc) {


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
	base_info.setTRANZ_ACCT(acc);

	System.out.println(base_info);
	return base_info;
}

public static RowType_CardInfo_PhysicalCard getRowType_CardInfo_PhysicalCardMoment() {

	RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
	physicalCard.setCARD_NAME("SOCIAL CARD");
	physicalCard.setSTATUS1("1");
	physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));

	return physicalCard;

}
public static RowType_CardInfo_LogicalCard getRowType_CardInfo_LogicalCardMoment() {

	RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
	logicalCard.setCOND_SET("001");
	logicalCard.setRISK_LEVEL("A");
	logicalCard.setBASE_SUPP("1");
	logicalCard.setBRANCH("001");
	logicalCard.setCARD_TYPE("01");
	logicalCard.setF_NAMES("");
	logicalCard.setRANGE_ID(BigDecimal.valueOf(2));

	return logicalCard;
}

public static List<Account> getallAccounts( String alias,String branch, String client ) throws ClassNotFoundException, SQLException  {

    List<Account> list = new ArrayList<Account>();
    Connection c = null;
    ResultSet rs = null;
    String accBal=null;
    PreparedStatement s=null;
   // String whr = "_____840%";
    String nm ="";

    try {
            c = ConnectionPool.getConnection( alias);
//    	Class.forName("oracle.jdbc.driver.OracleDriver");
//    	c = DriverManager.getConnection(
//                "jdbc:oracle:thin:@128.10.10.209:1521:agro", "admin", "admin");
    	
    	//System.out.println("DriverManager");
            
         Customer customer=HumoCardsService.getCustomerJ(c, branch, client);
            
         if (customer.getCode_type().equals("09")){
        	 accBal=ConnectionPool.getValue("HUMO_ACC_BAL_YUR"); 
         }
         
         else if (customer.getCode_type().equals("11")){
        	 accBal=ConnectionPool.getValue("HUMO_ACC_BAL_IP");  
         }
         else if (customer.getCode_type().equals("07")){
        	 accBal=ConnectionPool.getValue("HUMO_ACC_BAL_BANK");  
         }
         else {accBal="";}
            
        System.out.println("customer.getCode_type():"+customer.getCode_type());
             
        System.out.println("accBal:"+accBal);
        System.out.println("branch:"+branch);
        System.out.println("client:"+client);
        //PreparedStatement s = c.prepareStatement("SELECT * FROM Account where branch=? and client=? and acc_bal=? and state=2 and currency='000' and substr(id_order,1,1) in (2,3,4)"); // agro uchun
         s = c.prepareStatement("SELECT * FROM Account where branch=? and client=? and acc_bal=? and state=2 and currency='000'");
        // PreparedStatement s = c.prepareStatement("SELECT * FROM Account where branch='00394' and client='04003293' and state=3 and currency='000'");
            s.setString(1, branch);
            s.setString(2, client);
            s.setString(3, accBal);
            
            rs=s.executeQuery();
            while (rs.next()) {
                    list.add(new Account(
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
       
            
    } catch (SQLException e) {

            e.printStackTrace();
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
    	s.close();
    	rs.close();
            ConnectionPool.close(c);
    }
    return list;

}

public static Map<String, String> getCardNumber(Connection c) throws SQLException {

	Map<String, String> hashMap = new HashMap<String, String>();
	
	PreparedStatement psListCard = null;
	ResultSet rs = null;
	try {

		
		psListCard = c
				.prepareStatement("select c.card,c.real_card from humo_cards c,bf_empc_agreement a where c.card=a.card and c.card<>c.real_card");

		rs = psListCard.executeQuery();
		while (rs.next()) {

			hashMap.put(rs.getString("card"), rs.getString("real_card"));

		}

	} catch (Exception e) {
		ISLogger.getLogger().error(e);
		e.printStackTrace();
	} finally {
		
		if (psListCard != null) {

			psListCard.close();
		}
		if (rs != null) {
			rs.close();
		}

	}

	return hashMap;
}
public static void replaceCardToReal() throws SQLException, RemoteException {
	Connection c=null;
	try {
		 c = ConnectionPool.getConnection();

		Map<String, String> hashMap = getCardNumber(c);

		for (Map.Entry entry : hashMap.entrySet()) {

			updateCard(c,entry.getKey().toString(), entry.getValue().toString());
			
		}

	} catch (Exception e) {
		ISLogger.getLogger().error(e);
		

	}
	finally {
		c.commit();
		if (c!=null){c.close();}
		
		
	}

}

public static void updateCard(Connection c, String card,
		String realCard) {
	PreparedStatement psUpdateHumoCards = null;
	try {

		psUpdateHumoCards = c
				.prepareStatement("update bf_empc_agreement t set t.card=? where t.card=?");

		
		//String realCard=getNewRealCard(c,newCard);
		psUpdateHumoCards.setString(1, realCard);
		psUpdateHumoCards.setString(2, card );
		
		//ISLogger.getLogger().error(" oldCard3333 "+oldCard);
		//ISLogger.getLogger().error(" realCard3333 "+realCard);
	
          System.out.println("update bf_empc_agreement");

		psUpdateHumoCards.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();

		ISLogger.getLogger().error(CheckNull.getPstr(e));
	}
}
public static String addDobKorpKarta(Connection c,PreparedStatement ps,ResultSet rs,String code,String branch,Customer customer,String clientIdHumo,String clientB,String acc,String clCat) throws SQLException{
	String err=null;
	try{
		
		
		
	
	
	CardSetting cardSettingDobKorpKarta=getCardSettingDobKorpKarta(c,ps,rs,code,branch);
	HUMO_BINCOD=cardSettingDobKorpKarta.getBin();
	HUMO_BANK_C=cardSettingDobKorpKarta.getBankC();
	HUMO_BRANCHID=cardSettingDobKorpKarta.getBranchId();
	HUMO_GROUPC=cardSettingDobKorpKarta.getGroupC();
	HUMO_RANGEID=Long.valueOf(cardSettingDobKorpKarta.getRangeId());
	HUMO_CHIPAPPID=Long.valueOf(cardSettingDobKorpKarta.getChipAppId());
	OperationConnectionInfo connectionInfo =null;
	connectionInfo = getConInfo(HUMO_BANK_C, HUMO_GROUPC);
	
	IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
			ConnectionPool.getValue("HUMO_HOST"),
			ConnectionPool.getValue("HUMO_USERNAME"),
			ConnectionPool.getValue("HUMO_PASSWORD"));
	
	RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
			com.is.humo.HumoCardsService.getNewRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,"",customer,"",clientIdHumo));
	RowType_AccountInfo accountInfo = new RowType_AccountInfo();

	accountInfo.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,customer.getBranch(),c,rs));
	ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
	ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
	
	ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
	//accountsListInfo.value = ltaccounts;
	ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder(); 
	accountsListInfo.value = ltaccounts;
	
	RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
	RowType_CardInfo cardInfo = new RowType_CardInfo();
	ListType_CardInfo cards = new ListType_CardInfo();
	eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(Long.valueOf(HUMO_CHIPAPPID)));
    cardInfo.setLogicalCard(getRowType_CardInfo_LogicalCard2(getCustomerHumoForDobKarta(c,clientIdHumo),HUMO_RANGEID.toString(),HUMO_BRANCHID));
	cardInfo.setPhysicalCard(getRowType_CardInfo_PhysicalCard2(getCustomerHumoForDobKarta(c,clientIdHumo)));

	cardInfo.setEMV_Data(eMV_Data);
	cards.setRow(new RowType_CardInfo[] { cardInfo });
	cardsListInfo.value = cards;
	
	
	OperationResponseInfo operationResponsInfo = issuingPortProxy
	.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
	
	if (operationResponsInfo == null || operationResponsInfo.getError_description() != null) {
		ISLogger.getLogger().error("ошибка доб корп карты"
				+ operationResponsInfo.getError_description());
		
		err=operationResponsInfo.getError_description();
	
	} else {
		System.out.println("cardsListInfo888:"+cardsListInfo.toString());
		System.out.println("accountsListInfo888:"+accountsListInfo.toString());
		System.out.println("agreementInfo888:"+agreementInfo.toString());
	    insertNewClientAgreement(connectionInfo, agreementInfo, c);
		insertNewClientAccounts(accountsListInfo, cardsListInfo, acc, c);
		insertNewClientHumoCards(cardsListInfo, clientB,customer.getBranch(), "", acc, c,accountsListInfo);
	

		
	}	
	}catch(Exception e){
		e.printStackTrace();
		ISLogger.getLogger().error("Ошибка по откритие доб корп карты:"+e);
	}
	return err;
}

public static CardSetting getCardSettingDobKorpKarta(Connection c,PreparedStatement psSettingInfo,ResultSet rsSetting,String code, String branch) throws SQLException{
	CardSetting cardSetting	= new CardSetting();
	

String rangeId=null;
String branchId=null;
String chipAppId=null;
String bin=null;
String group_c=null;
String bank_c=null;


psSettingInfo = c
.prepareStatement("select t.range_id,t.branch_id,t.chip_app_id,t.bin,t.group_c,t.bank_c from ss_humo_type_of_card t where t.code=? and t.branch=?");
psSettingInfo.setString(1, code);
psSettingInfo.setString(2, branch);

System.out.println("code: "+code+"branch:"+branch);
rsSetting= psSettingInfo.executeQuery();
if (rsSetting.next()) {
	rangeId=rsSetting.getString("range_id");
	branchId=rsSetting.getString("branch_id");
	chipAppId=rsSetting.getString("chip_app_id");
	bin=rsSetting.getString("bin");
	group_c=rsSetting.getString("group_c");
	bank_c=rsSetting.getString("bank_c");
   	
}

cardSetting.setBranchId(branchId);
cardSetting.setRangeId(rangeId);
cardSetting.setChipAppId(chipAppId);
cardSetting.setBin(bin);
cardSetting.setGroupC(group_c);
cardSetting.setBankC(bank_c);


	
	return cardSetting;
}

public static String getClientIdHUMOForClientB(Connection c,PreparedStatement ps,ResultSet rs, String clientB) throws SQLException {

	String clientIdInHumo="";
	
	
	
	try {

		
		ps = c.prepareStatement("select client from humo_cards t where t.client_b=?");
		ps.setString(1, clientB);

		rs = ps.executeQuery();
		if (rs.next()) {

			clientIdInHumo=rs.getString("client");

		}

	} catch (Exception e) {
		ISLogger.getLogger().error(e);
		e.printStackTrace();
	
	}

	return clientIdInHumo;
}

public static CustomerHumo getCustomerHumoForDobKarta (Connection c,String client) throws SQLException{
	
	PreparedStatement pscustomerInfo=null;
	CustomerHumo customerHumo=new CustomerHumo();
	String name,surname,cardName;
	ResultSet rs=null;
	
	try{
	pscustomerInfo = c
	.prepareStatement("select cl.f_names,cl.surname,ca.card_name,cl.r_street,cl.r_city,cl.r_cntry from humo_cards ca, bf_empc_clients cl where ca.client=cl.client and cl.client=?");
	pscustomerInfo.setString(1, client);
	
     rs = pscustomerInfo.executeQuery();
    if (rs.next()) {
    	customerHumo.setName(rs.getString("f_names"));
    	customerHumo.setSurname(rs.getString("surname"));
    	customerHumo.setCardName(rs.getString("card_name"));
    	customerHumo.setStreet(rs.getString("r_street"));
    	customerHumo.setCity(rs.getString("r_city"));
    	customerHumo.setCntry(rs.getString("r_cntry"));
    	
     }
	}catch(Exception e){}
	finally{
		pscustomerInfo.close();
		rs.close();
		
	}
   return customerHumo;
}

public static Res addNewAgreement(Connection c,PreparedStatement ps, ResultSet rs,String code,String branch, Customer new_customer, String acc, String clientInHumo) throws JsonProcessingException, SQLException {
	ObjectMapper objectMapper = new ObjectMapper();
	Res res = new Res();
	boolean agre_exists = false;
	BigDecimal agre_nom = null;
	CardSetting cardSettingDobKorpKarta=getCardSettingDobKorpKarta(c,ps,rs,code,branch);
	// BigDecimal agre_nom = new BigDecimal("4694");

	// moy kod
	IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
			ConnectionPool.getValue("HUMO_HOST"),
			ConnectionPool.getValue("HUMO_USERNAME"),
			ConnectionPool.getValue("HUMO_PASSWORD"));
//	List<AccInfo> listAcc = null;
//	try {
//		listAcc = HumoCardsService.getAccInfo(branch, clientInHumo, "", issuingPortProxy);
//	} catch (Exception e1) {
//		System.out.println("getaccinfo error " + e1);
//		e1.printStackTrace();
//	}
//	for (int i = 0; i < listAcc.size(); i++) {
//		System.out.println("first for");
//		for (int k = 0; k < listAcc.get(i).getCardlist().size(); k++) {
//			System.out.println("second for");
//			CardInfo cardInfo = listAcc.get(i).getCardlist().get(k);
//
//			//agre_nom = BigDecimal.valueOf(Long.parseLong(cardInfo.getAGREEMENT_KEY()));
//			System.out.println("AGRE_NOM: " + agre_nom);
//			agre_exists = true;
//			break;
//
//		}
//	}

	if (agre_exists) {
		ISLogger.getLogger().error("AGREEMENT EXISTS!");
		try {
			OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(cardSettingDobKorpKarta.getBankC());
			
			connectionInfo.setGROUPC(cardSettingDobKorpKarta.getGroupC());
			String sysId=Utils.getExternalSession();
			ISLogger.getLogger().error("sysId:"+sysId);
			connectionInfo.setEXTERNAL_SESSION_ID(sysId);

			//KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
			//mainAgreementInfo.setAGRE_NOM(agre_nom);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());
			globus.issuing_v_01_02_xsd.ListType_AccountInfo accInfo = new ListType_AccountInfo();
			globus.issuing_v_01_02_xsd.RowType_AccountInfo[] rows = new globus.issuing_v_01_02_xsd.RowType_AccountInfo[1];
			globus.issuing_v_01_02_xsd.RowType_AccountInfo row = new RowType_AccountInfo();
			globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
			base_info.setC_ACCNT_TYPE("00");
			base_info.setCARD_ACCT(acc);
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
			base_info.setTRANZ_ACCT(acc);
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
			logicalCard.setRANGE_ID(new BigDecimal(cardSettingDobKorpKarta.getRangeId()));
			logicalCard.setCARD_TYPE("01");
			logicalCard.setBRANCH(cardSettingDobKorpKarta.getBranchId());
			logicalCard.setCLIENT(clientInHumo);
			RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
			physicalCard.setCARD_NAME(new_customer.getName());
			ISLogger.getLogger().error("CARD_NAME:"+new_customer.getName());
			physicalCard.setSTATUS1("1");
			physicalCard.setDESIGN_ID(BigDecimal.valueOf(1));
			RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
			eMV_Data.setCHIP_APP_ID(new BigDecimal(cardSettingDobKorpKarta.getChipAppId()));
			RowType_CardInfo cardInfo = new RowType_CardInfo();
			cardInfo.setLogicalCard(logicalCard);
			cardInfo.setPhysicalCard(physicalCard);
			cardInfo.setEMV_Data(eMV_Data);
			ListType_CardInfo cards = new ListType_CardInfo();
			cards.setRow(new RowType_CardInfo[] { cardInfo });
			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
			cardsListInfo.value = cards;
			ISLogger.getLogger().error("cardInfo: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardInfo));
			globus.issuing_v_01_02_xsd.RowType_Agreement agrInfo = new RowType_Agreement();
			Calendar cal = Calendar.getInstance();
			agrInfo.setAGRE_NOM(agre_nom);
			agrInfo.setBINCOD(cardSettingDobKorpKarta.getBin());
			
			agrInfo.setBRANCH(cardSettingDobKorpKarta.getBranchId());
			
			agrInfo.setBANK_CODE(cardSettingDobKorpKarta.getBankC());
			ISLogger.getLogger().error("Add new agreement CustomerService bank_c: " + cardSettingDobKorpKarta.getBankC());
			agrInfo.setSTREET(new_customer.getR_STREET() == null ? "STREET": new_customer.getR_STREET());
			agrInfo.setCITY(new_customer.getR_CITY() == null ? "UZB": new_customer.getR_CITY());
			agrInfo.setCOUNTRY(new_customer.getR_CNTRY() == null ? "UZB": new_customer.getR_CNTRY());
			agrInfo.setREP_LANG("1");
			agrInfo.setSTATUS("10");
			agrInfo.setPRODUCT("01");
			agrInfo.setENROLLED(cal);
			agrInfo.setCLIENT(clientInHumo);
			ISLogger.getLogger().error("agrInfo: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agrInfo));
			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(agrInfo);
			ISLogger.getLogger().error("10 connectionInfo1 "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo));
			ISLogger.getLogger().error("10 agreementInfo1 "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
			ISLogger.getLogger().error("10 accountsListInfo1 "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
			ISLogger.getLogger().error("10 cardsListInfo1 "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
	
			OperationResponseInfo orInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			ISLogger.getLogger().error("10 agreementInfo after=" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
			if (orInfo == null || orInfo.getError_description() != null) {
				ISLogger.getLogger().error("ADD NEW AGREEMENT ERROR DESC AGR EXISTS: "
						+ orInfo.getError_description());
				res.setCode(0);
				res.setName(orInfo == null ? "Не удалось отправить запрос в ЕМПЦ"
						: orInfo.getError_description());
			} else {
			
				try {
					ISLogger.getLogger().error("PERED INSERTAMI");
					c = ConnectionPool.getConnection();
					//CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Открыта карта No ["+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD()+ "]"));
					ISLogger.getLogger().error("10 cardsListInfo"+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
					ISLogger.getLogger().error("10 accountsListInfo"+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
					HumoCardsService.insertCards(cardsListInfo, accountsListInfo, new_customer.getId_client(), new_customer.getBranch(), c);
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					HumoCardsService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
					HumoCardsService.insertAgreement(connectionInfo, agreementInfo, c);
					c.commit();
					ISLogger.getLogger().error("10 NEW CARD: "+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
					psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
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
			connectionInfo.setBANK_C(cardSettingDobKorpKarta.getBankC());
			connectionInfo.setGROUPC(cardSettingDobKorpKarta.getGroupC());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());

			RowType_Agreement agreement = new RowType_Agreement();
			// !
			agreement.setBRANCH(cardSettingDobKorpKarta.getBranchId());
			// !
			agreement.setCLIENT(clientInHumo);
			agreement.setBANK_CODE(cardSettingDobKorpKarta.getBankC());
			ISLogger.getLogger().error("Add new agreeement bincod: "
					+ cardSettingDobKorpKarta.getBin());
			agreement.setBINCOD(cardSettingDobKorpKarta.getBin());
			agreement.setCITY("Tashkent");
			agreement.setENROLLED(calendar);
			agreement.setREP_LANG("1");
			agreement.setPRODUCT("01");
			agreement.setRISK_LEVEL("A");
			agreement.setSTREET("STREET");
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
			base_info.setTRANZ_ACCT(acc);

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

			logicalCard.setRANGE_ID(new BigDecimal(cardSettingDobKorpKarta.getRangeId()));
			
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
			eMV_Data.setCHIP_APP_ID(new BigDecimal(cardSettingDobKorpKarta.getChipAppId()));
			// !
			RowType_CardInfo cardInfo = new RowType_CardInfo();
			cardInfo.setLogicalCard(logicalCard);
			cardInfo.setPhysicalCard(physicalCard);
			cardInfo.setEMV_Data(eMV_Data);
			ListType_CardInfo cards = new ListType_CardInfo();
			cards.setRow(new RowType_CardInfo[] { cardInfo });

			ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
			cardsListInfo.value = cards;

//			ISLogger.getLogger().error("zapros agreementInfo="
//					+ objectMapper.writeValueAsString(agreementInfo));
//			ISLogger.getLogger().error("zapros connectionInfo="
//					+ objectMapper.writeValueAsString(connectionInfo));
//			ISLogger.getLogger().error("zapros accountsListInfo="
//					+ objectMapper.writeValueAsString(accountsListInfo));
//			ISLogger.getLogger().error("zapros cardsListInfo="
//					+ objectMapper.writeValueAsString(cardsListInfo));
			
			ObjectMapper mapper = new ObjectMapper();
			
			ISLogger.getLogger().error("zapros cardsListInfo="
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
			ISLogger.getLogger().error("zapros accountsListInfo="
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
			ISLogger.getLogger().error("zapros agreementInfo="
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
			ISLogger.getLogger().error("zapros connectionInfo="
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo));
			
			

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
				
				try {
					
					ISLogger.getLogger().info("PERED INSERTAMI");
					c = ConnectionPool.getConnection();
					ISLogger.getLogger().error("Otvet cardsListInfo="
							+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardsListInfo));
					ISLogger.getLogger().error("Otvet accountsListInfo="
							+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(accountsListInfo));
					ISLogger.getLogger().error("Otvet agreementInfo="
							+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agreementInfo));
					ISLogger.getLogger().error("Otvet connectionInfo="
							+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo));
					
					HumoCardsService.insertCards(cardsListInfo, accountsListInfo, new_customer.getId_client(), new_customer.getBranch(), c);
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					HumoCardsService.insertAccounts(accountsListInfo, agreementInfo.value.getCLIENT(), c, date);
					HumoCardsService.insertAgreement(connectionInfo, agreementInfo, c);
					c.commit();
					ISLogger.getLogger().error("NEW CARD: "
							+ cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
					psevdapan(cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
					res.setCode(1);
					res.setName("Карта открыта в HUMO");
				} catch (Exception e) {
					e.printStackTrace();
					res.setCode(0);
					res.setName(e.getMessage());
					ISLogger.getLogger().error(CheckNull.getPstr(e));
					Utils.rollback(c);
				} finally {
					
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

public static void insertCards(final ListType_CardInfoHolder cardsListInfo, final ListType_AccountInfoHolder accInfo, final String client_b, final String branch, final Connection c) throws Exception {
    PreparedStatement ps = null;
    final java.util.Date exp1 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1() == null) ? null : cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1().getTime();
    final java.util.Date exp2 = (cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2() == null) ? null : cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2().getTime();
    try {
        ps = c.prepareStatement("insert into humo_cards (client, client_b, branch, card, status1, status2, expiry1, expirity2, renew, renew_date, card_name, mc_name, m_name, stop_cause, renewed_card, design_id, instant, card_acct, tranz_acct, account_no) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, cardsListInfo.value.getRow(0).getLogicalCard().getCLIENT());
        ps.setString(2, client_b);
        ps.setString(3, branch);
        ps.setString(4, cardsListInfo.value.getRow(0).getLogicalCard().getCARD());
        ps.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard().getSTATUS1());
        ps.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard().getSTATUS2());
        ps.setDate(7, (exp1 == null) ? null : new java.sql.Date(exp1.getTime()));
        ps.setDate(8, (exp2 == null) ? null : new java.sql.Date(exp2.getTime()));
        ps.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard().getRENEW());
        ps.setDate(10, (exp1 == null) ? null : new java.sql.Date(exp1.getTime()));
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
    }
    catch (Exception e) {
		ISLogger.getLogger().error((Object) e);
		System.out.println("insertCards:" + e);
	}
    finally {
        Utils.close(ps);
    }
    Utils.close(ps);
}

public static void insertAccounts(final ListType_AccountInfoHolder accountsListInfo, final String client, final Connection c, Date date) throws Exception {
    PreparedStatement ps = null;
    try {
        ps = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values (?,?,?,?,?,?)");
        ps.setString(1, client);
        ps.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info().getACCOUNT_NO());
        ps.setString(3, accountsListInfo.value.getRow(0).getBase_info().getCARD_ACCT());
        ps.setString(4, accountsListInfo.value.getRow(0).getBase_info().getTRANZ_ACCT());
        if(!(accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY() == null)){
            ps.setDate(5, (accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY().getTime() == null) ? new java.sql.Date(date.getTime()) : new java.sql.Date(accountsListInfo.value.getRow(0).getBase_info().getAB_EXPIRITY().getTime().getTime()));
        }
        else{
        	ps.setDate(5, new java.sql.Date(date.getTime()));
        }
        if(!(accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE() == null)){
            ps.setDate(6, (accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE().getTime() == null) ? new java.sql.Date(date.getTime()) : new java.sql.Date(accountsListInfo.value.getRow(0).getBase_info().getCREATED_DATE().getTime().getTime()));
        }
        else{
        	ps.setDate(6, new java.sql.Date(date.getTime()));
        }
        ps.execute();
        ISLogger.getLogger().info((Object)"INSERT INTO BF_EMPC_ACCOUNTS");
    }
    finally {
        Utils.close(ps);
    }
    Utils.close(ps);
}

public static void insertAgreement(final OperationConnectionInfo connectionInfo, final RowType_AgreementHolder agreementInfo, final Connection c) throws Exception {
    PreparedStatement ps = null;
    try {
        ps = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
        ps.setString(1, agreementInfo.value.getAGRE_NOM() == null ? "0" : agreementInfo.value.getAGRE_NOM().toString());
        ps.setString(2, agreementInfo.value.getCLIENT());
        ps.setString(3, connectionInfo.getGROUPC());
        ps.setString(4, agreementInfo.value.getBINCOD());
        ps.setString(5, agreementInfo.value.getBANK_CODE());
        ps.setString(6, agreementInfo.value.getBRANCH());
        ps.setString(7, connectionInfo.getBANK_C());
        ps.setString(8, agreementInfo.value.getPRODUCT());
        ps.execute();
    }
    finally {
        Utils.close(ps);
    }
    Utils.close(ps);
}
public static void psevdapan(String card) {
	try {
		final IssuingPortProxy issuingPortProxy = new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"), ConnectionPool.getValue("HUMO_USERNAME"), ConnectionPool.getValue("HUMO_PASSWORD"));
		final String bankC = ConnectionPool.getValue("HUMO_BANK_C");
		final String groupC = ConnectionPool.getValue("HUMO_GROUPC");
		final String realCardNumber = HumoCardsService.getRealCardNumber(card, issuingPortProxy, bankC, groupC);
		insertRealCard(card, realCardNumber);
	} catch (Exception e) {
		ISLogger.getLogger().error("psevdapan ERROR: "+e.getLocalizedMessage());
	}
}

public static List<AccInfo> getAccInfo(String branch, String client, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)

{
	ISLogger.getLogger().error("ZAWOL V PROCEDURU");
	List<AccInfo> list = new ArrayList<AccInfo>();
	
	try
	{
		ObjectMapper mapper = new ObjectMapper();
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(ConnectionPool.getValue("HUMO_BANK_C"));
		connectionInfo.setGROUPC(ConnectionPool.getValue("HUMO_GROUPC"));
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		
		RowType_ListAccounts_Request parameters = new RowType_ListAccounts_Request();
		parameters.setCLIENT(client);			
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		ListType_GenericHolder details = new ListType_GenericHolder();
		issuingPortProxy.listAccounts(connectionInfo, parameters, responseInfo, details);
		if(details.value.getRow() != null){
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				AccInfo accInfo = new AccInfo();
				
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
					
					
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) accInfo.setAccount_no(Long.parseLong(details.value.getRow(i).getItem(j).getValue()));
					if (details.value.getRow(i).getItem(j).getName().equals("CLIENT")) accInfo.setClient(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT"))
					{
						accInfo.setCard_acct(details.value.getRow(i).getItem(j).getValue());
					}
					if (details.value.getRow(i).getItem(j).getName().equals("CTIME"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							accInfo.setCtime(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("AC_STATUS")) accInfo.setAc_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CL_STATUS")) accInfo.setCl_status(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("ACC_PRTY")) accInfo.setAcc_prty(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("C_ACCNT_TYPE")) accInfo.setC_accnt_type(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CCY")) accInfo.setCcy(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AB_EXPIRITY"))
					{
						if (!details.value.getRow(i).getItem(j).getName().equals(""))
						{
							accInfo.setAb_expirity(df.parse(details.value.getRow(i).getItem(j).getValue().substring(0, 10)));
						}
					}
					if (details.value.getRow(i).getItem(j).getName().equals("F_NAMES")) accInfo.setF_names(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("SURNAME")) accInfo.setSurname(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CITY")) accInfo.setCity(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) accInfo.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("TRANZ_ACCT")) accInfo.setTranz_acct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) accInfo.setCard(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STATUS"))
					{
						accInfo.setStatus1(details.value.getRow(i).getItem(j).getValue());
						accInfo.setAc_status(details.value.getRow(i).getItem(j).getValue());
						
					}
					if (details.value.getRow(i).getItem(j).getName().equals("PRODUCT")) accInfo.setProduct(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("STREET")) accInfo.setStreet(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("AGREEMENT_KEY")) accInfo.setAgreement_key(BigDecimal.valueOf(Long.parseLong(details.value.getRow(i).getItem(j).getValue())));
					if (details.value.getRow(i).getItem(j).getName().equals("AGRE_NOM")) accInfo.setAgre_nom(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CONTRACT")) accInfo.setContract(details.value.getRow(i).getItem(j).getValue());
				}
				accInfo.setCardlist(getCardInfo(branch, accInfo, alias, issuingPortProxy));
				
				list.add(accInfo);
			}
		}
		for (int k = 0; k < list.size(); k++) {
			AccInfo acc = list.get(k);
			for (int l = 0; l < acc.getCardlist().size(); l++) {
				CardInfo card = acc.getCardlist().get(l);
				ISLogger.getLogger().error("card.getCARD() = "+card.getCARD());
			}
		}
	} catch (NullPointerException e) {
		ISLogger.getLogger().error("ERRORRRR getAccInfo: "+e.getLocalizedMessage());
		// TODO: handle exception
	}
	catch (Exception e)
	{
		LtLogger.getLogger().error(CheckNull.getPstr(e));
		e.printStackTrace();
		System.out.println("Exception => " + e.getLocalizedMessage());
		ISLogger.getLogger().error("EXCEPTION: "+e.getLocalizedMessage());
	}
	return list;
}

public static List<CardInfo> getCardInfo(String branch,AccInfo account, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
{
	List<CardInfo> list = new ArrayList<CardInfo>();
	
	try
	{
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(ConnectionPool.getValue("HUMO_BANK_C"));
		connectionInfo.setGROUPC(ConnectionPool.getValue("HUMO_GROUPC"));
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		
		RowType_ListCardsByAccount_Request parameters = new RowType_ListCardsByAccount_Request();
		parameters.setACCOUNT_NO(BigInteger.valueOf(account.getAccount_no()));
		parameters.setCARD_ACCT(account.getCard_acct());
		parameters.setCCY(account.getCcy());
		//System.out.println("acc_no = "+parameters.getACCOUNT_NO());
		//System.out.println("CARD_ACCT = "+parameters.getCARD_ACCT());
		//System.out.println("CCY = "+parameters.getCCY());
		
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		
//		ListType_Generic listType_Generic = null;
		ListType_GenericHolder details = new ListType_GenericHolder();
		
		issuingPortProxy.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
		
		if(details.value.getRow()!=null)
		{
			for (int i = 0; i < details.value.getRow().length; i++)
			{
				CardInfo rs = new CardInfo();
				for (int j = 0; j < details.value.getRow(i).getItem().length; j++)
				{
					if (details.value.getRow(i).getItem(j).getName().equals("ACCOUNT_NO")) rs.setACCOUNT_NO(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD_ACCT")) rs.setCARD_ACCT(details.value.getRow(i).getItem(j).getValue());
					if (details.value.getRow(i).getItem(j).getName().equals("CARD")) {
						rs.setCARD(details.value.getRow(i).getItem(j).getValue());
					}
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
				getAccountBal(branch, rs, alias, issuingPortProxy);
				rs.setBank_account(account.getTranz_acct());
				rs.setBank_account_status(account.getStatus1());
				rs.setBank_account_Ccy(account.getCcy());
				list.add(rs);
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

public static Res getAccountBal(String branch,CardInfo rs, String alias, globus.IssuingWS.IssuingPortProxy issuingPortProxy)
{
	Res res = new Res();
	ObjectMapper objectMapper = new ObjectMapper();
	OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	connectionInfo.setBANK_C(ConnectionPool.getValue(""));
	connectionInfo.setGROUPC(ConnectionPool.getValue("HUMO_GROUPC"));
	connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
	OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
	ISLogger.getLogger().error("connconn");
	try {
		objectMapper.writeValueAsString(connectionInfo);
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ListType_Generic listType_Generic = null;
	ListType_GenericHolder details = new ListType_GenericHolder(listType_Generic);
	
	RowType_AccBalanceQueryByCard_Request parametersCard = new RowType_AccBalanceQueryByCard_Request();
	parametersCard.setBANK_C(ConnectionPool.getValue("HUMO_BANK_C"));
	parametersCard.setGROUPC(ConnectionPool.getValue("HUMO_GROUPC"));
	parametersCard.setCARD(rs.getCARD());
	ISLogger.getLogger().error("paramparam");
	try {
		objectMapper.writeValueAsString(parametersCard);
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
		System.out.println(responseInfo.value.getError_description().contains("\""));
	}
	catch (Exception e)
	{
		ISLogger.getLogger().error(CheckNull.getPstr(e));
		e.printStackTrace();
	}
	return res;
}

//public static String setSMS(String client,String card,String phoneNumber,String onOff) throws SQLException {
//
//	String card_name="";
//	String expiry="";
//	PreparedStatement ps=null;
//	ResultSet rs=null;
//	String line=null;
//	String expirySub="";
//	Connection c=ConnectionPool.getConnection();
//	
//	
//	try {
//
//		
//		ps = c.prepareStatement("select t.card_name,t.expiry1 from humo_cards t where t.client=? and t.real_card=?");
//		ps.setString(1, client);
//		ps.setString(2, card);
//
//		rs = ps.executeQuery();
//		if (rs.next()) {
//
//			card_name=rs.getString("card_name");
//			
//			expiry=df.format(rs.getDate("expiry1"));
//
//		}
//		
//		
//		
//		expirySub=expiry.substring(3,5)+expiry.substring(8);
//		
//		System.out.println("expirySub "+expirySub);
//		
//		URL url = new URL(ConnectionPool.getValue("HUMO_SMS")
//		+"PHONE_NUMBER="+phoneNumber+"&CARD_NUMBER="+card+"&EXPIRY="+expirySub+"&NAME="+card_name
//		+"&SURNAME=&STATE_CARD="+onOff+"&BANK_C="+ConnectionPool.getValue("HUMO_BANK_C")+"&CLIENT_ID="+client);
//		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//		line = in.readLine();
//		ISLogger.getLogger().error("SMS ON:"+line);
//		in.close();
//
//	} catch (Exception e) {
//	   ISLogger.getLogger().error("Ошибка SMS ON: "+e);
//	   line="Ошибка SMS "+onOff+": "+e;
//		e.printStackTrace();
//	
//	}
//
//	return line;
//}

public static String setSMS2(String client,String card,String phoneNumber,String onOff) throws SQLException {
	String myURL = ConnectionPool.getValue("HUMO_SMS");
	
	byte[] data = null;
	InputStream is = null;
	
	String card_name="";
	String expiry="";
	PreparedStatement ps=null;
	ResultSet rs=null;
	String line=null;
	String cardSh=null;
	String expirySub="";
	Connection c=ConnectionPool.getConnection();
	int responseCode=8;
	try {
		
		ps = c.prepareStatement("select t.card_name,t.expiry1,t.card from humo_cards t where t.client=? and t.real_card=?");
		ps.setString(1, client);
		ps.setString(2, card);

		rs = ps.executeQuery();
		if (rs.next()) {

			card_name=rs.getString("card_name");
			cardSh=rs.getString("card");
			
			expiry=df.format(rs.getDate("expiry1"));

		}
		
		
		
		expirySub=expiry.substring(3,5)+expiry.substring(8);
		
		String params = "PHONE_NUMBER="+"998"+phoneNumber+"&CARD_NUMBER="+cardSh+"&EXPIRY="+expirySub+"&NAME="+card_name
    		+"&SURNAME=&STATE_CARD="+onOff+"&BANK_C="+ConnectionPool.getValue("HUMO_BANK_C")+"&CLIENT_ID="+client;
    				
	    URL url = new URL(myURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setDoOutput(true);
	    conn.setDoInput(true);

	    conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
	    OutputStream os = conn.getOutputStream();
	    data = params.getBytes("UTF-8");
	    os.write(data);
	    data = null;

	    conn.connect();
	     responseCode= conn.getResponseCode();

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    is = conn.getInputStream();

	    byte[] buffer = new byte[8192]; // Такого вот размера буфер
	    // Далее, например, вот так читаем ответ
	    int bytesRead;
	    while ((bytesRead = is.read(buffer)) != -1) {
	        baos.write(buffer, 0, bytesRead);
	    }
	    data = baos.toByteArray();
	} catch (Exception e) {
	} finally {
	    try {
	        if (is != null)
	            is.close();
	    } catch (Exception ex) {}
	}
	return String.valueOf(responseCode);
}

//public static String setSMS3(String client,String card,String phoneNumber,String onOff) throws SQLException {
//
//	String card_name="";
//	String expiry="";
//	PreparedStatement ps=null;
//	ResultSet rs=null;
//	String line=null;
//	String expirySub="";
//	Connection c=ConnectionPool.getConnection();
//	
//	
//	try {
//
//		
//		ps = c.prepareStatement("select t.card_name,t.expiry1 from humo_cards t where t.client=? and t.real_card=?");
//		ps.setString(1, client);
//		ps.setString(2, card);
//
//		rs = ps.executeQuery();
//		if (rs.next()) {
//
//			card_name=rs.getString("card_name");
//			
//			expiry=df.format(rs.getDate("expiry1"));
//
//		}
//		
//		
//		
//		expirySub=expiry.substring(3,5)+expiry.substring(8);
//		
//		System.out.println("expirySub "+expirySub);
//		
//		URL url = new URL("http://ptsv2.com/t/w4tkw-1609243897/post?"
//		+"PHONE_NUMBER="+phoneNumber+"&CARD_NUMBER="+card+"&EXPIRY="+expirySub+"&NAME="+card_name
//		+"&SURNAME=&STATE_CARD="+onOff+"&BANK_C="+ConnectionPool.getValue("HUMO_BANK_C")+"&CLIENT_ID="+8888);
//		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//		line = in.readLine();
//		ISLogger.getLogger().error("SMS ON:"+line);
//		in.close();
//
//	} catch (Exception e) {
//	   ISLogger.getLogger().error("Ошибка SMS ON: "+e);
//	   line="Ошибка SMS "+onOff+": "+e;
//		e.printStackTrace();
//	
//	}
//
//	return line;
//}
//
public static String getTelNomer(String realCard) throws SQLException{
	PreparedStatement ps=null;
	ResultSet rs=null;
	String phone=null;
	Connection c=ConnectionPool.getConnection();
	
	
	try{
	ps = c.prepareStatement("select phone from humo_card_sms_state t where t.card=?");
	ps.setString(1, realCard);
	

	rs = ps.executeQuery();
	if (rs.next()) {

		phone=rs.getString("phone");
		
		}
	
	else {
		phone="+998";
	}
	
}catch(Exception e){}
finally{
	
}
return phone;
}
}


