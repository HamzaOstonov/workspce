package com.is.client_personmap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.is.client_personmap.dao.LegalEntityDao;
import com.is.client_personmap.dao.PersonDao;
import com.is.client_personmap.dao.PersonMapDao;
import com.is.clients.dao.DaoFactory;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.sap.service.exception.SAPDuplicationException;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.abstraction.AbstractSapRoleService;
import com.is.client_sap.exceptions.SapException;
import com.is.client_sap.exceptions.SapRelationException;
import com.is.client_sap.services.SapCustomerService;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.CheckNull;
import com.is.utils.Res;

import relationships.NCI.com.ipakyulibank.BPRelResp;

public class PersonMapService {

    private static Logger logger = Logger.getLogger(PersonMapService.class);

    private String alias;

    public final static int DEALID_ADDINFO = 209;
    public final static int GROUP_P = 1;
    public final static int GROUP_LE = 2;
    public final static int GROUP_MAP = 3;
    public final static int ACTION_CREATE = 1;
    public final static int ACTION_DELETE = 2;
    public final static int ACTION_MODIFY = 3;
    public final static int ACTION_RESTORE = 4;
    public final static int ACTION_CONFIRM = 5;
    public final static int ACTION_CONFIRM_HOP = 6;
    public final static int ACTION_ANNUL = 7;
    public final static int STATE_OPENED = 1;
    public final static int STATE_CONFIRMED = 3;

    private final static String ACTIONS_SELECT = "select aa.id data, aa.name label " +
            "from trans_client_addinfo tc, action_client_addinfo aa " +
            "where tc.deal_id = ? " +
            "and state_begin = ? " +
            "and aa.deal_id = ? " +
            "and aa.id = tc.action_id " +
            "and aa.manual=1 ";

    private LegalEntityDao legalEntityDao;
    private PersonDao personDao;
    private PersonMapDao personMapDao;

    private PersonMapService() {
        this.legalEntityDao = (LegalEntityDao) DaoFactory.instance().getLegalEntityDao();
        this.personDao = (PersonDao) DaoFactory.instance().getPersonDao();
        this.personMapDao = (PersonMapDao) DaoFactory.instance().getPersonMapDao();
    }

    private PersonMapService(String alias) {
        this.alias = alias;
        this.legalEntityDao = (LegalEntityDao) DaoFactory.getInstance(alias).getLegalEntityDao();
        this.personDao = (PersonDao) DaoFactory.getInstance(alias).getPersonDao();
        this.personMapDao = (PersonMapDao) DaoFactory.getInstance(alias).getPersonMapDao();
    }

    public static PersonMapService instance() {
        return new PersonMapService();
    }

    public static PersonMapService instance(String alias) {
        return new PersonMapService(alias);
    }


    interface ActionExecutor {
        Res execute(Connection c, CallableStatement actionSt, CallableStatement setParam, CallableStatement getParam) throws Exception;
    }

    Res abstractAction(ActionExecutor executor, String branch) {
        Res res = new Res();
        Connection c = null;
        CallableStatement doAction = null;
        CallableStatement clearParam = null;
        CallableStatement setParam = null;
        CallableStatement getParam = null;
        CallableStatement init = null;

        try {
            if (alias == null) {
                if (Executions.getCurrent() != null)
                    c = ConnectionPool.getConnection(
                            Executions.getCurrent()
                                    .getSession()
                                    .getAttribute("alias")
                                    .toString());
                else {
                    String schema = DbUtils.getSchemaByBranch(branch);
                    c = ConnectionPool.getConnection(schema);
                }
            } else {
                c = ConnectionPool.getConnection(alias);
            }

            init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            clearParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
            setParam = c.prepareCall(SqlScripts.SET_PARAM.getSql());
            getParam = c.prepareCall("{? = call Param.getparam('P_ID') }");
            doAction = c.prepareCall(SqlScripts.DO_ACTION.getSql());
            init.execute();
            clearParam.execute();
            getParam.registerOutParameter(1, Types.VARCHAR);
            res = executor.execute(c, doAction, setParam, getParam);
            c.commit();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
            DbUtils.rollback(c);
            if (e instanceof SapCustomerService.FounderDuplicationException)
                throw (SapCustomerService.FounderDuplicationException) e;
        } finally {
            DbUtils.closeStmt(init);
            DbUtils.closeStmt(clearParam);
            DbUtils.closeStmt(setParam);
            DbUtils.closeStmt(getParam);
            DbUtils.closeStmt(doAction);
            ConnectionPool.close(c);
        }

        return res;
    }

