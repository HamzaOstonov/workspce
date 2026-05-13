package com.is.clientcrm.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.clientcrm.utils.DbUtils;
import com.is.utils.RefData;

public class ReferenceService {
    private String choice;
    private String sql_b = "select nat_id data, nat_name label from s_nation where act <> 'Z'"; 
    private String col_1="nat_id";
    private String col_2="nat_name";
    
	public ReferenceService() {
		// TODO Auto-generated constructor stub
	}
	public ReferenceService(String choice) {
		this.setChoice(choice);
	}
	
	public List<RefData> getDataFl(String psql_b, String pcol_1, String pcol_2, RefData filter) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RefData> list = new ArrayList<RefData>();

		
		try {
			c = ConnectionPool.getConnection();
			if (filter.getData() == null)
				filter.setData("");
			if (filter.getLabel() == null)
				filter.setLabel("");
			
			String sql = psql_b;
			
			if (!filter.getData().isEmpty()){
				if (!sql.contains("where"))
					sql = sql + " where "+pcol_1+" like ?";
				else
				  sql = sql + " and "+pcol_1+" like ?";
			}
				
			if (!filter.getLabel().isEmpty()){
				if (!sql.contains("where"))
					sql = sql + " where upper("+pcol_2+") like ?";
				else
				  sql = sql + " and upper("+pcol_2+") like ?";
			}
			
			sql = sql + "order by "+pcol_1;
			
			ps = c.prepareStatement(sql);
			int pp = 0;
			if (!filter.getData().isEmpty()) {
				pp = pp + 1;
				String tmp = filter.getData().toUpperCase().replace("*", "%");
				if (!tmp.contains("%")) 
					tmp="%"+tmp+"%";
				ps.setString(pp, tmp);
			}
			if (!filter.getLabel().isEmpty()) {
				pp = pp + 1;
				String tmp = filter.getLabel().toUpperCase().replace("*", "%");
				if (!tmp.contains("%")) 
					tmp="%"+tmp+"%";
				ps.setString(pp, tmp);
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}

		return list;
	}

	public int getDataCountFl(int pageIndex, int pageSize, Reference filter) {
		int count = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
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
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}

		return count;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getChoice() {
		return choice;
	}
	public void setSql_b(String sql_b) {
		this.sql_b = sql_b;
	}
	public String getSql_b() {
		return sql_b;
	}
	public void setCol_1(String col_1) {
		this.col_1 = col_1;
	}
	public String getCol_1() {
		return col_1;
	}
	public void setCol_2(String col_2) {
		this.col_2 = col_2;
	}
	public String getCol_2() {
		return col_2;
	}

}
