package com.is.clients.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.is.customer_.core.ConnectionUtils;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.client_personmap.PersonLoadingService;
import com.is.clients.addinfo.ParameterService;
import com.is.clients.dao.DaoFactory;
import com.is.clients.ebp.EbpService;
import com.is.clients.models.ClientJ;
import com.is.clients.models.ResInn;
import com.is.clients.models.SapIpClient;
import com.is.clients.sap.SapHandler;
import com.is.clients.utils.ClientUtil;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.service.NibbdDao;
import com.is.user.Module;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class ClientJService {
    private String alias;
    private String un;
    private String pw;
    private final static Logger logger = Logger.getLogger(ClientJService.class);

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

    private static final int DEAL_ID = 1;
    public static final int ACTION_OPEN = 1;
    public static final int ACTION_CONFIRM = 2;
    public static final int ACTION_CLOSE = 3;
    public static final int ACTION_CHANGE_NIBBD = 4;
    private static final int ACTION_DELETE = 6;

    private ClientJService(String un, String pw, String alias) {
        this.alias = alias;
        this.un = un;
        this.pw = pw;
        // this.personMapService = personMapService;
    }

    public static ClientJService getInstance(String un, String pw, String alias) {
        return new ClientJService(un, pw, alias);
    }

    @SuppressWarnings("resource")
    public Res doAction(ClientJ clientj, int actionid) {
        Res res = null;

        // SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement getParam = null;
        CallableStatement csAtaccama = null;
        PreparedStatement ps = null;
        PreparedStatement customerStatement = null;
        ResultSet rs = null;
        boolean isIP = clientj.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || clientj.getCode_type().equals(ClientUtil.CODE_TYPE_SE);
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
            if (clientj.getState() != null) {
                cs.setString(1, "ID");
                cs.setLong(2, clientj.getId());
                cs.execute();
            }
            clientj.fillDirectors();
            if (clientj.getDirector() != null)
                if (clientj.getDirector().getCode_capacity() == null)
                    clientj.getDirector().setCode_capacity("0801");

            if (clientj.getAccountant() != null)
                if (clientj.getAccountant().getCode_capacity() == null)
                    clientj.getAccountant().setCode_capacity("0801");

            if (clientj.getJ_code_head_organization() == null) {
                clientj.setJ_code_head_organization("0");
            }
            if (clientj.getJ_inn_head_organization() == null) {
                clientj.setJ_inn_head_organization("0");
            }
            // if (clientj.getJ_code_sector() == null) {
            // clientj.setJ_code_sector("0");
            // } //2018-01-04
            for (Field field : ClientJ.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                if (field.getName().equals("id") && clientj.getState() == null || field.getName().equals("director")
                        || field.getName().equals("accountant") || field.getName().equals("individualEnterpreneur")) {
                    continue;
                }

                cs.setString(1, field.getName().toUpperCase());

                Object obj = field.get(clientj);

                if (obj instanceof Date)
                    cs.setDate(2, Util.sqlDate((Date) obj));
                else {
                    if (field.getName().equals("j_short_name"))
                        cs.setString(2, clientj.concatenateShortName());
                    else if (isIP && field.getName().equals("name") && clientj.getSign_registr() != 3)
                        cs.setString(2, clientj.concatenateFullName());
                    else if (isIP && field.getName().equals("j_director_name"))
                        cs.setString(2, clientj.concatenateNames());
                    else if (isIP && (field.getName().equals("p_family") || field.getName().equals("p_first_name")
                            || field.getName().equals("p_patronymic"))) {
                        if (obj != null)
                            cs.setObject(2, ((String) obj).trim());
                        else
                            cs.setObject(2, obj);
                    } else {
                        cs.setObject(2, obj);
                    }
                }
                cs.execute();
            }
            //direktor, buxgalter malumotlar
            if (actionid == ACTION_OPEN 
                    && !(clientj.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || clientj.getCode_type().equals(ClientUtil.CODE_TYPE_SE) )) {
                cs.setString(1, "J_DIRECTOR_FAMILY");
                cs.setString(2, clientj.getDirector().getFamily_local());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_FIRST_NAME");
                cs.setString(2, clientj.getDirector().getFirst_name_local());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PATRONYMIC");
                cs.setString(2, clientj.getDirector().getPatronymic_local());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_BIRTHDAY");
                cs.setDate(2, Util.sqlDate((Date) clientj.getDirector().getBirthday()));
                cs.execute();
                cs.setString(1, "J_DIRECTOR_CODE_GENDER");
                cs.setString(2, clientj.getDirector().getCode_gender());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_CODE_CITIZENSHIP");
                cs.setString(2, clientj.getDirector().getCode_citizenship());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_TYPE_DOCUMENT");
                cs.setString(2, clientj.getDirector().getType_document());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PASSP_SERIAL");
                cs.setString(2, clientj.getDirector().getPassport_serial());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PASSP_NUMBER");
                cs.setString(2, clientj.getDirector().getPassport_number());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PASSP_DATE_REG");
                cs.setDate(2, Util.sqlDate((Date)clientj.getDirector().getPassport_date_registration()));
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PASSP_PLACE_REG");  
                cs.setString(2, clientj.getDirector().getPassport_place_registration());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PINFL");
                cs.setString(2, clientj.getDirector().getPinfl());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_INN");
                cs.setString(2, clientj.getDirector().getNumber_tax_registration());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_CODE_RESIDENT");
                cs.setString(2, clientj.getDirector().getCode_resident());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_CODE_REGION");
                cs.setString(2, clientj.getDirector().getCode_adr_region());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_CODE_DISTR");
                cs.setString(2, clientj.getDirector().getCode_adr_distr());
                cs.execute();
                cs.setString(1, "J_DIRECTOR_PHONE");
                cs.setString(2, clientj.getDirector().getPhone_mobile());
                cs.execute();
                	
                cs.setString(1, "J_ACCOUNTANT_FAMILY");
                cs.setString(2, clientj.getAccountant().getFamily_local());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_FIRST_NAME");
                cs.setString(2, clientj.getAccountant().getFirst_name_local());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PATRONYMIC");
                cs.setString(2, clientj.getAccountant().getPatronymic_local());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_BIRTHDAY");
                cs.setDate(2, Util.sqlDate((Date) clientj.getAccountant().getBirthday()));
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_CODE_GENDER");
                cs.setString(2, clientj.getAccountant().getCode_gender());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_CODE_CITIZENSHIP");
                cs.setString(2, clientj.getAccountant().getCode_citizenship());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_TYPE_DOCUMENT");
                cs.setString(2, clientj.getAccountant().getType_document());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PASSP_SERIAL");
                cs.setString(2, clientj.getAccountant().getPassport_serial());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PASSP_NUMBER");
                cs.setString(2, clientj.getAccountant().getPassport_number());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PASSP_DATE_REG");
                cs.setDate(2, Util.sqlDate((Date)clientj.getAccountant().getPassport_date_registration()));
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PASSP_PLACE_REG"); 
                cs.setString(2, clientj.getAccountant().getPassport_place_registration());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PINFL");
                cs.setString(2, clientj.getAccountant().getPinfl());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_INN");
                cs.setString(2, clientj.getDirector().getNumber_tax_registration());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_CODE_RESIDENT");
                cs.setString(2, clientj.getDirector().getCode_resident());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_CODE_REGION");
                cs.setString(2, clientj.getDirector().getCode_adr_region());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_CODE_DISTR");
                cs.setString(2, clientj.getDirector().getCode_adr_distr());
                cs.execute();
                cs.setString(1, "J_ACCOUNTANT_PHONE");
                cs.setString(2, clientj.getDirector().getPhone_mobile());
                cs.execute();
                
            }
            
            // У ИП не передается физическая часть в client_j Смотреть класс PendingResponseHandler
            if ((actionid == 51 || actionid == ClientUtil.ACTION_CONFIRM_CLOSED) && isIP) {

                cs.setString(1, "CLIENT_P_NAME");
                cs.setString(2, clientj.getName());
                cs.execute();
                cs.setString(1, "P_BIRTHDAY");
                cs.setDate(2, Util.sqlDate(clientj.getP_birthday()));
                cs.execute();
                cs.setString(1, "P_PASSPORT_SERIAL");
                cs.setString(2, clientj.getP_passport_serial());
                cs.execute();
                cs.setString(1, "P_PASSPORT_NUMBER");
                cs.setString(2, clientj.getP_passport_number());
                cs.execute();
                cs.setString(1, "P_TYPE_DOCUMENT");
                cs.setString(2, clientj.getP_type_document());
                cs.execute();
                cs.execute();
                cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
                cs.setString(2, clientj.getP_passport_place_registration());
                cs.execute();
                cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
                cs.setDate(2, Util.sqlDate(clientj.getP_passport_date_expiration()));
                cs.execute();
                cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
                cs.setDate(2, Util.sqlDate(clientj.getP_passport_date_registration()));
                cs.execute();
                cs.setString(1, "P_CODE_GENDER");
                cs.setString(2, clientj.getP_code_gender());
                cs.execute();
                cs.setString(1, "P_CODE_ADR_REGION");
                cs.setString(2, clientj.getP_code_adr_region());
                cs.execute();
                cs.setString(1, "P_CODE_ADR_DISTR");
                cs.setString(2, clientj.getP_code_adr_distr());
                cs.execute();
                cs.setString(1, "P_BIRTH_PLACE");
                cs.setString(2, clientj.getP_birth_place());
                cs.execute();
                cs.setString(1, "P_POST_ADDRESS");
                cs.setString(2, clientj.getP_post_address());
                cs.execute();
                cs.setString(1, "P_CODE_CITIZENSHIP");
                cs.setString(2, clientj.getP_code_citizenship());
                cs.execute();
                cs.setString(1, "P_CODE_NATION");
                cs.setString(2, clientj.getP_code_nation());
                cs.execute();
            }

            if (actionid == 50) /* otpravit nulevoy zapros */ {
                // if sap action new generated id is required
                cs.setString(1, "ID_CLIENT");
                cs.setString(2, getNextValForSapAction(c));
                cs.execute();
            }

            //ataccama-hamza-2023.02.22
            if ( CustomerUtils.isAtaccamaOn()) {
            	if (!clientj.isCheckedInAtaccama()) {
            	 csAtaccama = c.prepareCall("{ call proc_ataccama()}");
            	 csAtaccama.execute();
            	}
            }
            
            acs.setInt(1, DEAL_ID);
            acs.setInt(2, clientj.getSign_registr());
            acs.setInt(3, actionid);

            acs.execute();

            if (actionid == ACTION_OPEN || actionid == 50) {
                if (clientj.getSign_registr() == ClientUtil.SIGN_REGISTR_PRIMARY
                        || clientj.getSign_registr() == ClientUtil.SIGN_REGISTR_PRIMARY_NOT) {
                    getParam.execute();
                    clientj.setId(getParam.getLong(1));
                    String idClient = null;
                    ps = c.prepareStatement("SELECT id_client,date_open FROM v_client_sap WHERE id=?");
                    ps.setLong(1, clientj.getId());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        idClient = rs.getString(1);
                        clientj.setDate_open(rs.getDate(2));
                    }
                    clientj.setId_client(idClient);
                    res.setName(idClient);
                }
                addToClientDesc(c, clientj);
                if (clientj.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || clientj.getCode_type().equals(ClientUtil.CODE_TYPE_SE)) {
                    addToClientP(c, clientj);
                    String ipIdSap = null;
                    if (clientj.getIndividualEnterpreneur() != null
                            && clientj.getIndividualEnterpreneur().getCustomer() != null) {
                        ipIdSap = clientj.getIndividualEnterpreneur().getCustomer().getIdSap();
                    }
                    DaoFactory.getInstance(alias).getSapIpClientDao().create(c, // zdes
                            // netu
                            // obrasheniya
                            // v
                            // sap.
                            new SapIpClient(clientj.getId_client(), clientj.getBranch(), ipIdSap));
                    // }
                }
            } else if (clientj.getSign_registr() == ClientUtil.SIGN_REGISTR_PRIMARY_NOT) {
                res.setName(clientj.getId_client());
            } else if ((actionid == ACTION_CONFIRM || actionid == ACTION_CHANGE_NIBBD)
                    && clientj.getSign_registr() == ClientUtil.SIGN_REGISTR_PRIMARY) {
                if (actionid == ACTION_CHANGE_NIBBD)
                    addToClientDesc(c, clientj);
                // Stub commented
                //String newId = stubNibbd(c, cs, clientj, actionid);
                //res.setName(newId);
            }

            if (Util.inInts(actionid, ClientUtil.ACTION_CHANGE, ClientUtil.ACTION_CONFIRM_CLOSED)) {
                addToClientDesc(c, clientj);
                SapHandler.makeSapRequest(clientj, c);
                if (clientj.isI014()) {
                    logger.error("Client J i014");
                    roleReaction(c, clientj);
                }
            }
            if (actionid == ACTION_CLOSE)
                SapHandler.sendUvk(ParameterService.getAddinfo(c, clientj.getBranch(), clientj.getId_client(),
                        clientj.getCode_subject(), clientj.getCode_type(), alias));

            if (actionid == 50) {
                Dao<Nibbd> nibbdDao = NibbdDao.getInstance(alias, clientj.getBranch());
                Nibbd nibbd = new Nibbd();
                nibbd.setQuery_num(0);
                nibbd.setNumber_tax_registration(clientj.getJ_number_tax_registration());
                nibbdDao.create(c, nibbd);
            }

            if (actionid == ACTION_DELETE) {

            }

            c.commit();

            if ((actionid == ACTION_OPEN || actionid == 50)
                    && !(clientj.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || clientj.getCode_type().equals(ClientUtil.CODE_TYPE_SE) )) {
                PersonLoadingService.instance(un, pw, alias).handle(clientj);
            }

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
            DbUtils.closeStmt(customerStatement);
            DbUtils.closeStmt(csAtaccama);
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

    private void addToClientP(Connection c, ClientJ client) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("UPDATE client_p SET family=?, FIRST_NAME=?, "
                    + "PATRONYMIC=?, code_tax_org=?, number_tax_registration=?, "
                    + "code_nation=?, code_birth_region=?, code_birth_distr=?, " + "inps = ?"
                    + "WHERE id=? AND branch=?");
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

    private void addToClientDesc(Connection c, ClientJ client) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT count(*) FROM client_desc WHERE id_client=? AND branch=?");
            ps.setString(1, client.getId_client());
            ps.setString(2, client.getBranch());

            rs = ps.executeQuery();
            boolean update = false;
            if (rs.next()) {
                update = rs.getInt(1) > 0;
            }
            ps.close();

            try {
                ClientJ ebp = EbpService.getClientFromEbp(client);
                if (ebp != null) {
                    client.setCapital_currency(ebp.getCapital_currency());
                    client.setCapital_inform(ebp.getCapital_inform());
                }
            } catch (Exception e) {
                logger.error("EBP Client error: " + CheckNull.getPstr(e));
            }
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

    private String stubNibbd(Connection c, CallableStatement setParam, ClientJ client, int action) throws Exception {
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
                ps = c.prepareStatement("SELECT id_client FROM v_client_nibbd_stub");
                rs = ps.executeQuery();

                if (rs.next()) {
                    idClient = rs.getString(1);
                    int numericId = Integer.parseInt(idClient) + 1;
                    idClient = String.format("%08d", numericId);
                }
                ps.close();
                rs.close();
            }
            // idClient = " + idClient);
            account = "202080009" + idClient + "001";
            // ISLogger.getLogger().error(":::::::::::::::::::::::::: stubNibbd
            // account = " + account);
            ps = c.prepareStatement("SELECT kernel.fckey(?,?) FROM dual");
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

    public int[] getAvailableModules(int userId) {
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        int roleId;
        int[] modules = null;
        try {
            c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            cs.execute();
            ps = c.prepareStatement("SELECT roleid FROM bf_user_roles WHERE userid=? AND branch=info.getbranch");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                roleId = rs.getInt("roleid");
                List<Module> list = UserService.getModuleInRole(roleId, alias);
                if (!list.isEmpty()) {
                    modules = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        modules[i] = list.get(i).getId();
                    }
                }

            }
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }

        return modules;
    }

    public static void main(String[] args) {
        String idClient = "04293827";
        int numericId = Integer.parseInt(idClient) + 1;
        System.out.println(String.format("%08d", numericId));
    }

    private void callReactionSAP(Connection c, CallableStatement setParam, ClientJ clientJ) {
        CallableStatement callableStatement = null;
        try {
            callableStatement = c.prepareCall("{call reaction_sap.onClientJApprove}");
            callableStatement.execute();
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeStatement(callableStatement);
        }
    }

    public void roleReaction(ClientJ customer) {
        Connection c = null;
        try {
            c = ConnectionUtils.getInstance().getConnectionByBranch(customer.getBranch());
            roleReaction(c, customer);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                ConnectionUtils.getInstance().close(c);
            } catch (SQLException e) {
                logger.error(CheckNull.getPstr(e));
            }
        }
    }

    public void roleReaction(Connection connection, ClientJ customer) {
        try {
            CallableStatement setParam = connection.prepareCall("{ call Param.SetParam(?,?) }");
            setParam.setString(1, "ID_CLIENT");
            setParam.setString(2, customer.getId_client());
            setParam.execute();
            setParam.setString(1, "BRANCH");
            setParam.setString(1, customer.getBranch());
            setParam.execute();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM NIBBD_RETURNED_CLIENTS WHERE BRANCH = ? AND ID_CLIENT= ?");
            preparedStatement.setString(1, customer.getBranch());
            preparedStatement.setString(2, customer.getId_client());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);

                if (count == 0) {
                    PreparedStatement insertStatement = connection.prepareStatement("select seq_nibbd_returned_clients.nextval from dual");
                    resultSet = insertStatement.executeQuery();

                    long id = 0;
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                    }

                    insertStatement = connection.prepareStatement("" +
                            "INSERT INTO NIBBD_RETURNED_CLIENTS (id, branch, id_client, state, emp_id) VALUES(?,?,?,?,?)");
                    insertStatement.setLong(1, id);
                    insertStatement.setString(2, customer.getBranch());
                    insertStatement.setString(3, customer.getId_client());
                    insertStatement.setInt(4, 2);
                    insertStatement.setInt(5, 90002);

                    insertStatement.executeQuery();

                    DbUtils.closeStmt(insertStatement);
                }
            }

            callReactionSAP(connection, setParam, customer);
            logger.error("Role reaction called");
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(setParam);
            DbUtils.closeStmt(preparedStatement);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    
    public static List<RefData> getCloseType(String alias) {
		return RefDataService.getRefData("select close_type_id data, close_type_name label from ss_spr_204 order by 1", alias);
	}
    
    public static String getUrl() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String url = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bf_sets where id like '%NIBBD_JUR_BANK_URL%'");

			rs = ps.executeQuery();
			if (rs.next()) {
				url = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e);
		} finally {
			ConnectionPool.close(c);
		}
		return url;
	}
    
    public static ResInn sendInn(String query, String un, String pw ) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ResInn str = null;
		String url = getUrl()+ "getAccountByInn/014/00444";


		String p_data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(query);

		System.out.println(p_data);

		String content = sendData(url, p_data, un, pw);

		String contNew = new String(content.getBytes(), "UTF-8");

		if (!contNew.equals(""))
			try {
				str = objectMapper.readValue(contNew, ResInn.class);
			} catch (Exception e) { 
				str.setCode("-1");
				str.setMessage(e.getMessage());
				ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue. content: " + contNew);
				ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue error: " + e.getMessage());
				throw new Exception("RequestOpenClientForEdit objectMapper.readValue error: " + e.getMessage());
			}
		else
			ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue. content is \"\" or null.");
		return str;
	}
	
	public static ResInn sendPinfl(String query, String un, String pw ) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ResInn str = null;
		String url = getUrl()+ "getSubjectByPin/014/00444";


		String p_data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(query);

		System.out.println(p_data);

		String content = sendData(url, p_data, un, pw);

		String contNew = new String(content.getBytes(), "UTF-8");

		if (!contNew.equals(""))
			try {
				str = objectMapper.readValue(contNew, ResInn.class);
			} catch (Exception e) { 
				str.setCode("-1");
				str.setMessage(e.getMessage());
				ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue. content: " + contNew);
				ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue error: " + e.getMessage());
				throw new Exception("RequestOpenClientForEdit objectMapper.readValue error: " + e.getMessage());
			}
		else
			ISLogger.getLogger().error("RequestOpenClientForEdit objectMapper.readValue. content is \"\" or null.");
		return str;
	}
    
    public static String sendData(String p_url, String p_data, String un, String pw) {

	    String message = "";
	    String message_err = "";
	    int responseCode = 0;
	    try {
	      URL url = new URL(p_url);

	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

	      connection.setDoOutput(true);
	      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	      wr.writeBytes(p_data);
	      wr.flush();
	      responseCode = connection.getResponseCode();

	      BufferedReader br = null;
	      if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
	        br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
	      } else {
	        br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	        message_err = connection.getResponseMessage() + ", code: " + responseCode;
	      }
	      StringBuilder sb = new StringBuilder();
	      String output;
	      while ((output = br.readLine()) != null) {
	        sb.append(output);
	      }
	      message = sb.toString();
	      if (!message_err.equals("") && message_err != "") {
	        message = message + ". error result: " + message_err;
	      }
	      // IOUtils.copy(connection.getInputStream(), writer, "utf-8");
	      ISLogger.getLogger().error("response code: " + responseCode + ". body: " + message);
	    } catch (Exception e) {
	      ISLogger.getLogger().error("url = " + p_url);
	      ISLogger.getLogger().error("sendData data : " + p_data);
	      ISLogger.getLogger().error("responseCode: " + responseCode);
	      ISLogger.getLogger().error("sendData err Message: " + e.getMessage());
	      ISLogger.getLogger().error("sendData err Cause: " + e.getCause());
	      e.printStackTrace();
	    }
	    return message;// writer.toString();
	  }  
}
