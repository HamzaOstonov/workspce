package com.is.nibbd.reis.reisServices;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.nibbd.models.Nibbd;
import com.is.utils.CheckNull;

public class MakerService{
	private String alias;
	private String branch;
	private boolean transactionMode;
	
	private MakerService() {}
	
	private MakerService(String alias,String branch) {
		this.alias = alias;
		this.branch = branch;
		transactionMode = false;
	}
	public static MakerService getInstance() {
		return new MakerService();
	}
	
	public static MakerService getInstance(String alias, String branch) {
		return new MakerService(alias, branch);
	}
	
	public List<Nibbd> getList() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Nibbd> list = null;
		try {
			if(alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			ps = c.prepareStatement("select * from nibbd where state=1");
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
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public int updateToState2(Nibbd item){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int count = 0;
		try {
			if(alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			cs = c.prepareCall("{ call info.init()}");
			cs.execute();
			ps = c.prepareStatement("update nibbd set reis_no=?,state=2 " +
					"where state=1");
			ps.setString(1, item.getReis_num());
			count = ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
		    try {
                c.rollback();
            }
            catch (Exception e1){
                ISLogger.getLogger().error(CheckNull.getPstr(e1));
            }
		} finally {
		    DbUtils.closeStmt(ps);
		    DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		
		return count;
	}
	
	public String getReis_(boolean makeReis){
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement init = null;
		ResultSet rs = null;
		int reis = 0;
		String branch = null;
		try {
			if(alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("select info.getBranch from dual");
			rs = ps.executeQuery();
			if(rs.next()) {
				branch = rs.getString(1);
			}
			ps.close();rs.close();
			
			if(makeReis) {
				ps = c.prepareStatement("select seq_nibbd_reis_no_"+branch+".nextval from dual");
				rs = ps.executeQuery();
				if(rs.next()) {
					reis = rs.getInt(1);
				}
			} else {
				ps = c.prepareStatement("select last_number from all_sequences where " +
						"sequence_owner = ? and sequence_name = ?");
				ps.setString(1, alias);
				ps.setString(2, "SEQ_NIBBD_REIS_NO_"+branch);
				rs = ps.executeQuery();
				if(rs.next()) {
					reis = rs.getInt(1);
				}
			}
		} catch(Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		return reisToStr(reis);
	}
	private String reisToStr(int reis){
		String res = null;
		if(reis != 100){
			res = reis < 10?"0"+reis:Integer.toString(reis);
		} else {
			res = "01";
		}
		return res;
	}
	
}
