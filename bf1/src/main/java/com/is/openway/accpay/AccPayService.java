
package com.is.openway.accpay;

import java.util.Calendar;
import java.util.HashMap;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.openwayutils.user.Action;
import com.is.openwayutils.utils.RefDataService;
import com.is.openwayutils.utils.FilterField;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.is.openwayutils.utils.Res;
import java.sql.SQLException;
import com.is.ISLogger;

import java.sql.Date;
import com.is.openwayutils.utils.RefData;
import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;


import com.is.openway.PostUtils;
import com.is.openway.XmlUtils;
import com.is.openway.customer.Customer;
import com.is.openway.customer.CustomerFilter;
import com.is.openway.model.AccInfo;
import com.is.openway.model.ActivityPeriod;
import com.is.openway.model.AddContractInfo;
import com.is.openway.model.Application;
import com.is.openway.model.ApplicationContract;
import com.is.openway.model.ApplicationContractCard;
import com.is.openway.model.BaseAddress;
import com.is.openway.model.CardInfo;
import com.is.openway.model.Client;
import com.is.openway.model.ClientAdd;
import com.is.openway.model.ClientIDT;
import com.is.openway.model.ClientInfo;
import com.is.openway.model.ClientInfoIdt;
import com.is.openway.model.ContractAdd;
import com.is.openway.model.ContractIDT;

import com.is.openway.model.Address;
import com.is.openway.model.ApplicationContractAddress;
import com.is.openway.model.ApplicationReIssue;
import com.is.openway.model.ApplicationStatus;
import com.is.openway.model.ApplicationUpdContract;
import com.is.openway.model.DataAdd;
import com.is.openway.model.DataAddress;
import com.is.openway.model.DataCardReIssue;
import com.is.openway.model.DataCardStatus;
import com.is.openway.model.DataContract;
import com.is.openway.model.InformationContract;
import com.is.openway.model.MsgDataAddClient;
import com.is.openway.model.MsgDataAddContract;
import com.is.openway.model.MsgDataAddContractCard;
import com.is.openway.model.MsgDataContractAddress;
import com.is.openway.model.MsgDataInqContract;
import com.is.openway.model.MsgDataReIssue;
import com.is.openway.model.MsgDataUpdContract;
import com.is.openway.model.MsgDataUpdStatus;
import com.is.openway.model.ObjectForContract;
import com.is.openway.model.Phone;
import com.is.openway.model.PhoneList;
import com.is.openway.model.PlasticInfo;
import com.is.openway.model.ProduceCard;
import com.is.openway.model.ProductContract;
import com.is.openway.model.ProductionParms;
import com.is.openway.model.SetStatus;
import com.is.openway.model.UFXMsgAddClient;
import com.is.openway.model.UFXMsgAddContractAcc;
import com.is.openway.model.UFXMsgAddContractAddress;
import com.is.openway.model.UFXMsgAddContractCard;
import com.is.openway.model.UFXMsgInqContract;
import com.is.openway.model.UFXMsgReIssue;
import com.is.openway.model.UFXMsgReqClient;
import com.is.openway.model.UFXMsgReqClientResp;
import com.is.openway.model.InformationReq;
import com.is.openway.model.MsgDataReq;
import com.is.openway.model.ObjectFor;
import com.is.openway.model.Parm;
import com.is.openway.model.ResultDtls;
import com.is.openway.model.Source;
import com.is.openway.model.UFXMsgReqContractResp;
import com.is.openway.model.UFXMsgUpdContractAcc;
import com.is.openway.model.UFXMsgUpdStatus;

import com.is.openway.model.UFXMsgUpdClient;

import com.is.openway.Utils;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import com.is.ConnectionPool;
//import com.rabbitmq.client.AMQP.Confirm.Select;


public class AccPayService {

