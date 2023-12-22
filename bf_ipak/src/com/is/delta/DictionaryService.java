package com.is.delta;

import com.is.ISLogger;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 23.04.2017.
 * 0:08
 */
public class DictionaryService {
    public static List<RefData> getActionTypes(String groupId,String schema){
        return RefDataService.getRefData(
                "SELECT ACTION_ID DATA, ACTION_NAME LABEL FROM DELTA_ACTION_TYPES WHERE GROUP_ID=?",
                    groupId,
                        schema);
    }

    public static List<RefData> getCustomerTypes(String schema) {
        return RefDataService.getRefData("SELECT DATA, NAME LABEL FROM DELTA_CUSTOMER_TYPES",schema);
    }

    public static List<RefData> getStateTypes(String schema) {
        return RefDataService.getRefData("SELECT * FROM DELTA_STATE_TYPES",schema);
    }

    public static String getActionName(String groupId, String key) throws SQLException {
        return CustomerUtils.decode(
                "SELECT ACTION_NAME FROM DELTA_ACTION_TYPES WHERE GROUP_ID ='" + groupId + "'  and ACTION_ID = ?",key);
        //return null;
    }

    public static String getStateName(String key) throws SQLException {
        return CustomerUtils.decode("SELECT LABEL FROM DELTA_STATE_TYPES WHERE DATA = ?",key);
        //return null;
    }

    public static String getCustomerType(String key) throws SQLException {
        return CustomerUtils.decode("SELECT NAME FROM DELTA_CUSTOMER_TYPES WHERE DATA = ?",key);
        //return null;
    }

    public static String getMigrationDate() {
        try {
            return CustomerUtils.decode("SELECT VALUE FROM BF_SETS A WHERE A.ID = ?", "SAP_MIGRATION_DATE");
        }
        catch (Exception e){
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        }
        return null;
    }
}
