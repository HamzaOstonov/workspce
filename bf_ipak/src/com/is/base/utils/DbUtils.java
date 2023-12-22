package com.is.base.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Action;
import com.is.base.History;
import com.is.base.SqlScripts;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

public class DbUtils {
	
	public static String getCond(List<FilterField> flfields) {
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}
	
	public static List<RefData> getRefData(Connection c,String sql) {
		List<RefData> list = new LinkedList<RefData>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.add(
						new RefData(rs.getString("data"),
								rs.getString("label")));
		}
		catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	
	public static List<RefData> getRefData(Connection c,String sql, String key)
	{
		List<RefData> list = new LinkedList<RefData>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			while (rs.next())
				list.add(
						new RefData(rs.getString("data"),
								rs.getString("label")));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return list;
	}
	
	public static HashMap<String, String> getHRefData(Connection c,String sql)
	{
		PreparedStatement s = null;
		ResultSet rs = null;
		HashMap<String,String> list = new HashMap<String,String>();
		try
		{
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.put(rs.getString("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	public static HashMap<String, String> getHRefData(String sql, String alias)
	{
		Connection c = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		HashMap<String,String> list = new HashMap<String,String>();
		try
		{
			c = ConnectionPool.getConnection(alias);
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.put(rs.getString("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	public static HashMap<Integer, String> getHRefDataInt(String sql, String alias)
	{
		Connection c = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		HashMap<Integer,String> list = new HashMap<Integer, String>();
		try
		{
			c = ConnectionPool.getConnection(alias);
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.put(rs.getInt("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	public static Map<Integer, String> getHRefDataInt(Connection c,String sql)
	{
		Map<Integer,String> list = new HashMap<Integer, String>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try
		{
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.put(rs.getInt("data"), rs.getString("label"));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	
	public static String getDesc(String code, String sql) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			while (rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static List<History> getHistory(String sql,String cl_id,String branch, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<History> list = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, cl_id);
			rs = ps.executeQuery();
			list = new ArrayList<History>();
			while (rs.next()){
				list.add(new History(
										rs.getDate("date_correct"),
										rs.getString("name"), 
										rs.getString("full_name"),
										rs.getTimestamp("date_time") ));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<Action> getActionData(Connection c, String sql) {
		List<Action> list = new ArrayList<Action>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try
		{	
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.add( new  Action(rs.getInt("deal_id"), rs.getInt("id"), rs.getString("name"),rs.getInt("manual")));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	
	public static List<Action> getActionData(String sql,String alias) {
		List<Action> list = new ArrayList<Action>();
		Connection c = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.add( new  Action(rs.getInt("deal_id"), rs.getInt("id"), rs.getString("name"),rs.getInt("manual")));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
		}
		return list;
	}
	public static void rollback(Connection c) {
		try {
			if(c != null)
				c.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static void closeStmt(Statement st) {
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static Date getOperDay(String alias){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Date res = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init()}");
			cs.execute();
			ps = c.prepareStatement("select info.getday from dual");
			rs = ps.executeQuery();
			if(rs.next()){
				res = new java.util.Date(rs.getDate(1).getTime());
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			if(ps != null ){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null ){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(cs != null ){try {cs.close();} catch (SQLException e) {e.printStackTrace();}}
			ConnectionPool.close(c);
		}
		return res;
	}
	public static Date getOperDay(Connection c){
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Date res = null;
		try {
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			ps = c.prepareStatement(SqlScripts.OPER_DAY.getSql());
			rs = ps.executeQuery();
			if(rs.next()){
				res = new java.util.Date(rs.getDate(1).getTime());
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			if(ps != null ){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null ){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(cs != null ){try {cs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return res;
	}
	
	public static String getGniByDistrCode(String distrCode) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String res = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select kod_gni from s_soato where distr=?");
			ps.setString(1, distrCode);
			rs = ps.executeQuery();
			if(rs.next()){
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStmt(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String getBranchBySchema(String schema){
	    Connection c = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String branch = null;
	    try{
	        c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement("SELECT BRANCH FROM SS_DBLINK_BRANCH A WHERE A.USER_NAME = ?");
            preparedStatement.setString(1,schema);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                branch = resultSet.getString(1);

            if (branch == null || branch.isEmpty())
                throw new RuntimeException("DbUtils branch is null or empty");
        }
        catch (Exception e){
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
            return branch;
        }
    }

    public static String getSchemaByBranch(String branch){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String schema = null;
        try{
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement("SELECT USER_NAME FROM SS_DBLINK_BRANCH A WHERE A.BRANCH = ?");
            preparedStatement.setString(1,branch);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                schema = resultSet.getString(1);

            if (schema == null || schema.isEmpty())
                throw new RuntimeException("DbUtils schema is null or empty");
        }
        catch (Exception e){
            ISLogger.getLogger().error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
            return schema;
        }
    }
}
