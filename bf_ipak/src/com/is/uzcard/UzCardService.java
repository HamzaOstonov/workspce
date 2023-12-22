package com.is.uzcard;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.StaticBucketMap;
//import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.apache.log4j.Logger;

//import com.is.ConnectionPool;
//import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.crm.ho.Customer;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;
import com.is.uzcard.Constants.FILTER_FIELD_TYPE;
import com.is.uzcard.model.AccountInfo;
import com.is.uzcard.model.BTRT;
import com.is.uzcard.model.BTRT03;
import com.is.uzcard.model.BTRT30;
import com.is.uzcard.model.Card;
import com.is.uzcard.model.CardMaskInfo;
import com.is.uzcard.model.Credential;
import com.is.uzcard.util.ConnectionPoolUtil;

import sun.util.logging.resources.logging;

public class UzCardService {
	
	private static final Logger log = ISLogger.getLogger(); 

	private static ConnectionPoolUtil cPoolUtil;
	static {
		 cPoolUtil = ConnectionPoolUtil.getInstance();
	}

	public static List<RefData> getActionsList(String branch) {
		return RefDataService.getRefData("select id data, app_type||' - '||name label from card_tlv_app ", branch);
	}

	public static List<RefData> getCountryList(String alias) {
		return RefDataService.getRefData("select code_str data, name label from S_STR order by 2", alias);
	}

	public static List<RefData> getRegionList(String alias) {
		return RefDataService.getRefData("select region_id data, region_nam label from S_REGION order by 2", alias);
	}

	public static List<RefData> getRefData(String refDataType, String alias) {
		StringBuilder sBuilder = new StringBuilder(Constants.SQL.comboBoxDataSQL);
		sBuilder.append(refDataType);
		return RefDataService.getRefData(sBuilder.toString(), alias);
	}

	public static List<RefData> getContractIdList(String alias) {
		return RefDataService.getRefData(
				"select s.code_card data,c.name,'( '||s.code_card||' )  '||c.name label,c.status,s.branch,s.code_card from ss_uzcard_card c, ss_uzcard_card_spr s "
						+ "where c.id_card=s.id_card and lpad(s.branch,5,'0')=(select lpad(t.branch,5,'0') from card_sets t where rownum=1) and c.status='P'",
				alias);
	};

	public static List<RefData> getCardMaskList(String alias, String contractId) {
		return RefDataService.getRefData(
				"select * from  SS_UZCARD_CARD_MASK m where m.id_card=(select id_card from ss_uzcard_card_spr where code_card="
						+ contractId + ")",
				alias);
	}

	public static boolean isExistClient(String branch, String clientId) {
		Connection connection = null;
		boolean result = false;

		try {
			connection = cPoolUtil.getConnectionByBranch(branch);
			PreparedStatement pStatement = connection.prepareStatement(Constants.SQL.clientBTRT2);
			pStatement.setString(1, branch);
			pStatement.setString(2, clientId);
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				int i = rSet.getInt(1);
				if (i != 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(connection);
		}
		return result;
	}

	public static String getWorkName(String branch, String workId) {
		Connection c = null;
		String res = null;
		try {
			c = cPoolUtil.getConnectionByBranch(branch);

			PreparedStatement statement = c
					.prepareStatement(Constants.SQL.viewClient + " and a.BRANCH =? and a.Id_Client=?");
			statement.setString(1, branch);
			statement.setString(2, workId);
			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				res = rSet.getString("NAME");
			}
			rSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}
		return res;

	}