    Res abstractAction(Connection c, ActionExecutor executor) {
        Res res = new Res();
//		Connection c = null;
        CallableStatement doAction = null;
        CallableStatement clearParam = null;
        CallableStatement setParam = null;
        CallableStatement getParam = null;
        CallableStatement init = null;

        try {
            init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
            clearParam = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
            setParam = c.prepareCall(SqlScripts.SET_PARAM.getSql());
            getParam = c.prepareCall("{? = call Param.getparam('P_ID') }");
            doAction = c.prepareCall(SqlScripts.DO_ACTION.getSql());
            init.execute();
            clearParam.execute();
            getParam.registerOutParameter(1, Types.VARCHAR);
            res = executor.execute(c, doAction, setParam, getParam);

        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            res = new Res(-1, e.getMessage());
        } finally {
            DbUtils.closeStmt(init);
            DbUtils.closeStmt(clearParam);
            DbUtils.closeStmt(setParam);
            DbUtils.closeStmt(getParam);
            DbUtils.closeStmt(doAction);
        }

        return res;
    }

    public Res personAction(final Person person, final int action) {
        Res res = abstractAction(new ActionExecutor() {

            @Override
            public Res execute(
                    Connection c,
                    CallableStatement actionSt,
                    CallableStatement setParam,
                    CallableStatement getParam) throws Exception {
                actionSt.setInt(1, DEALID_ADDINFO);
                actionSt.setInt(2, GROUP_P);
                actionSt.setInt(3, action);
                if (person.getName() == null || person.getName().trim().isEmpty())
                    throw new RuntimeException("Name is null");

                if (person.getPassport_serial() != null) {
                    person.setPassport_serial(person.getPassport_serial().toUpperCase());
                }

                for (Field field : Person.class.getDeclaredFields()) {
                    if (Modifier.isFinal(field.getModifiers())
                            || Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }

                    field.setAccessible(true);
                    setParam.setString(1, "P_" + field.getName().toUpperCase());
                    Object obj = field.get(person);

                    if (obj instanceof Date) {
                        setParam.setDate(2, Util.sqlDate((Date) obj));

                    } else {
                        setParam.setObject(2, obj);
                    }
                    if (field.getName().equalsIgnoreCase("NAME"))
                        if (obj == null)
                            throw new RuntimeException("Name is null");

                    //logger.info("Field Name -> " + field.getName().toUpperCase() + " Value -> " + obj);

                    setParam.execute();
                }

                actionSt.execute();
                getParam.execute();

                Res res = new Res(0, getParam.getString(1));
                if (person.getId() == null) {
                    person.setId(res.getName());
                }
                
                /*logger.error(" Person: " + person);
                logger.error(" Person name : " + person.getName());
                logger.error(" Person post_address                   : " + person.getPost_address                ());
                logger.error(" Person type_document                  : " + person.getType_document               ());
                logger.error(" Person passport_serial                : " + person.getPassport_serial             ());
                logger.error(" Person passport_number                : " + person.getPassport_number             ());
                logger.error(" Person passport_date_registration     : " + person.getPassport_date_registration  ());
                logger.error(" Person passport_place_registration    : " + person.getPassport_place_registration ());
                logger.error(" Person phone_home                     : " + person.getPhone_home                  ());
                logger.error(" Person phone_mobile                   : " + person.getPhone_mobile                ());
                logger.error(" Person code_citizenship               : " + person.getCode_citizenship            ());
                logger.error(" Person passport_date_expiration       : " + person.getPassport_date_expiration    ());
                logger.error(" Person family_local                   : " + person.getFamily_local                ());
                logger.error(" Person first_name_local               : " + person.getFirst_name_local            ());
                logger.error(" Person patronymic_local               : " + person.getPatronymic_local            ());
                logger.error(" Person pinfl                          : " + person.getPinfl                       ());
                logger.error(" Person code_resident                  : " + person.getCode_resident               ());
                logger.error(" Person code_adr_region                : " + person.getCode_adr_region             ());
                logger.error(" Person code_adr_distr                 : " + person.getCode_adr_distr              ());
                logger.error(" Person birthday                       : " + person.getBirthday                    ());
                logger.error(" Person code_gender                    : " + person.getCode_gender                 ());*/

                //updateCl_add_02_founders(c, person);

                if (action != ACTION_DELETE && action != ACTION_CREATE) {
                    Person refreshedPerson = personDao.getItemByStringId(c, null, person.getId());
                    person.setUnion_id(refreshedPerson.getUnion_id());
                    logger.error("Refreshed Person " + refreshedPerson);
                }

                if (action == ACTION_CONFIRM || action == ACTION_CONFIRM_HOP) {
                    if (person.getUnion_id() == null)
                        throw new RuntimeException("Union Id is null");
                    if (person.getIdSap() == null) {
                        SapFactory.instance().getCustomerService().process(person);
                    } else {
                        SapFactory.instance().getCustomerService().updatePerson(person);
                    }
                } else if (action == ACTION_MODIFY) {
                    // Confirmed
                    if (person.getState() == 3) {
                        //logger.error("PersonMapService Modify " + person);
                        SapFactory.instance().getCustomerService().process(person);
                    }
                }

                return res;
            }
        }, person.getBranch());

        return res;
    }

