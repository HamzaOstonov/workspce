package com.is.payments.service;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.core.dao.CustomerDao;
import com.is.customer_.core.dao.CustomerDaoException;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.payments.dao.CustomerDaoImpl;
import com.is.utils.CheckNull;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by root on 18.07.2017.
 * 13:05
 */
public class PaymentCustomerService {
    private final SessionAttributes sessionAttributes;

    public PaymentCustomerService(SessionAttributes sessionAttributes){
        this.sessionAttributes = sessionAttributes;
    }

    public void update(Customer customer) throws Exception {
        Connection c = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            CustomerDao dao = new CustomerDaoImpl(c,sessionAttributes);
            dao.updateCustomer(customer);
            try {
                Customer forSAP = (Customer) customer.clone();
                forSAP.setId("N" + customer.getId());
                SAPServiceFactory.getInstance().getBusinessPartnerService().update(forSAP);
            }
            catch (Exception e){
                ISLogger.getLogger().error(CheckNull.getPstr(e));
            }
            c.commit();
        }
        catch (Exception e){
            c.rollback();
            throw e;
        }
        finally {
            ConnectionPool.close(c);
        }
    }

    public Customer getCustomer(String branch,String id){
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection(sessionAttributes.getSchema());
            CustomerDao customerDao = new CustomerDaoImpl(connection,sessionAttributes);
            return customerDao.getCustomer(branch,id);
        } catch (SQLException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } catch (CustomerDaoException e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
            ConnectionPool.close(connection);
        }
        return new Customer();
    }
}
