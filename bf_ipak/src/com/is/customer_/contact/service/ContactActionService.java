package com.is.customer_.contact.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.clients.ebp.EbpService;
import com.is.clients.models.ClientJ;
import com.is.customer_.core.dao.CustomerDaoException;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.validator.CustomerValidator;
import com.is.customer_.sap.PARTNER_TYPES;
import com.is.customer_.sap.service.CustomerGatewayDecorator;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.transactionLogger.TransactionLogger;
import com.is.customer_.sap.transactionLogger.TransactionLoggerBuilder;
import com.is.utils.CheckNull;
import org.apache.poi.util.StringUtil;

/**
 * Created by root on 10.06.2017.
 * 15:15
 */
@SuppressWarnings("Duplicates")
public class ContactActionService {
    private Logger logger = Logger.getLogger(ContactActionService.class);
    private final SessionAttributes sessionAttributes;
    private final static String GET_CUSTOMER_BY_ID = "SELECT * FROM CLIENT_ADDINFO_PERSON A WHERE A.BRANCH = ? AND A.ID=?";
    private final static String GET_CUSTOMER_BY_UNION_ID = "SELECT * FROM CLIENT_ADDINFO_PERSON A WHERE A.BRANCH = ? AND A.UNION_ID = ? and state != 2";

    private CustomerGatewayDecorator customerGatewayDecorator =
            SAPServiceFactory.getInstance().
                    getCustomerGatewayDecorator();

    private ContactActionService(SessionAttributes sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public static ContactActionService getInstance(SessionAttributes sessionAttributes) {
        return new ContactActionService(sessionAttributes);
    }

    public Customer searchByDocument(String branch, Customer customer_){
        Connection c = null;
        Customer customer = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection(
                    sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            preparedStatement = c.prepareStatement("SELECT * FROM CLIENT_ADDINFO_PERSON WHERE TYPE_DOCUMENT = ? AND PASSPORT_SERIAL = ? AND PASSPORT_NUMBER = ? AND BRANCH = ?");
            preparedStatement.setString(1, customer_.getP_type_document());
            preparedStatement.setString(2, customer_.getP_passport_serial());
            preparedStatement.setString(3, customer_.getP_passport_number());
            preparedStatement.setString(4, branch);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                customer = mapToResultSet(resultSet);
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return customer;
    }

    public Customer getCustomer(String branch, String id) {
        Connection c = null;
        Customer customer = null;
        try {
            c = ConnectionPool.getConnection(
                    sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            customer = getCustomer(c, branch, id, GET_CUSTOMER_BY_ID);
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(c);
        }
        return customer;
    }

    public Customer getCustomerByUnionId(String branch, String union_id){
        Connection c = null;
        Customer customer = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getUsername(),sessionAttributes.getPassword(),sessionAttributes.getSchema());
            customer = getCustomer(c, branch, union_id, GET_CUSTOMER_BY_UNION_ID);
            // ѕопробовать поискать еще раз дл€ текущего филиала
            if (!customer.getBranch().equalsIgnoreCase(sessionAttributes.getBranch())){
                Customer result = getCustomer(c, sessionAttributes.getBranch(), union_id, GET_CUSTOMER_BY_UNION_ID);
                if(result != null)
                    customer = result;
            }
        } catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            ConnectionPool.close(c);
        }
        return customer;
    }

    public Customer getCustomer(Connection c, String branch, String id, final String SQL) throws CustomerDaoException, SQLException {
        Customer customer = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = c
                    .prepareStatement(SQL);
            preparedStatement.setString(1, branch);
            preparedStatement.setString(2, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer = mapToResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
            throw e;
        } finally {
            GeneralUtils.closeResultSet(rs);
            GeneralUtils.closeStatement(preparedStatement);
        }
        return customer;
    }

