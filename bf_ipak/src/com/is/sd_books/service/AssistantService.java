package com.is.sd_books.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.sd_books.model.Credentials;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class AssistantService {
	public static List<RefData> getDepStates(String alias){
		return RefDataService.getRefData("SELECT CODE DATA, NAME LABEL FROM SS_SD_STATES", alias);
	}
	
	public static Date getOperDate(String alias){
		Connection c = null;
		Date date = null;
		try{
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			CallableStatement cl = c.prepareCall("{? = call info.getDay()}");
			cl.registerOutParameter(1, java.sql.Types.DATE);
			cl.execute();
			date = cl.getDate(1);
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return date;
	}
	
	public static String getUserById(int emp_id,String alias){
		Connection c = null;
		String result = null;
		try{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT FULL_NAME FROM USERS WHERE ID = ?");
			ps.setInt(1, emp_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static String getSavingsBook(long book_id,String alias,String branch){
		Connection c = null;
		String result = null;
		try{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT b_ser || ' ' || b_num as name FROM SD_CLERK_ALL "
					+ "WHERE SD_BOOKS_ID = ? AND BRANCH = ?");
			ps.setLong(1, book_id);
			ps.setString(2, branch);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				result = rs.getString("name");
			}
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static Date getDeadlineDate(Credentials cr){
		Date date = null;
		Connection c = null;
		try{
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			CallableStatement cs = c.prepareCall("{? = call SD_RECKON.DEADLINE}");
			cs.registerOutParameter(1, java.sql.Types.DATE);
			cs.execute();
			
			date = cs.getDate(1);
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return date;
	}
	
	public static Date getLookBookDate(Credentials cr,String branch,Double book_id){
		Date date = null;
		Connection c = null;
		try{
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			CallableStatement cs = c.prepareCall("{? = call SD_2010.NEARCAPDATE(?,?)}");
			cs.registerOutParameter(1, java.sql.Types.DATE);
			cs.setString(2, branch);
			cs.setDouble(3, book_id);
			cs.execute();
			
			date = cs.getDate(1);
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return date;
	}
	
	// Проверка на валютность вклада
	public static Boolean isDepMonetary(String dep){
		Boolean isMonetary = true;
		Connection c = null;
		try{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT CODE_CURRENCY FROM SS_SD A WHERE A.CODE = ?");
			ps.setString(1, dep);
			ResultSet rs = ps.executeQuery();
			String code = null;
			if (rs.next())
				code = rs.getString(1);
			if (code != null)
				if (code.equals("000"))
					isMonetary = false;
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return isMonetary;
	}
	
	public static String getInfoFilialbyBranchSd(String neededInfo, String alias, String branch) {

		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String info = "";
		StringBuilder par = new StringBuilder("000");
		String id = par.append(branch).toString();

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from client_j h where h.branch=? and h.id=?");

			ps.setString(1, branch);
			ps.setString(2, id);

			rs = ps.executeQuery();
			if (rs.next()) {

				ResultSetMetaData md = rs.getMetaData();
				int columns = md.getColumnCount();

				Map<String, Object> row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				res.add(row);
			}

			for (int j = 0; j < res.size(); j++) {
				
				if (res.get(j).containsKey("DIRECTOR_NAME") && neededInfo.equals("DIRECTOR_NAME")) {
					info = (String) res.get(j).get("DIRECTOR_NAME");
					//System.out.println("res-----eeee1:" + info);
				} else if (res.get(j).containsKey("CHIEF_ACCOUNTER_NAME")&& neededInfo.equals("CHIEF_ACCOUNTER_NAME")) {
					info = (String) res.get(j).get("CHIEF_ACCOUNTER_NAME");
					//System.out.println("res-----eeee2:" + info);
				}
				else if(res.get(j).containsKey("ACCOUNT")&& neededInfo.equals("ACCOUNT")){
					info = (String) res.get(j).get("ACCOUNT");
					//System.out.println("res-----eeee3:" + info);
						
				}
				else if(res.get(j).containsKey("POST_ADDRESS")&& neededInfo.equals("POST_ADDRESS")){
					info = (String) res.get(j).get("POST_ADDRESS");
					//System.out.println("res-----eeee4:" + info);
									
				}
				else if(res.get(j).containsKey("NUMBER_TAX_REGISTRATION")&& neededInfo.equals("NUMBER_TAX_REGISTRATION")){
					info = (String) res.get(j).get("NUMBER_TAX_REGISTRATION");
					//System.out.println("res-----eeee5:" + info);
						
				}
				else if(res.get(j).containsKey("PHONE")&& neededInfo.equals("PHONE")){
					info = (String) res.get(j).get("PHONE");
					//System.out.println("res-----eeee6:" + info);
								
				}
			}
					} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return info;
	}
	
	public static String getInfoBankName(String branch,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select bank_name name from s_mfo where bank_id=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return name;
	}	
	public static String getUser(){
		Connection c = null;
		String user = null;
		try{
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT USER FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = rs.getString(1);
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return user;
	}
}
