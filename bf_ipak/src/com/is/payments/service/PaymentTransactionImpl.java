package com.is.payments.service;

import com.is.ConnectionPool;
import com.is.customer_.ActionsEnum;
import com.is.customer_.core.dao.CustomerDao;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.InternalControl;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.RoleInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.impl.RoleInterfaceImpl;
import com.is.payments.dao.CustomerDaoImpl;
import com.is.payments.dao.TransactionDaoImpl;
import com.is.payments.dao.TransactionDao_;
import com.is.payments.entity.Payment;
import com.is.utils.CheckNull;
import general.General;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 24.05.2017.
 * 16:21
 */
public class PaymentTransactionImpl implements PaymentTransactionInterface{
    private final static Logger logger = Logger.getLogger(PaymentTransactionImpl.class);

    private TransactionDao_ transactionDao_;
    private CustomerDao customerDao;
    private RoleInterface roleInterface;
    private BusinessPartnerInterface service = SAPServiceFactory.getInstance().getBusinessPartnerService();
    private SessionAttributes sessionAttributes;

    public PaymentTransactionImpl(TransactionDao_ paymentTransactionDao,
                                     CustomerDao customerDao,
                                     RoleInterface roleInterface,
                                     SessionAttributes sessionAttributes){
        this.transactionDao_ = paymentTransactionDao;
        this.customerDao = customerDao;
        this.roleInterface = roleInterface;
        this.sessionAttributes = sessionAttributes;
    }

    public PaymentTransactionImpl(SessionAttributes sessionAttributes){
        this.roleInterface = new RoleInterfaceImpl();
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public List<General> commitTransaction(Payment payment) throws Exception {
        payment.validate();

        List<General> transactions = new ArrayList<General>();
        Connection c = null;
        try {
            c = getConnection();
            initDaos(c);
            transactions = transactionDao_.commitTransaction(payment);
            Customer customer = customerDao.createCustomer
                    (payment.getReceiver().getCustomer());
            transactionDao_.attachPaymentToCustomer(customer);

            String customerId = customer.getId();
            customer.setId("N" + customerId);
            try {
                InternalControl internalControl = new InternalControl();
                internalControl.setRiskLevel("1");
                customer.setInternalControl(internalControl);
                if (customer.getIdSap() == null) {
                    service.create(customer);
                } else {
                    service.update(customer);
                }
                if (!EmergencyMode.isTrue) {
                    roleInterface.createRole(customer, ActionsEnum.EVENT_ACTION_PAYMENT_OPERATION.value());
                }
            }
            catch (Exception e){
                logger.error(CheckNull.getPstr(e));
            }
            finally {
                customer.setId(customerId);
            }
            // Создание роли в SAP
            c.commit();
        }
        catch (Exception e){
            c.rollback();
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e);
        }
        finally {
            System.out.print("Finally block has been reached");
            ConnectionPool.close(c);
        }
        return transactions;
    }

    private void initDaos(Connection c) {
        this.transactionDao_ = new TransactionDaoImpl(c);
        this.customerDao = new CustomerDaoImpl(c,sessionAttributes);
    }

    private Connection getConnection() {
        return ConnectionPool.getConnection(sessionAttributes.getUsername(),
                                         sessionAttributes.getPassword(),
                                         sessionAttributes.getSchema());
    }

    private void validate(Payment payment){
        payment.validate();
    }
}

