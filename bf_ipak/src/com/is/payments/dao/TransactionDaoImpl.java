package com.is.payments.dao;

import accounting_transaction.TransactionService;
import com.is.ISLogger;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.kernel.parameter.Parameters;
import com.is.payments.entity.Payment;
import general.General;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 27.05.2017.
 * 14:42
 */
public class TransactionDaoImpl implements TransactionDao_ {
    private final static  Logger logger = Logger.getLogger(TransactionDaoImpl.class);
    private Connection c;
    private List<General> generalList;

    public TransactionDaoImpl(Connection c){
        this.c = c;
    }

    @Override
    public List<General> commitTransaction(Payment payment) throws Exception{
        generalList = new ArrayList<General>();

        CallableStatement dealStatement = null;
        try{
            TransactionService transactionService = new TransactionService();
            transactionService.init(c);

            dealStatement = accounting_transaction.Service.init_get_deal_general(c);

            Parameters parameters = initParams(payment,dealStatement);

            long transactionId = transactionService.execute_operation(
                    (Long)parameters.get("operation_id"),
                            parameters,
                                c);
            if (transactionId == 0)
                throw new Exception(
                        new Throwable("Transaction Id is 0")
                                    );
            generalList = accounting_transaction.Service.action_general_doc(transactionId,1,c);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            GeneralUtils.closeStatement(dealStatement);
        }
        return generalList;
    }

    @Override
    public void attachPaymentToCustomer(Customer customer) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = c.prepareStatement("INSERT INTO CL_ADD_04_GENERAL " +
                    "(BRANCH,ID_CL_ADD,ID_GENERAL) VALUES(?,?,?)");
            preparedStatement.setString(1,customer.getBranch());
            preparedStatement.setString(2,customer.getId());
            preparedStatement.setLong(3, generalList.get(generalList.size() - 1).getId());
            resultSet = preparedStatement.executeQuery();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
        }
    }

    private Parameters initParams(Payment payment,CallableStatement dealStatement){
        Parameters parameters = new Parameters();
        parameters.put("parent_group_id", payment.DEAL_GROUP); // Deal Group
        parameters.put("branch", payment.getSender().getBranch());
        parameters.put("SENDER_ACC", payment.getSender().getAccount()); // Счет отправителя
        parameters.put("RECEIVER_ACC", payment.getReceiver().getAccount()); // Счет получателя
        parameters.put("RECEIVER_ACC_MFO",payment.getReceiver().getBranch());
        parameters.put("summa", payment.calculateSum());
        parameters.put("CSH", payment.getCashSymbol()); // Кассовый символ - CASHSYMBOL
        parameters.put("9999", payment.getCashSymbolDescription()); // Кассовый
        parameters.put("PURPOSE",payment.formatPurpose());
        parameters.put("PCODE",payment.getPurposeCode());
        parameters.put("parent_deal_id", 0); // BF_TR_PAYDOCS
        parameters.put("parent_id", 1l); // No need
        parameters.put("CS_PREP", dealStatement);
        parameters.put("operation_id",payment.getOperationCode());
        parameters.put("ACC_NAME_AND_TAX",payment.getReceiver().getName());
        parameters.put("PCODC",String.format("%s%s",payment.getCashSymbol(),payment.getCashSymbolDescription()));
        logger.error("PARAMETERS HASHMAP " + parameters.getParametersHashmap().toString());
        return parameters;
    }
}