    public Res personAction(Connection c, final Person person) {
        Res res = abstractAction(c, new ActionExecutor() {

            @Override
            public Res execute(Connection connection, CallableStatement actionSt, CallableStatement setParam, CallableStatement getParam) throws Exception {

                actionSt.setInt(1, DEALID_ADDINFO);
                actionSt.setInt(2, GROUP_P);
                actionSt.setInt(3, ACTION_CREATE);


                person.setPassport_serial(person.getPassport_serial().toUpperCase());

                setPersonParams(person, setParam);

                setParam.setString(1, "P_NAME");
                setParam.setString(2, person.personName());
                setParam.executeQuery();

                actionSt.execute();

                getParam.execute();

                Res res = new Res(0, getParam.getString(1));
                person.setId(res.getName());
                return res;
            }
        });

        return res;
    }

    public Res leAction(final LegalEntity le, final int action) {
        Res res = abstractAction(new ActionExecutor() {

            @Override
            public Res execute(Connection c, CallableStatement actionSt, CallableStatement setParam, CallableStatement getParam) throws Exception {

                actionSt.setInt(1, DEALID_ADDINFO);
                actionSt.setInt(2, GROUP_LE);
                actionSt.setInt(3, action);
                setLeParams(le, setParam);
                
                if ( CustomerUtils.isAtaccamaOn()){ 
                	callAtaccama(c);
                }	

                actionSt.execute();
                getParam.execute();

                String id = getParam.getString(1);

                Res res = new Res(0, id);
                if (action != ACTION_DELETE) {
                    LegalEntity refreshedLegalEntity = legalEntityDao.getItemByStringId(c,null, res.getName());
                    le.setUnion_id(refreshedLegalEntity.getUnion_id());
                }
                if (le.getId() == null) {
                    le.setId(res.getName());
                }
                if (action == ACTION_CREATE) {
                    le.setId(res.getName());

                } else if (action == ACTION_CONFIRM || action == ACTION_CONFIRM_HOP) {
                    if (le.getUnion_id() == null)
                        throw new RuntimeException("Union Id is null");
                    if (!EmergencyMode.isTrue) {
                        if (le.getIdSap() == null) {
                            SapFactory.instance().getLegalEntityService().createIfAbsent(le);
                        } else {
                            SapFactory.instance().getLegalEntityService().orgRequestEdit(le);
                        }
                    }
                } else if (action == ACTION_MODIFY) {
                    // Confirmed
                    if (!EmergencyMode.isTrue) {
                        if (le.getState() == 3)
                            SapFactory.instance().getLegalEntityService().createIfAbsent(le);
                    }
                }
                return res;
            }
        }, le.getBranch());

        return res;
    }

