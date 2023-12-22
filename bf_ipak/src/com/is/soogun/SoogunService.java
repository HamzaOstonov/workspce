package com.is.soogun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.customer_.core.utils.GeneralUtils;

public class SoogunService {

	public SoogunService() {
		// TODO Auto-generated constructor stub
	}

	public List<Soogun> getSoogunFl(int pageIndex, int pageSize, Soogun filter) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Soogun> list = new ArrayList<Soogun>();
		//int v_lowerbound = pageIndex + 1;
		int v_lowerbound = pageIndex * pageSize + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		
		// ddd
		// dictionaryKeeper.getSoogun());
		try {
			c = ConnectionPool.getConnection();
			// if (filter == null || (filter.getData().isEmpty() &&
			// filter.getLabel().isEmpty()))
			// list = ClientDictionaries.getSoogun(c, "");
			// else {
			// Statement s = c.createStatement();
			if (filter.getData() == null)
				filter.setData("");
			if (filter.getLabel() == null)
				filter.setLabel("");
			// String sql = "select soogu data,soogu1 label from s_soogun where
			// act <> 'Z'";
			String sql = "select * from (select rownum rownm,t.soogu data, t.soogu1 label from (select * from s_soogun where act <> 'Z'";
			// продолжение ") t ) b where b.rownm between 1 and 105"

			// select t.* from (select k.*,rownum rwnm from (select * from
			// (select user_name from users) ) k where rownum <= 9) t where
			// t.rwnm >= 7 order by 1;
			sql = "select t.* from (select k.*,rownum rwnm from (select soogu data, soogu1 label from (select /*+INDEX(s_soogun XUK_S_SOOGUN)*/ * from s_soogun where act <> 'Z'";
			// продолжение ") ) k where rownum <= 9) t where t.rwnm >= 7 order
			// by 1"
			if (!filter.getData().isEmpty())
				sql = sql + " and soogu like ?";
			if (!filter.getLabel().isEmpty())
				sql = sql + " and upper(soogu1) like ?";
			sql = sql + ") ) k where rownum <= ?) t where t.rwnm >= ? order by data";
			ps = c.prepareStatement(sql);
			int pp = 0;
			if (!filter.getData().isEmpty()) {
				pp = pp + 1;
				ps.setString(pp, filter.getData().toUpperCase().replace("*", "%"));
			}
			if (!filter.getLabel().isEmpty()) {
				pp = pp + 1;
				ps.setString(pp, filter.getLabel().toUpperCase().replace("*", "%"));
			}
			pp = pp + 1;
			ps.setInt(pp, v_upperbound);
			pp = pp + 1;
			ps.setInt(pp, v_lowerbound);

			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Soogun(rs.getString("data"), rs.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			GeneralUtils.closeStatement(ps);
			GeneralUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}

		return list;
	}

	public int getSoogunCountFl(int pageIndex, int pageSize, Soogun filter) {
		int count = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// List<Soogun> list = new ArrayList<Soogun>();
		// ddd
		// dictionaryKeeper.getSoogun());
		try {
			c = ConnectionPool.getConnection();
			// if (filter == null || (filter.getData().isEmpty() &&
			// filter.getLabel().isEmpty()))
			// list = ClientDictionaries.getSoogun(c, "");
			// else {
			// Statement s = c.createStatement();
			if (filter.getData() == null)
				filter.setData("");
			if (filter.getLabel() == null)
				filter.setLabel("");
			String sql = "select count(*) from s_soogun where act <> 'Z'";
			if (!filter.getData().isEmpty())
				sql = sql + " and soogu like ?";
			if (!filter.getLabel().isEmpty())
				sql = sql + " and upper(soogu1) like ?";
			ps = c.prepareStatement(sql + " order by soogu");
			int pp = 0;
			if (!filter.getData().isEmpty()) {
				pp = pp + 1;
				ps.setString(pp, filter.getData().toUpperCase().replace("*", "%"));
			}
			if (!filter.getLabel().isEmpty()) {
				pp = pp + 1;
				ps.setString(pp, filter.getLabel().toUpperCase().replace("*", "%"));
			}
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

			// }
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			GeneralUtils.closeStatement(ps);
			GeneralUtils.closeResultSet(rs);
			ConnectionPool.close(c);
		}

		return count;
	}

}
