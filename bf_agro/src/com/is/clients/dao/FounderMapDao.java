package com.is.clients.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.clients.models.FounderMap;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class FounderMapDao implements Dao<FounderMap> {
	private static Logger logger = Logger.getLogger(FounderMapDao.class);
	private String alias;
	private FounderMapDao(String alias) {
		this.alias = alias;
	}
	
	public static FounderMapDao instance(String alias) {
		return new FounderMapDao(alias);
	}
	
	@Override
	public <T1 extends FounderMap> void setFilter(T1 filter) {
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
	public List<FounderMap> getList(int pageIndex, int pageSize) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public FounderMap getItem(long idPersonMap) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public FounderMap getItem(Connection c, long idPersonMap) {
		PreparedStatement ps = null;
		CallableStatement init = null;
		ResultSet rs = null;
		FounderMap founderMap = null; 
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("select * from FOUNDER_MAP where branch = info.getBranch and id_person_map=?");
			ps.setLong(1, idPersonMap);
			rs = ps.executeQuery();
			if(rs.next()) {
				founderMap = new FounderMap(rs.getLong("id_person_map")
										, rs.getString("branch")
										, rs.getLong("id_cl_add")
										, rs.getInt("id_founder"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		
		return founderMap;
	}
	
	@Override
	public FounderMap getItem(String itemId) {
		throw new UnsupportedOperationException();
	}
	/**
	 * возвращает FOUNDERS_MAP по id из таблицы client_addinfo_person_map
	 * @param idPersonMap
	 * @return FounderMap
	 * */
	@Override
	public FounderMap getItem(Connection c, String idPersonMap) {
		PreparedStatement ps = null;
		CallableStatement init = null;
		ResultSet rs = null;
		FounderMap founderMap = null; 
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("select * from FOUNDER_MAP where branch = info.getBranch and id_person_map=?");
			ps.setString(1, idPersonMap);
			rs = ps.executeQuery();
			if(rs.next()) {
				founderMap = new FounderMap(rs.getLong("id_person_map")
										, rs.getString("branch")
										, rs.getLong("id_cl_add")
										, rs.getInt("id_founder"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		
		return founderMap;
	}

	@Override
	public FounderMap create(FounderMap item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public FounderMap create(Connection c, FounderMap item) throws Exception {
		PreparedStatement ps = null;
		CallableStatement init = null;
		FounderMap founderMap = null; 
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("insert into FOUNDER_MAP(id_person_map,branch,id_cl_add, id_founder)"
													+ "values(?,info.getBranch,?,?)");
			ps.setLong(1, item.getIdPersonMap());
			ps.setLong(2, item.getId_cl_add());
			ps.setInt(3, item.getId_founder());
			ps.executeUpdate();
			
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		
		return founderMap;
	}

	@Override
	public int update(FounderMap item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(Connection c, FounderMap item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int remove(FounderMap item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Connection c, FounderMap item) {
		PreparedStatement ps = null;
		CallableStatement init = null;
		int count = 0; 
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("delete from founder_map where id_person_map=?");
			ps.setLong(1, item.getIdPersonMap());
			count = ps.executeUpdate();
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		return count;
	}

}
