package com.is.clientcrm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.bpri.utils.Utils;
import com.is.callcentre.ISLogger;
import com.is.clientcrm.digid.ResponseDigId;
import com.is.clientcrm.res_gsp.Request_Gsp;
import com.is.clientcrm.res_gsp.Res_Gsp;
import com.is.clientcrm.res_nibbd.ResNibbd;
import com.is.clientcrm.utils.DbUtils;
import com.is.clientcrm.utils.Util;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

import oracle.jdbc.internal.OracleTypes;

public class ClientAService {
	private String alias;
	private String un;
	private String pw;
	private final static Logger logger = Logger.getLogger(ClientAService.class);
	private ClientAFilter filter;
	private int count;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private static final String INSER_CLIENT_DESC = "INSERT INTO CLIENT_DESC "
			+ "(LAST_NAME_CYR,FIRST_NAME_CYR,PATRONYMIC_CYR," + "CODE_TYPE,PASS_PLACE_REGION,PASS_PLACE_DISTRICT,"
			+ "POST_ADDRESS_STREET,POST_ADDRESS_HOUSE," + "POST_ADDRESS_FLAT,POST_ADDRESS_QUARTER, "
			+ "CODE_COUNTRY_ADR, post_address_country,BRANCH,ID_CLIENT, " + "capital_inform, capital_currency) "
			+ "VALUES(?,?,?, ?,?,?, ?,?,?, ?,?,?,?,?,?,?)";

	private static final String UPDATE_CLIENT_DESC = "UPDATE CLIENT_DESC " + "SET LAST_NAME_CYR = ?,"
			+ "FIRST_NAME_CYR = ?," + "PATRONYMIC_CYR = ?," + "CODE_TYPE = ?," + "PASS_PLACE_REGION = ?, "
			+ "PASS_PLACE_DISTRICT = ?, " + "POST_ADDRESS_STREET = ?," + "POST_ADDRESS_HOUSE = ?,"
			+ "POST_ADDRESS_FLAT = ?," + "POST_ADDRESS_QUARTER = ?, " + "CODE_COUNTRY_ADR = ?,"
			+ "post_address_country = ? " + "WHERE branch=? and ID_CLIENT = ?";

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM v_client ";
	
	private static String sql_upd = "update /*+ INDEX(client_p XUK_CLIENT_P) */ client_p set date_open_nibbd=?"
			+" where branch=? and id=?";

	private static final int DEAL_ID = 1;
	public static final int ACTION_OPEN = 1;
	public static final int ACTION_CONFIRM = 2;
	public static final int ACTION_CLOSE = 3;
	public static final int ACTION_CHANGE_NIBBD = 4;

	private ClientAService(String un, String pw, String alias) {
		this.alias = alias;
		this.un = un;
		this.pw = pw;
		// this.personMapService = personMapService;
	}

	public static ClientAService getInstance(String un, String pw, String alias) {
		return new ClientAService(un, pw, alias);
	}

	private ClientAService(String alias) {
		this.alias = alias;
	}

	private ClientAService() {
	}

	public static ClientAService getInstance() {
		return new ClientAService();
	}

	public static ClientAService getInstance(String alias) {
		return new ClientAService(alias);
	}

	@SuppressWarnings("resource")
	public Res doAction(ClientA clientA, int actionid) {
		Res res = null;
        
		System.out.println("pinfl: "+clientA.getP_pinfl());
		System.out.println("code_nation: "+clientA.getP_code_nation());
		System.out.println("code_gender: "+clientA.getP_code_gender());
		// SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement getParam = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isIP = clientA.getCode_type().equals(ClientUtil.CODE_TYPE_IP);
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			res = new Res(0, "");
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();

			cs = c.prepareCall(SqlScripts.SET_PARAM.getSql());
			acs = c.prepareCall(SqlScripts.DO_ACTION.getSql());
			getParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
			getParam.execute();

			getParam = c.prepareCall(SqlScripts.GET_PARAM_ID.getSql());
			getParam.registerOutParameter(1, Types.BIGINT);
			if (clientA.getState() != null && !clientA.getState().equals("0")) {
				cs.setString(1, "ID");
				cs.setLong(2, clientA.getId());
				cs.execute();
			}

			if (clientA.getJ_code_head_organization() == null) {
				clientA.setJ_code_head_organization("0");
			}
			if (clientA.getJ_inn_head_organization() == null) {
				clientA.setJ_inn_head_organization("0");
			}
			if (clientA.getJ_code_sector() == null) {
				clientA.setJ_code_sector("0");
			}
			for (Field field : ClientA.class.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
					continue;
				}
				field.setAccessible(true);
				if (field.getName().equals("id") && (clientA.getState() == null || clientA.getState().equals("0"))
						|| field.getName().equals("director") || field.getName().equals("accountant")
						|| field.getName().equals("individualEnterpreneur")) {
					continue;
				}
				cs.setString(1, field.getName().toUpperCase());
				Object obj = field.get(clientA);
				//ISLogger.getLogger().error("obj_clientA: "+obj.toString()+"----"+clientA.toString());
				// logger.error("ClientA Field Name " + field.getName() + " " +
				// obj);
				if (obj instanceof Date)
					cs.setDate(2, Util.sqlDate((Date) obj));
				else {
					if (field.getName().equals("j_short_name")) {
						cs.setString(2, clientA.substringShortName());
					} else if (isIP && field.getName().equals("name")) {
						String fullName = String.format("YATT %s %s %s", clientA.getP_family(),
								clientA.getP_first_name(), clientA.getP_patronymic());
						cs.setString(2, fullName);
					} else {
						cs.setObject(2, obj);
					}
				}
				cs.execute();
			}

			acs.setInt(1, DEAL_ID);
			acs.setInt(2, Integer.parseInt(clientA.getSign_registr()));
			acs.setInt(3, actionid);
			acs.execute();

			if (actionid == ACTION_OPEN ) {

				getParam.execute();
				clientA.setId(getParam.getLong(1));
				String idClient = null;
				ps = c.prepareStatement("select id_client,date_open from v_client where branch=? and id=?");
				ps.setString(1, clientA.getBranch());
				ps.setLong(2, clientA.getId());
				rs = ps.executeQuery();
				if (rs.next()) {
					idClient = rs.getString(1);
					clientA.setDate_open(rs.getDate(2));
				}
				clientA.setId_client(idClient);
				res.setName(idClient);
			} else
				res.setName(clientA.getId_client());

