package com.is.customer_.local.service;

import com.is.ConnectionPool;
import com.is.customer_.ActionsEnum;
import com.is.customer_.core.ConnectionUtils;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.dao.impl.AbstractLocalCustomerService;
import com.is.customer_.local.validator.CustomerValidator;
import com.is.customer_.sap.PARTNER_TYPES;
import com.is.customer_.sap.service.CustomerGatewayDecorator;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.transactionLogger.TransactionLogger;
import com.is.customer_.sap.transactionLogger.TransactionLoggerBuilder;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11.06.2017. 14:17
 */
public class CustomerActionService extends AbstractLocalCustomerService
		implements CustomerActionInterface {
	private final static Logger logger = Logger
			.getLogger(CustomerActionService.class);
	private final static String GET_SQL = "SELECT * FROM V_CLIENT_SAP "
			+ "WHERE BRANCH = ? AND ID_CLIENT = ? AND CODE_SUBJECT = ?";

	private CustomerGatewayDecorator customerGatewayDecorator = SAPServiceFactory
			.getInstance().getCustomerGatewayDecorator();

	private final SessionAttributes sessionAttributes;

	private CustomerActionService(SessionAttributes sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	public static CustomerActionService getInstance(
			SessionAttributes sessionAttributes) {
		return new CustomerActionService(sessionAttributes);
	}

	@Override
	public Customer getCustomer(String branch, String id) {
		Connection c = null;
		Customer customer = null;
		try {
			c = ConnectionUtils.getInstance().getConnectionByBranch(branch);
			customer = getCustomer(c, branch, id);
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			try {
				ConnectionUtils.getInstance().close(c);
			} catch (SQLException e) {
				logger.error(CheckNull.getPstr(e));
			}
		}
		return customer;
	}

	@Override
	public Customer getCustomer(Connection c, String branch, String id) {
		Customer customer = new Customer();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = c.prepareStatement(GET_SQL);
			preparedStatement.setString(1, branch);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, "P");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				customer = mapResultSetToCustomer(resultSet);
			// resultSet.close();
			// preparedStatement.close();
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			GeneralUtils.closeStatement(preparedStatement);
			GeneralUtils.closeResultSet(resultSet);
		}
		return customer;
	}

	@Override
	public Customer getCustomer(Connection c, String branch, String id,
			String code_subject) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = new Customer();
		try {
			preparedStatement = c.prepareStatement(GET_SQL);
			preparedStatement.setString(1, branch);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, code_subject);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				customer = mapResultSetToCustomer(resultSet);
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			GeneralUtils.closeResultSet(resultSet);
			GeneralUtils.closeStatement(preparedStatement);
		}
		return customer;
	}

	@Override
	public Customer getCustomer(String branch, String id, String code_subject) {
		Connection c = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;
		try {
			c = ConnectionUtils.getInstance().getConnectionByBranch(branch);
			preparedStatement = c.prepareStatement(GET_SQL);
			preparedStatement.setString(1, branch);
			preparedStatement.setString(2, id);
			preparedStatement.setString(3, code_subject);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				customer = mapResultSetToCustomer(resultSet);
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			try {
				GeneralUtils.closeStatement(preparedStatement);
				GeneralUtils.closeResultSet(resultSet);
				ConnectionUtils.getInstance().close(c);
			} catch (SQLException e) {
				logger.error(CheckNull.getPstr(e));
			}
		}
		return customer;
	}

	private Customer mapResultSetToCustomer(ResultSet resultSet)
			throws SQLException {
		return new CustomerBuilder()
				.setLongId(resultSet.getLong("id"))
				.setBranch(resultSet.getString("branch"))
				.setId(resultSet.getString("id_client"))
				.setName(resultSet.getString("name"))
				.setCode_country(resultSet.getString("code_country"))
				.setCode_type(resultSet.getString("code_type"))
				.setCode_resident(resultSet.getString("code_resident"))
				.setCode_subject(resultSet.getString("code_subject"))
				.setSign_registr(resultSet.getInt("sign_registr"))
				.setCode_form(resultSet.getString("code_form"))
				.setDate_open(resultSet.getDate("date_open"))
				.setDate_close(resultSet.getDate("date_close"))
				.setState(resultSet.getString("state"))
				.setKod_err(resultSet.getInt("kod_err"))
				.setFile_name(resultSet.getString("file_name"))
				.setP_birthday(resultSet.getDate("p_birthday"))
				.setP_post_address(resultSet.getString("p_post_address"))
				.setP_passport_type(resultSet.getString("p_passport_type"))
				.setP_passport_serial(resultSet.getString("p_passport_serial"))
				.setP_passport_number(resultSet.getString("p_passport_number"))
				.setP_passport_place_registration(
						resultSet.getString("p_passport_place_registration"))
				.setP_passport_date_registration(
						resultSet.getDate("p_passport_date_registration"))
				.setP_code_tax_org(resultSet.getString("p_code_tax_org"))
				.setP_number_tax_registration(
						resultSet.getString("p_number_tax_registration"))
				.setP_code_bank(resultSet.getString("p_code_bank"))
				.setP_code_class_credit(
						resultSet.getString("p_code_class_credit"))
				.setP_code_citizenship(
						resultSet.getString("p_code_citizenship"))
				.setP_birth_place(resultSet.getString("p_birth_place"))
				.setP_code_capacity(resultSet.getString("p_code_capacity"))
				.setP_capacity_status_date(
						resultSet.getDate("p_capacity_status_date"))
				.setP_capacity_status_place(
						resultSet.getString("p_capacity_status_place"))
				.setP_num_certif_capacity(
						resultSet.getString("p_num_certif_capacity"))
				.setP_pension_sertif_serial(
						resultSet.getString("p_pension_sertif_serial"))
				.setP_phone_home(resultSet.getString("p_phone_home"))
				.setP_phone_mobile(resultSet.getString("p_phone_mobile"))
				.setP_email_address(resultSet.getString("p_email_address"))
				.setP_code_gender(resultSet.getString("p_code_gender"))
				.setP_code_nation(resultSet.getString("p_code_nation"))
				.setP_code_birth_region(
						resultSet.getString("p_code_birth_region"))
				.setP_code_birth_distr(
						resultSet.getString("p_code_birth_distr"))
				.setP_type_document(resultSet.getString("p_type_document"))
				.setP_passport_date_expiration(
						resultSet.getDate("p_passport_date_expiration"))
				.setP_code_adr_region(resultSet.getString("p_code_adr_region"))
				.setP_code_adr_distr(resultSet.getString("p_code_adr_distr"))
				.setP_inps(resultSet.getString("p_inps"))
				.setP_pinfl(resultSet.getString("p_pinfl"))
				.setP_family(resultSet.getString("p_family"))
				.setP_first_name(resultSet.getString("p_first_name"))
				.setP_patronymic(resultSet.getString("p_patronymic"))
				.setP_family_local(resultSet.getString("p_last_name_cyr"))
				.setP_first_name_local(resultSet.getString("p_first_name_cyr"))
				.setP_patronymic_local(resultSet.getString("p_patronymic_cyr"))
				.setP_pass_place_region(
						resultSet.getString("p_pass_place_region"))
				.setP_pass_place_district(
						resultSet.getString("p_pass_place_district"))
				.setP_post_address_quarter(
						resultSet.getString("p_post_address_quarter"))
				.setP_post_address_street(
						resultSet.getString("p_post_address_street"))
				.setP_post_address_house(
						resultSet.getString("p_post_address_house"))
				.setP_post_address_flat(
						resultSet.getString("p_post_address_flat"))
				.setSign_100(resultSet.getString("sign_100"))
				.setJ_327(resultSet.getString("j_327"))
				.setP_date_open_nibbd(resultSet.getDate("p_date_open_nibbd"))
				.setP_date_close_nibbd(resultSet.getDate("p_date_close_nibbd"))
				.setP_id_nibbd(resultSet.getString("p_id_nibbd"))
				.setP_close_type(resultSet.getString("p_close_type"))
				.setP_close_doc_n(resultSet.getString("p_close_doc_n"))
				.setP_close_doc_d(resultSet.getDate("p_close_doc_d"))
				.setP_drive_permit_num(
						resultSet.getString("p_drive_permit_num"))
				.setP_drive_permit_ser(
						resultSet.getString("p_drive_permit_ser"))
				.setP_drive_permit_reg_d(
						resultSet.getDate("p_drive_permit_reg_d"))
				.setP_drive_permit_exp_d(
						resultSet.getDate("p_drive_permit_exp_d"))
				.setP_drive_permit_place(
						resultSet.getString("p_drive_permit_place"))
				.setP_agreement(resultSet.getString("p_agreement"))
				.setSubbranch(resultSet.getString("subbranch"))
				.createCustomer();
	}

	@Override
	public Customer executeAction(Customer actionCustomer_, int action)
			throws Exception {
		if (action != ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER.action()) {
			CustomerValidator customerValidator = CustomerValidator
					.getInstance(actionCustomer_, sessionAttributes);
			if (!customerValidator.validate())
				throw new RuntimeException(customerValidator.getMessage());
		}
		Connection c = null;
		Customer customerDb = new Customer();
		String idSAP = actionCustomer_.getIdSap();
		try {
			c = ConnectionPool.getConnection(sessionAttributes.getUsername(),
					sessionAttributes.getPassword(),
					sessionAttributes.getSchema());
			customerDb = executeActionC(c, actionCustomer_, action);
			c.commit();
		} catch (Exception e) {
			c.rollback();
			logger.error(CheckNull.getPstr(e));
			throw e;
		} finally {
			if (customerDb.getIdSap() == null)
				customerDb.setIdSap(idSAP);

			ConnectionPool.close(c);
		}
		return customerDb;
	}

	@Override
	public Customer executeActionC(Connection c,
			final Customer actionCustomer_, final int action) throws Exception {
		//logger.error("CustomerActionService ... -> " + actionCustomer_);

		String idSAP = null;
		String id = executeAction(c, actionCustomer_, action);
		id = id == null ? actionCustomer_.getId() : id;

		Customer customerDescription = (Customer) actionCustomer_.clone();
		customerDescription.setBranch(actionCustomer_.getBranch());
		customerDescription.setId(id);

		if (action == ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER.action()) {
			deleteCustomerDescription(c, customerDescription);
		} else {
			updateCustomerDescription(c, customerDescription);
		}

		Customer customerDb = getCustomer(c, actionCustomer_.getBranch(), id);
        /*2022.06.07*/
        //customerDb.setEmp_id(""+sessionAttributes.getUid());
        customerDb.setEmp_id(actionCustomer_.getEmp_id());
        /*end 2022.06.07*/

		// logger.error("Action " + action);
		if (action != ActionsEnum.ACTION_CREATE_BUSINESS_PARTNER.action()
		// && action != ActionsEnum.ACTION_CLOSE_BUSINESS_PARTNER.action()
				&& action != ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER
						.action()
				&& action != ActionsEnum.ACTION_CORRECT_PHONE.action()) {
			fillInternalControlForm(c, customerDb);
			customerDb.setIdSap(actionCustomer_.getIdSap());
			TransactionLogger transactionLogger = new TransactionLoggerBuilder()
					.setCustomer(customerDb)
					.setAction(
							action != 9999 ? action
									: ActionsEnum.ACTION_CONFIRM_BUSINESS_PARTNER
											.action())
					.setCustomer_type(PARTNER_TYPES.CUSTOMER.value())
					.setSessionAttributes(sessionAttributes).build();

			if (!transactionLogger.isEmergencyModeOn()) {
				try {
					if (action == ActionsEnum.ACTION_CONFIRM_CLOSED_BUSINESS_PARTNER
							.action()) {
						customerDb.setP_phone_mobile(null);
						customerDb.setP_phone_home(null);
					}
					if (actionCustomer_.isForceCreated()) {
						idSAP = customerGatewayDecorator.create(customerDb,
								"2", "2").getIdSap();
					} else
						idSAP = customerGatewayDecorator.process(customerDb);
					transactionLogger.logSuccessTransaction();
					customerDb.setSign_100("");
					customerDb.setIdSap(idSAP);

					if (customerDb.isI014())
						callReactionSAP(c, customerDb);

				} catch (Exception e) {
					transactionLogger.logErrorTransaction(e.getMessage());
					if (!transactionLogger.isErrorConsumed())
						throw e;
				}
			} else
				transactionLogger.logEmergencyTransaction();
		}
		return customerDb;
	}

	@Override
	public String executeActionOtherBranch(Customer actionCustomer_, int action)
			throws Exception {

		Connection c = null;
		String branchs = "";
		String br_ = "";
		// todo;
		// qaysi filiallarda shu klient mavjudligini topamiz
		// iloji bulsa faqatgina pasport malumotlari farq qiladiganlarini
		// topamiz, bir xil bulsa ignore qilamiz
		List<Customer> clientList = getOtherBranchClients(actionCustomer_, sessionAttributes.getSchema());
		// usha filial(lar)ga info.init qilib doaction qilamiz

		for (int i = 0; i < clientList.size(); i++) {
			try {
				c = ConnectionUtils.getInstance().getConnectionByBranch(
						clientList.get(i).getBranch());
                Customer cust = getCustomer(c, clientList.get(i).getBranch(), clientList.get(i).getId());
                
                cust.setP_type_document(actionCustomer_.getP_type_document());
                cust.setP_passport_serial(actionCustomer_.getP_passport_serial());
                cust.setP_passport_number(actionCustomer_.getP_passport_number());
                cust.setP_passport_place_registration(actionCustomer_.getP_passport_place_registration());
                cust.setP_passport_date_registration(actionCustomer_.getP_passport_date_registration());
                cust.setP_passport_date_expiration(actionCustomer_.getP_passport_date_expiration());

                cust.setP_family_local(actionCustomer_.getP_family_local());
                cust.setP_first_name_local(actionCustomer_.getP_first_name_local());
                cust.setP_patronymic_local(actionCustomer_.getP_patronymic_local());
                cust.setP_pass_place_region(actionCustomer_.getP_pass_place_region());
                cust.setP_pass_place_district(actionCustomer_.getP_pass_place_district());
                cust.setP_family(actionCustomer_.getP_family());
                cust.setP_first_name(actionCustomer_.getP_first_name());
                cust.setP_patronymic(actionCustomer_.getP_patronymic());
                cust.setP_code_nation(actionCustomer_.getP_code_nation());
                cust.setP_code_citizenship(actionCustomer_.getP_code_citizenship());
                cust.setCode_resident(actionCustomer_.getCode_resident());
                cust.setP_code_gender(actionCustomer_.getP_code_gender());
                cust.setP_birthday(actionCustomer_.getP_birthday());
                cust.setP_birth_place(actionCustomer_.getP_birth_place());
                cust.setCode_country(actionCustomer_.getCode_country());
                cust.setP_code_adr_region(actionCustomer_.getP_code_adr_region());
                cust.setP_code_adr_distr(actionCustomer_.getP_code_adr_distr());
                cust.setP_post_address(actionCustomer_.getP_post_address());

				br_ = executeActionOtherBranchC(c, cust, action);
				c.commit();
				if ( branchs.length()>0 )
					branchs=branchs+",";
				branchs=branchs+br_;
			} catch (Exception e) {
				c.rollback();
				logger.error(CheckNull.getPstr(e));
				throw e;
			} finally {
				
				ConnectionUtils.getInstance().close(c);
				ConnectionPool.close(c);
			}
		}

		return branchs;
	}

	@Override
	public String executeActionOtherBranchC(Connection c,  final Customer actionCustomer_, final int action) throws Exception {

		//String idSAP = null;
		String branch_ = executeActionOtherBranch(c, actionCustomer_, action);
		String id = actionCustomer_.getId();

		Customer customerDescription = (Customer) actionCustomer_.clone();
		customerDescription.setBranch(actionCustomer_.getBranch());
		customerDescription.setId(id);

		/*if (action == ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER.action()) {
			deleteCustomerDescription(c, customerDescription);
		} else {*/
			updateCustomerDescription(c, customerDescription);
		/*}*/

		//Customer customerDb = getCustomer(c, actionCustomer_.getBranch(), id);

		/*if (action != ActionsEnum.ACTION_CREATE_BUSINESS_PARTNER.action()
				&& action != ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER
						.action()
				&& action != ActionsEnum.ACTION_CORRECT_PHONE.action()) {
			fillInternalControlForm(c, customerDb);
			customerDb.setIdSap(actionCustomer_.getIdSap());
			TransactionLogger transactionLogger = new TransactionLoggerBuilder()
					.setCustomer(customerDb)
					.setAction(
							action != 9999 ? action
									: ActionsEnum.ACTION_CONFIRM_BUSINESS_PARTNER
											.action())
					.setCustomer_type(PARTNER_TYPES.CUSTOMER.value())
					.setSessionAttributes(sessionAttributes).build();

			if (!transactionLogger.isEmergencyModeOn()) {
				try {
					if (action == ActionsEnum.ACTION_CONFIRM_CLOSED_BUSINESS_PARTNER
							.action()) {
						customerDb.setP_phone_mobile(null);
						customerDb.setP_phone_home(null);
					}
					if (actionCustomer_.isForceCreated()) {
						idSAP = customerGatewayDecorator.create(customerDb,
								"2", "2").getIdSap();
					} else
						idSAP = customerGatewayDecorator.process(customerDb);
					transactionLogger.logSuccessTransaction();
					customerDb.setSign_100("");
					customerDb.setIdSap(idSAP);

					if (customerDb.isI014())
						callReactionSAP(c, customerDb);

				} catch (Exception e) {
					transactionLogger.logErrorTransaction(e.getMessage());
					if (!transactionLogger.isErrorConsumed())
						throw e;
				}
			} else
				transactionLogger.logEmergencyTransaction();
		}*/
		return branch_;
	}

	public List<Customer> getOtherBranchClients(Customer p_customer,
			String alias) {

		List<Customer> list = new ArrayList<Customer>();

		Connection c = null;
		// int params;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from v_client_p_all_pinfl where id_nibbd=? and pinfl=? and branch!=? ");
		sql.append("and (type_document!=? or passport_serial!=? or passport_number!=? ");
		sql.append("or passport_place_registration!=? or passport_date_registration!=? or passport_date_expiration!=? or post_address!=?) ");
		sql.append("order by branch");

		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();

			PreparedStatement ps = c.prepareStatement(sql.toString());

			ps.setString(1, p_customer.getP_id_nibbd());
			ps.setString(2, p_customer.getP_pinfl());
			ps.setString(3, p_customer.getBranch());
			ps.setString(4, p_customer.getP_type_document());
			ps.setString(5, p_customer.getP_passport_serial());
			ps.setString(6, p_customer.getP_passport_number());
			ps.setString(7, p_customer.getP_passport_place_registration());
			ps.setDate(8, CheckNull.d2sql((java.util.Date) p_customer
					.getP_passport_date_registration()));
			ps.setDate(9, CheckNull.d2sql((java.util.Date) p_customer
					.getP_passport_date_expiration()));
			ps.setString(10, p_customer.getP_post_address());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer partner = new Customer();
				partner.setBranch(rs.getString("branch"));
				partner.setId(rs.getString("id"));
				list.add(partner);
			}
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public Customer synchronize(Customer actionCustomer) throws Exception {
		return executeAction(actionCustomer,
				ActionsEnum.ACTION_CORRECT_BUSINESS_PARTNER.action());
	}

	private void fillInternalControlForm(Connection c, Customer customer)
			throws Exception {
		customer.setInternalControl(InternalControlService
				.getInternalControlForm(c, customer,
						sessionAttributes.getSchema()));
	}

	private void callReactionSAP(Connection c, Customer customer) {
		CallableStatement callableStatement = null;
		CallableStatement setParam = null;
		try {
			setParam = c.prepareCall("{call param.setParam(?,?)}");
			setParam.setString(1, "ID_CLIENT");
			setParam.setString(2, customer.getId());
			setParam.execute();
			callableStatement = c
					.prepareCall("{call reaction_sap.onClientPApprove()}");
			callableStatement.execute();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			GeneralUtils.closeStatement(callableStatement);
			GeneralUtils.closeStatement(setParam);
		}
	}
}