    public Res relationAction(final PersonMap personMap, final int action) {
        Res res = abstractAction(new ActionExecutor() {

            @Override
            public Res execute(Connection c, CallableStatement actionSt, CallableStatement setParam, CallableStatement getParam) throws Exception {
                if (action == ACTION_ANNUL) {
                    doRelationActionInSap(personMap, action, action);
                    return new Res(0, "");
                }

                actionSt.setInt(1, DEALID_ADDINFO);
                actionSt.setInt(2, GROUP_MAP);
                actionSt.setInt(3, action);

                /*if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                    setLeParams(personMap.getLegalEntity(), setParam);
                } else {
                    setPersonParams(personMap.getPerson(), setParam);
                }*/

                setMapParam(personMap, setParam);

                actionSt.execute();

                getParam.execute();
                Res res = new Res(0, getParam.getString(1));
                if (action == ACTION_CREATE) {
                    personMap.setId(Long.parseLong(res.getName()));
                }

                int actionForSap = action;

                if (!EmergencyMode.isTrue && !(action == ACTION_MODIFY && personMap.getState() != STATE_CONFIRMED)) {
                    logger.info("Sending relation to SAP " + personMap);
                    if (personMap.getUnion_id() == null && action != ACTION_DELETE) {
                        // Нужно для вызова из PersonLoadingService
                        String union_id = personMapDao.getUnionIdByMap(c, personMap);
                        if (union_id == null && action != ACTION_CREATE)
                            throw new RuntimeException("Union Id Is Null");

                        personMap.setUnion_id(union_id);
                    }

                    doRelationActionInSap(personMap, actionForSap, action);
                }

                logger.info("Action " + action + " is successful");
                return res;
            }

            private int getActionForIpRelation(final PersonMap personMap, int actionForSap) throws Exception {
                String idsap = SapFactory.instance().getRelationService()
                        .getIPcode(personMap.getClient_id(), personMap.getBranch());
                boolean hasRelation = idsap != null;
                if (personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_IP)) {
                    actionForSap = hasRelation ? ACTION_MODIFY : ACTION_CONFIRM;
                }
                return actionForSap;
            }

            private boolean isIpAction(final PersonMap personMap) {
                return personMap.getState() == STATE_CONFIRMED
                        && personMap.getPerson_kind().equals(PersonMapUtil.PERSONKIND_IP);
            }
        }, personMap.getBranch());