	public static List<Card> getcardList(String clientId, String branch) {

		List<Card> list = new ArrayList<Card>();
		Connection c = null;

		try {
			c = cPoolUtil.getConnection();
			PreparedStatement s = c.prepareStatement(
					//"select * from card c where c.def_pos_account in (select branch||id from account where client =?)");
					"select * from card c where c.branch=? and c.def_pos_account like ?");
			 s.setString(1, branch);
			s.setString(2, "______________"+clientId+"%");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(new Card(rs.getString("branch"), rs.getString("company_name"), rs.getString("card_number"),
						rs.getInt("is_primary"), rs.getString("card_type"), rs.getString("def_atm_account"),
						rs.getString("def_pos_account"), rs.getString("embossed_ch_name"),
						rs.getString("expiration_date"), rs.getString("card_status"), rs.getString("contract_id"),
						rs.getDate("date_open"), rs.getDate("date_close"), rs.getString("hot_card_status")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(c);
		}
		return list;

	}

	public static BTRT03 getBTRT03(String customerId, String branch) {
		Connection connection = null;
		BTRT03 btrt03 = null;

		try {
			connection = cPoolUtil.getConnectionByBranch(branch);
			PreparedStatement pStatement = connection.prepareStatement(
					Constants.SQL.BTRT03SQL + Constants.SQL.VIEW_03 + " and substr(b.customer_id,-8)=?");
			pStatement.setString(1, branch);
			pStatement.setString(2, customerId);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				btrt03 = new BTRT03();
				btrt03.setA_proc_mode(rSet.getInt("A_PROC_MODE"));
				btrt03.setAcc(rSet.getString("acc"));
				btrt03.setAccount_number(rSet.getString("ACCOUNT_NUMBER"));
				btrt03.setAccount_type(rSet.getString("ACCOUNT_TYPE"));
				btrt03.setAddress_id(rSet.getString("ADDRESS_ID"));
				btrt03.setAddress_line1(rSet.getString("ADDRESS_LINE1"));
				btrt03.setAddress_line2(rSet.getString("ADDRESS_LINE2"));
				btrt03.setAddress_type(rSet.getString("ADDRESS_TYPE"));
				btrt03.setApp_id(rSet.getDouble("app_id"));
				btrt03.setApp_tag(rSet.getString("APP_TAG"));
				btrt03.setApp_type(rSet.getString("APP_TYPE"));
				btrt03.setBirth_date(rSet.getDate("BIRTH_DATE"));
				btrt03.setBranch(rSet.getString("branch"));
				btrt03.setCard_number(rSet.getString("CARD_NUMBER"));
				btrt03.setCard_type(rSet.getString("CARD_TYPE"));
				btrt03.setCompany_name_card(rSet.getString("COMPANY_NAME"));
				btrt03.setContract_id(rSet.getString("CONTRACT_ID"));
				btrt03.setCountry(rSet.getString("COUNTRY"));
				btrt03.setCustomer_id(rSet.getString("CUSTOMER_ID"));
				btrt03.setClient(rSet.getString("CustomerId"));
				btrt03.setDealID(rSet.getInt("DEAL_ID"));
				btrt03.setDef_atm_account(rSet.getString("DEF_ATM_ACCOUNT"));
				btrt03.setDef_pos_account(rSet.getString("DEF_POS_ACCOUNT"));
				btrt03.setEmail(rSet.getString("EMAIL"));
				btrt03.setEmbossed_ch_name(rSet.getString("EMBOSSED_CH_NAME"));
				btrt03.setEmp_id(rSet.getString("EMP_ID"));
				btrt03.setExpiration_date(rSet.getDate("EXPIRATION_DATE"));
				btrt03.setFirst_name(rSet.getString("FIRST_NAME"));
				btrt03.setSecond_name(rSet.getString("SURNAME"));
				btrt03.setPatronymic(rSet.getString("SECOND_NAME"));
				btrt03.setIs_primary(rSet.getInt("IS_PRIMARY"));
				btrt03.setMobile_phone(rSet.getString("MOBILE_PHONE"));
				btrt03.setP_id_type(rSet.getString("P_ID_TYPE"));
				btrt03.setP_id_number(rSet.getString("P_ID_NUMBER"));
				btrt03.setP_id_series(rSet.getString("P_ID_SERIES"));
				btrt03.setP_proc_mode(rSet.getInt("P_PROC_MODE"));
				btrt03.setPerson_id(rSet.getString("PERSON_ID"));
				btrt03.setPrimary_phone(rSet.getString("PRIMARY_PHONE"));
				btrt03.setRegion(rSet.getString("REGION"));
				btrt03.setRec_number(rSet.getDouble("REC_NUMBER"));
				btrt03.setState(rSet.getInt("state"));
				btrt03.setState_name(rSet.getString("name"));
			}
			rSet.close();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return btrt03;
	}

	public static BTRT30 getBTRT30(String customerId, Credential cr) {
		// TODO сам не понимаю как это работает, всё сложно :(
		Connection connection = null;
		BTRT30 btrt30 = null;
		ResultSet rSetCard = null, rSetClient = null;

		try {
			btrt30 = new BTRT30();
			connection = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());
			PreparedStatement pStatement_card = connection.prepareStatement(Constants.SQL.BTRT30SQL_CARD);
			pStatement_card.setString(1, cr.getBranch());
			pStatement_card.setString(2, customerId);
			rSetCard = pStatement_card.executeQuery();
			if (rSetCard.next()) {

				btrt30.setA_proc_mode(Integer.parseInt(rSetCard.getString("A_PROC_MODE")));
				btrt30.setAcc(rSetCard.getString("acc"));
				btrt30.setAddress_id(rSetCard.getString("ADDRESS_ID"));
				btrt30.setAddress_line1(rSetCard.getString("ADDRESS_LINE1"));
				btrt30.setAddress_line2(rSetCard.getString("ADDRESS_LINE2"));
				btrt30.setAddress_type(rSetCard.getString("ADDRESS_TYPE"));
				// btrt30.setApp_type(rSet.getString("APP_TYPE"));
				btrt30.setBranch(rSetCard.getString("branch"));
				btrt30.setCard_number(rSetCard.getString("CARD_NUMBER"));
				btrt30.setCustomer_id(rSetCard.getString("CUSTOMER_ID"));
				btrt30.setClient(rSetCard.getString("client"));
				btrt30.setFirst_name(rSetCard.getString("FIRST_NAME"));
				btrt30.setSecond_name(rSetCard.getString("SURNAME"));
				btrt30.setPatronymic(rSetCard.getString("SECOND_NAME"));
				btrt30.setP_id_type(rSetCard.getString("P_ID_TYPE"));
				btrt30.setP_id_number(rSetCard.getString("P_ID_NUMBER"));
				btrt30.setP_id_series(rSetCard.getString("P_ID_SERIES"));
				btrt30.setP_id_issue_date(rSetCard.getDate("P_ID_ISSUE_DATE"));
				btrt30.setPerson_id(rSetCard.getString("PERSON_ID"));
				btrt30.setSecurity_id(rSetCard.getString("SECURITY_ID"));
				btrt30.setResidence(rSetCard.getString("RESIDENCE"));
				btrt30.setSex(rSetCard.getString("SEX"));
				btrt30.setP_id_authority(rSetCard.getString("P_ID_AUTHORITY"));
				btrt30.setNewP_ID_type(rSetCard.getString("P_ID_TYPE"));
			}
			rSetCard.close();
			pStatement_card.close();

			// ///////////////////////////////////////////////////////////////

			PreparedStatement pStatement_client = connection.prepareStatement(Constants.SQL.BTRT30SQL_CLIENT);
			pStatement_client.setString(1, cr.getBranch());
			pStatement_client.setString(2, customerId);
			rSetClient = pStatement_client.executeQuery();
			rSetClient.next();

			btrt30.setBirth_date(rSetClient.getDate("BIRTH_DATE"));
			btrt30.setNewDateOfBirth(rSetClient.getDate("BIRTH_DATE"));
			btrt30.setCustomer_desc(rSetClient.getString("CC_name"));
			btrt30.setEmail(rSetClient.getString("p_email_address"));
			btrt30.setMobile_phone(rSetClient.getString("p_phone_mobile"));
			btrt30.setPrimary_phone(rSetClient.getString("p_phone_home"));
			btrt30.setCountry(rSetClient.getString("code_country"));
			btrt30.setRegion(rSetClient.getString("p_code_adr_region"));
			btrt30.setNewAddressLine1(rSetClient.getString("p_post_address"));
			btrt30.setNewSecondName(rSetClient.getString("second_name"));
			btrt30.setNewFirstName(rSetClient.getString("FIRST_NAME"));
			btrt30.setNewPatronymic(rSetClient.getString("PATRONYMIC"));
			btrt30.setNewP_ID_authority(rSetClient.getString("p_id_authority"));
			btrt30.setNewP_ID_number(rSetClient.getString("p_id_number"));
			btrt30.setNewP_ID_series(rSetClient.getString("p_id_series"));
			btrt30.setP_id_issue_date(rSetClient.getDate("p_id_issue_date"));

			rSetClient.close();
			pStatement_client.close();

		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(connection);
		}
		return btrt30;
	}

	public static List<BTRT> getBTRT20List(String branch, String customerId) {
		Connection connection = null;
		List<BTRT> result = new ArrayList<BTRT>();

		try {
			connection = cPoolUtil.getConnectionByBranch(branch);
			PreparedStatement pStatement = connection.prepareStatement(Constants.SQL.BTRT20SQL);
			pStatement.setString(1, branch);
			pStatement.setString(2, customerId);
			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				BTRT btrt = new BTRT();
				btrt.setApp_id(rSet.getDouble("app_id"));
				btrt.setBirth_date(rSet.getDate("BIRTH_DATE"));
				btrt.setCard_number(rSet.getString("CARD_NUMBER"));
				btrt.setCard_type(rSet.getString("CARD_TYPE"));
				btrt.setContract_id(rSet.getString("CONTRACT_ID"));
				btrt.setEmbossed_ch_name(rSet.getString("EMBOSSED_CH_NAME"));
				btrt.setEmp_id(rSet.getString("EMP_ID"));
				btrt.setFirst_name(rSet.getString("FIRST_NAME"));
				btrt.setSecond_name(rSet.getString("SURNAME"));
				btrt.setPatronymic(rSet.getString("SECOND_NAME"));
				btrt.setP_id_type(rSet.getString("P_ID_TYPE"));
				btrt.setP_id_number(rSet.getString("P_ID_NUMBER"));
				btrt.setP_id_series(rSet.getString("P_ID_SERIES"));
				btrt.setP_proc_mode(rSet.getInt("P_PROC_MODE"));
				btrt.setPerson_id(rSet.getString("PERSON_ID"));
				btrt.setRec_number(rSet.getDouble("REC_NUMBER"));
				btrt.setBranch(rSet.getString("BRANCH"));
				btrt.setCustomer_id(rSet.getString("CUSTOMER_ID"));
				btrt.setVip_flag(rSet.getString("VIP_FLAG"));
				btrt.setOkpo(rSet.getString("OKPO"));
				btrt.setInn(rSet.getString("INN"));
				result.add(btrt);
			}
			rSet.close();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return result;
	}

	public static Customer getCustomerById(Credential cr, String customerId, String branch) throws Exception {

		Customer customer = new Customer();
		Connection c = null;

		try {
			c = cPoolUtil.getConnectionByBranch(cr.getUn(), cr.getPwd(), branch);
			/*
			 * PreparedStatement ps =
			 * c.prepareStatement("SELECT * FROM v_bfcustomer WHERE id_client=?"
			 * ); ps.setString(1, customerId);
			 */
			PreparedStatement ps = c.prepareStatement(Constants.SQL.viewClient + " and a.BRANCH =? and a.Id_Client=?");
			ps.setString(1, branch);
			ps.setString(2, customerId);
			ResultSet rs = ps.executeQuery();
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
				customer.setP_family(rs.getString("family"));
				customer.setP_first_name(rs.getString("first_name"));
				customer.setP_patronymic(rs.getString("patronymic"));
				// customer.setTieto_customer_id(rs.getString("TIETO_CUSTOMER_ID"));
			}
		} catch (Exception e) {
			// LtLogger.getLogger().error(CheckNull.getPstr(e));
			log.error(CheckNull.getPstr(e));
			// e.printStackTrace();
			throw e;
		} finally {
			cPoolUtil.close(c);
		}
		return customer;
	}

