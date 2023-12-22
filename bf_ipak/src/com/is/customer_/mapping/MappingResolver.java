package com.is.customer_.mapping;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.customer_.core.ConnectionUtils;
import com.is.customer_.core.model.Customer;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 09.05.2017.
 * 19:24
 */
public class MappingResolver {
    private static final Logger logger = Logger.getLogger(MappingResolver.class);
    public static boolean isSAPCustomer(String idSAP){
        return idSAP == null ? false : true;
    }

    public static int countBranchCustomers(List<Customer> list,String sessionBranch) {
        int count = 0;
        for (Customer customer : list){
            if (isCustomer(customer.getBranch(),customer.getId()))
                if (isBranchCustomer(customer.getBranch(),customer.getId(),sessionBranch))
                    count += 1;
        }
        return count;
    }

    public static boolean hasBranchCustomers(List<Customer> list,String sessionBranch){
        for (Customer customer : list)
            if (isCustomer(customer.getBranch(),customer.getId()))
                if (isBranchCustomer(customer.getBranch(),customer.getId(),sessionBranch))
                    return true;

        return false;
    }

    public static boolean isBranchCustomer(String branch, String id, String sessionBranch) {
        return branch != null && id != null && (id.startsWith("99") ||id.startsWith("6")||id.startsWith("7")||id.startsWith("8") ) && branch.equalsIgnoreCase(sessionBranch);
    }

    public static boolean isBranchCustomerExists(String branch,String id){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            c = ConnectionUtils.getInstance().getConnectionByBranch(branch);
            preparedStatement = c.prepareStatement("SELECT COUNT(*) FROM CLIENT_P A WHERE A.ID = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            try {
                ConnectionUtils.getInstance().close(c);
            } catch (SQLException e) {
                logger.error(CheckNull.getPstr(e));
            }
        }
        return count == 1 ? true : false;
    }

    public static boolean isContactPersonExists(String branch,String id,String sessionBranch){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try{
            c = ConnectionUtils.getInstance().getConnectionByBranch(branch);
            preparedStatement = c.prepareStatement("SELECT COUNT(*) FROM CLIENT_ADDINFO_PERSON A WHERE A.ID = ?");
            preparedStatement.setString(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            try {
                ConnectionUtils.getInstance().close(c);
            } catch (SQLException e) {
                logger.error(CheckNull.getPstr(e));
            }
        }
        return count == 1 ? true : false;
    }

    public static boolean isCustomer(String branch, String id) {
        return branch != null && id != null && (id.startsWith("99") ||id.startsWith("6") ||id.startsWith("7")||id.startsWith("8"));
    }

    public static List<Customer> sortList(List<Customer> list, String sessionBranch) {
        List<Customer> listSorted = new ArrayList<Customer>();
        for (Customer customer : list) {
            if (isCustomer(customer.getBranch(),customer.getId())) {
                if (isBranchCustomer(customer.getBranch(), customer.getId(), sessionBranch))
                    listSorted.add(0, customer);
                else
                    listSorted.add(customer);
            }
        }

        return listSorted;
    }

    public static boolean isContactPerson(String branch,String id, String sessionBranch){
        if (branch != null && id!=null && id.startsWith("A"))
            return true;
        return false;
    }
}
