package com.is.clients.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.clients.models.SapIpClient;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class SapIpClientDao implements Dao<SapIpClient> {
	private String alias;
	
	private SapIpClientDao() {}
	private SapIpClientDao(String alias) {this.alias = alias;}
	
	public static SapIpClientDao instance() { return new SapIpClientDao();}
	
	public static SapIpClientDao instance(String alias) {return new SapIpClientDao(alias);}

	@Override
	public <T1 extends SapIpClient> void setFilter(T1 filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<FilterField> getFilterFields() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getCount() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SapIpClient> getListWithPaging(int pageIndex, int pageSize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SapIpClient getItemByLongId(String branch,long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SapIpClient getItemByLongId(Connection c,String branch, long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SapIpClient getItemByStringId(String branch,String itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SapIpClient getItemByStringId(Connection c, String branch,String itemId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		SapIpClient result = null;
		try {
			ps = c.prepareStatement("select * from sap_ip_client where id_client=?");
			ps.setString(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = new SapIpClient(rs.getString("id_client"), rs.getString("branch"), rs.getString("id_ip_sap"));
			}
			
		} catch(Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return result;
	}

	@Override
	public SapIpClient create(SapIpClient item) throws Exception {
		Connection c = null;
		SapIpClient ipClient = null;
		try {
			c = ConnectionPool.getConnection();
			ipClient = create(c, item);
			c.commit();
		} catch(Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			DbUtils.rollback(c);
			throw new Exception(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		
		return ipClient;
	}

	@Override
	public SapIpClient create(Connection c, SapIpClient item) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("insert into sap_ip_client(id_client, branch, id_ip_sap) values(?,?,?)");
			ps.setString(1, item.getId_client());
			ps.setString(2, item.getBranch());
			ps.setString(3, item.getId_ip_sap());
			ps.executeUpdate();
		} finally {
			DbUtils.closeStmt(ps);
		}
		return null;
	}

	@Override
	public int update(SapIpClient item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Connection c, SapIpClient item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int remove(SapIpClient item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int remove(Connection c, SapIpClient item) throws Exception {
		throw new UnsupportedOperationException();
	}
	@Override
	public List<SapIpClient> getList() {
		throw new UnsupportedOperationException();
	}

}
