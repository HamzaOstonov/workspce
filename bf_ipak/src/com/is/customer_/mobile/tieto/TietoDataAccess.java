package com.is.customer_.mobile.tieto;

import com.is.base.utils.DbUtils;
import com.is.customer_.CustomerFactory;
import com.is.customer_.core.ConnectionUtils;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.service.CustomerActionInterface;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;

import java.sql.*;

/**
 * Created by Dev1 on 21.12.2018.
 */
@SuppressWarnings("JpaQueryApiInspection")
public class TietoDataAccess {
    private CustomerActionInterface _customerService;

    private static Logger logger = LogManager.getLogger(TietoDataAccess.class);

    public TietoDataAccess() {
        _customerService = CustomerFactory.getInstance(null).getCustomerActionService();
    }

    public Customer findCustomer(String branch, String id) {
        Customer customer = null;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String headCustomerID = null;
        try {
            c = ConnectionUtils.getInstance().getConnectionByBranch("00444");

            if (branch.equals("00444")) {
                headCustomerID = id;
            } else {
                preparedStatement = c.prepareStatement("select '00444' as branch, head_customer_id as id from bf_tieto_customers where branch = ? and bank_customer_id = ?");
                preparedStatement.setString(1, branch);
                preparedStatement.setString(2, id);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    headCustomerID = resultSet.getString("id");
                }
            }

            if (headCustomerID == null) {
                return null;
            }

            customer = _customerService.getCustomer(c, "00444", headCustomerID);

        } catch (Exception e) {

        } finally {
            GeneralUtils.closeStatement(preparedStatement);
            GeneralUtils.closeResultSet(resultSet);
            try {
                ConnectionUtils.getInstance().close(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }
}
