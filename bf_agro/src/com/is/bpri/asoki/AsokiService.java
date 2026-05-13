package com.is.bpri.asoki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;

public class AsokiService {

	public static List<Asoki> getAsokiModel(String niki_id, String branch, String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Asoki> list = new ArrayList<Asoki>();
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select id, request_id, niki_id, request_date, state_name, branch from " +
					"(select id, request_id, niki_id, request_date, state_name, branch from asoki_01 " +
					"union all " +
					"(select id, request_id, niki_id, request_date, state_name, branch from asoki_02) " +
					"union all " +
					"(select id, request_id, niki_id, request_date, state_name, branch from asoki_01_arh) " +
					"union all " +
					"(select id, request_id, niki_id, request_date, state_name, branch from asoki_02_arh)) s where niki_id = ? and branch = ?");
			ps.setString(1, niki_id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Asoki(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						rs.getString(6),
						rs.getDate(4),
						rs.getString(5)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
}