        return res;
    }

    private void doRelationActionInSap(final PersonMap personMap, int actionForSap, int actionNci)
            throws SapRelationException, Exception {
        final AbstractSapRelationService relationService = SapFactory.instance().getRelationService();
        final AbstractSapRoleService roleService = SapFactory.instance().getRoleService();
        boolean isExists = false;

        if (actionNci != ACTION_CREATE)
            isExists = isRelExists(personMap, relationService);

        switch (actionForSap) {
            case ACTION_CONFIRM:
                if (personMap.isFromSap() || isExists) {
                    relationService.modifyRelation(personMap);
                } else {
                    relationService.createRelation(personMap);
                    try {
                        roleService.createRole(personMap);
                    }
                    catch (Exception e){
                        logger.error(CheckNull.getPstr(e));
                    }
                }
                break;
            case ACTION_RESTORE:
                break;
            case ACTION_CONFIRM_HOP:
                if (personMap.isFromSap()) {
                    relationService.modifyRelation(personMap);
                } else {
                    relationService.createRelation(personMap);
                    try {
                        roleService.createRole(personMap);
                    }
                    catch (Exception e){
                        logger.error(CheckNull.getPstr(e));
                    }
                }
                break;
            case ACTION_MODIFY:
                if (isExists)
                    relationService.modifyRelation(personMap);
                break;
            case ACTION_DELETE:
                if (isExists)
                    relationService.deleteRelation(personMap);
                break;
            case ACTION_ANNUL:
                if (isExists) {
                    relationService.modifyRelation(personMap);
                    try {
                        roleService.deleteRole(personMap);
                    }
                    catch (Exception e){
                        logger.error(CheckNull.getPstr(e));
                    }
                }
            default:
                break;
        }
    }

    private boolean isRelExists(PersonMap personMap, AbstractSapRelationService abstractSapRelationService)
            throws Exception {
        BusinessPartnerInterface businessPartnerInterface = SAPServiceFactory.getInstance().getBusinessPartnerService();
        String branch = personMap.getBranch();
        String idJ = "J" + personMap.getClient_id();
        String personId = "A" + (personMap.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONKIND_IP) ? personMap.getPerson_id() : personMap.getUnion_id());
        String idSAP = personMap.getIdSap() != null ?
                personMap.getIdSap() :
                businessPartnerInterface.get(branch, personId, null).getIdSap();

        BPRelResp[] relations = abstractSapRelationService
                .getRelationsByIdAndBranch(personMap.getClient_id(), personMap.getBranch());
        if (relations != null) {
            for (int i = 0; i < relations.length; i++) {
                BPRelResp relation = relations[i];
                String sapRelation = relation.getBp_relationships().getRel_type();
                String nciRelation = SapEnum.getByNciKey(personMap.getPerson_kind()).getSapValue();
                if (idSAP != null && idSAP.equalsIgnoreCase(relation.getBp_id_01())
                        && sapRelation.equalsIgnoreCase(nciRelation))
                    return true;
            }
        }
        return false;
    }

    private void doFounderActionInSap(final PersonMap personMap, int action) throws SapException {
        if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
            if (action == ACTION_CONFIRM || action == ACTION_CONFIRM_HOP) {
                if (personMap.getLegalEntity().getIdSap() == null) {
                    SapFactory.instance().getLegalEntityService().createIfAbsent(personMap.getLegalEntity());
                } else {
                    SapFactory.instance().getLegalEntityService().orgRequestEdit(personMap.getLegalEntity());
                }
            } else if (action == ACTION_MODIFY) {
                SapFactory.instance().getLegalEntityService().createIfAbsent(personMap.getLegalEntity());
            }
        } else {
            if (action == ACTION_CONFIRM || action == ACTION_CONFIRM_HOP) {
                if (personMap.getPerson().getIdSap() == null) {
                    SapFactory.instance().getCustomerService().process(personMap.getPerson());
                } else {
                    SapFactory.instance().getCustomerService().updatePerson(personMap.getPerson());
                }
            } else if (action == ACTION_MODIFY) {
                SapFactory.instance().getCustomerService().process(personMap.getPerson());
            }
        }
    }


    public Res relationAction(Connection c, final PersonMap personMap) {
        Res res = abstractAction(c, new ActionExecutor() {

            @Override
            public Res execute(Connection connection, CallableStatement actionSt, CallableStatement setParam, CallableStatement getParam) throws Exception {
                actionSt.setInt(1, DEALID_ADDINFO);
                actionSt.setInt(2, GROUP_MAP);
                actionSt.setInt(3, ACTION_CREATE);

                //logger.error("dealId = " + DEALID_ADDINFO + " groipId = " + GROUP_MAP + " action = " + ACTION_CREATE);
                if (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)) {
                    setLeParams(personMap.getLegalEntity(), setParam);
                } else {
                    setPersonParams(personMap.getPerson(), setParam);
                }
                setMapParam(personMap, setParam);

                actionSt.execute();

                getParam.execute();
                Res res = new Res(0, getParam.getString(1));
                personMap.setId(Long.parseLong(res.getName()));
                return res;
            }
        });

        return res;
    }

    public Map<Integer, String> getAvailableActionsForFace(int state) {
        Map<Integer, String> actions = new HashMap<Integer, String>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(ACTIONS_SELECT);
            ps.setInt(1, GROUP_P);
            ps.setInt(2, state);
            ps.setInt(3, GROUP_P);
            rs = ps.executeQuery();

            while (rs.next()) {
                actions.put(rs.getInt("data"), rs.getString("label"));
            }

        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
            ConnectionPool.close(c);
        }

        return actions;
    }

    public Map<Integer, String> getAvailableActionsForRelation(int state) {
        Map<Integer, String> actions = new HashMap<Integer, String>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement(ACTIONS_SELECT);
            ps.setInt(1, GROUP_MAP);
            ps.setInt(2, state);
            ps.setInt(3, GROUP_MAP);
            rs = ps.executeQuery();
            while (rs.next()) {
                actions.put(rs.getInt("data"), rs.getString("label"));
            }

        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
            ConnectionPool.close(c);
        }

        return actions;
    }

    private void setMapParam(PersonMap personMap, CallableStatement setParam) throws SQLException {
        setParam.setString(1, "P_ID");
        setParam.setLong(2, personMap.getId());
        setParam.execute();
        //logger.info("P_ID = " + personMap.getId());

        setParam.setString(1, "P_BRANCH");
        setParam.setString(2, personMap.getBranch());
        setParam.execute();
        //logger.info("P_BRANCH = " + personMap.getBranch());

        setParam.setString(1, "P_CLIENT_ID");
        setParam.setString(2, personMap.getClient_id());
        setParam.execute();
        //logger.info("P_CLIENT_ID = " + personMap.getClient_id());

        setParam.setString(1, "P_PERSON_KIND");
        setParam.setString(2, personMap.getPerson_kind());
        setParam.execute();
        //logger.info("P_PERSON_KIND = " + personMap.getPerson_kind());

        setParam.setString(1, "P_PERSON_TYPE");
        setParam.setString(2, personMap.getPerson_type());
        setParam.execute();
        //logger.info("P_PERSON_TYPE = " + personMap.getPerson_type());

        setParam.setString(1, "P_PERSON_NAME");
        setParam.setString(2, personMap.name());
        setParam.execute();
        //logger.info("P_PERSON_NAME = " + personMap.name());

        setParam.setString(1, "P_PERSON_ID");
        setParam.setString(2, personMap.getPerson_id());
        setParam.execute();
        //logger.info("P_PERSON_ID = " + personMap.getPerson_id());

        setParam.setString(1, "P_PART_OF_CAPITAL");
        setParam.setBigDecimal(2, personMap.getCapital().getPart_of_capital());
        setParam.execute();
        //logger.info("P_PART_OF_CAPITAL = " + personMap.getCapital().getPart_of_capital());

        setParam.setString(1, "P_SUM_A");
        setParam.setBigDecimal(2, personMap.getCapital().getSum_a());
        setParam.execute();
        //logger.info("P_SUM_A = " + personMap.getCapital().getSum_a());

        setParam.setString(1, "P_SUM_B");
        setParam.setBigDecimal(2, personMap.getCapital().getSum_b());
        setParam.execute();
        //logger.info("P_SUM_B = " + personMap.getCapital().getSum_b());

        setParam.setString(1, "P_CURRENCY");
        setParam.setString(2, personMap.getCapital().getCurrency());
        setParam.execute();
        //logger.info("P_CURRENCY = " + personMap.getCapital().getCurrency());

        setParam.setString(1, "P_IS_DIRECTOR");
        setParam.setString(2, personMap.getCapital().getIs_director());
        setParam.execute();
        //logger.info("P_IS_DIRECTOR = " + personMap.getCapital().getIs_director());

        setParam.setString(1, "P_SHARES_NUMBER");
        setParam.setBigDecimal(2, personMap.getCapital().getShares_number());
        setParam.execute();
        //logger.info("P_SHARES_NUMBER = " + personMap.getCapital().getShares_number());
    }

    private void setLeParams(LegalEntity le, CallableStatement setParam) throws Exception {
        for (Field field : LegalEntity.class.getDeclaredFields()) {
            if (Modifier.isFinal(field.getModifiers())
                    || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            setParam.setString(1, "P_" + field.getName().toUpperCase());
            Object obj = field.get(le);
            if (obj instanceof Date) {
                setParam.setDate(2, Util.sqlDate((Date) obj));
            } else {
                setParam.setObject(2, obj);
            }
            setParam.execute();
        }
    }

    private void setPersonParams(Person person, CallableStatement setParam) throws Exception {
        for (Field field : Person.class.getDeclaredFields()) {
            if (Modifier.isFinal(field.getModifiers())
                    || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            setParam.setString(1, "P_" + field.getName().toUpperCase());
            Object obj = field.get(person);

            if (obj instanceof Date) {
                setParam.setDate(2, Util.sqlDate((Date) obj));
            } else {
                if (field.getName().equalsIgnoreCase("name"))
                    setParam.setObject(2, person.concatenateFullName());
                else
                    setParam.setObject(2, obj);
            }

            //logger.error("F n " + field.getName() );
            //logger.error("F v " + obj);
            setParam.execute();
        }
    }

    public Map<Integer, String> getStates() {
        return DbUtils.getHRefDataInt("select id data, name label from state_client_addinfo", alias);
    }

    public PersonMap getAvailableRelations(PersonMap personMap_) {
        PersonMap personMap = new PersonMap();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(alias);
            preparedStatement = c.prepareStatement("SELECT * FROM CLIENT_ADDINFO_PERSON");
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return personMap;
    }
    
    /*private void updateCl_add_02_founders(Connection c, Person person) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update (client_j) cl_add_02 set director_pinfl=?, director_family=?, director_first_name=?, director_patronymic=? where branch=? and id = ?");          
            ps.setString(1, client.getP_pinfl());
            ps.setString(2, client.getP_family_local() != null ? client.getP_family_local().toUpperCase() : null );
            ps.setString(3, client.getP_first_name_local() != null ? client.getP_first_name_local().toUpperCase() : null);
            ps.setString(4, client.getP_patronymic_local() != null ? client.getP_patronymic_local().toUpperCase() : null);
            ps.setString(5, client.getBranch());
            ps.setString(6, client.getParent_id_client_j());            
            ps.executeUpdate();
        } finally {
            DbUtils.closeStmt(ps);
        }
    }*/
    
    private void callAtaccama(Connection c) throws SQLException {
        
        	 CallableStatement csAtaccama = null;
        	 try {
          	 csAtaccama = c.prepareCall("{ call proc_ataccama()}");
          	 csAtaccama.execute();
        	 } finally {
        		 DbUtils.closeStmt(csAtaccama);	 
        	 }
        
    }
    

}
