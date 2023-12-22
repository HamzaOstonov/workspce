package com.is.delta_relations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class DeltaRelationDao implements Dao<DeltaRelation> {
	private Logger logger = Logger.getLogger(DeltaRelationDao.class);
	private String alias;
	private DeltaRelationFilter filter;
	
	private static final String psql1 ="select t.* from(select t.*,rownum rwnm from (";
	private static final String psql2 =" ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static final String msql ="select * from (SELECT d.*,c.name parent_name, m.prson_name child_name, m.person_kind "
									+ "FROM delta_relations d "
									+ "join client_addinfo_person_map m on d.id_person_map=m.id "
									+ "join client c on c.id_client=m.client_id and d.state != 3)";
	private DeltaRelationDao() {
	}

	private DeltaRelationDao(String alias) {
		this.alias = alias;
	}
	public static DeltaRelationDao instance() {return new DeltaRelationDao();} 
	public static DeltaRelationDao instance(String alias) {
		return new DeltaRelationDao(alias);
	}

	@Override
	public <T1 extends DeltaRelation> void setFilter(T1 filter) {
		this.filter = (DeltaRelationFilter) filter;
	}

	@Override
	public List<FilterField> getFilterFields() {
		List<FilterField> filterFields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getBranch())) {
			filterFields.add(new FilterField(DbUtils.getCond(filterFields) + "branch = ?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getDateFrom())) {
			filterFields.add(new FilterField(DbUtils.getCond(filterFields) + "v_date >= ?", filter.getDateFrom()));
		}
		if (!CheckNull.isEmpty(filter.getDateTo())) {
			filterFields.add(new FilterField(DbUtils.getCond(filterFields) + "v_date <= ?", filter.getDateTo()));
		}
		if (!CheckNull.isEmpty(filter.getUser_id())) {
			filterFields.add(new FilterField(DbUtils.getCond(filterFields) + "user_id = ?", filter.getUser_id()));
		}
		if (!CheckNull.isEmpty(filter.getPersonKind())) {
			filterFields.add(new FilterField(DbUtils.getCond(filterFields) + "person_kind = ?", filter.getPersonKind()));
		}
		return filterFields;
	}

	@Override
	public int getCount() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		List<FilterField> flFields = getFilterFields();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM delta_relations ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}
		return count;
	}

	@Override
	public List<DeltaRelation> getListWithPaging(int pageIndex, int pageSize) {
		Connection c = null;
		List<DeltaRelation> list = new ArrayList<DeltaRelation>();
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields();
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			if (alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			ps = c.prepareStatement(sql.toString());
			logger.error(":::::::::::::::::::::::::::: deltaRelationDao :: sql = " + sql.toString());
			cs.execute();

			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new DeltaRelation(rs.getLong("id"), 
										rs.getString("branch"), 
										rs.getLong("id_person_map"), 
										rs.getInt("action"), 
										rs.getDate("v_date"), 
										rs.getString("message"), 
										rs.getInt("state"), 
										rs.getInt("user_id"), 
										rs.getString("parent_name"), 
										rs.getString("child_name"), 
										rs.getString("person_kind")));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(ps);
		}
		return list;
	}

	@Override
	public DeltaRelation getItemByLongId(String branch,long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DeltaRelation getItemByLongId(Connection c,String branch, long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DeltaRelation getItemByStringId(String branch,String itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DeltaRelation getItemByStringId(Connection c, String branch,String itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DeltaRelation create(DeltaRelation item) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement init = null;
		try {
			c = ConnectionPool.getConnection(alias);
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("select seq_delta_relations.nextval from dual");
			rs = ps.executeQuery();
			if(rs.next()) {
				item.setId(rs.getLong(1));
			}
			ps.close();
			ps = c.prepareStatement("insert into delta_relations(id, branch, action,id_person_map, message,state,user_id, v_date) values(?,?,?,?,?,1,info.getEmpId,?)");
			ps.setLong(1, item.getId());
			ps.setString(2, item.getBranch());
			ps.setInt(3, item.getAction());
			ps.setLong(4, item.getIdPersonMap());
			ps.setString(5, item.getMessage());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
//			ps.setLong(6, item.getUser_id());
			ps.executeUpdate();
			c.commit();
			
		} catch(Exception e) {
			logger.error(CheckNull.getPstr(e));
			throw e;
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		
		return null;
	}

	@Override
	public DeltaRelation create(Connection c, DeltaRelation item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update(DeltaRelation item) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update delta_relations set state=? where id=?");
			ps.setInt(1, item.getState());
			ps.setLong(2, item.getId());
			ps.executeUpdate();
			c.commit();
		} catch(Exception e){
			logger.error(CheckNull.getPstr(e));
			throw e;
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return 0;
	}

	@Override
	public int update(Connection c, DeltaRelation item) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update delta_relations set state=? where id=?");
			ps.setInt(1, item.getState());
			ps.setLong(2, item.getId());
			ps.executeUpdate();
			c.commit();
		} finally {
			DbUtils.closeStmt(ps);
		}
		return 0;
	}

	@Override
	public int remove(DeltaRelation item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public int remove(Connection c, DeltaRelation item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DeltaRelation> getList() {
		throw new UnsupportedOperationException();
	}

}
