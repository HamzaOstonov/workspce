package com.is.customer_.local.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types; 

import com.is.customer_.ActionsEnum;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;

/**
 * Created by root on 10.05.2017.
 * 12:32
 */
public abstract class AbstractLocalCustomerService {
    protected String executeAction(Connection c, Customer customer, int action) throws SQLException {
        CallableStatement setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
        CallableStatement doAction = c.prepareCall("{ call kernel.doAction(?,?,?) }");

        CallableStatement clearParam = c.prepareCall("{ call Param.clearparam() }");
        clearParam.execute();

        CallableStatement getParam = c.prepareCall("{? = call Param.getparam('ID')}");
        getParam.registerOutParameter(1, Types.VARCHAR);

		/**/
        initParams(setParam, customer, action);


        if (action != 9999) {
            doAction.setInt(1, 1);
            doAction.setInt(2, 2);
            doAction.setInt(3, action); // Если 1, то создаем нового клиента
        }
        else
        {
            // если вызывается с пакетного открытия 208 2 2
            setParam.setString(1,"P_ACC_BAL");
            setParam.setString(2,customer.getAccount().getAcc_bal());
            setParam.execute();
            setParam.setString(1,"P_ID_ORDER");
            setParam.setString(2,customer.getAccount().getId_order());
            setParam.execute();
            doAction.setInt(1,208);
            doAction.setInt(2,2);
            doAction.setInt(3,2);
        }

        doAction.execute();
        getParam.execute();
        String ID = getParam.getString(1);

        ID = getId(c, ID);

        setParam.close();
        doAction.close();
        clearParam.close();
        getParam.close();

        return ID;
    }

