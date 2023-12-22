package com.is.customer_.core.utils;

import com.is.customer_.core.utils.CustomerUtils;

import java.sql.SQLException;

/**
 * Created by root on 05.05.2017.
 * 20:42
 */
public class ReferenceDecoder {
    public static String getApplicationName(String key) throws SQLException {
        return CustomerUtils.decode("SELECT label FROM SS_APPS_TYPES A WHERE A.DATA = ?",key);
    }

    public static String getCountryName(String key) throws SQLException {
        return CustomerUtils.decode("select name from s_str a where a.code_str = ?",key);
    }

    public static String getStateName(String key) throws SQLException {
        return CustomerUtils.decode("select name from state_client where deal_id = 2 and id = ?",key);
    }

    public static String getTaxOrgName(String key) throws SQLException{
        return CustomerUtils.decode("select name_gni from s_gni a where a.gni_id = ?",key);
    }

    public static String getGenderName(String key) throws SQLException{
        return CustomerUtils.decode("select name_sex from s_sex a where a.code_sex = ?",key);
    }

    public static String getNationName(String key) throws SQLException {
        return CustomerUtils.decode("select nat_name from s_nation where act <> 'Z' and nat_id = ? ",key);
    }

    public static String getDocumentName(String key) throws SQLException {
        return CustomerUtils.decode("select name_cert from s_certificate a where a.code_cert = ?",key);
    }

    public static String getRegionName(String key) throws SQLException {
        return CustomerUtils.decode("select region_nam from s_region a where a.region_id = ?",key);
    }

    public static String getDistrictName(String groupId,String key) throws SQLException {
        String SQL = "select distr_name from s_distr a where a.region_id = ";
        SQL += groupId;
        SQL += " and a.distr = ?";

        return CustomerUtils.decode(SQL,key);
    }

    public static String getCapacityName(String key) throws SQLException {
        return CustomerUtils.decode("select name_kr from s_krfl where act <> 'Z' and kod_kr = ?",key);
    }

    public static String getCustomerTypeName(String key) throws SQLException{
        return CustomerUtils.decode("select name_k2 from s_typekl a where a.kod_k = ?",key);
    }
}
