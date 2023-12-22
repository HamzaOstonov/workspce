package com.is.nibbd.reis.reisServices;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.nibbd.models.Nibbd;

public class ExecutorService {
	private static ExecutorService instance;
	private Connection c;
	private boolean useOpenedCon;
	private String alias;
	private String un;
	private String pw;
	
	private ExecutorService(String un, String pw, String alias) {
		this.alias = alias;
		this.un = un;
		this.pw = pw;
	}
	public static ExecutorService getInstance(String un, String pw, String alias) {
		if(instance == null) {
			instance = new ExecutorService(un, pw, alias);
		}
		return instance;
	}
	
	public void initConnect(){
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			useOpenedCon = true;
		} catch (Exception e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
		
	}
	
	public void closeConnect(){
		ConnectionPool.close(c);
		useOpenedCon = false;
	}
	public List<Nibbd> getList(){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Nibbd> list = null;
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			ps = c.prepareStatement("select * from nibbd where state=3");
			rs = ps.executeQuery();
			list = new ArrayList<Nibbd>();
			while (rs.next()) 
            {
               list.add(new Nibbd(
                                      rs.getString("branch"),
                                      rs.getLong("str_no"),
                                      rs.getInt("query_no"),
                                      rs.getString("reis_no"),
                                      rs.getString("query_inp"),
                                      rs.getString("parent_id"),
                                      rs.getInt("state"),
                                      rs.getDate("query_date"),
                                      rs.getString("id_client"),
                                      rs.getString("query_out"),
                                      ""));
            }
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	public int updateNibbdTo4(Nibbd nibbd){
		PreparedStatement ps = null;
		CallableStatement doAction = null;
		CallableStatement cs = null;
		CallableStatement clear = null;
		String code_error = nibbd.parseQueryOut();
		if(code_error == null || !code_error.equals("0")){
			ISLogger.getLogger().error("Error parsing query_out ");
			return 0;
		}
		int count = 0;
		try {
			if(!useOpenedCon || c == null || c.isClosed()) {
				c = ConnectionPool.getConnection(un,pw,alias);
			}
			clear = c.prepareCall("{ call Param.clearparam() }");
			clear.execute();
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			
			
			cs.setString(1, "ID_CLIENT");  cs.setString(2,nibbd.getId_client()); cs.execute();
			cs.setString(1, "J_ACCOUNT");  cs.setString(2,nibbd.getNew_acc()); cs.execute();
			cs.setString(1, "ID");  cs.setString(2,nibbd.getParent_id()); cs.execute();
			cs.setString(1, "NAME");  cs.setString(2,nibbd.getName()); cs.execute();
			cs.setString(1, "J_CODE_BANK");  cs.setString(2,nibbd.getBranch()); cs.execute();
			
			doAction = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			doAction.setInt(1, 1);
			doAction.setInt(2, 1);
			doAction.setInt(3,7);
			doAction.execute();
			
			if(!useOpenedCon || c == null || c.isClosed()) {
				c = ConnectionPool.getConnection(un,pw,alias);
			}
			ps = c.prepareStatement("update nibbd set state = 4 where str_no=?");
			ps.setLong(1, nibbd.getStr_num());
			ps.executeQuery();
			
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().error(e.getStackTrace());
				e1.printStackTrace();
			}
		} finally {
		    DbUtils.closeStmt(clear);
		    DbUtils.closeStmt(cs);
		    DbUtils.closeStmt(doAction);
			DbUtils.closeStmt(ps);
			if(!useOpenedCon){
				ConnectionPool.close(c);
			}
		}
		return count;
	}
	
}