    protected void initParams(CallableStatement setParam, Customer client, int action) throws SQLException {
        if (action != ActionsEnum.ACTION_CREATE_BUSINESS_PARTNER.action()) {
            if (client.getLongId() != null) {
                setParam.setString(1, "ID");
                setParam.setLong(2, client.getLongId());
                setParam.execute();
            }
            if (client.getId() != null) {
                setParam.setString(1, "ID_CLIENT");
                setParam.setString(2, client.getId());
                setParam.execute();
            }
        }
        setParam.setString(1, "BRANCH");
        setParam.setString(2, client.getBranch());
        setParam.execute();

        setParam.setString(1, "NAME");
        setParam.setString(2, client.getFullName().toUpperCase());
        setParam.execute();
        if (client.getP_type_document() != null) {
            setParam.setString(1, "P_TYPE_DOCUMENT");
            setParam.setString(2, client.getP_type_document().toUpperCase());
        }
        setParam.execute();
        setParam.setString(1, "CODE_COUNTRY");
        setParam.setString(2, client.getCode_country());
        setParam.execute();
        setParam.setString(1, "CODE_TYPE");
        setParam.setString(2, client.getCode_type());
        setParam.execute();
        setParam.setString(1, "CODE_RESIDENT");
        setParam.setString(2, client.getCode_resident());
        setParam.execute();
        setParam.setString(1, "CODE_SUBJECT");
        setParam.setString(2, client.getCode_subject());
        setParam.execute();
        setParam.setString(1, "SIGN_REGISTR");
        setParam.setString(2, client.getSign_registr() + "");
        setParam.execute();
        setParam.setString(1, "CODE_FORM");
        setParam.setString(2, client.getCode_form());
        setParam.execute();
        setParam.setString(1, "DATE_OPEN");
        setParam.setString(2, CustomerUtils.dateToString(client.getDate_open()));
        setParam.execute();
        setParam.setString(1, "DATE_CLOSE");
        setParam.setString(2, CustomerUtils.dateToString(client.getDate_close()));
        setParam.execute();
        if (!(client.getState() == null || client.getState().isEmpty())) {
            setParam.setString(1, "STATE");
            setParam.setInt(2, Integer.parseInt(client.getState()));
        }
        setParam.execute();
        setParam.setString(1, "P_BIRTHDAY");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_birthday()));
        setParam.execute();
        setParam.setString(1, "P_POST_ADDRESS");
        setParam.setString(2, client.getP_post_address() != null ? client.getP_post_address().toUpperCase() : client.getP_post_address());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_TYPE");
        setParam.setString(2, client.getP_passport_type());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_SERIAL");
        setParam.setString(2, client.getP_passport_serial() != null ?
                client.getP_passport_serial().toUpperCase() : client.getP_passport_serial());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_NUMBER");
        setParam.setString(2, client.getP_passport_number());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
        setParam.setString(2, client.getP_passport_place_registration() != null ? client.getP_passport_place_registration().toUpperCase() : client.getP_passport_place_registration());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_DATE_REGISTRATION");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_passport_date_registration()));
        setParam.execute();
        setParam.setString(1, "P_CODE_TAX_ORG");
        setParam.setString(2, client.getP_code_tax_org());
        setParam.execute();
        setParam.setString(1, "P_NUMBER_TAX_REGISTRATION");
        setParam.setString(2, client.getP_number_tax_registration());
        setParam.execute();
        setParam.setString(1, "P_CODE_BANK");
        setParam.setString(2, client.getP_code_bank());
        setParam.execute();
        setParam.setString(1, "P_CODE_CLASS_CREDIT");
        setParam.setString(2, client.getP_code_class_credit());
        setParam.execute();
        setParam.setString(1, "P_CODE_CITIZENSHIP");
        setParam.setString(2, client.getP_code_citizenship());
        setParam.execute();
        setParam.setString(1, "P_BIRTH_PLACE");
        setParam.setString(2, client.getP_birth_place() != null ? client.getP_birth_place().toUpperCase() : client.getP_birth_place());
        setParam.execute();
        setParam.setString(1, "P_CODE_CAPACITY");
        setParam.setString(2, client.getP_code_capacity());
        setParam.execute();
        setParam.setString(1, "P_CAPACITY_STATUS_DATE");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_capacity_status_date()));
        setParam.execute();
        setParam.setString(1, "P_CAPACITY_STATUS_PLACE");
        setParam.setString(2, client.getP_capacity_status_place() != null ? client.getP_capacity_status_place().toUpperCase() : client.getP_capacity_status_place());
        setParam.execute();
        setParam.setString(1, "P_NUM_CERTIF_CAPACITY");
        setParam.setString(2, client.getP_num_certif_capacity());
        setParam.execute();
        setParam.setString(1, "P_PHONE_HOME");
        setParam.setString(2, client.getP_phone_home());
        setParam.execute();
        setParam.setString(1, "P_PHONE_MOBILE");
        setParam.setString(2, client.getP_phone_mobile());
        setParam.execute();
        setParam.setString(1, "P_EMAIL_ADDRESS");
        setParam.setString(2, client.getP_email_address() != null ?
                client.getP_email_address().toLowerCase() : client.getP_email_address());
        setParam.execute();
        setParam.setString(1, "P_PENSION_SERTIF_SERIAL");
        setParam.setString(2, client.getP_pension_sertif_serial() != null ? client.getP_pension_sertif_serial()
                .toUpperCase() : client.getP_pension_sertif_serial());
        setParam.execute();
        setParam.setString(1, "P_CODE_GENDER");
        setParam.setString(2, client.getP_code_gender());
        setParam.execute();
        setParam.setString(1, "P_CODE_NATION");
        setParam.setString(2, client.getP_code_nation());
        setParam.execute();
        setParam.setString(1, "P_CODE_BIRTH_REGION");
        setParam.setString(2, client.getP_code_birth_region());
        setParam.execute();
        setParam.setString(1, "P_CODE_BIRTH_DISTR");
        setParam.setString(2, client.getP_code_birth_distr());
        setParam.execute();
        setParam.setString(1, "P_TYPE_DOCUMENT");
        setParam.setString(2, client.getP_type_document());
        setParam.execute();
        setParam.setString(1, "P_PASSPORT_DATE_EXPIRATION");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_passport_date_expiration()));
        setParam.execute();
        setParam.setString(1, "P_CODE_ADR_REGION");
        setParam.setString(2, client.getP_code_adr_region());
        setParam.execute();
        setParam.setString(1, "P_CODE_ADR_DISTR");
        setParam.setString(2, client.getP_code_adr_distr());
        setParam.execute();
        setParam.setString(1, "P_INPS");
        setParam.setString(2, client.getP_inps());
        setParam.execute();
        setParam.setString(1, "P_PINFL");
        setParam.setString(2, client.getP_pinfl());
        setParam.execute();
        setParam.setString(1, "P_FAMILY");
        setParam.setString(2, client.getP_family() != null ? client.getP_family().toUpperCase() : "");
        setParam.execute();
        setParam.setString(1, "P_FIRST_NAME");
        setParam.setString(2, client.getP_first_name() != null ? client.getP_first_name().toUpperCase() : "");
        setParam.execute();
        setParam.setString(1, "P_PATRONYMIC");
        setParam.setString(2, (client.getP_patronymic() != null) ? client.getP_patronymic().toUpperCase() : null);
        setParam.execute();
        
        setParam.setString(1, "P_DRIVE_PERMIT_SER");
        setParam.setString(2, client.getP_drive_permit_ser());
        setParam.execute();
        setParam.setString(1, "P_DRIVE_PERMIT_NUM");
        setParam.setString(2, client.getP_drive_permit_num());
        setParam.execute();
        setParam.setString(1, "P_DRIVE_PERMIT_REG_D");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_drive_permit_reg_d()));
        setParam.execute();
        setParam.setString(1, "P_DRIVE_PERMIT_EXP_D");
        setParam.setString(2, CustomerUtils.dateToString(client.getP_drive_permit_exp_d()));
        setParam.execute();
        setParam.setString(1, "P_DRIVE_PERMIT_PLACE");
        setParam.setString(2, client.getP_drive_permit_place());
        setParam.execute();
        setParam.setString(1, "P_AGREEMENT");
        setParam.setString(2, client.getP_agreement());
        setParam.execute();

        /*2022.03.02*/
        setParam.setString(1, "P_FAMILY_LOCAL");
        setParam.setString(2, client.getP_family_local() != null ? client.getP_family_local().toUpperCase() : "");
        setParam.execute();
        setParam.setString(1, "P_FIRST_NAME_LOCAL");
        setParam.setString(2, client.getP_first_name_local() != null ? client.getP_first_name_local().toUpperCase() : "");
        setParam.execute();
        setParam.setString(1, "P_PATRONYMIC_LOCAL");
        setParam.setString(2, (client.getP_patronymic_local() != null) ? client.getP_patronymic_local().toUpperCase() : null);
        setParam.execute();
        setParam.setString(1, "EMP_ID");
        setParam.setString(2, (client.getEmp_id() != null) ? client.getEmp_id() : null);
        setParam.execute();
        /*end 2022.03.02*/
        /*2023.05.02*/
        setParam.setString(1, "FILE_NAME");
        setParam.setString(2, client.getFile_name());
        setParam.execute();

        setParam.setString(1, "SUBBRANCH");
        setParam.setString(2, client.getSubbranch());
        setParam.execute();
        /*end 2023.05.02*/
    }

    protected String executeActionOtherBranch(Connection c, Customer customer, int action) throws SQLException {
    	
        CallableStatement setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
        CallableStatement doAction = c.prepareCall("{ call kernel.doAction(?,?,?) }");

        CallableStatement clearParam = c.prepareCall("{ call Param.clearparam() }");
        clearParam.execute();

        CallableStatement getParam = c.prepareCall("{? = call Param.getparam('ID')}");
        getParam.registerOutParameter(1, Types.VARCHAR);
	
        initParams(setParam, customer, action);

        if (action != 9999) {
            doAction.setInt(1, 1);
            doAction.setInt(2, 2);
            doAction.setInt(3, action); // Если 1, то создаем нового клиента
        }/*
        else
        {
            // если вызывается с пакетного открытия 208 2 2
            setParam.setString(1,"P_ACC_BAL");
            setParam.setString(2,customer.getAccount().getAcc_bal());
            setParam.execute();
            setParam.setString(1,"P_ID_ORDER");
            setParam.setString(2,customer.getAccount().getId_order());
            setParam.execute();
            doAction.setInt(1,208);
            doAction.setInt(2,2);
            doAction.setInt(3,2);
        }*/

        doAction.execute();
        getParam.execute();
        //String ID = getParam.getString(1);

        //ID = getId(c, ID);

        setParam.close();
        doAction.close();
        clearParam.close();
        getParam.close();

        //return ID;
        return customer.getBranch();
    }
    
    private void insertCustomerDescription(Connection c, Customer customer) throws SQLException {
        PreparedStatement ps = c.prepareStatement("INSERT INTO CLIENT_DESC"
                + " (BRANCH,ID_CLIENT,LAST_NAME_CYR,FIRST_NAME_CYR,PATRONYMIC_CYR,CODE_TYPE,PASS_PLACE_REGION,PASS_PLACE_DISTRICT,POST_ADDRESS_STREET,POST_ADDRESS_HOUSE,POST_ADDRESS_FLAT,POST_ADDRESS_QUARTER)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, customer.getBranch());
        ps.setString(2, customer.getId());
        ps.setString(3, customer.getP_family_local() != null ?
                customer.getP_family_local().toUpperCase() : null);
        ps.setString(4, customer.getP_first_name_local() != null ?
                customer.getP_first_name_local().toUpperCase() : null);
        ps.setString(5, customer.getP_patronymic_local() != null ?
                customer.getP_patronymic_local().toUpperCase() : null);
        ps.setString(6, customer.getCode_type());
        ps.setString(7, customer.getP_pass_place_region());
        ps.setString(8, customer.getP_pass_place_district());
        ps.setString(9, customer.getP_post_address_street());
        ps.setString(10, customer.getP_post_address_house());
        ps.setString(11, customer.getP_post_address_flat());
        ps.setString(12, customer.getP_post_address_quarter());
        ps.executeUpdate();
        ps.close();
    }

    protected void updateCustomerDescription(Connection c, Customer customer) throws SQLException {
        if (!isCustomerDescriptionExists(c, customer)) {
            insertCustomerDescription(c, customer);
            return;
        }
        PreparedStatement ps = c.prepareStatement("UPDATE CLIENT_DESC "
                + "SET BRANCH = ?," +
                "LAST_NAME_CYR = ?," +
                "FIRST_NAME_CYR = ?," +
                "PATRONYMIC_CYR = ?,CODE_TYPE = ?,PASS_PLACE_REGION = ?, PASS_PLACE_DISTRICT = ?, POST_ADDRESS_STREET = ?,POST_ADDRESS_HOUSE = ?,POST_ADDRESS_FLAT = ?,POST_ADDRESS_QUARTER = ?"
                + "WHERE BRANCH = ? and ID_CLIENT = ?");
        ps.setString(1, customer.getBranch());
        ps.setString(2, customer.getP_family_local() != null ? customer.getP_family_local().toUpperCase() : null);
        ps.setString(3, customer.getP_first_name_local() != null ?
                customer.getP_first_name_local().toUpperCase() : null);
        ps.setString(4, customer.getP_patronymic_local() != null ?
                customer.getP_patronymic_local().toUpperCase() : null);
        ps.setString(5, customer.getCode_type());
        ps.setString(6, customer.getP_pass_place_region());
        ps.setString(7, customer.getP_pass_place_district());
        ps.setString(8, customer.getP_post_address_street());
        ps.setString(9, customer.getP_post_address_house());
        ps.setString(10, customer.getP_post_address_flat());
        ps.setString(11, customer.getP_post_address_quarter());
        ps.setString(12, customer.getBranch());
        ps.setString(13, customer.getId());
        ps.executeUpdate();
        ps.close();
    }

    protected boolean isCustomerDescriptionExists(Connection c, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = c
                .prepareStatement("SELECT COUNT(*) FROM CLIENT_DESC WHERE BRANCH = ? AND ID_CLIENT = ?");
        preparedStatement.setString(1, customer.getBranch());
        preparedStatement.setString(2, customer.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next())
            count = resultSet.getInt(1);

        preparedStatement.close();
        resultSet.close();

        if (count > 0)
            return true;

        return false;
    }

    protected void deleteCustomerDescription(Connection c, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM CLIENT_DESC WHERE BRANCH = ? and ID_CLIENT = ?");
        preparedStatement.setString(1, customer.getBranch());
        preparedStatement.setString(2, customer.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    protected String getId(Connection c, String longId) throws SQLException {
        String id = null;
        String SQL = "SELECT ID_CLIENT FROM V_CLIENT_SAP WHERE ID = ?";
        PreparedStatement ps = c.prepareStatement(SQL);
        ps.setString(1, longId);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            id = rs.getString(1);

        rs.close();
        ps.close();
        return id;
    }
}