	public static List<BTRT> getBTRT25List(String branch, String customerId) {
		List<BTRT> result = new ArrayList<BTRT>();
		Connection connection = null;

		try {
			connection = cPoolUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(Constants.SQL.BTRT25SQL);
			pStatement.setString(1, branch);
			pStatement.setString(2, customerId);

			ResultSet rSet = pStatement.executeQuery();

			while (rSet.next()) {
				BTRT btrt = new BTRT();
				btrt.setBranch(rSet.getString("BRANCH"));
				btrt.setAcc(rSet.getString("ACC"));
				btrt.setAccount_type(rSet.getString("ACCOUNT_TYPE"));
				btrt.setAccount_number(rSet.getString("ACCOUNT_NUMBER"));
				btrt.setCard_number(rSet.getString("CARD_NUMBER"));
				btrt.setContract_id(rSet.getString("CONTRACT_ID"));
				btrt.setCurrency(rSet.getString("CURRENCY"));
				btrt.setCustomer_id(rSet.getString("CUSTOMER_ID"));
				btrt.setEmp_id(getEmpIdBTRT25());
				btrt.setPerson_id(rSet.getString("PERSON_ID"));
				btrt.setSecond_name(rSet.getString("SURNAME"));
				btrt.setFirst_name(rSet.getString("first_name"));
				btrt.setPatronymic(rSet.getString("second_name"));
				btrt.setCardExpirationDate(rSet.getString("expiration_date"));
				btrt.setClient(rSet.getString("client"));
				result.add(btrt);
			}
			rSet.close();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return result;
	}

	/*
	 * public static BTRT01 getClientData(String branch, String clientId){
	 * BTRT01 data = null; Connection c= null;
	 * 
	 * try{ c= ConnectionPool.getConnection(); Statement s =
	 * c.createStatement(); ResultSet rs = s.executeQuery(viewClient +
	 * " and a.BRANCH =?"+branch+" and a.Id_Client="+clientId); rs.next(); data
	 * = new BTRT01(); data.setCustomer_id(rs.getString("id_client"));
	 * data.setCustomer_desc(rs.getString("name"));
	 * data.setCountry(rs.getString("code_country"));
	 * data.setResidence(rs.getString("code_resident")); //TODO
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * ConnectionPool.close(c); } return data; }
	 */

	public static boolean isAccountExists(String branch, String accountId, String clientId) {
		Connection c = null;
		boolean res = false;

		try {
			c = cPoolUtil.getConnectionByBranch(branch);
			PreparedStatement s = c
					.prepareStatement("select * from account t where t.branch=? and t.id=? and t.client=?");
			s.setString(1, branch);
			s.setString(2, accountId);
			s.setString(3, clientId);
			ResultSet rs = s.executeQuery();
			res = rs.next();
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	public static boolean isAccountExists(String branch, String accountId, String clientId, int state) {
		Connection c = null;
		boolean res = false;

		try {
			c = cPoolUtil.getConnectionByBranch(branch);
			PreparedStatement s = c.prepareStatement(
					"select * from account t where t.branch=? and t.id=? and t.client=? and t.state=?");
			s.setString(1, branch);
			s.setString(2, accountId);
			s.setString(3, clientId);
			s.setInt(4, state);
			ResultSet rs = s.executeQuery();
			res = rs.next();
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	public static boolean saveStEmpAcc(String accStEmp, String innStEmp, String clientId, String branch)
			throws SQLException {
		Connection connection = null;
		boolean result = false;

		try {
			connection = cPoolUtil.getConnection();
			PreparedStatement pStatement = connection
					.prepareStatement("select * from s_budspr t where t.account=? and t.inn=? and act='A'");
			pStatement.setString(1, accStEmp);
			pStatement.setString(2, innStEmp);
			ResultSet rSet = pStatement.executeQuery();
			if (!rSet.next())
				return false;
			pStatement = connection.prepareStatement(
					"Delete CARD_CLIENT_BUDGET where branch=? and budget_account=? and budget_inn=? and Employee_id=?");
			pStatement.setString(1, branch);
			pStatement.setString(2, accStEmp);
			pStatement.setString(3, innStEmp);
			pStatement.setString(4, clientId);
			pStatement.execute();

			pStatement = connection.prepareStatement(
					"insert into CARD_CLIENT_BUDGET (branch,budget_account,budget_inn,Employee_id) values(?,?,?,?)");
			pStatement.setString(1, branch);
			pStatement.setString(2, accStEmp);
			pStatement.setString(3, innStEmp);
			pStatement.setString(4, clientId);
			pStatement.execute();
			rSet.close();
			pStatement.close();
			result = true;
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			throw e;
		} finally {
			cPoolUtil.close(connection);
		}
		return result;
	}

	public static boolean saveNotStateEmployer(String branch, String emploee_id, String customer_id, String account)
			throws Exception {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet rSet = null;
		boolean result = false;

		try {
			c = cPoolUtil.getConnection();
			s = c.prepareStatement("delete  CARD_CLIENT_WORK where branch=? and employee_id =?");
			s.setString(1, branch);
			s.setString(2, emploee_id);
			s.execute();

			s = c.prepareStatement("insert into CARD_CLIENT_WORK (branch,customer_id,employee_id,ACC) values(?,?,?,?)");
			s.setString(1, branch);
			s.setString(2, customer_id);
			s.setString(3, emploee_id);
			s.setString(4, account);
			s.execute();

			c.commit();

			result = true;
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			c.rollback();
			throw e;
		} finally {
			cPoolUtil.close(c);
		}
		return result;

	}

	public static List<CardMaskInfo> getCardMaskInfoList(String contractIdId) {// TODO
		List<CardMaskInfo> res = new ArrayList<CardMaskInfo>();
		Connection c = null;
		try {
			c = cPoolUtil.getConnection();
			PreparedStatement s = c.prepareStatement(
					"select * from  SS_UZCARD_CARD_MASK m where m.id_card=(select id_card from ss_uzcard_card_spr where code_card=?)");
			s.setString(1, contractIdId);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				res.add(new CardMaskInfo(rs.getString("ACC_BAL"), rs.getString("ACC_CUR"), rs.getString("ACC_NUM")));
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	public static List<AccountInfo> getAccountInfoList(String branch, String clientId) {
		List<AccountInfo> res = new ArrayList<AccountInfo>();
		Connection connection = null;
		try {
			connection = cPoolUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select Id, Name from account t where t.branch=? and  t.currency='000' and t.client=? and t.acc_bal in (select acc_bal from SS_UZCARD_CARD_MASK) and t.state =2");
			statement.setString(1, branch);
			statement.setString(2, clientId);
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				res.add(new AccountInfo(rSet.getString("Id"), rSet.getString("Name")));
			}
			rSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return res;
	}

	public static List<AccountInfo> getWorkInfoList(String branch) {
		List<AccountInfo> res = new ArrayList<AccountInfo>();
		Connection connection = null;
		try {
			connection = cPoolUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select Id_Client Id, Name Name from client t Where t.Branch =? and t.state = 2 and t.CODE_SUBJECT='J'");
			statement.setString(1, branch);
			ResultSet rSet = statement.executeQuery();
			// .executeQuery("select Id_Client Id, Name Name from client t Where
			// t.Branch ="+branch+" and t.state = 2 and t.CODE_SUBJECT='J'");
			while (rSet.next()) {
				res.add(new AccountInfo(rSet.getString("Id"), rSet.getString("Name")));
			}
			rSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return res;
	}

	public static int getCount(String filter, String branch, FILTER_FIELD_TYPE fieldType) {
		int n = 0;
		Connection connection = null;
		PreparedStatement pStatement = null;

		try {
			connection = cPoolUtil.getConnection();
			switch (fieldType) {
			case NOT_STATE_EMP:
				pStatement = connection.prepareStatement(
						"select count(*) from client t Where t.Branch =? and t.state = 2 and t.CODE_SUBJECT='J' and (Id_Client like '%"
								+ filter + "%' or Name like '%" + filter + "%')");
				pStatement.setString(1, branch);
				break;
			case STATE_EMP:
				pStatement = connection
						.prepareStatement("select count(*) from s_budspr  t where t.act='A' and (account like '%"
								+ filter + "%' or Name like '%" + filter + "%')");
				break;
			default:
				return n;
			}
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next())
				n = rSet.getInt(1);
			rSet.close();
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}

		return n;

	}

	/*
	 * public static List<AccountInfo> applyFilter(String filter, String branch,
	 * FILTER_FIELD_TYPE fieldType) { List<AccountInfo> res = new
	 * ArrayList<AccountInfo>(); Connection connection = null; PreparedStatement
	 * pStatement = null;
	 * 
	 * try { connection = ConnectionPool.getConnection(); switch (fieldType) {
	 * case NOT_STATE_EMP: pStatement = connection
	 * .prepareStatement("select Id_Client Id, Name Name from client t Where t.Branch =? and t.state = 2 and t.CODE_SUBJECT='J' and (Id_Client like '%"
	 * + filter + "%' or Name like '%" + filter + "%')");
	 * pStatement.setString(1, branch); break; case STATE_EMP: pStatement =
	 * connection
	 * .prepareStatement("select t.account id, t.inn||'   '||t.name name from s_budspr  t where t.act='A' and (account like '%"
	 * + filter + "%' or Name like '%" + filter + "%')"); break; default: return
	 * res; } ResultSet rSet = pStatement.executeQuery(); while (rSet.next()) {
	 * res.add(new AccountInfo(rSet.getString("Id"), rSet .getString("Name")));
	 * } rSet.close(); pStatement.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } finally { ConnectionPool.close(connection); }
	 * 
	 * return res;
	 * 
	 * }
	 */

	// public static List<E>

	public static List<AccountInfo> getStEmpInfoList() {
		List<AccountInfo> res = new ArrayList<AccountInfo>();
		Connection connection = null;
		try {
			connection = cPoolUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"select t.account id, t.inn||'   '||t.name name from s_budspr  t where t.act='A'");
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				res.add(new AccountInfo(rSet.getString("Id"), rSet.getString("Name")));
			}
			rSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(connection);
		}
		return res;
	}

	public static Res openAccount(CardMaskInfo cmInfo, Credential cr) {
		Res res = new Res(2, "ошибка");
		Connection c = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {

			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			ccs = c.prepareCall("{ call Param.clearparam() }");
			acs = c.prepareCall("{ call PROC_CARDAPP.open_acc(?,?,?,?)}");
			PreparedStatement pStatement = c.prepareStatement("select param.getparam('ID') id from dual");
			// getp = c.prepareCall("{? = call Param.GetParam('ID') }");
			// getp.registerOutParameter(1, java.sql.Types.VARCHAR);

			ccs.execute();

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");

			cs.setString(1, "P_ID_CLIENT");
			cs.setString(2, cmInfo.getClient_id());
			cs.execute();

			cs.setString(1, "P_NAME");
			cs.setString(2, cmInfo.getClient_name());
			cs.execute();

			cs.setString(1, "P_BAL_ACC");
			cs.setString(2, cmInfo.getAcc_bal());
			cs.execute();

			cs.setString(1, "P_ORDERACC");
			cs.setString(2, cmInfo.getAcc_num());
			cs.execute();

			cs.setString(1, "P_CURRENCY");
			cs.setString(2, cmInfo.getAcc_currency());
			cs.execute();

			cs.setString(1, "P_CONTRACT_ID");
			cs.setString(2, cmInfo.getContract_id());
			cs.execute();

			acs.setString(1, cr.getBranch());
			acs.setString(2, cmInfo.getClient_id());
			acs.setString(3, cmInfo.getClient_name());
			acs.setString(4, cmInfo.getAcc_num());
			acs.execute();

			// getp.execute();
			// res = getp.getString(1);
			ResultSet rSet = pStatement.executeQuery();
			if (rSet.next()) {
				res = new Res(0, rSet.getString("id"));
			}
			c.commit();

			cs.close();
			acs.close();
			ccs.close();

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res = new Res(1, e.getMessage());
			try {
				c.rollback();
			} catch (Exception g) {

				LtLogger.getLogger().error(CheckNull.getPstr(e));
				res = new Res(1, e.getMessage());
			}

			e.printStackTrace();
			// return "";
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	public static List<AccountInfo> getPageData(int activePage, int pageSize, String filter, String branch,
			FILTER_FIELD_TYPE type) {
		List<AccountInfo> res = new ArrayList<AccountInfo>();
		Connection connection = null;
		PreparedStatement pStatement = null;
		int lowerbound = activePage * pageSize + 1;
		int upperbound = lowerbound + pageSize;

		try {
			connection = cPoolUtil.getConnection();
			switch (type) {
			case NOT_STATE_EMP:
				pStatement = connection.prepareStatement(
						"select * from (select Id_Client Id, Name Name, rownum rnum from client t Where t.Branch =? and t.state = 2 and t.CODE_SUBJECT='J' and (Id_Client like '%"
								+ filter + "%' or Name like '%" + filter + "%')) where rnum>=? and rnum<=? ");
				pStatement.setString(1, branch);
				pStatement.setInt(2, lowerbound);
				pStatement.setInt(3, upperbound);

				break;
			case STATE_EMP:
				pStatement = connection.prepareStatement(
						"select * from (select t.account Id, t.inn||'   '||t.name Name, rownum rnum from s_budspr  t where t.act='A' and (account like '%"
								+ filter + "%' or Name like '%" + filter + "%')) where rnum>=? and rnum<=? ");
				pStatement.setInt(1, lowerbound);
				pStatement.setInt(2, upperbound);
				break;
			default:
				return res;
			}

			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				res.add(new AccountInfo(rSet.getString("Id"), rSet.getString("Name")));
			}
			rSet.close();
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(connection);
		}
		return res;
	}

	public static Res btrt_01_02_05_06(BTRT btrt, String btrtTag, Credential cr) {
		Res res = null;
		Connection c = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			// c = ConnectionPool.getConnection();
			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			// getp = c.prepareCall("{? = call Param.GetParam(?) }");
			// getp.registerOutParameter(1, java.sql.Types.VARCHAR);

			ccs.execute();
			/*
			 * ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			 * ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
			 */
			if (!CheckNull.isEmpty(btrt.getCompany_name())) {
				cs.setString(1, "P_CH_COMPANY_NAME");
				cs.setString(2, btrt.getCompany_name());
				cs.execute();
			}

			if (!CheckNull.isEmpty(btrt.getCompany_name_card())) {
				cs.setString(1, "P_COMPANY_NAME");
				cs.setString(2, btrt.getCompany_name_card());
				cs.execute();
			}

			if (!isNullOrEmpty(btrt.getAddress_id())) {
				cs.setString(1, "P_ADDRESS_ID");
				cs.setString(2, btrt.getAddress_id());
				cs.execute();
			}

			if (!isNullOrEmpty(btrt.getMobile_phone())) {
				cs.setString(1, "P_MOBILE_PHONE");
				cs.setString(2, btrt.getMobile_phone());
				cs.execute();
			}

			// if (!isNullOrEmpty(btrt.getEmail())) {
			cs.setString(1, "P_EMAIL");
			cs.setString(2, btrt.getEmail());
			cs.execute();
			// }

			cs.setString(1, "P_BRANCH");
			cs.setString(2, cr.getBranch());
			cs.execute();

			cs.setString(1, "P_APP_TYPE");
			cs.setString(2, btrtTag);
			cs.execute();

			cs.setString(1, "P_EMP_ID");
			cs.setString(2, getEmpId());
			cs.execute();

			cs.setString(1, "P_PERSON_ID");
			cs.setString(2, btrt.getPerson_id());
			cs.execute();

			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, btrt.getFirst_name());
			cs.execute();

			cs.setString(1, "P_SECOND_NAME");
			cs.setString(2, btrt.getPatronymic());
			cs.execute();

			cs.setString(1, "P_SURNAME");
			cs.setString(2, btrt.getSecond_name());
			cs.execute();

			cs.setString(1, "P_BIRTH_DATE");
			cs.setDate(2, new java.sql.Date(btrt.getBirth_date().getTime()));
			cs.execute();

			cs.setString(1, "P_P_PROC_MODE");
			cs.setInt(2, btrt.getP_proc_mode());
			cs.execute();

			cs.setString(1, "P_SECURITY_ID");
			cs.setString(2, btrt.getSecurity_id());
			cs.execute();

			cs.setString(1, "P_SEX");
			cs.setString(2, btrt.getSex());
			cs.execute();

			cs.setString(1, "P_RESIDENCE");
			cs.setString(2, btrt.getResidence());
			cs.execute();

			cs.setString(1, "P_P_ID_TYPE");
			cs.setString(2, btrt.getP_id_type());
			cs.execute();

			cs.setString(1, "P_P_ID_NUMBER");
			cs.setString(2, btrt.getP_id_number());
			cs.execute();

			cs.setString(1, "P_P_ID_SERIES");
			cs.setString(2, btrt.getP_id_series());
			cs.execute();

			cs.setString(1, "P_P_ID_AUTHORITY");
			cs.setString(2, btrt.getP_id_authority());
			cs.execute();

			cs.setString(1, "P_P_ID_ISSUE_DATE");
			cs.setDate(2, new java.sql.Date(btrt.getP_id_issue_date().getTime()));
			cs.execute();

			cs.setString(1, "P_ADDRESS_TYPE");
			cs.setString(2, btrt.getAddress_type());
			cs.execute();

			cs.setString(1, "P_A_PROC_MODE");
			cs.setInt(2, 3);
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE1");
			cs.setString(2, btrt.getAddress_line1());
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE2");
			cs.setString(2, btrt.getAddress_line2());
			cs.execute();

			cs.setString(1, "P_REGION");
			cs.setString(2, btrt.getRegion());
			cs.execute();

			cs.setString(1, "P_COUNTRY");
			cs.setString(2, btrt.getCountry());
			cs.execute();

			cs.setString(1, "P_PRIMARY_PHONE");
			cs.setString(2, btrt.getPrimary_phone());
			cs.execute();

			cs.setString(1, "P_CARD_NUMBER");
			cs.setString(2, btrt.getCard_number());
			cs.execute();

			cs.setString(1, "P_CARD_TYPE");
			cs.setString(2, btrt.getCard_type());
			cs.execute();

			cs.setString(1, "P_EMBOSSED_CH_NAME");
			cs.setString(2, btrt.getEmbossed_ch_name());
			cs.execute();

			cs.setString(1, "P_IS_PRIMARY");
			cs.setInt(2, btrt.getIs_primary());
			cs.execute();

			cs.setString(1, "P_EXPIRATION_DATE");
			cs.setString(2, "");
			cs.execute();

			cs.setString(1, "P_ACCOUNT_NUMBER");
			cs.setString(2, btrt.getAccount_number());
			cs.execute();

			cs.setString(1, "P_ACCOUNT_TYPE");
			cs.setString(2, btrt.getAccount_type());
			cs.execute();

			cs.setString(1, "P_CURRENCY");
			cs.setString(2, btrt.getCurrency());
			cs.execute();
			// //////////////////////////////////////////////////////////////
			if (btrtTag.equals(Constants.BTRT01)) {
				cs.setString(1, "P_REC_NUMBER");
				cs.setDouble(2, btrt.getRec_number());
				cs.execute();

				cs.setString(1, "P_CONTRACT_ID");
				cs.setString(2, btrt.getContract_id());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_ID");
				cs.setString(2, cr.getBranch().substring(1) + btrt.getCustomer_id());
				cs.execute();

				cs.setString(1, "P_VIP_FLAG");
				cs.setString(2, btrt.getVip_flag());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_DESC");
				cs.setString(2, btrt.getCustomer_desc());
				cs.execute();

				cs.setString(1, "P_INN");
				cs.setString(2, btrt.getInn());
				cs.execute();

				cs.setString(1, "P_OKPO");
				cs.setString(2, btrt.getOkpo());
				cs.execute();

				/*
				 * if(!btrt.getStEmpId().trim().isEmpty()){ PreparedStatement
				 * pStatement = c.prepareStatement(
				 * "Delete CARD_CLIENT_BUDGET where branch=? and budget_account=? and budget_inn=? and Employee_id=?"
				 * ); pStatement.setString(1, branch); pStatement.setString(2,
				 * btrt.getStEmpId()); pStatement.setString(3, ); }
				 */
				acs.setInt(1, 121);
				acs.setInt(2, 1);
				acs.setInt(3, 1);
				acs.execute();

				acs.setInt(1, 121);
				acs.setInt(2, 1);
				acs.setInt(3, 2);
				acs.execute();

				/*
				 * acs.setInt(3, 2); acs.execute();
				 */
			} else if (btrtTag.equals(Constants.BTRT02)) {
				cs.setString(1, "P_REC_NUMBER");
				cs.setDouble(2, btrt.getRec_number());
				cs.execute();

				cs.setString(1, "P_CONTRACT_ID");
				cs.setString(2, btrt.getContract_id());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_ID");
				cs.setString(2, btrt.getCustomer_id());
				cs.execute();

				cs.setString(1, "P_DEF_ATM_ACCOUNT");
				cs.setString(2, btrt.getDef_atm_account());
				cs.execute();

				cs.setString(1, "P_DEF_POS_ACCOUNT");
				cs.setString(2, btrt.getDef_pos_account());
				cs.execute();

				acs.setInt(1, 121);
				acs.setInt(2, 2);
				acs.setInt(3, 1);
				acs.execute();
				acs.setInt(1, 121);
				acs.setInt(2, 2);
				acs.setInt(3, 2);
				acs.execute();
				/*
				 * acs.setInt(3, 2); acs.execute();
				 */

			} else if (btrtTag.equals(Constants.BTRT05)) {
				cs.setString(1, "P_REC_NUMBER");
				cs.setDouble(2, btrt.getRec_number());
				cs.execute();

				cs.setString(1, "P_CONTRACT_ID");
				cs.setString(2, btrt.getContract_id());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_ID");
				cs.setString(2, cr.getBranch().substring(1) + btrt.getCustomer_id());
				cs.execute();

				cs.setString(1, "P_VIP_FLAG");
				cs.setString(2, btrt.getVip_flag());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_DESC");
				cs.setString(2, btrt.getCustomer_desc());
				cs.execute();

				cs.setString(1, "P_INN");
				cs.setString(2, btrt.getInn());
				cs.execute();

				cs.setString(1, "P_OKPO");
				cs.setString(2, btrt.getOkpo());
				cs.execute();

				acs.setInt(1, 121);
				acs.setInt(2, 8);
				acs.setInt(3, 1);
				acs.execute();
				acs.setInt(1, 121);
				acs.setInt(2, 8);
				acs.setInt(3, 2);
				acs.execute();
				/*
				 * acs.setInt(3, 2); acs.execute();
				 */

			} else if (btrtTag.equals(Constants.BTRT06)) {
				cs.setString(1, "P_REC_NUMBER");
				cs.setDouble(2, btrt.getRec_number());
				cs.execute();

				cs.setString(1, "P_CONTRACT_ID");
				cs.setString(2, btrt.getContract_id());
				cs.execute();

				cs.setString(1, "P_CUSTOMER_ID");
				cs.setString(2, cr.getBranch().substring(1) + btrt.getCustomer_id());
				cs.execute();

				cs.setString(1, "P_IS_PRIMARY");
				cs.setInt(2, btrt.getIs_primary());
				cs.execute();

				acs.setInt(1, 121);
				acs.setInt(2, 9);
				acs.setInt(3, 1);
				acs.execute();
				acs.setInt(1, 121);
				acs.setInt(2, 9);
				acs.setInt(3, 2);
				acs.execute();
				/*
				 * acs.setInt(3, 2); acs.execute();
				 */
			}

			c.commit();

			cs.close();
			ccs.close();
			acs.close();
			inf.close();
			res = new Res(0, "Заявление успешно создано");

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
		} finally {
			cPoolUtil.close(c);
		}

		return res;
	}

	public static Res btrt_03(BTRT btrt03, String contract_id, Credential cr) {
		Connection c = null;
		Res res = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			// getp = c.prepareCall("{? = call Param.GetParam(?) }");
			// getp.registerOutParameter(1, java.sql.Types.VARCHAR);

			ccs.execute();
			/*
			 * ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			 * ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
			 */
			cs.setString(1, "P_BRANCH");
			cs.setString(2, btrt03.getBranch());
			cs.execute();

			cs.setString(1, "P_APP_TYPE");
			cs.setString(2, "BTRT03");
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_CONTRACT_ID");
			cs.setString(2, contract_id);
			cs.execute();

			cs.setString(1, "P_EMP_ID");
			cs.setString(2, getEmpId());
			cs.execute();

			cs.setString(1, "P_CUSTOMER_ID");
			cs.setString(2, btrt03.getCustomer_id());
			cs.execute();

			cs.setString(1, "P_PERSON_ID");
			cs.setString(2, btrt03.getPerson_id());
			cs.execute();
			// /////////////////////////////////////////////////
			cs.setString(1, "P_EMBOSSED_CH_NAME");
			cs.setString(2, btrt03.getEmbossed_ch_name());
			cs.execute();

			cs.setString(1, "P_DEF_ATM_ACCOUNT");
			cs.setString(2, btrt03.getAcc());
			cs.execute();

			cs.setString(1, "P_DEF_POS_ACCOUNT");
			cs.setString(2, btrt03.getAcc());
			cs.execute();

			cs.setString(1, "P_ADDRESS_ID");
			cs.setString(2, btrt03.getAddress_id());
			cs.execute();

			cs.setString(1, "P_ACCOUNT_NUMBER");
			cs.setString(2, btrt03.getAcc());
			cs.execute();

			cs.setString(1, "P_ACCOUNT_TYPE");
			cs.setString(2, "ACCTD");
			cs.execute();

			cs.setString(1, "P_CURRENCY");
			cs.setString(2, "860");
			cs.execute();
			// ///////////////////////
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, btrt03.getFirst_name());
			cs.execute();

			cs.setString(1, "P_SECOND_NAME");
			cs.setString(2, btrt03.getSecond_name());
			cs.execute();

			cs.setString(1, "P_SURNAME");
			cs.setString(2, btrt03.getPatronymic());
			cs.execute();

			cs.setString(1, "P_BIRTH_DATE");
			cs.setDate(2, new java.sql.Date(btrt03.getBirth_date().getTime()));
			cs.execute();

			cs.setString(1, "P_P_PROC_MODE");
			cs.setString(2, "3");
			cs.execute();

			cs.setString(1, "P_CH_COMPANY_NAME");
			cs.setString(2, "");
			cs.execute();

			cs.setString(1, "P_SECURITY_ID");
			cs.setString(2, "11");
			cs.execute();

			cs.setString(1, "P_SEX");
			cs.setString(2, "SEXT2");
			cs.execute();

			cs.setString(1, "P_RESIDENCE");
			cs.setString(2, "RSDN1");
			cs.execute();

			cs.setString(1, "P_P_ID_TYPE");
			cs.setString(2, btrt03.getP_id_type());
			cs.execute();

			cs.setString(1, "P_P_ID_NUMBER");
			cs.setString(2, btrt03.getP_id_number());
			cs.execute();

			cs.setString(1, "P_P_ID_SERIES");
			cs.setString(2, btrt03.getP_id_series());
			cs.execute();

			cs.setString(1, "P_P_ID_AUTHORITY");
			cs.setString(2, "ХАмза");
			cs.execute();

			cs.setString(1, "P_P_ID_ISSUE_DATE");
			cs.setString(2, "01.01.2012");
			cs.execute();

			cs.setString(1, "P_ADDRESS_ID");
			cs.setString(2, btrt03.getAddress_id());
			cs.execute();

			cs.setString(1, "P_ADDRESS_TYPE");
			cs.setString(2, btrt03.getAddress_type());
			cs.execute();

			cs.setString(1, "P_A_PROC_MODE");
			cs.setString(2, "1");
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE1");
			cs.setString(2, btrt03.getAddress_line1());
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE2");
			cs.setString(2, btrt03.getAddress_line2());
			cs.execute();

			cs.setString(1, "P_REGION");
			cs.setString(2, btrt03.getRegion());
			cs.execute();

			cs.setString(1, "P_COUNTRY");
			cs.setString(2, btrt03.getCountry());
			cs.execute();

			cs.setString(1, "P_PRIMARY_PHONE");
			cs.setString(2, btrt03.getPrimary_phone());
			cs.execute();

			cs.setString(1, "P_MOBILE_PHONE");
			cs.setString(2, btrt03.getMobile_phone());
			cs.execute();

			cs.setString(1, "P_EMAIL");
			cs.setString(2, btrt03.getEmail());
			cs.execute();

			cs.setString(1, "P_CARD_TYPE");
			cs.setString(2, "1A");
			cs.execute();

			cs.setString(1, "P_IS_PRIMARY");
			cs.setString(2, "0");
			cs.execute();

			acs.setInt(1, 121);
			acs.setInt(2, 3);
			acs.setInt(3, 1);
			acs.execute();
			
			acs.setInt(1, 121);
			acs.setInt(2, 3);
			acs.setInt(3, 2);
			acs.execute();
			/*
			 * acs.setInt(3, 2); acs.execute();
			 */

			c.commit();

			cs.close();
			ccs.close();
			acs.close();
			inf.close();

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}

		return res;
	}

	public static Res btrt_20(BTRT btrt20, String reissue_command, Credential cr) {
		Connection c = null;
		Res res = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");

			ccs.execute();

			cs.setString(1, "P_BRANCH");
			cs.setString(2, cr.getBranch());
			cs.execute();

			cs.setString(1, "P_APP_TYPE");
			cs.setString(2, Constants.BTRT20);
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_CONTRACT_ID");
			cs.setString(2, btrt20.getContract_id());
			cs.execute();

			cs.setString(1, "P_CUSTOMER_ID");
			cs.setString(2, btrt20.getCustomer_id());
			cs.execute();

			cs.setString(1, "P_PERSON_ID");
			cs.setString(2, btrt20.getPerson_id());
			cs.execute();

			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, btrt20.getFirst_name());
			cs.execute();

			cs.setString(1, "P_SURNAME");
			cs.setString(2, btrt20.getSecond_name());
			cs.execute();

			cs.setString(1, "P_BIRTH_DATE");
			cs.setDate(2, new java.sql.Date(btrt20.getBirth_date().getTime()));
			cs.execute();

			cs.setString(1, "P_CARD_NUMBER");
			cs.setString(2, btrt20.getCard_number());
			cs.execute();

			cs.setString(1, "P_CARD_TYPE");
			cs.setString(2, "1A");
			cs.execute();

			cs.setString(1, "P_P_ID_TYPE");
			cs.setString(2, btrt20.getP_id_type());
			cs.execute();

			cs.setString(1, "P_P_ID_SERIES");
			cs.setString(2, btrt20.getP_id_series());
			cs.execute();

			cs.setString(1, "P_P_ID_NUMBER");
			cs.setString(2, btrt20.getP_id_number());
			cs.execute();

			cs.setString(1, "P_EMP_ID");
			cs.setString(2, btrt20.getEmp_id());
			cs.execute();

			cs.setString(1, "P_EMBOSSED_CH_NAME");
			cs.setString(2, btrt20.getEmbossed_ch_name());
			cs.execute();

			cs.setString(1, "P_P_PROC_MODE");
			cs.setInt(2, btrt20.getP_proc_mode());
			cs.execute();

			cs.setString(1, "P_VIP_FLAG");
			cs.setString(2, "CVIP0");
			cs.execute();

			StringBuilder sBuilder = new StringBuilder(btrt20.getFirst_name());
			sBuilder.append(" ");
			sBuilder.append(btrt20.getSecond_name());
			sBuilder.append(" ");
			sBuilder.append(btrt20.getPatronymic());
			cs.setString(1, "P_CUSTOMER_DESC");
			cs.setString(2, sBuilder.toString());
			cs.execute();

			cs.setString(1, "P_INN");
			cs.setString(2, "777777777");
			cs.execute();

			cs.setString(1, "P_OKPO");
			cs.setString(2, "0000");
			cs.execute();

			cs.setString(1, "P_INN");
			cs.setString(2, "777777777");
			cs.execute();

			cs.setString(1, "P_REISSUE_COMMAND");
			cs.setString(2, reissue_command);
			cs.execute();

			if (reissue_command.equals("CRNW30")) {
				cs.setString(1, "P_HOTCARDSTATUS");
				cs.setString(2, "CHST14");
				cs.execute();
			}

			acs.setInt(1, 121);
			acs.setInt(2, 5);
			acs.setInt(3, 1);
			acs.execute();
			acs.setInt(1, 121);
			acs.setInt(2, 5);
			acs.setInt(3, 2);
			acs.execute();
			/*
			 * acs.setInt(3, 2); acs.execute();
			 */

			c.commit();

			cs.close();
			acs.close();
			ccs.close();
			inf.close();

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}

		return res;
	}

