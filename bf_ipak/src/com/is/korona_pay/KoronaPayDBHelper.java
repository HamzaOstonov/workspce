package com.is.korona_pay;

import com.is.dper_info.*;
import com.is.dper_info.model.Oper_info;
import com.is.dper_info.model.dper_info;
import com.is.dper_info.service.SprService;
import com.is.dper_info.service.SumsService;
import com.is.dper_info.service.TransactionsService;
import com.is.dper_info.service.dper_infoService;
import com.is.korona_pay.TransactionService;

import org.zkoss.util.media.ContentTypes;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.bpri.utils.Utils;
import com.is.korona_pay.KoronaPayViewCtrl;
import com.is.korona_pay.KoronaPayService;
import com.is.korona_pay.Transliterator;
import com.is.korona_pay.model.Account;
import com.is.korona_pay.service.AccountService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.sun.corba.se.spi.ior.iiop.AlternateIIOPAddressComponent;

@SuppressWarnings("serial")

public class KoronaPayDBHelper {

	private static final long serialVersionUID = 1L;
	private static final boolean isDebugEnabled = true;
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static String abcclientid = null;
	private static String distrCode;
	static String g;
	dper_info dp = new dper_info();
	

	public static FindClientResponse findClient(String passportNumber, String passportSeries, String fullName, String alias) {

		FindClientResponse response = new FindClientResponse();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("alias: "+ alias);
		/*
		 * String lsName = fullName; if (fullName.contains(" ")) { lsName =
		 * fullName.substring(0, fullName.indexOf(" ")); }
		 */

		try {

			c = ConnectionPool.getConnection();
			
			
			ps = c.prepareStatement(
					"select c.*, to_char(c.birthday, 'DD.MM.YYYY') as birth, to_char(c.passport_date_registration, 'DD.MM.YYYY') \r\n" + 
					"as pass_date_registration, to_char(c.passport_date_expiration, 'DD.MM.YYYY') as pass_date_expiration \r\n" + 
					"from client_p c where c.branch = ? and c.passport_number= ? and c.passport_serial= ? and c.state= 2");
			ps.setString(1, alias.replace("BANK", "00"));  //BANK394 = 00394
			//ps.setString(1, "00583");  //BANK394 = 00394
			ps.setString(2, passportNumber);   
			ps.setString(3, passportSeries.toUpperCase());//passport_serial
			//ps.setString(3, fullName);
	
			rs = ps.executeQuery();
			if (rs.next()) {

				response.setAction("Ans_FindClient");
				// String firstName = Transliterator.lat2cyr(rs.getString("FIRST_NAME"));
				// response.setFirstName(Transliterator.lat2cyr(rs.getString("FIRST_NAME")));
				response.setFirstName(rs.getString("FIRST_NAME"));
				// System.out.println("firstName === " + firstName);
				// String family = Transliterator.lat2cyr(rs.getString("FAMILY"));
				// response.setLastName(Transliterator.lat2cyr(rs.getString("FAMILY")));
				response.setLastName(rs.getString("FAMILY"));
				// System.out.println("family === " + family);
				// String patronymic = Transliterator.lat2cyr(rs.getString("PATRONYMIC"));
				// response.setMiddleName(Transliterator.lat2cyr(rs.getString("PATRONYMIC")));
				response.setMiddleName(rs.getString("PATRONYMIC"));
				// System.out.println("patronymic === " + patronymic);

				response.setLatinFirstName(rs.getString("FIRST_NAME"));
				response.setLatinLastName(rs.getString("FAMILY"));
				response.setLatinMiddleName(rs.getString("PATRONYMIC"));

				if(rs.getString("TYPE_DOCUMENT").equals("0")) {
					response.setDocType("IDCARDUZB");
				}
				response.setDocType("PASSPORTUZB");
				response.setDocNumber(rs.getString("PASSPORT_NUMBER"));
				response.setDocSeries(rs.getString("PASSPORT_SERIAL"));
				String code = rs.getString("CODE_CITIZENSHIP");
				String gg = citizenship(code, c);
				response.setDocIssueCountryIso(gg);
				response.setDocIssuer(rs.getString("PASSPORT_PLACE_REGISTRATION"));
				response.setDocIssuerCode("");
				response.setDocIssueDate(rs.getString("PASS_DATE_REGISTRATION"));
				response.setDocExpireDate(rs.getString("PASS_DATE_EXPIRATION"));

				
				
				response.setCitizenship(gg);

				response.setIsResident("");
				response.setBirthCountryIso(gg);
				response.setBirthCity(rs.getString("BIRTH_PLACE"));
				response.setBirthDate(rs.getString("BIRTH"));
				response.setiNN(rs.getString("NUMBER_TAX_REGISTRATION"));
				response.setRegCountryIso(gg);
				response.setRegRegion(region(rs.getString("CODE_ADR_DISTR"), c));
				response.setRegCity(rs.getString("CODE_PLACE_REGIST"));
				response.setRegAddress(rs.getString("POST_ADDRESS"));
				response.setPostCode("");
				response.setPhone(rs.getString("PHONE_MOBILE"));
				response.setMigrationCardSeries("");
				response.setMigrationCardNumber("");
				response.setMigrationCardIssueDate("");
				response.setMigrationCardExpireDate("");
				response.setiPDLType("");
				response.setAbsClientId(rs.getString("ID"));
				response.setRetCode(0);
				response.setRetMsg(rs.getString("KOD_ERR"));
				response.setRetExtMsg("");

			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			response.setAction("Ans_FindClient");
			response.setRetCode(1);
			response.setRetMsg("Ошибка в БД");
			response.setRetExtMsg(e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			ConnectionPool.close(c);
		}
		return response;
	}

	public static String citizenship(String citizenship, Connection c) {

		String uzb = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select s.alpha_3 from s_str s where s.code_str = ?");
			ps.setString(1, citizenship);
			rs = ps.executeQuery();
			if (rs.next()) {
				uzb = rs.getString("alpha_3");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return uzb;
	}

	public static HandleOperationResponse insertHandleOperation(HandleOperationRequest request, String un, String pwd, String alias) {
		HandleOperationResponse response = new HandleOperationResponse();

        	ISLogger.getLogger().error("request.getAbsClientId()  === " + request.getAbsClientId());
    		
    		Connection c = null;
    		PreparedStatement ps = null;
    		
    		
    		ISLogger.getLogger().error("alias  === " + alias + " un: " + un + " pwd: " + pwd);
    		
    		
    		try {
    			c = ConnectionPool.getConnection(un, pwd, alias);
    			

    			ps = c.prepareStatement("insert into korona_pay_transfers "
    					+ "(ABSCLIENTID, ACTION, COMMISSIONAMOUNT, COMMISSIONCUR, COMMISSIONEXP, FROMCOUNTRY, OPERATION, OPERATIONDATE, PAYAMOUNT, PAYCUR, "
    					+ "PAYEXP, RECEIVERBIRTHCITY, RECEIVERBIRTHDATE, RECEIVERCITIZENSHIP, RECEIVERCOMMISSIONAMOUNT, RECEIVERCOMMISSIONCUR, RECEIVERCOMMISSIONEXP, RECEIVERDOCISSUEDATE, RECEIVERDOCISSUER, RECEIVERDOCNUMBER, "
    					+ "RECEIVERDOCSERIES, RECEIVERDOCTYPE, RECEIVERFULLNAME, RECEIVERORIGFULLNAME, RECEIVERPHONE, RECEIVERREGADDRESS, RECEIVERREGCITY, RECEIVERREGCOUNTRYISO, SENDERBIRTHCITY, SENDERBIRTHDATE, SENDERREGCOUNTRYISO, "
    					+ "SENDERCITIZENSHIP, SENDERDOCISSUEDATE, SENDERDOCISSUER, SENDERDOCISSUERCODE, SENDERDOCNUMBER, SENDERDOCSERIES, SENDERDOCTYPE, SENDERFULLNAME, SENDERPHONE, SENDERREGADDRESS, SENDERREGCITY, "
    					+ "TOCITYID, TOCOUNTRY, TRANSFERTYPE, UIN, STATE, BRANCH, SENDERCOMMISSIONAMOUNT) "
    					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

    			ps.setString(1, request.getAbsClientId());
    			ps.setString(2, request.getAction());
    			ps.setInt(3, request.getCommissionAmount());
    			ps.setString(4, request.getCommissionCur());
    			ps.setInt(5, request.getCommissionExp());
    			ps.setString(6, request.getFromCountryIso());
    			ps.setInt(7, request.getOperation());
    			ps.setString(8, request.getOperationDate());
    			ps.setInt(9, request.getPayAmount());
    			ps.setString(10, request.getPayCur());

    			ps.setInt(11, request.getPayExp());
    			ps.setString(12, request.getReceiverBirthCity());
    			ps.setString(13, request.getReceiverBirthDate());
    			ps.setString(14, request.getReceiverCitizenship());
    			ps.setInt(15, request.getReceiverCommissionAmount());
    			ps.setString(16, request.getReceiverCommissionCur());
    			ps.setInt(17, request.getReceiverCommissionExp());
    			ps.setString(18, request.getReceiverDocIssueDate());
    			ps.setString(19, request.getReceiverDocIssuer());
    			ps.setString(20, request.getReceiverDocNumber());

    			ps.setString(21, request.getReceiverDocSeries());
    			ps.setString(22, request.getReceiverDocType());
    			ps.setString(23, request.getReceiverFullName());
    			ps.setString(24, request.getReceiverOrigFullName());
    			ps.setString(25, request.getReceiverPhone());
    			ps.setString(26, request.getReceiverRegAddress());
    			ps.setString(27, request.getReceiverRegCity());
    			ps.setString(28, request.getReceiverRegCountryIso());
    			ps.setString(29, request.getSenderBirthCity());
    			ps.setString(30, request.getSenderBirthDate());
    			ps.setString(31, request.getSenderRegCountryIso());

    			ps.setString(32, request.getSenderCitizenship());
    			ps.setString(33, request.getSenderDocIssueDate());
    			ps.setString(34, request.getSenderDocIssuer());
    			ps.setString(35, request.getSenderDocIssuer());
    			ps.setString(36, request.getSenderDocNumber());
    			
    			ps.setString(37, request.getSenderDocSeries());
    			ps.setString(38, request.getSenderDocType());
    			ps.setString(39, request.getSenderFullName());
    			ps.setString(40, request.getSenderPhone());
    			ps.setString(41, request.getSenderRegAddress());
    			ps.setString(42, request.getSenderRegCity());

    			ps.setString(43, request.getToCityId());
    			
    			ps.setString(44, request.getToCountryIso());
    			ps.setString(45, request.getTransferType());
    			ps.setString(46, request.getuIN());
    			ps.setInt(47, 0);
    			ps.setString(48, alias.replace("BANK", "00"));
    			ps.setInt(49, request.getSenderCommissionAmount());

    			ps.executeUpdate();
    			c.commit();
    			
    			response.setAction("Ans_HandleOperation");
    			response.setRetCode(0);
    			response.setRetMsg("Ok");
    			response.setRetExtMsg("");

    		} catch (Exception e) {
    			e.printStackTrace();
    			//LtLogger.getLogger().error("OLEG LбOG LOG: " + com.is.ConnectionPool.getPstr(e));
    			ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
    			response.setAction("Ans_HandleOperation");
    			response.setRetCode(1);
    			response.setRetMsg("Ошибка в БД");
    			
    			response.setRetExtMsg(e.getMessage()+" , message: "+com.is.utils.CheckNull.getPstr(e));
    		} finally {
    			try {
    				ps.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    				//LtLogger.getLogger().error("OLEG LOG LOG: " + com.is.ConnectionPool.getPstr(e));
    			}
    			ConnectionPool.close(c);
    		}
        		
		
		return response;
	}

	public static HandleOperationRequest getHadleOperationByUIN(String uin) {
		HandleOperationRequest handleOperation = new HandleOperationRequest();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			
			ps = c.prepareStatement("select * from korona_pay_transfers where UIN=? and STATE = '0'");
			ps.setString(1, uin);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				handleOperation.setAbsClientId(rs.getString("ABSCLIENTID"));
				handleOperation.setAction(rs.getString("ACTION"));
				handleOperation.setCommissionAmount(rs.getInt("COMMISSIONAMOUNT"));
				handleOperation.setCommissionCur(rs.getString("COMMISSIONCUR"));
				handleOperation.setCommissionExp(rs.getInt("COMMISSIONEXP"));
				handleOperation.setFromCountryIso(rs.getString("FROMCOUNTRYISO"));
				handleOperation.setOperation(rs.getInt("OPERATION"));
				handleOperation.setOperationDate(rs.getString("OPERATIONDATE"));
				handleOperation.setPayAmount(rs.getInt("PAYAMOUNT"));
				handleOperation.setPayCur(rs.getString("PAYCUR"));
				handleOperation.setPayExp(rs.getInt("PAYEXP"));
				handleOperation.setReceiverBirthCity(rs.getString("RECEIVERBIRTHCITY"));
				handleOperation.setReceiverBirthDate(rs.getString("RECEIVERBIRTHDATE"));
				handleOperation.setReceiverCitizenship(rs.getString("RECEIVERCITIZENSHIP"));
				handleOperation.setReceiverCommissionAmount(rs.getInt("RECEIVERCOMMISSIONAMOUNT"));
				handleOperation.setReceiverCommissionCur(rs.getString("RECEIVERCOMMISSIONCUR"));
				handleOperation.setReceiverCommissionExp(rs.getInt("RECEIVERCOMMISSIONEXP"));
				handleOperation.setReceiverDocIssueDate(rs.getString("RECEIVERDOCISSUEDATE"));
				handleOperation.setReceiverDocIssuer(rs.getString("RECEIVERDOCISSUER"));
				handleOperation.setReceiverDocNumber(rs.getString("RECEIVERDOCNUMBER"));
				handleOperation.setReceiverDocSeries(rs.getString("RECEIVERDOCSERIES"));
				handleOperation.setReceiverDocType(rs.getString("RECEIVERDOCTYPE"));
				handleOperation.setReceiverFullName(rs.getString("RECEIVERFULLNAME"));
				handleOperation.setReceiverOrigFullName(rs.getString("RECEIVERORIGFULLNAME"));
				handleOperation.setReceiverPhone(rs.getString("RECEIVERPHONE"));
				handleOperation.setReceiverRegAddress(rs.getString("RECEIVERREGADDRESS"));
				handleOperation.setReceiverRegCity(rs.getString("RECEIVERREGCITY"));
				handleOperation.setReceiverRegCountryIso(rs.getString("RECEIVERREGCOUNTRYISO"));
				handleOperation.setSenderBirthCity(rs.getString("SENDERBIRTHCITY"));
				handleOperation.setSenderBirthDate(rs.getString("SENDERBIRTHDATE"));
				handleOperation.setSenderRegCountryIso(rs.getString("SENDERREGCOUNTRYISO"));
				handleOperation.setSenderCitizenship(rs.getString("SENDERCITIZENSHIP"));
				handleOperation.setSenderDocIssueDate(rs.getString("SENDERDOCISSUEDATE"));
				handleOperation.setSenderDocIssuer(rs.getString("SENDERDOCISSUER"));
				handleOperation.setSenderDocIssuerCode(rs.getString("SENDERDOCISSUERCODE"));
				handleOperation.setSenderDocNumber(rs.getString("SENDERDOCNUMBER"));
				handleOperation.setSenderDocSeries(rs.getString("SENDERDOCSERIES"));
				handleOperation.setSenderDocType(rs.getString("SENDERDOCTYPE"));
				handleOperation.setSenderFullName(rs.getString("SENDERFULLNAME"));
				handleOperation.setSenderPhone(rs.getString("SENDERPHONE"));
				handleOperation.setSenderRegAddress(rs.getString("SENDERREGADDRESS"));
				handleOperation.setSenderRegCity(rs.getString("SENDERREGCITY"));
				handleOperation.setToCityId(rs.getString("TOCITYID"));
				handleOperation.setToCountryIso(rs.getString("TOCOUNTRYISO"));
				handleOperation.setTransferType(rs.getString("TRANSFERTYPE"));
				handleOperation.setuIN(rs.getString("UIN"));
				handleOperation.setAccount(rs.getString("DEPOSIT_ACCOUNT"));
				handleOperation.setName(rs.getString("DEPOSIT_NAME"));
				handleOperation.setsOut(rs.getString("DEPOSIT_S_OUT"));
				handleOperation.setSenderCommissionAmount(rs.getInt("SENDERCOMMISSIONAMOUNT"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return handleOperation;
	}

	

	public static String region(String region, Connection c) {

		String gorod = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = c.prepareStatement("select * from s_distr where distr = ?");
			ps.setString(1, region);
			rs = ps.executeQuery();
			if (rs.next()) {
				gorod = rs.getString("DISTR_NAME");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return gorod;
	}

	public static boolean setStateHadleOperation(String uin, String absClientID, String alias) {
		boolean b = false;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			
			ps = c.prepareStatement("update korona_pay_transfers set STATE=1 where UIN=? and ABSCLIENTID=?");
			ps.setString(1, uin);
			ps.setString(2, absClientID);
			ps.executeUpdate();
			c.commit();
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return b;
	}

	public static boolean setStateHadleOperation4(String uin, String alias) { // Операция на изменение данных
		boolean b = false;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);

			ps = c.prepareStatement("update korona_pay_transfers set STATE=4 where UIN=?");
			ps.setString(1, uin);
			ps.executeUpdate();
			c.commit();
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return b;
	}

	

	private static Map<String, String> getDepositAccount(String branch, String absClientId) {
		Map<String, String> map = new HashMap<String, String>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			
			ps = c.prepareStatement("select a.id as account, a.name as name, a.s_out / 100 as s_out from account a \r\n"
					+ "where a.branch=? and a.acc_bal='20206' and a.client=? and a.currency='840' and state =2");
			ps.setString(1, branch);
			ps.setString(2, absClientId);
			rs = ps.executeQuery();
			if (rs.next()) {
				map.put("account", rs.getString("ACCOUNT"));
				map.put("name", rs.getString("NAME"));
				map.put("s_out", rs.getString("S_OUT"));
			}

		} catch (Exception e) {
			ISLogger.getLogger().error("Oshibka v BD : "+e.getMessage()+" , message:"+com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return map;
	}

	public static HandleOperationResponse cancelHandleOperation(HandleOperationRequest request, String alias) {
		HandleOperationResponse response = new HandleOperationResponse();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			
			ps = c.prepareStatement(
					"update korona_pay_transfers set STATE = ? where  STATE IN ('0','9') and  UIN = ?");
			ps.setInt(1, request.getOperation());
			ps.setString(2, request.getuIN());
			ps.executeUpdate();
			c.commit();

			response.setAction("Ans_HandleOperation");
			response.setRetCode(0);
			response.setRetMsg("Ok");
			response.setRetExtMsg("");

		} catch (Exception e) {
			e.printStackTrace();
			response.setAction("Ans_HandleOperation");
			response.setRetCode(1);
			response.setRetMsg("Ошибка в БД");
			response.setRetExtMsg(e.getStackTrace().toString());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		return response;
	}

	public static HandleOperationResponse changeHandleOperation(HandleOperationRequest request, String alias) {
		HandleOperationResponse response = new HandleOperationResponse();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			
			ps = c.prepareStatement(
					"update korona_pay_transfers set RECEIVERFULLNAME=?, RECEIVERPHONE=?, STATE = '6' where UIN=?");
			ps.setString(1, request.getReceiverFullName());

			System.out.println("UIN == " + request.getuIN());

			ps.setString(2, request.getReceiverPhone());

			System.out.println("FULLNAME == " + request.getReceiverFullName());

			ps.setString(3, request.getuIN());

			System.out.println("Phone == " + request.getReceiverPhone());

			ps.executeUpdate();
			c.commit();

			try {
				Map<String, String> map2 = new HashMap<String, String>();

				map2.put("uin", request.getuIN());

				System.out.println("UIN == " + request.getuIN());

				map2.put("fullName", request.getReceiverFullName());

				System.out.println("FULLNAME == " + request.getReceiverFullName());

				map2.put("phone", request.getReceiverPhone());

				System.out.println("Phone == " + request.getReceiverPhone());

				boolean confirmStatus = KoronaPayService.regConfirmation6(map2, alias);

				System.out.println("confirmStatus === " + confirmStatus);

				if (confirmStatus = true) {
					System.out.println("Платеж проведен успешно");
				} else {
					System.out.println("Ошибка подтверждения");
				}
				System.out.println(confirmStatus);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Сервер Золотая Корона не отвечает" + "Ошибка: WS Translator не отвечает"
						+ "Обратитесь к Администратору");
			}

			response.setAction("Ans_HandleOperation");
			response.setRetCode(0);
			response.setRetMsg("Ok");
			response.setRetExtMsg("");

		} catch (Exception e) {
			e.printStackTrace();
			response.setAction("Ans_HandleOperation");
			response.setRetCode(1);
			response.setRetMsg("Ошибка в БД");
			response.setRetExtMsg(e.getStackTrace().toString());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		return response;
	}

	public static Map<String, String> getUserInfo(int id, String un, String pwd, String alias) {

		Map<String, String> map = new HashMap<String, String>();

		Connection c = null;

		CallableStatement inf = null;

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);

			/*
			 * inf = c.prepareCall("{ call info.init() }"); inf.execute();
			 */

			ps = c.prepareStatement("select b.*, u.*, s.agent_id, k.pwda, k.pwdb\r\n"
					+ "            from v_bf_bank_users b, ss_subsidiary_user u, Ss_Dpf_Synh_Korona_Pay s, korona_password k\r\n"
					+ "            where b.branch = u.branch and s.branch = b.branch\r\n"
					+ "            and b.id = u.id_user and s.veoper = 42216\r\n" 
			//		+ "            and b.branch = k.branch and b.user_name = k.user_name\r\n"
					+ "            and b.id = ? and s.sub_branch in\r\n"
					+ "           (select t.code from SS_SUBSIDIARY_USER t where t.id_user = b.id)");
			// ps.setString(1, branch1);
			ps.setString(1, String.valueOf(id));

			rs = ps.executeQuery();

			if (rs.next()) {
				map.put("AgentId", rs.getString("AGENT_ID"));
				map.put("FullName", rs.getString("FULL_NAME"));
				map.put("UserName", rs.getString("USER_NAME"));
				map.put("PWDA", rs.getString("PWDA"));
				map.put("PWDB", rs.getString("PWDB"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("GetErrorMessage" + e.getMessage());
			org.zkoss.zk.ui.util.Clients.alert(com.is.utils.CheckNull.getPstr(e));
			ISLogger.getLogger().error("GetErrorMessage" + CheckNull.getPstr(e));
			System.out.println("GetErrorMessage" + CheckNull.getPstr(e));
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		ISLogger.getLogger().error("MAP: " + map);
		return map;
	}

	public static String getWsTranslatorIP() {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value = "";

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select VALUE from bf_sets where id='WS_TRANSLATOR_ID'");

			rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("VALUE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return value;

	}

	public static String getPrintOrders() {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		return null;
	}

	public static HandleOperationRequest getPerevod(String uin) {

		// StateTransfers request = new StateTransfers();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HandleOperationRequest resp = new HandleOperationRequest();
		String result = "";
		System.out.println("uin = " + uin);

		try {
			c = ConnectionPool.getConnection();// KoronaPayViewCtrl.un,
																													// KoronaPayViewCtrl.pwd,
																													// KoronaPayViewCtrl.alias
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, uin);
			rs = ps.executeQuery();
            
			if (rs.next()) {
				
				resp.setAbsClientId(rs.getString("ABSCLIENTID"));
				resp.setPayAmount(rs.getInt("PAYAMOUNT"));
				resp.setSenderFullName(rs.getString("SENDERFULLNAME"));
				resp.setReceiverFullName(rs.getString("RECEIVERFULLNAME"));
				resp.setFromCountryIso(rs.getString("FROMCOUNTRY"));
				resp.setToCountryIso(rs.getString("TOCOUNTRY"));
				resp.setAction(rs.getString("STATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return resp;
	}
	
	public static String getAddress() {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";

		try {
			c = ConnectionPool.getConnection();																																				
			ps = c.prepareStatement("select VALUE from bf_sets where id = 'KORONA_PAY_SERVICE'");
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getString("VALUE");
			}

		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return result;
	}

	public static String getPrintOrder(PrintOrdersRequest request1) {

		PrintOrders request2 = new PrintOrders();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = " ";

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, request1.getuIN());
			rs = ps.executeQuery();

			if (rs.next()) {
				String[] fullName = null;

				if (request1.getOperation() == 0) {

					// request2.setV_date(rs.getString("OPERATIONDATE"));

					fullName = rs.getString("SENDERFULLNAME").split(" ");

					request2.setClient_name1(fullName[0]);
					request2.setClient_name2(fullName[1]);
					request2.setClient_name3(fullName[2]);

					request2.setAccdoper1(rs.getString("DEPOSIT_ACCOUNT"));

					// request2.setSummaoper1(rs.getInt("PAYAMOUNT"));

					// request2.setBranch(rs.getInt("BRANCH"));

				} else {

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return null;

	}

	public static PrintOrders getPrint(PrintOrdersRequest request) {

		PrintOrders printOrders = new PrintOrders();
		dper_info dper_info = new dper_info();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"SELECT a.*, b.*, c.DISTR_NAME FROM dper_info a, dper_books b, s_distr c where a.distr = c.distr and a.id = b.info_id and typeoper = '3' and mtcn = ?");
			ps.setString(1, request.getuIN());
			System.out.println("UIN == " + request.getuIN());
			rs = ps.executeQuery();
			if (rs.next()) {
				dper_info = new dper_info();
				printOrders = new PrintOrders();

				dper_info.setBranch(rs.getString("branch"));
				printOrders.setBranch(rs.getString("branch"));

				dper_info.setMbranch(rs.getString("mbranch"));
				dper_info.setId(rs.getLong("id"));
				dper_info.setVeoper(rs.getString("veoper"));
				dper_info.setKind(rs.getString("kind"));
				dper_info.setStrs(rs.getString("strs"));
				dper_info.setStrr(rs.getString("strr"));
				dper_info.setDistr(rs.getString("distr"));

				dper_info.setSumma(rs.getBigDecimal("summa"));
				printOrders.setSummaoper1(rs.getBigDecimal("summa"));

				dper_info.setCurrency(rs.getString("currency"));

				Integer i = rs.getInt("KIND");
				System.out.println("KIND == " + i);
				System.out.println("KIND == " + rs.getInt("KIND"));

				if (rs.getInt("KIND") == 0) {
					printOrders.setType_order("Приходный");
					printOrders.setPurpose1("Золотая Корона оркали жунатилган маблаг");
				} else if (rs.getInt("KIND") == 1 || rs.getInt("KIND") == 2) {
					printOrders.setType_order("Расходный");
					printOrders.setPurpose1("Золотая Корона оркали жунатилган маблаг");
				} else {
					System.out.println("Выберите операцию");
				}

				dper_info.setV_date(rs.getDate("v_date"));
				printOrders.setV_date(rs.getDate("v_date"));

				dper_info.setClient(rs.getString("client"));

				dper_info.setClient_name1(rs.getString("client_name1"));
				printOrders.setClient_name1(rs.getString("client_name1"));

				dper_info.setClient_name2(rs.getString("client_name2"));
				printOrders.setClient_name2(rs.getString("client_name2"));

				dper_info.setClient_name3(rs.getString("client_name3"));
				printOrders.setClient_name3(rs.getString("client_name3"));

				dper_info.setRezident(rs.getString("rezident"));
				dper_info.setDoc_id(rs.getString("doc_id"));
				dper_info.setDoc_series(rs.getString("doc_series"));
				dper_info.setDoc_number(rs.getString("doc_number"));
				dper_info.setDoc_issue(rs.getString("doc_issue"));
				dper_info.setDoc_date_issue(rs.getDate("doc_date_issue"));
				dper_info.setClient_i(rs.getString("client_i"));
				dper_info.setState(rs.getLong("state"));
				dper_info.setPost_address(rs.getString("post_address"));
				dper_info.setBirthday(rs.getDate("birthday"));
				dper_info.setProfit(rs.getBigDecimal("profit"));
				dper_info.setMtcn(rs.getString("mtcn"));
				dper_info.setU1f2(rs.getInt("u1f2"));
				dper_info.setCentsumma(rs.getBigDecimal("centsumma"));
				dper_info.setClient_i2(rs.getString("client_i2"));
				dper_info.setClient_i3(rs.getString("client_i3"));
				dper_info.setClient_i4(rs.getString("client_i4"));
				dper_info.setClient_i5(rs.getString("client_i5"));
				dper_info.setClient_i6(rs.getString("client_i6"));
				dper_info.setClient_i7(rs.getString("client_i7"));
				dper_info.setClient_i8(rs.getString("client_i8"));
				dper_info.setClient_i9(rs.getString("client_i9"));
				dper_info.setClient_i10(rs.getString("client_i10"));
				dper_info.setClient_i11date(rs.getDate("client_i11date"));
				dper_info.setClient_i12(rs.getString("client_i12"));
				dper_info.setSumma2(rs.getBigDecimal("summa2"));
				dper_info.setSumma3(rs.getBigDecimal("summa3"));
				dper_info.setSumma4(rs.getBigDecimal("summa4"));
				dper_info.setSumma5(rs.getBigDecimal("summa5"));
				dper_info.setClient_i13code_str(rs.getString("client_i13code_str"));
				dper_info.setRegion_offshor(rs.getString("region_offshor"));
				dper_info.setClient_grstr(rs.getString("client_grstr"));

				printOrders.setAccdoper1(rs.getString("ACC_D"));
				printOrders.setOpendoper(rs.getString("ACC_C"));
				printOrders.setPsummaoper1("ОДИН ДОЛЛАР");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return printOrders;

	}

	public static PrintOrders getPrints(String uin) {

		PrintOrders printOrders = new PrintOrders();
		dper_info dper_info = new dper_info();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"SELECT a.*, b.* FROM dper_info a, dper_books b where a.id = b.info_id and typeoper in('2', '3') and mtcn = ?");
			ps.setString(1, uin);
			System.out.println("UIN == " + uin);
			rs = ps.executeQuery();
			if (rs.next()) {
				dper_info = new dper_info();
				printOrders = new PrintOrders();

				dper_info.setBranch(rs.getString("branch"));
				printOrders.setBranch(rs.getString("branch"));

				dper_info.setMbranch(rs.getString("mbranch"));
				dper_info.setId(rs.getLong("id"));
				dper_info.setVeoper(rs.getString("veoper"));
				dper_info.setKind(rs.getString("kind"));
				dper_info.setStrs(rs.getString("strs"));
				dper_info.setStrr(rs.getString("strr"));
				dper_info.setDistr(rs.getString("distr"));

				dper_info.setSumma(rs.getBigDecimal("summa"));

				printOrders.setSummaoper1(rs.getBigDecimal("summa").divide(new BigDecimal(100)));

				dper_info.setCurrency(rs.getString("currency"));

				if (rs.getString("CURRENCY").equals("840")) {
					printOrders.setCur("Доллар");
					System.out.println("USD");
				} else if (rs.getString("CURRENCY").equals("643")) {
					printOrders.setCur("Рубль");
				} else if (rs.getString("CURRENCY").equals("000") || rs.getString("currency").equals("860")) {
					printOrders.setCur("Сум");
				} else {
					System.out.println("Ошибка Валюты");
				}

				if (rs.getString("KIND").equals("0")) {
					printOrders.setType_order("ПРИХОДНЫЙ");
					printOrders.setPurpose1("Золотая Корона оркали жунатилган маблаг");
				} else if (rs.getString("KIND").equals("1") || rs.getString("KIND").equals("2")) {
					printOrders.setType_order("РАСХОДНЫЙ");
					printOrders.setPurpose1("Золотая Корона оркали келган маблаг");
				} else {
					System.out.println("Выберите операцию");
				}

				dper_info.setV_date(rs.getDate("v_date"));
				printOrders.setV_date(rs.getDate("v_date"));

				dper_info.setClient(rs.getString("client"));

				dper_info.setClient_name1(rs.getString("client_name1"));
				printOrders.setClient_name1(rs.getString("client_name1"));

				dper_info.setClient_name2(rs.getString("client_name2"));
				printOrders.setClient_name2(rs.getString("client_name2"));

				dper_info.setClient_name3(rs.getString("client_name3"));
				printOrders.setClient_name3(rs.getString("client_name3"));
				

				dper_info.setRezident(rs.getString("rezident"));
				dper_info.setDoc_id(rs.getString("doc_id"));
				
				dper_info.setDoc_series(rs.getString("doc_series"));
				printOrders.setDoc_series(rs.getString("doc_series"));
				
				dper_info.setDoc_number(rs.getString("doc_number"));
				printOrders.setDoc_number(rs.getString("doc_number"));
				
				dper_info.setDoc_issue(rs.getString("doc_issue"));
				printOrders.setDoc_issue(rs.getString("doc_issue"));
				
				dper_info.setDoc_date_issue(rs.getDate("doc_date_issue"));
				printOrders.setDoc_date_issue(rs.getString("doc_date_issue"));
				
				dper_info.setClient_i(rs.getString("client_i"));
				dper_info.setState(rs.getLong("state"));
				dper_info.setPost_address(rs.getString("post_address"));
				dper_info.setBirthday(rs.getDate("birthday"));
				dper_info.setProfit(rs.getBigDecimal("profit"));
				printOrders.setComission(rs.getBigDecimal("profit").divide(new BigDecimal(100)));

				dper_info.setMtcn(rs.getString("mtcn"));
				dper_info.setU1f2(rs.getInt("u1f2"));
				dper_info.setCentsumma(rs.getBigDecimal("centsumma"));
				dper_info.setClient_i2(rs.getString("client_i2"));
				dper_info.setClient_i3(rs.getString("client_i3"));
				dper_info.setClient_i4(rs.getString("client_i4"));
				dper_info.setClient_i5(rs.getString("client_i5"));
				dper_info.setClient_i6(rs.getString("client_i6"));
				dper_info.setClient_i7(rs.getString("client_i7"));
				dper_info.setClient_i8(rs.getString("client_i8"));
				dper_info.setClient_i9(rs.getString("client_i9"));
				dper_info.setClient_i10(rs.getString("client_i10"));
				dper_info.setClient_i11date(rs.getDate("client_i11date"));
				dper_info.setClient_i12(rs.getString("client_i12"));
				dper_info.setSumma2(rs.getBigDecimal("summa2"));
				dper_info.setSumma3(rs.getBigDecimal("summa3"));
				dper_info.setSumma4(rs.getBigDecimal("summa4"));
				dper_info.setSumma5(rs.getBigDecimal("summa5"));
				
				
				double summa = new BigDecimal(rs.getInt("SUMMA") + rs.getInt("PROFIT")).divide(new BigDecimal(100)).doubleValue();
				System.out.println("SUMMAAAA: " + summa);
				printOrders.setSumma5(String.valueOf(summa));
				
				String psumma5 = CheckNull.F2Money(summa);
				printOrders.setPsumma5(psumma5);
				
				int forex_course = KoronaPayDBHelper.centSumma(rs.getString("MTCN"));
				double sumkurs = new BigDecimal(summa).multiply(new BigDecimal(forex_course)).doubleValue();
				
				System.out.println("FXCOURSE  " + forex_course);
				System.out.println("SUMKURS   " + sumkurs);
				printOrders.setKursvalfxsumma(String.valueOf(sumkurs));
				
				//printOrders.setPkursvalfxsumma();
		
				String pkurs = CheckNull.F2Money(sumkurs, "", "");
				printOrders.setPkursvalfxsumma(pkurs);
				System.out.println("PKURS  "+pkurs);
				
				printOrders.setTveoper("Золотая Корона");
				
				dper_info.setClient_i13code_str(rs.getString("client_i13code_str"));
				dper_info.setRegion_offshor(rs.getString("region_offshor"));
				dper_info.setClient_grstr(rs.getString("client_grstr"));

				printOrders.setAccdoper1(rs.getString("ACC_D"));  //rs.getString("ACC_D")
				printOrders.setOpendoper(rs.getString("ACC_C"));
				double b2 = rs.getBigDecimal("summa").divide(new BigDecimal(100)).doubleValue();

				System.out.println("b2 == " + b2);

				String b1 = CheckNull.F2Money(b2);

				System.out.println("b1 == " + b1);

				String b4 = rs.getString("CURRENCY");

				System.out.println("b4 == " + b4);

				String b3 = CheckNull.F2Money(b2, b4, b4);

				System.out.println("b3 == " + b3);
				printOrders.setPsummaoper1(b3);
				
				printOrders.setMessage("Successful");

			}

		} catch (Exception e) {
			printOrders.setMessage("Ошибка в БД \n Причина:" + e.getMessage());
			ISLogger.getLogger().error("e.getMessage  === " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return printOrders;

	}

	public static String getStateTransfers(String uin, String un, String pwd, String alias, String branch) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;
		String result = "";

		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			ps = c.prepareStatement("select a.*, dp.id as IDDPER from korona_pay_transfers a \r\n" + 
					"left join dper_info dp on dp.mtcn=a.uin \r\n" + 
					"where a.state in ('0','3','4','5','6','9') and a.operation in ('0','1') and a.uin = ?");
			ps.setString(1, uin);
			rs = ps.executeQuery();
			System.out.println("UIN == " + uin);

			if (rs.next()) {

				if (rs.getString("STATE").equals("0")) {
					dper_info dper = new dper_info();

					
					String mbranch = TransactionService.getMbranch_code(c);
					distrCode = TransactionService.getDistrByBranch(c, branch);

					System.out.println("MBRANCH == " + mbranch);

					try {

						dper.setBranch(branch);
						System.out.println("1 === " + branch);

						dper.setMbranch(mbranch);
						System.out.println("2 === " + mbranch);

						// dper.setId(new Long("SQ_DPER_INFO.NEXTVAL")); //Он сам создает ID поэтому не
						// надо его прописывать
						dper.setVeoper("42216");
						System.out.println();

						dper.setKind(String.valueOf(rs.getInt("OPERATION")));
						System.out.println("3 === " + rs.getInt("OPERATION"));

						dper.setStrr(rs.getString("TOCOUNTRYISO"));
						System.out.println("4 === " + rs.getString("TOCOUNTRYISO"));

						dper.setStrs(rs.getString("FROMCOUNTRYISO"));
						System.out.println("5 === " + rs.getString("FROMCOUNTRYISO"));

						dper.setDistr(distrCode); // Из какой улицы банк
                        System.out.println("DISTRCODE: " + distrCode);
                        
                        

						if (rs.getString("PAYCUR").equals("860")) {
							dper.setCurrency("000");
							System.out.println("7 === " + "000");

							dper.setEval("000");
							System.out.println("8 === " + "000");
						} else if (rs.getString("PAYCUR").equals("840")) {
							dper.setCurrency(rs.getString("PAYCUR"));
							System.out.println("7 === " + rs.getString("PAYCUR"));

							dper.setEval(rs.getString("PAYCUR"));
							System.out.println("8 === " + rs.getString("PAYCUR"));
						}
                     
						

						System.out.println("OPERDAtr" + rs.getString("OPERATIONDATE"));

						try {
							
							Date dateNow = new Date();

							Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020"); // rs.getString("OPERATIONDATE")
																								// "17.02.2020"
							
							dper.setV_date(d);
							System.out.println("ddd = " + d);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ISLogger.getLogger().error("DPER.SetV_DATE === " + rs.getString("OPERATIONDATE"));
						}

						dper.setClient(rs.getString("ABSCLIENTID"));
						System.out.println("10 === " + rs.getString("ABSCLIENTID"));

						String[] fullName = null;
						String[] fullName2 = null;

						dper.setState(Long.parseLong("3"));
						System.out.println("11 === " + Long.parseLong("3"));

						dper.setMtcn(rs.getString("UIN"));
						System.out.println("13 === " + rs.getString("UIN"));

						if (rs.getString("OPERATION").equals("0")) {
							
							fullName = rs.getString("RECEIVERFULLNAME").split(" ");
							fullName2 = rs.getString("SENDERFULLNAME").split(" ");

							dper.setClient_name1(fullName2[0]);
							System.out.println("14 === " + fullName2[0]);

							dper.setClient_name2(fullName2[1]);
							System.out.println("15 === " + fullName2[1]);

							dper.setClient_name3(fullName2.length > 2 ? fullName2[2] : "N");
							System.out.println("16 ==== " + (fullName.length > 2 ? fullName[2] : "N"));

							if (rs.getString("SENDERCITIZENSHIP") == "UZB") {
								dper.setRezident("1");
							} else {
								dper.setRezident("2");
							}

							if (rs.getString("SENDERDOCTYPE") == "PASSPORTUZB") {
								dper.setDoc_id("6");
							} else {
								dper.setDoc_id("4");
							}

							dper.setDoc_series(rs.getString("SENDERDOCSERIES"));
							System.out.println("17 === " + rs.getString("SENDERDOCSERIES"));

							dper.setDoc_number(rs.getString("SENDERDOCNUMBER"));
							System.out.println("18 === " + rs.getString("SENDERDOCNUMBER"));

							dper.setDoc_issue(rs.getString("SENDERDOCISSUER"));
							System.out.println("19 === " + rs.getString("SENDERDOCISSUER"));
							
							//-----------------------------------------------------------------
							
							int sum5 = rs.getInt("COMMISSIONAMOUNT") + rs.getInt("PAYAMOUNT");
							System.out.println("SUM5: " + sum5);
							
							dper.setSumma(new BigDecimal(sum5)); //request.getPayAmount()
							
							dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT") + rs.getInt("COMMISSIONAMOUNT"))); //request.getPayAmount()
							
							dper.setSumma3(new BigDecimal(rs.getString("PAYAMOUNT")));   //Сумма к переводу
							
							dper.setSumma4(new BigDecimal(rs.getInt("COMMISSIONAMOUNT"))); //Комиссия
							
							dper.setSumma5(new BigDecimal(sum5)); // Сумма с учетом комиссии
							
							dper.setCentsumma(new BigDecimal(0).divide(new BigDecimal(100)));

							
							dper.setProfit(new BigDecimal(rs.getInt("SENDERCOMMISSIONAMOUNT"))); // request.getCommissionAmount()
							System.out.println("12 ===  "
									+ BigDecimal.valueOf(rs.getInt("SENDERCOMMISSIONAMOUNT")).divide(new BigDecimal(100)));
							

							try {

								Date date_isue = sdf.parse(rs.getString("SENDERDOCISSUEDATE"));
								dper.setDoc_date_issue(date_isue);

							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ISLogger.getLogger().error(
										"request.getSenderDocIssueDate === " + rs.getString("SENDERDOCISSUEDATE"));
							}

							dper.setPost_address(rs.getString("SENDERREGADDRESS"));
							System.out.println("21 === " + rs.getString("SENDERREGADDRESS"));

							try {

								Date date_setBirthDay = sdf.parse(rs.getString("SENDERBIRTHDATE"));
								dper.setBirthday(date_setBirthDay);

							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ISLogger.getLogger()
										.error("request.getSenderBirthDate() === " + rs.getString("SENDERBIRTHDATE"));

							}
							dper.setClient_i(fullName[1]);
							System.out.println("23 === " + fullName[1]);

							dper.setClient_i2(fullName[0]);
							System.out.println("24 === " + fullName[0]);

							dper.setClient_i3(fullName.length > 2 ? fullName[2] : "N");
							System.out.println("25 === " + (fullName.length > 2 ? fullName[2] : "N"));

							dper.setClient_i4("");
							dper.setClient_i5("");
							dper.setClient_i6("");
							dper.setClient_i7("");
							dper.setClient_i8("N");
							System.out.println("26 === При отправке Данные пасспорта не указываются");

							dper.setClient_i9("N");
							System.out.println("27 === При отправке данные пасспорта не указываются");

							dper.setClient_i10("N");
							System.out.println("28 === При отправке данные пасспорта не указываются");

							Date setClient_i11date = sdf.parse(rs.getString("SENDERBIRTHDATE")); // При отправке даты
																									// рождения у
																									// получателя не
																									// указываются

							dper.setClient_i11date(setClient_i11date);

							dper.setClient_i12("N");
							System.out.println("30 === При отправке данные пасспорта не указываются");

						} else if (rs.getString("OPERATION").equals("1")) {
							dper.setStrr("860");
							
							fullName2 = rs.getString("RECEIVERFULLNAME").split(" ");
							fullName = rs.getString("SENDERFULLNAME").split(" ");

							dper.setClient_name1(fullName2[0]);
							System.out.println("31 === " + fullName2[0]);

							dper.setClient_name2(fullName2[1]);
							System.out.println("32 === " + fullName2[1]);

							dper.setClient_name3(fullName2.length > 2 ? fullName2[2] : "N");
							System.out.println("33 === " + (fullName2.length > 2 ? fullName2[2] : "N"));

							if (rs.getString("RECEIVERCITIZENSHIP").equals("UZB")) {
								dper.setRezident("1");
							} else {
								dper.setRezident("2");
							}

							if (rs.getString("RECEIVERDOCTYPE").equals("PASSPORTUZB")) {
								dper.setDoc_id("6");
							} else {
								dper.setDoc_id("4");
							}

							dper.setDoc_series(rs.getString("RECEIVERDOCSERIES"));
							System.out.println("34 === " + rs.getString("RECEIVERDOCSERIES"));

							dper.setDoc_number(rs.getString("RECEIVERDOCNUMBER"));
							System.out.println("35 === " + rs.getString("RECEIVERDOCNUMBER"));

							dper.setDoc_issue(rs.getString("RECEIVERDOCISSUER"));
							System.out.println("36 === " + rs.getString("RECEIVERDOCISSUER"));

							
							dper.setProfit(BigDecimal.valueOf(rs.getInt("RECEIVERCOMMISSIONAMOUNT"))); 
							System.out.println("Profit ===  " + BigDecimal.valueOf(0));

							try {

								Date setDoc_date_issue = sdf.parse(rs.getString("RECEIVERDOCISSUEDATE"));
								dper.setDoc_date_issue(setDoc_date_issue);

							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ISLogger.getLogger().error("request.getReceiverDocIssueDate() === "
										+ rs.getString("RECEIVERDOCISSUEDATE"));
							}

							dper.setPost_address(rs.getString("RECEIVERREGADDRESS"));
							System.out.println("38 === " + rs.getString("RECEIVERREGADDRESS"));

							try {
								Date setBirthday = sdf.parse(rs.getString("RECEIVERBIRTHDATE"));
								dper.setBirthday(setBirthday);

							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ISLogger.getLogger().error(
										"request.getReceiverBirthDate() === " + rs.getString("RECEIVERBIRTHDATE"));
							}

							dper.setClient_i(fullName[1]);
							System.out.println("40 === " + fullName[1]);

							dper.setClient_i2(fullName[0]);
							System.out.println("41 === " + fullName[0]);

							dper.setClient_i3(fullName.length > 2 ? fullName[2] : "N");
							System.out.println("42 === " + (fullName.length > 2 ? fullName[2] : "N"));

							dper.setClient_i4("");
							dper.setClient_i5("");
							dper.setClient_i6("");
							dper.setClient_i7("");

							dper.setClient_i8(rs.getString("SENDERDOCSERIES"));
							System.out.println("43 === " + rs.getString("SENDERDOCSERIES"));

							dper.setClient_i9(rs.getString("SENDERDOCNUMBER"));
							System.out.println("44 === " + rs.getString("SENDERDOCNUMBER"));

							dper.setClient_i10(rs.getString("SENDERREGADDRESS"));
							System.out.println("45 === " + rs.getString("SENDERREGADDRESS"));

							try {

								Date setClient_i11date = sdf.parse(rs.getString("SENDERBIRTHDATE"));
								if(rs.getString("SENDERBIRTHDATE") == null) {
									dper.setClient_i11date(sdf.parse("02.03.1991"));
								} else {
									dper.setClient_i11date(setClient_i11date);
								}
								
							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ISLogger.getLogger()
										.error("request.getSenderBirthDate() === " + rs.getString("SENDERBIRTHDATE"));
							}

							dper.setClient_i12(rs.getString("SENDERREGCITY"));
							System.out.println("47 === " + rs.getString("SENDERREGCITY"));
							
							dper.setCentsumma(new BigDecimal(0));

							dper.setSumma(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma3(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma4(new BigDecimal(0));
							
							dper.setSumma5(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
						}

						dper.setClient_grstr("1");
						System.out.println("48 === " + "1");
						
						account = AccountService.getAccountFromState(c, branch, rs.getString("ABSCLIENTID"), alias);					
						if (account.getAcc_dep().isEmpty()) {
							result = "Проверьте депозитный счет";
							ISLogger.getLogger().error("Dep_Account    :  Проверьте депозитный счет");
						} else {
							dper.setAcc_dep(account.getAcc_dep());
							ISLogger.getLogger().error("Dep_Account    :  " + account.getAcc_dep());
							System.out.println("Dep_Account123 === " + account.getAcc_dep());
						}
						
						Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020");
						Date dateNow = new Date();
						String nazn = "", purpose = "";
						String kurs = "";
						StringBuilder valuta = new StringBuilder();
						int purposeIndex = 1;
						
						List<Oper_info> opers = ActionService
								.getPurposes(dper, alias);
						for (Oper_info oper: opers) {
							System.out.println("oper.getVal_c() >>> " + oper.getVal_c());
							System.out.println("oper.getVal_c() >>> " + oper.getVal_d());
							System.out.println("opers >>> " + opers);
							
							if (!CheckNull.isEmpty(oper.getNazn())) {
								nazn = oper.getNazn();
								System.out.println("1++ >>> " + nazn);
							} else if (!CheckNull.isEmpty(oper.getCashsymd())) {
								nazn = oper.getCashsymd();
								System.out.println("2+ >>> " + nazn);
							}
							if (!CheckNull.isEmpty(oper.getVal_c())
									&& !CheckNull.isEmpty(oper.getVal_d())
									&& !(oper.getVal_d()).equals((oper.getVal_c()))) {
								    
								if (!(oper.getVal_d()).equals("000")) {
									kurs = SumsService.getCourse(oper.getVal_d(),
											CheckNull.d2sql(dper.getV_date()), alias);
									valuta.append("Курс").append(oper.getVal_d())
											.append(kurs);
									switch (oper.getTypeover()) {
									case (1):
										valuta.insert(0, "S=" + dper.getSumma());
										break;
									case (2):
										valuta.insert(0, "S=" + dper.getSumma3());
										break;
									case (3):
										valuta.insert(0, "S=" + dper.getSumma2());
										break;
									case (4):
										valuta.insert(0, "S=" + dper.getSumma2());
										break;
									case (5):
										valuta.insert(0, "S=" + dper.getSumma4());
										break;
									case (6):
										valuta.insert(0, "S=" + dper.getProfit());
										break;
									case (7):
										valuta.insert(0, "S=" + dper.getCentsumma());
										break;
									}
								}
								if (!(oper.getVal_c()).equals("000")) {
									kurs = SumsService.getCourse(oper.getVal_c(),
											CheckNull.d2sql(dateNow), alias);
									valuta.append("Курс").append(oper.getVal_c())
											.append(kurs);
									switch (oper.getTypeover()) {
									case (1):
										valuta.insert(0, "S=" + dper.getSumma());
										break;
									case (2):
										valuta.insert(0, "S=" + dper.getSumma3());
										break;
									case (3):
										valuta.insert(0, "S=" + dper.getSumma2());
										break;
									case (4):
										valuta.insert(0, "S=" + dper.getSumma2());
										break;
									case (5):
										valuta.insert(0, "S=" + dper.getSumma4());
										break;
									case (6):
										valuta.insert(0, "S=" + dper.getProfit());
										break;
									case (7):
										valuta.insert(0, "S=" + dper.getCentsumma());
										break;
									}
								}
							}
							String mbranchCode = TransactionService.getMbranch_code(c);
							System.out.println("purpose >>> " + oper.getPurpose());
							System.out.println("purpose >>> " + dper.getCurrency());
							System.out.println("purpose >>> " + getPurpose(oper.getPurpose(), dper));
							purpose = getPurpose(oper.getPurpose(), dper);
							switch (purposeIndex) {
							case (1):
								dper.setPurpose_1(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							case (2):
								dper.setPurpose_2(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							case (3):
								dper.setPurpose_3(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							case (4):
								dper.setPurpose_4(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							case (5):
								dper.setPurpose_5(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							case (6):
								dper.setPurpose_6(nazn + " " + mbranchCode + " " + purpose
										+ " " + valuta);
								break;
							}
							purposeIndex++;
						}
						

						dper_infoService.getPurposes(dper, alias); // вместо bank394 незабудьте вставить aliace

						ISLogger.getLogger().error("InsertNewData");
						Res res = TransactionService.insertNewData(dper, un, pwd, alias, c);
						
						if(res.getCode()!=0) {
						   result = res.getName();
						}
                        
                        
						String info_id = TransactionService.getId(c);

						System.out.println("info_id:  " + info_id);
						System.out.println("res.getcode = " + res.getCode());
						ISLogger.getLogger().error("info_id:  " + info_id);
						ISLogger.getLogger().error("res.getCode()    :  " + res.getCode());

						if (res.getCode() == 0) {
							try {
								Map<String, String> map = new HashMap<String, String>();

								map.put("uin", rs.getString("UIN"));
								System.out.println("info_id" + info_id);
								map.put("operation", String.valueOf(rs.getInt("OPERATION")));
								System.out.println("OPERATION" + (String.valueOf(rs.getInt("OPERATION"))));

								map.put("nciDocId", info_id);

								map.put("nciDocDate",
										new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

								String[] fullNames = rs.getString("SENDERFULLNAME").split(" ");
								System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
								String[] fullNames2 = rs.getString("RECEIVERFULLNAME").split(" ");
								System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
								map.put("payerLastName", fullNames[0]);
								map.put("payerFirstName", fullNames[1]);

								try {
									map.put("payerOtherName", fullNames[2]);
								} catch (Exception e) {
									map.put("payerOtherName", "");
								}

								map.put("payeeLastName", fullNames2[0]);
								map.put("payeeFirstName", fullNames2[1]);

								try {
									map.put("payeeOtherName", fullNames2[2]);
								} catch (Exception e) {
									map.put("payeeOtherName", "");
								}

								map.put("absClientId", rs.getString("ABSCLIENTID"));

								map.put("amount", String.valueOf(rs.getInt("PAYAMOUNT")));
								map.put("exp", String.valueOf(rs.getInt("PAYEXP")));
								map.put("cur", String.valueOf(rs.getInt("PAYCUR")));
								// map.put("passportNumber", h.getSenderDocNumber());
								System.out.println("map === " + map);

								boolean confirmStatus = KoronaPayService.regConfirmation(map, alias);

								ISLogger.getLogger().error("confirmStatus    :  " + confirmStatus);
								System.out.println("confirmStatus === " + confirmStatus);

								if (confirmStatus == true) {
									System.out.println("Платеж проведен успешно");
									result = "Платеж проведен успешно";
									ISLogger.getLogger().error("result    :  " + result);

								} else {
									ActionService.changeState(rs.getString("UIN"));
									System.out.println("Ошибка подтверждения");
									result = "Ошибка подтверждения";
									ISLogger.getLogger().error("result    :  " + result);
								}
								System.out.println(confirmStatus);
								ISLogger.getLogger().error("confirmStatus    :  " + confirmStatus);

							} catch (Exception e) {
								e.printStackTrace();
								ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
								ActionService.changeState(rs.getString("UIN"));
								System.out.println("Сервер Золотая Корона не отвечает"
										+ "Ошибка: WS Translator не отвечает" + "Обратитесь к Администратору");
								result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
							}
						} else {
							//result = "Перевод не выполнен";
						}
					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
					}
					
				} else if (rs.getString("STATE").equals("9")) {
					
					System.out.println("state = 9");
					
					try {
						Map<String, String> map = new HashMap<String, String>();

						map.put("uin", rs.getString("UIN"));
						System.out.println("info_id" + rs.getString("IDDPER"));
						map.put("operation", String.valueOf(rs.getInt("OPERATION")));
						System.out.println("OPERATION" + (String.valueOf(rs.getInt("OPERATION"))));

						map.put("nciDocId", rs.getString("IDDPER"));

						map.put("nciDocDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

						String[] fullNames = rs.getString("SENDERFULLNAME").split(" ");
						System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
						String[] fullNames2 = rs.getString("RECEIVERFULLNAME").split(" ");
						System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
						map.put("payerLastName", fullNames[0]);
						map.put("payerFirstName", fullNames[1]);

						try {
							map.put("payerOtherName", fullNames[2]);
						} catch (Exception e) {
							map.put("payerOtherName", "");
						}

						map.put("payeeLastName", fullNames2[0]);
						map.put("payeeFirstName", fullNames2[1]);

						try {
							map.put("payeeOtherName", fullNames2[2]);
						} catch (Exception e) {
							map.put("payeeOtherName", "");
						}

						map.put("absClientId", rs.getString("ABSCLIENTID"));

						map.put("amount", String.valueOf(rs.getInt("PAYAMOUNT")));
						map.put("exp", String.valueOf(rs.getInt("PAYEXP")));
						map.put("cur", String.valueOf(rs.getInt("PAYCUR")));
						// map.put("passportNumber", h.getSenderDocNumber());
						System.out.println("map === " + map);

						boolean confirmStatus = KoronaPayService.regConfirmation(map, alias);

						ISLogger.getLogger().error("confirmStatus    :  " + confirmStatus);
						System.out.println("confirmStatus === " + confirmStatus);

						if (confirmStatus == true) {
							System.out.println("Платеж проведен успешно");
							result = "Платеж проведен успешно";

						} else {

							System.out.println("Ошибка подтверждения");
							result = "Ошибка подтверждения";
						}
						System.out.println(confirmStatus);
						ISLogger.getLogger().error("confirmStatus    :  " + confirmStatus);
					
					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
						System.out.println("Сервер Золотая Корона не отвечает" + "Ошибка: WS Translator не отвечает"
								+ "Обратитесь к Администратору");
						result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
					}
				}
			}

		} catch (Exception e) {
			ISLogger.getLogger().error("Exception e:  " + e);
			ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}

		
		return result;
		
	}
	
	private static String getPurpose(String p, dper_info newdper) {
		StringBuilder sb = new StringBuilder();
		StringBuilder new_ = new StringBuilder();
		String old_;
		
		if (p.trim().length() == 0) {
			return "";
		}
		sb.append(p);
		
		new_.setLength(0);
		old_ = "#SNP";
		new_.append(newdper.getDoc_series()).append(newdper.getDoc_number());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#DP";
		new_.append(newdper.getVeoper());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#IO";
		new_.append(newdper.getKind());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#DATE";
		new_.append(newdper.getV_date());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#FIO";
		new_.append(newdper.getClient_name1()).append(" ")
				.append(newdper.getClient_name2()).append(" ")
				.append(newdper.getClient_name3());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#KO";
		new_.append(newdper.getClient_i());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		new_.setLength(0);
		old_ = "#MTCN";
		new_.append(newdper.getMtcn());
		if (sb.indexOf(old_) > 0) {
			sb.replace(sb.indexOf(old_), sb.indexOf(old_) + old_.length(),
					new_.toString());
		}
		
		return sb.toString();
	}

	/*public static String statetrans(String uin) {
		Connection c2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		String result = "";

		try {
			c2 = ConnectionPool.getConnection();
			ps2 = c2.prepareStatement(
					"select a.*, b.ID from korona_pay_transfers a, dper_info b where a.state = '0' and a.uin = ? and a.uin = b.mtcn");
			ps2.setString(1, uin);
			rs2 = ps2.executeQuery();

			if (rs2.next()) {
				try {
					Map<String, String> map = new HashMap<String, String>();

					map.put("uin", rs2.getString("UIN"));
					System.out.println("info_id" + rs2.getString("UIN"));
					map.put("operation", String.valueOf(rs2.getInt("OPERATION")));
					System.out.println("OPERATION" + (String.valueOf(rs2.getInt("OPERATION"))));

					map.put("nciDocId", rs2.getString("ID"));

					map.put("nciDocDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

					String[] fullNames = rs2.getString("SENDERFULLNAME").split(" ");
					System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
					String[] fullNames2 = rs2.getString("RECEIVERFULLNAME").split(" ");
					System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
					map.put("payerLastName", fullNames[0]);
					map.put("payerFirstName", fullNames[1]);

					try {
						map.put("payerOtherName", fullNames[2]);
					} catch (Exception e) {
						map.put("payerOtherName", "");
					}

					map.put("payeeLastName", fullNames2[0]);
					map.put("payeeFirstName", fullNames2[1]);

					try {
						map.put("payeeOtherName", fullNames2[2]);
					} catch (Exception e) {
						map.put("payeeOtherName", "");
					}

					map.put("absClientId", rs2.getString("ABSCLIENTID"));

					map.put("amount", String.valueOf(rs2.getInt("PAYAMOUNT")));
					map.put("exp", String.valueOf(rs2.getInt("PAYEXP")));
					map.put("cur", String.valueOf(rs2.getInt("PAYCUR")));
					// map.put("passportNumber", h.getSenderDocNumber());
					System.out.println("map === " + map);

					//boolean confirmStatus = KoronaPayService.regConfirmation(map);

					System.out.println("confirmStatus === " + confirmStatus);

					if (confirmStatus == true) {
						System.out.println("Платеж проведен успешно");
						result = "Платеж проведен успешно";

					} else {
						System.out.println("Ошибка подтверждения");
						result = "Ошибка подтверждения";
					}
					System.out.println(confirmStatus);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Сервер Золотая Корона не отвечает" + "Ошибка: WS Translator не отвечает"
							+ "Обратитесь к Администратору");
					result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c2);
		}

		return result;
	}*/
	
	public static int comission(String mtcn) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		int sum = 0;
		int kind = 0;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {
				sum = rs.getInt("COMMISSIONAMOUNT");
				
			}

			System.out.println("SUM ===== " + sum);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return sum;

	}

	public static int centSum(String mtcn) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		int sum = 0;
		int kind = 0;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {
				if(rs.getString("OPERATION").equals("0")) {
				sum = rs.getInt("PAYAMOUNT") + rs.getInt("COMMISSIONAMOUNT");
				} else {
				sum = rs.getInt("PAYAMOUNT");
				}
			}

			System.out.println("SUM ===== " + sum);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return sum;

	}
	
	
	private static BigDecimal truncateDecimal(double x,int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }
	
	
	

	public static String centSumOk(String mtcn, double sum2, double sum3, double sum1, double summa, String un, String pwd, String alias, String branch) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		Account account;

		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			ps = c.prepareStatement("select a.*, dp.id as IDDPER from korona_pay_transfers a \r\n" + 
					"   left join dper_info dp on dp.mtcn=a.uin \r\n" + 
					"where a.state in ('0','9','2','3','4','6') and a.operation in ('0', '1') and a.uin = ?");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {
				
				if(rs.getString("STATE").equals("0")) {

				dper_info dper = new dper_info();

				
				String mbranch = TransactionService.getMbranch_code(c);
				distrCode = TransactionService.getDistrByBranch(c, branch);

				System.out.println("MBRANCH == " + mbranch);

				try {

					dper.setBranch(branch);
					System.out.println("1 === " + branch);

					dper.setMbranch(mbranch);
					System.out.println("2 === " + mbranch);

					// dper.setId(new Long("SQ_DPER_INFO.NEXTVAL")); //Он сам создает ID поэтому не
					// надо его прописывать
					dper.setVeoper("42216");
					System.out.println();

					dper.setKind(String.valueOf(rs.getInt("OPERATION")));
					System.out.println("3 === " + rs.getInt("OPERATION"));

					dper.setStrs(rs.getString("FROMCOUNTRYISO"));
					
					System.out.println("4 === " + rs.getString("FROMCOUNTRYISO"));

					dper.setStrr(rs.getString("TOCOUNTRYISO"));
					System.out.println("5 === " + rs.getString("TOCOUNTRYISO"));

					dper.setDistr(distrCode); // Из какой улицы банк

					dper.setCurrency(rs.getString("PAYCUR"));
					System.out.println("7 === " + rs.getString("PAYCUR"));

					dper.setEval(rs.getString("PAYCUR"));
					System.out.println("8 === " + rs.getString("PAYCUR"));
					
					
					System.out.println("OPERDAtr" + rs.getString("OPERATIONDATE"));

					try {
                        
						Date dateNow = new Date();
						SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
						
						Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020");  //rs.getString("OPERATIONDATE")
						System.out.println(d);

						dper.setV_date(d);
						System.out.println("ddd = " + dateNow);

					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
						ISLogger.getLogger().error("DPER.SetV_DATE === " + rs.getString("OPERATIONDATE"));
					}

					dper.setClient(rs.getString("ABSCLIENTID"));
					System.out.println("10 === " + rs.getString("ABSCLIENTID"));

					String[] fullName = null;
					String[] fullName2 = null;

					dper.setState(Long.parseLong("3"));
					System.out.println("11 === " + Long.parseLong("3"));

					dper.setMtcn(rs.getString("UIN"));
					System.out.println("13 === " + rs.getString("UIN"));

					// dper.setU1f2(2);
					
					

					if (rs.getString("OPERATION").equals("0")) {
						fullName = rs.getString("RECEIVERFULLNAME").split(" ");
						fullName2 = rs.getString("SENDERFULLNAME").split(" ");

						dper.setClient_name1(fullName2[0]);
						System.out.println("14 === " + fullName2[0]);

						dper.setClient_name2(fullName2[1]);
						System.out.println("15 === " + fullName2[1]);

						dper.setClient_name3(fullName2.length > 2 ? fullName2[2] : "N");
						System.out.println("16 ==== " + (fullName.length > 2 ? fullName[2] : "N"));

						if (rs.getString("SENDERCITIZENSHIP") == "UZB") {
							dper.setRezident("1");
						} else {
							dper.setRezident("2");
						}

						if (rs.getString("SENDERDOCTYPE") == "PASSPORTUZB") {
							dper.setDoc_id("6");
						} else {
							dper.setDoc_id("4");
						}

						dper.setDoc_series(rs.getString("SENDERDOCSERIES"));
						System.out.println("17 === " + rs.getString("SENDERDOCSERIES"));

						dper.setDoc_number(rs.getString("SENDERDOCNUMBER"));
						System.out.println("18 === " + rs.getString("SENDERDOCNUMBER"));

						dper.setDoc_issue(rs.getString("SENDERDOCISSUER"));
						System.out.println("19 === " + rs.getString("SENDERDOCISSUER"));
						
						
						
						try {

							Date date_isue = sdf.parse(rs.getString("SENDERDOCISSUEDATE"));
							dper.setDoc_date_issue(date_isue);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ISLogger.getLogger()
									.error("request.getSenderDocIssueDate === " + rs.getString("SENDERDOCISSUEDATE"));
						}

						dper.setPost_address(rs.getString("SENDERREGADDRESS"));
						System.out.println("21 === " + rs.getString("SENDERREGADDRESS"));

						try {

							Date date_setBirthDay = sdf.parse(rs.getString("SENDERBIRTHDATE"));
							dper.setBirthday(date_setBirthDay);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ISLogger.getLogger()
									.error("request.getSenderBirthDate() === " + rs.getString("SENDERBIRTHDATE"));

						}
						dper.setClient_i(fullName[1]);
						System.out.println("23 === " + fullName[1]);

						dper.setClient_i2(fullName[0]);
						System.out.println("24 === " + fullName[0]);

						dper.setClient_i3(fullName.length > 2 ? fullName[2] : "N");
						System.out.println("25 === " + (fullName.length > 2 ? fullName[2] : "N"));

						dper.setClient_i4("");
						dper.setClient_i5("");
						dper.setClient_i6("");
						dper.setClient_i7("");
						dper.setClient_i8("N");
						System.out.println("26 === При отправке Данные пасспорта не указываются");

						dper.setClient_i9("N");
						System.out.println("27 === При отправке данные пасспорта не указываются");

						dper.setClient_i10("N");
						System.out.println("28 === При отправке данные пасспорта не указываются");

						Date setClient_i11date = sdf.parse(rs.getString("SENDERBIRTHDATE")); // При отправке даты
																								// рождения у
																								// получателя не
																								// указываются

						dper.setClient_i11date(setClient_i11date);

						dper.setClient_i12("N");
						System.out.println("30 === При отправке данные пасспорта не указываются");
						
						
						//---------------------------------------------------------------------------------
					
						
						int sum5 = rs.getInt("COMMISSIONAMOUNT") + rs.getInt("PAYAMOUNT");
						System.out.println("SUM5: " + sum5);
						
						if(sum3 == sum1) {  //Если всю сумму нужно конвертировать
							
							dper.setSumma(new BigDecimal(0));
							
							dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma3(new BigDecimal(rs.getInt("PAYAMOUNT")));   //Сумма к переводу
							
							dper.setSumma4(new BigDecimal(rs.getInt("COMMISSIONAMOUNT")));
							
							dper.setSumma5(new BigDecimal(summa));
							
							
						} else {
							
						double  divided = (double) (sum1 - sum3) ;
						
						BigDecimal t = truncateDecimal(summa, 2);  //До этого сдесь был divided
						
				         BigDecimal m = t.multiply(new BigDecimal(100));
				         
				         System.out.println("Big_Dec :" + m.longValue());
							
						dper.setSumma(new BigDecimal(m.longValue())); 
						
						dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT"))); //request.getPayAmount()
						
						dper.setSumma3(new BigDecimal(rs.getInt("PAYAMOUNT")));   //Сумма к переводу
						
						dper.setSumma4(new BigDecimal(rs.getInt("COMMISSIONAMOUNT")));
						
						dper.setSumma5(new BigDecimal(sum5)); // Сумма с учетом комиссии
						
						}
						
						System.out.println("SUM2 Convert Summa : " + sum2);
						int value = (int)sum2; 
						dper.setCentsumma(BigDecimal.valueOf(value).multiply(new BigDecimal(100)));
						System.out.println("SUM2 :  Конвертированная сумма:  " + BigDecimal.valueOf(sum2).multiply(new BigDecimal(100)));

						dper.setProfit(
								BigDecimal.valueOf(rs.getInt("SENDERCOMMISSIONAMOUNT"))); // request.getSenderCommissionAmount()
						System.out.println("12 ===  "
								+ BigDecimal.valueOf(0).divide(new BigDecimal(100)));
						

					} else if (rs.getString("OPERATION").equals("1")) {

						fullName2 = rs.getString("RECEIVERFULLNAME").split(" ");
						fullName = rs.getString("SENDERFULLNAME").split(" ");

						dper.setClient_name1(fullName2[0]);
						System.out.println("31 === " + fullName2[0]);

						dper.setClient_name2(fullName2[1]);
						System.out.println("32 === " + fullName2[1]);

						dper.setClient_name3(fullName2.length > 2 ? fullName2[2] : "N");
						System.out.println("33 === " + (fullName2.length > 2 ? fullName2[2] : "N"));

						if (rs.getString("RECEIVERCITIZENSHIP").equals("UZB")) {
							dper.setRezident("1");
						} else {
							dper.setRezident("2");
						}

						if (rs.getString("RECEIVERDOCTYPE").equals("PASSPORTUZB")) {
							dper.setDoc_id("6");
						} else {
							dper.setDoc_id("4");
						}

						dper.setDoc_series(rs.getString("RECEIVERDOCSERIES"));
						System.out.println("34 === " + rs.getString("RECEIVERDOCSERIES"));

						dper.setDoc_number(rs.getString("RECEIVERDOCNUMBER"));
						System.out.println("35 === " + rs.getString("RECEIVERDOCNUMBER"));

						dper.setDoc_issue(rs.getString("RECEIVERDOCISSUER"));
						System.out.println("36 === " + rs.getString("RECEIVERDOCISSUER"));


						try {

							Date setDoc_date_issue = sdf.parse(rs.getString("RECEIVERDOCISSUEDATE"));
							dper.setDoc_date_issue(setDoc_date_issue);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error(
									"request.getReceiverDocIssueDate() === " + rs.getString("RECEIVERDOCISSUEDATE"));
						}

						dper.setPost_address(rs.getString("RECEIVERREGADDRESS"));
						System.out.println("38 === " + rs.getString("RECEIVERREGADDRESS"));

						try {
							Date setBirthday = sdf.parse(rs.getString("RECEIVERBIRTHDATE"));
							dper.setBirthday(setBirthday);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ISLogger.getLogger()
									.error("request.getReceiverBirthDate() === " + rs.getString("RECEIVERBIRTHDATE"));
						}

						dper.setClient_i(fullName[1]);
						System.out.println("40 === " + fullName[1]);

						dper.setClient_i2(fullName[0]);
						System.out.println("41 === " + fullName[0]);

						dper.setClient_i3(fullName.length > 2 ? fullName[2] : "N");
						System.out.println("42 === " + (fullName.length > 2 ? fullName[2] : "N"));

						dper.setClient_i4("");
						dper.setClient_i5("");
						dper.setClient_i6("");
						dper.setClient_i7("");

						dper.setClient_i8(rs.getString("SENDERDOCSERIES"));
						System.out.println("43 === " + rs.getString("SENDERDOCSERIES"));

						dper.setClient_i9(rs.getString("SENDERDOCNUMBER"));
						System.out.println("44 === " + rs.getString("SENDERDOCNUMBER"));

						dper.setClient_i10(rs.getString("SENDERREGADDRESS"));
						System.out.println("45 === " + rs.getString("SENDERREGADDRESS"));

						try {

							Date setClient_i11date = sdf.parse(rs.getString("SENDERBIRTHDATE"));
							dper.setClient_i11date(setClient_i11date);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ISLogger.getLogger()
									.error("request.getSenderBirthDate() === " + rs.getString("SENDERBIRTHDATE"));
						}

						dper.setClient_i12(rs.getString("SENDERREGCITY"));
						System.out.println("47 === " + rs.getString("SENDERREGCITY"));
						
						dper.setProfit(BigDecimal.valueOf(rs.getInt("RECEIVERCOMMISSIONAMOUNT"))); // request.getCommissionAmount()
						System.out.println("Profit ===  " + BigDecimal.valueOf(rs.getInt("RECEIVERCOMMISSIONAMOUNT")));
						
						dper.setCentsumma(BigDecimal.valueOf(sum3).multiply(new BigDecimal(100)));
						
						if (sum3 == sum1) {
							
							dper.setSumma(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							dper.setSumma3(new BigDecimal(0));
							
							dper.setSumma4(new BigDecimal(0)); //Комиссия

							dper.setSumma5(new BigDecimal(rs.getInt("PAYAMOUNT")));
							
							
						} else {
						
						dper.setSumma(new BigDecimal(rs.getInt("PAYAMOUNT")));
						
						dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT")));
						
                        double  divided = (double) (sum1 - sum3) ;
						
						BigDecimal t = truncateDecimal(divided, 2);
						
				        BigDecimal m = t.multiply(new BigDecimal(100));
				         
				        System.out.println("Big_Dec :" + m.longValue());
							
						dper.setSumma3(new BigDecimal(m.longValue()));   
						
						dper.setSumma4(new BigDecimal(0)); //Комиссия

						dper.setSumma5(new BigDecimal(rs.getInt("PAYAMOUNT")));
						
						}
						

					}

					
					// dper.setSumma5(new BigDecimal(" ")); // Сумма с учетом комиссии
					
					dper.setClient_grstr("1");
					System.out.println("48 === " + "1");
					
					account = AccountService.getAccountFromState(c, branch, rs.getString("ABSCLIENTID"), alias);					
					if (account.getAcc_dep().isEmpty()) {
						result = "Проверьте депозитный счет";
						ISLogger.getLogger().error("Dep_Account    :  Проверьте депозитный счет");
					} else {
						dper.setAcc_dep(account.getAcc_dep());
						ISLogger.getLogger().error("Dep_Account    :  " + account.getAcc_dep());
						System.out.println("Dep_Account123 === " + account.getAcc_dep());
					}
									
					Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020");
					Date dateNow = new Date();
					String nazn = "", purpose = "";
					String kurs = "";
					StringBuilder valuta = new StringBuilder();
					int purposeIndex = 1;
					
					List<Oper_info> opers = ActionService
							.getPurposes(dper, alias);
					for (Oper_info oper: opers) {
						if (!CheckNull.isEmpty(oper.getNazn())) {
							nazn = oper.getNazn();
						} else if (!CheckNull.isEmpty(oper.getCashsymd())) {
							nazn = oper.getCashsymd();
						}
						if (!CheckNull.isEmpty(oper.getVal_c())
								&& !CheckNull.isEmpty(oper.getVal_d())
								&& !(oper.getVal_d()).equals((oper.getVal_c()))) {
							if (!(oper.getVal_d()).equals("000")) {
								kurs = SumsService.getCourse(oper.getVal_d(),
										CheckNull.d2sql(dper.getV_date()), alias);
								valuta.append("Курс").append(oper.getVal_d())
										.append(kurs);
								switch (oper.getTypeover()) {
								case (1):
									valuta.insert(0, "S=" + dper.getSumma());
									break;
								case (2):
									valuta.insert(0, "S=" + dper.getSumma3());
									break;
								case (3):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (4):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (5):
									valuta.insert(0, "S=" + dper.getSumma4());
									break;
								case (6):
									valuta.insert(0, "S=" + dper.getProfit());
									break;
								case (7):
									valuta.insert(0, "S=" + dper.getCentsumma());
									break;
								}
							}
							if (!(oper.getVal_c()).equals("000")) {
								kurs = SumsService.getCourse(oper.getVal_c(),
										CheckNull.d2sql(dateNow), alias);
								valuta.append("Курс").append(oper.getVal_c())
										.append(kurs);
								switch (oper.getTypeover()) {
								case (1):
									valuta.insert(0, "S=" + dper.getSumma());
									break;
								case (2):
									valuta.insert(0, "S=" + dper.getSumma3());
									break;
								case (3):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (4):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (5):
									valuta.insert(0, "S=" + dper.getSumma4());
									break;
								case (6):
									valuta.insert(0, "S=" + dper.getProfit());
									break;
								case (7):
									valuta.insert(0, "S=" + dper.getCentsumma());
									break;
								}
							}
						}
						String mbranchCode = TransactionService.getMbranch_code(c);
						purpose = getPurpose(oper.getPurpose(), dper);
						switch (purposeIndex) {
						case (1):
							dper.setPurpose_1(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (2):
							dper.setPurpose_2(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (3):
							dper.setPurpose_3(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (4):
							dper.setPurpose_4(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (5):
							dper.setPurpose_5(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (6):
							dper.setPurpose_6(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						}
						purposeIndex++;
					}

					dper_infoService.getPurposes(dper, alias); // вместо bank394 незабудьте вставить aliace

					System.out.println("com.is.dper_info.service.TransactionsService.insertNewData(dper, alias)");

					Res res = TransactionService.insertNewData(dper, un, pwd, alias, c);

					String info_id = TransactionService.getId(c);

					System.out.println("info_id:  " + info_id);
					System.out.println("res.getcode = " + res.getCode());
					ISLogger.getLogger().error("res.getcode = " + res.getCode());
					ISLogger.getLogger().error("info_id:  " + info_id);

					if (res.getCode() == 0) {
						try {
							Map<String, String> map = new HashMap<String, String>();

							map.put("uin", rs.getString("UIN"));
							System.out.println("info_id" + info_id);
							map.put("operation", String.valueOf(rs.getInt("OPERATION")));
							System.out.println("OPERATION" + (String.valueOf(rs.getInt("OPERATION"))));

							map.put("nciDocId", info_id);

							map.put("nciDocDate",
									new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

							String[] fullNames = rs.getString("SENDERFULLNAME").split(" ");
							System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
							String[] fullNames2 = rs.getString("RECEIVERFULLNAME").split(" ");
							System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
							map.put("payerLastName", fullNames[0]);
							map.put("payerFirstName", fullNames[1]);

							try {
								map.put("payerOtherName", fullNames[2]);
							} catch (Exception e) {
								map.put("payerOtherName", "");
							}

							map.put("payeeLastName", fullNames2[0]);
							map.put("payeeFirstName", fullNames2[1]);

							try {
								map.put("payeeOtherName", fullNames2[2]);
							} catch (Exception e) {
								map.put("payeeOtherName", "");
							}

							map.put("absClientId", rs.getString("ABSCLIENTID"));

							map.put("amount", String.valueOf(rs.getInt("PAYAMOUNT")));
							map.put("exp", String.valueOf(rs.getInt("PAYEXP")));
							map.put("cur", String.valueOf(rs.getInt("PAYCUR")));
							// map.put("passportNumber", h.getSenderDocNumber());
							System.out.println("map === " + map);

							boolean confirmStatus = KoronaPayService.regConfirmation(map, alias);

							ISLogger.getLogger().error("confirmStatus    :  " + confirmStatus);
							System.out.println("confirmStatus === " + confirmStatus);

							if (confirmStatus == true) {
								System.out.println("Платеж проведен успешно");
								ISLogger.getLogger().error("result    :  " + result);
								result = "Платеж проведен успешно";

							} else {
								ActionService.changeState(rs.getString("UIN"));
								System.out.println("Ошибка подтверждения");
								result = "Ошибка подтверждения";
							}
							System.out.println(confirmStatus);

						} catch (Exception e) {
							e.printStackTrace();
							ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
							ActionService.changeState(rs.getString("UIN"));
							System.out.println("Сервер Золотая Корона не отвечает"
									+ "Ошибка: WS Translator не отвечает" + "Обратитесь к Администратору");
							result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
						}
					} else {
						
					}
					

				} catch (Exception e) {
					ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
					e.printStackTrace();
				}
				
				} else if (rs.getString("STATE").equals("9")) {
					
					System.out.println("state = 9");
					
					try {
						Map<String, String> map = new HashMap<String, String>();

						map.put("uin", rs.getString("UIN"));
						System.out.println("info_id" + rs.getString("IDDPER"));
						map.put("operation", String.valueOf(rs.getInt("OPERATION")));
						System.out.println("OPERATION" + (String.valueOf(rs.getInt("OPERATION"))));

						map.put("nciDocId", rs.getString("IDDPER"));

						map.put("nciDocDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

						String[] fullNames = rs.getString("SENDERFULLNAME").split(" ");
						System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
						String[] fullNames2 = rs.getString("RECEIVERFULLNAME").split(" ");
						System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
						map.put("payerLastName", fullNames[0]);
						map.put("payerFirstName", fullNames[1]);

						try {
							map.put("payerOtherName", fullNames[2]);
						} catch (Exception e) {
							map.put("payerOtherName", "");
						}

						map.put("payeeLastName", fullNames2[0]);
						map.put("payeeFirstName", fullNames2[1]);

						try {
							map.put("payeeOtherName", fullNames2[2]);
						} catch (Exception e) {
							map.put("payeeOtherName", "");
						}

						map.put("absClientId", rs.getString("ABSCLIENTID"));

						map.put("amount", String.valueOf(rs.getInt("PAYAMOUNT")));
						map.put("exp", String.valueOf(rs.getInt("PAYEXP")));
						map.put("cur", String.valueOf(rs.getInt("PAYCUR")));
						// map.put("passportNumber", h.getSenderDocNumber());
						System.out.println("map === " + map);

						boolean confirmStatus = KoronaPayService.regConfirmation(map, alias);

						System.out.println("confirmStatus === " + confirmStatus);

						if (confirmStatus == true) {
							System.out.println("Платеж проведен успешно");
							result = "Платеж проведен успешно";

						} else {

							System.out.println("Ошибка подтверждения");
							result = "Ошибка подтверждения";
						}
						System.out.println(confirmStatus);

					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
						System.out.println("Сервер Золотая Корона не отвечает" + "Ошибка: WS Translator не отвечает"
								+ "Обратитесь к Администратору");
						result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
						ISLogger.getLogger().error("result: " +result);
					}
				}

			}

		} catch (Exception e) {
			ISLogger.getLogger().error("CATCH: " +e);
			result = "Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e);
			ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}

		return result;

	}

	public static int centSumma(String mtcn) { // Курс валюты для ЦентСуммы

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int course = 0;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select a.operation,a.payamount,a.paycur,(select curdate from sets),\r\n" + 
					"decode(a.operation, 0, info.GetCourse('840','000',6,(select curdate from sets)), \r\n" + 
					"                    1, info.GetCourse('840','000',7,(select curdate from sets)), 99) as equalkurs\r\n" + 
					"from korona_pay_transfers a, dual b\r\n" + 
					"where a.uin = ?");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {
				course = rs.getInt("EQUALKURS");
				ISLogger.getLogger().error("course forex:: " +course);
			}

			System.out.println("current ===== " + course);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		
		return course;
	}

	public static int current(String mtcn) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		int current = 0;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {
				current = rs.getInt("PAYCUR");
			}

			System.out.println("current ===== " + current);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}

		return current;

	}

	public static String btn_return(String mtcn, String un, String pwd, String alias, String branch) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Account account;
		String result = "";
		ISLogger.getLogger().error("btn_return mtcn: " +mtcn);
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ? and operation='0' and state='1'");
			ps.setString(1, mtcn);
			rs = ps.executeQuery();

			if (rs.next()) {

				dper_info dper = new dper_info();
				

				
				String mbranch = TransactionService.getMbranch_code(c);
				distrCode = TransactionService.getDistrByBranch(c, branch);
				
				System.out.println("MBRANCH == " + mbranch);

				ISLogger.getLogger().error("MBRANCH: " +mbranch);
				try {

					dper.setBranch(branch);
					System.out.println("1 === " + branch);
					ISLogger.getLogger().error("1: " +branch);

					dper.setMbranch(mbranch);
					System.out.println("2 === " + mbranch);
					

					// dper.setId(new Long("SQ_DPER_INFO.NEXTVAL")); //Он сам создает ID поэтому не
					// надо его прописывать
					dper.setVeoper("42216");
					System.out.println();

					dper.setKind("2");
					System.out.println("3 === " + "2");

					dper.setStrs(rs.getString("FROMCOUNTRYISO"));
					System.out.println("4 === " + rs.getString("FROMCOUNTRYISO"));

					dper.setStrr(rs.getString("TOCOUNTRYISO"));
					System.out.println("5 === " + rs.getString("TOCOUNTRYISO"));

					dper.setDistr(distrCode); // Из какой улицы банк

					dper.setCurrency(rs.getString("PAYCUR"));
					System.out.println("7 === " + rs.getString("PAYCUR"));

					dper.setEval(rs.getString("PAYCUR"));
					System.out.println("8 === " + rs.getString("PAYCUR"));

					System.out.println("OPERATIONDATE" + rs.getString("OPERATIONDATE"));

					try {

						Date dateNow = new Date();
						Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020");
						System.out.println(d);

						dper.setV_date(dateNow);
						System.out.println("ddd = " + d);

					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger().error("DPER.SetV_DATE === " + rs.getString("OPERATIONDATE"));
					}

					dper.setClient(rs.getString("ABSCLIENTID"));
					System.out.println("10 === " + rs.getString("ABSCLIENTID"));
					ISLogger.getLogger().error("ABSCLIENTID:   " +rs.getString("ABSCLIENTID"));

					String[] fullName = null;
					String[] fullName2 = null;

					dper.setState(Long.parseLong("3"));
					System.out.println("11 === " + Long.parseLong("3"));

					dper.setMtcn(rs.getString("UIN"));
					System.out.println("13 === " + rs.getString("UIN"));
					ISLogger.getLogger().error("UIN:   " +rs.getString("UIN"));

					// dper.setU1f2(2);
					// dper.setCentsumma();

					fullName = rs.getString("RECEIVERFULLNAME").split(" ");
					fullName2 = rs.getString("SENDERFULLNAME").split(" ");

					dper.setClient_name1(fullName2[0]);
					System.out.println("14 === " + fullName2[0]);
					ISLogger.getLogger().error("SENDERFULLNAME0:   " +fullName2[0]);

					dper.setClient_name2(fullName2[1]);
					System.out.println("15 === " + fullName2[1]);
					ISLogger.getLogger().error("SENDERFULLNAME1:   " +fullName2[1]);

					dper.setClient_name3(fullName2.length > 2 ? fullName2[2] : "N");
					System.out.println("16 ==== " + (fullName.length > 2 ? fullName[2] : "N"));
					ISLogger.getLogger().error("SENDERFULLNAME2:   " +(fullName.length > 2 ? fullName[2] : "N"));

					if (rs.getString("SENDERCITIZENSHIP").equals("UZB")) {
						dper.setRezident("1");
					} else {
						dper.setRezident("2");
					}

					if (rs.getString("SENDERDOCTYPE").equals("PASSPORTUZB")) {
						dper.setDoc_id("6");
					} else {
						dper.setDoc_id("4");
					}

					dper.setDoc_series(rs.getString("SENDERDOCSERIES"));
					System.out.println("17 === " + rs.getString("SENDERDOCSERIES"));

					dper.setDoc_number(rs.getString("SENDERDOCNUMBER"));
					System.out.println("18 === " + rs.getString("SENDERDOCNUMBER"));

					dper.setDoc_issue(rs.getString("SENDERDOCISSUER"));
					System.out.println("19 === " + rs.getString("SENDERDOCISSUER"));
					
					dper.setSumma(new BigDecimal(rs.getInt("PAYAMOUNT")).divide(new BigDecimal(100)));
					dper.setSumma2(new BigDecimal(rs.getInt("PAYAMOUNT")).divide(new BigDecimal(100)));					
					dper.setSumma3(new BigDecimal(rs.getInt("PAYAMOUNT")).divide(new BigDecimal(100)));
					dper.setSumma4(new BigDecimal(0).divide(new BigDecimal(100)));			
					dper.setSumma5(new BigDecimal(rs.getInt("PAYAMOUNT")).divide(new BigDecimal(100)));

					dper.setProfit(BigDecimal.valueOf(0)); 
					System.out.println(
							"12 ===  " + BigDecimal.valueOf(0));

					try {

						Date date_isue = sdf.parse(rs.getString("SENDERDOCISSUEDATE"));
						dper.setDoc_date_issue(date_isue);

					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger()
								.error("request.getSenderDocIssueDate === " + rs.getString("SENDERDOCISSUEDATE"));
					}

					dper.setPost_address(rs.getString("SENDERREGADDRESS"));
					System.out.println("21 === " + rs.getString("SENDERREGADDRESS"));

					try {

						Date date_setBirthDay = sdf.parse(rs.getString("SENDERBIRTHDATE"));
						dper.setBirthday(date_setBirthDay);

					} catch (Exception e) {
						e.printStackTrace();
						ISLogger.getLogger()
								.error("request.getSenderBirthDate() === " + rs.getString("SENDERBIRTHDATE"));

					}
					dper.setClient_i(fullName[1]);
					System.out.println("23 === " + fullName[1]);

					dper.setClient_i2(fullName[0]);
					System.out.println("24 === " + fullName[0]);

					dper.setClient_i3(fullName.length > 2 ? fullName[2] : "N");
					System.out.println("25 === " + (fullName.length > 2 ? fullName[2] : "N"));

					dper.setClient_i4("");
					dper.setClient_i5("");
					dper.setClient_i6("");
					dper.setClient_i7("");
					dper.setClient_i8("N");
					System.out.println("26 === При отправке Данные пасспорта не указываются");

					dper.setClient_i9("N");
					System.out.println("27 === При отправке данные пасспорта не указываются");

					dper.setClient_i10("N");
					System.out.println("28 === При отправке данные пасспорта не указываются");

					Date setClient_i11date = sdf.parse(rs.getString("SENDERBIRTHDATE")); // При отправке даты рождения у
																							// получателя не указываются

					dper.setClient_i11date(setClient_i11date);

					dper.setClient_i12("N");
					System.out.println("30 === При отправке данные пасспорта не указываются");

					dper.setClient_grstr("1");
					System.out.println("48 === " + "1");

					account = AccountService.getAccountFromState(c, branch, rs.getString("ABSCLIENTID"), alias);					
					if (account.getAcc_dep().isEmpty()) {
						result = "Проверьте депозитный счет";
						ISLogger.getLogger().error("Dep_Account    :  Проверьте депозитный счет");
					} else {
						dper.setAcc_dep(account.getAcc_dep());
						ISLogger.getLogger().error("Dep_Account    :  " + account.getAcc_dep());
						System.out.println("Dep_Account123 === " + account.getAcc_dep());
					}
					
					Date d = new SimpleDateFormat("dd.MM.yyyy").parse("17.02.2020");
					Date dateNow = new Date();
					String nazn = "", purpose = "";
					String kurs = "";
					StringBuilder valuta = new StringBuilder();
					int purposeIndex = 1;
					
					List<Oper_info> opers = ActionService
							.getPurposes(dper, alias);
					for (Oper_info oper: opers) {
						if (!CheckNull.isEmpty(oper.getNazn())) {
							nazn = oper.getNazn();
						} else if (!CheckNull.isEmpty(oper.getCashsymd())) {
							nazn = oper.getCashsymd();
						}
						if (!CheckNull.isEmpty(oper.getVal_c())
								&& !CheckNull.isEmpty(oper.getVal_d())
								&& !(oper.getVal_d()).equals((oper.getVal_c()))) {
							if (!(oper.getVal_d()).equals("000")) {
								kurs = SumsService.getCourse(oper.getVal_d(),
										CheckNull.d2sql(dper.getV_date()), alias);
								valuta.append("Курс").append(oper.getVal_d())
										.append(kurs);
								switch (oper.getTypeover()) {
								case (1):
									valuta.insert(0, "S=" + dper.getSumma());
									break;
								case (2):
									valuta.insert(0, "S=" + dper.getSumma3());
									break;
								case (3):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (4):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (5):
									valuta.insert(0, "S=" + dper.getSumma4());
									break;
								case (6):
									valuta.insert(0, "S=" + dper.getProfit());
									break;
								case (7):
									valuta.insert(0, "S=" + dper.getCentsumma());
									break;
								}
							}
							if (!(oper.getVal_c()).equals("000")) {
								kurs = SumsService.getCourse(oper.getVal_c(),
										CheckNull.d2sql(dateNow), alias);
								valuta.append("Курс").append(oper.getVal_c())
										.append(kurs);
								switch (oper.getTypeover()) {
								case (1):
									valuta.insert(0, "S=" + dper.getSumma());
									break;
								case (2):
									valuta.insert(0, "S=" + dper.getSumma3());
									break;
								case (3):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (4):
									valuta.insert(0, "S=" + dper.getSumma2());
									break;
								case (5):
									valuta.insert(0, "S=" + dper.getSumma4());
									break;
								case (6):
									valuta.insert(0, "S=" + dper.getProfit());
									break;
								case (7):
									valuta.insert(0, "S=" + dper.getCentsumma());
									break;
								}
							}
						}
						String mbranchCode = TransactionService.getMbranch_code(c);
						purpose = getPurpose(oper.getPurpose(), dper);
						switch (purposeIndex) {
						case (1):
							dper.setPurpose_1(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (2):
							dper.setPurpose_2(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (3):
							dper.setPurpose_3(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (4):
							dper.setPurpose_4(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (5):
							dper.setPurpose_5(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						case (6):
							dper.setPurpose_6(nazn + " " + mbranchCode + " " + purpose
									+ " " + valuta);
							break;
						}
						purposeIndex++;
					}

			       
					dper_infoService.getPurposes(dper, alias); // вместо bank394 незабудьте вставить aliace

					
					ISLogger.getLogger().error("com.is.dper_info.service.TransactionsService.insertNewData(dper, alias)");
					
					Res res = TransactionService.insertNewData(dper, un, pwd, alias, c);

					String info_id = TransactionService.getId(c);

					System.out.println("info_id:  " + info_id);
					System.out.println("res.getcode = " + res.getCode());
					ISLogger.getLogger().error("res.getcode: " +res.getCode());
					ISLogger.getLogger().error("info_id: " +info_id);

					if (res.getCode() == 0) {
                        
						try {
							Map<String, String> map = new HashMap<String, String>();
                            
							map.put("uin", rs.getString("UIN"));
							System.out.println("info_id" + rs.getString("UIN"));
							map.put("operation", "2");
							System.out.println("OPERATION" + (String.valueOf(rs.getInt("OPERATION"))));

							map.put("nciDocId", info_id);

							map.put("nciDocDate",
									new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+05:00").format(new Date()));

							String[] fullNames = rs.getString("SENDERFULLNAME").split(" ");
							System.out.println("SENDERFULLNAME" + fullNames[0] + fullNames[1]);
							String[] fullNames2 = rs.getString("RECEIVERFULLNAME").split(" ");
							System.out.println("RECEIVERFULLNAME" + fullNames2[0] + fullNames2[1]);
							map.put("payerLastName", fullNames[0]);
							map.put("payerFirstName", fullNames[1]);

							try {
								map.put("payerOtherName", fullNames[2]);
							} catch (Exception e) {
								map.put("payerOtherName", "");
							}

							map.put("payeeLastName", fullNames2[0]);
							map.put("payeeFirstName", fullNames2[1]);

							try {
								map.put("payeeOtherName", fullNames2[2]);
							} catch (Exception e) {
								map.put("payeeOtherName", "");
							}

							map.put("absClientId", rs.getString("ABSCLIENTID"));

							map.put("amount", String.valueOf(rs.getInt("PAYAMOUNT")));
							map.put("exp", String.valueOf(rs.getInt("PAYEXP")));
							map.put("cur", String.valueOf(rs.getInt("PAYCUR")));
							// map.put("passportNumber", h.getSenderDocNumber());
							System.out.println("map === " + map);
							ISLogger.getLogger().error("map: " +map);
							boolean confirmStatus = KoronaPayService.regConfirmation(map, alias);
							ISLogger.getLogger().error("confirmStatus: " +confirmStatus);
							System.out.println("confirmStatus === " + confirmStatus);

							if (confirmStatus == true) {
								System.out.println("Операция возврата успешно выполнена");
								result = "Операция возврата успешно выполнена";
								ISLogger.getLogger().error("result: " +result);
							} else {
								System.out.println("Ошибка подтверждения");
								result = "Ошибка подтверждения";
								ISLogger.getLogger().error("result: " +result);
							}
							System.out.println(confirmStatus);
							ISLogger.getLogger().error("confirmStatus: " +confirmStatus);
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Сервер Золотая Корона не отвечает" + "Ошибка: WS Translator не отвечает"
									+ "Обратитесь к Администратору");
							result = "Сервер Золотая Корона не отвечает /n Ошибка: WS Translator не отвечает /n Обратитесь к Администратору";
							ISLogger.getLogger().error("result: " +result);
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			ISLogger.getLogger().error("Exception e: " +e);
			ISLogger.getLogger().error("Ошибка в БД  === " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}

		return result;

	}
	
	

}