    private Customer mapToResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setBranch(rs.getString("branch"));
        customer.setId(rs.getString("id"));
        customer.setState(rs.getString("state"));
        customer.setName(rs.getString("name"));
        customer.setP_birthday(rs.getDate("birthday"));
        customer.setP_post_address(rs.getString("post_address"));
        customer.setP_passport_serial(rs.getString("passport_serial"));
        customer.setP_passport_number(rs.getString("passport_number"));
        customer.setP_passport_place_registration(rs.getString("passport_place_registration"));
        customer.setP_passport_date_registration(rs.getDate("passport_date_registration"));
        customer.setP_code_tax_org(rs.getString("code_tax_org"));
        customer.setP_number_tax_registration(rs.getString("number_tax_registration"));
        //customer.setP_code_bank(sessionAttributes.getBranch());
        customer.setP_code_class_credit(rs.getString("code_class_credit"));
        customer.setP_code_citizenship(rs.getString("code_citizenship"));
        customer.setP_birth_place(rs.getString("birth_place"));
        customer.setP_code_capacity(rs.getString("code_capacity"));
        customer.setP_phone_home(rs.getString("phone_home"));
        customer.setP_phone_mobile(rs.getString("phone_mobile"));
        customer.setP_email_address(rs.getString("email_address"));
        customer.setP_code_gender(rs.getString("code_gender"));
        customer.setP_code_nation(rs.getString("code_nation"));
        customer.setP_type_document(rs.getString("type_document"));
        customer.setP_passport_date_expiration(rs.getDate("passport_date_expiration"));
        customer.setP_code_adr_region(rs.getString("code_adr_region"));
        customer.setP_code_adr_distr(rs.getString("code_adr_distr"));
        customer.setP_inps(rs.getString("inps"));
        customer.setP_pinfl(rs.getString("pinfl"));
        customer.setP_family(rs.getString("family"));
        customer.setP_first_name(rs.getString("first_name"));
        customer.setP_patronymic(rs.getString("patronymic"));
        customer.setCode_country(rs.getString("code_country"));
        customer.setCode_resident(rs.getString("code_resident"));
        customer.setP_family_local(rs.getString("family_local"));
        customer.setP_first_name_local(rs.getString("first_name_local"));
        customer.setP_patronymic_local(rs.getString("patronymic_local"));
        customer.setP_pass_place_region(rs.getString("pass_place_region"));
        customer.setP_pass_place_district(rs.getString("pass_place_district"));
        customer.setP_post_address_quarter(rs.getString("p_post_address_quarter"));
        customer.setP_post_address_street(rs.getString("p_post_address_street"));
        customer.setP_post_address_house(rs.getString("p_post_address_house"));
        customer.setP_post_address_flat(rs.getString("p_post_address_flat"));
        customer.setP_code_citizenship(rs.getString("code_citizenship"));
        customer.setUnion_id(rs.getString("union_id"));
        customer.setSign_public_official(rs.getString("sign_public_official"));
        customer.setPo_job_title(rs.getString("po_job_title"));
        customer.setPo_welfare_source(rs.getString("po_welfare_source"));
        customer.setPo_other_data(rs.getString("po_other_data"));

