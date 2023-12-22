package com.is.payments;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.model.Account;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.payments.entity.BudgetAccount;
import com.is.utils.CheckNull;
import com.is.utils.RefData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.05.2017.
 * 11:41
 */
public class PaymentUtils {
    public static final String internalCash = "5000";
    public static final String externalCash = "5001";
    public static final String nonCash = "5002";
    public static final String interCard = "5003";

    public static List<RefData> getPaymentTypes(){
        List<RefData> list = new ArrayList<RefData>();
        list.add(new RefData(internalCash,"Наличный Внутрибанковский платеж"));
        list.add(new RefData(externalCash,"Наличный Внебанковский"));
        list.add(new RefData(nonCash,"Безналичный платеж"));
        list.add(new RefData(interCard,"С карты на карту"));
        /*list.add(new RefData("5004","Межкарточный внутренний"));
        list.add(new RefData("5005","Межкарточный внешний"));*/
        return list;
    }

    public static void checkStopList(SessionAttributes sessionAttributes,Customer customer) throws Exception {
        Connection c = null;
        CallableStatement callableStatement = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();
            callableStatement.close();

            callableStatement = c.prepareCall("{ " +
                    "call stoplist.reaction(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1,null);//customer.getBranch());
            callableStatement.setString(2,null);//customer.getId());
            callableStatement.setString(3, customer.getFullName());
            callableStatement.setString(4, null);//customer.getP_passport_serial());
            callableStatement.setString(5, null);//customer.getP_passport_number());
            callableStatement.setString(6, null);//customer.getP_type_document());
            callableStatement.setString(7, customer.getP_family() );
            callableStatement.setString(8, customer.getP_first_name());
            callableStatement.setString(9, customer.getP_patronymic());
            callableStatement.execute();

            //ISLogger.getLogger().error("STOP LIST PARAMS " + customer.getFullName() +
                    //" " + customer.getP_family()
                    //+ " " + customer.getP_first_name()
                    //+ " " + customer.getP_patronymic());

        } catch (Exception e) {
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            GeneralUtils.closeStatement(callableStatement);
            ConnectionPool.close(c);
        }
    }

    public static BudgetAccount getCentralBankAccount(String schema){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BudgetAccount account = null;
        try{
            c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement("select * from s_kaznspr where rownum = 1 and act = 'A'");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                account = new BudgetAccount(
                        resultSet.getString("treasure_id"),
                        resultSet.getString("bank_id"),
                        resultSet.getString("account"),
                        null,
                        resultSet.getString("inn"),
                        resultSet.getString("name"),
                        null
                );
        }
        catch (Exception e){
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
        finally {
            ConnectionPool.close(c);
        }
        return account;
    }
}