	public static Res btrt_25(BTRT btrt25, String summ, Credential cr) {
		Connection c = null;
		Res res = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");

			ccs.execute();

			cs.setString(1, "P_BRANCH");
			cs.setString(2, btrt25.getBranch());
			cs.execute();

			cs.setString(1, "P_APP_TYPE");
			cs.setString(2, "BTRT25");
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_CARD_TYPE");
			cs.setString(2, "1A");
			cs.execute();

			cs.setString(1, "P_ACCOUNT_TYPE");
			cs.setString(2, btrt25.getAccount_type());
			cs.execute();

			cs.setString(1, "P_APP_TAG");
			cs.setString(2, "FFFF0C");
			cs.execute();

			cs.setString(1, "P_P_PROC_MODE");
			cs.setString(2, "3");
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_ACCOUNT_NUMBER");
			cs.setString(2, btrt25.getAccount_number());
			cs.execute();

			cs.setString(1, "P_CARD_NUMBER");
			cs.setString(2, btrt25.getCard_number());
			cs.execute();

			cs.setString(1, "P_CONTRACT_ID");
			cs.setString(2, btrt25.getContract_id());
			cs.execute();

			cs.setString(1, "P_CURRENCY");
			cs.setString(2, btrt25.getCurrency());
			cs.execute();

			cs.setString(1, "P_CUSTOMER_ID");
			cs.setString(2, btrt25.getCustomer_id());
			cs.execute();

			cs.setString(1, "P_EMP_ID");
			cs.setString(2, btrt25.getEmp_id());
			cs.execute();

			cs.setString(1, "P_LIMIT");
			cs.setString(2, summ);
			cs.execute();

			cs.setString(1, "P_PERSON_ID");
			cs.setString(2, btrt25.getPerson_id());
			cs.execute();

			cs.setString(1, "P_SURNAME");
			cs.setString(2, btrt25.getSecond_name());
			cs.execute();

			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, btrt25.getFirst_name());
			cs.execute();

