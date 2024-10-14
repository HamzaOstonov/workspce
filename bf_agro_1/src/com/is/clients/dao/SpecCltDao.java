package com.is.clients.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.clients.models.SpecClt;
import com.is.clients.models.SpecCltFilter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class SpecCltDao implements Dao<SpecClt> {
	private static Logger logger = Logger.getLogger(SpecCltDao.class);
	
	private String alias;
	private SpecCltFilter filter;
	private final String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private final String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private final String msql = "select /*+ ordered */ a.*, "
			+ "b.name, b.code_type, c.name_k2 type_name "
			+ "from specialclt a, client b, s_typekl c ";
	private SpecCltDao(String alias) {
		super();
		this.alias = alias;
	}
	
	public static SpecCltDao getInstance(String alias) {
		return new SpecCltDao(alias);
	}

	@Override
	public List<FilterField> getFilterFields() {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.branch=?", filter
					.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId_special())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.id_special=?",
					filter.getId_special()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.id_client=?",
					filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getValue())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.value=?", filter
					.getValue()));
		}
		if (!CheckNull.isEmpty(filter.getPrim())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.prim=?", filter
					.getPrim()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(DbUtils.getCond(flfields) + "a.name=?", filter
					.getName()));
		}


		flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	@Override
	public int getCount() {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields();
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ ordered */ count(*) from specialclt a");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	@Override
	public List<SpecClt> getList(int pageIndex, int pageSize) {
		List<SpecClt> list = new ArrayList<SpecClt>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields =getFilterFields();

        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
                sql.append(" and a.branch = b.branch and a.id_client = b.id_client and b.code_type = c.kod_k");
                
        }
        sql.append(psql2);
        

        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement(sql.toString());
                for(params=0;params<flFields.size();params++){
                ps.setObject(params+1, flFields.get(params).getColobject());
                }
                params++;
                ps.setInt(params++,v_upperbound);
                ps.setInt(params++,v_lowerbound);

                rs = ps.executeQuery();
                while (rs.next()) {
                	list.add(new SpecClt(
                            rs.getString("branch"),
                            rs.getString("id_special"),
                            rs.getString("id_client"),
                            rs.getString("value"),
                            rs.getString("prim"),
                            rs.getString("name"),
                            rs.getString("code_type"),
                            rs.getString("type_name")));
                }
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
        	if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
                ConnectionPool.close(c);
        }
        return list;
	}

	@Override
	public SpecClt getItem(long itemId) {
		SpecClt spechar = new SpecClt();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM specialclt WHERE id=?");
			ps.setLong(1, itemId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				spechar = new SpecClt();

				spechar.setBranch(rs.getString("branch"));
				spechar.setId_special(rs.getString("id_special"));
				spechar.setId_client(rs.getString("id_client"));
				spechar.setValue(rs.getString("value"));
				spechar.setPrim(rs.getString("prim"));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return spechar;
	}

	@Override
	public int update(SpecClt spechar) {
		Connection c = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE specialclt SET branch=?, id_special=?, id_client=?, value=?, prim=?  WHERE id=?");

			ps.setString(1, spechar.getBranch());
			ps.setString(2, spechar.getId_special());
			ps.setString(3, spechar.getId_client());
			ps.setString(4, spechar.getValue());
			ps.setString(5, spechar.getPrim());
			count = ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return count;
	}

	@Override
	public int remove(SpecClt item) {
		Connection c = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM specialclt WHERE id=?");
			ps.setString(1, item.getId_special());
			count = ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return count;
	}

	@Override
	public SpecClt create(SpecClt spechar) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO specialclt (branch, id_special, id_client, value, prim ) VALUES (?,?,?,?,?)");

			ps.setString(1, spechar.getBranch());
			ps.setString(2, spechar.getId_special());
			ps.setString(3, spechar.getId_client());
			ps.setString(4, spechar.getValue());
			ps.setString(5, spechar.getPrim());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
			throw new RuntimeException("Create operation failed");
		} finally {
			ConnectionPool.close(c);
		}
		return null;
	}

	@Override
	public SpecClt getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T1 extends SpecClt> void setFilter(T1 filter) {
		this.filter = (SpecCltFilter) filter;
	}

	@Override
	public SpecClt create(Connection c, SpecClt item)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Connection c, SpecClt item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Connection c, SpecClt item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SpecClt getItem(Connection c, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpecClt getItem(Connection c, String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