			c.commit();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			if (e.getMessage() != null)
				res = new Res(-1, e.getMessage());
			else
				res = new Res(-1, CheckNull.getPstr(e));
			DbUtils.rollback(c);
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(getParam);
			DbUtils.closeStmt(acs);
			DbUtils.closeStmt(cs);
		}
		return res;
	}
     
	public String getNextValForSapAction(Connection c) throws SQLException {
		String id = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = c
					.prepareStatement("SELECT CONCAT('NEW',LPAD(TO_CHAR(SEQ_CLIENT_J.NEXTVAL),5,'0')) FROM DUAL");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				id = resultSet.getString(1);
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(resultSet);
			DbUtils.closeStmt(preparedStatement);
		}
		return id;
	}

	private void addToClientP(Connection c, ClientA client) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update client_p set family=?, FIRST_NAME=?, "
					+ "PATRONYMIC=?, code_tax_org=?, number_tax_registration=?, "
					+ "code_nation=?, code_birth_region=?, code_birth_distr=?, " + "inps = ?"
					+ "where id=? and branch=?");
			ps.setString(1, client.getP_family());
			ps.setString(2, client.getP_first_name());
			ps.setString(3, client.getP_patronymic());
			ps.setString(4, client.getP_code_tax_org());
			ps.setString(5, client.getP_number_tax_registration());
			ps.setString(6, client.getP_code_nation());
			ps.setString(7, client.getP_code_birth_region());
			ps.setString(8, client.getP_code_birth_distr());
			ps.setString(9, client.getP_inps());
			ps.setString(10, client.getId_client());
			ps.setString(11, client.getBranch());
			ps.executeUpdate();
		} finally {
			DbUtils.closeStmt(ps);
		}

	}

	private void addToClientDesc(Connection c, ClientA client) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select count(*) from client_desc where id_client=? and branch=?");
			ps.setString(1, client.getId_client());
			ps.setString(2, client.getBranch());

			rs = ps.executeQuery();
			boolean update = false;
			if (rs.next()) {
				update = rs.getInt(1) > 0;
			}
			ps.close();

			ps = c.prepareStatement(update ? UPDATE_CLIENT_DESC : INSER_CLIENT_DESC);

			ps.setString(1, client.getP_last_name_cyr());
			ps.setString(2, client.getP_first_name_cyr());
			ps.setString(3, client.getP_patronymic_cyr());
			ps.setString(4, client.getCode_type());
			ps.setString(5, client.getP_pass_place_region());
			ps.setString(6, client.getP_pass_place_district());
			ps.setString(7, client.getP_post_address_street());
			ps.setString(8, client.getP_post_address_house());
			ps.setString(9, client.getP_post_address_flat());
			ps.setString(10, client.getP_post_address_quarter());
			ps.setString(11, client.getAddressCountry());
			ps.setString(12, client.getPost_address_country());
			ps.setString(13, client.getBranch());
			ps.setString(14, client.getId_client());
			if (!update) {
				ps.setString(15, client.getCapital_inform());
				ps.setString(16, client.getCapital_currency());
			}
			ps.executeUpdate();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}

	}

	private String stubNibbd(Connection c, CallableStatement setParam, ClientA client, int action) throws Exception {
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String idClient = client.getId_client();
		String account = null;
		try {

			if (client.getId_client() != null && client.getId_client().startsWith("NEW")) {
				/*
				 * ps = c.prepareStatement( "select id_client from " +
				 * "(select id_client from v_client_nibbd_stub where id_Client not like 'NEW%' "
				 * + "and id_Client not like 'I%' " +
				 * "and code_type not in ('07', '08') order by id_client desc) where rownum <= 1"
				 * );
				 */
				ps = c.prepareStatement("select id_client from v_client_nibbd_stub");
				rs = ps.executeQuery();

				if (rs.next()) {
					idClient = rs.getString(1);
					int numericId = Integer.parseInt(idClient) + 1;
					idClient = String.format("%08d", numericId);
				}
				ps.close();
				rs.close();
			}
			// ISLogger.getLogger().error(":::::::::::::::::::::::::: stubNibbd
			// idClient = " + idClient);
			account = "202080009" + idClient + "001";
			// ISLogger.getLogger().error(":::::::::::::::::::::::::: stubNibbd
			// account = " + account);
			ps = c.prepareStatement("select kernel.fckey(?,?) from dual");
			ps.setString(1, account);
			ps.setString(2, client.getBranch());
			rs = ps.executeQuery();
			if (rs.next()) {
				account = rs.getString(1);
			}

			setParam.setString(1, "ID");
			setParam.setLong(2, client.getId());
			setParam.execute();
			setParam.setString(1, "ID_CLIENT");
			setParam.setString(2, idClient);
			setParam.execute();
			setParam.setString(1, "NAME");
			setParam.setString(2, client.getName());
			setParam.execute();
			setParam.setString(1, "J_ACCOUNT");
			setParam.setString(2, account);
			setParam.execute();

			cs = c.prepareCall("{call kernel.doAction(1,1,?)}");
			if (action == ACTION_CONFIRM) {
				cs.setInt(1, 7);
			}
			if (action == ACTION_CHANGE_NIBBD) {
				cs.setInt(1, 9);
			}
			cs.execute();
			if (action == ACTION_CONFIRM) {
				client.setId_client(idClient);
				client.setJ_account(account);
			}

			/*
			 * SapHandler.makeSapRequest(client, action); if (action ==
			 * ACTION_CONFIRM) { client.setState("2"); }
			 */
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(cs);
		}
		return idClient;
	}

	/*
	 * public int[] getAvailableModules(int userId) { Connection c = null;
	 * PreparedStatement ps = null; CallableStatement cs = null; ResultSet rs =
	 * null; int roleId; int[] modules = null; try { c =
	 * ConnectionPool.getConnection(alias); cs =
	 * c.prepareCall(SqlScripts.INFO_INIT.getSql()); cs.execute(); ps =
	 * c.prepareStatement(
	 * "select roleid from bf_user_roles where userid=? and branch=info.getbranch"
	 * ); ps.setInt(1, userId); rs = ps.executeQuery(); if (rs.next()) { roleId
	 * = rs.getInt("roleid"); List<Module> list =
	 * UserService.getModuleInRole(roleId, alias); if (!list.isEmpty()) {
	 * modules = new int[list.size()]; for (int i = 0; i < list.size(); i++) {
	 * modules[i] = list.get(i).getId(); } }
	 * 
	 * } } catch (SQLException e) {
	 * ISLogger.getLogger().error(CheckNull.getPstr(e)); } finally {
	 * DbUtils.closeStmt(ps); ConnectionPool.close(c); }
	 * 
	 * return modules; }
	 */

	public static void main(String[] args) {
		String idClient = "04293827";
		int numericId = Integer.parseInt(idClient) + 1;
		System.out.println(String.format("%08d", numericId));
	}

	public static List<FilterField> getFilterFields(ClientAFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client=?", filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getClientIds())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client in (?)", filter.getClientIds()));
		}
		// if (!CheckNull.isEmpty(filter.getName())) {
		// flfields.add(new FilterField(DbUtils.getCond(flfields) + "name like
		// upper(?)", filter.getName()));
		// }
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "(upper(name) like ? or upper(j_short_name) like ?)",
							filter.getName()));
			// yeshyo raz delayu. parametr doljen peredatsya dva raz
			flfields.add(new FilterField("", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_country=?", filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_type=?", filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_resident=?", filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_subject=?", filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "code_form=?", filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 1
				&& !CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_open=?", Util.sqlDate(filter.getDate_open())));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 2
				&& !CheckNull.isEmpty(filter.getDate_open()) && !CheckNull.isEmpty(filter.getDate_open1())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_open between ? and ?", filter.getDate_open()));
			flfields.add(new FilterField("", filter.getDate_open1()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 1
				&& !CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_close=?", Util.sqlDate(filter.getDate_close())));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 2
				&& !CheckNull.isEmpty(filter.getDate_close()) && !CheckNull.isEmpty(filter.getDate_close1())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "date_close between ? and ?", filter.getDate_close()));
			flfields.add(new FilterField("", filter.getDate_close1()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getKod_err())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "kod_err=?", filter.getKod_err()));
		}
		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "file_name=?", filter.getFile_name()));
		}
		/*
		 * if(!CheckNull.isEmpty(filter.getJ_short_name())){ flfields.add(new
		 * FilterField(DbUtils.getCond(flfields)+
		 * "j_short_name=?",filter.getJ_short_name())); }
		 */
		if (!CheckNull.isEmpty(filter.getJ_place_regist_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_place_regist_name=?",
					filter.getJ_place_regist_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_date_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_date_registration=?",
					Util.sqlDate(filter.getJ_date_registration())));
		}
		if (!CheckNull.isEmpty(filter.getJ_number_registration_doc())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_registration_doc=?",
					filter.getJ_number_registration_doc()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_tax_org())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_tax_org=?", filter.getJ_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getJ_number_tax_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_number_tax_registration like ?",
					filter.getJ_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_sector())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_sector=?", filter.getJ_code_sector()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_organ_direct())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_organ_direct=?",
					filter.getJ_code_organ_direct()));
		}
		if (!CheckNull.isEmpty(filter.getJ_type_activity())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_type_activity=?", filter.getJ_type_activity()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_head_organization())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_head_organization=?",
					filter.getJ_code_head_organization()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_class_credit())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_class_credit=?",
					filter.getJ_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_name=?", filter.getJ_director_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passport())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passport=?",
					filter.getJ_director_passport()));
		}
		if (!CheckNull.isEmpty(filter.getJ_chief_accounter_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_name=?",
					filter.getJ_chief_accounter_name()));
		}
		if (!CheckNull.isEmpty(filter.getJ_chief_accounter_passport())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_chief_accounter_passport=?",
					filter.getJ_chief_accounter_passport()));
		}
		if (!CheckNull.isEmpty(filter.getJ_code_bank())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_code_bank=?", filter.getJ_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getJ_account())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_account=?", filter.getJ_account()));
		}
		if (!CheckNull.isEmpty(filter.getJ_post_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_post_address=?", filter.getJ_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_phone())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_phone=?", filter.getJ_phone()));
		}
		if (!CheckNull.isEmpty(filter.getJ_fax())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_fax=?", filter.getJ_fax()));
		}
		if (!CheckNull.isEmpty(filter.getJ_email())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_email=?", filter.getJ_email()));
		}
		if (!CheckNull.isEmpty(filter.getJ_sign_trade())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_sign_trade=?", filter.getJ_sign_trade()));
		}
		if (!CheckNull.isEmpty(filter.getJ_opf())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_opf=?", filter.getJ_opf()));
		}
		if (!CheckNull.isEmpty(filter.getJ_soato())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_soato=?", filter.getJ_soato()));
		}
		if (!CheckNull.isEmpty(filter.getJ_okpo())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_okpo=?", filter.getJ_okpo()));
		}
		if (!CheckNull.isEmpty(filter.getJ_inn_head_organization())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_inn_head_organization=?",
					filter.getJ_inn_head_organization()));
		}
		if (!CheckNull.isEmpty(filter.getJ_region())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_region=?", filter.getJ_region()));
		}
		if (!CheckNull.isEmpty(filter.getJ_distr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_distr=?", filter.getJ_distr()));
		}
		if (!CheckNull.isEmpty(filter.getJ_small_business())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "j_small_business=?", filter.getJ_small_business()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_type_document=?",
					filter.getJ_director_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_serial=?",
					filter.getJ_director_passp_serial()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_number())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_number=?",
					filter.getJ_director_passp_number()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_date_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_reg=?",
					filter.getJ_director_passp_date_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_place_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_place_reg=?",
					filter.getJ_director_passp_place_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_passp_date_end())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_passp_date_end=?",
					filter.getJ_director_passp_date_end()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_code_citizenship=?",
					filter.getJ_director_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birthday=?",
					filter.getJ_director_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_birth_place=?",
					filter.getJ_director_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getJ_director_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_director_address=?",
					filter.getJ_director_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_type_document=?",
					filter.getJ_accountant_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_serial=?",
					filter.getJ_accountant_passp_serial()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_number())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_number=?",
					filter.getJ_accountant_passp_number()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_reg=?",
					filter.getJ_accountant_passp_date_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_place_reg())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_place_reg=?",
					filter.getJ_accountant_passp_place_reg()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_passp_date_end())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_passp_date_end=?",
					filter.getJ_accountant_passp_date_end()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_code_citizenship=?",
					filter.getJ_accountant_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birthday=?",
					filter.getJ_accountant_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_birth_place=?",
					filter.getJ_accountant_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getJ_accountant_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_accountant_address=?",
					filter.getJ_accountant_address()));
		}
		if (!CheckNull.isEmpty(filter.getJ_327())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_327=?", filter.getJ_327()));
		}
		if (!CheckNull.isEmpty(filter.getJ_patent_expiration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "j_patent_expiration=?",
					filter.getJ_patent_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getJ_responsible_emp())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "j_responsible_emp=?", filter.getJ_responsible_emp()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_birthday=?", filter.getP_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_post_address) like ?", filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_type=?", filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "upper(p_passport_serial) like ?", filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "upper(p_passport_number) like ?", filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_passport_place_registration) like ?",
					filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_registration=?",
					filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_tax_org=?", filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_number_tax_registration like ?",
					filter.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_bank=?", filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_class_credit=?",
					filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_citizenship=?",
					filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_birth_place) like ?", filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_capacity=?", filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_capacity_status_date=?",
					filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_capacity_status_place) like ?",
					filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_num_certif_capacity=?",
					filter.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_home like ?", filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_phone_mobile like ?", filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_email_address) like ?", filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_pension_sertif_serial=?",
					filter.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_gender=?", filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_nation=?", filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_region=?",
					filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_code_birth_distr=?",
					filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_type_document=?", filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_passport_date_expiration=?",
					filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_code_adr_region=?", filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "p_code_adr_distr=?", filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "p_inps=?", filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_family) like ?", filter.getP_family())); 
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_first_name) like ?", filter.getP_first_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_patronymic) like ?", filter.getP_patronymic()));
		}
		if (!CheckNull.isEmpty(filter.getP_last_name_cyr())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "upper(p_last_name_cyr) like ?", filter.getP_last_name_cyr()));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name_cyr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "upper(p_first_name_cyr) like ?", filter.getP_first_name_cyr()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic_cyr())) {
			flfields.add(
					new FilterField(DbUtils.getCond(flfields) + "upper(p_patronymic_cyr) like ?", filter.getP_patronymic_cyr()));
		}
		flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	private String appendLikeConditions() {
		StringBuilder sb = new StringBuilder();
		if (!CheckNull.isEmpty(filter.getName())) {
			sb.append(" and name like '").append(filter.getName().toUpperCase()).append("' ");
		}
		return sb.toString();
	}

	public static int getCount(ClientAFilter filter, String un, String pw, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_client");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
			// sql.append(appendLikeConditions());
			// sql.append(" and code_subject in('J','I')");
		}
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return count;
	}

	public List<ClientA> getList(ClientAFilter filter, String alias) {
		Connection c = null;
		List<ClientA> list = new ArrayList<ClientA>();
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from v_client ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
			// sql.append(appendLikeConditions());
			// sql.append(" and code_subject in ('J','I')");
		}
		try {
			if (alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{ call info.init() }");
			ps = c.prepareStatement(sql.toString());
			cs.execute();
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ClientA(rs.getLong("id"), rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name"), rs.getString("code_country"), rs.getString("code_type"),
						rs.getString("code_resident"), rs.getString("code_subject"), rs.getString("sign_registr"),
						rs.getString("code_form"), rs.getDate("date_open"), rs.getDate("date_close"),
						rs.getString("state"), rs.getInt("kod_err"), rs.getString("file_name"),
						rs.getString("j_short_name"), rs.getString("j_place_regist_name"),
						rs.getDate("j_date_registration"), rs.getString("j_number_registration_doc"),
						rs.getString("j_code_tax_org"), rs.getString("j_number_tax_registration"),
						rs.getString("j_code_sector_old"), rs.getString("j_code_sector"),
						rs.getString("j_code_organ_direct"), rs.getString("j_code_head_organization"),
						rs.getString("j_code_class_credit"), rs.getString("j_director_name"),
						rs.getString("j_director_passport"), rs.getString("j_chief_accounter_name"),
						rs.getString("j_chief_accounter_passport"), rs.getString("j_code_bank"),
						rs.getString("j_account"), rs.getString("j_post_address"), rs.getString("j_phone"),
						rs.getString("j_fax"), rs.getString("j_email"), rs.getString("j_sign_trade"),
						rs.getString("j_opf").trim(), rs.getString("j_soato").trim(), rs.getString("j_okpo").trim(),
						rs.getString("j_inn_head_organization"), rs.getString("j_region"), rs.getString("j_distr"),
						rs.getString("j_small_business"), rs.getString("j_director_type_document"),
						rs.getString("j_director_passp_serial"), rs.getString("j_director_passp_number"),
						rs.getDate("j_director_passp_date_reg"), rs.getString("j_director_passp_place_reg"),
						rs.getDate("j_director_passp_date_end"), rs.getString("j_director_code_citizenship"),
						rs.getDate("j_director_birthday"), rs.getString("j_director_birth_place"),
						rs.getString("j_director_address"), rs.getString("j_accountant_type_document"),
						rs.getString("j_accountant_passp_serial"), rs.getString("j_accountant_passp_number"),
						rs.getDate("j_accountant_passp_date_reg"), rs.getString("j_accountant_passp_place_reg"),
						rs.getDate("j_accountant_passp_date_end"), rs.getString("j_accountant_code_citizenship"),
						rs.getDate("j_accountant_birthday"), rs.getString("j_accountant_birth_place"),
						rs.getString("j_accountant_address"), rs.getLong("j_327"), rs.getDate("j_patent_expiration"),
						rs.getString("j_responsible_emp"), rs.getString("i_short_name"),
						rs.getDate("i_date_registration"), rs.getString("i_number_registration_doc"),
						rs.getString("i_place_regist_name"), rs.getString("i_number_tax_registration"),
						rs.getString("i_opf"), rs.getString("i_form"), rs.getString("i_sector"),
						rs.getString("i_organ_direct"), rs.getString("i_account"), rs.getString("i_post_address"),
						rs.getString("i_director_name"), rs.getString("i_chief_accounter_name"),
						rs.getString("i_phone"), rs.getString("i_fax"), rs.getString("i_email"),
						rs.getDate("p_birthday"), rs.getString("p_post_address"), rs.getString("p_passport_type"),
						rs.getString("p_passport_serial"), rs.getString("p_passport_number"),
						rs.getString("p_passport_place_registration"), rs.getDate("p_passport_date_registration"),
						rs.getString("p_code_tax_org"), rs.getString("p_number_tax_registration"),
						rs.getString("p_code_bank"), rs.getString("p_code_class_credit"),
						rs.getString("p_code_citizenship"), rs.getString("p_birth_place"),
						rs.getString("p_code_capacity"), rs.getDate("p_capacity_status_date"),
						rs.getString("p_capacity_status_place"), rs.getString("p_num_certif_capacity"),
						rs.getString("p_phone_home"), rs.getString("p_phone_mobile"), rs.getString("p_email_address"),
						rs.getString("p_pension_sertif_serial"), rs.getString("p_code_gender"),
						rs.getString("p_code_nation"), rs.getString("p_code_birth_region"),
						rs.getString("p_code_birth_distr"), rs.getString("p_type_document"),
						rs.getDate("p_passport_date_expiration"), rs.getString("p_code_adr_region"),
						rs.getString("p_code_adr_distr"), rs.getString("p_inps"), rs.getString("p_family"),
						rs.getString("p_first_name"), rs.getString("p_patronymic"), rs.getString("p_last_name_cyr"),
						rs.getString("p_first_name_cyr"), rs.getString("p_patronymic_cyr"),
						rs.getString("p_pass_place_region"), rs.getString("p_pass_place_district"),
						rs.getString("p_post_address_street"), rs.getString("p_post_address_house"),
						rs.getString("p_post_address_flat"), rs.getString("sign_100"), rs.getString("j_type_activity"),
						rs.getString("code_country_adr"), null, null, 0, 0, rs.getString("post_address_country"),rs.getString("p_pinfl"),
						rs.getString("p_Code_Nibbd"),rs.getDate("p_Date_ApprovedNibbd"),rs.getDate("p_Date_ClosedNibbd")));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
		}
		return list;
	}

	public static List<ClientA> getListWithPaging(int pageIndex, int pageSize, ClientAFilter filter, String un,
			String pw, String alias) {
		Connection c = null;
		List<ClientA> list = new ArrayList<ClientA>();
		PreparedStatement ps = null;
	//	CallableStatement cs = null;
		ResultSet rs = null;
		int v_lowerbound = pageIndex * pageSize + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append("SELECT id, branch, id_client, name, code_type, sign_registr, date_open, state FROM v_client ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
			// sql.append(appendLikeConditions());
			// sql.append(" and code_subject in ('J','I')");
		}
		sql.append(psql2);
		try {
			if (alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			// cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			ps = c.prepareStatement(sql.toString());
			// cs.execute();

			for (params = 0; params < flFields.size(); params++) {
				Object obj = flFields.get(params).getColobject();
				if (obj instanceof java.util.Date) {
					ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
					continue;
				}
				if (obj instanceof String) {
					// ps.setString(params + 1, ((String) obj).toUpperCase());
					ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
					continue;
				}
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ClientA(rs.getLong("id"), rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name"), rs.getString("code_type"), rs.getString("sign_registr"),
						rs.getDate("date_open"), rs.getString("state")));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
			//		DbUtils.closeStmt(cs);
			
		}
		return list;
	}

	public ClientA getItemByLongId(String branch, long itemId) {
		throw new UnsupportedOperationException();
	}

	public int update(ClientA ClientA) {
		return 0;
	}

	public int remove(ClientA ClientA) {
		return 0;
	}

	public ClientA create(ClientA ClientA) {
		return null;
	}

	public static ClientA getItemByStringId(String branch, String itemId, String un, String pw, String alias) {
		ClientA customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			if (alias == null) {
				String schema = DbUtils.getSchemaByBranch(branch);
				c = ConnectionPool.getConnection(schema);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("SELECT * FROM v_client WHERE branch = ? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new ClientA();
				customer.setId(rs.getLong("id"));
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id_client"));
				customer.setName(rs.getString("name"));
				customer.setCode_country(rs.getString("code_country"));
				customer.setCode_type(rs.getString("code_type"));
				customer.setCode_resident(rs.getString("code_resident"));
				customer.setCode_subject(rs.getString("code_subject"));
				customer.setSign_registr(rs.getString("sign_registr"));
				customer.setCode_form(rs.getString("code_form"));
				customer.setDate_open(rs.getDate("date_open"));
				customer.setDate_close(rs.getDate("date_close"));
				customer.setState(rs.getString("state"));
				customer.setKod_err(rs.getInt("kod_err"));
				customer.setFile_name(rs.getString("file_name"));
				customer.setJ_short_name(rs.getString("j_short_name"));
				customer.setJ_place_regist_name(rs.getString("j_place_regist_name"));
				customer.setJ_date_registration(rs.getDate("j_date_registration"));
				customer.setJ_number_registration_doc(rs.getString("j_number_registration_doc"));

				customer.setJ_code_tax_org(rs.getString("j_code_tax_org"));
				customer.setJ_number_tax_registration(rs.getString("j_number_tax_registration"));
				customer.setJ_code_sector_old(rs.getString("j_code_sector_old"));
				customer.setJ_code_sector(rs.getString("j_code_sector"));
				customer.setJ_code_organ_direct(rs.getString("j_code_organ_direct"));
				customer.setJ_code_head_organization(rs.getString("j_code_head_organization"));
				customer.setJ_code_class_credit(rs.getString("j_code_class_credit"));
				customer.setJ_director_name(rs.getString("j_director_name"));
				customer.setJ_director_passport(rs.getString("j_director_passport"));
				customer.setJ_chief_accounter_name(rs.getString("j_chief_accounter_name"));
				customer.setJ_chief_accounter_passport(rs.getString("j_chief_accounter_passport"));
				customer.setJ_code_bank(rs.getString("j_code_bank"));
				customer.setJ_account(rs.getString("j_account"));
				customer.setJ_post_address(rs.getString("j_post_address"));
				customer.setJ_phone(rs.getString("j_phone"));
				customer.setJ_fax(rs.getString("j_fax"));
				customer.setJ_email(rs.getString("j_email"));
				customer.setJ_sign_trade(rs.getString("j_sign_trade"));
				customer.setJ_opf(rs.getString("j_opf"));
				customer.setJ_soato(rs.getString("j_soato"));
				customer.setJ_okpo(rs.getString("j_okpo"));
				customer.setJ_inn_head_organization(rs.getString("j_inn_head_organization"));
				customer.setJ_region(rs.getString("j_region"));
				customer.setJ_distr(rs.getString("j_distr"));
				customer.setJ_small_business(rs.getString("j_small_business"));
				customer.setJ_director_type_document(rs.getString("j_director_type_document"));
				customer.setJ_director_passp_serial(rs.getString("j_director_passp_serial"));
				customer.setJ_director_passp_number(rs.getString("j_director_passp_number"));
				customer.setJ_director_passp_date_reg(rs.getDate("j_director_passp_date_reg"));
				customer.setJ_director_passp_place_reg(rs.getString("j_director_passp_place_reg"));
				customer.setJ_director_passp_date_end(rs.getDate("j_director_passp_date_end"));
				customer.setJ_director_code_citizenship(rs.getString("j_director_code_citizenship"));
				customer.setJ_director_birthday(rs.getDate("j_director_birthday"));
				customer.setJ_director_birth_place(rs.getString("j_director_birth_place"));
				customer.setJ_director_address(rs.getString("j_director_address"));
				customer.setJ_accountant_type_document(rs.getString("j_accountant_type_document"));
				customer.setJ_accountant_passp_serial(rs.getString("j_accountant_passp_serial"));
				customer.setJ_accountant_passp_number(rs.getString("j_accountant_passp_number"));
				customer.setJ_accountant_passp_date_reg(rs.getDate("j_accountant_passp_date_reg"));
				customer.setJ_accountant_passp_place_reg(rs.getString("j_accountant_passp_place_reg"));
				customer.setJ_accountant_passp_date_end(rs.getDate("j_accountant_passp_date_end"));
				customer.setJ_accountant_code_citizenship(rs.getString("j_accountant_code_citizenship"));
				customer.setJ_accountant_birthday(rs.getDate("j_accountant_birthday"));
				customer.setJ_accountant_birth_place(rs.getString("j_accountant_birth_place"));
				customer.setJ_accountant_address(rs.getString("j_accountant_address"));
				customer.setJ_327(rs.getLong("j_327"));
				customer.setJ_patent_expiration(rs.getDate("j_patent_expiration"));
				customer.setJ_responsible_emp(rs.getString("j_responsible_emp"));
				customer.setI_short_name(rs.getString("i_short_name"));
				customer.setI_date_registration(rs.getDate("i_date_registration"));
				customer.setI_number_registration_doc(rs.getString("i_number_registration_doc"));
				customer.setI_place_regist_name(rs.getString("i_place_regist_name"));
				customer.setI_number_tax_registration(rs.getString("i_number_tax_registration"));
				customer.setI_opf(rs.getString("i_opf"));
				customer.setI_form(rs.getString("i_form"));
				customer.setI_sector(rs.getString("i_sector"));
				customer.setI_organ_direct(rs.getString("i_organ_direct"));
				customer.setI_account(rs.getString("i_account"));
				customer.setI_post_address(rs.getString("i_post_address"));
				customer.setI_director_name(rs.getString("i_director_name"));
				customer.setI_chief_accounter_name(rs.getString("i_chief_accounter_name"));
				customer.setI_phone(rs.getString("i_phone"));
				customer.setI_fax(rs.getString("i_fax"));
				customer.setI_email(rs.getString("i_email"));
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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_Code_Nibbd(rs.getString("p_id_nibbd"));
				customer.setP_Date_ApprovedNibbd(rs.getDate("p_date_open_nibbd"));
				customer.setP_Date_ClosedNibbd(rs.getDate("p_date_close_nibbd"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
				customer.setJ_type_activity(rs.getString("j_type_activity"));
				customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
						? customer.getJ_code_head_organization().trim() : "0");
				customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
						? customer.getJ_inn_head_organization().trim() : "0");
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			DbUtils.closeCStmt(cs);
			ConnectionPool.close(c);
			}
		return customer;
	}

	public <T1 extends ClientA> void setFilter(T1 filter) {
		this.filter = (ClientAFilter) filter;
	}

	public ClientA create(Connection c, ClientA item) throws Exception {
		return null;
	}

	public int update(Connection c, ClientA item) throws Exception {
		return 0;
	}

	public int remove(Connection c, ClientA item) {
		return 0;
	}

	public ClientA getItemByLongId(Connection c, String branch, long itemId) {
		return null;
	}

	public ClientA getItemByStringId(Connection c, String branch, String itemId) {
		ClientA customer = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("SELECT * FROM v_client WHERE branch = ? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new ClientA(rs.getLong("id"), rs.getString("branch"), rs.getString("id_client"),
						rs.getString("name"), rs.getString("code_country"), rs.getString("code_type"),
						rs.getString("code_resident"), rs.getString("code_subject"), rs.getString("sign_registr"),
						rs.getString("code_form"), rs.getDate("date_open"), rs.getDate("date_close"),
						rs.getString("state"), rs.getInt("kod_err"), rs.getString("file_name"),
						rs.getString("j_short_name"), rs.getString("j_place_regist_name"),
						rs.getDate("j_date_registration"), rs.getString("j_number_registration_doc"),
						rs.getString("j_code_tax_org"), rs.getString("j_number_tax_registration"),
						rs.getString("j_code_sector_old"), rs.getString("j_code_sector"),
						rs.getString("j_code_organ_direct"), rs.getString("j_code_head_organization"),
						rs.getString("j_code_class_credit"), rs.getString("j_director_name"),
						rs.getString("j_director_passport"), rs.getString("j_chief_accounter_name"),
						rs.getString("j_chief_accounter_passport"), rs.getString("j_code_bank"),
						rs.getString("j_account"), rs.getString("j_post_address"), rs.getString("j_phone"),
						rs.getString("j_fax"), rs.getString("j_email"), rs.getString("j_sign_trade"),
						rs.getString("j_opf").trim(), rs.getString("j_soato").trim(), rs.getString("j_okpo").trim(),
						rs.getString("j_inn_head_organization"), rs.getString("j_region"), rs.getString("j_distr"),
						rs.getString("j_small_business"), rs.getString("j_director_type_document"),
						rs.getString("j_director_passp_serial"), rs.getString("j_director_passp_number"),
						rs.getDate("j_director_passp_date_reg"), rs.getString("j_director_passp_place_reg"),
						rs.getDate("j_director_passp_date_end"), rs.getString("j_director_code_citizenship"),
						rs.getDate("j_director_birthday"), rs.getString("j_director_birth_place"),
						rs.getString("j_director_address"), rs.getString("j_accountant_type_document"),
						rs.getString("j_accountant_passp_serial"), rs.getString("j_accountant_passp_number"),
						rs.getDate("j_accountant_passp_date_reg"), rs.getString("j_accountant_passp_place_reg"),
						rs.getDate("j_accountant_passp_date_end"), rs.getString("j_accountant_code_citizenship"),
						rs.getDate("j_accountant_birthday"), rs.getString("j_accountant_birth_place"),
						rs.getString("j_accountant_address"), rs.getLong("j_327"), rs.getDate("j_patent_expiration"),
						rs.getString("j_responsible_emp"), rs.getString("i_short_name"),
						rs.getDate("i_date_registration"), rs.getString("i_number_registration_doc"),
						rs.getString("i_place_regist_name"), rs.getString("i_number_tax_registration"),
						rs.getString("i_opf"), rs.getString("i_form"), rs.getString("i_sector"),
						rs.getString("i_organ_direct"), rs.getString("i_account"), rs.getString("i_post_address"),
						rs.getString("i_director_name"), rs.getString("i_chief_accounter_name"),
						rs.getString("i_phone"), rs.getString("i_fax"), rs.getString("i_email"),
						rs.getDate("p_birthday"), rs.getString("p_post_address"), rs.getString("p_passport_type"),
						rs.getString("p_passport_serial"), rs.getString("p_passport_number"),
						rs.getString("p_passport_place_registration"), rs.getDate("p_passport_date_registration"),
						rs.getString("p_code_tax_org"), rs.getString("p_number_tax_registration"),
						rs.getString("p_code_bank"), rs.getString("p_code_class_credit"),
						rs.getString("p_code_citizenship"), rs.getString("p_birth_place"),
						rs.getString("p_code_capacity"), rs.getDate("p_capacity_status_date"),
						rs.getString("p_capacity_status_place"), rs.getString("p_num_certif_capacity"),
						rs.getString("p_phone_home"), rs.getString("p_phone_mobile"), rs.getString("p_email_address"),
						rs.getString("p_pension_sertif_serial"), rs.getString("p_code_gender"),
						rs.getString("p_code_nation"), rs.getString("p_code_birth_region"),
						rs.getString("p_code_birth_distr"), rs.getString("p_type_document"),
						rs.getDate("p_passport_date_expiration"), rs.getString("p_code_adr_region"),
						rs.getString("p_code_adr_distr"), rs.getString("p_inps"), rs.getString("p_family"),
						rs.getString("p_first_name"), rs.getString("p_patronymic"), rs.getString("p_last_name_cyr"),
						rs.getString("p_first_name_cyr"), rs.getString("p_patronymic_cyr"),
						rs.getString("p_pass_place_region"), rs.getString("p_pass_place_district"),
						rs.getString("p_post_address_street"), rs.getString("p_post_address_house"),
						rs.getString("p_post_address_flat"), rs.getString("sign_100"), rs.getString("j_type_activity"),
						rs.getString("code_country_adr"), null, null, 0, 0, rs.getString("post_address_country"),rs.getString("p_pnfl"),
						rs.getString("p_Code_Nibbd"),rs.getDate("p_Date_ApprovedNibbd"),rs.getDate("p_Date_ClosedNibbd"));
				customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
						? customer.getJ_code_head_organization().trim() : "0");
				customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
						? customer.getJ_inn_head_organization().trim() : "0");
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return customer;
	}
	
	
	// CRM uchun
	public static com.is.clientcrm.ClientA getClientAByStringId(String branch, String itemId, String un, String pw, String alias) {
		com.is.clientcrm.ClientA customer = null;
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			if (alias == null) {
				String schema = DbUtils.getSchemaByBranch(branch);
				c = ConnectionPool.getConnection(schema);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("SELECT * FROM v_client WHERE branch = ? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, itemId);
			rs = ps.executeQuery();
			if (rs.next()) {
				customer = new com.is.clientcrm.ClientA();
				customer.setId(rs.getLong("id"));
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id_client"));
				customer.setName(rs.getString("name"));
				customer.setCode_country(rs.getString("code_country"));
				customer.setCode_type(rs.getString("code_type"));
				customer.setCode_resident(rs.getString("code_resident"));
				customer.setCode_subject(rs.getString("code_subject"));
				customer.setSign_registr(rs.getString("sign_registr"));
				customer.setCode_form(rs.getString("code_form"));
				customer.setDate_open(rs.getDate("date_open"));
				customer.setDate_close(rs.getDate("date_close"));
				customer.setState(rs.getString("state"));
				customer.setKod_err(rs.getInt("kod_err"));
				customer.setFile_name(rs.getString("file_name"));
				customer.setJ_short_name(rs.getString("j_short_name"));
				customer.setJ_place_regist_name(rs.getString("j_place_regist_name"));
				customer.setJ_date_registration(rs.getDate("j_date_registration"));
				customer.setJ_number_registration_doc(rs.getString("j_number_registration_doc"));

				customer.setJ_code_tax_org(rs.getString("j_code_tax_org"));
				customer.setJ_number_tax_registration(rs.getString("j_number_tax_registration"));
				customer.setJ_code_sector_old(rs.getString("j_code_sector_old"));
				customer.setJ_code_sector(rs.getString("j_code_sector"));
				customer.setJ_code_organ_direct(rs.getString("j_code_organ_direct"));
				customer.setJ_code_head_organization(rs.getString("j_code_head_organization"));
				customer.setJ_code_class_credit(rs.getString("j_code_class_credit"));
				customer.setJ_director_name(rs.getString("j_director_name"));
				customer.setJ_director_passport(rs.getString("j_director_passport"));
				customer.setJ_chief_accounter_name(rs.getString("j_chief_accounter_name"));
				customer.setJ_chief_accounter_passport(rs.getString("j_chief_accounter_passport"));
				customer.setJ_code_bank(rs.getString("j_code_bank"));
				customer.setJ_account(rs.getString("j_account"));
				customer.setJ_post_address(rs.getString("j_post_address"));
				customer.setJ_phone(rs.getString("j_phone"));
				customer.setJ_fax(rs.getString("j_fax"));
				customer.setJ_email(rs.getString("j_email"));
				customer.setJ_sign_trade(rs.getString("j_sign_trade"));
				customer.setJ_opf(rs.getString("j_opf"));
				customer.setJ_soato(rs.getString("j_soato"));
				customer.setJ_okpo(rs.getString("j_okpo"));
				customer.setJ_inn_head_organization(rs.getString("j_inn_head_organization"));
				customer.setJ_region(rs.getString("j_region"));
				customer.setJ_distr(rs.getString("j_distr"));
				customer.setJ_small_business(rs.getString("j_small_business"));
				customer.setJ_director_type_document(rs.getString("j_director_type_document"));
				customer.setJ_director_passp_serial(rs.getString("j_director_passp_serial"));
				customer.setJ_director_passp_number(rs.getString("j_director_passp_number"));
				customer.setJ_director_passp_date_reg(rs.getDate("j_director_passp_date_reg"));
				customer.setJ_director_passp_place_reg(rs.getString("j_director_passp_place_reg"));
				customer.setJ_director_passp_date_end(rs.getDate("j_director_passp_date_end"));
				customer.setJ_director_code_citizenship(rs.getString("j_director_code_citizenship"));
				customer.setJ_director_birthday(rs.getDate("j_director_birthday"));
				customer.setJ_director_birth_place(rs.getString("j_director_birth_place"));
				customer.setJ_director_address(rs.getString("j_director_address"));
				customer.setJ_accountant_type_document(rs.getString("j_accountant_type_document"));
				customer.setJ_accountant_passp_serial(rs.getString("j_accountant_passp_serial"));
				customer.setJ_accountant_passp_number(rs.getString("j_accountant_passp_number"));
				customer.setJ_accountant_passp_date_reg(rs.getDate("j_accountant_passp_date_reg"));
				customer.setJ_accountant_passp_place_reg(rs.getString("j_accountant_passp_place_reg"));
				customer.setJ_accountant_passp_date_end(rs.getDate("j_accountant_passp_date_end"));
				customer.setJ_accountant_code_citizenship(rs.getString("j_accountant_code_citizenship"));
				customer.setJ_accountant_birthday(rs.getDate("j_accountant_birthday"));
				customer.setJ_accountant_birth_place(rs.getString("j_accountant_birth_place"));
				customer.setJ_accountant_address(rs.getString("j_accountant_address"));
				customer.setJ_327(rs.getLong("j_327"));
				customer.setJ_patent_expiration(rs.getDate("j_patent_expiration"));
				customer.setJ_responsible_emp(rs.getString("j_responsible_emp"));
				customer.setI_short_name(rs.getString("i_short_name"));
				customer.setI_date_registration(rs.getDate("i_date_registration"));
				customer.setI_number_registration_doc(rs.getString("i_number_registration_doc"));
				customer.setI_place_regist_name(rs.getString("i_place_regist_name"));
				customer.setI_number_tax_registration(rs.getString("i_number_tax_registration"));
				customer.setI_opf(rs.getString("i_opf"));
				customer.setI_form(rs.getString("i_form"));
				customer.setI_sector(rs.getString("i_sector"));
				customer.setI_organ_direct(rs.getString("i_organ_direct"));
				customer.setI_account(rs.getString("i_account"));
				customer.setI_post_address(rs.getString("i_post_address"));
				customer.setI_director_name(rs.getString("i_director_name"));
				customer.setI_chief_accounter_name(rs.getString("i_chief_accounter_name"));
				customer.setI_phone(rs.getString("i_phone"));
				customer.setI_fax(rs.getString("i_fax"));
				customer.setI_email(rs.getString("i_email"));
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
				customer.setJ_type_activity(rs.getString("j_type_activity"));
				customer.setJ_code_head_organization(customer.getJ_code_head_organization() != null
						? customer.getJ_code_head_organization().trim() : "0");
				customer.setJ_inn_head_organization(customer.getJ_inn_head_organization() != null
						? customer.getJ_inn_head_organization().trim() : "0");
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return customer;
	}
	
	public static String getGender(String id,String branch,String un, String pw, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String res = null;
		try {
			if (alias == null) {
				String schema = DbUtils.getSchemaByBranch(branch);
				c = ConnectionPool.getConnection(schema);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
		
			ps = c.prepareStatement("select NAME_SEX from S_SEX where CODE_SEX=? and act<>'Z'");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
		    res = rs.getString("NAME_SEX");
			}
		}
		catch(Exception e){
		e.printStackTrace();
		ISLogger.getLogger().error("Oshibka_gender: "+e.getMessage());
		
		}	
		
		finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return res;		
		
	}
	
	public static String getNation(String natId,String branch,String un, String pw, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String res = null;
		try {
			if (alias == null) {
				String schema = DbUtils.getSchemaByBranch(branch);
				c = ConnectionPool.getConnection(schema);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
		
			ps = c.prepareStatement("select nat_name from s_nation where nat_id=? and act <> 'Z'");
			ps.setString(1, natId);
			rs = ps.executeQuery();
			if (rs.next()) {
		    res = rs.getString("NAT_NAME");
			}
		}
		catch(Exception e){
		e.printStackTrace();
		ISLogger.getLogger().error("Oshibka_nation: "+e.getMessage());
		
		}	
		
		finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return res;		
		
	}
	public static String getInfoBankType(String branch){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String b_Type = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select bank_type type from s_mfo where bank_id=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				b_Type = rs.getString("TYPE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return b_Type;
	}
	public ResNibbd getResponseNibbd(String bank_type,String branch,String pinfl, String id_Nibbd,
			String gend,Date birth_date,String mob_phone,String p_e_mail){
		
		ResNibbd resNibbd = null;
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		ObjectMapper objectMapper = new ObjectMapper();
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement clParam = null;
		CallableStatement csAff = null;
		Date birth_Date = birth_date;
		String date_Birth = formatter.format(birth_date);
		System.out.println("date_birth: "+birth_Date);
		ISLogger.getLogger().error("date_birth: "+birth_Date);
		java.sql.Date sqlDate = new java.sql.Date(birth_Date.getTime());
		ISLogger.getLogger().error("date_birth_sqlDate: "+sqlDate);
		int index =0;
		
		
		try{
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
		
			clParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
			clParam.execute();			
			
			csAff = c.prepareCall("{?= call proc_clients.setPhysicalByPin(?,?,?,?,?,?,?,?)}");
			csAff.registerOutParameter(1, oracle.jdbc.OracleTypes.CHAR);
			csAff.setString(2, bank_type);
			csAff.setString(3, branch);
			csAff.setString(4, pinfl);
			csAff.setString(5, id_Nibbd);
			csAff.setString(6, gend);
			csAff.setString(7, date_Birth);
			csAff.setString(8, mob_phone);
			csAff.setString(9, p_e_mail);
			csAff.execute();
			
			ISLogger.getLogger().error("Response_Object1 : "+csAff.getObject(1).toString());
			ISLogger.getLogger().error("Response_Object12 : "+csAff.getObject(1));
			if(index<csAff.getObject(1).toString().length()){
		     index = csAff.getObject(1).toString().indexOf('{', 14);
		    int end_index = csAff.getObject(1).toString().length()-1;
		     ISLogger.getLogger().error("Response_Object2_substring : "+csAff.getObject(1).toString().substring(index, end_index));
			
		    
			resNibbd = objectMapper.readValue(csAff.getObject(1).toString().substring(index, end_index), ResNibbd.class);
			ISLogger.getLogger().error("Response_Object4_final : "+resNibbd.toString());
			 }
		   } 
		catch(Exception e){
			e.printStackTrace();
			ISLogger.getLogger().error("Oshibka_ResponseNibbd: "+e.getMessage());		
			
		} finally {
			Utils.close(csAff);
			Utils.close(clParam);
			Utils.close(cs);
			ConnectionPool.close(c);
		}		
		
		return resNibbd;	
		
	}
	public String getUpdClientP(ResNibbd res_Nibbd,String Branch,String id_Client) throws ParseException{
		String res ="ok";
		Connection c = null;
		PreparedStatement ps = null;
	//	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String  date_str = res_Nibbd.getHeader().getRespond().substring(0,10);
		ISLogger.getLogger().error("Upd_Client_date_str: "+date_str);
	//	String v_date = df.format(date_str);
	//	java.sql.Date sqlDate2 = new java.sql.Date(v_date.getTime());
	//	ISLogger.getLogger().error("date_NIBBDAffirm_sqlDate: "+sqlDate2);
	//	ISLogger.getLogger().error("Upd_Client_date_parsed: "+v_date);
		
		try{
			c = ConnectionPool.getConnection();
		    ps = c.prepareStatement("update /*+ INDEX(client_p XUK_CLIENT_P) */ client_p set date_open_nibbd=to_date(?,'dd.mm.yyyy')"
			                        +" where branch=? and id=?");
			ps.setString(1, date_str);
			ps.setString(2, Branch);
			ps.setString(3, id_Client);
			
		    ps.executeUpdate();
		    
		    c.commit();			
		}
		
		catch(Exception e){
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = e.getMessage();
					try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().error(CheckNull.getPstr(e1));
			res = e1.getMessage();	
			}
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);			
		}
       return res;
			
		}
	
	public static String getDocnum(String branch) {
		String result = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select seq_nibbd_service.nextval docnum from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString("docnum");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public Res_Gsp resGsp(String bank_type,String branch,Request_Gsp req_gsp){
		
		Res_Gsp res = null;
		ObjectMapper objectMapper = new ObjectMapper();
		Connection c = null;
		CallableStatement clParam = null;
		CallableStatement csAff = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT seq_SSI_PHYSICAL.nextval id FROM dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				req_gsp.setId_request(rs.getString("ID"));
			}
			clParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
			clParam.execute();
			
			csAff = c.prepareCall("{?= call proc_clients.getPhysicalQuery(?,?,?,?,?,?,?,?,?,?)}");
			csAff.registerOutParameter(1, oracle.jdbc.OracleTypes.CHAR);
			csAff.setString(2, bank_type);
			csAff.setString(3, branch);
			csAff.setString(4, req_gsp.getLang());
			csAff.setString(5, req_gsp.getPas_ser());
			csAff.setString(6, req_gsp.getPas_num());
			csAff.setString(7, req_gsp.getPinfl());
			csAff.setString(8, req_gsp.getInn());
			csAff.setString(9, req_gsp.getDate_birth());
			csAff.setString(10, req_gsp.getId_client());
			csAff.setString(11, req_gsp.getId_request());
			csAff.execute();
			
			ISLogger.getLogger().error("Response_Object1 : "+csAff.getObject(1).toString());
						
			res = objectMapper.readValue(csAff.getObject(1).toString(), Res_Gsp.class);
			ISLogger.getLogger().error("Response_Object4_final : "+res.toString());
						

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(csAff);
			Utils.close(clParam);
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;		
		
	}
	
	public static ClientA getPgs(long id){
	
		ClientA result = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from ssi_physical s where s.id =?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new ClientA();
				result.setP_pinfl(rs.getString("PIN"));
				result.setP_family(rs.getString("LAST_NAME"));
				result.setP_first_name(rs.getString("FIRST_NAME"));
				result.setP_patronymic(rs.getString("PATRONYM"));
				result.setP_birthday(rs.getDate("BIRTH_DATE"));
				result.setP_code_gender(rs.getString("SEX"));
				result.setP_passport_serial(rs.getString("PASSPORT_SERIA"));
				result.setP_passport_number(rs.getString("PASSPORT_NUMBER"));
				result.setP_passport_date_registration(rs.getDate("DATE_ISSUE"));
				result.setP_passport_date_expiration(rs.getDate("DATE_EXPIRY"));
				result.setP_passport_place_registration(rs.getString("GIVE_PLACE"));
				result.setP_birth_place(rs.getString("BIRTH_PLACE"));
				result.setP_code_nation(rs.getString("NATIONALITY"));
				result.setP_code_tax_org(rs.getString("INN_REGISTRATED_GNI"));
				result.setP_number_tax_registration(rs.getString("INN"));
				result.setP_post_address(rs.getString("DOMICILE_ADDRESS"));
				result.setP_code_adr_region(rs.getString("DOMICILE_REGION"));
				result.setP_code_adr_distr(rs.getString("DOMICILE_DISTRICT"));
				result.setP_post_address_house(rs.getString("DOMICILE_HOUSE"));
				result.setP_post_address_flat(rs.getString("DOMICILE_FLAT"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
		
	}
	
	public static String getNationCode(String id_nation,String neededInfo){
		
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		String info =null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select s.nationality,s.map from ss_national_gcp s where s.id =?");
			ps.setLong(1, Long.valueOf(id_nation));
			rs = ps.executeQuery();
			
			if (rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();
				
				Map<String, Object> row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), rs.getObject(i));
					
				}
				res.add(row);
				
			}
                for (int j = 0; j < res.size(); j++) {
				
				if (res.get(j).containsKey("NATIONALITY") && neededInfo.equals("NATIONALITY")) {
					info = (String) res.get(j).get("NATIONALITY");
					System.out.println("res-----eeee1:" + info);
				} else if (res.get(j).containsKey("MAP")&& neededInfo.equals("MAP")) {
					info = (String) res.get(j).get("MAP");
			
				}
                    }
		   } catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error("oshibka_getNationCode: "+e.getMessage());
           } finally {
             Utils.close(rs);
             Utils.close(ps);
             ConnectionPool.close(c);
              }
             return info;		
	}
		
}