			cs.setString(1, "P_SECOND_NAME");
			cs.setString(2, btrt25.getPatronymic());
			cs.execute();

			acs.setInt(1, 121);
			acs.setInt(2, 22);
			acs.setInt(3, 1);
			acs.execute();
			acs.setInt(1, 121);
			acs.setInt(2, 22);
			acs.setInt(3, 2);
			acs.execute();

			/*
			 * acs.setInt(3, 2); acs.execute();
			 */
			c.commit();

			cs.close();
			acs.close();
			ccs.close();
			inf.close();

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}

		return res;
	}

	public static Res btrt30(BTRT30 btrt30, Credential cr) {
		Connection c = null;
		Res res = null;
		CallableStatement inf = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			c = cPoolUtil.getConnectionBySchema(cr.getUn(), cr.getPwd(), cr.getAlias());

			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			System.out.println("Connection => " + c);

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");

			ccs.execute();

			cs.setString(1, "P_BRANCH");
			cs.setString(2, cr.getBranch());
			cs.execute();

			cs.setString(1, "P_APP_TYPE");
			cs.setString(2, "BTRT30");
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_CUSTOMER_ID");
			cs.setString(2, btrt30.getCustomer_id());
			cs.execute();

			cs.setString(1, "P_CONTRACT_ID");
			cs.setString(2, "O");
			cs.execute();

			cs.setString(1, "P_CUSTOMER_DESC");
			cs.setString(2, btrt30.getCustomer_desc());
			cs.execute();

			cs.setString(1, "P_PERSON_ID");
			cs.setString(2, btrt30.getPerson_id());
			cs.execute();

			cs.setString(1, "P_SEX");
			cs.setString(2, btrt30.getSex());
			cs.execute();

			cs.setString(1, "P_RESIDENCE");
			cs.setString(2, btrt30.getResidence());
			cs.execute();

			cs.setString(1, "P_SECURITY_ID");
			cs.setString(2, btrt30.getSecurity_id());
			cs.execute();

			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, btrt30.getFirst_name());
			cs.execute();

			cs.setString(1, "P_SECOND_NAME");
			cs.setString(2, btrt30.getPatronymic());
			cs.execute();

			cs.setString(1, "P_SURNAME");
			cs.setString(2, btrt30.getSecond_name());
			cs.execute();

			cs.setString(1, "P_BIRTH_DATE");
			cs.setDate(2, new java.sql.Date(btrt30.getBirth_date().getTime()));
			cs.execute();

			cs.setString(1, "P_ADDRESS_ID");
			cs.setString(2, btrt30.getAddress_id());
			cs.execute();

			cs.setString(1, "P_ADDRESS_TYPE");
			cs.setString(2, btrt30.getAddress_type());
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE1");
			cs.setString(2, btrt30.getAddress_line1());
			cs.execute();

			cs.setString(1, "P_ADDRESS_LINE2");
			cs.setString(2, btrt30.getAddress_line2());
			cs.execute();

			cs.setString(1, "P_REGION");
			cs.setString(2, btrt30.getRegion());
			cs.execute();

			cs.setString(1, "P_COUNTRY");
			cs.setString(2, btrt30.getCountry());
			cs.execute();

			cs.setString(1, "P_PRIMARY_PHONE");
			cs.setString(2, btrt30.getPrimary_phone());
			cs.execute();

			cs.setString(1, "P_MOBILE_PHONE");
			cs.setString(2, btrt30.getMobile_phone());
			cs.execute();

			cs.setString(1, "P_EMAIL");
			cs.setString(2, btrt30.getEmail());
			cs.execute();

			cs.setString(1, "P_NEW_SURNAME");
			cs.setString(2, btrt30.getNewSecondName());
			cs.execute();

			cs.setString(1, "P_NEW_FIRST_NAME");
			cs.setString(2, btrt30.getNewFirstName());
			cs.execute();

			cs.setString(1, "P_NEW_SECOND_NAME");
			cs.setString(2, btrt30.getNewPatronymic());
			cs.execute();

			cs.setString(1, "P_NEW_BIRTH_DATE");
			cs.setDate(2, new java.sql.Date(btrt30.getNewDateOfBirth().getTime()));
			cs.execute();

			cs.setString(1, "P_A_PROC_MODE");
			cs.setInt(2, btrt30.getA_proc_mode());
			cs.execute();

			StringBuilder sBuilder = new StringBuilder(btrt30.getNewFirstName());
			sBuilder.append(" ");
			sBuilder.append(btrt30.getNewSecondName());
			sBuilder.append(" ");
			sBuilder.append(btrt30.getNewPatronymic());
			cs.setString(1, "P_NEW_CUSTOMER_NAME");
			cs.setString(2, sBuilder.toString());
			cs.execute();

			cs.setString(1, "P_NEW_P_ID_TYPE");
			cs.setString(2, btrt30.getNewP_ID_type());
			cs.execute();

			cs.setString(1, "P_NEW_P_ID_NUMBER");
			cs.setString(2, btrt30.getNewP_ID_number());
			cs.execute();

			cs.setString(1, "P_NEW_P_ID_SERIES");
			cs.setString(2, btrt30.getNewP_ID_series());
			cs.execute();

			cs.setString(1, "P_P_ID_AUTHORITY");
			cs.setString(2, btrt30.getNewP_ID_authority());
			cs.execute();

			cs.setString(1, "P_P_ID_ISSUE_DATE");
			cs.setDate(2, new java.sql.Date(btrt30.getP_id_issue_date().getTime()));
			cs.execute();

			cs.setString(1, "P_NEW_ADDRESS_LINE1");
			cs.setString(2, btrt30.getNewAddressLine1());
			cs.execute();

			cs.setString(1, "P_REC_NUMBER");
			cs.setString(2, "2");
			cs.execute();

			cs.setString(1, "P_VIP_FLAG");
			cs.setString(2, "CVIP0");
			cs.execute();

			sBuilder = new StringBuilder(btrt30.getFirst_name());
			sBuilder.append(" ");
			sBuilder.append(btrt30.getSecond_name());
			sBuilder.append(" ");
			sBuilder.append(btrt30.getPatronymic());
			cs.setString(1, "P_CUSTOMER_DESC");
			cs.setString(2, sBuilder.toString());
			cs.execute();

			cs.setString(1, "P_INN");
			cs.setString(2, "777777777");
			cs.execute();

			cs.setString(1, "P_OKPO");
			cs.setString(2, "0000");
			cs.execute();

			acs.setInt(1, 121);
			acs.setInt(2, 6);
			acs.setInt(3, 1);
			acs.execute();
			acs.setInt(1, 121);
			acs.setInt(2, 6);
			acs.setInt(3, 2);
			acs.execute();
			/*
			 * acs.setInt(3, 2); acs.execute();
			 */

			c.commit();

			cs.close();
			ccs.close();
			acs.close();
			inf.close();

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}

		return res;
	}

	private static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	private static String getEmpId() {
		String res = "";
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = cPoolUtil.getConnection();
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			PreparedStatement s = c.prepareStatement("select info.GETEMPID from dual");
			ResultSet rs = s.executeQuery();
			rs.next();
			res = rs.getString("GETEMPID");
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	private static String getEmpIdBTRT25() {
		String res = "";
		Connection c = null;
		try {
			c = cPoolUtil.getConnection();
			PreparedStatement s = c.prepareStatement("select value from card_sets where upper(id)=upper('OfficerId') ");
			ResultSet rs = s.executeQuery();
			rs.next();
			res = rs.getString("value");
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}
		return res;
	}

	public static List<String> getTableBTRTList(String btrtNum) {
		List<String> list = new ArrayList<String>();
		Connection c = null;

		try {
			c = cPoolUtil.getConnection();
			PreparedStatement s = c.prepareStatement(
					"select distinct db_table from card_tags_table_map where app_id IN (select app_id from card_tags_table_map where tag_name Like '%"
							+ btrtNum + "%')");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String str = rs.getString("DB_TABLE");
				if (str != null && str.length() > 0)
					list.add(str.toLowerCase());
			}
			rs.close();
			s.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cPoolUtil.close(c);
		}

		return list;
	}

}