        return customer;
    }

    public Customer doAction(final Integer dealId, final Customer actionCustomer_, final Integer action) throws Exception {
        logger.error("Contact Person before doAction " + actionCustomer_);
        if (action != 2) {
            CustomerValidator customerValidator = CustomerValidator.getInstance(actionCustomer_, sessionAttributes);
            if (!customerValidator.validate()) {
                throw new RuntimeException(customerValidator.getMessage());
            } /* elyor20221214 */else {
            	//actionCustomer_.setCheckedStoplistAtaccama(true);
            }/**/
        }

        Connection c = null;
        Customer customer = new Customer();
        String idSAP = actionCustomer_.getIdSap();
        try {
            c = ConnectionPool.getConnection(
                    sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            //logger.info("Action Contact -> " + actionCustomer_);
            String id = executeAction(c, actionCustomer_, action);
            // If action is not delete
            if (action != 2) {
                customer = getCustomer(c, actionCustomer_.getBranch(), id, GET_CUSTOMER_BY_ID);
                //logger.info("Action Contact Service -> Customer -> DB -> " + customer);
                //logger.info("Action Contact Service -> Branch = " + actionCustomer_.getBranch() + " Id = " + id);
                customer.setId(id);
            }
            //direktor korrektirovat
            if (action==3 && actionCustomer_.getParent_id_client_j()!=null &&  actionCustomer_.getPerson_role().equals("1") ) { 
            	updateClient_j_director(c, actionCustomer_);            	
            }
            //buxgalter korrektirovat
            if (action==3 && actionCustomer_.getParent_id_client_j()!=null &&  actionCustomer_.getPerson_role().equals("2") ) { 
            	updateClient_j_accountant(c, actionCustomer_);            	
            }
            // Confirmation
            if (action == 3 || action == 5 || action == 6) {
                TransactionLogger transactionLogger =
                        new TransactionLoggerBuilder()
                                .setCustomer_type(PARTNER_TYPES.CONTACT_PERSON.value())
                                .setCustomer(customer)
                                .setSessionAttributes(sessionAttributes)
                                //.setAction(action == 3 ? ActionsEnum.ACTION_CORRECT_BUSINESS_PARTNER.action() :
                                //ActionsEnum.ACTION_CONFIRM_BUSINESS_PARTNER.action())
                                .setAction(action)
                                .build();
                if (!transactionLogger.isEmergencyModeOn()) {
                    try {
                        if (customer.getUnion_id() == null)
                            throw new RuntimeException("Union Id is Null");

                        Customer customerForSAP = (Customer) customer.clone();
                        customerForSAP.setId("A" + customer.getUnion_id()); //2018-02-07
                        customerForSAP.setIdSap(idSAP);
                        if (actionCustomer_.isForceCreated()){
                            idSAP = customerGatewayDecorator.create(customerForSAP, "2","2").getIdSap();
                        }
                        else
                            idSAP = customerGatewayDecorator.process(customerForSAP);
                        transactionLogger.logSuccessTransaction();
                    } catch (Exception e) {
                        transactionLogger.logErrorTransaction(e.getMessage());
                        if (!transactionLogger.isErrorConsumed())
                            throw e;
                    }
                } else {
                    transactionLogger.logEmergencyTransaction();
                }
            }
            c.commit();
        } catch (Exception e) {
            c.rollback();
            logger.error(CheckNull.getPstr(e));
            throw e;
        } finally {
            if (customer != null)
                customer.setIdSap(idSAP);
            ConnectionPool.close(c);
        }
        return customer;
    }

    private String executeAction(Connection c, Customer customer, int action) throws CustomerDaoException, SQLException, IllegalAccessException {
        String id = null;
        CallableStatement setParam = null;
        CallableStatement doAction = null;
        CallableStatement clearParam = null;
        CallableStatement getParam = null;
        try {
            setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
            doAction = c.prepareCall("{ call kernel.doAction(?,?,?) }");

            clearParam = c.prepareCall("{ call Param.clearparam() }");
            clearParam.execute();

            getParam = c.prepareCall("{? = call Param.getparam('P_ID')}");
            getParam.registerOutParameter(1, Types.VARCHAR);

            setParams(setParam, customer);

            doAction.setInt(1, 209);
            doAction.setInt(2, 1);
            doAction.setInt(3, action); // ≈сли 1, то создаем нового клиента

            doAction.execute();
            getParam.execute();

            id = getParam.getString(1);

            //logger.error("P_ID " + id);

            return id;
        } finally {
            setParam.close();
            doAction.close();
            clearParam.close();
            getParam.close();
        }
    }

    private void setParams(CallableStatement setParam, Customer customer) throws SQLException, IllegalAccessException {
        if (customer.getId() != null && !customer.getId().isEmpty()) {
            setParam.setString(1, "P_ID");
            setParam.setString(2, customer.getId());
            setParam.execute();
        }

        setParam.setString(1, "P_BRANCH");
        setParam.setString(2, customer.getBranch());
        setParam.execute();
        
        setParam.setString(1, "P_NAME");
        setParam.setString(2, customer.getFullName() != null ? customer.getFullName().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_BIRTHDAY");
        setParam.setDate(2, customer.getP_birthday() == null ? null :
                CheckNull.d2sql(customer.getP_birthday()));
        setParam.execute();

        setParam.setString(1, "P_POST_ADDRESS");
        setParam.setString(2, customer.getP_post_address() != null ? customer.getP_post_address().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PASSPORT_SERIAL");
        setParam.setString(2, customer.getP_passport_serial() != null ? customer.getP_passport_serial().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PASSPORT_NUMBER");
        setParam.setString(2, customer.getP_passport_number());
        setParam.execute();

        setParam.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
        setParam.setString(2, customer.getP_passport_place_registration() != null ?
                customer.getP_passport_place_registration().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PASSPORT_DATE_REGISTRATION");
        setParam.setDate(2, customer.getP_passport_date_registration() != null ?
                CheckNull.d2sql(customer.getP_passport_date_registration()) : null);
        setParam.execute();

        //setParam.setString(1,"P_CODE_PLACE_REGIST");
        //setParam.setString(1,_DATE_REGISTRATION");
        /*setParam.setString(1,"P_NUMBER_REGISTRATION_DOC");
        setParam.setString(2,customer.getP_number_tax_registration());
        setParam.execute();*/

        setParam.setString(1, "P_CODE_TAX_ORG");
        setParam.setString(2, customer.getP_code_tax_org());
        setParam.execute();

        setParam.setString(1, "P_NUMBER_TAX_REGISTRATION");
        setParam.setString(2, customer.getP_number_tax_registration());
        setParam.execute();

        //setParam.setString(1,"CODE_SECTOR");
        //setParam.setString(2,"P_CODE_ORGAN_DIRECT");

        setParam.setString(1, "P_CODE_BANK");
        setParam.setString(2, customer.getBranch());
        setParam.execute();

        //setParam.setString(1,"ACCOUNT");
        setParam.setString(1, "P_CODE_CLASS_CREDIT");
        setParam.setString(2, customer.getP_code_class_credit());
        setParam.execute();

        //setParam.setString(1,"P_KOD_ERR");
        //setParam.setString(1,"P_FILE_NAME");
        setParam.setString(1, "P_CODE_CITIZENSHIP");
        setParam.setString(2, customer.getP_code_citizenship());
        setParam.execute();

        setParam.setString(1, "P_BIRTH_PLACE");
        setParam.setString(2, customer.getP_birth_place() != null ? customer.getP_birth_place().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_CODE_CAPACITY");
        setParam.setString(2, customer.getP_code_capacity());
        setParam.execute();

        setParam.setString(1, "P_CAPACITY_STATUS_DATE");
        setParam.setDate(2, customer.getP_capacity_status_date() != null ?
                CheckNull.d2sql(customer.getP_capacity_status_date()) : null);
        setParam.execute();

        setParam.setString(1, "P_CAPACITY_STATUS_PLACE");
        setParam.setString(2, customer.getP_capacity_status_place());
        setParam.execute();

        setParam.setString(1, "P_NUM_CERTIF_CAPACITY");
        setParam.setString(2, customer.getP_num_certif_capacity());
        setParam.execute();

        setParam.setString(1, "P_PHONE_HOME");
        setParam.setString(2, customer.getP_phone_home());
        setParam.execute();

        setParam.setString(1, "P_PHONE_MOBILE");
        setParam.setString(2, customer.getP_phone_mobile());
        setParam.execute();

        setParam.setString(1, "P_EMAIL_ADDRESS");
        setParam.setString(2, customer.getP_email_address() != null ? customer.getP_email_address().toLowerCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PENSION_SERTIF_SERIAL");
        setParam.setString(2, customer.getP_pension_sertif_serial());
        setParam.execute();

        setParam.setString(1, "P_CODE_GENDER");
        setParam.setString(2, customer.getP_code_gender());
        setParam.execute();

        setParam.setString(1, "P_CODE_NATION");
        setParam.setString(2, customer.getP_code_nation());
        setParam.execute();

        //setParam.setString(1,"P_CODE_BIRTH_REGION");
        //setParam.setString(1,"P_CODE_BIRTH_DISTR");

        setParam.setString(1, "P_TYPE_DOCUMENT");
        setParam.setString(2, customer.getP_type_document());
        setParam.execute();

        setParam.setString(1, "P_PASSPORT_DATE_EXPIRATION");
        setParam.setDate(2, customer.getP_passport_date_expiration() != null ?
                CheckNull.d2sql(customer.getP_passport_date_expiration()) : null);
        setParam.execute();

        setParam.setString(1, "P_CODE_ADR_REGION");
        setParam.setString(2, customer.getP_code_adr_region());
        setParam.execute();

        setParam.setString(1, "P_CODE_ADR_DISTR");
        setParam.setString(2, customer.getP_code_adr_distr());
        setParam.execute();

        setParam.setString(1, "P_INPS");
        setParam.setString(2, customer.getP_inps());
        setParam.execute();

        setParam.setString(1, "P_PINFL");
        setParam.setString(2, customer.getP_pinfl());
        setParam.execute();
        
        setParam.setString(1, "P_FAMILY");
        setParam.setString(2,
                customer.getP_family() != null ? customer.getP_family().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_FIRST_NAME");
        setParam.setString(2, customer.getP_first_name() != null ?
                customer.getP_first_name().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PATRONYMIC");
        setParam.setString(2, customer.getP_patronymic() != null ? customer.getP_patronymic().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_CODE_COUNTRY");
        setParam.setString(2, customer.getCode_country());
        setParam.execute();

        setParam.setString(1, "P_CODE_RESIDENT");
        setParam.setString(2, customer.getCode_resident());
        setParam.execute();

        setParam.setString(1, "P_PASS_PLACE_REGION");
        setParam.setString(2, customer.getP_pass_place_region());
        setParam.execute();

        setParam.setString(1, "P_PASS_PLACE_DISTRICT");
        setParam.setString(2, customer.getP_pass_place_district());
        setParam.execute();

        setParam.setString(1, "P_FAMILY_LOCAL");
        setParam.setString(2,
                customer.getP_family_local() != null ?
                        customer.getP_family_local().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_FIRST_NAME_LOCAL");
        setParam.setString(2,
                customer.getP_first_name_local() != null ?
                        customer.getP_first_name_local().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_PATRONYMIC_LOCAL");
        setParam.setString(2,
                customer.getP_patronymic_local() != null ?
                        customer.getP_patronymic_local().toUpperCase() : null);
        setParam.execute();

        setParam.setString(1, "P_POST_ADDRESS_STREET");
        setParam.setString(2, customer.getP_post_address_street());
        setParam.execute();

        setParam.setString(1, "P_POST_ADDRESS_HOUSE");
        setParam.setString(2, customer.getP_post_address_house());
        setParam.execute();

        setParam.setString(1, "P_POST_ADDRESS_FLAT");
        setParam.setString(2, customer.getP_post_address_flat());
        setParam.execute();

        setParam.setString(1, "P_POST_ADDRESS_QUARTER");
        setParam.setString(2, customer.getP_post_address_quarter());
        setParam.execute();

        setParam.setString(1, "PARENT_ID_CLIENT_J");
        setParam.setString(2, customer.getParent_id_client_j());
        setParam.execute();
        
        //if (customer.isCheckedStoplistAtaccama()) {
        //	setParam.setString(1, "NOT_CHECK_ATACCAMA");
        //    setParam.setString(2, "Y");
        //    setParam.execute();
        //}
        
        //2023.04.24 begin
        setParam.setString(1, "P_SIGN_PUBLIC_OFFICIAL");
        setParam.setString(2, customer.getSign_public_official());
        setParam.execute();
        
        setParam.setString(1, "P_PO_JOB_TITLE");
        setParam.setString(2, customer.getPo_job_title());
        setParam.execute();
        
        setParam.setString(1, "P_PO_WELFARE_SOURCE");
        setParam.setString(2, customer.getPo_welfare_source());
        setParam.execute();
        
        setParam.setString(1, "P_PO_OTHER_DATA");
        setParam.setString(2, customer.getPo_other_data());
        setParam.execute();
        //2023.04.24 end
    }

    public Customer synchronize(Customer customer) throws Exception {
        return doAction(1, customer, 3);
    }
    
    private void updateClient_j_director(Connection c, Customer client) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update client_j set director_pinfl=?, director_family=?, director_first_name=?, " +
            		"director_patronymic=?, director_code_gender=?, director_code_region=?, director_code_distr=?, " +
            		"director_code_resident=?, director_phone=? " +
            		"where branch=? and id = ?");
           
            ps.setString(1, client.getP_pinfl());
            ps.setString(2, client.getP_family_local() != null ? client.getP_family_local().toUpperCase() : null );
            ps.setString(3, client.getP_first_name_local() != null ? client.getP_first_name_local().toUpperCase() : null);
            ps.setString(4, client.getP_patronymic_local() != null ? client.getP_patronymic_local().toUpperCase() : null);
            
            ps.setString(5, client.getP_code_gender());
            ps.setString(6, client.getP_code_adr_region());
            ps.setString(7, client.getP_code_adr_distr());
            ps.setString(8, client.getCode_resident());
            ps.setString(9, client.getP_phone_mobile());            
            
            //director_code_gender
            //director_code_region
            //director_code_distr
            //director_code_resident
            //director_phone
            
            ps.setString(10, client.getBranch());
            ps.setString(11, client.getParent_id_client_j());
            
            ps.executeUpdate();
        } finally {
            //DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
    }

    private void updateClient_j_accountant(Connection c, Customer client) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update client_j set accountant_pinfl=?, accountant_family=?, accountant_first_name=?, " +
            		"accountant_patronymic=?, accountant_code_gender=?, accountant_code_region=?, accountant_code_distr=?, " +
            		"accountant_code_resident=?, accountant_phone=? " +
            		"where branch=? and id = ?");
           
            ps.setString(1, client.getP_pinfl());
            ps.setString(2, client.getP_family_local() != null ? client.getP_family_local().toUpperCase() : null);
            ps.setString(3, client.getP_first_name_local() != null ? client.getP_first_name_local().toUpperCase() : null);
            ps.setString(4, client.getP_patronymic_local() != null ? client.getP_patronymic_local().toUpperCase() : null);
            ps.setString(5, client.getP_code_gender());
            ps.setString(6, client.getP_code_adr_region());
            ps.setString(7, client.getP_code_adr_distr());
            ps.setString(8, client.getCode_resident());
            ps.setString(9, client.getP_phone_mobile());            
            ps.setString(10, client.getBranch());
            ps.setString(11, client.getParent_id_client_j());
            
            ps.executeUpdate();
        } finally {
            //DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
    }

}