	private static String psql1;
	private static String psql2;
	private static String msql;
	private static String msql2;
	//private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	private static SimpleDateFormat mdf = new SimpleDateFormat("yyMMdd-HHmmss");
	private static List<RefData> listTypeDocument;
	private static List<RefData> listClientTypeWay4;
	private static List<RefData> listAccBal;
	private static List<RefData> listProductCode1Way4;
	private static List<RefData> listSubProductCode1Way4;
	private static List<RefData> listTax;
	private static List<RefData> listRegion;
	private static List<RefData> listDistr;
	private static HashMap<String, List<RefData>> listDistrByRegion;

	private static List<RefData> listCountry;
	private static List<RefData> listWayCert;
	private static List<RefData> listWayCountries;
	private static List<RefData> listReportFileTemplates;
	
	static {
		AccPayService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
		AccPayService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
		AccPayService.msql = "select * from ";
		AccPayService.msql2 = "v_bf_openway_customer";
	}

	public AccPayService() {
		// this.cvc = new CustomerViewCtrl();
	}

	public static List<Action> getActions(int userid, String user_branch, String alias) {

        List<Action> list = new ArrayList<Action>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection( alias );
                //System.out.println("select a.* from bf_actions a where a.mid=11 and exists(select 'x' from bf_role_actions r,bf_user_roles u where u.roleid=r.roleid and u.userid="+userid+" and r.actionid=a.id)");
                      
                PreparedStatement ps = c.prepareStatement("Select act.*"+

															" From   BF_USER_ROLES   UR,"+
															       " BF_ROLE_ACTIONS RA,"+
															       " bf_actions act"+
															
															" Where  UR.USERID = ?"+
															       " and UR.BRANCH = ?"+
															       " and RA.ROLEID = UR.ROLEID"+
															       " and act.id = ra.actionid"+
															       " and act.deal_id = ra.deal_id"+
															       " and RA.MID = 857"+
															       " and act.mid = RA.MID");
                //ps.setInt(1, state_begin);
                //ps.setInt(2, deal_id);
                ps.setInt(1, userid);
                ps.setString(2, user_branch);
                ResultSet rs = ps.executeQuery();
              
                while (rs.next()) {
                        list.add(new Action(
                                rs.getInt("id"),
                                rs.getInt("mid"),
                                rs.getString("name"),
                                rs.getString("icon"),
                                rs.getInt("deal_id"),
                                rs.getInt("rep_type_id")));

                }
        } catch (SQLException e) {
        	ISLogger.getLogger().error(CheckNull.getPstr(e));
                ISLogger.getLogger().error(CheckNull.getPstr(e));
                
        } finally {
                ConnectionPool.close(c);
        }
        return list;

    }

	public static Res check_allowed_card_action(int deal_group, int deal_id, int action_id, String card, String alias)
    {
		Res res = new Res(0,"");
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		
    		CallableStatement proc = c.prepareCall("{call BF.CHECK_ALLOWED_CARD_ACTION(?, ?, ?, ?) }");
			
			proc.setInt(1, deal_group);
			proc.setInt(2, deal_id);
			proc.setInt(3, action_id);
			proc.setString(4, card);
			
			proc.execute();
			
    	} catch (Exception e) {
    		ISLogger.getLogger().error(CheckNull.getPstr(e));
                res.setCode(-1);
                res.setName(e.getMessage());
        } finally {
                ConnectionPool.close(c);
        }
        return res;
    }
	
	
	public static Res doAction(final String un, final String pw,
			final Customer customer, final int actionid,
			final int utv_actionid, final String alias, final Boolean selfBranch) {
		Res res = null;
		//final SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
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

	public static int getCount(final CustomerFilter filter, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		int n = 0;
		
		return n;
	}
	

	public static List<Customer> getCustomersFl(final int pageIndex, final int pageSize, final CustomerFilter filter, final String alias) {
		System.out.println("1");
		final List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		
		ConnectionPool.close(c);
		return list;
	}

	
	public static void deleteTable(String branch, String filename)
			throws SQLException {
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
		
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}


	public static String get_contract(final String branch,
			final String card_code, final String alias) {
		String res = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			final PreparedStatement proc = c
					.prepareStatement("select BF.GET_CONTRACT_NUMBER(?,?) from dual");
			proc.setString(1, branch);
			proc.setString(2, card_code);
			final ResultSet rs = proc.executeQuery();
			if (rs.next()) {
				res = new String(rs.getString(1));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			return res;
		} finally {
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}

	public static List<RefData> getMfo(final String branch) {
		return (List<RefData>) RefDataService
				.getRefData(
						"select bank_id data, bank_name label from S_mfo order by bank_id",
						branch);
	}

	public static String getNewestAccount(String clientId, String branch,
			int firstOrd, int lastOrd) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String account = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id from (SELECT *\n"
					+ "  FROM Account\n" + " where branch = ?\n"
					+ "   and acc_bal like '22618'\n"
					+ "   and currency = '000'\n" + "   and client = ?\n"
					+ "   and id_order >= ?\n" + "   and id_order < ?\n"
					+ "   and state = '2'\n" + "   order by id_order desc)\n"
					+ "   where rownum = 1");
			ps.setString(1, branch);
			ps.setString(2, clientId);
			ps.setInt(3, firstOrd);
			ps.setInt(4, lastOrd);
			rs = ps.executeQuery();
			if (rs.next()) {
				account = rs.getString(1);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return account;

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
			if (rs.next()) {
				isAccessGranted = rs.getInt(1) > 0 ? true : false;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return isAccessGranted;
	}

	public static String getReportTemplate(int deal_id, int action_id, String alias) {
		String v_res="";
		List<RefData> tmp = getListReportFileTemplates(alias);
		
		for (int i = 0; i < tmp.size(); i++) {
			
			if ( tmp.get(i).getData() == ""+deal_id+"-"+action_id || tmp.get(i).getData().equals(""+deal_id+"-"+action_id)) {
				v_res=tmp.get(i).getLabel();
				break;
			} 
			}
		return v_res;
	}
	public static String getUser_name(int user_id, String branch, String alias)
    {
            String res = null;
            Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);

                PreparedStatement ps = c.prepareStatement("select user_name_trans from bf_users_fullname_transleat where user_id = ? and branch = ?");
                ps.setString(1, user_id+"");
                ps.setString(2, branch);
                ResultSet rs = ps.executeQuery();
                //rs.next();
                if (rs != null) {
                	  while (rs.next()) {
                		  res = rs.getString("user_name_trans");
                	  }
                }
        } catch (SQLException e) {
        	e.printStackTrace();
                com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
            return res;
    }
	
	/* справочниклар бошланиши, spravochniklar */
	public static List<RefData> getCountry(final String alias) {
		if (listCountry==null || listCountry.size()==0)
		 listCountry = (List<RefData>) Utils
				.getRefData(
						"select s.code_str data, s.name label from S_STR s where s.act='A' order by 2",
						alias);
		return listCountry ;
	}

	public static List<RefData> getDistr(final String alias) {
		if (listDistr==null || listDistr.size()==0)
		listDistr= (List<RefData>) Utils
				.getRefData(
						"select distr data, distr_name label from s_distr where act='A' order by 2",
						alias);
		 return listDistr;
	}
	
	public static List<RefData> getDistrByRegion(String region_id, final String alias) {

		if (listDistrByRegion == null ) 
			listDistrByRegion = new HashMap<String, List<RefData>>();
		if ( !listDistrByRegion.containsKey(region_id) ) {
			List<RefData> lst = (List<RefData>) Utils
			.getRefData(
					"select distr data, distr_name label from s_distr where region_id=? and act='A' order by 2", region_id,
					alias);
			
		   listDistrByRegion.put(region_id, lst);
		}
		return listDistrByRegion.get(region_id);
	}

	public static List<RefData> getRegion(final String alias) {
		if (listRegion==null || listRegion.size()==0)
		listRegion= (List<RefData>) Utils
				.getRefData(
						"select region_id data, region_nam label from s_region where act='A' order by 2",
						alias);
		return listRegion;
	}

	public static List<RefData> getTax(final String branch) {
		if (listTax==null || listTax.size()==0)
		listTax = (List<RefData>) Utils
				.getRefData(
						"select gni_id data, name_gni label from s_gni where act <> 'Z' order by 2",
						branch);
		return listTax;
	}

	public static List<RefData> getType_document(final String alias) {
		if (listTypeDocument == null || listTypeDocument.size() == 0)
			listTypeDocument = (List<RefData>) Utils
					.getRefData(
							"select s.code_cert data, s.name_cert label from S_CERTIFICATE s where act='A' order by 2",
							alias);
		return listTypeDocument;

	}

	public static List<RefData> getClient_type_way4(final String alias) {
		if (listClientTypeWay4==null || listClientTypeWay4.size()==0)
		listClientTypeWay4 = (List<RefData>) Utils
				.getRefData(
						"select 'PR' data,	'‘изическое лицо - резидент(PR)' label, 0 ord from dual union all "
								+ "select 'PNR' data,	'‘изическое лицо - нерезидент(PNR)' label, 1 ord from dual union all "
								+ "select 'RPR' data,	'‘изическое лицо, пенсионер(RPR)' label, 2 ord from dual order by ord",
						alias);
		return listClientTypeWay4;
	}

	public static List<RefData> getWayCertificates(final String alias) {
		if (listWayCert==null || listWayCert.size()==0)
			listWayCert = (List<RefData>) Utils
				.getRefData(
						"select s.CODE_CERT_NCI data, s.CODE_CERT_WAY label from BF_OPENWAY_SS_CERT s order by 1",
						alias);
		return listWayCert ;
	}
	public static List<RefData> getWayCountries(final String alias) {
		if (listWayCountries==null || listWayCountries.size()==0)
			listWayCountries = (List<RefData>) Utils
				.getRefData(
						"select s.CODE_str data, s.alpha_3 label from s_str s order by decode(s.CODE_str, '860','000', s.CODE_str)",
						alias);
		return listWayCountries ;
	}
	public static List<RefData> getAcc_bal(final String alias) {
		if (listAccBal==null || listAccBal.size()==0)
		listAccBal = (List<RefData>) Utils
				.getRefData(
						"select '22618' data,	'22618' label, 0 ord from dual",
						alias);
		return listAccBal;
	}	
	
	public static List<RefData> getProduct_code1_way4(final String alias) {
		if (listProductCode1Way4==null || listProductCode1Way4.size()==0)
			listProductCode1Way4 = (List<RefData>) Utils
				.getRefData(
						"select 'ISS_DB_USD' data,	'ISS_DB_USD' label, 0 ord from dual",
						alias);
		return listProductCode1Way4;
	}
	
	public static List<RefData> getSubProduct_code1_way4(final String alias) {
		if (listSubProductCode1Way4==null || listSubProductCode1Way4.size()==0)
			listSubProductCode1Way4 = (List<RefData>) Utils
				.getRefData(
						"select 'VISA_CLASSIC_USD' data, 'VISA_CLASSIC_USD' label, 0 ord from dual",
						alias);
		return listSubProductCode1Way4;
	}
	public static List<RefData> getListReportFileTemplates(final String alias) {
		if (listReportFileTemplates==null || listReportFileTemplates.size()==0)
			listReportFileTemplates = (List<RefData>) Utils
				.getRefData(
						"select s.deal_id||'-'||s.action_id data, s.report label from BF_TIETO_TR_ACTION_REPORT s",
						alias);
		return listReportFileTemplates ;
	}
	
	/* справочниклар тугаши*/
   
	public static String getWayCertCode(String nciCertCode, String alias) {
		String v_res="OTHER";
		List<RefData> tmp = getWayCertificates(alias);
		for (int i = 0; i < tmp.size(); i++) {
			  //System.out.println(i);
			if ( tmp.get(i).getData() == nciCertCode || tmp.get(i).getData().equals(nciCertCode)) {
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
	
	public static Integer getSeqApplRegNumber()  {
           Connection c = null;
           PreparedStatement ps = null;
           ResultSet rs = null;
           Integer v_res = 0;
           try {
                   c = ConnectionPool.getConnection();
                   ps = c.prepareStatement("SELECT seq_bf_appl_regnumber.NEXTVAL id FROM DUAL");
                   rs = ps.executeQuery();
                   if (rs.next()) {
                	   v_res=rs.getInt("id");
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

	public static Integer getSeqCardRbsNumber()  {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer v_res = 0;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT seq_bf_card_rbsnumber.NEXTVAL id FROM DUAL");
                rs = ps.executeQuery();
                if (rs.next()) {
             	   v_res=rs.getInt("id");
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

	public static UFXMsgAddContractAddress makeAddSMS(AccInfo acc, String phoneMobile, String alias) {
		UFXMsgAddContractAddress addSMS = new UFXMsgAddContractAddress();
		addSMS.setMsgId("ADDSMS-CONTRACT-ACC-01142-001");
		Source src = new Source();
		addSMS.setSource(src);

		MsgDataContractAddress msgData = new MsgDataContractAddress();
		ApplicationContractAddress app = new ApplicationContractAddress();
			
		app.setRegNumber("SMSONCARD-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+ String.format("%04d", getSeqApplRegNumber()));
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contrIdt1 = new ContractIDT();
		contrIdt1.setRBSNumber(acc.getCbsNumber()); /*<RBSNumber>0039422618840599219938002</RBSNumber>*/
		Client clt = new Client();
		ClientInfo cltInfo = new ClientInfo();
		cltInfo.setClientNumber(acc.getClientNumber());
		cltInfo.setSocialNumber(acc.getSocialNumber());
		clt.setClientInfo(cltInfo);
		contrIdt1.setClientObject(clt);
		objFor.setContractIDT(contrIdt1);
		app.setObjectFor(objFor);
		
		DataAddress dataObj = new DataAddress();
		Address addr = new Address();
		addr.setAddressType("SMS");
		addr.setPostalCode(phoneMobile);
		addr.setAddressLine1("SMS Notification On (Auto)");
		
		dataObj.setAddress(addr);
		app.setData(dataObj);
		msgData.setApplication(app);
		addSMS.setMsgDataReq(msgData);
		return addSMS;
	}
	
	public static UFXMsgInqContract makeClassForBalance (String cardRbsNumber) {
		
		UFXMsgInqContract bal = new UFXMsgInqContract();
		bal.setMsgId("BALANCEINFO-01142-001");
		Source src = new Source();
		bal.setSource(src);
		
		MsgDataInqContract msgData = new MsgDataInqContract();
		InformationContract inf = new InformationContract();
		inf.setRegNumber("BALANCEINFO-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+ String.format("%04d", getSeqApplRegNumber()));
		
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contrIdt1 = new ContractIDT();
		contrIdt1.setRBSNumber(cardRbsNumber); 
		objFor.setContractIDT(contrIdt1);
		inf.setObjectFor(objFor);
		
		ResultDtls rdtl = new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Balance");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		inf.setResultDtls(rdtl);
		
		msgData.setApplication(inf);
		bal.setMsgDataReq(msgData);
		return bal;

	}

	public static UFXMsgUpdStatus makeLockCard(CardInfo card, String statusCode, String statusComment) {
		UFXMsgUpdStatus updStatus = new UFXMsgUpdStatus();
		updStatus.setMsgId("CHANGECARDSTATE-01142-001"); 
		Source src = new Source();
		updStatus.setSource(src);

		MsgDataUpdStatus msgData = new MsgDataUpdStatus();
		ApplicationStatus app = new ApplicationStatus();
		app.setRegNumber("CARDSTATE-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+ String.format("%04d", getSeqApplRegNumber()));
		ResultDtls rdtl=new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("AcceptRq");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		
		
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contrIdt1 = new ContractIDT();
		contrIdt1.setRBSNumber(card.getRbsNumberIbs()); /*<RBSNumber>9058-P-083262-0000009</RBSNumber>*/
		Client clt = new Client();
		ClientInfo cltInfo = new ClientInfo();
		cltInfo.setClientNumber(card.getBranch() + card.getClient_id());
		cltInfo.setSocialNumber(card.getSocialNumber());
		clt.setClientInfo(cltInfo);
		contrIdt1.setClientObject(clt);
		objFor.setContractIDT(contrIdt1);
		app.setObjectFor(objFor);
		
		DataCardStatus dataObj = new DataCardStatus();
		SetStatus setStatus = new SetStatus();
		setStatus.setStatusCode(statusCode);
		setStatus.setStatusComment(statusComment);
		
		dataObj.setSetStatus(setStatus);
		app.setData(dataObj);
		msgData.setApplication(app);
		updStatus.setMsgDataReq(msgData);
		return updStatus;
	}
	
	public static UFXMsgReIssue makeReIssueCard(CardInfo card) {
	
		UFXMsgReIssue updStatus = new UFXMsgReIssue();
		updStatus.setMsgId("REISSUENSE-01142-001"); 
		Source src = new Source();
		updStatus.setSource(src);

		MsgDataReIssue msgData = new MsgDataReIssue();
		ApplicationReIssue app = new ApplicationReIssue();
		app.setRegNumber("REISSUENSE-058-"+mdf.format(Calendar.getInstance().getTime())+"-"+ String.format("%04d", getSeqApplRegNumber()));
		ResultDtls rdtl=new ResultDtls();
		Parm parm = new Parm();
		parm.setParmCode("Response");
		parm.setValue("Y");
		rdtl.setParmObject(parm);
		app.setResultDtls(rdtl);
		
		ObjectForContract objFor = new ObjectForContract();
		ContractIDT contrIdt1 = new ContractIDT();
		contrIdt1.setRBSNumber(card.getCbsNumber());
		Client clt = new Client();
		ClientInfo cltInfo = new ClientInfo();
		cltInfo.setClientNumber(card.getBranch() + card.getClient_id());
		cltInfo.setSocialNumber(card.getSocialNumber());
		clt.setClientInfo(cltInfo);
		contrIdt1.setClientObject(clt);
		objFor.setContractIDT(contrIdt1);
		app.setObjectFor(objFor);
		
		DataCardReIssue dataObj = new DataCardReIssue();
		ProduceCard produceCard = new ProduceCard();
		produceCard.setProductionEvent("NLOST");		
		
		dataObj.setProduceCard(produceCard);
		app.setData(dataObj);
		msgData.setApplication(app);
		updStatus.setMsgDataReq(msgData);
		return updStatus;
	}

	public static Res activateSmsService(AccInfo acc, String phoneNumber) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            ISLogger.getLogger().error("insert/update bf_openway_sms");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            	"begin "+
                " update bf_openway_sms set phone_number=? "+
                "  where branch=? and id_client=? and rbs_number=?; "+
                " if sql%rowcount=0 then "+
                "  insert into bf_openway_sms(phone_number, branch, id_client, rbs_number) "+
                "  values (?,?,?,?); "+
                " end if; "+
                " insert into bf_openway_sms_hist(phone_number, branch, id_client, rbs_number, act ) "+ 
                " values (?,?,?,?,'A');  "+
                "end; ");
            ps.setString(1, phoneNumber);
            ps.setString(2, acc.getBranch());
            ps.setString(3, acc.getClient());
            ps.setString(4, acc.getCbsNumber());
            ps.setString(5, phoneNumber);
            ps.setString(6, acc.getBranch());
            ps.setString(7, acc.getClient());
            ps.setString(8, acc.getCbsNumber());
            ps.setString(9, phoneNumber);
            ps.setString(10, acc.getBranch());
            ps.setString(11, acc.getClient());
            ps.setString(12, acc.getCbsNumber());
            
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

    public static Res deActivateSmsService(AccInfo acc, String phoneNumber) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            ISLogger.getLogger().error("delete bf_openway_sms");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            	"begin "+
            	" delete from bf_openway_sms "+ 
		        " where branch=? and id_client=? and rbs_number=? and (phone_number=? or phone_number=phone_number); "+
            	" if sql%rowcount!=0 then   "+
		        "  insert into bf_openway_sms_hist(branch, id_client, rbs_number, phone_number, act ) "+ 
		        "  values (?,?,?,?,'Z');   "+
		        " end if; "+
	            "end;");
            
            ps.setString(1, acc.getBranch());
            ps.setString(2, acc.getClient());
            ps.setString(3, acc.getCbsNumber());
            ps.setString(4, phoneNumber);
            ps.setString(5, acc.getBranch());
            ps.setString(6, acc.getClient());
            ps.setString(7, acc.getCbsNumber());
            ps.setString(8, phoneNumber);
            
            ps.execute();
            
            c.commit();
            //bool = true;
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

    public static Res lockCard(CardInfo card, String status_code, String status_comment) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            ISLogger.getLogger().error("insert/update bf_openway_lock");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
            	"begin "+
                " update bf_openway_lock set status_code=?, status_comment=? "+
                "  where branch=? and id_client=? and rbs_number=?; "+
                " if sql%rowcount=0 then "+
                "  insert into bf_openway_lock(status_code, status_comment, branch, id_client, rbs_number) "+
                "  values (?,?,?,?,?); "+
                " end if; "+
                " insert into bf_openway_lock_hist(status_code, status_comment, branch, id_client, rbs_number, act ) "+ 
                " values (?,?,?,?,?,'A');  "+
                "end; ");
            ps.setString(1, status_code);
            ps.setString(2, status_comment);
            ps.setString(3, card.getBranch());
            ps.setString(4, card.getClient_id());
            ps.setString(5, card.getRbsNumberIbs());
            ps.setString(6, status_code);
            ps.setString(7, status_comment);
            ps.setString(8, card.getBranch());
            ps.setString(9, card.getClient_id());
            ps.setString(10, card.getRbsNumberIbs());
            ps.setString(11, status_code);
            ps.setString(12, status_comment);
            ps.setString(13, card.getBranch());
            ps.setString(14, card.getClient_id());
            ps.setString(15, card.getRbsNumberIbs());
            
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

    public static Res unLockCard(CardInfo card, String status_code, String status_comment) {
		Res res = null;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            ISLogger.getLogger().error("insert/update bf_openway_lock");
            c = ConnectionPool.getConnection();
   
            ps = c.prepareStatement(
              	"begin "+
               	" delete from bf_openway_lock "+ 
   		        " where branch=? and id_client=? and rbs_number=? and (status_code=? or status_code=status_code) "+
   		        " and (status_comment=? or status_comment=status_comment); "+
               	" if sql%rowcount!=0 then   "+
   		        "  insert into bf_openway_lock_hist(branch, id_client, rbs_number, status_code, status_comment, act ) "+ 
   		        "  values (?,?,?,?,?,'Z');   "+
   		        " end if; "+
   	            "end;");
            ps.setString(1, card.getBranch());
            ps.setString(2, card.getClient_id());
            ps.setString(3, card.getRbsNumberIbs());
            ps.setString(4, status_code);
            ps.setString(5, status_comment);
            ps.setString(6, card.getBranch());
            ps.setString(7, card.getClient_id());
            ps.setString(8, card.getRbsNumberIbs());
            ps.setString(9, status_code);
            ps.setString(10, status_comment);
            
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

	
}